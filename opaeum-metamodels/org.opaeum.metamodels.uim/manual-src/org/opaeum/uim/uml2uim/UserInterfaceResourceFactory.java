package org.opaeum.uim.uml2uim;

import org.eclipse.emf.ecore.resource.Resource;
import org.opaeum.emf.workspace.EmfWorkspace;

public interface UserInterfaceResourceFactory{
	Resource getResource(String id,String extenstion);
}
