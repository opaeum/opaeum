package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collection;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.GeneralizationUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class TinkerTransformation extends AbstractJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			if (c.getGeneralizations().isEmpty()) {
				addVertexFieldWithSetter(ojClass);
				addVertexInDefaultConstructor(c, ojClass);
			}
			addContructorWithVertex(ojClass, c);
			if (c.getEndToComposite() == null) {
				// TODO attach to root somehow
			} else if (!c.getIsAbstract()) {
				initializeVertex(ojClass, c);
			}
		}
	}

	private void addVertexInDefaultConstructor(INakedEntity entity, OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.getDefaultConstructor();
		constructor.getBody().addToStatements("this.vertex = " + UtilityCreator.getUtilPathName().toJavaString() + ".DbThreadVar.getDB().addVertex(null);");
	}

	private void initializeVertex(OJAnnotatedClass ojClass, INakedEntity c) {
		NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJAnnotatedOperation initVertex = new OJAnnotatedOperation();
		initVertex.setName("initVertex");
		initVertex.addParam("owningObject", compositeEndMap.javaBaseTypePath());
		initVertex.setVisibility(OJVisibilityKind.PRIVATE);
		OJConstructor constructor = ojClass.findConstructor(compositeEndMap.javaBaseTypePath());
		constructor.getBody().getStatements().add(0,new OJSimpleStatement("this()"));
		constructor.getBody().addToStatements(new OJSimpleStatement("initVertex(owningObject)"));
		Collection<INakedClassifier> otherEndClasses = GeneralizationUtil.getAllSubClassifiers(c.getEndToComposite().getOtherEnd().getOwner(),
				getModelInScope());
		if (!c.getEndToComposite().getOtherEnd().getOwner().getIsAbstract()) {
			otherEndClasses.add(c.getEndToComposite().getOtherEnd().getOwner());
		}
		for (INakedClassifier compositeEndClass : otherEndClasses) {
			OJIfStatement ifStatement = new OJIfStatement("owningObject.getClass()==" + compositeEndClass.getMappingInfo().getJavaName().toString() + ".class");
			OJBlock ojBlock = new OJBlock();
			String relationshipName = TinkerUtil.constructCompositeRelationshipName(compositeEndClass, c, c.getEndToComposite());
			
			if (compositeEndMap.isOneToOne()) {
				ojBlock.addToStatements("Iterable<Edge> iter = owningObject.getVertex().getOutEdges" + "(\"" + relationshipName + "\")");
				OJIfStatement ifAllReadyHasOne = new OJIfStatement("iter.iterator().hasNext()");
				ifAllReadyHasOne.addToThenPart(UtilityCreator.getUtilPathName().toJavaString()	+ ".DbThreadVar.getDB().removeVertex(this.vertex)");		
				ifAllReadyHasOne.addToThenPart("throw new IllegalStateException(\""
						+ c.getEndToComposite().getOtherEnd().getOwner().getMappingInfo().getQualifiedJavaName()
						+ " already has an association with " + c.getMappingInfo().getQualifiedJavaName() + ", the relationship is one to one!\")");
				ojBlock.addToStatements(ifAllReadyHasOne);
			}			
			
			ojBlock.addToStatements(UtilityCreator.getUtilPathName().toJavaString()
					+ ".DbThreadVar.getDB().addEdge(null, owningObject.getVertex(), this.vertex, \"" + relationshipName + "\")");
			ifStatement.setThenPart(ojBlock);
			initVertex.getBody().addToStatements(ifStatement);
		}
		ojClass.addToOperations(initVertex);
	}

	private void addContructorWithVertex(OJAnnotatedClass ojClass, INakedEntity c) {
		OJConstructor constructor = new OJConstructor();
		constructor.addParam("vertex", new OJPathName("com.tinkerpop.blueprints.pgm.Vertex"));
		if (c.getGeneralizations().isEmpty()) {
			constructor.getBody().addToStatements("this.vertex=vertex");
		} else {
			constructor.getBody().addToStatements("super(vertex)");
		}
		ojClass.addToConstructors(constructor);
	}

	private void addVertexFieldWithSetter(OJAnnotatedClass ojClass) {
		OJField vertexField = new OJAnnotatedField();
		vertexField.setName("vertex");
		OJPathName underlyingVertexPath = new OJPathName("com.tinkerpop.blueprints.pgm.Vertex");
		vertexField.setType(underlyingVertexPath);
		vertexField.setVisibility(OJVisibilityKind.PROTECTED);
		ojClass.addToFields(vertexField);
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName("getVertex");
		setter.setReturnType(underlyingVertexPath);
		setter.getBody().addToStatements("return this.vertex");
		ojClass.addToOperations(setter);
	}

}
