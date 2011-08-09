package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.uml2.uml.UMLPackage;

public class OpaqueExpressionBodySection extends AbstractOpaqueBodySection{
	public EAttribute getLanguagesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueExpression_Language();
	}
	public EAttribute getBodiesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueExpression_Body();
	}
}
