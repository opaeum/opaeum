package org.nakeduml.tinker.auditing.tinker;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.validation.namegeneration.AbstractJavaNameGenerator;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.tinker.basicjava.tinker.TinkerUtil;

public class TinkerAuditAuditClassTransformation extends AbstractJavaProducingVisitor {

	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass ojAuditClass = findAuditJavaClass(c);
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
			}
			if (!c.getIsAbstract()) {
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

	@VisitAfter(matchSubclasses = true)
	public void visitInterface(INakedInterface c) {
		OJAnnotatedInterface myIntf = (OJAnnotatedInterface) findJavaClass(c);
		implementTinkerAuditableNode(myIntf);
		OJAnnotatedInterface auditIntf = (OJAnnotatedInterface) findAuditJavaClass(c);
		implementTinkerAuditNode(auditIntf);
	}
	
	private void addIteratorToNext(OJAnnotatedClass ojAuditClass) {
		OJOperation iterateToLatest = new OJOperation();
		iterateToLatest.setName("iterateToLatest");
		iterateToLatest.setReturnType(TinkerUtil.tinkerAuditNodePathName);
		iterateToLatest.addParam("transactionNo", new OJPathName("int"));
		iterateToLatest.addParam("previous", TinkerUtil.tinkerAuditNodePathName);
		iterateToLatest.getBody().addToStatements("TinkerAuditNode nextAudit = previous.getNextAuditEntry()");
		OJIfStatement ifNextAuditNotNull = new OJIfStatement("nextAudit!=null");
		OJIfStatement ifTransactionNoSmaller = new OJIfStatement("((transactionNo == -1) || (nextAudit.getTransactionNo() < transactionNo))", "return iterateToLatest(transactionNo, nextAudit)");
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
		getPreviousAuditVertexInternal.setReturnType(TinkerUtil.vertexPathName);
		getPreviousAuditVertexInternal.getBody().addToStatements("TreeMap<Integer, Edge> auditTransactions = new TreeMap<Integer, Edge>()");
		OJForStatement forEdge = new OJForStatement("edge", TinkerUtil.edgePathName, "getOriginal().getVertex().getOutEdges(\"audit\")");
		forEdge.getBody().addToStatements("Integer transaction = (Integer) edge.getProperty(\"transactionNo\")");
		forEdge.getBody().addToStatements("auditTransactions.put(transaction, edge)");
		getPreviousAuditVertexInternal.getBody().addToStatements(forEdge);

		OJIfStatement ifNoAudits = new OJIfStatement("!auditTransactions.isEmpty()");
		OJForStatement forAudits;
		if (previous) {
			ifNoAudits.addToThenPart("NavigableSet<Integer> descendingKeySet = auditTransactions.descendingKeySet()");
			forAudits = new OJForStatement("auditTransactionNo", new OJPathName("Integer"), "descendingKeySet");
		} else {
			ifNoAudits.addToThenPart("NavigableSet<Integer> ascendingKeySet = auditTransactions.navigableKeySet()");
			forAudits = new OJForStatement("auditTransactionNo", new OJPathName("Integer"), "ascendingKeySet");
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
		getPreviousAuditVertexInternal.getBody().addToStatements(ifNoAudits);

		ojAuditClass.addToImports(new OJPathName("java.util.TreeMap"));
		ojAuditClass.addToImports(new OJPathName("java.util.NavigableSet"));
		ojAuditClass.addToOperations(getPreviousAuditVertexInternal);
	}

	private void addCreateEdgeToPreviousNextAuditInternal(OJAnnotatedClass ojAuditClass, boolean previous) {
		OJOperation createEdgeToPreviousAuditInternal = new OJOperation();
		createEdgeToPreviousAuditInternal.setVisibility(OJVisibilityKind.PROTECTED);
		if (previous) {
			createEdgeToPreviousAuditInternal.setName("createEdgeToPreviousAuditInternal");
		} else {
			createEdgeToPreviousAuditInternal.setName("createEdgeToNextAuditInternal");
		}
		createEdgeToPreviousAuditInternal.setReturnType(TinkerUtil.edgePathName);
		createEdgeToPreviousAuditInternal.getBody().addToStatements(
				"Vertex " + (previous ? "previousAuditVertex  = getPreviousAuditVertexInternal()" : "nextAuditVertex = getNextAuditVertexInternal()"));
		OJIfStatement ifPreviousAuditVertex = new OJIfStatement();
		if (previous) {
			ifPreviousAuditVertex.setCondition("previousAuditVertex != null");
			ifPreviousAuditVertex.addToThenPart("Edge auditParentEdge = GraphDb.getDB().addEdge(null, this.vertex, previousAuditVertex, \"previous\")");
		} else {
			ifPreviousAuditVertex.setCondition("nextAuditVertex != null");
			ifPreviousAuditVertex.addToThenPart("Edge auditParentEdge = GraphDb.getDB().addEdge(null, nextAuditVertex, this.vertex, \"previous\")");
		}
		ifPreviousAuditVertex.addToThenPart("auditParentEdge.setProperty(\"outClass\", this.getClass().getName() + \"Audit\")");
		ifPreviousAuditVertex.addToThenPart("auditParentEdge.setProperty(\"inClass\", this.getClass().getName() + \"Audit\")");
		ifPreviousAuditVertex.addToThenPart("return auditParentEdge");
		ifPreviousAuditVertex.addToElsePart("return null");
		createEdgeToPreviousAuditInternal.getBody().addToStatements(ifPreviousAuditVertex);
		ojAuditClass.addToImports(TinkerUtil.graphDbPathName);
		ojAuditClass.addToOperations(createEdgeToPreviousAuditInternal);
	}	

	private void implementAbstractGetNextAuditEntries(OJAnnotatedClass ojAuditClass) {
		ojAuditClass.addToImports(TinkerUtil.edgePathName);
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
		ojAuditClass.addToImports(TinkerUtil.edgePathName);
		OJOperation getNextAuditEntries = new OJOperation();
		getNextAuditEntries.setName("getNextAuditEntries");
		OJPathName result = new OJPathName("java.util.List");
		OJPathName resultElementType = new OJPathName(ojAuditClass.getPathName().toJavaString());
		resultElementType.replaceTail("? extends " + resultElementType.getLast());
		result.addToElementTypes(resultElementType);
		getNextAuditEntries.setReturnType(result);
		getNextAuditEntries.getBody().addToStatements(
				"List<" + ojAuditClass.getPathName().getLast() + "> result = new ArrayList<" + ojAuditClass.getPathName().getLast() + ">()");
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
		ifStatement.addToThenPart(ojAuditClass.getPathName().getLast() + " nextAudit = new " + ojAuditClass.getPathName().getLast()
				+ "(iter.next().getOutVertex())");
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
		ojOperation.setReturnType(new OJPathName("int"));
		ojOperation.getBody().addToStatements("return (Integer)this.vertex.getProperty(\"transactionNo\")");
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
		OJForStatement forStatement = new OJForStatement("edge", TinkerUtil.edgePathName, "iter");
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
		ojAuditClass.addToImports(TinkerUtil.edgePathName);
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