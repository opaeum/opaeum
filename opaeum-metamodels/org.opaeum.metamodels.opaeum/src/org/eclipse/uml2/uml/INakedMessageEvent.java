package org.eclipse.uml2.uml;

import java.util.List;


public interface INakedMessageEvent extends INakedEvent{
	List<? extends INakedTypedElement> getEventParameters();
}
