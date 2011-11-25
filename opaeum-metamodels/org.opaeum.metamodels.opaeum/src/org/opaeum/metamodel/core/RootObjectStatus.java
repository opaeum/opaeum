package org.opaeum.metamodel.core;

public enum RootObjectStatus{
	CREATED,
	EXTRACTED,
	LINKED,
	VALIDATED,
	NAMED;
	public boolean isExtracted(){
		return this == EXTRACTED || isLinked();
	}
	public boolean isLinked(){
		return this == LINKED ||isValidated();
	}
	public boolean isValidated(){
		return this == VALIDATED;
	}
	public boolean isNamed(){
		return this == NAMED || isValidated();
	}
}
