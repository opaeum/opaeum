package org.opaeum.runtime.statemachines;

import java.util.Set;

import javax.persistence.MappedSuperclass;

import org.opaeum.hibernate.domain.AbstractToken;

@MappedSuperclass
public abstract class StateMachineToken extends AbstractToken {
	private String currentElementId;
	private boolean hasRunToCompletion = false;

	public abstract IStateMachineExecution getStateMachineExecution();

	public abstract Set<? extends StateMachineToken> getChildTokens();

	public abstract StateMachineToken getParentToken();

	public IStateMachineExecutionElement getCurrentExecutionElement() {
		return getStateMachineExecution().getExecutionElements().get(
				currentElementId);
	}

	public void transferTo(VertexActivation vertexActivation) {
		currentElementId = vertexActivation.getId();
	}

	public void fireCompletionEvent() {
		for (StateMachineToken t : getParentToken().getChildTokens()) {
			if (!t.isHasRunToCompletion()) {
				return;
			}
		}
		VertexActivation sa = (VertexActivation)getCurrentExecutionElement();
		sa.onCompletion();

	}

	public boolean isHasRunToCompletion() {
		return hasRunToCompletion;
	}

	public void setHasRunToCompletion(boolean hasRunToCompletion) {
		this.hasRunToCompletion = hasRunToCompletion;
	}
}
