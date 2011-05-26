package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VariableAction;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

public class VariableActionVariableSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getVariableAction_Variable();
	}
	protected String getLabelText(){
		return "Variable :";
	}
	protected Object[] getComboFeatureValues(){
		List<Object> result = new ArrayList<Object>();
		result.add("");
		Element e=getAction();
		while(!(e instanceof Activity)){
			if(e.getOwner() instanceof Activity){
				result.addAll(((Activity) e.getOwner()).getVariables());
			}
			if(e.getOwner() instanceof StructuredActivityNode){
				result.addAll(((StructuredActivityNode) e.getOwner()).getVariables());
			}
			e=e.getOwner();
		}
		
		return result.toArray();
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()){
			public String getText(Object object){
				IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
				return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
			}
		};
	}
	protected Object getFeatureValue(){
		return getAction().getVariable();
	}
	private VariableAction getAction(){
		return((VariableAction) getEObject());
	}
}
