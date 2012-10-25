package org.opaeum.metamodel.validation;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;

public interface IValidationRule {
	UMLPackage pkg=UMLPackage.eINSTANCE;
	String getDescription();
	String getMessagePattern();
	String name();
	Class<?> getDeclaringClass();
	EStructuralFeature[] getFeatures();
}
