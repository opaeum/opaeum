package org.eclipse.uml2.uml;


public interface INakedAcceptCallAction extends INakedAcceptEventAction{
	INakedOutputPin getReturnInfo();

	INakedOperation getOperation();
	INakedReplyAction getReplyAction();
}
