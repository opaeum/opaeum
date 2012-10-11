package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;

public class AssociationEndNavigabilityAndCompositionSection extends AbstractMultiFeaturePropertySection{
	private BooleanSubsection isComposite;
	private BooleanSubsection isNavigable;
	public AssociationEndNavigabilityAndCompositionSection(){
		BooleanSubsection result = new BooleanSubsection(this){
			@Override
			protected Command buildCommand(EObject selection, EObject featureOwner){
				if(getControl().getSelection()){
					return SetCommand.create(getEditingDomain(), featureOwner, getFeature(), AggregationKind.COMPOSITE_LITERAL);
				}else{
					return SetCommand.create(getEditingDomain(), featureOwner, getFeature(), AggregationKind.NONE_LITERAL);
				}
			}
		};
		result.setLabelWidth(120);
		result.setLabelText("Is Composition");
		result.setFeature(UMLPackage.eINSTANCE.getProperty_Aggregation());
		result.setDefaultValue(false);
		isComposite = result;
		BooleanSubsection result1 = new BooleanSubsection(this){
			@Override
			protected Command buildCommand(EObject selection, EObject featureOwner){
				Property p = (Property) featureOwner;
				if(getControl().getSelection()){
					return AddCommand.create(getEditingDomain(), p.getAssociation(), getFeature(), featureOwner);
				}else{
					return RemoveCommand.create(getEditingDomain(), p.getAssociation(), getFeature(), featureOwner);
				}
			}
		};
		result1.setLabelWidth(120);
		result1.setLabelText("Is Navigable");
		result1.setFeature(UMLPackage.eINSTANCE.getAssociation_NavigableOwnedEnd());
		result1.setDefaultValue(false);
		isNavigable = result1;
	}
	@Override
	public String getLabelText(){
		return "End config:";
	}
}