package org.nakeduml.eclipse.javasync;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.runtime.IProgressMonitor;
import org.nakeduml.topcased.uml.editor.NakedUmlContextListener;
import org.nakeduml.topcased.uml.editor.NakedUmlSynchronizationListener;

public class NamespaceRenameRequests implements NakedUmlSynchronizationListener, NakedUmlContextListener{
	private Map<String,NamespaceRenameRequest> renamedRequestsByNewName = new HashMap<String,NamespaceRenameRequest>();
	private void maybeAddRenameRequest(INakedNameSpace ne){
		// NB!!! this has to be done here in case multiple renames occurred before synchronization with java source
		if(ne.getMappingInfo().requiresJavaRename()){
			// Optimize namespace renames to minimize need for other models to be recompiled
			if(ne instanceof INakedClassifier){
				addRenameRequest(ne, ne.getMappingInfo().getOldQualifiedJavaName().toLowerCase(), ne.getMappingInfo().getQualifiedJavaName().toLowerCase());
			}
			addRenameRequest(ne, ne.getMappingInfo().getOldQualifiedJavaName(), ne.getMappingInfo().getQualifiedJavaName());
		}
	}
	private void addRenameRequest(INakedNameSpace ne,String oldName,String newName){
		NamespaceRenameRequest prev = renamedRequestsByNewName.get(oldName);
		if(prev != null){
			// just update the previous rename request
			prev.newName = ne.getMappingInfo().getQualifiedJavaName();
			renamedRequestsByNewName.remove(ne.getMappingInfo().getOldQualifiedJavaName());
		}else{
			prev = new NamespaceRenameRequest(ne.getMappingInfo().getOldQualifiedJavaName(), ne.getMappingInfo().getQualifiedJavaName());
		}
		renamedRequestsByNewName.put(newName, prev);
	}
	@Override
	public void synchronizationComplete(INakedModelWorkspace ws,Set<INakedElement> affectedElements){
		for(INakedElement ne:affectedElements){
			if(ne instanceof INakedClassifier || ne instanceof INakedPackage){
				maybeAddRenameRequest((INakedNameSpace) ne);
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
	public void onSave(IProgressMonitor monitor){
	}
	@Override
	public void onClose(boolean save){
		
	}
}