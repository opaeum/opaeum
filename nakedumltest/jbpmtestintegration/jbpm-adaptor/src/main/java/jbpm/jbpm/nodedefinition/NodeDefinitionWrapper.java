package jbpm.jbpm.nodedefinition;

import jbpm.jbpm.rip.NodeDefinition;

public class NodeDefinitionWrapper {

	public NodeDefinition getNodeDefinition() {
		return nodeDefinition;
	}

	private NodeDefinition nodeDefinition;

	public NodeDefinitionWrapper(NodeDefinition nodeDefinition) {
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
		if (obj == null || !(obj instanceof NodeDefinitionWrapper)) {
			return false;
		}
		NodeDefinitionWrapper other = (NodeDefinitionWrapper)obj;
		return nodeDefinition.equals(other.getNodeDefinition());
	}
	
}
