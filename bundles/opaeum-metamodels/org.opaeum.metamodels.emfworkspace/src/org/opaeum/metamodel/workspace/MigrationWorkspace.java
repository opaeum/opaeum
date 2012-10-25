package org.opaeum.metamodel.workspace;

import org.opaeum.emf.workspace.EmfWorkspace;


public class MigrationWorkspace{
	EmfWorkspace fromWorkspace;
	EmfWorkspace toWorkspace;
	public MigrationWorkspace(EmfWorkspace fromWorkspace,EmfWorkspace toWorkspace){
		super();
		this.fromWorkspace = fromWorkspace;
		this.toWorkspace = toWorkspace;
	}
	public EmfWorkspace getFromWorkspace(){
		return fromWorkspace;
	}
	public EmfWorkspace getToWorkspace(){
		return toWorkspace;
	}
	
}
