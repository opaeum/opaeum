package net.sf.nakeduml.metamodel.activities;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
public interface INakedActivityEdge extends GuardedFlow,INakedElementOwner{
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