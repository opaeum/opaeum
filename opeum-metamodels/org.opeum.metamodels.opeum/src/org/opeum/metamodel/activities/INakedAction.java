package org.opeum.metamodel.activities;

import java.util.Collection;

import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.PreAndPostConstrained;

public interface INakedAction extends INakedExecutableNode,INakedElementOwner,PreAndPostConstrained{
	Collection<INakedInputPin> getInput();
	Collection<INakedOutputPin> getOutput();
	boolean hasExceptions();
	boolean handlesException();
	boolean isLongRunning();
}