package org.opaeum.metamodel.actions.internal;

import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.IParameterOwner;

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
	public IParameterOwner getCalledElement(){
		return getOperation();
	}
	@Override
	public INakedMessageStructure getMessageStructure(){
		return messageStructure;
	}
	public void initMessageStructure(){
		if(messageStructure == null && getOperation() != null){
			if(getOperation().getMessageStructure()==null){
				getOperation().initMessageStructure();
			}
			messageStructure = getOperation().getMessageStructure();
		}
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
