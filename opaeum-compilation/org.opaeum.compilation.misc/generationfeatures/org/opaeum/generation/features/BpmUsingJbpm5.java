package org.opeum.generation.features;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaFeature;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.simpleactions.EventGeneratorImplementor;
import org.opeum.javageneration.jbpm5.Jbpm5JavaStep;
import org.opeum.jbpm5.ActivityFlowStep;
import org.opeum.jbpm5.StateMachineFlowStep;
import org.opeum.pomgeneration.Jbpm5PomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Jbpm5JavaStep.class,StateMachineFlowStep.class,ActivityFlowStep.class,Jbpm5PomStep.class,EventGeneratorImplementor.class
},after = {
		
})
public class BpmUsingJbpm5 extends JavaFeature{
}
