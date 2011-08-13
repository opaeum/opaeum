package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class SecondEndDefaultValueSection extends PropertyDefaultValueSection{

	@Override
	protected Property getProperty(EObject e){
		if(e == null){
			return null;
		}else{
			return ((Association) e).getMemberEnds().get(1);
		}
	}
}
