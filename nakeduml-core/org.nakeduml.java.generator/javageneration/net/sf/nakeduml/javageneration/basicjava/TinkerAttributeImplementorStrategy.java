package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collection;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.GeneralizationUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
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

		// Iterable<Edge> iter =
		// AbstractX1.associationVertex.getOutEdges("A__abstractX1___abstractY1_");
		// for (Edge edge : iter) {
		// Iterable<Edge> iter1 =
		// this.vertex.getInEdges(edge.getProperty("otherClass") +
		// "__abstractX1___abstractY1_org__" + this.getClass().getName());
		// if (iter1.iterator().hasNext()) {
		// Class<?> c;
		// try {
		// c = Class.forName((String) edge.getProperty("otherClass"));
		// return (AbstractX1)
		// c.getConstructor(Vertex.class).newInstance(iter1.iterator().next().getOutVertex());
		// } catch (Exception e) {
		// throw new RuntimeException(e);
		// }
		// }
		// }

		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateNonCompositeDirection(map, oneToOne, isComposite);

		INakedClassifier thisClassifier = map.getProperty().getOwner();
		INakedClassifier thisSuperClassifier = thisClassifier.getSupertype();
		String thisClassName = thisSuperClassifier == null ? thisClassifier.getMappingInfo().getJavaName().getAsIs() : thisSuperClassifier.getMappingInfo()
				.getJavaName().getAsIs();

		INakedClassifier otherClassifier = map.getProperty().getOtherEnd().getOwner();
		INakedClassifier otherSuperClassifier = otherClassifier.getSupertype();
		String otherClassName = otherSuperClassifier == null ? otherClassifier.getMappingInfo().getJavaName().getAsIs() : otherSuperClassifier.getMappingInfo()
				.getJavaName().getAsIs();
		String otherAssociationName = map.getProperty().getAssociation().getName();

		getter.getBody().addToStatements("Iterable<Edge> iter = " + thisClassName + ".associationVertex.getInEdges(\"" + otherAssociationName + "\")");
		OJPathName edgePathName = new OJPathName("com.tinkerpop.blueprints.pgm.Edge");
		OJForStatement forStatement = new OJForStatement("edge", edgePathName, "iter");
		getter.getBody().addToStatements(forStatement);

		if (isComposite) {
			forStatement.getBody().addToStatements(
					"Iterable<Edge> iter1 = this.vertex.getOutEdges(this.getClass().getName()+\"__" + otherAssociationName
							+ "__\" + edge.getProperty(\"otherClass\"))");
		} else {
			forStatement.getBody().addToStatements(
					"Iterable<Edge> iter1 = this.vertex.getInEdges(edge.getProperty(\"otherClass\")" + "+\"__" + otherAssociationName
							+ "__\" + this.getClass().getName())");
		}
		OJIfStatement ifStatement = new OJIfStatement("iter1.iterator().hasNext()");
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"otherClass\"))");
		if (isComposite) {
			ojTryStatement.getTryPart().addToStatements(
					"return (" + otherClassName + ") c.getConstructor(Vertex.class).newInstance(iter1.iterator().next().getInVertex())");
		} else {
			ojTryStatement.getTryPart().addToStatements(
					"return (" + otherClassName + ") c.getConstructor(Vertex.class).newInstance(iter1.iterator().next().getOutVertex())");

		}
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ifStatement.addToThenPart(ojTryStatement);
		forStatement.getBody().addToStatements(ifStatement);

		// Collection<INakedEntity> subClasses =
		// GeneralizationUtil.getConcreteEntityImplementationsOf(manyClassifier,
		// modelInScope);
		// if (!manyClassifier.getIsAbstract()) {
		// subClasses.add((INakedEntity)manyClassifier);
		// }
		// INakedClassifier otherManyClassifier = map.getProperty().getOwner();
		// Collection<INakedEntity> otherSubClasses =
		// GeneralizationUtil.getConcreteEntityImplementationsOf(otherManyClassifier,
		// modelInScope);
		// if (!otherManyClassifier.getIsAbstract()) {
		// otherSubClasses.add((INakedEntity)otherManyClassifier);
		// }
		// int count = 1;
		// for (INakedClassifier subClassClassifier : subClasses) {
		// for (INakedClassifier otherSubClassClassifier : otherSubClasses) {
		// String relationshipName;
		// if (isComposite) {
		// relationshipName =
		// TinkerUtil.constructCompositeRelationshipName(otherSubClassClassifier,
		// subClassClassifier, map.getProperty());
		// } else {
		// relationshipName =
		// TinkerUtil.constructCompositeRelationshipName(subClassClassifier,
		// otherSubClassClassifier, map.getProperty());
		// }
		// getter.getBody().addToStatements(
		// "Iterable<Edge> iter" + count + " = this.vertex." + (isComposite ?
		// "getOutEdges" : "getInEdges") + "(\"" + relationshipName + "\")");
		// getter.getBody().addToStatements(
		// new OJIfStatement("iter" + count + ".iterator().hasNext()",
		// "return new "
		// + subClassClassifier.getMappingInfo().getJavaName().toString() +
		// "(iter" + count + ".iterator().next()."
		// + (isComposite ? "getInVertex" : "getOutVertex") + "())"));
		// count++;
		// }
		// }
		getter.getBody().addToStatements("return null");
	}

	private boolean calculateNonCompositeDirection(NakedStructuralFeatureMap map, boolean oneToOne, boolean isComposite) {
		if (oneToOne && !isComposite && !map.getProperty().getOtherEnd().isComposite()) {
			isComposite = map.getProperty().getMultiplicity().getLower() == 1 && map.getProperty().getMultiplicity().getUpper() == 1;
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
		Collection<INakedEntity> subClasses = GeneralizationUtil.getConcreteEntityImplementationsOf(manyClassifier, modelInScope);
		if (!manyClassifier.getIsAbstract()) {
			subClasses.add((INakedEntity) manyClassifier);
		}
		INakedClassifier otherManyClassifier = map.getProperty().getOwner();
		Collection<INakedEntity> otherSubClasses = GeneralizationUtil.getConcreteEntityImplementationsOf(otherManyClassifier, modelInScope);
		if (!otherManyClassifier.getIsAbstract()) {
			otherSubClasses.add((INakedEntity) otherManyClassifier);
		}
		int count = 1;
		for (INakedClassifier subClassClassifier : subClasses) {
			for (INakedClassifier otherSubClassClassifier : otherSubClasses) {
				getter.getBody().addToStatements(
						"Iterable<Edge> iter" + count + " = this.vertex.getOutEdges(\""
								+ TinkerUtil.constructCompositeRelationshipName(otherSubClassClassifier, subClassClassifier, map.getProperty()) + "\")");
				OJForStatement forStatement = new OJForStatement("edge", edgePathName, "iter" + count);
				forStatement.getBody().addToStatements(
						"result.add(new " + subClassClassifier.getMappingInfo().getJavaName().toString() + "(edge.getInVertex()))");
				getter.getBody().addToStatements(forStatement);
				getter.getOwner().addToImports(OJUtil.classifierPathname(subClassClassifier));
				count++;
			}
		}
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
		isComposite = calculateNonCompositeDirection(map, oneToOne, isComposite);

		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		Collection<INakedEntity> subClasses = GeneralizationUtil.getConcreteEntityImplementationsOf(umlOwner, modelInScope);
		if (!umlOwner.getIsAbstract()) {
			subClasses.add((INakedEntity) umlOwner);
		}
		INakedClassifier otherOneClassifier = map.getProperty().getOtherEnd().getOwner();
		Collection<INakedEntity> otherSubClasses = GeneralizationUtil.getConcreteEntityImplementationsOf(otherOneClassifier, modelInScope);
		if (!otherOneClassifier.getIsAbstract()) {
			otherSubClasses.add((INakedEntity) otherOneClassifier);
		}
		for (INakedClassifier subClassClassifier : subClasses) {
			for (INakedClassifier otherSubClassClassifier : otherSubClasses) {
				String relationshipName;
				if (isComposite) {
					relationshipName = TinkerUtil.constructCompositeRelationshipName(subClassClassifier, otherSubClassClassifier, map.getProperty());
				} else {
					relationshipName = TinkerUtil.constructCompositeRelationshipName(otherSubClassClassifier, subClassClassifier, map.getProperty());
				}
				ifParamNotNull.getThenPart().addToStatements(
						new OJIfStatement("(this.getClass() == " + subClassClassifier.getMappingInfo().getJavaName().toString() + ".class) && ("
								+ map.umlName() + ".getClass() == " + otherSubClassClassifier.getMappingInfo().getJavaName().toString() + ".class)",
								UtilityCreator.getUtilPathName().toJavaString()
										+ ".DbThreadVar.getDB().addEdge(null, "
										+ (!isComposite ? "((" + otherSubClassClassifier.getMappingInfo().getJavaName().toString() + ")" + map.umlName()
												+ ").getVertex(), this.vertex," : "this.vertex, (("
												+ otherSubClassClassifier.getMappingInfo().getJavaName().toString() + ")" + map.umlName() + ").getVertex(),")
										+ "\"" + relationshipName + "\")"));
			}
		}
		setter.getBody().addToStatements(ifParamNotNull);
	}

	private void removePolymorphicToOneRelationship(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner,
			OJOperation setter, Collection<INakedRootObject> modelInScope, boolean oneToOne) {
		boolean isComposite = map.getProperty().isComposite();
		isComposite = calculateNonCompositeDirection(map, oneToOne, isComposite);
		INakedClassifier oneClassifier = map.getProperty().getOwner();
		Collection<INakedEntity> subClasses = GeneralizationUtil.getConcreteEntityImplementationsOf(oneClassifier, modelInScope);
		if (!oneClassifier.getIsAbstract()) {
			subClasses.add((INakedEntity) oneClassifier);
		}
		INakedClassifier otherOneClassifier = map.getProperty().getOtherEnd().getOwner();
		Collection<INakedEntity> otherSubClasses = GeneralizationUtil.getConcreteEntityImplementationsOf(otherOneClassifier, modelInScope);
		if (!otherOneClassifier.getIsAbstract()) {
			otherSubClasses.add((INakedEntity) otherOneClassifier);
		}
		int count = 1;
		for (INakedClassifier subClassClassifier : subClasses) {
			for (INakedClassifier otherSubClassClassifier : otherSubClasses) {
				String relationshipName;
				if (isComposite) {
					relationshipName = TinkerUtil.constructCompositeRelationshipName(subClassClassifier, otherSubClassClassifier, map.getProperty());
				} else {
					relationshipName = TinkerUtil.constructCompositeRelationshipName(otherSubClassClassifier, subClassClassifier, map.getProperty());
				}
				setter.getBody().addToStatements(
						"Iterable<Edge> iter" + count + " = this.vertex." + (isComposite ? "getOutEdges" : "getInEdges") + "(\"" + relationshipName + "\")");
				OJIfStatement ifNotNull = new OJIfStatement();
				ifNotNull.setCondition("iter" + count + ".iterator().hasNext()");
				ifNotNull.getThenPart().addToStatements(
						UtilityCreator.getUtilPathName().toJavaString() + ".DbThreadVar.getDB().removeEdge(iter" + count + ".iterator().next())");
				setter.getBody().addToStatements(ifNotNull);
				count++;
			}
		}
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
