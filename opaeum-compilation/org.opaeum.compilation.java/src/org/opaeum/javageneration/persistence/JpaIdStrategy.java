package org.opaeum.javageneration.persistence;

import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.metamodel.core.INakedComplexStructure;


public interface JpaIdStrategy {
	void annotate(OJAnnotatedField f);
	void annotate(OJAnnotatedClass c, INakedComplexStructure tableName);
}
