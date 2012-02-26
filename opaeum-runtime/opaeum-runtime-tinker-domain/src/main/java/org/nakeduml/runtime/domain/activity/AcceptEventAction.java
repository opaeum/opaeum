package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AcceptEventAction extends AbstractTriggeredAction {

	public AcceptEventAction() {
		super();
	}

	public AcceptEventAction(boolean persist, String name) {
		super(persist, name);
	}

	public AcceptEventAction(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		Boolean result = super.processNextStart();
		if (getInFlows().isEmpty()) {
			setNodeStatus(NodeStatus.ENABLED);
		}
		return result;
	}
	
	@Override
	public List<ControlToken> getInTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + getName());
		for (Edge edge : iter) {
			result.add(new ControlToken(edge.getInVertex()));
		}
		return result;
	}

	@Override
	protected List<? extends OutputPin<?>> getOutputPins() {
		return Collections.<OutputPin<?>> emptyList();
	}	
	
}
