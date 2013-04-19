package org.opaeum.metamodel.actions.internal;

import java.util.Collection;

import org.eclipse.uml2.uml.INakedAction;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedExceptionHandler;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.opaeum.metamodel.core.internal.NakedElementImpl;

public class NakedExceptionHandlerImpl extends NakedElementImpl implements INakedExceptionHandler{
	/**
	 * 
	 */
	private static final long serialVersionUID = -522395170986184586L;
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
