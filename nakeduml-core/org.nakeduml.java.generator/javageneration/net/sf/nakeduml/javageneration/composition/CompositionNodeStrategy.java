package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.metamodel.core.INakedEntity;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

public interface CompositionNodeStrategy {
	void addMarkDeleted(INakedEntity entity, OJClass ojClass);

	void addAddToOwningObject(INakedEntity entity, OJAnnotatedClass ojClass);

	void addConstructorForTests(OJAnnotatedClass ojClass, INakedEntity entity);
}
