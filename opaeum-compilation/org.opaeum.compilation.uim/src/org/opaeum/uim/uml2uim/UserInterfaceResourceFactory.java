package org.opaeum.uim.uml2uim;

import org.eclipse.emf.ecore.resource.Resource;

public interface UserInterfaceResourceFactory{
	Resource getResource(String id,String extenstion);
}
