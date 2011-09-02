package net.sf.nakeduml.validation;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.actions.INakedReadStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;

@StepDependency(phase = ValidationPhase.class)
public class ActionValidator extends AbstractValidator{
	@VisitBefore(matchSubclasses=true)
	public void visitStructuralFeatureAction(INakedStructuralFeatureAction a){
		if(a.getFeature() == null){
			getErrorMap().putError(a, ActionValidationRule.FEATURE_ACTION_REQUIRES_FEATURE);
		}
		if(a instanceof INakedReadStructuralFeatureAction && ((INakedReadStructuralFeatureAction) a).getResult()==null){
			getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN,"Output Pin", "Result");
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitVariableAction(INakedVariableAction a){
		if(a.getVariable() == null){
			getErrorMap().putError(a, ActionValidationRule.VARIABLE_ACTION_REQUIRES_FEATURE);
		}
		if(a instanceof INakedReadVariableAction && ((INakedReadVariableAction) a).getResult()==null){
			getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN,"Output Pin", "Result");
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitSendSignalAction(INakedSendSignalAction a){
		if(a.getSignal() == null){
			getErrorMap().putError(a, ActionValidationRule.SEND_SIGNAL_ACTION_REQUIRES_SIGNAL);
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitCallBehaviorAction(INakedCallBehaviorAction a){
		if(a.getBehavior() == null){
			getErrorMap().putError(a, ActionValidationRule.CALL_BEHAVIOR_ACTION_REQUIRES_BEHAVIOR);
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitCallOperationAction(INakedCallOperationAction a){
		if(a.getOperation() == null){
			getErrorMap().putError(a, ActionValidationRule.CALL_OPERATION_ACTION_REQUIRES_OPERATION);
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitCreateObjectAction(INakedCreateObjectAction a){
		if(a.getClassifier() == null){
			getErrorMap().putError(a, ActionValidationRule.CREATE_OBJECT_ACTION_REQUIRES_CLASSIFIER);
		}
		if(a.getResult()==null){
			getErrorMap().putError(a, ActionValidationRule.REQUIRED_PIN,"Output Pin", "Result");
		}

	}
	@VisitBefore(matchSubclasses=true)
	public void visitExpansionRegion(INakedExpansionRegion a){
		if(a.getInputElement().size()!=1){
			getErrorMap().putError(a, ActionValidationRule.EXPANSION_REGION_REQUIRES_INPUT_ELEMENT);
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitExpansionNode(INakedExpansionNode a){
		if(!(a.isInputElement()||a.isOutputElement())){
			getErrorMap().putError(a, ActionValidationRule.EXPANSION_NODE_REQUIRES_EXPANSION_REGION);
		}
	}

}
