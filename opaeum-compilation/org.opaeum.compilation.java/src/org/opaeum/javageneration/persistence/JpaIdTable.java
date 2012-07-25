package org.opaeum.javageneration.persistence;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;


public class JpaIdTable implements JpaIdStrategy {
	
	@Override
	public void annotate(OJAnnotatedField idField) {
		OJAnnotationValue id = new OJAnnotationValue(new OJPathName("javax.persistence.Id"));
		idField.putAnnotation(id);
		OJAnnotationValue generatedValue = new OJAnnotationValue(new OJPathName("javax.persistence.GeneratedValue"));
		generatedValue.putAttribute(new OJAnnotationAttributeValue("strategy", new OJEnumValue(new OJPathName("javax.persistence.GenerationType"), "TABLE")));
		generatedValue.putAttribute(new OJAnnotationAttributeValue("generator", "id_generator"));
		idField.putAnnotation(generatedValue);
	}

	@Override
	public void annotate(OJAnnotatedClass javaRoot, Classifier complexType) {
		OJAnnotationValue generator = new OJAnnotationValue(new OJPathName("javax.persistence.TableGenerator"));
		generator.putAttribute(new OJAnnotationAttributeValue("name", "id_generator"));
		generator.putAttribute(new OJAnnotationAttributeValue("table", "hi_value"));
		generator.putAttribute(new OJAnnotationAttributeValue("pkColumnName", "type"));
		String tableName = PersistentNameUtil.getPersistentName(complexType).getAsIs();
		generator.putAttribute(new OJAnnotationAttributeValue("pkColumnValue", tableName));
		generator.putAttribute(new OJAnnotationAttributeValue("allocationSize", new Integer(20)));
		javaRoot.putAnnotation(generator);
	}

}
