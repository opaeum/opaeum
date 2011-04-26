package net.sf.nakeduml.javageneration.basicjava;

import java.util.Arrays;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.runtime.domain.TinkerNode;

public class TinkerAuditTransformation extends AbstractJavaProducingVisitor {

	private static final String PREVIOUS_AUDIT_EDGE = "previous";

	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass originalClass = findJavaClass(c);
			OJAnnotatedClass ojAuditClass = findAuditJavaClass(c);
			originalClass.addToImports(new OJPathName(UtilityCreator.getUtilPathName().toJavaString() + ".TransactionThreadVar"));
			originalClass.addToImports(new OJPathName("com.tinkerpop.blueprints.pgm.Edge"));
			if (c.getGeneralizations().isEmpty()) {
				addVertexFieldWithSetter(ojAuditClass);
				addAuditVertexField(originalClass);
				implementCreateAuditVertex(originalClass, c);
				addGetMostRecentAuditVertex(originalClass);
				addCreateAuditVertexWithAuditEdge(originalClass);
				addCreateAuditVertexWithoutParent(originalClass);
				addGetPreviousAuditVertex(originalClass);
				addCreateEdgeToPreviousAudit(originalClass);
				if (c.getEndToComposite() != null) {
					initialiseAuditVertexConstructorWithOwningObject(c, originalClass);
				} else {
					initialiseAuditVertexInDefaultConstructor(originalClass);
				}
				implementGetAudits(originalClass, c);
			}
			if (!c.getIsAbstract()) {
				implementGetPreviousAuditEntry(ojAuditClass);
			}
			addContructorWithVertex(ojAuditClass, c);
			implementTinkerNode(ojAuditClass);
		}
	}

	private void implementGetPreviousAuditEntry(OJAnnotatedClass ojAuditClass) {
		OJOperation getPreviousAuditEntry = new OJOperation();
		getPreviousAuditEntry.setName("getPreviousAuditEntry");
		getPreviousAuditEntry.setReturnType(ojAuditClass.getPathName());
		getPreviousAuditEntry.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"" + PREVIOUS_AUDIT_EDGE + "\")");
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		forStatement.getBody().addToStatements("return new " + ojAuditClass.getPathName().getLast() + "(edge.getInVertex())");
		getPreviousAuditEntry.getBody().addToStatements(forStatement);
		getPreviousAuditEntry.getBody().addToStatements("return null");
		ojAuditClass.addToOperations(getPreviousAuditEntry);
	}

	private void initialiseAuditVertexConstructorWithOwningObject(INakedEntity c, OJAnnotatedClass originalClass) {
		NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJConstructor constructor = originalClass.findConstructor(compositeEndMap.javaBaseTypePath());
		constructor.getBody().addToStatements("createAuditVertex()");
	}

	private void addCreateEdgeToPreviousAudit(OJAnnotatedClass originalClass) {
		OJOperation createEdgeToPreviousAudit = new OJOperation();
		createEdgeToPreviousAudit.setName("createEdgeToPreviousAudit");
		OJField previousAuditVertex = new OJField();
		previousAuditVertex.setName("previousAuditVertex");
		previousAuditVertex.setType(TinkerUtil.vertexPathName);
		previousAuditVertex.setInitExp("getPreviousAuditVertex()");
		createEdgeToPreviousAudit.getBody().addToLocals(previousAuditVertex);
		OJIfStatement ifStatement = new OJIfStatement("previousAuditVertex != null");
		ifStatement.addToThenPart("Edge auditParentEdge = org.util.GraphDb.getDB().addEdge(null, this.auditVertex, previousAuditVertex, \""
				+ PREVIOUS_AUDIT_EDGE + "\")");
		ifStatement.addToThenPart("auditParentEdge.setProperty(\"outClass\", this.getClass().getName() + \"Audit\")");
		ifStatement.addToThenPart("auditParentEdge.setProperty(\"inClass\", this.getClass().getName() + \"Audit\")");
		createEdgeToPreviousAudit.getBody().addToStatements(ifStatement);
		originalClass.addToOperations(createEdgeToPreviousAudit);
	}

	private void addGetPreviousAuditVertex(OJAnnotatedClass originalClass) {
		OJAnnotatedOperation getPreviousAuditVertex = new OJAnnotatedOperation("getPreviousAuditVertex");
		getPreviousAuditVertex.setReturnType(TinkerUtil.vertexPathName);
		originalClass.addToImports(new OJPathName("java.util.TreeMap"));
		getPreviousAuditVertex.getBody().addToStatements(new OJSimpleStatement("TreeMap<Integer, Edge> auditTransactions = new TreeMap<Integer, Edge>()"));
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "this.vertex.getOutEdges()");
		getPreviousAuditVertex.getBody().addToStatements(forStatement);
		forStatement.getBody().addToStatements("String label = edge.getLabel()");
		OJIfStatement ifStatement = new OJIfStatement("label.startsWith(\"audit\")");
		ifStatement.addToThenPart("Integer transaction = Integer.valueOf(label.substring(label.length() - 1, label.length()))");
		ifStatement.addToThenPart("auditTransactions.put(transaction, edge)");
		forStatement.getBody().addToStatements(ifStatement);

		getPreviousAuditVertex.getBody().addToStatements("NavigableSet<Integer> descendingKeySet = auditTransactions.navigableKeySet()");
		originalClass.addToImports(new OJPathName("java.util.NavigableSet"));
		getPreviousAuditVertex.getBody().addToStatements("descendingKeySet.remove(descendingKeySet.last())");
		OJIfStatement ifStatement2 = new OJIfStatement("!descendingKeySet.isEmpty()", "return auditTransactions.get(descendingKeySet.last()).getInVertex()");
		ifStatement2.addToElsePart("return null");
		getPreviousAuditVertex.getBody().addToStatements(ifStatement2);
		originalClass.addToOperations(getPreviousAuditVertex);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		if (OJUtil.hasOJClass(p.getOwner())) {
			if (p.getAssociation() instanceof INakedAssociationClass) {
				// visitProperty(p.getOwner(),
				// OJUtil.buildAssociationClassMap(p,getOclEngine().getOclLibrary()));
			} else {
				visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}

	private void initialiseAuditVertexInDefaultConstructor(OJAnnotatedClass ojClass) {
		OJConstructor constructor = ojClass.getDefaultConstructor();
		constructor.getBody().addToStatements("createAuditVertex()");
	}

	private void addCreateAuditVertexWithoutParent(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation createAuditVertexWithoutParent = new OJAnnotatedOperation("createAuditVertexWithoutParent");
		createAuditVertexWithoutParent.setReturnType(TinkerUtil.vertexPathName);
		OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement.addToThenPart("createAuditVertexWithAuditEdge()");
		ifStatement.addToThenPart("createEdgeToPreviousAudit()");
		ifStatement.addToElsePart("this.auditVertex = getMostRecentAuditVertex()");
		createAuditVertexWithoutParent.getBody().addToStatements(ifStatement);
		createAuditVertexWithoutParent.getBody().addToStatements("return this.auditVertex");
		ojClass.addToOperations(createAuditVertexWithoutParent);
	}

	private void addCreateAuditVertexWithAuditEdge(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation createAuditVertexWithAuditEdge = new OJAnnotatedOperation("createAuditVertexWithAuditEdge");
		createAuditVertexWithAuditEdge.setVisibility(OJVisibilityKind.PRIVATE);
		createAuditVertexWithAuditEdge.getBody().addToStatements("this.auditVertex = org.util.GraphDb.getDB().addVertex(null)");
		createAuditVertexWithAuditEdge.getBody().addToStatements("TransactionThreadVar.setNewVertexFalse(getClass().getName() + getUid())");
		createAuditVertexWithAuditEdge
				.getBody()
				.addToStatements(
						"Edge auditEdgeToOriginal = org.util.GraphDb.getDB().addEdge(null, this.vertex, this.auditVertex, \"audit\" + org.util.GraphDb.getTransactionCount())");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"outClass\", this.getClass().getName())");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"inClass\", this.getClass().getName() + \"Audit\")");
		ojClass.addToOperations(createAuditVertexWithAuditEdge);
	}

	private void addGetMostRecentAuditVertex(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getMostRecentAuditVertex = new OJAnnotatedOperation("getMostRecentAuditVertex");
		getMostRecentAuditVertex.setReturnType(TinkerUtil.vertexPathName);
		ojClass.addToImports(new OJPathName("java.util.TreeMap"));
		getMostRecentAuditVertex.getBody().addToStatements(new OJSimpleStatement("TreeMap<Integer, Edge> auditTransactions = new TreeMap<Integer, Edge>()"));
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "this.vertex.getOutEdges()");
		getMostRecentAuditVertex.getBody().addToStatements(forStatement);
		forStatement.getBody().addToStatements("String label = edge.getLabel()");
		OJIfStatement ifStatement = new OJIfStatement("label.startsWith(\"audit\")");
		ifStatement.addToThenPart("Integer transaction = Integer.valueOf(label.substring(label.length() - 1, label.length()))");
		ifStatement.addToThenPart("auditTransactions.put(transaction, edge)");
		forStatement.getBody().addToStatements(ifStatement);
		getMostRecentAuditVertex.getBody().addToStatements("return auditTransactions.lastEntry().getValue().getInVertex()");
		ojClass.addToOperations(getMostRecentAuditVertex);
	}

	private void visitProperty(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		if (!OJUtil.isBuiltIn(p)) {
			if (p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)) {
			} else if (p.isDerived() || p.isReadOnly()) {
			} else {
				OJAnnotatedClass owner = findJavaClass(umlOwner);
				addAuditToSetter(umlOwner, owner, map);
			}
		}
	}

	private void addAuditToSetter(INakedClassifier umlOwner, OJAnnotatedClass originalClass, NakedStructuralFeatureMap map) {
		if (originalClass instanceof OJAnnotatedInterface) {
		} else {
			INakedProperty prop = map.getProperty();
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				if (map.isManyToOne()) {
				} else if (map.isOneToMany()) {
				} else if (map.isManyToMany()) {
				} else if (map.isOneToOne()) {
				}
			} else {
				OJAnnotatedOperation setter = (OJAnnotatedOperation) originalClass.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
				OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
				ifStatement.addToThenPart("createAuditVertex()");
				setter.getBody().addToStatements(ifStatement);
				setter.getBody()
						.addToStatements(
								"this.auditVertex.setProperty(\"" + TinkerUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName())
										+ "\", name)");
			}
		}
	}

	private void implementGetAudits(OJAnnotatedClass originalClass, INakedEntity c) {
		OJAnnotatedOperation getAudits = new OJAnnotatedOperation("getAudits");
		OJField result = new OJField();
		result.setName("result");
		OJPathName resultPathName = new OJPathName("java.util.List");
		OJPathName auditPath = new OJPathName(c.getMappingInfo().getQualifiedJavaName() + TinkerAuditCreator.AUDIT);
		resultPathName.addToElementTypes(auditPath);
		getAudits.setReturnType(resultPathName);
		result.setType(resultPathName);
		result.setInitExp("new ArrayList<" + auditPath.getLast() + ">()");
		getAudits.getBody().addToLocals(result);

		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "this.vertex.getOutEdges()");
		OJIfStatement ifStatement = new OJIfStatement("edge.getLabel().startsWith(\"" + TinkerUtil.constructSelfToAuditEdgeLabel(c) + "\")");

		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
		ojTryStatement.getTryPart().addToStatements("result.add((" + auditPath.getLast() + ") c.getConstructor(Vertex.class).newInstance(edge.getInVertex()))");
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");

		ifStatement.addToThenPart(ojTryStatement);
		forStatement.getBody().addToStatements(ifStatement);
		getAudits.getBody().addToStatements(forStatement);
		getAudits.getBody().addToStatements("return result");
		originalClass.addToOperations(getAudits);
	}

	private void addAuditVertexField(OJAnnotatedClass ojClass) {
		OJField auditVertexField = new OJAnnotatedField();
		auditVertexField.setName("auditVertex");
		OJPathName underlyingVertexPath = new OJPathName("com.tinkerpop.blueprints.pgm.Vertex");
		auditVertexField.setType(underlyingVertexPath);
		auditVertexField.setVisibility(OJVisibilityKind.PROTECTED);
		ojClass.addToFields(auditVertexField);
	}

	private void implementTinkerNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName(TinkerNode.class.getName()));
	}

	private void implementCreateAuditVertex(OJAnnotatedClass ojClass, INakedEntity entity) {
		OJAnnotatedOperation createAuditVertex = new OJAnnotatedOperation("createAuditVertex");
		createAuditVertex.setVisibility(OJVisibilityKind.PRIVATE);
		createAuditVertex.getBody().addToStatements("createAuditVertexWithAuditEdge()");
		if (entity.getEndToComposite() != null) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(entity.getEndToComposite());
			createAuditVertex.getBody().addToStatements("Vertex owningAuditVertex = " + map.getter() + "().createAuditVertexWithoutParent()");
		}

		if (entity.getEndToComposite() != null) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(entity.getEndToComposite());
			String associationName = entity.getEndToComposite().getAssociation().getName();
			createAuditVertex.getBody().addToStatements(
					"Edge auditParentEdge = org.util.GraphDb.getDB().addEdge(null, owningAuditVertex, this.auditVertex, \"" + associationName + "\")");
			createAuditVertex.getBody().addToStatements("auditParentEdge.setProperty(\"outClass\"," + map.getter() + "().getClass().getName() + \"Audit\")");
			createAuditVertex.getBody().addToStatements("auditParentEdge.setProperty(\"inClass\", this.getClass().getName() + \"Audit\")");
		}

		createAuditVertex.getBody().addToStatements("createEdgeToPreviousAudit()");

		ojClass.addToOperations(createAuditVertex);
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
