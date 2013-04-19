package org.opaeum.validation.commonbehavior;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.BehaviorValidationRule;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class BehaviorValidator extends AbstractValidator{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(Behavior b){
		if(b instanceof OpaqueBehavior){
			OpaqueBehavior ob = (OpaqueBehavior) b;
			if(ob.getBodies().size()>0){
				if(EmfBehaviorUtil.getResultParameters( ob).size() > 1){
					this.getErrorMap().putError(b, BehaviorValidationRule.SINGLE_RESULT_FOR_OCL_BEHAVIOR);
				}
			}
		}
		if(b.getSpecification() != null){
			if(b.getContext() == null){
				this.getErrorMap().putError(b, BehaviorValidationRule.SPECIFICATION_CONTEXT_NOT_NULL, b.getSpecification());
			}else if(!b.getContext().conformsTo((Classifier) b.getSpecification().getOwner())){
				this.getErrorMap().putError(b, BehaviorValidationRule.SPECIFICATION_CONTEXT_CONFORMANCE, b.getSpecification(), b.getContext(),
						b.getSpecification().getOwner());
			}
		}
	}
}
