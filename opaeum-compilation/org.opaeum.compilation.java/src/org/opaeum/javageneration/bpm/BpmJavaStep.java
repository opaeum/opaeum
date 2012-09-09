package org.opaeum.javageneration.bpm;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.bpm.activity.ActivityEventConsumptionImplementor;
import org.opaeum.javageneration.bpm.activity.ActivityNodeEnumerationImplementor;
import org.opaeum.javageneration.bpm.activity.TaskAndResponsibilityImplementor;
import org.opaeum.javageneration.bpm.statemachine.StateEnumerationImplementor;
import org.opaeum.javageneration.bpm.statemachine.StateMachineEventConsumptionImplementor;
import org.opaeum.javageneration.organization.OrganizationImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		ActivityNodeEnumerationImplementor.class,StateEnumerationImplementor.class,TaskAndResponsibilityImplementor.class,
		ActivityEventConsumptionImplementor.class,StateMachineEventConsumptionImplementor.class,EventHandlerImplementor.class,OrganizationImplementor.class,
})
public class BpmJavaStep extends JavaFeature{
}
