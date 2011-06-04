package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.metamodel.core.ICompositionParticipant;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

public interface CompositionNodeStrategy {
	void addMarkDeleted(ICompositionParticipant entity, OJClass ojClass);

	void addAddToOwningObject(ICompositionParticipant entity, OJAnnotatedClass ojClass);

	void addConstructorForTests(OJAnnotatedClass ojClass, ICompositionParticipant entity);
}
