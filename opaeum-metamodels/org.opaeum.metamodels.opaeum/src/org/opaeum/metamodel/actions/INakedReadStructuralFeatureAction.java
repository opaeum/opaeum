package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedOutputPin;

public interface INakedReadStructuralFeatureAction extends INakedStructuralFeatureAction{


	public INakedOutputPin getResult();

	public void setResult(INakedOutputPin result);
	
}
