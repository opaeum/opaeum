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
	private IEvent event;
	
	public AbstractTriggeredAction() {
		super();
	}

	public AbstractTriggeredAction(boolean persist, String name) {
		super(persist, name);
	}

	public AbstractTriggeredAction(Vertex vertex) {
		super(vertex);
	}
	
	public void setTrigger(IEvent signal) {
		this.triggered = true;
		this.event = signal;
	}
	
	@Override
	protected void transferObjectTokensToAction() {
		copyEventToOutputPin(this.event);
		removeEvent(this.event);
	}	

	public abstract void copyEventToOutputPin(IEvent event);

	protected void removeEvent(IEvent event) {
		if (event instanceof ISignalEvent) {
			ISignal signal = ((ISignalEvent)event).getSignal();
			if (signal instanceof TinkerNode) {
				GraphDb.getDb().removeVertex(((TinkerNode) signal).getVertex());
			}
		}
		GraphDb.getDb().removeVertex(((TinkerNode)event).getVertex());
	}	
	
	@Override
	protected boolean isTriggered() {
		return this.triggered;
	}

	public List<Trigger> getTriggers() {
		return triggers;
	}
	
	public void addToTriggers(String name, String eventName) {
		this.triggers.add(new Trigger(name, eventName));
	}
	
	public boolean containsTriggerForEvent(IEvent event) {
		for (Trigger trigger : this.triggers) {
			if (trigger.getEventName().equals(event.getEventName())) {
				return true;
			}
		}
		return false;
	}

}
