package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class Action extends ExecutableNode {

	public Action() {
		super();
	}

	public Action(Vertex vertex) {
		super(vertex);
	}

	public Action(boolean persist, String name) {
		super(persist, name);
	}

	protected abstract boolean hasPostConditionPassed();
	protected abstract boolean hasPreConditionPassed();
	protected abstract List<? extends InputPin<?>> getInputPins();
	
	@Override
	protected boolean mayContinue() {
		return doAllIncomingFlowsHaveTokens() && hasPreConditionPassed() && hasPostConditionPassed() && isTriggered() && isInputPinsSatisfied();
	}

	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		removeIncomingControlTokens();
		transferObjectTokensToAction();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		execute();

		this.nodeStat.increment();

		// For each out control flow add a control token
		for (ActivityEdge<ControlToken> flow : getOutFlows()) {
			addOutgoingToken(new ControlToken(flow.getName()));
		}

		// Continue each out flow with its tokens
		for (ActivityEdge<ControlToken> flow : getOutFlows()) {
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

	protected void transferObjectTokensToAction() {
		for (InputPin<?> inputPin : this.getInputPins()) {
			for (ObjectToken<?> token : inputPin.getInTokens()) {
				token.removeEdgeFromActivityNode();
				addToInputPinVariable(inputPin, token.getObject());
			}
		}
	}

	protected abstract void addToInputPinVariable(InputPin<?> inputPin, Object object);

	protected boolean isInputPinsSatisfied() {
		for (InputPin<?> inputPin : this.getInputPins()) {
			if (!inputPin.mayContinue()) {
				return false;
			}
		}
		return true;
	}

	protected void execute() {
		//Empty
		
		//Consume object tokens
		for (InputPin<?> inputPin : this.getInputPins()) {
			for (ObjectToken<?> token : inputPin.getInTokens()) {
				token.removeEdgeFromActivityNode();
				token.removeEdgeToObject();
				GraphDb.getDb().removeVertex(token.getVertex());
			}
		}		
		System.out.println("executing action " + getClass().getSimpleName());
	}

	protected boolean isTriggered() {
		return true;
	}
	
	@Override
	protected boolean mayAcceptToken() {
		return true;
	}
	
	@Override
	public List<ControlToken> getInTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ActivityEdge<ControlToken> flow : getInFlows()) {
			if (flow instanceof ControlFlow) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ControlToken(edge.getInVertex()));
				}
			} else {
				throw new IllegalStateException("wtf");
			}
		}
		return result;
	}
	
	@Override
	public List<ControlToken> getInTokens(String inFlowName) {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ActivityEdge<ControlToken> flow : getInFlows()) {
			if (inFlowName.equals(flow.getName())) {
				if (flow instanceof ControlFlow) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add(new ControlToken(edge.getInVertex()));
					}
				} else {
					throw new IllegalStateException("wtf");
				}
			}
		}
		return result;
	}
	
	@Override
	public List<ControlToken> getOutTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ActivityEdge<ControlToken> flow : getOutFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ControlToken(edge.getInVertex()));
			}
		}
		return result;
	}	

	@Override
	public List<ControlToken> getOutTokens(String outFlowName) {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ActivityEdge<ControlToken> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					ControlToken e = new ControlToken(edge.getInVertex());
					result.add(e);
				}
			}
		}
		return result;
	}

}
