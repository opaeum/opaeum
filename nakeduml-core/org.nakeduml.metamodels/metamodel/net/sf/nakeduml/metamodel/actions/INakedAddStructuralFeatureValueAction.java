package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public interface INakedAddStructuralFeatureValueAction extends INakedWriteStructuralFeatureAction{
	boolean isReplaceAll();
	void setReplaceAll(boolean f);
	public INakedInputPin getInsertAt();
	public void setInsertAt(INakedInputPin insertAt);
}
