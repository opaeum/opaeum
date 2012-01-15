package org.nakeduml.runtime.domain.activity;


import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class AbstractInitialNode extends AbstractAction {

	public AbstractInitialNode() {
		super();
	}
	
	public AbstractInitialNode(Vertex vertex) {
		super(vertex);
	}	

	public AbstractInitialNode(boolean persist) {
		super(persist);
	}

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
