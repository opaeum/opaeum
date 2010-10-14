package net.sf.nakeduml.javageneration.bpm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.core.INakedElement;

public class FromNode {
	INakedElement waitingElement;
	Set<GuardedFlow> transitions = new HashSet<GuardedFlow>();
	private boolean isRestingNode;

	public FromNode(INakedElement source, boolean isRestingNode) {
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
			if (f.getGuard() != null) {
				results.add(f);
			}
		}
		return results;
	}

	public GuardedFlow getDefaultTransition() {
		for (GuardedFlow f : transitions) {
			if (f.getGuard() == null) {
				return f;
			}
		}
		return null;
	}

	public INakedElement getWaitingElement() {
		return waitingElement;
	}

	public Collection<GuardedFlow> getTransitions() {
		return transitions;
	}
}
