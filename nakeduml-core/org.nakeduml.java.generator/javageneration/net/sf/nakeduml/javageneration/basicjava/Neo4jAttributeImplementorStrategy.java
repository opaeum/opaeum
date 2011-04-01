package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class Neo4jAttributeImplementorStrategy implements AttributeImplementorStrategy {

	@Override
	public void addSimpleSetterBody(OJOperation setter, NakedStructuralFeatureMap map) {
		setter.getBody().addToStatements(
				"this.underlyingNode.setProperty(\"" + map.getProperty().getMappingInfo().getQualifiedUmlName() + "\", " + map.umlName() + ")");
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
			OJPathName relationship = new OJPathName("org.neo4j.graphdb.Relationship");
			owner.addToImports(relationship);
			owner.addToImports(new OJPathName("org.neo4j.graphdb.Direction"));
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(prop.getOtherEnd());
				if (map.isManyToOne()) {
					System.out.println("");
				} else if (map.isOneToMany()) {
					OJField result = new OJField();
					result.setType(map.javaTypePath());
					result.setName("result");
					result.setInitExp(map.javaDefaultValue());
					getter.getBody().addToLocals(result);
					getter.getBody().addToStatements(
							"Iterable<Relationship> iter = this.underlyingNode.getRelationships(new RelationshipType() {public String name() {return \""
									+ Neo4jUtil.constructCompositeRelationshipName(map.getProperty()) + "\";}}, Direction.OUTGOING)");
					OJForStatement forStatement = new OJForStatement("rel", relationship, "iter");
					forStatement.getBody().addToStatements("result.add(new " + map.javaBaseType() + "(rel.getEndNode()))");
					getter.getBody().addToStatements(forStatement);
					getter.getBody().addToStatements("return result");
				} else if (map.isManyToMany()) {
				} else if (map.isOneToOne()) {
					getter.getBody().addToStatements(Neo4jUtil.constructSingleRelationshipToCompositeParent(prop, false));
					OJIfStatement ifRelNull = new OJIfStatement("rel==null", "return null");
					getter.getBody().addToStatements(ifRelNull);
					getter.getBody().addToStatements("return new " + map.javaBaseType() + "(rel.getEndNode())");
				}
			} else {
				getter.getBody().addToStatements(
						"return (" + map.javaBaseType() + ") this.underlyingNode.getProperty(\"" + prop.getMappingInfo().getQualifiedUmlName() + "\")");
			}

		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	@Override
	public void buildManyToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		// remove "this" from existing reference
		OJIfStatement ifNotNull = new OJIfStatement();
		ifNotNull.setCondition(map.getter() + "()!=null");

		OJSimpleStatement getRelatioship = new OJSimpleStatement(Neo4jUtil.constructSingleRelationshipToCompositeParent(otherMap.getProperty(), true));
		ifNotNull.getThenPart().addToStatements(getRelatioship);
		ifNotNull.getThenPart().addToStatements("rel.delete()");
		setter.getBody().addToStatements(ifNotNull);
		owner.addToImports(new OJPathName("org.neo4j.graphdb.Relationship"));

		// add "this" to new reference
		OJIfStatement ifParamNotNull = new OJIfStatement();
		ifParamNotNull.setName(AttributeImplementor.IF_PARAM_NOT_NULL);
		ifParamNotNull.setCondition(map.umlName() + "!=null");
		ifParamNotNull.getThenPart().addToStatements(
				map.umlName() + ".getUnderlyingNode().createRelationshipTo(this.underlyingNode, new RelationshipType() {public String name() {return \""
						+ Neo4jUtil.constructCompositeRelationshipName(otherMap.getProperty()) + "\";}})");
		setter.getBody().addToStatements(ifParamNotNull);
	}
	
	@Override
	public void buildOneToOneSetter(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJAnnotatedClass owner, OJOperation setter) {
		buildManyToOneSetter(map,otherMap,owner,setter);
	}

}
