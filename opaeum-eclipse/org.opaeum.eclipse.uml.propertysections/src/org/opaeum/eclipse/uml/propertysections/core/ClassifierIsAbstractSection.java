package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanPropertySection;

public class ClassifierIsAbstractSection extends AbstractBooleanPropertySection{

	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getClassifier_IsAbstract();
	}

	@Override
	protected Boolean getDefaultValue(){
		return Boolean.FALSE;
	}


	@Override
	public String getLabelText(){
		return "Is Abstract";
	}
}
