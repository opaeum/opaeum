package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.model.ParameterDirectionKind;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

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
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedOclAction;
import org.opaeum.metamodel.actions.INakedOpaqueAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.activities.ControlNodeType;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedValueSpecificationImpl;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
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
			activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerInActivityParameterNodePathName.getCopy());
			addGetOutFlows(activityParameterNodeClass, oa);
			addOutControlFlowGetters(activityParameterNodeClass, oa);
		} else if ((oa.getParameter().getDirection() == ParameterDirectionKind.IN) || (oa.getParameter().getDirection() == ParameterDirectionKind.INOUT)) {
			activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerOutActivityParameterNodePathName.getCopy());
			addGetInFlows(activityParameterNodeClass, oa);
			addInControlFlowGetters(activityParameterNodeClass, oa);
		}
		OJPathName parameterPath = activityParameterNodeClass.getSuperclass();
		parameterPath.addToGenerics(OJUtil.classifierPathname(oa.getParameter().getNakedBaseType()));
		activityParameterNodeClass.addToImports(OJUtil.classifierPathname(oa.getParameter().getNakedBaseType()));
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
		INakedActivityEdge next;
		switch (controlNode.getControlNodeType()) {
		case ACTIVITY_FINAL_NODE:
			controlNodeClass = createFinalNodeClass(controlNode, TinkerBehaviorUtil.tinkerFinalNodePathName.getCopy());
			break;
		case INITIAL_NODE:
			controlNodeClass = createInitialNodeClass(controlNode);
			break;
		case FORK_NODE:
			next = controlNode.getIncoming().iterator().next();
			if (next instanceof INakedObjectFlow) {
				superPath = TinkerBehaviorUtil.tinkerForkNodeObjectTokenPathName.getCopy();
			} else {
				superPath = TinkerBehaviorUtil.tinkerForkNodeControlTokenPathName.getCopy();
			}
			controlNodeClass = createDecisionOrForkNodeClass(controlNode, superPath);
			break;
		case MERGE_NODE:
			next = controlNode.getIncoming().iterator().next();
			if (next instanceof INakedObjectFlow) {
				
				List<INakedClassifier> c = new ArrayList<INakedClassifier>();
				Exitter mutableBoolean = new Exitter(false);
				getObjectFlowPathName(controlNode, c, mutableBoolean);
				
				if (!mutableBoolean.shouldExit()) {
					OJPathName pathName = OJUtil.classifierPathname(c.get(0));
					superPath = TinkerBehaviorUtil.tinkerMergeNodeObjectTokenKnownPathName.getCopy();
					superPath.addToGenerics(pathName);
				} else {
					superPath = TinkerBehaviorUtil.tinkerMergeNodeObjectTokenUnknownPathName.getCopy();
				}
			} else {
				superPath = TinkerBehaviorUtil.tinkerMergeNodeControlTokenPathName.getCopy();
			}
			controlNodeClass = createMergeNodeClass(controlNode, superPath);
			break;
		case JOIN_NODE:
			next = controlNode.getIncoming().iterator().next();
			if (next instanceof INakedObjectFlow) {
				
				List<INakedClassifier> c = new ArrayList<INakedClassifier>();
				Exitter mutableBoolean = new Exitter(false);
				getObjectFlowPathName(controlNode, c, mutableBoolean);
				
				if (!mutableBoolean.shouldExit()) {
					OJPathName pathName = OJUtil.classifierPathname(c.get(0));
					superPath = TinkerBehaviorUtil.tinkerJoinNodeObjectTokenKnownPathName.getCopy();
					superPath.addToGenerics(pathName);
				} else {
					superPath = TinkerBehaviorUtil.tinkerJoinNodeObjectTokenUnknownPathName.getCopy();
				}
			} else {
				superPath = TinkerBehaviorUtil.tinkerJoinNodeControlTokenPathName.getCopy();
			}
			controlNodeClass = createJoinNodeClass(controlNode, superPath);
			break;
		case FLOW_FINAL_NODE:
			controlNodeClass = createFinalNodeClass(controlNode, TinkerBehaviorUtil.tinkerFinalNodePathName.getCopy());
			break;
		case DECISION_NODE:
			next = controlNode.getIncoming().iterator().next();
			if (next instanceof INakedObjectFlow) {
				List<INakedClassifier> c = new ArrayList<INakedClassifier>();
				Exitter mutableBoolean = new Exitter(false);
				getObjectFlowPathName(controlNode, c, mutableBoolean);
				
				if (!mutableBoolean.shouldExit()) {
					OJPathName pathName = OJUtil.classifierPathname(c.get(0));
					superPath = TinkerBehaviorUtil.tinkerDecisionObjectTokenNodeKnownPathName.getCopy();
					superPath.addToGenerics(pathName);
				} else {
					superPath = TinkerBehaviorUtil.tinkerDecisionObjectTokenNodeUnknownPathName.getCopy();
				}
			} else {
				superPath = TinkerBehaviorUtil.tinkerDecisionControlTokenNodePathName.getCopy();
			}
			controlNodeClass = createDecisionOrForkNodeClass(controlNode, superPath);
			break;
		default:
			break;
		}
		if (tokenType.equals(TinkerBehaviorUtil.tinkerObjectTokenPathName.getLast())) {
			controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy());
		} else {
			controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy());
		}
		addContextObjectField(controlNodeClass, controlNode.getActivity().getContext());
		addContextObjectToDefaultConstructor(controlNodeClass, controlNode.getActivity().getContext());
		addGetContextObject(controlNodeClass, controlNode.getActivity().getContext());
	}

	// TODO this needs to be recursive no?
	private void getObjectFlowPathName(INakedControlNode controlNode, List<INakedClassifier> c, Exitter mutableBoolean) {
		// if all edges are object nodes and have the same type then return it
		if (!mutableBoolean.shouldExit()) {
			for (INakedActivityEdge edge : controlNode.getIncoming()) {
				if (edge instanceof INakedObjectFlow) {
					INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
					INakedActivityNode source = objectFlow.getSource();
					if (source instanceof INakedObjectNode) {
						INakedObjectNode objectNode = (INakedObjectNode) source;
						if (c.isEmpty()) {
							c.add(objectNode.getNakedBaseType());
						} else {
							if (!c.get(0).equals(objectNode.getNakedBaseType())) {
								mutableBoolean.setExit(false);
								break;
							}
						}
					} else {
						getObjectFlowPathName((INakedControlNode) source, c, mutableBoolean);
					}
				} else {
					mutableBoolean.setExit(false);
					break;
				}
			}
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
					INakedValuePin valuePin = (INakedValuePin) inputPin;
					constructSignal.getBody().addToStatements(
							signalField.getName() + "." + map.setter() + "("
									+ ValueSpecificationUtil.expressValue(constructSignal, valuePin.getValue(), oa.getContext(), valuePin.getType()) + ")");
				} else {
					// TODO
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
		addGetOutFlows(initControlNode, controlNode);
		addOutControlFlowGetters(initControlNode, controlNode);

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
		addGetInFlows(mergeControlNode, controlNode);
		addInControlFlowGetters(mergeControlNode, controlNode);
		addGetOutFlow(mergeControlNode, controlNode);
		if (controlNode.getOutgoing().size() > 1) {
			throw new IllegalStateException("Join node can only have one outgoing edge");
		}
		addOutControlFlowGetters(mergeControlNode, controlNode);
		mergeControlNode.addToImports(mergeControlNode.getSuperclass().getGenerics());
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
		addGetInFlows(joinControlNode, controlNode);
		addInControlFlowGetters(joinControlNode, controlNode);
		addGetOutFlow(joinControlNode, controlNode);
		if (controlNode.getOutgoing().size() > 1) {
			throw new IllegalStateException("Join node can only have one outgoing edge");
		}
		addOutControlFlowGetters(joinControlNode, controlNode);
		joinControlNode.addToImports(joinControlNode.getSuperclass().getGenerics());
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
		addGetOutFlows(forkControlNode, controlNode);
		addOutControlFlowGetters(forkControlNode, controlNode);
		addGetInFlow(forkControlNode, controlNode);
		if (controlNode.getIncoming().size() > 1) {
			throw new IllegalStateException("Fork node can only have one incoming edge");
		}
		addInControlFlowGetters(forkControlNode, controlNode);
		forkControlNode.addToImports(forkControlNode.getSuperclass().getGenerics());
		return forkControlNode;
	}

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
		addGetInFlows(finalControlNode, controlNode);
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
			OJPathName superClass;
			if (edge.getSource() instanceof INakedControlNode) {
				
				List<INakedClassifier> c = new ArrayList<INakedClassifier>();
				Exitter mutableBoolean = new Exitter(false);
				getObjectFlowPathName((INakedControlNode) edge.getSource(), c, mutableBoolean);
				
				if (!mutableBoolean.shouldExit()) {
					OJPathName objectTokenKnownPathName = OJUtil.classifierPathname(c.get(0));
					superClass = TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getCopy();
					superClass.addToGenerics(objectTokenKnownPathName);
					controlFlowEdge.addToImports(objectTokenKnownPathName);
				} else {
					superClass = TinkerBehaviorUtil.tinkerObjectFlowUnknownPathName.getCopy();
				}
			} else {
				INakedObjectNode originatingObjectNode = ((INakedObjectFlow) edge).getOriginatingObjectNode();
				OJPathName genericPathname;
				if (originatingObjectNode != null) {
					superClass = TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getCopy();
					genericPathname = OJUtil.classifierPathname(originatingObjectNode.getNakedBaseType());
					superClass.addToGenerics(genericPathname);
					controlFlowEdge.addToImports(genericPathname);
				} else {
					superClass = TinkerBehaviorUtil.tinkerObjectFlowUnknownPathName.getCopy();
				}
			}
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
			INakedObjectNode originatingObjectNode = ((INakedObjectFlow) edge).getOriginatingObjectNode();
			if (originatingObjectNode != null) {
				tokenPathName.addToGenerics(OJUtil.classifierPathname(originatingObjectNode.getNakedBaseType()));
			} else {
				tokenPathName.addToGenerics(new OJPathName("java.lang.Object"));
			}
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
			INakedObjectNode originatingObjectNode = ((INakedObjectFlow) edge).getOriginatingObjectNode();
			if (originatingObjectNode != null) {
				tokenPathName.addToGenerics(OJUtil.classifierPathname(originatingObjectNode.getNakedBaseType()));
			} else {
				tokenPathName.addToGenerics(new OJPathName("java.loang.Object"));
			}
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
		controlFlowEdge.addToOperations(evaluateGuardConditions);
		String guardEvaluation;
		if (edge instanceof INakedObjectFlow) {
			evaluateGuardConditions.addParam("token", TinkerBehaviorUtil.tinkerObjectTokenPathName);
			guardEvaluation = ValueSpecificationUtil.expressValue(evaluateGuardConditions, edge.getGuard(), edge.getActivity() , new StdlibPrimitiveType("Boolean"));
		} else {
			evaluateGuardConditions.addParam("token", TinkerBehaviorUtil.tinkerControlTokenPathName);
			guardEvaluation = ValueSpecificationUtil.expressValue(controlFlowEdge, edge.getGuard(), new StdlibPrimitiveType("Boolean"), false);
		}
		evaluateGuardConditions.getBody().addToStatements("return " + guardEvaluation);
		evaluateGuardConditions.setVisibility(OJVisibilityKind.PROTECTED);
		evaluateGuardConditions.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
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
			addGetInputPinVariables(actionClass, (INakedAction) oa);
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

		OJField result = new OJField();
		result.setName("result");
		result.setType(new OJPathName("java.util.List").addToGenerics(new OJPathName("Object")));
		result.setInitExp("new ArrayList<Object>()");
		;
		actionClass.addToImports(new OJPathName("java.util.ArrayList"));
		getInputPinVariables.getBody().addToLocals(result);

		for (INakedInputPin inputPin : oa.getInput()) {
			if (inputPin instanceof INakedValuePin || (oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
				continue;
			}
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
			OJIfStatement ifStatement = new OJIfStatement("this." + map.fieldname() + " != null", "result.add(this." + map.fieldname() + ")");
			getInputPinVariables.getBody().addToStatements(ifStatement);
		}
		getInputPinVariables.getBody().addToStatements("return result");
		actionClass.addToOperations(getInputPinVariables);
	}

	private void createVariablesForInputPins(OJClass actionClass, INakedAction oa) {
		for (INakedInputPin inputPin : oa.getInput()) {
			if (inputPin instanceof INakedValuePin || (oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
				continue;
			}
			// TODO move this somewhere useful
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
			OJField pinVariable = new OJField();
			pinVariable.setName(map.fieldname());
			pinVariable.setType(map.javaDefaultTypePath());
			actionClass.addToFields(pinVariable);
			OJAnnotatedOperation setterForPinVariable = new OJAnnotatedOperation(map.setter());
			setterForPinVariable.addParam("val", map.javaDefaultTypePath());
			actionClass.addToOperations(setterForPinVariable);
			setterForPinVariable.getBody().addToStatements("this." + map.fieldname() + " = val");
			OJAnnotatedOperation getterForPinVariable = new OJAnnotatedOperation(map.getter());
			getterForPinVariable.setReturnType(map.javaDefaultTypePath());
			OJField result = new OJField();
			result.setName("result");
			result.setType(map.javaDefaultTypePath());
			result.setInitExp("this." + map.fieldname());
			getterForPinVariable.getBody().addToLocals(result);
			getterForPinVariable.getBody().addToStatements("return result");
			actionClass.addToOperations(getterForPinVariable);
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

			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));

			OJIfStatement ifInputPinIsType = new OJIfStatement("inputPin instanceof " + NameConverter.capitalize(inputPin.getName()));
			addToInputPinVariable.getBody().addToStatements(ifInputPinIsType);
			ifInputPinIsType.addToThenPart(map.setter() + "((" + map.javaDefaultTypePath().getLast() + ")object)");
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
		addGetOutFlows(actionClass, oa);
		addGetInFlows(actionClass, oa);
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

	private void addGetOutFlow(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getOutFlow = new OJAnnotatedOperation("getOutFlow");

		INakedActivityEdge outEdge = oa.getOutgoing().iterator().next();
		OJPathName path = OJUtil.packagePathname(outEdge.getNameSpace()).getCopy();
		path.addToNames(NameConverter.capitalize(outEdge.getName()));
		getOutFlow.setReturnType(path);

		for (INakedActivityEdge edge : oa.getOutgoing()) {
			getOutFlow.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(edge) + "()");
		}
		actionClass.addToOperations(getOutFlow);
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

	private void addGetInFlows(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getInControlFlows = new OJAnnotatedOperation("getInFlows");
		TinkerGenerationUtil.addOverrideAnnotation(getInControlFlows);

		OJPathName returnType = new OJPathName("java.util.List");
		OJPathName edgePathName;
		StringBuilder sb;
		if (oa instanceof INakedControlNode
				&& (((INakedControlNode) oa).getControlNodeType() == ControlNodeType.FLOW_FINAL_NODE || ((INakedControlNode) oa).getControlNodeType() == ControlNodeType.ACTIVITY_FINAL_NODE)) {
			edgePathName = TinkerBehaviorUtil.tinkerActivityEdgePathName.getCopy();
			edgePathName.addToGenerics(new OJPathName("?"));
			actionClass.addToImports(TinkerBehaviorUtil.tinkerTokenPathName);
			returnType.addToElementTypes(new OJPathName("? extends " + edgePathName.getLast()));
			sb = new StringBuilder("return Arrays.asList(");
		} else {
			if (oa instanceof INakedObjectNode) {
				edgePathName = TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getCopy();
				OJPathName tokenGenericPathName = OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType());
				edgePathName.addToGenerics(tokenGenericPathName);
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.");
				sb.append("<" + TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getLast() + "<");
				sb.append(tokenGenericPathName.getLast());
				sb.append(">>asList(");
			} else if (oa instanceof INakedControlNode) {
				INakedControlNode controlNode = (INakedControlNode) oa;
				if (controlNode.getIncoming().iterator().next() instanceof INakedObjectFlow) {
					
					List<INakedClassifier> c = new ArrayList<INakedClassifier>();
					Exitter mutableBoolean = new Exitter(false);
					getObjectFlowPathName(controlNode, c, mutableBoolean);
					
					if (!mutableBoolean.shouldExit()) {
						OJPathName objectFlowKnownTokenPathName = OJUtil.classifierPathname(c.get(0));
						edgePathName = TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getCopy();
						edgePathName.addToGenerics(objectFlowKnownTokenPathName);
						returnType.addToElementTypes(edgePathName);
						sb = new StringBuilder("return Arrays.");
						sb.append("<" + TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getLast() + "<");
						sb.append(objectFlowKnownTokenPathName.getLast());
						sb.append(">>asList(");
					} else {
						edgePathName = TinkerBehaviorUtil.tinkerObjectFlowUnknownPathName.getCopy();
						returnType.addToElementTypes(edgePathName);
						sb = new StringBuilder("return Arrays.");
						sb.append("<" + TinkerBehaviorUtil.tinkerObjectFlowUnknownPathName.getLast() + ">asList(");

					}
				} else {
					edgePathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
					returnType.addToElementTypes(edgePathName);
					sb = new StringBuilder("return Arrays.<ControlFlow>asList(");
				}
			} else {
				edgePathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.<ControlFlow>asList(");
			}
		}
		getInControlFlows.setReturnType(returnType);

		boolean first = true;
		for (INakedActivityEdge edge : oa.getIncoming()) {
			if (!first) {
				sb.append(", ");
			}
			first = false;
			sb.append(TinkerBehaviorUtil.edgeGetter(edge));
			sb.append("()");

			if (edge.getSource() instanceof INakedControlNode) {
				OJPathName objectTokenKnownPathName = null;
				OJPathName controlNodeObjectTokenKnownPathName = null;
				if (oa instanceof INakedControlNode && !((INakedControlNode) oa).getControlNodeType().isFinalNode()) {
					
					List<INakedClassifier> c = new ArrayList<INakedClassifier>();
					Exitter mutableBoolean = new Exitter(false);
					getObjectFlowPathName((INakedControlNode) edge.getSource(), c, mutableBoolean);
					if (!mutableBoolean.shouldExit()) {
						objectTokenKnownPathName = OJUtil.classifierPathname(c.get(0));
					}

					c = new ArrayList<INakedClassifier>();
					mutableBoolean = new Exitter(false);
					getObjectFlowPathName((INakedControlNode) oa, c, mutableBoolean);
					if (!mutableBoolean.shouldExit()) {
						controlNodeObjectTokenKnownPathName = OJUtil.classifierPathname(c.get(0));
					}
					
				} else if (oa instanceof INakedObjectNode) {

					List<INakedClassifier> c = new ArrayList<INakedClassifier>();
					Exitter mutableBoolean = new Exitter(false);
					getObjectFlowPathName((INakedControlNode) edge.getSource(), c, mutableBoolean);
					if (!mutableBoolean.shouldExit()) {
						objectTokenKnownPathName = OJUtil.classifierPathname(c.get(0));
					}
					
					controlNodeObjectTokenKnownPathName = OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType());
				}
				if (controlNodeObjectTokenKnownPathName != null && objectTokenKnownPathName == null) {
					sb.append(".<");
					sb.append(controlNodeObjectTokenKnownPathName.getLast());
					sb.append(">convertToKnown()");
				} else if (controlNodeObjectTokenKnownPathName == null && objectTokenKnownPathName != null) {
					sb.append(".<");
					sb.append(objectTokenKnownPathName.getLast());
					sb.append(">convertToUnknown()");
				}
			}
		}
		sb.append(")");
		getInControlFlows.getBody().addToStatements(sb.toString());
		actionClass.addToImports(new OJPathName("java.util.Arrays"));
		actionClass.addToImports(TinkerBehaviorUtil.tinkerActivityEdgePathName);
		actionClass.addToOperations(getInControlFlows);
	}

	private void addGetOutFlows(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getOutControlFlows = new OJAnnotatedOperation("getOutFlows");
		TinkerGenerationUtil.addOverrideAnnotation(getOutControlFlows);

		OJPathName returnType = new OJPathName("java.util.List");
		OJPathName edgePathName;
		StringBuilder sb;
		if (oa instanceof INakedObjectNode) {
			edgePathName = TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getCopy();
			OJPathName tokenGenericPathName = OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType());
			edgePathName.addToGenerics(tokenGenericPathName);
			returnType.addToElementTypes(edgePathName);
			sb = new StringBuilder("return Arrays.");
			sb.append("<" + TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getLast() + "<");
			sb.append(tokenGenericPathName.getLast());
			sb.append(">>asList(");
		} else if (oa instanceof INakedControlNode && !((INakedControlNode) oa).getControlNodeType().isInitialNode()) {
			INakedControlNode controlNode = (INakedControlNode) oa;
			if (controlNode.getIncoming().iterator().next() instanceof INakedObjectFlow) {
				List<INakedClassifier> c = new ArrayList<INakedClassifier>();
				Exitter mutableBoolean = new Exitter(false);
				getObjectFlowPathName(controlNode, c, mutableBoolean);
				if (!mutableBoolean.shouldExit()) {
					OJPathName objectFlowKnownTokenPathName = OJUtil.classifierPathname(c.get(0));
					edgePathName = TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getCopy();
					edgePathName.addToGenerics(objectFlowKnownTokenPathName);
					returnType.addToElementTypes(edgePathName);
					sb = new StringBuilder("return Arrays.");
					sb.append("<" + TinkerBehaviorUtil.tinkerObjectFlowKnownPathName.getLast() + "<");
					sb.append(objectFlowKnownTokenPathName.getLast());
					sb.append(">>asList(");
				} else {
					edgePathName = TinkerBehaviorUtil.tinkerObjectFlowUnknownPathName.getCopy();
					returnType.addToElementTypes(edgePathName);
					sb = new StringBuilder("return Arrays.");
					sb.append("<" + TinkerBehaviorUtil.tinkerObjectFlowUnknownPathName.getLast() + ">asList(");

				}
			} else {
				edgePathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.<ControlFlow>asList(");
			}
		} else {
			edgePathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
			returnType.addToElementTypes(edgePathName);
			sb = new StringBuilder("return Arrays.<ControlFlow>asList(");
		}
		getOutControlFlows.setReturnType(returnType);

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
			String expressValue = ValueSpecificationUtil.expressValue(getBodyExpression, new NakedValueSpecificationImpl(oa.getBodyExpression()), oa.getActivity(), oa
					.getReturnPin().getType());
			if (oa.getReturnPin().getNakedMultiplicity().isMany()) {
				getBodyExpression.getBody().addToStatements("return " + expressValue);
			} else {
				getBodyExpression.getBody().addToStatements("return Arrays.asList(" + expressValue + ")");
			}
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), oa.getReturnPin(), false));
			OJPathName collectionPathName = new OJPathName("java.util.Collection");
			collectionPathName.addToElementTypes(map.javaBaseTypePath());
			getBodyExpression.setReturnType(collectionPathName);
		} else {
			getBodyExpression.getBody().addToStatements("return null");
			OJPathName collectionPathName = new OJPathName("java.util.Collection");
			collectionPathName.addToElementTypes(new OJPathName("java.lang.Object"));
			getBodyExpression.setReturnType(collectionPathName);
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
		// TODO think about many triggers here, for now kinda assuming one
		for (INakedTrigger trigger : oa.getTriggers()) {
			if (trigger.getEvent() instanceof INakedSignalEvent) {
				INakedSignalEvent signalEvent = (INakedSignalEvent) trigger.getEvent();
				INakedSignal signal = signalEvent.getSignal();
				OJIfStatement ifInstanceOf = new OJIfStatement("signal instanceof " + OJUtil.classifierPathname(signal).getLast());
				// Correlate signal attributes with output pins
				int i = 0;
				for (INakedOutputPin outputPin : oa.getOutput()) {
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(signal.getOwnedAttributes().get(i++));
					ifInstanceOf.addToThenPart(TinkerBehaviorUtil.outputPinGetterName(outputPin) + "().addOutgoingToken(new ObjectToken<" + map.javaBaseTypePath().getLast() + ">("
							+ TinkerBehaviorUtil.outputPinGetterName(outputPin) + "().getName(), ((" + OJUtil.classifierPathname(signal).getLast() + ")signal)." + map.getter()
							+ "()))");
					actionClass.addToImports(map.javaBaseTypePath());
				}
				copySignalToOutputPin.getBody().addToStatements(ifInstanceOf);
			}

		}
		actionClass.addToOperations(copySignalToOutputPin);

	}

}
