package org.opeum.metamodel.bpm;

import org.opeum.metamodel.actions.INakedCallBehaviorAction;
import org.opeum.metamodel.statemachines.INakedStateMachine;

public interface INakedEmbeddedScreenFlowTask extends INakedCallBehaviorAction,INakedEmbeddedTask{
	INakedStateMachine getScreenFlow();

}
