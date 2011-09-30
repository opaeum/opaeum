package org.opeum.metamodel.statemachines;
import java.util.List;

import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedTriggerContainer;
import org.opeum.metamodel.core.INakedEntity;
public interface INakedStateMachine extends IRegionOwner, INakedTriggerContainer,INakedBehavior{

	boolean hasEntityContext();
	INakedEntity getContext();
	List<INakedTransition> getTransitions();
	StateMachineKind getStateMachineKind();
	void setStateMachineKind(StateMachineKind k);
}