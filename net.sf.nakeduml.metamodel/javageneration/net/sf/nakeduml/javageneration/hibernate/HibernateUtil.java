package net.sf.nakeduml.javageneration.hibernate;

import java.util.Collection;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.hibernate.annotations.CascadeType;
import org.hibernate.dialect.Dialect;

public class HibernateUtil{
	private static Dialect dialect;

	public static Dialect getHibernateDialect(NakedUmlConfig config) {
		if (dialect == null) {
			Dialect d = buildHibernateDialect(config);
			dialect=d;
		}
		return dialect;
	}
	private static Dialect buildHibernateDialect(NakedUmlConfig config) {
		Dialect d=null;
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

	public static void addAny(OJAnnotatedClass owner, OJAnnotatedField field,String column,Collection<INakedEntity> implementations){
		JpaUtil.addJoinColumn(field, column, false);
		OJAnnotationValue any = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Any"));
		OJAnnotationValue metaColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		metaColumn.putAttribute(new OJAnnotationAttributeValue("name", column + "_type"));
		any.putAttribute(new OJAnnotationAttributeValue("metaColumn", metaColumn));
		any.putAttribute(new OJAnnotationAttributeValue("metaDef", metadefName(owner, field)));
		field.addAnnotationIfNew(any);
		addMetaDef(owner,field, implementations);
	}

	private static String metadefName(OJAnnotatedClass owner,OJAnnotatedField field){
		return owner.getName() + field.getName() + "MetaDef";
	}

	private static void addMetaDef(OJAnnotatedClass owner, OJAnnotatedField field, Collection<INakedEntity> implementations) {
		OJAnnotationValue metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
		metaDef.putAttribute(new OJAnnotationAttributeValue("name", metadefName(owner, field)));
		metaDef.putAttribute(new OJAnnotationAttributeValue("idType", "long"));
		metaDef.putAttribute(new OJAnnotationAttributeValue("metaType", "string"));
		OJAnnotationAttributeValue metaDefsAttr = new OJAnnotationAttributeValue("metaValues");
		for(INakedClassifier c:implementations){
			OJAnnotationValue metaValue = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.MetaValue"));
			//Use qualified persistent name
			metaValue.putAttribute(new OJAnnotationAttributeValue("value", c.getMappingInfo().getQualifiedJavaName()));
			metaValue.putAttribute(new OJAnnotationAttributeValue("targetEntity", OJUtil.classifierPathname(c)));
			metaDefsAttr.addAnnotationValue(metaValue);
		}
		metaDef.putAttribute(metaDefsAttr);
		field.addAnnotationIfNew(metaDef);
	}

	public static void applyFilter(OJAnnotatedField field, Dialect dialect){
		OJAnnotationValue filter = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Filter"));
		filter.putAttribute(new OJAnnotationAttributeValue("name", "noDeletedObjects"));
		filter.putAttribute(new OJAnnotationAttributeValue("condition", "deleted_on > " + dialect.getCurrentTimestampSQLFunctionName()));
		field.addAnnotationIfNew(filter);
	}

	public static void addCascade(OJAnnotatedField field, CascadeType deleteOrphan) {
		OJAnnotationValue cascade = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Cascade"));
		OJAnnotationAttributeValue value = new OJAnnotationAttributeValue("value");
		//Hack, this is due to import clash
		OJPathName cascadeType = new OJPathName("org.hibernate.annotations.CascadeType");
		value.addEnumValue(new OJEnumValue(cascadeType, deleteOrphan.name()));
		cascade.putAttribute(value);
		field.addAnnotationIfNew(cascade);
	}

	public static void addManyToAny(INakedClassifier umlOwner, OJAnnotatedClass owner, OJAnnotatedField field, INakedProperty p, NakedStructuralFeatureMap map, Collection<INakedEntity> implementations) {
		JpaUtil.addJoinTable(umlOwner,p,map,field);
		OJAnnotationValue any = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.ManyToAny"));
		OJAnnotationValue metaColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		metaColumn.putAttribute(new OJAnnotationAttributeValue("name", p.getMappingInfo().getPersistentName()+ "_type"));
		any.putAttribute(new OJAnnotationAttributeValue("metaColumn", metaColumn));
		any.putAttribute(new OJAnnotationAttributeValue("metaDef", metadefName(owner, field)));
		field.addAnnotationIfNew(any);
		addMetaDef(owner, field, implementations);
		
	}
}
