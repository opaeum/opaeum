package org.nakeduml.runtime.domain.activity;

import java.util.NoSuchElementException;

import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class AcceptEventAction extends AbstractTriggeredAction {

	public AcceptEventAction() {
		super();
	}

	public AcceptEventAction(boolean persist, String name) {
		super(persist, name);
	}

	public AcceptEventAction(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		Boolean result = super.processNextStart();
		if (getInFlows().isEmpty()) {
			setNodeStatus(NodeStatus.ENABLED);
		}
		return result;
	}

}
