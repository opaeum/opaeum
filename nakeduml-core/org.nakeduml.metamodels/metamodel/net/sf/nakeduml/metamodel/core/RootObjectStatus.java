package net.sf.nakeduml.metamodel.core;

public enum RootObjectStatus{
	CREATED,
	EXTRACTED,
	LINKED,
	VALIDATED,
	NAMED;
	public boolean isExtracted(){
		return this == EXTRACTED || this == LINKED || this == VALIDATED||this==NAMED;
	}
	public boolean isLinked(){
		return this == LINKED || this == VALIDATED||this==NAMED;
	}
	public boolean isValidated(){
		return this == VALIDATED||this==NAMED;
	}
	public boolean isNamed(){
		return this == NAMED;
	}
}
