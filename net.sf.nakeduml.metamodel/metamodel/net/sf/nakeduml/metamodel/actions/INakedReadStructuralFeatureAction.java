package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedOutputPin;

public interface INakedReadStructuralFeatureAction extends INakedStructuralFeatureAction{


	public INakedOutputPin getResult();

	public void setResult(INakedOutputPin result);
	
}
