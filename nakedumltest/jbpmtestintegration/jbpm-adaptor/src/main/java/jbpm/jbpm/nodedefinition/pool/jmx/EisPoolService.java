package jbpm.jbpm.nodedefinition.pool.jmx;

import jbpm.jbpm.nodedefinition.pool.EisPool;


//@Service (objectName = "com.rorotika:service=EisPoolService")
//@TransactionManagement(TransactionManagementType.BEAN)
public class EisPoolService implements IEisPoolService {

	private EisPool eisPoolSingleton;
	
	@Override
	public void create() throws Exception {
	}

	@Override
	public void start() throws Exception {
	}

	@Override
	public void stop() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public Integer getNumActive() {
		return eisPoolSingleton.getNumActive();
	}

	@Override
	public Integer getNumIdle() {
		return eisPoolSingleton.getNumIdle();
	}

	public Integer getMaxTotal() {
		return eisPoolSingleton.getMaxTotal();
	}

	public void setMaxTotal(Integer maxTotal) {
		eisPoolSingleton.setMaxTotal(maxTotal);
	}

	public Integer getMaxActive() {
		return eisPoolSingleton.getMaxActive();
	}

	public void setMaxActive(Integer maxActive) {
		eisPoolSingleton.setMaxActive(maxActive);
	}

	public Integer getMaxIdle() {
		return eisPoolSingleton.getMaxIdle();
	}

	public void setMaxIdle(Integer maxIdle) {
		eisPoolSingleton.setMaxIdle(maxIdle);
	}

	public Integer getMinIdle() {
		return eisPoolSingleton.getMinIdle();
	}

	public void setMinIdle(Integer minIdle) {
		eisPoolSingleton.setMinIdle(minIdle);
	}

	public Long getTimeBetweenEvictionRunsMillis() {
		return eisPoolSingleton.getTimeBetweenEvictionRunsMillis();
	}

	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
		eisPoolSingleton.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
	}

	@Override
	public Integer getNumSshSessions() {
		return eisPoolSingleton.getNumSshSessions();
	}

	@Override
	public String getSshSessionInfo() {
		return eisPoolSingleton.getSshSessionInfo();
	}

}
