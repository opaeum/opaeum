package org.opaeum.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StepDependency{
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface StrategyRequirement{
		Class<?> strategyContract();
		Class<?> requires();
		Class<?>[] replaces() default{};
	}
	Class<? extends TransformationPhase<? extends ITransformationStep,?>> phase();
	Class<? extends ITransformationStep>[] before() default {};
	Class<? extends ITransformationStep>[] replaces() default {};
	Class<? extends ITransformationStep>[] after() default {};
	Class<? extends ITransformationStep>[] requires() default {};
	StrategyRequirement[] strategyRequirement() default {};
}
