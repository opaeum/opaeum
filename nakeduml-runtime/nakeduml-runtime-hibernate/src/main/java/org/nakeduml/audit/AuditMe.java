package org.nakeduml.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
	ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditMe{
	Class<? extends AuditEntryFactory<? extends AuditEntry>> factory() default DefaultAuditEntryFactory.class;
}
