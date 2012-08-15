package org.opaeum.runtime.statemachines;

import org.opaeum.runtime.domain.IToken;

public interface IStateMachineToken<SME extends IStateMachineExecution> extends IToken<SME>{
	SME getStateMachineExecution();
}
