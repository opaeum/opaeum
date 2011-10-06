package org.opaeum.javageneration.hibernate;

import org.hibernate.annotations.CascadeType;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.SqlDialect;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;

public class HibernateUtil{
	public static void addAny(OJAnnotatedField field,NakedStructuralFeatureMap map){
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
	public static void applyFilter(OJAnnotatedField field,SqlDialect sqlDialect){
		OJAnnotationValue filter = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Filter"));
		filter.putAttribute(new OJAnnotationAttributeValue("name", "noDeletedObjects"));
		filter.putAttribute(new OJAnnotationAttributeValue("condition", "deleted_on > " + sqlDialect.getCurrentTimeStampString()));
		field.addAnnotationIfNew(filter);
	}
	public static void addCascade(OJAnnotatedField field,CascadeType deleteOrphan){
		OJAnnotationValue cascade = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Cascade"));
		OJAnnotationAttributeValue value = new OJAnnotationAttributeValue("value");
		// Hack, this is due to import clash
		OJPathName cascadeType = new OJPathName("org.hibernate.annotations.CascadeType");
		value.addEnumValue(new OJEnumValue(cascadeType, deleteOrphan.name()));
		cascade.putAttribute(value);
		field.addAnnotationIfNew(cascade);
	}
	public static void addManyToAny(INakedClassifier umlOwner,OJAnnotatedField field,NakedStructuralFeatureMap map,OpaeumConfig config){
		JpaUtil.addJoinTable(umlOwner, map, field, config);
		OJAnnotationValue any = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.ManyToAny"));
		OJAnnotationValue metaColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		INakedProperty p = map.getProperty();
		metaColumn.putAttribute(new OJAnnotationAttributeValue("name", p.getMappingInfo().getPersistentName() + "_type"));
		any.putAttribute(new OJAnnotationAttributeValue("metaColumn", metaColumn));
		any.putAttribute(new OJAnnotationAttributeValue("metaDef", metadefName(p.getNakedBaseType())));
		field.addAnnotationIfNew(any);
	}
	public static String metadefName(INakedClassifier nakedBaseType){
		return nakedBaseType.getMappingInfo().getJavaName().getAsIs();
	}
	public static void addEnumResolverAsCustomType(OJAnnotatedField field,OJPathName pn){
		field.removeAnnotation(new OJPathName("javax.persistence.Enumerated"));
		OJAnnotationValue type = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Type"));
		type.putAttribute("type", pn.toJavaString() + "Resolver");
		field.putAnnotation(type);
	}
}
