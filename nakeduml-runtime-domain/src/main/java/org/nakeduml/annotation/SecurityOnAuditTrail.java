package org.nakeduml.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SecurityOnAuditTrail {
	String[] requiredRoles();
	boolean requiresUserOwnership();	
	boolean requiresGroupOwnership();
}
