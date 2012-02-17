package org.opaeum.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Variable;
import org.eclipse.uml2.uml.VariableAction;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.topcased.propertysections.OpaeumChooserPropertySection;

public class VariableActionVariableSection extends OpaeumChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getVariableAction_Variable();
	}
	protected String getLabelText(){
		return "Variable :";
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	private VariableAction getAction(){
		return((VariableAction) getEObject());
	}
	@Override
	protected Object getFeatureValue(){
		return getAction().getVariable();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Variable> result = new ArrayList<Variable>();
		Element e = getAction();
		while(!(e instanceof Activity)){
			if(e.getOwner() instanceof Activity){
				result.addAll(((Activity) e.getOwner()).getVariables());
			}
			if(e.getOwner() instanceof StructuredActivityNode){
				result.addAll(((StructuredActivityNode) e.getOwner()).getVariables());
			}
			e = e.getOwner();
		}
		return result.toArray();
	}
}
