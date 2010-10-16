package net.sf.nakeduml.metamodel.activities;
import java.util.Collection;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
public interface INakedAction extends INakedActivityNode, INakedElementOwner,PreAndPostConstrained {
	Collection<INakedInputPin> getInput();
	Collection<INakedOutputPin> getOutput();
	ActionType getActionType();
	boolean isImplicitJoin();
	boolean isImplicitDecision();
}