package org.opaeum.metamodel.actions.internal;

import org.eclipse.uml2.uml.INakedAddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.INakedInputPin;

public class NakedAddStructuralFeatureValueActionImpl extends NakedWriteStructuralFeatureActionImpl implements INakedAddStructuralFeatureValueAction{
	private static final long serialVersionUID = -6520786921966729943L;
	private boolean replaceAll;
	private INakedInputPin insertAt;
	public INakedInputPin getInsertAt(){
		return insertAt;
	}
	public void setInsertAt(INakedInputPin insertAt){
		replacePin(this.insertAt, insertAt);
		this.insertAt = insertAt;
	}
	public boolean isReplaceAll(){
		return replaceAll;
	}
	public void setReplaceAll(boolean f){
		this.replaceAll = f;
	}
}
