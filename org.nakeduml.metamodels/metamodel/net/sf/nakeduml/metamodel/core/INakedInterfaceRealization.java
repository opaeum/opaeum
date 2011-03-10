package net.sf.nakeduml.metamodel.core;

public interface INakedInterfaceRealization extends INakedElement {
	INakedInterface getContract();
	INakedClassifier getImplementingClassifier();
	void setContract(INakedInterface c);
	void setImplementingClassifier(INakedClassifier c);
}
