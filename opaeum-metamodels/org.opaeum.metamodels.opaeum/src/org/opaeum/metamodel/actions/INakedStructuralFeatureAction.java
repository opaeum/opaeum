package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.core.INakedProperty;

public interface INakedStructuralFeatureAction extends IActionWithTargetPin{
	INakedProperty getFeature();
	void setFeature(INakedProperty feature);
	INakedInputPin getObject();
	void setObject(INakedInputPin object);
}
