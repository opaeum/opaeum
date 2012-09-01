package org.opaeum.eclipse.newchild;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class FeaturesWithAlternativeStereotypedTypes{
	public static Map<EReference,Collection<String>> FEATURE_STEREOTYPE_MAP = new HashMap<EReference,Collection<String>>();
	public static Map<EReference,Collection<EClass>> FEATURE_TYPE_MAP = new HashMap<EReference,Collection<EClass>>();
	static{
		FEATURE_STEREOTYPE_MAP.put(UMLPackage.eINSTANCE.getClass_NestedClassifier(),
				Arrays.asList(StereotypeNames.NOTIFICATION, StereotypeNames.BUSINESS_DOCUMENT));
		FEATURE_TYPE_MAP.put(UMLPackage.eINSTANCE.getClass_NestedClassifier(),
				Arrays.asList(UMLPackage.eINSTANCE.getClass_(),UMLPackage.eINSTANCE.getSignal()));
	}
}
