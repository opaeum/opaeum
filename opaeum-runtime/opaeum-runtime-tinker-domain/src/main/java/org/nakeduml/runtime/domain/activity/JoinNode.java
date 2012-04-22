package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNode<IN extends Token, OUT extends Token> extends ControlNode<IN, OUT> {

	public JoinNode() {
		super();
	}

	public JoinNode(Vertex vertex) {
		super(vertex);
	}

	public JoinNode(boolean persist, String name) {
		super(persist, name);
	}

	@Override
	protected abstract List<? extends ActivityEdge<IN>> getInFlows();

	protected abstract ActivityEdge<OUT> getOutFlow();

	@Override
	protected List<? extends ActivityEdge<OUT>> getOutFlows() {
		List<ActivityEdge<OUT>> result = new ArrayList<ActivityEdge<OUT>>();
		result.add(getOutFlow());
		return result;
	}

}
