package net.sf.nakeduml.metamodel.actions.internal;
import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
public class NakedCallBehaviorActionImpl extends NakedCallActionImpl implements INakedCallBehaviorAction {
	private static final long serialVersionUID = -236758123440467617L;
	private INakedBehavior behavior;
	private boolean isAutomated;
	public boolean isAutomated() {
		return this.isAutomated;
	}
	public void setAutomated(boolean isAutomated) {
		this.isAutomated = isAutomated;
	}
	public INakedBehavior getBehavior() {
		return this.behavior;
	}
	public void setBehavior(INakedBehavior operation) {
		this.behavior = operation;
	}
	public ActionType getActionType() {
		return ActionType.CALL_OPERATION_ACTION;
	}
	public IParameterOwner getCalledElement() {
		return getBehavior();
	}
	@Override
	public INakedMessageStructure getMessageStructure(){
		return getBehavior();
	}
	@Override
	public boolean isTask() {
		return false;
	}

}
