package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OutputPin<O> extends ObjectNode<O> {

	public OutputPin() {
		super();
	}

	public OutputPin(boolean persist, String name) {
		super(persist, name);
	}

	public OutputPin(Vertex vertex) {
		super(vertex);
	}

	protected abstract int getLowerMultiplicity();

	protected abstract int getUpperMultiplicity();

	protected abstract Action getAction();

	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		if (mayContinue()) {
			return executeNode();
		} else {
			return false;
		}
	}
	
	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		// execute();

		this.nodeStat.increment();

		for (ObjectToken<O> objectToken : getOutTokens()) {
			// For each out flow add a token
			for (ActivityEdge<ObjectToken<O>> flow : getOutFlows()) {
				ObjectToken<O> duplicate = objectToken.duplicate(flow.getName());
				addOutgoingToken(duplicate);
			}
			objectToken.remove();
		}
		// Continue each out flow with its tokens
		for (ActivityEdge<ObjectToken<O>> flow : getOutFlows()) {
			flow.setStarts(getOutTokens(flow.getName()));
			flowResult.add(flow.processNextStart());
		}

		// TODO Start transaction
		setNodeStatus(NodeStatus.COMPLETE);
		// TODO End transaction
		boolean result = true;
		for (Boolean b : flowResult) {
			if (!b) {
				result = false;
				break;
			}
		}
		return result;
	}

	protected boolean isLowerMultiplicityReached() {
		return getOutTokens().size() >= getLowerMultiplicity();
	}

	protected boolean isUpperMultiplicityReached() {
		return getOutTokens().size() >= getUpperMultiplicity();
	}

	// TODO think about upper
	@Override
	protected boolean mayContinue() {
		return isLowerMultiplicityReached();
	}
	
	@Override
	protected List<? extends ActivityEdge<ObjectToken<O>>> getInFlows() {
		return Collections.emptyList();
	}

	protected abstract void copyTokensToStart();
}
