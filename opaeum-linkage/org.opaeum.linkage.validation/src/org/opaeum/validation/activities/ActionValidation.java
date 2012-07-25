package org.opaeum.validation.activities;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StructuralFeatureAction;
import org.eclipse.uml2.uml.VariableAction;
import org.eclipse.uml2.uml.WriteStructuralFeatureAction;
import org.eclipse.uml2.uml.WriteVariableAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
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
				}else if(!rsfa.getResult().canAcceptInputFrom(rsfa.getStructuralFeature())){
					getErrorMap().putError(rsfa.getResult(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Property", rsfa.getStructuralFeature());
				}
			}
			if(a instanceof WriteStructuralFeatureAction){
				WriteStructuralFeatureAction wsfa = (WriteStructuralFeatureAction) a;
				if(wsfa.getValue() == null){
					getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Input Pin", "Value");
				}else if(!wsfa.getValue().canDeliverOutputTo(wsfa.getStructuralFeature())){
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
			}else if(!rva.getResult().canAcceptInputFrom(rva.getVariable())){
				getErrorMap().putError(rva.getResult(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Variable", rva.getVariable());
			}
		}
		if(a instanceof WriteVariableAction){
			WriteVariableAction wva = (WriteVariableAction) a;
			if(wva.getValue() == null){
				getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN, "Input Pin", "Value");
			}else if(!wva.getValue().canDeliverOutputTo(wva.getVariable())){
				getErrorMap().putError(wva.getValue(), ActionValidationRule.REQUIRED_MULTIPLICITY, "Variable", wva.getVariable());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitSendSignalAction(SendSignalAction a){
		if(a.getSignal() == null){
			getErrorMap().putError(a, ActionValidationRule.SEND_SIGNAL_ACTION_REQUIRES_SIGNAL);
		}
		if(EmfActionUtil.getTargetElement( a) != null){
			if(EmfActionUtil.getTargetType(a) instanceof BehavioredClassifier){
				BehavioredClassifier bc = (BehavioredClassifier) EmfActionUtil.getTargetType(a);
				if(a.getSignal() != null && !bc.hasReceptionOrTriggerFor(a.getSignal())){
					getErrorMap().putError(EmfActionUtil.getTargetElement( a), ActionValidationRule.SEND_SIGNAL_TARGET_MUST_RECEIVE_SIGNAL, a, EmfActionUtil.getTargetType(a),
							a.getSignal());
				}
			}else if(EmfActionUtil.getTargetType(a) instanceof Interface){
				Interface i = (Interface) EmfActionUtil.getTargetType(a);
				if(a.getSignal() != null && !i.hasReceptionFor(a.getSignal())){
					getErrorMap().putError(EmfActionUtil.getTargetElement( a), ActionValidationRule.SEND_SIGNAL_TARGET_MUST_RECEIVE_SIGNAL, a, EmfActionUtil.getTargetType(a),
							a.getSignal());
				}
			}else{
				getErrorMap().putError(EmfActionUtil.getTargetElement( a), ActionValidationRule.SEND_SIGNAL_REQUIRES_BEHAVIORED_CLASSIFIER_TARGET, a);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallBehaviorAction(CallBehaviorAction a){
		if(a.getBehavior() == null){
			getErrorMap().putError(a, ActionValidationRule.CALL_BEHAVIOR_ACTION_REQUIRES_BEHAVIOR);
		}else{
			if(!a.getActivity().getEffectiveBehaviors().contains(a.getBehavior())){
				if(a.getActivity().getContext() != null){
					if(!a.getActivity().getContext().getEffectiveBehaviors().contains(a.getBehavior())){
						if(a.getInPartitions().isEmpty()
								|| !(a.getInPartition().getType() instanceof BehavioredClassifier && ((BehavioredClassifier) a.getInPartition()
										.getType()).getEffectiveBehaviors().contains(a.getBehavior())))
							getErrorMap().putError(a, ActionValidationRule.CALL_BEHAVIOR_ACTION_BEHAVIOR_IN_CONTEXT, a.getBehavior(), a.getActivity(),
									a.getActivity().getContext());
					}
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitCallOperationAction(CallOperationAction a){
		if(a.getOperation() == null){
			getErrorMap().putError(a, ActionValidationRule.CALL_OPERATION_ACTION_REQUIRES_OPERATION);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitAceptCallAction(AcceptCallAction a){
		if(a.getTriggers().size() == 0 || !(a.getTriggers().iterator().next().getEvent() instanceof CallEvent)
				|| ((CallEvent) a.getTriggers().iterator().next().getEvent()).getOperation() == null){
			getErrorMap().putError(a, ActionValidationRule.ACCEPT_CALL_REQUIRES_SINGLE_CALL_EVENT);
		}
		if(a.getReturnInformation() == null || EmfActionUtil.getReplyAction( a) == null){
			getErrorMap().putError(a, ActionValidationRule.ACCEPT_CALL_RETURN_INFO_MUST_LINK);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitReplyAction(ReplyAction ra){
		if(ra.getReturnInformation() == null || EmfActionUtil.getCause( ra) == null){
			getErrorMap().putError(ra, ActionValidationRule.REPLY_ACTION_RETURN_INFO_MUST_LINK);
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
		if(!(a.getRegionAsInput()!=null || a.getRegionAsOutput()!=null)){
			getErrorMap().putError(a, ActionValidationRule.EXPANSION_NODE_REQUIRES_EXPANSION_REGION);
		}
	}
}
