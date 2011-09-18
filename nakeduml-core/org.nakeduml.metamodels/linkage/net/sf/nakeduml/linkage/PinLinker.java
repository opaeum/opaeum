package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.IActionWithTargetPin;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.actions.INakedReadStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedWriteStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedWriteVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.bpm.INakedAcceptDeadlineAction;
import net.sf.nakeduml.metamodel.bpm.internal.NakedAcceptTaskEventActionImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.validation.activities.ActivityValidationRule;

@StepDependency(phase = LinkagePhase.class,after = {
		MappedTypeLinker.class,ParameterLinker.class
},before = {
	ObjectFlowLinker.class
},requires = {
		MappedTypeLinker.class,ObjectFlowLinker.class,ParameterLinker.class
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
			INakedMultiplicity nakedMultiplicity = pin.getNakedMultiplicity();
			INakedMultiplicity nakedMultiplicity2 = typedElement.getNakedMultiplicity();
			if(nakedMultiplicity.getUpper() < nakedMultiplicity2.getUpper()){
				pin.setMultiplicity(nakedMultiplicity2);
				pin.setIsUnique(typedElement.isUnique());
				pin.setIsOrdered(typedElement.isOrdered());
			}
		}
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
				time.setBaseType(workspace.getNakedUmlLibrary().getDateType());
			}
			time.setType(time.getNakedBaseType());
		}else if(action instanceof NakedAcceptTaskEventActionImpl && result.size() >= 1){
			INakedOutputPin by = result.get(0);
			if(by.getNakedBaseType() == null){
				by.setBaseType(workspace.getNakedUmlLibrary().getBusinessRole());
				by.setType(workspace.getNakedUmlLibrary().getBusinessRole());
			}
			if(result.size() >= 2){
				INakedOutputPin to = result.get(1);
				if(by.getNakedBaseType() == null){
					to.setBaseType(workspace.getNakedUmlLibrary().getBusinessRole());
					to.setType(workspace.getNakedUmlLibrary().getBusinessRole());
				}
				if(result.size() >= 3){
					INakedOutputPin task = result.get(2);
					task.setBaseType(workspace.getNakedUmlLibrary().getTaskRequest());
					task.setType(workspace.getNakedUmlLibrary().getTaskRequest());
				}
			}
		}else if(action instanceof INakedAcceptDeadlineAction && result.size() >= 1){
			INakedOutputPin by = result.get(0);
			if(by.getNakedBaseType() == null){
				by.setBaseType(workspace.getNakedUmlLibrary().getDateType());
				by.setType(workspace.getNakedUmlLibrary().getDateType());
			}
			if(result.size() >= 2){
				INakedOutputPin task = result.get(1);
				if(by.getNakedBaseType() == null){
					task.setBaseType(workspace.getNakedUmlLibrary().getTaskRequest());
					task.setType(workspace.getNakedUmlLibrary().getTaskRequest());
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
