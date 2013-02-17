package org.opaeum.javageneration.bpm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.bpm.activity.ActivityNodeEnumerationImplementor;
import org.opaeum.javageneration.bpm.statemachine.StateEnumerationImplementor;
import org.opaeum.javageneration.persistence.AbstractEnumResolverImplementor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {StateEnumerationImplementor.class,
		ActivityNodeEnumerationImplementor.class},after = {StateEnumerationImplementor.class,ActivityNodeEnumerationImplementor.class})
public class ProcessStepResolverImplementor extends AbstractEnumResolverImplementor{
	@VisitBefore
	public void visitActivity(Activity a){
		if(EmfBehaviorUtil.isProcess( a)){
			doNodes(a);
		}
	}
	private void doNodes(Namespace container){
		List<ActivityNode> restingNodes = new ArrayList<ActivityNode>();
		for(ActivityNode n:EmfActivityUtil.getActivityNodes(container) ){
			if(EmfBehaviorUtil.isRestingNode(n)){
				restingNodes.add(n);
			}
			if(n instanceof StructuredActivityNode){
				doNodes((Namespace) n);
			}
		}
		OJClass findClass = javaModel.findClass(ojUtil.statePathname(container));
		createResolver((OJEnum) findClass, restingNodes, ojUtil.requiresJavaRename( container) ? ojUtil
				.getOldClassifierPathname(container) + "State" : null);
	}
	@VisitBefore
	public void visitStateMachine(StateMachine sm){
		Collection<State> allStates = EmfStateMachineUtil.getStatesRecursively(sm);
		List<State> restingStates = new ArrayList<State>();
		for(State s:allStates){
			restingStates.add(s);
		}
		OJEnum e = (OJEnum) javaModel.findClass(ojUtil.statePathname(sm));
		createResolver(e, restingStates, ojUtil.requiresJavaRename( sm) ? ojUtil.getOldClassifierPathname(sm) + "State"
				: null);
	}
	@Override
	protected String getLiteralName(NamedElement l){
		return BpmUtil.stepLiteralName(l);
	}
}
