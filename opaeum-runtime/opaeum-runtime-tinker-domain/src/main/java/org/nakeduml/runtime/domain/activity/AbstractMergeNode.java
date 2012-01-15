package org.nakeduml.runtime.domain.activity;

import java.util.Arrays;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AbstractMergeNode extends AbstractAction {

	public AbstractMergeNode() {
		super();
	}
	
	public AbstractMergeNode(Vertex vertex) {
		super(vertex);
	}		

	public AbstractMergeNode(boolean persist) {
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

	protected abstract AbstractControlFlowEdge getOutControlFlow();

	@Override
	protected List<? extends AbstractControlFlowEdge> getOutControlFlows() {
		return Arrays.asList(getOutControlFlow());
	}

	protected boolean doAllIncomingFlowsHaveTokens() {
		return true;
	}
}
