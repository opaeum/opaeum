package org.opaeum.metamodel.activities;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedValueSpecification;
public interface INakedValuePin extends INakedInputPin, INakedElementOwner {
	INakedValueSpecification getValue();
	void setValue(INakedValueSpecification value);
}
