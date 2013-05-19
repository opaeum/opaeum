package org.opaeum.javageneration.hibernate;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractAttributeImplementer;
import org.opaeum.javageneration.basicjava.DefaultAttributeStrategy;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class HibernateAttributeStrategy extends DefaultAttributeStrategy{
	ThreadLocal<Boolean> isExternalValue = new ThreadLocal<Boolean>();
	ThreadLocal<Boolean> isInterfaceValue = new ThreadLocal<Boolean>();
	@Override
	public void beforeProperty(OJAnnotatedClass owner,Classifier umlOwner,PropertyMap map){
		isExternalValue.set(isExternalValue(owner, map));
		isInterfaceValue.set(HibernateUtil.isInterfaceValue(owner, map));
		super.beforeProperty(owner, umlOwner, map);
	}
	public boolean isExternalValue(OJAnnotatedClass owner,PropertyMap map){
		return HibernateUtil.isExternalValue(owner, map);
	}
	@Override
	public OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return getFromInterfaceValue(umlOwner, owner, map, isExternalValue.get(), attributeImplementer.getLibrary());
		}else{
			return super.buildGetter(umlOwner, owner, map, derived);
		}
	}
	@Override
	public OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return HibernateUtil.removeFromInterfaceValue(owner, map);
		}else{
			return super.buildInternalRemover(owner, map);
		}
	}
	@Override
	public OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return addToInterfaceValue(owner, map, isExternalValue.get());
		}else{
			return super.buildInternalAdder(owner, map);
		}
	}
	@Override
	public OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return putField(owner, map, isExternalValue.get());
		}else{
			return super.buildField(owner, map);
		}
	}
	private OJAnnotatedOperation getFromInterfaceValue(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,Boolean isExternalValue2,
			OpaeumLibrary library){
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		getter.initializeResultVariable("null");
		String string = buildAnyMappingInit(map, isExternalValue2);
		String init = string;
		getter.getBody().addToStatements(
				new OJIfStatement(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + "==null",
						AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + "=" + init));
		getter.getBody().addToStatements(
				"result=(" + map.javaType() + ")" + AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".getValue("
						+ (EmfClassifierUtil.isPersistent(umlOwner) ? "persistence" : "null") + ")");
		Element property = map.getProperty();
		AbstractAttributeImplementer.addPropertyMetaInfo(umlOwner, getter, map.getProperty(), library);
		OJUtil.addMetaInfo(getter, property);
		return getter;
	}
	private OJAnnotatedField putField(OJAnnotatedClass owner,PropertyMap map,Boolean isExternal){
		OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), calculateAnyMappingType(map, isExternal));
		field.setVisibility(OJVisibilityKind.PROTECTED);
		owner.addToFields(field);
		return field;
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
	public OJAnnotatedOperation addToInterfaceValue(OJAnnotatedClass owner,PropertyMap map,Boolean isExternalValue2){
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		OJIfStatement ifSet = new OJIfStatement(map.fieldname() + ".equals(" + map.getter() + "())", "return");
		adder.getBody().addToStatements(ifSet);
		String init = buildAnyMappingInit(map, isExternalValue2);
		adder.getBody().addToStatements(
				new OJIfStatement(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + "==null",
						AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + "=" + init));
		adder.getBody().addToStatements(
				AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".setValue(" + map.fieldname() + ")");
		adder.addParam(map.fieldname(), map.javaBaseTypePath());
		owner.addToOperations(adder);
		return adder;
	}
	@Override
	protected String readCurrentSingleValueFieldValue(OJAnnotatedClass owner, PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() +"==null?null:this."+map.fieldname() +".getValue("
					+ (EmfClassifierUtil.isPersistent(map.getDefiningClassifier()) ? "persistence" : "null") + ")";
		}else{
			return super.readCurrentSingleValueFieldValue(owner, map);
		}
	}
}
