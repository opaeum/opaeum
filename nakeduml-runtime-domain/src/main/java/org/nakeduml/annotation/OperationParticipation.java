package org.nakeduml.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperationParticipation {
	double displayIndex();
	String humanName();
	String instructionToUser();
	OperationParticipationKind participationInEdit();
	OperationParticipationKind participationInList();
	OperationParticipationKind participationInView();
	String resultingUserInteractionSpecification();
	String successMessage();
	String userInteractionSpecification();
}
