package net.sf.nakeduml.metamodel.core;

public enum RootObjectStatus{
	CREATED,
	EXTRACTED,
	LINKED,
	VALIDATED;
	public boolean isExtracted(){
		return this == EXTRACTED || this == LINKED || this == VALIDATED;
	}
	public boolean isLinked(){
		return this == LINKED || this == VALIDATED;
	}
	public boolean isValidated(){
		return this == VALIDATED;
	}
}
