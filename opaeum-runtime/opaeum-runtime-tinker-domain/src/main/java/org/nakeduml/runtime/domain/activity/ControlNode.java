package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ControlNode<IN extends Token, OUT extends Token> extends ActivityNode<IN, OUT> {

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
	
	public abstract List<IN> getInTokens();
	public abstract List<IN> getInTokens(String inFlowName);
	public abstract List<OUT> getOutTokens();
	public abstract List<OUT> getOutTokens(String outFlowName);
	protected abstract List<? extends ActivityEdge<OUT>> getOutFlows();

	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		execute();

		this.nodeStat.increment();

		for (IN token : getInTokens()) {
			// For each out flow add a token
			for (ActivityEdge<OUT> flow : getOutFlows()) {
				OUT duplicate = token.duplicate(flow.getName());
				addOutgoingToken(duplicate);
			}
			token.remove();
		}

		// Continue each out flow with its tokens
		for (ActivityEdge<OUT> flow : getOutFlows()) {
			flow.setStarts(getOutTokens(flow.getName()));
			flowResult.add(flow.processNextStart());
		}
		
		setNodeStatus(NodeStatus.COMPLETE);
		boolean result = true;
		for (Boolean b : flowResult) {
			if (!b) {
				result = false;
				break;
			}
		}
		return result;
	}

}
