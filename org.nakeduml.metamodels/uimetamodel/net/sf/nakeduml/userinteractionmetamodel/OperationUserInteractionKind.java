package net.sf.nakeduml.userinteractionmetamodel;


public enum OperationUserInteractionKind {
	INVOKE,
	RETURN,
	INBOX,
	QUERY;


	public String getName() {
		return name();
	}

}