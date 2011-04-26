package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.runtime.domain.TinkerNode;

public class TinkerTransformation extends AbstractJavaProducingVisitor {

	private static final String BASE_TINKER = "org.util.BaseTinker";

	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			if (c.getGeneralizations().isEmpty()) {
				addGetVersion(ojClass);
				persistUid(ojClass);
				initialiseVertexInDefaultConstructor(ojClass);
				extendsBaseTinker(ojClass);
			}
			if (c.getEndToComposite() != null) {
				initializeVertex(ojClass, c);
			}
			addSuperToDefaultConstructor(ojClass);
			addContructorWithVertex(ojClass, c);
			implementTinkerNode(ojClass);
		}
	}

	private void addSuperToDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.getDefaultConstructor();
		constructor.getBody().getStatements().add(0,new OJSimpleStatement("super()"));
	}

	private void extendsBaseTinker(OJAnnotatedClass ojClass) {
		ojClass.setSuperclass(new OJPathName(BASE_TINKER));
	}

	private void persistUid(OJAnnotatedClass ojClass) {
		OJOperation getUid = OJUtil.findOperation(ojClass, "getUid");
		getUid.getBody().removeAllFromStatements();
		getUid.getBody().addToStatements("String uid = (String) this.vertex.getProperty(\"uid\")");
		OJIfStatement ifStatement = new OJIfStatement("uid==null || uid.trim().length()==0");
		ifStatement.setCondition("uid==null || uid.trim().length()==0");
		ifStatement.addToThenPart("uid=UUID.randomUUID().toString()");
		ifStatement.addToThenPart("this.vertex.setProperty(\"uid\", uid)");
		getUid.getBody().addToStatements(ifStatement);
		getUid.getBody().addToStatements("return uid");
	}

	private void addGetVersion(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getVersion = new OJAnnotatedOperation("getVersion");
		getVersion.setReturnType(new OJPathName("int"));
		getVersion.getBody().addToStatements("return ((OrientVertex)this.vertex).getRawElement().getVersion()");
		ojClass.addToImports(TinkerUtil.orientVertexPathName);
		ojClass.addToOperations(getVersion);
	}

	private void implementTinkerNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName(TinkerNode.class.getName()));
	}

	private void initialiseVertexInDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.getDefaultConstructor();
		constructor.getBody().addToStatements("this.vertex = " + UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().addVertex(null)");
	}

	private void initializeVertex(OJAnnotatedClass ojClass, INakedEntity c) {
		NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJAnnotatedOperation initVertex = new OJAnnotatedOperation();
		initVertex.setName("initVertex");
		initVertex.addParam("owningObject", compositeEndMap.javaBaseTypePath());
		initVertex.setVisibility(OJVisibilityKind.PRIVATE);
		OJConstructor constructor = ojClass.findConstructor(compositeEndMap.javaBaseTypePath());
		constructor.getBody().getStatements().add(0, new OJSimpleStatement("this()"));
		constructor.getBody().addToStatements(new OJSimpleStatement("initVertex(owningObject)"));

		if (compositeEndMap.isOneToOne()) {
			initVertex.getBody().addToStatements(
					"Iterable<Edge> iter = owningObject.getVertex().getOutEdges" + "(\"" + c.getEndToComposite().getAssociation().getName() + "\")");
			OJIfStatement ifAllReadyHasOne = new OJIfStatement("iter.iterator().hasNext()");
			ifAllReadyHasOne.addToThenPart(UtilityCreator.getUtilPathName().toJavaString() + ".GraphDb.getDB().removeVertex(this.vertex)");
			ifAllReadyHasOne.addToThenPart("throw new IllegalStateException(\""
					+ c.getEndToComposite().getOtherEnd().getOwner().getMappingInfo().getQualifiedJavaName() + " already has an association with "
					+ c.getMappingInfo().getQualifiedJavaName() + ", the relationship is one to one!\")");
			initVertex.getBody().addToStatements(ifAllReadyHasOne);
		}

		// Add association meta information, i.e. classname of other end
		String associationName = c.getEndToComposite().getAssociation().getName();
		initVertex.getBody().addToStatements(
				"Edge edge = org.util.GraphDb.getDB().addEdge(null, owningObject.getVertex(), this.vertex, \"" + associationName + "\")");
		initVertex.getBody().addToStatements("edge.setProperty(\"outClass\", owningObject.getClass().getName())");
		initVertex.getBody().addToStatements("edge.setProperty(\"inClass\", this.getClass().getName())");

		OJPathName edgePathName = new OJPathName("com.tinkerpop.blueprints.pgm.Edge");
		ojClass.addToImports(edgePathName);
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

}
