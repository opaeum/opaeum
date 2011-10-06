package org.opaeum.validation.commonbehavior;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedOpaqueBehavior;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

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
