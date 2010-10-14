package net.sf.nakeduml.metamodel.actions.internal;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedStartClassifierBehaviorAction;

public class NakedStartClassifierBehaviorActionImpl extends NakedInvocationActionImpl implements INakedStartClassifierBehaviorAction{
	private static final long serialVersionUID = -236758123440467617L;
	public ActionType getActionType(){
		return ActionType.CALL_OPERATION_ACTION;
	}
}
