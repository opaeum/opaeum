package org.eclipse.uml2.uml;


public interface INakedRaiseExceptionAction extends INakedAction {
	void setException(INakedInputPin p);
	INakedInputPin getException();
}
