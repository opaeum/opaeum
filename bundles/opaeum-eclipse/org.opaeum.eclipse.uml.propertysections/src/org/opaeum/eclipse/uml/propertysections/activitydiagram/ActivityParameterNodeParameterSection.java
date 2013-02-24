package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;

public class ActivityParameterNodeParameterSection extends AbstractChooserPropertySection{
	protected Object[] getComboFeatureValues(){
		ActivityParameterNode node= getActivityParameterNode();
		Activity containingActivity = EmfActivityUtil.getContainingActivity(node);
        List<Object> choices = new ArrayList<Object>();
        choices.add("");
        choices.addAll(containingActivity.getOwnedParameters());
        if(containingActivity.getOwner() instanceof Transition || containingActivity.getOwner() instanceof State){
        	StateMachine sm = EmfStateMachineUtil.getNearestApplicableStateMachine(containingActivity);
        	choices.addAll(sm.getOwnedParameters());
        }
		return choices.toArray();
	}
	protected ActivityParameterNode getActivityParameterNode(){
		return (ActivityParameterNode)getEObject();
	}
	protected Object getFeatureValue(){
		return getActivityParameterNode().getParameter();
	}
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getActivityParameterNode_Parameter();
	}
	public String getLabelText(){
		return "Parameter:";
	}
}
