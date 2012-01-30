package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.runtime.domain.ISignal;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AbstractTriggeredAction extends AbstractAction {

	private List<Trigger> triggers = new ArrayList<Trigger>();
	private boolean triggered = false;
	
	public AbstractTriggeredAction() {
		super();
	}

	public AbstractTriggeredAction(boolean persist, String name) {
		super(persist, name);
	}

	public AbstractTriggeredAction(Vertex vertex) {
		super(vertex);
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
				this.triggered = true;
				return true;
			}
		}
		return false;
	}

}
