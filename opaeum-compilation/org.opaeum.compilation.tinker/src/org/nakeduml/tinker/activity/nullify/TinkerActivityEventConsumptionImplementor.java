package org.nakeduml.tinker.activity.nullify;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.FromNode;
import org.opaeum.javageneration.jbpm5.activity.ActivityEventConsumptionImplementor;
import org.opaeum.javageneration.jbpm5.activity.ActivityProcessImplementor;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.activities.INakedActivity;

@StepDependency(phase = JavaTransformationPhase.class, requires = { ActivityProcessImplementor.class }, after = { ActivityProcessImplementor.class}, replaces = ActivityEventConsumptionImplementor.class)
public class TinkerActivityEventConsumptionImplementor extends ActivityEventConsumptionImplementor {

	@VisitBefore(matchSubclasses = true)
	public void visitActivity(INakedActivity activity){
	}
	@Override
	protected void consumeEvent(OJOperation operationContext,FromNode fromNode,OJIfStatement ifTokenFound){
	}
	protected void checkWaitAndFlowToNextNodes(OJOperation operationContext,OJBlock block,INakedAcceptEventAction node){
	}
}

