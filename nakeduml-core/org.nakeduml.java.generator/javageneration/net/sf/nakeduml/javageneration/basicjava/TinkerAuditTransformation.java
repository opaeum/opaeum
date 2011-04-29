package net.sf.nakeduml.javageneration.basicjava;

import java.util.Arrays;
import java.util.List;

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

import org.nakeduml.java.metamodel.OJBlock;
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

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

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
				addCreateEdgeToOne(originalClass);
				implementCreateAuditVertex(originalClass, c);
				addGetMostRecentAuditVertex(originalClass);
				addCreateAuditVertexWithAuditEdge(originalClass);
				addCreateAuditVertexWithoutParent(originalClass);
				addGetPreviousAuditVertex(originalClass);
				addCreateEdgeToPreviousAudit(originalClass);
				addGetOriginal(ojAuditClass, c);
				if (c.getEndToComposite() != null) {
					initialiseAuditVertexConstructorWithOwningObject(c, originalClass);
				} else {
					initialiseAuditVertexInDefaultConstructor(originalClass);
				}
				implementGetAudits(originalClass, c);
			} else {
				if (c.getEndToComposite() != null) {
					addCreateAuditVertexInConstructorWithOwningObject(originalClass, c);
				}
			}
			if (!c.getIsAbstract()) {
				implementGetPreviousAuditEntry(ojAuditClass);
			} else {
				implementAbstractGetPreviousAuditEntry(ojAuditClass);
			}
			addContructorWithVertex(ojAuditClass, c);
			implementTinkerNode(ojAuditClass);
		}
	}

	private void addCreateEdgeToOne(OJAnnotatedClass ojAuditClass) {
		OJOperation createEdgeToOne = new OJOperation();
		createEdgeToOne.setName("createEdgeToOne");
		createEdgeToOne.addParam("oneAuditVertex", TinkerUtil.vertexPathName);
		createEdgeToOne.addParam("inverse", new OJPathName("Boolean"));
		createEdgeToOne.addParam("clazz", new OJPathName("Class<?>"));
		createEdgeToOne.addParam("label", new OJPathName("String"));
		OJIfStatement ifStatement = new OJIfStatement("!inverse");
		ifStatement.addToThenPart("Edge auditParentEdge = org.util.GraphDb.getDB().addEdge(null, oneAuditVertex, this.auditVertex, label)");
		ifStatement.addToThenPart("auditParentEdge.setProperty(\"outClass\",clazz.getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		ifStatement.addToThenPart("auditParentEdge.setProperty(\"inClass\",this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		ifStatement.addToElsePart("Edge auditParentEdge = org.util.GraphDb.getDB().addEdge(null, this.auditVertex, oneAuditVertex, label)");
		ifStatement.addToElsePart("auditParentEdge.setProperty(\"outClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		ifStatement.addToElsePart("auditParentEdge.setProperty(\"inClass\", clazz.getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		createEdgeToOne.getBody().addToStatements(ifStatement);
		ojAuditClass.addToOperations(createEdgeToOne);
	}

	private void addCreateAuditVertexInConstructorWithOwningObject(OJAnnotatedClass originalClass, INakedEntity c) {
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJConstructor constructor = originalClass.findConstructor(map.javaBaseTypePath());
		constructor.getBody().addToStatements("createAuditVertex(true)");
	}

	private void addGetOriginal(OJAnnotatedClass ojAuditClass, INakedEntity c) {
		OJOperation getOriginal = new OJOperation();
		getOriginal.setName("getOriginal");
		getOriginal.setReturnType(OJUtil.classifierPathname(c));
		getOriginal.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges()");
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
		OJIfStatement ifStatement = new OJIfStatement("edge.getLabel().startsWith(\"audit\")");
		forStatement.getBody().addToStatements(ifStatement);
		getOriginal.getBody().addToStatements(forStatement);
		OJTryStatement ojTryStatement = new OJTryStatement();
		OJBlock tryBlock = new OJBlock();
		tryBlock.addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
		tryBlock.addToStatements("return (" + OJUtil.classifierPathname(c).getLast() + ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
		OJBlock catchBlock = new OJBlock();
		catchBlock.addToStatements("throw new RuntimeException(e)");
		ojTryStatement.setCatchPart(catchBlock);
		ojTryStatement.setTryPart(tryBlock);
		ifStatement.addToThenPart(ojTryStatement);
		getOriginal.getBody().addToStatements("return null");
		ojAuditClass.addToOperations(getOriginal);
	}

	private void implementAbstractGetPreviousAuditEntry(OJAnnotatedClass ojAuditClass) {
		ojAuditClass.addToImports(TinkerUtil.edgePathName);
		OJOperation getPreviousAuditEntry = new OJOperation();
		getPreviousAuditEntry.setName("getPreviousAuditEntry");
		getPreviousAuditEntry.setReturnType(ojAuditClass.getPathName());
		getPreviousAuditEntry.setAbstract(true);
		ojAuditClass.addToOperations(getPreviousAuditEntry);
	}

	private void implementGetPreviousAuditEntry(OJAnnotatedClass ojAuditClass) {
		ojAuditClass.addToImports(TinkerUtil.edgePathName);
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
		constructor.getBody().addToStatements("createAuditVertex(true)");
	}

	private void addCreateEdgeToPreviousAudit(OJAnnotatedClass originalClass) {
		OJOperation createEdgeToPreviousAudit = new OJOperation();
		createEdgeToPreviousAudit.setVisibility(OJVisibilityKind.PRIVATE);
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
		getPreviousAuditVertex.setVisibility(OJVisibilityKind.PRIVATE);
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
		constructor.getBody().addToStatements("createAuditVertex(false)");
	}

	// TODO this needs to move as now it is public api
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
		createAuditVertexWithAuditEdge.getBody().addToStatements("copyShallowState(this, this)");
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
		getMostRecentAuditVertex.setVisibility(OJVisibilityKind.PUBLIC);
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
				ifStatement.addToThenPart("createAuditVertex(false)");
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

	private void implementTinkerNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName(TinkerNode.class.getName()));
	}

	private void implementCreateAuditVertex(OJAnnotatedClass ojClass, INakedEntity entity) {
		OJAnnotatedOperation createAuditVertex = new OJAnnotatedOperation("createAuditVertex");
		createAuditVertex.setVisibility(OJVisibilityKind.PUBLIC);
		createAuditVertex.addParam("createParentVertex", new OJPathName("boolean"));
		createAuditVertex.getBody().addToStatements("createAuditVertexWithAuditEdge()");
		if (entity.getEndToComposite() != null) {
			OJField owningAuditVertex = new OJField();
			owningAuditVertex.setName("owningAuditVertex");
			owningAuditVertex.setType(TinkerUtil.vertexPathName);
			createAuditVertex.getBody().addToLocals(owningAuditVertex);
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(entity.getEndToComposite());
			OJIfStatement ifStatement = new OJIfStatement("createParentVertex || !TransactionThreadVar.hasNoAuditEntry(" + map.javaBaseTypePath().getLast()
					+ ".class.getName() + " + map.getter() + "().getUid())");
			ifStatement.addToThenPart("owningAuditVertex = " + map.getter() + "().createAuditVertexWithoutParent()");
			ifStatement.addToElsePart("owningAuditVertex = " + map.getter() + "().getMostRecentAuditVertex()");
			createAuditVertex.getBody().addToStatements(ifStatement);
			String associationName = entity.getEndToComposite().getAssociation().getName();
			createAuditVertex.getBody().addToStatements(
					"createEdgeToOne(owningAuditVertex, false, " + map.getter() + "().getClass(),\"" + associationName + "\")");
		}
		List<? extends INakedProperty> properties = entity.getEffectiveAttributes();
		for (INakedProperty p : properties) {
			if (p != entity.getEndToComposite() && (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly()) && p.getOtherEnd().isComposite() && p.getOtherEnd().getType() != entity)) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
				if (map.isOneToOne() || (map.isManyToOne() && map.getProperty().getSubsettedProperties().isEmpty())) {
					boolean isComposite = map.getProperty().isComposite();
					isComposite = TinkerAttributeImplementorStrategy.calculateDirection(map, isComposite);
					OJIfStatement ifStatement = new OJIfStatement(map.getter() + "() != null && " + map.getter() + "().getMostRecentAuditVertex() != null");
					ifStatement.addToThenPart("createEdgeToOne(" + map.getter() + "().getMostRecentAuditVertex(), " + isComposite + ", " + map.getter()
							+ "().getClass(),\"" + map.getProperty().getAssociation().getName() + "\")");
					createAuditVertex.getBody().addToStatements(ifStatement);
				}
			}
		}
		createAuditVertex.getBody().addToStatements("createEdgeToPreviousAudit()");
		addAttachNonCompositeToOnesAlreadyCaptured(ojClass);
		createAuditVertex.getBody().addToStatements("attachNonCompositeToOnesAlreadyCaptured()");
		createAuditVertex.getBody().addToStatements("TransactionAuditThreadVar.addVertex(this.auditVertex)");
		ojClass.addToImports(TinkerUtil.transactionAuditThreadVar);
		ojClass.addToOperations(createAuditVertex);
	}

	private void addAttachNonCompositeToOnesAlreadyCaptured(OJAnnotatedClass ojClass) {
		ojClass.addToImports(new OJPathName("java.util.Set"));
		OJOperation oper = new OJOperation();
		oper.setName("attachNonCompositeToOnesAlreadyCaptured");
		oper.getBody().addToStatements("Set<Vertex> auditVertices = TransactionAuditThreadVar.get()");
		oper.getBody().addToStatements("Iterable<Edge> iter = this.auditVertex.getOutEdges(\"previous\")");
		OJIfStatement ifStatement = new OJIfStatement("iter.iterator().hasNext()");
		ifStatement.addToThenPart("Vertex previous = iter.iterator().next().getInVertex()");
		OJForStatement forStatement = new OJForStatement("transactionHeldAuditVertex", TinkerUtil.vertexPathName, "auditVertices");
		forStatement.getBody().addToStatements("List<Edge> edgesToRemove = new ArrayList<Edge>()");
		forStatement.getBody().addToStatements("List<Edge> edgesToAdd = new ArrayList<Edge>()");
		OJForStatement forStatement2 = new OJForStatement("edge", TinkerUtil.edgePathName, "transactionHeldAuditVertex.getInEdges()");
		forStatement.getBody().addToStatements(forStatement2);
		OJIfStatement ifStatement2 = new OJIfStatement("edge.getOutVertex().equals(previous)");
		ifStatement2.addToThenPart("edgesToRemove.add(edge)");
		ifStatement2.addToThenPart("edgesToAdd.add(edge)");
		forStatement2.getBody().addToStatements(ifStatement2);
		OJForStatement forStatement3 = new OJForStatement("edge", TinkerUtil.edgePathName, "edgesToRemove");
		forStatement3.getBody().addToStatements("org.util.GraphDb.getDB().removeEdge(edge)");
		forStatement.getBody().addToStatements(forStatement3);
		OJForStatement forStatement4 = new OJForStatement("edge", TinkerUtil.edgePathName, "edgesToAdd");
		forStatement4.getBody().addToStatements("Edge newAuditEdge = org.util.GraphDb.getDB().addEdge(null, this.auditVertex, transactionHeldAuditVertex, edge.getLabel())");
		forStatement4.getBody().addToStatements("newAuditEdge.setProperty(\"outClass\", edge.getProperty(\"outClass\"))");
		forStatement4.getBody().addToStatements("newAuditEdge.setProperty(\"inClass\", edge.getProperty(\"inClass\"))");
		forStatement.getBody().addToStatements(forStatement4);
		ifStatement.addToThenPart(forStatement);
		oper.getBody().addToStatements(ifStatement);
		ojClass.addToOperations(oper);
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
