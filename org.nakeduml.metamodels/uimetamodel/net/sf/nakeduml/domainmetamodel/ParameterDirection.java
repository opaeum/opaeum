package net.sf.nakeduml.domainmetamodel;


public enum ParameterDirection {
	IN,
	OUT,
	INOUT,
	RETURN;


	public String getName() {
		return name();
	}

}