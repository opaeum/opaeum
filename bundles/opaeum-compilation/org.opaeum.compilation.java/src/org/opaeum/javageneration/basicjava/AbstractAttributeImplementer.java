package org.opaeum.javageneration.basicjava;

import java.util.Collection;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClassifier;
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
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.AbstractOclContext;

public abstract class AbstractAttributeImplementer extends AbstractStructureVisitor{
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	public static final String MANY_INTERNAL_REMOVE_FROM_COLLECTION = "manyInternalRemoveToCollection";
	private AttributeStrategy strategy;
	@Override
	public void startVisiting(EmfWorkspace root){
		getStrategy().init(this);
		super.startVisiting(root);
	}
	public static void addPropertyMetaInfo(Classifier owner,OJAnnotatedOperation element,Property property,IPropertyEmulation opaeumLibrary){
		addPropertyMetaInfo(owner.getOwnedRules(), element, property, opaeumLibrary);
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	protected void addGetterForOnDerivedProperty(PropertyMap map,OJClassifier owner){
		OJAnnotatedOperation getterFor = new OJAnnotatedOperation(map.getter(), map.javaBaseTypePath());
		owner.addToOperations(getterFor);
		addQualifierParameters(map,getterFor);
		getterFor.initializeResultVariable("null");
		OJForStatement forAll = new OJForStatement("elem", map.javaBaseTypePath(), map.getter()+ "()");
		StringBuilder condition = new StringBuilder();
		EList<Property> qualifiers = map.getProperty().getQualifiers();
		for(Property property:qualifiers){
			Property bp = EmfPropertyUtil.getBackingPropertyForQualifier(property);
			if(bp!=null){
				condition.append("elem.");
				condition.append(ojUtil.buildStructuralFeatureMap(bp).getter());
				condition.append("()!=null && elem.");
				condition.append(ojUtil.buildStructuralFeatureMap(bp).getter());
				condition.append("().equals(");
				condition.append(ojUtil.buildStructuralFeatureMap(bp).fieldname());
				condition.append(") && ");
			}else{
				//TODO implement 
				//Will be an association class, iterate through the links and retrieve the property from the the link right object/property  
			}
		}
		OJIfStatement ifMatch = new OJIfStatement(condition.substring(0, condition.length()-3), "result=elem");
		forAll.getBody().addToStatements(ifMatch);
		ifMatch.getThenPart().addToStatements("break");
		getterFor.getBody().addToStatements(forAll);
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
	protected final OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		OJAnnotatedOperation getter = getStrategy().buildGetter(umlOwner, owner, map, derived);
		return getter;
	}
	protected final OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		return getStrategy().buildInternalRemover(owner, map);
	}
	protected final OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		return getStrategy().buildInternalAdder(owner, map);
	}
	protected final OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		return getStrategy().buildField(owner,map);
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
		OJPathName type = map.javaTypePath().getDeepCopy();
		type.markAsExtendingElement(type.getElementTypes().get(0));
		removeAll.addParam(map.fieldname(), type);
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
				forAll.getBody().addToStatements(map.adder() + "(" + ojUtil.addQualifierArguments(map.getProperty().getQualifiers(), "o") + "o" + ")");
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
			if(isMap(p) && isModifiable(p)){
				modifyPropertiesQualifiedByThisProperty(map, setter, p, true);
			}
		}
	}
	protected void addToPropertiesQualifiedByThisProperty(PropertyMap map,OJAnnotatedOperation setter){
		for(Property p:EmfPropertyUtil.getPropertiesQualified(map.getProperty())){
			if(isMap(p) && isModifiable(p)){
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
			if(EmfAssociationUtil.isClass(peerMap.getProperty().getAssociation())){
				AssociationClassEndMap aMap = peerMap.getAssocationClassMap();
				String args = ojUtil.addQualifierArguments(qualifiedProperty.getQualifiers(), "this") + aMap.getEndToAssocationClassMap().getter() + "()";
				String modifier = remove ? aMap.getOtherEndToAssocationClassMap().internalRemover() : aMap.getOtherEndToAssocationClassMap().internalAdder();
				ifNotNull.getThenPart().addToStatements(peerMap.getter() + "()." + modifier + "(" + args + ")");
			}else{
				ifNotNull.getThenPart().addToStatements(
						peerMap.getter() + "()." + (remove ? qMap.internalRemover() : qMap.internalAdder()) + "("
								+ ojUtil.addQualifierArguments(qualifiedProperty.getQualifiers(), "this") + "this)");
			}
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
	public static String getReferencePrefix(OJAnnotatedClass o,PropertyMap map){
		return map.isStatic() ? o.getName() + "." : "this.";
	}
	public OJUtil getOjUtil(){
		return ojUtil;
	}
	protected AttributeStrategy getStrategy() {
		if(strategy==null){
			strategy=super.transformationContext.getStrategy(AttributeStrategy.class);
			strategy.init(this);
		}
		return strategy;
	}
	void setStrategy(AttributeStrategy strategy) {
		this.strategy = strategy;
	}
}