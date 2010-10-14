package net.sf.nakeduml.javageneration.bpm;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.bpm.activity.ActionEnumerationImplementor;
import net.sf.nakeduml.javageneration.bpm.activity.ActivityEventHandlerInserter;
import net.sf.nakeduml.javageneration.bpm.activity.ActivityImplementor;
import net.sf.nakeduml.javageneration.bpm.activity.TaskExecutionImplementor;
import net.sf.nakeduml.javageneration.bpm.statemachine.StateEnumerationImplementor;
import net.sf.nakeduml.javageneration.bpm.statemachine.StateMachineEventHandlerInserter;
import net.sf.nakeduml.javageneration.bpm.statemachine.StateMachineImplementor;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;
import net.sf.nakeduml.linkage.PinLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = { PersistenceStep.class, OclExpressionExecution.class, PinLinker.class,
		ProcessIdentifier.class }, after = { PersistenceStep.class, OclExpressionExecution.class })
public class BusinessProcessManagementStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		ActivityImplementor ab = new ActivityImplementor();
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
		JpdlGenerator jg = new JpdlGenerator();
		jg.initialize(workspace, javaModel, config, textWorkspace);
		jg.startVisiting(workspace);
	}
}
