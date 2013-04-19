package org.opaeum.javageneration.persistence;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;


public interface JpaIdStrategy {
	void annotate(OJAnnotatedField f);
	void annotate(OJAnnotatedClass c, Classifier tableName);
}
