package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public interface INakedStructuralFeatureAction extends IActionWithTarget{
	INakedProperty getFeature();
	void setFeature(INakedProperty feature);
	INakedInputPin getObject();
	void setObject(INakedInputPin object);
}
