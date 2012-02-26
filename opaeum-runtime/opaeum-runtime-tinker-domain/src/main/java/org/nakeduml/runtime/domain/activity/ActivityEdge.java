package org.nakeduml.runtime.domain.activity;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.pipes.AbstractPipe;

public abstract class ActivityEdge<T extends Token> extends AbstractPipe<T, Boolean> {

	protected Edge edge;
	private List<Token> controlTokens = new ArrayList<Token>();

	public ActivityEdge(Edge edge) {
		super();
		this.edge = edge;
	}

	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		// Take all tokens
		while (starts.hasNext()) {
			T controlToken = this.starts.next();
			if (evaluateGuardConditions(controlToken)) {
				this.controlTokens.add(controlToken);
			} else {
				GraphDb.getDb().removeVertex(controlToken.getVertex());
			}
		}
		if (hasWeightPassed()) {
			ActivityNode<Token> target = getTarget();
			target.setStarts(this.controlTokens);
			return target.next();
		} else {
			return false;
		}
	}

	protected abstract <O extends Token> ActivityNode<O> getTarget();

	protected abstract <O extends Token> ActivityNode<O> getSource();

	public abstract String getName();

	protected abstract boolean evaluateGuardConditions(T token);

	protected abstract int getWeigth();

	protected boolean hasWeightPassed() {
		return getWeigth() <= this.controlTokens.size();
	}
	
}
