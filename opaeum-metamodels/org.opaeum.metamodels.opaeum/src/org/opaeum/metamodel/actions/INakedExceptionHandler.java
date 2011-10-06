package org.opaeum.metamodel.actions;

import java.util.Collection;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;

public interface INakedExceptionHandler extends INakedElement{
	Collection<INakedClassifier> getExceptionTypes();
	void setExceptionTypes(Collection<INakedClassifier> t);
	INakedObjectNode getExceptionInput();
	void setExceptionInput(INakedObjectNode i);
	INakedAction getHandlerBody();
	void setHandlerBody(INakedAction a);
}
