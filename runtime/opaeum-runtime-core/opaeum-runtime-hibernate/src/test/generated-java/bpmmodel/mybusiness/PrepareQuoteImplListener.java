package bpmmodel.mybusiness;

import org.opaeum.runtime.domain.IToken;

public interface PrepareQuoteImplListener extends PrepareQuoteListener {
	public void onPrepareQuoteImplComplete(IToken callingToken, PrepareQuoteImpl completedProcess);
	
	public void onPrepareQuoteImplUnhandledException(IToken callingToken, Object exception, PrepareQuoteImpl completedProcess);

}