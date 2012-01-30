package org.nakeduml.runtime.domain.activity;

import java.util.NoSuchElementException;

import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class AbstractAcceptEventAction extends AbstractTriggeredAction {

	public AbstractAcceptEventAction() {
		super();
	}

	public AbstractAcceptEventAction(boolean persist, String name) {
		super(persist, name);
	}

	public AbstractAcceptEventAction(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		Boolean result = super.processNextStart();
		if (getInControlFlows().isEmpty()) {
			setNodeStatus(NodeStatus.ENABLED);
		}
		return result;
	}

}
