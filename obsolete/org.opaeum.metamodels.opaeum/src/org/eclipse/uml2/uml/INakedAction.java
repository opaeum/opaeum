package org.eclipse.uml2.uml;

import java.util.Collection;


public interface INakedAction extends INakedExecutableNode,INakedElementOwner,PreAndPostConstrained{
	Collection<INakedInputPin> getInput();
	Collection<INakedOutputPin> getOutput();
	boolean hasExceptions();
	boolean handlesException();
	boolean isLongRunning();
}