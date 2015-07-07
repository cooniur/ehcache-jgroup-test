package com.tliu3.demo;

import java.lang.management.ManagementFactory;
import java.util.Map;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tliu3.demo.jmx.HibernateStatisticsServiceDelegate;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;

@EnableCaching
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		// Serialize `LocalDateTime` to string; otherwise it'll be serialized to an array
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return builder.build();
	}

	@Bean
	public HttpMessageConverters customConverters() {
		return new HttpMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper()));
	}

	@Configuration
	static class HibernateMBeansConfig {
		@Bean
		@Autowired
		public InitializingBean hibernateStatistics(EntityManagerFactory entityManagerFactory) {
			return () -> {
				MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
				ObjectName objectName = new ObjectName("Hibernate:type=statistics");
				mBeanServer.registerMBean(new HibernateStatisticsServiceDelegate(entityManagerFactory.unwrap(SessionFactory.class).getStatistics()),
						objectName);
			};
		}
	}

	@Configuration
	static class EhCacheMBeansConfig {
		@Bean
		public InitializingBean registerEhCacheMBeans() {
			return () -> {
				MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
				CacheManager.ALL_CACHE_MANAGERS.stream().forEach(c -> {
					ManagementService.registerMBeans(c, mBeanServer, true, true, true, true, true);
				});
			};
		}
	}

	@Configuration
	static class SpringCacheConfig {
		@Autowired
		private JpaProperties jpaProperties;

		@Bean
		public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
			EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
			bean.setConfigLocation(new ClassPathResource(jpaProperties.getProperties().get("net.sf.ehcache.configurationResourceName")));
			bean.setShared(true);
			return bean;
		}

		@Bean
		public org.springframework.cache.CacheManager cacheManager() {
			return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
		}
	}

	@Configuration
	static class CustomHibernateJpaConfiguration extends HibernateJpaAutoConfiguration {
		@Override
		protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
			vendorProperties.put("hibernate.cache.region.factory_class", EhCacheRegionFactory.class.getName());
			super.customizeVendorProperties(vendorProperties);
		}
	}
}
