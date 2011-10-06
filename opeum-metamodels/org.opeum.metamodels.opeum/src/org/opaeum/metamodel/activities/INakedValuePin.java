package org.opeum.metamodel.activities;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedValueSpecification;
public interface INakedValuePin extends INakedInputPin, INakedElementOwner {
	INakedValueSpecification getValue();
	void setValue(INakedValueSpecification value);
}
