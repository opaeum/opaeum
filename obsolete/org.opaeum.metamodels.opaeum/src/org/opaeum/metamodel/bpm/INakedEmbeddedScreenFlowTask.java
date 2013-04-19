package org.opaeum.metamodel.bpm;

import org.eclipse.uml2.uml.INakedCallBehaviorAction;
import org.eclipse.uml2.uml.INakedStateMachine;

public interface INakedEmbeddedScreenFlowTask extends INakedCallBehaviorAction,INakedEmbeddedTask{
	INakedStateMachine getScreenFlow();

}
