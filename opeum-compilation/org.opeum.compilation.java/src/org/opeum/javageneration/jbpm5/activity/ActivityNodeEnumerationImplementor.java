package org.opeum.javageneration.jbpm5.activity;

import java.util.ArrayList;
import java.util.Collection;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.annotation.OJEnum;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.actions.INakedAcceptEventAction;
import org.opeum.metamodel.activities.ActivityKind;
import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.activities.INakedActivityNode;
import org.opeum.metamodel.activities.INakedStructuredActivityNode;
import org.opeum.metamodel.commonbehaviors.INakedStep;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedOperation;

@StepDependency(phase = JavaTransformationPhase.class,requires = ActivityProcessImplementor.class,after = ActivityProcessImplementor.class)
public class ActivityNodeEnumerationImplementor extends ProcessStepEnumerationImplementor{
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedActivity c){
		if(c.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			OJEnum e = super.buildOJEnum(c, hasStructuredNodes(c));
			Collection<INakedActivityNode> activityNodes = c.getActivityNodes();
			nodes(e, activityNodes);
		}
	}
	private void nodes(OJEnum e,Collection<INakedActivityNode> activityNodes){
		for(INakedActivityNode n:activityNodes){
			if(BehaviorUtil.isRestingNode(n)){
				buildLiteral(n, e);
				if(n instanceof INakedStructuredActivityNode){
					nodes(e, ((INakedStructuredActivityNode) n).getActivityNodes());
				}
			}
		}
	}
	private boolean hasStructuredNodes(INakedActivity sm){
		for(INakedActivityNode n:sm.getActivityNodesRecursively()){
			if(n.getInStructuredNode() != null){
				return true;
			}
		}
		return false;
	};
	@Override
	protected INakedStep getEnclosingElement(INakedElement s){
		INakedActivityNode node = (INakedActivityNode) s;
		return node.getInStructuredNode();
	}
	@Override
	protected Collection<INakedTrigger> getOperationTriggers(INakedElement step){
		Collection<INakedTrigger> result = new ArrayList<INakedTrigger>();
		if(step instanceof INakedAcceptEventAction){
			INakedAcceptEventAction a = (INakedAcceptEventAction) step;
			for(INakedTrigger t:a.getTriggers()){
				if(t.getEvent() instanceof INakedOperation){
					result.add(t);
				}
			}
		}
		return result;
	}
}
