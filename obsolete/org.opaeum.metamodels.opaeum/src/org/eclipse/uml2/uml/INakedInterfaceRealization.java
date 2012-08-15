package org.eclipse.uml2.uml;


public interface INakedInterfaceRealization extends INakedElement {
	INakedInterface getContract();
	INakedBehavioredClassifier getImplementingClassifier();
	void setContract(INakedInterface c);
	public abstract void setIndex(int index);
	public abstract int getIndex();
}
