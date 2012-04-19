package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;

public interface INakedWriteStructuralFeatureAction extends INakedStructuralFeatureAction{
	INakedInputPin getValue();
	void setValue(INakedInputPin v);
	INakedOutputPin getResult();
	void setResult(INakedOutputPin v);

}
