package org.opaeum.runtime.statemachines;

import java.util.HashSet;
import java.util.Set;

public abstract class StateActivation<SME extends IStateMachineExecution,T extends IStateMachineToken<SME> >  extends VertexActivation<SME,T> {
	private Set<TransitionInstance<SME,T>> outgoing = new HashSet<TransitionInstance<SME,T>>();
	protected Set<RegionActivation<SME,T>> regions = new HashSet<RegionActivation<SME,T>>();

	public StateActivation(String id, RegionActivation<SME,T> region) {
		super(id, region);
	}

	public boolean contains(VertexActivation<SME,T> other) {
		for (RegionActivation<SME,T> r : regions) {
			if (r.contains(other)) {
				return true;
			}
		}
		return false;
	}

	public void enter(T token, VertexActivation<SME,T> target) {
		token.transferTo(this);
		onEntry(token);
		if (target != this) {
			for (RegionActivation<SME,T> ra : regions) {
				if (ra.contains(target)) {
					ra.enter(token, target);
				} else {
					ra.initiate(token);
				}
			}
		}
	}

	public void addOutgoing(TransitionInstance<SME,T> ti) {
		outgoing.add(ti);
	}

	protected void exit(T token) {
		super.exit(token);
		onExit();
	}

	public void onExit() {

	}

	public void onEntry(T token) {

	}

	public Set<RegionActivation<SME,T>> getRegions() {
		return regions;
	}

	public void setRegions(Set<RegionActivation<SME,T>> regions) {
		this.regions = regions;
	}


	public void onCompletion() {
		
	}
}
