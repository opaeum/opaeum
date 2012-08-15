package org.eclipse.uml2.uml;

import java.util.Collection;
import java.util.List;


public interface INakedStateMachine extends IRegionOwner,INakedTriggerContainer,INakedBehavior,INakedObservantElement{
	boolean hasEntityContext();
	INakedEntity getContext();
	List<INakedTransition> getTransitions();
	StateMachineKind getStateMachineKind();
	void setStateMachineKind(StateMachineKind k);
}