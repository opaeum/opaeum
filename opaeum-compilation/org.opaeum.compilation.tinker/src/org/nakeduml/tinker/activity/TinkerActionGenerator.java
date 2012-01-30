package org.nakeduml.tinker.activity;

import java.util.Collection;

import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.nakeduml.tinker.generator.TinkerImplementNodeStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedOpaqueAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerImplementNodeStep.class }, after = { TinkerImplementNodeStep.class })
public class TinkerActionGenerator extends StereotypeAnnotator {

	@VisitBefore(matchSubclasses = true, match = { INakedOpaqueAction.class })
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(oa.getName());
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAbstractActionPathName);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedAcceptEventAction.class })
	public void visitAcceptEventAction(INakedAcceptEventAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(oa.getName());
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAbstractAcceptEventAction);
		addActionOperations(actionClass, oa);
		OJConstructor constructor = actionClass.findConstructor(TinkerGenerationUtil.vertexPathName, OJUtil.classifierPathname(oa.getActivity().getContext()));
		addTriggersInConstructor(constructor, actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		OJConstructor constructor1 = actionClass.findConstructor(OJUtil.classifierPathname(oa.getActivity().getContext()));
		addTriggersInConstructor(constructor1, actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedSendSignalAction.class })
	public void visitSendSignalAction(INakedSendSignalAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(oa.getName());
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAbstractSendSignalAction);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		addConstructSignal(actionClass, oa);
		addResolveTarget(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	private void addResolveTarget(OJAnnotatedClass actionClass, INakedSendSignalAction oa) {
		OJAnnotatedOperation resolveTarget = new OJAnnotatedOperation("resolveTarget");
		resolveTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		resolveTarget.setReturnType(TinkerBehaviorUtil.tinkerBaseTinkerBehavioredClassifier);
		actionClass.addToOperations(resolveTarget);
		INakedValuePin targetValuePin = (INakedValuePin) oa.getTarget();
		resolveTarget.getBody().addToStatements("return " + ValueSpecificationUtil.expressValue(resolveTarget, targetValuePin.getValue(), oa.getContext(), targetValuePin.getType()));
	}

	private void addConstructSignal(OJAnnotatedClass actionClass, INakedSendSignalAction oa) {
		OJAnnotatedOperation constructSignal = new OJAnnotatedOperation("constructSignal");
		constructSignal.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		constructSignal.setReturnType(TinkerBehaviorUtil.tinkerSignalPathName);
		OJPathName signalPathname = OJUtil.classifierPathname(oa.getSignal());
		constructSignal.getBody().addToStatements("return new " + signalPathname.getLast() + "(true)");
		actionClass.addToImports(signalPathname);
		actionClass.addToOperations(constructSignal);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedActivityEdge.class })
	public void visitActivityEdge(INakedActivityEdge edge) {
		createControlFlowEdgeClass(edge);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedControlNode.class })
	public void visitControlNode(INakedControlNode controlNode) {
		OJAnnotatedClass controlNodeClass = null;
		switch (controlNode.getControlNodeType()) {
		case ACTIVITY_FINAL_NODE:
			controlNodeClass = createFinalNodeClass(controlNode);
			break;
		case INITIAL_NODE:
			controlNodeClass = createInitialNodeClass(controlNode);
			break;
		case FORK_NODE:
			controlNodeClass = createForkNodeClass(controlNode, TinkerBehaviorUtil.tinkerAbstractForkNodePathName);
			break;
		case MERGE_NODE:
			controlNodeClass = createMergeNodeClass(controlNode);
			break;
		case JOIN_NODE:
			controlNodeClass = createJoinNodeClass(controlNode);
			break;
		case FLOW_FINAL_NODE:
			controlNodeClass = createFinalNodeClass(controlNode);
			break;
		case DECISION_NODE:
			// Same as fork, one in multipl out
			controlNodeClass = createForkNodeClass(controlNode, TinkerBehaviorUtil.tinkerAbstractDecisionNodePathName);
			break;
		default:
			break;
		}
		addContextObjectField(controlNodeClass, controlNode.getActivity().getContext());
		addContextObjectToDefaultConstructor(controlNodeClass, controlNode.getActivity().getContext());
		addGetContextObject(controlNodeClass, controlNode.getActivity().getContext());
	}

	private void addTriggersInConstructor(OJConstructor constructor, OJClass actionClass, INakedAcceptEventAction oa) {
		Collection<INakedTrigger> triggers = oa.getTriggers();
		for (INakedTrigger trigger : triggers) {
			if(trigger.getEvent() instanceof INakedSignalEvent){
				INakedSignalEvent signalEvent = (INakedSignalEvent) trigger.getEvent();
				constructor.getBody().addToStatements("addToTriggers(\"" + trigger.getName() +"\", "+OJUtil.classifierPathname(signalEvent.getSignal()).getLast()+".class)");
				actionClass.addToImports(OJUtil.classifierPathname(signalEvent.getSignal()));
			}
		}
	}

	private OJAnnotatedClass createInitialNodeClass(INakedControlNode controlNode) {
		OJAnnotatedClass initControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		initControlNode.setMyPackage(pack);
		initControlNode.setSuperclass(TinkerBehaviorUtil.tinkerAbstractInitialNodePathName);
		super.createTextPath(initControlNode, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addDefaultConstructor(initControlNode, controlNode);
		addConstructorWithVertex(initControlNode, controlNode.getActivity().getContext());
		addGetOutControlFlows(initControlNode, controlNode);
		addOutControlFlowGetters(initControlNode, controlNode);
		return initControlNode;
	}

	private OJAnnotatedClass createMergeNodeClass(INakedControlNode controlNode) {
		OJAnnotatedClass mergeControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		mergeControlNode.setMyPackage(pack);
		mergeControlNode.setSuperclass(TinkerBehaviorUtil.tinkerAbstractMergeNodePathName);
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
		return mergeControlNode;
	}

	private OJAnnotatedClass createJoinNodeClass(INakedControlNode controlNode) {
		OJAnnotatedClass joinControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		joinControlNode.setMyPackage(pack);
		joinControlNode.setSuperclass(TinkerBehaviorUtil.tinkerAbstractJoinNodePathName);
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
		return joinControlNode;
	}

	private OJAnnotatedClass createForkNodeClass(INakedControlNode controlNode, OJPathName superClass) {
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
			throw new IllegalStateException("Fok node can only have one incoming edge");
		}
		addInControlFlowGetters(forkControlNode, controlNode);
		return forkControlNode;
	}

	private void addDefaultConstructor(OJAnnotatedClass controlNodeClass, INakedActivityNode controlNode) {
		OJConstructor defaultConstructor = new OJConstructor();
		initVertexInConstructor(controlNodeClass, controlNode, defaultConstructor);
	}

	private void initVertexInConstructor(OJAnnotatedClass controlNodeClass, INakedActivityNode controlNode, OJConstructor defaultConstructor) {
		defaultConstructor.getBody().addToStatements("super(true, \"" + controlNodeClass.getName() + "\")");
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

	private OJAnnotatedClass createFinalNodeClass(INakedControlNode controlNode) {
		OJAnnotatedClass finalControlNode = new OJAnnotatedClass(NameConverter.capitalize(controlNode.getName()));
		OJPathName path = OJUtil.packagePathname(controlNode.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		finalControlNode.setMyPackage(pack);
		finalControlNode.setSuperclass(TinkerBehaviorUtil.tinkerAbstractFinalNodePathName);
		super.createTextPath(finalControlNode, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addDefaultConstructor(finalControlNode, controlNode);
		addConstructorWithVertex(finalControlNode, controlNode.getActivity().getContext());
		addGetInControlFlows(finalControlNode, controlNode);
		addInControlFlowGetters(finalControlNode, controlNode);
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
		controlFlowEdge.setSuperclass(TinkerBehaviorUtil.tinkerAbstractControlFlowEdgePathName);
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
		OJPathName actionPathName = TinkerBehaviorUtil.actionPathName(edge.getSource());
		getSourceAction.setReturnType(actionPathName);
		getSourceAction.getBody().addToStatements("return new " + actionPathName.getLast() + "(this.edge.getOutVertex(), this.contextObject)");
		controlFlowEdge.addToOperations(getSourceAction);

		OJAnnotatedOperation getTarget = new OJAnnotatedOperation("getSource");
		getTarget.setReturnType(TinkerBehaviorUtil.tinkerAbstractNodePathName);
		getTarget.getBody().addToStatements("return " + TinkerBehaviorUtil.actionSourceGetter(edge) + "()");
		getTarget.setVisibility(OJVisibilityKind.PROTECTED);
		getTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getTarget);
	}

	private void addName(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation getName = new OJAnnotatedOperation("getName");
		getName.setReturnType(new OJPathName("String"));
		getName.getBody().addToStatements("return \"" + edge.getName() + "\"");
		controlFlowEdge.addToOperations(getName);
	}

	private void addTarget(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation getTargetAction = new OJAnnotatedOperation(TinkerBehaviorUtil.actionTargetGetter(edge));
		OJPathName actionPathName = TinkerBehaviorUtil.actionPathName(edge.getTarget());
		getTargetAction.setReturnType(actionPathName);
		getTargetAction.getBody().addToStatements("return new " + actionPathName.getLast() + "(this.edge.getInVertex(), this.contextObject)");
		controlFlowEdge.addToOperations(getTargetAction);

		OJAnnotatedOperation getTarget = new OJAnnotatedOperation("getTarget");
		getTarget.setReturnType(TinkerBehaviorUtil.tinkerAbstractNodePathName);
		getTarget.getBody().addToStatements("return " + TinkerBehaviorUtil.actionTargetGetter(edge) + "()");
		getTarget.setVisibility(OJVisibilityKind.PROTECTED);
		getTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getTarget);
	}

	private void addEvaluateGuardCondition(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation evaluateGuardConditions = new OJAnnotatedOperation("evaluateGuardConditions");
		evaluateGuardConditions.setReturnType(new OJPathName("boolean"));
		evaluateGuardConditions.addParam("controlToken", TinkerBehaviorUtil.tinkerControlTokenPathName);
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

	private void addActionOperations(OJClass actionClass, INakedAction oa) {
		addHasPostConditionPassed(actionClass);
		addHasPreConditionPassed(actionClass);
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
		OJAnnotatedOperation getInControlFlows = new OJAnnotatedOperation("getInControlFlows");
		OJPathName returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends AbstractControlFlowEdge"));
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
		actionClass.addToImports(TinkerBehaviorUtil.tinkerAbstractControlFlowEdgePathName);
		actionClass.addToOperations(getInControlFlows);
	}

	private void addGetInControlFlow(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getInControlFlows = new OJAnnotatedOperation("getInControlFlow");
		getInControlFlows.setReturnType(TinkerBehaviorUtil.tinkerAbstractControlFlowEdgePathName);
		for (INakedActivityEdge edge : oa.getIncoming()) {
			getInControlFlows.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(edge) + "()");
		}
		actionClass.addToImports(new OJPathName("java.util.Arrays"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerAbstractControlFlowEdgePathName);
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
		OJAnnotatedOperation getOutControlFlows = new OJAnnotatedOperation("getOutControlFlows");
		OJPathName returnType = new OJPathName("java.util.List");
		returnType.addToElementTypes(new OJPathName("? extends AbstractControlFlowEdge"));
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
		actionClass.addToImports(TinkerBehaviorUtil.tinkerAbstractControlFlowEdgePathName);
		actionClass.addToOperations(getOutControlFlows);
	}

	private void addGetOutControlFlow(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getOutControlFlows = new OJAnnotatedOperation("getOutControlFlow");
		getOutControlFlows.setReturnType(TinkerBehaviorUtil.tinkerAbstractControlFlowEdgePathName);
		for (INakedActivityEdge edge : oa.getOutgoing()) {
			getOutControlFlows.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(edge) + "()");
		}
		actionClass.addToImports(TinkerBehaviorUtil.tinkerAbstractControlFlowEdgePathName);
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

}
