package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public interface INakedRaiseExceptionAction extends INakedAction {
	void setException(INakedInputPin p);
	INakedInputPin getException();
}
