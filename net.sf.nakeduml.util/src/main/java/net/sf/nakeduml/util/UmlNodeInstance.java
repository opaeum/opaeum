package net.sf.nakeduml.util;

public interface UmlNodeInstance {
	public void takeTransition(String targetNodeName, TransitionListener listener) ;
	public void takeTransition(String targetNodeName) ;
}
