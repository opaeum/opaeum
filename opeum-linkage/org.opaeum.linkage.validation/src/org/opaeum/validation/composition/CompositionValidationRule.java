package org.opeum.validation.composition;

import org.opeum.metamodel.validation.IValidationRule;

public enum CompositionValidationRule implements IValidationRule {
	PERSISTENT_CONTAINS_PERSISTENT(
			"Composition may only occur between entities and structured datatypes",
			"{0} is contained by a class other than an entity or structured datatype"), ENTITIES_HAVE_ONE_COMPOSITE_ONLY(
			"Entities can only have one compositional parent",
			"{0} has more than one  compositional parent");
	public String getDescription() {
		return description;
	}

	public String getMessagePattern() {
		return messagePattern;
	}
	private String description;
	private String messagePattern;

	private CompositionValidationRule(String description, String messagePattern) {
		this.description = description;
		this.messagePattern = messagePattern;
	}
}
