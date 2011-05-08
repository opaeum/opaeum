package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
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
import org.nakeduml.runtime.domain.TinkerNode;

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
	public static OJPathName TINKER_NODE = new OJPathName(TinkerNode.class.getName());

	public TinkerAttributeImplementorStrategy() {
		super();
	}

	@Override
	public void addSimpleSetterBody(OJOperation setter, NakedStructuralFeatureMap map) {
		setter.getBody().addToStatements(
				"this.vertex.setProperty(\"" + TinkerUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName()) + "\", " + map.umlName()
						+ ")");
		addEntityToTransactionThreadEntityVar(setter);
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
				if (map.isManyToOne() && map.getProperty().getSubsettedProperties().isEmpty()) {
					buildPolymorphicGetterForToOne(map, getter);
				} else if (map.isOneToMany()) {
					buildPolymorphicGetterForMany(map, getter);
				} else if (map.isManyToMany()) {
					buildPolymorphicGetterForMany(map, getter);
				} else if (map.isOneToOne()) {
					buildPolymorphicGetterForToOne(map, getter);
				}
			} else {
				getter.getBody().addToStatements(
						"return (" + map.javaBaseType() + ") this.vertex.getProperty(\""
								+ TinkerUtil.tinkeriseUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
			}

		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	public void buildPolymorphicGetterForToOne(NakedStructuralFeatureMap map, OJOperation getter) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		INakedClassifier otherClassifier = map.getProperty().getOtherEnd().getOwner();
		INakedClassifier otherSuperClassifier = otherClassifier.getSupertype();
		String otherClassName = otherSuperClassifier == null ? otherClassifier.getMappingInfo().getJavaName().getAsIs() : otherSuperClassifier.getMappingInfo()
				.getJavaName().getAsIs();
		String otherAssociationName = map.getProperty().getAssociation().getName();
		if (isComposite) {
			getter.getBody().addToStatements("Iterable<Edge> iter1 = this.vertex.getOutEdges(\"" + otherAssociationName + "\")");
		} else {
			getter.getBody().addToStatements("Iterable<Edge> iter1 = this.vertex.getInEdges(\"" + otherAssociationName + "\")");
		}
		OJIfStatement ifStatement = new OJIfStatement("iter1.iterator().hasNext()");
		ifStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_IF);
		ifStatement.addToThenPart("Edge edge = iter1.iterator().next()");
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"return (" + otherClassName
							+ ") c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
		} else {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"return (" + otherClassName
							+ ") c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");

		}
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ifStatement.addToThenPart(ojTryStatement);
		getter.getBody().addToStatements(ifStatement);
		getter.getBody().addToStatements("return null");
	}

	public static boolean calculateDirection(NakedStructuralFeatureMap map, boolean isComposite) {
		if (map.isOneToOne() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getLower() == 1 && map.getProperty().getMultiplicity().getUpper() == 1;
		} else if (map.isOneToMany() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getUpper() > 1;
		} else if (map.isManyToMany() && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = 0 > map.getProperty().getName().compareTo(map.getProperty().getOtherEnd().getName());
		}
		return isComposite;
	}

	private void buildPolymorphicGetterForMany(NakedStructuralFeatureMap map, OJOperation getter) {
		OJField result = new OJField();
		result.setType(map.javaTypePath());
		result.setName("result");
		result.setInitExp(map.javaDefaultValue());
		OJPathName defaultValue = map.javaDefaultTypePath();
		getter.getOwner().addToImports(defaultValue);

		getter.getBody().addToLocals(result);
		INakedClassifier manyClassifier = map.getProperty().getOtherEnd().getOwner();

		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		if (isComposite) {
			getter.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + map.getProperty().getAssociation().getName() + "\")");
		} else {
			getter.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges(\"" + map.getProperty().getAssociation().getName() + "\")");
		}
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		forStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_FOR);
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setName(POLYMORPHIC_GETTER_FOR_TO_MANY_TRY);
		forStatement.getBody().addToStatements(ojTryStatement);
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"result.add((" + manyClassifier.getMappingInfo().getJavaName().getAsIs()
							+ ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex()))");
		} else {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"result.add((" + manyClassifier.getMappingInfo().getJavaName().getAsIs()
							+ ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex()))");
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
		removePolymorphicToOneRelationship(map, otherMap, owner, setter);
		createPolymorphicToOneRelationship(umlOwner, map, otherMap, setter);
	}
	
	@Override
	public void buildOneToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		addEntityToTransactionThreadEntityVar(setter);
		removePolymorphicToOneRelationship(map, otherMap, owner, setter);
		removeInverseToPolymorphicRelationship(map, otherMap, owner, setter);
		createPolymorphicToOneRelationship(umlOwner, map, otherMap, setter);
	}

	private void createPolymorphicToOneRelationship(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap,
			OJOperation setter) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);

		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		String relationshipName = map.getProperty().getAssociation().getName();
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

	private void removePolymorphicToOneRelationship(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		String relationshipName = map.getProperty().getAssociation().getName();
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
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		owner.addToImports(new OJPathName("java.util.List"));
		owner.addToImports(new OJPathName("java.util.ArrayList"));
		setter.getBody().addToStatements("List<Edge> edgesToRemove = new ArrayList<Edge>()");
		String relationshipName = map.getProperty().getAssociation().getName();
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
		isComposite = calculateDirection(map, isComposite);
		OJIfStatement ifStatement = new OJIfStatement("!" + map.getter() + "().contains(" + map.umlName() + ")");
		ifStatement.setName(MANY_TO_MANY_ADDER_IF_CONTAINS);
		String relationshipName = map.getProperty().getAssociation().getName();
		adder.getBody().addToStatements(ifStatement);
		ifStatement.addToThenPart(
				new OJSimpleStatement("Edge edge = "
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

	@Override
	public void buildManyRemover(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation remover) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, isComposite);
		remover.getBody().addToStatements("List<Edge> edgesToRemove = new ArrayList<Edge>()");
		String relationshipName = map.getProperty().getAssociation().getName();
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
}
