package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedOutputPin;

public interface INakedReadStructuralFeatureAction extends INakedStructuralFeatureAction{


	public INakedOutputPin getResult();

	public void setResult(INakedOutputPin result);
	
}
