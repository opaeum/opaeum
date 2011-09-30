package org.opeum.validation.commonbehavior;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedOpaqueBehavior;
import org.opeum.validation.AbstractValidator;
import org.opeum.validation.ValidationPhase;

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
