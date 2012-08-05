package org.opaeum.javageneration.persistence;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.hibernate.annotations.Filter;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedElement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;
public class JpaUtil{
	public static final String BACKTICK = "";
	private static Set<String> RESERVED_NAMES = new HashSet<String>();
	static{
		RESERVED_NAMES.add("group");
		RESERVED_NAMES.add("user");
		RESERVED_NAMES.add("min");
		RESERVED_NAMES.add("order");
		RESERVED_NAMES.add("max");
		RESERVED_NAMES.add("count");
		RESERVED_NAMES.add("sum");
		RESERVED_NAMES.add("avg");
		RESERVED_NAMES.add("date");
		RESERVED_NAMES.add("int");
		RESERVED_NAMES.add("timestamp");
	}
	public static OJAnnotationValue buildTableAnnotation(OJAnnotatedClass owner,String tableName,OpaeumConfig config){
		return buildTableAnnotation(owner, tableName, config, null);
	}
	public static String getNearestSchema(Namespace ns){
		while(!(isSchema(ns) || ns == null)){
			ns = ns.getNamespace();
		}
		if(isSchema(ns)){
			if(ns instanceof Package){
				return PersistentNameUtil.getPersistentName(ns).getAsIs();
			}else{
				return (String) ns.getValue(StereotypesHelper.getStereotype(ns, StereotypeNames.COMPONENT), "schemaName");
			}
		}
		return null;
	}
	private static boolean isSchema(Namespace ns){
		if(ns instanceof Package){
			return EmfPackageUtil.isSchema((Package) ns); 
		}else {
			return EmfClassifierUtil.isSchema((Classifier) ns);
		}
	}
	public static OJAnnotationValue buildTableAnnotation(OJAnnotatedClass owner,String tableName,OpaeumConfig config,Namespace ns){
		OJAnnotationValue table = new OJAnnotationValue(new OJPathName("javax.persistence.Table"));
		buildTableAndSchema(tableName, config, ns, table);
		owner.addAnnotationIfNew(table);
		return table;
	}
	private static void buildTableAndSchema(String tableName,OpaeumConfig config,Namespace ns,OJAnnotationValue table){
		if(config.needsSchema()){
			String schema = getNearestSchema(ns);
			table.putAttribute(new OJAnnotationAttributeValue("name", getValidSqlName(tableName)));
			if(schema != null){
				table.putAttribute(new OJAnnotationAttributeValue("schema", BACKTICK + schema + BACKTICK));
			}else if(config.getDefaultSchema() != null){
				table.putAttribute(new OJAnnotationAttributeValue("schema", BACKTICK + config.getDefaultSchema() + BACKTICK));
			}
		}else{
			table.putAttribute(new OJAnnotationAttributeValue("name", BACKTICK + tableName + BACKTICK));
		}
	}
	public static String getValidSqlName(String tableName){
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
	public static void addAndAnnotatedIdAndVersion(JpaIdStrategy jpaIdStrategy,OJAnnotatedClass ojClass,Classifier complexType){
		OJUtil.addPersistentProperty(ojClass, "objectVersion", new OJPathName("int"), true);
		JpaUtil.annotateVersion(ojClass);
		if((complexType instanceof Class && EmfClassifierUtil.getPrimaryKeyProperties((Class) complexType).size()>0)){
			return;
		}else{
			OJUtil.addPersistentProperty(ojClass, "id", new OJPathName(Long.class.getName()), true);
			JpaUtil.annotateId(jpaIdStrategy, complexType, ojClass);
		}
	}
	private static void annotateId(JpaIdStrategy jpaIdStrategy,Classifier complexType,OJAnnotatedClass javaRoot){
		OJAnnotatedField idField = (OJAnnotatedField) javaRoot.findField("id");
		jpaIdStrategy.annotate(idField);
		jpaIdStrategy.annotate(javaRoot, complexType);
	}
	private static void annotateVersion(OJAnnotatedClass javaRoot){
		OJAnnotatedField versionField = (OJAnnotatedField) javaRoot.findField("objectVersion");
		OJAnnotationValue version = new OJAnnotationValue(new OJPathName("javax.persistence.Version"));
		versionField.putAnnotation(version);
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute(new OJAnnotationAttributeValue("name", "object_version"));
		versionField.putAnnotation(column);
	}
	public static void addClass(OJAnnotatedClass ojClass){
		OJAnnotationValue entity = new OJAnnotationValue(new OJPathName("javax.persistence.Class"));
		entity.putAttribute(new OJAnnotationAttributeValue("name", ojClass.getName()));
		ojClass.addAnnotationIfNew(entity);
	}
	public static void addJoinTable(Classifier umlOwner,StructuralFeatureMap map,OJAnnotatedField field,OpaeumConfig config){
		// ManyToMany or non-navigable XToMany
		Property f = map.getProperty();
		String tableName = calculateTableName(umlOwner, f);
		String keyToParentTable = calculateKeyToOwnerTable(f);
		OJAnnotationValue joinTable = new OJAnnotationValue(new OJPathName("javax.persistence.JoinTable"));
		buildTableAndSchema(tableName, config, umlOwner, joinTable);
		OJAnnotationValue otherJoinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		otherJoinColumn.putAttribute(new OJAnnotationAttributeValue("name", PersistentNameUtil.getPersistentName( f).getAsIs()));
		joinTable.putAttribute(new OJAnnotationAttributeValue("inverseJoinColumns", otherJoinColumn));
		OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		joinColumn.putAttribute(new OJAnnotationAttributeValue("name", keyToParentTable));
		joinTable.putAttribute(new OJAnnotationAttributeValue("joinColumns", joinColumn));
		if(map.isOneToMany() && !(map.getProperty().getType() instanceof Interface)){
			// NB!!! unique==true messes the manyToAny mapping up
			otherJoinColumn.putAttribute(new OJAnnotationAttributeValue("unique", Boolean.TRUE));
			otherJoinColumn.putAttribute(new OJAnnotationAttributeValue("nullable", false));
			joinColumn.putAttribute(new OJAnnotationAttributeValue("nullable", false));
		}
		field.addAnnotationIfNew(joinTable);
	}
	static String calculateKeyToOwnerTable(Property f){
		String keyToParentTable = null;
		if(f instanceof Property && f.getOtherEnd() != null){
			Property p = f;
			keyToParentTable = PersistentNameUtil.getPersistentName(p.getOtherEnd()).getAsIs();
		}else{
			Classifier nakedOwner = (Classifier) f.getOwner();
			keyToParentTable = PersistentNameUtil.getPersistentName(nakedOwner).toString() + "_id";
		}
		return keyToParentTable;
	}
	static String calculateTableName(Classifier umlOwner,Property f){
		String tableName = null;
		if(f instanceof Property && (f).getAssociation() != null && !(f.getOwner() instanceof Interface)){
			// For interfaces, create an association table per realization of
			// the association.
			Property p = f;
			tableName = PersistentNameUtil.getPersistentName( p.getAssociation()).toString();
		}else{
			Classifier nakedOwner = umlOwner;
			tableName = PersistentNameUtil.getPersistentName(nakedOwner) + "_" + PersistentNameUtil.getPersistentName( f).getWithoutId();
		}
		return tableName;
	}
	public static void addNamedQueryForUniquenessConstraints(OJAnnotatedClass ojClass,Class entity, OJUtil ojUtil){
		for(Property p:EmfPropertyUtil.getUniquenessConstraints( entity)){
			if(!p.getOtherEnd().getQualifiers().isEmpty()){
				StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(p);
				String queryString = "from " + entity.getName() + " a where a." + map.fieldname() + " = :" + map.fieldname();
				String queryName = "Query" + entity.getName() + "With";
				for(Property q:p.getOtherEnd().getQualifiers()){
					StructuralFeatureMap qualifiedMap = ojUtil.buildStructuralFeatureMap(q);
					queryString += " and a." + qualifiedMap.fieldname() + " = :" + qualifiedMap.fieldname();
					queryName += NameConverter.capitalize(qualifiedMap.fieldname());
				}
				queryName += "For" + NameConverter.capitalize(map.fieldname());
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
	public static String generateIndexColumnName(StructuralFeatureMap map,String prefix){
		String columnName;
		if(map.isManyToMany()){
			// simple column name - no requirement for uniqueness
			columnName = prefix + "_in_" + PersistentNameUtil.getPersistentName( map.getProperty()).getWithoutId();
		}else{
			columnName = prefix + "_in_";
			String withoutId = PersistentNameUtil.getPersistentName(map.getProperty()).getWithoutId().getAsIs();
			// complex column name - has to be unique across all usages of the
			// entity
			columnName += shortenName(withoutId, 8);
			columnName += "_on_";
			columnName += shortenName(PersistentNameUtil.getPersistentName(map.getProperty().getOwner()).getAsIs(), 8);
		}
		return columnName;
	}
	protected static final String shortenName(String withoutId,int i){
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(withoutId, "_");
		// Name gets way too long.
		// TODO specify and use max columnNameSize
		int maxLength = (i / st.countTokens()) - 1;// one for the u nderscore
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			if(token.length() >= maxLength){
				sb.append(token.substring(0, maxLength));
			}else{
				sb.append(token);
			}
			if(st.hasMoreTokens()){
				sb.append("_");
			}
		}
		return sb.toString();
	}
}
