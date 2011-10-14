package org.opaeum.javageneration.jbpm5.activity;

import java.util.ArrayList;
import java.util.Collection;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.ActivityNodeContainer;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.commonbehaviors.INakedStep;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedOperation;

@StepDependency(phase = JavaTransformationPhase.class,requires = ActivityProcessImplementor.class,after = ActivityProcessImplementor.class)
public class ActivityNodeEnumerationImplementor extends ProcessStepEnumerationImplementor{
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedActivity c){
		if(c.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			OJEnum e = super.buildOJEnum(c, false);
			nodes(e, c, c);
		}
	}
	private void nodes(OJEnum e,ActivityNodeContainer c,INakedClassifier msg){

		
		Collection<INakedActivityNode> activityNodes = c.getActivityNodes();
		for(INakedActivityNode n:activityNodes){
			String parentLiteral;
			if(BehaviorUtil.isRestingNode(n)){
				if(getEnclosingElement(n) == null){
					parentLiteral = "null";
				}else{
					OJPathName parentState = OJUtil.classifierPathname(msg.getNestingClassifier());
					e.addToImports(parentState);
					parentLiteral = parentState.getLast() + "State." + Jbpm5Util.stepLiteralName(getEnclosingElement(n));
				}
				buildLiteral(n, e, parentLiteral);
			}
			if(n instanceof INakedStructuredActivityNode){
				INakedStructuredActivityNode san = (INakedStructuredActivityNode) n;
				OJEnum e2 = super.buildOJEnum(san.getMessageStructure(), false);
				nodes(e2, san, san.getMessageStructure());
			}
		}
	}
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
