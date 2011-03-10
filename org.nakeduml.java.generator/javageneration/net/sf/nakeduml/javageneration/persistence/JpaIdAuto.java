package net.sf.nakeduml.javageneration.persistence;

import net.sf.nakeduml.metamodel.core.INakedComplexStructure;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;


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
