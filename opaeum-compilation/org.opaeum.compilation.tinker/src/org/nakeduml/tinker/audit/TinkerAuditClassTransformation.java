package org.nakeduml.tinker.audit;

import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedSimpleType;

@StepDependency(phase = TinkerAuditPhase.class, requires = { TinkerAuditSuperTypeGenerator.class }, after = { TinkerAuditSuperTypeGenerator.class })
public class TinkerAuditClassTransformation extends AbstractAuditJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass ojAuditClass = findAuditJavaClass(c);
			ojAuditClass.addToImports(TinkerGenerationUtil.introspectionUtilPathName);
			if (c.getGeneralizations().isEmpty()) {
				addVertexFieldWithSetter(ojAuditClass);
				addGetPreviousNextAuditVertexInternal(ojAuditClass, true);
				addGetPreviousNextAuditVertexInternal(ojAuditClass, false);
				addCreateEdgeToPreviousNextAuditInternal(ojAuditClass, true);
				addCreateEdgeToPreviousNextAuditInternal(ojAuditClass, false);
				addIteratorToNext(ojAuditClass);
				addGetOriginal(ojAuditClass, c);
				implementGetTransactionNo(ojAuditClass);
				addGetUid(ojAuditClass);
				addGetOriginalUid(ojAuditClass);
				implementGetSetId(ojAuditClass);
				addGetObjectVersion(ojAuditClass);
				if (c.findAttribute("name") == null) {
					addGetName(c, ojAuditClass);
				}
			}
			if (!c.isAbstract()) {
				implementGetPreviousAuditEntry(ojAuditClass);
				implementGetNextAuditEntry(ojAuditClass);
				implementGetNextAuditEntries(ojAuditClass);
			} else {
				implementAbstractGetPreviousAuditEntry(ojAuditClass);
				implementAbstractGetNextAuditEntries(ojAuditClass);
			}
			addContructorWithVertex(ojAuditClass, c);
			implementTinkerNode(ojAuditClass);
			implementTinkerAuditNode(ojAuditClass);
			implementIsTinkerRoot(ojAuditClass, c.getEndToComposite() == null);
		}
	}

	private void addGetOriginalUid(OJAnnotatedClass ojAuditClass) {
		OJOperation getOriginalUid = new OJOperation();
		getOriginalUid.setName("getOriginalUid");
		getOriginalUid.setReturnType(new OJPathName("String"));
		getOriginalUid.getBody().addToStatements("return (String) this.vertex.getProperty(\"" + TinkerGenerationUtil.ORIGINAL_UID + "\")");
		ojAuditClass.addToOperations(getOriginalUid);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitInterface(INakedInterface c) {
		OJAnnotatedInterface myIntf = (OJAnnotatedInterface) findJavaClass(c);
		implementTinkerAuditableNode(myIntf);
		OJAnnotatedInterface auditIntf = (OJAnnotatedInterface) findAuditJavaClass(c);
		implementTinkerAuditNode(auditIntf);
	}

	private void addGetName(INakedComplexStructure entity, OJClass ojClass) {
		OJAnnotatedOperation getName = new OJAnnotatedOperation("getName");
		getName.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getName.setName("getName");
		getName.setReturnType(new OJPathName("String"));
		getName.setBody(new OJBlock());
		getName.getBody().addToStatements("return \"" + entity.getMappingInfo().getJavaName() + "[\"+getId()+\"]\"");
		ojClass.addToOperations(getName);
	}

	private void addGetObjectVersion(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getObjectVersion = new OJAnnotatedOperation("getObjectVersion");
		getObjectVersion.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getObjectVersion.setReturnType(new OJPathName("int"));
		getObjectVersion.getBody().addToStatements("return TinkerIdUtil.getVersion(this.vertex)");
		ojClass.addToImports(TinkerGenerationUtil.tinkerIdUtilPathName);
		ojClass.addToOperations(getObjectVersion);
	}

	private void implementGetSetId(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getId = new OJAnnotatedOperation("getId");
		getId.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getId.setReturnType(new OJPathName("java.lang.Long"));
		getId.getBody().addToStatements("return TinkerIdUtil.getId(this.vertex)");
		ojClass.addToImports(TinkerGenerationUtil.tinkerIdUtilPathName);
		ojClass.addToOperations(getId);

		OJAnnotatedOperation setId = new OJAnnotatedOperation("setId");
		setId.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		setId.addParam("id", new OJPathName("java.lang.Long"));
		setId.getBody().addToStatements("TinkerIdUtil.setId(this.vertex, id)");
		ojClass.addToOperations(setId);
	}

	private void addIteratorToNext(OJAnnotatedClass ojAuditClass) {
		OJOperation iterateToLatest = new OJOperation();
		iterateToLatest.setName("iterateToLatest");
		iterateToLatest.setReturnType(TinkerGenerationUtil.tinkerAuditNodePathName);
		iterateToLatest.addParam("transactionNo", new OJPathName("java.lang.Long"));
		iterateToLatest.addParam("previous", TinkerGenerationUtil.tinkerAuditNodePathName);
		iterateToLatest.getBody().addToStatements("TinkerAuditNode nextAudit = previous.getNextAuditEntry()");
		OJIfStatement ifNextAuditNotNull = new OJIfStatement("nextAudit!=null");
		OJIfStatement ifTransactionNoSmaller = new OJIfStatement("((transactionNo == -1L) || (nextAudit.getTransactionNo() < transactionNo))",
				"return iterateToLatest(transactionNo, nextAudit)");
		ifTransactionNoSmaller.addToElsePart("return previous");
		ifNextAuditNotNull.addToThenPart(ifTransactionNoSmaller);
		ifNextAuditNotNull.addToElsePart("return previous");
		iterateToLatest.getBody().addToStatements(ifNextAuditNotNull);
		ojAuditClass.addToOperations(iterateToLatest);
	}

	private void addGetPreviousNextAuditVertexInternal(OJAnnotatedClass ojAuditClass, boolean previous) {
		OJOperation getPreviousAuditVertexInternal = new OJOperation();
		getPreviousAuditVertexInternal.setVisibility(OJVisibilityKind.PRIVATE);
		if (previous) {
			getPreviousAuditVertexInternal.setName("getPreviousAuditVertexInternal");
		} else {
			getPreviousAuditVertexInternal.setName("getNextAuditVertexInternal");
		}
		getPreviousAuditVertexInternal.setReturnType(TinkerGenerationUtil.vertexPathName);
		
		OJIfStatement ifHasOriginal = new OJIfStatement("getOriginal()!=null");
		getPreviousAuditVertexInternal.getBody().addToStatements(ifHasOriginal);
		ifHasOriginal.addToElsePart("return null");
		ifHasOriginal.addToThenPart("TreeMap<Long, Edge> auditTransactions = new TreeMap<Long, Edge>()");
		OJForStatement forEdge = new OJForStatement("edge", TinkerGenerationUtil.edgePathName, "getOriginal().getVertex().getOutEdges(\"audit\")");
		forEdge.getBody().addToStatements("Long transaction = (Long) edge.getProperty(\"transactionNo\")");
		forEdge.getBody().addToStatements("auditTransactions.put(transaction, edge)");
		ifHasOriginal.addToThenPart(forEdge);

		OJIfStatement ifNoAudits = new OJIfStatement("!auditTransactions.isEmpty()");
		OJForStatement forAudits;
		if (previous) {
			ifNoAudits.addToThenPart("NavigableSet<Long> descendingKeySet = auditTransactions.descendingKeySet()");
			forAudits = new OJForStatement("auditTransactionNo", new OJPathName("Long"), "descendingKeySet");
		} else {
			ifNoAudits.addToThenPart("NavigableSet<Long> ascendingKeySet = auditTransactions.navigableKeySet()");
			forAudits = new OJForStatement("auditTransactionNo", new OJPathName("Long"), "ascendingKeySet");
		}
		OJIfStatement ifTransactionSmaller = new OJIfStatement();
		if (previous) {
			ifTransactionSmaller.setCondition("auditTransactionNo < getTransactionNo()");
		} else {
			ifTransactionSmaller.setCondition("auditTransactionNo > getTransactionNo()");

		}
		ifTransactionSmaller.addToThenPart("return auditTransactions.get(auditTransactionNo).getInVertex()");
		forAudits.getBody().addToStatements(ifTransactionSmaller);
		ifNoAudits.addToThenPart(forAudits);
		ifNoAudits.addToThenPart("return null");
		ifNoAudits.addToElsePart("return null");
		ifHasOriginal.addToThenPart(ifNoAudits);

		ojAuditClass.addToImports(new OJPathName("java.util.TreeMap"));
		ojAuditClass.addToImports(new OJPathName("java.util.NavigableSet"));
		ojAuditClass.addToOperations(getPreviousAuditVertexInternal);
	}

	private void addCreateEdgeToPreviousNextAuditInternal(OJAnnotatedClass ojAuditClass, boolean previous) {
		OJOperation createEdgeToPreviousAuditInternal = new OJOperation();
		//THis protected scope is more like friendly scope, not for inheritence
		createEdgeToPreviousAuditInternal.setVisibility(OJVisibilityKind.PROTECTED);
		if (previous) {
			createEdgeToPreviousAuditInternal.setName("createEdgeToPreviousAuditInternal");
		} else {
			createEdgeToPreviousAuditInternal.setName("createEdgeToNextAuditInternal");
		}
		createEdgeToPreviousAuditInternal.setReturnType(TinkerGenerationUtil.edgePathName);
		createEdgeToPreviousAuditInternal.getBody().addToStatements(
				"Vertex " + (previous ? "previousAuditVertex  = getPreviousAuditVertexInternal()" : "nextAuditVertex = getNextAuditVertexInternal()"));
		OJIfStatement ifPreviousAuditVertex = new OJIfStatement();

		if (previous) {
			ifPreviousAuditVertex.setCondition("previousAuditVertex != null");
		} else {
			ifPreviousAuditVertex.setCondition("nextAuditVertex != null");
		}

		OJField auditParentEdge = new OJField();
		auditParentEdge.setName("auditParentEdge");
		auditParentEdge.setType(TinkerGenerationUtil.edgePathName);
		ifPreviousAuditVertex.getThenPart().addToLocals(auditParentEdge);

		OJIfStatement isTransactionNotActive = new OJIfStatement("!GraphDb.getDb().isTransactionActive()");
		ifPreviousAuditVertex.getThenPart().addToStatements(isTransactionNotActive);
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.setCatchPart(null);
		isTransactionNotActive.addToThenPart(ojTryStatement);
		ojTryStatement.getTryPart().addToStatements("GraphDb.getDb().startTransaction()");
		ojTryStatement.getFinallyPart().addToStatements("GraphDb.getDb().stopTransaction(Conclusion.SUCCESS)");
		ojAuditClass.addToImports(TinkerGenerationUtil.tinkerConclusionPathName);
		if (previous) {
			ojTryStatement.getTryPart().addToStatements(
					"auditParentEdge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, this.vertex, previousAuditVertex, \"previous\")");
		} else {
			ojTryStatement.getTryPart().addToStatements("auditParentEdge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, nextAuditVertex, this.vertex, \"previous\")");
		}
		ojTryStatement.getTryPart().addToStatements("auditParentEdge.setProperty(\"outClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \"Audit\")");
		ojTryStatement.getTryPart().addToStatements("auditParentEdge.setProperty(\"inClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \"Audit\")");

		if (previous) {
			isTransactionNotActive.addToElsePart("auditParentEdge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, this.vertex, previousAuditVertex, \"previous\")");
		} else {
			isTransactionNotActive.addToElsePart("auditParentEdge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, nextAuditVertex, this.vertex, \"previous\")");
		}
		isTransactionNotActive.addToElsePart("auditParentEdge.setProperty(\"outClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \"Audit\")");
		isTransactionNotActive.addToElsePart("auditParentEdge.setProperty(\"inClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \"Audit\")");

		ifPreviousAuditVertex.addToThenPart("return auditParentEdge");
		ifPreviousAuditVertex.addToElsePart("return null");
		createEdgeToPreviousAuditInternal.getBody().addToStatements(ifPreviousAuditVertex);
		ojAuditClass.addToImports(TinkerGenerationUtil.graphDbPathName);
		ojAuditClass.addToOperations(createEdgeToPreviousAuditInternal);
	}

	private void implementAbstractGetNextAuditEntries(OJAnnotatedClass ojAuditClass) {
		ojAuditClass.addToImports(TinkerGenerationUtil.edgePathName);
		OJOperation getNextAuditEntries = new OJOperation();
		getNextAuditEntries.setName("getNextAuditEntries");
		OJPathName result = new OJPathName("java.util.List");
		OJPathName elementType = new OJPathName(ojAuditClass.getPathName().toJavaString());
		elementType.replaceTail("? extends " + ojAuditClass.getPathName().getLast());
		result.addToElementTypes(elementType);
		getNextAuditEntries.setReturnType(result);
		getNextAuditEntries.setAbstract(true);
		ojAuditClass.addToOperations(getNextAuditEntries);
	}

	private void implementGetNextAuditEntries(OJAnnotatedClass ojAuditClass) {
		ojAuditClass.addToImports(TinkerGenerationUtil.edgePathName);
		OJOperation getNextAuditEntries = new OJOperation();
		getNextAuditEntries.setName("getNextAuditEntries");
		OJPathName result = new OJPathName("java.util.List");
		OJPathName resultElementType = new OJPathName(ojAuditClass.getPathName().toJavaString());
		resultElementType.replaceTail("? extends " + resultElementType.getLast());
		result.addToElementTypes(resultElementType);
		getNextAuditEntries.setReturnType(result);
		getNextAuditEntries.getBody().addToStatements("List<" + ojAuditClass.getPathName().getLast() + "> result = new ArrayList<" + ojAuditClass.getPathName().getLast() + ">()");
		getNextAuditEntries.getBody().addToStatements("getNextAuditEntriesInternal(result)");
		getNextAuditEntries.getBody().addToStatements("return result");
		ojAuditClass.addToOperations(getNextAuditEntries);
		ojAuditClass.addToImports(new OJPathName("java.util.ArrayList"));
		ojAuditClass.addToImports(new OJPathName("java.util.Iterator"));

		OJOperation getNextAuditEntriesInternal = new OJOperation();
		getNextAuditEntriesInternal.setName("getNextAuditEntriesInternal");
		getNextAuditEntriesInternal.setVisibility(OJVisibilityKind.PRIVATE);
		OJPathName resultInternalElementType = new OJPathName("java.util.List");
		resultInternalElementType.addToElementTypes(ojAuditClass.getPathName());
		getNextAuditEntriesInternal.addParam("nextAudits", resultInternalElementType);
		getNextAuditEntriesInternal.getBody().addToStatements("Iterator<Edge> iter = this.vertex.getInEdges(\"previous\").iterator()");

		OJIfStatement ifStatement = new OJIfStatement("iter.hasNext()");
		ifStatement.addToThenPart(ojAuditClass.getPathName().getLast() + " nextAudit = new " + ojAuditClass.getPathName().getLast() + "(iter.next().getOutVertex())");
		ifStatement.addToThenPart("nextAudits.add(nextAudit)");
		ifStatement.addToThenPart("nextAudit.getNextAuditEntriesInternal(nextAudits)");
		getNextAuditEntriesInternal.getBody().addToStatements(ifStatement);
		ojAuditClass.addToOperations(getNextAuditEntriesInternal);
	}

	private void addGetUid(OJAnnotatedClass ojAuditClass) {
		OJOperation getUid = new OJOperation();
		getUid.setName("getUid");
		getUid.setReturnType(new OJPathName("String"));
		getUid.getBody().addToStatements("String uid = (String) this.vertex.getProperty(\"uid\")");
		OJIfStatement ifStatement = new OJIfStatement("uid==null || uid.trim().length()==0");
		ifStatement.addToThenPart("uid=UUID.randomUUID().toString()");
		ifStatement.addToThenPart("this.vertex.setProperty(\"uid\", uid)");
		getUid.getBody().addToStatements(ifStatement);
		getUid.getBody().addToStatements("return uid");
		ojAuditClass.addToOperations(getUid);
		ojAuditClass.addToImports(new OJPathName("java.util.UUID"));
	}

	private void implementIsTinkerRoot(OJAnnotatedClass ojClass, boolean b) {
		OJOperation isRoot = new OJOperation();
		isRoot.setName("isTinkerRoot");
		isRoot.setReturnType(new OJPathName("boolean"));
		isRoot.getBody().addToStatements("return " + b);
		ojClass.addToOperations(isRoot);
	}

	private void implementGetTransactionNo(OJAnnotatedClass ojAuditClass) {
		OJOperation ojOperation = new OJOperation();
		ojOperation.setName("getTransactionNo");
		ojOperation.setReturnType(new OJPathName("java.lang.Long"));
		ojOperation.getBody().addToStatements("return (Long)this.vertex.getProperty(\"transactionNo\")");
		ojAuditClass.addToOperations(ojOperation);
	}

	private void implementTinkerAuditableNode(OJAnnotatedInterface intf) {
		intf.addToSuperInterfaces(new OJPathName("org.nakeduml.runtime.domain.TinkerAuditableNode"));
	}

	private void implementTinkerAuditNode(OJAnnotatedInterface intf) {
		intf.addToSuperInterfaces(new OJPathName("org.nakeduml.runtime.domain.TinkerAuditNode"));
	}

	private void implementTinkerAuditNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName("org.nakeduml.runtime.domain.TinkerAuditNode"));
	}

	private void addGetOriginal(OJAnnotatedClass ojAuditClass, INakedEntity c) {
		OJOperation getOriginal = new OJOperation();
		getOriginal.setName("getOriginal");
		getOriginal.setReturnType(OJUtil.classifierPathname(c));
		getOriginal.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges()");
		OJForStatement forStatement = new OJForStatement("edge", TinkerGenerationUtil.edgePathName, "iter");
		OJIfStatement ifStatement = new OJIfStatement("edge.getLabel().startsWith(\"audit\")");
		forStatement.getBody().addToStatements(ifStatement);
		getOriginal.getBody().addToStatements(forStatement);
		OJTryStatement ojTryStatement = new OJTryStatement();
		OJBlock tryBlock = new OJBlock();
		tryBlock.addToStatements("Class<?> c = Class.forName((String) edge.getProperty(\"outClass\"))");
		tryBlock.addToStatements("return (" + OJUtil.classifierPathname(c).getLast() + ")c.getConstructor(Vertex.class).newInstance(edge.getOutVertex())");
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
		ojAuditClass.addToImports(TinkerGenerationUtil.edgePathName);
		OJOperation getPreviousAuditEntry = new OJOperation();
		getPreviousAuditEntry.setName("getPreviousAuditEntry");
		getPreviousAuditEntry.setReturnType(ojAuditClass.getPathName());
		getPreviousAuditEntry.setAbstract(true);
		ojAuditClass.addToOperations(getPreviousAuditEntry);
	}

	private void implementGetPreviousAuditEntry(OJAnnotatedClass ojAuditClass) {
		ojAuditClass.addToImports(TinkerGenerationUtil.edgePathName);
		OJOperation getPreviousAuditEntry = new OJOperation();
		getPreviousAuditEntry.setName("getPreviousAuditEntry");
		getPreviousAuditEntry.setReturnType(ojAuditClass.getPathName());
		getPreviousAuditEntry.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getOutEdges(\"previous\")");
		OJIfStatement ifIter = new OJIfStatement("!iter.iterator().hasNext()");
		ifIter.addToThenPart("Edge previousEdge = createEdgeToPreviousAuditInternal()");
		OJIfStatement ifHasPrevious = new OJIfStatement("previousEdge != null");
		ifHasPrevious.addToThenPart("return new " + ojAuditClass.getName() + "(previousEdge.getInVertex())");
		ifHasPrevious.addToElsePart("return null");
		ifIter.addToThenPart(ifHasPrevious);

		ifIter.addToElsePart("return new " + ojAuditClass.getName() + "(this.vertex.getOutEdges(\"previous\").iterator().next().getInVertex())");
		getPreviousAuditEntry.getBody().addToStatements(ifIter);
		ojAuditClass.addToOperations(getPreviousAuditEntry);
	}

	private void implementGetNextAuditEntry(OJAnnotatedClass ojAuditClass) {
		ojAuditClass.addToImports(TinkerGenerationUtil.edgePathName);
		OJOperation getPreviousAuditEntry = new OJOperation();
		getPreviousAuditEntry.setName("getNextAuditEntry");
		getPreviousAuditEntry.setReturnType(ojAuditClass.getPathName());
		getPreviousAuditEntry.getBody().addToStatements("Iterable<Edge> iter = this.vertex.getInEdges(\"previous\")");
		OJIfStatement ifIter = new OJIfStatement("!iter.iterator().hasNext()");
		ifIter.addToThenPart("Edge nextEdge = createEdgeToNextAuditInternal()");
		OJIfStatement ifHasPrevious = new OJIfStatement("nextEdge != null");
		ifHasPrevious.addToThenPart("return new " + ojAuditClass.getName() + "(nextEdge.getOutVertex())");
		ifHasPrevious.addToElsePart("return null");
		ifIter.addToThenPart(ifHasPrevious);

		ifIter.addToElsePart("return new " + ojAuditClass.getName() + "(this.vertex.getInEdges(\"previous\").iterator().next().getOutVertex())");
		getPreviousAuditEntry.getBody().addToStatements(ifIter);
		ojAuditClass.addToOperations(getPreviousAuditEntry);
	}

	private void implementTinkerNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName("org.nakeduml.runtime.domain.TinkerNode"));
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
		OJPathName underlyingVertexPath = new OJPathName("com.tinkerpop.blueprints.pgm.Vertex");
		OJField vertexField = new OJAnnotatedField("vertex", underlyingVertexPath);
		vertexField.setVisibility(OJVisibilityKind.PROTECTED);
		ojClass.addToFields(vertexField);
		OJOperation setter = new OJAnnotatedOperation("getVertex");
		setter.setName("getVertex");
		setter.setReturnType(underlyingVertexPath);
		setter.getBody().addToStatements("return this.vertex");
		ojClass.addToOperations(setter);
	}

}
