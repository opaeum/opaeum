package net.sf.nakeduml.validation;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedProperty;

@StepDependency(phase = ValidationPhase.class)
public class PropertyValidation extends AbstractValidator{
	// TODO parameters
	@VisitBefore(matchSubclasses = true)
	public void visitProperty(INakedProperty p){
		if(p.getSubsettedProperties().size()>0){
			for(INakedProperty sp:p.getSubsettedProperties()){
				if(!sp.isDerivedUnion()){
					getErrorMap().putError(sp, CoreValidationRule.SUBSETTED_PROPERTY_NO_UNION, p.getOwner().getName(),p.getName());
				}
			}
		}
	}
}
