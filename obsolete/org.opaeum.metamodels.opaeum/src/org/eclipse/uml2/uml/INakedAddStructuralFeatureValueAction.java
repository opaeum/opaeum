package org.eclipse.uml2.uml;


public interface INakedAddStructuralFeatureValueAction extends INakedWriteStructuralFeatureAction{
	boolean isReplaceAll();
	void setReplaceAll(boolean f);
	public INakedInputPin getInsertAt();
	public void setInsertAt(INakedInputPin insertAt);
}
