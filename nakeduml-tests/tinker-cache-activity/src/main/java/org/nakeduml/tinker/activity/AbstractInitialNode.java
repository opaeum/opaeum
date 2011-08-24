package org.nakeduml.tinker.activity;

import java.util.Collections;
import java.util.List;


public abstract class AbstractInitialNode extends AbstractAction {

	protected boolean doAllIncomingFlowsHaveTokens() {
		return true;
	}
	
	@Override
	protected boolean hasPostConditionPassed() {
		return true;
	}

	@Override
	protected boolean hasPreConditionPassed() {
		return true;
	}
	
	@Override
	protected List<? extends AbstractControlFlowEdge> getInControlFlows() {
		return Collections.<AbstractControlFlowEdge>emptyList();
	}	

}
