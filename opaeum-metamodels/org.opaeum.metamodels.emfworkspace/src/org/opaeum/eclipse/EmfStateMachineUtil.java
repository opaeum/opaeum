package org.opaeum.eclipse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Vertex;

public class EmfStateMachineUtil{
	public static String getStatePath(Vertex v){
		StringBuilder sb = new StringBuilder();
		maybeAppendParent(v, sb);
		sb.append(v.getName());
		return sb.toString();
	}
	private static void maybeAppendParent(Vertex v,StringBuilder sb){
		if(v.getContainer().getState() != null){
			sb.append(v.getContainer().getState().getName());
			sb.append("::");
			maybeAppendParent(v.getContainer().getState(), sb);
		}
	}
	public static Collection<StateMachine> getAllOwnedStateMachines(Class representedClass){
		Collection<StateMachine> results = new ArrayList<StateMachine>();
		for(Behavior b:EmfBehaviorUtil.findBehaviorsInScope(representedClass)){
			if(b instanceof StateMachine){
				results.add((StateMachine) b);
			}
		}
		return results;
	}
	public static Collection<State> getAllStates(StateMachine stateMachine){
		Collection<State> results = new HashSet<State>();
		addStatesRecursively(results, stateMachine.getRegions());
		return results;
	}
	private static void addStatesRecursively(Collection<State> results,EList<Region> regions){
		for(Region r:regions){
			for(Vertex v:r.getSubvertices()){
				if(v instanceof State){
					State state = (State) v;
					results.add(state);
					addStatesRecursively(results, state.getRegions());
				}
			}
		}
	}
	public static List<Operation> getNonTriggerOperations(StateMachine sm){
		List<Operation> opers = new ArrayList<Operation>();
		if(sm.getContext() != null && sm == sm.getContext().getClassifierBehavior()){
			opers.addAll(sm.getContext().getAllOperations());
		}else{
			opers.addAll(sm.getAllOperations());
		}
		Set<CallEvent> results = new HashSet<CallEvent>();
		addCallEvents(results, sm.getRegions());
		opers.removeAll(results);
		return opers;
	}
	static void addCallEvents(Set<CallEvent> results,EList<Region> regions){
		for(Region child:regions){
			for(Transition tn:child.getTransitions()){
				for(Trigger tr:tn.getTriggers()){
					if(tr.getEvent() instanceof CallEvent){
						results.add((CallEvent) tr.getEvent());
					}
				}
			}
			for(Vertex v:child.getSubvertices()){
				if(v instanceof State){
					addCallEvents(results, ((State) v).getRegions());
				}
			}
		}
	}
	public static StateMachine getStateMachine(Element s){
		while(!(s instanceof StateMachine)){
			s = s.getOwner();
		}
		return (StateMachine) s;
	}
	public static List<Operation> getTriggerOperations(State s){
		EList<Transition> outgoings = s.getOutgoings();
		List<Operation> results = new ArrayList<Operation>();
		for(Transition transition:outgoings){
			EList<Trigger> triggers = transition.getTriggers();
			for(Trigger trigger:triggers){
				if(trigger.getEvent() instanceof CallEvent && ((CallEvent) trigger.getEvent()).getOperation() != null){
					results.add(((CallEvent) trigger.getEvent()).getOperation());
				}
			}
		}
		return results;
	}
	public static Collection<Region> getRegions(Namespace owner){
		if(owner instanceof StateMachine){
			return ((StateMachine) owner).getRegions();
		}else if(owner instanceof State){
			return ((State) owner).getRegions();
		}
		return Collections.emptySet();
	}
	public static Pseudostate getHistoryPeer(Vertex state){
		for(Vertex s:state.getContainer().getSubvertices()){
			if(isShallowHistory(s)){
				return (Pseudostate) s;
			}
		}
		return null;
	}
	public static boolean isShallowHistory(Vertex s){
		return s instanceof Pseudostate && ((Pseudostate) s).getKind() == PseudostateKind.SHALLOW_HISTORY_LITERAL;
	}
	public static boolean doesWorkOnEntry(Vertex state){
		if(state instanceof State){
			State s = (State) state;
			if( s.getEntry() != null || s.getDoActivity() != null || s instanceof FinalState){
				return true;
			}
		}
		return hasImmediateCompletionTransitions(state) || requestsEvents(state) || (needsToRecordHistory(state)) || isShallowHistory(state)
				|| requestsEvents(state);
	}
	private static boolean needsToRecordHistory(Vertex state){
		return getHistoryPeer(state) != null;
	}
	private static boolean hasImmediateCompletionTransitions(Vertex state){
		if(getCompletionTransitions(state).size()>0){
			if(state instanceof State){
				State s =(State) state;
				if(s.isOrthogonal() || s.isComposite()){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
	public static Collection<Transition> getCompletionTransitions(Vertex state){
		Collection<Transition>  result = new ArrayList<Transition>();
		EList<Transition> outgoings = state.getOutgoings();
		for(Transition transition:outgoings){
			if(!hasGuard(transition)){
				result.add(transition);
			}
		}
		return result;
	}
	public static boolean hasGuard(Transition transition){
		return transition.getGuard()!=null && transition.getGuard().getSpecification() instanceof OpaqueExpression;
	}
	public static boolean requestsEvents(Vertex state){
		for(Transition t:state.getOutgoings()){
			for(Trigger tigger:t.getTriggers()){
				if(tigger.getEvent() instanceof ChangeEvent || tigger.getEvent() instanceof TimeEvent){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean doesWorkOnExit(State state){
		return state.getExit() != null || requestsEvents(state);
	}
	public static boolean isStartingVertex(Vertex v){
		if(v instanceof Pseudostate){
			Pseudostate state =(Pseudostate) v;
			return state.getKind() == PseudostateKind.INITIAL_LITERAL || state.getKind()==PseudostateKind.DEEP_HISTORY_LITERAL || state.getKind()==PseudostateKind.SHALLOW_HISTORY_LITERAL;
		}
		return false;
	}
	public static Collection<Transition> getTransitions(StateMachine ns){
		Collection<Transition> result = new HashSet<Transition>();
		EList<Region> regions = ns.getRegions();
		addTransitions(result, regions);
		return result;
	}
	private static void addTransitions(Collection<Transition> result,EList<Region> regions){
		for(Region region:regions){
			result.addAll(region.getTransitions());
			for(Vertex vertex:region.getSubvertices()){
				if(vertex instanceof State){
					addTransitions(result, ((State) vertex).getRegions());
				}
			}
		}
	}
	public static Collection<Transition> getConditionalTransitions(Vertex node){
		Set<Transition> result = new HashSet<Transition>();
		EList<Transition> outgoings = node.getOutgoings();
		for(Transition transition:outgoings){
			if(hasGuard(transition)){
				result.add(transition);
			}
		}
		return result;
	}
}
