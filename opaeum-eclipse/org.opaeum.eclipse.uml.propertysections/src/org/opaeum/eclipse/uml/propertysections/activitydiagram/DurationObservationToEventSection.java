package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
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
	protected String getLabelText(){
		return "To Element";
	}
	protected void handleComboModified(){
		if(!isRefreshing()){
			EditingDomain editingDomain = getEditingDomain();
			CompoundCommand compoundCommand = new CompoundCommand("Stuff");
			if(getObservation().getEvents().size() == 0){
				compoundCommand.append(AddCommand.create(editingDomain, getObservation(), getFeature(), cSingleObjectChooser.getSelection()));
				compoundCommand.append(AddCommand.create(editingDomain, getObservation(), getFeature(), cSingleObjectChooser.getSelection()));
			}else if(getObservation().getEvents().size() == 1){
				compoundCommand.append(AddCommand.create(editingDomain, getObservation(), getFeature(), cSingleObjectChooser.getSelection()));
			}else{
				compoundCommand.append(SetCommand.create(editingDomain, getObservation(), getFeature(), cSingleObjectChooser.getSelection(), 1));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	@Override
	public void handleFirstEvent(boolean a){
		if(!isRefreshing()){
			EditingDomain editingDomain = getEditingDomain();
			CompoundCommand compoundCommand = new CompoundCommand("Stuff");
			EStructuralFeature feature = UMLPackage.eINSTANCE.getDurationObservation_FirstEvent();
			if(getObservation().getEvents().size() == 0){
				compoundCommand.append(AddCommand.create(editingDomain, getObservation(), feature, true));
				compoundCommand.append(AddCommand.create(editingDomain, getObservation(), feature, a));
			}else if(getObservation().getEvents().size() == 1){
				compoundCommand.append(AddCommand.create(editingDomain, getObservation(), feature, a));
			}else{
				compoundCommand.append(SetCommand.create(editingDomain, getObservation(), feature, a, 1));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
		
	}
	@Override
	public boolean isFirstEvent(){
		DurationObservation durationObservation = getObservation();
		if(durationObservation.getFirstEvents().size() >= 2){
			return durationObservation.getFirstEvents().get(1);
		}else{
			return false;
		}
	}
}
