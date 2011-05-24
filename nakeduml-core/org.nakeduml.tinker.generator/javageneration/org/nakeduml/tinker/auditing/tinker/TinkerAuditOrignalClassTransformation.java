package org.nakeduml.tinker.auditing.tinker;

import java.util.Arrays;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.auditing.tinker.TinkerAuditCreator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
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
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.tinker.basicjava.tinker.TinkerAttributeImplementorStrategy;
import org.nakeduml.tinker.basicjava.tinker.TinkerTransformation;
import org.nakeduml.tinker.basicjava.tinker.TinkerUtil;

public class TinkerAuditOrignalClassTransformation extends AbstractJavaProducingVisitor {

	private static final String BASE_AUDIT_TINKER = "org.util.BaseTinkerAuditable";

	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass originalClass = findJavaClass(c);
			originalClass.addToImports(new OJPathName(UtilityCreator.getUtilPathName().toJavaString() + ".TransactionThreadVar"));
			originalClass.addToImports(new OJPathName("com.tinkerpop.blueprints.pgm.Edge"));
			if (c.getGeneralizations().isEmpty()) {
				addCreateEdgeToOne(originalClass);
				addCreateAuditVertex(originalClass, c);
				if (c.getEndToComposite() != null) {
					addCreateEdgeToCompositeOwner(originalClass, c);
				} else {
					if (c.getIsAbstract()) {
						implementAbstractAddEdgeToCompositeOwner(originalClass);
					}
				}
				addGetMostRecentAuditVertex(originalClass);
				addCreateAuditVertexWithAuditEdge(originalClass);
				addAuditVertexGetter(originalClass);
				addCreateAuditVertexWithoutParent(originalClass);
				addGetPreviousAuditVertex(originalClass);
				if (c.getEndToComposite() != null) {
					addCreateAuditVertexInConstructorWithOwningObject(originalClass, c);
				}
				implementGetAudits(originalClass, c);
			} else {
				if (c.getEndToComposite() != null) {
					addCreateAuditVertexInConstructorWithOwningObject(originalClass, c);
				}
				// This select hierarchy type pattern
				if (c.getEndToComposite() != null && ((ICompositionParticipant) c.getSupertype()).getEndToComposite() == null) {
					addCreateEdgeToCompositeOwner(originalClass, c);
				}
			}
			if (!c.hasSupertype()) {
				extendsBaseTinkerAuditable(originalClass);
			}
			implementTinkerAuditableNode(originalClass);
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		if (OJUtil.hasOJClass(p.getOwner())) {
			if (p.getAssociation() instanceof INakedAssociationClass) {
			} else {
				visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}

	@VisitAfter(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class, INakedAssociationClass.class })
	public void visitFeature(INakedClassifier entity) {
		for (INakedProperty p : entity.getEffectiveAttributes()) {
			if (p.getOwner() instanceof INakedInterface && OJUtil.hasOJClass(entity)) {
				if (p.getAssociation() instanceof INakedAssociationClass) {
					// TODO test this may create duplicates
					// buildAssociationClassLogic(entity,
					// (INakedAssociationClass) p.getAssociation());
				} else {
					visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				}
			}
		}
	}
	
	private void extendsBaseTinkerAuditable(OJAnnotatedClass originalClass) {
		originalClass.setSuperclass(new OJPathName(BASE_AUDIT_TINKER));
	}

	private void implementAbstractAddEdgeToCompositeOwner(OJAnnotatedClass ojAuditClass) {
		OJOperation createEdgeToCompositeOwner = new OJOperation();
		createEdgeToCompositeOwner.setName("addEdgeToCompositeOwner");
		createEdgeToCompositeOwner.setAbstract(true);
		ojAuditClass.addToOperations(createEdgeToCompositeOwner);
	}

	private void addCreateEdgeToCompositeOwner(OJAnnotatedClass originalClass, INakedEntity c) {
		OJOperation addEdgeToCompositeOwner = new OJOperation();
		addEdgeToCompositeOwner.setName("addEdgeToCompositeOwner");
		OJField owningAuditVertex = new OJField();
		owningAuditVertex.setName("owningAuditVertex");
		owningAuditVertex.setType(TinkerUtil.vertexPathName);
		addEdgeToCompositeOwner.getBody().addToLocals(owningAuditVertex);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(c.getEndToComposite());
		addEdgeToCompositeOwner.getBody().addToStatements("owningAuditVertex = " + map.getter() + "().createAuditVertexWithoutParent()");
		String associationName = c.getEndToComposite().getAssociation().getName();
		addEdgeToCompositeOwner.getBody().addToStatements(
				"createEdgeToOne(owningAuditVertex, false, " + map.getter() + "().getClass(),\"" + associationName + "\")");
		originalClass.addToOperations(addEdgeToCompositeOwner);

	}

	private void addAuditVertexGetter(OJAnnotatedClass originalClass) {
		OJOperation getAuditVertex = new OJOperation();
		getAuditVertex.setName("getAuditVertex");
		getAuditVertex.setReturnType(TinkerUtil.vertexPathName);
		getAuditVertex.getBody().addToStatements("Vertex auditVertex = TransactionThreadVar.getAuditEntry(getClass().getName() + getUid())");
		OJIfStatement hasAuditVertex = new OJIfStatement("auditVertex==null", "return getMostRecentAuditVertex()");
		hasAuditVertex.addToElsePart("return auditVertex");
		getAuditVertex.getBody().addToStatements(hasAuditVertex);
		originalClass.addToOperations(getAuditVertex);
	}

	private void implementTinkerAuditableNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName("org.nakeduml.runtime.domain.TinkerAuditableNode"));
	}

	private void addCreateEdgeToOne(OJAnnotatedClass originalClass) {
		originalClass.addToImports(new OJPathName("java.util.Iterator"));
		OJOperation createEdgeToOne = new OJOperation();
		createEdgeToOne.setName("createEdgeToOne");
		createEdgeToOne.addParam("oneAuditVertex", TinkerUtil.vertexPathName);
		createEdgeToOne.addParam("inverse", new OJPathName("Boolean"));
		createEdgeToOne.addParam("clazz", new OJPathName("Class<?>"));
		createEdgeToOne.addParam("label", new OJPathName("String"));
		OJIfStatement ifStatement = new OJIfStatement("!inverse");
		originalClass.addToImports(TinkerUtil.graphDbPathName);
		ifStatement.addToThenPart("Iterator<Edge> inIter = getAuditVertex().getInEdges(label).iterator()");
		OJIfStatement ifOneInEdgeAllreadyExist = new OJIfStatement("!inIter.hasNext()");
		ifOneInEdgeAllreadyExist.addToThenPart("Edge auditParentEdge = GraphDb.getDB().addEdge(null, oneAuditVertex, getAuditVertex(), label)");
		ifOneInEdgeAllreadyExist.addToThenPart("auditParentEdge.setProperty(\"outClass\",clazz.getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		ifOneInEdgeAllreadyExist.addToThenPart("auditParentEdge.setProperty(\"inClass\",this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		ifStatement.addToThenPart(ifOneInEdgeAllreadyExist);
		ifStatement.addToElsePart("Iterator<Edge> outIter = getAuditVertex().getOutEdges(label).iterator()");
		OJIfStatement ifOneOutEdgeAllreadyExist = new OJIfStatement("!outIter.hasNext()");
		ifOneOutEdgeAllreadyExist.addToThenPart("Edge auditParentEdge = GraphDb.getDB().addEdge(null, getAuditVertex(), oneAuditVertex, label)");
		ifOneOutEdgeAllreadyExist.addToThenPart("auditParentEdge.setProperty(\"outClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		ifOneOutEdgeAllreadyExist.addToThenPart("auditParentEdge.setProperty(\"inClass\", clazz.getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		ifStatement.addToElsePart(ifOneOutEdgeAllreadyExist);
		createEdgeToOne.getBody().addToStatements(ifStatement);
		originalClass.addToOperations(createEdgeToOne);
	}

	private void addCreateAuditVertexInConstructorWithOwningObject(OJAnnotatedClass originalClass, INakedEntity c) {
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJConstructor constructor = originalClass.findConstructor(map.javaBaseTypePath());
		OJSimpleStatement ojSimpleStatement = (OJSimpleStatement) constructor.getBody().findStatement(TinkerTransformation.INIT_VERTEX);
		constructor.getBody().getStatements()
				.add(constructor.getBody().getStatements().indexOf(ojSimpleStatement) + 1, new OJSimpleStatement("createAuditVertex(true)"));
	}

	private void addGetPreviousAuditVertex(OJAnnotatedClass originalClass) {
		OJAnnotatedOperation getPreviousAuditVertex = new OJAnnotatedOperation("getPreviousAuditVertex");
		getPreviousAuditVertex.setVisibility(OJVisibilityKind.PRIVATE);
		getPreviousAuditVertex.setReturnType(TinkerUtil.vertexPathName);
		originalClass.addToImports(new OJPathName("java.util.TreeMap"));
		getPreviousAuditVertex.getBody().addToStatements(new OJSimpleStatement("TreeMap<Integer, Edge> auditTransactions = new TreeMap<Integer, Edge>()"));
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "this.vertex.getOutEdges(\"audit\")");
		forStatement.getBody().addToStatements("Integer transaction = (Integer)edge.getProperty(\"transactionNo\")");
		forStatement.getBody().addToStatements("auditTransactions.put(transaction, edge)");
		getPreviousAuditVertex.getBody().addToStatements(forStatement);

		getPreviousAuditVertex.getBody().addToStatements("NavigableSet<Integer> descendingKeySet = auditTransactions.navigableKeySet()");
		originalClass.addToImports(new OJPathName("java.util.NavigableSet"));
		getPreviousAuditVertex.getBody().addToStatements("descendingKeySet.remove(descendingKeySet.last())");
		OJIfStatement ifStatement2 = new OJIfStatement("!descendingKeySet.isEmpty()", "return auditTransactions.get(descendingKeySet.last()).getInVertex()");
		ifStatement2.addToElsePart("return null");
		getPreviousAuditVertex.getBody().addToStatements(ifStatement2);
		originalClass.addToOperations(getPreviousAuditVertex);
	}

	// TODO this needs to move as now it is public api
	private void addCreateAuditVertexWithoutParent(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation createAuditVertexWithoutParent = new OJAnnotatedOperation("createAuditVertexWithoutParent");
		createAuditVertexWithoutParent.setReturnType(TinkerUtil.vertexPathName);
		OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement.addToThenPart("createAuditVertexWithAuditEdge()");
		// ifStatement.addToThenPart("createEdgeToPreviousAudit()");
		createAuditVertexWithoutParent.getBody().addToStatements(ifStatement);
		createAuditVertexWithoutParent.getBody().addToStatements("return getAuditVertex()");
		ojClass.addToOperations(createAuditVertexWithoutParent);
	}

	private void addCreateAuditVertexWithAuditEdge(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation createAuditVertexWithAuditEdge = new OJAnnotatedOperation("createAuditVertexWithAuditEdge");
		createAuditVertexWithAuditEdge.setVisibility(OJVisibilityKind.PRIVATE);
		ojClass.addToImports(TinkerUtil.graphDbPathName);
		createAuditVertexWithAuditEdge.getBody().addToStatements("this.auditVertex = GraphDb.getDB().addVertex(null)");
		createAuditVertexWithAuditEdge.getBody().addToStatements("TransactionThreadVar.putAuditVertexFalse(getClass().getName() + getUid(), this.auditVertex)");
		createAuditVertexWithAuditEdge.getBody().addToStatements("this.auditVertex.setProperty(\"transactionNo\", GraphDb.getTransactionCount())");
		createAuditVertexWithAuditEdge.getBody().addToStatements(
				"Edge auditEdgeToOriginal = GraphDb.getDB().addEdge(null, this.vertex, this.auditVertex, \"audit\")");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"transactionNo\", GraphDb.getTransactionCount())");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"outClass\", this.getClass().getName())");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"inClass\", this.getClass().getName() + \"Audit\")");
		createAuditVertexWithAuditEdge.getBody().addToStatements("copyShallowState(this, this)");
		ojClass.addToOperations(createAuditVertexWithAuditEdge);
	}

	private void addGetMostRecentAuditVertex(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getMostRecentAuditVertex = new OJAnnotatedOperation("getMostRecentAuditVertex");
		getMostRecentAuditVertex.setVisibility(OJVisibilityKind.PRIVATE);
		getMostRecentAuditVertex.setReturnType(TinkerUtil.vertexPathName);
		ojClass.addToImports(new OJPathName("java.util.TreeMap"));
		getMostRecentAuditVertex.getBody().addToStatements(new OJSimpleStatement("TreeMap<Integer, Edge> auditTransactions = new TreeMap<Integer, Edge>()"));
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "this.vertex.getOutEdges(\"audit\")");
		getMostRecentAuditVertex.getBody().addToStatements(forStatement);
		forStatement.getBody().addToStatements("Integer transaction = (Integer)edge.getProperty(\"transactionNo\")");
		forStatement.getBody().addToStatements("auditTransactions.put(transaction, edge)");
		getMostRecentAuditVertex.getBody().addToStatements("return auditTransactions.lastEntry().getValue().getInVertex()");
		ojClass.addToOperations(getMostRecentAuditVertex);
	}

	private void visitProperty(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		if (!OJUtil.isBuiltIn(p)) {
			if (p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)) {
			} else if (p.isDerived() || p.isReadOnly()) {
			} else {
				implementAttributeFully(umlOwner, map);
			}
		}
	}

	private void implementAttributeFully(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		if (map.isMany()) {
			buildAdder(owner, map);
			// buildAddAll(owner, map);
			buildRemover(owner, map);
			// buildRemoveAll(owner, map);
			// buildClear(owner, map);
		} else if (map.isOne() && isPersistent(p.getNakedBaseType()) || p.getBaseType() instanceof INakedInterface) {
			// buildInternalAdder(owner, map);
			// buildInternalRemover(owner, map);
		}
		addAuditToSetter(umlOwner, owner, map);
	}

	private OJOperation buildRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation remover = owner.findOperation(map.remover(), Arrays.asList(map.javaBaseTypePath()));
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				buildManyRemover(map, otherMap, remover);
				owner.addToImports(new OJPathName("java.lang.reflect.Constructor"));
			} else {
			}
		} else {
		}
		owner.addToOperations(remover);
		return remover;
	}

	private void buildManyRemover(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder) {
		OJForStatement forStatement = (OJForStatement) adder.getBody().findStatement(TinkerAttributeImplementorStrategy.TINKER_MANY_REMOVER);
		OJIfStatement ifStatement1 = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement1.addToThenPart("createAuditVertex(false)");
		forStatement.getBody().addToStatements(ifStatement1);
		OJIfStatement ifStatement2 = new OJIfStatement(map.umlName() + " != null && TransactionThreadVar.hasNoAuditEntry(" + map.umlName()
				+ ".getClass().getName() + " + map.umlName() + ".getUid())");
		ifStatement2.addToThenPart(map.umlName() + ".createAuditVertex(false)");
		forStatement.getBody().addToStatements(ifStatement2);

		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);

		OJTryStatement tryException = buildRemovedAuditEdge(map, otherMap, isComposite);
		forStatement.getBody().addToStatements(tryException);

	}

	private OJTryStatement buildRemovedAuditEdge(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, boolean isComposite) {
		OJTryStatement tryException = new OJTryStatement();

		if (!isComposite) {
			tryException.getTryPart().addToStatements(
					"Class<" + otherMap.javaBaseTypePath().getLast() + "> inClazz = (Class<" + otherMap.javaBaseTypePath().getLast()
							+ ">) Class.forName((String)edge.getProperty(\"inClass\"))");
			tryException.getTryPart().addToStatements(
					"Constructor<" + otherMap.javaBaseTypePath().getLast() + "> inConstructor = inClazz.getConstructor(Vertex.class)");
			tryException.getTryPart()
					.addToStatements(otherMap.javaBaseTypePath().getLast() + " manyToRemoveIn = inConstructor.newInstance(edge.getInVertex())");
			tryException.getTryPart().addToStatements(
					"Class<" + map.javaBaseTypePath().getLast() + "> outClazz = (Class<" + map.javaBaseTypePath().getLast()
							+ ">) Class.forName((String)edge.getProperty(\"outClass\"))");
			tryException.getTryPart().addToStatements(
					"Constructor<" + map.javaBaseTypePath().getLast() + "> outConstructor = outClazz.getConstructor(Vertex.class)");
			tryException.getTryPart().addToStatements(map.javaBaseTypePath().getLast() + " manyToRemoveOut = outConstructor.newInstance(edge.getOutVertex())");
			tryException.getTryPart().addToStatements(
					"Edge auditEdge = GraphDb.getDB().addEdge(null, manyToRemoveOut.getAuditVertex(), manyToRemoveIn.getAuditVertex(),\""
							+ map.getProperty().getAssociation().getName() + "\")");

			tryException.getTryPart().addToStatements("auditEdge.setProperty(\"inClass\", edge.getProperty(\"inClass\")" + "+ \"Audit\")");
			tryException.getTryPart().addToStatements("auditEdge.setProperty(\"outClass\", edge.getProperty(\"outClass\")" + "+ \"Audit\")");
		} else {
			tryException.getTryPart().addToStatements(
					"Class<" + map.javaBaseTypePath().getLast() + "> inClazz = (Class<" + map.javaBaseTypePath().getLast()
							+ ">) Class.forName((String)edge.getProperty(\"inClass\"))");
			tryException.getTryPart().addToStatements(
					"Constructor<" + map.javaBaseTypePath().getLast() + "> inConstructor = inClazz.getConstructor(Vertex.class)");
			tryException.getTryPart().addToStatements(map.javaBaseTypePath().getLast() + " manyToRemoveIn = inConstructor.newInstance(edge.getInVertex())");
			tryException.getTryPart().addToStatements(
					"Class<" + otherMap.javaBaseTypePath().getLast() + "> outClazz = (Class<" + otherMap.javaBaseTypePath().getLast()
							+ ">) Class.forName((String)edge.getProperty(\"outClass\"))");
			tryException.getTryPart().addToStatements(
					"Constructor<" + otherMap.javaBaseTypePath().getLast() + "> outConstructor = outClazz.getConstructor(Vertex.class)");
			tryException.getTryPart().addToStatements(
					otherMap.javaBaseTypePath().getLast() + " manyToRemoveOut = outConstructor.newInstance(edge.getOutVertex())");
			tryException.getTryPart().addToStatements(
					"Edge auditEdge = GraphDb.getDB().addEdge(null, manyToRemoveOut.getAuditVertex(), manyToRemoveIn.getAuditVertex(),\""
							+ map.getProperty().getAssociation().getName() + "\")");

			tryException.getTryPart().addToStatements("auditEdge.setProperty(\"inClass\", edge.getProperty(\"inClass\")" + "+ \"Audit\")");
			tryException.getTryPart()
					.addToStatements("auditEdge.setProperty(\"outClass\", edge.getProperty(\"outClass\")" + "+ \"Audit\")");
		}
		tryException.getTryPart().addToStatements("auditEdge.setProperty(\"deletedOn\", TinkerFormatter.format(new Date()))");
		OJParameter catchParam = new OJParameter("e", new OJPathName("java.lang.Exception"));
		tryException.setCatchParam(catchParam);
		tryException.getCatchPart().addToStatements("throw new RuntimeException(e)");
		return tryException;
	}

	private OJOperation buildAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = owner.findOperation(map.adder(), Arrays.asList(map.javaBaseTypePath()));
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				owner.addToImports(TinkerUtil.graphDbPathName);
				buildManyAdder(map, otherMap, adder);
			} else {
			}
		} else {
		}
		owner.addToOperations(adder);
		return adder;
	}

	private void buildManyAdder(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder) {
		OJIfStatement ifStatement = (OJIfStatement) adder.getBody().findStatement(TinkerAttributeImplementorStrategy.MANY_TO_MANY_ADDER_IF_CONTAINS);
		OJIfStatement ifStatement1 = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement1.addToThenPart("createAuditVertex(false)");
		ifStatement.addToThenPart(ifStatement1);
		OJIfStatement ifStatement2 = new OJIfStatement(map.umlName() + " != null && TransactionThreadVar.hasNoAuditEntry(" + map.umlName()
				+ ".getClass().getName() + " + map.umlName() + ".getUid())");
		ifStatement2.addToThenPart(map.umlName() + ".createAuditVertex(false)");
		ifStatement.addToThenPart(ifStatement2);

		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);
		if (isComposite) {
			ifStatement.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, getAuditVertex(), " + map.umlName() + ".getAuditVertex(),\""
					+ map.getProperty().getAssociation().getName() + "\")");
			ifStatement.addToThenPart("auditEdge.setProperty(\"outClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement.addToThenPart("auditEdge.setProperty(\"inClass\", " + map.umlName() + ".getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		} else {
			ifStatement.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, " + map.umlName() + ".getAuditVertex(), getAuditVertex(),\""
					+ map.getProperty().getAssociation().getName() + "\")");
			ifStatement.addToThenPart("auditEdge.setProperty(\"outClass\", " + map.umlName() + ".getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement.addToThenPart("auditEdge.setProperty(\"inClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		}
	}

	private void addAuditToSetter(INakedClassifier umlOwner, OJAnnotatedClass originalClass, NakedStructuralFeatureMap map) {
		if (originalClass instanceof OJAnnotatedInterface) {
		} else {
			INakedProperty prop = map.getProperty();
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				if (map.isManyToOne()) {
					addAuditToToOneSetter(umlOwner, originalClass, map);
				} else if (map.isOneToMany()) {
				} else if (map.isManyToMany()) {
					addAuditToManyToManySetter(umlOwner, originalClass, map);
				} else if (map.isOneToOne()) {
					addAuditToToOneSetter(umlOwner, originalClass, map);
				}
			} else {
				OJAnnotatedOperation setter = (OJAnnotatedOperation) originalClass.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
				OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
				ifStatement.addToThenPart("createAuditVertex(false)");
				setter.getBody().addToStatements(ifStatement);
				if (map.isMany() && map.getProperty().getBaseType() instanceof INakedEnumeration) {
					setter.getBody()
							.addToStatements(
									"getAuditVertex().setProperty(\"" + TinkerUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName())
											+ "\", "+ TinkerUtil.tinkerUtil.getLast() + ".convertEnumsForPersistence(" + map.umlName()+"))");
					originalClass.addToImports(TinkerUtil.tinkerUtil);
				} else {
					setter.getBody()
					.addToStatements(
							"getAuditVertex().setProperty(\"" + TinkerUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName())
									+ "\"," + map.umlName()+")");
					
				}
			}
		}
	}

	private void addAuditToManyToManySetter(INakedClassifier umlOwner, OJAnnotatedClass originalClass, NakedStructuralFeatureMap map) {
		OJOperation setter = originalClass.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
		OJForStatement collectEdges = (OJForStatement) setter.getBody().findStatement(
				TinkerAttributeImplementorStrategy.TINKER_MANY_TO_MANY_SETTER_COLLECT_EDGES);

		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);

		if (isComposite) {
			OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(" + map.javaBaseTypePath().getLast()
					+ ".class.getName() + edge.getInVertex().getProperty(\"uid\"))");
			collectEdges.getBody().addToStatements(ifStatement);
			OJTryStatement ojTryStatement = new OJTryStatement();
			OJBlock tryBlock = new OJBlock();
			tryBlock.addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
			tryBlock.addToStatements(map.javaBaseTypePath().getLast() + " manyToRemove = (" + map.javaBaseTypePath().getLast()
					+ ")c.getConstructor(Vertex.class).newInstance(edge.getInVertex())");
			tryBlock.addToStatements("manyToRemove.createAuditVertex(false)");
			ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.land.Exception")));
			OJBlock catchBlock = new OJBlock();
			catchBlock.addToStatements("throw new RuntimeException(e)");
			ojTryStatement.setCatchPart(catchBlock);
			ojTryStatement.setTryPart(tryBlock);
			ifStatement.addToThenPart(ojTryStatement);
		} else {
			OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(" + map.javaBaseTypePath().getLast()
					+ ".class.getName() + edge.getOutVertex().getProperty(\"uid\"))");
			collectEdges.getBody().addToStatements(ifStatement);
			OJTryStatement ojTryStatement = new OJTryStatement();
			OJBlock tryBlock = new OJBlock();
			tryBlock.addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
			tryBlock.addToStatements(map.javaBaseTypePath().getLast() + " manyToRemove = (" + map.javaBaseTypePath().getLast()
					+ ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
			tryBlock.addToStatements("manyToRemove.createAuditVertex(false)");
			ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.land.Exception")));
			OJBlock catchBlock = new OJBlock();
			catchBlock.addToStatements("throw new RuntimeException(e)");
			ojTryStatement.setCatchPart(catchBlock);
			ojTryStatement.setTryPart(tryBlock);
			ifStatement.addToThenPart(ojTryStatement);
		}

		OJIfStatement ifStatement = new OJIfStatement("!edgesToRemove.isEmpty() && TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())",
				"createAuditVertex(false)");
		setter.getBody().getStatements().add(setter.getBody().getStatements().indexOf(collectEdges) + 1, ifStatement);

		OJForStatement removeEdges = (OJForStatement) setter.getBody()
				.findStatement(TinkerAttributeImplementorStrategy.TINKER_MANY_TO_MANY_SETTER_REMOVE_EDGES);

		NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(map.getProperty().getOtherEnd());
		OJTryStatement tryException = buildRemovedAuditEdge(map, otherMap, isComposite);
		removeEdges.getBody().addToStatements(tryException);

	}

	private void addAuditToToOneSetter(INakedClassifier umlOwner, OJAnnotatedClass originalClass, NakedStructuralFeatureMap map) {
		OJAnnotatedOperation setter = (OJAnnotatedOperation) originalClass.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
		OJIfStatement ifStatement1 = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement1.addToThenPart("createAuditVertex(false)");
		setter.getBody().getStatements().add(0, ifStatement1);

		OJIfStatement ifStatement2 = new OJIfStatement(map.getter() + "() != null && TransactionThreadVar.hasNoAuditEntry(" + map.getter()
				+ "().getClass().getName() + " + map.getter() + "().getUid())");
		ifStatement2.addToThenPart(map.getter() + "().createAuditVertex(false)");
		setter.getBody().getStatements().add(1, ifStatement2);

		setter.getBody().getStatements().add(2, new OJSimpleStatement("Vertex edgeRemovedFromAuditVertex = null"));
		OJIfStatement ifStatement = new OJIfStatement(map.getter() + "() != null");
		ifStatement.addToThenPart("edgeRemovedFromAuditVertex = " + map.getter() + "().getAuditVertex()");
		setter.getBody().getStatements().add(3, ifStatement);

		OJIfStatement ifStatement3 = new OJIfStatement(map.umlName() + " != null && TransactionThreadVar.hasNoAuditEntry(" + map.umlName()
				+ ".getClass().getName() + " + map.umlName() + ".getUid())");
		ifStatement3.addToThenPart(map.umlName() + ".createAuditVertex(false)");
		setter.getBody().getStatements().add(4, ifStatement3);

		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerUtil.calculateDirection(map, isComposite);
		originalClass.addToImports(TinkerUtil.graphDbPathName);
		originalClass.addToImports(TinkerUtil.tinkerUtil);
		String associationName = map.getProperty().getAssociation().getName();
		if (isComposite) {
			OJIfStatement ifVarNull = new OJIfStatement(map.umlName() + " != null");

			OJIfStatement existEdge = new OJIfStatement("TinkerUtil.getEdgesBetween(getAuditVertex(), " + map.umlName() + ".getAuditVertex(),\""
					+ associationName + "\").isEmpty()");
			ifVarNull.addToThenPart(existEdge);
			existEdge.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, getAuditVertex()," + map.umlName() + ".getAuditVertex(), \""
					+ associationName + "\")");
			existEdge.addToThenPart("auditEdge.setProperty(\"outClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			existEdge.addToThenPart("auditEdge.setProperty(\"inClass\", " + map.umlName() + ".getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			setter.getBody().addToStatements(ifVarNull);

			OJIfStatement ifEdgeToRemoveNull = new OJIfStatement(
					"edgeRemovedFromAuditVertex != null && TinkerUtil.getEdgesBetween(edgeRemovedFromAuditVertex, getAuditVertex(),\"" + associationName
							+ "\").isEmpty()");
			ifEdgeToRemoveNull.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, getAuditVertex(), edgeRemovedFromAuditVertex, \""
					+ associationName + "\")");
			ifEdgeToRemoveNull.addToThenPart("auditEdge.setProperty(\"outClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifEdgeToRemoveNull.addToThenPart("auditEdge.setProperty(\"inClass\", " + map.javaBaseTypePath().getLast() + ".class.getName() + \""
					+ TinkerAuditCreator.AUDIT + "\")");
			ifEdgeToRemoveNull.addToThenPart("auditEdge.setProperty(\"deletedOn\", TinkerFormatter.format(new Date()))");
			setter.getBody().addToStatements(ifEdgeToRemoveNull);
		} else {
			OJIfStatement ifVarNull = new OJIfStatement(map.umlName() + " != null");
			OJIfStatement existEdge = new OJIfStatement("TinkerUtil.getEdgesBetween(" + map.umlName() + ".getAuditVertex(), getAuditVertex(),\""
					+ associationName + "\").isEmpty()");
			ifVarNull.addToThenPart(existEdge);

			existEdge.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, " + map.umlName() + ".getAuditVertex(), getAuditVertex(),\""
					+ associationName + "\")");
			existEdge.addToThenPart("auditEdge.setProperty(\"outClass\", " + map.umlName() + ".getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			existEdge.addToThenPart("auditEdge.setProperty(\"inClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			setter.getBody().addToStatements(ifVarNull);

			OJIfStatement ifEdgeToRemoveNull = new OJIfStatement(
					"edgeRemovedFromAuditVertex != null  && TinkerUtil.getEdgesBetween(edgeRemovedFromAuditVertex, getAuditVertex(),\"" + associationName
							+ "\").isEmpty()");
			ifEdgeToRemoveNull.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, edgeRemovedFromAuditVertex, getAuditVertex(),\"" + associationName
					+ "\")");
			ifEdgeToRemoveNull.addToThenPart("auditEdge.setProperty(\"outClass\", " + map.javaBaseTypePath().getLast() + ".class.getName() + \""
					+ TinkerAuditCreator.AUDIT + "\")");
			ifEdgeToRemoveNull.addToThenPart("auditEdge.setProperty(\"inClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifEdgeToRemoveNull.addToThenPart("auditEdge.setProperty(\"deletedOn\", TinkerFormatter.format(new Date()))");
			setter.getBody().addToStatements(ifEdgeToRemoveNull);
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

		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "this.vertex.getOutEdges(\"audit\")");

		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.getTryPart().addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"inClass\"))");
		ojTryStatement.getTryPart().addToStatements("result.add((" + auditPath.getLast() + ") c.getConstructor(Vertex.class).newInstance(edge.getInVertex()))");
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("Exception")));
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");

		forStatement.getBody().addToStatements(ojTryStatement);
		getAudits.getBody().addToStatements(forStatement);

		getAudits
				.getBody()
				.addToStatements(
						"Collections.sort(result,new Comparator<TinkerAuditNode>() {@Override public int compare(TinkerAuditNode o1, TinkerAuditNode o2) { return (o1.getTransactionNo()<o2.getTransactionNo() ? -1 : (o1.getTransactionNo()==o2.getTransactionNo() ? 0 : 1)); }})");
		originalClass.addToImports(new OJPathName("java.util.Collections"));
		originalClass.addToImports(new OJPathName("java.util.Comparator"));
		originalClass.addToImports(TinkerUtil.tinkerAuditNodePathName);

		getAudits.getBody().addToStatements("return result");
		originalClass.addToOperations(getAudits);
	}

	private void addCreateAuditVertex(OJAnnotatedClass ojClass, INakedEntity entity) {
		OJAnnotatedOperation createAuditVertex = new OJAnnotatedOperation("createAuditVertex");
		createAuditVertex.setVisibility(OJVisibilityKind.PUBLIC);
		createAuditVertex.addParam("createParentVertex", new OJPathName("boolean"));
		createAuditVertex.getBody().addToStatements("createAuditVertexWithAuditEdge()");
		if (entity.getEndToComposite() != null || entity.getIsAbstract()) {
			OJIfStatement ifCreateParentVertex = new OJIfStatement("createParentVertex", "addEdgeToCompositeOwner()");
			createAuditVertex.getBody().addToStatements(ifCreateParentVertex);
		}
		createAuditVertex.getBody().addToStatements("TransactionAuditThreadVar.addVertex(getAuditVertex())");
		ojClass.addToImports(TinkerUtil.transactionAuditThreadVar);
		ojClass.addToOperations(createAuditVertex);
	}
}