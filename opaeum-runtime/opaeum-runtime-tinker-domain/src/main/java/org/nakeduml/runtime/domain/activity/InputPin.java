package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IInputPin;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class InputPin<O, IN extends ObjectToken<O>> extends Pin<O, IN, IN> implements IInputPin<O, IN> {

	public InputPin() {
		super();
	}

	public InputPin(boolean persist, String name) {
		super(persist, name);
	}

	public InputPin(Vertex vertex) {
		super(vertex);
	}

	protected abstract Action getAction();

	protected Boolean executeNode() {
		Action action = this.getAction();
		if (action.mayContinue()) {
			return action.executeNode();
		} else {
			return false;
		}
	}

	@Override
	protected List<? extends ObjectFlowKnown<O, IN>> getOutFlows() {
		return Collections.emptyList();
	}
	
}
