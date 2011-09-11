package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public interface INakedTimer extends INakedEvent{
	boolean isRelative();
	INakedValueSpecification getWhen();
	INakedEnumerationLiteral getTimeUnit();
	INakedClassifier getNearestClassifier();

}
