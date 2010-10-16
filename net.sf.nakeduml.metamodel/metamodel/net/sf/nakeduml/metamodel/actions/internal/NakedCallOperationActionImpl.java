package net.sf.nakeduml.metamodel.actions.internal;
import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
public class NakedCallOperationActionImpl extends NakedCallActionImpl implements INakedCallOperationAction {
	private static final long serialVersionUID = -236758123440467617L;
	private INakedOperation operation;
	private INakedMessageStructure messageStructure;
	public boolean isProcessCall() {
		System.out.println("NakedCallOperationActionImpl.isProcessCall()");
		return getOperation().isProcess();
	}
	@Override
	public boolean hasMessageStructure(){
		return super.hasMessageStructure() || getOperation().isUserResponsibility();
	}
	public INakedOperation getOperation() {
		return this.operation;
	}
	public void setOperation(INakedOperation operation) {
		this.operation = operation;
	}
	public ActionType getActionType() {
		return ActionType.CALL_OPERATION_ACTION;
	}
	public IParameterOwner getCalledElement() {
		return getOperation();
	}
	@Override
	public INakedMessageStructure getMessageStructure() {
		if (messageStructure == null) {
			if (getOperation().getMethods().size()==1) {
				messageStructure = getOperation().getMethods().iterator().next();
			} else {
				messageStructure = new OperationMessageStructureImpl(getOperation().getOwner(), getOperation());
			}
		}
		return messageStructure;
	}

	@Override
	public INakedClassifier getExpectedTargetType() {
		return getOperation().getOwner();
	}
	@Override
	public boolean isTask() {
		if(getOperation().isUserResponsibility()){
			return true;
		}else if (getOperation().getOwner() instanceof INakedInterface){
			return ((INakedInterface)getOperation().getOwner()).representsUser();
		}else{
			return false;
		}
	}

}
