package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedInputPin;

public interface INakedWriteStructuralFeatureAction extends INakedStructuralFeatureAction{
	INakedInputPin getValue();
	void setValue(INakedInputPin v);

}
