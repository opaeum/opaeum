package org.opaeum.demo1.structuredbusiness.branch.branch;

import org.opaeum.runtime.domain.IToken;

public interface PrepareQuoteListener {
	public void onPrepareQuoteComplete(IToken callingToken, PrepareQuote completedProcess);
	
	public void onPrepareQuoteUnhandledException(IToken callingToken, Object exception, PrepareQuote completedProcess);

}