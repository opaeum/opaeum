package net.sf.nakeduml.javageneration.basicjava.tinker;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementor;
import net.sf.nakeduml.javageneration.basicjava.AttributeImplementorStrategy;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class TinkerAttributeImplementorStrategy implements AttributeImplementorStrategy {

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
	public static OJPathName TINKER_NODE = new OJPathName("org.nakeduml.runtime.domain.TinkerNode");

	public TinkerAttributeImplementorStrategy() {
		super();
	}

	@Override
	public void addSimpleSetterBody(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter) {
		if (map.getProperty().getBaseType() instanceof INakedEntity) {
			if (map.isOne()) {
				buildManyToOneSetter(umlOwner, map, null, owner, setter);
			} else if (map.isMany()) {
				buildManyToManySetter(map, null, owner, setter);
			} else {
			}
		} else {
			if (map.isMany() && map.getProperty().getBaseType() instanceof INakedEnumeration) {
				setter.getBody().addToStatements(
						"this.vertex.setProperty(\"" + TinkerUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName()) + "\", "
								+ TinkerUtil.tinkerUtil + ".convertEnumsForPersistence(" + map.umlName() + "))");
			} else {
				setter.getBody().addToStatements(
						"this.vertex.setProperty(\"" + TinkerUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName()) + "\", "
								+ map.umlName() + ")");
			}
			addEntityToTransactionThreadEntityVar(setter);
		}
	}

	@Override
	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		if (owner instanceof OJAnnotatedInterface) {
		} else if (returnDefault) {
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		} else {
			INakedProperty prop = map.getProperty();
			owner.addToImports(TinkerUtil.edgePathName);
			owner.addToImports(TinkerUtil.vertexPathName);
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				if (map.isManyToOne()) {
					buildPolymorphicGetterForToOne(map, getter);
				} else if (map.isOneToMany()) {
					buildPolymorphicGetterForMany(map, getter);
				} else if (map.isManyToMany()) {
					buildPolymorphicGetterForMany(map, getter);
				} else if (map.isOneToOne()) {
					buildPolymorphicGetterForToOne(map, getter);
				}
			} else {
				if (prop.getBaseType() instanceof INakedEntity) {
					if (map.isOne()) {
						buildPolymorphicGetterForToOne(map, getter);
					} else if (map.isMany()) {
						buildPolymorphicGetterForMany(map, getter);
					} else {
					}
				} else if (prop.getBaseType() instanceof INakedEnumeration) {
					if (map.isOne()) {
						getter.getBody().addToStatements(
								"String enumValue = (String)this.vertex.getProperty(\""
										+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
						OJIfStatement ifNotNull = new OJIfStatement("enumValue !=null");
						ifNotNull.addToThenPart("return " + map.javaTypePath().getLast() + ".valueOf(enumValue)");
						ifNotNull.addToElsePart("return null");
						getter.getBody().addToStatements(ifNotNull);
					} else {
						OJField result = new OJField();
						result.setName(EMBEDDED_MANY_RESULT);
						result.setType(map.javaTypePath());
						result.setInitExp("(" + map.javaTypePath().getCollectionTypeName() + ") " + TinkerUtil.tinkerUtil + ".convertEnumsFromPersistence((Collection<String>)" + "this.vertex.getProperty(\""
								+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\"), "+map.javaBaseTypePath().getLast()+".class, "+map.getProperty().isOrdered()+" )");
						owner.addToImports(new OJPathName("java.util.Collection"));
						getter.getBody().addToLocals(result);
						OJIfStatement ifNull = new OJIfStatement(EMBEDDED_MANY_RESULT + " != null");
						ifNull.setName(IFNOTNULL_EMBEDDED_MANY);
						ifNull.addToThenPart("return " + EMBEDDED_MANY_RESULT);
						ifNull.addToElsePart("return " + map.javaDefaultValue());
						getter.getBody().addToStatements(ifNull);
					}
				} else {
					if (map.isOne()) {
						getter.getBody().addToStatements(
								"return (" + map.javaTypePath().getLast() + ") this.vertex.getProperty(\""
										+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
					} else {
						OJField result = new OJField();
						result.setName(EMBEDDED_MANY_RESULT);
						result.setType(map.javaTypePath());
						result.setInitExp("(" + map.javaTypePath().getCollectionTypeName() + ") this.vertex.getProperty(\""
								+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
						getter.getBody().addToLocals(result);
						OJIfStatement ifNull = new OJIfStatement(EMBEDDED_MANY_RESULT + " != null");
						ifNull.setName(IFNOTNULL_EMBEDDED_MANY);
						ifNull.addToThenPart("return " + EMBEDDED_MANY_RESULT);
						ifNull.addToElsePart("return " + map.javaDefaultValue());
						getter.getBody().addToStatements(ifNull);
					}
				}
			}

		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	public void buildPolymorphicGetterForToOne(NakedStructuralFeatureMap map, OJOperation getter) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);
		INakedClassifier otherClassifier;
		String otherClassName;
		String otherAssociationName = TinkerUtil.getEdgeName(map);
		if (map.getProperty().getOtherEnd() != null) {
			otherClassifier = map.getProperty().getOtherEnd().getOwner();
			otherClassName = otherClassifier.getMappingInfo().getJavaName().getAsIs();
		} else {
			otherClassifier = (INakedClassifier) map.getProperty().getBaseType();
			otherClassName = otherClassifier.getMappingInfo().getJavaName().getAsIs();
		}
		if (isComposite) {
			OJSimpleStatement iter = new OJSimpleStatement("Iterable<Edge> iter1 = this.vertex.getOutEdges(\"" + otherAssociationName + "\")");
			iter.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_ITER);
			getter.getBody().addToStatements(iter);
		} else {
			OJSimpleStatement iter = new OJSimpleStatement("Iterable<Edge> iter1 = this.vertex.getInEdges(\"" + otherAssociationName + "\")");
			iter.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_ITER);
			getter.getBody().addToStatements(iter);
		}
		OJIfStatement ifStatement = new OJIfStatement("iter1.iterator().hasNext()");
		ifStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_IF);
		ifStatement.addToThenPart("Edge edge = iter1.iterator().next()");
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements("return (" + otherClassName + ") c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
		} else {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements("return (" + otherClassName + ") c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");

		}
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ifStatement.addToThenPart(ojTryStatement);
		getter.getBody().addToStatements(ifStatement);
		getter.getBody().addToStatements("return null");
	}

	private void buildPolymorphicGetterForMany(NakedStructuralFeatureMap map, OJOperation getter) {
		OJField result = new OJField();
		result.setType(map.javaTypePath());
		result.setName("result");
		result.setInitExp(map.javaDefaultValue());
		OJPathName defaultValue = map.javaDefaultTypePath();
		getter.getOwner().addToImports(defaultValue);

		getter.getBody().addToLocals(result);
		INakedClassifier manyClassifier;
		String associationName = TinkerUtil.getEdgeName(map);
		boolean isComposite;
		if (map.getProperty().getOtherEnd() != null) {
			manyClassifier = map.getProperty().getOtherEnd().getOwner();
			isComposite = map.getProperty().isComposite();
			isComposite = TinkerUtil.calculateDirection(map, isComposite);
		} else {
			manyClassifier = (INakedClassifier) map.getProperty().getBaseType();
			isComposite = true;
		}

		OJSimpleStatement simpleStatement = new OJSimpleStatement();
		simpleStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_ITER);
		if (isComposite) {
			simpleStatement.setExpression("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + associationName + "\")");
		} else {
			simpleStatement.setExpression("Iterable<Edge> iter = this.vertex.getInEdges(\"" + associationName + "\")");
		}
		getter.getBody().addToStatements(simpleStatement);
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		forStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_FOR);
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_TRY);
		forStatement.getBody().addToStatements(ojTryStatement);
		if (isComposite) {
			OJSimpleStatement classForNameSimpleStatement = new OJSimpleStatement("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			classForNameSimpleStatement.setName(POLYMORPHIC_GETTER_FOR_MANY_CLASS_FORNAME);
			ojTryStatement.getTryPart().addToStatements(classForNameSimpleStatement);
			OJSimpleStatement construction = new OJSimpleStatement("result.add((" + manyClassifier.getMappingInfo().getJavaName().getAsIs()
					+ ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex()))");
			construction.setName(POLYMORPHIC_GETTER_FORMANY_CONSTRUCTION);
			ojTryStatement.getTryPart().addToStatements(construction);
		} else {
			OJSimpleStatement classForNameSimpleStatement = new OJSimpleStatement("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			classForNameSimpleStatement.setName(POLYMORPHIC_GETTER_FOR_MANY_CLASS_FORNAME);
			ojTryStatement.getTryPart().addToStatements(classForNameSimpleStatement);
			OJSimpleStatement construction = new OJSimpleStatement("result.add((" + manyClassifier.getMappingInfo().getJavaName().getAsIs()
					+ ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex()))");
			construction.setName(POLYMORPHIC_GETTER_FORMANY_CONSTRUCTION);
			ojTryStatement.getTryPart().addToStatements(construction);
		}

		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");

		getter.getBody().addToStatements(forStatement);
		getter.getBody().addToStatements("return result");
	}

	@Override
	public void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		// Used by hibernate strategy
	}

	@Override
	public void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		// Used by hibernate strategy
	}

	@Override
	public void buildManyToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		addEntityToTransactionThreadEntityVar(setter);
		removePolymorphicToOneRelationship(map, owner, setter);
		createPolymorphicToOneRelationship(umlOwner, map, setter);
	}

	@Override
	public void buildOneToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		addEntityToTransactionThreadEntityVar(setter);
		removePolymorphicToOneRelationship(map, owner, setter);
		removeInverseToPolymorphicRelationship(map, otherMap, owner, setter);
		createPolymorphicToOneRelationship(umlOwner, map, setter);
	}

	private void createPolymorphicToOneRelationship(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJOperation setter) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);

		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		String relationshipName = TinkerUtil.getEdgeName(map);
		ifParamNotNull.getThenPart().addToStatements(
				new OJSimpleStatement("Edge edge = "
						+ UtilityCreator.getUtilPathName().toJavaString()
						+ ".GraphDb.getDB().addEdge(null, "
						+ (!isComposite ? "((" + TINKER_NODE.getLast() + ")" + map.umlName() + ").getVertex(), this.vertex," : "this.vertex, (("
								+ TINKER_NODE.getLast() + ")" + map.umlName() + ").getVertex(),") + "\"" + relationshipName + "\")"));
		if (isComposite) {
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"inClass\", " + map.umlName() + ".getClass().getName())");
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"outClass\", this.getClass().getName())");
		} else {
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"outClass\", " + map.umlName() + ".getClass().getName())");
			ifParamNotNull.getThenPart().addToStatements("edge.setProperty(\"inClass\", this.getClass().getName())");
		}
		setter.getBody().addToStatements(ifParamNotNull);
	}

	private void removePolymorphicToOneRelationship(NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);
		String relationshipName = TinkerUtil.getEdgeName(map);
		setter.getBody()
				.addToStatements("Iterable<Edge> iter = this.vertex." + (isComposite ? "getOutEdges" : "getInEdges") + "(\"" + relationshipName + "\")");
		OJIfStatement ifNotNull = new OJIfStatement();
		ifNotNull.setCondition("iter.iterator().hasNext()");
		ifNotNull.getThenPart().addToStatements(UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().removeEdge(iter.iterator().next())");
		setter.getBody().addToStatements(ifNotNull);
	}

	private void removeInverseToPolymorphicRelationship(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		setter.getBody().addToStatements(
				new OJIfStatement(map.umlName() + "!=null && " + map.umlName() + "." + otherMap.getter() + "()!=null", map.umlName() + "." + otherMap.getter()
						+ "()." + map.setter() + "(null)"));
	}

	@Override
	public void buildOneToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		OJForStatement forEachOld = new OJForStatement();
		forEachOld.setCollection("new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseType() + ">(" + map.getter() + "())");
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

	@Override
	public void buildManyToManySetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		addEntityToTransactionThreadEntityVar(setter);
		boolean isComposite;
		if (otherMap != null) {
			isComposite = map.getProperty().isComposite();
			isComposite = TinkerUtil.calculateDirection(map, isComposite);
		} else {
			isComposite = true;
		}
		owner.addToImports(new OJPathName("java.util.List"));
		owner.addToImports(new OJPathName("java.util.ArrayList"));
		setter.getBody().addToStatements("List<Edge> edgesToRemove = new ArrayList<Edge>()");
		String relationshipName = TinkerUtil.getEdgeName(map);
		setter.getBody()
				.addToStatements("Iterable<Edge> iter = this.vertex." + (isComposite ? "getOutEdges" : "getInEdges") + "(\"" + relationshipName + "\")");
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		forStatement.setName(TINKER_MANY_TO_MANY_SETTER_COLLECT_EDGES);
		forStatement.getBody().addToStatements("edgesToRemove.add(edge)");
		setter.getBody().addToStatements(forStatement);

		OJForStatement removeFor = new OJForStatement("edge", TinkerUtil.edgePathName, "edgesToRemove");
		removeFor.setName(TINKER_MANY_TO_MANY_SETTER_REMOVE_EDGES);
		removeFor.getBody().addToStatements(UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().removeEdge(edge)");
		setter.getBody().addToStatements(removeFor);

		OJIfStatement ifNotNull = new OJIfStatement(map.umlName() + " != null");
		OJForStatement forAdding = new OJForStatement("o", map.javaBaseTypePath(), map.umlName());
		forAdding.setName(TINKER_MANY_TO_MANY_SETTER_FOR_ADDING);
		forAdding.getBody().addToStatements(map.adder() + "(o)");
		ifNotNull.addToThenPart(forAdding);
		setter.getBody().addToStatements(ifNotNull);
	}

	@Override
	public void buildManyAdder(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);
		OJIfStatement ifStatement = new OJIfStatement("!" + map.getter() + "().contains(" + map.umlName() + ")");
		ifStatement.setName(MANY_TO_MANY_ADDER_IF_CONTAINS);
		adder.getBody().addToStatements(ifStatement);
		String relationshipName = TinkerUtil.getEdgeName(map);
		ifStatement.addToThenPart(new OJSimpleStatement("Edge edge = "
				+ UtilityCreator.getUtilPathName().toJavaString()
				+ ".GraphDb.getDB().addEdge(null, "
				+ (!isComposite ? "((" + TINKER_NODE.getLast() + ")" + map.umlName() + ").getVertex(), this.vertex," : "this.vertex, (("
						+ TINKER_NODE.getLast() + ")" + map.umlName() + ").getVertex(),") + "\"" + relationshipName + "\")"));
		if (isComposite) {
			ifStatement.addToThenPart("edge.setProperty(\"inClass\", " + map.umlName() + ".getClass().getName())");
			ifStatement.addToThenPart("edge.setProperty(\"outClass\", this.getClass().getName())");
		} else {
			ifStatement.addToThenPart("edge.setProperty(\"outClass\", " + map.umlName() + ".getClass().getName())");
			ifStatement.addToThenPart("edge.setProperty(\"inClass\", this.getClass().getName())");
		}
	}

	private void buildManyEmbeddedAdder(NakedStructuralFeatureMap map, Object object, OJOperation adder) {
		OJField tmp = new OJField();
		tmp.setName("tmp");
		tmp.setType(map.javaTypePath());
		tmp.setInitExp(map.getter() + "()");
		OJIfStatement ifStatement = new OJIfStatement("!tmp.contains(" + map.umlName() + ")");
		ifStatement.setName(MANY_TO_MANY_ADDER_IF_CONTAINS);
		adder.getBody().addToStatements(ifStatement);
		adder.getBody().addToLocals(tmp);
		ifStatement.addToThenPart("tmp.add(" + map.umlName() + ")");
		ifStatement.addToThenPart(map.setter() + "(tmp)");
	}

	@Override
	public void buildManyRemover(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation remover) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);
		remover.getBody().addToStatements("List<Edge> edgesToRemove = new ArrayList<Edge>()");
		String relationshipName = TinkerUtil.getEdgeName(map);
		remover.getBody().addToStatements(
				"Iterable<Edge> iter = this.vertex." + (isComposite ? "getOutEdges" : "getInEdges") + "(\"" + relationshipName + "\")");
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		forStatement.setName(TINKER_MANY_REMOVER_ITER);
		OJIfStatement ifStatement;
		if (isComposite) {
			ifStatement = new OJIfStatement("edge.getInVertex().getId().equals(((" + TINKER_NODE.getLast() + ")" + map.umlName() + ").getVertex().getId())");
		} else {
			ifStatement = new OJIfStatement("edge.getOutVertex().getId().equals(((" + TINKER_NODE.getLast() + ")" + map.umlName() + ").getVertex().getId())");
		}
		forStatement.getBody().addToStatements(ifStatement);
		ifStatement.addToThenPart("edgesToRemove.add(edge)");
		remover.getBody().addToStatements(forStatement);

		OJForStatement removeFor = new OJForStatement("edge", TinkerUtil.edgePathName, "edgesToRemove");
		removeFor.setName(TINKER_MANY_REMOVER);
		removeFor.getBody().addToStatements(UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().removeEdge(edge)");
		remover.getBody().addToStatements(removeFor);
	}

	private void addEntityToTransactionThreadEntityVar(OJOperation setter) {
		setter.getBody().addToStatements("TransactionThreadEntityVar.setNewEntity(this)");
		setter.getOwner().addToImports(TinkerUtil.transactionThreadEntityVar);
	}

	@Override
	public void addSimpleAdder(NakedStructuralFeatureMap map, OJOperation adder) {
		if (map.isMany()) {
			if (map.getProperty().getBaseType() instanceof INakedEntity) {
				buildManyAdder(map, null, adder);
			} else {
				buildManyEmbeddedAdder(map, null, adder);
			}
		} else {
			adder.getBody().addToStatements(map.getter() + "().add(" + map.umlName() + ")");
		}
	}

	@Override
	public void buildSimpleRemover(NakedStructuralFeatureMap map, OJOperation remover) {
		if (map.isMany()) {
			if (map.getProperty().getBaseType() instanceof INakedEntity) {
				buildManyRemover(map, null, remover);
			} else {
				buildManyEmbeddedRemover(map, null, remover);
			}
		} else {
			remover.getBody().addToStatements(map.getter() + "().remove(" + map.umlName() + ")");
		}
	}

	private void buildManyEmbeddedRemover(NakedStructuralFeatureMap map, Object object, OJOperation remover) {
		OJField tmp = new OJField();
		tmp.setName("tmp");
		tmp.setType(map.javaTypePath());
		tmp.setInitExp(map.getter() + "()");
		OJIfStatement ifStatement = new OJIfStatement("tmp.contains(" + map.umlName() + ")");
		ifStatement.setName(MANY_TO_MANY_ADDER_IF_CONTAINS);
		remover.getBody().addToStatements(ifStatement);
		remover.getBody().addToLocals(tmp);
		ifStatement.addToThenPart("tmp.remove(" + map.umlName() + ")");
		ifStatement.addToThenPart(map.setter() + "(tmp)");
	}
}
