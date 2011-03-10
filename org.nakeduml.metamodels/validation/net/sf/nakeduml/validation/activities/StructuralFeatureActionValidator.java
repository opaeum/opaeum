package net.sf.nakeduml.validation.activities;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedStructuralFeatureAction;
import net.sf.nakeduml.validation.AbstractValidator;
import net.sf.nakeduml.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class StructuralFeatureActionValidator extends AbstractValidator {
	@VisitBefore(matchSubclasses=true)
	public void visistStructuralFeatureAction(INakedStructuralFeatureAction a) {
		if (a.getObject() == null ) {
			if (a.getInPartition() == null) {
				if (!a.getContext().conformsTo(a.getExpectedTargetType())) {
					getErrorMap().putError(a, StructuralFeatureActionValidationRule.TARGET_OBJECT_DOES_NOT_CONFORM_TO_OWNER,a.getInPartition(),
							a.getFeature());
				}
			} else {
				if (!a.getContext().conformsTo(a.getExpectedTargetType())) {
					getErrorMap().putError(a, StructuralFeatureActionValidationRule.NO_TARGET_REQUIRES_ACTIVITY_OWNERSHIP,a.getContext(),
							a.getFeature());
				}
			}
		} else {
			if (!a.getObject().getNakedBaseType().conformsTo(a.getExpectedTargetType())) {
				getErrorMap().putError(a, StructuralFeatureActionValidationRule.TARGET_OBJECT_DOES_NOT_CONFORM_TO_OWNER,a.getObject(),
						a.getFeature());
			}
		}
	}
}
