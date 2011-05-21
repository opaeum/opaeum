package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

public interface CompositionNodeStrategy {
	void addMarkDeleted(INakedBehavioredClassifier entity, OJClass ojClass);

	void addAddToOwningObject(INakedBehavioredClassifier entity, OJAnnotatedClass ojClass);

	void addConstructorForTests(OJAnnotatedClass ojClass, INakedBehavioredClassifier entity);
}
