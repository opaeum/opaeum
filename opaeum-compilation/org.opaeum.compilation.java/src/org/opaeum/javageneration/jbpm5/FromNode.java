package org.opaeum.javageneration.jbpm5;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Transition;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfStateMachineUtil;
//TODO after porting to EMF UML this class seems obsolete
public class FromNode {
	NamedElement waitingElement;
	Set<NamedElement> transitions = new HashSet<NamedElement>();
	private boolean isRestingNode;

	public FromNode(NamedElement source, boolean isRestingNode) {
		this.waitingElement = source;
		this.isRestingNode = isRestingNode;
	}

	public boolean isRestingNode() {
		return isRestingNode;
	}

	public void addTransition(String name, NamedElement guard) {
		transitions.add(guard);
	}
	public Set<NamedElement> getConditionalTransitions() {
		Set<NamedElement> results = new HashSet<NamedElement>();
		for (NamedElement f : transitions) {
			if (hasGuard(f)) {
				results.add(f);
			}
		}
		return results;
	}

	private boolean hasGuard(NamedElement t){
		if(t instanceof Transition){
			return EmfStateMachineUtil.hasGuard((Transition) t);
		}else{
			return EmfActivityUtil.hasGuard((ActivityEdge) t);
		}
	}

	public NamedElement getDefaultTransition() {
		for (NamedElement f : transitions) {
			if (!hasGuard(f)) {
				return f;
			}
		}
		return null;
	}

	public NamedElement getWaitingElement() {
		return waitingElement;
	}

	public Collection<NamedElement> getTransitions() {
		return transitions;
	}
}
