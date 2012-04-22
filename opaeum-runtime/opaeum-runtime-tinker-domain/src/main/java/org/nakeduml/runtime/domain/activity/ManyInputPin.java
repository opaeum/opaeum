package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ManyInputPin<O> extends InputPin<O, CollectionObjectToken<O>> {

	public ManyInputPin() {
		super();
	}

	public ManyInputPin(boolean persist, String name) {
		super(persist, name);
	}

	public ManyInputPin(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected List<ManyObjectFlowKnown<O>> getInFlows() {
		return Collections.emptyList();
	}

	@Override
	protected abstract List<ManyObjectFlowKnown<O>> getOutFlows();	

	@Override
	protected int countNumberOfElementsOnTokens() {
		int size = 0;
		List<CollectionObjectToken<O>> tokens = getInTokens();
		for (CollectionObjectToken<O> collectionObjectToken : tokens) {
			size += collectionObjectToken.getCollection().size();
		}
		return size;
	}

}
