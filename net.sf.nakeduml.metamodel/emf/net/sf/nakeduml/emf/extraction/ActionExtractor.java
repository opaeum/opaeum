package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptEventActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCallOperationActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCreateObjectActionimpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedOpaqueActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedSendObjectActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedSendSignalActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedStartClassifierBehaviorActionImpl;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedExpansionNodeImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedExpansionRegionImpl;
import net.sf.nakeduml.metamodel.activities.internal.NakedObjectNodeImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.SendObjectAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;

@StepDependency(phase = EmfExtractionPhase.class, requires = { TypedElementExtractor.class, ActivityStructureExtractor.class }, after = {
		TypedElementExtractor.class, ActivityStructureExtractor.class })
public class ActionExtractor extends AbstractActionExtractor {
	@VisitBefore
	public void visitCreateObjectAction(CreateObjectAction emfAction, NakedCreateObjectActionimpl nakedAction) {
		nakedAction.setClassifier((INakedClassifier) getNakedPeer(emfAction.getClassifier()));
		nakedAction.setResult((INakedOutputPin) initializePin(emfAction.getActivity(), emfAction.getResult()));
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitStartClassifierBehaviorAction(StartClassifierBehaviorAction emfAction,
			NakedStartClassifierBehaviorActionImpl nakedAction) {
		nakedAction.setTarget((INakedInputPin) initializePin(emfAction.getActivity(), emfAction.getObject()));
		// List<INakedOutputPin> arguments =
		// populatePins(getActivity(emfAction), emfAction.getOutputs());
		// nakedAction.set(arguments);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitCallBehaviorAction(CallBehaviorAction emfAction, NakedCallBehaviorActionImpl nakedAction) {
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setBehavior((INakedBehavior) getNakedPeer(emfAction.getBehavior()));
		// RSA workardound - it forces the called behavior's name into the name
		// of
		// the action
		nakedAction.setSynchronous(emfAction.isSynchronous());
		nakedAction.setName("call" + emfAction.getBehavior().getName());
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitOpaqueAction(OpaqueAction emfAction, NakedOpaqueActionImpl nakedAction) {
		Activity emfActivity = getActivity(emfAction);
		List<InputPin> inputs = new ArrayList<InputPin>(emfAction.getInputValues());
		InputPin target = null;
		for (InputPin p : inputs) {
			if (p.hasKeyword("target") || StereotypesHelper.hasStereotype(p, "target")) {
				target = p;
			}
		}
		if (target != null) {
			nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, target));
			inputs.remove(target);
		}
		ParsedOclString bodyExpression = super.buildParsedOclString(emfAction, OclUsageType.BODY, emfAction.getLanguages(),
				emfAction.getBodies());
		nakedAction.setBodyExpression(bodyExpression);
		List<INakedInputPin> inputValues = populatePins(emfActivity, inputs);
		nakedAction.setInputValues(inputValues);
		List<INakedOutputPin> outputValues = populatePins(emfActivity, emfAction.getOutputValues());
		nakedAction.setOutputValues(outputValues);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitCallOperationAction(CallOperationAction emfAction, NakedCallOperationActionImpl nakedAction) {
		Activity emfActivity = getActivity(emfAction);
		Operation operation = emfAction.getOperation();
		nakedAction.setOperation((INakedOperation) getNakedPeer(operation));
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget()));
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		nakedAction.setSynchronous(emfAction.isSynchronous());
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitSendSignalAction(SendSignalAction emfAction, NakedSendSignalActionImpl nakedAction) {
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget()));
		nakedAction.setSignal((INakedSignal) getNakedPeer(emfAction.getSignal()));
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitSendObjectAction(SendObjectAction emfAction, NakedSendObjectActionImpl nakedAction) {
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget()));
		nakedAction.setRequest((INakedInputPin) initializePin(emfActivity, emfAction.getRequest()));
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitAcceptEventAction(AcceptEventAction emfAction, NakedAcceptEventActionImpl nakedAction) {
		Activity emfActivity = getActivity(emfAction);
		if (!emfAction.getTriggers().isEmpty()) {
			// we only support one trigger
			nakedAction.setEvent(buildEvent(emfActivity, emfAction.getTriggers().iterator().next()));
		}
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitAcceptCallAction(AcceptCallAction emfAction, NakedAcceptEventActionImpl nakedAction) {
		Activity emfActivity = getActivity(emfAction);
		if (!emfAction.getTriggers().isEmpty()) {
			// we only support one trigger
			nakedAction.setEvent(buildEvent(emfActivity, emfAction.getTriggers().iterator().next()));
		}
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		assignPartition(nakedAction, emfAction);
	}
}
