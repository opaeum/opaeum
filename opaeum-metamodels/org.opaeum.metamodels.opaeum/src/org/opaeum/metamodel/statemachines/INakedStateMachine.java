package org.opaeum.metamodel.statemachines;

import java.util.Collection;
import java.util.List;

import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedDurationObservation;
import org.opaeum.metamodel.commonbehaviors.INakedObservantElement;
import org.opaeum.metamodel.commonbehaviors.INakedTimeObservation;
import org.opaeum.metamodel.commonbehaviors.INakedTriggerContainer;
import org.opaeum.metamodel.core.INakedEntity;

public interface INakedStateMachine extends IRegionOwner,INakedTriggerContainer,INakedBehavior,INakedObservantElement{
	boolean hasEntityContext();
	INakedEntity getContext();
	List<INakedTransition> getTransitions();
	StateMachineKind getStateMachineKind();
	void setStateMachineKind(StateMachineKind k);
}