package net.sf.nakeduml.metamodel.activities;
import java.util.Set;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
public interface INakedAction extends INakedActivityNode, INakedElementOwner,PreAndPostConstrained {
	Set<INakedInputPin> getInput();
	ActionType getActionType();
	boolean isImplicitJoin();
	boolean isImplicitDecision();
}