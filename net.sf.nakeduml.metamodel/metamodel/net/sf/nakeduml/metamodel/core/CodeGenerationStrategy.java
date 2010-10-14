package net.sf.nakeduml.metamodel.core;

public enum CodeGenerationStrategy{
	abstractSupertypeOnly,
	none,
	all,
	abstractLibraryOnly;
	public boolean isAbstractSupertypeOnly(){
		return this == abstractSupertypeOnly;
	}
	public boolean isNone(){
		return this == none;
	}
	public boolean isAll(){
		return this == all;
	}
	public boolean isAbstractLibraryOnly(){
		return this == abstractLibraryOnly;
	}
}
