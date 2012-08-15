package org.opaeum.runtime.statemachines;

import org.opaeum.runtime.domain.IExecutionElement;

public interface IStateMachineExecutionElement  <SME extends IStateMachineExecution,T extends IStateMachineToken<SME> > extends IExecutionElement<SME>{
	public abstract SME getStateMachineExecution();

}
