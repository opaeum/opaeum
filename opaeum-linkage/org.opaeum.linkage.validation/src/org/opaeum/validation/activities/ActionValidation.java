package org.opaeum.validation.activities;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.actions.INakedCreateObjectAction;
import org.opaeum.metamodel.actions.INakedReadStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedReadVariableAction;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.actions.INakedStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedVariableAction;
import org.opaeum.metamodel.actions.INakedWriteStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedWriteVariableAction;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class ActionValidation extends AbstractValidator{
	@VisitBefore(matchSubclasses = true)
	public void visitStructuralFeatureAction(INakedStructuralFeatureAction a){
		if(a.getFeature() == null){
			getErrorMap().putError(a, ActionValidationRule.FEATURE_ACTION_REQUIRES_FEATURE);
		}else{
			if(a instanceof INakedReadStructuralFeatureAction){
				INakedReadStructuralFeatureAction rsfa = (INakedReadStructuralFeatureAction) a;
				if(rsfa.getResult() == null){
					getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Output Pin", "Result");
				}else if(!rsfa.getResult().canAcceptInputFrom(rsfa.getFeature())){
					getErrorMap().putError(rsfa.getResult(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Property", rsfa.getFeature());

				}
			}
			if(a instanceof INakedWriteStructuralFeatureAction){
				INakedWriteStructuralFeatureAction wsfa = (INakedWriteStructuralFeatureAction) a;
				if(wsfa.getValue() == null){
					getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Input Pin", "Value");
				}else if(!wsfa.getValue().canDeliverOutputTo(wsfa.getFeature())){
					getErrorMap().putError(wsfa.getValue(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Property", wsfa.getFeature());
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitVariableAction(INakedVariableAction a){
		if(a.getVariable() == null){
			getErrorMap().putError(a, ActionValidationRule.VARIABLE_ACTION_REQUIRES_FEATURE);
		}
		if(a instanceof INakedReadVariableAction){
			INakedReadVariableAction rva = (INakedReadVariableAction) a;
			if(rva.getResult() == null){
				getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Output Pin", "Result");
			}else if(!rva.getResult().canAcceptInputFrom(rva.getVariable())){
				getErrorMap().putError(rva.getResult(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Variable", rva.getVariable());
			}
		}
		if(a instanceof INakedWriteVariableAction){
			INakedWriteVariableAction wva = (INakedWriteVariableAction) a;
			if(wva.getValue() == null){
				getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Input Pin", "Value");
			}else if(!wva.getValue().canDeliverOutputTo(wva.getVariable())){
				getErrorMap().putError(wva.getValue(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Variable", wva.getVariable());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitSendSignalAction(INakedSendSignalAction a){
		if(a.getSignal() == null){
			getErrorMap().putError(a, ActionValidationRule.SEND_SIGNAL_ACTION_REQUIRES_SIGNAL);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallBehaviorAction(INakedCallBehaviorAction a){
		if(a.getBehavior() == null){
			getErrorMap().putError(a, ActionValidationRule.CALL_BEHAVIOR_ACTION_REQUIRES_BEHAVIOR);
		}else{
			if(!a.getActivity().getEffectiveBehaviors().contains(a.getBehavior())){
				if(a.getActivity().getContext() != null){
					if(!a.getActivity().getContext().getEffectiveBehaviors().contains(a.getBehavior())){
						if(a.getInPartition() == null
								|| !(a.getInPartition().getNakedBaseType() instanceof INakedBehavioredClassifier && ((INakedBehavioredClassifier) a.getInPartition()
										.getNakedBaseType()).getEffectiveBehaviors().contains(a.getBehavior())))
							getErrorMap().putError(a, ActionValidationRule.CALL_BEHAVIOR_ACTION_BEHAVIOR_IN_CONTEXT, a.getBehavior(), a.getActivity(),
									a.getActivity().getContext());
					}
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallOperationAction(INakedCallOperationAction a){
		if(a.getOperation() == null){
			getErrorMap().putError(a, ActionValidationRule.CALL_OPERATION_ACTION_REQUIRES_OPERATION);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitAceptCallAction(INakedAcceptCallAction a){
		if(a.getTriggers().size() == 0 || !(a.getTriggers().iterator().next().getEvent() instanceof INakedCallEvent)
				|| ((INakedCallEvent) a.getTriggers().iterator().next().getEvent()).getOperation() == null){
			getErrorMap().putError(a, ActionValidationRule.ACCEPT_CALL_REQUIRES_SINGLE_CALL_EVENT);
		}
		if(a.getReturnInfo() == null || a.getReplyAction() == null){
			getErrorMap().putError(a, ActionValidationRule.ACCEPT_CALL_RETURN_INFO_MUST_LINK);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitReplyAction(INakedReplyAction ra){
		if(ra.getReturnInfo() == null || ra.getCause() == null){
			getErrorMap().putError(ra, ActionValidationRule.REPLY_ACTION_RETURN_INFO_MUST_LINK);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCreateObjectAction(INakedCreateObjectAction a){
		if(a.getClassifier() == null){
			getErrorMap().putError(a, ActionValidationRule.CREATE_OBJECT_ACTION_REQUIRES_CLASSIFIER);
		}
		if(a.getResult() == null){
			getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Output Pin", "Result");
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitExpansionRegion(INakedExpansionRegion a){
		if(a.getInputElement().size() != 1){
			getErrorMap().putError(a, ActionValidationRule.EXPANSION_REGION_REQUIRES_INPUT_ELEMENT);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitExpansionNode(INakedExpansionNode a){
		if(!(a.isInputElement() || a.isOutputElement())){
			getErrorMap().putError(a, ActionValidationRule.EXPANSION_NODE_REQUIRES_EXPANSION_REGION);
		}
	}
}
