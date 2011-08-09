package net.sf.nakeduml.javageneration.jbpm5;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityNodeEnumerationImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateEnumerationImplementor;
import net.sf.nakeduml.javageneration.persistence.AbstractEnumResolverImplementor;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJEnum;

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
}
