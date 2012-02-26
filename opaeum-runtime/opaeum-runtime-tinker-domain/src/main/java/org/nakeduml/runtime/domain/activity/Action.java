package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.nakeduml.runtime.domain.BaseTinkerSoftDelete;
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

	protected abstract List<? extends OutputPin<?>> getOutputPins();

	protected abstract void addToInputPinVariable(InputPin<?> inputPin, Object object);

	protected abstract List<? extends Object> getInputPinVariables();

	@Override
	protected boolean mayContinue() {
		return doAllIncomingFlowsHaveTokens() && hasPreConditionPassed() && hasPostConditionPassed() && isTriggered() && isInputPinsSatisfied();
	}

	@Override
	protected Boolean executeNode() {
		if (logger.isLoggable(Level.FINEST)) {
			logger.finest("start executeNode");
			logger.finest(toString());
		}
		List<Boolean> flowResult = new ArrayList<Boolean>();

		transferObjectTokensToAction();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		execute();

		this.nodeStat.increment();

		for (ControlToken token : getInTokens()) {
			token.remove();
		}
		// For each out flow add a token
		for (ActivityEdge<ControlToken> flow : getOutFlows()) {
			ControlToken duplicate = new ControlToken(flow.getName());
			addOutgoingToken(duplicate);
		}

		// Continue each out flow with its tokens
		for (ActivityEdge<ControlToken> flow : getOutFlows()) {
			flow.setStarts(getOutTokens(flow.getName()));
			flowResult.add(flow.processNextStart());
		}

		for (OutputPin<?> outputPin : getOutputPins()) {
			// The output pins starts must be set in concrete actions
			outputPin.copyTokensToStart();
			flowResult.add(outputPin.processNextStart());
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
		if (logger.isLoggable(Level.FINEST)) {
			logger.finest("end executeNode");
			logger.finest(toString());
		}
		return result;
	}

	protected void transferObjectTokensToAction() {
		for (InputPin<?> inputPin : this.getInputPins()) {
			for (ObjectToken<?> token : inputPin.getInTokens()) {
				token.removeEdgeFromActivityNode();
				addToInputPinVariable(inputPin, token.getObject());
				token.remove();
			}
		}
	}

	protected boolean isInputPinsSatisfied() {
		for (InputPin<?> inputPin : this.getInputPins()) {
			if (!inputPin.mayContinue()) {
				return false;
			}
		}
		return true;
	}

	protected void execute() {
		// Empty

		// Consume object tokens
		for (InputPin<?> inputPin : this.getInputPins()) {
			for (ObjectToken<?> token : inputPin.getInTokens()) {
				token.removeEdgeFromActivityNode();
				token.removeEdgeToObject();
				GraphDb.getDb().removeVertex(token.getVertex());
			}
		}
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
		for (ActivityEdge<?> flow : getInFlows()) {
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
		for (ActivityEdge<?> flow : getInFlows()) {
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

	@Override
	protected List<? extends ActivityEdge<?>> getInFlows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<? extends ActivityEdge<ControlToken>> getOutFlows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseTinkerSoftDelete getContextObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("\n");
		sb.append("InputPin:");
		int i = 0;
		for (Object o : getInputPinVariables()) {
			sb.append("		");
			sb.append(getInputPins().get(i++).getName());
			sb.append(" = ");
			sb.append(o);
			sb.append("\n");
		}
		sb.append("OutputPin:\n");
		sb.append("Values on pin:");
		for (OutputPin<?> outputPin : getOutputPins()) {
			sb.append("		");
			sb.append(outputPin.getName());
			sb.append(" = ");
			for (ObjectToken<?> t : outputPin.getOutTokens()) {
				sb.append(t.getObject());
			}
		}
		sb.append("\nValues on pin's outflows:");
		for (OutputPin<?> outputPin : getOutputPins()) {
			sb.append("		");
			sb.append(outputPin.getName());
			sb.append(" = ");
			for (ActivityEdge<?> flow : outputPin.getOutFlows()) {
				for (ObjectToken<?> t : outputPin.getOutTokens(flow.getName())) {
					sb.append("flow = ");
					sb.append(flow.getName());
					sb.append("pin value = ");
					sb.append(t.getObject());
				}
			}
		}
		return sb.toString();
	}

}
