package org.opaeum.runtime.statemachines;

import java.util.HashSet;
import java.util.Set;

public abstract class StateActivation extends VertexActivation {
	private Set<TransitionInstance> outgoing = new HashSet<TransitionInstance>();
	protected Set<RegionActivation> regions = new HashSet<RegionActivation>();

	public StateActivation(String id, RegionActivation region) {
		super(id, region);
	}

	public boolean contains(VertexActivation other) {
		for (RegionActivation r : regions) {
			if (r.contains(other)) {
				return true;
			}
		}
		return false;
	}

	public void enter(StateMachineToken token, VertexActivation target) {
		token.transferTo(this);
		onEntry(token);
		if (target != this) {
			for (RegionActivation ra : regions) {
				if (ra.contains(target)) {
					ra.enter(token, target);
				} else {
					ra.initiate(token);
				}
			}
		}
	}

	public void addOutgoing(TransitionInstance ti) {
		outgoing.add(ti);
	}

	protected void exit(StateMachineToken token) {
		super.exit(token);
		onExit();
	}

	public void onExit() {

	}

	public void onEntry(StateMachineToken token) {

	}

	public Set<RegionActivation> getRegions() {
		return regions;
	}

	public void setRegions(Set<RegionActivation> regions) {
		this.regions = regions;
	}

	public TransitionInstance selectCompletionTransition() {
		return null;
	}

	public void onCompletion() {
		
	}
}
