package net.sf.nakeduml.validation.commonbehavior;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import net.sf.nakeduml.validation.AbstractValidator;
import net.sf.nakeduml.validation.ValidationPhase;
import nl.klasse.octopus.model.IClassifier;

@StepDependency(phase = ValidationPhase.class)
public class BehaviorValidator extends AbstractValidator {
	@VisitBefore(matchSubclasses = true)
	public void class_Before(INakedClassifier c) {
		if (c instanceof INakedBehavior) {
			INakedBehavior behavior = (INakedBehavior) c;
			if (behavior instanceof INakedOpaqueBehavior) {
				INakedOpaqueBehavior ob = (INakedOpaqueBehavior) behavior;
				if (ob.getBodyExpression() != null) {
					if (ob.getResultParameters().size() > 1) {
						this.getErrorMap().putError(behavior, BehaviorValidationRule.SINGLE_RESULT_FOR_OCL_BEHAVIOR,
								"Opaque OCL Behaviors cannot have multiple concurrent results or exceptions");
					}
					if (ob.getReturnParameter() == null) {
						this.getErrorMap().putError(behavior, BehaviorValidationRule.SINGLE_RESULT_FOR_OCL_BEHAVIOR,
								"Opaque OCL Behaviors must have exactly on return parameter");
					}
				}
			}
			for (INakedElement ne : behavior.getOwnedElements()) {
				if (ne instanceof INakedTimeEvent) {
					INakedTimeEvent te = (INakedTimeEvent) ne;
					if (!te.isRelative() && te.getWhen() != null && te.getWhen().getType() != null) {
						boolean isBuiltInType = false;
						boolean hasBuiltInTimeType = false;
						IClassifier type = te.getWhen().getType();
						NakedUmlLibrary builtInTypes = workspace.getNakedUmlLibrary();
						if (builtInTypes.getDateType() != null) {
							hasBuiltInTimeType = true;
							if (type.conformsTo(builtInTypes.getDateType())) {
								isBuiltInType = true;
							}
						}
						if (builtInTypes.getDateType() != null) {
							hasBuiltInTimeType = true;
							if (type.conformsTo(builtInTypes.getDateType())) {
								isBuiltInType = true;
							}
						}
						if (!isBuiltInType) {
							this.getErrorMap().putError(ne, BehaviorValidationRule.WHEN_EXPRESSION_MUST_HAVE_BUILTIN_DATE_TYPE, "");
						}
						if (!hasBuiltInTimeType) {
							this.getErrorMap().putError(ne, BehaviorValidationRule.TIME_EVENTS_REQUIRE_BUILT_IN_TIME_TYPES, "");
						}
					}
				}
			}
		}
	}
}
