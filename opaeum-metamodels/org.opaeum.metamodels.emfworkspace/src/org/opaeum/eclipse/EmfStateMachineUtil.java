package org.opaeum.eclipse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Vertex;

public class EmfStateMachineUtil {
	public static Collection<StateMachine> getAllOwnedStateMachines(
			Class representedClass) {
		Collection<StateMachine> results = new ArrayList<StateMachine>();
		for (Behavior b : EmfBehaviorUtil.findBehaviorsInScope(representedClass)) {
			if (b instanceof StateMachine) {
				results.add((StateMachine) b);
			}
		}
		return results;
	}
	public static Collection<State> getAllStates(StateMachine stateMachine) {
		Collection<State> results = new HashSet<State>();
		addStatesRecursively(results, stateMachine.getRegions());
		return results;
	}

	private static void addStatesRecursively(Collection<State> results,
			EList<Region> regions) {
		for (Region r : regions) {
			for (Vertex v : r.getSubvertices()) {
				if (v instanceof State) {
					State state = (State) v;
					results.add(state);
					addStatesRecursively(results, state.getRegions());
				}
			}
		}
	}
	public static List<Operation> getNonTriggerOperations(StateMachine sm) {
		List<Operation> opers = new ArrayList<Operation>();
		if (sm.getContext() != null
				&& sm == sm.getContext().getClassifierBehavior()) {
			opers.addAll(sm.getContext().getAllOperations());
		} else {
			opers.addAll(sm.getAllOperations());
		}
		Set<CallEvent> results = new HashSet<CallEvent>();
		addCallEvents(results, sm.getRegions());
		opers.removeAll(results);
		return opers;
	}
	static void addCallEvents(Set<CallEvent> results,
			EList<Region> regions) {
		for (Region child : regions) {
			for (Transition tn : child.getTransitions()) {
				for (Trigger tr : tn.getTriggers()) {
					if (tr.getEvent() instanceof CallEvent) {
						results.add((CallEvent) tr.getEvent());
					}
				}
			}
			for (Vertex v : child.getSubvertices()) {
				if (v instanceof State) {
					addCallEvents(results, ((State) v).getRegions());
				}
			}
		}
	}
	public static StateMachine getStateMachine(Element s) {
		while (!(s instanceof StateMachine)) {
			s = s.getOwner();
		}
		return (StateMachine) s;
	}
	public static List<Operation> getTriggerOperations(State s) {
		EList<Transition> outgoings = s.getOutgoings();
		List<Operation> results = new ArrayList<Operation>();
		for (Transition transition : outgoings) {
			EList<Trigger> triggers = transition.getTriggers();
			for (Trigger trigger : triggers) {
				if (trigger.getEvent() instanceof CallEvent
						&& ((CallEvent) trigger.getEvent()).getOperation() != null) {
					results
							.add(((CallEvent) trigger.getEvent())
									.getOperation());
				}
			}
		}
		return results;
	}
	public static StringBuffer getStatePath(State myState) {
		StringBuffer sb = new StringBuffer();
		if(myState.getContainer().getOwner() instanceof State){
			sb.append(getStatePath((State) myState.getContainer().getOwner()));
			sb.append("::");
		}
		sb.append(myState.getName());
		return sb;
	}
}
