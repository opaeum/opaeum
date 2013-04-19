package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.UMLPackage;

public class TimeObservationEventSection extends AbstractObsercationEventSection{
	@Override
	protected Object getFeatureValue(){
		return getObservation().getEvent();
	}
	protected TimeObservation getObservation(){
		return (TimeObservation) getEObject();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		return super.getComboFeatureValues();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getTimeObservation_Event();
	}
	@Override
	public String getLabelText(){
		return "Observed Element";
	}
	@Override
	public void handleFirstEvent(boolean a){
		if(!isRefreshing()){
			EditingDomain editingDomain = getEditingDomain();
			CompoundCommand compoundCommand = new CompoundCommand("Stuff");
			EStructuralFeature feature = UMLPackage.eINSTANCE.getDurationObservation_FirstEvent();
			compoundCommand.append(SetCommand.create(editingDomain, getObservation(), feature, a));
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	@Override
	public boolean isFirstEvent(){
		return getObservation().isFirstEvent();
	}
}
