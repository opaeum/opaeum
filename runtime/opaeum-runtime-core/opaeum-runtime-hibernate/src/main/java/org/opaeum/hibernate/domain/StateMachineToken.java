package org.opaeum.hibernate.domain;

import java.util.Set;

import javax.persistence.MappedSuperclass;

import org.opaeum.runtime.domain.IExecutionElement;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.statemachines.IStateMachineExecution;
import org.opaeum.runtime.statemachines.IStateMachineToken;
import org.opaeum.runtime.statemachines.VertexActivation;

@MappedSuperclass
public abstract class StateMachineToken<SME extends IStateMachineExecution> extends AbstractToken<SME> implements IStateMachineToken<SME>{
	private static final long serialVersionUID = -5311569035163093127L;

	public SME getStateMachineExecution(){
		return getBehaviorExecution();
	}
	@Override
	public void transferTo(IExecutionElement vertexActivation){
		super.transferTo(vertexActivation);
	}	

	public void fireCompletionEvent() {
		Set<IToken<SME>> childTokens = getChildTokens();
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
