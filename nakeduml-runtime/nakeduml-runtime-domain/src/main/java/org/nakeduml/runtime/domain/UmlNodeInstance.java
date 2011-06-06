package org.nakeduml.runtime.domain;

import org.jbpm.workflow.instance.NodeInstance;


public interface UmlNodeInstance extends NodeInstance {
	public void takeTransition(String targetNodeName, TransitionListener listener) ;
	public void takeTransition(String targetNodeName) ;
	public void triggerEvent(String event);
}
