package net.sf.nakeduml.metamodel.actions.internal;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class NakedCallOperationActionImpl extends NakedCallActionImpl implements INakedCallOperationAction{
	private static final long serialVersionUID = -236758123440467617L;
	private INakedOperation operation;
	private INakedMessageStructure messageStructure;
	public INakedOperation getOperation(){
		return this.operation;
	}
	public void setOperation(INakedOperation operation){
		this.operation = operation;
	}
	public ActionType getActionType(){
		return ActionType.CALL_OPERATION_ACTION;
	}
	public IParameterOwner getCalledElement(){
		return getOperation();
	}
	@Override
	public INakedMessageStructure getMessageStructure(IOclLibrary lib){
		if(messageStructure == null && getOperation() != null){
			messageStructure = new OperationMessageStructureImpl(getOperation().getOwner(), getOperation(), lib);
		}
		return messageStructure;
	}
	@Override
	public INakedClassifier getExpectedTargetType(){
		if(getOperation() != null){
			return getOperation().getOwner();
		}else{
			return null;
		}
	}
	@Override
	public boolean isLongRunning(){
		return getOperation() != null && getOperation().isLongRunning();
	}
}
