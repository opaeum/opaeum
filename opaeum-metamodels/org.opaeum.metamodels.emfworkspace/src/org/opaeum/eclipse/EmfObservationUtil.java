package org.opaeum.eclipse;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.UMLPackage;

public class EmfObservationUtil{
	public static Collection<TimeObservation> getObservingTimeObservations(Element e){
		Collection<Setting> refs = ECrossReferenceAdapter.getCrossReferenceAdapter(e).getNonNavigableInverseReferences(e);
		Collection<TimeObservation> result = new HashSet<TimeObservation>();
		for(Setting setting:refs){
			if(setting.getEStructuralFeature().equals(UMLPackage.eINSTANCE.getTimeObservation_Event())){
				result.add((TimeObservation) setting.getEObject());
			}
		}
		return result;
	}
	public static Collection<DurationObservation> getObservingDurationObservations(Element e){
		Collection<Setting> refs = ECrossReferenceAdapter.getCrossReferenceAdapter(e).getNonNavigableInverseReferences(e);
		Collection<DurationObservation> result = new HashSet<DurationObservation>();
		for(Setting setting:refs){
			if(setting.getEStructuralFeature().equals(UMLPackage.eINSTANCE.getDurationObservation_Event())){
				result.add((DurationObservation) setting.getEObject());
			}
		}
		return result;
	}
}
