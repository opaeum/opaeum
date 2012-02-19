package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ControlNode<T extends Token> extends ActivityNode<T> {

	public ControlNode() {
		super();
	}

	public ControlNode(boolean persist, String name) {
		super(persist, name);
	}

	public ControlNode(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected boolean mayAcceptToken() {
		return true;
	}
	
	@Override
	protected boolean mayContinue() {
		return doAllIncomingFlowsHaveTokens();
	}	
	
	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		removeIncomingControlTokens();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		execute();

		this.nodeStat.increment();

		// For each out control flow add a control token
		for (ActivityEdge<T> flow : getOutFlows()) {
			T token = instantiateToken(flow.getName());	
			addOutgoingToken(token);
		}

		// Continue each out flow with its tokens
		for (ActivityEdge<T> flow : getOutFlows()) {
			flow.setStarts(getOutTokens(flow.getName()));
			flowResult.add(flow.processNextStart());
		}

		// TODO Start transaction
		setNodeStatus(NodeStatus.COMPLETE);
		// TODO End transaction
		boolean result = true;
		for (Boolean b : flowResult) {
			if (!b) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	protected abstract T instantiateToken(String name);	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getInTokens() {
		List<T> result = new ArrayList<T>();
		for (ActivityEdge<T> flow : getInFlows()) {
			if (flow instanceof ControlFlow) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add((T) new ControlToken(edge.getInVertex()));
				}
			} else if (flow instanceof ObjectFlow) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add((T) new ObjectToken(edge.getInVertex()));
				}
			} else {
				throw new IllegalStateException("wtf");
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getInTokens(String inFlowName) {
		List<T> result = new ArrayList<T>();
		for (ActivityEdge<T> flow : getInFlows()) {
			if (flow.getName().equals(inFlowName)) {
				if (flow instanceof ControlFlow) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add((T) new ControlToken(edge.getInVertex()));
					}
				} else if (flow instanceof ObjectFlow) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add((T) new ObjectToken(edge.getInVertex()));
					}
				} else {
					throw new IllegalStateException("wtf");
				}
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getOutTokens() {
		List<T> result = new ArrayList<T>();
		for (ActivityEdge<T> flow : getOutFlows()) {
			if (flow instanceof ControlFlow) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add((T) new ControlToken(edge.getInVertex()));
				}
			} else if (flow instanceof ObjectFlow) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add((T) new ObjectToken(edge.getInVertex()));
				}
			} else {
				throw new IllegalStateException("wtf");
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getOutTokens(String outFlowName) {
		List<T> result = new ArrayList<T>();
		for (ActivityEdge<T> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				if (flow instanceof ControlFlow) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add((T) new ControlToken(edge.getInVertex()));
					}
				} else if (flow instanceof ObjectFlow) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add((T) new ObjectToken(edge.getInVertex()));
					}
				} else {
					throw new IllegalStateException("wtf");
				}
			}
		}
		return result;
	}

}
