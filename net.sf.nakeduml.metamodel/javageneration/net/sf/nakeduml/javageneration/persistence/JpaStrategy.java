package net.sf.nakeduml.javageneration.persistence;

import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public interface JpaStrategy{
	void annotate(OJAnnotatedField f, INakedProperty p);
}
