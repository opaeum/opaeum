package org.opaeum.eclipse;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InvocationAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.StructuralFeatureAction;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.WriteStructuralFeatureAction;
import org.eclipse.uml2.uml.WriteVariableAction;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.TaskDelegation;

public class EmfActionUtil{
	public static InputPin getLogicalTarget(Action a){
		if(a instanceof SendSignalAction){
			return ((SendSignalAction) a).getTarget();
		}else if(a instanceof CallOperationAction){
			return ((CallOperationAction) a).getTarget();
		}else if(a instanceof StartClassifierBehaviorAction){
			return ((StartClassifierBehaviorAction) a).getObject();
		}else if(a instanceof StructuralFeatureAction){
			return ((StructuralFeatureAction) a).getObject();
		}else{
			return null;
		}
	}
	public static NamedElement getTargetElement(Action a){
		InputPin pin=getLogicalTarget(a);
		if(pin != null){
			return pin;
		}else if(a.getInPartitions().size() == 1){
			return (NamedElement) a.getInPartitions().get(0).getRepresents();
		}else{
			return null;
		}
	}
	public static Classifier getTargetType(Action a){
		NamedElement te = getTargetElement(a);
		if(te instanceof Classifier){
			return (Classifier) te;
		}else if(te instanceof TypedElement){
			return (Classifier) ((TypedElement) te).getType();
		}
		return null;
	}
	public static List<OutputPin> getExceptionPins(Action a){
		List<OutputPin> exceptionPins = new ArrayList<OutputPin>();
		for(OutputPin pin:a.getOutputs()){
			TypedElement lte = getLinkedTypedElement(pin);
			if(lte instanceof Parameter){
				Parameter p = (Parameter) lte;
				if(p.isException() && p.getDirection() == ParameterDirectionKind.OUT_LITERAL){
					exceptionPins.add(pin);
				}
			}
		}
		return exceptionPins;
	}
	public static boolean isExceptionPin(Pin pin){
		TypedElement te = getLinkedTypedElement(pin);
		if(te instanceof Parameter){
			return ((Parameter) te).isException();
		}else{
			return false;
		}
	}
	public static TypedElement getLinkedTypedElement(Pin pin){
		Action action = (Action) pin.getOwner();
		if(pin.eContainingFeature() == UMLPackage.eINSTANCE.getWriteStructuralFeatureAction_Value()){
			return ((WriteStructuralFeatureAction) action).getStructuralFeature();
		}else if(pin.eContainingFeature() == UMLPackage.eINSTANCE.getWriteStructuralFeatureAction_Result()){
			return ((WriteStructuralFeatureAction) action).getStructuralFeature();
		}else if(pin.eContainingFeature() == UMLPackage.eINSTANCE.getReadStructuralFeatureAction_Result()){
			return ((WriteStructuralFeatureAction) action).getStructuralFeature();
		}else if(pin.eContainingFeature() == UMLPackage.eINSTANCE.getWriteVariableAction_Value()){
			return ((WriteVariableAction) action).getVariable();
		}else if(pin.eContainingFeature() == UMLPackage.eINSTANCE.getReadVariableAction_Result()){
			return ((ReadVariableAction) action).getVariable();
		}else if(pin.eContainingFeature() == UMLPackage.eINSTANCE.getInvocationAction_Argument()){
			List<? extends TypedElement> typedElements = null;
			List<InputPin> args = ((InvocationAction) action).getArguments();
			if(action instanceof SendSignalAction){
				typedElements = EmfElementFinder.getTypedElementsInScope(((SendSignalAction) action).getSignal());
			}else if(action instanceof CallBehaviorAction){
				typedElements = EmfBehaviorUtil.getArgumentParameters(((CallBehaviorAction) action).getBehavior());
			}else if(action instanceof CallOperationAction){
				typedElements = EmfBehaviorUtil.getArgumentParameters(((CallOperationAction) action).getOperation());
			}
			if(typedElements.size() == args.size()){
				return typedElements.get(args.indexOf(pin));
			}else{
				return null;
			}
		}else if(pin.eContainingFeature() == UMLPackage.eINSTANCE.getCallAction_Result()){
			List<? extends TypedElement> typedElements = null;
			if(action instanceof CallBehaviorAction){
				typedElements = EmfBehaviorUtil.getResultParameters(((CallBehaviorAction) action).getBehavior());
			}else{
				typedElements = EmfBehaviorUtil.getResultParameters(((CallOperationAction) action).getOperation());
			}
			List<OutputPin> result = ((CallAction) action).getResults();
			if(typedElements.size() == result.size()){
				return typedElements.get(result.indexOf(pin));
			}else{
				return null;
			}
		}else if(action instanceof AcceptCallAction && pin.eContainingFeature() == UMLPackage.eINSTANCE.getAcceptEventAction_Result()){
			List<? extends TypedElement> typedElements = null;
			AcceptCallAction acceptAction = (AcceptCallAction) action;
			typedElements = EmfBehaviorUtil.getResultParameters(getOperation(acceptAction));
			List<OutputPin> result = acceptAction.getResults();
			if(typedElements.size() == result.size()){
				return typedElements.get(result.indexOf(pin));
			}else{
				return null;
			}
		}
	}
	public static OutputPin getReturnPin(Action a){
		Parameter p = null;
		if(a instanceof CallBehaviorAction){
			p = EmfBehaviorUtil.getReturnParameter(((CallBehaviorAction) a).getBehavior());
		}else if(a instanceof CallOperationAction){
			p = EmfBehaviorUtil.getReturnParameter(((CallOperationAction) a).getOperation());
		}
		EList<OutputPin> outputs = a.getOutputs();
		for(OutputPin outputPin:outputs){
			if(getLinkedTypedElement(outputPin) == p){
				return outputPin;
			}
		}
		if(outputs.size() == 1){
			return outputs.get(0);
		}else{
			return null;
		}
	}
	public static boolean isEmbeddedTask(ActivityNode n){
		return isSingleScreenTask(n) || isSingleScreenTask(n);
	}
	public static boolean isSingleScreenTask(ActivityNode n){
		return StereotypesHelper.hasStereotype(n, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
	}
	public static boolean isScreenFlowTask(ActivityNode n){
		return StereotypesHelper.hasStereotype(n, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
	}
	public static Operation getOperation(AcceptCallAction action){
		EList<Trigger> triggers = action.getTriggers();
		for(Trigger trigger:triggers){
			if(trigger.getEvent() instanceof CallEvent){
				return ((CallEvent) trigger.getEvent()).getOperation();
			}
		}
		return null;
	}
	public static boolean hasMessageStructure(Action action){
		if(isEmbeddedTask(action)){
			return true;
		}else if(action instanceof CallBehaviorAction){
			return EmfBehaviorUtil.hasExecutionInstance(((CallBehaviorAction) action).getBehavior());
		}else if(action instanceof CallOperationAction){
			return EmfBehaviorUtil.hasExecutionInstance(((CallOperationAction) action).getOperation());
		}else if(action instanceof AcceptCallAction){
			return EmfBehaviorUtil.hasExecutionInstance(getOperation((AcceptCallAction) action));
		}else{
			return false;
		}
	}
	public static ResponsibilityDefinition getTaskDefinition(final Element node,String embeddedScreenFlowTask){
		final Stereotype stereotype = StereotypesHelper.getStereotype(node, embeddedScreenFlowTask);
		return new ResponsibilityDefinition(){
			@Override
			public OpaqueExpression getPotentialStakeholders(){
				return (OpaqueExpression) node.getValue(stereotype, "potentialStakeholders");
			}
			@Override
			public OpaqueExpression getPotentialOwners(){
				return (OpaqueExpression) node.getValue(stereotype, "potentialOwners");
			}
			@Override
			public OpaqueExpression getPotentialBusinessAdministrators(){
				return (OpaqueExpression) node.getValue(stereotype, "potentialBusinessAdministrators");
			}
			@Override
			public Classifier getExpressionContext(){
				// TODO Auto-generated method stub
				return null;
			}
			public Collection<Deadline> getDeadlines(){
				return null;
			}
			@Override
			public TaskDelegation getDelegation(){
				Object value = node.getValue(stereotype, "taskDelegation");
				if(value instanceof EnumerationLiteral){
					return TaskDelegation.valueOf(((EnumerationLiteral) value).getName().toUpperCase());
				}
				return null;
			}
		};
	}
	public static boolean hasExceptions(Action action){
		return getExceptionPins(action).size() > 0 || action.getHandlers().size() > 0;
	}
	public static boolean isLongRunning(Action node){
		if(isEmbeddedTask(node)){
			return true;
		}else if(node instanceof CallBehaviorAction){
			return EmfBehaviorUtil.isLongRunning(((CallBehaviorAction) node).getBehavior());
		}else if(node instanceof CallOperationAction){
			return EmfBehaviorUtil.isLongRunning(((CallOperationAction) node).getOperation());
		}else{
			return node instanceof AcceptEventAction;
		}
	}
	public static boolean isSynchronous(Action node){
		if(node instanceof CallAction){
			return ((CallAction) node).isSynchronous();
		}else if(isSingleScreenTask(node)){
			Stereotype st = StereotypesHelper.getStereotype(node, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
			return Boolean.TRUE.equals(node.getValue(st, "isSynchronous"));
		}
		return true;
	}
	public static AcceptCallAction getCause(ReplyAction node){
		if(node.getReturnInformation()!=null){
			ObjectNode feedingNode = EmfActivityUtil.getFeedingNode(node.getReturnInformation());
			if(feedingNode!=null && feedingNode.getOwner() instanceof AcceptCallAction){
				return (AcceptCallAction) feedingNode.getOwner();
			}
		}
		return null;
	}

}
