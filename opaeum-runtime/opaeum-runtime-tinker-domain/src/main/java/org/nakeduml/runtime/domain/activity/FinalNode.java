package org.nakeduml.runtime.domain.activity;


import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class FinalNode extends GenericControlNode {

	public FinalNode() {
		super();
	}
	
	public FinalNode(Vertex vertex) {
		super(vertex);
	}	

	public FinalNode(boolean persist, String name) {
		super(persist, name);
	}
	
	@Override
	public List<ActivityEdge<Token>> getOutgoing() {
		return Collections.<ActivityEdge<Token>>emptyList();
	}
	
	@Override
	public boolean mayContinue() {
		return true;
	}

	@Override
	protected Boolean executeNode() {
		Boolean result = super.executeNode();
//		TinkerActivityFinalNodeBlockingQueue.INSTANCE.complete(getActivity().getUid());
		return result;
	}	

}
