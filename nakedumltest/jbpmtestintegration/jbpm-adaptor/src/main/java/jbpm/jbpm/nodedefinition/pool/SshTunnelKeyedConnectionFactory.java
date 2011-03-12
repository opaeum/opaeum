package jbpm.jbpm.nodedefinition.pool;

import jbpm.jbpm.nodedefinition.EISConnection;
import jbpm.jbpm.nodedefinition.EricssonManagedConnection;
import jbpm.jbpm.nodedefinition.HuaweiM2000ManagedConnection;
import jbpm.jbpm.nodedefinition.HuaweiManagedConnection;
import jbpm.jbpm.nodedefinition.NodeDefinitionWrapper;
import jbpm.jbpm.rip.HuaweiNodeDefinition;
import jbpm.jbpm.rip.NodeDefinition;

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
		NodeDefinitionWrapper wrapper = (NodeDefinitionWrapper) key;
		NodeDefinition nodeDefinition = wrapper.getNodeDefinition();
		if (nodeDefinition.getRequiresSshTunnel()) {
			if (!sessionManager.containsSession(nodeDefinition.getSshTunnelSpec().getHost())) {
				sessionManager.createSessionAndPortForward(nodeDefinition);
				logger.info("port forwarding done for session  for node " + nodeDefinition.getName() + ", application "
						+ nodeDefinition.getNetworkSoftwareVersion().getNetwork().getApplication().getName() + ", network " + nodeDefinition.getNetworkSoftwareVersion().getNetwork().getName() + " on port "
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
			switch (nodeDefinition.getNetworkSoftwareVersion().getSoftwareVersion()) {
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
		NodeDefinitionWrapper wrapper = (NodeDefinitionWrapper) key;
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
