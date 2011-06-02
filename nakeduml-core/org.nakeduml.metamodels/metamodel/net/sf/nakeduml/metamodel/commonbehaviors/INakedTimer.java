package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

import org.nakeduml.runtime.domain.TimeUnit;

public interface INakedTimer extends INakedElement{
	boolean isRelative();
	INakedValueSpecification getWhen();
	TimeUnit getTimeUnit();
	INakedClassifier getNearestClassifier();

}
