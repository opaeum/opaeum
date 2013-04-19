package org.eclipse.uml2.uml;

import java.util.Collection;


public interface INakedExceptionHandler extends INakedElement{
	Collection<INakedClassifier> getExceptionTypes();
	void setExceptionTypes(Collection<INakedClassifier> t);
	INakedObjectNode getExceptionInput();
	void setExceptionInput(INakedObjectNode i);
	INakedAction getHandlerBody();
	void setHandlerBody(INakedAction a);
}
