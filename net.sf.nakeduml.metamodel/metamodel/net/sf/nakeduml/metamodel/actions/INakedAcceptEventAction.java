package net.sf.nakeduml.metamodel.actions;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
public interface INakedAcceptEventAction extends INakedAction {
	INakedTrigger getTrigger();
	void setTrigger(INakedTrigger trigger);
	List<INakedOutputPin> getResult();
	List<INakedTypedElement> getParameters();
}
