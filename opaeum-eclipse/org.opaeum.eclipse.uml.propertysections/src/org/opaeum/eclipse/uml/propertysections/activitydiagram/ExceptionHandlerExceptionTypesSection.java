package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractReferencePropertySection;



public class ExceptionHandlerExceptionTypesSection extends AbstractReferencePropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getExceptionHandler_ExceptionType();
	}
	public String getLabelText(){
		return "Exceptions";
	}
	@Override
	public Object[] getChoices(){
		return OpaeumEclipseContext.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getClassifier()).toArray();
	}
}
