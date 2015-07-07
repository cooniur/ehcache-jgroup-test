package com.tliu3.demo.jmx;

import org.hibernate.stat.CollectionStatistics;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.NaturalIdCacheStatistics;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;

public class HibernateStatisticsServiceDelegate implements HibernateStatisticsService {
	private final Statistics delegate;

	public HibernateStatisticsServiceDelegate(Statistics delegate) {
		this.delegate = delegate;
	}

	public void clear() {
		delegate.clear();
	}

	public long getCloseStatementCount() {
		return delegate.getCloseStatementCount();
	}

	public long getCollectionFetchCount() {
		return delegate.getCollectionFetchCount();
	}

	public long getCollectionLoadCount() {
		return delegate.getCollectionLoadCount();
	}

	public long getCollectionRecreateCount() {
		return delegate.getCollectionRecreateCount();
	}

	public long getCollectionRemoveCount() {
		return delegate.getCollectionRemoveCount();
	}

	public String[] getCollectionRoleNames() {
		return delegate.getCollectionRoleNames();
	}

	public String getCollectionRoleNamesText() {
		return String.join(", ", delegate.getCollectionRoleNames());
	}

	public CollectionStatistics getCollectionStatistics(String arg0) {
		return delegate.getCollectionStatistics(arg0);
	}

	public long getCollectionUpdateCount() {
		return delegate.getCollectionUpdateCount();
	}

	public long getConnectCount() {
		return delegate.getConnectCount();
	}

	public long getEntityDeleteCount() {
		return delegate.getEntityDeleteCount();
	}

	public long getEntityFetchCount() {
		return delegate.getEntityFetchCount();
	}

	public long getEntityInsertCount() {
		return delegate.getEntityInsertCount();
	}

	public long getEntityLoadCount() {
		return delegate.getEntityLoadCount();
	}

	public String[] getEntityNames() {
		return delegate.getEntityNames();
	}

	public String getEntityNamesText() {
		return String.join(", ", delegate.getEntityNames());
	}

	public EntityStatistics getEntityStatistics(String arg0) {
		return delegate.getEntityStatistics(arg0);
	}

	public long getEntityUpdateCount() {
		return delegate.getEntityUpdateCount();
	}

	public long getFlushCount() {
		return delegate.getFlushCount();
	}

	public long getNaturalIdCacheHitCount() {
		return delegate.getNaturalIdCacheHitCount();
	}

	public long getNaturalIdCacheMissCount() {
		return delegate.getNaturalIdCacheMissCount();
	}

	public long getNaturalIdCachePutCount() {
		return delegate.getNaturalIdCachePutCount();
	}

	public NaturalIdCacheStatistics getNaturalIdCacheStatistics(String arg0) {
		return delegate.getNaturalIdCacheStatistics(arg0);
	}

	public long getNaturalIdQueryExecutionCount() {
		return delegate.getNaturalIdQueryExecutionCount();
	}

	public long getNaturalIdQueryExecutionMaxTime() {
		return delegate.getNaturalIdQueryExecutionMaxTime();
	}

	public String getNaturalIdQueryExecutionMaxTimeRegion() {
		return delegate.getNaturalIdQueryExecutionMaxTimeRegion();
	}

	public long getOptimisticFailureCount() {
		return delegate.getOptimisticFailureCount();
	}

	public long getPrepareStatementCount() {
		return delegate.getPrepareStatementCount();
	}

	public String[] getQueries() {
		return delegate.getQueries();
	}

	public String getQueriesText() {
		return String.join(", ", delegate.getQueries());
	}

	public long getQueryCacheHitCount() {
		return delegate.getQueryCacheHitCount();
	}

	public long getQueryCacheMissCount() {
		return delegate.getQueryCacheMissCount();
	}

	public long getQueryCachePutCount() {
		return delegate.getQueryCachePutCount();
	}

	public long getQueryExecutionCount() {
		return delegate.getQueryExecutionCount();
	}

	public long getQueryExecutionMaxTime() {
		return delegate.getQueryExecutionMaxTime();
	}

	public String getQueryExecutionMaxTimeQueryString() {
		return delegate.getQueryExecutionMaxTimeQueryString();
	}

	public QueryStatistics getQueryStatistics(String arg0) {
		return delegate.getQueryStatistics(arg0);
	}

	public long getSecondLevelCacheHitCount() {
		return delegate.getSecondLevelCacheHitCount();
	}

	public long getSecondLevelCacheMissCount() {
		return delegate.getSecondLevelCacheMissCount();
	}

	public long getSecondLevelCachePutCount() {
		return delegate.getSecondLevelCachePutCount();
	}

	public String[] getSecondLevelCacheRegionNames() {
		return delegate.getSecondLevelCacheRegionNames();
	}

	public String getSecondLevelCacheRegionNamesText() {
		return String.join(", ", delegate.getSecondLevelCacheRegionNames());
	}

	public SecondLevelCacheStatistics getSecondLevelCacheStatistics(String arg0) {
		return delegate.getSecondLevelCacheStatistics(arg0);
	}

	public long getSessionCloseCount() {
		return delegate.getSessionCloseCount();
	}

	public long getSessionOpenCount() {
		return delegate.getSessionOpenCount();
	}

	public long getStartTime() {
		return delegate.getStartTime();
	}

	public long getSuccessfulTransactionCount() {
		return delegate.getSuccessfulTransactionCount();
	}

	public long getTransactionCount() {
		return delegate.getTransactionCount();
	}

	public long getUpdateTimestampsCacheHitCount() {
		return delegate.getUpdateTimestampsCacheHitCount();
	}

	public long getUpdateTimestampsCacheMissCount() {
		return delegate.getUpdateTimestampsCacheMissCount();
	}

	public long getUpdateTimestampsCachePutCount() {
		return delegate.getUpdateTimestampsCachePutCount();
	}

	public boolean isStatisticsEnabled() {
		return delegate.isStatisticsEnabled();
	}

	public void logSummary() {
		delegate.logSummary();
	}

	public void setStatisticsEnabled(boolean arg0) {
		delegate.setStatisticsEnabled(arg0);
	}
}
