package org.opeum.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StepDependency{
	Class<? extends TransformationPhase<? extends ITransformationStep,?>> phase();
	Class<? extends ITransformationStep>[] before() default {};
	Class<? extends ITransformationStep>[] replaces() default {};
	Class<? extends ITransformationStep>[] after() default {};
	Class<? extends ITransformationStep>[] requires() default {};
}
