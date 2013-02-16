package org.opaeum.runtime.ejb3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.opaeum.runtime.environment.Environment;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface FromEnvironment{
	Class<? extends Environment> source() default Environment.class; 
}
