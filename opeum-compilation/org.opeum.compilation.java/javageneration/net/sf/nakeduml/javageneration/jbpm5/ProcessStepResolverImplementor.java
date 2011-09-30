package org.opeum.javageneration.jbpm5;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.jbpm5.activity.ActivityNodeEnumerationImplementor;
import org.opeum.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import org.opeum.javageneration.persistence.AbstractEnumResolverImplementor;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.activities.INakedActivityNode;
import org.opeum.metamodel.commonbehaviors.INakedStep;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.statemachines.INakedState;
import org.opeum.metamodel.statemachines.INakedStateMachine;

import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJEnum;

@StepDependency(phase = JavaTransformationPhase.class,requires = {StateEnumerationImplementor.class,ActivityNodeEnumerationImplementor.class},after = {StateEnumerationImplementor.class,ActivityNodeEnumerationImplementor.class})
public class ProcessStepResolverImplementor extends AbstractEnumResolverImplementor{
	@VisitBefore
	public void visitActivity(INakedActivity a){
		if(a.isProcess()){
			List<INakedActivityNode> restingNodes = new ArrayList<INakedActivityNode>();
			for(INakedActivityNode n:a.getActivityNodesRecursively()){
				if(BehaviorUtil.isRestingNode(n)){
					restingNodes.add(n);
				}				
			}
			OJClass findClass = javaModel.findClass(new OJPathName(a.getMappingInfo().getQualifiedJavaName()+"State"));
			createResolver((OJEnum) findClass, restingNodes, a.getMappingInfo().requiresJavaRename()?a.getMappingInfo().getOldQualifiedJavaName()+"State":null );
		}
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
		createResolver(e, restingStates,sm.getMappingInfo().requiresJavaRename()?sm.getMappingInfo().getOldQualifiedJavaName() + "State":null);
	}
	@Override
	protected String getLiteralName(INakedElement l){
		return Jbpm5Util.stepLiteralName((INakedStep) l);
	}
}
