package com.tliu3.demo;

import java.lang.management.ManagementFactory;
import java.util.Map;
import javax.management.MBeanServer;

import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;

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

	@Bean
	public InitializingBean registerEhCacheMBeans() {
		return () -> {
			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
			CacheManager.ALL_CACHE_MANAGERS.stream().forEach(c -> {
				ManagementService.registerMBeans(c, mBeanServer, true, true, true, true, true);
			});
		};
	}

	@Configuration
	static class CustomHibernateJpaConfiguration extends HibernateJpaAutoConfiguration {

		@Override
		protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
			//			vendorProperties.put("hibernate.cache.region.factory_class", SingletonRedisRegionFactory.class.getName());
			//			vendorProperties.put("hibernate.cache.provider_configuration_file_resource_path", "hibernate-redis.properties");
			vendorProperties.put("hibernate.cache.region.factory_class", EhCacheRegionFactory.class.getName());
			super.customizeVendorProperties(vendorProperties);
		}
	}
}
