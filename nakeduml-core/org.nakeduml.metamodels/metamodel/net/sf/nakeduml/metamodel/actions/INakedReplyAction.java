package net.sf.nakeduml.metamodel.actions;

import java.util.List;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public interface INakedReplyAction extends INakedAction{
	INakedInputPin getReturnInfo();
	INakedAcceptCallAction getCause();
	List<INakedInputPin> getReplyValues();
}
