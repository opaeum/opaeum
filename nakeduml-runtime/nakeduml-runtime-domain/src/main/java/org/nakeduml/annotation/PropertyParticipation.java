package org.nakeduml.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PropertyParticipation {
	String columnStyle();	
	double displayIndex();
	String humanName();
	PropertyParticipationKind participationInCreate();
	PropertyParticipationKind participationInEdit();
	PropertyParticipationKind participationInList();
	PropertyParticipationKind participationInView();
	String plural();
	String resultingUserInteractionSpecification();
}
