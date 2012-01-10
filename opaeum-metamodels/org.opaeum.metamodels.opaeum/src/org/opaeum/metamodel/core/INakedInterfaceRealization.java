package org.opaeum.metamodel.core;

import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;

public interface INakedInterfaceRealization extends INakedElement {
	INakedInterface getContract();
	INakedBehavioredClassifier getImplementingClassifier();
	void setContract(INakedInterface c);
	public abstract void setIndex(int index);
	public abstract int getIndex();
}
