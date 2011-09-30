package org.opeum.metamodel.statemachines.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.internal.NakedNameSpaceImpl;
import org.opeum.metamodel.statemachines.INakedRegion;
import org.opeum.metamodel.statemachines.INakedState;
import org.opeum.metamodel.statemachines.INakedStateMachine;
import org.opeum.metamodel.statemachines.INakedTransition;
import org.opeum.metamodel.statemachines.IRegionOwner;

public class NakedRegionImpl extends NakedNameSpaceImpl implements INakedRegion{
	private static final long serialVersionUID = 7711042727363444332L;
	private static final String META_CLASS = "region";
	private List<INakedState> states = new ArrayList<INakedState>();
	private List<INakedTransition> transitions= new ArrayList<INakedTransition>();
	private INakedState initial;
	public NakedRegionImpl(){
		super();
	}

	public List<INakedState> getStates(){
		return this.states;
	}
	public Set<INakedRegion> getPeerRegions(){
		Set<INakedRegion> results = new HashSet<INakedRegion>();
		results.addAll(getRegionOwner().getRegions());
		results.remove(this);
		return results;
	}
	public boolean hasPeerRegions(){
		return getRegionOwner().getRegions().size() > 1;
	}
	public boolean hasOwningState(){
		return getNameSpace() instanceof INakedState;
	}
	public boolean hasInitialState(){
		return getInitialState() != null;
	}
	public INakedState getOwningState(){
		if(hasOwningState()){
			return (INakedState) getNameSpace();
		}else{
			return null;
		}
	}
	public boolean hasOwningStateMachine(){
		return getNameSpace() instanceof NakedStateMachineImpl;
	}
	public INakedStateMachine getOwningStateMachine(){
		if(hasOwningStateMachine()){
			return (NakedStateMachineImpl) getNameSpace();
		}else{
			return null;
		}
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	public boolean hasFinalStates(){
		for(int i = 0;i < this.states.size();i++){
			INakedState state = this.states.get(i);
			if(state.getKind().isFinal()){
				return true;
			}
		}
		return false;
	}
	public INakedState getInitialState(){
		if(initial == null){
			for(INakedState state:states){
				if(state.getKind().isInitial() || state.getKind().isShallowHistory() || state.getKind().isDeepHistory()){
					if(this.initial != null && !state.equals(this.initial)){
						throw new IllegalStateException("A region cannot have more than one Initial type state");
					}
					this.initial = state;
				}
			}
		}
		return this.initial;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		element.setOwnerElement(this);
		if(element instanceof INakedState){
			this.states.add((INakedState) element);
		}else if(element instanceof INakedTransition){
			this.transitions.add((INakedTransition) element);
		}
	}
	public IRegionOwner getRegionOwner(){
		if(hasOwningState()){
			return getOwningState();
		}else{
			return getOwningStateMachine();
		}
	}
	public INakedStateMachine getStateMachine(){
		if(hasOwningState()){
			return getOwningState().getStateMachine();
		}else{
			return getOwningStateMachine();
		}
	}
	public boolean contains(INakedState state){
		Iterator<?> states = getStates().iterator();
		while(states.hasNext()){
			INakedState parent = (INakedState) states.next();
			if(parent.isAncestorOf(state)){
				return true;
			}
		}
		return false;
	}
	@Override
	public void removeOwnedElement(INakedElement element, boolean recursively) {
		super.removeOwnedElement(element, recursively);
		if(element instanceof INakedState){
			this.states.remove((INakedState) element);
		}else if(element instanceof INakedTransition){
			INakedTransition t = (INakedTransition) element;
			t.setSource(null);
			t.setTarget(null);
			this.transitions.remove(t);
			
		}
	};
	@Override
	public List<INakedTransition> getTransitions() {
		return transitions;
	}
	
}
