package net.sf.nakeduml.javageneration.persistence;

import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;


public interface JpaStrategy{
	void annotate(OJAnnotatedField f, INakedProperty p);
}
