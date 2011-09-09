package net.sf.nakeduml.metamodel.core;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;

public interface INakedInterfaceRealization extends INakedElement {
	INakedInterface getContract();
	INakedBehavioredClassifier getImplementingClassifier();
	void setContract(INakedInterface c);
}
