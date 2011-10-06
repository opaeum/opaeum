package org.opeum.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.RaiseExceptionAction;
import org.eclipse.uml2.uml.ReadSelfAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.SendObjectAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import org.opeum.metamodel.actions.internal.NakedCallOperationActionImpl;
import org.opeum.metamodel.actions.internal.NakedCreateObjectActionimpl;
import org.opeum.metamodel.actions.internal.NakedGenericActionImpl;
import org.opeum.metamodel.actions.internal.NakedOclActionImpl;
import org.opeum.metamodel.actions.internal.NakedOpaqueActionImpl;
import org.opeum.metamodel.actions.internal.NakedRaiseExceptionActionImpl;
import org.opeum.metamodel.actions.internal.NakedReplyActionImpl;
import org.opeum.metamodel.actions.internal.NakedSendObjectActionImpl;
import org.opeum.metamodel.actions.internal.NakedSendSignalActionImpl;
import org.opeum.metamodel.actions.internal.NakedStartClassifierBehaviorActionImpl;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.bpm.internal.NakedEmbeddedScreenFlowTaskImpl;
import org.opeum.metamodel.bpm.internal.NakedEmbeddedSingleScreenTaskImpl;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedSignal;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.internal.NakedElementImpl;
import org.opeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = EmfExtractionPhase.class,requires = {
		FeatureExtractor.class,ActivityStructureExtractor.class,InstanceExtractor.class
},after = {
		FeatureExtractor.class,ActivityStructureExtractor.class,InstanceExtractor.class
})
public class ActionExtractor extends AbstractActionExtractor{
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof OpaqueAction){
			OpaqueAction emfAction = (OpaqueAction) e;
			Stereotype stereotype = StereotypesHelper.getStereotype(emfAction, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
			if(stereotype != null){
				return new NakedEmbeddedSingleScreenTaskImpl();
			}else {
				return new NakedOclActionImpl();
			}
		}else if(e instanceof CallBehaviorAction){
			CallBehaviorAction emfAction = (CallBehaviorAction) e;
			Stereotype stereotype = StereotypesHelper.getStereotype(emfAction, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
			if(stereotype != null && emfAction.getBehavior() instanceof StateMachine){
				return new NakedEmbeddedScreenFlowTaskImpl();
			}else{
				return new NakedCallBehaviorActionImpl();
			}
		}else{
			return super.createElementFor(e, peerClass);
		}
	}
	@VisitBefore
	public void visitCreateObjectAction(CreateObjectAction emfAction,NakedCreateObjectActionimpl nakedAction){
		initAction(emfAction, nakedAction);
		nakedAction.setClassifier((INakedClassifier) getNakedPeer(emfAction.getClassifier()));
		nakedAction.setResult((INakedOutputPin) initializePin(emfAction.getActivity(), emfAction.getResult()));
	}
	@VisitBefore
	public void visitReplyAction(ReplyAction emfAction,NakedReplyActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		nakedAction.setReturnInfo((INakedInputPin) initializePin(getActivity(emfAction), emfAction.getReturnInformation()));
		List<INakedInputPin> replyValues = populatePins(getActivity(emfAction), emfAction.getReplyValues());
		nakedAction.setReplyValues(replyValues);
	}
	@VisitBefore
	public void visitStartClassifierBehaviorAction(StartClassifierBehaviorAction emfAction,NakedStartClassifierBehaviorActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		nakedAction.setObject((INakedInputPin) initializePin(emfAction.getActivity(), emfAction.getObject()));
		// List<INakedOutputPin> arguments =
		// populatePins(getActivity(emfAction), emfAction.getOutputs());
		// nakedAction.set(arguments);
	}
	@VisitBefore
	public void visitCallBehaviorAction(CallBehaviorAction emfAction,NakedCallBehaviorActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		Stereotype stereotype = StereotypesHelper.getStereotype(emfAction, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		if(stereotype != null){
			initializeDeadlines(stereotype, emfAction);
		}
		initAction(emfAction, nakedAction);
		nakedAction.setBehavior((INakedBehavior) getNakedPeer(emfAction.getBehavior()));
		nakedAction.setSynchronous(emfAction.isSynchronous());
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
	}
	@VisitBefore
	public void visitOpaqueAction(OpaqueAction emfAction,NakedOpaqueActionImpl action){
		Activity emfActivity = getActivity(emfAction);
		Stereotype stereotype = StereotypesHelper.getStereotype(emfAction, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
		initAction(emfAction, action);
		if(stereotype != null){
			initializeDeadlines(stereotype, emfAction);
			((NakedEmbeddedSingleScreenTaskImpl) action).setOutputValues(this.<INakedOutputPin>populatePins(emfActivity, emfAction.getOutputValues()));
		}else if(emfAction.getBodies().size() >= 1){
			ParsedOclString bodyExpression = super.buildParsedOclString(emfAction, emfAction.getLanguages(), emfAction.getBodies(), OclUsageType.DEF);
			((NakedOclActionImpl) action).setBodyExpression(bodyExpression);
			if(emfAction.getOutputValues().size() == 1){
				((NakedOclActionImpl) action).setReturnPin((INakedOutputPin) initializePin(emfActivity, emfAction.getOutputValues().get(0)));
			}
		}
		List<InputPin> inputs = new ArrayList<InputPin>(emfAction.getInputValues());
		action.setInputValues(this.<INakedInputPin>populatePins(emfActivity, inputs));
	}
	@VisitBefore
	public void visitCallOperationAction(CallOperationAction emfAction,NakedCallOperationActionImpl nakedAction){
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
	public void visitReadSelfAction(ReadSelfAction emfAction,NakedGenericActionImpl nakedAction){
		populateGenericAction(emfAction, nakedAction);
		
	}
	protected void populateGenericAction(Action emfAction,NakedGenericActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		List<INakedInputPin> inputs = populatePins(emfActivity, emfAction.getInputs());
		nakedAction.setInput(inputs);
		List<INakedOutputPin> outputs = populatePins(emfActivity, emfAction.getOutputs());
		nakedAction.setOutput(outputs);
	}
	@VisitBefore
	public void visitSendSignalAction(SendSignalAction emfAction,NakedSendSignalActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget()));
		nakedAction.setSignal((INakedSignal) getNakedPeer(emfAction.getSignal()));
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
	}
	@VisitBefore
	public void visitSendObjectAction(SendObjectAction emfAction,NakedSendObjectActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget()));
		nakedAction.setRequest((INakedInputPin) initializePin(emfActivity, emfAction.getRequest()));
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
	}
	@VisitBefore
	public void visitRaiseExceptionAction(RaiseExceptionAction emfAction,NakedRaiseExceptionActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		nakedAction.setException((INakedInputPin) initializePin(emfAction.getActivity(), emfAction.getException()));
	}
}
