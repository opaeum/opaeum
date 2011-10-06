package org.opaeum.metamodel.commonbehaviors.internal;

import java.util.List;

import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedTypedElement;

public class NakedCallEventImpl extends NakedEventImpl implements INakedCallEvent{
	private static final long serialVersionUID = -4805579769388763854L;
	INakedOperation operation;
	public INakedOperation getOperation(){
		return operation;
	}
	public void setOperation(INakedOperation operation){
		this.operation = operation;
	}
	@Override
	public INakedClassifier getContext(){
		return null;
	}
	@Override
	public List<? extends INakedTypedElement> getEventParameters(){
		return getOperation().getArgumentParameters();
	}
	@Override
	public String getMetaClass(){
		return "signalEvent";
	}
}
