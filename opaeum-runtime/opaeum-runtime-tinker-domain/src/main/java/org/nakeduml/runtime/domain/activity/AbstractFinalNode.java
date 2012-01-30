package org.nakeduml.runtime.domain.activity;


import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AbstractFinalNode extends AbstractAction {

	public AbstractFinalNode() {
		super();
	}
	
	public AbstractFinalNode(Vertex vertex) {
		super(vertex);
	}	

	public AbstractFinalNode(boolean persist, String name) {
		super(persist, name);
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
	protected List<? extends AbstractControlFlowEdge> getOutControlFlows() {
		return Collections.<AbstractControlFlowEdge>emptyList();
	}
	
	//This is irrelevant for a flowfinal
	protected boolean doAllIncomingFlowsHaveTokens() {
		return true;
	}
	
}
