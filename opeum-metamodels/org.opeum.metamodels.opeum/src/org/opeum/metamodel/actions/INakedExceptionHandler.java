package org.opeum.metamodel.actions;

import java.util.Collection;

import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedObjectNode;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;

public interface INakedExceptionHandler extends INakedElement{
	Collection<INakedClassifier> getExceptionTypes();
	void setExceptionTypes(Collection<INakedClassifier> t);
	INakedObjectNode getExceptionInput();
	void setExceptionInput(INakedObjectNode i);
	INakedAction getHandlerBody();
	void setHandlerBody(INakedAction a);
}
