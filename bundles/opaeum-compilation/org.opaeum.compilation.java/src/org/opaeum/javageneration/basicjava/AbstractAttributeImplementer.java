package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.EndToAssociationClass;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.AbstractOclContext;

public abstract class AbstractAttributeImplementer extends AbstractStructureVisitor{
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	public static final String MANY_INTERNAL_REMOVE_FROM_COLLECTION = "manyInternalRemoveToCollection";
	public static void addPropertyMetaInfo(Classifier owner,OJAnnotatedOperation element,Property property,IPropertyEmulation opaeumLibrary){
		addPropertyMetaInfo(owner.getOwnedRules(), element, property, opaeumLibrary);
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass ojOwner,Classifier umlOwner){
		return true;
	}
	@Override
	protected void visitInterfaceProperty(OJAnnotatedClass oj,Interface owner,PropertyMap map){
		visitProperty(oj, owner, map);
	}
	public static void addPropertyMetaInfo(Collection<? extends Constraint> ownedRules,OJAnnotatedOperation element,Property property,
			IPropertyEmulation opaeumLibrary){
		OJAnnotationValue ap = new OJAnnotationValue(new OJPathName(PropertyMetaInfo.class.getName()));
		ap.putAttribute("isComposite", property.isComposite());
		if(property.getType() != null && EmfClassifierUtil.isSimpleType(property.getType())){
			AbstractStrategyFactory stf = EmfClassifierUtil.getStrategyFactory((DataType) property.getType());
			if(stf != null && stf.getRuntimeStrategyFactory() != null){
				ap.putAttribute("strategyFactory", new OJPathName(stf.getRuntimeStrategyFactory()));
			}
		}
		if(property instanceof IEmulatedElement){
			IEmulatedElement ee = (IEmulatedElement) property;
			ap.putAttribute("uuid", EmfWorkspace.getId(ee.getOriginalElement()));
		}else{
			ap.putAttribute("uuid", EmfWorkspace.getId(property));
		}
		ap.putAttribute("opaeumId", EmfWorkspace.getOpaeumId(property));
		if(property.getOwnedComments().size() > 0){
			ap.putAttribute("shortDescription", property.getOwnedComments().get(0).getBody());
		}
		if(property.getOtherEnd() != null){
			ap.putAttribute("opposite", property.getOtherEnd().getName());
		}
		OJAnnotationAttributeValue constraints = new OJAnnotationAttributeValue("constraints");
		ap.putAttribute(constraints);
		for(Constraint c:ownedRules){
			boolean isLookupConstraint = c.getConstrainedElements().contains(property);
			if(!isLookupConstraint && property instanceof IEmulatedElement){
				isLookupConstraint = c.getConstrainedElements().contains(((IEmulatedElement) property).getOriginalElement());
			}
			if(c.getSpecification() instanceof OpaqueExpression && isLookupConstraint){
				AbstractOclContext oclContext = opaeumLibrary.getOclExpressionContext((OpaqueExpression) c.getSpecification());
				if(oclContext.getExpression() != null && oclContext.getExpression().getType() instanceof CollectionType){
					ap.putAttribute("lookupMethod", "get" + NameConverter.capitalize(c.getName()));
					// Lookup method
				}else{
					// Associated constraint
					OJAnnotationValue constraint = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.PropertyConstraint"));
					constraint.putAttribute("method", "is" + NameConverter.capitalize(c.getName()));
					constraint.putAttribute("message", NameConverter.separateWords(NameConverter.capitalize(c.getName())));
					constraints.addAnnotationValue(constraint);
				}
			}
		}
		element.addAnnotationIfNew(ap);
	}
	public AbstractAttributeImplementer(){
		super();
	}
	protected OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		OJAnnotatedOperation getter = (OJAnnotatedOperation) owner.findOperation(map.getter(), Collections.<OJPathName>emptyList());
		if(getter == null){
			// Could exist in the case of DerivedUnion from an Interface with the same name as the implementing property
			// In this case we need to ovverride it
			getter = new OJAnnotatedOperation(map.getter(), map.javaTypePath());
			owner.addToOperations(getter);
		}else{
			if(map.getProperty().isDerivedUnion()){
				return getter;// We should not overwrite the implementing property
			}
		}
		getter.getOwner().addToImports(map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			if(derived){
				getter.initializeResultVariable(map.javaDefaultValue());
			}else if(map.isMany() && isMap(map.getProperty())){
				String defaultValue = map.javaDefaultValue();
				getter.initializeResultVariable(defaultValue.substring(0, defaultValue.length() - 1) + getReferencePrefix(owner, map) + map.fieldname() + ".values())");
				OJAnnotatedOperation getterFor = new OJAnnotatedOperation(map.getter(), map.javaBaseTypePath());
				owner.addToOperations(getterFor);
				addQualifierParams(getterFor, map.getProperty().getQualifiers());
				getterFor.initializeResultVariable("null");
				getterFor.getBody().addToStatements("result=" + getReferencePrefix(owner, map) + map.fieldname() + ".get(key.toString())");
			}else{
				getter.initializeResultVariable(getReferencePrefix(owner, map) + map.fieldname());
			}
		}
		if(getter.getReturnType().getElementTypes().size() == 1 && map.isMany() && map.getProperty().getSubsettedProperties().size() > 0){
			getter.getReturnType().markAsExtendingElement(getter.getReturnType().getElementTypes().get(0));
		}
		getter.setStatic(map.isStatic());
		Element property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
		// TODO move thisS
		addPropertyMetaInfo(umlOwner, getter, map.getProperty(), getLibrary());
		return getter;
	}
	protected OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
		owner.addToOperations(remover);
		remover.setStatic(map.isStatic());
		if(isMap(map.getProperty())){
			addQualifierParams(remover, map.getProperty().getQualifiers());
		}
		if(!(owner instanceof OJAnnotatedInterface)){
			if(map.isMany()){
				if(isMap(map.getProperty())){
					remover.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".remove(key.toString())");
				}else{
					remover.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".remove(" + map.fieldname() + ")");
				}
			}else{
				String remove = getReferencePrefix(owner, map) + map.fieldname() + "=null";
				String condition = map.getter() + "()!=null && " + map.fieldname() + "!=null && " + map.fieldname() + ".equals(" + map.getter() + "())";
				OJIfStatement ifEquals = new OJIfStatement(condition, remove);
				remover.getBody().addToStatements(ifEquals);
				ifEquals.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + "=null");
			}
		}
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		return remover;
	}
	protected OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		owner.addToOperations(adder);
		adder.setVisibility(OJVisibilityKind.PUBLIC);
		if(isMap(map.getProperty())){
			addQualifierParams(adder, map.getProperty().getQualifiers());
		}
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			if(map.isMany()){
				OJIfStatement ifSet = new OJIfStatement(map.getter() + "().contains(" + map.fieldname() + ")", "return");
				adder.getBody().addToStatements(ifSet);
				if(isMap(map.getProperty())){
					String targetExpression=map.fieldname();
					if(map.getProperty() instanceof EndToAssociationClass){
						//TODO think of an alternative solution, this code belongs in AssociationClassAttributeImplementor
						AssociationClassEndMap aMap = ojUtil.buildAssociationClassEndMap(((EndToAssociationClass)map.getProperty()).getOriginalProperty());
						targetExpression=targetExpression+"." + aMap.getAssocationClassToOtherEndMap().getter() + "()";
						
					}
					for(Property q:map.getProperty().getQualifiers()){
						// if we get here, all qualifiers are backed by properties on the baseType
						PropertyMap qMap = ojUtil.buildStructuralFeatureMap(q);
						adder.getBody().addToStatements("" + targetExpression + "." + qMap.internalAdder() + "(" + qMap.fieldname() + ")");
					}
					adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".put(key.toString()," + map.fieldname() + ")");
					adder.getBody().addToStatements("" + map.fieldname() + "." + map.qualifierPropertySetter() + "(key.toString())");
				}else{
					adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".add(" + map.fieldname() + ")");
				}
			}else{
				OJIfStatement ifSet = new OJIfStatement(map.fieldname() + ".equals(" + map.getter() + "())", "return");
				adder.getBody().addToStatements(ifSet);
				adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + "=" + map.fieldname() + "");
			}
		}
		adder.addParam(map.fieldname(), map.javaBaseTypePath());
		return adder;
	}
	private void addQualifierParams(OJAnnotatedOperation adder,List<Property> qualifiers){
		OJPathName formatter = ojUtil.utilClass(getCurrentRootObject(), "Formatter");
		adder.getOwner().addToImports(formatter);
		OJAnnotatedField key = new OJAnnotatedField("key", new OJPathName("String"));
		adder.getBody().addToLocals(key);
		StringBuilder sb = new StringBuilder();
		Iterator<Property> iterator = qualifiers.iterator();
		while(iterator.hasNext()){
			Property q = (Property) iterator.next();
			PropertyMap qMap = ojUtil.buildStructuralFeatureMap(q);
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
	protected OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedField field;
		if(map.isMany() && isMap(map.getProperty())){
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
	protected void applySimnpleTypesAnnotations(OJAnnotatedField field,Classifier baseType){
		applyStereotypesAsAnnotations(baseType, field);
		for(Generalization g:baseType.getGeneralizations()){
			applySimnpleTypesAnnotations(field, g.getGeneral());
		}
	}
	protected void buildInitExpression(OJAnnotatedClass owner,PropertyMap map,OJAnnotatedField field){
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);
		if(map.isMany() && isMap(map.getProperty())){
			owner.addToImports(new OJPathName("java.util.HashMap"));
			field.setInitExp("new HashMap<String," + map.javaBaseType() + ">()");
		}else{
			field.setInitExp("new " + defaultValue.getTypeNameWithTypeArguments() + "()");
		}
	}
	protected void addQualifierParameters(PropertyMap map,OJOperation adder){
		for(Property q:map.getProperty().getQualifiers()){
			PropertyMap qMap = ojUtil.buildStructuralFeatureMap(q);
			adder.addParam(qMap.fieldname(), qMap.javaTypePath());
		}
	}
	protected OJOperation buildRemoveAll(OJAnnotatedClass owner,PropertyMap map){
		OJOperation removeAll = new OJAnnotatedOperation(map.removeAll());
		removeAll.addParam(map.fieldname(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			removeAll.setStatic(map.isStatic());
			removeAll.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			iterateAndRemove(map, removeAll, map.fieldname());
			owner.addToOperations(removeAll);
		}
		return removeAll;
	}
	private void iterateAndRemove(PropertyMap map,OJOperation adder,String source){
		OJAnnotatedField tmpList = new OJAnnotatedField("tmp", map.javaTypePath());
		tmpList.setInitExp(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 1) + source + ")");
		adder.getBody().addToLocals(tmpList);
		OJForStatement forAll = new OJForStatement();
		forAll.setCollectionExpression("tmp");
		forAll.setElemName("o");
		forAll.setElemType(map.javaBaseTypePath());
		forAll.setBody(new OJBlock());
		if(isMap(map.getProperty())){
			forAll.getBody().addToStatements(map.remover() + "(" + ojUtil.addQualifierArguments(map.getProperty().getQualifiers(), "o") + "o)");
		}else{
			forAll.getBody().addToStatements(map.remover() + "(o)");
		}
		adder.getBody().addToStatements(forAll);
	}
	protected OJOperation buildClear(OJAnnotatedClass owner,PropertyMap map){
		OJOperation clear = new OJAnnotatedOperation(map.clearer());
		if(!(owner instanceof OJAnnotatedInterface)){
			clear.setStatic(map.isStatic());
			clear.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			iterateAndRemove(map, clear, map.getter() + "()");
		}
		owner.addToOperations(clear);
		return clear;
	}
	protected OJOperation buildAddAll(OJAnnotatedClass owner,PropertyMap map){
		OJOperation adder = new OJAnnotatedOperation(map.allAdder());
		adder.addParam(map.fieldname(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJForStatement forAll = new OJForStatement();
			forAll.setCollectionExpression(map.fieldname());
			forAll.setElemName("o");
			forAll.setElemType(map.javaBaseTypePath());
			forAll.setBody(new OJBlock());
			if(isMap(map.getProperty())){
				forAll.getBody().addToStatements(map.adder() + ojUtil.addQualifierArgumentsAndVariableAndBrackets(map.getProperty().getQualifiers(), "o"));
			}else{
				forAll.getBody().addToStatements(map.adder() + "(o)");
			}
			adder.getBody().addToStatements(forAll);
			owner.addToOperations(adder);
		}
		return adder;
	}
	protected void removeFromPropertiesQualifiedByThisProperty(PropertyMap map,OJAnnotatedOperation setter){
		for(Property p:EmfPropertyUtil.getPropertiesQualified(map.getProperty())){
			if(isMap(p) && !p.isReadOnly()){
				modifyPropertiesQualifiedByThisProperty(map, setter, p, true);
			}
		}
	}
	protected void addToPropertiesQualifiedByThisProperty(PropertyMap map,OJAnnotatedOperation setter){
		for(Property p:EmfPropertyUtil.getPropertiesQualified(map.getProperty())){
			if(isMap(p) && !p.isReadOnly()){
				modifyPropertiesQualifiedByThisProperty(map, setter, p, false);
			}
		}
	}
	private void modifyPropertiesQualifiedByThisProperty(PropertyMap map,OJAnnotatedOperation setter,Property qualifiedProperty,boolean remove){
		PropertyMap peerMap = ojUtil.buildStructuralFeatureMap(qualifiedProperty.getOtherEnd());
		PropertyMap qMap = ojUtil.buildStructuralFeatureMap(qualifiedProperty);
		if(peerMap.isOne()){
			OJIfStatement ifNotNull = new OJIfStatement(peerMap.getter() + "()!=null && " + map.getter() + "()!=null");
			setter.getBody().addToStatements(ifNotNull);
			ifNotNull.getThenPart().addToStatements(
					peerMap.getter() + "()." + (remove ? qMap.internalRemover() : qMap.internalAdder()) + "("
							+ ojUtil.addQualifierArguments(qualifiedProperty.getQualifiers(), "this") + "this)");
		}else{
			if(EmfAssociationUtil.isClass(peerMap.getProperty().getAssociation())){
				AssociationClassEndMap aMap = ojUtil.buildAssociationClassEndMap(peerMap.getProperty());
				OJForStatement ifNotNull = new OJForStatement("curVal", aMap.getEndToAssocationClassMap().javaBaseTypePath(), aMap.getEndToAssocationClassMap()
						.getter() + "()");
				setter.getBody().addToStatements(ifNotNull);
				String modifier = remove ? aMap.getOtherEndToAssocationClassMap().internalRemover() : aMap.getOtherEndToAssocationClassMap().internalAdder();
				ifNotNull.getBody().addToStatements(
						"curVal." + aMap.getAssocationClassToOtherEndMap().getter() + "()." + modifier + "("
								+ ojUtil.addQualifierArguments(qualifiedProperty.getQualifiers(), "this") + "curVal)");
			}else{
				OJForStatement ifNotNull = new OJForStatement("curVal", peerMap.javaBaseTypePath(), peerMap.getter() + "()");
				setter.getBody().addToStatements(ifNotNull);
				ifNotNull.getBody().addToStatements(
						"curVal." + (remove ? qMap.internalRemover() : qMap.internalAdder()) + "("
								+ ojUtil.addQualifierArguments(qualifiedProperty.getQualifiers(), "this") + "this)");
			}
		}
	}
	protected OJAnnotatedOperation buildSetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation setter = new OJAnnotatedOperation(map.setter());
		setter.addParam(map.fieldname(), map.javaTypePath());
		Property prop = map.getProperty();
		owner.addToOperations(setter);
		if(!(owner instanceof OJAnnotatedInterface)){
			setter.setStatic(map.isStatic());
			setter.setVisibility(prop.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			removeFromPropertiesQualifiedByThisProperty(map, setter);
			if(StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER)){
				setter.getBody().addToStatements("this." + map.fieldname() + "=" + map.fieldname());
			}else if(prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable()){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(prop.getOtherEnd());
				if(map.isManyToOne()){
					String args = buildQualifiedArgumentStringForOtherEnd(prop);
					// remove "this" from existing reference
					OJIfStatement ifCurrentValueNotNull = new OJIfStatement();
					ifCurrentValueNotNull.setCondition(getReferencePrefix(owner, map) + map.getter() + "()!=null");
					if(!otherMap.getProperty().isReadOnly()){
						ifCurrentValueNotNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.getter() + "()." + otherMap.internalRemover() + args);
					}
					setter.getBody().addToStatements(ifCurrentValueNotNull);
					OJIfStatement ifNewValueNull = new OJIfStatement(map.fieldname() + " == null", getReferencePrefix(owner, map) + map.internalRemover() + "("
							+ getReferencePrefix(owner, map) + map.getter() + "())");
					setter.getBody().addToStatements(ifNewValueNull);
					ifNewValueNull.setElsePart(new OJBlock());
					ifNewValueNull.getElsePart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					// add "this" to new reference
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + args);
					setter.getBody().addToStatements(ifParamNotNull);
				}else if(map.isMany()){
					if(!map.getProperty().isReadOnly()){
						setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
					}
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.fieldname() + ")");
				}else if(map.isOneToOne()){
					OJAnnotatedField oldValue = new OJAnnotatedField("oldValue", map.javaTypePath());
					oldValue.setInitExp(getReferencePrefix(owner, map) + map.getter() + "()");
					setter.getBody().addToLocals(oldValue);
					// If oldValue==null then set the new Value unconditionally
					OJIfStatement ifNull = new OJIfStatement();
					ifNull.setName(AttributeImplementor.IF_OLD_VALUE_NULL);
					ifNull.setCondition("oldValue==null");// && );
					setter.getBody().addToStatements(ifNull);
					OJIfStatement ifNotSame = new OJIfStatement();
					ifNotSame.setCondition("!oldValue.equals(" + map.fieldname() + ")");
					ifNull.addToElsePart(ifNotSame);
					// remove "this" from existing reference
					ifNotSame.getThenPart().addToStatements("oldValue." + otherMap.internalRemover() + "(this)");
					ifNotSame.getThenPart().addToStatements(map.internalRemover() + "(oldValue)");
					// add "this" to new reference\
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(
							owner.getName() + " oldOther = (" + owner.getName() + ")" + map.fieldname() + "." + otherMap.getter() + "()");
					if(!prop.isReadOnly()){
						ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(oldOther)");
					}
					ifParamNotNull.getThenPart().addToStatements(
							new OJIfStatement("oldOther != null", "oldOther" + "." + map.internalRemover() + "(" + map.fieldname() + ")"));
					ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
					ifNotSame.getThenPart().addToStatements(ifParamNotNull);
					ifNotSame.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					ifNull.getThenPart().addToStatements(ifParamNotNull);
					ifNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
				}
			}else{
				if(map.isMany()){
					if(!map.getProperty().isReadOnly()){
						setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
					}
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.fieldname() + ")");
				}else{
					if(prop.isReadOnly()){
						setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					}else{
						OJIfStatement ifNull = new OJIfStatement(map.fieldname() + " == null", getReferencePrefix(owner, map) + map.internalRemover() + "(" + map.getter()
								+ "())");
						setter.getBody().addToStatements(ifNull);
						ifNull.setElsePart(new OJBlock());
						ifNull.getElsePart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					}
				}
			}
			addToPropertiesQualifiedByThisProperty(map, setter);
		}
		return setter;
	}
	protected String getReferencePrefix(OJAnnotatedClass o,PropertyMap map){
		return map.isStatic() ? o.getName() + "." : "this.";
	}
	protected String buildQualifiedArgumentStringForOtherEnd(Property prop){
		String args = "(this)";
		if(isMap(prop.getOtherEnd())){
			args = "(" + ojUtil.addQualifierArguments(prop.getOtherEnd().getQualifiers(), "this") + "this)";
		}
		return args;
	}
}