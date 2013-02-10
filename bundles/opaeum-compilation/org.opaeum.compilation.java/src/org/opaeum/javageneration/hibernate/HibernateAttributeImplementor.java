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
	@Override
	protected OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		boolean isExternalValue = false;
		if(isInterfaceValue(owner, map) | (isExternalValue = isExternalValue(owner, map))){
			OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
			getter.setReturnType(map.javaTypePath());
			owner.addToOperations(getter);
			getter.initializeResultVariable("null");
			String string = buildInit(owner, map, isExternalValue);
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
	protected String buildInit(OJClassifier c,PropertyMap map,boolean isExternalValue){
		if(isExternalValue){
			c.addToImports(new OJPathName("org.opaeum.hibernate.domain.ExternalValue"));
			return "new ExternalValue()";
		}else if(map.getProperty().isComposite()){
			c.addToImports(new OJPathName("org.opaeum.hibernate.domain.UiidBasedCascadingInterfaceValue"));
			return "new UiidBasedCascadingInterfaceValue()";
		}else {
			c.addToImports(new OJPathName("org.opaeum.hibernate.domain.UiidBasedInterfaceValue"));
			return "new UiidBasedInterfaceValue()";
		}
	}
	private boolean isExternalValue(OJAnnotatedClass c,PropertyMap map){
		Resource myResource = map.getProperty().eResource();
		Resource externalResource = map.getBaseType().eResource();
		if(myResource != externalResource && EmfClassifierUtil.isPersistent(map.getBaseType())){
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
		if(isInterfaceValue(owner, map)){
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
		if(isInterfaceValue(owner, map)){
			OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			String init = map.getProperty().isComposite() ? "new CascadingInterfaceValue()" : "new InterfaceValue()";
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
		if(isInterfaceValue(owner, map)){
			OJAnnotatedField field = null;
			if(map.getProperty().isComposite()){
				field = new OJAnnotatedField(map.fieldname(), new OJPathName("org.opaeum.hibernate.domain.CascadingInterfaceValue"));
				owner.addToFields(field);
				field.setInitExp("new CascadingInterfaceValue()");
			}else{
				field = new OJAnnotatedField(map.fieldname(), new OJPathName("org.opaeum.hibernate.domain.InterfaceValue"));
				owner.addToFields(field);
				field.setInitExp("new InterfaceValue()");
			}
			field.setVisibility(OJVisibilityKind.PROTECTED);
			return field;
		}else{
			return super.buildField(owner, map);
		}
	}
	private boolean isInterfaceValue(OJAnnotatedClass c,PropertyMap map){
		return !(c instanceof OJAnnotatedInterface) && !EmfPropertyUtil.isDerived(map.getProperty()) && map.isOne()
				&& map.getBaseType() instanceof Interface && !StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER);
	}
}
