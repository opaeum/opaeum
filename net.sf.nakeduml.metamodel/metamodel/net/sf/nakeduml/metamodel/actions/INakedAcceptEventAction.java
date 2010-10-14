package net.sf.nakeduml.metamodel.actions;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
public interface INakedAcceptEventAction extends INakedAction {
	INakedElement getEvent();
	void setEvent(INakedElement trigger);
	List<INakedOutputPin> getResult();
	List<INakedTypedElement> getParameters();
}
