package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedInputPin;

public interface INakedWriteStructuralFeatureAction extends INakedStructuralFeatureAction{
	INakedInputPin getValue();
	void setValue(INakedInputPin v);

}
