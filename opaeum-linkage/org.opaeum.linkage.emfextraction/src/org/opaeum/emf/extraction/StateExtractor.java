package org.opaeum.emf.extraction;

import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.statemachines.StateKind;
import org.opaeum.metamodel.statemachines.internal.NakedRegionImpl;
import org.opaeum.metamodel.statemachines.internal.NakedStateImpl;
import org.opaeum.metamodel.statemachines.internal.NakedStateMachineImpl;

@StepDependency(phase = EmfExtractionPhase.class,requires = FeatureExtractor.class,after = FeatureExtractor.class)
public class StateExtractor extends CommonBehaviorExtractor{
	@VisitBefore
	public void visitStateMachine(StateMachine esm, NakedStateMachineImpl nsm){
		if(esm.getContext() != null && esm.equals(esm.getContext().getClassifierBehavior())){
			INakedBehavioredClassifier ctx = (INakedBehavioredClassifier) getNakedPeer(esm.getContext());
			ctx.setClassifierBehavior(nsm);
		}
	}
	@VisitBefore
	public void visitFinalState(FinalState emfState,NakedStateImpl nakedState){
		nakedState.setKind(StateKind.FINAL);
	}
	@VisitBefore
	public void visitState(State emfState,NakedStateImpl nakedState){
		StateMachine emfStateMachine = null;
		Region region = emfState.getContainer();
		while(emfStateMachine == null){
			if(region.getState() != null){
				region = region.getState().getContainer();
			}else{
				emfStateMachine = region.getStateMachine();
			}
		}
		if(emfState.isOrthogonal()){
			nakedState.setKind(StateKind.ORTHOGONAL);
		}else if(emfState.isComposite()){
			nakedState.setKind(StateKind.COMPOSITE);
		}else{
			nakedState.setKind(StateKind.SIMPLE);
		}
		nakedState.setEntry(getOwnedBehavior(nakedState, emfState.getEntry()));
		nakedState.setExit(getOwnedBehavior(nakedState, emfState.getExit()));
		nakedState.setDoActivity(getOwnedBehavior(nakedState, emfState.getDoActivity()));
	}
	@VisitBefore
	public void visitRegion(Region r,NakedRegionImpl nr){
	}
	@VisitBefore
	public void visitPseudostate(Pseudostate emState,NakedStateImpl nakedState){
		nakedState.setKind(resolve(emState.getKind()));
		if(nakedState.getKind().isFork() || nakedState.getKind().isJoin()){
			if(emState.getIncomings().size() > 1){
				nakedState.setKind(StateKind.JOIN);
			}else if(emState.getOutgoings().size() > 1){
				nakedState.setKind(StateKind.FORK);
			}
		}
	}
	private StateKind resolve(PseudostateKind kind){
		if(PseudostateKind.CHOICE_LITERAL.equals(kind)){
			return StateKind.CHOICE;
		}
		if(PseudostateKind.DEEP_HISTORY_LITERAL.equals(kind)){
			return StateKind.JUNCTION;
		}
		if(PseudostateKind.ENTRY_POINT_LITERAL.equals(kind)){
			return StateKind.JUNCTION;
		}
		if(PseudostateKind.EXIT_POINT_LITERAL.equals(kind)){
			return StateKind.JUNCTION;
		}
		if(PseudostateKind.FORK_LITERAL.equals(kind)){
			return StateKind.FORK;
		}
		if(PseudostateKind.INITIAL_LITERAL.equals(kind)){
			return StateKind.INITIAL;
		}
		if(PseudostateKind.JOIN_LITERAL.equals(kind)){
			return StateKind.JOIN;
		}
		if(PseudostateKind.JUNCTION_LITERAL.equals(kind)){
			return StateKind.JUNCTION;
		}
		if(PseudostateKind.SHALLOW_HISTORY_LITERAL.equals(kind)){
			return StateKind.SHALLOW_HISTORY;
		}
		if(PseudostateKind.TERMINATE_LITERAL.equals(kind)){
			return StateKind.JUNCTION;
		}
		return StateKind.JUNCTION;
	}
}
