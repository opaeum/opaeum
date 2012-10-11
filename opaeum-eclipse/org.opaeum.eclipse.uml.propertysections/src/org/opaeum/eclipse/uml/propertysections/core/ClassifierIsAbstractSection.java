package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanSection;

public class ClassifierIsAbstractSection extends AbstractBooleanSection{

	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getClassifier_IsAbstract();
	}

	@Override
	protected Boolean getDefaultValue(){
		return Boolean.FALSE;
	}

	@Override
	protected Element getElement(EObject eObject){
		return (Element)eObject;
	}

	@Override
	public String getLabelText(){
		return "Is Abstract";
	}
}
