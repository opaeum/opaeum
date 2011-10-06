package org.opeum.metamodel.commonbehaviors;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedEnumerationLiteral;
import org.opeum.metamodel.core.INakedValueSpecification;

public interface INakedTimer extends INakedEvent{
	boolean isRelative();
	INakedValueSpecification getWhen();
	INakedEnumerationLiteral getTimeUnit();
	INakedClassifier getNearestClassifier();

}
