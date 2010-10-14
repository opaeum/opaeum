package net.sf.nakeduml.validation.activities;

import net.sf.nakeduml.metamodel.validation.IValidationRule;

public enum StructuralFeatureActionValidationRule implements IValidationRule {
	TARGET_OBJECT_DOES_NOT_CONFORM_TO_OWNER(
			"The target object of a structural feature action must have a type that conforms to the owner of the structural feature",
			"On action {0}, the type of {1} does conform to the owner of {2} "), NO_TARGET_REQUIRES_ACTIVITY_OWNERSHIP(
			"If no object or partition is specified that has the feature, the context of the action itself must have the feature",
			"On action {0}, the context {1} does own the feature {2} ");
	private String messagePattern;
	private String description;

	private StructuralFeatureActionValidationRule(String description, String messagePattern) {
		this.description = description;
		this.messagePattern = messagePattern;
	}

	public String getDescription() {
		return this.description;
	}

	public String getMessagePattern() {
		return this.messagePattern;
	}
}
