package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IManyOutputPin;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ManyOutputPin<O> extends OutputPin<O, CollectionObjectToken<O>> implements IManyOutputPin<O> {

	public ManyOutputPin() {
		super();
	}

	public ManyOutputPin(boolean persist, String name) {
		super(persist, name);
	}

	public ManyOutputPin(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract List<ManyObjectFlowKnown<O>> getInFlows();

	@Override
	protected List<ManyObjectFlowKnown<O>> getOutFlows() {
		return Collections.emptyList();
	}
	
	@Override
	protected int countNumberOfElementsOnTokens() {
		int size = 0;
		List<CollectionObjectToken<O>> tokens = getOutTokens();
		for (CollectionObjectToken<O> collectionObjectToken : tokens) {
			size += collectionObjectToken.getElements().size();
		}
		return size;
	}	

}
