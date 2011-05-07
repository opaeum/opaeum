package net.sf.nakeduml.javageneration.basicjava;

import java.util.Arrays;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
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
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.runtime.domain.TinkerAuditNode;
import org.nakeduml.runtime.domain.TinkerAuditableNode;
import org.nakeduml.runtime.domain.TinkerNode;
import org.util.TinkerFormatter;

public class TinkerAuditTransformation extends AbstractJavaProducingVisitor {

	private static final String PREVIOUS_AUDIT_EDGE = "previous";
	private static final String BASE_AUDIT_TINKER = "org.util.BaseTinkerAuditable";

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
				addCreateAuditVertex(originalClass, c);
				if (c.getEndToComposite()!=null) {
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
				addCreateEdgeToPreviousAudit(originalClass);
				addGetOriginal(ojAuditClass, c);
				if (c.getEndToComposite() != null) {
					addCreateAuditVertexInConstructorWithOwningObject(originalClass, c);
				}
				implementGetAudits(originalClass, c);
				implementGetTransactionNo(ojAuditClass);
				addGetUid(ojAuditClass);
			} else {
				if (c.getEndToComposite() != null) {
					addCreateAuditVertexInConstructorWithOwningObject(originalClass, c);
				}
				//This select hierarchy type pattern
				if (c.getEndToComposite() != null && ((ICompositionParticipant)c.getSupertype()).getEndToComposite()==null) {
					addCreateEdgeToCompositeOwner(originalClass, c);
				}
			}
			if (!c.getIsAbstract()) {
				implementGetPreviousAuditEntry(ojAuditClass);
				implementGetNextAuditEntries(ojAuditClass);
			} else {
				implementAbstractGetPreviousAuditEntry(ojAuditClass);
				implementAbstractGetNextAuditEntries(ojAuditClass);
			}
			if (!c.hasSupertype()) {
				extendsBaseTinkerAuditable(originalClass);
			}
			addContructorWithVertex(ojAuditClass, c);
			implementTinkerNode(ojAuditClass);
			implementTinkerAuditableNode(originalClass);
			implementTinkerAuditNode(ojAuditClass);
			implementIsTinkerRoot(ojAuditClass, c.getEndToComposite()==null);
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitInterface(INakedInterface c) {
		OJAnnotatedInterface myIntf = (OJAnnotatedInterface) findJavaClass(c);
		implementTinkerAuditableNode(myIntf);
		OJAnnotatedInterface auditIntf = (OJAnnotatedInterface) findAuditJavaClass(c);
		implementTinkerAuditNode(auditIntf);
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
	
	@VisitAfter(matchSubclasses = true,match = {INakedEntity.class,INakedStructuredDataType.class,INakedAssociationClass.class})
	public void visitFeature(INakedClassifier entity){
		for(INakedProperty p:entity.getEffectiveAttributes()){
			if(p.getOwner() instanceof INakedInterface && OJUtil.hasOJClass(entity)){
				if(p.getAssociation() instanceof INakedAssociationClass){
					// TODO test this may create duplicates
					// buildAssociationClassLogic(entity,
					// (INakedAssociationClass) p.getAssociation());
				}else{
					visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				}
			}
		}
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
		result.addToElementTypes(ojAuditClass.getPathName());
		getNextAuditEntries.setReturnType(result);
		getNextAuditEntries.getBody().addToStatements("List<"+ojAuditClass.getPathName().getLast()+"> result = new ArrayList<"+ojAuditClass.getPathName().getLast()+">()");
		getNextAuditEntries.getBody().addToStatements("getNextAuditEntriesInternal(result)");
		getNextAuditEntries.getBody().addToStatements("return result");
		ojAuditClass.addToOperations(getNextAuditEntries);
		ojAuditClass.addToImports(new OJPathName("java.util.ArrayList"));
		ojAuditClass.addToImports(new OJPathName("java.util.Iterator"));
		
		OJOperation getNextAuditEntriesInternal = new OJOperation();
		getNextAuditEntriesInternal.setName("getNextAuditEntriesInternal");
		getNextAuditEntriesInternal.addParam("nextAudits", result);
		getNextAuditEntriesInternal.getBody().addToStatements("Iterator<Edge> iter = this.vertex.getInEdges(\"previous\").iterator()");
		
		OJIfStatement ifStatement = new OJIfStatement("iter.hasNext()");
		ifStatement.addToThenPart(ojAuditClass.getPathName().getLast() + " nextAudit = new "+ojAuditClass.getPathName().getLast()+"(iter.next().getOutVertex())");
		ifStatement.addToThenPart("nextAudits.add(nextAudit)");
		ifStatement.addToThenPart("nextAudit.getNextAuditEntriesInternal(nextAudits)");
		getNextAuditEntriesInternal.getBody().addToStatements(ifStatement);
		ojAuditClass.addToOperations(getNextAuditEntriesInternal);
	}

	private void extendsBaseTinkerAuditable(OJAnnotatedClass originalClass) {
		originalClass.setSuperclass(new OJPathName(BASE_AUDIT_TINKER));
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

	private void implementAbstractAddEdgeToCompositeOwner(OJAnnotatedClass ojAuditClass) {
		OJOperation createEdgeToCompositeOwner = new OJOperation();
		createEdgeToCompositeOwner.setName("addEdgeToCompositeOwner");
		createEdgeToCompositeOwner.addParam("createParentVertex", new OJPathName("boolean"));
		createEdgeToCompositeOwner.setAbstract(true);
		ojAuditClass.addToOperations(createEdgeToCompositeOwner);
	}	
	
	private void addCreateEdgeToCompositeOwner(OJAnnotatedClass originalClass, INakedEntity c) {
		OJOperation addEdgeToCompositeOwner = new OJOperation();
		addEdgeToCompositeOwner.setName("addEdgeToCompositeOwner");
		addEdgeToCompositeOwner.addParam("createParentVertex", new OJPathName("boolean"));

		OJField owningAuditVertex = new OJField();
		owningAuditVertex.setName("owningAuditVertex");
		owningAuditVertex.setType(TinkerUtil.vertexPathName);
		addEdgeToCompositeOwner.getBody().addToLocals(owningAuditVertex);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(c.getEndToComposite());
		OJIfStatement ifParentNotNull = new OJIfStatement(map.getter() + "() != null");
		OJIfStatement ifStatement = new OJIfStatement("createParentVertex || !TransactionThreadVar.hasNoAuditEntry(" + map.javaBaseTypePath().getLast()
				+ ".class.getName() + " + map.getter() + "().getUid())");
		ifStatement.addToThenPart("owningAuditVertex = " + map.getter() + "().createAuditVertexWithoutParent()");
		ifParentNotNull.addToThenPart(ifStatement);
		addEdgeToCompositeOwner.getBody().addToStatements(ifParentNotNull);
		String associationName = c.getEndToComposite().getAssociation().getName();
		ifStatement.addToThenPart(
				"createEdgeToOne(owningAuditVertex, false, " + map.getter() + "().getClass(),\"" + associationName + "\")");
		originalClass.addToOperations(addEdgeToCompositeOwner);
		
	}

	private void addAuditVertexGetter(OJAnnotatedClass originalClass) {
		OJOperation getAuditVertex = new OJOperation();
		getAuditVertex.setName("getAuditVertex");
		getAuditVertex.setReturnType(TinkerUtil.vertexPathName);
		getAuditVertex.getBody().addToStatements("return getMostRecentAuditVertex()");
		originalClass.addToOperations(getAuditVertex);
	}	
	
	private void implementGetTransactionNo(OJAnnotatedClass ojAuditClass) {
		OJOperation ojOperation = new OJOperation();
		ojOperation.setName("getTransactionNo");
		ojOperation.setReturnType(new OJPathName("int"));
		ojOperation.getBody().addToStatements("return (Integer)this.vertex.getProperty(\"transactionNo\")");
		ojAuditClass.addToOperations(ojOperation);
	}	

	private void implementTinkerAuditableNode(OJAnnotatedInterface intf) {
		intf.addToSuperInterfaces(new OJPathName(TinkerAuditableNode.class.getName()));
	}
	
	private void implementTinkerAuditableNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName(TinkerAuditableNode.class.getName()));
	}	

	private void implementTinkerAuditNode(OJAnnotatedInterface intf) {
		intf.addToSuperInterfaces(new OJPathName(TinkerAuditNode.class.getName()));
	}
	
	private void implementTinkerAuditNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName(TinkerAuditNode.class.getName()));
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
		constructor.getBody().getStatements().add(constructor.getBody().getStatements().indexOf(ojSimpleStatement)+1,new OJSimpleStatement("createAuditVertex(true)"));
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
		originalClass.addToImports(TinkerUtil.graphDbPathName);
		ifStatement.addToThenPart("Edge auditParentEdge = GraphDb.getDB().addEdge(null, getAuditVertex(), previousAuditVertex, \""
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

	// TODO this needs to move as now it is public api
	private void addCreateAuditVertexWithoutParent(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation createAuditVertexWithoutParent = new OJAnnotatedOperation("createAuditVertexWithoutParent");
		createAuditVertexWithoutParent.setReturnType(TinkerUtil.vertexPathName);
		OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement.addToThenPart("createAuditVertexWithAuditEdge()");
		ifStatement.addToThenPart("createEdgeToPreviousAudit()");
		createAuditVertexWithoutParent.getBody().addToStatements(ifStatement);
		createAuditVertexWithoutParent.getBody().addToStatements("return getAuditVertex()");
		ojClass.addToOperations(createAuditVertexWithoutParent);
	}

	private void addCreateAuditVertexWithAuditEdge(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation createAuditVertexWithAuditEdge = new OJAnnotatedOperation("createAuditVertexWithAuditEdge");
		createAuditVertexWithAuditEdge.setVisibility(OJVisibilityKind.PRIVATE);
		ojClass.addToImports(TinkerUtil.graphDbPathName);
		createAuditVertexWithAuditEdge.getBody().addToStatements("this.auditVertex = GraphDb.getDB().addVertex(null)");
		createAuditVertexWithAuditEdge.getBody().addToStatements("TransactionThreadVar.setNewVertexFalse(getClass().getName() + getUid())");
		createAuditVertexWithAuditEdge.getBody().addToStatements("this.auditVertex.setProperty(\"transactionNo\", GraphDb.getTransactionCount())");
		createAuditVertexWithAuditEdge
				.getBody()
				.addToStatements(
						"Edge auditEdgeToOriginal = GraphDb.getDB().addEdge(null, this.vertex, this.auditVertex, \"audit\" + GraphDb.getTransactionCount())");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"outClass\", this.getClass().getName())");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"inClass\", this.getClass().getName() + \"Audit\")");
		createAuditVertexWithAuditEdge.getBody().addToStatements("copyShallowState(this, this)");
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
		ifStatement.addToThenPart("Integer transaction = Integer.valueOf(label.substring(\"audit\".length()))");
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
		OJOperation adder = owner.findOperation(map.remover(), Arrays.asList(map.javaBaseTypePath()));
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				buildManyRemover(map, otherMap, adder);
			} else {
			}
		} else {
		}
		owner.addToOperations(adder);
		return adder;
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
		isComposite = TinkerAttributeImplementorStrategy.calculateDirection(map, isComposite);
		if (isComposite) {
			ifStatement.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, " + map.umlName() + ".getAuditVertex(), getAuditVertex(),\""
					+ map.getProperty().getAssociation().getName() + "\")");
			ifStatement.addToThenPart("auditEdge.setProperty(\"outClass\", " + map.umlName() + ".getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement.addToThenPart("auditEdge.setProperty(\"inClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
		} else {
			ifStatement.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, getAuditVertex(), " + map.umlName() + ".getAuditVertex(),\""
					+ map.getProperty().getAssociation().getName() + "\")");
			ifStatement.addToThenPart("auditEdge.setProperty(\"outClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement.addToThenPart("auditEdge.setProperty(\"inClass\", " + map.umlName() + ".getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
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
				setter.getBody()
						.addToStatements(
								"getAuditVertex().setProperty(\"" + TinkerUtil.tinkeriseUmlName(map.getProperty().getMappingInfo().getQualifiedUmlName())
										+ "\", name)");
			}
		}
	}

	private void addAuditToManyToManySetter(INakedClassifier umlOwner, OJAnnotatedClass originalClass, NakedStructuralFeatureMap map) {
		OJOperation setter = originalClass.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
		OJForStatement collectEdges = (OJForStatement) setter.getBody().findStatement(
				TinkerAttributeImplementorStrategy.TINKER_MANY_TO_MANY_SETTER_COLLECT_EDGES);

		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerAttributeImplementorStrategy.calculateDirection(map, isComposite);

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

	}

	private void addAuditToToOneSetter(INakedClassifier umlOwner, OJAnnotatedClass originalClass, NakedStructuralFeatureMap map) {
		OJAnnotatedOperation setter = (OJAnnotatedOperation) originalClass.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
		OJIfStatement ifStatement1 = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement1.addToThenPart("createAuditVertex(false)");
		setter.getBody().getStatements().add(0, ifStatement1);
		setter.getBody().getStatements().add(1, new OJSimpleStatement("Vertex edgeRemovedFromAuditVertex = null"));

		OJIfStatement ifStatement2 = new OJIfStatement(map.getter() + "() != null && TransactionThreadVar.hasNoAuditEntry(" + map.getter()
				+ "().getClass().getName() + " + map.getter() + "().getUid())");
		ifStatement2.addToThenPart(map.getter() + "().createAuditVertex(false)");
		ifStatement2.addToThenPart("edgeRemovedFromAuditVertex = "+map.getter()+"().getAuditVertex()");
		setter.getBody().getStatements().add(2, ifStatement2);

		OJIfStatement ifStatement3 = new OJIfStatement(map.umlName() + " != null && TransactionThreadVar.hasNoAuditEntry(" + map.umlName()
				+ ".getClass().getName() + " + map.umlName() + ".getUid())");
		ifStatement3.addToThenPart(map.umlName() + ".createAuditVertex(false)");
		setter.getBody().getStatements().add(3, ifStatement3);

		boolean isComposite = map.getProperty().isComposite();
		isComposite = TinkerAttributeImplementorStrategy.calculateDirection(map, isComposite);
		originalClass.addToImports(TinkerUtil.graphDbPathName);
		if (isComposite) {
			setter.getBody().addToStatements(
					"Iterable<Edge> auditIter = getAuditVertex().getOutEdges(\"" + map.getProperty().getAssociation().getName() + "\")");
			OJIfStatement ifStatement = new OJIfStatement("auditIter.iterator().hasNext()", "GraphDb.getDB().removeEdge(auditIter.iterator().next())");
			setter.getBody().addToStatements(ifStatement);
			OJIfStatement ifStatement4 = new OJIfStatement(map.umlName() + " != null");
			ifStatement4.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, getAuditVertex()," + map.umlName() + ".getAuditVertex(), \""
					+ map.getProperty().getAssociation().getName() + "\")");
			ifStatement4.addToThenPart("auditEdge.setProperty(\"outClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement4.addToThenPart("auditEdge.setProperty(\"inClass\", " + map.umlName() + ".getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			
			OJIfStatement ifStatement5 = new OJIfStatement("edgeRemovedFromAuditVertex != null");
			ifStatement5.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, getAuditVertex(), edgeRemovedFromAuditVertex, \""
					+ map.getProperty().getAssociation().getName() + "\")");
			ifStatement5.addToThenPart("auditEdge.setProperty(\"outClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement5.addToThenPart("auditEdge.setProperty(\"inClass\", " + map.javaBaseTypePath().getLast() + ".class.getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement5.addToThenPart("auditEdge.setProperty(\"deletedOn\", TinkerFormatter.format(new Date()))");
			
			ifStatement4.addToElsePart(ifStatement5);
			
			setter.getBody().addToStatements(ifStatement4);
		} else {
			setter.getBody()
					.addToStatements("Iterable<Edge> auditIter = getAuditVertex().getInEdges(\"" + map.getProperty().getAssociation().getName() + "\")");
			OJIfStatement ifStatement = new OJIfStatement("auditIter.iterator().hasNext()", "GraphDb.getDB().removeEdge(auditIter.iterator().next())");
			setter.getBody().addToStatements(ifStatement);
			OJIfStatement ifStatement4 = new OJIfStatement(map.umlName() + " != null");
			ifStatement4.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, " + map.umlName() + ".getAuditVertex(), getAuditVertex(),\""
					+ map.getProperty().getAssociation().getName() + "\")");
			ifStatement4
					.addToThenPart("auditEdge.setProperty(\"outClass\", " + map.umlName() + ".getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement4.addToThenPart("auditEdge.setProperty(\"inClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");

			OJIfStatement ifStatement5 = new OJIfStatement("edgeRemovedFromAuditVertex != null");
			ifStatement5.addToThenPart("Edge auditEdge = GraphDb.getDB().addEdge(null, edgeRemovedFromAuditVertex, getAuditVertex(),\""
					+ map.getProperty().getAssociation().getName() + "\")");
			ifStatement5
					.addToThenPart("auditEdge.setProperty(\"outClass\", " + map.javaBaseTypePath().getLast() + ".class.getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement5.addToThenPart("auditEdge.setProperty(\"inClass\", this.getClass().getName() + \"" + TinkerAuditCreator.AUDIT + "\")");
			ifStatement5.addToThenPart("auditEdge.setProperty(\"deletedOn\", TinkerFormatter.format(new Date()))");
			
			ifStatement4.addToElsePart(ifStatement5);

			setter.getBody().addToStatements(ifStatement4);
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
		
		getAudits.getBody().addToStatements("Collections.sort(result,new Comparator<TinkerAuditNode>() {@Override public int compare(TinkerAuditNode o1, TinkerAuditNode o2) { return (o1.getTransactionNo()<o2.getTransactionNo() ? -1 : (o1.getTransactionNo()==o2.getTransactionNo() ? 0 : 1)); }})");
		originalClass.addToImports(new OJPathName("java.util.Collections"));
		originalClass.addToImports(new OJPathName("java.util.Comparator"));
		originalClass.addToImports(TinkerUtil.tinkerAuditNodePathName);
		
		getAudits.getBody().addToStatements("return result");
		originalClass.addToOperations(getAudits);
	}

	private void implementTinkerNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName(TinkerNode.class.getName()));
	}

	private void addCreateAuditVertex(OJAnnotatedClass ojClass, INakedEntity entity) {
		OJAnnotatedOperation createAuditVertex = new OJAnnotatedOperation("createAuditVertex");
		createAuditVertex.setVisibility(OJVisibilityKind.PUBLIC);
		createAuditVertex.addParam("createParentVertex", new OJPathName("boolean"));
		createAuditVertex.getBody().addToStatements("createAuditVertexWithAuditEdge()");
		if (entity.getEndToComposite() != null || entity.getIsAbstract()) {
			createAuditVertex.getBody().addToStatements("addEdgeToCompositeOwner(createParentVertex)");
		}
		createAuditVertex.getBody().addToStatements("createEdgeToPreviousAudit()");
		createAuditVertex.getBody().addToStatements("TransactionAuditThreadVar.addVertex(getAuditVertex())");
		ojClass.addToImports(TinkerUtil.transactionAuditThreadVar);
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
