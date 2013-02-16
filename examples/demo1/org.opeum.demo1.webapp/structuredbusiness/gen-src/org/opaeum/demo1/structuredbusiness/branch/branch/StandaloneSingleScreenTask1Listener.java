package org.opaeum.demo1.structuredbusiness.branch.branch;

import org.opaeum.runtime.domain.IToken;

public interface StandaloneSingleScreenTask1Listener {
	public void onStandaloneSingleScreenTask1Complete(IToken callingToken, StandaloneSingleScreenTask1 completedProcess);
	
	public void onStandaloneSingleScreenTask1UnhandledException(IToken callingToken, Object exception, StandaloneSingleScreenTask1 completedProcess);

}