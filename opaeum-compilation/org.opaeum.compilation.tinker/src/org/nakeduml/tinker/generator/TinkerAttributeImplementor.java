package org.nakeduml.tinker.generator;

import java.util.Arrays;

import org.opaeum.feature.StepDependency;
import org.opaeum.generation.features.ExtendedCompositionSemantics;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.composition.ComponentInitializer;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;

@StepDependency(phase = JavaTransformationPhase.class, replaces = AttributeImplementor.class, after = { TinkerImplementNodeStep.class, ExtendedCompositionSemantics.class,
		ComponentInitializer.class })
public class TinkerAttributeImplementor extends AttributeImplementor {

	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_IF = "buildPolymorphicGetterForToOneIf";
	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_TRY = "buildPolymorphicGetterForToOneTry";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_FOR = "buildPolymorphicGetterForToManyFor";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_TRY = "buildPolymorphicGetterForToManyTry";
	public static final String TINKER_MANY_TO_MANY_SETTER_COLLECT_EDGES = "tinkerManyToManySetterCollectEdges";
	public static final String TINKER_MANY_TO_MANY_SETTER_REMOVE_EDGES = "tinkerManyToManySetterRemoveEdges";
	public static final String TINKER_MANY_TO_MANY_SETTER_FOR_ADDING = "tinkerManyToManySetterForAdding";
	public static final String TINKER_MANY_REMOVER_ITER = "tinkerManyRemoveIter";
	public static final String TINKER_MANY_REMOVER = "tinkerManyRemover";
	public static final String MANY_TO_MANY_ADDER_IF_CONTAINS = "manyToManyAdderIfContains";
	public static final String POLYMORPHIC_GETTER_FOR_TO_ONE_ITER = "polymorphicGetterForOneIter";
	public static final String POLYMORPHIC_GETTER_FOR_TO_MANY_ITER = "polymorphicGetterForToManyIter";
	public static final String POLYMORPHIC_GETTER_FOR_MANY_CLASS_FORNAME = "polymorphicGetterForManyClassForName";
	public static final String POLYMORPHIC_GETTER_FORMANY_CONSTRUCTION = "polymorphicGetterForManyConstruction";
	public static final String IFNOTNULL_EMBEDDED_MANY = "ifNotNullEmbeddedMany";
	public static final String EMBEDDED_MANY_RESULT = "embeddedManyResult";
	public static final String EMBEDDED_MANY_RESULT_IFNOTNULL = "embeddedManyResultifNotNull";
	public static final String EMBEDDED_MANY_PROPERTY_RESULT = "embeddedManyPropertyResult";
	public static final String TINKER_DB_NULL = "__NULL__";

	@Override
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
					//Only remote the elements if it needs to be unique
					if (map.getProperty().isUnique()) {
						ifNotNul2.getThenPart().addToStatements(map.fieldname() + "." + otherMap.internalRemover() + "(" + map.fieldname() + "." + otherMap.getter() + "())");
					}
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

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.nakeduml.javageneration.basicjava.AttributeImplementor#buildField
	 * (org.nakeduml.java.metamodel.annotation.OJAnnotatedClass,
	 * net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap)
	 * 
	 * Tinker uses TinkerSet and TinkerList that are initiated in constructors
	 */
	@Override
	protected OJAnnotatedField buildField(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJAnnotatedField field = super.buildField(owner, map);
		if (map.isMany()) {
			OJPathName fieldType;

			if (map.getProperty().isOrdered() && map.getProperty().isUnique()) {
				if (map.getProperty().hasQualifiers()) {
					fieldType = TinkerGenerationUtil.tinkerQualifiedOrderedSet.getCopy();
				} else {
					fieldType = TinkerGenerationUtil.tinkerOrderedSet.getCopy();
				}
			} else if (map.getProperty().isOrdered() && !map.getProperty().isUnique()) {
				if (map.getProperty().hasQualifiers()) {
					fieldType = TinkerGenerationUtil.tinkerQualifiedSequence.getCopy();
				} else {
					fieldType = TinkerGenerationUtil.tinkerSequence.getCopy();
				}
			} else if (!map.getProperty().isOrdered() && !map.getProperty().isUnique()) {
				if (map.getProperty().hasQualifiers()) {
					fieldType = TinkerGenerationUtil.tinkerQualifiedBag.getCopy();
				} else {
					fieldType = TinkerGenerationUtil.tinkerBag.getCopy();
				}
			} else if (!map.getProperty().isOrdered() && map.getProperty().isUnique()) {
				if (map.getProperty().hasQualifiers()) {
					fieldType = TinkerGenerationUtil.tinkerQualifiedSet.getCopy();
				} else {
					fieldType = TinkerGenerationUtil.tinkerSet.getCopy();
				}
			} else {
				throw new RuntimeException("wtf");
			}

			// This line removes HashSet import required for other methods, add
			// it back in
			owner.addToImports(map.javaDefaultTypePath());

			fieldType.addToElementTypes(map.javaBaseTypePath());
			field.setType(fieldType);
			field.setInitExp(null);
		}
		return field;
	}

	@Override
	protected void buildInitExpression(OJAnnotatedClass owner, NakedStructuralFeatureMap map, OJAnnotatedField field) {
		// This method call seems superfluous
	}

	@Override
	protected OJAnnotatedOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean derived) {
		owner.addToImports(TinkerGenerationUtil.vertexPathName);
		INakedProperty prop = map.getProperty();
		OJAnnotatedOperation getter;
		if (!derived) {
			if (map.isOne()) {
				getter = super.buildGetter(owner, map, derived);
				if ((prop.getBaseType() instanceof INakedEntity)
						|| (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly()))) {
					buildTinkerGetterForOne(owner, map, getter);
				} else {
					if (prop.getBaseType() instanceof INakedEnumeration) {
						buildGetterForToOneEnumeration(map, getter, prop);
					} else {
						OJIfStatement ifResultNull = new OJIfStatement("result == null");
						ifResultNull.addToThenPart("result = (" + map.javaTypePath().getLast() + ") this.vertex.getProperty(\""
								+ TinkerGenerationUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
						ifResultNull.addToThenPart("result = (result==null || result.equals(\"" + TINKER_DB_NULL + "\"))?null:result");
						getter.getBody().addToStatements(ifResultNull);
					}
				}
			} else {
				if (map.getProperty().isOrdered() && map.getProperty().isUnique()) {
					getter = buildOrderedSetGetter(owner, map, derived);
				} else if (!map.getProperty().isOrdered() && !map.getProperty().isUnique()) {
					getter = buildBagGetter(owner, map, derived);
				} else {
					getter = super.buildGetter(owner, map, derived);
				}
			}
		} else {
			getter = super.buildGetter(owner, map, derived);
		}
		return getter;
	}

	private OJAnnotatedOperation buildBagGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean derived) {
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		if (map.getProperty().hasQualifiers()) {
			getter.setReturnType(TinkerGenerationUtil.tinkerQualifiedBag.getCopy());
		} else {
			getter.setReturnType(TinkerGenerationUtil.tinkerBag.getCopy());
		}
		getter.getReturnType().addToElementTypes(map.javaBaseTypePath());
		owner.addToOperations(getter);
		if (!(owner instanceof OJAnnotatedInterface)) {
			if (derived) {
				getter.initializeResultVariable(map.javaDefaultValue());
			} else {
				getter.initializeResultVariable("this." + map.fieldname());
			}
		}
		getter.setStatic(map.isStatic());
		INakedElement property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
		return getter;
	}

	private OJAnnotatedOperation buildOrderedSetGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean derived) {
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		if (map.getProperty().hasQualifiers()) {
			getter.setReturnType(TinkerGenerationUtil.tinkerQualifiedOrderedSet.getCopy());
		} else {
			getter.setReturnType(TinkerGenerationUtil.tinkerOrderedSet.getCopy());
		}
		getter.getReturnType().addToElementTypes(map.javaBaseTypePath());
		owner.addToOperations(getter);
		if (!(owner instanceof OJAnnotatedInterface)) {
			if (derived) {
				getter.initializeResultVariable(map.javaDefaultValue());
			} else {
				getter.initializeResultVariable("this." + map.fieldname());
			}
		}
		getter.setStatic(map.isStatic());
		INakedElement property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
		return getter;
	}

	@Override
	protected void buildInternalAdder(OJAnnotatedClass owner, AssociationClassEndMap aMap) {
		// TODO Auto-generated method stub
		super.buildInternalAdder(owner, aMap);
	}

	@Override
	protected void buildInternalRemover(OJAnnotatedClass owner, AssociationClassEndMap aMap) {
		// TODO Auto-generated method stub
		super.buildInternalRemover(owner, aMap);
	}

	@Override
	protected OJOperation buildRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation remover = new OJAnnotatedOperation(map.remover());
		remover.addParam(map.fieldname(), map.javaBaseTypePath());
		INakedProperty p = map.getProperty();
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
			remover.setVisibility(p.isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			OJIfStatement ifNotNull = new OJIfStatement(map.fieldname() + "!=null");
			remover.getBody().addToStatements(ifNotNull);
			if(p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
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
	
	@Override
	protected void buildInternalRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		buildTinkerInternalRemover(owner, map);
	}

	@Override
	protected void buildInternalAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		if (map.getProperty().getOwner() instanceof INakedInterface) {
			super.buildInternalAdder(owner, map);
		} else {
			buildTinkerInternalAdder(owner, map);
		}
	}

	private void buildTinkerInternalAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		owner.addToImports(TinkerGenerationUtil.TINKER_NODE);
		INakedProperty prop = map.getProperty();
		INakedClassifier umlOwner = (INakedClassifier) map.getFeature().getOwner();
		if (map.isMany() || prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
			if (map.isOne()) {
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(prop.getOtherEnd());
				buildTinkerToOneAdder(umlOwner, map, otherMap, owner, buildBasicAdder(owner, map));
			} else {
				builTinkerManyAdder(owner, map);
			}
		} else {
			addSimpleInternalAdderBody(umlOwner, map, owner, buildBasicAdder(owner, map));
		}
	}

	private void buildTinkerInternalRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		INakedProperty prop = map.getProperty();
		INakedClassifier umlOwner = (INakedClassifier) map.getFeature().getOwner();
		if (map.isMany() || prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
			if (map.isOne()) {
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(prop.getOtherEnd());
				buildTinkerToOneRemover(umlOwner, map, otherMap, owner, buildBasicRemover(owner, map));
			} else {
				builTinkerManyRemover(owner, map);
			}
		} else {
			addSimpleInternalRemoverBody(umlOwner, map, owner, buildBasicRemover(owner, map));
		}
	}

	private void builTinkerManyAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
//		super.buildInternalAdder(owner, map);
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		adder.addParam("val", map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		OJSimpleStatement s = new OJSimpleStatement(getReferencePrefix(owner, map) + map.fieldname() + ".add(val)");
		adder.getBody().addToStatements(s);
		owner.addToOperations(adder);
		if (!(!(map.getProperty().getBaseType() instanceof INakedEnumeration) || !map.isJavaPrimitive()) && !map.getProperty().isInverse()) {
			// Do not create an edge
			s.setExpression(s.getExpression().replace(".add(", ".tinkerAdd("));
		}
		if (!map.getProperty().getQualifiers().isEmpty()) {
			s.setExpression(s.getExpression().replace("val)", "val, " + TinkerGenerationUtil.contructNameForQualifiedGetter(map) + "(val))"));
		}
	}

	private void builTinkerManyRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		super.buildInternalRemover(owner, map);
		OJOperation remover = owner.findOperation(map.internalRemover(), Arrays.asList(map.javaBaseTypePath()));
		OJSimpleStatement s = (OJSimpleStatement) remover.getBody().findStatementRecursive(AttributeImplementor.MANY_INTERNAL_REMOVE_FROM_COLLECTION);
		if (!(!(map.getProperty().getBaseType() instanceof INakedEnumeration) || !map.isJavaPrimitive()) && !map.getProperty().isInverse()) {
			// Do not create an edge
			s.setExpression(s.getExpression().replace(".remove(", ".tinkerRemove("));
		}
	}

	private OJAnnotatedOperation buildBasicAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		adder.addParam("val", map.javaBaseTypePath());
		if (!(owner instanceof OJAnnotatedInterface)) {
			adder.setStatic(map.isStatic());
		}
		owner.addToOperations(adder);
		return adder;
	}

	private OJAnnotatedOperation buildBasicRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
		remover.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		remover.addParam("val", map.javaBaseTypePath());
		if (!(owner instanceof OJAnnotatedInterface)) {
			remover.setStatic(map.isStatic());
		}
		owner.addToOperations(remover);
		return remover;
	}

	public void addSimpleInternalAdderBody(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation adder) {
		if (map.getProperty().getBaseType() instanceof INakedEntity) {
			if (map.isOne()) {
				buildTinkerToOneAdder(umlOwner, map, null, owner, adder);
			}
		} else {
			if (map.getProperty().getBaseType() instanceof INakedEnumeration) {
				adder.getBody().addToStatements(
						"this.vertex.setProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName())
								+ "\", val!=null?val.name():null)");
			} else {
				adder.getBody().addToStatements(
						"this.vertex.setProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName()) + "\", val==null?\""
								+ TINKER_DB_NULL + "\":val)");
			}
			adder.getBody().addToStatements("this." + map.umlName() + " = val");
			addEntityToTransactionThreadEntityVar(adder);
		}
	}

	public void addSimpleInternalRemoverBody(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation remover) {
		if (map.getProperty().getBaseType() instanceof INakedEntity && map.isOne()) {
			buildTinkerToOneRemover(umlOwner, map, null, owner, remover);
		}
	}

	private void addEntityToTransactionThreadEntityVar(OJOperation setter) {
		setter.getBody().addToStatements("TransactionThreadEntityVar.setNewEntity(this)");
		setter.getOwner().addToImports(TinkerGenerationUtil.transactionThreadEntityVar);
	}

	public void buildTinkerToOneAdder(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		addEntityToTransactionThreadEntityVar(setter);
		if (map.getProperty().isInverse() || map.getProperty().getOtherEnd() == null) {
			// Create an edge
			createPolymorphicToOneRelationship(umlOwner, map, setter);
		}
		setter.getBody().addToStatements(map.umlName() + " = val");
	}

	public void buildTinkerToOneRemover(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation remover) {
		//Manies gets removed in the collection
		if (map.isOneToOne() && (!map.getProperty().isInverse() || map.getProperty().getOtherEnd() == null)) {
			// Remove the edge
			removePolymorphicToOneRelationship(map, owner, remover);
		}
		String remove = getReferencePrefix(owner, map) + map.fieldname() + "=null";
		String condition = map.getter() + "()!=null && val!=null && val.equals(" + map.getter() + "())";
		OJIfStatement ifEquals = new OJIfStatement(condition, remove);
		remover.getBody().addToStatements(ifEquals);
	}

	private void removePolymorphicToOneRelationship(NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation remover) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = map.getProperty().isInverse();
		String relationshipName = TinkerGenerationUtil.getEdgeName(map);
		remover.getBody().addToStatements("Iterable<Edge> iter = this.vertex." + (isComposite ? "getOutEdges" : "getInEdges") + "(\"" + relationshipName + "\")");
		OJIfStatement ifNotNull = new OJIfStatement();
		ifNotNull.setCondition("iter.iterator().hasNext()");
		ifNotNull.getThenPart().addToStatements(TinkerGenerationUtil.graphDbAccess + ".removeEdge(iter.iterator().next())");
		remover.getBody().addToStatements(ifNotNull);
	}

	private void createPolymorphicToOneRelationship(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJOperation setter) {
		boolean isComposite = map.getProperty().isComposite();
		// isComposite = TinkerGenerationUtil.calculateDirection(map,
		// isComposite);
		isComposite = map.getProperty().isInverse();

		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
		ifParamNotNull.setCondition("val != null");
		String relationshipName = TinkerGenerationUtil.getEdgeName(map);
		ifParamNotNull.getThenPart().addToStatements(
				new OJSimpleStatement("Edge edge = "
						+ TinkerGenerationUtil.graphDbAccess
						+ ".addEdge(null, "
						+ (!isComposite ? "((" + TinkerGenerationUtil.TINKER_NODE.getLast() + ")val).getVertex(), this.vertex," : "this.vertex, (("
								+ TinkerGenerationUtil.TINKER_NODE.getLast() + ")val).getVertex(),") + "\"" + relationshipName + "\")"));
		if (isComposite) {
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"inClass\", val.getClass().getName())");
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"outClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + ")");
		} else {
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"outClass\", val.getClass().getName())");
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"inClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + ")");
		}
		setter.getBody().addToStatements(ifParamNotNull);
	}

	private void buildTinkerGetterForOne(OJAnnotatedClass owner, NakedStructuralFeatureMap map, OJAnnotatedOperation getter) {
		boolean isComposite = map.getProperty().isComposite();
		// isComposite = TinkerGenerationUtil.calculateDirection(map,
		// isComposite);
		isComposite = map.getProperty().isInverse();
		INakedClassifier otherClassifier;
		String otherClassName;
		String otherAssociationName = TinkerGenerationUtil.getEdgeName(map);
		if (map.getProperty().getOtherEnd() != null) {
			otherClassifier = map.getProperty().getOtherEnd().getOwner();
			otherClassName = otherClassifier.getMappingInfo().getJavaName().getAsIs();
		} else {
			otherClassifier = (INakedClassifier) map.getProperty().getBaseType();
			otherClassName = otherClassifier.getMappingInfo().getJavaName().getAsIs();
		}
		OJBlock block = new OJBlock();
		if (isComposite) {
			OJSimpleStatement iter = new OJSimpleStatement("Iterable<Edge> iter1 = this.vertex.getOutEdges(\"" + otherAssociationName + "\")");
			iter.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_ITER);
			block.addToStatements(iter);
		} else {
			OJSimpleStatement iter = new OJSimpleStatement("Iterable<Edge> iter1 = this.vertex.getInEdges(\"" + otherAssociationName + "\")");
			iter.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_ITER);
			block.addToStatements(iter);
		}
		OJIfStatement ifStatement = new OJIfStatement("iter1.iterator().hasNext()");
		ifStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_IF);
		ifStatement.addToThenPart("Edge edge = iter1.iterator().next()");
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);
		ojTryStatement.getTryPart().addToStatements(
				"Class<?> c = org.util.OrgJavaMetaInfoMap.INSTANCE.getClass(\"" + TinkerGenerationUtil.getClassMetaId(findJavaClass(otherClassifier)) + "\")");
		if (isComposite) {
			// ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements("result = (" + otherClassName + ") c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
		} else {
			// ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements("result = (" + otherClassName + ") c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");

		}
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ifStatement.addToThenPart(ojTryStatement);
		block.addToStatements(ifStatement);

		OJIfStatement ifResultNull = new OJIfStatement("result == null");
		ifResultNull.addToThenPart(block);
		getter.getBody().addToStatements(ifResultNull);
	}

	private static void buildGetterForToOneEnumeration(NakedStructuralFeatureMap map, OJOperation getter, INakedProperty prop) {
		getter.getBody().addToStatements(
				"String enumValue = (String)this.vertex.getProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
		OJIfStatement ifNotNull = new OJIfStatement("enumValue !=null");
		ifNotNull.addToThenPart("result = " + map.javaTypePath().getLast() + ".valueOf(enumValue)");
		getter.getBody().addToStatements(ifNotNull);
	}

	public static void buildGetterForManyEnumeration(OJAnnotatedClass owner, NakedStructuralFeatureMap map, OJOperation getter, INakedProperty prop) {
		OJField result = new OJField();
		result.setName(EMBEDDED_MANY_RESULT);
		result.setType(map.javaTypePath());
		result.setInitExp("(" + map.javaTypePath().getCollectionTypeName() + ") " + TinkerGenerationUtil.tinkerUtil + ".convertEnumsFromPersistence(this.vertex.getProperty(\""
				+ TinkerGenerationUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\"), " + map.javaBaseTypePath().getLast() + ".class, "
				+ map.getProperty().isOrdered() + " )");
		owner.addToImports(new OJPathName("java.util.Collection"));
		getter.getBody().addToLocals(result);
		getter.getBody().addToStatements("return " + EMBEDDED_MANY_RESULT);
	}

	public static void buildGetterForToManyEmbbedded(OJAnnotatedClass owner, NakedStructuralFeatureMap map, OJOperation getter, INakedProperty prop) {
		OJField result = new OJField();
		result.setName(EMBEDDED_MANY_RESULT);
		result.setType(map.javaTypePath());
		getter.getBody().addToLocals(result);

		OJField property = new OJField();
		property.setType(new OJPathName("java.lang.Object"));
		property.setInitExp("this.vertex.getProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
		property.setName(EMBEDDED_MANY_PROPERTY_RESULT);
		getter.getBody().addToLocals(property);

		OJIfStatement ifNull = new OJIfStatement(EMBEDDED_MANY_PROPERTY_RESULT + " != null");
		ifNull.setName(EMBEDDED_MANY_RESULT_IFNOTNULL);

		OJIfStatement ifList = new OJIfStatement(EMBEDDED_MANY_PROPERTY_RESULT + " instanceof List");
		ifNull.addToThenPart(ifList);

		if (map.getProperty().isOrdered()) {
			ifList.addToThenPart(result.getName() + " = " + " (List)" + EMBEDDED_MANY_PROPERTY_RESULT);
			ifList.addToElsePart(result.getName() + " = " + "Arrays.asList( (" + map.javaBaseTypePath().getLast() + "[])" + EMBEDDED_MANY_PROPERTY_RESULT + ")");
		} else {
			ifList.addToThenPart(result.getName() + " = " + "new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseTypePath().getLast() + ">((List)"
					+ EMBEDDED_MANY_PROPERTY_RESULT + ")");
			ifList.addToElsePart(result.getName() + " = " + "new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseTypePath().getLast() + ">(Arrays.asList(("
					+ map.javaBaseTypePath().getLast() + "[])" + EMBEDDED_MANY_PROPERTY_RESULT + "))");
		}

		ifNull.addToElsePart(result.getName() + " = " + map.javaDefaultValue());
		getter.getBody().addToStatements(ifNull);
		getter.getBody().addToStatements("return " + EMBEDDED_MANY_RESULT);
		owner.addToImports(new OJPathName("java.util.Arrays"));
	}

}
