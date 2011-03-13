package jbpm.jbpm.nodedefinition.pool;

import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import jbpm.jbpm.nodedefinition.NodeDefinitionWrapper;
import jbpm.jbpm.nodedefinition.pool.SessionManager.NodeDefinitionCount;
import jbpm.jbpm.rip.NodeDefinition;

import org.apache.commons.pool.PoolUtils;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPool.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class EisPool {

	Logger logger = LoggerFactory.getLogger(EisPool.class);
	
	private GenericKeyedObjectPool pool;
	private Integer maxTotal;
	private Integer maxActive;
	private Integer maxIdle;
	private Integer minIdle;
	private Long timeBetweenEvictionRunsMillis;
	private SshTunnelKeyedConnectionFactory keyedFactory;

	@Inject
	public EisPool(List<NodeDefinition> nodeDefinitions, SshTunnelKeyedConnectionFactory keyedFactory) {
		super();
		maxTotal = 10;
		maxActive = 1;
		maxIdle = 0;
		minIdle = 0;
		timeBetweenEvictionRunsMillis = 2000L;
		Config config = new Config();
		config.maxTotal = this.maxTotal;
		config.maxActive = this.maxActive;
		config.maxIdle = this.maxIdle;
		config.minIdle = this.minIdle;
		config.timeBetweenEvictionRunsMillis = this.timeBetweenEvictionRunsMillis;
		this.keyedFactory = keyedFactory; 
		pool = new GenericKeyedObjectPool(PoolUtils.synchronizedPoolableFactory(keyedFactory),config);
		for (NodeDefinition nodeDefinition : nodeDefinitions) {
			pool.preparePool(new NodeDefinitionWrapper(nodeDefinition), false);
		}
	}
	
	@PreDestroy
	public void stop() {
		logger.debug("stop EisPoolService");
		try {
			pool.close();
		} catch (Exception e) {
			logger.error("error closing EisPool", e);
		}
	}

	@Produces
	public GenericKeyedObjectPool getPool() {
		return pool;
	}

	public Integer getNumActive() {
		return pool.getNumActive();
	}

	public Integer getNumIdle() {
		return pool.getNumIdle();
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxActive() {
		return pool.getMaxActive();
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Integer getMaxIdle() {
		return pool.getMaxIdle();
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMinIdle() {
		return pool.getMinIdle();
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Long getTimeBetweenEvictionRunsMillis() {
		return pool.getTimeBetweenEvictionRunsMillis();
	}

	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public Integer getNumSshSessions() {
		return keyedFactory.getSessionManager().getSessionMap().size();
	}

	public String getSshSessionInfo() {
		StringBuilder sb = new StringBuilder();
		Map<String, List<NodeDefinitionCount>> sessionPortList = keyedFactory.getSessionManager().getSessionPortCount();
		for (String sshHost : sessionPortList.keySet()) {
			sb.append(sshHost);
			sb.append("\n");
			List<NodeDefinitionCount> lportCountMap = sessionPortList.get(sshHost);
			for (NodeDefinitionCount nodeConnectionSpecCount : lportCountMap) {
				sb.append("    lport = ");
				sb.append(nodeConnectionSpecCount.getLport());
				sb.append(" :: ");
				sb.append("name = ");
				sb.append(nodeConnectionSpecCount.getName());
				sb.append(" :: ");
				sb.append("count = ");
				sb.append(nodeConnectionSpecCount.getCount());
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public void setKeyedFactory(SshTunnelKeyedConnectionFactory keyedFactory) {
		this.keyedFactory = keyedFactory;
	}

}
