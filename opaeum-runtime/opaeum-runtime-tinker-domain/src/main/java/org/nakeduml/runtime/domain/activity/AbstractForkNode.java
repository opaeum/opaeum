package org.nakeduml.runtime.domain.activity;


import java.util.Arrays;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AbstractForkNode extends AbstractAction {

	public AbstractForkNode() {
		super();
	}
	
	public AbstractForkNode(Vertex vertex) {
		super(vertex);
	}	

	public AbstractForkNode(boolean persist) {
		super(persist);
	}
	
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
