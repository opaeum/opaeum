package org.opaeum.eclipse.context;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.uml2.uml.UMLPackage;

public class RelevantUmlElements{
	public static final Set<EClassifier> ELEMENTS = new HashSet<EClassifier>();
	static{
		ELEMENTS.add(UMLPackage.eINSTANCE.getType());
		ELEMENTS.add(UMLPackage.eINSTANCE.getClassifier());
		ELEMENTS.add(UMLPackage.eINSTANCE.getOperation());
		ELEMENTS.add(UMLPackage.eINSTANCE.getSignal());
		ELEMENTS.add(UMLPackage.eINSTANCE.getInterface());
	}
}
