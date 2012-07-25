package org.eclipse.uml2.uml;


public interface INakedReadVariableAction extends INakedVariableAction {
	INakedOutputPin getResult();
	void setResult(INakedOutputPin result);
}
