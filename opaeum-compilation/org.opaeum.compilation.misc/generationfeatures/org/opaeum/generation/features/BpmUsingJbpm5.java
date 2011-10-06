package org.opaeum.generation.features;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaFeature;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.simpleactions.EventGeneratorImplementor;
import org.opaeum.javageneration.jbpm5.Jbpm5JavaStep;
import org.opaeum.jbpm5.ActivityFlowStep;
import org.opaeum.jbpm5.StateMachineFlowStep;
import org.opaeum.pomgeneration.Jbpm5PomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Jbpm5JavaStep.class,StateMachineFlowStep.class,ActivityFlowStep.class,Jbpm5PomStep.class,EventGeneratorImplementor.class
},after = {
		
})
public class BpmUsingJbpm5 extends JavaFeature{
}
