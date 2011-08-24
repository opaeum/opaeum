package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaFeature;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.simpleactions.EventManagementImplementor;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5JavaStep;
import net.sf.nakeduml.jbpm5.ActivityFlowStep;
import net.sf.nakeduml.jbpm5.StateMachineFlowStep;
import net.sf.nakeduml.pomgeneration.Jbpm5PomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Jbpm5JavaStep.class,StateMachineFlowStep.class,ActivityFlowStep.class,Jbpm5PomStep.class,EventManagementImplementor.class
},after = {
		
})
public class BpmUsingJbpm5 extends JavaFeature{
}
