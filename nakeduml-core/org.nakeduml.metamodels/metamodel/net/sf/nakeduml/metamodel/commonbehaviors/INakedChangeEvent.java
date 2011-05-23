package net.sf.nakeduml.metamodel.commonbehaviors;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public interface INakedChangeEvent extends INakedElement{
	INakedValueSpecification getChangeExpression();
	INakedClassifier getOwningBehavior();
}
