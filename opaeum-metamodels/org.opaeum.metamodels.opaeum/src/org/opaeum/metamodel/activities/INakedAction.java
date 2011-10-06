package org.opaeum.metamodel.activities;

import java.util.Collection;

import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.PreAndPostConstrained;

public interface INakedAction extends INakedExecutableNode,INakedElementOwner,PreAndPostConstrained{
	Collection<INakedInputPin> getInput();
	Collection<INakedOutputPin> getOutput();
	boolean hasExceptions();
	boolean handlesException();
	boolean isLongRunning();
}