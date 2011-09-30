package jbpm.jbpm.nodedefinition.pool.jmx;


//@Management
public interface IEisPoolService {

	Integer getMaxTotal();

	void setMaxTotal(Integer maxTotal);

	Integer getMaxActive();

	void setMaxActive(Integer maxActive);

	Integer getMaxIdle();

	void setMaxIdle(Integer maxIdle);

	Integer getMinIdle();

	void setMinIdle(Integer minIdle);

	Long getTimeBetweenEvictionRunsMillis();

	void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis);
	
	Integer getNumActive();
	
	Integer getNumIdle();
	
	Integer getNumSshSessions();

	String getSshSessionInfo();
	
	void create() throws Exception;

	void start() throws Exception;

	void stop();

	void destroy();
}
