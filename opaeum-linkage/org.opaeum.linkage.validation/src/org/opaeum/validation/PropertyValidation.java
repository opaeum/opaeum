package org.opaeum.validation;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.validation.IValidationRule;

@StepDependency(phase = ValidationPhase.class)
public class PropertyValidation extends AbstractValidator{
	enum PropertyValidationRule implements IValidationRule{
		INTERFACE_PROPERTIES_CANNOT_BE_STATIC("Interface properties cannot be static","{0} is marked as static but belongs to an interface"),
		REDEFINED_ASSOCIATION_END_ON_BIDIRECTIONAL_ASSOCATION(
				"One end of a bidirectional cannot be redefined as it breaks to two-way semantics.",
				"{0} redefines {1} which participates in a bidirectional association. Redefine both ends of the association."),
				REDEFINED_ASSOCIATION_END_SAME_NO_OF_QUALIFIERS(
						"Qualifiers of an association end must correspond with qualifiers of the redefined association end.",
						"{0} redefines {1} but their qualifiers do not correspond in number and/or type."),
		DERIVED_UNION_NOT_BIDIRECTIONAL(
				"When an association end on a bidirectional association is marked as a derived union, the opposite end should be marked as a derived union too.",
				"{0} is marked as a derived union, but its opposite end {1} is not."),
		SUBSETTED_PROPERTY_NO_UNION("Subsetted properties must be marked as derived unions",
				"{0} subsets {1} which is not marked as a derived union"),
		SUBSETTED_PROPERTY_NOT_IN_CONTEXT("Subsetted properties must be in the redefinition context of the subseting property",
				"{0} subset {1}  which is not accessible from {2}"),
		REDEFINED_PROPERTY_NOT_IN_CONTEXT("Redefined properties must be in the redefinition context of the subseting property",
				"{0} redefines {1} which is not accessible from {2}");
		private String description;
		private String messagePattern;
		private PropertyValidationRule(String description,String messagePattern){
			this.description = description;
			this.messagePattern = messagePattern;
		}
		public String getDescription(){
			return this.description;
		}
		public String getMessagePattern(){
			return messagePattern;
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitProperty(Property p){
		// TODO property cannot be derivedUnion AND have defaultVAlue
		if(p.getAssociationEnd() == null){
			Classifier owner = (Classifier) EmfElementFinder.getContainer(p);
			if(p.isStatic() && owner instanceof Interface){
				getErrorMap().putError(p, PropertyValidationRule.INTERFACE_PROPERTIES_CANNOT_BE_STATIC);
			}
			if(p.isNavigable() && p.isDerivedUnion() && p.getAssociation() != null && p.getOtherEnd() != null && p.getOtherEnd().isNavigable()
					&& !p.getOtherEnd().isDerivedUnion()){
				getErrorMap().putError(p, PropertyValidationRule.DERIVED_UNION_NOT_BIDIRECTIONAL, p.getOtherEnd());
			}
			if(p.getSubsettedProperties().size() > 0){
				for(Property sp:p.getSubsettedProperties()){
					if(!sp.isDerivedUnion()){
						getErrorMap().putError(p, PropertyValidationRule.SUBSETTED_PROPERTY_NO_UNION, sp);
					}
					if(workspace.getOpaeumLibrary().findEffectiveAttribute(owner, sp.getName()) == null){
						getErrorMap().putError(p, PropertyValidationRule.SUBSETTED_PROPERTY_NOT_IN_CONTEXT, sp, owner);
					}
				}
			}
			if(p.getRedefinedProperties().size() > 0){
				for(Property rp:p.getRedefinedProperties()){
					if(workspace.getOpaeumLibrary().findEffectiveAttribute(owner, rp.getName()) == null){
						getErrorMap().putError(p, PropertyValidationRule.REDEFINED_PROPERTY_NOT_IN_CONTEXT, rp, owner);
					}
					if(rp.getQualifiers().size()!=p.getQualifiers().size()){
						//TODO check for type of qualifiers
						getErrorMap().putError(p, PropertyValidationRule.REDEFINED_ASSOCIATION_END_SAME_NO_OF_QUALIFIERS, rp);
					}
					if(rp.isNavigable() && rp.getAssociation() != null && rp.getOtherEnd().isNavigable()){
						if(p.getAssociation() == null || !p.getOtherEnd().getRedefinedProperties().contains(rp.getOtherEnd())){
							getErrorMap().putError(p, PropertyValidationRule.REDEFINED_ASSOCIATION_END_ON_BIDIRECTIONAL_ASSOCATION, rp);
						}
					}
				}
			}
		}
	}
}
