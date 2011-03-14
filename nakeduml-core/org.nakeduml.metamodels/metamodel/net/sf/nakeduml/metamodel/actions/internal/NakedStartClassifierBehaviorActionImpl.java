package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedStartClassifierBehaviorAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;

public class NakedStartClassifierBehaviorActionImpl extends NakedInvocationActionImpl implements INakedStartClassifierBehaviorAction{
	private static final long serialVersionUID = -236758123440467617L;
	public ActionType getActionType(){
		return ActionType.START_CLASSIFIER_BEHAVIOR_ACTION;
	}
	public Collection<INakedOutputPin> getOutput() {
		return new HashSet<INakedOutputPin>();
	}
	
}
