package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.TypeResolver;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedHelper;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.internal.EndToAssociationClass;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.textmetamodel.TextWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})
public class AttributeImplementor extends AbstractStructureVisitor{
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	@Override
	public void initialize(OJPackage javaModel,OpaeumConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		super.initialize(javaModel, config, textWorkspace, workspace);
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
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
			// TODO this object already exists - find it
			EndToAssociationClass end1ToAssocationClass = new EndToAssociationClass(((INakedAssociation) umlOwner).getEnd1());
			TypeResolver.resolveCollection(end1ToAssocationClass, end1ToAssocationClass.getBaseType(), getOclEngine().getOclLibrary());
			NakedStructuralFeatureMap mapFromEnd1 = new NakedStructuralFeatureMap(end1ToAssocationClass);
			EndToAssociationClass end2ToAssocationClass = new EndToAssociationClass(((INakedAssociation) umlOwner).getEnd2());
			TypeResolver.resolveCollection(end2ToAssocationClass, end2ToAssocationClass.getBaseType(), getOclEngine().getOclLibrary());
			NakedStructuralFeatureMap mapFromEnd2 = new NakedStructuralFeatureMap(end2ToAssocationClass);
			if(assocClass.getEnd1().isNavigable()){
				clear.getBody().addToStatements(mapToEnd1.getter() + "()." + mapFromEnd1.internalRemover() + "(this)");
				clear.getBody().addToStatements("this." + mapToEnd1.internalRemover() + "(" + mapToEnd1.getter() + "())");
			}
			if(assocClass.getEnd2().isNavigable()){
				clear.getBody().addToStatements(mapToEnd2.getter() + "()." + mapFromEnd2.internalRemover() + "(this)");
				clear.getBody().addToStatements("this." + mapToEnd2.internalRemover() + "(" + mapToEnd2.getter() + "())");
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
				OJIfStatement ifNull = new OJIfStatement(OJAnnotatedOperation.RESULT + "==null",OJAnnotatedOperation.RESULT+ "=" +  map.fieldname() + "=(" + map.javaBaseType() + ")" + Environment.class.getName()
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
			}else{
				getter.initializeResultVariable("this." + map.fieldname());
			}
		}
		getter.setStatic(map.isStatic());
		INakedElement property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
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
				buildAddAll(owner, map);
				buildRemover(owner, map);
				buildRemoveAll(owner, map);
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
			forEach.getBody().addToStatements(new OJIfStatement("var." + aMap.getAssocationClassToOtherEndMap().getter() + "().equals(match)", "return var"));
			getter.getBody().addToStatements("return null");
		}else{
			OJIfStatement ifEquals = new OJIfStatement(mapToAssocationClass.fieldname() + "." + aMap.getAssocationClassToOtherEndMap().getter() + "().equals(match)");
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
					"newOne." + aMap.getAssocationClassToOtherEndMap().getter() + "()." + aMap.getOtherEndToAssocationClassMap().internalAdder() + "(newOne)");
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
				OJForStatement foreach = new OJForStatement("cur", mapToAssClass.javaBaseTypePath(), newOne + getReferencePrefix(owner, map) + mapToAssClass.fieldname()
						+ ")");
				internalRemover.getBody().addToStatements(foreach);
				OJIfStatement ifMatch = new OJIfStatement("cur." + aMap.getAssocationClassToOtherEndMap().getter() + "().equals(" + map.fieldname() + ")");
				foreach.getBody().addToStatements(ifMatch);
				ifMatch.getThenPart().addToStatements("cur.clear()");
				ifMatch.getThenPart().addToStatements("break");
			}else{
				internalRemover.getBody().addToStatements(getReferencePrefix(owner, map) + mapToAssClass.fieldname() + ".clear()");
			}
		}
		owner.addToOperations(internalRemover);
	}
	protected void buildGetter(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		NakedStructuralFeatureMap map = aMap.getMap();
		OJOperation internalRemover = new OJAnnotatedOperation(map.getter(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			OJAnnotatedField result = new OJAnnotatedField("result", map.javaTypePath());
			internalRemover.setStatic(map.isStatic());
			result.setInitExp(map.javaDefaultValue());
			internalRemover.getBody().addToLocals(result);
			StructuralFeatureMap mapToAssClass = aMap.getEndToAssocationClassMap();
			INakedAssociation assc = (INakedAssociation) map.getProperty().getAssociation();
			INakedProperty otherEnd = map.getProperty().getOtherEnd();
			INakedProperty fromAssToOtherEnd = assc.getEnd1() == otherEnd ? assc.getPropertyToEnd1() : assc.getPropertyToEnd2();
			NakedStructuralFeatureMap mapFromAssClassToOtherEnd = new NakedStructuralFeatureMap(fromAssToOtherEnd);
			if(map.isMany()){
				OJForStatement foreach = new OJForStatement("cur", mapToAssClass.javaBaseTypePath(), getReferencePrefix(owner, map) + mapToAssClass.getter() + "()");
				internalRemover.getBody().addToStatements(foreach);
				foreach.getBody().addToStatements("result.add(cur." + mapFromAssClassToOtherEnd.getter() + "())");
			}else{
				result.setInitExp(getReferencePrefix(owner, map) + mapToAssClass.fieldname() + "." + aMap.getAssocationClassToOtherEndMap().getter() + "()");
			}
			internalRemover.getBody().addToStatements("return result");
		}
		owner.addToOperations(internalRemover);
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
		if(map.isMany()){
			if(field != null){
				buildInitExpression(owner, map, field);
			}
			buildAdder(owner, map);
			buildAddAll(owner, map);
			buildRemover(owner, map);
			buildRemoveAll(owner, map);
			buildClear(owner, map);
		}
		buildSetter(umlOwner, owner, map);
		buildGetter(owner, map, false);
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
		remover.addParam("val", map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
			if(map.isMany()){
				remover.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".remove(val)");
			}else{
				String remove = getReferencePrefix(owner, map) + map.fieldname() + "=null";
				String condition = map.getter() + "()!=null && val!=null && val.equals(" + map.getter() + "())";
				OJIfStatement ifEquals = new OJIfStatement(condition, remove);
				remover.getBody().addToStatements(ifEquals);
			}
		}
		owner.addToOperations(remover);
	}
	protected void buildInternalAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		adder.addParam("val", map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			if(map.isMany()){
				adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".add(val)");
			}else{
				adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + "=val");
			}
		}
		owner.addToOperations(adder);
	}
	OJAnnotatedField buildField(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaTypePath());
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
		field.setInitExp("new " + defaultValue.getCollectionTypeName() + "()");
	}
	protected OJOperation buildAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.adder());
		adder.addParam(map.fieldname(), map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			INakedProperty p = map.getProperty();
			adder.setVisibility(p.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.setStatic(map.isStatic());
			if(!(p.getOtherEnd() == null || p.getOtherEnd().isDerived()) && p.getOtherEnd().isNavigable()){
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
				if(otherMap.isMany()){
					if(!OJUtil.hasOJClass((INakedClassifier) p.getAssociation())){
						adder.getBody().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(this)");
					}
					adder.getBody().addToStatements(map.internalAdder() + "(" + map.fieldname() + ")");
				}else{
					OJIfStatement ifNotNul2 = new OJIfStatement(map.fieldname() + "!=null");
					adder.getBody().addToStatements(ifNotNul2);
					ifNotNul2.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(" + map.fieldname() + "." + otherMap.getter() + "())");
					ifNotNul2.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "(this)");
					ifNotNul2.getThenPart().addToStatements(map.internalAdder() + "(" + map.fieldname() + ")");
					// if(p.getBaseType() instanceof INakedInterface){
					// adder.getBody().addToStatements(map.fieldname() + "." +
					// otherMap.getter() + "().add(this)");
					// }
				}
			}else{
				adder.getBody().addToStatements(map.internalAdder() + "(" + map.fieldname() + ")");
			}
		}
		owner.addToOperations(adder);
		return adder;
	}
	protected OJOperation buildRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation remover = new OJAnnotatedOperation(map.remover());
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		INakedProperty p = map.getProperty();
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
			remover.setVisibility(p.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJIfStatement ifNotNull = new OJIfStatement(map.fieldname() + "!=null");
			if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
				remover.getBody().addToStatements(ifNotNull);
				if(!OJUtil.hasOJClass((INakedClassifier) map.getProperty().getAssociation())){
					ifNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(this)");
				}
				ifNotNull.getThenPart().addToStatements(map.internalRemover() + "(" + map.fieldname() + ")");
			}else{
				ifNotNull.getThenPart().addToStatements(map.internalRemover() + "(" + map.fieldname() + ")");
			}
			owner.addToOperations(remover);
		}
		return remover;
	}
	protected OJOperation buildRemoveAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.removeAll());
		adder.addParam(map.fieldname(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJAnnotatedField tmpList = new OJAnnotatedField("tmp", map.javaTypePath());
			tmpList.setInitExp(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 1) + map.fieldname() + ")");
			adder.getBody().addToLocals(tmpList);
			OJForStatement forAll = new OJForStatement();
			forAll.setCollection("tmp");
			forAll.setElemName("o");
			forAll.setElemType(map.javaBaseTypePath());
			forAll.setBody(new OJBlock());
			forAll.getBody().addToStatements(map.remover() + "(o)");
			adder.getBody().addToStatements(forAll);
			owner.addToOperations(adder);
		}
		return adder;
	}
	protected OJOperation buildClear(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.clearer());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
		}
		owner.addToOperations(adder);
		return adder;
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
			if(map.getProperty().getNakedBaseType() instanceof INakedHelper){
				setter.getBody().addToStatements("this." + map.fieldname() + "=" + map.fieldname());
			}else if(prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable()){
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(prop.getOtherEnd());
				if(map.isManyToOne()){
					// remove "this" from existing reference
					OJIfStatement ifNotNull = new OJIfStatement();
					ifNotNull.setCondition(getReferencePrefix(owner, map) + map.getter() + "()!=null");
					ifNotNull.getThenPart().addToStatements(
							getReferencePrefix(owner, map) + map.getter() + "()." + otherMap.internalRemover() + "((" + owner.getName() + ")this)");
					setter.getBody().addToStatements(ifNotNull);
					// add "this" to new reference
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
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
					ifNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					setter.getBody().addToStatements(ifNull);
					OJIfStatement ifNotSame = new OJIfStatement();
					ifNotSame.setCondition("!oldValue.equals(" + map.fieldname() + ")");
					ifNull.addToElsePart(ifNotSame);
					ifNotSame.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
					// remove "this" from existing reference
					ifNotSame.getThenPart().addToStatements("oldValue." + otherMap.internalRemover() + "(this)");
					// add "this" to new reference\
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setCondition(map.fieldname() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
					ifNotSame.getThenPart().addToStatements(ifParamNotNull);
					ifNull.getThenPart().addToStatements(ifParamNotNull);
				}
			}else{
				if(map.isMany()){
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.fieldname() + ")");
				}else{
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.fieldname() + ")");
				}
			}
		}
		return setter;
	}
}
