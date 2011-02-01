package net.sf.nakeduml.javageneration.persistence;

import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;

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
	public void annotate(OJAnnotatedClass javaRoot, INakedComplexStructure complexType) {
		OJAnnotationValue generator = new OJAnnotationValue(new OJPathName("javax.persistence.TableGenerator"));
		generator.putAttribute(new OJAnnotationAttributeValue("name", "id_generator"));
		generator.putAttribute(new OJAnnotationAttributeValue("table", "hi_value"));
		generator.putAttribute(new OJAnnotationAttributeValue("pkColumnName", "type"));
		String tableName = complexType.getMappingInfo().getPersistentName().getAsIs();
		generator.putAttribute(new OJAnnotationAttributeValue("pkColumnValue", tableName));
		generator.putAttribute(new OJAnnotationAttributeValue("allocationSize", new Integer(20)));
		javaRoot.putAnnotation(generator);
	}

}
