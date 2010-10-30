package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActionEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityProcessImplementor;
import net.sf.nakeduml.javageneration.jbpm5.activity.TaskExecutionImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineImplementor;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;
import net.sf.nakeduml.jbpm5.FlowGenerationStep;
import net.sf.nakeduml.jbpm5.StateMachineFlowStep;
import net.sf.nakeduml.linkage.PinLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = { PersistenceStep.class, OclExpressionExecution.class, PinLinker.class,
		ProcessIdentifier.class,StateMachineFlowStep.class }, after = { PersistenceStep.class, OclExpressionExecution.class })
public class Jbpm5Step extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		Jbpm5EnvironmentBuilder eb = new Jbpm5EnvironmentBuilder();
		eb.initialize(workspace, javaModel, config, textWorkspace);
		eb.startVisiting(workspace);
		ActivityProcessImplementor ab = new ActivityProcessImplementor();
		ab.initialize(workspace, javaModel, config, textWorkspace);
		ab.startVisiting(workspace);
		ActionEnumerationImplementor action = new ActionEnumerationImplementor();
		action.initialize(workspace, javaModel, config, textWorkspace);
		action.startVisiting(workspace);
		SignalImplementor si = new SignalImplementor();
		si.initialize(workspace, javaModel, config, textWorkspace);
		si.startVisiting(workspace);
		TaskExecutionImplementor uiei = new TaskExecutionImplementor();
		uiei.initialize(workspace, javaModel, config, textWorkspace);
		uiei.startVisiting(workspace);
		StateMachineImplementor smimpl = new StateMachineImplementor();
		smimpl.initialize(workspace, javaModel, config, textWorkspace);
		smimpl.startVisiting(workspace);
		StateEnumerationImplementor statimpl = new StateEnumerationImplementor();
		statimpl.initialize(workspace, javaModel, config, textWorkspace);
		statimpl.startVisiting(workspace);
		BehaviorEnvironmentBuilder beb = new BehaviorEnvironmentBuilder();
		beb.initialize(workspace, javaModel, config, textWorkspace);
		beb.startVisiting(workspace);
		// Lastly we insert the events - add to end of methods.
		ActivityEventHandlerInserter aehi = new ActivityEventHandlerInserter();
		aehi.initialize(workspace, javaModel, config, textWorkspace);
		aehi.startVisiting(workspace);
		StateMachineEventHandlerInserter smehi = new StateMachineEventHandlerInserter();
		smehi.initialize(workspace, javaModel, config, textWorkspace);
		smehi.startVisiting(workspace);
		/*
		 * JpdlDeployerGenerator jdg = new JpdlDeployerGenerator();
		 * jdg.initialize(workspace, javaModel, config, textWorkspace);
		 * jdg.startVisiting(workspace);
		 */
	}
}
