package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.internal.OpaqueActionMessageStructureImpl;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

public class AttributeImplementor extends StereotypeAnnotator {
	public static final String IF_OLD_VALUE_NULL = "ifParamNull";
	public static final String IF_PARAM_NOT_NULL = "ifParamNotNull";

	@VisitAfter(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class, INakedAssociationClass.class })
	public void visitFeature(INakedClassifier entity) {
		for (INakedProperty p : entity.getEffectiveAttributes()) {
			if (p.getOwner() instanceof INakedInterface && hasOJClass(entity)) {
				if (p.getAssociation() instanceof INakedAssociationClass) {
					// TODO test this may create duplicates
					// buildAssociationClassLogic(entity,
					// (INakedAssociationClass) p.getAssociation());
				} else {
					visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				}
			}
		}
	}

	@VisitBefore
	public void visitAssociationClass(INakedAssociationClass ac) {
		//
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap((INakedProperty) ac.getEnd1());
		if (map.isManyToMany()) {
			new AssocClassCreator().generateManyToMany(ac, findJavaClass(ac), findJavaClass(ac.getEnd1().getNakedBaseType()), findJavaClass(ac
					.getEnd2().getNakedBaseType()));
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		if (hasOJClass(p.getOwner())) {
			if (p.getAssociation() instanceof INakedAssociationClass) {
				// visitProperty(p.getOwner(),
				// OJUtil.buildAssociationClassMap(p,getOclEngine().getOclLibrary()));
			} else {
				visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}

	@VisitBefore(matchSubclasses = true, match = { INakedParameterNode.class, INakedOutputPin.class })
	public void visitObjectNode(INakedObjectNode node) {
		if (BehaviorUtil.hasExecutionInstance(node.getActivity())) {
			implementAttributeFully(node.getActivity(), OJUtil.buildStructuralFeatureMap(node.getActivity(), node));
		}
	}

	@VisitBefore()
	public void visitOperation(INakedOperation o) {
		if (o.shouldEmulateClass()) {
			OperationMessageStructureImpl umlOwner = new OperationMessageStructureImpl(o);
			for (INakedParameter parm : o.getOwnedParameters()) {
				implementAttributeFully(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, parm));
			}
		}
	}

	@VisitBefore()
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		OpaqueActionMessageStructureImpl umlOwner = new OpaqueActionMessageStructureImpl(oa);
		for (INakedPin pin : oa.getPins()) {
			implementAttributeFully(umlOwner, OJUtil.buildStructuralFeatureMap(umlOwner, pin));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitCallAction(INakedCallAction node) {
		if (BehaviorUtil.hasExecutionInstance(node.getActivity())
				&& (BehaviorUtil.isUserTask(node) || BehaviorUtil.hasExecutionInstance(node.getCalledElement()))) {
			implementAttributeFully(node.getActivity(), OJUtil.buildStructuralFeatureMap(node, getOclEngine().getOclLibrary()));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitParameter(INakedParameter node) {
		if (node.getOwnerElement() instanceof INakedStateMachine || node.getOwnerElement() instanceof INakedOpaqueBehavior) {
			INakedBehavior sm = (INakedBehavior) node.getOwnerElement();
			if (BehaviorUtil.hasExecutionInstance(sm)) {
				implementAttributeFully(sm, OJUtil.buildStructuralFeatureMap(sm, node));
			}
		}
	}

	private void visitProperty(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		if (!OJUtil.isBuiltIn(p)) {
			if (p.isDerived() || p.isReadOnly()) {
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				OJOperation getter = buildGetter(owner, map,true);
				applyStereotypesAsAnnotations((p), getter);
			} else {
				implementAttributeFully(umlOwner, map);
			}
		}
	}

	private void implementAttributeFully(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJAnnotatedField field = buildField(owner, map);
		if (map.isMany()) {
			buildInitExpression(owner, map, field);
			buildAdder(owner, map);
			buildAddAll(owner, map);
			buildRemover(owner, map);
			buildRemoveAll(owner, map);
			buildClear(owner, map);
		} else if (map.isOneToOne()) {
			buildInternalAdder(owner, map);
			buildInternalRemover(owner, map);
		}
		buildSetter(owner, map);
		buildGetter(owner, map, false);
		applyStereotypesAsAnnotations((p), field);
		INakedClassifier baseType = p.getNakedBaseType();
		if (baseType instanceof INakedSimpleType) {
			applySimnpleTypesAnnotations(field, baseType);
		}
	}

	public OJAnnotatedField buildField(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJAnnotatedField field = new OJAnnotatedField();
		field.setType(map.javaTypePath());
		field.setName(map.umlName());
		if (map.isJavaPrimitive() || map.isCollection()) {
			field.setInitExp(map.javaDefaultValue());
		}
		owner.addToFields(field);
		return field;
	}

	private void applySimnpleTypesAnnotations(OJAnnotatedField field, INakedClassifier baseType) {
		applyStereotypesAsAnnotations(baseType, field);
		for (INakedGeneralization g : baseType.getNakedGeneralizations()) {
			applySimnpleTypesAnnotations(field, g.getGeneral());
		}
		for (INakedInterfaceRealization g : baseType.getInterfaceRealizations()) {
			applySimnpleTypesAnnotations(field, g.getContract());
		}
	}

	private void buildInternalAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalAdder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		String remove;
		remove = "this." + map.umlName() + "=" + map.umlName();
		OJIfStatement ifEquals = new OJIfStatement(map.getter() + "()==null || !" + map.getter() + "().equals(" + map.umlName() + ")",
				remove);
		adder.getBody().addToStatements(ifEquals);
		owner.addToOperations(adder);
	}

	private void buildInternalRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalRemover());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		String remove;
		remove = "this." + map.umlName() + "=null";
		OJIfStatement ifEquals = new OJIfStatement(map.getter() + "()!=null && " + map.getter() + "().equals(" + map.umlName() + ")",
				remove);
		adder.getBody().addToStatements(ifEquals);
		owner.addToOperations(adder);
	}

	private void buildInitExpression(OJAnnotatedClass owner, NakedStructuralFeatureMap map, OJAnnotatedField field) {
		OJPathName defaultValue = map.javaDefaultTypePath();
		owner.addToImports(defaultValue);
		field.setInitExp("new " + defaultValue.getCollectionTypeName() + "()");
	}

	private OJOperation buildAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.adder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable()
				&& !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				adder.getBody().addToStatements(map.umlName() + "." + otherMap.getter() + "().add(this)");
				adder.getBody().addToStatements(map.getter() + "().add(" + map.umlName() + ")");
			} else {
				adder.getBody().addToStatements(map.umlName() + "." + otherMap.setter() + "(this)");
			}
		} else {
			adder.getBody().addToStatements(map.getter() + "().add(" + map.umlName() + ")");
		}
		owner.addToOperations(adder);
		return adder;
	}

	private OJOperation buildRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.remover());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				adder.getBody().addToStatements(map.umlName() + "." + otherMap.getter() + "().remove(this)");
				adder.getBody().addToStatements(map.getter() + "().remove(" + map.umlName() + ")");
			} else {
				adder.getBody().addToStatements(map.umlName() + "." + otherMap.setter() + "(null)");
			}
		} else {
			adder.getBody().addToStatements(map.getter() + "().remove(" + map.umlName() + ")");
		}
		owner.addToOperations(adder);
		return adder;
	}

	private OJOperation buildRemoveAll(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.removeAll());
		adder.addParam(map.umlName(), map.javaTypePath());
		adder.setStatic(map.isStatic());
		OJAnnotatedField tmpList = new OJAnnotatedField();
		tmpList.setName("tmp");
		tmpList.setType(map.javaTypePath());
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
		return adder;
	}

	private OJOperation buildClear(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.clearer());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
		owner.addToOperations(adder);
		return adder;
	}

	private OJOperation buildAddAll(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		// TODO put in NakedUmlSFMap
		adder.setName(map.allAdder());
		adder.addParam(map.umlName(), map.javaTypePath());
		adder.setStatic(map.isStatic());
		OJForStatement forAll = new OJForStatement();
		forAll.setCollection(map.umlName());
		forAll.setElemName("o");
		forAll.setElemType(map.javaBaseTypePath());
		forAll.setBody(new OJBlock());
		forAll.getBody().addToStatements(map.adder() + "(o)");
		adder.getBody().addToStatements(forAll);
		owner.addToOperations(adder);
		return adder;
	}

	protected OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		if (owner instanceof OJAnnotatedInterface) {
		} else if(returnDefault){
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		}else{
			getter.getBody().addToStatements("return " + map.umlName());
		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	protected OJOperation buildSetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName(map.setter());
		setter.addParam(map.umlName(), map.javaTypePath());
		setter.setStatic(map.isStatic());
		owner.addToOperations(setter);
		if (owner instanceof OJAnnotatedInterface) {
		} else {
			INakedProperty prop = map.getProperty();
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable()
					&& !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(prop.getOtherEnd());
				if (map.isManyToOne()) {
					buildManyToOneSetter(map, otherMap, owner, setter);
				} else if (map.isOneToMany()) {
					buildOneToManySetter(map, otherMap, owner, setter);
				} else if (map.isManyToMany()) {
					buildManyToManySetter(map, otherMap, owner, setter);
				} else if (map.isOneToOne()) {
					buildOneToOneSetter(map, otherMap, owner, setter);
				}
			} else {
				setter.getBody().addToStatements("this." + map.umlName() + "=" + map.umlName());
			}
		}
		return setter;
	}

	public void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		OJAnnotatedField oldValue = new OJAnnotatedField();
		oldValue.setName("oldValue");
		oldValue.setType(map.javaTypePath());
		oldValue.setInitExp("this." + map.umlName());
		setter.getBody().addToLocals(oldValue);
		// If oldValue==null then set the new Value unconditionally
		OJIfStatement ifNull = new OJIfStatement();
		ifNull.setName(IF_OLD_VALUE_NULL);
		ifNull.setCondition("oldValue==null");// && );
		ifNull.getThenPart().addToStatements("this." + map.umlName() + "=" + map.umlName());
		setter.getBody().addToStatements(ifNull);
		OJIfStatement ifNotSame = new OJIfStatement();
		ifNotSame.setCondition("!oldValue.equals(" + map.umlName() + ")");
		ifNull.addToElsePart(ifNotSame);
		ifNotSame.getThenPart().addToStatements("this." + map.umlName() + "=" + map.umlName());
		// remove "this" from existing reference
		ifNotSame.getThenPart().addToStatements("oldValue." + otherMap.internalRemover() + "(this)");
		// add "this" to new reference\
		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		ifParamNotNull.getThenPart().addToStatements(map.umlName() + "." + otherMap.internalAdder() + "((" + owner.getName() + ")this)");
		ifNotSame.getThenPart().addToStatements(ifParamNotNull);
		ifNull.getThenPart().addToStatements(ifParamNotNull);
	}

	public void buildManyToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		// remove from existing references and at
		OJForStatement forEach = new OJForStatement();
		forEach.setCollection("this." + map.umlName());
		forEach.setElemName("o");
		forEach.setElemType(map.javaBaseTypePath());
		OJBlock body = new OJBlock();
		forEach.setBody(body);
		body.addToStatements("o." + otherMap.getter() + "().remove((" + owner.getName() + ")this)");
		setter.getBody().addToStatements(forEach);
		OJForStatement forEachParam = new OJForStatement();
		forEachParam.setCollection(map.umlName());
		forEachParam.setElemName("o");
		forEachParam.setElemType(map.javaBaseTypePath());
		OJBlock forEachParamBody = new OJBlock();
		forEachParam.setBody(forEachParamBody);
		forEachParamBody.addToStatements("o." + otherMap.getter() + "().add((" + owner.getName() + ")this)");
		setter.getBody().addToStatements(forEachParam);
		setter.getBody().addToStatements("this." + map.umlName() + ".clear()");
		setter.getBody().addToStatements("this." + map.umlName() + ".addAll(" + map.umlName() + ")");
	}

	public void buildOneToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		// Delegate to the setter of the non-inverse end which will do all
		// the work
		OJForStatement forEachOld = new OJForStatement();
		forEachOld.setCollection("new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseType() + ">(this." + map.umlName() + ")");
		forEachOld.setElemName("o");
		forEachOld.setElemType(map.javaBaseTypePath());
		forEachOld.setBody(new OJBlock());
		forEachOld.getBody().addToStatements("o." + otherMap.setter() + "(null)");
		setter.getBody().addToStatements(forEachOld);
		OJForStatement forEachNew = new OJForStatement();
		forEachNew.setCollection(map.umlName());
		forEachNew.setElemName("o");
		forEachNew.setElemType(map.javaBaseTypePath());
		forEachNew.setBody(new OJBlock());
		forEachNew.getBody().addToStatements("o." + otherMap.setter() + "((" + owner.getName() + ")this)");
		setter.getBody().addToStatements(forEachNew);
	}

	public void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		// remove "this" from existing reference
		OJIfStatement ifNotNull = new OJIfStatement();
		ifNotNull.setCondition("this." + map.umlName() + "!=null");
		ifNotNull.getThenPart().addToStatements(
				"this." + map.umlName() + "." + otherMap.getter() + "().remove((" + owner.getName() + ")this)");
		setter.getBody().addToStatements(ifNotNull);
		// add "this" to new reference
		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setName(IF_PARAM_NOT_NULL);
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		ifParamNotNull.getThenPart().addToStatements(map.umlName() + "." + otherMap.getter() + "().add((" + owner.getName() + ")this)");
		ifParamNotNull.getThenPart().addToStatements("this." + map.umlName() + "=" + map.umlName());
		ifParamNotNull.setElsePart(new OJBlock());
		ifParamNotNull.getElsePart().addToStatements("this." + map.umlName() + "=null");
		setter.getBody().addToStatements(ifParamNotNull);
	}
}