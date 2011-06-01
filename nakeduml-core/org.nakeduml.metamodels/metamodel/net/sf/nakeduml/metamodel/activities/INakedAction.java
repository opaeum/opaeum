package net.sf.nakeduml.metamodel.activities;

import java.util.Collection;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;

public interface INakedAction extends INakedExecutableNode,INakedElementOwner,PreAndPostConstrained{
	ActionType getActionType();
	Collection<INakedInputPin> getInput();
	Collection<INakedOutputPin> getOutput();
	boolean hasExceptions();
	boolean handlesException();
	boolean isLongRunning();
}