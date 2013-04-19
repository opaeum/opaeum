package org.eclipse.uml2.uml;
import java.util.Set;

public interface INakedActivityEdge extends GuardedFlow,INakedElementOwner,Comparable<INakedActivityEdge>{
	int getIndexInOutgoing();
	INakedActivityNode getSource();	
	void setSource(INakedActivityNode source);
	INakedActivityNode getTarget();
	/**
	 * If the target is a pin belonging to an action, returns the action 
	 * @return
	 */
	INakedActivityNode getEffectiveTarget();
	void setTarget(INakedActivityNode target);
	INakedValueSpecification getGuard();
	void setGuard(INakedValueSpecification guardExpresion);
	boolean isFromExceptionPin();
	void setWeight(INakedValueSpecification weight);
	INakedValueSpecification getWeight();
	INakedActivityNode getEffectiveSource();
	INakedActivity getActivity();
	Set<INakedActivityEdge> getRedefinedEdges();
	
}