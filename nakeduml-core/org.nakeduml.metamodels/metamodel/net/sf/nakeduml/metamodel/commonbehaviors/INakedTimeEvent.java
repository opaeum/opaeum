package net.sf.nakeduml.metamodel.commonbehaviors;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

import org.nakeduml.runtime.domain.TimeUnit;
public interface INakedTimeEvent extends INakedEvent{
	boolean isRelative();
	INakedValueSpecification getWhen();
	TimeUnit getTimeUnit();
	/**
	 * The "when" expression of a time event needs to be evaluated within the context of a classifier. This is 
	 * a flaw in UML2 
	 */
	INakedBehavior getOwningBehavior();
}
