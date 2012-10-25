package org.opaeum.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PhaseDependency {
	Class<? extends TransformationPhase<? extends ITransformationStep,?>>[] before() default {};
	Class<? extends TransformationPhase<? extends ITransformationStep,?>>[] after() default {};
}
