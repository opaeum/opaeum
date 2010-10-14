package net.sf.nakeduml.userinteractionmetamodel;


public enum UserInteractionKind {
	CREATE,
	VIEW,
	LIST,
	EDIT;


	public String getName() {
		return name();
	}

}