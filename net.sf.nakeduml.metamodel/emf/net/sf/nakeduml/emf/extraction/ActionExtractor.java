package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptEventActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCallBehaviorActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCallOperationActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedCreateObjectActionimpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedOpaqueActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedSendObjectActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedSendSignalActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedStartClassifierBehaviorActionImpl;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedOperation;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.SendObjectAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;

@StepDependency(phase = EmfExtractionPhase.class,requires = {TypedElementExtractor.class,ActivityStructureExtractor.class},after = {
		TypedElementExtractor.class,ActivityStructureExtractor.class})
public class ActionExtractor extends AbstractActionExtractor{
	@VisitBefore
	public void visitCreateObjectAction(CreateObjectAction emfAction,NakedCreateObjectActionimpl nakedAction){
		nakedAction.setClassifier((INakedClassifier) getNakedPeer(emfAction.getClassifier()));
		nakedAction.setResult((INakedOutputPin) initializePin(emfAction.getActivity(), emfAction.getResult(), emfAction.getClassifier()));
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}
	@VisitBefore
	public void visitStartClassifierBehaviorAction(StartClassifierBehaviorAction emfAction,NakedStartClassifierBehaviorActionImpl nakedAction){
		nakedAction.setTarget((INakedInputPin) initializePin(emfAction.getActivity(), emfAction.getObject(), null));
//		List<INakedOutputPin> arguments = populatePins(getActivity(emfAction), emfAction.getOutputs());
//		nakedAction.set(arguments);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}
	@VisitBefore
	public void visitCallBehaviorAction(CallBehaviorAction emfAction,NakedCallBehaviorActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setBehavior((INakedBehavior) getNakedPeer(emfAction.getBehavior()));
		// RSA workardound - it forces the called behavior's name into the name
		// of
		// the action
		nakedAction.setName("call" + emfAction.getBehavior().getName());
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}
	@VisitBefore
	public void visitOpaqueAction(OpaqueAction emfAction,NakedOpaqueActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		List<InputPin> inputs = new ArrayList<InputPin>(emfAction.getInputValues());
		InputPin target = null;
		for(InputPin p:inputs){
			if(p.hasKeyword("target") || StereotypesHelper.hasStereotype(p, "target")){
				target = p;
			}
		}
		if(target != null){
			nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, target, null));
			inputs.remove(target);
		}
		List<INakedInputPin> inputValues = populatePins(emfActivity, inputs);
		nakedAction.setInputValues(inputValues);
		List<INakedOutputPin> outputValues = populatePins(emfActivity, emfAction.getOutputValues());
		nakedAction.setOutputValues(outputValues);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}
	@VisitBefore
	public void visitCallOperationAction(CallOperationAction emfAction,NakedCallOperationActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setOperation((INakedOperation) getNakedPeer(emfAction.getOperation()));
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget(), emfAction.getOperation().getClass_()));
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}
	@VisitBefore
	public void visitSendSignalAction(SendSignalAction emfAction,NakedSendSignalActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget(), null));
		nakedAction.setSignal((INakedSignal) getNakedPeer(emfAction.getSignal()));
		List<INakedInputPin> arguments = populatePins(emfActivity, emfAction.getArguments());
		nakedAction.setArguments(arguments);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}
	@VisitBefore
	public void visitSendObjectAction(SendObjectAction emfAction,NakedSendObjectActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setTarget((INakedInputPin) initializePin(emfActivity, emfAction.getTarget(), null));
		nakedAction.setRequest((INakedInputPin) initializePin(emfActivity, emfAction.getRequest(), null));
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}
	@VisitBefore
	public void visitAcceptEventAction(AcceptEventAction emfAction,NakedAcceptEventActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		if(!emfAction.getTriggers().isEmpty()){
			// we only support one trigger
			nakedAction.setEvent(buildEvent(emfActivity, emfAction.getTriggers().iterator().next()));
		}
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		assignPartition(nakedAction, emfAction);
	}
	@VisitBefore
	public void visitAcceptCallAction(AcceptCallAction emfAction,NakedAcceptEventActionImpl nakedAction){
		Activity emfActivity = getActivity(emfAction);
		if(!emfAction.getTriggers().isEmpty()){
			// we only support one trigger
			nakedAction.setEvent(buildEvent(emfActivity, emfAction.getTriggers().iterator().next()));
		}
		this.addLocalPreAndPostConditions(nakedAction, emfAction);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
		assignPartition(nakedAction, emfAction);
	}

}
