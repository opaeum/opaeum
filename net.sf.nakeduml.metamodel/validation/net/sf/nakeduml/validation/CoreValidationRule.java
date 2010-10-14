package net.sf.nakeduml.validation;

import net.sf.nakeduml.metamodel.validation.IValidationRule;

public enum CoreValidationRule implements IValidationRule {
	OCL("Validates correctness of OCL statements both syntactically and contextually", "The OCL on {0} is invalid: {1}"),
	INVERSE(
			"For persistence it is necessary to determine which side of bidirectional assocatiation is the primary side.",
			"It is not possible to calucate if {0} is the inverse side of an association"),
	DATA_TYPE_NAVIGABILITY("", ""),
	COMPOSITE_DATA_TYPE("", ""),
	ONE_ROOT_ENTITY("", ""),
	ONE_ROOT_USER("", ""),
	USER_ROLE_ENUM("", ""),
	OWNER_FOR_PROPERTY("", ""),
	QUALIFIER_NEEDS_BACKING_ATTRIBUTE("", ""),
	BACKING_ATTRIBUTE_NO_ON_TYPE("", ""),
	PRIMITIVE_TYPE_MUST_EXTEND_OCL_PRIMITIVE(
			"All primitive types must in some way or other be related to one of the primitive types supported by OCL - String, Boolean, Real or Integer",
			"{0} does not specialise any of the supported primitive types (String, Boolean, Real or Integer)"),
	PRIMITIVE_MUST_SPECIALIZE_PRIMITIVES(
			"Primitive types have to extend other primitive types",
			"{0} does not specialize (extend) a primitive");
	private String description;
	private String messagePattern;

	private CoreValidationRule(String description, String messagePattern) {
		this.description = description;
		this.messagePattern = messagePattern;
	}

	public String getDescription() {
		return this.description;
	}

	public String getMessagePattern() {
		return messagePattern;
	}
}
