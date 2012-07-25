package org.opaeum.eclipse.context;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.OpaeumSynchronizationListener;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.ModelWorkspace;

public class OpenUmlFile implements OpaeumSynchronizationListener{
	private EmfWorkspace emfWorkspace;
	EditingDomain editingDomain;
	private Package model;
	private IFile file;
	private boolean dirty;
	public OpenUmlFile(EmfWorkspace emfWorkspace,EditingDomain editingDomain,Package model,IFile f){
		super();
		this.setEmfWorkspace(emfWorkspace);
		this.setEditingDomain(editingDomain);
		this.setModel(model);
		this.setFile(f);
	}
	public EmfWorkspace getEmfWorkspace(){
		return emfWorkspace;
	}
	void setEmfWorkspace(EmfWorkspace emfWorkspace){
		this.emfWorkspace = emfWorkspace;
	}
	public EditingDomain getEditingDomain(){
		return editingDomain;
	}
	void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain;
	}
	public Package getModel(){
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
	public boolean isDirty(){
		return dirty;
	}
	public void setDirty(boolean dirty){
		this.dirty = dirty;
	}
	@Override
	public void synchronizationComplete(ModelWorkspace workspace,Set<Element> affectedElements){
		if(affectedElements.size()>0){
			this.dirty=true;
		}
		
	}
}