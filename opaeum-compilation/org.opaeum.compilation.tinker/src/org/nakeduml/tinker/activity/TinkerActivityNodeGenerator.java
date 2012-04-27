package org.nakeduml.tinker.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedAddStructuralFeatureValueAction;
import org.opaeum.metamodel.actions.INakedAddVariableValueAction;
import org.opaeum.metamodel.actions.INakedCreateObjectAction;
import org.opaeum.metamodel.actions.INakedOclAction;
import org.opaeum.metamodel.actions.INakedOpaqueAction;
import org.opaeum.metamodel.actions.INakedReadVariableAction;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.actions.INakedStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedVariableAction;
import org.opaeum.metamodel.actions.INakedWriteStructuralFeatureAction;
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
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.commonbehaviors.INakedMessageEvent;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedSignalEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedValueSpecificationImpl;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerImplementNodeStep.class }, after = { TinkerImplementNodeStep.class })
public class TinkerActivityNodeGenerator extends StereotypeAnnotator {

	@VisitBefore(matchSubclasses = false, match = { INakedInputPin.class })
	public void visitInputPins(INakedInputPin oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass inputPinClass = new OJAnnotatedClass(TinkerBehaviorUtil.activityNodePathName(oa).getLast());
		inputPinClass.setMyPackage(pack);

		OJPathName superClass;
		if (oa.getAction() instanceof INakedReplyAction && ((INakedReplyAction) oa.getAction()).getReturnInfo().equals(oa)) {
			if (oa.getNakedMultiplicity().isOne()) {
				superClass = TinkerBehaviorUtil.tinkerOneReturnInformationInputPinPathName.getCopy();
			} else {
				superClass = TinkerBehaviorUtil.tinkerManyReturnInformationInputPinPathName.getCopy();
			}
		} else {
			if (oa.getNakedMultiplicity().isOne()) {
				superClass = TinkerBehaviorUtil.tinkerOneInputPinPathName.getCopy();
			} else {
				superClass = TinkerBehaviorUtil.tinkerManyInputPinPathName.getCopy();
			}
		}
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

	@VisitBefore(matchSubclasses = false, match = { INakedValuePin.class })
	public void visitValuePins(INakedValuePin oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass valuePinClass = new OJAnnotatedClass(TinkerBehaviorUtil.activityNodePathName(oa).getLast());
		valuePinClass.setMyPackage(pack);
		OJPathName superClass;
		if (oa.getNakedMultiplicity().isOne()) {
			superClass = TinkerBehaviorUtil.tinkerOneValuePinPathName.getCopy();
		} else {
			superClass = TinkerBehaviorUtil.tinkerManyValuePinPathName.getCopy();
		}
		valuePinClass.setSuperclass(superClass);
		superClass.addToGenerics(OJUtil.classifierPathname(oa.getNakedBaseType()));
		valuePinClass.addToImports(OJUtil.classifierPathname(oa.getNakedBaseType()));

		addGetOutFlows(valuePinClass, oa);
		addGetInFlows(valuePinClass, oa);
		addOutControlFlowGetters(valuePinClass, oa);
		addInControlFlowGetters(valuePinClass, oa);
		addGetContextObject(valuePinClass, oa.getActivity().getContext());
		implementGetActivity(valuePinClass, oa);

		addContextObjectField(valuePinClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(valuePinClass, oa.getActivity().getContext());
		addGetContextObject(valuePinClass, oa.getActivity().getContext());
		addGetValue(valuePinClass, oa);
		implementUpperBound(valuePinClass, oa);
		implementMultiplicity(valuePinClass, oa);
		implementGetAction(valuePinClass, oa);
		super.createTextPath(valuePinClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = false, match = { INakedOutputPin.class })
	public void visitOutputPins(INakedOutputPin oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass outputPinClass = new OJAnnotatedClass(TinkerBehaviorUtil.activityNodePathName(oa).getLast());
		outputPinClass.setMyPackage(pack);

		boolean implementGetReturnInformationInputPin = false;
		OJPathName superClass;
		if (oa.getAction() instanceof INakedAcceptCallAction && ((INakedAcceptCallAction) oa.getAction()).getReturnInfo().equals(oa)) {
			if (oa.getNakedMultiplicity().isOne()) {
				superClass = TinkerBehaviorUtil.tinkerOneReturnInformationOutputPinPathName.getCopy();
			} else {
				superClass = TinkerBehaviorUtil.tinkerManyReturnInformationOutputPinPathName.getCopy();
			}
			implementGetReturnInformationInputPin = true;
		} else {
			if (oa.getNakedMultiplicity().isOne()) {
				superClass = TinkerBehaviorUtil.tinkerOneOutputPinPathName.getCopy();
			} else {
				superClass = TinkerBehaviorUtil.tinkerManyOutputPinPathName.getCopy();
			}
		}
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

		removeOutputPinfromActivityClass(oa);

		if (implementGetReturnInformationInputPin) {
			implementGetReturnInformationInputPin(outputPinClass, oa);
		}
		super.createTextPath(outputPinClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedParameterNode.class })
	public void visitActivityParameterNode(INakedParameterNode oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass activityParameterNodeClass = new OJAnnotatedClass(TinkerBehaviorUtil.activityNodePathName(oa).getLast());
		activityParameterNodeClass.setMyPackage(pack);
		if ((oa.getParameter().getDirection() == ParameterDirectionKind.IN) || (oa.getParameter().getDirection() == ParameterDirectionKind.INOUT) && oa.getIncoming().isEmpty()) {
			if (oa.getNakedMultiplicity().isOne()) {
				activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerOneInActivityParameterNodePathName.getCopy());
			} else {
				activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerManyInActivityParameterNodePathName.getCopy());
			}
			addGetOutFlows(activityParameterNodeClass, oa);
			addOutControlFlowGetters(activityParameterNodeClass, oa);
		} else if ((oa.getParameter().getDirection() == ParameterDirectionKind.OUT) || (oa.getParameter().getDirection() == ParameterDirectionKind.INOUT)
				&& oa.getOutgoing().isEmpty()) {
			if (oa.getNakedMultiplicity().isOne()) {
				activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerOneOutActivityParameterNodePathName.getCopy());
			} else {
				activityParameterNodeClass.setSuperclass(TinkerBehaviorUtil.tinkerManyOutActivityParameterNodePathName.getCopy());
			}
			addGetInFlows(activityParameterNodeClass, oa);
			addInControlFlowGetters(activityParameterNodeClass, oa);
		}
		OJPathName superClass = activityParameterNodeClass.getSuperclass();
		superClass.addToGenerics(OJUtil.classifierPathname(oa.getParameter().getNakedBaseType()));
		activityParameterNodeClass.addToImports(OJUtil.classifierPathname(oa.getParameter().getNakedBaseType()));
		activityParameterNodeClass.setSuperclass(superClass);

		addConstructorWithVertex(activityParameterNodeClass, oa.getActivity().getContext());
		addGetContextObject(activityParameterNodeClass, oa.getActivity().getContext());
		addInitVertexToDefaultConstructor(activityParameterNodeClass, oa);
		addContextObjectField(activityParameterNodeClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(activityParameterNodeClass, oa.getActivity().getContext());
		implementUpperBound(activityParameterNodeClass, oa);
		implementGetActivity(activityParameterNodeClass, oa);
		super.createTextPath(activityParameterNodeClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedAddStructuralFeatureValueAction.class })
	public void visitAddStructuralFeatureValueAction(INakedAddStructuralFeatureValueAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAddStructuralFeatureValueAction.getCopy());
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		implementGenerics(actionClass, oa);
		implementStructuralFeatureAction(actionClass, oa);
		implementWriteStructuralFeatureAction(actionClass, oa);
		implementAddStructuralFeatureValueAction(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedCreateObjectAction.class })
	public void visitCreateObjectAction(INakedCreateObjectAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerCreateObjectAction.getCopy());
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		implementGenerics(actionClass, oa);
		implementCreateObject(actionClass, oa);
		implementGetResult(actionClass, oa.getResult());
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedReadVariableAction.class })
	public void visitReadVariableAction(INakedReadVariableAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		if (oa.getVariable().getNakedMultiplicity().isOne()) {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOneReadVariableAction.getCopy());
		} else {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerManyReadVariableAction.getCopy());
		}
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());

		implementReadVariable(actionClass, oa);
		implementGenerics(actionClass, oa);

		implementGetResult(actionClass, oa.getResult());
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedAddVariableValueAction.class })
	public void visitAddVariableValueAction(INakedAddVariableValueAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		if (oa.getVariable().getNakedMultiplicity().isOne()) {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOneAddVariableValueAction.getCopy());
		} else {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerManyAddVariableValueAction.getCopy());
		}
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		implementGenerics(actionClass, oa);
		implementWriteVariable(actionClass, oa);
		implementGetValue(actionClass, oa.getValue());
		implementReadVariable(actionClass, oa);
		// implementGetResult(actionClass, oa.getResult());
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedOpaqueAction.class })
	public void visitOpaqueAction(INakedOclAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		// Calc one or many
		if (oa.getReturnPin()!=null && oa.getReturnPin().getNakedMultiplicity().isMany()) {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerManyOpaqueActionPathName.getCopy());
		} else if (oa.getReturnPin()!=null && oa.getReturnPin().getNakedMultiplicity().isOne()) {
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOneOpaqueActionPathName.getCopy());
		} else {
			//Nada
			actionClass.setSuperclass(TinkerBehaviorUtil.tinkerOpaqueActionPathName.getCopy());
		}
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		if (oa.getReturnPin()!=null) {
			addGetBodyExpression(actionClass, oa);
		}
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedReplyAction.class })
	public void visitReplyAction(INakedReplyAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerReplyActionPathName.getCopy());
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addActionOperations(actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		// addBlockingQueue(actionClass, oa);
//		if (!oa.getReplyValues().isEmpty()) {
//			addGetReply(actionClass, oa);
//		} else {
//			System.out.println();
//		}
		putReplyInBlockingQueue(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = false, match = { INakedAcceptEventAction.class })
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
		addCopyEventToOutputPin(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedAcceptCallAction.class })
	public void visitAcceptCallAction(INakedAcceptCallAction oa) {
		OJPathName path = OJUtil.packagePathname(oa.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		OJAnnotatedClass actionClass = new OJAnnotatedClass(NameConverter.capitalize(oa.getName()));
		actionClass.setMyPackage(pack);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerAcceptCallAction);
		addActionOperations(actionClass, oa);
		OJConstructor constructor = actionClass.findConstructor(TinkerGenerationUtil.vertexPathName, OJUtil.classifierPathname(oa.getActivity().getContext()));
		addTriggersInConstructor(constructor, actionClass, oa);
		addInitVertexToDefaultConstructor(actionClass, oa);
		addContextObjectField(actionClass, oa.getActivity().getContext());
		addContextObjectToDefaultConstructor(actionClass, oa.getActivity().getContext());
		OJConstructor constructor1 = actionClass.findConstructor(OJUtil.classifierPathname(oa.getActivity().getContext()));
		addTriggersInConstructor(constructor1, actionClass, oa);
		addCopyEventToOutputPin(actionClass, oa);
		addGetReturnInformationOutputPin(actionClass, oa);
		addGetReplyAction(actionClass, oa);
		removeAcceptCallActionFromActivityClass(actionClass, oa);
		super.createTextPath(actionClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}

	public OpaeumLibrary getLibrary() {
		return workspace.getOpaeumLibrary();
	}

	private void removeAcceptCallActionFromActivityClass(OJAnnotatedClass actionClass, INakedAcceptCallAction oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa, getLibrary());
		OJAnnotatedClass activityClass = findJavaClass(oa.getActivity());
		OJField fieldToRemove = null;
		OJOperation internalAdderToRemove = null;
		OJOperation internalRemoverToRemove = null;
		OJOperation getter = null;
		OJOperation setter = null;
		for (OJField field : activityClass.getFields()) {
			if (field.getName().equals(map.fieldname())) {
				fieldToRemove = field;
				internalAdderToRemove = activityClass.findOperation(map.internalAdder(), Arrays.asList(map.javaBaseTypePath()));
				internalRemoverToRemove = activityClass.findOperation(map.internalRemover(), Arrays.asList(map.javaBaseTypePath()));
				getter = activityClass.findOperation(map.getter(), Collections.emptyList());
				setter = activityClass.findOperation(map.setter(), Arrays.asList(map.javaBaseTypePath()));
				break;
			}
		}
		activityClass.removeFromFields(fieldToRemove);
		activityClass.removeFromOperations(internalAdderToRemove);
		activityClass.removeFromOperations(internalRemoverToRemove);
		activityClass.removeFromOperations(getter);
		activityClass.removeFromOperations(setter);
	}

	private void addGetReplyAction(OJAnnotatedClass actionClass, INakedAcceptCallAction oa) {
		OJAnnotatedOperation getReplyAction = new OJAnnotatedOperation("getReplyAction", TinkerBehaviorUtil.activityNodePathName(oa.getReplyAction()));
		TinkerGenerationUtil.addOverrideAnnotation(getReplyAction);
		getReplyAction.getBody().addToStatements("return getReturnInformationOutputPin().getReturnInformationInputPin().getAction()");
		actionClass.addToOperations(getReplyAction);
	}

	private void addGetReturnInformationOutputPin(OJAnnotatedClass actionClass, INakedAcceptCallAction oa) {
		OJAnnotatedOperation getReturnInformationOutputPin = new OJAnnotatedOperation("getReturnInformationOutputPin");
		TinkerGenerationUtil.addOverrideAnnotation(getReturnInformationOutputPin);
		for (INakedOutputPin outputPin : oa.getOutput()) {
			if (outputPin.equals(((INakedAcceptCallAction) oa).getReturnInfo())) {
				getReturnInformationOutputPin.setReturnType(TinkerBehaviorUtil.activityNodePathName(outputPin));
				getReturnInformationOutputPin.getBody().addToStatements("return " + TinkerBehaviorUtil.outputPinGetterName(outputPin) + "()");
				break;
			}
		}
		actionClass.addToOperations(getReturnInformationOutputPin);
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
		if (tokenType.equals(TinkerBehaviorUtil.tinkerObjectTokenPathName.getLast())) {
			controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerObjectTokenPathName.getCopy());
		} else {
			controlNodeClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName.getCopy());
		}
		addContextObjectField(controlNodeClass, controlNode.getActivity().getContext());
		addContextObjectToDefaultConstructor(controlNodeClass, controlNode.getActivity().getContext());
		addGetContextObject(controlNodeClass, controlNode.getActivity().getContext());
		implementGetActivity(controlNodeClass, controlNode);
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
			if (trigger.getEvent() instanceof INakedMessageEvent) {
				INakedMessageEvent signalEvent = (INakedMessageEvent) trigger.getEvent();
				constructor.getBody().addToStatements("addToTriggers(\"" + trigger.getName() + "\",\"" + signalEvent.getName() + "\")");
				// actionClass.addToImports(OJUtil.classifierPathname(signalEvent.getSignal()));
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
		OJAnnotatedClass controlFlowEdge = new OJAnnotatedClass(TinkerBehaviorUtil.activityEdgePathName(edge).getLast());
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
			INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
			INakedClassifier objectFlowClassifier = objectFlow.getOriginatingObjectNodeClassifier();

			if (objectFlowClassifier == null) {
				addEvaluateGuardCondition(controlFlowEdge, (INakedObjectFlow) edge, new OJPathName("java.lang.Object"));
				if (objectFlow.getTinkerOriginatingMultiplicity().isOne()) {
					controlFlowEdge.setSuperclass(TinkerBehaviorUtil.tinkerOneObjectFlowUnknownPathName.getCopy());
				} else {
					controlFlowEdge.setSuperclass(TinkerBehaviorUtil.tinkerManyObjectFlowUnknownPathName.getCopy());
				}
			} else {
				OJPathName superClass;
				if (objectFlow.getTinkerOriginatingMultiplicity().isOne()) {
					superClass = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
				} else {
					superClass = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
				}
				OJPathName objectTokenPathName = OJUtil.classifierPathname(objectFlowClassifier);
				superClass.addToGenerics(objectTokenPathName);
				controlFlowEdge.setSuperclass(superClass);
				addEvaluateGuardCondition(controlFlowEdge, (INakedObjectFlow) edge, objectTokenPathName);
				controlFlowEdge.addToImports(objectTokenPathName);
			}
		} else {
			controlFlowEdge.setSuperclass(TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy());
			addEvaluateControlFlowGuardCondition(controlFlowEdge, edge);
		}

		super.createTextPath(controlFlowEdge, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);

		addGetWeight(controlFlowEdge, edge);
		addTarget(controlFlowEdge, edge);
		addSource(controlFlowEdge, edge);
		addName(controlFlowEdge, edge);

		OJAnnotatedOperation getContextObject = new OJAnnotatedOperation("getContextObject");
		getContextObject.setReturnType(OJUtil.classifierPathname(edge.getActivity().getContext()));
		getContextObject.getBody().addToStatements("return this.contextObject");
		controlFlowEdge.addToOperations(getContextObject);

	}

	private void addSource(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {

		ConcreteEmulatedClassifier sourceClassifier = new ConcreteEmulatedClassifier(edge.getSource().getNameSpace(), edge.getSource());
		NakedStructuralFeatureMap sourceMap = new NakedStructuralFeatureMap(new ActivityNodeBridge(sourceClassifier, edge.getSource()));
		OJAnnotatedField sourceField = new OJAnnotatedField(sourceMap.fieldname(), TinkerBehaviorUtil.activityNodePathName(edge.getSource()));
		controlFlowEdge.addToFields(sourceField);

		OJAnnotatedOperation getSourceAction = new OJAnnotatedOperation(TinkerBehaviorUtil.actionSourceGetter(edge));
		OJPathName actionPathName = TinkerBehaviorUtil.activityNodePathName(edge.getSource());
		getSourceAction.setReturnType(actionPathName);

		OJIfStatement ifSourceNull = new OJIfStatement("this." + sourceMap.fieldname() + " == null");
		ifSourceNull.addToThenPart("this." + sourceMap.fieldname() + " = new " + actionPathName.getLast() + "(this.edge.getOutVertex(), this.contextObject)");
		getSourceAction.getBody().addToStatements(ifSourceNull);
		getSourceAction.getBody().addToStatements("return this." + sourceMap.fieldname());

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
		ConcreteEmulatedClassifier targetClassifier = new ConcreteEmulatedClassifier(edge.getTarget().getNameSpace(), edge.getTarget());
		NakedStructuralFeatureMap targetMap = new NakedStructuralFeatureMap(new ActivityNodeBridge(targetClassifier, edge.getTarget()));
		OJAnnotatedField targetField = new OJAnnotatedField(targetMap.fieldname(), TinkerBehaviorUtil.activityNodePathName(edge.getTarget()));
		controlFlowEdge.addToFields(targetField);

		OJAnnotatedOperation getTargetAction = new OJAnnotatedOperation(TinkerBehaviorUtil.actionTargetGetter(edge));
		OJPathName actionPathName = TinkerBehaviorUtil.activityNodePathName(edge.getTarget());
		getTargetAction.setReturnType(actionPathName);

		OJIfStatement ifTargetNull = new OJIfStatement("this." + targetMap.fieldname() + " == null");
		ifTargetNull.addToThenPart("this." + targetMap.fieldname() + " = new " + actionPathName.getLast() + "(this.edge.getInVertex(), this.contextObject)");
		getTargetAction.getBody().addToStatements(ifTargetNull);
		getTargetAction.getBody().addToStatements("return this." + targetMap.fieldname());

		controlFlowEdge.addToOperations(getTargetAction);

		OJAnnotatedOperation getTarget = new OJAnnotatedOperation("getTarget");
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
		OJPathName returnType = TinkerBehaviorUtil.activityNodePathName(edge.getTarget());
		getTarget.setReturnType(returnType);
		getTarget.getBody().addToStatements("return " + TinkerBehaviorUtil.actionTargetGetter(edge) + "()");
		getTarget.setVisibility(OJVisibilityKind.PROTECTED);
		getTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		controlFlowEdge.addToOperations(getTarget);
	}

	private void addEvaluateGuardCondition(OJAnnotatedClass controlFlowEdge, INakedObjectFlow edge, OJPathName tokenValuePathName) {
		OJAnnotatedOperation evaluateGuardConditions = new OJAnnotatedOperation("evaluateGuardConditions");
		TinkerGenerationUtil.addOverrideAnnotation(evaluateGuardConditions);
		evaluateGuardConditions.setReturnType(new OJPathName("boolean"));
		controlFlowEdge.addToOperations(evaluateGuardConditions);
		String guardEvaluation;
		if (edge.getTinkerOriginatingMultiplicity().isOne()) {
			evaluateGuardConditions.addParam(NameConverter.decapitalize(edge.getName()), tokenValuePathName);
		} else {
			OJPathName collection = new OJPathName("java.util.Collection");
			controlFlowEdge.addToImports(collection);
			collection.addToGenerics(tokenValuePathName);
			evaluateGuardConditions.addParam(NameConverter.decapitalize(edge.getName()), collection);
		}
		guardEvaluation = ValueSpecificationUtil.expressValue(evaluateGuardConditions, edge.getGuard(), edge.getActivity(), new StdlibPrimitiveType("Boolean"));
		evaluateGuardConditions.getBody().addToStatements("return " + guardEvaluation);
		evaluateGuardConditions.setVisibility(OJVisibilityKind.PROTECTED);
		evaluateGuardConditions.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
	}

	private void addEvaluateControlFlowGuardCondition(OJAnnotatedClass controlFlowEdge, INakedActivityEdge edge) {
		OJAnnotatedOperation evaluateGuardConditions = new OJAnnotatedOperation("evaluateGuardConditions");
		TinkerGenerationUtil.addOverrideAnnotation(evaluateGuardConditions);
		evaluateGuardConditions.setReturnType(new OJPathName("boolean"));
		controlFlowEdge.addToOperations(evaluateGuardConditions);
		String guardEvaluation;
		guardEvaluation = ValueSpecificationUtil.expressValue(controlFlowEdge, edge.getGuard(), new StdlibPrimitiveType("Boolean"), false);
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
			if (oa instanceof INakedOpaqueAction || oa instanceof INakedReplyAction) {
				addAddToInputPinVariable(actionClass, (INakedAction) oa);
			}
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
			if ((oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
				continue;
			}
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
			AttributeImplementor attributeImplementor = new AttributeImplementor();
			attributeImplementor.setJavaModel(this.javaModel);
			attributeImplementor.implementAttributeFully(new TinkerEmulatedAction(oa.getNameSpace(), oa), map);
		}
	}

	private void addAddToInputPinVariable(OJClass actionClass, INakedAction oa) {
		OJAnnotatedOperation addToInputPinVariable = new OJAnnotatedOperation("addToInputPinVariable");
		TinkerGenerationUtil.addOverrideAnnotation(addToInputPinVariable);

		if (oa instanceof INakedOclAction && ((INakedOclAction) oa).getReturnPin()!=null) {
			addToInputPinVariable.addParam("inputPin", TinkerBehaviorUtil.tinkerIInputPinPathName.getCopy().addToGenerics(new OJPathName("?")).addToGenerics(new OJPathName("?")));
			addToInputPinVariable.addParam("elements", new OJPathName("Collection<?>"));
		} else {
			addToInputPinVariable.addParam("inputPin", TinkerBehaviorUtil.tinkerIInputPinPathName.getCopy());
			addToInputPinVariable.addParam("elements", new OJPathName("Collection"));
		}
		actionClass.addToImports(new OJPathName("java.util.Collection"));
		for (INakedInputPin inputPin : oa.getInput()) {
			if ((oa instanceof INakedSendSignalAction && ((INakedSendSignalAction) oa).getTarget() == inputPin)) {
				continue;
			}

			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
			OJIfStatement ifInputPinIsType = new OJIfStatement("inputPin instanceof " + TinkerBehaviorUtil.activityNodePathName(inputPin).getLast());
			addToInputPinVariable.getBody().addToStatements(ifInputPinIsType);
			if (map.isOne()) {
				ifInputPinIsType.addToThenPart(map.setter() + "(("+map.javaBaseTypePath().getLast()+")(!elements.isEmpty() ? elements.iterator().next() : null))");
			} else {
				ifInputPinIsType.addToThenPart(map.allAdder() + "(new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseTypePath().getLast() + ">((Collection<"+map.javaBaseTypePath().getLast()+">)elements))");
			}
		}

		actionClass.addToOperations(addToInputPinVariable);
	}

	private void addReturnPinGenericType(OJClass actionClass, INakedOclAction oa) {
		OJPathName superPathName = actionClass.getSuperclass().getCopy();
		superPathName.addToGenerics(OJUtil.classifierPathname(oa.getReturnPin().getNakedBaseType()));
		// OJPathName objectToken = null;
		// if (oa.getReturnPin().getNakedMultiplicity().isOne()) {
		// objectToken = TinkerBehaviorUtil.tinkerSingleObjectToken.getCopy();
		// } else {
		// objectToken =
		// TinkerBehaviorUtil.tinkerCollectionObjectToken.getCopy();
		// }
		// actionClass.addToImports(objectToken);
		// objectToken.addToGenerics(OJUtil.classifierPathname(oa.getReturnPin().getNakedBaseType()));
		// superPathName.addToGenerics(objectToken);
		actionClass.setSuperclass(superPathName);
	}

	private void addOutputPinGetters(OJClass actionClass, INakedAction a) {

		StringBuilder sb = new StringBuilder("return Arrays.asList(");
		Collection<INakedOutputPin> outputPins = a.getOutput();
		boolean hasOutputPins = false;
		for (INakedOutputPin outputPin : outputPins) {

			ConcreteEmulatedClassifier pinClassifier = new ConcreteEmulatedClassifier(outputPin.getNameSpace(), outputPin);
			NakedStructuralFeatureMap pinMap = new NakedStructuralFeatureMap(new PinBridge(pinClassifier, outputPin));

			if (a instanceof INakedSendSignalAction && ((INakedSendSignalAction) a).getTarget() == outputPin) {
				continue;
			}
			hasOutputPins = true;

			OJAnnotatedField pinField = new OJAnnotatedField(pinMap.fieldname(), TinkerBehaviorUtil.activityNodePathName(outputPin));
			actionClass.addToFields(pinField);

			OJAnnotatedOperation getOutputPin = new OJAnnotatedOperation(TinkerBehaviorUtil.outputPinGetterName(outputPin));
			sb.append(getOutputPin.getName() + "()");
			sb.append(",");
			getOutputPin.setReturnType(TinkerBehaviorUtil.activityNodePathName(outputPin));

			OJIfStatement ifPinNull = new OJIfStatement("this." + pinMap.fieldname() + " == null");
			ifPinNull.addToThenPart("this." + pinMap.fieldname() + " = new " + TinkerBehaviorUtil.activityNodePathName(outputPin).getLast() + "(this.vertex.getOutEdges(\""
					+ TinkerBehaviorUtil.pinActionEdgeName(outputPin) + "\").iterator().next().getInVertex(), this.contextObject)");

			getOutputPin.getBody().addToStatements(ifPinNull);
			getOutputPin.getBody().addToStatements("return this." + pinMap.fieldname());
			actionClass.addToOperations(getOutputPin);
		}

		if (a instanceof INakedOclAction) {
			OJAnnotatedOperation getResultPin = new OJAnnotatedOperation("getResultPin");
			actionClass.addToOperations(getResultPin);
			INakedOutputPin returnPin = ((INakedOclAction) a).getReturnPin();
			if (returnPin == null) {
				getResultPin.setReturnType(TinkerBehaviorUtil.tinkerOutputPinPathName.getCopy());
				getResultPin.getReturnType().addToGenerics(new OJPathName("?, ?"));
				getResultPin.getBody().addToStatements("return null");
			} else if (returnPin.getNakedMultiplicity().isOne()) {
				getResultPin.setReturnType(TinkerBehaviorUtil.tinkerOneOutputPinPathName.getCopy());
				actionClass.addToImports(TinkerBehaviorUtil.tinkerOneOutputPinPathName.getCopy());
			} else {
				getResultPin.setReturnType(TinkerBehaviorUtil.tinkerManyOutputPinPathName.getCopy());
				actionClass.addToImports(TinkerBehaviorUtil.tinkerManyOutputPinPathName.getCopy());
			}
			if (returnPin != null) {
				getResultPin.getReturnType().addToGenerics(OJUtil.classifierPathname(returnPin.getNakedBaseType()));
				getResultPin.getBody().addToStatements("return " + TinkerBehaviorUtil.outputPinGetterName(returnPin) + "()");
			}
		} else {

			OJAnnotatedOperation getOutputPins = new OJAnnotatedOperation("getOutputPins");
			TinkerGenerationUtil.addOverrideAnnotation(getOutputPins);
			OJPathName returnType = new OJPathName("java.util.List");
			returnType.addToElementTypes(new OJPathName("? extends OutputPin<?, ?>"));
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

			ConcreteEmulatedClassifier pinClassifier = new ConcreteEmulatedClassifier(inputPin.getNameSpace(), inputPin);
			NakedStructuralFeatureMap pinMap = new NakedStructuralFeatureMap(new PinBridge(pinClassifier, inputPin));

			if ((a instanceof INakedSendSignalAction && ((INakedSendSignalAction) a).getTarget() == inputPin)) {
				continue;
			}
			hasInputPins = true;

			OJAnnotatedField inputPinField = new OJAnnotatedField(pinMap.fieldname(), TinkerBehaviorUtil.activityNodePathName(inputPin));
			actionClass.addToFields(inputPinField);

			OJAnnotatedOperation getInputPin = new OJAnnotatedOperation(TinkerBehaviorUtil.inputPinGetter(inputPin));
			sb.append(getInputPin.getName() + "()");
			sb.append(",");
			getInputPin.setReturnType(TinkerBehaviorUtil.activityNodePathName(inputPin));

			OJIfStatement ifPinNull = new OJIfStatement("this." + pinMap.fieldname() + " == null");
			if (inputPin instanceof INakedValuePin) {
				ifPinNull.addToThenPart("this." + pinMap.fieldname() + " = new " + TinkerBehaviorUtil.activityNodePathName(inputPin).getLast() + "(this.contextObject)");

			} else {
				ifPinNull.addToThenPart("this." + pinMap.fieldname() + " = new " + TinkerBehaviorUtil.activityNodePathName(inputPin).getLast() + "(this.vertex.getOutEdges(\""
						+ TinkerBehaviorUtil.pinActionEdgeName(inputPin) + "\").iterator().next().getInVertex(), this.contextObject)");
			}
			getInputPin.getBody().addToStatements(ifPinNull);
			getInputPin.getBody().addToStatements("return this." + pinMap.fieldname());
			actionClass.addToOperations(getInputPin);
		}
		OJAnnotatedOperation getInputPins = new OJAnnotatedOperation("getInputPins");
		TinkerGenerationUtil.addOverrideAnnotation(getInputPins);
		OJPathName returnType = new OJPathName("java.util.List");
		OJPathName inputPinPath = null;

//		if (a instanceof INakedOclAction && ((INakedOclAction) a).getReturnPin() != null && ((INakedOclAction) a).getReturnPin().getNakedMultiplicity().isOne()) {
//			inputPinPath = TinkerBehaviorUtil.tinkerOneInputPinPathName.getCopy();
//			inputPinPath.addToGenerics(OJUtil.classifierPathname(((INakedOclAction) a).getReturnPin().getNakedBaseType()));
//			returnType.addToElementTypes(new OJPathName("? extends " + inputPinPath.getLast()));
//			actionClass.addToImports(TinkerBehaviorUtil.tinkerOneInputPinPathName);
//		} else if (a instanceof INakedOclAction && ((INakedOclAction) a).getReturnPin() != null && ((INakedOclAction) a).getReturnPin().getNakedMultiplicity().isMany()) {
//			inputPinPath = TinkerBehaviorUtil.tinkerManyInputPinPathName.getCopy();
//			inputPinPath.addToGenerics(OJUtil.classifierPathname(((INakedOclAction) a).getReturnPin().getNakedBaseType()));
//			returnType.addToElementTypes(new OJPathName("? extends " + inputPinPath.getLast()));
//			actionClass.addToImports(TinkerBehaviorUtil.tinkerManyInputPinPathName);
//		} else {
			inputPinPath = TinkerBehaviorUtil.tinkerInputPinPathName.getCopy();
			returnType.addToElementTypes(new OJPathName("? extends InputPin<?, ?>"));
//		}
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
		implementGetActivity(actionClass, oa);
	}

	private void implementGetActivity(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getActivity = new OJAnnotatedOperation("getActivity");
		TinkerGenerationUtil.addOverrideAnnotation(getActivity);
		NakedClassifierMap map = OJUtil.buildClassifierMap(oa.getActivity());
		getActivity.setReturnType(map.javaTypePath());
		actionClass.addToImports(map.javaTypePath());
		getActivity.getBody().addToStatements(
				"return new " + map.javaTypePath().getLast() + "(this.vertex.getInEdges(\"" + NameConverter.decapitalize(TinkerBehaviorUtil.activityNodePathName(oa).getLast())
						+ "Edge\").iterator().next().getOutVertex())");
		actionClass.addToOperations(getActivity);
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
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(edge.getSource().getNameSpace(), edge.getSource());
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new EdgeBridge(concreteEmulatedClassifier, edge));
		OJAnnotatedField flowField = new OJAnnotatedField(map.fieldname(), map.javaBaseTypePath());
		actionClass.addToFields(flowField);
		OJAnnotatedOperation flowGetter = new OJAnnotatedOperation(map.getter());
		OJPathName edgePathname = map.javaBaseTypePath();
		actionClass.addToImports(edgePathname);
		flowGetter.setReturnType(edgePathname);
		OJIfStatement ifNotNull = new OJIfStatement("this." + map.fieldname() + " == null");
		ifNotNull.addToThenPart("this." + map.fieldname() + " = new " + edgePathname.getLast() + "(vertex.getInEdges(\"" + edge.getName()
				+ "\").iterator().next(), this.contextObject)");
		flowGetter.getBody().addToStatements(ifNotNull);
		flowGetter.getBody().addToStatements("return this." + map.fieldname());
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
		ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(edge.getSource().getNameSpace(), edge.getSource());
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new EdgeBridge(concreteEmulatedClassifier, edge));

		OJAnnotatedOperation flowGetter = new OJAnnotatedOperation(map.getter());
		OJPathName edgePathname = map.javaBaseTypePath();
		actionClass.addToImports(edgePathname);
		flowGetter.setReturnType(edgePathname);

		OJAnnotatedField field = new OJAnnotatedField(map.fieldname(), map.javaBaseTypePath());
		actionClass.addToFields(field);

		OJIfStatement ifNull = new OJIfStatement("this." + map.fieldname() + " == null");
		ifNull.addToThenPart("this." + map.fieldname() + " = new " + edgePathname.getLast() + "(vertex.getOutEdges(\"" + edge.getName()
				+ "\").iterator().next(), this.contextObject)");
		flowGetter.getBody().addToStatements(ifNull);
		flowGetter.getBody().addToStatements("return this." + map.fieldname());
		actionClass.addToOperations(flowGetter);

		// OJAnnotatedOperation flowGetter = new
		// OJAnnotatedOperation(TinkerBehaviorUtil.edgeGetter(edge));
		// OJPathName edgePathname = TinkerBehaviorUtil.edgePathname(edge);
		// actionClass.addToImports(edgePathname);
		// flowGetter.setReturnType(edgePathname);
		// flowGetter.getBody().addToStatements("return new " +
		// edgePathname.getLast() + "(vertex.getOutEdges(\"" + edge.getName() +
		// "\").iterator().next(), this.contextObject)");
		// actionClass.addToOperations(flowGetter);
	}

	private void addGetInFlows(OJClass actionClass, INakedActivityNode oa) {
		OJAnnotatedOperation getInControlFlows = new OJAnnotatedOperation("getInFlows");
		TinkerGenerationUtil.addOverrideAnnotation(getInControlFlows);
		OJPathName returnType = new OJPathName("java.util.List");
		OJPathName returnPathName;
		OJPathName genericReturnPathName = null;
		if (oa instanceof INakedControlNode
				&& (((INakedControlNode) oa).getControlNodeType() == ControlNodeType.FLOW_FINAL_NODE || ((INakedControlNode) oa).getControlNodeType() == ControlNodeType.ACTIVITY_FINAL_NODE)) {
			returnPathName = new OJPathName(TinkerBehaviorUtil.tinkerActivityEdgePathName.getCopy().getLast());
			genericReturnPathName = new OJPathName("?");
		} else {
			if (oa instanceof INakedObjectNode) {
				if (((INakedObjectNode) oa).getNakedMultiplicity().isOne()) {
					returnPathName = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
				} else {
					returnPathName = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
				}
				genericReturnPathName = OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType());
			} else if (oa instanceof INakedControlNode) {
				INakedControlNode controlNode = (INakedControlNode) oa;
				INakedClassifier nodeTokenClassifier = controlNode.getOriginatingObjectNodeClassifier();
				if (nodeTokenClassifier == null && !controlNode.hasIncomingObjectFlow()) {
					returnPathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
				} else if (nodeTokenClassifier == null && controlNode.hasIncomingObjectFlow() && controlNode.hasIncomingControlFlow()) {
					returnPathName = TinkerBehaviorUtil.tinkerActivityEdgePathNameWithToken.getCopy();
					actionClass.addToImports(TinkerBehaviorUtil.tinkerTokenPathName.getCopy());
				} else if (nodeTokenClassifier == null && controlNode.hasIncomingObjectFlow() && !controlNode.hasIncomingControlFlow()) {
					if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
						returnPathName = TinkerBehaviorUtil.tinkerOneObjectFlowUnknownPathName.getCopy();
					} else {
						returnPathName = TinkerBehaviorUtil.tinkerManyObjectFlowUnknownPathName.getCopy();
					}
				} else {
					genericReturnPathName = OJUtil.classifierPathname(nodeTokenClassifier);
					if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
						returnPathName = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
					} else {
						returnPathName = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
					}
				}
			} else {
				returnPathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
			}
		}
		returnType.addToElementTypes(returnPathName);
		getInControlFlows.setReturnType(returnType);

		OJPathName returnPathNameTmp = returnPathName.getCopy();
		StringBuilder sb = new StringBuilder();
		sb.append("return Arrays.<");
		if (genericReturnPathName != null) {
			returnPathName.addToGenerics(genericReturnPathName);
		}
		sb.append(returnPathName.getLast());
		sb.append(">asList(");
		boolean first = true;
		for (INakedActivityEdge edge : oa.getIncoming()) {

			if (!first) {
				sb.append(", ");
			}
			first = false;
			sb.append(TinkerBehaviorUtil.edgeGetter(edge));
			sb.append("()");
			if (oa instanceof INakedObjectNode) {
				INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
				INakedClassifier objectTokenClassifier = objectFlow.getOriginatingObjectNodeClassifier();
				if (objectTokenClassifier == null) {
					sb.append(".<");
					sb.append(genericReturnPathName.getLast());
					sb.append(">convertToKnownObjectFlow()");
				}
			} else if (oa instanceof INakedControlNode) {
				if (edge instanceof INakedObjectFlow) {
					INakedObjectFlow objectFlow = (INakedObjectFlow) edge;
					INakedClassifier objectTokenClassifier = objectFlow.getOriginatingObjectNodeClassifier();
					if (objectTokenClassifier == null) {
						// This flow is unknown, check if the activityNode is
						// known,
						// if so convert it
						if (returnPathNameTmp.equals(TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName)
								|| returnPathNameTmp.equals(TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName)) {
							sb.append(".<");
							sb.append(genericReturnPathName.getLast());
							sb.append(">convertToUnknownObjectFlow()");
						}
					} else {
						// This flow is known, check if the activityNode is
						// unknown,
						// if so convert it
						if (returnPathNameTmp.equals(TinkerBehaviorUtil.tinkerOneObjectFlowUnknownPathName)
								|| returnPathNameTmp.equals(TinkerBehaviorUtil.tinkerManyObjectFlowUnknownPathName)) {
							sb.append(".convertToUnknownObjectFlow()");
						}
					}
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
			if (((INakedObjectNode) oa).getNakedMultiplicity().isOne()) {
				edgePathName = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
			} else {
				edgePathName = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
			}
			OJPathName tokenGenericPathName = OJUtil.classifierPathname(((INakedObjectNode) oa).getNakedBaseType());
			edgePathName.addToGenerics(tokenGenericPathName);
			returnType.addToElementTypes(edgePathName);
			sb = new StringBuilder("return Arrays.<");
			sb.append(edgePathName.getLast());
			sb.append(">asList(");
		} else if (oa instanceof INakedControlNode && !((INakedControlNode) oa).getControlNodeType().isInitialNode()) {
			INakedControlNode controlNode = (INakedControlNode) oa;
			INakedClassifier objectFlowClassifier = controlNode.getOriginatingObjectNodeClassifier();
			if (objectFlowClassifier == null && controlNode.hasIncomingObjectFlow()) {
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					edgePathName = TinkerBehaviorUtil.tinkerOneObjectFlowUnknownPathName.getCopy();
				} else {
					edgePathName = TinkerBehaviorUtil.tinkerManyObjectFlowUnknownPathName.getCopy();
				}
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.");
				sb.append("<" + edgePathName.getLast() + ">asList(");
			} else if (objectFlowClassifier == null && !controlNode.hasIncomingObjectFlow()) {
				edgePathName = TinkerBehaviorUtil.tinkerControlFlowPathName.getCopy();
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.<ControlFlow>asList(");
			} else {
				OJPathName objectFlowKnownTokenPathName = OJUtil.classifierPathname(objectFlowClassifier);
				if (controlNode.getTinkerOriginatingMultiplicity().isOne()) {
					edgePathName = TinkerBehaviorUtil.tinkerOneObjectFlowKnownPathName.getCopy();
				} else {
					edgePathName = TinkerBehaviorUtil.tinkerManyObjectFlowKnownPathName.getCopy();
				}
				edgePathName.addToGenerics(objectFlowKnownTokenPathName);
				returnType.addToElementTypes(edgePathName);
				sb = new StringBuilder("return Arrays.<");
				sb.append(edgePathName.getLast());
				sb.append(">asList(");
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
		implementMultiplicity(inputPinClass, oa.getNakedMultiplicity());
	}

	private void implementMultiplicity(OJAnnotatedClass inputPinClass, INakedParameterNode oa) {
		implementMultiplicity(inputPinClass, oa.getNakedMultiplicity());
	}

	private void implementMultiplicity(OJAnnotatedClass inputPinClass, INakedMultiplicity oa) {
		OJAnnotatedOperation getUpperMultiplicity = new OJAnnotatedOperation("getUpperMultiplicity", new OJPathName("int"));
		TinkerGenerationUtil.addOverrideAnnotation(getUpperMultiplicity);
		getUpperMultiplicity.getBody().addToStatements("return " + oa.getUpper());
		inputPinClass.addToOperations(getUpperMultiplicity);

		OJAnnotatedOperation getLowerMultiplicity = new OJAnnotatedOperation("getLowerMultiplicity", new OJPathName("int"));
		TinkerGenerationUtil.addOverrideAnnotation(getLowerMultiplicity);
		getLowerMultiplicity.getBody().addToStatements("return " + oa.getLower());
		inputPinClass.addToOperations(getLowerMultiplicity);
	}

	private void implementGetAction(OJAnnotatedClass inputPinClass, INakedPin pin) {
		ConcreteEmulatedClassifier actionClassifier = new ConcreteEmulatedClassifier(pin.getNameSpace(), pin.getAction());
		NakedStructuralFeatureMap actionMap = new NakedStructuralFeatureMap(new ActionBridge(actionClassifier, pin.getAction()));

		OJAnnotatedField actionField = new OJAnnotatedField(actionMap.fieldname(), TinkerBehaviorUtil.activityNodePathName(pin.getAction()));
		inputPinClass.addToFields(actionField);

		OJAnnotatedOperation getAction = new OJAnnotatedOperation("getAction");
		TinkerGenerationUtil.addOverrideAnnotation(getAction);
		getAction.setReturnType(TinkerBehaviorUtil.activityNodePathName(pin.getAction()));

		OJIfStatement ifActionNull = new OJIfStatement("this." + actionMap.fieldname() + " == null");
		ifActionNull.addToThenPart("this." + actionMap.fieldname() + " = new " + TinkerBehaviorUtil.activityNodePathName(pin.getAction()).getLast() + "(this.vertex.getInEdges(\""
				+ TinkerBehaviorUtil.pinActionEdgeName(pin) + "\").iterator().next().getOutVertex(), this.contextObject)");

		getAction.getBody().addToStatements(ifActionNull);
		getAction.getBody().addToStatements("return this." + actionMap.fieldname());
		inputPinClass.addToOperations(getAction);
	}

	private void addGetBodyExpression(OJAnnotatedClass actionClass, INakedOclAction oa) {
		OJAnnotatedOperation getBodyExpression = new OJAnnotatedOperation("getBodyExpression");
		TinkerGenerationUtil.addOverrideAnnotation(getBodyExpression);
		actionClass.addToOperations(getBodyExpression);
		if (oa.getBodyExpression() != null) {
			String expressValue = ValueSpecificationUtil.expressValue(getBodyExpression, new NakedValueSpecificationImpl(oa.getBodyExpression()), oa.getActivity(), oa
					.getReturnPin().getType());
			getBodyExpression.getBody().addToStatements("return " + expressValue);
			if (oa.getReturnPin().getNakedMultiplicity().isMany()) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), oa.getReturnPin(), false));
				OJPathName collectionPathName = new OJPathName("java.util.Collection");
				collectionPathName.addToElementTypes(map.javaBaseTypePath());
				getBodyExpression.setReturnType(collectionPathName);
			} else {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), oa.getReturnPin(), false));
				getBodyExpression.setReturnType(map.javaBaseTypePath());
			}
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

	private void addCopyEventToOutputPin(OJAnnotatedClass actionClass, INakedAcceptEventAction oa) {
		OJAnnotatedOperation copyEventToOutputPin = new OJAnnotatedOperation("copyEventToOutputPin");
		TinkerGenerationUtil.addOverrideAnnotation(copyEventToOutputPin);
		copyEventToOutputPin.addParam("event", TinkerBehaviorUtil.tinkerIEventPathName.getCopy());
		// TODO think about many triggers here, for now kinda assuming one
		for (INakedTrigger trigger : oa.getTriggers()) {

			if (trigger.getEvent() instanceof INakedSignalEvent) {
				OJIfStatement ifSignal = new OJIfStatement("event instanceof " + TinkerBehaviorUtil.tinkerISignalEventPathName.getLast());
				ifSignal.addToThenPart(TinkerBehaviorUtil.tinkerISignalEventPathName.getLast() + " signalEvent = (" + TinkerBehaviorUtil.tinkerISignalEventPathName.getLast()
						+ ")event");
				actionClass.addToImports(TinkerBehaviorUtil.tinkerISignalEventPathName);
				INakedSignalEvent signalEvent = (INakedSignalEvent) trigger.getEvent();
				INakedSignal signal = signalEvent.getSignal();
				String signalClassName = OJUtil.classifierPathname(signal).getLast();
				actionClass.addToImports(OJUtil.classifierPathname(signal));
				OJIfStatement ifInstanceOf = new OJIfStatement("signalEvent.getSignal() instanceof " + signalClassName);
				ifInstanceOf.addToThenPart(signalClassName + " tmp  = (" + signalClassName + ")signalEvent.getSignal()");

				// Correlate signal attributes with output pins
				int i = 0;
				for (INakedOutputPin outputPin : oa.getOutput()) {
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(signal.getOwnedAttributes().get(i++));
					ifInstanceOf.addToThenPart(TinkerBehaviorUtil.outputPinGetterName(outputPin)
							+ "().addOutgoingToken(new "
							+ (outputPin.getNakedMultiplicity().isOne() ? TinkerBehaviorUtil.tinkerSingleObjectToken.getLast() : TinkerBehaviorUtil.tinkerCollectionObjectToken
									.getLast()) + "<" + map.javaBaseTypePath().getLast() + ">(" + TinkerBehaviorUtil.outputPinGetterName(outputPin) + "().getName(), tmp."
							+ map.getter() + "()))");
					actionClass.addToImports(map.javaBaseTypePath());
					if (outputPin.getNakedMultiplicity().isOne()) {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerSingleObjectToken);
					} else {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerCollectionObjectToken);
					}
				}

				ifSignal.addToThenPart(ifInstanceOf);
				copyEventToOutputPin.getBody().addToStatements(ifSignal);
				copyEventToOutputPin.getBody().addToStatements(ifSignal);
			} else {
				if (!(oa instanceof INakedAcceptCallAction)) {
					throw new IllegalStateException("Excepting a INakedAcceptCallAction found " + oa.getClass().getSimpleName());
				}
				if (!(trigger.getEvent() instanceof INakedCallEvent)) {
					throw new IllegalStateException("Excepting a INakedCallEvent");
				}
				INakedAcceptCallAction callAction = (INakedAcceptCallAction) oa;

				INakedCallEvent callEvent = (INakedCallEvent) trigger.getEvent();
				INakedOperation operation = callEvent.getOperation();

				ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(callEvent.getNameSpace(), callEvent);
				OJPathName eventClassifierPathname = OJUtil.classifierPathname(concreteEmulatedClassifier);
				actionClass.addToImports(eventClassifierPathname);
				copyEventToOutputPin.getBody().addToStatements(eventClassifierPathname.getLast() + " callEvent = (" + eventClassifierPathname.getLast() + ")event");

				// Correlate signal attributes with output pins
				int i = 0;
				for (INakedOutputPin outputPin : oa.getOutput()) {
					if (callAction.getReturnInfo().equals(outputPin)) {
						continue;
					}
					TypedElementPropertyBridge bridge = new TypedElementPropertyBridge(concreteEmulatedClassifier, operation.getArgumentParameters().get(i++));
					NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(bridge);
					copyEventToOutputPin.getBody().addToStatements(
							TinkerBehaviorUtil.outputPinGetterName(outputPin)
									+ "().addOutgoingToken(new "
									+ (outputPin.getNakedMultiplicity().isOne() ? TinkerBehaviorUtil.tinkerSingleObjectToken.getLast()
											: TinkerBehaviorUtil.tinkerCollectionObjectToken.getLast()) + "<" + map.javaBaseTypePath().getLast() + ">("
									+ TinkerBehaviorUtil.outputPinGetterName(outputPin) + "().getName(), callEvent." + map.getter() + "()))");

					if (outputPin.getNakedMultiplicity().isOne()) {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerSingleObjectToken);
					} else {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerCollectionObjectToken);
					}

					actionClass.addToImports(map.javaBaseTypePath());
				}

			}

		}
		actionClass.addToOperations(copyEventToOutputPin);

	}

	private void removeOutputPinfromActivityClass(INakedOutputPin oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa, true);
		OJAnnotatedClass activityClass = findJavaClass(oa.getActivity());
		OJOperation clearToRemove = null;
		OJOperation adderAllToRemove = null;
		OJOperation removeAllToRemove = null;
		OJField fieldToRemove = null;
		OJOperation adderToRemove = null;
		OJOperation removerToRemove = null;
		OJOperation internalAdderToRemove = null;
		OJOperation internalRemoverToRemove = null;
		OJOperation getter = null;
		OJOperation setter = null;
		OJOperation manySetter = null;
		for (OJField field : activityClass.getFields()) {
			if (field.getName().equals(map.fieldname())) {
				fieldToRemove = field;
				clearToRemove = activityClass.findOperation(map.clearer(), Collections.emptyList());
				removeAllToRemove = activityClass.findOperation(map.removeAll(), Arrays.asList(map.javaTypePath()));
				adderAllToRemove = activityClass.findOperation(map.allAdder(), Arrays.asList(map.javaTypePath()));
				adderToRemove = activityClass.findOperation(map.adder(), Arrays.asList(map.javaBaseTypePath()));
				removerToRemove = activityClass.findOperation(map.remover(), Arrays.asList(map.javaBaseTypePath()));
				internalAdderToRemove = activityClass.findOperation(map.internalAdder(), Arrays.asList(map.javaBaseTypePath()));
				internalRemoverToRemove = activityClass.findOperation(map.internalRemover(), Arrays.asList(map.javaBaseTypePath()));
				getter = activityClass.findOperation(map.getter(), Collections.emptyList());
				setter = activityClass.findOperation(map.setter(), Arrays.asList(map.javaBaseTypePath()));
				manySetter = activityClass.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
				break;
			}
		}
		activityClass.removeFromOperations(removeAllToRemove);
		activityClass.removeFromOperations(clearToRemove);
		activityClass.removeFromFields(fieldToRemove);
		activityClass.removeFromOperations(adderToRemove);
		activityClass.removeFromOperations(adderAllToRemove);
		activityClass.removeFromOperations(removerToRemove);
		activityClass.removeFromOperations(internalAdderToRemove);
		activityClass.removeFromOperations(internalRemoverToRemove);
		activityClass.removeFromOperations(getter);
		activityClass.removeFromOperations(setter);
		activityClass.removeFromOperations(manySetter);

		OJAnnotatedOperation clearCache = (OJAnnotatedOperation) activityClass.findOperation("clearCache", Collections.emptyList());
		clearCache.getBody().removeFromStatements(clearCache.getBody().findStatement(map.fieldname()));

	}

	private void addBlockingQueue(OJAnnotatedClass actionClass, INakedReplyAction oa) {
		OJField synchronousField = new OJField();
		synchronousField.setName("replyQueue");
		synchronousField.setType(new OJPathName("java.util.concurrent.BlockingQueue"));
		for (INakedInputPin inputPin : oa.getInput()) {
			if (oa instanceof INakedReplyAction && !inputPin.equals(((INakedReplyAction) oa).getReturnInfo())) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
				synchronousField.getType().addToGenerics(map.javaBaseTypePath());
				synchronousField.setInitExp("new ArrayBlockingQueue<" + map.javaBaseTypePath().getLast() + ">(1)");
				break;
			}
		}
		actionClass.addToFields(synchronousField);
		actionClass.addToImports(new OJPathName("java.util.concurrent.ArrayBlockingQueue"));
		actionClass.addToImports(new OJPathName("java.util.concurrent.BlockingQueue"));
	}

	private void addGetReply(OJAnnotatedClass actionClass, INakedReplyAction oa) {
		OJAnnotatedOperation getReply = new OJAnnotatedOperation("getReply");
		TinkerGenerationUtil.addOverrideAnnotation(getReply);

		getReply.getBody().addToStatements("return " + TinkerBehaviorUtil.tinkerOperationBlockingQueue.getLast() + ".INSTANCE.take(getUid())");
		actionClass.addToImports(TinkerBehaviorUtil.tinkerOperationBlockingQueue);

		for (INakedInputPin inputPin : oa.getInput()) {
			if (oa instanceof INakedReplyAction && !inputPin.equals(((INakedReplyAction) oa).getReturnInfo())) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));
				getReply.setReturnType(map.javaBaseTypePath());
				break;
			}
		}

		actionClass.addToOperations(getReply);
	}

	private void putReplyInBlockingQueue(OJAnnotatedClass actionClass, INakedReplyAction oa) {
		for (INakedInputPin inputPin : oa.getInput()) {
			if (oa instanceof INakedReplyAction && !inputPin.equals(((INakedReplyAction) oa).getReturnInfo())) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(oa.getActivity(), inputPin, false));

				OJOperation adder = actionClass.findOperation(map.internalAdder(), Arrays.asList(map.javaBaseTypePath()));
				adder.getBody().addToStatements(TinkerBehaviorUtil.tinkerOperationBlockingQueue.getLast() + ".INSTANCE.put(getUid(), " + map.fieldname() + ")");
				actionClass.addToImports(TinkerBehaviorUtil.tinkerOperationBlockingQueue);

				break;
			}
		}

	}

	private void implementGetReturnInformationInputPin(OJAnnotatedClass outputPinClass, INakedOutputPin oa) {
		OJAnnotatedOperation getReturnInformationInputPin = new OJAnnotatedOperation("getReturnInformationInputPin");
		getReturnInformationInputPin.setReturnType(TinkerBehaviorUtil.activityNodePathName(oa.getOutgoing().iterator().next().getTarget()));
		INakedActivityEdge outFlow = oa.getOutgoing().iterator().next();
		INakedActivityNode target = outFlow.getTarget();

		ConcreteEmulatedClassifier inputPinClassifier = new ConcreteEmulatedClassifier(target.getNameSpace(), target);
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(new ActivityNodeBridge(inputPinClassifier, target));

		getReturnInformationInputPin.getBody().addToStatements("return " + TinkerBehaviorUtil.edgeGetter(outFlow) + "()." + map.getter() + "()");
		outputPinClass.addToOperations(getReturnInformationInputPin);
	}

	private void implementStructuralFeatureAction(OJAnnotatedClass actionClass, INakedStructuralFeatureAction oa) {
		OJAnnotatedOperation getObject = new OJAnnotatedOperation("getObject");
		TinkerGenerationUtil.addOverrideAnnotation(getObject);
		getObject.setReturnType(TinkerBehaviorUtil.activityNodePathName(oa.getObject()));
		getObject.getBody().addToStatements("return this." + TinkerBehaviorUtil.inputPinGetter(oa.getObject()) + "()");
		actionClass.addToOperations(getObject);
	}

	private void implementWriteStructuralFeatureAction(OJAnnotatedClass actionClass, INakedWriteStructuralFeatureAction oa) {
		implementGetValue(actionClass, oa.getValue());

		implementGetResult(actionClass, oa.getResult());

	}

	private void implementAddStructuralFeatureValueAction(OJAnnotatedClass actionClass, INakedAddStructuralFeatureValueAction oa) {
		OJAnnotatedOperation writeStructuralFeature = new OJAnnotatedOperation("writeStructuralFeature");
		TinkerGenerationUtil.addOverrideAnnotation(writeStructuralFeature);
		NakedStructuralFeatureMap mapObject = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getObject(), true);
		writeStructuralFeature.addParam("o", mapObject.javaBaseTypePath());
		NakedStructuralFeatureMap mapValue = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getValue(), true);
		writeStructuralFeature.addParam("v", mapValue.javaBaseTypePath());

		NakedStructuralFeatureMap mapFeature = OJUtil.buildStructuralFeatureMap(oa.getFeature());

		if (mapFeature.isOne()) {
			writeStructuralFeature.getBody().addToStatements("o." + mapFeature.setter() + "(v)");
		} else {
			// TODO take insertAt into account is ordered
			writeStructuralFeature.getBody().addToStatements("o." + mapFeature.adder() + "(v)");
		}
		actionClass.addToOperations(writeStructuralFeature);
	}

	private void implementGenerics(OJAnnotatedClass actionClass, INakedVariableAction oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getVariable());
		actionClass.getSuperclass().addToGenerics(map.javaBaseTypePath());
	}

	private void implementGenerics(OJAnnotatedClass actionClass, INakedAddStructuralFeatureValueAction oa) {
		NakedStructuralFeatureMap mapObject = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getObject(), true);
		NakedStructuralFeatureMap mapFeature = OJUtil.buildStructuralFeatureMap(oa.getFeature());
		actionClass.getSuperclass().addToGenerics(mapFeature.javaBaseTypePath());
		actionClass.getSuperclass().addToGenerics(mapObject.javaBaseTypePath());
	}

	private void implementGenerics(OJAnnotatedClass actionClass, INakedCreateObjectAction oa) {
		NakedClassifierMap map = OJUtil.buildClassifierMap(oa.getClassifier());
		actionClass.addToImports(map.javaTypePath());
		actionClass.getSuperclass().addToGenerics(map.javaTypePath());
	}

	private void implementCreateObject(OJAnnotatedClass actionClass, INakedCreateObjectAction oa) {
		NakedClassifierMap map = OJUtil.buildClassifierMap(oa.getClassifier());
		OJAnnotatedOperation createObject = new OJAnnotatedOperation("createObject");
		TinkerGenerationUtil.addOverrideAnnotation(createObject);
		createObject.setReturnType(map.javaTypePath());
		createObject.getBody().addToStatements("return new " + map.javaTypePath().getLast() + "(true)");
		actionClass.addToOperations(createObject);
	}

	private void implementGetResult(OJAnnotatedClass actionClass, INakedOutputPin oa) {
		OJAnnotatedOperation getResult = new OJAnnotatedOperation("getResult");
		TinkerGenerationUtil.addOverrideAnnotation(getResult);
		getResult.setReturnType(TinkerBehaviorUtil.activityNodePathName(oa));
		getResult.getBody().addToStatements("return this." + TinkerBehaviorUtil.outputPinGetterName(oa) + "()");
		actionClass.addToOperations(getResult);
	}

	// TODO support multiplicity many
	private void implementWriteVariable(OJAnnotatedClass actionClass, INakedAddVariableValueAction oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getVariable());
		OJAnnotatedOperation writeVariable = new OJAnnotatedOperation("writeVariable");
		TinkerGenerationUtil.addOverrideAnnotation(writeVariable);
		actionClass.addToOperations(writeVariable);
		writeVariable.addToParameters(new OJParameter("v", map.javaBaseTypePath()));
		writeVariable.getBody().addToStatements("this.getActivity()." + map.setter() + "(v)");
	}

	private void implementGetValue(OJAnnotatedClass actionClass, INakedInputPin value) {
		OJAnnotatedOperation getValue = new OJAnnotatedOperation("getValue");
		TinkerGenerationUtil.addOverrideAnnotation(getValue);
		getValue.setReturnType(TinkerBehaviorUtil.activityNodePathName(value));
		getValue.getBody().addToStatements("return this." + TinkerBehaviorUtil.inputPinGetter(value) + "()");
		actionClass.addToOperations(getValue);
	}

	// TODO support multiplicity many
	private void implementReadVariable(OJAnnotatedClass actionClass, INakedVariableAction oa) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getActivity(), oa.getVariable());
		OJAnnotatedOperation getVariable = new OJAnnotatedOperation("getVariable");
		TinkerGenerationUtil.addOverrideAnnotation(getVariable);
		getVariable.setReturnType(map.javaBaseTypePath());
		getVariable.getBody().addToStatements("return this.getActivity()." + map.getter() + "()");
		actionClass.addToOperations(getVariable);
	}

	private void addGetValue(OJAnnotatedClass inputPinClass, INakedValuePin oa) {
		OJAnnotatedOperation getValue = new OJAnnotatedOperation("getValue");
		TinkerGenerationUtil.addOverrideAnnotation(getValue);
		getValue.setReturnType(OJUtil.classifierPathname(oa.getNakedBaseType()));
		String expressValue = ValueSpecificationUtil.expressValue(getValue, oa.getValue(), oa.getAction().getContext(), oa.getType());
		getValue.getBody().addToStatements("return " + expressValue);
		inputPinClass.addToOperations(getValue);
	}

}
