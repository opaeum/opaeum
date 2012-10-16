package org.opaeum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.METHOD})
public @interface ParameterMetaInfo{
	String uuid();
	long opaeumId();
	Class<? extends SimpleTypeRuntimeStrategyFactory> strategyFactory() default SimpleTypeRuntimeStrategyFactory.class;
	String shortDescription() default "";
	String name();
}
