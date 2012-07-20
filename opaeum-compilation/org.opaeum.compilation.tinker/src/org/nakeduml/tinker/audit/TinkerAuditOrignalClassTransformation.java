package org.nakeduml.tinker.audit;

import java.util.Arrays;
import java.util.Collections;

import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = TinkerAuditPhase.class, requires = { TinkerAuditClassTransformation.class, TinkerAuditCopyMethodImplementor.class }, after = { TinkerAuditClassTransformation.class })
public class TinkerAuditOrignalClassTransformation extends AbstractAuditJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			OJAnnotatedClass originalClass = findJavaClass(c);
			originalClass.addToImports(TinkerGenerationUtil.transactionThreadEntityVar);
			originalClass.addToImports(TinkerGenerationUtil.transactionThreadVar);
			originalClass.addToImports(TinkerGenerationUtil.edgePathName);
			if (!c.hasSupertype()) {
				extendsBaseTinkerAuditable(originalClass);
			}
			implementTinkerAuditableNode(originalClass);
			addPreviousEdgesOnMarkDeleted(originalClass, c);
			if (c.getGeneralizations().isEmpty()) {
				addCreateEdgeToOne(originalClass);
				addCreateAuditVertex(originalClass, c);
				if (c.getEndToComposite() != null) {
					// addCreateEdgeToCompositeOwner(originalClass, c);
				} else {
					if (c.isAbstract()) {
						// implementAbstractAddEdgeToCompositeOwner(originalClass);
					}
				}

				addGetMostRecentAuditVertex(originalClass);
				addCreateAuditVertexWithAuditEdge(c, originalClass);
				addAuditVertexGetter(originalClass);
				addCreateAuditVertexWithoutParent(originalClass);
				addGetPreviousAuditVertex(originalClass);
				if (c.getEndToComposite() != null) {
					// addCreateAuditVertexInConstructorWithOwningObject(originalClass,
					// c);
				}
				implementGetAudits(originalClass, c);
			} else {
				if (c.getEndToComposite() != null) {
					// addCreateAuditVertexInConstructorWithOwningObject(originalClass,
					// c);
				}
				// This select hierarchy type pattern
				if (c.getEndToComposite() != null && ((ICompositionParticipant) c.getSupertype()).getEndToComposite() == null) {
					// addCreateEdgeToCompositeOwner(originalClass, c);
				}
			}
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		if (OJUtil.hasOJClass(p.getOwner())) {
			// if (p.getAssociation() instanceof INakedAssociationClass) {
			// } else {
			visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			// }
		}
	}

	@VisitAfter(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class })
	public void visitFeature(INakedClassifier entity) {
		for (INakedProperty p : entity.getEffectiveAttributes()) {
			if (p.getOwner() instanceof INakedInterface && OJUtil.hasOJClass(entity)) {
				// if (p.getAssociation() instanceof INakedAssociationClass) {
				// // TODO test this may create duplicates
				// // buildAssociationClassLogic(entity,
				// // (INakedAssociationClass) p.getAssociation());
				// } else {
				visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				// }
			}
		}
	}

	private void addPreviousEdgesOnMarkDeleted(OJAnnotatedClass originalClass, INakedEntity c) {
		OJOperation markDeleted = originalClass.findOperation("markDeleted", Collections.EMPTY_LIST);

		OJPathName auditListPathName = new OJPathName("java.util.List");
		auditListPathName.addToElementTypes(new OJPathName(OJUtil.classifierPathname(getSuperSuperType(c)).toJavaString() + "Audit"));

		OJSimpleStatement auditList = new OJSimpleStatement(auditListPathName.getCollectionTypeName() + " audits " + " = getAudits()");
		markDeleted.getBody().getStatements().add(markDeleted.getBody().getStatements().size() - 1, auditList);

		OJSimpleStatement addEdgeToPrevious = new OJSimpleStatement("audits.get(audits.size()-1).createEdgeToPreviousAuditInternal()");
		markDeleted.getBody().getStatements().add(markDeleted.getBody().getStatements().size() - 1, addEdgeToPrevious);
	}

	private INakedClassifier getSuperSuperType(INakedClassifier c) {
		if (c.getSupertype() == null) {
			return c;
		} else {
			return getSuperSuperType(c.getSupertype());
		}
	}

	private void addCreateAuditVertexWithAuditEdge(INakedEntity c, OJAnnotatedClass ojClass) {
		OJAnnotatedOperation createAuditVertexWithAuditEdge = new OJAnnotatedOperation("createAuditVertexWithAuditEdge");
		createAuditVertexWithAuditEdge.setVisibility(OJVisibilityKind.PRIVATE);
		ojClass.addToImports(TinkerGenerationUtil.graphDbPathName);
		createAuditVertexWithAuditEdge.getBody().addToStatements(
				"this.auditVertex = " + TinkerGenerationUtil.graphDbAccess + ".addVertex(\"" + TinkerGenerationUtil.getClassMetaId(ojClass) + "Audit\")");
		createAuditVertexWithAuditEdge.getBody().addToStatements("TransactionThreadVar.putAuditVertexFalse(getClass().getName() + getUid(), this.auditVertex)");
		createAuditVertexWithAuditEdge.getBody().addToStatements(
				"this.auditVertex.setProperty(\"transactionNo\", " + TinkerGenerationUtil.graphDbAccess + ".getTransactionCount())");
		createAuditVertexWithAuditEdge.getBody().addToStatements(
				"Edge auditEdgeToOriginal = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, this.vertex, this.auditVertex, \"audit\")");
		createAuditVertexWithAuditEdge.getBody().addToStatements(
				"auditEdgeToOriginal.setProperty(\"transactionNo\", " + TinkerGenerationUtil.graphDbAccess + ".getTransactionCount())");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"outClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + ")");
		createAuditVertexWithAuditEdge.getBody().addToStatements("auditEdgeToOriginal.setProperty(\"inClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \"Audit\")");
		createAuditVertexWithAuditEdge.getBody().addToStatements("copyAuditShallowState(this, this)");
		ojClass.addToOperations(createAuditVertexWithAuditEdge);
	}

	private void implementTinkerAuditableNode(OJAnnotatedClass ojClass) {
		ojClass.addToImplementedInterfaces(new OJPathName("org.nakeduml.runtime.domain.TinkerAuditableNode"));
	}

	private void extendsBaseTinkerAuditable(OJAnnotatedClass originalClass) {
		originalClass.setSuperclass(new OJPathName(TinkerGenerationUtil.BASE_AUDIT_TINKER));
	}

	private void addAuditVertexGetter(OJAnnotatedClass originalClass) {
		OJOperation getAuditVertex = new OJOperation();
		getAuditVertex.setName("getAuditVertex");
		getAuditVertex.setReturnType(TinkerGenerationUtil.vertexPathName);
		getAuditVertex.getBody().addToStatements("Vertex auditVertex = TransactionThreadVar.getAuditEntry(getClass().getName() + getUid())");
		OJIfStatement hasAuditVertex = new OJIfStatement("auditVertex==null", "return getMostRecentAuditVertex()");
		hasAuditVertex.addToElsePart("return auditVertex");
		getAuditVertex.getBody().addToStatements(hasAuditVertex);
		originalClass.addToOperations(getAuditVertex);
	}

	private void addCreateEdgeToCompositeOwner(OJAnnotatedClass originalClass, INakedEntity c) {
		OJOperation addEdgeToCompositeOwner = new OJOperation();
		addEdgeToCompositeOwner.setName("addEdgeToCompositeOwner");
		OJField owningAuditVertex = new OJField();
		owningAuditVertex.setName("owningAuditVertex");
		owningAuditVertex.setType(TinkerGenerationUtil.vertexPathName);
		addEdgeToCompositeOwner.getBody().addToLocals(owningAuditVertex);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(c.getEndToComposite());
		addEdgeToCompositeOwner.getBody().addToStatements("owningAuditVertex = " + map.getter() + "().createAuditVertexWithoutParent()");
		String associationName = c.getEndToComposite().getAssociation().getName();
		addEdgeToCompositeOwner.getBody().addToStatements("createEdgeToOne(owningAuditVertex, false, " + map.getter() + "().getClass(),\"" + associationName + "\")");
		originalClass.addToOperations(addEdgeToCompositeOwner);

	}

	private void addCreateEdgeToOne(OJAnnotatedClass originalClass) {
		originalClass.addToImports(new OJPathName("java.util.Iterator"));
		OJOperation createEdgeToOne = new OJOperation();
		createEdgeToOne.setName("createEdgeToOne");
		createEdgeToOne.setReturnType(TinkerGenerationUtil.edgePathName);
		createEdgeToOne.addParam("oneAuditVertex", TinkerGenerationUtil.vertexPathName);
		createEdgeToOne.addParam("inverse", new OJPathName("Boolean"));
		createEdgeToOne.addParam("clazz", new OJPathName("Class<?>"));
		createEdgeToOne.addParam("label", new OJPathName("String"));

		OJField returnAuditEdge = new OJField();
		returnAuditEdge.setName("auditParentEdge");
		returnAuditEdge.setType(TinkerGenerationUtil.edgePathName);
		createEdgeToOne.getBody().addToLocals(returnAuditEdge);
		returnAuditEdge.setInitExp("null");

		OJIfStatement ifStatement = new OJIfStatement("!inverse");
		originalClass.addToImports(TinkerGenerationUtil.graphDbPathName);
		// ifStatement.addToThenPart("Iterator<Edge> inIter = getAuditVertex().getInEdges(label).iterator()");
		// OJIfStatement ifOneInEdgeAllreadyExist = new
		// OJIfStatement("!inIter.hasNext()");
		// ifOneInEdgeAllreadyExist.addToThenPart("auditParentEdge = " +
		// TinkerGenerationUtil.graphDbAccess +
		// ".addEdge(null, oneAuditVertex, getAuditVertex(), label)");
		// ifOneInEdgeAllreadyExist.addToThenPart("auditParentEdge.setProperty(\"outClass\",clazz.getName() + \""
		// + TinkerAuditGenerationUtil.AUDIT + "\")");
		// ifOneInEdgeAllreadyExist.addToThenPart("auditParentEdge.setProperty(\"inClass\","
		// + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \"" +
		// TinkerAuditGenerationUtil.AUDIT
		// + "\")");
		// ifStatement.addToThenPart(ifOneInEdgeAllreadyExist);

		ifStatement.addToThenPart("auditParentEdge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, oneAuditVertex, getAuditVertex(), label)");
		ifStatement.addToThenPart("auditParentEdge.setProperty(\"outClass\",clazz.getName() + \"" + TinkerAuditGenerationUtil.AUDIT + "\")");
		ifStatement.addToThenPart("auditParentEdge.setProperty(\"inClass\"," + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \"" + TinkerAuditGenerationUtil.AUDIT + "\")");

		// ifStatement.addToElsePart("Iterator<Edge> outIter = getAuditVertex().getOutEdges(label).iterator()");
		// OJIfStatement ifOneOutEdgeAllreadyExist = new
		// OJIfStatement("!outIter.hasNext()");
		// ifOneOutEdgeAllreadyExist.addToThenPart("auditParentEdge = " +
		// TinkerGenerationUtil.graphDbAccess +
		// ".addEdge(null, getAuditVertex(), oneAuditVertex, label)");
		// ifOneOutEdgeAllreadyExist.addToThenPart("auditParentEdge.setProperty(\"outClass\", "
		// + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \""
		// + TinkerAuditGenerationUtil.AUDIT + "\")");
		// ifOneOutEdgeAllreadyExist.addToThenPart("auditParentEdge.setProperty(\"inClass\", clazz.getName() + \""
		// + TinkerAuditGenerationUtil.AUDIT + "\")");
		// ifStatement.addToElsePart(ifOneOutEdgeAllreadyExist);

		ifStatement.addToElsePart("auditParentEdge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, getAuditVertex(), oneAuditVertex, label)");
		ifStatement.addToElsePart("auditParentEdge.setProperty(\"outClass\", " + TinkerGenerationUtil.TINKER_GET_CLASSNAME + " + \"" + TinkerAuditGenerationUtil.AUDIT + "\")");
		ifStatement.addToElsePart("auditParentEdge.setProperty(\"inClass\", clazz.getName() + \"" + TinkerAuditGenerationUtil.AUDIT + "\")");

		createEdgeToOne.getBody().addToStatements(ifStatement);
		createEdgeToOne.getBody().addToStatements("return auditParentEdge");
		originalClass.addToOperations(createEdgeToOne);
	}

	// private void
	// addCreateAuditVertexInConstructorWithOwningObject(OJAnnotatedClass
	// originalClass, INakedEntity c) {
	// NakedStructuralFeatureMap map = new
	// NakedStructuralFeatureMap(c.getEndToComposite());
	// OJConstructor constructor =
	// originalClass.findConstructor(map.javaBaseTypePath());
	// constructor.getBody().addToStatements("createAuditVertex(true)");
	// }

	private void addGetPreviousAuditVertex(OJAnnotatedClass originalClass) {
		OJAnnotatedOperation getPreviousAuditVertex = new OJAnnotatedOperation("getPreviousAuditVertex");
		getPreviousAuditVertex.setVisibility(OJVisibilityKind.PRIVATE);
		getPreviousAuditVertex.setReturnType(TinkerGenerationUtil.vertexPathName);
		originalClass.addToImports(new OJPathName("java.util.TreeMap"));
		getPreviousAuditVertex.getBody().addToStatements(new OJSimpleStatement("TreeMap<Integer, Edge> auditTransactions = new TreeMap<Integer, Edge>()"));
		OJForStatement forStatement = new OJForStatement("edge", TinkerGenerationUtil.edgePathName, "this.vertex.getOutEdges(\"audit\")");
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
		createAuditVertexWithoutParent.setReturnType(TinkerGenerationUtil.vertexPathName);
		OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement.addToThenPart("createAuditVertexWithAuditEdge()");
		// ifStatement.addToThenPart("createEdgeToPreviousAudit()");
		createAuditVertexWithoutParent.getBody().addToStatements(ifStatement);
		createAuditVertexWithoutParent.getBody().addToStatements("return getAuditVertex()");
		ojClass.addToOperations(createAuditVertexWithoutParent);
	}

	private void addGetMostRecentAuditVertex(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation getMostRecentAuditVertex = new OJAnnotatedOperation("getMostRecentAuditVertex");
		getMostRecentAuditVertex.setVisibility(OJVisibilityKind.PRIVATE);
		getMostRecentAuditVertex.setReturnType(TinkerGenerationUtil.vertexPathName);
		ojClass.addToImports(new OJPathName("java.util.TreeMap"));
		getMostRecentAuditVertex.getBody().addToStatements(new OJSimpleStatement("TreeMap<Integer, Edge> auditTransactions = new TreeMap<Integer, Edge>()"));
		OJForStatement forStatement = new OJForStatement("edge", TinkerGenerationUtil.edgePathName, "this.vertex.getOutEdges(\"audit\")");
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
				if ((map.isOne() /*
								 * && map.getProperty().getBaseType() instanceof
								 * INakedEnumeration) || map.isJavaPrimitive()
								 * || map.isUmlPrimitive()
								 */)) {
					implementAttributeFully(umlOwner, map);
				}
			}
		}
	}

	private void implementAttributeFully(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		auditInternalAdder(umlOwner, map);
		if (map.isOneToOne() /*
							 * && (!map.getProperty().isInverse() ||
							 * map.getProperty().getOtherEnd() == null)
							 */) {
			auditInternalRemover(umlOwner, map);
		}
	}

	private void auditInternalRemover(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJOperation internalRemover = owner.findOperation(map.internalRemover(), Arrays.asList(map.javaBaseTypePath()));
		OJBlock s = internalRemover.getBody();
		OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement.addToThenPart("createAuditVertex(false)");

		OJIfStatement ifStatement2 = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(val.getClass().getName() + val.getUid())");
		ifStatement2.addToThenPart("val.createAuditVertex(false)");

		if (!map.getProperty().isInverse() || (map.getProperty().getOtherEnd() == null && map.getProperty().getBaseType() instanceof INakedEntity)) {
			s = ((OJIfStatement) internalRemover.getBody().findStatement(TinkerAttributeImplementor.ITER_HAS_NEXT)).getThenPart();
			s.addToStatements(ifStatement);
			s.addToStatements(ifStatement2);
			s.addToStatements("Edge auditOfRemovedEdge = createEdgeToOne(val.getAuditVertex(), " + map.getProperty().isInverse() + ", val.getClass(), \""
					+ TinkerGenerationUtil.getEdgeName(map) + "\")");
			s.addToStatements("auditOfRemovedEdge.setProperty(\"deletedOn\", TinkerFormatter.format(new Date()))");
			owner.addToImports(TinkerGenerationUtil.tinkerFormatter);
			owner.addToImports(new OJPathName("java.util.Date"));
		}
	}

	private void auditInternalAdder(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJOperation internalAdder = owner.findOperation(map.internalAdder(), Arrays.asList(map.javaBaseTypePath()));
		OJBlock s = internalAdder.getBody();
		OJIfStatement ifStatement = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(getClass().getName() + getUid())");
		ifStatement.addToThenPart("createAuditVertex(false)");

		OJIfStatement ifStatement2 = new OJIfStatement("TransactionThreadVar.hasNoAuditEntry(val.getClass().getName() + val.getUid())");
		ifStatement2.addToThenPart("val.createAuditVertex(false)");

		if (map.getProperty().isInverse() || (map.getProperty().getOtherEnd() == null && map.getProperty().getBaseType() instanceof INakedEntity)) {
			s = ((OJIfStatement) internalAdder.getBody().findStatement(AttributeImplementor.IF_PARAM_NOT_NULL)).getThenPart();
			s.addToStatements(ifStatement);
			s.addToStatements(ifStatement2);
			s.addToStatements("createEdgeToOne(val.getAuditVertex(), true, val.getClass(), \"" + TinkerGenerationUtil.getEdgeName(map) + "\")");
		} else if (!map.getProperty().isInverse() && map.getProperty().getOtherEnd() == null) {
			s.addToStatements(ifStatement);
			internalAdder.getBody().addToStatements(TinkerGenerationUtil.addSetterForSimpleType(map, true));
		}
	}

	private OJTryStatement buildRemovedAuditEdge(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, boolean isComposite) {
		OJTryStatement tryException = new OJTryStatement();

		if (!isComposite) {
			tryException.getTryPart().addToStatements(
					"Class<" + otherMap.javaBaseTypePath().getLast() + "> inClazz = (Class<" + otherMap.javaBaseTypePath().getLast()
							+ ">) Class.forName((String)edge.getProperty(\"inClass\"))");
			tryException.getTryPart().addToStatements("Constructor<" + otherMap.javaBaseTypePath().getLast() + "> inConstructor = inClazz.getConstructor(Vertex.class)");
			tryException.getTryPart().addToStatements(otherMap.javaBaseTypePath().getLast() + " manyToRemoveIn = inConstructor.newInstance(edge.getInVertex())");
			tryException.getTryPart().addToStatements(
					"Class<" + map.javaBaseTypePath().getLast() + "> outClazz = (Class<" + map.javaBaseTypePath().getLast()
							+ ">) Class.forName((String)edge.getProperty(\"outClass\"))");
			tryException.getTryPart().addToStatements("Constructor<" + map.javaBaseTypePath().getLast() + "> outConstructor = outClazz.getConstructor(Vertex.class)");
			tryException.getTryPart().addToStatements(map.javaBaseTypePath().getLast() + " manyToRemoveOut = outConstructor.newInstance(edge.getOutVertex())");
			tryException.getTryPart().addToStatements(
					"Edge auditEdge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, manyToRemoveOut.getAuditVertex(), manyToRemoveIn.getAuditVertex(),\""
							+ map.getProperty().getAssociation().getName() + "\")");

			tryException.getTryPart().addToStatements("auditEdge.setProperty(\"inClass\", edge.getProperty(\"inClass\")" + "+ \"Audit\")");
			tryException.getTryPart().addToStatements("auditEdge.setProperty(\"outClass\", edge.getProperty(\"outClass\")" + "+ \"Audit\")");
		} else {
			tryException.getTryPart().addToStatements(
					"Class<" + map.javaBaseTypePath().getLast() + "> inClazz = (Class<" + map.javaBaseTypePath().getLast()
							+ ">) Class.forName((String)edge.getProperty(\"inClass\"))");
			tryException.getTryPart().addToStatements("Constructor<" + map.javaBaseTypePath().getLast() + "> inConstructor = inClazz.getConstructor(Vertex.class)");
			tryException.getTryPart().addToStatements(map.javaBaseTypePath().getLast() + " manyToRemoveIn = inConstructor.newInstance(edge.getInVertex())");
			tryException.getTryPart().addToStatements(
					"Class<" + otherMap.javaBaseTypePath().getLast() + "> outClazz = (Class<" + otherMap.javaBaseTypePath().getLast()
							+ ">) Class.forName((String)edge.getProperty(\"outClass\"))");
			tryException.getTryPart().addToStatements("Constructor<" + otherMap.javaBaseTypePath().getLast() + "> outConstructor = outClazz.getConstructor(Vertex.class)");
			tryException.getTryPart().addToStatements(otherMap.javaBaseTypePath().getLast() + " manyToRemoveOut = outConstructor.newInstance(edge.getOutVertex())");
			tryException.getTryPart().addToStatements(
					"Edge auditEdge = " + TinkerGenerationUtil.graphDbAccess + ".addEdge(null, manyToRemoveOut.getAuditVertex(), manyToRemoveIn.getAuditVertex(),\""
							+ map.getProperty().getAssociation().getName() + "\")");

			tryException.getTryPart().addToStatements("auditEdge.setProperty(\"inClass\", edge.getProperty(\"inClass\")" + "+ \"Audit\")");
			tryException.getTryPart().addToStatements("auditEdge.setProperty(\"outClass\", edge.getProperty(\"outClass\")" + "+ \"Audit\")");
		}
		tryException.getTryPart().addToStatements("auditEdge.setProperty(\"deletedOn\", TinkerFormatter.format(new Date()))");
		OJParameter catchParam = new OJParameter("e", new OJPathName("java.lang.Exception"));
		tryException.setCatchParam(catchParam);
		tryException.getCatchPart().addToStatements("throw new RuntimeException(e)");
		return tryException;
	}

	private void implementGetAudits(OJAnnotatedClass originalClass, INakedEntity c) {
		OJAnnotatedOperation getAudits = new OJAnnotatedOperation("getAudits");
		OJField result = new OJField();
		result.setName("result");
		OJPathName resultPathName = new OJPathName("java.util.List");
		OJPathName auditPath = OJUtil.classifierPathname(c);
		auditPath = new OJPathName(auditPath.toJavaString() + TinkerAuditGenerationUtil.AUDIT);
		resultPathName.addToElementTypes(auditPath);
		getAudits.setReturnType(resultPathName);
		result.setType(resultPathName);
		result.setInitExp("new ArrayList<" + auditPath.getLast() + ">()");
		getAudits.getBody().addToLocals(result);

		OJForStatement forStatement = new OJForStatement("edge", TinkerGenerationUtil.edgePathName, "this.vertex.getOutEdges(\"audit\")");

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
		originalClass.addToImports(TinkerGenerationUtil.tinkerAuditNodePathName);

		getAudits.getBody().addToStatements("return result");
		originalClass.addToOperations(getAudits);
	}

	private void addCreateAuditVertex(OJAnnotatedClass ojClass, INakedEntity entity) {
		OJAnnotatedOperation createAuditVertex = new OJAnnotatedOperation("createAuditVertex");
		createAuditVertex.setVisibility(OJVisibilityKind.PUBLIC);
		createAuditVertex.addParam("createParentVertex", new OJPathName("boolean"));
		createAuditVertex.getBody().addToStatements("createAuditVertexWithAuditEdge()");
		// if (entity.getEndToComposite() != null || entity.getIsAbstract()) {
		// OJIfStatement ifCreateParentVertex = new
		// OJIfStatement("createParentVertex", "addEdgeToCompositeOwner()");
		// createAuditVertex.getBody().addToStatements(ifCreateParentVertex);
		// }
		ojClass.addToOperations(createAuditVertex);
	}

	private void implementAbstractAddEdgeToCompositeOwner(OJAnnotatedClass ojAuditClass) {
		OJOperation createEdgeToCompositeOwner = new OJOperation();
		createEdgeToCompositeOwner.setName("addEdgeToCompositeOwner");
		createEdgeToCompositeOwner.setAbstract(true);
		ojAuditClass.addToOperations(createEdgeToCompositeOwner);
	}

}
