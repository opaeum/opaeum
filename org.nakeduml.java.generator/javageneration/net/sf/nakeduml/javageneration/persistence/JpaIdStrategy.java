package net.sf.nakeduml.javageneration.persistence;

import net.sf.nakeduml.metamodel.core.INakedComplexStructure;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;


public interface JpaIdStrategy {
	void annotate(OJAnnotatedField f);
	void annotate(OJAnnotatedClass c, INakedComplexStructure tableName);
}
