package org.opaeum.javageneration.hibernate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.hibernate.annotations.CascadeType;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.SqlDialect;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.basicjava.AbstractAttributeImplementer;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.name.NameWrapper;

public class HibernateUtil{
	public static final OJPathName RETURN_INFO = new OJPathName(ReturnInfo.class.getName());
	public static final OJPathName STATE_MACHINE_TOKEN = new OJPathName(StateMachineToken.class.getName());
	public static void addAny(OJAnnotatedField field,PropertyMap map){
		Property p = map.getProperty();
		String column = PersistentNameUtil.getPersistentName( p).getAsIs();
		JpaUtil.addJoinColumn(field, column, false);
		OJAnnotationValue any = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Any"));
		OJAnnotationValue metaColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		metaColumn.putAttribute(new OJAnnotationAttributeValue("name", column + "_type"));
		any.putAttribute(new OJAnnotationAttributeValue("metaColumn", metaColumn));
		any.putAttribute(new OJAnnotationAttributeValue("metaDef", metadefName((Classifier) p.getType())));
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
	public static void addManyToAny(Classifier umlOwner,OJAnnotatedField field,PropertyMap map,OpaeumConfig config){
		JpaUtil.addJoinTable(umlOwner, map, field, config);
		OJAnnotationValue any = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.ManyToAny"));
		OJAnnotationValue metaColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		Property p = map.getProperty();
		metaColumn.putAttribute(new OJAnnotationAttributeValue("name", PersistentNameUtil.getPersistentName( p) + "_type"));
		any.putAttribute(new OJAnnotationAttributeValue("metaColumn", metaColumn));
		any.putAttribute(new OJAnnotationAttributeValue("metaDef", metadefName((Classifier) p.getType())));
		field.addAnnotationIfNew(any);
	}
	public static String metadefName(Classifier nakedBaseType){
		return nakedBaseType.getName();
	}
	public static void addEnumResolverAsCustomType(OJAnnotatedField field,OJPathName pn){
		field.removeAnnotation(new OJPathName("javax.persistence.Enumerated"));
		OJAnnotationValue type = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.Type"));
		type.putAttribute("type", pn.toJavaString() + "Resolver");
		field.putAnnotation(type);
	}
	public static void overrideInterfaceValueAtributes(OJAnnotatedField field,NameWrapper persistentName){
		field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(Embedded.class.getName())));
		OJAnnotationValue overrides = new OJAnnotationValue(new OJPathName(AttributeOverrides.class.getName()));
		OJAnnotationValue identifier = new OJAnnotationValue(new OJPathName(AttributeOverride.class.getName()));
		identifier.putAttribute("name", "identifier");
		overrides.addAnnotationValue(identifier);
		OJAnnotationValue identifierColumn = new OJAnnotationValue(new OJPathName(Column.class.getName()));
		identifier.putAttribute("column", identifierColumn);
		identifierColumn.putAttribute("name", persistentName.getAsIs());
		field.addAnnotationIfNew(overrides);
		OJAnnotationValue classIdentifier = new OJAnnotationValue(new OJPathName(AttributeOverride.class.getName()));
		classIdentifier.putAttribute("name", "classIdentifier");
		OJAnnotationValue classIdentifierColumn = new OJAnnotationValue(new OJPathName(Column.class.getName()));
		classIdentifier.putAttribute("column", classIdentifierColumn);
		classIdentifierColumn.putAttribute("name", persistentName.getAsIs() + "_type");
		overrides.addAnnotationValue(classIdentifier);
	}

	public static OJAnnotatedOperation removeFromInterfaceValue(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
		String condition = map.getter() + "()!=null && " + map.fieldname() + "!=null && " + map.fieldname() + ".equals(" + map.getter()
				+ "())";
		OJIfStatement ifEquals = new OJIfStatement(condition);
		remover.getBody().addToStatements(ifEquals);
		ifEquals.getThenPart().addToStatements(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".setValue(null)");
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		owner.addToOperations(remover);
		return remover;
	}
	public static boolean isExternalValue(OJAnnotatedClass c,PropertyMap map){
		Resource myResource = map.getProperty().eResource();
		Resource externalResource = map.getBaseType().eResource();
		if(myResource == null || externalResource == null){
			System.out.println("Resources null:" +map.getProperty().getQualifiedName());
		}else if(myResource != externalResource && EmfClassifierUtil.isPersistent(map.getBaseType())){
			if(!(c instanceof OJAnnotatedInterface) && !EmfPropertyUtil.isDerived(map.getProperty()) && map.isOne()){
				if(!myResource.getURI().trimFragment().lastSegment().equals(externalResource.getURI().trimFragment().lastSegment())){
					// different dir
					if(map.getBaseType().getModel() != null && !EmfClassifierUtil.isHelper(map.getBaseType())
							&& !EmfPackageUtil.isRegeneratingLibrary(map.getBaseType().getModel())){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean isInterfaceValue(OJAnnotatedClass c,PropertyMap map){
		return !(c instanceof OJAnnotatedInterface) && !EmfPropertyUtil.isDerived(map.getProperty()) && map.isOne()
				&& map.getBaseType() instanceof Interface && !StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER);
	}
}
