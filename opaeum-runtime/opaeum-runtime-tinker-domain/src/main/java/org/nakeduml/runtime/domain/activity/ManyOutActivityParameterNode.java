package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IManyOutActivityParameterNode;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ManyOutActivityParameterNode<O> extends OutActivityParameterNode<O,CollectionObjectToken<O>> implements IManyOutActivityParameterNode<O> {

	public ManyOutActivityParameterNode() {
		super();
	}

	public ManyOutActivityParameterNode(boolean persist, String name) {
		super(persist, name);
	}

	public ManyOutActivityParameterNode(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	public abstract List<ManyObjectFlowKnown<O>> getIncoming();

	@Override
	public List<ManyObjectFlowKnown<O>> getOutgoing() {
		return Collections.emptyList();
	}

//	@Override
//	protected int countNumberOfElementsOnTokens() {
//		int size = 0;
//		List<CollectionObjectToken<O>> tokens = getInTokens();
//		for (CollectionObjectToken<O> collectionObjectToken : tokens) {
//			size += collectionObjectToken.getCollection().size();
//		}
//		return size;
//	}	
	
	@Override
	public List<O> getReturnParameterValues() {
		List<O> result = new ArrayList<O>();
		for (CollectionObjectToken<O> token : getInTokens()) {
			result.addAll(token.getElements());
		}
		return result;
	}

}