package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityMessageEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityNodeEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.activity.ResponsibilityImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineMessageEventHandlerInserter;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Jbpm5EnvironmentBuilder.class,ActivityNodeEnumerationImplementor.class,StateEnumerationImplementor.class,ResponsibilityImplementor.class,
		ActivityMessageEventHandlerInserter.class,StateMachineMessageEventHandlerInserter.class,ProcessStepResolverImplementor.class
})
public class Jbpm5JavaStep extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
	}
}
