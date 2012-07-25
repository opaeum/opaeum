package org.nakeduml.tinker.activity;

import java.util.Collection;

import nl.klasse.octopus.model.ParameterDirectionKind;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

import org.eclipse.uml2.uml.ControlNodeType;
import org.eclipse.uml2.uml.INakedAcceptEventAction;
import org.eclipse.uml2.uml.INakedAction;
import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedActivityNode;
import org.eclipse.uml2.uml.INakedBehavioredClassifier;
import org.eclipse.uml2.uml.INakedControlNode;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedObjectFlow;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.eclipse.uml2.uml.INakedOclAction;
import org.eclipse.uml2.uml.INakedOpaqueAction;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedParameterNode;
import org.eclipse.uml2.uml.INakedPin;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.INakedSendSignalAction;
import org.eclipse.uml2.uml.INakedSignal;
import org.eclipse.uml2.uml.INakedSignalEvent;
import org.eclipse.uml2.uml.INakedTrigger;
import org.eclipse.uml2.uml.INakedValuePin;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.NakedValueSpecificationImpl;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerImplementNodeStep.class }, after = { TinkerImplementNodeStep.class })
public class TinkerActivityNodeGenerator extends StereotypeAnnotator {

	@VisitBefore(matchSubclasses = false, match = { INakedInputPin.class })
	public void visitInputpins(INakedInputPin oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass inputPinClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		inputPinClass.setMyPackage(pack);

		OJPathName superClass = TinkerBehaviorUtil.tinkerInputPinPathName.getCopy();
		superClass.addToGenerics(OJUtil.classifierPathname(oa.getNakedBaseType()));
		inputPinClass.addToImports(OJUtil.classifierPathname(oa.getNakedBaseType()));
		inputPinClass.setSuperclass(superClass);
		addActivityNodeOperations(inputPinClass, oa);

		addInitVertexToDefaultConstructor(inputPinClass, oa);
		addContextObjectField(inputPinClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(inputPinClass, oa.getActivity().getContext());

		implementUpperBound(inputPinClass, oa);
		implementMultiplicity(inputPinClass, oa);
		implementGetAction(inputPinClass, oa);
		super.createTextPath(inputPinClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = false, match = { INakedOutputPin.class })
	public void visitOutputpins(INakedOutputPin oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass outputPinClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		outputPinClass.setMyPackage(pack);

		OJPathName superClass = TinkerBehaviorUtil.tinkerOutputPinPathName.getCopy();
		superClass.addToGenerics(OJUtil.classifierPathname(oa.getNakedBaseType()));
		outputPinClass.addToImports(OJUtil.classifierPathname(oa.getNakedBaseType()));
		outputPinClass.setSuperclass(superClass);

		addActivityNodeOperations(outputPinClass, oa);

		addInitVertexToDefaultConstructor(outputPinClass, oa);
		addContextObjectField(outputPinClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(outputPinClass, oa.getActivity().getContext());

		implementUpperBound(outputPinClass, oa);
		implementMultiplicity(outputPinClass, oa);
		implementGetAction(outputPinClass, oa);
		implementCopyTokensToStart(outputPinClass);
		super.createTextPath(outputPinClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedParameterNode.class })
	public void visitParameterNode(INakedParameterNode oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass activityParameterNodeClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		activityParameterNodeClass.setMyPackage(pack);
		if ((oa.getParameter().getDirection() == ParameterDirectionKind.IN) || (oa.getParameter().getDirection() == ParameterDirectionKind.INOUT)) {
			activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerInActivityParameterNodePathName);
			addGetOutControlFlows(activityParameterNodeClass, oa);
			addOutControlFlowGetters(activityParameterNodeClass, oa);
		} else if ((oa.getParameter().getDirection() == ParameterDirectionKind.IN) || (oa.getParameter().getDirection() == ParameterDirectionKind.INOUT)) {
			activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerOutActivityParameterNodePathName);
			addGetInControlFlows(activityParameterNodeClass, oa);
			addInControlFlowGetters(activityParameterNodeClass, oa);
		}
		OJPathName parameterPath = activityParameterNodeClass.getSuperclass();
		parameterPath.addToGenerics(OJUtil.classifierPathname(oa.getParameter().getNakedBaseType()));
		activityParameterNodeClass.setSuperclass(parameterPath);

		addConstructorWithVertex(activityParameterNodeClass, oa.getActivity().getContext());
		addGetContextObject(activityParameterNodeClass, oa.getActivity().getContext());
		addInitVertexToDefaultConstructor(activityParameterNodeClass, oa);
		addContextObjectField(activityParameterNodeClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(activityParameterNodeClass, oa.getActivity().getContext());
		implementUpperBound(activityParameterNodeClass, oa);
		super.createTextPath(activityParameterNodeClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedOpaqueAction.class })
	public void visitOpaqueAction(INakedOclAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOpaqueActionPathName);
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		addGetBodyExpression(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedAcceptEventAction.class })
	public void visitAcceptEventAction(INakedAcceptEventAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAcceptEventAction);
		addActionOperations(actionClass, oa);
		OJConstructor constructor = actionClass.findConstructor(TinkerGenerationUtil.vertexPathName, OJUtil.classifierPathname(oa.getActivity().getContext()));
		addTriggersInConstructor(constructor, actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		OJConstructor constructor1 = actionClass.findConstructor(OJUtil.classifierPathname(oa.getActivity().getContext()));
		addTriggersInConstructor(constructor1, actionClass, oa);
		addCopySignalToOutputPin(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedSendSignalAction.class })
	public void visitSendSignalAction(INakedSendSignalAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerSendSignalAction);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		addConstructSignal(actionClass, oa);
		addResolveTarget(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedActivityEdge.class })
	public void visitActivityEdge(INakedActivityEdge edge) {
		createControlFlowEdgeClass(edge);
	}

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
			if (controlNode.getIncoming().iterator().next() instanceof INakedObjectFlow) {
				superPath = TinkerBehaviorUtil.tinkerForkNodeObjectTokenPathName;
			} else {
				superPath = TinkerBehaviorUtil.tinkerForkNodeControlTokenPathName;
			}
			controlNodeClass = createDecisionOrForkNodeClass(controlNode, superPath);
			break;
		case MERGE_NODE:
			if (controlNode.getIncoming().iterator().next() instanceof INakedObjectFlow) {
				superPath = TinkerBehaviorUtil.tinkerMergeNodeObjectTokenPathName;
			} else {
				superPath = TinkerBehaviorUtil.tinkerMergeNodeControlTokenPathName;
			}
			controlNodeClass = createMergeNodeClass(controlNode, superPath);
			break;
		case JOIN_NODE:
			if (controlNode.getIncoming().iterator().next() instanceof INakedObjectFlow) {
				superPath = TinkerBehaviorUtil.tinkerJoinNodeObjectTokenPathName;
			} else {
				superPath = TinkerBehaviorUtil.tinkerJoinNodeControlTokenPathName;
			}
			controlNodeClass = createJoinNodeClass(controlNode, superPath);
			break;
		case FLOW_FINAL_NODE:
			controlNodeClass = createFinalNodeClass(controlNode, TinkerBehaviorUtil.tinkerFinalNodePathName.getCopy());
			break;
		case DECISION_NODE:
			// Same as fork, one in multipl out
			if (controlNode.getIncoming().iterator().next() instanceof INakedObjectFlow) {
				superPath = TinkerBehaviorUtil.tinkerDecisionObjectTokenNodePathName;
			} else {
				superPath = TinkerBehaviorUtil.tinkerDecisionControlTokenNodePathName;
			}
			controlNodeClass = createDecisionOrForkNodeClass(controlNode, superPath);
			break;
		default:
			break;
		}
		if (tokenType.equals(TinkerBehaviorUtil.tinkerObjectTokenPathName.getLast())) {
			controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
		} else {
			controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		}
		addContextObjectField(controlNodeClass, controlNode.getActivity().getContext());
		addContextObjectToDefaultConstructor(controlNodeClass, controlNode.getActivity().getContext());
		addGetContextObject(controlNodeClass, controlNode.getActivity().getContext());
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

	private void addResolveTarget(OJAnnotatedClass actionClass, INakedSendSignalAction oa) {
		OJAnnotatedOperation resolveTarget = new OJAnnotatedOperation("resolveTarget");
		resolveTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		resolveTarget.setReturnType(TinkerBehaviorUtil.tinkerBaseTinkerBehavioredClassifier);
		actionClass.addToOperations(resolveTarget);
		INakedValuePin targetValuePin = (INakedValuePin) oa.getTarget();
		resolveTarget.getBody().addToStatements(
				"return " + ValueSpecificationUtil.expressValue(resolveTarget, targetValuePin.getValue(), oa.getContext(), targetValuePin.getType()));
	}

	private void addConstructSignal(OJAnnotatedClass actionClass, INakedSendSignalAction oa) {
		OJAnnotatedOperation constructSignal = new OJAnnotatedOperation("constructSignal");
		constructSignal.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		constructSignal.setReturnType(TinkerBehaviorUtil.tinkerSignalPathName);
		INakedSignal signal = oa.getSignal();
		OJPathName signalPathname = OJUtil.classifierPathname(signal);
		OJField signalField = new OJField();
		signalField.setName(NameConverter.decapitalize(signal.getName()));
		signalField.setType(signalPathname);
		signalField.setInitExp("new " + signalPathname.getLast() + "(true)");
		constructSignal.getBody().addToLocals(signalField);
		actionClass.addToImports(signalPathname);
		actionClass.addToOperations(constructSignal);

		// Match up input pins with signal attributes
		int i = 0;
		for (INakedInputPin inputPin : oa.getInput()) {
			if (inputPin != oa.getTarget()) {
				INakedProperty attribute = signal.getOwnedAttributes().get(i++);
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(attribute);
				if (inputPin instanceof INakedValuePin) {
					INakedValuePin valuePin = (INakedValuePin)inputPin;
					constructSignal.getBody().addToStatements(
							signalField.getName() + "." + map.setter() + "("
									+ ValueSpecificationUtil.expressValue(constructSignal, valuePin.getValue(), oa.getContext(), valuePin.getType()) + ")");
				} else {
					//TODO
				}
			}
		}
		constructSignal.getBody().addToStatements("return " + signalField.getName());
	}

	private void addTriggersInConstructor(OJConstructor constructor, OJClass actionClass, INakedAcceptEventAction oa) {
		Collection<INakedTrigger> triggers = oa.getTriggers();
		for (INakedTrigger trigger : triggers) {
			if (trigger.getEvent() instanceof INakedSignalEvent) {
				INakedSignalEvent signalEvent = (INakedSignalEvent) trigger.getEvent();
				constructor.getBody().addToStatements("addToTriggers(\"" + trigger.getName() + "\", " + OJUtil.classifierPathname(signalEvent.getSignal()).getLast() + ".class)");
				actionClass.addToImports(OJUtil.classifierPathname(signalEvent.getSignal()));
			}
		}
	}

	private OJAnnotatedClass createInitialNodeClass(INakedControlNode controlNode) {
		OJAnnotatedClass initControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		initControlNode.setMyPackage(pack);
		initControlNode.setSuperclass(TinkerBehaviorUtil.tinkerInitialNodePathName);
		super.createTextPath(initControlNode, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addDefaultConstructor(initControlNode, controlNode);
		addConstructorWithVertex(initControlNode, controlNode.getActivity().getContext());
		addGetOutControlFlows(initControlNode, controlNode);
		addOutControlFlowGetters(initControlNode, controlNode);

		// addInstantiateToken(initControlNode, controlNode);
		return initControlNode;
	}

	private OJAnnotatedClass createMergeNodeClass(INakedControlNode controlNode, OJPathName superType) {
		OJAnnotatedClass mergeControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		mergeControlNode.setMyPackage(pack);
		mergeControlNode.setSuperclass(superType);
		super.createTextPath(mergeControlNode, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addDefaultConstructor(mergeControlNode, controlNode);
		addConstructorWithVertex(mergeControlNode, controlNode.getActivity().getContext());
		addGetInControlFlows(mergeControlNode, controlNode);
		addInControlFlowGetters(mergeControlNode, controlNode);
		addGetOutControlFlow(mergeControlNode, controlNode);
		if (controlNode.getOutgoing().size() > 1) {
			throw new IllegalStateException("Join node can only have one outgoing edge");
		}
		addOutControlFlowGetters(mergeControlNode, controlNode);

		// addInstantiateToken(mergeControlNode, controlNode);
		return mergeControlNode;
	}

	private OJAnnotatedClass createJoinNodeClass(INakedControlNode controlNode, OJPathName superType) {
		OJAnnotatedClass joinControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		joinControlNode.setMyPackage(pack);
		joinControlNode.setSuperclass(superType);
		super.createTextPath(joinControlNode, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addDefaultConstructor(joinControlNode, controlNode);
		addConstructorWithVertex(joinControlNode, controlNode.getActivity().getContext());
		addGetInControlFlows(joinControlNode, controlNode);
		addInControlFlowGetters(joinControlNode, controlNode);
		addGetOutControlFlow(joinControlNode, controlNode);
		if (controlNode.getOutgoing().size() > 1) {
			throw new IllegalStateException("Join node can only have one outgoing edge");
		}
		addOutControlFlowGetters(joinControlNode, controlNode);

		// addInstantiateToken(joinControlNode, controlNode);
		return joinControlNode;
	}

	private OJAnnotatedClass createDecisionOrForkNodeClass(INakedControlNode controlNode, OJPathName superClass) {
		OJAnnotatedClass forkControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		forkControlNode.setMyPackage(pack);
		forkControlNode.setSuperclass(superClass);
		super.createTextPath(forkControlNode, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addDefaultConstructor(forkControlNode, controlNode);
		addConstructorWithVertex(forkControlNode, controlNode.getActivity().getContext());
		addGetOutControlFlows(forkControlNode, controlNode);
		addOutControlFlowGetters(forkControlNode, controlNode);
		addGetInControlFlow(forkControlNode, controlNode);
		if (controlNode.getIncoming().size() > 1) {
			throw new IllegalStateException("Fork node can only have one incoming edge");
		}
		addInControlFlowGetters(forkControlNode, controlNode);

		// addInstantiateToken(forkControlNode, controlNode);
		return forkControlNode;
	}

	// private void addInstantiateToken(OJAnnotatedClass nodeClass,
	// INakedControlNode controlNode) {
	// OJAnnotatedOperation instantiateToken = new
	// OJAnnotatedOperation("instantiateToken");
	// TinkerGenerationUtil.addOverrideAnnotation(instantiateToken);
	// instantiateToken.setReturnType(determineTokenType(controlNode));
	// instantiateToken.addToParameters(new OJParameter("name", new
	// OJPathName("String")));
	// instantiateToken.getBody().addToStatements("return new " +
	// instantiateToken.getReturnType().getLast() + "(name)");
	// nodeClass.addToOperations(instantiateToken);
	// }

	private void addDefaultConstructor(OJAnnotatedClass controlNodeClass, INakedActivityNode controlNode) {
		OJConstructor defaultConstructor = new OJConstructor();
		initVertexInConstructor(controlNodeClass, controlNode, defaultConstructor);
	}

	private void initVertexInConstructor(OJAnnotatedClass controlNodeClass, INakedActivityNode controlNode, OJConstructor defaultConstructor) {
		defaultConstructor.getBody().addToStatements("super(true, \"" + controlNode.getName() + "\")");
		defaultConstructor.getBody().addToStatements("setNodeStatus(NodeStatus.INACTIVE)");
		controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerNodeStatusPathName);
		controlNodeClass.addToConstructors(defaultConstructor);
	}

	private void addInitVertexToDefaultConstructor(OJAnnotatedClass actionClass, INakedActivityNode controlNode) {
		OJConstructor defaultConstructor = actionClass.getDefaultConstructor();
		initVertexInConstructor(actionClass, controlNode, defaultConstructor);
	}

	private void addContextObjectField(OJAnnotatedClass actionClass, INakedBehavioredClassifier context) {
		OJField contextObjectField = new OJField();
		contextObjectField.setType(OJUtil.classifierPathname(context));
		contextObjectField.setName("contextObject");
		actionClass.addToFields(contextObjectField);
	}

	private void addContextObjectToDefaultConstructor(OJAnnotatedClass actionClass, INakedBehavioredClassifier contextClassifier) {
		OJConstructor defaultConstructor = actionClass.getDefaultConstructor();
		defaultConstructor.addParam("contextObject", OJUtil.classifierPathname(contextClassifier));
		defaultConstructor.getBody().addToStatements("this.contextObject = contextObject");
	}

	private OJAnnotatedClass createFinalNodeClass(INakedControlNode controlNode, OJPathName superType) {
		OJAnnotatedClass finalControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		finalControlNode.setMyPackage(pack);
		finalControlNode.setSuperclass(superType);
		super.createTextPath(finalControlNode, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addDefaultConstructor(finalControlNode, controlNode);
		addConstructorWithVertex(finalControlNode, controlNode.getActivity().getContext());
		addGetInControlFlows(finalControlNode, controlNode);
		addInControlFlowGetters(finalControlNode, controlNode);

		// addInstantiateToken(finalControlNode, controlNode);
		return finalControlNode;
	}

	private void createControlFlowEdgeClass(INakedActivityEdge edge) {
		OJAnnotatedClass controlFlowEdge = new OJAnnotatedClass(NameConverter.capitalize(edge.getName()));
		OJPathName path = OJUtil.packagePathname(edge.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		controlFlowEdge.setMyPackage(pack);
		addContextObjectField(controlFlowEdge, edge.getActivity().getContext());
		OJConstructor edgeConstructor = new OJConstructor();
		edgeConstructor.addParam("edge", TinkerGenerationUtil.edgePathName);
		edgeConstructor.getBody().addToStatements("super(edge)");
		edgeConstructor.addParam("contextObject", OJUtil.classifierPathname(edge.getActivity().getContext()));
		edgeConstructor.getBody().addToStatements("this.contextObject = contextObject");

		controlFlowEdge.addToConstructors(edgeConstructor);

		if (edge instanceof INakedObjectFlow) {
			OJPathName superClass = TinkerBehaviorUtil.tinkerObjectFlowPathName.getCopy();
			OJPathName genericPathname = OJUtil.classifierPathname(((INakedObjectFlow) edge).getOriginatingObjectNode().getNakedBaseType());
			superClass.addToGenerics(genericPathname);
			controlFlowEdge.addToImports(genericPathname);
			controlFlowEdge.setSuperclass(superClass);
		} else {
			controlFlowEdge.setSuperclass(TinkerBehaviorUtil.tinkerControlFlowPathName);
		}

		super.createTextPath(controlFlowEdge, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addGetWeight(controlFlowEdge, edge);
		addEvaluateGuardCondition(controlFlowEdge, edge);
		addTarget(controlFlowEdge, edge);
		addSource(controlFlowEdge, edge);
		addName(controlFlowEdge, edge);

		OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject");
		getContextObject.setReturnType(OJUtil.classifierPathname(edge.getActivity().getContext()));
		getContextObject.getBody().addToStatements("return this.contextObject");
		controlFlowEdge.addToOperations(getContextObject);

	}

	private void addSource(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation getSourceAction = new OJAnnotatedOperation(TinkerBehaviorUtil.actionSourceGetter(edge));
		OJPathName actionPathName = TinkerBehaviorUtil.activityNodePathName(edge.getSource());
		getSourceAction.setReturnType(actionPathName);
		getSourceAction.getBody().addToStatements("return new " + actionPathName.getLast() + "(this.edge.getOutVertex(), this.contextObject)");
		controlFlowEdge.addToOperations(getSourceAction);

		OJAnnotatedOperation getSource = new OJAnnotatedOperation("getSource");

		OJPathName tokenPathName;
		if (edge instanceof INakedObjectFlow) {
			tokenPathName = TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy();
			tokenPathName.addToGenerics(OJUtil.classifierPathname(((INakedObjectFlow) edge).getOriginatingObjectNode().getNakedBaseType()));
			controlFlowEdge.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
		} else {
			tokenPathName = TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy();
			controlFlowEdge.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		}
		// OJPathName returnType =
		// TinkerBehaviorUtil.tinkerActivityNodePathName.getCopy();
		// returnType.addToGenerics(tokenPathName);
		OJPathName returnType = TinkerBehaviorUtil.activityNodePathName(edge.getSource());
		getSource.setReturnType(returnType);

		getSource.getBody().addToStatements("return " + TinkerBehaviorUtil.actionSourceGetter(edge) + "()");
		getSource.setVisibility(OJVisibilityKind.PROTECTED);
		getSource.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getSource);
	}

	private void addName(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation getName = new OJAnnotatedOperation("getName");
		getName.setReturnType(new OJPathName("String"));
		getName.getBody().addToStatements("return \"" + edge.getName() + "\"");
		controlFlowEdge.addToOperations(getName);
	}

	private void addTarget(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation getTargetAction = new OJAnnotatedOperation(TinkerBehaviorUtil.actionTargetGetter(edge));
		OJPathName actionPathName = TinkerBehaviorUtil.activityNodePathName(edge.getTarget());
		getTargetAction.setReturnType(actionPathName);
		getTargetAction.getBody().addToStatements("return new " + actionPathName.getLast() + "(this.edge.getInVertex(), this.contextObject)");
		controlFlowEdge.addToOperations(getTargetAction);

		OJAnnotatedOperation getTarget = new OJAnnotatedOperation("getTarget");
		OJPathName tokenPathName;
		if (edge instanceof INakedObjectFlow) {
			tokenPathName = TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy();
			tokenPathName.addToGenerics(OJUtil.classifierPathname(((INakedObjectFlow) edge).getOriginatingObjectNode().getNakedBaseType()));
			controlFlowEdge.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
		} else {
			tokenPathName = TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy();
			controlFlowEdge.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		}
		// OJPathName returnType =
		// TinkerBehaviorUtil.tinkerActivityNodePathName.getCopy();
		// returnType.addToGenerics(tokenPathName);
		OJPathName returnType = TinkerBehaviorUtil.activityNodePathName(edge.getTarget());
		getTarget.setReturnType(returnType);
		getTarget.getBody().addToStatements("return " + TinkerBehaviorUtil.actionTargetGetter(edge) + "()");
		getTarget.setVisibility(OJVisibilityKind.PROTECTED);
		getTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getTarget);
	}

	private void addEvaluateGuardCondition(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation evaluateGuardConditions = new OJAnnotatedOperation("evaluateGuardConditions");
		evaluateGuardConditions.setReturnType(new OJPathName("boolean"));
		if (edge instanceof INakedObjectFlow) {
			evaluateGuardConditions.addParam("controlToken", TinkerBehaviorUtil.tinkerObjectTokenPathName);
		} else {
			evaluateGuardConditions.addParam("controlToken", TinkerBehaviorUtil.tinkerControlTokenPathName);
		}
		String guardEvaluation = ValueSpecificationUtil.expressValue(controlFlowEdge, edge.getGuard(), new StdlibPrimitiveType("Boolean"), false);
		evaluateGuardConditions.getBody().addToStatements("return " + guardEvaluation);
		evaluateGuardConditions.setVisibility(OJVisibilityKind.PROTECTED);
		evaluateGuardConditions.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(evaluateGuardConditions);
	}

	private void addGetWeight(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation getWeight = new OJAnnotatedOperation("getWeigth");
		getWeight.setReturnType(new OJPathName("int"));
		getWeight.getBody().addToStatements("return " + edge.getWeight().intValue());
		getWeight.setVisibility(OJVisibilityKind.PROTECTED);
		getWeight.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getWeight);
	}

	private void addActionOperations(OJClass actionClass, INakedActivityNode oa) {
		addHasPostConditionPassed(actionClass);
		addHasPreConditionPassed(actionClass);
		addActivityNodeOperations(actionClass, oa);
		if (oa instanceof INakedAction) {
			addInputPinGetters(actionClass, (INakedAction) oa);
			addOutputPinGetters(actionClass, (INakedAction) oa);
			addAddToInputPinVariable(actionClass, (INakedAction) oa);
			createVariablesForInputPins(actionClass, (INakedAction) oa);
			addGetInputPinVariables(actionClass,(INakedAction) oa);
		}
		if (oa instanceof INakedOclAction && ((INakedOclAction) oa).getReturnPin() != null) {
			addReturnPinGenericType(actionClass, (INakedOclAction) oa);
		}
	}

	private void addGetInputPinVariables(OJClass actionClass, INakedAction oa) {
		OJAnnotatedOperation getInputPinVariables = new OJAnnotatedOperation("getInputPinVariables");
		OJPathName returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends Object"));
		getInputPinVariables.setReturnType(returnType);
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (INakedInputPin inputPin : oa.getInput()) {
			if (inputPin instanceof INakedValuePin || (oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
				continue;
			}
			if (!first) {
				sb.append(", ");
				first = false;
			}
			sb.append("this.");
			sb.append(TinkerBehaviorUtil.inputPinVariableName(inputPin));
		}
		getInputPinVariables.getBody().addToStatements("return Arrays.asList(" + sb.toString() + ")");
		actionClass.addToOperations(getInputPinVariables);
	}

	private void createVariablesForInputPins(OJClass actionClass, INakedAction oa) {
		for (INakedInputPin inputPin : oa.getInput()) {
			if (inputPin instanceof INakedValuePin || (oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
				continue;
			}
			if (inputPin.getNakedMultiplicity().isOne()) {
				OJField pinVariable = new OJField();
				pinVariable.setName(TinkerBehaviorUtil.inputPinVariableName(inputPin));
				pinVariable.setType(OJUtil.classifierPathname(inputPin.getNakedBaseType()));
				actionClass.addToFields(pinVariable);

				OJAnnotatedOperation setterForPinVariable = new OJAnnotatedOperation("set" + NameConverter.capitalize(inputPin.getName()));
				setterForPinVariable.addParam("val", OJUtil.classifierPathname(inputPin.getNakedBaseType()));
				actionClass.addToOperations(setterForPinVariable);
				setterForPinVariable.getBody().addToStatements("this." + inputPin.getName() + " = val");

				OJAnnotatedOperation getterForPinVariable = new OJAnnotatedOperation("get" + NameConverter.capitalize(inputPin.getName()));
				getterForPinVariable.setReturnType(OJUtil.classifierPathname(inputPin.getNakedBaseType()));
				OJField result = new OJField();
				result.setName("result");
				result.setType(OJUtil.classifierPathname(inputPin.getNakedBaseType()));
				result.setInitExp("this." + inputPin.getName());
				getterForPinVariable.getBody().addToLocals(result);
				getterForPinVariable.getBody().addToStatements("return result");
				actionClass.addToOperations(getterForPinVariable);
			} else {
				//TODO
			}
		}
	}

	private void addAddToInputPinVariable(OJClass actionClass, INakedAction oa) {
		OJAnnotatedOperation addToInputPinVariable = new OJAnnotatedOperation("addToInputPinVariable");
		TinkerGenerationUtil.addOverrideAnnotation(addToInputPinVariable);
		addToInputPinVariable.addParam("inputPin", TinkerBehaviorUtil.tinkerInputPinPathName.getCopy().addToGenerics(new OJPathName("?")));
		addToInputPinVariable.addParam("object", new OJPathName("java.lang.Object"));

		for (INakedInputPin inputPin : oa.getInput()) {
			if (inputPin instanceof INakedValuePin || (oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
				continue;
			}
			OJIfStatement ifInputPinIsType = new OJIfStatement("inputPin instanceof " + NameConverter.capitalize(inputPin.getName()));
			addToInputPinVariable.getBody().addToStatements(ifInputPinIsType);
			ifInputPinIsType.addToThenPart("set" + NameConverter.capitalize(inputPin.getName()) + "((" + inputPin.getNakedBaseType().getName() + ")object)");
		}

		actionClass.addToOperations(addToInputPinVariable);
	}

	private void addReturnPinGenericType(OJClass actionClass, INakedOclAction oa) {
		OJPathName superPathName = actionClass.getSuperclass().getCopy();
		superPathName.addToGenerics(OJUtil.classifierPathname(oa.getReturnPin().getNakedBaseType()));
		actionClass.setSuperclass(superPathName);
	}

	private void addOutputPinGetters(OJClass actionClass, INakedAction a) {

		StringBuilder sb = new StringBuilder("return Arrays.asList(");
		Collection<INakedOutputPin> outputPins = a.getOutput();
		boolean hasOutputPins = false;
		for (INakedOutputPin outputPin : outputPins) {

			if (a instanceof INakedSendSignalAction && ((INakedSendSignalAction) a).getTarget() == outputPin) {
				continue;
			}
			hasOutputPins = true;
			OJAnnotatedOperation getOutputPin = new OJAnnotatedOperation(TinkerBehaviorUtil.outputPinGetterName(outputPin));
			sb.append(getOutputPin.getName() + "()");
			sb.append(",");
			getOutputPin.setReturnType(TinkerBehaviorUtil.activityNodePathName(outputPin));
			getOutputPin.getBody().addToStatements(
					"return new " + TinkerBehaviorUtil.activityNodePathName(outputPin).getLast() + "(this.vertex.getOutEdges(\"outputPin"
							+ NameConverter.capitalize(outputPin.getName() + "\").iterator().next().getInVertex(), this.contextObject)"));
			actionClass.addToOperations(getOutputPin);
		}

		if (a instanceof INakedOclAction) {
			OJAnnotatedOperation getResultPin = new OJAnnotatedOperation("getResultPin");
			getResultPin.setReturnType(TinkerBehaviorUtil.tinkerOutputPinPathName.getCopy());
			actionClass.addToOperations(getResultPin);
			if (((INakedOclAction) a).getReturnPin() != null) {
				getResultPin.getReturnType().addToGenerics(OJUtil.classifierPathname(((INakedOclAction) a).getReturnPin().getNakedBaseType()));
				getResultPin.getBody().addToStatements("return get" + NameConverter.capitalize(((INakedOclAction) a).getReturnPin().getName()) + "OutputPin()");
			} else {
				getResultPin.getReturnType().addToGenerics(new OJPathName("?"));
				getResultPin.getBody().addToStatements("return null");
			}
		} else {

			OJAnnotatedOperation getOutputPins = new OJAnnotatedOperation("getOutputPins");
			TinkerGenerationUtil.addOverrideAnnotation(getOutputPins);
			OJPathName returnType = new OJPathName("java.util.List");
			returnType.addToElementTypes(new OJPathName("? extends OutputPin<?>"));
			actionClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
			getOutputPins.setReturnType(returnType);
			String statement = sb.toString();
			if (hasOutputPins) {
				statement = statement.substring(0, sb.toString().length() - 1);
			}
			statement += ")";
			getOutputPins.getBody().addToStatements(statement);
			actionClass.addToOperations(getOutputPins);
			actionClass.addToImports(TinkerBehaviorUtil.tinkerOutputPinPathName);
		}
	}

	private void addInputPinGetters(OJClass actionClass, INakedAction a) {
		StringBuilder sb = new StringBuilder("return Arrays.asList(");
		Collection<INakedInputPin> inputPins = a.getInput();
		boolean hasInputPins = false;
		for (INakedInputPin inputPin : inputPins) {

			if (inputPin instanceof INakedValuePin || (a instanceof INakedSendSignalAction && ((INakedSendSignalAction) a).getTarget() == inputPin)) {
				continue;
			}
			hasInputPins = true;
			OJAnnotatedOperation getInputPin = new OJAnnotatedOperation(TinkerBehaviorUtil.inputPinGetter(inputPin));
			sb.append(getInputPin.getName() + "()");
			sb.append(",");
			getInputPin.setReturnType(TinkerBehaviorUtil.activityNodePathName(inputPin));
			getInputPin.getBody().addToStatements(
					"return new " + TinkerBehaviorUtil.activityNodePathName(inputPin).getLast() + "(this.vertex.getOutEdges(\"inputPin"
							+ NameConverter.capitalize(inputPin.getName() + "\").iterator().next().getInVertex(), this.contextObject)"));
			actionClass.addToOperations(getInputPin);
		}
		OJAnnotatedOperation getInputPins = new OJAnnotatedOperation("getInputPins");
		TinkerGenerationUtil.addOverrideAnnotation(getInputPins);
		OJPathName returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends InputPin<?>"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
		getInputPins.setReturnType(returnType);
		String statement = sb.toString();
		if (hasInputPins) {
			statement = statement.substring(0, sb.toString().length() - 1);
		}
		statement += ")";
		getInputPins.getBody().addToStatements(statement);
		actionClass.addToOperations(getInputPins);
		actionClass.addToImports(TinkerBehaviorUtil.tinkerInputPinPathName);
	}

	private void addActivityNodeOperations(OJClass actionClass, INakedActivityNode oa) {
		addGetOutControlFlows(actionClass, oa);
		addGetInControlFlows(actionClass, oa);
		addOutControlFlowGetters(actionClass, oa);
		addInControlFlowGetters(actionClass, oa);
		addConstructorWithVertex(actionClass, oa.getActivity().getContext());
		addGetContextObject(actionClass, oa.getActivity().getContext());
	}

	private void addGetContextObject(OJClass actionClass, INakedBehavioredClassifier context) {
		OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject");
		getContextObject.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		getContextObject.setReturnType(OJUtil.classifierPathname(context));
		getContextObject.getBody().addToStatements("return this.contextObject");
		actionClass.addToOperations(getContextObject);
	}

	private void addGetInControlFlows(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getInControlFlows = new OJAnnotatedOperation("getInFlows");
		TinkerGenerationUtil.addOverrideAnnotation(getInControlFlows);

		OJPathName tokenPathName;
		if (oa instanceof INakedObjectNode) {
			actionClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
			tokenPathName = TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy();
			tokenPathName.addToGenerics(OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType()));
		} else {
			actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
			tokenPathName = TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy();
		}
		OJPathName returnType = new OJPathName("java.util.List");
		OJPathName edgePathName;
		if (oa instanceof INakedControlNode
				&& (((INakedControlNode) oa).getControlNodeType() == ControlNodeType.FLOW_FINAL_NODE || ((INakedControlNode) oa).getControlNodeType() == ControlNodeType.ACTIVITY_FINAL_NODE)) {
			edgePathName = TinkerBehaviorUtil.tinkerActivityEdgePathName.getCopy();
			// edgePathName.addToGenerics(TinkerBehaviorUtil.tinkerTokenPathName.getCopy());
			edgePathName.addToGenerics(new OJPathName("?"));
			actionClass.addToImports(TinkerBehaviorUtil.tinkerTokenPathName);
		} else {
			edgePathName = TinkerBehaviorUtil.tinkerActivityEdgePathName.getCopy();
			edgePathName.addToGenerics(tokenPathName);
		}
		returnType.addToElementTypes(new OJPathName("? extends " + edgePathName.getLast()));
		getInControlFlows.setReturnType(returnType);

		StringBuilder sb = new StringBuilder("return Arrays.asList(");
		boolean first = true;
		for (INakedActivityEdge edge : oa.getIncoming()) {
			if (!first) {
				sb.append(", ");
			}
			first = false;
			sb.append(TinkerBehaviorUtil.edgeGetter(edge));
			sb.append("()");
		}
		sb.append(")");
		getInControlFlows.getBody().addToStatements(sb.toString());
		actionClass.addToImports(new OJPathName("java.util.Arrays"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		actionClass.addToOperations(getInControlFlows);
	}

	private void addGetInControlFlow(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getInControlFlows = new OJAnnotatedOperation("getInFlow");

		String tokenType;
		if (oa instanceof INakedObjectNode) {
			tokenType = TinkerBehaviorUtil.tinkerObjectTokenPathName.getLast();
		} else {
			tokenType = TinkerBehaviorUtil.tinkerControlTokenPathName.getLast();
		}
		OJPathName returnType = new OJPathName(TinkerBehaviorUtil.tinkerActivityEdgePathName.toJavaString() + "<" + tokenType + ">");
		getInControlFlows.setReturnType(returnType);

		for (INakedActivityEdge edge : oa.getIncoming()) {
			getInControlFlows.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(edge) + "()");
		}
		actionClass.addToImports(new OJPathName("java.util.Arrays"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		actionClass.addToOperations(getInControlFlows);
	}

	private void addInControlFlowGetters(OJClass actionClass, INakedActivityNode oa) {
		for (INakedActivityEdge edge : oa.getIncoming()) {
			buildIncomingControlFlowGetter(actionClass, edge);
		}
	}

	private void buildIncomingControlFlowGetter(OJClass actionClass, INakedActivityEdge edge) {
		OJAnnotatedOperation flowGetter = new OJAnnotatedOperation(TinkerBehaviorUtil.edgeGetter(edge));
		OJPathName edgePathname = TinkerBehaviorUtil.edgePathname(edge);
		actionClass.addToImports(edgePathname);
		flowGetter.setReturnType(edgePathname);
		flowGetter.getBody().addToStatements("return new " + edgePathname.getLast() + "(vertex.getInEdges(\"" + edge.getName() + "\").iterator().next(), this.contextObject)");
		actionClass.addToOperations(flowGetter);
	}

	private OJConstructor addConstructorWithVertex(OJClass actionClass, INakedBehavioredClassifier contextObject) {
		OJConstructor constructorWithEdge = new OJConstructor();
		constructorWithEdge.addParam("vertex", TinkerGenerationUtil.vertexPathName);
		constructorWithEdge.addParam("contextObject", OJUtil.classifierPathname(contextObject));
		constructorWithEdge.getBody().addToStatements("super(vertex)");
		constructorWithEdge.getBody().addToStatements("this.contextObject = contextObject");
		actionClass.addToConstructors(constructorWithEdge);
		return constructorWithEdge;
	}

	private void addOutControlFlowGetters(OJClass actionClass, INakedActivityNode oa) {
		for (INakedActivityEdge edge : oa.getOutgoing()) {
			buildOutgoingControlFlowGetter(actionClass, edge);
		}
	}

	private void buildOutgoingControlFlowGetter(OJClass actionClass, INakedActivityEdge edge) {
		OJAnnotatedOperation flowGetter = new OJAnnotatedOperation(TinkerBehaviorUtil.edgeGetter(edge));
		OJPathName edgePathname = TinkerBehaviorUtil.edgePathname(edge);
		actionClass.addToImports(edgePathname);
		flowGetter.setReturnType(edgePathname);
		flowGetter.getBody().addToStatements("return new " + edgePathname.getLast() + "(vertex.getOutEdges(\"" + edge.getName() + "\").iterator().next(), this.contextObject)");
		actionClass.addToOperations(flowGetter);
	}

	private void addGetOutControlFlows(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getOutControlFlows = new OJAnnotatedOperation("getOutFlows");
		TinkerGenerationUtil.addOverrideAnnotation(getOutControlFlows);

		OJPathName tokenPathName;
		if (oa instanceof INakedObjectNode) {
			tokenPathName = TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy();
			actionClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName);
			tokenPathName.addToGenerics(OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType()));
		} else {
			actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
			tokenPathName = TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy();
		}

		OJPathName returnType = new OJPathName("java.util.List");
		OJPathName edgePathName = TinkerBehaviorUtil.tinkerActivityEdgePathName.getCopy();
		edgePathName.addToGenerics(tokenPathName);
		returnType.addToElementTypes(new OJPathName("? extends " + edgePathName.getLast()));
		getOutControlFlows.setReturnType(returnType);

		StringBuilder sb = new StringBuilder("return Arrays.asList(");
		boolean first = true;
		for (INakedActivityEdge edge : oa.getOutgoing()) {
			if (!first) {
				sb.append(", ");
			}
			first = false;
			sb.append(TinkerBehaviorUtil.edgeGetter(edge));
			sb.append("()");
		}
		sb.append(")");
		getOutControlFlows.getBody().addToStatements(sb.toString());
		actionClass.addToImports(new OJPathName("java.util.Arrays"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		actionClass.addToOperations(getOutControlFlows);
	}

	private void addGetOutControlFlow(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getOutControlFlows = new OJAnnotatedOperation("getOutFlow");

		String tokenType;
		if (oa instanceof INakedObjectNode) {
			tokenType = TinkerBehaviorUtil.tinkerObjectTokenPathName.getLast();
		} else {
			tokenType = TinkerBehaviorUtil.tinkerControlTokenPathName.getLast();
		}
		OJPathName returnType = new OJPathName(TinkerBehaviorUtil.tinkerActivityEdgePathName.toJavaString() + "<" + tokenType + ">");
		getOutControlFlows.setReturnType(returnType);

		// getOutControlFlows.setReturnType(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		for (INakedActivityEdge edge : oa.getOutgoing()) {
			getOutControlFlows.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(edge) + "()");
		}
		actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		actionClass.addToOperations(getOutControlFlows);
	}

	private void addHasPreConditionPassed(OJClass actionClass) {
		OJAnnotatedOperation hasPreConditionPassed = new OJAnnotatedOperation("hasPreConditionPassed");
		hasPreConditionPassed.setReturnType(new OJPathName("boolean"));
		hasPreConditionPassed.getBody().addToStatements("return true");
		hasPreConditionPassed.setVisibility(OJVisibilityKind.PROTECTED);
		hasPreConditionPassed.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		actionClass.addToOperations(hasPreConditionPassed);
	}

	private void addHasPostConditionPassed(OJClass actionClass) {
		OJAnnotatedOperation hasPostConditionPassed = new OJAnnotatedOperation("hasPostConditionPassed");
		hasPostConditionPassed.setReturnType(new OJPathName("boolean"));
		hasPostConditionPassed.getBody().addToStatements("return true");
		hasPostConditionPassed.setVisibility(OJVisibilityKind.PROTECTED);
		hasPostConditionPassed.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		actionClass.addToOperations(hasPostConditionPassed);
	}

	private void implementUpperBound(OJAnnotatedClass activityParameterNodeClass, INakedObjectNode oa) {
		OJAnnotatedOperation getUpperBound = new OJAnnotatedOperation("getUpperBound", new OJPathName("int"));
		TinkerGenerationUtil.addOverrideAnnotation(getUpperBound);
		if (oa.getUpperBound() != null) {
			getUpperBound.getBody().addToStatements("return " + oa.getUpperBound().intValue());
		} else {
			getUpperBound.getBody().addToStatements("return " + Integer.MAX_VALUE);
		}
		activityParameterNodeClass.addToOperations(getUpperBound);
	}

	private void implementMultiplicity(OJAnnotatedClass inputPinClass, INakedPin oa) {
		OJAnnotatedOperation getUpperMultiplicity = new OJAnnotatedOperation("getUpperMultiplicity", new OJPathName("int"));
		TinkerGenerationUtil.addOverrideAnnotation(getUpperMultiplicity);
		getUpperMultiplicity.getBody().addToStatements("return " + oa.getNakedMultiplicity().getUpper());
		inputPinClass.addToOperations(getUpperMultiplicity);

		OJAnnotatedOperation getLowerMultiplicity = new OJAnnotatedOperation("getLowerMultiplicity", new OJPathName("int"));
		TinkerGenerationUtil.addOverrideAnnotation(getLowerMultiplicity);
		getLowerMultiplicity.getBody().addToStatements("return " + oa.getNakedMultiplicity().getLower());
		inputPinClass.addToOperations(getLowerMultiplicity);

	}

	private void implementGetAction(OJAnnotatedClass inputPinClass, INakedPin oa) {
		OJAnnotatedOperation getAction = new OJAnnotatedOperation("getAction");
		TinkerGenerationUtil.addOverrideAnnotation(getAction);
		getAction.setReturnType(TinkerBehaviorUtil.activityNodePathName(oa.getAction()));
		getAction.getBody().addToStatements(
				"return new " + TinkerBehaviorUtil.activityNodePathName(oa.getAction()).getLast() + "(this.vertex.getInEdges(\"inputPin"
						+ NameConverter.capitalize(oa.getName() + "\").iterator().next().getOutVertex(), this.contextObject)"));
		inputPinClass.addToOperations(getAction);
	}

	private void addGetBodyExpression(OJAnnotatedClass actionClass, INakedOclAction oa) {
		OJAnnotatedOperation getBodyExpression = new OJAnnotatedOperation("getBodyExpression");
		actionClass.addToOperations(getBodyExpression);
		if (oa.getBodyExpression() != null) {
			getBodyExpression.getBody().addToStatements(
					"return "
							+ ValueSpecificationUtil.expressValue(getBodyExpression, new NakedValueSpecificationImpl(oa.getBodyExpression()), oa.getActivity(), oa.getReturnPin()
									.getType()));
			getBodyExpression.setReturnType(OJUtil.classifierPathname(oa.getReturnPin().getNakedBaseType()));
		} else {
			getBodyExpression.getBody().addToStatements("return null");
			getBodyExpression.setReturnType(new OJPathName("java.lang.Object"));
		}
	}

	private void implementCopyTokensToStart(OJAnnotatedClass outputPinClass) {
		OJAnnotatedOperation copyTokensToStart = new OJAnnotatedOperation("copyTokensToStart");
		TinkerGenerationUtil.addOverrideAnnotation(copyTokensToStart);
		copyTokensToStart.setVisibility(OJVisibilityKind.PROTECTED);
		copyTokensToStart.getBody().addToStatements("setStarts(getOutTokens())");
		outputPinClass.addToOperations(copyTokensToStart);
	}

	private void addCopySignalToOutputPin(OJAnnotatedClass actionClass, INakedAcceptEventAction oa) {
		OJAnnotatedOperation copySignalToOutputPin = new OJAnnotatedOperation("copySignalToOutputPin");
		TinkerGenerationUtil.addOverrideAnnotation(copySignalToOutputPin);
		copySignalToOutputPin.addParam("signal", TinkerBehaviorUtil.tinkerSignalPathName.getCopy());
		//TODO think about many triggers here, for now kinda assuming one
		for (INakedTrigger trigger : oa.getTriggers()) {
			if (trigger.getEvent() instanceof INakedSignalEvent) {
				INakedSignalEvent signalEvent = (INakedSignalEvent) trigger.getEvent();
				INakedSignal signal = signalEvent.getSignal();
				OJIfStatement ifInstanceOf = new OJIfStatement("signal instanceof " + OJUtil.classifierPathname(signal).getLast());
				//Correlate signal attributes with output pins
				int i = 0;
				for (INakedOutputPin outputPin : oa.getOutput()) {
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(signal.getOwnedAttributes().get(i++));
					ifInstanceOf.addToThenPart(TinkerBehaviorUtil.outputPinGetterName(outputPin) + "().addOutgoingToken(new ObjectToken<" + map.javaBaseTypePath().getLast() + ">(" + TinkerBehaviorUtil.outputPinGetterName(outputPin) + "().getName(), (("+OJUtil.classifierPathname(signal).getLast()+")signal)." + map.getter() + "()))");
					actionClass.addToImports(map.javaBaseTypePath());
				}
				copySignalToOutputPin.getBody().addToStatements(ifInstanceOf);
			}
			
		}
		actionClass.addToOperations(copySignalToOutputPin);
		
	}

}
