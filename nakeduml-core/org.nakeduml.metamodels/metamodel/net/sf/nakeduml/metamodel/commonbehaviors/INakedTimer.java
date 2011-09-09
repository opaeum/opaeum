package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

import org.nakeduml.runtime.domain.TimeUnit;

public interface INakedTimer extends INakedEvent{
	boolean isRelative();
	INakedValueSpecification getWhen();
	INakedEnumerationLiteral getTimeUnit();
	INakedClassifier getNearestClassifier();

}
