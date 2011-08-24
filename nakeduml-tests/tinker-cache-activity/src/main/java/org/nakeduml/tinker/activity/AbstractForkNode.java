package org.nakeduml.tinker.activity;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractForkNode extends AbstractAction {

	@Override
	protected boolean hasPostConditionPassed() {
		return true;
	}

	@Override
	protected boolean hasPreConditionPassed() {
		return true;
	}

	protected abstract AbstractControlFlowEdge getInControlFlow();

	@Override
	protected List<? extends AbstractControlFlowEdge> getInControlFlows() {
		return Arrays.asList(getInControlFlow());
	}

}
