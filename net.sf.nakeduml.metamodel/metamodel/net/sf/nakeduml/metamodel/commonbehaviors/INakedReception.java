package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedElement;

public interface INakedReception extends INakedElement{
	INakedSignal getSignal();
	void setSignal(INakedSignal s);
}
