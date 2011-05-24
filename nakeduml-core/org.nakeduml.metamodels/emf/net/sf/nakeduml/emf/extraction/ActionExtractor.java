package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptCallActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptEventActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCallOperationActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCreateObjectActionimpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedOpaqueActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedRaiseExceptionActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedReplyActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedSendObjectActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedSendSignalActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedStartClassifierBehaviorActionImpl;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.RaiseExceptionAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.SendObjectAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;

@StepDependency(phase = EmfExtractionPhase.class, requires = { TypedElementExtractor.class, ActivityStructureExtractor.class }, after = {
		TypedElementExtractor.class, ActivityStructureExtractor.class })
public class ActionExtractor extends AbstractActionExtractor {
	@VisitBefore
	public void visitCreateObjectAction(CreateObjectAction emfAction, NakedCreateObjectActionimpl nakedAction) {
		initAction(emfAction, nakedAction);
		nakedAction.setClassifier((INakedClassifier) getNakedPeer(emfAction.getClassifier()));
		nakedAction.setResult((INakedOutputPin) initializePin(emfAction.getActivity(), emfAction.getResult()));
	}
	@VisitBefore
	public void visitReplyAction(ReplyAction emfAction, NakedReplyActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		nakedAction.setReturnInfo((INakedInputPin) initializePin(getActivity(emfAction), emfAction.getReturnInformation()));
		List<INakedInputPin> replyValues = populatePins(getActivity(emfAction), emfAction.getReplyValues());
		nakedAction.setReplyValues(replyValues);
	}

	@VisitBefore
	public void visitStartClassifierBehaviorAction(StartClassifierBehaviorAction emfAction,
			NakedStartClassifierBehaviorActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfAction.getActivity(), emfAction.getObject()));
		// List<INakedOutputPin> arguments =
		// populatePins(getActivity(emfAction), emfAction.getOutputs());
		// nakedAction.set(arguments);
	}

	@VisitBefore
	public void visitCallBehaviorAction(CallBehaviorAction emfAction, NakedCallBehaviorActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
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
	}

	@VisitBefore
	public void visitOpaqueAction(OpaqueAction emfAction, NakedOpaqueActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
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
	}

	@VisitBefore
	public void visitCallOperationAction(CallOperationAction emfAction, NakedCallOperationActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		Operation operation = emfAction.getOperation();
		nakedAction.setOperation((INakedOperation) getNakedPeer(operation));
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget()));
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		nakedAction.setSynchronous(emfAction.isSynchronous());
	}

	@VisitBefore
	public void visitSendSignalAction(SendSignalAction emfAction, NakedSendSignalActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget()));
		nakedAction.setSignal((INakedSignal) getNakedPeer(emfAction.getSignal()));
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
	}

	@VisitBefore
	public void visitSendObjectAction(SendObjectAction emfAction, NakedSendObjectActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget()));
		nakedAction.setRequest((INakedInputPin) initializePin(emfActivity, emfAction.getRequest()));
	}

	@VisitBefore
	public void visitAcceptEventAction(AcceptEventAction emfAction, NakedAcceptEventActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		if (!emfAction.getTriggers().isEmpty()) {
			// we only support one trigger
			nakedAction.setTrigger(buildTrigger(emfActivity, emfAction.getTriggers().iterator().next()));
		}
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
	}


	@VisitBefore
	public void visitAcceptCallAction(AcceptCallAction emfAction, NakedAcceptCallActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		if (!emfAction.getTriggers().isEmpty()) {
			// we only support one trigger
			nakedAction.setTrigger(buildTrigger(emfActivity, emfAction.getTriggers().iterator().next()));
		}
		nakedAction.setReturnInfo((INakedOutputPin) initializePin(emfActivity, emfAction.getReturnInformation()));
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
	}
	@VisitBefore
	public void visitRaiseExceptionAction(RaiseExceptionAction emfAction, NakedRaiseExceptionActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		nakedAction.setException((INakedInputPin) initializePin(emfAction.getActivity(), emfAction.getException()));
	}
}
