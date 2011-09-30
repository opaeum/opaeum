package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedInputPin;

public interface INakedAddStructuralFeatureValueAction extends INakedWriteStructuralFeatureAction{
	boolean isReplaceAll();
	void setReplaceAll(boolean f);
	public INakedInputPin getInsertAt();
	public void setInsertAt(INakedInputPin insertAt);
}
