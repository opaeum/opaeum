package net.sf.nakeduml.javageneration.persistence;

import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;

public interface JpaIdStrategy {
	void annotate(OJAnnotatedField f);
	void annotate(OJAnnotatedClass c, INakedComplexStructure tableName);
}
