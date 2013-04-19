package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;

public class BehavioredClassifierClassifierBehaviorSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior();
	}
	public String getLabelText(){
		return "Classifier Behavior";
	}
	protected Object[] getComboFeatureValues(){
		BehavioredClassifier bc = (BehavioredClassifier) getSelectedObject();
		List<Behavior> result=new ArrayList<Behavior>();
		for(Behavior behavior:bc.getOwnedBehaviors()){
			if(EmfBehaviorUtil.isProcess(behavior)){
				result.add(behavior);
			}
		}
		return (Behavior[]) result.toArray(new Behavior[result.size()]);
	}
	@Override
	protected Command createSingleCommand(EditingDomain editingDomain,Object value,EStructuralFeature f,EObject owner){
		return super.createSingleCommand(editingDomain, value, f, owner);
	}
	protected Object getFeatureValue(){
		return ((BehavioredClassifier) getSelectedObject()).getClassifierBehavior();
	}
}