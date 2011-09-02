package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EContentAdapter;
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
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.InvocationAction;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.ObjectNode;
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
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
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
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;

public class NakedUmlElementLinker extends EContentAdapter{
	public static final class EmfUmlElementLinker extends UMLSwitch<EObject>{
		private Notification notification;
		public EmfUmlElementLinker(Notification not){
			notification = not;
		}
		@Override
		public EObject caseInstanceSpecification(InstanceSpecification object){
			switch(this.notification.getFeatureID(InstanceSpecification.class)){
			case UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER:
				if(notification.getNewValue() == null){
					if(notification.getOldValue() instanceof Classifier){
						StereotypesHelper.getNumlAnnotation((Element) notification.getOldValue()).getReferences().remove(object);
					}else if(notification.getOldValue() instanceof List){
						List<Classifier> oldValue = (List<Classifier>) notification.getOldValue();
						if(oldValue.size() > 0){
							StereotypesHelper.getNumlAnnotation(oldValue.get(0)).getReferences().remove(object);
						}
					}
					object.getSlots().clear();
				}else{
					Classifier newValue = (Classifier) notification.getNewValue();
					StereotypesHelper.getNumlAnnotation(newValue).getReferences().add(object);
					this.synchronizeSlots(newValue, object);
				}
				break;
			}
			return null;
		}
		@Override
		public EObject caseValuePin(ValuePin vp){
			switch(this.notification.getFeatureID(Namespace.class)){
			case UMLPackage.VALUE_PIN__VALUE:
				if(notification.getNewValue() == null){
					// No cigar!
					if(StereotypesHelper.hasKeyword(vp, StereotypeNames.NEW_OBJECT_INPUT)){
						vp.setValue(createInstanceValue(vp.getName()));
					}else{
						vp.setValue(createOclExpression(vp.getName()));
					}
				}
				break;
			case UMLPackage.VALUE_PIN__TYPE:
				if(StereotypesHelper.hasKeyword(vp, StereotypeNames.NEW_OBJECT_INPUT) && notification.getNewValue() != null){
					InstanceValue value = (InstanceValue) vp.getValue();
					value.getInstance().getClassifiers().clear();
					value.getInstance().getClassifiers().add((Classifier) notification.getNewValue());
				}
				break;
			}
			return null;
		};
		@Override
		public EObject caseConstraint(Constraint c){
			switch(this.notification.getFeatureID(Namespace.class)){
			case UMLPackage.CONSTRAINT__SPECIFICATION:
				if(notification.getNewValue() == null){
					// No cigar!
					c.setSpecification(createOclExpression(c.getName()));
				}
				break;
			}
			return null;
		}
		@Override
		public EObject caseNamespace(Namespace n){
			switch(this.notification.getFeatureID(Namespace.class)){
			case UMLPackage.NAMESPACE__OWNED_RULE:
				Constraint p = null;
				switch(notification.getEventType()){
				case Notification.ADD:
					p = (Constraint) notification.getNewValue();
					if(p.getSpecification() == null){
						p.setSpecification(createOclExpression(p.getName()));
					}
					break;
				}
				break;
			}
			return null;
		}
		private OpaqueExpression createOclExpression(String ownerName){
			OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
			oe.setName(ownerName + "Specification");
			oe.getLanguages().add("OCL");
			oe.getBodies().add(OclBodyComposite.REQUIRED_TEXT);
			return oe;
		}
		private InstanceValue createInstanceValue(String ownerName){
			InstanceValue oe = UMLFactory.eINSTANCE.createInstanceValue();
			oe.setName(ownerName + "Specification");
			InstanceSpecification is = UMLFactory.eINSTANCE.createInstanceSpecification();
			StereotypesHelper.getNumlAnnotation(oe).getContents().add(is);
			oe.setInstance(is);
			return oe;
		}
		public EObject caseAssociation(Association a){
			switch(this.notification.getFeatureID(Association.class)){
			case UMLPackage.ASSOCIATION__NAVIGABLE_OWNED_END:
				Property p = null;
				switch(notification.getEventType()){
				case Notification.ADD:
					p = (Property) notification.getNewValue();
					synchronizeSlotsOnReferringInstances((Classifier) p.getOtherEnd().getType());
					if(p.getOtherEnd().getType() instanceof Signal){
						synchronizeSignalPins((Signal) p.getOtherEnd().getType());
					}
					break;
				case Notification.REMOVE:
					p = (Property) notification.getOldValue();
					synchronizeSlotsOnReferringInstances((Classifier) p.getOtherEnd().getType());
					if(p.getOtherEnd().getType() instanceof Signal){
						// remove individually to ensure existing value pins stay in tact
						removeReferencingPins(p);
					}
					break;
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
					if(ne instanceof Pin && ne.getName().contains(ne.eClass().getName())){
						EReference feat = ne.eContainmentFeature();
						if(feat.isMany()){
							setUniqueName(feat.getName(), ne);
						}else{
							ne.setName(feat.getName());
						}
					}else{
						Set<Entry<String,String>> entrySet = StereotypesHelper.getNumlAnnotation(ne).getDetails().entrySet();
						for(Entry<String,String> entry:entrySet){
							if(entry.getValue() == null || entry.getValue().trim().length() == 0 && !ne.eClass().getName().equals(entry.getKey())){
								// Keyword
								String newValue = notification.getNewStringValue();
								if(newValue.contains(ne.eClass().getName()) && Character.isDigit(newValue.charAt(newValue.length() - 1))){
									setUniqueName(entry.getKey(), ne);
								}
								break;
							}
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
					applyStereotypeIfNecessary(a, (Element) notification.getNewValue(), StereotypeNames.BUSINES_PROCESS, StereotypeNames.OPIUM_BPM_PROFILE);
					applyStereotypeIfNecessary(a, (Element) notification.getNewValue(), StereotypeNames.METHOD, StereotypeNames.OPIUM_BPM_PROFILE);
				}
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
			if(notification.getEventType() == Notification.SET || notification.getEventType() == Notification.ADD){
				if(notification.getNewValue() instanceof ObjectNode){
					ObjectNode inputPin = (ObjectNode) notification.getNewValue();
					// inputPin.setName(((EStructuralFeature) notification.getFeature()).getName());
					if(inputPin.getUpperBound() == null){
						LiteralUnlimitedNatural upperBound = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
						upperBound.setValue(-1);
						inputPin.setUpperBound(upperBound);
					}
					if(notification.getNewValue() instanceof ValuePin){
						ValuePin vp = (ValuePin) notification.getNewValue();
						if(vp.getValue() == null){
							if(StereotypesHelper.hasKeyword(vp, StereotypeNames.OCL_INPUT)){
								vp.setValue(createOclExpression(vp.getName()));
							}else if(StereotypesHelper.hasKeyword(vp, StereotypeNames.NEW_OBJECT_INPUT)){
								vp.setValue(createInstanceValue(vp.getName()));
							}else{
								vp.setValue(createOclExpression(vp.getName()));
							}
						}
					}
				}
			}
			return null;
		}
		private void ensureActivityNodeStereotypes(Element a){
			switch(notification.getEventType()){
			case Notification.ADD:
				ActivityNode newValue = (ActivityNode) notification.getNewValue();
				if(newValue instanceof AcceptEventAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.ACCEPT_DEADLINE_ACTION, StereotypeNames.OPIUM_BPM_PROFILE);
				}else if(newValue instanceof OpaqueAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK, StereotypeNames.OPIUM_BPM_PROFILE);
				}else if(newValue instanceof CallBehaviorAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK, StereotypeNames.OPIUM_BPM_PROFILE);
				}else if(newValue instanceof SendSignalAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.SEND_NOTIFICATION_ACTION, StereotypeNames.OPIUM_BPM_PROFILE);
				}else if(newValue instanceof AcceptCallAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.ACCEPT_TASK_EVENT_ACTION, StereotypeNames.OPIUM_BPM_PROFILE);
				}
				if(newValue instanceof Action){
					populateRequiredPins((Action) newValue);
				}
				break;
			}
		}
		protected void populateRequiredPins(Action ac){
			if(ac instanceof ReadStructuralFeatureAction){
				((ReadStructuralFeatureAction) ac).setResult(UMLFactory.eINSTANCE.createOutputPin());
			}else if(ac instanceof ReadVariableAction){
				((ReadVariableAction) ac).setResult(UMLFactory.eINSTANCE.createOutputPin());
			}else if(ac instanceof CreateObjectAction){
				((CreateObjectAction) ac).setResult(UMLFactory.eINSTANCE.createOutputPin());
			}
		}
		public EObject caseBehavior(Behavior behavior){
			switch(notification.getFeatureID(Operation.class)){
			case UMLPackage.BEHAVIOR__OWNED_PARAMETER:
				switch(notification.getEventType()){
				case Notification.ADD:
					Parameter newValue = (Parameter) notification.getNewValue();
					if(newValue.getDirection() == null){
						newValue.setDirection(ParameterDirectionKind.IN_LITERAL);
					}
					for(EObject e:StereotypesHelper.getNumlAnnotation(behavior).getReferences()){
						if(e instanceof CallBehaviorAction){
							synchronizeParameters(behavior.getOwnedParameters(), (CallAction) e);
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
		public EObject casePackage(Package o){
			Namespace p = o;
			switch(notification.getEventType()){
			case Notification.ADD:
				processNewPackageableElement(p);
				break;
			}
			return null;
		}
		public EObject caseComponent(Component o){
			Namespace p = o;
			switch(notification.getEventType()){
			case Notification.ADD:
				processNewPackageableElement(p);
				break;
			}
			return null;
		}
		public void processNewPackageableElement(Namespace p){
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
				applyStereotypeIfNecessary(p, (Element) notification.getNewValue(), StereotypeNames.NOTIFICATION, StereotypeNames.OPIUM_BPM_PROFILE);
			}
			if(notification.getNewValue() instanceof Interface){
				Interface intf = (Interface) notification.getNewValue();
				applyStereotypeIfNecessary(p, intf, StereotypeNames.BUSINESS_SERVICE, StereotypeNames.OPIUM_BPM_PROFILE);
				applyStereotypeIfNecessary(p, intf, StereotypeNames.HELPER, StereotypeNames.OPIUM_STANDARD_PROFILE);
			}
			if(notification.getNewValue() instanceof Component){
				applyStereotypeIfNecessary(p, (Element) notification.getNewValue(), StereotypeNames.BUSINESS_COMPONENT, StereotypeNames.OPIUM_BPM_PROFILE);
			}
			if(notification.getNewValue() instanceof org.eclipse.uml2.uml.Class){
				applyStereotypeIfNecessary(p, (Element) notification.getNewValue(), StereotypeNames.BUSINESS_ROLE, StereotypeNames.OPIUM_BPM_PROFILE);
			}
			if(notification.getNewValue() instanceof TimeEvent){
				applyRelativeTimeEventStereotype((TimeEvent) notification.getNewValue(), p);
				TimeEvent te = (TimeEvent) notification.getNewValue();
				if(te.getWhen() == null){
					te.setWhen(UMLFactory.eINSTANCE.createTimeExpression());
				}
				if(te.getWhen().getExpr() == null){
					OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
					te.getWhen().setExpr(oe);
					oe.getLanguages().add("OCL");
					oe.getBodies().add(OclBodyComposite.REQUIRED_TEXT);
				}
			}
			if(notification.getNewValue() instanceof ChangeEvent){
				ChangeEvent ce = (ChangeEvent) notification.getNewValue();
				if(ce.getChangeExpression() == null){
					OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
					ce.setChangeExpression(oe);
					oe.getLanguages().add("OCL");
					oe.getBodies().add(OclBodyComposite.REQUIRED_TEXT);
				}
			}
		}
		public EObject caseSlot(Slot v){
			switch(notification.getFeatureID(Slot.class)){
			case UMLPackage.SLOT__DEFINING_FEATURE:
				Property p = (Property) notification.getOldValue();
				if(p != null && p.eContainer() == null && notification.getNewValue() == null){
					v.getOwningInstance().getSlots().remove(v);
				}
				break;
			}
			return null;
		}
		public EObject caseGeneralization(Generalization g){
			switch(notification.getFeatureID(Generalization.class)){
			case UMLPackage.GENERALIZATION__GENERAL:
				if(notification.getNewValue() instanceof Signal){
					synchronizeSignalPins((Signal) g.getSpecific());
				}
				break;
			}
			return null;
		}
		public EObject caseSignal(Signal s){
			switch(notification.getFeatureID(Signal.class)){
			case UMLPackage.CLASSIFIER__GENERALIZATION:
				synchronizeSignalPins(s);
				synchronizeSlotsOnReferringInstances(s);
				break;
			case UMLPackage.SIGNAL__OWNED_ATTRIBUTE:
				if(notification.getEventType() == Notification.REMOVE){
					removeReferencingPins((TypedElement) notification.getOldValue());
				}else{
					synchronizeSignalPins(s);
				}
				synchronizeSlotsOnReferringInstances(s);
				break;
			}
			return null;
		}
		private void synchronizeSignalPins(Signal s){
			List<Property> args = EmfParameterUtil.getArguments(s);
			for(EObject r:StereotypesHelper.getNumlAnnotation(s).getReferences()){
				if(r instanceof SendSignalAction){
					synchronizeArguments(args, (SendSignalAction) r);
				}else if(r instanceof Trigger && r.eContainer() instanceof AcceptEventAction){
					synchronizeResults(args, (AcceptEventAction) r.eContainer());
				}
			}
		}
		@Override
		public EObject caseDataType(DataType object){
			switch(notification.getFeatureID(DataType.class)){
			case UMLPackage.CLASSIFIER__GENERALIZATION:
			case UMLPackage.DATA_TYPE__OWNED_ATTRIBUTE:
				synchronizeSlotsOnReferringInstances(object);
				break;
			}
			return null;
		};
		@Override
		public EObject caseClass(Class object){
			switch(notification.getFeatureID(Class.class)){
			case UMLPackage.CLASSIFIER__GENERALIZATION:
			case UMLPackage.CLASS__OWNED_ATTRIBUTE:
				synchronizeSlotsOnReferringInstances(object);
				break;
			}
			return null;
		};
		@Override
		public EObject caseEnumeration(Enumeration en){
			switch(notification.getFeatureID(Enumeration.class)){
			case UMLPackage.ENUMERATION__OWNED_LITERAL:
				switch(notification.getEventType()){
				case Notification.ADD:
					EList<Classifier> classifiers = ((EnumerationLiteral) notification.getNewValue()).getClassifiers();
					if(!classifiers.contains(en)){
						classifiers.add(en);
					}
					// This would trigger normal Slot synchronization logic
					break;
				case Notification.ADD_MANY:
					break;
				default:
				}
				break;
			}
			return null;
		}
		private void synchronizeSlotsOnReferringInstances(Classifier en){
			for(EObject eObject:StereotypesHelper.getNumlAnnotation(en).getReferences()){
				if(eObject instanceof InstanceSpecification){
					synchronizeSlots(en, (InstanceSpecification) eObject);
				}
			}
		}
		private void synchronizeSlots(Classifier en,InstanceSpecification newValue){
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
				switch(notification.getEventType()){
				case Notification.ADD:
					Parameter newValue = (Parameter) notification.getNewValue();
					if(newValue.getDirection() == null){
						newValue.setDirection(ParameterDirectionKind.IN_LITERAL);
					}
					synchronizeOperationPins(oper);
					break;
				case Notification.REMOVE:
					removeReferencingPins((Parameter) notification.getOldValue());
					break;
				}
				break;
			}
			return null;
		}
		private void synchronizeOperationPins(Operation oper){
			EList<Parameter> ownedParameters = oper.getOwnedParameters();
			for(EObject e:StereotypesHelper.getNumlAnnotation(oper).getReferences()){
				if(e instanceof Trigger && e.eContainer() instanceof AcceptEventAction){
					synchronizeResults(ownedParameters, (AcceptEventAction) e.eContainer());
				}else if(e instanceof CallAction){
					synchronizeParameters(ownedParameters, (CallAction) e);
				}
			}
			for(Behavior b:oper.getMethods()){
				synchronizeBehaviorParameters(b, oper);
			}
		}
		public EObject caseParameter(Parameter p){
			switch(notification.getFeatureID(Parameter.class)){
			case UMLPackage.PARAMETER__DIRECTION:
				ParameterDirectionKind newDirection = (ParameterDirectionKind) notification.getNewValue();
				for(EObject eObject:StereotypesHelper.getNumlAnnotation(p).getReferences()){
					if(eObject instanceof Parameter){
						((Parameter) eObject).setDirection(newDirection);
					}
				}
				Element owner = (Element) p.eContainer();
				EList<Parameter> parms = owner instanceof Behavior ? ((Behavior) owner).getOwnedParameters() : ((Operation) owner).getOwnedParameters();
				for(EObject eObject:StereotypesHelper.getNumlAnnotation(owner).getReferences()){
					if(eObject instanceof CallAction){
						synchronizeParameters(parms, (CallAction) eObject);
					}else if(eObject instanceof Trigger && eObject.eContainer() instanceof AcceptEventAction){
						AcceptEventAction a = (AcceptEventAction) eObject.eContainer();
						synchronizeResults(parms, a);
					}
				}
				break;
			case UMLPackage.PARAMETER__IS_EXCEPTION:
				for(EObject eObject:StereotypesHelper.getNumlAnnotation(p).getReferences()){
					if(eObject instanceof Parameter){
						boolean newBooleanValue = notification.getNewBooleanValue();
						((Parameter) eObject).setIsException(newBooleanValue);
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
						outputPin.setType(ImportLibraryAction.importLibraryIfNecessary(aea.getModel(), StereotypeNames.OPIUM_SIMPLE_TYPES).getOwnedType("DateTime"));
						outputPin.setName("time");
						while(1 < aea.getResults().size()){
							aea.getResults().remove(1);
						}
						if(StereotypesHelper.hasStereotype(aea, StereotypeNames.ACCEPT_DEADLINE_ACTION)){
							Model lib = ImportLibraryAction.importLibraryIfNecessary(aea.getModel(), StereotypeNames.OPIUM_BPM_LIBRARY);
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
						Model lib = ImportLibraryAction.importLibraryIfNecessary(aea.getModel(), StereotypeNames.OPIUM_BPM_LIBRARY);
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
							synchronizeResults(EmfParameterUtil.getArguments(origin), a);
						}
					}
				}
				break;
			}
			return null;
		}
		private void synchronizeResults(List<? extends TypedElement> args,AcceptEventAction a){
			for(TypedElement t:args){
				setOutputpin(t, a.getResults(), false);
			}
			while(a.getResults().size() > args.size()){
				a.getResults().remove(a.getResults().size() - 1);
			}
		}
		public EObject caseCallOperationAction(CallOperationAction a){
			switch(notification.getFeatureID(CallOperationAction.class)){
			case UMLPackage.CALL_OPERATION_ACTION__OPERATION:
				manageReferences(notification);
				Operation oper = (Operation) notification.getNewValue();
				List<Parameter> emptyList = Collections.emptyList();
				synchronizeParameters(oper == null ? emptyList : oper.getOwnedParameters(), a);
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
				synchronizeArguments(EmfParameterUtil.getArguments(s), a);
				break;
			}
			return null;
		}
		private void synchronizeArguments(List<? extends TypedElement> args,SendSignalAction a){
			for(TypedElement p:args){
				setArgument(p, a, false);
			}
			while(a.getArguments().size() > args.size()){
				a.getArguments().remove(a.getArguments().size() - 1);
			}
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
		public EObject caseTypedElement(TypedElement te){
			switch(notification.getFeatureID(Property.class)){
			case UMLPackage.NAMED_ELEMENT__NAME:
				for(EObject e:StereotypesHelper.getNumlAnnotation(te).getReferences()){
					if(e instanceof Pin){
						((Pin) e).setName(te.getName());
					}else if(e instanceof Parameter){
						((Parameter) e).setName(te.getName());
					}
				}
				break;
			case UMLPackage.TYPED_ELEMENT__TYPE:
				for(EObject e:StereotypesHelper.getNumlAnnotation(te).getReferences()){
					if(e instanceof Pin){
						Pin pin = (Pin) e;
						pin.setType(te.getType());
						if(pin instanceof ValuePin && ((ValuePin) pin).getValue() != null){
							((ValuePin) pin).getValue().setType(te.getType());
						}
					}else if(e instanceof Parameter){
						((Parameter) e).setType(te.getType());
					}
				}
				break;
			}
			return null;
		}
		public EObject caseProperty(Property p){
			switch(notification.getFeatureID(Property.class)){
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
				synchronizeSlotsOnReferringInstances(context);
				break;
			}
			return null;
		}
		private void synchronizeParameters(List<Parameter> parms,CallAction a){
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
		private void removeReferencingPins(TypedElement oldProp){
			for(EObject e:StereotypesHelper.getNumlAnnotation(oldProp).getReferences()){
				if(e.eContainer() instanceof InvocationAction){
					InvocationAction ia = (InvocationAction) e.eContainer();
					if(e instanceof InputPin){
						ia.getArguments().remove(e);
					}else if(e instanceof OutputPin && e.eContainer() instanceof CallAction){
						CallAction a = (CallAction) e.eContainer();
						a.getResults().remove(e);
					}
				}else if(e instanceof OutputPin && e.eContainer() instanceof AcceptEventAction){
					AcceptEventAction a = (AcceptEventAction) e.eContainer();
					a.getResults().remove(e);
				}else if(e.eContainer() instanceof Behavior){
					((Behavior) e.eContainer()).getOwnedParameters().remove(oldProp);
				}
			}
		}
		private void setArgument(TypedElement p,InvocationAction a,boolean insertAtIndex){
			int idx = EmfParameterUtil.calculateIndex(p, EmfParameterUtil.ARGUMENT);
			EList<EObject> references = StereotypesHelper.getNumlAnnotation(p).getReferences();
			if(a.getArguments().size() <= idx || insertAtIndex){
				ValuePin pin = UMLFactory.eINSTANCE.createValuePin();
				pin.setName(p.getName());
				pin.setType(p.getType());
				pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
				OpaqueExpression oe = (OpaqueExpression) pin.createValue(pin.getName(), p.getType(), UMLPackage.eINSTANCE.getOpaqueExpression());
				a.getArguments().add(Math.min(a.getArguments().size(), idx), pin);
				references.add(pin);
			}else{
				InputPin pin = a.getArguments().get(idx);
				pin.setName(p.getName());
				if(pin instanceof ValuePin && ((ValuePin) pin).getValue() != null){
					((ValuePin) pin).getValue().setType(p.getType());
				}
				pin.setType(p.getType());
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
		private void removeFromReferences(Element targetElement,Object interestedElement){
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
		private void ensureSlotsPresence(InstanceSpecification is,Property a){
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
					applyStereotypeIfNecessary(eModelElement, te, StereotypeNames.DEADLINE, StereotypeNames.OPIUM_BPM_PROFILE);
					applyRelativeTimeEventStereotype(te, eModelElement);
				}
				break;
			default:
				break;
			}
		}
	}
	private static void applyRelativeTimeEventStereotype(TimeEvent te,Element eModelElement){
		if(te.isRelative()){
			Profile pr = ApplyProfileAction.applyProfile(eModelElement.getModel(), StereotypeNames.OPIUM_STANDARD_PROFILE);
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
			pin.setType(newValue.getType());
			pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
			results.add(Math.min(idx, results.size()), pin);
			references.add(pin);
		}else{
			OutputPin pin = results.get(idx);
			pin.setType(newValue.getType());
			pin.setName(newValue.getName());
			if(!references.contains(pin)){
				references.add(pin);
			}
		}
	}
	private static void applyStereotypeIfNecessary(Element parent,Element ass,String stereotypeName,String profileName){
		if(StereotypesHelper.hasKeyword(ass, stereotypeName)){
			Profile pr = ApplyProfileAction.applyProfile(parent.getModel(), profileName);
			Stereotype st = pr.getOwnedStereotype(stereotypeName);
			if(!(ass instanceof Pin) && ass instanceof NamedElement && parent instanceof Namespace){
				setUniqueName(stereotypeName, (NamedElement) ass);
			}
			if(st != null && !ass.isStereotypeApplied(st)){
				StereotypesHelper.applyStereotype(ass, st);
			}
			if(ass instanceof Classifier){
				Classifier specific = (Classifier) ass;
				if(StereotypesHelper.hasStereotype(specific, stereotypeName)){
					Classifier general = (Classifier) ImportLibraryAction.importLibraryIfNecessary(specific.getModel(), StereotypeNames.OPIUM_BPM_LIBRARY).getOwnedType(
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
	protected static void setUniqueName(String stereotypeName,NamedElement ne){
		int lastNumber = 0;
		List<NamedElement> members = new ArrayList<NamedElement>();
		if(ne.getNamespace() == null){
			for(Element element:ne.getOwner().getOwnedElements()){
				if(element instanceof NamedElement){
					members.add((NamedElement) element);
				}
			}
		}else{
			members.addAll(ne.getNamespace().getMembers());
		}
		for(NamedElement namedElement:members){
			if(namedElement != ne && namedElement.getName() != null && namedElement.getName().contains(stereotypeName)){
				String number = namedElement.getName().substring(stereotypeName.length());
				try{
					int currentNumber = Integer.parseInt(number);
					if(currentNumber > lastNumber){
						lastNumber = currentNumber;
					}
				}catch(Exception e){
				}
			}
		}
		ne.setName(stereotypeName + (lastNumber + 1));
	}
	private static void maybeGeneralize(Classifier specific,Classifier general){
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
	private static void maybeRealizeInterface(BehavioredClassifier behavioredClassifier,Interface general2){
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
}