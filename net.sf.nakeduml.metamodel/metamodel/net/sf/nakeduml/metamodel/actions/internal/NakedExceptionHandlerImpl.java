package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedModelElementImpl;

public class NakedExceptionHandlerImpl extends NakedModelElementImpl implements INakedExceptionHandler{
	INakedObjectNode exceptionInput;
	INakedAction handlerBody;
	Collection<INakedClassifier> exceptionTypes;
	public INakedObjectNode getExceptionInput() {
		return exceptionInput;
	}
	public void setExceptionInput(INakedObjectNode exceptionInput) {
		this.exceptionInput = exceptionInput;
		if(exceptionInput!=null){
			exceptionInput.setIncomingExceptionHandler(this);
		}
	}
	public INakedAction getHandlerBody() {
		return handlerBody;
	}
	public void setHandlerBody(INakedAction handlerBody) {
		this.handlerBody = handlerBody;
	}
	public Collection<INakedClassifier> getExceptionTypes() {
		return exceptionTypes;
	}
	public void setExceptionTypes(Collection<INakedClassifier> exceptionTypes) {
		this.exceptionTypes = exceptionTypes;
	}
	@Override
	public String getMetaClass() {
		return "exceptionHandler";
	}
}
