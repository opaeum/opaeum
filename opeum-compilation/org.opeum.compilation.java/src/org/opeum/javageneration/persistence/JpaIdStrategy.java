package org.opeum.javageneration.persistence;

import org.opeum.metamodel.core.INakedComplexStructure;

import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;


public interface JpaIdStrategy {
	void annotate(OJAnnotatedField f);
	void annotate(OJAnnotatedClass c, INakedComplexStructure tableName);
}
