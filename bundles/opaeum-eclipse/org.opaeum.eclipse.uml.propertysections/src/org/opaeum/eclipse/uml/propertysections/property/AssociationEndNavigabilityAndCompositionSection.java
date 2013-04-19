package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;

public class AssociationEndNavigabilityAndCompositionSection extends AbstractMultiFeaturePropertySection{
	private BooleanSubsection isComposite;
	private BooleanSubsection isNavigable;
	public AssociationEndNavigabilityAndCompositionSection(){
		BooleanSubsection result = new BooleanSubsection(this);
		result.setLabelWidth(140);
		result.setLabelText("Is Composition");
		result.setFeature(UMLPackage.eINSTANCE.getProperty_IsComposite());
		result.setDefaultValue(false);
		result.setControlWidth(40);
		isComposite = result;
		BooleanSubsection result1 = new BooleanSubsection(this){
			@Override
			protected Command buildCommand(EObject selection, EObject featureOwner){
				Property p = (Property) featureOwner;
				if(getControl().getSelection()){
					return AddCommand.create(getEditingDomain(), p.getAssociation(), UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), featureOwner);
				}else{
					return RemoveCommand.create(getEditingDomain(), p.getAssociation(), UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd(), featureOwner);
				}
			}
			@Override
			public Boolean getCurrentValue(EObject e){
				Property p = (Property) e;
				return p.isNavigable();
			}
		};
		result1.setLabelWidth(140);
		result1.setControlWidth(40);
		result1.setLabelText("Is Navigable");
		result1.setDefaultValue(false);
		isNavigable = result1;
	}
	@Override
	public String getLabelText(){
		return "End config:";
	}
}