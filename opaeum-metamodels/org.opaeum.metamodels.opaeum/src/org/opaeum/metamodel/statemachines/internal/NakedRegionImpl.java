package org.opaeum.metamodel.statemachines.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.opaeum.metamodel.core.DefaultOpaeumComparator;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.internal.NakedNameSpaceImpl;
import org.opaeum.metamodel.statemachines.INakedRegion;
import org.opaeum.metamodel.statemachines.INakedState;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.statemachines.INakedTransition;
import org.opaeum.metamodel.statemachines.IRegionOwner;

public class NakedRegionImpl extends NakedNameSpaceImpl implements INakedRegion{
	private static final long serialVersionUID = 7711042727363444332L;
	private static final String META_CLASS = "region";
	private SortedSet<INakedState> states = new TreeSet<INakedState>(new DefaultOpaeumComparator());
	private SortedSet<INakedTransition> transitions = new TreeSet<INakedTransition>(new DefaultOpaeumComparator());
	private INakedState initial;
	public NakedRegionImpl(){
		super();
	}
	public Collection<INakedState> getStates(){
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
	public Collection<INakedElement> removeOwnedElement(INakedElement element,boolean recursively){
		Collection<INakedElement> result = super.removeOwnedElement(element, recursively);
		if(element instanceof INakedState){
			this.states.remove((INakedState) element);
		}else if(element instanceof INakedTransition){
			INakedTransition t = (INakedTransition) element;
			if(recursively){
				t.getSource().getOutgoing().remove(t);
				t.getTarget().getIncoming().remove(t);
				result.add(t.getSource());
				result.add(t.getTarget());
				t.setSource(null);
				t.setTarget(null);
			}
			this.transitions.remove(t);
		}
		return result;
	};
	@Override
	public Collection<INakedTransition> getTransitions(){
		return transitions;
	}
}
