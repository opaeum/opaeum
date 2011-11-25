package org.opaeum.metamodel.workspace;


public class MigrationWorkspace{
	INakedModelWorkspace fromWorkspace;
	INakedModelWorkspace toWorkspace;
	public MigrationWorkspace(INakedModelWorkspace fromWorkspace,INakedModelWorkspace toWorkspace){
		super();
		this.fromWorkspace = fromWorkspace;
		this.toWorkspace = toWorkspace;
	}
	public INakedModelWorkspace getFromWorkspace(){
		return fromWorkspace;
	}
	public INakedModelWorkspace getToWorkspace(){
		return toWorkspace;
	}
	
}
