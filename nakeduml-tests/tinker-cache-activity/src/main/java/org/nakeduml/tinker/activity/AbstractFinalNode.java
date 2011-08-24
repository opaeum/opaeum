package org.nakeduml.tinker.activity;

import java.util.Collections;
import java.util.List;

public abstract class AbstractFinalNode extends AbstractAction {

	@Override
	protected boolean hasPostConditionPassed() {
		return true;
	}

	@Override
	protected boolean hasPreConditionPassed() {
		return true;
	}

	@Override
	protected List<? extends AbstractControlFlowEdge> getOutControlFlows() {
		return Collections.<AbstractControlFlowEdge>emptyList();
	}
	
}
