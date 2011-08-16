package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
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
import org.eclipse.uml2.uml.InterfaceRealization;
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
import org.nakeduml.eclipse.EmfElementFinder;
import org.nakeduml.eclipse.EmfParameterUtil;
import org.nakeduml.eclipse.EmfValidationUtil;
import org.nakeduml.eclipse.ImportLibraryAction;

public class NakedUmlElementLinker{
	private final class EmfUmlElementLinker extends UMLSwitch<EObject>{
		private Notification notification;
		private EmfUmlElementLinker(Notification not){
			notification = not;
		}
		public EObject caseAssociation(Association a){
			switch(this.notification.getFeatureID(Association.class)){
			case UMLPackage.ASSOCIATION__NAVIGABLE_OWNED_END:
				Property p = null;
				switch(notification.getEventType()){
				case Notification.ADD:
					p = (Property) notification.getNewValue();
					if(p.getOtherEnd().getType() instanceof Enumeration){
						Enumeration e = (Enumeration) p.getOtherEnd().getType();
						for(EnumerationLiteral enumerationLiteral:e.getOwnedLiterals()){
							ensureSlotsPresence(enumerationLiteral, p);
						}
					}else if(p.getOtherEnd().getType() instanceof Signal){
						addPinForSignalActions(p, (Signal) p.getOtherEnd().getType());
					}
					break;
				case Notification.REMOVE:
					p = (Property) notification.getOldValue();
					if(p.getOtherEnd().getType() instanceof Enumeration){
						Enumeration e = (Enumeration) p.getOtherEnd().getType();
						synchronizeSlotsOnLiterals(e);
					}else if(p.getOtherEnd().getType() instanceof Signal){
						removeReferencingPins(p);
					}
				}
				break;
			}
			return null;
		}
		public EObject caseNamedElement(NamedElement ne){
			switch(notification.getEventType()){
			case Notification.SET:
				switch(this.notification.getFeatureID(NamedElement.class)){
				case UMLPackage.NAMED_ELEMENT__NAME:
					Set<Entry<String,String>> entrySet = StereotypesHelper.getNumlAnnotation(ne).getDetails().entrySet();
					for(Entry<String,String> entry:entrySet){
						if(entry.getValue() == null || entry.getValue().trim().length() == 0){
							// Keyword
							String newValue = notification.getNewStringValue();
							if(newValue.contains(ne.eClass().getName()) && Character.isDigit(newValue.charAt(newValue.length() - 1))){
								ne.setName(ne.getName().replaceAll(ne.eClass().getName(), entry.getKey()));
							}
							break;
						}
					}
					break;
				}
				break;
			}
			return null;
		}
		public EObject caseStructuredActivityNode(StructuredActivityNode a){
			switch(this.notification.getFeatureID(StructuredActivityNode.class)){
			case UMLPackage.STRUCTURED_ACTIVITY_NODE__CONTAINED_NODE:
				ensureActivityNodeStereotypes(a);
				break;
			}
			return null;
		}
		public EObject caseBehavioredClassifier(BehavioredClassifier a){
			switch(notification.getEventType()){
			case Notification.ADD:
				if(notification.getNewValue() instanceof Activity){
					applyStereotypeIfNecessary(a, (Element) notification.getNewValue(), StereotypeNames.BUSINES_PROCESS, StereotypeNames.NAKEDUML_BPM_PROFILE);
					applyStereotypeIfNecessary(a, (Element) notification.getNewValue(), StereotypeNames.METHOD, StereotypeNames.NAKEDUML_BPM_PROFILE);
				}
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
							setArgument(newValue, (CallAction) e, true);
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
				}else if(notification.getNewValue() == null && notification.getOldValue() != null){
					oper = (Operation) notification.getOldValue();
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
					Parameter bp = UMLFactory.eINSTANCE.createParameter();
					bp.setName(p.getName());
					bp.setType(p.getType());
					bp.setDirection(p.getDirection());
					behavior.getOwnedParameters().add(bp);
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
								ensureSlotsPresence(e, property.getOtherEnd());
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
				case Notification.REMOVE:
					synchronizeSlotsOnLiterals(en);
				}
				break;
			case UMLPackage.ENUMERATION__OWNED_LITERAL:
				switch(notification.getEventType()){
				case Notification.ADD:
					synchronizeSlots(en, (EnumerationLiteral) notification.getNewValue());
					break;
				case Notification.ADD_MANY:
					break;
				default:
				}
				break;
			}
			return null;
		}
		private void synchronizeSlotsOnLiterals(Enumeration en){
			for(EnumerationLiteral e:en.getOwnedLiterals()){
				synchronizeSlots(en, e);
			}
		}
		private void synchronizeSlots(Classifier en,InstanceSpecification newValue){
			newValue.getClassifiers().add(en);
			List<Property> propertiesInScope = EmfElementFinder.getPropertiesInScope(en);
			outer:for(Slot slot:new ArrayList<Slot>(newValue.getSlots())){
				for(Property a:propertiesInScope){
					if(a.equals(slot.getDefiningFeature()) && !(a.isDerived() || a.isDerivedUnion())){
						continue outer;
					}
				}
				newValue.getSlots().remove(slot);
			}
			for(Property a:propertiesInScope){
				if(!(a.isDerived() || a.isDerivedUnion())){
					ensureSlotsPresence(newValue, a);
				}
			}
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
							setOutputpin(newValue, ((AcceptEventAction) e.eContainer()).getResults(), true);
						}else if(e instanceof CallOperationAction){
							setArgument(newValue, (CallAction) e, true);
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
								setArgument(p, a, true);
							}
							if(EmfParameterUtil.isResult(newDirection)){
								setOutputpin(p, a.getResults(), true);
							}
						}else{
							if(EmfParameterUtil.isResult(oldDirection) && !EmfParameterUtil.isResult(newDirection)){
								removeResult(p, a);
							}
							if(EmfParameterUtil.isArgument(oldDirection) && !EmfParameterUtil.isArgument(newDirection)){
								removeArgument(p, a);
							}
							if(!EmfParameterUtil.isResult(oldDirection) && EmfParameterUtil.isResult(newDirection)){
								setOutputpin(p, a.getResults(), true);
							}
							if(!EmfParameterUtil.isArgument(oldDirection) && EmfParameterUtil.isArgument(newDirection)){
								setArgument(p, a, true);
							}
						}
					}else if(eObject instanceof Trigger && eObject.eContainer() instanceof AcceptEventAction){
						AcceptEventAction a = (AcceptEventAction) eObject.eContainer();
						if(notification.getOldValue() == null){
							if(EmfParameterUtil.isArgument(newDirection)){
								setOutputpin(p, a.getResults(), true);
							}
						}else{
							if(!EmfParameterUtil.isArgument(oldDirection) && EmfParameterUtil.isArgument(newDirection)){
								setOutputpin(p, a.getResults(), true);
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
			case UMLPackage.PARAMETER__IS_EXCEPTION:
				for(EObject eObject:StereotypesHelper.getNumlAnnotation(p).getReferences()){
					if(eObject instanceof Parameter){
						((Parameter) eObject).setIsException(notification.getNewBooleanValue());
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
							outputPin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
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
								outputPin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
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
							outputPin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
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
						origin = getOrigin(notification.getNewValue());
						if(origin != null){
							StereotypesHelper.getNumlAnnotation(origin).getReferences().add(trigger);
							AcceptEventAction a = (AcceptEventAction) trigger.getOwner();
							List<? extends TypedElement> args = EmfParameterUtil.getArguments(origin);
							for(TypedElement t:args){
								setOutputpin(t, a.getResults(), false);
							}
							while(a.getResults().size() > args.size()){
								a.getResults().remove(a.getResults().size() - 1);
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
				List<Property> args = EmfParameterUtil.getArguments(s);
				for(Property p:args){
					setArgument(p, a, false);
				}
				while(a.getArguments().size() > args.size()){
					a.getArguments().remove(a.getArguments().size() - 1);
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
			case UMLPackage.PROPERTY__IS_COMPOSITE:
			case UMLPackage.PROPERTY__AGGREGATION:
				if(p.isComposite()){
					if(p.getAssociation() != null){
						if(!p.isNavigable()){
							p.setIsNavigable(true);
						}
						if(!p.getOtherEnd().isNavigable() && p.getOtherEnd().getType() instanceof Class){
							p.getOtherEnd().setIsNavigable(true);
						}
					}
				}
				break;
			case UMLPackage.PROPERTY__IS_DERIVED:
			case UMLPackage.PROPERTY__IS_DERIVED_UNION:
				Classifier context = (Classifier) (p.getOtherEnd() != null ? p.getOtherEnd().getType() : p.getOwner());
				if(context instanceof Enumeration){
					synchronizeSlotsOnLiterals((Enumeration) context);
				}
			}
			return null;
		}
		private void addPinForSignalActions(Property newProperty,Signal signal){
			// FIrst do subclasses
			for(DirectedRelationship d:signal.getTargetDirectedRelationships()){
				if(d instanceof Generalization){
					addPinForSignalActions(newProperty, (Signal) d.getSources().get(0));
				}
			}
			for(EObject eObject:StereotypesHelper.getNumlAnnotation(signal).getReferences()){
				if(eObject instanceof SendSignalAction){
					setArgument(newProperty, (InvocationAction) eObject, true);
				}else if(eObject instanceof Trigger && eObject.eContainer() instanceof AcceptEventAction){
					setOutputpin(newProperty, ((AcceptEventAction) eObject.eContainer()).getResults(), true);
				}
			}
		}
		private void synchronizeParameters(EList<Parameter> parms,CallAction a){
			int argCount = 0;
			int resultCount = 0;
			for(Parameter parameter:parms){
				if(EmfParameterUtil.isArgument(parameter.getDirection())){
					setArgument(parameter, a, false);
					argCount++;
				}
				if(EmfParameterUtil.isResult(parameter.getDirection())){
					setOutputpin(parameter, a.getResults(), false);
					resultCount++;
				}
			}
			while(a.getArguments().size() > argCount){
				a.getArguments().remove(argCount);
			}
			while(a.getResults().size() > resultCount){
				a.getResults().remove(resultCount);
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
		private void setArgument(TypedElement p,InvocationAction a,boolean insertAtIndex){
			int idx = EmfParameterUtil.calculateIndex(p, EmfParameterUtil.ARGUMENT);
			EList<EObject> references = StereotypesHelper.getNumlAnnotation(p).getReferences();
			if(a.getArguments().size() <= idx || insertAtIndex){
				ValuePin pin = UMLFactory.eINSTANCE.createValuePin();
				pin.setName(p.getName());
				pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
				pin.createValue(pin.getName(), p.getType(), UMLPackage.eINSTANCE.getOpaqueExpression());
				a.getArguments().add(Math.min(a.getArguments().size(), idx), pin);
				references.add(pin);
			}else{
				InputPin pin = a.getArguments().get(idx);
				pin.setName(p.getName());
				pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
				if(!references.contains(pin)){
					references.add(pin);
				}
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
		public void ensureSlotsPresence(InstanceSpecification is,Property a){
			Slot found = null;
			for(Slot slot:new ArrayList<Slot>(is.getSlots())){
				if(slot.getDefiningFeature() != null && slot.getDefiningFeature().equals(a)){
					found = slot;
				}
			}
			if(found == null){
				found = UMLFactory.eINSTANCE.createSlot();
				found.setDefiningFeature(a);
				OpaqueExpression oclExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
				oclExpression.getBodies().add(EmfValidationUtil.TYPE_EXPRESSION_HERE);
				oclExpression.getLanguages().add("ocl");
				found.getValues().add(oclExpression);
				is.getSlots().add(found);
			}
		}
	}
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
	public static void setOutputpin(TypedElement newValue,EList<OutputPin> results,boolean insertAtIndex){
		int idx = EmfParameterUtil.calculateIndex(newValue, EmfParameterUtil.ARGUMENT);
		EList<EObject> references = StereotypesHelper.getNumlAnnotation(newValue).getReferences();
		if(results.size() <= idx || insertAtIndex){
			OutputPin pin = UMLFactory.eINSTANCE.createOutputPin();
			pin.setName(newValue.getName());
			pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
			results.add(Math.min(idx, results.size()), pin);
			references.add(pin);
		}else{
			OutputPin pin = results.get(idx);
			pin.setName(newValue.getName());
			if(!references.contains(pin)){
				references.add(pin);
			}
		}
	}
	private void applyStereotypeIfNecessary(Element parent,Element ass,String stereotypeName,String profileName){
		if(StereotypesHelper.hasKeyword(ass, stereotypeName)){
			Profile pr = ApplyProfileAction.applyProfile(parent.getModel(), profileName);
			Stereotype st = pr.getOwnedStereotype(stereotypeName);
			if(st != null && !ass.isStereotypeApplied(st)){
				StereotypesHelper.applyStereotype(ass, st);
			}
			if(ass instanceof Classifier){
				Classifier specific = (Classifier) ass;
				if(StereotypesHelper.hasStereotype(specific, stereotypeName)){
					Classifier general = (Classifier) ImportLibraryAction.importLibrary(specific.getModel(), StereotypeNames.NAKEDUML_BPM_LIBRARY).getOwnedType(
							stereotypeName);
					if(general != null){
						if(general instanceof Interface && specific instanceof BehavioredClassifier){
							maybeRealizeInterface((BehavioredClassifier) specific, (Interface) general);
						}else{
							maybeGeneralize(specific, general);
						}
					}
				}
			}
		}
	}
	private void maybeGeneralize(Classifier specific,Classifier general){
		boolean found = false;
		for(Generalization g:specific.getGeneralizations()){
			if(g.getGeneral() == general){
				found = true;
			}
		}
		if(found == false){
			specific.createGeneralization(general);
		}
	}
	private void maybeRealizeInterface(BehavioredClassifier behavioredClassifier,Interface general2){
		boolean found = false;
		for(InterfaceRealization g:behavioredClassifier.getInterfaceRealizations()){
			if(g.getContract() == general2){
				found = true;
			}
		}
		if(found == false){
			behavioredClassifier.createInterfaceRealization("isA" + general2.getName(), general2);
		}
	}
	public static void main(String[] args){
		List l = new ArrayList<Object>(5);
		l.add("Stirng");
		l.add(3, "asdf");
	}
}