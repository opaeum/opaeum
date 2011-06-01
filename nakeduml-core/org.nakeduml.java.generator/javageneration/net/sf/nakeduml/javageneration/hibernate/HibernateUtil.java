package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.hibernate.annotations.CascadeType;
import org.hibernate.dialect.Dialect;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.java.metamodel.annotation.OJEnumValue;

public class HibernateUtil {
	private static Dialect dialect;

	public static OJPathName getHibernatePackage(boolean isAdaptor) {
		if (isAdaptor) {
			return UtilityCreator.getUtilPathName().append("hibernate").append("adaptor");
		} else {
			return UtilityCreator.getUtilPathName().append("hibernate").append("domain");
		}
	}

	public static Dialect getHibernateDialect(NakedUmlConfig config) {
		if (dialect == null) {
			Dialect d = buildHibernateDialect(config);
			dialect = d;
		}
		return dialect;
	}

	private static Dialect buildHibernateDialect(NakedUmlConfig config) {
		Dialect d = null;
		try {
			d = (Dialect) Class.forName(config.getJdbcDialect()).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return d;
	}

	public static void addAny(OJAnnotatedField field, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		String column = p.getMappingInfo().getPersistentName().getAsIs();
		JpaUtil.addJoinColumn(field, column, false);
		OJAnnotationValue any = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Any"));
		OJAnnotationValue metaColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		metaColumn.putAttribute(new OJAnnotationAttributeValue("name", column + "_type"));
		any.putAttribute(new OJAnnotationAttributeValue("metaColumn", metaColumn));
		any.putAttribute(new OJAnnotationAttributeValue("metaDef", metadefName(p.getNakedBaseType())));
		field.addAnnotationIfNew(any);
	}

	public static void applyFilter(OJAnnotatedField field, Dialect dialect) {
		OJAnnotationValue filter = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Filter"));
		filter.putAttribute(new OJAnnotationAttributeValue("name", "noDeletedObjects"));
		filter.putAttribute(new OJAnnotationAttributeValue("condition", "deleted_on > " + dialect.getCurrentTimestampSQLFunctionName()));
		field.addAnnotationIfNew(filter);
	}

	public static void addCascade(OJAnnotatedField field, CascadeType deleteOrphan) {
		OJAnnotationValue cascade = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Cascade"));
		OJAnnotationAttributeValue value = new OJAnnotationAttributeValue("value");
		// Hack, this is due to import clash
		OJPathName cascadeType = new OJPathName("org.hibernate.annotations.CascadeType");
		value.addEnumValue(new OJEnumValue(cascadeType, deleteOrphan.name()));
		cascade.putAttribute(value);
		field.addAnnotationIfNew(cascade);
	}

	public static void addManyToAny(INakedClassifier umlOwner, OJAnnotatedField field, NakedStructuralFeatureMap map,NakedUmlConfig config) {
		JpaUtil.addJoinTable(umlOwner, map, field,config);
		OJAnnotationValue any = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.ManyToAny"));
		OJAnnotationValue metaColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		INakedProperty p = map.getProperty();
		metaColumn.putAttribute(new OJAnnotationAttributeValue("name", p.getMappingInfo().getPersistentName() + "_type"));
		any.putAttribute(new OJAnnotationAttributeValue("metaColumn", metaColumn));
		any.putAttribute(new OJAnnotationAttributeValue("metaDef", metadefName(p.getNakedBaseType())));
		field.addAnnotationIfNew(any);
	}

	public static String metadefName(INakedClassifier nakedBaseType) {
		return nakedBaseType.getMappingInfo().getJavaName().getAsIs();
	}
}
