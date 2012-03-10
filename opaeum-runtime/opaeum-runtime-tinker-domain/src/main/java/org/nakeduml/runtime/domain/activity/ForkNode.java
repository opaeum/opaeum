package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ForkNode<IN extends Token> extends ControlNode<IN,IN> {

	public ForkNode() {
		super();
	}

	public ForkNode(Vertex vertex) {
		super(vertex);
	}

	public ForkNode(boolean persist, String name) {
		super(persist, name);
	}

	protected abstract ActivityEdge<IN> getInFlow();

	@Override
	protected List<ActivityEdge<IN>> getInFlows() {
		List<ActivityEdge<IN>> result = new ArrayList<ActivityEdge<IN>>();
		result.add(getInFlow());
		return result;
	}

}
