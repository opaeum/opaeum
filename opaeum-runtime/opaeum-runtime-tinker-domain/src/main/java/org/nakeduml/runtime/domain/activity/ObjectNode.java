package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ObjectNode<O> extends ActivityNode<ObjectToken<O>> {

	public ObjectNode() {
		super();
	}

	public ObjectNode(boolean persist, String name) {
		super(persist, name);
	}

	public ObjectNode(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected boolean mayAcceptToken() {
		return !isUpperReached();
	}

	protected boolean isUpperReached() {
		if (getUpperBound() == -1) {
			return false;
		} else {
			return getInTokens().size() >= getUpperBound();
		}
	}

	protected abstract int getUpperBound();

	@Override
	protected abstract List<ObjectFlow<O>> getInFlows();

	@Override
	protected abstract List<ObjectFlow<O>> getOutFlows();

	@Override
	public List<ObjectToken<O>> getInTokens() {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ObjectFlow<O> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ObjectToken<O>(edge.getInVertex()));
			}
		}
		return result;
	}
	
	@Override
	public List<ObjectToken<O>> getInTokens(String inFlowName) {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ActivityEdge<?> flow : getInFlows()) {
			if (inFlowName.equals(flow.getName())) {
				if (flow instanceof ObjectFlow) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add(new ObjectToken<O>(edge.getInVertex()));
					}
				} else {
					throw new IllegalStateException("wtf");
				}
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nakeduml.runtime.domain.activity.ActivityNode#getOutTokens()
	 * 
	 * Out tokens count tokens before they are duplicated onto all out flows
	 */
	@Override
	public List<ObjectToken<O>> getOutTokens() {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + getName());
		for (Edge edge : iter) {
			result.add(new ObjectToken<O>(edge.getInVertex()));
		}
		return result;
	}

	@Override
	public List<ObjectToken<O>> getOutTokens(String outFlowName) {
		List<ObjectToken<O>> result = new ArrayList<ObjectToken<O>>();
		for (ObjectFlow<O> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ObjectToken<O>(edge.getInVertex()));
				}
			}
		}
		return result;
	}

	@Override
	protected void addIncomingToken(ObjectToken<O> token) {
		token.removeEdgeFromActivityNode();
		token.addEdgeToActivityNode(this);
	}

	protected void addIncomingToken(ObjectTokenInterator<O> iter) {
		// mayAcceptToken validates upper
		while (iter.hasNext() && mayAcceptToken()) {
			ObjectToken<O> objectToken = (ObjectToken<O>) iter.next();
			addIncomingToken(objectToken);
		}
		if (!mayAcceptToken()) {
			logger.finest(String.format("Inputpin %s has reached its upper", getName()));
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("\n");
		sb.append(getClass().getSimpleName());
		sb.append(" has the following in tokens,");
		for (ObjectFlow<?> flow : getInFlows()) {
			for (ObjectToken<O> t : getInTokens(flow.getName())) {
				sb.append("\nFlow = ");
				sb.append(flow.getName());
				sb.append(" value = ");
				sb.append(t.toString());
			}
		}
		sb.append("\nAnd the following out tokens,");
		for (ObjectFlow<?> flow : getOutFlows()) {
			for (ObjectToken<O> t : getOutTokens(flow.getName())) {
				sb.append("\nFlow = ");
				sb.append(flow.getName());
				sb.append(" value = ");
				sb.append(t.toString());
			}
		}
		return sb.toString();
	}

}
