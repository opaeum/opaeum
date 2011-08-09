package org.nakeduml.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.propertysections.AbstractOpaqueBodySection;

public class OpaqueActionBodySection extends AbstractOpaqueBodySection{
	public EAttribute getLanguagesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueAction_Language();
	}
	public EAttribute getBodiesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueAction_Body();
	}
}
