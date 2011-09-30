package org.opeum.javageneration.persistence;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Filter;
import org.opeum.feature.OpeumConfig;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedElement;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;
import org.opeum.java.metamodel.annotation.OJEnumValue;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedAssociation;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedNameSpace;
import org.opeum.metamodel.core.INakedPackage;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.name.NameConverter;

public class JpaUtil{
	public static final String BACKTICK = "";
	private static Set<String> RESERVED_NAMES = new HashSet<String>();
	static{
		RESERVED_NAMES.add("group");
		RESERVED_NAMES.add("user");
		RESERVED_NAMES.add("min");
		RESERVED_NAMES.add("max");
		RESERVED_NAMES.add("count");
		RESERVED_NAMES.add("sum");
		RESERVED_NAMES.add("avg");
	}
	public static OJAnnotationValue buildTableAnnotation(OJAnnotatedClass owner,String tableName,OpeumConfig config){
		return buildTableAnnotation(owner, tableName, config, null);
	}
	public static INakedPackage getNearestSchema(INakedNameSpace ns){
		while(!(isSchema(ns) || ns == null)){
			ns = ns.getNameSpace();
		}
		if(isSchema(ns)){
			return (INakedPackage) ns;
		}
		return null;
	}
	private static boolean isSchema(INakedNameSpace ns){
		return(ns instanceof INakedPackage && ((INakedPackage) ns).isSchema());
	}
	public static OJAnnotationValue buildTableAnnotation(OJAnnotatedClass owner,String tableName,OpeumConfig config,INakedNameSpace ns){
		OJAnnotationValue table = new OJAnnotationValue(new OJPathName("javax.persistence.Table"));
		buildTableAndSchema(tableName, config, ns, table);
		owner.addAnnotationIfNew(table);
		return table;
	}
	private static void buildTableAndSchema(String tableName,OpeumConfig config,INakedNameSpace ns,OJAnnotationValue table){
		if(config.needsSchema()){
			INakedPackage schema = getNearestSchema(ns);
			table.putAttribute(new OJAnnotationAttributeValue("name", getValidSqlName(tableName)));
			if(schema != null){
				table.putAttribute(new OJAnnotationAttributeValue("schema", BACKTICK + schema.getMappingInfo().getPersistentName() + BACKTICK));
			}else if(config.getDefaultSchema()!=null){
				table.putAttribute(new OJAnnotationAttributeValue("schema", BACKTICK + config.getDefaultSchema() + BACKTICK));
			}
		}else{
			table.putAttribute(new OJAnnotationAttributeValue("name", BACKTICK + tableName + BACKTICK));
		}
	}
	private static String getValidSqlName(String tableName){
		if(RESERVED_NAMES.contains(tableName.toLowerCase())){
			return '`' + tableName + '`';
		}else{
			return tableName;
		}
	}
	public static void fetchLazy(OJAnnotationValue a){
		a.putAttribute(new OJAnnotationAttributeValue("fetch", new OJEnumValue(new OJPathName("javax.persistence.FetchType"), "LAZY")));
	}
	public static OJAnnotationValue addJoinColumn(OJAnnotatedField f,String fk,boolean nullable){
		OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("name", fk));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("nullable", nullable || JpaAnnotator.DEVELOPMENT_MODE));
		f.addAnnotationIfNew(joinColumn);
		return joinColumn;
	}
	public static OJAnnotationValue addAuditJoinColumns(OJAnnotatedField f,String fk,boolean nullable){
		OJAnnotationValue joinColumns = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumns"));
		OJAnnotationValue joinColumn1 = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		joinColumn1.putAttribute(new OJAnnotationAttributeValue("name", fk + "_id"));
		joinColumns.addAnnotationValue(joinColumn1);
		OJAnnotationValue joinColumn2 = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		joinColumn2.putAttribute(new OJAnnotationAttributeValue("name", fk + "_object_version"));
		joinColumns.addAnnotationValue(joinColumn2);
		f.addAnnotationIfNew(joinColumns);
		return joinColumns;
	}
	public static OJAnnotationValue addColumn(OJAnnotatedField f,String name,boolean nullable){
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", name));
		column.putAttribute(new OJAnnotationAttributeValue("nullable", nullable || JpaAnnotator.DEVELOPMENT_MODE));
		f.addAnnotationIfNew(column);
		return column;
	}
	public static OJAnnotationValue addColumn(OJAnnotatedField f,String name,boolean nullable,int precision,int scale){
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", name));
		column.putAttribute(new OJAnnotationAttributeValue("nullable", nullable || JpaAnnotator.DEVELOPMENT_MODE));
		column.putAttribute(new OJAnnotationAttributeValue("precision", precision));
		column.putAttribute(new OJAnnotationAttributeValue("scale", scale));
		f.addAnnotationIfNew(column);
		return column;
	}
	public static void cascadeAll(OJAnnotationValue a){
		a.putAttribute(new OJAnnotationAttributeValue("cascade", new OJEnumValue(new OJPathName("javax.persistence.CascadeType"), "ALL")));
	}
	public static void addAndAnnotatedIdAndVersion(JpaIdStrategy jpaIdStrategy,OJAnnotatedClass ojClass,INakedComplexStructure complexType){
		OJUtil.addProperty(ojClass, "id", new OJPathName(Long.class.getName()), true);
		JpaUtil.annotateId(jpaIdStrategy, complexType, ojClass);
		JpaUtil.annotateVersion(ojClass);
	}
	private static void annotateId(JpaIdStrategy jpaIdStrategy,INakedComplexStructure complexType,OJAnnotatedClass javaRoot){
		OJAnnotatedField idField = (OJAnnotatedField) javaRoot.findField("id");
		jpaIdStrategy.annotate(idField);
		jpaIdStrategy.annotate(javaRoot, complexType);
	}
	private static void annotateVersion(OJAnnotatedClass javaRoot){
		OJUtil.addProperty(javaRoot, "objectVersion", new OJPathName("int"), true);
		OJAnnotatedField versionField = (OJAnnotatedField) javaRoot.findField("objectVersion");
		OJAnnotationValue version = new OJAnnotationValue(new OJPathName("javax.persistence.Version"));
		versionField.putAnnotation(version);
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", "object_version"));
		versionField.putAnnotation(column);
	}
	public static void addEntity(OJAnnotatedClass ojClass){
		OJAnnotationValue entity = new OJAnnotationValue(new OJPathName("javax.persistence.Entity"));
		entity.putAttribute(new OJAnnotationAttributeValue("name", ojClass.getName()));
		ojClass.addAnnotationIfNew(entity);
	}
	public static void addJoinTable(INakedClassifier umlOwner,NakedStructuralFeatureMap map,OJAnnotatedField field,OpeumConfig config){
		// ManyToMany or non-navigable XToMany
		INakedProperty f = map.getProperty();
		String tableName = calculateTableName(umlOwner, f);
		String keyToParentTable = calculateKeyToOwnerTable(f);
		OJAnnotationValue joinTable = new OJAnnotationValue(new OJPathName("javax.persistence.JoinTable"));
		buildTableAndSchema(tableName, config, umlOwner, joinTable);
		OJAnnotationValue otherJoinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		otherJoinColumn.putAttribute(new OJAnnotationAttributeValue("name", f.getMappingInfo().getPersistentName().getAsIs()));
		joinTable.putAttribute(new OJAnnotationAttributeValue("inverseJoinColumns", otherJoinColumn));
		OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("name", keyToParentTable));
		joinTable.putAttribute(new OJAnnotationAttributeValue("joinColumns", joinColumn));
		if(map.isOneToMany() && !(map.getProperty().getBaseType() instanceof INakedInterface)){
			// NB!!! unique==true messes the manyToAny mapping up
			otherJoinColumn.putAttribute(new OJAnnotationAttributeValue("unique", Boolean.TRUE));
			otherJoinColumn.putAttribute(new OJAnnotationAttributeValue("nullable", false));
			joinColumn.putAttribute(new OJAnnotationAttributeValue("nullable", false));
		}
		field.addAnnotationIfNew(joinTable);
	}
	static String calculateKeyToOwnerTable(INakedProperty f){
		String keyToParentTable = null;
		if(f instanceof INakedProperty && f.getOtherEnd() != null){
			INakedProperty p = f;
			keyToParentTable = p.getOtherEnd().getMappingInfo().getPersistentName().getAsIs();
		}else{
			INakedClassifier nakedOwner = f.getOwner();
			keyToParentTable = nakedOwner.getMappingInfo().getPersistentName().toString() + "_id";
		}
		return keyToParentTable;
	}
	static String calculateTableName(INakedClassifier umlOwner,INakedProperty f){
		String tableName = null;
		if(f instanceof INakedProperty && (f).getAssociation() != null && !(f.getOwner() instanceof INakedInterface)){
			// For interfaces, create an association table per realization of
			// the association.
			INakedProperty p = f;
			tableName = ((INakedAssociation) p.getAssociation()).getMappingInfo().getPersistentName().toString();
		}else{
			INakedClassifier nakedOwner = umlOwner;
			tableName = nakedOwner.getMappingInfo().getPersistentName() + "_" + f.getMappingInfo().getPersistentName().getWithoutId();
		}
		return tableName;
	}
	public static void addNamedQueryForUniquenessConstraints(OJAnnotatedClass ojClass,INakedEntity entity){
		for(INakedProperty p:entity.getUniquenessConstraints()){
			if(!p.getOtherEnd().getQualifiers().isEmpty()){
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
				String queryString = "from " + entity.getMappingInfo().getJavaName() + " a where a." + map.umlName() + " = :" + map.umlName();
				String queryName = "Query" + entity.getMappingInfo().getJavaName() + "With";
				for(INakedProperty q:p.getOtherEnd().getQualifiers()){
					NakedStructuralFeatureMap qualifiedMap = new NakedStructuralFeatureMap(q);
					queryString += " and a." + qualifiedMap.umlName() + " = :" + qualifiedMap.umlName();
					queryName += NameConverter.capitalize(qualifiedMap.umlName());
				}
				queryName += "For" + NameConverter.capitalize(map.umlName());
				addNamedQueries(ojClass, queryName, queryString);
			}
		}
	}
	public static void addNamedQueries(OJAnnotatedElement pack,String name,String query){
		OJAnnotationValue namedQueries = pack.findAnnotation(new OJPathName("javax.persistence.NamedQueries"));
		if(namedQueries == null){
			namedQueries = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQueries"));
			pack.addAnnotationIfNew(namedQueries);
		}
		OJAnnotationAttributeValue namedQueryAttr = namedQueries.findAttribute("value");
		if(namedQueryAttr == null){
			namedQueryAttr = new OJAnnotationAttributeValue("value");
			namedQueries.putAttribute(namedQueryAttr);
		}
		for(OJAnnotationValue v:namedQueryAttr.getAnnotationValues()){
			if(v.findAttribute("name").getStringValues().get(0).equals(name)){
				return;
			}
		}
		OJAnnotationValue namedQuery = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQuery"));
		namedQuery.putAttribute("name", name);
		namedQuery.putAttribute("query", query);
		namedQueryAttr.addAnnotationValue(namedQuery);
		namedQueries.putAttribute(namedQueryAttr);
	}
	public static OJAnnotationValue buildFilterAnnotation(String name){
		OJAnnotationValue filter = new OJAnnotationValue(new OJPathName(Filter.class.getName()));
		filter.putAttribute("name", "noDeletedObjects");
		return filter;
	}
}
