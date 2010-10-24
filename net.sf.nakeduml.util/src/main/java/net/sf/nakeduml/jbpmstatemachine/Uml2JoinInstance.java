package net.sf.nakeduml.jbpmstatemachine;

import java.util.List;

import net.sf.nakeduml.util.TransitionListener;
import net.sf.nakeduml.util.UmlNode;

import org.drools.definition.process.Connection;
import org.drools.reteoo.JoinNode;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.jbpm.workflow.instance.node.EndNodeInstance;
import org.jbpm.workflow.instance.node.JoinInstance;

public class Uml2JoinInstance extends JoinInstance implements UmlNode {
	public void takeTransition(String name, TransitionListener listener) {
		for (List<Connection> list : getJoin().getOutgoingConnections().values()) {
			for (Connection connection : list) {
				if (connection.getTo().getName().equals(name)) {
					listener.onTransition();
					((org.jbpm.workflow.instance.NodeInstanceContainer) getNodeInstanceContainer()).removeNodeInstance(this);
					triggerConnection(connection);
					return;
				}
			}
		}
	}
}
