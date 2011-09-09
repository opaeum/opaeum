package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedCallEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;

public class NakedCallEventImpl extends NakedEventImpl implements INakedCallEvent{
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
