package org.nakeduml.jbpm.domain;

import java.util.List;


import org.drools.definition.process.Connection;
import org.drools.reteoo.JoinNode;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.jbpm.workflow.instance.node.EndNodeInstance;
import org.jbpm.workflow.instance.node.JoinInstance;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;

public class Uml2JoinInstance extends JoinInstance implements UmlNodeInstance {
	public void takeTransition(String name, TransitionListener listener) {
		for (List<Connection> list : getJoin().getOutgoingConnections().values()) {
			for (Connection connection : list) {
				if (connection.getTo().getName().equals(name)) {
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
	public void triggerEvent(String type) {
	}

}
