package net.sf.nakeduml.validation.commonbehavior;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.validation.AbstractValidator;
import net.sf.nakeduml.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class BehaviorValidator extends AbstractValidator{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior b){
		if(b instanceof INakedOpaqueBehavior){
			INakedOpaqueBehavior ob = (INakedOpaqueBehavior) b;
			if(ob.getBodyExpression() != null){
				if(ob.getResultParameters().size() > 1){
					this.getErrorMap().putError(b, BehaviorValidationRule.SINGLE_RESULT_FOR_OCL_BEHAVIOR);
				}
			}
		}
		if(b.getSpecification() != null){
			if(b.getContext() == null){
				this.getErrorMap().putError(b, BehaviorValidationRule.SPECIFICATION_CONTEXT_NOT_NULL, b.getSpecification());
			}else if(!b.getContext().conformsTo(b.getSpecification().getContext())){
				this.getErrorMap().putError(b, BehaviorValidationRule.SPECIFICATION_CONTEXT_CONFORMANCE, b.getSpecification(), b.getContext(),
						b.getSpecification().getContext());
			}
		}
	}
}
