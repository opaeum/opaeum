package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.nakeduml.runtime.domain.BaseTinkerBehavioredClassifier;
import org.nakeduml.runtime.domain.activity.interf.IAction;
import org.nakeduml.runtime.domain.activity.interf.IInputPin;
import org.nakeduml.runtime.domain.activity.interf.IOutputPin;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class Action extends ExecutableNode implements IAction {

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

	@Override
	public abstract List<? extends IInputPin<?,?>> getInput();
	
	@Override
	public abstract List<? extends IOutputPin<?,?>> getOutput();

	protected abstract List<? extends Object> getInputPinVariables();

	protected void transferObjectTokensToAction() {
		//For now this copies the tokens to variables in OpaqueAction and ReplyAction
	}
	
	@Override
	public boolean mayContinue() {
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
		for (ControlFlow flow : getOutgoing()) {
			ControlToken duplicate = new ControlToken(flow.getName());
			addOutgoingToken(duplicate);
		}

		// Continue each out flow with its tokens
		for (ActivityEdge<ControlToken> flow : getOutgoing()) {
			flow.setStarts(getOutTokens(flow.getName()));
			flowResult.add(flow.processNextStart());
		}

		for (IOutputPin<?,?> outputPin : getOutput()) {
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

	protected boolean isInputPinsSatisfied() {
		for (IInputPin<?,?> inputPin : this.getInput()) {
			if (!(inputPin instanceof ValuePin) && !inputPin.mayContinue()) {
				return false;
			}
		}
		return true;
	}

	protected void execute() {
		// Empty
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
		for (ControlFlow flow : getIncoming()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				result.add(new ControlToken(edge.getInVertex()));
			}
		}
		return result;
	}

	@Override
	public List<ControlToken> getInTokens(String inFlowName) {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ControlFlow flow : getIncoming()) {
			if (inFlowName.equals(flow.getName())) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(new ControlToken(edge.getInVertex()));
				}
			}
		}
		return result;
	}

	@Override
	public List<ControlToken> getOutTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		for (ControlFlow flow : getOutgoing()) {
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
		for (ControlFlow flow : getOutgoing()) {
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
	public abstract List<ControlFlow> getIncoming();

	@Override
	public abstract List<ControlFlow> getOutgoing();

	@Override
	public abstract BaseTinkerBehavioredClassifier getContext();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("\n");
		sb.append("InputPins:");
		for (IInputPin<?,?> o : getInput()) {
			sb.append("		");
			sb.append(o.toString());
			sb.append("\n");
		}
		sb.append("\nVariables:");
		for (Object o : getInputPinVariables()) {
			sb.append("		");
			sb.append(o.toString());
			sb.append("\n");
		}
		sb.append("\nOutputPin:");
		for (IOutputPin<?,?> outputPin : getOutput()) {
			sb.append("		");
			sb.append(outputPin.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

}
