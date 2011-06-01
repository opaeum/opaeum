package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedOperation;

public interface INakedAcceptCallAction extends INakedAcceptEventAction{
	INakedOutputPin getReturnInfo();

	INakedOperation getOperation();
	INakedReplyAction getReplyAction();
}
