package net.sf.nakeduml.validation.commonbehavior;

import net.sf.nakeduml.metamodel.validation.IValidationRule;

public enum BehaviorValidationRule implements IValidationRule{
	SINGLE_RESULT_FOR_OCL_BEHAVIOR,
	COMPOSITE_MUST_BE_CONTEXT,
	WHEN_EXPRESSION_MUST_HAVE_BUILTIN_DATE_TYPE,
	TIME_EVENTS_REQUIRE_BUILT_IN_TIME_TYPES;
	public String getDescription() {
		return description;
	}
	public String getMessagePattern() {
		return messagePattern;
	}
	private String description;
	private String messagePattern;
}
