package jbpm.jbpm.nodedefinition;

import jbpm.jbpm.rip.NodeDefinition;

public class NodeConnectionSpecWrapper {

	public NodeDefinition getNodeDefinition() {
		return nodeDefinition;
	}

	private NodeDefinition nodeDefinition;

	public NodeConnectionSpecWrapper(NodeDefinition nodeDefinition) {
		super();
		this.nodeDefinition = nodeDefinition;
	}
	
	public String getSshHost() {
		return nodeDefinition.getSshTunnelSpec().getHost();
	}

	@Override
	public int hashCode() {
		return nodeDefinition.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof NodeConnectionSpecWrapper)) {
			return false;
		}
		NodeConnectionSpecWrapper other = (NodeConnectionSpecWrapper)obj;
		return nodeDefinition.equals(other.getNodeDefinition());
	}
	
}
