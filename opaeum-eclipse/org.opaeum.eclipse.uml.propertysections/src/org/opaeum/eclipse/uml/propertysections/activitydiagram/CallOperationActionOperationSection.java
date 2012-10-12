package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;



public class CallOperationActionOperationSection extends OpaeumChooserPropertySection{
	@Override
	protected void handleComboModified(){
		super.handleComboModified();
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = OpaeumEclipseContext.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getOperation());
		choices.addAll(types);
		return choices.toArray();
	}
	@Override
	protected Object getFeatureValue(){
		return ((CallOperationAction) getEObject()).getOperation();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getCallOperationAction_Operation();
	}
	@Override
	public String getLabelText(){
		return "Operation:";
	}
}
