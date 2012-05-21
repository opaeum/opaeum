package org.nakeduml.tinker.activity.generator;

import java.util.Collections;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.name.NameConverter;

@StepDependency(phase = TinkerActivityPhase.class, requires = {TinkerActivityNodeGenerator.class}, after = {TinkerActivityNodeGenerator.class})
public class TinkerControlNodeGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = true, match = { INakedControlNode.class })
	public void visitControlNode(INakedControlNode controlNode) {
		OJAnnotatedClass controlNodeClass = null;
		String tokenType = determineTokenType(controlNode).getLast();
		OJPathName superPath;
		switch (controlNode.getControlNodeType()) {
		case ACTIVITY_FINAL_NODE:
			controlNodeClass = createFinalNodeClass(controlNode, TinkerBehaviorUtil.tinkerFinalNodePathName.getCopy());
			break;
		case INITIAL_NODE:
			controlNodeClass = createInitialNodeClass(controlNode);
			break;
		case FORK_NODE:
			INakedClassifier objectNodeClasssifier = controlNode.getOriginatingObjectNodeClassifier();
			if (objectNodeClasssifier == null && controlNode.hasIncomingObjectFlow() && !controlNode.hasIncomingControlFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneForkNodeObjectTokenUnknownPathName.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyForkNodeObjectTokenUnknownPathName.getCopy();
				}
			} else if (objectNodeClasssifier == null && !controlNode.hasIncomingObjectFlow()) {
				superPath = TinkerBehaviorUtil.tinkerForkNodeControlTokenPathName.getCopy();
			} else if (objectNodeClasssifier == null && controlNode.hasIncomingObjectFlow() && controlNode.hasIncomingControlFlow()) {
				throw new IllegalStateException("The edges coming into and out of a fork node must be either all object flows or all control flows");
			} else if (objectNodeClasssifier != null && controlNode.hasIncomingControlFlow()) {
				throw new IllegalStateException("The edges coming into and out of a fork node must be either all object flows or all control flows");
			} else {
				OJPathName pathName = OJUtil.classifierPathname(objectNodeClasssifier);
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneForkNodeObjectTokenKnownPathName.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyForkNodeObjectTokenKnownPathName.getCopy();
				}
				superPath.addToGenerics(pathName);
			}
			controlNodeClass = createDecisionOrForkNodeClass(controlNode, superPath);
			break;
		case MERGE_NODE:
			objectNodeClasssifier = controlNode.getOriginatingObjectNodeClassifier();
			if (objectNodeClasssifier == null && controlNode.hasIncomingObjectFlow() && !controlNode.hasIncomingControlFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneMergeNodeObjectTokenUnknownPathName.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyMergeNodeObjectTokenUnknownPathName.getCopy();
				}
			} else if (objectNodeClasssifier == null && !controlNode.hasIncomingObjectFlow()) {
				superPath = TinkerBehaviorUtil.tinkerMergeNodeControlTokenPathName.getCopy();
			} else if (objectNodeClasssifier == null && controlNode.hasIncomingObjectFlow() && controlNode.hasIncomingControlFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneMergeNodeObjectTokenUnknownWithInControlToken.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyMergeNodeObjectTokenUnknownWithInControlToken.getCopy();
				}
			} else if (objectNodeClasssifier != null && controlNode.hasIncomingControlFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneMergeNodeObjectTokenKnownWithInControlToken.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyMergeNodeObjectTokenKnownWithInControlToken.getCopy();
				}
			} else {
				OJPathName pathName = OJUtil.classifierPathname(objectNodeClasssifier);
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneMergeNodeObjectTokenKnownPathName.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyMergeNodeObjectTokenKnownPathName.getCopy();
				}
				superPath.addToGenerics(pathName);
			}
			controlNodeClass = createMergeNodeClass(controlNode, superPath);
			break;
		case JOIN_NODE:
			objectNodeClasssifier = controlNode.getOriginatingObjectNodeClassifier();
			if (objectNodeClasssifier == null && controlNode.hasIncomingObjectFlow() && !controlNode.hasIncomingControlFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneJoinNodeObjectTokenUnknownPathName.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyJoinNodeObjectTokenUnknownPathName.getCopy();
				}
			} else if (objectNodeClasssifier == null && !controlNode.hasIncomingObjectFlow()) {
				superPath = TinkerBehaviorUtil.tinkerJoinNodeControlTokenPathName.getCopy();
			} else if (objectNodeClasssifier == null && controlNode.hasIncomingObjectFlow() && controlNode.hasIncomingControlFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneJoinNodeObjectTokenUnknownWithInControlToken.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyJoinNodeObjectTokenUnknownWithInControlToken.getCopy();
				}
			} else if (objectNodeClasssifier != null && controlNode.hasIncomingControlFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneJoinNodeObjectTokenKnownWithInControlToken.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyJoinNodeObjectTokenKnownWithInControlToken.getCopy();
				}
			} else {
				OJPathName pathName = OJUtil.classifierPathname(objectNodeClasssifier);
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneJoinNodeObjectTokenKnownPathName.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyJoinNodeObjectTokenKnownPathName.getCopy();
				}
				superPath.addToGenerics(pathName);
			}
			controlNodeClass = createJoinNodeClass(controlNode, superPath);
			break;
		case FLOW_FINAL_NODE:
			controlNodeClass = createFinalNodeClass(controlNode, TinkerBehaviorUtil.tinkerFinalNodePathName.getCopy());
			break;
		case DECISION_NODE:
			objectNodeClasssifier = controlNode.getOriginatingObjectNodeClassifier();
			if (objectNodeClasssifier == null && controlNode.hasIncomingObjectFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneDecisionObjectTokenNodeUnknownPathName.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyDecisionObjectTokenNodeUnknownPathName.getCopy();
				}
			} else if (objectNodeClasssifier == null && !controlNode.hasIncomingObjectFlow()) {
				superPath = TinkerBehaviorUtil.tinkerDecisionControlTokenNodePathName.getCopy();
			} else {
				OJPathName pathName = OJUtil.classifierPathname(objectNodeClasssifier);
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					superPath = TinkerBehaviorUtil.tinkerOneDecisionObjectTokenNodeKnownPathName.getCopy();
				} else {
					superPath = TinkerBehaviorUtil.tinkerManyDecisionObjectTokenNodeKnownPathName.getCopy();
				}
				superPath.addToGenerics(pathName);
			}
			controlNodeClass = createDecisionOrForkNodeClass(controlNode, superPath);
			break;
		default:
			break;
		}
		implementGetActivity(controlNodeClass, controlNode);
		implementGetContextObject(controlNodeClass, controlNode.getActivity().getContext());

		if (tokenType.equals(TinkerBehaviorUtil.tinkerObjectTokenPathName.getLast())) {
			controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy());
		} else {
			controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy());
		}
	}

	private OJPathName determineTokenType(INakedControlNode controlNode) {
		OJPathName tokenType;
		if (!controlNode.getOutgoing().isEmpty()) {
			if (controlNode.getOutgoing().iterator().next() instanceof INakedObjectNode) {
				tokenType = TinkerBehaviorUtil.tinkerObjectTokenPathName;
			} else {
				tokenType = TinkerBehaviorUtil.tinkerControlTokenPathName;
			}
		} else {
			if (controlNode.getIncoming().iterator().next() instanceof INakedObjectNode) {
				tokenType = TinkerBehaviorUtil.tinkerObjectTokenPathName;
			} else {
				tokenType = TinkerBehaviorUtil.tinkerControlTokenPathName;
			}
		}
		return tokenType;
	}

	private OJAnnotatedClass createFinalNodeClass(INakedControlNode controlNode, OJPathName superType) {
		OJAnnotatedClass finalControlNode = findJavaClassForActivityNode(controlNode);
		finalControlNode.setSuperclass(superType);
		addDefaultConstructor(finalControlNode, controlNode);
		removeGetOutgoing(finalControlNode);
		return finalControlNode;
	}

	private void addDefaultConstructor(OJAnnotatedClass controlNodeClass, INakedActivityNode controlNode) {
		OJConstructor defaultConstructor = new OJConstructor();
		initVertexInConstructor(controlNodeClass, controlNode, defaultConstructor);
	}


	private OJAnnotatedClass createInitialNodeClass(INakedControlNode controlNode) {
		OJAnnotatedClass initControlNode = findJavaClassForActivityNode(controlNode);
		initControlNode.setSuperclass(TinkerBehaviorUtil.tinkerInitialNodePathName);
		addDefaultConstructor(initControlNode, controlNode);
		removeGetIncoming(initControlNode);
		addSetStatusEnabledInContructor(initControlNode);
		return initControlNode;
	}

	private OJAnnotatedClass createDecisionOrForkNodeClass(INakedControlNode controlNode, OJPathName superClass) {
		OJAnnotatedClass forkControlNode = findJavaClassForActivityNode(controlNode);
		forkControlNode.setSuperclass(superClass);
		addDefaultConstructor(forkControlNode, controlNode);
		if (controlNode.getIncoming().size() > 1) {
			throw new IllegalStateException("Fork node can only have one incoming edge");
		}
		addGetInFlow(forkControlNode, controlNode);
		forkControlNode.removeFromOperations(forkControlNode.findOperation("getIncoming", Collections.emptyList()));
		forkControlNode.addToImports(forkControlNode.getSuperclass().getGenerics());
		return forkControlNode;
	}

	private OJAnnotatedClass createMergeNodeClass(INakedControlNode controlNode, OJPathName superType) {
		OJAnnotatedClass mergeControlNode = findJavaClassForActivityNode(controlNode);
		mergeControlNode.setSuperclass(superType);

		addDefaultConstructor(mergeControlNode, controlNode);
		addGetOutFlow(mergeControlNode, controlNode);
		if (controlNode.getOutgoing().size() > 1) {
			throw new IllegalStateException("Join node can only have one outgoing edge");
		}
		mergeControlNode.removeFromOperations(mergeControlNode.findOperation("getOutgoing", Collections.emptyList()));
		mergeControlNode.addToImports(mergeControlNode.getSuperclass().getGenerics());
		return mergeControlNode;
	}

	private OJAnnotatedClass createJoinNodeClass(INakedControlNode controlNode, OJPathName superType) {
		OJAnnotatedClass joinControlNode = findJavaClassForActivityNode(controlNode);
		joinControlNode.setSuperclass(superType);
		addDefaultConstructor(joinControlNode, controlNode);
		addGetOutFlow(joinControlNode, controlNode);
		if (controlNode.getOutgoing().size() > 1) {
			throw new IllegalStateException("Join node can only have one outgoing edge");
		}
		joinControlNode.removeFromOperations(joinControlNode.findOperation("getOutgoing", Collections.emptyList()));
		joinControlNode.addToImports(joinControlNode.getSuperclass().getGenerics());
		return joinControlNode;
	}

	private void addGetInFlow(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getInFlow = new OJAnnotatedOperation("getInFlow");

		INakedActivityEdge outEdge = oa.getIncoming().iterator().next();
		OJPathName path = OJUtil.packagePathname(outEdge.getNameSpace()).getCopy();
		path.addToNames(NameConverter.capitalize(outEdge.getName()));
		getInFlow.setReturnType(path);

		for (INakedActivityEdge edge : oa.getIncoming()) {
			getInFlow.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(edge) + "()");
		}
		actionClass.addToImports(new OJPathName("java.util.Arrays"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		actionClass.addToOperations(getInFlow);
	}
}
