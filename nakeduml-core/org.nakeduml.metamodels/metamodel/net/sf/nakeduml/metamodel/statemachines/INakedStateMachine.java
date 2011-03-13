package net.sf.nakeduml.metamodel.statemachines;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
public interface INakedStateMachine extends IRegionOwner, INakedTriggerContainer,INakedBehavior{

	boolean hasTriggerFor(INakedOperation o);
	boolean hasEntityContext();
	INakedEntity getContext();
	List<INakedTransition> getTransitionsFrom(INakedState fromState);
	List<INakedTransition> getTransitionsTo(INakedState fromState);
	List<INakedTransition> getTransitions();
	StateMachineKind getStateMachineKind();
	void setStateMachineKind(StateMachineKind k);
}