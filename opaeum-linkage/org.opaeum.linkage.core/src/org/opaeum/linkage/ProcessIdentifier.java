package org.opaeum.linkage;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.core.internal.NakedOperationImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.statemachines.StateMachineKind;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = LinkagePhase.class,after = {DependencyCalculator.class},requires = {
	DependencyCalculator.class
})
public class ProcessIdentifier extends AbstractModelElementLinker{
	private BehaviorUtil behaviorUtil;
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace){
		setBehaviorUtil(new BehaviorUtil(workspace));
	}
	@VisitBefore
	public void visitStateMachine(INakedStateMachine sm){
		if(sm.isClassifierBehavior()){
			sm.setStateMachineKind(StateMachineKind.LONG_LIVED);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(NakedOperationImpl op){
		if(!op.isLongRunning()){
			op.setIsLongRunning(getBehaviorUtil().isLongRunning(op));
		}
	}
	@VisitBefore
	public void visitBehavior(INakedActivity a){
		if(a.hasStereotype(StereotypeNames.BUSINES_PROCESS)){
			a.setActivityKind(ActivityKind.PROCESS);
		}
		if(getBehaviorUtil().requiresExternalInput(a)){
			a.setActivityKind(ActivityKind.PROCESS);
		}else if(a.getOwnedBehaviors().size()>0 || a.getOperations().size()>0 || a.hasMultipleConcurrentResults() || BehaviorUtil.hasParallelFlows(a) || BehaviorUtil.getNearestActualClass(a) == null || BehaviorUtil.hasLoopBack(a)){
			a.setActivityKind(ActivityKind.COMPLEX_SYNCHRONOUS_METHOD);
		}else{
			a.setActivityKind(ActivityKind.SIMPLE_SYNCHRONOUS_METHOD);
		}
	}
	private BehaviorUtil getBehaviorUtil(){
		if(behaviorUtil == null){
			behaviorUtil = new BehaviorUtil(workspace);
		}
		return behaviorUtil;
	}
	private void setBehaviorUtil(BehaviorUtil behaviorUtil){
		this.behaviorUtil = behaviorUtil;
	}
}
