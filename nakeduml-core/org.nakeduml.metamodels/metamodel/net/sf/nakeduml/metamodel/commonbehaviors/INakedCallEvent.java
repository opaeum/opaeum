package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedOperation;

public interface INakedCallEvent extends INakedMessageEvent{
	INakedOperation getOperation();
}
