package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;

import org.nakeduml.java.metamodel.annotation.OJEnum;

public class ActivityNodeEnumerationImplementor extends ProcessStepEnumerationImplementor {
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedActivity c) {
		if (c.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD) {
			OJEnum e = super.buildOJEnum(c, hasStructuredNodes(c));
			for (INakedActivityNode n : c.getActivityNodesRecursively()) {
				if (isRestingNode(n)) {
					buildLiteral(n, e);
				}
			}
		}
	}

	private static boolean isRestingNode(INakedActivityNode n) {
		if (n instanceof INakedPin) {
			return false;
		}else if(n instanceof INakedStructuredActivityNode){
			INakedStructuredActivityNode s=(INakedStructuredActivityNode) n;
			for(INakedActivityNode child:s.getActivityNodes()){
				if(isRestingNode(child)){
					return true;
				}
			}
			return false;
		} else if (BehaviorUtil.requiresExternalInput(n.getActivity(), n) || BehaviorUtil.isEffectiveFinalNode(n)) {
			return true;
		} else if (n instanceof INakedParameterNode) {
			return ((INakedParameterNode) n).getParameter().isResult();
		} else if (n instanceof INakedControlNode) {
			INakedControlNode cNode = (INakedControlNode) n;
			ControlNodeType cNodeType = cNode.getControlNodeType();
			return cNodeType.isActivityFinalNode() || cNodeType.isFlowFinalNode() || cNodeType.isJoinNode();
		}else{
			return false;
		}
	}

	private boolean hasStructuredNodes(INakedActivity sm) {
		for (INakedActivityNode n : sm.getActivityNodesRecursively()) {
			if (n.getInStructuredNode() != null) {
				return true;
			}
		}
		return false;
	};

	@Override
	protected INakedElement getEnclosingElement(INakedElement s) {
		INakedActivityNode node = (INakedActivityNode) s;
		return node.getInStructuredNode();
	}

	@Override
	protected Collection<INakedTrigger> getMethodTriggers(INakedElement step) {
		Collection<INakedTrigger> result = new ArrayList<INakedTrigger>();
		if (step instanceof INakedAcceptEventAction) {
			INakedAcceptEventAction a = (INakedAcceptEventAction) step;
			if (a.getTrigger() != null && a.getTrigger().getEvent() instanceof INakedOperation) {
				result.add(a.getTrigger());
			}
		}
		return result;
	}
}
