package net.sf.nakeduml.metamodel.commonbehaviors;

import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedTypedElement;

public interface INakedMessageEvent extends INakedEvent{
	List<? extends INakedTypedElement> getEventParameters();
}
