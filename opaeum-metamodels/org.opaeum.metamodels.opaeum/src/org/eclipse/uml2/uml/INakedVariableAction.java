package org.eclipse.uml2.uml;


public interface INakedVariableAction extends INakedAction {
	INakedActivityVariable getVariable();
	void setVariable(INakedActivityVariable v);
}
