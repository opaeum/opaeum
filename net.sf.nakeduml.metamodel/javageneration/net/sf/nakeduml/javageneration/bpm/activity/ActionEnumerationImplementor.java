package net.sf.nakeduml.javageneration.bpm.activity;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.bpm.ProcessStepEnumerationImplementor;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.ControlNodeType;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedElement;

public class ActionEnumerationImplementor extends ProcessStepEnumerationImplementor{
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedActivity c){
		if(c.getActivityKind() == ActivityKind.PROCESS){
			OJEnum e = super.buildOJEnum(c, hasStructuredNodes(c));
			for(INakedActivityNode n:c.getActivityNodesRecursively()){
				if(isRestingNode(n)){
					buildLiteral(n, e);
				}
			}
		}
	}
	private static boolean isRestingNode(INakedActivityNode n){
		if(BehaviorUtil.requiresExternalInput(n)){
			return true;
		}else if(n instanceof INakedControlNode){
			INakedControlNode cNode = (INakedControlNode) n;
			ControlNodeType cNodeType = cNode.getControlNodeType();
			return cNodeType.isActivityFinalNode() || cNodeType.isFlowFinalNode() || cNodeType.isForkNode();
		}else{
			return n instanceof INakedStructuredActivityNode;
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
	protected INakedElement getEnclosingElement(INakedElement s){
		INakedActivityNode node = (INakedActivityNode) s;
		return node.getInStructuredNode();
	}
}
