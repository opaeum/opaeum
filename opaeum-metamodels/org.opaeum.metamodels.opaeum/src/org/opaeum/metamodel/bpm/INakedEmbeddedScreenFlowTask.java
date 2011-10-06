package org.opaeum.metamodel.bpm;

import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.statemachines.INakedStateMachine;

public interface INakedEmbeddedScreenFlowTask extends INakedCallBehaviorAction,INakedEmbeddedTask{
	INakedStateMachine getScreenFlow();

}
