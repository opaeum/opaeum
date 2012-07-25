package org.eclipse.uml2.uml;


public interface INakedWriteVariableAction extends INakedVariableAction{
	INakedInputPin getValue();
	void setValue(INakedInputPin p);
	public INakedInputPin getInsertAt();
	public void setInsertAt(INakedInputPin insertAt);
}
