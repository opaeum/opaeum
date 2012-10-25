package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;

public class WhenExpressionSubsection extends OpaqueExpressionSubsection{
	public WhenExpressionSubsection(IMultiPropertySection section){
		super(section);
	}
	// NB!! OpaeumElementLinker will ensure that "when" is always populated
	@Override
	protected EObject getFeatureOwner(EObject eObject){
		return ((TimeEvent) eObject).getWhen();
	}
	@Override
	public EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getTimeEvent_When();
	}
}
