package net.sf.nakeduml.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StepDependency{
	Class<? extends TransformationPhase<? extends TransformationStep,?>> phase();
	Class<? extends TransformationStep>[] before() default {};
	Class<? extends TransformationStep>[] replaces() default {};
	Class<? extends TransformationStep>[] after() default {};
	Class<? extends TransformationStep>[] requires() default {};
}
