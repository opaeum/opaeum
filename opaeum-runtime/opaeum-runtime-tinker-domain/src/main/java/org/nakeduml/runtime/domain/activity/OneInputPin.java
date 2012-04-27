package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IOneInputPin;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OneInputPin<O> extends InputPin<O, SingleObjectToken<O>> implements IOneInputPin<O> {

	public OneInputPin() {
		super();
	}

	public OneInputPin(boolean persist, String name) {
		super(persist, name);
	}

	public OneInputPin(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected List<OneObjectFlowKnown<O>> getInFlows() {
		return Collections.emptyList();
	}

	@Override
	protected abstract List<OneObjectFlowKnown<O>> getOutFlows();
	
	@Override
	protected int countNumberOfElementsOnTokens() {
		return getInTokens().size();
	}	
	
}
