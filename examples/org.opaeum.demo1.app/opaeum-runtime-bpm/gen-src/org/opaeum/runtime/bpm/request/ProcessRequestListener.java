package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.domain.IToken;

public interface ProcessRequestListener extends AbstractRequestListener {
	public void onAbstractRequestComplete(IToken callingToken, ProcessRequest completedProcess);
	
	public void onAbstractRequestUnhandledException(IToken callingToken, Object exception, ProcessRequest completedProcess);

}