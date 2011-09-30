package org.opeum.javageneration;

public enum JavaSourceKind{
	PROVIDED_IMPLEMENTATION(""),
	ABSTRACT_SUPERCLASS("Generated"),
	CONCRETE_IMPLEMENTATION(""),
	NORMAL("");
	private String suffix;
	private JavaSourceKind(String suffix){
		this.suffix=suffix;
	}
	public String getSuffix(){
		return this.suffix;
	}
}
