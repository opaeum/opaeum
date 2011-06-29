package net.sf.nakeduml.metamodel.bpm;

import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

public interface INakedEmbeddedScreenFlowTask extends INakedCallBehaviorAction,INakedEmbeddedTask{
	INakedStateMachine getScreenFlow();

}