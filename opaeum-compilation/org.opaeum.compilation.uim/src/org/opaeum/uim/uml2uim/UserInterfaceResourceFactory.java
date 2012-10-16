package org.opaeum.uim.uml2uim;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Element;

public interface UserInterfaceResourceFactory{
	Resource getResource(Element  e);
}
