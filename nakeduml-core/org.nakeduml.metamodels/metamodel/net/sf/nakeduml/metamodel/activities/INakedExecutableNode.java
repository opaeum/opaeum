package net.sf.nakeduml.metamodel.activities;

import java.util.Collection;

import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;

public interface INakedExecutableNode extends INakedActivityNode {

	public Collection<INakedExceptionHandler> getHandlers();

	public void setHandlers(Collection<INakedExceptionHandler> handlers);


}
