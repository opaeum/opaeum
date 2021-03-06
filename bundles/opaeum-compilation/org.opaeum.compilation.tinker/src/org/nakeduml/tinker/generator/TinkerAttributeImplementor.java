package org.nakeduml.tinker.generator;

import java.util.Arrays;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
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
import org.opaeum.javageneration.basicjava.AbstractAttributeImplementer;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.basicjava.DefaultAttributeStrategy;
import org.opaeum.javageneration.composition.ComponentInitializer;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.util.OJUtil;

public class TinkerAttributeImplementor extends DefaultAttributeStrategy{
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
	public static final String ITER_HAS_NEXT = "ITER_HAS_NEXT";
	@Override
	public OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedField field = super.buildField(owner, map);
		if(map.isMany()){
			OJPathName fieldType;
			if(map.getProperty().isOrdered() && map.getProperty().isUnique()){
				if(map.getProperty().getQualifiers().size() > 0){
					fieldType = TinkerGenerationUtil.tinkerQualifiedOrderedSet.getCopy();
				}else{
					fieldType = TinkerGenerationUtil.tinkerOrderedSet.getCopy();
				}
			}else if(map.getProperty().isOrdered() && !map.getProperty().isUnique()){
				if(map.getProperty().getQualifiers().size() > 0){
					fieldType = TinkerGenerationUtil.tinkerQualifiedSequence.getCopy();
				}else{
					fieldType = TinkerGenerationUtil.tinkerSequence.getCopy();
				}
			}else if(!map.getProperty().isOrdered() && !map.getProperty().isUnique()){
				if(map.getProperty().getQualifiers().size() > 0){
					fieldType = TinkerGenerationUtil.tinkerQualifiedBag.getCopy();
				}else{
					fieldType = TinkerGenerationUtil.tinkerBag.getCopy();
				}
			}else if(!map.getProperty().isOrdered() && map.getProperty().isUnique()){
				if(map.getProperty().getQualifiers().size() > 0){
					fieldType = TinkerGenerationUtil.tinkerQualifiedSet.getCopy();
				}else{
					fieldType = TinkerGenerationUtil.tinkerSet.getCopy();
				}
			}else{
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
	public OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		owner.addToImports(TinkerGenerationUtil.vertexPathName);
		Property prop = map.getProperty();
		OJAnnotatedOperation getter;
		if(!derived){
			if(map.isOne()){
				getter = super.buildGetter(umlOwner, owner, map, derived);
				if((prop.getType() instanceof Class)
						|| (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(EmfPropertyUtil.isDerived(prop.getOtherEnd()) || prop
								.getOtherEnd().isReadOnly()))){
					buildTinkerGetterForOne(owner, map, getter);
				}else{
					if(prop.getType() instanceof Enumeration){
						buildGetterForToOneEnumeration(map, getter, prop);
					}else{
						OJIfStatement ifResultNull = new OJIfStatement("result == null");
						ifResultNull.addToThenPart("result = (" + map.javaTypePath().getLast() + ") this.vertex.getProperty(\""
								+ TinkerGenerationUtil.tinkeriseUmlName(prop.getQualifiedName()) + "\")");
						ifResultNull.addToThenPart("result = (result==null || result.equals(\"" + TinkerGenerationUtil.TINKER_DB_NULL
								+ "\"))?null:result");
						getter.getBody().addToStatements(ifResultNull);
					}
				}
			}else{
				if(map.getProperty().isOrdered() && map.getProperty().isUnique()){
					getter = buildOrderedSetGetter(owner, map, derived);
				}else if(!map.getProperty().isOrdered() && !map.getProperty().isUnique()){
					getter = buildBagGetter(owner, map, derived);
				}else{
					getter = super.buildGetter(umlOwner, owner, map, derived);
				}
			}
		}else{
			getter = super.buildGetter(umlOwner, owner, map, derived);
		}
		return getter;
	}
	private OJAnnotatedOperation buildBagGetter(OJAnnotatedClass owner,PropertyMap map,boolean derived){
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		if(map.getProperty().getQualifiers().size() > 0){
			getter.setReturnType(TinkerGenerationUtil.tinkerQualifiedBag.getCopy());
		}else{
			getter.setReturnType(TinkerGenerationUtil.tinkerBag.getCopy());
		}
		getter.getReturnType().addToElementTypes(map.javaBaseTypePath());
		owner.addToOperations(getter);
		if(!(owner instanceof OJAnnotatedInterface)){
			if(derived){
				getter.initializeResultVariable(map.javaDefaultValue());
			}else{
				getter.initializeResultVariable("this." + map.fieldname());
			}
		}
		getter.setStatic(map.isStatic());
		Element property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
		return getter;
	}
	private OJAnnotatedOperation buildOrderedSetGetter(OJAnnotatedClass owner,PropertyMap map,boolean derived){
		OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
		if(map.getProperty().getQualifiers().size() > 0){
			getter.setReturnType(TinkerGenerationUtil.tinkerQualifiedOrderedSet.getCopy());
		}else{
			getter.setReturnType(TinkerGenerationUtil.tinkerOrderedSet.getCopy());
		}
		getter.getReturnType().addToElementTypes(map.javaBaseTypePath());
		owner.addToOperations(getter);
		if(!(owner instanceof OJAnnotatedInterface)){
			if(derived){
				getter.initializeResultVariable(map.javaDefaultValue());
			}else{
				getter.initializeResultVariable("this." + map.fieldname());
			}
		}
		getter.setStatic(map.isStatic());
		Element property = map.getProperty();
		OJUtil.addMetaInfo(getter, property);
		return getter;
	}


	@Override
	public OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		Property prop = map.getProperty();
		Classifier umlOwner = (Classifier) map.getProperty().getOwner();
		OJAnnotatedOperation buildBasicRemover = buildBasicRemover(owner, map);
		if(map.isMany() || prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable()
				&& !(EmfPropertyUtil.isDerived(prop.getOtherEnd()) || prop.getOtherEnd().isReadOnly())){
			if(map.isOne()){
				PropertyMap otherMap = attributeImplementer.getOjUtil().buildStructuralFeatureMap(prop.getOtherEnd());
				buildTinkerToOneRemover(umlOwner, map, otherMap, owner, buildBasicRemover);
			}else{
				builTinkerManyRemover(owner, map);
			}
		}else{
			addSimpleInternalRemoverBody(umlOwner, map, owner, buildBasicRemover);
		}
		return buildBasicRemover;
	}
	@Override
	public OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		if(map.getProperty().getOwner() instanceof Interface){
			return super.buildInternalAdder(owner, map);
		}else{
			return buildTinkerInternalAdder(owner, map);
		}
	}
	private OJAnnotatedOperation buildTinkerInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		owner.addToImports(TinkerGenerationUtil.TINKER_NODE);
		Property prop = map.getProperty();
		Classifier umlOwner = (Classifier) map.getProperty().getOwner();
		OJAnnotatedOperation adder = buildBasicAdder(owner, map);
		if(map.isMany() || prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable()
				&& !(EmfPropertyUtil.isDerived(prop.getOtherEnd()) || prop.getOtherEnd().isReadOnly())){
			if(map.isOne()){
				PropertyMap otherMap = attributeImplementer.getOjUtil().buildStructuralFeatureMap(prop.getOtherEnd());
				buildTinkerToOneAdder(umlOwner, map, otherMap, owner, adder);
			}else{
				builTinkerManyAdder(owner, map);
			}
		}else{
			addSimpleInternalAdderBody(umlOwner, map, owner, adder);
		}
		return adder;
	}
	private void builTinkerManyAdder(OJAnnotatedClass owner,PropertyMap map){
		// super.buildInternalAdder(owner, map);
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		adder.addParam("val", map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		OJSimpleStatement s = new OJSimpleStatement(AttributeImplementor. getReferencePrefix(owner, map) + map.fieldname() + ".add(val)");
		adder.getBody().addToStatements(s);
		owner.addToOperations(adder);
		if(!(!(map.getBaseType() instanceof Enumeration) || !map.isJavaPrimitive()) && !EmfPropertyUtil.isInverse(map.getProperty())){
			// Do not create an edge
			s.setExpression(s.getExpression().replace(".add(", ".tinkerAdd("));
		}
		if(!map.getProperty().getQualifiers().isEmpty()){
			s.setExpression(s.getExpression().replace("val)", "val, " + TinkerGenerationUtil.contructNameForQualifiedGetter(map) + "(val))"));
		}
	}
	private void builTinkerManyRemover(OJAnnotatedClass owner,PropertyMap map){
		super.buildInternalRemover(owner, map);
		OJOperation remover = owner.findOperation(map.internalRemover(), Arrays.asList(map.javaBaseTypePath()));
		OJSimpleStatement s = (OJSimpleStatement) remover.getBody().findStatementRecursive(
				AttributeImplementor.MANY_INTERNAL_REMOVE_FROM_COLLECTION);
		if(!(!(map.getBaseType() instanceof Enumeration) || !map.isJavaPrimitive()) && !EmfPropertyUtil.isInverse(map.getProperty())){
			// Do not create an edge
			s.setExpression(s.getExpression().replace(".remove(", ".tinkerRemove("));
		}
	}
	private OJAnnotatedOperation buildBasicAdder(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
		adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		adder.addParam("val", map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			adder.setStatic(map.isStatic());
		}
		owner.addToOperations(adder);
		return adder;
	}
	private OJAnnotatedOperation buildBasicRemover(OJAnnotatedClass owner,PropertyMap map){
		OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
		remover.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
		remover.addParam("val", map.javaBaseTypePath());
		if(!(owner instanceof OJAnnotatedInterface)){
			remover.setStatic(map.isStatic());
		}
		owner.addToOperations(remover);
		return remover;
	}
	public void addSimpleInternalAdderBody(Classifier umlOwner,PropertyMap map,OJAnnotatedClass owner,OJOperation adder){
		if(map.getBaseType() instanceof Class){
			if(map.isOne()){
				buildTinkerToOneAdder(umlOwner, map, null, owner, adder);
			}
		}else{
			adder.getBody().addToStatements(TinkerGenerationUtil.addSetterForSimpleType(map));
			adder.getBody().addToStatements("this." + map.umlName() + " = val");
			if(EmfClassifierUtil.isCompositionParticipant(umlOwner)){
				addEntityToTransactionThreadEntityVar(adder);
			}
		}
	}
	public void addSimpleInternalRemoverBody(Classifier umlOwner,PropertyMap map,OJAnnotatedClass owner,OJOperation remover){
		if(map.getBaseType() instanceof Class && map.isOne()){
			buildTinkerToOneRemover(umlOwner, map, null, owner, remover);
		}
	}
	private void addEntityToTransactionThreadEntityVar(OJOperation setter){
		setter.getBody().addToStatements("TransactionThreadEntityVar.setNewEntity(this)");
		setter.getOwner().addToImports(TinkerGenerationUtil.transactionThreadEntityVar);
	}
	public void buildTinkerToOneAdder(Classifier umlOwner,PropertyMap map,PropertyMap otherMap,OJAnnotatedClass owner,OJOperation setter){
		if(EmfClassifierUtil.isCompositionParticipant(umlOwner)){
			addEntityToTransactionThreadEntityVar(setter);
		}
		if(EmfPropertyUtil.isInverse(map.getProperty()) || map.getProperty().getOtherEnd() == null){
			// Create an edge
			if(map.getProperty().getOtherEnd() != null){
				OJField oldValue = new OJAnnotatedField("oldValue", map.javaBaseTypePath());
				oldValue.setInitExp(map.getter() + "()");
				setter.getBody().addToLocals(oldValue);
				setter.getBody().addToStatements(map.internalRemover() + "(oldValue)");
				OJIfStatement ifOldValueNotNull = new OJIfStatement("oldValue != null");
				ifOldValueNotNull.addToThenPart("oldValue." + otherMap.internalRemover() + "(this)");
				setter.getBody().addToStatements(ifOldValueNotNull);
			}
			createPolymorphicToOneRelationship(umlOwner, map, setter);
		}
		setter.getBody().addToStatements(map.umlName() + " = val");
	}
	public void buildTinkerToOneRemover(Classifier umlOwner,PropertyMap map,PropertyMap otherMap,OJAnnotatedClass owner,OJOperation remover){
		if(EmfClassifierUtil.isCompositionParticipant(umlOwner)){
			addEntityToTransactionThreadEntityVar(remover);
		}
		// Manies gets removed in the collection
		if(map.isOneToOne() && (!EmfPropertyUtil.isInverse(map.getProperty()) || map.getProperty().getOtherEnd() == null)){
			// Remove the edge
			removePolymorphicToOneRelationship(map, owner, remover);
		}
		String remove = AbstractAttributeImplementer.getReferencePrefix(owner, map) + map.fieldname() + "=null";
		String condition = map.getter() + "()!=null && val!=null && val.equals(" + map.getter() + "())";
		OJIfStatement ifEquals = new OJIfStatement(condition, remove);
		remover.getBody().addToStatements(ifEquals);
	}
	private void removePolymorphicToOneRelationship(PropertyMap map,OJAnnotatedClass owner,OJOperation remover){
		boolean isComposite = map.getProperty().isComposite();
		isComposite = EmfPropertyUtil.isInverse(map.getProperty());
		String relationshipName = TinkerGenerationUtil.getEdgeName(map);
		remover.getBody().addToStatements(
				"Iterable<Edge> iter = this.vertex." + (isComposite ? "getOutEdges" : "getInEdges") + "(\"" + relationshipName + "\")");
		OJIfStatement ifNotNull = new OJIfStatement();
		ifNotNull.setName(ITER_HAS_NEXT);
		ifNotNull.setCondition("iter.iterator().hasNext()");
		ifNotNull.getThenPart().addToStatements(TinkerGenerationUtil.graphDbAccess + ".removeEdge(iter.iterator().next())");
		remover.getBody().addToStatements(ifNotNull);
	}
	private void createPolymorphicToOneRelationship(Classifier umlOwner,PropertyMap map,OJOperation setter){
		boolean isComposite = map.getProperty().isComposite();
		// isComposite = TinkerGenerationUtil.calculateDirection(map,
		// isComposite);
		isComposite = EmfPropertyUtil.isInverse(map.getProperty());
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
		if(isComposite){
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"inClass\", val.getClass().getName())");
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"outClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + ")");
		}else{
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"outClass\", val.getClass().getName())");
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"inClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + ")");
		}
		setter.getBody().addToStatements(ifParamNotNull);
	}
	private void buildTinkerGetterForOne(OJAnnotatedClass owner,PropertyMap map,OJAnnotatedOperation getter){
		boolean isComposite = map.getProperty().isComposite();
		isComposite = EmfPropertyUtil.isInverse(map.getProperty());
		Classifier otherClassifier;
		String otherClassName;
		String otherAssociationName = TinkerGenerationUtil.getEdgeName(map);
		otherClassifier = (Classifier) map.getBaseType();
		otherClassName = otherClassifier.getName();
		OJBlock block = new OJBlock();
		if(isComposite){
			OJSimpleStatement iter = new OJSimpleStatement("Iterable<Edge> iter1 = this.vertex.getOutEdges(\"" + otherAssociationName + "\")");
			iter.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_ITER);
			block.addToStatements(iter);
		}else{
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
				"Class<?> c = org.util.OrgJavaMetaInfoMap.INSTANCE.getClass(\""
						+ TinkerGenerationUtil.getClassMetaId(attributeImplementer.findJavaClass(otherClassifier)) + "\")");
		if(isComposite){
			ojTryStatement.getTryPart().addToStatements(
					"this." + map.fieldname() + " = (" + otherClassName + ") c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
		}else{
			ojTryStatement.getTryPart().addToStatements(
					"this." + map.fieldname() + " = (" + otherClassName + ") c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
		}
		ojTryStatement.getTryPart().addToStatements("result = this." + map.fieldname());
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ifStatement.addToThenPart(ojTryStatement);
		block.addToStatements(ifStatement);
		OJIfStatement ifResultNull = new OJIfStatement("result == null");
		ifResultNull.addToThenPart(block);
		getter.getBody().addToStatements(ifResultNull);
	}
	public static void buildGetterForToOneEnumeration(PropertyMap map,OJOperation getter,Property prop){
		getter.getBody().addToStatements(
				"String enumValue = (String)this.vertex.getProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(prop.getQualifiedName()) + "\")");
		OJIfStatement ifNotNull = new OJIfStatement("enumValue !=null");
		ifNotNull.addToThenPart("result = " + map.javaTypePath().getLast() + ".valueOf(enumValue)");
		getter.getBody().addToStatements(ifNotNull);
	}
	public static void buildGetterForManyEnumeration(OJAnnotatedClass owner,PropertyMap map,OJOperation getter,Property prop){
		OJField result = new OJAnnotatedField(EMBEDDED_MANY_RESULT, map.javaTypePath());
		result.setInitExp("(" + map.javaTypePath().getTypeNameWithTypeArguments() + ") " + TinkerGenerationUtil.tinkerUtil
				+ ".convertEnumsFromPersistence(this.vertex.getProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(prop.getQualifiedName())
				+ "\"), " + map.javaBaseTypePath().getLast() + ".class, " + map.getProperty().isOrdered() + " )");
		owner.addToImports(new OJPathName("java.util.Collection"));
		getter.getBody().addToLocals(result);
		getter.getBody().addToStatements("return " + EMBEDDED_MANY_RESULT);
	}
	public static void buildGetterForToManyEmbbedded(OJAnnotatedClass owner,PropertyMap map,OJOperation getter,Property prop){
		OJField result = new OJAnnotatedField(EMBEDDED_MANY_RESULT, map.javaTypePath());
		getter.getBody().addToLocals(result);
		OJField property = new OJAnnotatedField(EMBEDDED_MANY_PROPERTY_RESULT, new OJPathName("java.lang.Object"));
		property.setInitExp("this.vertex.getProperty(\"" + TinkerGenerationUtil.tinkeriseUmlName(prop.getQualifiedName()) + "\")");
		getter.getBody().addToLocals(property);
		OJIfStatement ifNull = new OJIfStatement(EMBEDDED_MANY_PROPERTY_RESULT + " != null");
		ifNull.setName(EMBEDDED_MANY_RESULT_IFNOTNULL);
		OJIfStatement ifList = new OJIfStatement(EMBEDDED_MANY_PROPERTY_RESULT + " instanceof List");
		ifNull.addToThenPart(ifList);
		if(map.getProperty().isOrdered()){
			ifList.addToThenPart(result.getName() + " = " + " (List)" + EMBEDDED_MANY_PROPERTY_RESULT);
			ifList.addToElsePart(result.getName() + " = " + "Arrays.asList( (" + map.javaBaseTypePath().getLast() + "[])"
					+ EMBEDDED_MANY_PROPERTY_RESULT + ")");
		}else{
			ifList.addToThenPart(result.getName() + " = " + "new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseTypePath().getLast()
					+ ">((List)" + EMBEDDED_MANY_PROPERTY_RESULT + ")");
			ifList.addToElsePart(result.getName() + " = " + "new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseTypePath().getLast()
					+ ">(Arrays.asList((" + map.javaBaseTypePath().getLast() + "[])" + EMBEDDED_MANY_PROPERTY_RESULT + "))");
		}
		ifNull.addToElsePart(result.getName() + " = " + map.javaDefaultValue());
		getter.getBody().addToStatements(ifNull);
		getter.getBody().addToStatements("return " + EMBEDDED_MANY_RESULT);
		owner.addToImports(new OJPathName("java.util.Arrays"));
	}
}
