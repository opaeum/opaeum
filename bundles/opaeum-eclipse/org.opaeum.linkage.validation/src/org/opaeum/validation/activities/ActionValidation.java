package org.opaeum.validation.activities;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.StructuralFeatureAction;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.VariableAction;
import org.eclipse.uml2.uml.WriteStructuralFeatureAction;
import org.eclipse.uml2.uml.WriteVariableAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class ActionValidation extends AbstractValidator{
	@VisitBefore(matchSubclasses = true)
	public void visitStructuralFeatureAction(StructuralFeatureAction a){
		if(a.getStructuralFeature() == null){
			getErrorMap().putError(a, ActionValidationRule.FEATURE_ACTION_REQUIRES_FEATURE);
		}else{
			if(a instanceof ReadStructuralFeatureAction){
				ReadStructuralFeatureAction rsfa = (ReadStructuralFeatureAction) a;
				if(rsfa.getResult() == null){
					getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Output Pin", "Result");
				}else if(!canReadDataFrom(rsfa.getResult(), rsfa.getStructuralFeature())){
					getErrorMap().putError(rsfa.getResult(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Property", rsfa.getStructuralFeature());
				}
			}
			if(a instanceof WriteStructuralFeatureAction){
				WriteStructuralFeatureAction wsfa = (WriteStructuralFeatureAction) a;
				if(wsfa.getValue() == null){
					getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Input Pin", "Value");
				}else if(!canWriteDataTo(wsfa.getValue(), wsfa.getStructuralFeature())){
					getErrorMap().putError(wsfa.getValue(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Property", wsfa.getStructuralFeature());
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitVariableAction(VariableAction a){
		if(a.getVariable() == null){
			getErrorMap().putError(a, ActionValidationRule.VARIABLE_ACTION_REQUIRES_FEATURE);
		}
		if(a instanceof ReadVariableAction){
			ReadVariableAction rva = (ReadVariableAction) a;
			if(rva.getResult() == null){
				getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Output Pin", "Result");
			}else if(!canReadDataFrom(rva.getResult(), rva.getVariable())){
				getErrorMap().putError(rva.getResult(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Variable", rva.getVariable());
			}
		}
		if(a instanceof WriteVariableAction){
			WriteVariableAction wva = (WriteVariableAction) a;
			if(wva.getValue() == null){
				getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Input Pin", "Value");
			}else if(!canWriteDataTo(wva.getValue(), wva.getVariable())){
				getErrorMap().putError(wva.getValue(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Variable", wva.getVariable());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitSendSignalAction(SendSignalAction a){
		if(a.getSignal() == null){
			getErrorMap().putError(a, ActionValidationRule.SEND_SIGNAL_ACTION_REQUIRES_SIGNAL);
		}else{
			List<Property> effectiveProperties = EmfPropertyUtil.getEffectiveProperties(a.getSignal());
			validateFormalVsActual(a, a.getSignal(), a.getArguments(), effectiveProperties, "arguments", "attributes");
		}
		if(EmfActionUtil.getTargetElement(a) != null){
			Classifier targetType = getLibrary().getTargetType(a);
			if(EmfClassifierUtil.isNotification(a.getSignal()) && !(EmfClassifierUtil.conformsTo(targetType, getLibrary().getNotificationReceiver()))){
				// TODO
			}else if(targetType instanceof BehavioredClassifier){
				BehavioredClassifier bc = (BehavioredClassifier) targetType;
				if(a.getSignal() != null && !EmfEventUtil.hasReceptionOrTriggerFor(bc, a.getSignal())){
					getErrorMap().putError(EmfActionUtil.getTargetElement(a), ActionValidationRule.SEND_SIGNAL_TARGET_MUST_RECEIVE_SIGNAL, a, targetType, a.getSignal());
				}
			}else if(targetType instanceof Interface){
				Interface i = (Interface) targetType;
				if(a.getSignal() != null && !EmfEventUtil.hasReceptionFor(i, a.getSignal())){
					getErrorMap().putError(EmfActionUtil.getTargetElement(a), ActionValidationRule.SEND_SIGNAL_TARGET_MUST_RECEIVE_SIGNAL, a, targetType, a.getSignal());
				}
			}else{
				getErrorMap().putError(EmfActionUtil.getTargetElement(a), ActionValidationRule.SEND_SIGNAL_REQUIRES_BEHAVIORED_CLASSIFIER_TARGET, a);
			}
		}
	}
	protected void validateFormalVsActual(Action actual,NamedElement formal,List<? extends Pin> actuals,List<? extends TypedElement> formals,
			String actualFeature,String formalFeature){
		if(actuals.size() != formals.size()){
			getErrorMap().putError(actual, ActionValidationRule.NUMBER_OF_PINS_AND_PARAMETERS, actualFeature, formalFeature, formal);
		}else{
			for(int i = 0;i < actuals.size();i++){
				Pin pin = actuals.get(i);
				TypedElement te = formals.get(i);
				if(pin instanceof OutputPin){
					if(!canReadDataFrom(pin, (MultiplicityElement) te)){
						getErrorMap().putError(pin, ActionValidationRule.REQUIRED_MULTIPLICITY, te.eClass().getName(), te);
					}
				}else{
					if(!canWriteDataTo(pin, (MultiplicityElement) te)){
						getErrorMap().putError(pin, ActionValidationRule.REQUIRED_MULTIPLICITY, te.eClass().getName(), te);
					}
					Classifier calculateType = getLibrary().getOcl().calculateType((InputPin) pin);
					if(te.getType() != null && calculateType != null && !EmfClassifierUtil.conformsTo(calculateType, (Classifier) te.getType())){
						getErrorMap().putError(pin, ActionValidationRule.PINS_MATCH_REQUIRED_TYPES, te);
					}
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallBehaviorAction(CallBehaviorAction a){
		if(a.getBehavior() == null){
			getErrorMap().putError(a, ActionValidationRule.CALL_BEHAVIOR_ACTION_REQUIRES_BEHAVIOR);
		}else{
			validateFormalVsActual(a, a.getBehavior(), a.getArguments(), EmfBehaviorUtil.getArgumentParameters(a.getBehavior()), "arguments", "inputParameters");
			validateFormalVsActual(a, a.getBehavior(), a.getResults(), EmfBehaviorUtil.getResultParameters(a.getBehavior()), "results", "outputParameters");
			Activity activity = EmfActivityUtil.getContainingActivity(a);
			if(!EmfActionUtil.findBehaviorsInScope(a).contains(a.getBehavior())){
				getErrorMap().putError(a, ActionValidationRule.CALL_BEHAVIOR_ACTION_BEHAVIOR_IN_CONTEXT, a.getBehavior(), activity, activity.getContext());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallOperationAction(CallOperationAction a){
		if(a.getOperation() == null){
			getErrorMap().putError(a, ActionValidationRule.CALL_OPERATION_ACTION_REQUIRES_OPERATION);
		}else{
			validateFormalVsActual(a, a.getOperation(), a.getArguments(), EmfBehaviorUtil.getArgumentParameters(a.getOperation()), "arguments", "inputParameters");
			validateFormalVsActual(a, a.getOperation(), a.getResults(), EmfBehaviorUtil.getResultParameters(a.getOperation()), "results", "outputParameters");
			Classifier type = getLibrary().getTargetType(a);
			if(type != null && !EmfClassifierUtil.conformsTo(type, (Classifier) a.getOperation().getOwner())){
				getErrorMap().putError(EmfActionUtil.getTargetElement(a), ActionValidationRule.CALL_OPERATION_ACTION_TARGET_TYPE_INVALID, a.getOperation().getOwner(),
						a.getOperation());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitAceptEventAction(AcceptEventAction aea){
		if(aea instanceof AcceptCallAction){
			AcceptCallAction a = (AcceptCallAction) aea;
			if(a.getTriggers().size() == 0 || !(a.getTriggers().iterator().next().getEvent() instanceof CallEvent)
					|| ((CallEvent) a.getTriggers().iterator().next().getEvent()).getOperation() == null){
				getErrorMap().putError(a, ActionValidationRule.ACCEPT_CALL_REQUIRES_SINGLE_CALL_EVENT);
			}else{
				Operation op = EmfActionUtil.getOperation(a);
				validateFormalVsActual(a, op, a.getResults(), EmfBehaviorUtil.getResultParameters(op), "results", "outputParameters");
			}
			if(a.getReturnInformation() == null || EmfActionUtil.getReplyAction(a) == null){
				getErrorMap().putError(a, ActionValidationRule.ACCEPT_CALL_RETURN_INFO_MUST_LINK);
			}
		}else{
			EList<Trigger> triggers = aea.getTriggers();
			for(Trigger trigger:triggers){
				if(trigger.getEvent() instanceof CallEvent){
					CallEvent ce = (CallEvent) trigger.getEvent();
					if(StereotypesHelper.hasStereotype(aea, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
						// TODO
					}else if(ce.getOperation() != null){
						Operation op = ce.getOperation();
						validateFormalVsActual(aea, op, aea.getResults(), EmfBehaviorUtil.getResultParameters(op), "results", "outputParameters");
					}
				}else if(trigger.getEvent() instanceof SignalEvent){
					SignalEvent se = (SignalEvent) trigger.getEvent();
					if(se.getSignal() != null){
						List<Property> effectiveProperties = EmfPropertyUtil.getEffectiveProperties(se.getSignal());
						validateFormalVsActual(aea, se.getSignal(), aea.getResults(), effectiveProperties, "results", "attributes");
					}
				}else{
					// TODO time,deadline
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitReplyAction(ReplyAction a){
		if(a.getReturnInformation() == null || EmfActionUtil.getCause(a) == null){
			getErrorMap().putError(a, ActionValidationRule.REPLY_ACTION_RETURN_INFO_MUST_LINK);
		}else{
			Operation op = EmfActionUtil.getOperation(a);
			if(op != null){
				validateFormalVsActual(a, op, a.getReplyValues(), EmfBehaviorUtil.getResultParameters(op), "replyValues", "outputParameters");
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCreateObjectAction(CreateObjectAction a){
		if(a.getClassifier() == null){
			getErrorMap().putError(a, ActionValidationRule.CREATE_OBJECT_ACTION_REQUIRES_CLASSIFIER);
		}
		if(a.getResult() == null){
			getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Output Pin", "Result");
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitExpansionRegion(ExpansionRegion a){
		if(a.getInputElements().size() != 1){
			getErrorMap().putError(a, ActionValidationRule.EXPANSION_REGION_REQUIRES_INPUT_ELEMENT);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitExpansionNode(ExpansionNode a){
		if(!(a.getRegionAsInput() != null || a.getRegionAsOutput() != null)){
			getErrorMap().putError(a, ActionValidationRule.EXPANSION_NODE_REQUIRES_EXPANSION_REGION);
		}
	}
}
