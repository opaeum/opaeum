package org.opaeum.eclipse.uml.propertysections.statemachine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;

public class TransitionRedefinedTransitionSection extends OpaeumChooserPropertySection{
	@Override
	protected Object getFeatureValue(){
		return getTransition().getRedefinedTransition();
	}
	public Transition getTransition(){
		return (Transition) getEObject();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> result = new ArrayList<Object>();
		StateMachine sm = EmfStateMachineUtil.getStateMachine(getTransition());
		for(Classifier psm:sm.getGenerals()){
			if(psm instanceof StateMachine){
				result.addAll(EmfStateMachineUtil.getTransitions((StateMachine) psm));
			}
		}
		result.add("");
		return result.toArray();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getTransition_RedefinedTransition();
	}
	@Override
	public String getLabelText(){
		return "Redefined Transition";
	}
}
