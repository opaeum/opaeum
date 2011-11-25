package org.opaeum.linkage;

import java.util.List;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.IActionWithTargetPin;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.actions.INakedCreateObjectAction;
import org.opaeum.metamodel.actions.INakedExceptionHandler;
import org.opaeum.metamodel.actions.INakedReadStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedReadVariableAction;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.actions.INakedSendSignalAction;
import org.opaeum.metamodel.actions.INakedWriteStructuralFeatureAction;
import org.opaeum.metamodel.actions.INakedWriteVariableAction;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.bpm.INakedAcceptDeadlineAction;
import org.opaeum.metamodel.bpm.internal.NakedAcceptTaskEventActionImpl;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedMultiplicity;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedTypedElement;

@StepDependency(phase = LinkagePhase.class,after = {
	ParameterLinker.class
},before = {},requires = {
	ParameterLinker.class
})
public class PinLinker extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void linkTarget(IActionWithTargetPin action){
		if(action.getExpectedTargetType() != null && action.getTarget() != null && action.getTarget().getNakedBaseType() == null){
			action.getTarget().setBaseType(action.getExpectedTargetType());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkResult(INakedCreateObjectAction action){
		if(action.getClassifier() != null && action.getResult() != null){
			action.getResult().setBaseType(action.getClassifier());
		}
	}
	@VisitBefore
	public void linkExceptionInput(INakedExceptionHandler h){
		if(h.getExceptionTypes().size() == 1 && h.getExceptionInput() != null){
			INakedClassifier type = h.getExceptionTypes().iterator().next();
			h.getExceptionInput().setBaseType(type);
			h.getExceptionInput().setType(type);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkStructuralFeature(INakedReadStructuralFeatureAction action){
		linkTypedElement(action.getResult(), action.getFeature());
	}
	@VisitBefore(matchSubclasses = true)
	public void linkStructuralFeature(INakedWriteStructuralFeatureAction action){
		linkTypedElement(action.getValue(), action.getFeature());
	}
	@VisitBefore
	public void linkgReadVariableAction(INakedReadVariableAction action){
		linkTypedElement(action.getResult(), action.getVariable());
	}
	@VisitBefore(matchSubclasses = true)
	public void linkWriteVariableAction(INakedWriteVariableAction action){
		linkTypedElement(action.getValue(), action.getVariable());
	}
	private void linkTypedElement(INakedPin pin,INakedTypedElement typedElement){
		if(pin != null && typedElement != null){
			pin.setLinkedTypedElement(typedElement);
			if(pin instanceof INakedInputPin && typedElement.getNakedMultiplicity().getUpper() < pin.getNakedMultiplicity().getUpper()){
				// TODO replace this with a validation
				pin.setMultiplicity(typedElement.getNakedMultiplicity());
				pin.setIsUnique(typedElement.isUnique());
				pin.setIsOrdered(typedElement.isOrdered());
			}else if(pin instanceof INakedOutputPin && typedElement.getNakedMultiplicity().getUpper() > pin.getNakedMultiplicity().getUpper()){
				// TODO replace this with a validation
				pin.setMultiplicity(typedElement.getNakedMultiplicity());
				pin.setIsUnique(typedElement.isUnique());
				pin.setIsOrdered(typedElement.isOrdered());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkReplyAction(INakedReplyAction action){
		linkLists(action.getCause().getOperation().getResultParameters(), action.getReplyValues());
	}
	@VisitBefore(matchSubclasses = true)
	public void linkAcceptEvent(INakedAcceptEventAction action){
		List<? extends INakedTypedElement> parameters = action.getParameters();
		List<INakedOutputPin> result = action.getResult();
		if(parameters.size() > 0){
			INakedElement element = action.getTriggers().iterator().next().getEvent();
			if(result.size() != parameters.size()){
				getErrorMap().putError(action, ActivityValidationRule.MORE_PINS_THAN_PARAMETERS, element.getName());
			}else{
				linkLists(parameters, result);
			}
		}
		if(action.triggeredByTimeEventsOnly() && result.size() == 1){
			INakedOutputPin time = result.get(0);
			if(time.getNakedBaseType() == null){
				time.setBaseType(workspace.getOpaeumLibrary().getDateType());
			}
			time.setType(time.getNakedBaseType());
		}else if(action instanceof NakedAcceptTaskEventActionImpl && result.size() >= 1){
			INakedOutputPin by = result.get(0);
			if(by.getNakedBaseType() == null){
				by.setBaseType(workspace.getOpaeumLibrary().getBusinessRole());
				by.setType(workspace.getOpaeumLibrary().getBusinessRole());
			}
			if(result.size() >= 2){
				INakedOutputPin to = result.get(1);
				if(by.getNakedBaseType() == null){
					to.setBaseType(workspace.getOpaeumLibrary().getBusinessRole());
					to.setType(workspace.getOpaeumLibrary().getBusinessRole());
				}
				if(result.size() >= 3){
					INakedOutputPin task = result.get(2);
					task.setBaseType(workspace.getOpaeumLibrary().getTaskRequest());
					task.setType(workspace.getOpaeumLibrary().getTaskRequest());
				}
			}
		}else if(action instanceof INakedAcceptDeadlineAction && result.size() >= 1){
			INakedOutputPin by = result.get(0);
			if(by.getNakedBaseType() == null){
				by.setBaseType(workspace.getOpaeumLibrary().getDateType());
				by.setType(workspace.getOpaeumLibrary().getDateType());
			}
			if(result.size() >= 2){
				INakedOutputPin task = result.get(1);
				if(by.getNakedBaseType() == null){
					task.setBaseType(workspace.getOpaeumLibrary().getTaskRequest());
					task.setType(workspace.getOpaeumLibrary().getTaskRequest());
				}
			}
		}
	}
	private void linkLists(List<? extends INakedTypedElement> parameters,List<? extends INakedPin> result){
		for(int i = 0;i < parameters.size();i++){
			linkTypedElement(result.get(i), parameters.get(i));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkSignal(INakedSendSignalAction action){
		if(action.getSignal() != null){
			if(action.getArguments().size() != action.getSignal().getEffectiveAttributes().size()){
				getErrorMap().putError(action, ActivityValidationRule.MORE_PINS_THAN_PARAMETERS, action.getSignal().getName());
			}else{
				linkLists(action.getSignal().getEffectiveAttributes(), action.getArguments());
			}
		}
	}
	@VisitAfter(matchSubclasses = false)
	public void link(INakedInputPin inputPin){
		// will primarily be for cases where there is no linkedTypedElement or
		// given baseType, i.e. opaqueActions
		if(inputPin.getIncoming().size() == 1){
			INakedActivityEdge in = inputPin.getIncoming().iterator().next();
			if(in instanceof INakedObjectFlow){
				INakedObjectFlow oin = (INakedObjectFlow) in;
				if(oin.getTransformation() == null){
					if(oin.getSource() instanceof INakedOutputPin){
						INakedOutputPin source = (INakedOutputPin) oin.getSource();
						if(inputPin.getNakedBaseType() == null){
							inputPin.setBaseType(source.getNakedBaseType());
						}
						if(oin.getSelection() == null && source.getNakedMultiplicity().getUpper() > inputPin.getNakedMultiplicity().getUpper()){
							inputPin.setMultiplicity(source.getNakedMultiplicity());
						}
					}
				}else{
					inputPin.setBaseType(oin.getTransformation().getReturnParameter().getNakedBaseType());
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEdge(INakedActivityEdge edge){
		final INakedActivityNode effectiveSource = edge.getEffectiveSource();
		final INakedElementOwner ownerElement = effectiveSource.getOwnerElement();
		if(edge.getOwnerElement() != ownerElement){
			edge.getOwnerElement().removeOwnedElement(edge, false);
			ownerElement.addOwnedElement(edge);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkCallAction(INakedCallAction action){
		if(action.getCalledElement() != null){
			List<? extends INakedParameter> argumentParameters = action.getCalledElement().getArgumentParameters();
			if(action.getArguments().size() != argumentParameters.size()){
				getErrorMap().putError(action, ActivityValidationRule.MORE_PINS_THAN_PARAMETERS, action.getCalledElement().getName());
			}else{
				linkLists(argumentParameters, action.getArguments());
			}
			List<? extends INakedParameter> resultParameters = action.getCalledElement().getResultParameters();
			if(action.getResult().size() != resultParameters.size()){
				getErrorMap().putError(action, ActivityValidationRule.MORE_PINS_THAN_PARAMETERS, action.getCalledElement().getName());
			}else{
				linkLists(resultParameters, action.getResult());
			}
		}
	}
}
