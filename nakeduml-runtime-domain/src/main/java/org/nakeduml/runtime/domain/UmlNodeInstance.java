package org.nakeduml.runtime.domain;


public interface UmlNodeInstance {
	public void takeTransition(String targetNodeName, TransitionListener listener) ;
	public void takeTransition(String targetNodeName) ;
}
