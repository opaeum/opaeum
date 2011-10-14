package org.opaeum.javageneration.jbpm5;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.activity.ActivityNodeEnumerationImplementor;
import org.opaeum.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import org.opaeum.javageneration.persistence.AbstractEnumResolverImplementor;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.activities.ActivityNodeContainer;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.commonbehaviors.INakedStep;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedStateMachine;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		StateEnumerationImplementor.class,ActivityNodeEnumerationImplementor.class
},after = {
		StateEnumerationImplementor.class,ActivityNodeEnumerationImplementor.class
})
public class ProcessStepResolverImplementor extends AbstractEnumResolverImplementor{
	@VisitBefore
	public void visitActivity(INakedActivity a){
		if(a.isProcess()){
			doNodes(a);
		}
	}
	private void doNodes(ActivityNodeContainer container){
		List<INakedActivityNode> restingNodes = new ArrayList<INakedActivityNode>();
		for(INakedActivityNode n:container.getActivityNodes()){
			if(BehaviorUtil.isRestingNode(n)){
				restingNodes.add(n);
			}
			if(n instanceof INakedStructuredActivityNode){
				doNodes((ActivityNodeContainer) n);
			}
		}
		OJClass findClass = javaModel.findClass(new OJPathName(container.getMappingInfo().getQualifiedJavaName() + "State"));
		createResolver((OJEnum) findClass, restingNodes, container.getMappingInfo().requiresJavaRename() ? container.getMappingInfo().getOldQualifiedJavaName()
				+ "State" : null);
	}
	@VisitBefore
	public void visitStateMachine(INakedStateMachine sm){
		Set<INakedState> allStates = sm.getAllStates();
		List<INakedState> restingStates = new ArrayList<INakedState>();
		for(INakedState s:allStates){
			if(s.getKind().isRestingState()){
				restingStates.add(s);
			}
		}
		OJEnum e = (OJEnum) javaModel.findClass(new OJPathName(sm.getMappingInfo().getQualifiedJavaName() + "State"));
		createResolver(e, restingStates, sm.getMappingInfo().requiresJavaRename() ? sm.getMappingInfo().getOldQualifiedJavaName() + "State" : null);
	}
	@Override
	protected String getLiteralName(INakedElement l){
		return Jbpm5Util.stepLiteralName((INakedStep) l);
	}
}
