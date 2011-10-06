package org.opaeum.metamodel.commonbehaviors;

import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedValueSpecification;

public interface INakedTimer extends INakedEvent{
	boolean isRelative();
	INakedValueSpecification getWhen();
	INakedEnumerationLiteral getTimeUnit();
	INakedClassifier getNearestClassifier();

}
