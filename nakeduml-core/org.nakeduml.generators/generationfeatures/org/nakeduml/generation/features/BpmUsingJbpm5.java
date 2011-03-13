package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5JavaStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;
import net.sf.nakeduml.jbpm5.ActivityFlowStep;
import net.sf.nakeduml.jbpm5.StateMachineFlowStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.Jbpm5PomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Jbpm5JavaStep.class,StateMachineFlowStep.class,ActivityFlowStep.class,Jbpm5PomStep.class},after = {
		PersistenceStep.class,OclExpressionExecution.class})
public class BpmUsingJbpm5 extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
	}
}
