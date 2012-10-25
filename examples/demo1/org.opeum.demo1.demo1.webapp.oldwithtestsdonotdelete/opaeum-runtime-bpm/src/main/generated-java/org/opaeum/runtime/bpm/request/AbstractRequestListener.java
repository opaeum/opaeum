package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.domain.IToken;

public interface AbstractRequestListener {
	public void onAbstractRequestComplete(IToken callingToken, AbstractRequest completedProcess);
	
	public void onAbstractRequestUnhandledException(IToken callingToken, Object exception, AbstractRequest completedProcess);

}