package net.sf.nakeduml.metamodel.core;

public enum CodeGenerationStrategy{
	ABSTRACT_SUPERTYPE_ONLY,
	NO_CODE,
	ALL;
	public boolean isAbstractSupertypeOnly(){
		return this == ABSTRACT_SUPERTYPE_ONLY;
	}
	public boolean isNone(){
		return this == NO_CODE;
	}
	public boolean isAll(){
		return this == ALL;
	}
}
