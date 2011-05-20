package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityNodeEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityProcessImplementor;
import net.sf.nakeduml.javageneration.jbpm5.activity.TaskImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineImplementor;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;
import net.sf.nakeduml.linkage.PinLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = { PersistenceStep.class, OclExpressionExecution.class, PinLinker.class,
		ProcessIdentifier.class}, after = { PersistenceStep.class, OclExpressionExecution.class })
public class Jbpm5JavaStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		Jbpm5EnvironmentBuilder eb = new Jbpm5EnvironmentBuilder(false);
		eb.initialize(javaModel, config, textWorkspace, context);
		eb.startVisiting(workspace);
		ActivityProcessImplementor ab = new ActivityProcessImplementor();
		ab.initialize(javaModel, config, textWorkspace, context);
		ab.startVisiting(workspace);
		ActivityNodeEnumerationImplementor action = new ActivityNodeEnumerationImplementor();
		action.initialize(javaModel, config, textWorkspace, context);
		action.startVisiting(workspace);
		TaskImplementor uiei = new TaskImplementor();
		uiei.initialize(javaModel, config, textWorkspace, context);
		uiei.startVisiting(workspace);
		StateMachineImplementor smimpl = new StateMachineImplementor();
		smimpl.initialize(javaModel, config, textWorkspace, context);
		smimpl.startVisiting(workspace);
		StateEnumerationImplementor statimpl = new StateEnumerationImplementor();
		statimpl.initialize(javaModel, config, textWorkspace, context);
		statimpl.startVisiting(workspace);
		// Lastly we insert the events - add to end of methods.
		ActivityEventHandlerInserter aehi = new ActivityEventHandlerInserter();
		aehi.initialize(javaModel, config, textWorkspace, context);
		aehi.startVisiting(workspace);
		StateMachineEventHandlerInserter smehi = new StateMachineEventHandlerInserter();
		smehi.initialize(javaModel, config, textWorkspace, context);
		smehi.startVisiting(workspace);
		/*
		 * JpdlDeployerGenerator jdg = new JpdlDeployerGenerator();
		 * jdg.initialize(workspace, javaModel, config, textWorkspace);
		 * jdg.startVisiting(workspace);
		 */
	}
}
