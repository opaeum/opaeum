package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;

public class DurationObservationToEventSection extends OpaeumChooserPropertySection{
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
		EObject container = EmfElementFinder.getContainer(getEObject());
		if(container instanceof StateMachine){
			return EmfStateMachineUtil.getAllStates((StateMachine) container).toArray();
		}else if(container instanceof StructuredActivityNode){
			return ((StructuredActivityNode) container).getNodes().toArray();
		}else if(container instanceof Activity){
			return ((Activity) container).getNodes().toArray();
		}
		return new Object[0];
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getTimeObservation_Event();
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
}
