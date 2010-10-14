package net.sf.nakeduml.domainmetamodel;


public enum ClassifierKind {
	ENTITY,
	ENUMERATION,
	SIMPLETYPE,
	STRUCTUREDDATATYPE,
	INTERFACE;


	public String getName() {
		return name();
	}

}