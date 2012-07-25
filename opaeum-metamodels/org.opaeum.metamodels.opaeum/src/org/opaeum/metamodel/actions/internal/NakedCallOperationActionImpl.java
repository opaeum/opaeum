package org.opaeum.metamodel.actions.internal;

import org.eclipse.uml2.uml.INakedCallOperationAction;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedMessageStructure;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.IParameterOwner;

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
		if(getOperation().getMessageStructure()!=null){
			return getOperation().getMessageStructure();
		}
		return null;
	}
	public void initMessageStructure(){
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
