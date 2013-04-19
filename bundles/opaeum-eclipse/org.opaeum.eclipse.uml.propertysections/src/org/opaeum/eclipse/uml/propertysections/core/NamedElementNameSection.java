package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;

public class NamedElementNameSection extends AbstractStringPropertySection{
	public String getLabelText(){
		return "Name";
	}
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getNamedElement_Name();
	}
}
