package net.sf.nakeduml.userinteractionmetamodel;


public enum TypedElementParticipationKind {
	HIDDEN,
	READONLY,
	READWRITE,
	NAVIGATION,
	REQUIRED;


	public String getName() {
		return name();
	}

}