package org.opaeum.javageneration.basicjava;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.EndToAssociationClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.util.OJUtil;

public class DefaultAttributeStrategy implements AttributeStrategy{
	protected AbstractAttributeImplementer attributeImplementer;
	@Override
	public void beforeProperty(OJAnnotatedClass owner,Classifier umlOwner,PropertyMap map){

	}
	@Override
	public OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		OJAnnotatedOperation getter = (OJAnnotatedOperation) owner.findOperation(map.getter(), Collections.<OJPathName>emptyList());
		if(getter == null){
			// Could exist in the case of DerivedUnion from an Interface with the same name as the implementing property
			// In this case we need to ovverride it
			
			getter = new OJAnnotatedOperation(map.getter(), map.javaTypePath());
			getter.initializeResultVariable("null");
			owner.addToOperations(getter);
		}else{
			if(map.getProperty().isDerivedUnion()){
				return getter;// We should not overwrite the implementing property
			}
		}
		getter.getOwner().addToImports(map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			if(derived){
				if(isMap(map) && !map.isEndToAssociationClass()){
					attributeImplementer.addGetterForOnDerivedProperty(map,owner);
				}
				getter.initializeResultVariable(map.javaDefaultValue());
			}else if(map.isMany() && isMap(map)){
				String defaultValue = map.javaDefaultValue();
				getter.initializeResultVariable(defaultValue.substring(0, defaultValue.length() - 1) + AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".values())");
				OJAnnotatedOperation getterFor = new OJAnnotatedOperation(map.getter(), map.javaBaseTypePath());
				owner.addToOperations(getterFor);
				addQualifierParamsAndBuildKeyVariable(getterFor, map.getProperty().getQualifiers());
				getterFor.initializeResultVariable("null");
				getterFor.getBody().addToStatements("result=" + AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".get(key.toString())");
			}else{
				getter.initializeResultVariable(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname());
			}
		}
		if(getter.getReturnType().getElementTypes().size() == 1 && map.isMany() && map.getProperty().getSubsettedProperties().size() > 0){
			getter.getReturnType().markAsExtendingElement(getter.getReturnType().getElementTypes().get(0));
		}
		getter.setStatic(map.isStatic());
		OJUtil.addMetaInfo(getter, map.getProperty());
		// TODO move thisS
		AbstractAttributeImplementer.addPropertyMetaInfo(umlOwner, getter, map.getProperty(), attributeImplementer.getLibrary());
		return getter;
	}
	protected boolean isMap(PropertyMap map){
		return attributeImplementer.isMap(map.getProperty());
	}
	@Override
	public OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
		owner.addToOperations(remover);
		remover.setStatic(map.isStatic());
		if(isMap(map)){
			addQualifierParamsAndBuildKeyVariable(remover, map.getProperty().getQualifiers());
		}
		if(!(owner instanceof OJAnnotatedInterface)){
			if(map.isMany()){
				if(isMap(map)){
					remover.getBody().addToStatements(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".remove(key.toString())");
				}else{
					remover.getBody().addToStatements(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".remove(" + map.fieldname() + ")");
				}
			}else{
				String remove = AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + "=null";
				String condition = map.getter() + "()!=null && " + map.fieldname() + "!=null && " + map.fieldname() + ".equals(" + map.getter() + "())";
				OJIfStatement ifEquals = new OJIfStatement(condition, remove);
				remover.getBody().addToStatements(ifEquals);
				ifEquals.getThenPart().addToStatements(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + "=null");
			}
		}
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		return remover;
	}
	
	@Override
	public OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		owner.addToOperations(adder);
		adder.setVisibility(OJVisibilityKind.PUBLIC);
		if(isMap(map)){
			addQualifierParamsAndBuildKeyVariable(adder, map.getProperty().getQualifiers());
		}
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			if(map.isMany()){
				String containsStatement=null;
				if(map.isAssociationClassToEnd() || map.isEndToAssociationClass()){
					//TODO fix this for redefined associations remember
					containsStatement=map.getter() + "().contains(" + map.fieldname() + ")";
				}else if(isMap(map)){
					containsStatement=AbstractAttributeImplementer.getReferencePrefix(owner, map)+map.fieldname() + ".containsValue(" + map.fieldname() + ")";
				}else{
					containsStatement=AbstractAttributeImplementer.getReferencePrefix(owner, map)+map.fieldname() + ".contains(" + map.fieldname() + ")";
				}
				OJIfStatement ifSet = new OJIfStatement(containsStatement, "return");
				adder.getBody().addToStatements(ifSet);
				if(isMap(map)){
					String targetExpression = map.fieldname();
					if(map.getProperty() instanceof EndToAssociationClass){
						// TODO think of an alternative solution, this code belongs in AssociationClassAttributeImplementor
						AssociationClassEndMap aMap = attributeImplementer.getOjUtil().buildAssociationClassEndMap(((EndToAssociationClass) map.getProperty()).getOriginalProperty());
						targetExpression = targetExpression + "." + aMap.getAssocationClassToOtherEndMap().getter() + "()";
					}
					for(Property q:map.getProperty().getQualifiers()){
						// if we get here, all qualifiers are backed by properties on the baseType
						if(EmfPropertyUtil.getBackingPropertyForQualifier(q) != null){
							PropertyMap qMap = attributeImplementer.getOjUtil().buildStructuralFeatureMap(EmfPropertyUtil.getBackingPropertyForQualifier(q));
							if(attributeImplementer.isModifiable(qMap)){
								adder.getBody().addToStatements("" + targetExpression + "." + qMap.internalAdder() + "(" + qMap.fieldname() + ")");
							}else{
								adder.getBody().addToStatements(
										new OJIfStatement("" + targetExpression + "." + qMap.getter() + "()==null", "throw new IllegalStateException(\"The qualifying property '"
												+ qMap.fieldname() + "' must be set before adding a value to '" + map.fieldname() + "'\")"));
							}
						}else{
							// TODO Currently we validate against this, but this association should become an association class and the property should be
							// set on it;
							// TODO when implementing above, remember to move this method out to the two separate subclasses of AbstractAttributeImplementer.
						}
					}
					adder.getBody().addToStatements(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".put(key.toString()," + map.fieldname() + ")");
					adder.getBody().addToStatements("" + map.fieldname() + "." + map.qualifierPropertySetter() + "(key.toString())");
				}else{
					adder.getBody().addToStatements(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + ".add(" + map.fieldname() + ")");
				}
			}else{
				String currentValue = readCurrentSingleValueFieldValue(owner,map);
				OJIfStatement ifSet = new OJIfStatement(map.fieldname() + ".equals(" + currentValue + ")", "return");
				adder.getBody().addToStatements(ifSet);
				adder.getBody().addToStatements(AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + "=" + map.fieldname() + "");
			}
		}
		adder.addParam(map.fieldname(), map.javaBaseTypePath());
		return adder;
	}
	protected String readCurrentSingleValueFieldValue(OJAnnotatedClass owner,PropertyMap map){
		String currentValue=AbstractAttributeImplementer.getReferencePrefix(owner, map)+map.fieldname();
		if(map.isAssociationClassToEnd() || map.isEndToAssociationClass()){
			//TODO fix this for redefined associations remember
			currentValue=map.getter() + "()";
		}
		return currentValue;
	}
	@Override
	public void init(AbstractAttributeImplementer source){
		this.attributeImplementer=source;
		
	}
	@Override
	public OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedField field;
		if(map.isMany() && isMap(map)){
			OJPathName javaTypePath = new OJPathName("java.util.Map");
			javaTypePath.addToElementTypes(StdlibMap.javaStringType);
			javaTypePath.addToElementTypes(map.javaBaseTypePath());
			field = new OJAnnotatedField(map.fieldname(), javaTypePath);
			field.setInitExp("new HashMap<String," + map.javaBaseType() + ">()");
		}else{
			field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
			if(map.isJavaPrimitive() || map.isCollection()){
				field.setInitExp(map.javaDefaultValue());
			}
		}
		field.setStatic(map.isStatic());
		field.setVisibility(OJVisibilityKind.PROTECTED);// NB!required for AbstractSuperclasses to have access to the concrete implementation
																										// and redefinitions
		owner.addToFields(field);
		return field;
	}
	protected void addQualifierParamsAndBuildKeyVariable(OJAnnotatedOperation adder,List<Property> qualifiers){
		OJPathName formatter = attributeImplementer.getOjUtil().utilClass(attributeImplementer.getCurrentRootObject(), "Formatter");
		adder.getOwner().addToImports(formatter);
		OJAnnotatedField key = new OJAnnotatedField("key", new OJPathName("String"));
		adder.getBody().addToLocals(key);
		StringBuilder sb = new StringBuilder();
		Iterator<Property> iterator = qualifiers.iterator();
		while(iterator.hasNext()){
			Property q = (Property) iterator.next();
			PropertyMap qMap = attributeImplementer.getOjUtil().buildStructuralFeatureMap(q);
			adder.addParam(qMap.fieldname(), qMap.javaBaseTypePath());
			if(EmfClassifierUtil.isSimpleType(qMap.getBaseType())){
				sb.append(formatter.getLast());
				sb.append(".getInstance().format");
				sb.append(qMap.javaType());
				sb.append("Qualifier(");
				sb.append(qMap.fieldname());
				sb.append(")");
				// TODO user formatting
			}else{
				sb.append(qMap.fieldname());
				sb.append(".getUid()");
			}
			if(iterator.hasNext()){
				sb.append("+");
			}
		}
		key.setInitExp(sb.toString());
	}

	@Override
	public void startSetter(OJAnnotatedClass owner,OJAnnotatedOperation setter,PropertyMap map){
		
	}
}
