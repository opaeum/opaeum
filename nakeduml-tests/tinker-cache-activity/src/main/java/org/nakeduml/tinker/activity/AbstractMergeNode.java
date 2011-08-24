package org.nakeduml.tinker.activity;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractMergeNode extends AbstractAction {

	@Override
	protected boolean hasPostConditionPassed() {
		return true;
	}

	@Override
	protected boolean hasPreConditionPassed() {
		return true;
	}

	protected abstract AbstractControlFlowEdge getOutControlFlow();

	@Override
	protected List<? extends AbstractControlFlowEdge> getOutControlFlows() {
		return Arrays.asList(getOutControlFlow());
	}

	protected boolean doAllIncomingFlowsHaveTokens() {
		return true;
	}
}
