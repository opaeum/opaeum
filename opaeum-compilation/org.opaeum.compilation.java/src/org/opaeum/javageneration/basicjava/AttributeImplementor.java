package org.opaeum.javageneration.basicjava;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

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
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedHelper;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.internal.EndToAssociationClass;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.emulated.EmulatingElement;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class},after = {Java6ModelGenerator.class})
public class AttributeImplementor extends AbstractStructureVisitor{
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	public static final String MANY_INTERNAL_REMOVE_FROM_COLLECTION = "manyInternalRemoveToCollection";
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public static void addPropertyMetaInfo(NakedStructuralFeatureMap map,OJAnnotatedOperation element){
		// TODO move thisS
		OJAnnotationValue ap = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.PropertyMetaInfo"));
		ap.putAttribute("isComposite", map.getProperty().isComposite());
		if(map.getProperty() instanceof EmulatingElement){
			EmulatingElement ee = (EmulatingElement) map.getProperty();
			ap.putAttribute("uuid", ee.getOriginalElement().getId());
		}else{
			ap.putAttribute("uuid", map.getProperty().getId());
		}
		ap.putAttribute("opaeumId", map.getProperty().getMappingInfo().getOpaeumId());
		if(map.getProperty().getDocumentation() != null){
			ap.putAttribute("shortDescripion", map.getProperty().getDocumentation());
		}
		if(map.getProperty().getOtherEnd() != null){
			ap.putAttribute("opposite", map.getProperty().getOtherEnd().getName());
		}
		OJAnnotationAttributeValue constraints = new OJAnnotationAttributeValue("constraints");
		ap.putAttribute(constraints);
		for(INakedConstraint c:map.getProperty().getOwner().getOwnedRules()){
			boolean isLookupConstraint = c.getConstrainedElements().contains(map.getProperty());
			if(!isLookupConstraint && map.getProperty() instanceof EmulatingElement){
				isLookupConstraint=c.getConstrainedElements().contains(((EmulatingElement) map.getProperty()).getOriginalElement());
			}
			if(c.getSpecification().isValidOclValue() && isLookupConstraint){
				if(c.getSpecification().getOclValue().getExpression().getExpressionType().isCollectionKind()){
					ap.putAttribute("lookupMethod", "get" + c.getMappingInfo().getJavaName().getCapped());
					// Lookup method
				}else{
					// Associated constraint
					OJAnnotationValue constraint = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.PropertyConstraint"));
					constraint.putAttribute("method", "is" + c.getMappingInfo().getJavaName().getCapped());
					constraint.putAttribute("message", c.getMappingInfo().getJavaName().getSeparateWords().getAsIs());
					constraints.addAnnotationValue(constraint);
				}
			}
		}
		// if(ap.findAttribute("lookupMethod") == null){
		// ap.putAttribute("lookupMethod", ((OJOperation) element).getName() + "SourcePopulation");
		// }
		element.addAnnotationIfNew(ap);
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		if(umlOwner instanceof INakedAssociation){
			OJAnnotatedClass ojOwner = findJavaClass(umlOwner);
			OJConstructor constr1 = new OJConstructor();
			INakedAssociation assocClass = (INakedAssociation) umlOwner;
			NakedStructuralFeatureMap mapToEnd1 = new NakedStructuralFeatureMap(assocClass.getPropertyToEnd1());
			NakedStructuralFeatureMap mapToEnd2 = new NakedStructuralFeatureMap(assocClass.getPropertyToEnd2());
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
			if(assocClass.getEnd1().isNavigable()){
				EndToAssociationClass end1ToAssocationClass = (EndToAssociationClass) assocClass.getPropertyToEnd1().getOtherEnd();
				NakedStructuralFeatureMap mapFromEnd1 = OJUtil.buildStructuralFeatureMap(end1ToAssocationClass);
				if(assocClass.getEnd1().isNavigable()){
					clear.getBody().addToStatements(mapToEnd1.getter() + "()." + mapFromEnd1.internalRemover() + "(this)");
					clear.getBody().addToStatements("this." + mapToEnd1.internalRemover() + "(" + mapToEnd1.getter() + "())");
				}
			}
			if(assocClass.getEnd2().isNavigable()){
				EndToAssociationClass end2ToAssocationClass = (EndToAssociationClass) assocClass.getPropertyToEnd2().getOtherEnd();// new
																																																														// EndToAssociationClass(((INakedAssociation)
																																																														// umlOwner).getEnd2());
				NakedStructuralFeatureMap mapFromEnd2 = OJUtil.buildStructuralFeatureMap(end2ToAssocationClass);
				if(assocClass.getEnd2().isNavigable()){
					clear.getBody().addToStatements(mapToEnd2.getter() + "()." + mapFromEnd2.internalRemover() + "(this)");
					clear.getBody().addToStatements("this." + mapToEnd2.internalRemover() + "(" + mapToEnd2.getter() + "())");
				}
			}
		}
		// Do nothing
	}
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(INakedInterface i){
		if(OJUtil.hasOJClass(i)){
			for(INakedProperty p:i.getOwnedAttributes()){
				visitProperty(i, OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}
	protected void visitProperty(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		if(!OJUtil.isBuiltIn(p)){
			if(p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)){
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				buildSetter(umlOwner, owner, map);
				buildField(owner, map).setTransient(true);
				OJOperation getter = buildGetter(owner, map, false);
				getter.setBody(new OJBlock());
				OJIfStatement ifNull = new OJIfStatement(OJAnnotatedOperation.RESULT + "==null", OJAnnotatedOperation.RESULT + "="
						+ map.fieldname() + "=(" + map.javaBaseType() + ")" + org.opaeum.runtime.environment.Environment.class.getName()
						+ ".getInstance().getComponent(" + map.javaTypePath() + ".class)");
				getter.getBody().addToStatements(ifNull);
				owner.addToImports(map.javaBaseTypePath());
			}else if(p.isDerived()){
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				OJAnnotatedOperation getter = buildGetter(owner, map, true);
				applyStereotypesAsAnnotations((p), getter);
			}else{
				implementAttributeFully(umlOwner, map);
			}
		}
	}
	protected OJAnnotatedOperation buildGetter(OJAnnotatedClass owner,NakedStructuralFeatureMap map,boolean derived){
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		if(!(owner instanceof OJAnnotatedInterface)){
			if(derived){
				getter.initializeResultVariable(map.javaDefaultValue());
			}else if(map.isMany() && isMap(map.getProperty())){
				getter.initializeResultVariable(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 1) + "this."
						+ map.fieldname() + ".values())");
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
		INakedElement property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
		addPropertyMetaInfo(map, getter);
		return getter;
	}
	@Override
	public void visitAssociationClassProperty(INakedClassifier c,AssociationClassEndMap aMap){
		NakedStructuralFeatureMap map = aMap.getMap();
		OJAnnotatedClass owner = findJavaClass(c);
		if(map.getProperty().isDerived()){
			buildGetter(owner, aMap);
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
			buildGetter(owner, aMap);
			buildGetterFor(owner, aMap);
		}
	}
	protected void buildGetterFor(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		NakedStructuralFeatureMap mapToAssocationClass = aMap.getEndToAssocationClassMap();
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
		NakedStructuralFeatureMap map = aMap.getMap();
		OJOperation internalAdder = new OJAnnotatedOperation(map.internalAdder());
		internalAdder.addParam(map.fieldname(), map.javaBaseTypePath());
		NakedStructuralFeatureMap mapToAssClass = aMap.getEndToAssocationClassMap();
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
	protected String getReferencePrefix(OJAnnotatedClass o,NakedStructuralFeatureMap map){
		return map.isStatic() ? o.getName() + "." : "this.";
	}
	protected void buildInternalRemover(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		NakedStructuralFeatureMap map = aMap.getMap();
		OJOperation internalRemover = new OJAnnotatedOperation(map.internalRemover());
		internalRemover.addParam(map.fieldname(), map.javaBaseTypePath());
		internalRemover.setStatic(map.isStatic());
		StructuralFeatureMap mapToAssClass = aMap.getEndToAssocationClassMap();
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
	protected void buildGetter(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		NakedStructuralFeatureMap map = aMap.getMap();
		OJOperation getter = new OJAnnotatedOperation(map.getter(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			OJAnnotatedField result = new OJAnnotatedField("result", map.javaTypePath());
			getter.setStatic(map.isStatic());
			result.setInitExp(map.javaDefaultValue());
			getter.getBody().addToLocals(result);
			StructuralFeatureMap mapToAssClass = aMap.getEndToAssocationClassMap();
			INakedAssociation assc = (INakedAssociation) map.getProperty().getAssociation();
			INakedProperty otherEnd = map.getProperty().getOtherEnd();
			INakedProperty fromAssToOtherEnd = assc.getEnd1() == otherEnd ? assc.getPropertyToEnd1() : assc.getPropertyToEnd2();
			NakedStructuralFeatureMap mapFromAssClassToOtherEnd = new NakedStructuralFeatureMap(fromAssToOtherEnd);
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
			getter.getBody().addToStatements("return result");
		}
		owner.addToOperations(getter);
	}
	protected void implementAttributeFully(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJAnnotatedField field = null;
		field = buildField(owner, map);
		buildInternalAdder(owner, map);
		if(!p.isReadOnly()){
			buildInternalRemover(owner, map);
		}
		if(p.getOtherEnd() != null && isMap(p.getOtherEnd())){
			NakedStructuralFeatureMap otherMAp = OJUtil.buildStructuralFeatureMap(p.getOtherEnd());
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
				buildRemoveAll(owner, map);
			}
			buildRemover(owner, map);
			buildClear(owner, map);
		}else{
			buildSetter(umlOwner, owner, map);
		}
		OJAnnotatedOperation getter = buildGetter(owner, map, false);
		if(field != null){
			applyStereotypesAsAnnotations((p), field);
			INakedClassifier baseType = p.getNakedBaseType();
			if(baseType instanceof INakedSimpleType){
				applySimnpleTypesAnnotations(field, baseType);
			}
		}
	}
	protected void buildInternalRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
			if(map.isMany()){
				if(isMap(map.getProperty())){
					List<INakedProperty> qualifiers = map.getProperty().getQualifiers();
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
		}
		remover.addParam("val", map.javaBaseTypePath());
		owner.addToOperations(remover);
	}
	protected void buildInternalAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			if(map.isMany()){
				if(isMap(map.getProperty())){
					List<INakedProperty> qualifiers = map.getProperty().getQualifiers();
					addQualifierParams(adder, qualifiers);
					for(INakedProperty q:qualifiers){
						// if we get here, all qualifiers are backed by properties on the baseType
						NakedStructuralFeatureMap qMap = OJUtil.buildStructuralFeatureMap(q);
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
	private void addQualifierParams(OJAnnotatedOperation adder,List<INakedProperty> qualifiers){
		OJAnnotatedField key = new OJAnnotatedField("key", new OJPathName("StringBuilder"));
		adder.getBody().addToLocals(key);
		key.setInitExp("new StringBuilder()");
		for(INakedProperty q:qualifiers){
			NakedStructuralFeatureMap qMap = OJUtil.buildStructuralFeatureMap(q);
			adder.addParam(qMap.fieldname(), qMap.javaBaseTypePath());
			if(qMap.getProperty().getNakedBaseType() instanceof INakedSimpleType){
				adder.getBody().addToStatements("key.append(" + qMap.fieldname() + ".toString())");
			}else{
				adder.getBody().addToStatements("key.append(" + qMap.fieldname() + ".getUid())");
			}
		}
	}
	protected OJAnnotatedField buildField(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
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
	protected void applySimnpleTypesAnnotations(OJAnnotatedField field,INakedClassifier baseType){
		applyStereotypesAsAnnotations(baseType, field);
		for(INakedGeneralization g:baseType.getNakedGeneralizations()){
			applySimnpleTypesAnnotations(field, g.getGeneral());
		}
	}
	protected void buildInitExpression(OJAnnotatedClass owner,NakedStructuralFeatureMap map,OJAnnotatedField field){
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);
		if(map.isMany() && isMap(map.getProperty())){
			owner.addToImports(new OJPathName("java.util.HashMap"));
			field.setInitExp("new HashMap<String," + map.javaBaseType() + ">()");
		}else{
			field.setInitExp("new " + defaultValue.getCollectionTypeName() + "()");
		}
	}
	protected OJOperation buildAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.adder());
		if(!(owner instanceof OJAnnotatedInterface)){
			INakedProperty p = map.getProperty();
			adder.setVisibility(p.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.setStatic(map.isStatic());
			String internalAddStatement = map.internalAdder() + "(" + map.fieldname() + ")";
			if(isMap(map.getProperty())){
				StringBuilder sb = addQualifierArguments(map, adder);
				internalAddStatement = map.internalAdder() + "(" + sb.toString() + map.fieldname() + ")";
			}
			if(!(p.getOtherEnd() == null || p.getOtherEnd().isDerived()) && p.getOtherEnd().isNavigable()){
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
				if(otherMap.isMany()){
					if(!OJUtil.hasOJClass((INakedClassifier) p.getAssociation())){
						if(isMap(p.getOtherEnd())){
							adder.getBody().addToStatements(
									map.fieldname() + "." + otherMap.internalAdder() + "(" + addQualifierArguments(otherMap, "this") + "this)");
						}else if(p.getAssociation() == null || !p.getAssociation().isClass()){
							adder.getBody().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(this)");
						}
					}
					adder.getBody().addToStatements(internalAddStatement);
				}else{
					OJIfStatement ifNotNul2 = new OJIfStatement(map.fieldname() + "!=null");
					adder.getBody().addToStatements(ifNotNul2);
					ifNotNul2.getThenPart().addToStatements(
							map.fieldname() + "." + otherMap.internalRemover() + "(" + map.fieldname() + "." + otherMap.getter() + "())");
					if(isMap(p.getOtherEnd())){
						ifNotNul2.getThenPart().addToStatements(
								map.fieldname() + "." + otherMap.internalAdder() + "(" + addQualifierArguments(otherMap, "this") + "this)");
					}else if(p.getAssociation() == null || !p.getAssociation().isClass()){
						ifNotNul2.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(this)");
					}
					ifNotNul2.getThenPart().addToStatements(internalAddStatement);
					// if(p.getBaseType() instanceof INakedInterface){
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
	private StringBuilder addQualifierArguments(NakedStructuralFeatureMap map,String target){
		StringBuilder result = new StringBuilder();
		for(INakedProperty p:map.getProperty().getQualifiers()){
			NakedStructuralFeatureMap qMap = OJUtil.buildStructuralFeatureMap(p);
			result.append(target);
			result.append(".");
			result.append(qMap.getter());
			result.append("(),");
		}
		return result;
	}
	private StringBuilder addQualifierArguments(NakedStructuralFeatureMap map,OJOperation adder){
		StringBuilder sb = new StringBuilder();
		for(INakedProperty q:map.getProperty().getQualifiers()){
			NakedStructuralFeatureMap qMap = OJUtil.buildStructuralFeatureMap(q);
			sb.append(qMap.fieldname());
			sb.append(',');
			adder.addParam(qMap.fieldname(), qMap.javaTypePath());
		}
		return sb;
	}
	protected OJOperation buildRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation remover = new OJAnnotatedOperation(map.remover());
		INakedProperty p = map.getProperty();
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
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
				remover.getBody().addToStatements(ifNotNull);
				if(!OJUtil.hasOJClass((INakedClassifier) map.getProperty().getAssociation())){
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
	protected OJOperation buildRemoveAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.removeAll());
		adder.addParam(map.fieldname(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			iterateAndRemove(map, adder, map.fieldname());
			owner.addToOperations(adder);
		}
		return adder;
	}
	private void iterateAndRemove(NakedStructuralFeatureMap map,OJOperation adder,String source){
		OJAnnotatedField tmpList = new OJAnnotatedField("tmp", map.javaTypePath());
		tmpList.setInitExp(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 1) + source + ")");
		adder.getBody().addToLocals(tmpList);
		OJForStatement forAll = new OJForStatement();
		forAll.setCollection("tmp");
		forAll.setElemName("o");
		forAll.setElemType(map.javaBaseTypePath());
		forAll.setBody(new OJBlock());
		if(isMap(map.getProperty())){
			forAll.getBody().addToStatements(map.remover() + "(" + OJUtil.addQualifierArguments(map.getProperty().getQualifiers(), "o") + "o)");
		}else{
			forAll.getBody().addToStatements(map.remover() + "(o)");
		}
		adder.getBody().addToStatements(forAll);
	}
	protected OJOperation buildClear(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
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
	protected OJOperation buildAddAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.allAdder());
		adder.addParam(map.fieldname(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJForStatement forAll = new OJForStatement();
			forAll.setCollection(map.fieldname());
			forAll.setElemName("o");
			forAll.setElemType(map.javaBaseTypePath());
			forAll.setBody(new OJBlock());
			forAll.getBody().addToStatements(map.adder() + "(o)");
			adder.getBody().addToStatements(forAll);
			owner.addToOperations(adder);
		}
		return adder;
	}
	protected OJOperation buildSetter(INakedClassifier umlOwner,OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation setter = new OJAnnotatedOperation(map.setter());
		setter.addParam(map.fieldname(), map.javaTypePath());
		INakedProperty prop = map.getProperty();
		owner.addToOperations(setter);
		if(!(owner instanceof OJAnnotatedInterface)){
			setter.setStatic(map.isStatic());
			setter.setVisibility(prop.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			for(INakedProperty p:map.getProperty().getPropertiesQualified()){
				if(isMap(p)){
					NakedStructuralFeatureMap peerMap = OJUtil.buildStructuralFeatureMap(p.getOtherEnd());
					NakedStructuralFeatureMap qMap = OJUtil.buildStructuralFeatureMap(p);
					OJIfStatement ifNotNull = new OJIfStatement(peerMap.getter() + "()!=null && " + map.getter() + "()!=null");
					setter.getBody().addToStatements(ifNotNull);
					ifNotNull.getThenPart().addToStatements(
							peerMap.getter() + "()." + qMap.internalRemover() + "(" + OJUtil.addQualifierArguments(p.getQualifiers(), "this") + "this)");
				}
			}
			if(map.getProperty().getNakedBaseType() instanceof INakedHelper){
				setter.getBody().addToStatements("this." + map.fieldname() + "=" + map.fieldname());
			}else if(prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable()){
				NakedStructuralFeatureMap otherMap = OJUtil.buildStructuralFeatureMap(prop.getOtherEnd());
				String args = "(this)";
				if(map.isManyToOne()){
					if(isMap(prop.getOtherEnd())){
						args = "(" + OJUtil.addQualifierArguments(prop.getOtherEnd().getQualifiers(), "this") + "this)";
					}
					// remove "this" from existing reference
					OJIfStatement ifNotNull = new OJIfStatement();
					ifNotNull.setCondition(getReferencePrefix(owner, map) + map.getter() + "()!=null");
					ifNotNull.getThenPart()
							.addToStatements(getReferencePrefix(owner, map) + map.getter() + "()." + otherMap.internalRemover() + args);
					setter.getBody().addToStatements(ifNotNull);
					// add "this" to new reference
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + args);
					ifParamNotNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					setter.getBody().addToStatements(ifParamNotNull);
				}else if(map.isMany()){
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
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
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.fieldname() + ")");
				}else{
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
				}
			}
			for(INakedProperty p:map.getProperty().getPropertiesQualified()){
				if(isMap(p)){
					NakedStructuralFeatureMap peerMap = OJUtil.buildStructuralFeatureMap(p.getOtherEnd());
					NakedStructuralFeatureMap qMap = OJUtil.buildStructuralFeatureMap(p);
					OJIfStatement ifNotNull = new OJIfStatement(peerMap.getter() + "()!=null && " + map.getter() + "()!=null");
					setter.getBody().addToStatements(ifNotNull);
					ifNotNull.getThenPart().addToStatements(
							peerMap.getter() + "()." + qMap.internalAdder() + "(" + OJUtil.addQualifierArguments(p.getQualifiers(), "this") + "this)");
				}
			}
		}
		return setter;
	}
}