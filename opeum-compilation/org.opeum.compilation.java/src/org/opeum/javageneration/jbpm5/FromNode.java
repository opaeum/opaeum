package org.opeum.javageneration.jbpm5;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.opeum.metamodel.commonbehaviors.GuardedFlow;
import org.opeum.metamodel.commonbehaviors.INakedStep;

public class FromNode {
	INakedStep waitingElement;
	Set<GuardedFlow> transitions = new HashSet<GuardedFlow>();
	private boolean isRestingNode;

	public FromNode(INakedStep source, boolean isRestingNode) {
		this.waitingElement = source;
		this.isRestingNode = isRestingNode;
	}

	public boolean isRestingNode() {
		return isRestingNode;
	}

	public void addTransition(String name, GuardedFlow guard) {
		transitions.add(guard);
	}
	public Set<GuardedFlow> getConditionalTransitions() {
		Set<GuardedFlow> results = new HashSet<GuardedFlow>();
		for (GuardedFlow f : transitions) {
			if (hasGuard(f)) {
				results.add(f);
			}
		}
		return results;
	}

	private boolean hasGuard(GuardedFlow t){
		return t.hasGuard();
	}

	public GuardedFlow getDefaultTransition() {
		for (GuardedFlow f : transitions) {
			if (!hasGuard(f)) {
				return f;
			}
		}
		return null;
	}

	public INakedStep getWaitingElement() {
		return waitingElement;
	}

	public Collection<GuardedFlow> getTransitions() {
		return transitions;
	}
}
