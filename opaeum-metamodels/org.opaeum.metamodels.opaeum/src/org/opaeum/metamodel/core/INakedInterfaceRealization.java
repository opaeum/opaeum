package org.opeum.metamodel.core;

import org.opeum.metamodel.commonbehaviors.INakedBehavioredClassifier;

public interface INakedInterfaceRealization extends INakedElement {
	INakedInterface getContract();
	INakedBehavioredClassifier getImplementingClassifier();
	void setContract(INakedInterface c);
}
