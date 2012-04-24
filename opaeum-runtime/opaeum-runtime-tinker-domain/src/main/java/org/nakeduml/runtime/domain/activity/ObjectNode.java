package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ObjectNode<O,IN extends ObjectToken<O>,OUT extends ObjectToken<O>> extends ActivityNode<IN, OUT> {

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
	protected abstract int getLowerMultiplicity();
	protected abstract int getUpperMultiplicity();

	protected boolean isLowerMultiplicityReached() {
		int size = countNumberOfElementsOnTokens();
		return size >= getLowerMultiplicity();
	}

	protected abstract int countNumberOfElementsOnTokens();
	
	@Override
	protected boolean mayContinue() {
		return isLowerMultiplicityReached();
	}

	protected boolean isUpperMultiplicityReached() {
		int size = countNumberOfElementsOnTokens();
		return size >= getUpperMultiplicity();
	}
	
	@Override
	public List<IN> getInTokens() {
		List<IN> result = new ArrayList<IN>();
		for (ObjectFlowKnown<O,IN> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				try {
					result.add(constructInToken(edge));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@Override
	public List<IN> getInTokens(String inFlowName) {
		List<IN> result = new ArrayList<IN>();
		for (ObjectFlowKnown<O,IN> flow : getInFlows()) {
			if (inFlowName.equals(flow.getName())) {
				if (flow instanceof ObjectFlowKnown) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add(constructInToken(edge));
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
	 * Out tokens tokens before they are duplicated onto all out flows
	 */
	@Override
	public List<OUT> getOutTokens() {
		List<OUT> result = new ArrayList<OUT>();
		Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + getName());
		for (Edge edge : iter) {
			result.add(constructOutToken(edge));
		}
		return result;
	}
	
	@Override
	public List<OUT> getOutTokens(String outFlowName) {
		List<OUT> result = new ArrayList<OUT>();
		for (ObjectFlowKnown<O,OUT> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(constructOutToken(edge));
				}
			}
		}
		return result;
	}

	
	@Override
	protected abstract List<? extends ObjectFlowKnown<O,IN>> getInFlows();
	@Override
	protected abstract List<? extends ObjectFlowKnown<O,OUT>> getOutFlows();

	@SuppressWarnings("unchecked")
	protected IN constructInToken(Edge edge) {
		try {
			Class<?> c = Class.forName((String) edge.getProperty("tokenClass"));
			return (IN) c.getConstructor(Vertex.class).newInstance(edge.getInVertex());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	protected OUT constructOutToken(Edge edge) {
		try {
			Class<?> c = Class.forName((String) edge.getProperty("tokenClass"));
			return (OUT) c.getConstructor(Vertex.class).newInstance(edge.getInVertex());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void addIncomingToken(IN token) {
		token.removeEdgeFromActivityNode();
		token.addEdgeToActivityNode(this);
	}

	protected void addIncomingToken(ObjectTokenInterator<O, IN> iter) {
		// mayAcceptToken validates upper
		while (iter.hasNext() && mayAcceptToken()) {
			IN objectToken = (IN) iter.next();
			addIncomingToken(objectToken);
		}
		if (!mayAcceptToken()) {
			logger.finest(String.format("Inputpin %s has reached its upper", getName()));
		}
	}

//	protected <T> List<ObjectFlowKnown<O,ObjectToken<O>>> convertToKnownObjectFlows(List<ObjectFlowUnknown> asList) {
//		List<ObjectFlowKnown<O,ObjectToken<O>>> result = new ArrayList<ObjectFlowKnown<O,ObjectToken<O>>>();
//		for (ObjectFlowUnknown objectFlowUnknown : asList) {
//			result.add(objectFlowUnknown.<T> convertToKnownObjectFlow());
//		}
//		return result;
//	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("\n");
		sb.append(getClass().getSimpleName());
		sb.append(" has the following in tokens,");
		for (ObjectFlowKnown<?,?> flow : getInFlows()) {
			for (IN t : getInTokens(flow.getName())) {
				sb.append("\nFlow = ");
				sb.append(flow.getName());
				sb.append(" value = ");
				sb.append(t.toString());
			}
		}
		sb.append("\nAnd the following out tokens,");
		for (ObjectFlowKnown<O,OUT> flow : getOutFlows()) {
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
