package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNodeObjectTokenUnknownWithInControlToken<OUT extends ObjectToken<?>> extends JoinNode<Token, OUT> {

	public JoinNodeObjectTokenUnknownWithInControlToken() {
		super();
	}

	public JoinNodeObjectTokenUnknownWithInControlToken(Vertex vertex) {
		super(vertex);
	}

	public JoinNodeObjectTokenUnknownWithInControlToken(boolean persist, String name) {
		super(persist, name);
	}

	@Override
	protected abstract ObjectFlowUnknown<OUT> getOutFlow();

	@Override
	protected List<ObjectFlowUnknown<OUT>> getOutFlows() {
		List<ObjectFlowUnknown<OUT>> result = new ArrayList<ObjectFlowUnknown<OUT>>();
		result.add(getOutFlow());
		return result;
	}

	@Override
	protected abstract List<ActivityEdge<Token>> getInFlows();

	/*
	 * (non-Javadoc)
	 * @see org.nakeduml.runtime.domain.activity.JoinNode#getInTokens()
	 * 
	 * Consume control tokens, only object token continue.
	 */
	//TODO
	@SuppressWarnings("rawtypes")
	@Override
	public List<Token> getInTokens() {
		if (true) {
			throw new IllegalStateException("Checka this out");
			//Not sure about this consuming, consume elsewhere
		}		
		List<Token> result = new ArrayList<Token>();
		for (ActivityEdge<? extends Token> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				Token token;
				if (!(flow instanceof ControlFlow)) {
					token = contructOutToken(edge);
					result.add(token);
				} else {
					token = new ControlToken(edge.getInVertex());
					token.remove();
				}
			}
		}
		return result;
	}

}
