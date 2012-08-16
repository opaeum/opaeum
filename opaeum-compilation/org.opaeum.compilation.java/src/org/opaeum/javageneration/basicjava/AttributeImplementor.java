package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

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
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForAssociation;
import org.opaeum.eclipse.emulated.EndToAssociationClass;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJConstructor;
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
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;
import org.opaeum.ocl.uml.AbstractOclContext;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class},after = {Java6ModelGenerator.class})
public class AttributeImplementor extends AbstractStructureVisitor{
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	public static final String MANY_INTERNAL_REMOVE_FROM_COLLECTION = "manyInternalRemoveToCollection";
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public static void addPropertyMetaInfo(Classifier owner,OJAnnotatedOperation element,Property property,OpaeumLibrary opaeumLibrary){
		addPropertyMetaInfo(owner.getOwnedRules(), element, property, opaeumLibrary);
	}
	public static void addPropertyMetaInfo(Collection<? extends Constraint> ownedRules,OJAnnotatedOperation element,Property property,
			OpaeumLibrary opaeumLibrary){
		OJAnnotationValue ap = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.PropertyMetaInfo"));
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
			ap.putAttribute("shortDescripion", property.getOwnedComments().get(0).getBody());
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
				if(oclContext.getExpression().getType() instanceof CollectionType){
					ap.putAttribute("lookupMethod", "get" + NameConverter.capitalize(c.getName()));
					// Lookup method
				}else{
					// Associated constraint
					OJAnnotationValue constraint = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.PropertyConstraint"));
					constraint.putAttribute("name", NameConverter.capitalize(c.getName()));
					constraint.putAttribute("message", NameConverter.separateWords(NameConverter.capitalize(c.getName())));
					constraints.addAnnotationValue(constraint);
				}
			}
		}
		element.addAnnotationIfNew(ap);
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		if(umlOwner instanceof Association){
			OJAnnotatedClass ojOwner = findJavaClass(umlOwner);
			OJConstructor constr1 = new OJConstructor();
			Association assocClass = (Association) umlOwner;
			EmulatedPropertyHolderForAssociation holder = (EmulatedPropertyHolderForAssociation) getLibrary().getEmulatedPropertyHolder(
					assocClass);
			PropertyMap mapToEnd1 = ojUtil.buildStructuralFeatureMap(holder.getEmulatedAttribute(assocClass.getMemberEnds().get(0)));
			PropertyMap mapToEnd2 = ojUtil.buildStructuralFeatureMap(holder.getEmulatedAttribute(assocClass.getMemberEnds().get(1)));
			constr1.addParam("end1", mapToEnd1.javaTypePath());
			constr1.addParam("end2", mapToEnd2.javaTypePath());
			constr1.getBody().addToStatements("this." + mapToEnd1.internalAdder() + "(end1)");
			constr1.getBody().addToStatements("this." + mapToEnd2.internalAdder() + "(end2)");
			ojOwner.addToConstructors(constr1);
			OJConstructor constr2 = new OJConstructor();
			ojOwner.addToConstructors(constr2);
			constr2.addParam("end2", mapToEnd2.javaTypePath());
			constr2.addParam("end1", mapToEnd1.javaTypePath());
			constr2.getBody().addToStatements("this." + mapToEnd1.internalAdder() + "(end1)");
			constr2.getBody().addToStatements("this." + mapToEnd2.internalAdder() + "(end2)");
			OJAnnotatedOperation clear = new OJAnnotatedOperation("clear");
			ojOwner.addToOperations(clear);
			if(assocClass.getMemberEnds().get(0).isNavigable()){
				EndToAssociationClass end1ToAssocationClass = holder.getEndToAssociation(assocClass.getMemberEnds().get(0));
				PropertyMap mapFromEnd1 = ojUtil.buildStructuralFeatureMap(end1ToAssocationClass);
				if(assocClass.getMemberEnds().get(0).isNavigable()){
					clear.getBody().addToStatements(mapToEnd2.getter() + "()." + mapFromEnd1.internalRemover() + "(this)");
					clear.getBody().addToStatements("this." + mapToEnd2.internalRemover() + "(" + mapToEnd2.getter() + "())");
				}
			}
			if(assocClass.getMemberEnds().get(1).isNavigable()){
				EndToAssociationClass end2ToAssocationClass = holder.getEndToAssociation(assocClass.getMemberEnds().get(1));
				PropertyMap mapFromEnd2 = ojUtil.buildStructuralFeatureMap(end2ToAssocationClass);
				if(assocClass.getMemberEnds().get(1).isNavigable()){
					clear.getBody().addToStatements(mapToEnd1.getter() + "()." + mapFromEnd2.internalRemover() + "(this)");
					clear.getBody().addToStatements("this." + mapToEnd1.internalRemover() + "(" + mapToEnd1.getter() + "())");
				}
			}
		}
		// Do nothing
	}
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(Interface i){
		if(ojUtil.hasOJClass(i)){
			for(Property p:i.getOwnedAttributes()){
				visitProperty(i, ojUtil.buildStructuralFeatureMap(p));
			}
			for(Association a:i.getAssociations()){
				for(Property p:a.getMemberEnds()){
					if(p.getOtherEnd().getType() == i){
						if(EmfAssociationUtil.isClass(a)){
							EmulatedPropertyHolderForAssociation epha = (EmulatedPropertyHolderForAssociation) getLibrary().getEmulatedPropertyHolder(a);
							visitProperty(i, ojUtil.buildStructuralFeatureMap(epha.getEndToAssociation(p)));
						}
						visitProperty(i, ojUtil.buildStructuralFeatureMap(p));
					}
				}
			}
		}
	}
	protected void visitProperty(Classifier umlOwner,PropertyMap map){
		Property p = map.getProperty();
		if(!OJUtil.isBuiltIn(p)){
			if(StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER)){
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				buildSetter(umlOwner, owner, map);
				buildField(owner, map).setTransient(true);
				OJOperation getter = buildGetter(umlOwner, owner, map, false);
				getter.setBody(new OJBlock());
				OJIfStatement ifNull = new OJIfStatement(OJAnnotatedOperation.RESULT + "==null", OJAnnotatedOperation.RESULT + "="
						+ map.fieldname() + "=(" + map.javaBaseType() + ")" + org.opaeum.runtime.environment.Environment.class.getName()
						+ ".getInstance().getComponent(" + map.javaTypePath() + ".class)");
				getter.getBody().addToStatements(ifNull);
				owner.addToImports(map.javaBaseTypePath());
			}else if(EmfPropertyUtil.isDerived( p)){
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				OJAnnotatedOperation getter = buildGetter(umlOwner, owner, map, true);
				applyStereotypesAsAnnotations((p), getter);
			}else{
				implementAttributeFully(umlOwner, map);
			}
		}
	}
	protected OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		if(!(owner instanceof OJAnnotatedInterface)){
			if(derived){
				getter.initializeResultVariable(map.javaDefaultValue());
			}else if(map.isMany() && isMap(map.getProperty())){
				String defaultValue = map.javaDefaultValue();
				getter.initializeResultVariable(defaultValue.substring(0, defaultValue.length() - 1) + "this." + map.fieldname() + ".values())");
				OJAnnotatedOperation getterFor = new OJAnnotatedOperation(map.getter(), map.javaBaseTypePath());
				addQualifierParams(getterFor, map.getProperty().getQualifiers());
				getterFor.initializeResultVariable("null");
				getterFor.getBody().addToStatements("result=" + getReferencePrefix(owner, map) + map.fieldname() + ".get(key.toString())");
				owner.addToOperations(getterFor);
			}else{
				getter.initializeResultVariable("this." + map.fieldname());
			}
		}
		getter.setStatic(map.isStatic());
		Element property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
		// TODO move thisS
		addPropertyMetaInfo(umlOwner, getter, map.getProperty(), getLibrary());
		return getter;
	}
	@Override
	public void visitAssociationClassProperty(Classifier c,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
		OJAnnotatedClass owner = findJavaClass(c);
		if( EmfPropertyUtil.isDerived( map.getProperty())){
			buildGetter(c, owner, aMap);
		}else{
			if(map.isMany()){
				// These are all the same as for normal attributes
				buildAdder(owner, map);
				if(!isMap(map.getProperty())){
					buildAddAll(owner, map);
					buildRemoveAll(owner, map);
				}
				buildRemover(owner, map);
				buildClear(owner, map);
			}
			buildSetter(c, owner, map);
			// Here are the deviations from normal attributes
			buildInternalAdder(owner, aMap);
			buildInternalRemover(owner, aMap);
			buildGetter(c, owner, aMap);
			buildGetterFor(owner, aMap);
		}
	}
	protected void buildGetterFor(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap mapToAssocationClass = aMap.getEndToAssocationClassMap();
		OJAnnotatedOperation getter = new OJAnnotatedOperation(mapToAssocationClass.getter() + "For", mapToAssocationClass.javaBaseTypePath());
		getter.addParam("match", aMap.getAssocationClassToOtherEndMap().javaBaseTypePath());
		owner.addToOperations(getter);
		if(mapToAssocationClass.isMany()){
			OJForStatement forEach = new OJForStatement("var", mapToAssocationClass.javaBaseTypePath(), mapToAssocationClass.getter() + "()");
			getter.getBody().addToStatements(forEach);
			forEach.getBody().addToStatements(
					new OJIfStatement("var." + aMap.getAssocationClassToOtherEndMap().getter() + "().equals(match)", "return var"));
			getter.getBody().addToStatements("return null");
		}else{
			OJIfStatement ifEquals = new OJIfStatement(mapToAssocationClass.fieldname() + "." + aMap.getAssocationClassToOtherEndMap().getter()
					+ "().equals(match)");
			getter.getBody().addToStatements(ifEquals);
			ifEquals.getThenPart().addToStatements("return " + mapToAssocationClass.fieldname());
			ifEquals.setElsePart(new OJBlock());
			ifEquals.getElsePart().addToStatements("return null");
		}
	}
	protected void buildInternalAdder(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
		OJOperation internalAdder = new OJAnnotatedOperation(map.internalAdder());
		internalAdder.addParam(map.fieldname(), map.javaBaseTypePath());
		PropertyMap mapToAssClass = aMap.getEndToAssocationClassMap();
		internalAdder.setStatic(map.isStatic());
		String ref = getReferencePrefix(owner, map);
		OJAnnotatedField newOne = new OJAnnotatedField("newOne", mapToAssClass.javaBaseFacadeTypePath());
		internalAdder.getBody().addToLocals(newOne);
		newOne.setInitExp("new " + mapToAssClass.javaBaseType() + "(this," + map.fieldname() + ")");
		internalAdder.getBody().addToStatements(ref + mapToAssClass.internalAdder() + "(newOne)");
		if(aMap.getOtherEndToAssocationClassMap() != null){
			// Could be non-navigable
			internalAdder.getBody().addToStatements(
					"newOne." + aMap.getAssocationClassToOtherEndMap().getter() + "()." + aMap.getOtherEndToAssocationClassMap().internalAdder()
							+ "(newOne)");
		}
		owner.addToOperations(internalAdder);
	}
	protected String getReferencePrefix(OJAnnotatedClass o,PropertyMap map){
		return map.isStatic() ? o.getName() + "." : "this.";
	}
	protected void buildInternalRemover(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
		OJOperation internalRemover = new OJAnnotatedOperation(map.internalRemover());
		internalRemover.addParam(map.fieldname(), map.javaBaseTypePath());
		internalRemover.setStatic(map.isStatic());
		PropertyMap mapToAssClass = aMap.getEndToAssocationClassMap();
		if(!(owner instanceof OJAnnotatedInterface)){
			internalRemover.setStatic(map.isStatic());
			String newOne = mapToAssClass.javaDefaultValue();
			newOne = newOne.substring(0, newOne.length() - 1);
			if(map.isMany()){
				OJForStatement foreach = new OJForStatement("cur", mapToAssClass.javaBaseTypePath(), newOne + getReferencePrefix(owner, map)
						+ mapToAssClass.fieldname() + ")");
				internalRemover.getBody().addToStatements(foreach);
				OJIfStatement ifMatch = new OJIfStatement("cur." + aMap.getAssocationClassToOtherEndMap().getter() + "().equals(" + map.fieldname()
						+ ")");
				foreach.getBody().addToStatements(ifMatch);
				ifMatch.getThenPart().addToStatements("cur.clear()");
				ifMatch.getThenPart().addToStatements("break");
			}else{
				OJIfStatement ifNotNull = new OJIfStatement(getReferencePrefix(owner, map) + mapToAssClass.fieldname() + "!=null");
				internalRemover.getBody().addToStatements(ifNotNull);
				ifNotNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + mapToAssClass.fieldname() + ".clear()");
			}
		}
		owner.addToOperations(internalRemover);
	}
	protected void buildGetter(Classifier umlOwner,OJAnnotatedClass owner,AssociationClassEndMap aMap){
		PropertyMap map = aMap.getMap();
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			getter.setStatic(map.isStatic());
			getter.initializeResultVariable(map.javaDefaultValue());
			PropertyMap mapToAssClass = aMap.getEndToAssocationClassMap();
			Association assc = (Association) map.getProperty().getAssociation();
			EmulatedPropertyHolderForAssociation holder = (EmulatedPropertyHolderForAssociation) getLibrary().getEmulatedPropertyHolder(assc);
			Property fromAssToOtherEnd = holder.getEmulatedAttribute(map.getProperty());
			PropertyMap mapFromAssClassToOtherEnd = ojUtil.buildStructuralFeatureMap(fromAssToOtherEnd);
			if(map.isMany()){
				OJForStatement foreach = new OJForStatement("cur", mapToAssClass.javaBaseTypePath(), getReferencePrefix(owner, map)
						+ mapToAssClass.getter() + "()");
				getter.getBody().addToStatements(foreach);
				foreach.getBody().addToStatements("result.add(cur." + mapFromAssClassToOtherEnd.getter() + "())");
			}else{
				OJIfStatement ifNotNull = new OJIfStatement(getReferencePrefix(owner, map) + mapToAssClass.fieldname() + "!=null");
				getter.getBody().addToStatements(ifNotNull);
				ifNotNull.getThenPart().addToStatements(
						"result = " + getReferencePrefix(owner, map) + mapToAssClass.fieldname() + "."
								+ aMap.getAssocationClassToOtherEndMap().getter() + "()");
			}
		}
		addPropertyMetaInfo(umlOwner, getter, map.getProperty(), getLibrary());
		owner.addToOperations(getter);
	}
	protected void implementAttributeFully(Classifier umlOwner,PropertyMap map){
		Property p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJAnnotatedField field = null;
		field = buildField(owner, map);
		buildInternalAdder(owner, map);
		if(!p.isReadOnly()){
			buildInternalRemover(owner, map);
		}
		if(p.getOtherEnd() != null && isMap(p.getOtherEnd())){
			PropertyMap otherMAp = ojUtil.buildStructuralFeatureMap(p.getOtherEnd());
			OJUtil.addPersistentProperty(owner, otherMAp.qualifierProperty(), new OJPathName("String"), true);
		}
		if(map.isMany()){
			if(field != null){
				buildInitExpression(owner, map, field);
			}
			buildAdder(owner, map);
			if(!isMap(map.getProperty())){
				buildSetter(umlOwner, owner, map);
				buildAddAll(owner, map);
				if(!p.isReadOnly()){
					buildRemoveAll(owner, map);
				}
			}
			if(!p.isReadOnly()){
				buildRemover(owner, map);
				buildClear(owner, map);
			}
		}else{
			buildSetter(umlOwner, owner, map);
		}
		buildGetter(umlOwner, owner, map, false);
		if(field != null){
			applyStereotypesAsAnnotations((p), field);
			Classifier baseType = (Classifier) map.getBaseType();
			if(EmfClassifierUtil.isSimpleType(baseType)){
				applySimnpleTypesAnnotations(field, baseType);
			}
		}
	}
	protected void buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
		remover.setStatic(map.isStatic());
		if(map.isMany()){
			if(isMap(map.getProperty())){
				List<Property> qualifiers = map.getProperty().getQualifiers();
				addQualifierParams(remover, qualifiers);
				remover.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".remove(key.toString())");
			}else{
				remover.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".remove(val)");
			}
		}else{
			String remove = getReferencePrefix(owner, map) + map.fieldname() + "=null";
			String condition = map.getter() + "()!=null && val!=null && val.equals(" + map.getter() + "())";
			OJIfStatement ifEquals = new OJIfStatement(condition, remove);
			remover.getBody().addToStatements(ifEquals);
			ifEquals.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + "=null");
		}
		remover.addParam("val", map.javaBaseTypePath());
		owner.addToOperations(remover);
	}
	protected void buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(OJVisibilityKind.PUBLIC);
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			if(map.isMany()){
				if(isMap(map.getProperty())){
					List<Property> qualifiers = map.getProperty().getQualifiers();
					addQualifierParams(adder, qualifiers);
					for(Property q:qualifiers){
						// if we get here, all qualifiers are backed by properties on the baseType
						PropertyMap qMap = ojUtil.buildStructuralFeatureMap(q);
						adder.getBody().addToStatements("val." + qMap.internalAdder() + "(" + qMap.fieldname() + ")");
					}
					adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".put(key.toString(),val)");
					adder.getBody().addToStatements("val." + map.qualifierPropertySetter() + "(key.toString())");
				}else{
					adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".add(val)");
				}
			}else{
				adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + "=val");
			}
		}
		adder.addParam("val", map.javaBaseTypePath());
		owner.addToOperations(adder);
	}
	private void addQualifierParams(OJAnnotatedOperation adder,List<Property> qualifiers){
		OJAnnotatedField key = new OJAnnotatedField("key", new OJPathName("StringBuilder"));
		adder.getBody().addToLocals(key);
		key.setInitExp("new StringBuilder()");
		for(Property q:qualifiers){
			PropertyMap qMap = ojUtil.buildStructuralFeatureMap(q);
			adder.addParam(qMap.fieldname(), qMap.javaBaseTypePath());
			if(EmfClassifierUtil.isSimpleType(qMap.getBaseType())){
				adder.getBody().addToStatements("key.append(" + qMap.fieldname() + ".toString())");
			}else{
				adder.getBody().addToStatements("key.append(" + qMap.fieldname() + ".getUid())");
			}
		}
	}
	protected OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		OJPathName javaTypePath;
		if(map.isMany() && isMap(map.getProperty())){
			javaTypePath = new OJPathName("java.util.Map");
			javaTypePath.addToElementTypes(new OJPathName("String"));
			javaTypePath.addToElementTypes(map.javaBaseTypePath());
		}else{
			javaTypePath = map.javaTypePath();
		}
		OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), javaTypePath);
		if(map.isJavaPrimitive() || map.isCollection()){
			field.setInitExp(map.javaDefaultValue());
		}
		field.setStatic(map.isStatic());
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
	protected OJOperation buildAdder(OJAnnotatedClass owner,PropertyMap map){
		OJOperation adder = new OJAnnotatedOperation(map.adder());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.setStatic(map.isStatic());
			String internalAddStatement = map.internalAdder() + "(" + map.fieldname() + ")";
			if(isMap(map.getProperty())){
				StringBuilder sb = addQualifierArguments(map, adder);
				internalAddStatement = map.internalAdder() + "(" + sb.toString() + map.fieldname() + ")";
			}
			if(!(map.getProperty().getOtherEnd() == null || EmfPropertyUtil.isDerived(map.getProperty().getOtherEnd()))
					&& map.getProperty().getOtherEnd().isNavigable()){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap((map.getProperty()).getOtherEnd());
				if(otherMap.isMany()){
					if(!ojUtil.hasOJClass((Classifier) map.getProperty().getAssociation())){
						if(isMap(map.getProperty().getOtherEnd())){
							adder.getBody().addToStatements(
									map.fieldname() + "." + otherMap.internalAdder() + "(" + addQualifierArguments(otherMap, "this") + "this)");
						}else if(map.getProperty().getAssociation() == null || !EmfAssociationUtil.isClass(map.getProperty().getAssociation())){
							adder.getBody().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(this)");
						}
					}
					adder.getBody().addToStatements(internalAddStatement);
				}else{
					OJIfStatement ifNotNul2 = new OJIfStatement(map.fieldname() + "!=null");
					adder.getBody().addToStatements(ifNotNul2);
					if(!otherMap.getProperty().isReadOnly()){
						ifNotNul2.getThenPart().addToStatements(
								map.fieldname() + "." + otherMap.internalRemover() + "(" + map.fieldname() + "." + otherMap.getter() + "())");
					}
					if(isMap(map.getProperty().getOtherEnd())){
						ifNotNul2.getThenPart().addToStatements(
								map.fieldname() + "." + otherMap.internalAdder() + "(" + addQualifierArguments(otherMap, "this") + "this)");
					}else if(map.getProperty().getAssociation() == null || !EmfAssociationUtil.isClass(map.getProperty().getAssociation())){
						ifNotNul2.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(this)");
					}
					ifNotNul2.getThenPart().addToStatements(internalAddStatement);
					// if(map.getBaseType() instanceof Interface){
					// adder.getBody().addToStatements(map.fieldname() + "." +
					// otherMap.getter() + "().add(this)");
					// }
				}
			}else{
				adder.getBody().addToStatements(internalAddStatement);
			}
		}
		adder.addParam(map.fieldname(), map.javaBaseTypePath());
		owner.addToOperations(adder);
		return adder;
	}
	private StringBuilder addQualifierArguments(PropertyMap map,String target){
		StringBuilder result = new StringBuilder();
		for(Property p:map.getProperty().getQualifiers()){
			PropertyMap qMap = ojUtil.buildStructuralFeatureMap(p);
			result.append(target);
			result.append(".");
			result.append(qMap.getter());
			result.append("(),");
		}
		return result;
	}
	private StringBuilder addQualifierArguments(PropertyMap map,OJOperation adder){
		StringBuilder sb = new StringBuilder();
		for(Property q:map.getProperty().getQualifiers()){
			PropertyMap qMap = ojUtil.buildStructuralFeatureMap(q);
			sb.append(qMap.fieldname());
			sb.append(',');
			adder.addParam(qMap.fieldname(), qMap.javaTypePath());
		}
		return sb;
	}
	protected OJOperation buildRemover(OJAnnotatedClass owner,PropertyMap map){
		OJOperation remover = new OJAnnotatedOperation(map.remover());
		Property p = map.getProperty();
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
			remover.setVisibility(p.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJIfStatement ifNotNull = new OJIfStatement(map.fieldname() + "!=null");
			String removeStatement = map.internalRemover() + "(" + map.fieldname() + ")";
			if(isMap(map.getProperty())){
				StringBuilder sb = addQualifierArguments(map, remover);
				removeStatement = map.internalRemover() + "(" + sb.toString() + map.fieldname() + ")";
			}
			if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap((p).getOtherEnd());
				remover.getBody().addToStatements(ifNotNull);
				if(!ojUtil.hasOJClass((Classifier) map.getProperty().getAssociation()) && !map.getProperty().getOtherEnd().isReadOnly()){
					ifNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(this)");
				}
				ifNotNull.getThenPart().addToStatements(removeStatement);
			}else{
				ifNotNull.getThenPart().addToStatements(removeStatement);
			}
			owner.addToOperations(remover);
		}
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		return remover;
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
			if(isMap(map.getProperty())){
				iterateAndRemove(map, clear, map.getter() + "()");
				clear.getBody().addToStatements(map.fieldname() + ".clear()");
			}else{
				clear.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
			}
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
			forAll.getBody().addToStatements(map.adder() + "(o)");
			adder.getBody().addToStatements(forAll);
			owner.addToOperations(adder);
		}
		return adder;
	}
	protected OJOperation buildSetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map){
		OJOperation setter = new OJAnnotatedOperation(map.setter());
		setter.addParam(map.fieldname(), map.javaTypePath());
		Property prop = map.getProperty();
		owner.addToOperations(setter);
		if(!(owner instanceof OJAnnotatedInterface)){
			setter.setStatic(map.isStatic());
			setter.setVisibility(prop.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			for(Property p:EmfPropertyUtil.getPropertiesQualified(map.getProperty())){
				if(isMap(p)){
					PropertyMap peerMap = ojUtil.buildStructuralFeatureMap(p.getOtherEnd());
					PropertyMap qMap = ojUtil.buildStructuralFeatureMap(p);
					OJIfStatement ifNotNull = new OJIfStatement(peerMap.getter() + "()!=null && " + map.getter() + "()!=null");
					setter.getBody().addToStatements(ifNotNull);
					ifNotNull.getThenPart().addToStatements(
							peerMap.getter() + "()." + qMap.internalRemover() + "(" + ojUtil.addQualifierArguments(p.getQualifiers(), "this") + "this)");
				}
			}
			if(StereotypesHelper.hasStereotype(map.getBaseType(), StereotypeNames.HELPER)){
				setter.getBody().addToStatements("this." + map.fieldname() + "=" + map.fieldname());
			}else if(prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable()){
				PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(prop.getOtherEnd());
				String args = "(this)";
				if(map.isManyToOne()){
					if(isMap(prop.getOtherEnd())){
						args = "(" + ojUtil.addQualifierArguments(prop.getOtherEnd().getQualifiers(), "this") + "this)";
					}
					// remove "this" from existing reference
					OJIfStatement ifNotNull = new OJIfStatement();
					ifNotNull.setCondition(getReferencePrefix(owner, map) + map.getter() + "()!=null");
					if(!otherMap.getProperty().isReadOnly()){
						ifNotNull.getThenPart().addToStatements(
								getReferencePrefix(owner, map) + map.getter() + "()." + otherMap.internalRemover() + args);
					}
					setter.getBody().addToStatements(ifNotNull);
					// add "this" to new reference
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					if(prop.getAssociation() == null || !EmfAssociationUtil.isClass(prop.getAssociation())){
						// Association classes cause both ends to be maintained from the one side.
						ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + args);
					}
					ifParamNotNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
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
					ifNotSame.getThenPart().addToStatements("oldValue." + otherMap.internalRemover() + args);
					ifNotSame.getThenPart().addToStatements(map.internalRemover() + "(oldValue)");
					// add "this" to new reference\
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(
							owner.getName() + " oldOther = (" + owner.getName() + ")" + map.fieldname() + "." + otherMap.getter() + "()");
					ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(oldOther)");
					ifParamNotNull.getThenPart().addToStatements(
							new OJIfStatement("oldOther != null", "oldOther" + "." + map.internalRemover() + "(" + map.fieldname() + ")"));
					ifParamNotNull.getThenPart()
							.addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
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
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
				}
			}
			for(Property p:EmfPropertyUtil.getPropertiesQualified(map.getProperty())){
				if(isMap(p)){
					PropertyMap peerMap = ojUtil.buildStructuralFeatureMap(p.getOtherEnd());
					PropertyMap qMap = ojUtil.buildStructuralFeatureMap(p);
					OJIfStatement ifNotNull = new OJIfStatement(peerMap.getter() + "()!=null && " + map.getter() + "()!=null");
					setter.getBody().addToStatements(ifNotNull);
					ifNotNull.getThenPart().addToStatements(
							peerMap.getter() + "()." + qMap.internalAdder() + "(" + ojUtil.addQualifierArguments(p.getQualifiers(), "this") + "this)");
				}
			}
		}
		return setter;
	}
}