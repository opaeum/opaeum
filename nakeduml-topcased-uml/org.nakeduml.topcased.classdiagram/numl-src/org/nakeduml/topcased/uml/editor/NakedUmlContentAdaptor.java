package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InvocationAction;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.nakeduml.eclipse.ApplyProfileAction;
import org.nakeduml.eclipse.EmfParameterUtil;
import org.nakeduml.eclipse.ImportLibraryAction;

public class NakedUmlContentAdaptor extends EContentAdapter{
	
	private final class EmfUmlElementLinker extends UMLSwitch<EObject>{
		private Notification notification;
		private EmfUmlElementLinker(Notification not){
			notification = not;
		}
		public EObject caseStructuredActivityNode(StructuredActivityNode a){
			switch(this.notification.getFeatureID(Activity.class)){
			case UMLPackage.STRUCTURED_ACTIVITY_NODE__CONTAINED_NODE:
				ensureActivityNodeStereotypes(a);
				break;
			}
			return null;
		}
		public EObject caseActivity(Activity a){
			switch(this.notification.getFeatureID(Activity.class)){
			case UMLPackage.ACTIVITY__NODE:
				ensureActivityNodeStereotypes(a);
				break;
			}
			return null;
		}
		public EObject caseAction(Action a){
			switch(notification.getFeatureID(Action.class)){
			case UMLPackage.ACTION__INPUT:
				InputPin inputPin = (InputPin) notification.getNewValue();
				if(inputPin.getUpperBound() == null){
					LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
					upperBound.setValue(-1);
					inputPin.setUpperBound(upperBound);
				}
				break;
			case UMLPackage.ACTION__OUTPUT:
				OutputPin outputPin = (OutputPin) notification.getNewValue();
				if(outputPin.getUpperBound() == null){
					LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
					upperBound.setValue(-1);
					outputPin.setUpperBound(upperBound);
				}
				break;
			}
			return null;
		}
		private void ensureActivityNodeStereotypes(Element a){
			switch(notification.getEventType()){
			case Notification.ADD:
				ActivityNode newValue = (ActivityNode) notification.getNewValue();
				if(newValue instanceof AcceptEventAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.ACCEPT_DEADLINE_ACTION, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}else if(newValue instanceof OpaqueAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}else if(newValue instanceof CallBehaviorAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}else if(newValue instanceof SendSignalAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.SEND_NOTIFICATION_ACTION, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}else if(newValue instanceof AcceptCallAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.ACCEPT_TASK_EVENT_ACTION, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}
			}
		}
		public EObject caseBehavior(Behavior behavior){
			switch(notification.getFeatureID(Operation.class)){
			case UMLPackage.BEHAVIOR__OWNED_PARAMETER:
				switch(notification.getEventType()){
				case Notification.ADD:
					Parameter newValue = (Parameter) notification.getNewValue();
					newValue.setDirection(ParameterDirectionKind.IN_LITERAL);
					for(EObject e:StereotypesHelper.getNumlAnnotation(behavior).getReferences()){
						if(e instanceof CallBehaviorAction){
							addArgument(newValue, (CallAction) e);
						}
					}
					break;
				case Notification.REMOVE:
					Parameter oldValue = (Parameter) notification.getOldValue();
					removeReferencingPins(oldValue);
					break;
				}
				break;
			case UMLPackage.BEHAVIOR__SPECIFICATION:
				Operation oper = (Operation) notification.getNewValue();
				if(notification.getNewValue() instanceof Operation){
					synchronizeBehaviorParameters(behavior, oper);
				}else if(notification.getNewValue()==null){
					for(Parameter parameter:oper.getOwnedParameters()){
						EList<EObject> references = StereotypesHelper.getNumlAnnotation(parameter).getReferences();
						references.removeAll(behavior.getOwnedParameters());
					}
				}
				break;
			}
			return null;
		}
		private void synchronizeBehaviorParameters(Behavior behavior,Operation oper){
			EList<Parameter> operParams = oper.getOwnedParameters();
			for(int i = 0;i < operParams.size();i++){
				Parameter p = operParams.get(i);
				EList<EObject> references = StereotypesHelper.getNumlAnnotation(p).getReferences();
				if(behavior.getOwnedParameters().size() > i){
					Parameter bp = behavior.getOwnedParameters().get(i);
					bp.setName(p.getName());
					bp.setType(p.getType());
					bp.setDirection(p.getDirection());
					if(!references.contains(bp)){
						references.add(bp);
					}
				}else{
					Parameter bp = behavior.createOwnedParameter(p.getName(), p.getType());
					bp.setDirection(p.getDirection());
					references.add(bp);
				}
			}
			while(behavior.getOwnedParameters().size() > operParams.size()){
				behavior.getOwnedParameters().remove(behavior.getOwnedParameters().size() - 1);
			}
		}
		public EObject casePackage(Package p){
			p.getName();
			switch(notification.getEventType()){
			case Notification.ADD:
				if(notification.getNewValue() instanceof Association){
					Association ass = (Association) notification.getNewValue();
					for(Property property:ass.getMemberEnds()){
						if(property.getOtherEnd().isNavigable() && property.getType() instanceof Enumeration){
							for(EnumerationLiteral e:((Enumeration) property.getType()).getOwnedLiterals()){
								createSlot(e, property.getOtherEnd());
							}
						}
					}
				}
				if(notification.getNewValue() instanceof Signal){
					applyStereotypeIfNecessary(p, (Element) notification.getNewValue(), StereotypeNames.NOTIFICATION, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}
				if(notification.getNewValue() instanceof Interface){
					Interface intf = (Interface) notification.getNewValue();
					applyStereotypeIfNecessary(p, intf, StereotypeNames.BUSINESS_SERVICE, StereotypeNames.NAKEDUML_BPM_PROFILE);
					applyStereotypeIfNecessary(p, intf, StereotypeNames.HELPER, StereotypeNames.NAKEDUML_PROFILE);
				}
				if(notification.getNewValue() instanceof Component){
					applyStereotypeIfNecessary(p, (Element) notification.getNewValue(), StereotypeNames.BUSINESS_COMPONENT, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}
				if(notification.getNewValue() instanceof org.eclipse.uml2.uml.Class){
					applyStereotypeIfNecessary(p, (Element) notification.getNewValue(), StereotypeNames.BUSINESS_ROLE, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}
				if(notification.getNewValue() instanceof TimeEvent){
					applyRelativeTimeEventStereotype((TimeEvent) notification.getNewValue(), p);
				}
			}
			return null;
		}
		public EObject caseSlot(Slot v){
			switch(notification.getFeatureID(Slot.class)){
			case UMLPackage.SLOT__DEFINING_FEATURE:
				Property p = (Property) notification.getOldValue();
				if(p != null && p.eContainer() == null && notification.getNewValue() == null){
					v.getOwningInstance().getSlots().remove(v);
				}
			}
			return null;
		}
		public EObject caseGeneralization(Generalization g){
			switch(notification.getFeatureID(Generalization.class)){
			case UMLPackage.GENERALIZATION__GENERAL:
				if(notification.getNewValue() instanceof Signal){
					for(Property p:EmfParameterUtil.getArguments((Signal) g.getGeneral())){
						addPinForSignalActions(p, (Signal) g.getSpecific());
					}
				}
				break;
			}
			return null;
		}
		public EObject caseSignal(Signal s){
			switch(notification.getFeatureID(Signal.class)){
			case UMLPackage.CLASSIFIER__GENERALIZATION:
				switch(notification.getEventType()){
				case Notification.ADD:
					// Nothing - general is null
					break;
				case Notification.REMOVE:
					Generalization oldG = (Generalization) notification.getOldValue();
					for(Property p:EmfParameterUtil.getArguments((Signal) oldG.getGeneral())){
						removeReferencingPins(p);
					}
					break;
				}
				break;
			case UMLPackage.SIGNAL__OWNED_ATTRIBUTE:
				switch(notification.getEventType()){
				case Notification.ADD:
					Property p = (Property) notification.getNewValue();
					for(DirectedRelationship d:s.getTargetDirectedRelationships()){
						if(d instanceof Generalization){
							addPinForSignalActions(p, (Signal) d.getSources().get(0));
						}
					}
					addPinForSignalActions(p, s);
					break;
				case Notification.REMOVE:
					Property oldProp = (Property) notification.getOldValue();
					removeReferencingPins(oldProp);
				}
				break;
			}
			return null;
		}
		public EObject caseEnumeration(Enumeration en){
			switch(notification.getFeatureID(Enumeration.class)){
			case UMLPackage.DATA_TYPE__OWNED_ATTRIBUTE:
				switch(notification.getEventType()){
				case Notification.ADD:
					for(EnumerationLiteral e:en.getOwnedLiterals()){
						createSlot((InstanceSpecification) e, (Property) notification.getNewValue());
					}
				}
				break;
			case UMLPackage.ENUMERATION__OWNED_LITERAL:
				switch(notification.getEventType()){
				case Notification.ADD:
					EnumerationLiteral newValue = (EnumerationLiteral) notification.getNewValue();
					newValue.getClassifiers().add(en);
					for(Property a:en.getAllAttributes()){
						createSlot(newValue, a);
					}
					for(Association a:en.getAssociations()){
						for(Property p:a.getMemberEnds()){
							if(p.getOtherEnd().getType() == en && p.isNavigable()){
								createSlot(newValue, p);
							}
						}
					}
					break;
				case Notification.ADD_MANY:
					break;
				default:
				}
				break;
			}
			return null;
		}
		public EObject caseOperation(Operation oper){
			switch(notification.getFeatureID(Operation.class)){
			case UMLPackage.OPERATION__OWNED_PARAMETER:
				for(Behavior b:oper.getMethods()){
					synchronizeBehaviorParameters(b, oper);
				}
				switch(notification.getEventType()){
				case Notification.ADD:
					Parameter newValue = (Parameter) notification.getNewValue();
					newValue.setDirection(ParameterDirectionKind.IN_LITERAL);
					for(EObject e:StereotypesHelper.getNumlAnnotation(oper).getReferences()){
						if(e instanceof Trigger && e.eContainer() instanceof AcceptEventAction){
							addResult(newValue, (AcceptEventAction) e.eContainer());
						}else if(e instanceof CallOperationAction){
							addArgument(newValue, (CallAction) e);
						}
					}
					break;
				case Notification.REMOVE:
					Parameter oldValue = (Parameter) notification.getOldValue();
					removeReferencingPins(oldValue);
					break;
				}
				break;
			}
			return null;
		}
		public EObject caseParameter(Parameter p){
			switch(notification.getFeatureID(Parameter.class)){
			case UMLPackage.PARAMETER__DIRECTION:
				ParameterDirectionKind oldDirection = (ParameterDirectionKind) notification.getOldValue();
				ParameterDirectionKind newDirection = (ParameterDirectionKind) notification.getNewValue();
				for(EObject eObject:StereotypesHelper.getNumlAnnotation(p).getReferences()){
					if(eObject instanceof Parameter){
						((Parameter) eObject).setDirection(newDirection);
					}
				}
				for(EObject eObject:StereotypesHelper.getNumlAnnotation((Element) p.eContainer()).getReferences()){
					if(eObject instanceof CallAction){
						CallAction a = (CallAction) eObject;
						if(notification.getOldValue() == null){
							if(EmfParameterUtil.isArgument(newDirection)){
								addArgument(p, a);
							}
							if(EmfParameterUtil.isResult(newDirection)){
								addResult(p, a);
							}
						}else{
							if(EmfParameterUtil.isResult(oldDirection) && !EmfParameterUtil.isResult(newDirection)){
								removeResult(p, a);
							}
							if(EmfParameterUtil.isArgument(oldDirection) && !EmfParameterUtil.isArgument(newDirection)){
								removeArgument(p, a);
							}
							if(!EmfParameterUtil.isResult(oldDirection) && EmfParameterUtil.isResult(newDirection)){
								addResult(p, a);
							}
							if(!EmfParameterUtil.isArgument(oldDirection) && EmfParameterUtil.isArgument(newDirection)){
								addArgument(p, a);
							}
						}
					}else if(eObject instanceof Trigger && eObject.eContainer() instanceof AcceptEventAction){
						AcceptEventAction a = (AcceptEventAction) eObject.eContainer();
						if(notification.getOldValue() == null){
							if(EmfParameterUtil.isArgument(newDirection)){
								addResult(p, a);
							}
						}else{
							if(!EmfParameterUtil.isArgument(oldDirection) && EmfParameterUtil.isArgument(newDirection)){
								addResult(p, a);
							}
							if(EmfParameterUtil.isArgument(oldDirection) && !EmfParameterUtil.isArgument(newDirection)){
								removeResult(p, a);
							}
						}
					}
				}
				break;
			case UMLPackage.PARAMETER__NAME:
				for(EObject eObject:StereotypesHelper.getNumlAnnotation(p).getReferences()){
					if(eObject instanceof Pin){
						((Pin) eObject).setName(notification.getNewStringValue());
					}else if(eObject instanceof Parameter){
						((Parameter) eObject).setName(notification.getNewStringValue());
					}
				}
				break;
			case UMLPackage.PARAMETER__TYPE:
				for(EObject eObject:StereotypesHelper.getNumlAnnotation(p).getReferences()){
					if(eObject instanceof Pin){
						((Pin) eObject).setType((Type) notification.getNewValue());
					}else if(eObject instanceof Parameter){
						((Parameter) eObject).setType((Type) notification.getNewValue());
					}
				}
			}
			return null;
		}
		public EObject caseTrigger(Trigger trigger){
			switch(notification.getFeatureID(Trigger.class)){
			case UMLPackage.TRIGGER__EVENT:
				Element origin = getOrigin(notification.getOldValue());
				if(origin != null){
					removeFromReferences(origin, trigger);
				}
				if(trigger.getOwner() instanceof AcceptEventAction){
					AcceptEventAction aea = (AcceptEventAction) trigger.getOwner();
					if(trigger.getEvent() instanceof TimeEvent){
						OutputPin outputPin;
						if(aea.getResults().size() >= 1){
							outputPin = aea.getResults().get(0);
						}else{
							outputPin = UMLFactory.eINSTANCE.createOutputPin();
							aea.getResults().add(outputPin);
						}
						outputPin.setType(ImportLibraryAction.importLibrary(aea.getModel(), "NakedUMLSimpleTypes.library.uml").getOwnedType("DateTime"));
						outputPin.setName("time");
						while(1 < aea.getResults().size()){
							aea.getResults().remove(1);
						}
						if(StereotypesHelper.hasStereotype(aea, StereotypeNames.ACCEPT_DEADLINE_ACTION)){
							Model lib = ImportLibraryAction.importLibrary(aea.getModel(), StereotypeNames.NAKEDUML_BPM_LIBRARY);
							if(aea.getResults().size() >= 2){
								outputPin = aea.getResults().get(1);
							}else{
								outputPin = UMLFactory.eINSTANCE.createOutputPin();
								aea.getResults().add(outputPin);
							}
							outputPin.setType(lib.getOwnedType("TaskRequest"));
							outputPin.setName("task");
						}
					}else if(StereotypesHelper.hasStereotype(aea, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
						Model lib = ImportLibraryAction.importLibrary(aea.getModel(), StereotypeNames.NAKEDUML_BPM_LIBRARY);
						OutputPin outputPin;
						if(aea.getResults().size() >= 1){
							outputPin = aea.getResults().get(0);
						}else{
							outputPin = UMLFactory.eINSTANCE.createOutputPin();
							aea.getResults().add(outputPin);
						}
						outputPin.setType(lib.getOwnedType("TaskRequest"));
						outputPin.setName("task");
						if(aea.getResults().size() >= 2){
							outputPin = aea.getResults().get(1);
						}else{
							outputPin = UMLFactory.eINSTANCE.createOutputPin();
							aea.getResults().add(outputPin);
						}
						outputPin.setType(lib.getOwnedType("BusinessRole"));
						outputPin.setName("user");
						int i = 2;
						for(TypedElement t:EmfParameterUtil.getArguments(trigger.getEvent())){
							if(aea.getResults().size() > i){
								aea.getResults().get(i).setType(t.getType());
								aea.getResults().get(i).setName(t.getName());
							}else{
								OutputPin pin = UMLFactory.eINSTANCE.createOutputPin();
								pin.setName(t.getName());
								pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
								aea.getResults().add(pin);
								StereotypesHelper.getNumlAnnotation(t).getReferences().add(pin);
							}
							i++;
						}
						while(i < aea.getResults().size()){
							aea.getResults().remove(i);
						}
					}else{
						aea.getResults().clear();
						origin = getOrigin(notification.getNewValue());
						if(origin != null){
							StereotypesHelper.getNumlAnnotation(origin).getReferences().add(trigger);
							AcceptEventAction a = (AcceptEventAction) trigger.getOwner();
							for(TypedElement t:EmfParameterUtil.getArguments(origin)){
								addResult(t, a);
							}
						}
					}
				}
				break;
			}
			return null;
		}
		public EObject caseCallOperationAction(CallOperationAction a){
			switch(notification.getFeatureID(CallOperationAction.class)){
			case UMLPackage.CALL_OPERATION_ACTION__OPERATION:
				manageReferences(notification);
				Operation oper = (Operation) notification.getNewValue();
				synchronizeParameters(oper.getOwnedParameters(), a);
				break;
			}
			return null;
		}
		public EObject caseSendSignalAction(SendSignalAction a){
			switch(notification.getFeatureID(SendSignalAction.class)){
			case UMLPackage.SEND_SIGNAL_ACTION__SIGNAL:
				manageReferences(notification);
				Signal s = (Signal) notification.getNewValue();
				StereotypesHelper.getNumlAnnotation(s).getReferences().add(a);
				a.getArguments().clear();
				for(Property p:EmfParameterUtil.getArguments(s)){
					addArgument(p, a);
				}
				break;
			}
			return null;
		}
		public EObject caseCallBehaviorAction(CallBehaviorAction a){
			switch(notification.getFeatureID(CallBehaviorAction.class)){
			case UMLPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR:
				manageReferences(notification);
				Behavior oper = (Behavior) notification.getNewValue();
				if(oper != null){
					synchronizeParameters(oper.getOwnedParameters(), a);
				}
				break;
			}
			return null;
		}
		public EObject caseProperty(Property p){
			switch(notification.getFeatureID(Property.class)){
			case UMLPackage.NAMED_ELEMENT__NAME:
				for(EObject e:StereotypesHelper.getNumlAnnotation(p).getReferences()){
					if(e instanceof InputPin){
						((InputPin) e).setName(p.getName());
					}
				}
				break;
			}
			return null;
		}
		private void addPinForSignalActions(Property p,Signal signal){
			for(EObject eObject:StereotypesHelper.getNumlAnnotation(signal).getReferences()){
				if(eObject instanceof SendSignalAction){
					addArgument(p, (InvocationAction) eObject);
				}else if(eObject instanceof Trigger && eObject.eContainer() instanceof AcceptEventAction){
					addResult(p, (AcceptEventAction) eObject.eContainer());
				}
			}
		}
		private void synchronizeParameters(EList<Parameter> parms,CallAction a){
			a.getArguments().clear();
			a.getResults().clear();
			for(Parameter parameter:parms){
				if(EmfParameterUtil.isArgument(parameter.getDirection())){
					addArgument(parameter, a);
				}
				if(EmfParameterUtil.isResult(parameter.getDirection())){
					addResult(parameter, a);
				}
			}
		}
		private void removeArgument(Parameter p,CallAction a){
			removeFrom(p, a.getArguments());
		}
		private void removeResult(Parameter p,CallAction a){
			removeFrom(p, a.getResults());
		}
		private void removeResult(Parameter p,AcceptEventAction a){
			removeFrom(p, a.getResults());
		}
		private void removeFrom(Parameter p,EList<? extends Pin> containerList){
			EList<EObject> references = StereotypesHelper.getNumlAnnotation(p).getReferences();
			List<Pin> arguments = new ArrayList<Pin>(containerList);
			for(Pin pin:arguments){
				if(references.contains(pin)){
					containerList.remove(pin);
					break;
				}
			}
		}
		private void removeReferencingPins(TypedElement oldProp){
			for(EObject e:StereotypesHelper.getNumlAnnotation(oldProp).getReferences()){
				if(e.eContainer() instanceof InvocationAction){
					InvocationAction ia = (InvocationAction) e.eContainer();
					if(e instanceof InputPin){
						ia.getArguments().remove(e);
					}else if(e instanceof OutputPin && e.eContainer() instanceof CallAction){
						CallAction a = (CallAction) e.eContainer();
						a.getResults().remove(e);
					}else if(e instanceof OutputPin && e.eContainer() instanceof AcceptEventAction){
						AcceptEventAction a = (AcceptEventAction) e.eContainer();
						a.getResults().remove(e);
					}
				}
			}
		}
		private void addArgument(TypedElement p,InvocationAction a){
			int idx = EmfParameterUtil.calculateIndex(p, EmfParameterUtil.ARGUMENT);
			if(a.getArguments().size() >= idx){
				ValuePin pin = UMLFactory.eINSTANCE.createValuePin();
				pin.setName(p.getName());
				pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
				pin.createValue(pin.getName(), p.getType(), UMLPackage.eINSTANCE.getOpaqueExpression());
				a.getArguments().add(idx, pin);
				StereotypesHelper.getNumlAnnotation(p).getReferences().add(pin);
			}else{
				// TODO synchronize them
			}
		}
		public Element getOrigin(Object oldValue){
			Element origin = null;
			if(oldValue instanceof SignalEvent){
				origin = ((SignalEvent) oldValue).getSignal();
			}
			if(oldValue instanceof CallEvent){
				origin = ((CallEvent) oldValue).getOperation();
			}
			return origin;
		}
		private void manageReferences(Notification notification){
			Object notifier = notification.getNotifier();
			if(notification.getOldValue() != null){
				removeFromReferences((Element) notification.getOldValue(), notifier);
			}
			if(notification.getNewValue() != null){
				StereotypesHelper.getNumlAnnotation((Element) notification.getNewValue()).getReferences().add((EObject) notifier);
			}
		}
		public void removeFromReferences(Element targetElement,Object interestedElement){
			if(targetElement != null){
				EList<EObject> references = StereotypesHelper.getNumlAnnotation(targetElement).getReferences();
				ArrayList<EObject> arrayList = new ArrayList<EObject>(references);
				for(EObject eObject:arrayList){
					if(eObject == interestedElement){
						references.remove(eObject);
						break;
					}
				}
			}
		}
		public void createSlot(InstanceSpecification newValue,Property a){
			Slot slot = newValue.createSlot();
			slot.setDefiningFeature(a);
			OpaqueExpression oclExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
			oclExpression.getBodies().add("Type value here");
			oclExpression.getLanguages().add("ocl");
			slot.getValues().add(oclExpression);
		}
	}
	@Override
	public void notifyChanged(final Notification not){
		if(not.getNotifier() instanceof Element){
			EmfUmlElementLinker emfUmlElementLinker = new EmfUmlElementLinker(not);
			emfUmlElementLinker.doSwitch((Element) not.getNotifier());
		}else if(not.getNotifier() instanceof EAnnotation){
			switch(not.getFeatureID(EAnnotation.class)){
			case EcorePackage.EANNOTATION__CONTENTS:
				if(not.getNewValue() instanceof TimeEvent){
					EAnnotation eAnnotation = (EAnnotation) not.getNotifier();
					TimeEvent te = (TimeEvent) not.getNewValue();
					Element eModelElement = (Element) eAnnotation.getEModelElement();
					applyStereotypeIfNecessary(eModelElement, te, StereotypeNames.DEADLINE, StereotypeNames.NAKEDUML_BPM_PROFILE);
					applyRelativeTimeEventStereotype(te, eModelElement);
				}
				break;
			default:
				break;
			}
		}
		super.notifyChanged(not);
	}
	private void applyRelativeTimeEventStereotype(TimeEvent te,Element eModelElement){
		if(te.isRelative()){
			Profile pr = ApplyProfileAction.applyProfile(eModelElement.getModel(), StereotypeNames.NAKEDUML_PROFILE);
			Stereotype st = pr.getOwnedStereotype(StereotypeNames.RELATIVE_TIME_EVENT);
			if(!te.isStereotypeApplied(st)){
				StereotypesHelper.applyStereotype(te, st);
			}
		}
	}
	public static void addResult(TypedElement newValue,AcceptEventAction a){
		addOutputpin(newValue, a.getResults());
	}
	private void addResult(Parameter newValue,CallAction e){
		addOutputpin(newValue, e.getResults());
	}
	static void addOutputpin(TypedElement newValue,EList<OutputPin> results){
		int idx = EmfParameterUtil.calculateIndex(newValue, EmfParameterUtil.ARGUMENT);
		if(results.size() >= idx){
			OutputPin pin = UMLFactory.eINSTANCE.createOutputPin();
			pin.setName(newValue.getName());
			pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
			results.add(idx, pin);
			StereotypesHelper.getNumlAnnotation(newValue).getReferences().add(pin);
		}
	}
	private void applyStereotypeIfNecessary(Element parent,Element ass,String stereotypeName,String profileName){
		if(StereotypesHelper.hasKeyword(ass, stereotypeName)){
			Profile pr = ApplyProfileAction.applyProfile(parent.getModel(), profileName);
			Stereotype st = pr.getOwnedStereotype(stereotypeName);
			if(!ass.isStereotypeApplied(st)){
				StereotypesHelper.applyStereotype(ass, st);
				if(ass instanceof NamedElement){
					NamedElement ne = (NamedElement) ass;
					ne.setName(ne.getName().replaceAll(ass.eClass().getName(), stereotypeName));
				}
			}
			if(ass instanceof Classifier){
				Classifier intf = (Classifier) ass;
				if(StereotypesHelper.hasStereotype(intf, stereotypeName)){
					boolean found = false;
					Classifier general = (Classifier) ImportLibraryAction.importLibrary(intf.getModel(), StereotypeNames.NAKEDUML_BPM_LIBRARY).getOwnedType(
							stereotypeName);
					for(Generalization g:intf.getGeneralizations()){
						if(g.getGeneral() == general){
							found = true;
						}
					}
					if(found == false){
						intf.createGeneralization(general);
					}
				}
			}
		}
	}
}