package org.eclipse.uml2.uml;

import java.util.List;


public interface INakedReplyAction extends INakedAction{
	INakedInputPin getReturnInfo();
	INakedAcceptCallAction getCause();
	List<INakedInputPin> getReplyValues();
}
