package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;

public interface INakedWriteStructuralFeatureAction extends INakedStructuralFeatureAction{
	INakedInputPin getValue();
	void setValue(INakedInputPin v);
}
