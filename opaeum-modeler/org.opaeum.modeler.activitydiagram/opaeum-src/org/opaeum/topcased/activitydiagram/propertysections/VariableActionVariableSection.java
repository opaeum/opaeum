package org.opaeum.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
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
import org.opaeum.topcased.propertysections.AbstractComboPropertySection;

public class VariableActionVariableSection extends AbstractComboPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getVariableAction_Variable();
	}
	protected String getLabelText(){
		return "Variable :";
	}
	protected List<? extends EObject> getComboValues(){
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
		return result;
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	private VariableAction getAction(){
		return((VariableAction) getEObject());
	}
	@Override
	protected String getFeatureAsText(){
		return "";
	}
	@Override
	protected Object getOldFeatureValue(){
		return getAction().getVariable();
	}
}
