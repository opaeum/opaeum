package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.id.IdentityGenerator.GetGeneratedKeysDelegate;

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
			if (hasGuard(f)) {
				results.add(f);
			}
		}
		return results;
	}

	private boolean hasGuard(GuardedFlow t){
		if (t.getGuard() == null) {
			return false;
		} else {
			if (t.getGuard().getValue() .equals(Boolean.TRUE)) {
				//DEfault value in Topcased
				return false;
			} else {
				return true;
			}
		}
	}

	public GuardedFlow getDefaultTransition() {
		for (GuardedFlow f : transitions) {
			if (!hasGuard(f)) {
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
