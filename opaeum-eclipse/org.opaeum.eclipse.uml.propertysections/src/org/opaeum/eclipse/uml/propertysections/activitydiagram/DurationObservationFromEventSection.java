package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.UMLPackage;

public class DurationObservationFromEventSection extends AbstractObsercationEventSection{
	@Override
	protected Object getFeatureValue(){
		DurationObservation durationObservation = getObservation();
		if(durationObservation.getEvents().size() >= 1){
			return durationObservation.getEvents().get(0);
		}else{
			return null;
		}
	}
	protected DurationObservation getObservation(){
		return (DurationObservation) getEObject();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		return super.getComboFeatureValues();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getDurationObservation_Event();
	}
	@Override
	public String getLabelText(){
		return "From Element";
	}
	protected void handleComboModified(){
		if(!isRefreshing()){
			EditingDomain editingDomain = getEditingDomain();
			EStructuralFeature feature = UMLPackage.eINSTANCE.getDurationObservation_Event();
			editingDomain.getCommandStack().execute(new DurationCommand(getObservation(), feature, cSingleObjectChooser.getSelectedObject(), 0));
		}
	}
	@Override
	public void handleFirstEvent(boolean a){
		if(!isRefreshing()){
			EditingDomain editingDomain = getEditingDomain();
			EStructuralFeature feature = UMLPackage.eINSTANCE.getDurationObservation_FirstEvent();
			editingDomain.getCommandStack().execute(new DurationCommand(getObservation(), feature, a, 0));
		}
		
	}
	@Override
	public boolean isFirstEvent(){
		DurationObservation durationObservation = getObservation();
		if(durationObservation.getFirstEvents().size() >= 1){
			return Boolean.TRUE.equals(durationObservation.getFirstEvents().get(0));
		}else{
			return false;
		}
	}

}
