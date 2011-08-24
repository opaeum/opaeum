package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.tinker.runtime.GraphDb;

public abstract class AbstractAction extends AbstractNode {

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
		if (doAllIncomingFlowsHaveTokens() && hasPreConditionPassed() && hasPostConditionPassed()) {
			
			removeIncomingControlTokens();
			
			setNodeStatus(NodeStatus.ENABLED);
			setNodeStatus(NodeStatus.ACTIVE);

			//Execute whatever
			
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

	protected void removeIncomingControlTokens() {
		for (ControlToken incomingControlToken : getInControlTokens()) {
			GraphDb.getDb().removeVertex(incomingControlToken.getVertex());
		}
	}

	protected boolean doAllIncomingFlowsHaveTokens() {
		for (AbstractControlFlowEdge flow : getInControlFlows()) {
			if (getInControlTokens(flow.getName()).isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
