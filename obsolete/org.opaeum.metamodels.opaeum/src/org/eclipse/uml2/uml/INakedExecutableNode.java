package org.eclipse.uml2.uml;

import java.util.Collection;


public interface INakedExecutableNode extends INakedActivityNode {

	public Collection<INakedExceptionHandler> getHandlers();

	public void setHandlers(Collection<INakedExceptionHandler> handlers);


}
