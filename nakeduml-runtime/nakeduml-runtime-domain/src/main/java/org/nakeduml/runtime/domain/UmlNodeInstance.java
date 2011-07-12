package org.nakeduml.runtime.domain;

import org.drools.definition.process.Connection;
import org.jbpm.workflow.instance.NodeInstance;

public interface UmlNodeInstance extends NodeInstance{
	public void triggerEvent(String event);
	public void transitionToNode(long to,TransitionListener listener);
	public void triggerConnection(Connection connection);
	public void flowToNode(String targetNodeName);
}
