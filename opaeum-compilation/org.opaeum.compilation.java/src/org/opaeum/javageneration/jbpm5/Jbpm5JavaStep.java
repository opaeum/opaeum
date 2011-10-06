package org.opaeum.javageneration.jbpm5;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.activity.ActivityEventConsumptionImplementor;
import org.opaeum.javageneration.jbpm5.activity.ActivityNodeEnumerationImplementor;
import org.opaeum.javageneration.jbpm5.activity.ResponsibilityImplementor;
import org.opaeum.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import org.opaeum.javageneration.jbpm5.statemachine.StateMachineEventConsumptionImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Jbpm5EnvironmentBuilder.class,ActivityNodeEnumerationImplementor.class,StateEnumerationImplementor.class,ResponsibilityImplementor.class,
		ActivityEventConsumptionImplementor.class,StateMachineEventConsumptionImplementor.class,EventHandlerImplementor.class
})
public class Jbpm5JavaStep extends JavaFeature{
}
