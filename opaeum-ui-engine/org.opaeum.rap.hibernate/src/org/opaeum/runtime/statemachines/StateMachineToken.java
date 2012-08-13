package org.opaeum.runtime.statemachines;

import java.util.Set;

import javax.persistence.MappedSuperclass;

import org.opaeum.hibernate.domain.AbstractToken;
import org.opaeum.runtime.domain.IBehaviorExecution;

@MappedSuperclass
public abstract class StateMachineToken extends AbstractToken {
	private static final long serialVersionUID = -5311569035163093127L;

	public abstract IStateMachineExecution getStateMachineExecution();

	public abstract Set<? extends StateMachineToken> getChildTokens();

	public abstract StateMachineToken getParentToken();
	public IBehaviorExecution getBehaviorExecution(){
		return getStateMachineExecution();
	}
	public IStateMachineExecutionElement getCurrentExecutionElement() {
		return (IStateMachineExecutionElement) super.getCurrentExecutionElement();
	}

	public void transferTo(VertexActivation vertexActivation) {
		super.transferTo(vertexActivation);
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

}
