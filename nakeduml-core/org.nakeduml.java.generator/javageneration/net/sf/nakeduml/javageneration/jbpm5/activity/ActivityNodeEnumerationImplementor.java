package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;

import org.nakeduml.java.metamodel.annotation.OJEnum;

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
	protected INakedElement getEnclosingElement(INakedElement s){
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
