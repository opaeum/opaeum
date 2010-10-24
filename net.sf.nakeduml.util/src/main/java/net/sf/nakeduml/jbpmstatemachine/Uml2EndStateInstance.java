package net.sf.nakeduml.jbpmstatemachine;

import java.util.List;

import net.sf.nakeduml.util.TransitionListener;
import net.sf.nakeduml.util.UmlNode;

import org.drools.definition.process.Connection;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.jbpm.workflow.instance.node.EndNodeInstance;
import org.jbpm.workflow.instance.node.StateNodeInstance;

public class Uml2EndStateInstance extends EndNodeInstance implements UmlNode {
	public void takeTransition(String name, TransitionListener listener) {
		for (List<Connection> list : getEndNode().getOutgoingConnections().values()) {
			for (Connection connection : list) {
				if (connection.getTo().getName().equals(name)) {
					triggerEvent(ExtendedNodeImpl.EVENT_NODE_EXIT);
					listener.onTransition();
					((org.jbpm.workflow.instance.NodeInstanceContainer) getNodeInstanceContainer()).removeNodeInstance(this);
					triggerConnection(connection);
					return;
				}
			}
		}
	}
}
