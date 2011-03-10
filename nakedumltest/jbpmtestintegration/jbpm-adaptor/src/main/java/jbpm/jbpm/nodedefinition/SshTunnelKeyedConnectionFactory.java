package jbpm.jbpm.nodedefinition;

import jbpm.jbpm.rip.HuaweiNodeDefinition;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.Technology;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SshTunnelKeyedConnectionFactory extends BaseKeyedPoolableObjectFactory {

	private static Logger logger = LoggerFactory.getLogger(SshTunnelKeyedConnectionFactory.class);
	private SessionManager sessionManager;

	public SshTunnelKeyedConnectionFactory() {
		super();
		sessionManager = new SessionManager();
	}

	@Override
	public Object makeObject(Object key) throws Exception {
		NodeConnectionSpecWrapper wrapper = (NodeConnectionSpecWrapper) key;
		NodeDefinition nodeDefinition = wrapper.getNodeDefinition();
		if (nodeDefinition.getRequiresSshTunnel()) {
			if (!sessionManager.containsSession(nodeDefinition.getSshTunnelSpec().getHost())) {
				sessionManager.createSessionAndPortForward(nodeDefinition);
				logger.info("port forwarding done for session  for node " + nodeDefinition.getName() + ", application "
						+ nodeDefinition.getNetwork().getApplication().getName() + ", network " + nodeDefinition.getNetwork().getName() + " on port "
						+ nodeDefinition.getSshTunnelSpec().getLport());
			} else {
				// Increment
				if (!sessionManager.containsPortForward(nodeDefinition)) {
					sessionManager.setPortForwardingL(nodeDefinition);
				} else {
					sessionManager.increment(nodeDefinition);
				}
			}
		}
		try {
			switch (nodeDefinition.getSoftwareVersion()) {
			case R12:
				return new EricssonManagedConnection(nodeDefinition);
			case BSC_6K:
				HuaweiNodeDefinition huaweiNodeDefinition = (HuaweiNodeDefinition)nodeDefinition;
				if (huaweiNodeDefinition.isM2000()) {
					return new HuaweiM2000ManagedConnection(nodeDefinition);
				} else {
					return new HuaweiManagedConnection(nodeDefinition);
				}
			default:
				throw new IllegalStateException("Vendor Technology not yet supported!");
			}
		} catch (Exception e) {
			logger.error("makeObject exception", e);
			if (nodeDefinition.getRequiresSshTunnel()) {
				sessionManager.destroy(nodeDefinition);
			}
			throw e;
		}
	}

	@Override
	public void destroyObject(Object key, Object obj) throws Exception {
		NodeConnectionSpecWrapper wrapper = (NodeConnectionSpecWrapper) key;
		NodeDefinition nodeConnectionSpec = wrapper.getNodeDefinition();
		EISConnection ericssonManagedConnection = (EISConnection) obj;
		if (nodeConnectionSpec.getRequiresSshTunnel()) {
			// Decrement
			sessionManager.destroy(nodeConnectionSpec);
		}
		ericssonManagedConnection.destroy();
	}

	@Override
	public boolean validateObject(Object key, Object obj) {
		EISConnection ericssonManagedConnection = (EISConnection) obj;
		return ericssonManagedConnection.isValid();
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

}
