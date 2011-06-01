package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.actions.IActionWithTargetPin;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.actions.INakedExceptionHandler;
import net.sf.nakeduml.metamodel.actions.INakedReadStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedWriteStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedWriteVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
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
			if(pin.getNakedMultiplicity().getUpper() < typedElement.getNakedMultiplicity().getUpper()){
				pin.setMultiplicity(typedElement.getNakedMultiplicity());
				pin.setIsUnique(typedElement.isUnique());
				pin.setIsOrdered(typedElement.isOrdered());
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkAcceptEvent(INakedAcceptEventAction action){
		if(action.getTrigger() != null && action.getTrigger().getEvent() instanceof INakedSignal){
			INakedSignal signal = (INakedSignal) action.getTrigger().getEvent();
			linkByNameIfRequired(signal, signal.getArgumentParameters(), action.getResult());
		}else{
			List<INakedTypedElement> args = action.getParameters();
			for(int i = 0;i < args.size();i++){
				linkTypedElement(action.getResult().get(i), args.get(i));
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkSignal(INakedSendSignalAction action){
		if(action.getSignal() != null){
			linkByNameIfRequired(action.getSignal(), action.getSignal().getArgumentParameters(), action.getArguments());
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
	private void linkByNameIfRequired(INakedSignal signal,List<INakedProperty> formalArgs,List<? extends INakedPin> actualArgs){
		if(formalArgs.size() != actualArgs.size()){
			for(INakedPin ip:actualArgs){
				linkTypedElement(ip, signal.findEffectiveAttribute(ip.getName()));
			}
		}else{
			for(int i = 0;i < formalArgs.size();i++){
				linkTypedElement(actualArgs.get(i), formalArgs.get(i));
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEdge(INakedActivityEdge edge){
		if(edge.getOwnerElement() != edge.getEffectiveSource().getOwnerElement()){
			edge.getOwnerElement().removeOwnedElement(edge);
			edge.getEffectiveSource().getOwnerElement().addOwnedElement(edge);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkCallAction(INakedCallAction action){
		if(action.getCalledElement() != null){
			if(action.getArguments().size() > action.getCalledElement().getArgumentParameters().size()){
				getErrorMap().putError(action, ActivityValidationRule.MORE_PINS_THAN_PARAMETERS, action.getCalledElement().getName());
			}else{
				for(INakedParameter p1:action.getCalledElement().getArgumentParameters()){
					if(action.getArguments().size() < p1.getArgumentIndex() + 1){
						getErrorMap().putError(action, ActivityValidationRule.PIN_FOR_PARAMETER,
								"Parameter " + p1.getName() + " of " + action.getCalledElement().getName());
					}else{
						linkTypedElement(action.getArguments().get(p1.getArgumentIndex()), p1);
					}
				}
			}
			if(action.getResult().size() > action.getCalledElement().getResultParameters().size()){
				getErrorMap().putError(action, ActivityValidationRule.MORE_PINS_THAN_PARAMETERS, action.getCalledElement().getName());
			}else{
				for(INakedParameter p:action.getCalledElement().getResultParameters()){
					if(action.getResult().size() < p.getResultIndex() + 1){
						getErrorMap().putError(action, ActivityValidationRule.PIN_FOR_PARAMETER,
								"Parameter " + p.getName() + " of " + action.getCalledElement().getName());
					}else{
						linkTypedElement(action.getResult().get(p.getResultIndex()), p);
					}
				}
			}
		}else{
			// TODO add error
		}
	}
}
