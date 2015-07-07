package com.tliu3.demo.jmx;

import javax.management.MXBean;

import org.hibernate.stat.Statistics;

@MXBean
public interface HibernateStatisticsService extends Statistics {
	String getCollectionRoleNamesText();

	String getEntityNamesText();

	String getQueriesText();

	String getSecondLevelCacheRegionNamesText();
}
