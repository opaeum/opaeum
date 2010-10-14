package net.sf.nakeduml.metamodel.commonbehaviors;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.util.TimeUnit;
public interface INakedTimeEvent extends INakedElement,INakedElementOwner {
	boolean isRelative();
	INakedValueSpecification getWhen();
	TimeUnit getTimeUnit();
	/**
	 * The "when" expression of a time event needs to be evaluated within the context of a classifier. This is 
	 * a flaw in UML2 
	 */
	INakedClassifier getContext();
}
