package net.sf.nakeduml.metamodel.commonbehaviors;

import java.util.Collection;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;

public interface INakedReception extends INakedElement{
	INakedSignal getSignal();
	void setSignal(INakedSignal s);
	Collection<INakedBehavior> getMethods();
	INakedClassifier getOwner();
}
