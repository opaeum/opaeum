package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AbstractAction extends AbstractNode {

	public AbstractAction() {
		super();
	}

	public AbstractAction(Vertex vertex) {
		super(vertex);
	}

	public AbstractAction(boolean persist, String name) {
		super(persist, name);
	}

	protected abstract boolean hasPostConditionPassed();

	protected abstract boolean hasPreConditionPassed();

	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		//Persist incoming control tokens
		while (starts.hasNext()) {
			ControlToken incomingControlToken = (ControlToken) starts.next();
			//This also removes the token from the source
			addIncomingControlToken(incomingControlToken);
		}
		List<Boolean> flowResult = new ArrayList<Boolean>();
		if (doAllIncomingFlowsHaveTokens() && hasPreConditionPassed() && hasPostConditionPassed() && isTriggered()) {
			
			removeIncomingControlTokens();
			
			setNodeStatus(NodeStatus.ENABLED);
			setNodeStatus(NodeStatus.ACTIVE);
			
			execute();

			//Execute whatever
			nodeStat.increment();
			
			//For each out control flow add a control token
			for (AbstractControlFlowEdge flow : getOutControlFlows()) {
				addOutgoingControlToken(new ControlToken(flow.getName()));
			}

			//Continue each out flow with its tokens
			for (AbstractControlFlowEdge flow : getOutControlFlows()) {
				flow.setStarts(getOutControlTokens(flow.getName()));
				flowResult.add(flow.processNextStart());
			}
			
			//TODO Start transaction
			setNodeStatus(NodeStatus.COMPLETE);
			//TODO End transaction
			boolean result = true;
			for (Boolean b : flowResult) {
				if (!b) {
					result = false;
					break;
				}
			}
			return result;
		} else {
			return false;
		}
	}

	protected void execute() {
		//Empty
	}

	protected boolean isTriggered() {
		return true;
	}

	protected void removeIncomingControlTokens() {
		for (ControlToken incomingControlToken : getInControlTokens()) {
			GraphDb.getDb().removeVertex(incomingControlToken.getVertex());
		}
	}

	protected boolean doAllIncomingFlowsHaveTokens() {
		for (AbstractControlFlowEdge flow : getInControlFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(CONTROL_TOKEN + flow.getName());
			if (iter.iterator().hasNext()) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

}
