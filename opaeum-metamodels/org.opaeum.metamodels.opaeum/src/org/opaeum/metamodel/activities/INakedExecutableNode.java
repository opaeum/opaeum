package org.opeum.metamodel.activities;

import java.util.Collection;

import org.opeum.metamodel.actions.INakedExceptionHandler;

public interface INakedExecutableNode extends INakedActivityNode {

	public Collection<INakedExceptionHandler> getHandlers();

	public void setHandlers(Collection<INakedExceptionHandler> handlers);


}
