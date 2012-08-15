package org.opaeum.runtime.statemachines;

import java.util.Set;

import javax.persistence.MappedSuperclass;

import org.opaeum.hibernate.domain.AbstractToken;
import org.opaeum.runtime.domain.IToken;

@MappedSuperclass
public abstract class StateMachineToken<SME extends IStateMachineExecution> extends AbstractToken<SME> implements IStateMachineToken<SME>{
	private static final long serialVersionUID = -5311569035163093127L;

	public abstract SME getStateMachineExecution();

	public SME getBehaviorExecution(){
		return getStateMachineExecution();
	}

	public void fireCompletionEvent() {
		Set<IToken<SME>> childTokens = getParentToken().getChildTokens();
		for (IToken<SME>  t : childTokens) {
			if (!t.isHasRunToCompletion()) {
				return;
			}
		}
		@SuppressWarnings("unchecked")
		VertexActivation<SME,?> sa = (VertexActivation<SME,?>)getCurrentExecutionElement();
		sa.onCompletion();

	}

}
