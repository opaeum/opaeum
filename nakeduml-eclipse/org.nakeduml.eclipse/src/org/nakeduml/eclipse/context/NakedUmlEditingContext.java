package org.nakeduml.topcased.uml.editor;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Package;

public class NakedUmlEditingContext{
	private EmfWorkspace emfWorkspace;
	EditingDomain editingDomain;
	private Package model;
	private IFile file;
	public NakedUmlEditingContext(EmfWorkspace emfWorkspace,EditingDomain editingDomain,Package model,IFile f){
		super();
		this.setEmfWorkspace(emfWorkspace);
		this.setEditingDomain(editingDomain);
		this.setModel(model);
		this.setFile(f);
	}
	EmfWorkspace getEmfWorkspace(){
		return emfWorkspace;
	}
	void setEmfWorkspace(EmfWorkspace emfWorkspace){
		this.emfWorkspace = emfWorkspace;
	}
	EditingDomain getEditingDomain(){
		return editingDomain;
	}
	void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain;
	}
	Package getModel(){
		return model;
	}
	void setModel(Package model){
		this.model = model;
	}
	IFile getFile(){
		return file;
	}
	private void setFile(IFile file){
		this.file = file;
	}
}