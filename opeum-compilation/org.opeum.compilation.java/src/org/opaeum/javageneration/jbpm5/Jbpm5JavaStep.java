package org.opeum.javageneration.jbpm5;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaFeature;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.jbpm5.activity.ActivityEventConsumptionImplementor;
import org.opeum.javageneration.jbpm5.activity.ActivityNodeEnumerationImplementor;
import org.opeum.javageneration.jbpm5.activity.ResponsibilityImplementor;
import org.opeum.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import org.opeum.javageneration.jbpm5.statemachine.StateMachineEventConsumptionImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Jbpm5EnvironmentBuilder.class,ActivityNodeEnumerationImplementor.class,StateEnumerationImplementor.class,ResponsibilityImplementor.class,
		ActivityEventConsumptionImplementor.class,StateMachineEventConsumptionImplementor.class,EventHandlerImplementor.class
})
public class Jbpm5JavaStep extends JavaFeature{
}
