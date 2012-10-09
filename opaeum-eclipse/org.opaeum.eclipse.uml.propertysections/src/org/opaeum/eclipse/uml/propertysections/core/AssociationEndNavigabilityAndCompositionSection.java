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
import org.opaeum.eclipse.uml.propertysections.base.BooleanSubSection;

public class AssociationEndNavigabilityAndCompositionSection extends AbstractMultiFeaturePropertySection{
	private BooleanSubSection isComposite;
	private BooleanSubSection isNavigable;
	public AssociationEndNavigabilityAndCompositionSection(){
		BooleanSubSection result = new BooleanSubSection(this){
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
		BooleanSubSection result1 = new BooleanSubSection(this){
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
}