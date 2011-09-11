package net.sf.nakeduml.validation;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.validation.IValidationRule;

@StepDependency(phase = ValidationPhase.class)
public class PropertyValidation extends AbstractValidator{
	enum PropertyValidationRule implements IValidationRule{
		SUBSETTED_PROPERTY_NO_UNION("Subsetted properties must be marked as derived unions","{0} is not marked as a derived union but is subsetted by {1}::{2}"),
		SUBSETTED_PROPERTY_NOT_IN_CONTEXT("Subsetted properties must be in the redefinition context of the subseting property",
				"Subsetted {0} is not accessible from the classifier context of {1}::{2}"),
		REDEFINED_PROPERTY_NOT_IN_CONTEXT("Redefined properties must be in the redefinition context of the subseting property",
				"Redifined {0} is not accessible from the classifier context {1}::{2}");
		;
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
	public void visitProperty(INakedProperty p){
		if(p.getSubsettedProperties().size() > 0){
			for(INakedProperty sp:p.getSubsettedProperties()){
				if(!sp.isDerivedUnion()){
					getErrorMap().putError(sp, PropertyValidationRule.SUBSETTED_PROPERTY_NO_UNION, p.getOwner().getName(), p.getName());
				}
				if(p.getOwner().findEffectiveAttribute(sp.getName()) == null){
					if(p.getOwner().findEffectiveAttribute(sp.getName()) == null){
						getErrorMap().putError(p, PropertyValidationRule.SUBSETTED_PROPERTY_NOT_IN_CONTEXT, p.getOwner().getName(), p.getName());
					}
				}
			}
		}
		if(p.getRedefinedProperties().size() > 0){
			for(INakedProperty sp:p.getSubsettedProperties()){
				if(p.getOwner().findEffectiveAttribute(sp.getName()) == null){
					getErrorMap().putError(p, PropertyValidationRule.REDEFINED_PROPERTY_NOT_IN_CONTEXT, p.getOwner().getName(), p.getName());
				}
			}
		}
	}
}
