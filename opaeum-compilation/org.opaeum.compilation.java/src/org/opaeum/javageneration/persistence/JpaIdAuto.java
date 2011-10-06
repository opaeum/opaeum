package org.opaeum.javageneration.persistence;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.metamodel.core.INakedComplexStructure;


public class JpaIdAuto implements JpaIdStrategy {

	@Override
	public void annotate(OJAnnotatedField idField) {
		OJAnnotationValue id = new OJAnnotationValue(new OJPathName("javax.persistence.Id"));
		idField.putAnnotation(id);
		OJAnnotationValue generatedValue = new OJAnnotationValue(new OJPathName("javax.persistence.GeneratedValue"));
		generatedValue.putAttribute(new OJAnnotationAttributeValue("strategy", new OJEnumValue(new OJPathName("javax.persistence.GenerationType"), "AUTO")));
		idField.putAnnotation(generatedValue);
	}

	@Override
	public void annotate(OJAnnotatedClass javaRoot, INakedComplexStructure tableName) {
	}
}
