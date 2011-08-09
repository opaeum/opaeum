package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.uml2.uml.UMLPackage;

public class OpaqueBehaviorBodySection extends AbstractOpaqueBodySection{
	public EAttribute getLanguagesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueBehavior_Language();
	}
	public EAttribute getBodiesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueBehavior_Body();
	}
}
