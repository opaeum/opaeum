package org.nakeduml.eclipse.javasync;

public class NamespaceRenameRequest{
	public String oldName;
	public String newName;
	public NamespaceRenameRequest(String oldName,String newName){
		super();
		this.oldName = oldName;
		this.newName = newName;
	}
	public String getOldName(){
		return oldName;
	}
	public String getNewName(){
		return newName;
	}
}