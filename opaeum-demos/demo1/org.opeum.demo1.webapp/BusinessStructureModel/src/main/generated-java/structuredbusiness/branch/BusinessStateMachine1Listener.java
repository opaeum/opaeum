package structuredbusiness.branch;

import org.opaeum.runtime.domain.IToken;

public interface BusinessStateMachine1Listener {
	public void onBusinessStateMachine1Complete(IToken callingToken, BusinessStateMachine1 completedProcess);
	
	public void onBusinessStateMachine1UnhandledException(IToken callingToken, Object exception, BusinessStateMachine1 completedProcess);

}