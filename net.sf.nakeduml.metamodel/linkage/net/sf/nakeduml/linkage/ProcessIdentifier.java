package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.StateMachineKind;
@StepDependency(phase = LinkagePhase.class, after = {UserRepresentationCalculator.class}, requires = {UserRepresentationCalculator.class})
public class ProcessIdentifier extends AbstractModelElementLinker{
	@VisitBefore
	public void visitOperation(INakedOperation o){
		if(BehaviorUtil.isUserResponsibility(o)){
			o.setIsUserResponsibility(true);
		}
	}
	@VisitBefore
	public void visitBehavior(INakedStateMachine sm){
		if(sm.isClassifierBehavior()){
			sm.setStateMachineKind(StateMachineKind.LONG_LIVED);
		}
	}
	@VisitBefore
	public void visitBehavior(INakedActivity a){
		if(BehaviorUtil.requiresExternalInput(a)){
			a.setActivityKind(ActivityKind.PROCESS);
		}else if(a.hasMultipleConcurrentResults() || BehaviorUtil.hasParallelFlows(a)){
			a.setActivityKind(ActivityKind.COMPLEX_SYNCHRONOUS_METHOD);
		}else{
			a.setActivityKind(ActivityKind.SIMPLE_SYNCHRONOUS_METHOD);
		}
	}
}
