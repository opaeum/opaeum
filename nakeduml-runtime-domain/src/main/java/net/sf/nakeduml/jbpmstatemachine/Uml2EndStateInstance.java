package net.sf.nakeduml.jbpmstatemachine;

import java.util.List;

import net.sf.nakeduml.util.TransitionListener;
import net.sf.nakeduml.util.UmlNodeInstance;

import org.drools.definition.process.Connection;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.jbpm.workflow.instance.node.EndNodeInstance;
import org.jbpm.workflow.instance.node.StateNodeInstance;

public class Uml2EndStateInstance extends EndNodeInstance implements UmlNodeInstance {
	public void takeTransition(String name, TransitionListener listener) {
		for (List<Connection> list : getEndNode().getOutgoingConnections().values()) {
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
