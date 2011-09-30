package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.core.INakedProperty;

public interface INakedStructuralFeatureAction extends IActionWithTargetPin{
	INakedProperty getFeature();
	void setFeature(INakedProperty feature);
	INakedInputPin getObject();
	void setObject(INakedInputPin object);
}
