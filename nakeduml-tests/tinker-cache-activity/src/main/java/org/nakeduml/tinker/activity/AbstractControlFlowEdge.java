package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.pipes.AbstractPipe;

public abstract class AbstractControlFlowEdge extends AbstractPipe<ControlToken, Boolean> {

	protected Edge edge;
	transient private List<ControlToken> controlTokens = new ArrayList<ControlToken>();

	public AbstractControlFlowEdge(Edge edge) {
		super();
		this.edge = edge;
	}

	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		// Take all tokens
		while (starts.hasNext()) {
			ControlToken controlToken = (ControlToken) starts.next();
			if (evaluateGuardConditions(controlToken)) {
				this.controlTokens.add(controlToken);
			} else {
				GraphDb.getDb().removeVertex(controlToken.getVertex());
			}
		}
		if (hasWeightPassed()) {
			AbstractNode target = getTarget();
			target.setStarts(this.controlTokens);
			return target.next();
		} else {
			return false;
		}
	}

	protected abstract AbstractNode getTarget();

	protected abstract AbstractNode getSource();

	protected abstract String getName();

	protected abstract boolean evaluateGuardConditions(ControlToken controlToken);

	protected abstract int getWeigth();

	protected boolean hasWeightPassed() {
		return getWeigth() <= this.controlTokens.size();
	}

}
