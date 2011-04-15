package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collection;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
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

	@Override
	public void addSimpleSetterBody(OJOperation setter, NakedStructuralFeatureMap map) {
		setter.getBody().addToStatements(
				"this.vertex.setProperty(\"" + TinkerUtil.tinkerpoperizeUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName()) + "\", "
						+ map.umlName() + ")");
	}

	@Override
	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		// Used by hibernate strategy
		return null;
	}

	@Override
	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault, Collection<INakedRootObject> modelInScope) {
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName(map.getter());
		getter.setReturnType(map.javaTypePath());
		owner.addToOperations(getter);
		if (owner instanceof OJAnnotatedInterface) {
		} else if (returnDefault) {
			getter.getBody().addToStatements("return " + map.javaDefaultValue());
		} else {
			INakedProperty prop = map.getProperty();
			OJPathName edgePathName = new OJPathName("com.tinkerpop.blueprints.pgm.Edge");
			owner.addToImports(edgePathName);
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				if (map.isManyToOne() && map.getProperty().getSubsettedProperties().isEmpty()) {
					buildPolymorphicGetterForToOne(map, getter, modelInScope, false);
				} else if (map.isOneToMany()) {
					buildPolymorphicGetterForMany(map, getter, edgePathName, modelInScope);
				} else if (map.isManyToMany()) {
				} else if (map.isOneToOne()) {
					buildPolymorphicGetterForToOne(map, getter, modelInScope, true);
				}
			} else {
				getter.getBody().addToStatements(
						"return (" + map.javaBaseType() + ") this.vertex.getProperty(\""
								+ TinkerUtil.tinkerpoperizeUmlName(prop.getMappingInfo().getQualifiedUmlName()) + "\")");
			}

		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	private void buildPolymorphicGetterForToOne(NakedStructuralFeatureMap map, OJOperation getter, Collection<INakedRootObject> modelInScope, boolean oneToOne) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, oneToOne, isComposite);
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
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.getTryPart().addToStatements("Edge edge = iter1.iterator().next()");
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

	private boolean calculateDirection(NakedStructuralFeatureMap map, boolean oneToOne, boolean isComposite) {
		if (oneToOne && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getLower() == 1 && map.getProperty().getMultiplicity().getUpper() == 1;
		} else if (!oneToOne && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getUpper() > 1;
		}
		return isComposite;
	}

	private void buildPolymorphicGetterForMany(NakedStructuralFeatureMap map, OJOperation getter, OJPathName edgePathName,
			Collection<INakedRootObject> modelInScope) {
		OJField result = new OJField();
		result.setType(map.javaTypePath());
		result.setName("result");
		result.setInitExp(map.javaDefaultValue());
		getter.getBody().addToLocals(result);
		INakedClassifier manyClassifier = map.getProperty().getOtherEnd().getOwner();

		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, false, isComposite);
		if (isComposite) {
			getter.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + map.getProperty().getAssociation().getName() + "\")");
		} else {
			getter.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges(\"" + map.getProperty().getAssociation().getName() + "\")");
		}
		OJForStatement forStatement = new OJForStatement("edge", edgePathName, "iter");
		OJTryStatement ojTryStatement = new OJTryStatement();
		forStatement.getBody().addToStatements(ojTryStatement);
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"result.add((" + manyClassifier.getMappingInfo().getJavaName().getAsIs() + ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex()))");
		} else {
			ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			ojTryStatement.getTryPart().addToStatements(
					"result.add((" + manyClassifier.getMappingInfo().getJavaName().getAsIs() + ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex()))");
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
	public void buildManyToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter, Collection<INakedRootObject> modelInScope) {
		removePolymorphicToOneRelationship(map, otherMap, owner, setter, modelInScope, false);
		createPolymorphicToOneRelationship(umlOwner, map, otherMap, setter, modelInScope, false);
	}

	private void createPolymorphicToOneRelationship(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap,
			OJOperation setter, Collection<INakedRootObject> modelInScope, boolean oneToOne) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, oneToOne, isComposite);

		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		String relationshipName = map.getProperty().getAssociation().getName();
		ifParamNotNull.getThenPart().addToStatements(
				new OJSimpleStatement("Edge edge = " + UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().addEdge(null, "
						+ (!isComposite ? map.umlName() + ".getVertex(), this.vertex," : "this.vertex, " + map.umlName() + ".getVertex(),") + "\""
						+ relationshipName + "\")"));
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
			OJOperation setter, Collection<INakedRootObject> modelInScope, boolean oneToOne) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateDirection(map, oneToOne, isComposite);
		String relationshipName = map.getProperty().getAssociation().getName();
		setter.getBody()
				.addToStatements("Iterable<Edge> iter = this.vertex." + (isComposite ? "getOutEdges" : "getInEdges") + "(\"" + relationshipName + "\")");
		OJIfStatement ifNotNull = new OJIfStatement();
		ifNotNull.setCondition("iter.iterator().hasNext()");
		ifNotNull.getThenPart().addToStatements(UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().removeEdge(iter.iterator().next())");
		setter.getBody().addToStatements(ifNotNull);
	}

	@Override
	public void buildOneToOneSetter(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter, Collection<INakedRootObject> modelInScope) {
		removePolymorphicToOneRelationship(map, otherMap, owner, setter, modelInScope, true);
		removeInverseToPolymorphicRelationship(map, otherMap, owner, setter, modelInScope);
		createPolymorphicToOneRelationship(umlOwner, map, otherMap, setter, modelInScope, true);
	}

	private void removeInverseToPolymorphicRelationship(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter, Collection<INakedRootObject> modelInScope) {
		setter.getBody().addToStatements(
				new OJIfStatement(map.umlName() + "!=null && " + map.umlName() + "." + otherMap.getter() + "()!=null", map.umlName() + "." + otherMap.getter()
						+ "()." + map.setter() + "(null)"));
	}

	@Override
	public void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		// Used by hibernate strategy
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
}
