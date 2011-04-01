package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;

import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class Neo4JTransformation extends AbstractJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			if (c.getGeneralizations().isEmpty()) {
				addNeo4jNodeWithSetter(ojClass);
			}
			addContructorWithNeoNode(ojClass, c);
			if (c.getEndToComposite() == null) {
				initializeNeoNodeInDefaultConstructor(ojClass, c);
			} else if (!c.getIsAbstract()) {
				initializeNeoNode(ojClass, c);
			}
		}
	}

	private void initializeNeoNode(OJAnnotatedClass ojClass, INakedEntity c) {
		NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJAnnotatedOperation initUnderlyingNode = new OJAnnotatedOperation();
		initUnderlyingNode.setName("initUnderlyingNode");
		initUnderlyingNode.addParam("owningObject", compositeEndMap.javaBaseTypePath());
		initUnderlyingNode.setVisibility(OJVisibilityKind.PRIVATE);
		initUnderlyingNode.getBody().addToStatements("this.underlyingNode = weave.DbThreadVar.getDB().createNode();");
		String relationshipName = Neo4jUtil.constructCompositeRelationshipName(c.getEndToComposite().getOtherEnd());
		initUnderlyingNode.getBody().addToStatements("owningObject.getUnderlyingNode().createRelationshipTo(this.underlyingNode, new RelationshipType() {public String name() {return \"" + relationshipName + "\";}})");
		ojClass.addToOperations(initUnderlyingNode);
		ojClass.addToImports(new OJPathName("org.neo4j.graphdb.RelationshipType"));
		
		OJConstructor constructor = ojClass.findConstructor(compositeEndMap.javaBaseTypePath());
		constructor.getBody().getStatements().add(0, new OJSimpleStatement("initUnderlyingNode(owningObject)"));
	}

	private void initializeNeoNodeInDefaultConstructor(OJAnnotatedClass root, INakedEntity c) {
		OJConstructor constructor = root.getDefaultConstructor();
		constructor.getBody().addToStatements("this.underlyingNode = weave.DbThreadVar.getDB().createNode()");
		StringBuilder sb = new StringBuilder();
		sb.append("weave.DbThreadVar.getDB().getReferenceNode().createRelationshipTo(underlyingNode,");
		sb.append("new RelationshipType() {public String name() {return \"" + c.getMappingInfo().getQualifiedUmlName() + "\";}})");
		constructor.getBody().addToStatements(sb.toString());
		root.addToImports(new OJPathName("org.neo4j.graphdb.RelationshipType"));
	}

	private void addContructorWithNeoNode(OJAnnotatedClass ojClass, INakedEntity c) {
		OJConstructor constructor = new OJConstructor();
		constructor.addParam("underlyingNode", new OJPathName("org.neo4j.graphdb.Node"));
		if (c.getGeneralizations().isEmpty()) {
			constructor.getBody().addToStatements("this.underlyingNode=underlyingNode");
		} else {
			constructor.getBody().addToStatements("super(underlyingNode)");
		}
		ojClass.addToConstructors(constructor);
	}

	private void addNeo4jNodeWithSetter(OJAnnotatedClass ojClass) {
		OJField neo4jNodeField = new OJAnnotatedField();
		neo4jNodeField.setName("underlyingNode");
		OJPathName underlyingNodePath = new OJPathName("org.neo4j.graphdb.Node");
		neo4jNodeField.setType(underlyingNodePath);
		neo4jNodeField.setVisibility(OJVisibilityKind.PROTECTED);
		ojClass.addToFields(neo4jNodeField);
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName("getUnderlyingNode");
		setter.setReturnType(underlyingNodePath);
		setter.getBody().addToStatements("return this.underlyingNode");
		ojClass.addToOperations(setter);
	}

}
