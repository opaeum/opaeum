package org.opaeum.eclipse.javasync;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.OpaeumSynchronizationListener;
import org.opaeum.eclipse.context.OpaeumEclipseContextListener;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.ModelWorkspace;

public class NamespaceRenameRequests implements OpaeumSynchronizationListener,OpaeumEclipseContextListener{
	private Map<String,NamespaceRenameRequest> renamedRequestsByNewName = new HashMap<String,NamespaceRenameRequest>();
	private void maybeAddRenameRequest(OJUtil ojUtil, Namespace ne){
		// NB!!! this has to be done here in case multiple renames occurred before synchronization with java source
		if(ojUtil.requiresJavaRename(ne)){
			// Optimize namespace renames to minimize need for other models to be recompiled
			addRenameRequest( ojUtil.getOldClassifierPathname(ne).toJavaString(), ojUtil.classifierPathname(ne).toJavaString());
			addRenameRequest(ojUtil.getOldPackagePathname(ne).toJavaString(), ojUtil.packagePathname(ne).toJavaString());
		}
	}
	private void addRenameRequest(String oldName,String newName){
		NamespaceRenameRequest prev = renamedRequestsByNewName.get(oldName);
		if(prev != null){
			// just update the previous rename request
			prev.newName = newName;
			renamedRequestsByNewName.remove(oldName);
		}else{
			prev = new NamespaceRenameRequest(oldName, newName);
		}
		renamedRequestsByNewName.put(newName, prev);
	}
	@Override
	public void synchronizationComplete(OpenUmlFile ws,Set<Element> affectedElements){
		for(Element ne:affectedElements){
			if(ne instanceof Classifier || ne instanceof Package){
				maybeAddRenameRequest(ws.getOJUtil(), (Namespace) ne);
			}
		}
	}
	public Set<NamespaceRenameRequest> getRenamedNamespaces(){
		return new HashSet<NamespaceRenameRequest>(this.renamedRequestsByNewName.values());
	}
	public void clearRenamedNamespaces(){
		this.renamedRequestsByNewName.clear();
	}
	@Override
	public void onSave(IProgressMonitor monitor,OpenUmlFile f){
	}
	@Override
	public void onClose(OpenUmlFile openUmlFile){
		this.renamedRequestsByNewName=null;
		
	}

}