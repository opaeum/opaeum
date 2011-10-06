package org.opaeum.feature.visit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitAfter {
	Class<?>[] match() default{};
	boolean matchSubclasses() default false;
}
