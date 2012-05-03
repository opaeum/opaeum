package org.opaeum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.opaeum.runtime.organization.IBusinessComponentBase;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BusinessRole{
	Class<? extends IBusinessComponentBase> businessComponent() default IBusinessComponentBase.class;
}
