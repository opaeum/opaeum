package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityEventConsumptionImplementor;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityNodeEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.activity.ResponsibilityImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineEventConsumptionImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Jbpm5EnvironmentBuilder.class,ActivityNodeEnumerationImplementor.class,StateEnumerationImplementor.class,ResponsibilityImplementor.class,
		ActivityEventConsumptionImplementor.class,StateMachineEventConsumptionImplementor.class,ProcessStepResolverImplementor.class,EventHandlerImplementor.class
})
public class Jbpm5JavaStep extends JavaFeature{
}
