package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.UMLPackage;

public class DurationObservationToEventSection extends AbstractObsercationEventSection{
	@Override
	protected Object getFeatureValue(){
		DurationObservation durationObservation = getObservation();
		if(durationObservation.getEvents().size() >= 2){
			return durationObservation.getEvents().get(1);
		}else{
			return null;
		}
	}
	protected DurationObservation getObservation(){
		return (DurationObservation) getEObject();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getDurationObservation_Event();
	}
	@Override
	public String getLabelText(){
		return "To Element";
	}
	protected void handleComboModified(){
		if(!isRefreshing()){
			EditingDomain editingDomain = getEditingDomain();
			EStructuralFeature feature = UMLPackage.eINSTANCE.getDurationObservation_Event();
			editingDomain.getCommandStack().execute(new DurationCommand(getObservation(), feature, cSingleObjectChooser.getSelection(), 1));
		}
	}
	@Override
	public void handleFirstEvent(boolean a){
		if(!isRefreshing()){
			EditingDomain editingDomain = getEditingDomain();
			EStructuralFeature feature = UMLPackage.eINSTANCE.getDurationObservation_FirstEvent();
			editingDomain.getCommandStack().execute(new DurationCommand(getObservation(), feature, a, 1));
		}
	}
	@Override
	public boolean isFirstEvent(){
		DurationObservation durationObservation = getObservation();
		if(durationObservation.getFirstEvents().size() >= 2){
			return Boolean.TRUE.equals(durationObservation.getFirstEvents().get(1));
		}else{
			return false;
		}
	}
}
