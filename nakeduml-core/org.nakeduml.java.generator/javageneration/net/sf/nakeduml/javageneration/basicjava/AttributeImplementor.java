package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.TypeResolver;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.internal.EndToAssociationClass;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.VisibilityKind;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})
public class AttributeImplementor extends AbstractStructureVisitor{
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";
	@Override
	public void initialize(OJAnnotatedPackage javaModel,NakedUmlConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		super.initialize(javaModel, config, textWorkspace, workspace);
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		if(umlOwner instanceof INakedAssociationClass){
			OJAnnotatedClass ojOwner = findJavaClass(umlOwner);
			OJConstructor constr1 = new OJConstructor();
			INakedAssociationClass assocClass = (INakedAssociationClass) umlOwner;
			NakedStructuralFeatureMap mapToEnd1 = new NakedStructuralFeatureMap(assocClass.getPropertyToEnd1());
			NakedStructuralFeatureMap mapToEnd2 = new NakedStructuralFeatureMap(assocClass.getPropertyToEnd2());
			constr1.addParam("end1", mapToEnd1.javaTypePath());
			constr1.addParam("end2", mapToEnd2.javaTypePath());
			constr1.getBody().addToStatements("this." + mapToEnd1.setter() + "(end1)");
			constr1.getBody().addToStatements("this." + mapToEnd2.setter() + "(end2)");
			ojOwner.addToConstructors(constr1);
			OJConstructor constr2 = new OJConstructor();
			ojOwner.addToConstructors(constr2);
			constr2.addParam("end2", mapToEnd2.javaTypePath());
			constr2.addParam("end1", mapToEnd1.javaTypePath());
			constr2.getBody().addToStatements("this." + mapToEnd1.setter() + "(end1)");
			constr2.getBody().addToStatements("this." + mapToEnd2.setter() + "(end2)");
			OJAnnotatedOperation clear = new OJAnnotatedOperation("clear");
			ojOwner.addToOperations(clear);
			// TODO this object already exists - find it
			EndToAssociationClass end1ToAssocationClass = new EndToAssociationClass(((INakedAssociationClass) umlOwner).getEnd1());
			TypeResolver.resolveCollection(end1ToAssocationClass, end1ToAssocationClass.getBaseType(), getOclEngine().getOclLibrary());
			NakedStructuralFeatureMap mapFromEnd1 = new NakedStructuralFeatureMap(end1ToAssocationClass);
			EndToAssociationClass end2ToAssocationClass = new EndToAssociationClass(((INakedAssociationClass) umlOwner).getEnd2());
			TypeResolver.resolveCollection(end2ToAssocationClass, end2ToAssocationClass.getBaseType(), getOclEngine().getOclLibrary());
			NakedStructuralFeatureMap mapFromEnd2 = new NakedStructuralFeatureMap(end2ToAssocationClass);
			if(assocClass.getEnd1().isNavigable()){
				clear.getBody().addToStatements(mapToEnd1.getter() + "()." + mapFromEnd1.internalRemover() + "(this)");
			}
			if(assocClass.getEnd2().isNavigable()){
				clear.getBody().addToStatements(mapToEnd2.getter() + "()." + mapFromEnd2.internalRemover() + "(this)");
			}
		}
		// Do nothing
	}
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(INakedInterface i){
		for(INakedProperty p:i.getOwnedAttributes()){
			visitProperty(i, OJUtil.buildStructuralFeatureMap(p));
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
				OJIfStatement ifNull = new OJIfStatement(map.umlName() + "==null", map.umlName() + "=(" + map.javaBaseType()
						+ ")org.nakeduml.environment.Environment.getInstance().getComponent(" + map.javaTypePath() + ".class)");
				getter.getBody().addToStatements(ifNull);
				getter.getBody().addToStatements("return " + map.umlName());
				owner.addToImports(map.javaBaseTypePath());
			}else if(p.isDerived()){
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				OJOperation getter = buildGetter(owner, map, true);
				applyStereotypesAsAnnotations((p), getter);
			}else{
				implementAttributeFully(umlOwner, map);
			}
		}
	}
	private OJOperation buildGetter(OJAnnotatedClass owner,NakedStructuralFeatureMap map,boolean b){
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		if(!(owner instanceof OJAnnotatedInterface)){
			if(b){
				getter.getBody().addToStatements("return " + map.javaDefaultValue());
			}else{
				getter.getBody().addToStatements("return " + map.umlName());
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
		if(map.isMany()){
			OJAnnotatedClass owner = findJavaClass(c);
			// These are all the same as for normal attributes
			buildAdder(owner, map);
			buildAddAll(owner, map);
			buildRemover(owner, map);
			buildRemoveAll(owner, map);
			buildClear(owner, map);
			buildSetter(c, owner, map);
			// Here are the deviations from normal attributes
			buildInternalAdder(owner, aMap);
			buildInternalRemover(owner, aMap);
			buildGetter(owner, aMap);
		}
	}
	private void buildInternalAdder(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		NakedStructuralFeatureMap map = aMap.getMap();
		OJOperation internalAdder = new OJAnnotatedOperation(map.internalAdder());
		internalAdder.addParam(map.umlName(), map.javaBaseTypePath());
		NakedStructuralFeatureMap mapToAssClass = aMap.getEndToAssocationClassMap();
		internalAdder.setStatic(map.isStatic());
		internalAdder.getBody().addToStatements(
				getReferencePrefix(owner, map) + mapToAssClass.umlName() + ".add(new " + mapToAssClass.javaBaseType() + "(this," + map.umlName() + "))");
		owner.addToOperations(internalAdder);
	}
	protected String getReferencePrefix(OJAnnotatedClass o,NakedStructuralFeatureMap map){
		return map.isStatic() ? o.getName() + "." : "this.";
	}
	private void buildInternalRemover(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		NakedStructuralFeatureMap map = aMap.getMap();
		OJOperation internalRemover = new OJAnnotatedOperation(map.internalRemover());
		internalRemover.addParam(map.umlName(), map.javaBaseTypePath());
		internalRemover.setStatic(map.isStatic());
		if(!(owner instanceof OJAnnotatedInterface)){
			internalRemover.setStatic(map.isStatic());
			StructuralFeatureMap mapToAssClass = aMap.getEndToAssocationClassMap();
			String newOne = mapToAssClass.javaDefaultValue();
			newOne = newOne.substring(0, newOne.length() - 1);
			OJForStatement foreach = new OJForStatement("cur", mapToAssClass.javaBaseTypePath(), newOne + getReferencePrefix(owner, map) + mapToAssClass.umlName() + ")");
			internalRemover.getBody().addToStatements(foreach);
			OJIfStatement ifMatch = new OJIfStatement("cur." + aMap.getAssocationClassToEndMap().getter() + "().equals(" + map.umlName() + ")");
			foreach.getBody().addToStatements(ifMatch);
			ifMatch.getThenPart().addToStatements("cur.clear()");
			ifMatch.getThenPart().addToStatements("break");
		}
		owner.addToOperations(internalRemover);
	}
	private void buildGetter(OJAnnotatedClass owner,AssociationClassEndMap aMap){
		NakedStructuralFeatureMap map = aMap.getMap();
		OJOperation internalRemover = new OJAnnotatedOperation(map.getter(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			OJAnnotatedField result = new OJAnnotatedField("result", map.javaTypePath());
			internalRemover.setStatic(map.isStatic());
			result.setInitExp(map.javaDefaultValue());
			internalRemover.getBody().addToLocals(result);
			StructuralFeatureMap mapToAssClass = aMap.getEndToAssocationClassMap();
			OJForStatement foreach = new OJForStatement("cur", mapToAssClass.javaBaseTypePath(), getReferencePrefix(owner, map) + mapToAssClass.getter() + "()");
			internalRemover.getBody().addToStatements(foreach);
			INakedProperty otherEnd = map.getProperty().getOtherEnd();
			INakedAssociationClass assc = (INakedAssociationClass) map.getProperty().getAssociation();
			INakedProperty fromAssToOtherEnd = assc.getEnd1() == otherEnd ? assc.getPropertyToEnd1() : assc.getPropertyToEnd2();
			NakedStructuralFeatureMap mapFromAssClassToOtherEnd = new NakedStructuralFeatureMap(fromAssToOtherEnd);
			foreach.getBody().addToStatements("result.add(cur." + mapFromAssClassToOtherEnd.getter() + "())");
			internalRemover.getBody().addToStatements("return result");
		}
		owner.addToOperations(internalRemover);
	}
	private void implementAttributeFully(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
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
	private void buildInternalRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
		remover.addParam("val", map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
			if(map.isMany()){
				remover.getBody().addToStatements(getReferencePrefix(owner, map) + map.umlName() + ".remove(val)");
			}else{
				String remove = getReferencePrefix(owner, map) + map.umlName() + "=null";
				String condition = map.getter() + "()!=null && val!=null && val.equals(" + map.getter() + "())";
				OJIfStatement ifEquals = new OJIfStatement(condition, remove);
				remover.getBody().addToStatements(ifEquals);
			}
		}
		owner.addToOperations(remover);
	}
	private void buildInternalAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		adder.addParam("val", map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			if(map.isMany()){
				adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.umlName() + ".add(val)");
			}else{
				adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.umlName() + "=val");
			}
		}
		owner.addToOperations(adder);
	}
	OJAnnotatedField buildField(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedField field = new OJAnnotatedField(map.umlName(), map.javaTypePath());
		if(map.isJavaPrimitive() || map.isCollection()){
			field.setInitExp(map.javaDefaultValue());
		}
		field.setStatic(map.isStatic());
		owner.addToFields(field);
		return field;
	}
	private void applySimnpleTypesAnnotations(OJAnnotatedField field,INakedClassifier baseType){
		applyStereotypesAsAnnotations(baseType, field);
		for(INakedGeneralization g:baseType.getNakedGeneralizations()){
			applySimnpleTypesAnnotations(field, g.getGeneral());
		}
		for(INakedInterfaceRealization g:baseType.getInterfaceRealizations()){
			applySimnpleTypesAnnotations(field, g.getContract());
		}
	}
	private void buildInitExpression(OJAnnotatedClass owner,NakedStructuralFeatureMap map,OJAnnotatedField field){
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);
		field.setInitExp("new " + defaultValue.getCollectionTypeName() + "()");
	}
	private OJOperation buildAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.adder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			INakedProperty p = map.getProperty();
			adder.setVisibility(p.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.setStatic(map.isStatic());
			if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
				if(otherMap.isMany()){
					if(!OJUtil.hasOJClass((INakedClassifier) p.getAssociation())){
						adder.getBody().addToStatements(map.umlName() + "." + otherMap.internalAdder() + "(this)");
					}
					adder.getBody().addToStatements(map.internalAdder() + "(" + map.umlName() + ")");
				}else{
					OJIfStatement ifNotNul2 = new OJIfStatement(map.umlName() + "!=null");
					adder.getBody().addToStatements(ifNotNul2);
					ifNotNul2.getThenPart().addToStatements(map.umlName() + "." + otherMap.internalRemover() + "(" + map.umlName() + "." + otherMap.getter() + "())");
					ifNotNul2.getThenPart().addToStatements(map.umlName() + "." + otherMap.internalAdder() + "(this)");
					ifNotNul2.getThenPart().addToStatements(map.internalAdder() + "(" + map.umlName() + ")");
					// if(p.getBaseType() instanceof INakedInterface){
					// adder.getBody().addToStatements(map.umlName() + "." +
					// otherMap.getter() + "().add(this)");
					// }
				}
			}else{
				adder.getBody().addToStatements(map.internalAdder() + "(" + map.umlName() + ")");
			}
		}
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation remover = new OJAnnotatedOperation(map.remover());
		remover.addParam(map.umlName(), map.javaBaseTypePath());
		INakedProperty p = map.getProperty();
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
			remover.setVisibility(p.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJIfStatement ifNotNull = new OJIfStatement(map.umlName() + "!=null");
			if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
				remover.getBody().addToStatements(ifNotNull);
				if(!OJUtil.hasOJClass((INakedClassifier) map.getProperty().getAssociation())){
					ifNotNull.getThenPart().addToStatements(map.umlName() + "." + otherMap.internalRemover() + "(this)");
				}
				ifNotNull.getThenPart().addToStatements(map.internalRemover() + "(" + map.umlName() + ")");
			}else{
				ifNotNull.getThenPart().addToStatements(map.internalRemover() + "(" + map.umlName() + ")");
			}
			owner.addToOperations(remover);
		}
		return remover;
	}
	private OJOperation buildRemoveAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.removeAll());
		adder.addParam(map.umlName(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJAnnotatedField tmpList = new OJAnnotatedField("tmp", map.javaTypePath());
			tmpList.setInitExp(map.javaDefaultValue().substring(0, map.javaDefaultValue().length() - 1) + map.umlName() + ")");
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
	private OJOperation buildClear(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.clearer());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
		}
		owner.addToOperations(adder);
		return adder;
	}
	private OJOperation buildAddAll(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation(map.allAdder());
		adder.addParam(map.umlName(), map.javaTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJForStatement forAll = new OJForStatement();
			forAll.setCollection(map.umlName());
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
		setter.addParam(map.umlName(), map.javaTypePath());
		INakedProperty prop = map.getProperty();
		owner.addToOperations(setter);
		if(!(owner instanceof OJAnnotatedInterface)){
			setter.setStatic(map.isStatic());
			setter.setVisibility(prop.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			if(prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable()){
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
					ifParamNotNull.setCondition(map.umlName() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(map.umlName() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
					ifParamNotNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.umlName() + ")");
					ifParamNotNull.setElsePart(new OJBlock());
					ifParamNotNull.getElsePart().addToStatements(getReferencePrefix(owner, map) + map.internalRemover() + "(this)");
					setter.getBody().addToStatements(ifParamNotNull);
				}else if(map.isMany()){
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.umlName() + ")");
				}else if(map.isOneToOne()){
					OJAnnotatedField oldValue = new OJAnnotatedField("oldValue", map.javaTypePath());
					oldValue.setInitExp(getReferencePrefix(owner, map) + map.getter() + "()");
					setter.getBody().addToLocals(oldValue);
					// If oldValue==null then set the new Value unconditionally
					OJIfStatement ifNull = new OJIfStatement();
					ifNull.setName(AttributeImplementor.IF_OLD_VALUE_NULL);
					ifNull.setCondition("oldValue==null");// && );
					ifNull.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.umlName() + ")");
					setter.getBody().addToStatements(ifNull);
					OJIfStatement ifNotSame = new OJIfStatement();
					ifNotSame.setCondition("!oldValue.equals(" + map.umlName() + ")");
					ifNull.addToElsePart(ifNotSame);
					ifNotSame.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.umlName() + ")");
					// remove "this" from existing reference
					ifNotSame.getThenPart().addToStatements("oldValue." + otherMap.internalRemover() + "(this)");
					// add "this" to new reference\
					OJIfStatement ifParamNotNull = new OJIfStatement();
					ifParamNotNull.setCondition(map.umlName() + "!=null");
					ifParamNotNull.getThenPart().addToStatements(map.umlName() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
					ifNotSame.getThenPart().addToStatements(ifParamNotNull);
					ifNull.getThenPart().addToStatements(ifParamNotNull);
				}
			}else{
				if(map.isMany()){
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.clearer() + "()");
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.allAdder() + "(" + map.umlName() + ")");
				}else{
					setter.getBody().addToStatements(getReferencePrefix(owner, map) + map.internalAdder() + "(" + map.umlName() + ")");
				}
			}
		}
		return setter;
	}
}
