package org.opaeum.javageneration.persistence;

import javax.persistence.TableGenerator;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;

public class Jpa2IdTable implements JpaIdStrategy{
	@Override
	public void annotate(OJAnnotatedField idField) {
		OJAnnotationValue id = new OJAnnotationValue(new OJPathName("javax.persistence.Id"));
		idField.putAnnotation(id);
		OJAnnotationValue generatedValue = new OJAnnotationValue(new OJPathName("javax.persistence.GeneratedValue"));
		generatedValue.putAttribute(new OJAnnotationAttributeValue("strategy", new OJEnumValue(new OJPathName("javax.persistence.GenerationType"), "TABLE")));
		generatedValue.putAttribute(new OJAnnotationAttributeValue("generatorName", "myGenerator"));
		idField.putAnnotation(generatedValue);
	}

	@Override
	public void annotate(OJAnnotatedClass javaRoot, Classifier complexType) {
//		OJAnnotationValue tableGenerator = new OJAnnotationValue(new OJPathName(TableGenerator.class.getName()));
//		tableGenerator.putAttribute("name","myGenerator");
//    tableGenerator.putAttribute("table", "hi_value");
//    tableGenerator.putAttribute("pkColumnName" , "type");
//    valueColumnName = "hi"
//    pkColumnValue="EMP",
//    allocationSize=20
//		javaRoot.putAnnotation(tableGenerator);
	}
}
