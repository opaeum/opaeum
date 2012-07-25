package org.opaeum.metamodel.validation;

public interface IValidationRule {
	String getDescription();
	String getMessagePattern();
	String name();
	Class<?> getDeclaringClass();
}
