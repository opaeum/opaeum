package org.nakeduml.runtime.domain.activity;


import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.TinkerActivityFinalNodeBlockingQueue;

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
	protected List<ActivityEdge<Token>> getOutFlows() {
		return Collections.<ActivityEdge<Token>>emptyList();
	}
	
	@Override
	protected boolean mayContinue() {
		return true;
	}

	@Override
	protected Boolean executeNode() {
		Boolean result = super.executeNode();
		TinkerActivityFinalNodeBlockingQueue.INSTANCE.complete(getActivity().getUid());
		return result;
	}	

}
