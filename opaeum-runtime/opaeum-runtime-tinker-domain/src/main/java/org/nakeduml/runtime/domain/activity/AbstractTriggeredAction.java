package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.ISignal;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AbstractTriggeredAction extends Action {

	private List<Trigger> triggers = new ArrayList<Trigger>();
	private boolean triggered = false;
	private ISignal signal;
	
	public AbstractTriggeredAction() {
		super();
	}

	public AbstractTriggeredAction(boolean persist, String name) {
		super(persist, name);
	}

	public AbstractTriggeredAction(Vertex vertex) {
		super(vertex);
	}
	
	public void setTrigger(ISignal signal) {
		this.triggered = true;
		this.signal = signal;
	}
	
	@Override
	protected void transferObjectTokensToAction() {
		super.transferObjectTokensToAction();
		copySignalToOutputPin(this.signal);
		removeSignal(this.signal);
	}	

	public abstract void copySignalToOutputPin(ISignal signal);

	protected void removeSignal(ISignal signal) {
		if ( signal instanceof TinkerNode ) {
			GraphDb.getDb().removeVertex(((TinkerNode)signal).getVertex());
		}
	}
	
	@Override
	protected boolean isTriggered() {
		return this.triggered;
	}

	public List<Trigger> getTriggers() {
		return triggers;
	}
	
	public void addToTriggers(String name, Class<? extends ISignal> signalType) {
		this.triggers.add(new Trigger(name, signalType));
	}
	
	public boolean containsTriggerWithSignalType(Class<? extends ISignal> signalType) {
		for (Trigger trigger : this.triggers) {
			if (trigger.getSignalType().isAssignableFrom(signalType)) {
				return true;
			}
		}
		return false;
	}

}
