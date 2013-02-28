package org.opaeum.javageneration.hibernate;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.hibernate.domain.CascadingInterfaceValue;
import org.opaeum.hibernate.domain.InterfaceValue;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = JavaTransformationPhase.class,replaces = AttributeImplementor.class)
public class HibernateAttributeImplementor extends AttributeImplementor{
	ThreadLocal<Boolean> isExternalValue = new ThreadLocal<Boolean>();
	ThreadLocal<Boolean> isInterfaceValue = new ThreadLocal<Boolean>();
	@Override
	protected void visitProperty(OJAnnotatedClass owner,Classifier umlOwner,PropertyMap map){
		isExternalValue.set(isExternalValue(owner, map));
		isInterfaceValue.set(isInterfaceValue(owner, map));
		// TODO Auto-generated method stub
		super.visitProperty(owner, umlOwner, map);
	}
	@Override
	protected OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		if(isInterfaceValue.get() || isExternalValue.get()){
			OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
			getter.setReturnType(map.javaTypePath());
			owner.addToOperations(getter);
			getter.initializeResultVariable("null");
			String string = buildAnyMappingInit(map, isExternalValue.get());
			String init = string;
			getter.getBody().addToStatements(
					new OJIfStatement(getReferencePrefix(owner, map) + map.fieldname() + "==null", getReferencePrefix(owner, map) + map.fieldname()
							+ "=" + init));
			getter.getBody().addToStatements(
					"result=(" + map.javaType() + ")" + getReferencePrefix(owner, map) + map.fieldname() + ".getValue("
							+ (isPersistent(umlOwner) ? "persistence" : "null") + ")");
			Element property = map.getProperty();
			addPropertyMetaInfo(umlOwner, getter, map.getProperty(), getLibrary());
			OJUtil.addMetaInfo(getter, property);
			return getter;
		}else{
			return super.buildGetter(umlOwner, owner, map, derived);
		}
	}
	protected OJPathName calculateAnyMappingType(PropertyMap map,boolean isExternal){
		if(isExternal){
			return new OJPathName("org.opaeum.hibernate.domain.ExternalValue");
		}else if(map.getProperty().isComposite()){
			return new OJPathName("org.opaeum.hibernate.domain.UiidBasedCascadingInterfaceValue");
		}else{
			return new OJPathName("org.opaeum.hibernate.domain.UiidBasedInterfaceValue");
		}
	}
	protected String buildAnyMappingInit(PropertyMap map,boolean isExternalValue){
		if(isExternalValue){
			return "new ExternalValue()";
		}else if(map.getProperty().isComposite()){
			return "new UiidBasedCascadingInterfaceValue()";
		}else{
			return "new UiidBasedInterfaceValue()";
		}
	}
	protected boolean isExternalValue(OJAnnotatedClass c,PropertyMap map){
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
	@Override
	protected OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
			String condition = map.getter() + "()!=null && " + map.fieldname() + "!=null && " + map.fieldname() + ".equals(" + map.getter()
					+ "())";
			OJIfStatement ifEquals = new OJIfStatement(condition);
			remover.getBody().addToStatements(ifEquals);
			ifEquals.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".setValue(null)");
			remover.addParam(map.fieldname(), map.javaBaseTypePath());
			owner.addToOperations(remover);
			return remover;
		}else{
			return super.buildInternalRemover(owner, map);
		}
	}
	@Override
	protected OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJIfStatement ifSet = new OJIfStatement(map.fieldname() + ".equals("+ map.getter()+"())", "return");
			adder.getBody().addToStatements(ifSet);

			String init = buildAnyMappingInit(map, isExternalValue.get());
			adder.getBody().addToStatements(
					new OJIfStatement(getReferencePrefix(owner, map) + map.fieldname() + "==null", getReferencePrefix(owner, map) + map.fieldname()
							+ "=" + init));
			adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".setValue(" + map.fieldname() + ")");
			adder.addParam(map.fieldname(), map.javaBaseTypePath());
			owner.addToOperations(adder);
			return adder;
		}else{
			return super.buildInternalAdder(owner, map);
		}
	}
	@Override
	protected OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), calculateAnyMappingType(map, isExternalValue.get()));
			field.setVisibility(OJVisibilityKind.PROTECTED);
			owner.addToFields(field);
			return field;
		}else{
			return super.buildField(owner, map);
		}
	}
	protected  boolean isInterfaceValue(OJAnnotatedClass c,PropertyMap map){
		return !(c instanceof OJAnnotatedInterface) && !EmfPropertyUtil.isDerived(map.getProperty()) && map.isOne()
				&& map.getBaseType() instanceof Interface && !StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER);
	}
}
