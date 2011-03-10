package net.sf.nakeduml.metamodel.actions;

import java.util.Collection;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;

public interface INakedExceptionHandler extends INakedElement{
	Collection<INakedClassifier> getExceptionTypes();
	void setExceptionTypes(Collection<INakedClassifier> t);
	INakedObjectNode getExceptionInput();
	void setExceptionInput(INakedObjectNode i);
	INakedAction getHandlerBody();
	void setHandlerBody(INakedAction a);
}
