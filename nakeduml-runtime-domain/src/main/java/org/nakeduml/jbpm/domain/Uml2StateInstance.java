package org.nakeduml.jbpm.domain;

import java.util.List;


import org.drools.definition.process.Connection;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.jbpm.workflow.instance.node.StateNodeInstance;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;

public class Uml2StateInstance extends StateNodeInstance implements UmlNodeInstance {
	public void takeTransition(String name, TransitionListener listener) {
		for (List<Connection> list : getStateNode().getOutgoingConnections().values()) {
			for (Connection connection : list) {
				if (connection.getTo().getName().equals(name)) {
					triggerEvent(ExtendedNodeImpl.EVENT_NODE_EXIT);
					if (listener != null) {
						listener.onTransition();
					}
					((org.jbpm.workflow.instance.NodeInstanceContainer) getNodeInstanceContainer()).removeNodeInstance(this);
					triggerConnection(connection);
					return;
				}
			}
		}
	}

	@Override
	public void takeTransition(String targetNodeName) {
		takeTransition(targetNodeName, null);
	}
}
