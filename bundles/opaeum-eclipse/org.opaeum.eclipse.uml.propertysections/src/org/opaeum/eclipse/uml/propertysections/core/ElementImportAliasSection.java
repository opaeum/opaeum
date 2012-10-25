package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;

public class ElementImportAliasSection extends AbstractStringPropertySection{
	@Override
	public String getLabelText(){
		return "Alias";
	}
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getElementImport_Alias();
	}
}
