package net.sf.nakeduml.metamodel.statemachines;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedEntity;
public interface INakedStateMachine extends IRegionOwner, INakedTriggerContainer,INakedBehavior{

	boolean hasEntityContext();
	INakedEntity getContext();
	List<INakedTransition> getTransitions();
	StateMachineKind getStateMachineKind();
	void setStateMachineKind(StateMachineKind k);
}