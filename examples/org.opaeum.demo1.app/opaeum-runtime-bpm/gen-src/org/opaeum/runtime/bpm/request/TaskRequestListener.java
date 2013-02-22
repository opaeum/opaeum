package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.domain.IToken;

public interface TaskRequestListener extends AbstractRequestListener {
	public void onAbstractRequestComplete(IToken callingToken, TaskRequest completedProcess);
	
	public void onAbstractRequestUnhandledException(IToken callingToken, Object exception, TaskRequest completedProcess);

}