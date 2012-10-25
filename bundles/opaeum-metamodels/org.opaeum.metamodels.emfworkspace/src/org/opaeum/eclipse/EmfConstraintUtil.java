package org.opaeum.eclipse;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.metamodel.core.internal.TagNames;

public class EmfConstraintUtil{
	public static List<OpaqueExpression> getMessageArguments(Constraint c){
		for(EObject eObject:c.getStereotypeApplications()){
			EStructuralFeature f = eObject.eClass().getEStructuralFeature(TagNames.MESSAGE_ARGUMENTS);
			if(f != null){
				return (List<OpaqueExpression>) eObject.eGet(f);
			}
		}
		return Collections.emptyList();
	}
}
