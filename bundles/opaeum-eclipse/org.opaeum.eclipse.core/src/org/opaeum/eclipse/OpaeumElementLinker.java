package org.opaeum.eclipse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.AddVariableValueAction;
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
import org.eclipse.uml2.uml.DurationConstraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.IntervalConstraint;
import org.eclipse.uml2.uml.InvocationAction;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
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
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.TimeConstraint;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

//TODO!!!!! Get rid of unneccessary references - use CrossReferenceAdapter 
public class OpaeumElementLinker extends EContentAdapter{
	public void notifyChanged(final Notification not){
		if(not.getEventType() == Notification.ADD || not.getEventType() == Notification.ADD_MANY || not.getEventType() == Notification.REMOVE
				|| not.getEventType() == Notification.REMOVE_MANY || not.getEventType() == Notification.SET){
			if(not.getNotifier() instanceof Element){
				EmfUmlElementLinker emfUmlElementLinker = new EmfUmlElementLinker(not);
				emfUmlElementLinker.doSwitch((Element) not.getNotifier());
			}else if(not.getNotifier() instanceof DynamicEObjectImpl && ((EStructuralFeature) not.getFeature()).getName().startsWith(Extension.METACLASS_ROLE_PREFIX)){
				Element element = UMLUtil.getBaseElement((EObject) not.getNotifier());
				ApplyStereotypeCommand.implementInterfacesIfNecessary(element);
			}
		}
	}
	public static final class EmfUmlElementLinker extends UMLSwitch<EObject>{
		private Notification notification;
		private ECrossReferenceAdapter crossReferenceAdapter;
		public EmfUmlElementLinker(Notification not){
			notification = not;
		}
		@Override
		public EObject caseElement(Element object){
			return super.caseElement(object);
		}
		@Override
		public EObject caseExpansionRegion(ExpansionRegion object){
			if(this.notification.getEventType() == Notification.ADD){
				if(notification.getNewValue() instanceof ExpansionNode){
					ExpansionNode en = (ExpansionNode) notification.getNewValue();
					if(StereotypesHelper.hasKeyword(en, StereotypeNames.LOOP_INPUT_COLLECTION)){
						object.getInputElements().add(en);
					}else{
						object.getOutputElements().add(en);
					}
				}
			}
			return super.caseExpansionRegion(object);
		}
		@Override
		public EObject caseEncapsulatedClassifier(EncapsulatedClassifier object){
			if(this.notification.getEventType() == Notification.ADD){
				if(this.notification.getNewValue() instanceof Port){
					applyStereotypeIfNecessary(object, (Element) notification.getNewValue(), StereotypeNames.BUSINESS_GATEWAY, StereotypeNames.OPAEUM_BPM_PROFILE);
				}
			}
			return super.caseEncapsulatedClassifier(object);
		}
		@Override
		public EObject caseStructuredClassifier(StructuredClassifier object){
			if(this.notification.getEventType() == Notification.ADD){
				switch(this.notification.getFeatureID(StructuredClassifier.class)){
				case UMLPackage.STRUCTURED_CLASSIFIER__OWNED_CONNECTOR:
					applyStereotypeIfNecessary(object, (Element) notification.getNewValue(), StereotypeNames.BUSINESS_CHANNEL, StereotypeNames.OPAEUM_BPM_PROFILE);
					applyStereotypeIfNecessary(object, (Element) notification.getNewValue(), StereotypeNames.RESPONSIBILITY_DELEGATION,
							StereotypeNames.OPAEUM_BPM_PROFILE);
					break;
				}
			}
			return super.caseStructuredClassifier(object);
		}
		@Override
		public EObject caseInstanceSpecification(InstanceSpecification object){
			switch(this.notification.getFeatureID(InstanceSpecification.class)){
			case UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER:
				if(notification.getNewValue() == null){
					object.getSlots().clear();
				}else{
					Classifier newValue = (Classifier) notification.getNewValue();
					synchronizeSlots(newValue, object);
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
						vp.setValue(EmfValueSpecificationUtil.buildOpaqueExpression(vp, "Value", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
					}
				}
				break;
			case UMLPackage.VALUE_PIN__TYPE:
				if(vp.getValue() instanceof InstanceValue && notification.getNewValue() != null){
					InstanceValue value = (InstanceValue) vp.getValue();
					if(value.getInstance() == null){
						InstanceSpecification is = UMLFactory.eINSTANCE.createInstanceSpecification();
						EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
						value.getEAnnotations().add(ann);
						ann.setSource(StereotypeNames.NUML_ANNOTATION);
						ann.getContents().add(is);
						value.setInstance(is);
					}
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
					// No cigar
					forceConstraintSpecification(c);
				}
				break;
			}
			return null;
		}
		@Override
		public EObject casePackageImport(PackageImport object){
			switch(this.notification.getFeatureID(PackageImport.class)){
			case UMLPackage.PACKAGE_IMPORT__IMPORTED_PACKAGE:
				if(notification.getNewValue() == null){
					object.getImportingNamespace().getPackageImports().remove(object);
				}
			}
			return null;
		};
		@Override
		public EObject caseElementImport(ElementImport object){
			switch(this.notification.getFeatureID(ElementImport.class)){
			case UMLPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				if(notification.getNewValue() == null){
					object.getImportingNamespace().getElementImports().remove(object);
				}
			}
			return null;
		};
		@Override
		public EObject caseNamespace(Namespace n){
			switch(this.notification.getFeatureID(Namespace.class)){
			case UMLPackage.NAMESPACE__OWNED_RULE:
				Constraint p = null;
				switch(notification.getEventType()){
				case Notification.ADD:
					p = (Constraint) notification.getNewValue();
					forceConstraintSpecification(p);
					break;
				}
				break;
			}
			return null;
		}
		private InstanceValue createInstanceValue(String ownerName){
			InstanceValue oe = UMLFactory.eINSTANCE.createInstanceValue();
			oe.setName(ownerName + "Specification");
			InstanceSpecification is = UMLFactory.eINSTANCE.createInstanceSpecification();
			StereotypesHelper.findOrCreateNumlAnnotation(oe).getContents().add(is);
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
					if(p.getOtherEnd() != null && p.getOtherEnd().getType() != null){// Remember n-ary associations
						synchronizeSlotsOnReferringInstances((Classifier) p.getOtherEnd().getType());
						if(p.getOtherEnd().getType() instanceof Signal){
							synchronizeSignalPins((Signal) p.getOtherEnd().getType());
						}
					}
					break;
				case Notification.REMOVE:
					p = (Property) notification.getOldValue();
					if(p.getOtherEnd() != null && p.getOtherEnd().getType() != null){
						// NB!! could be deleting
						synchronizeSlotsOnReferringInstances((Classifier) p.getOtherEnd().getType());
						if(p.getOtherEnd().getType() instanceof Signal){
							// remove individually to ensure existing value pins stay in tact
							removeReferencingPins(p);
						}
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
					EReference feat = ne.eContainmentFeature();
					if(ne instanceof Pin && ne.getName() != null){
						if(ne.getName().contains(ne.eClass().getName()) && !ne.getName().equals(feat.getName())){
							if(!feat.isMany()){
								ne.setName(feat.getName());
							}
						}
					}
					if(ne instanceof Classifier){
						updateRelatedPowerTypeInstances((Classifier) ne);
					}
					break;
				}
				break;
			}
			return null;
		}
		private void updateRelatedPowerTypeInstances(Classifier c){
			for(Generalization generalization:c.getGeneralizations()){
				for(GeneralizationSet generalizationSet:generalization.getGeneralizationSets()){
					Enumeration pt = (Enumeration) generalizationSet.getPowertype();
					if(pt != null){
						for(EnumerationLiteral l:pt.getOwnedLiterals()){
							Stereotype pti = StereotypesHelper.getStereotype(l, StereotypeNames.POWER_TYPE_INSANCE);
							if(pti != null && l.isStereotypeApplicable(pti)){
								if(l.getValue(pti, "representedGeneralization") == generalization){
									l.setName(c.getName());
								}
							}
						}
					}
				}
			}
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
					applyStereotypeIfNecessary(a, (Element) notification.getNewValue(), StereotypeNames.BUSINES_PROCESS, StereotypeNames.OPAEUM_BPM_PROFILE);
					applyStereotypeIfNecessary(a, (Element) notification.getNewValue(), StereotypeNames.METHOD, StereotypeNames.OPAEUM_BPM_PROFILE);
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
								vp.setValue(EmfValueSpecificationUtil.buildOpaqueExpression(vp, "Value", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
							}else if(StereotypesHelper.hasKeyword(vp, StereotypeNames.NEW_OBJECT_INPUT)){
								vp.setValue(createInstanceValue(vp.getName()));
							}else{
								vp.setValue(EmfValueSpecificationUtil.buildOpaqueExpression(vp, "Value", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
							}
						}
					}
				}else if(notification.getNewValue() instanceof Constraint){
					forceConstraintSpecification((Constraint) notification.getNewValue());
				}
			}
			return null;
		}
		protected void forceConstraintSpecification(Constraint cc){
			if(StereotypesHelper.hasStereotype(cc, StereotypeNames.ESCALATION)){
				// skip - not required
			}else if(cc instanceof TimeConstraint){
				if(cc.getSpecification() == null){
					cc.setSpecification(UMLFactory.eINSTANCE.createTimeInterval());
				}
			}else if(cc instanceof InteractionConstraint){
				if(cc.getSpecification() == null){
					// p.setSpecification(UMLFactory.eINSTANCE.createDurationInterval());
				}
			}else if(cc instanceof DurationConstraint){
				if(cc.getSpecification() == null){
					cc.setSpecification(UMLFactory.eINSTANCE.createDurationInterval());
				}
			}else if(cc instanceof IntervalConstraint){
				if(cc.getSpecification() == null){
					cc.setSpecification(UMLFactory.eINSTANCE.createInterval());
				}
			}else if(cc instanceof Constraint){
				if(cc.getSpecification() == null){
					cc.setSpecification(EmfValueSpecificationUtil.buildOpaqueExpression(cc, "Specification", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
				}
			}
		}
		private void ensureActivityNodeStereotypes(Element a){
			switch(notification.getEventType()){
			case Notification.ADD:
				ActivityNode newValue = (ActivityNode) notification.getNewValue();
				if(newValue instanceof AcceptEventAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.ACCEPT_DEADLINE_ACTION, StereotypeNames.OPAEUM_BPM_PROFILE);
				}else if(newValue instanceof OpaqueAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK, StereotypeNames.OPAEUM_BPM_PROFILE);
				}else if(newValue instanceof CallBehaviorAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK, StereotypeNames.OPAEUM_BPM_PROFILE);
				}else if(newValue instanceof SendSignalAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.SEND_NOTIFICATION_ACTION, StereotypeNames.OPAEUM_BPM_PROFILE);
				}else if(newValue instanceof AcceptCallAction){
					applyStereotypeIfNecessary(a, newValue, StereotypeNames.ACCEPT_TASK_EVENT_ACTION, StereotypeNames.OPAEUM_BPM_PROFILE);
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
			}else if(ac instanceof AddStructuralFeatureValueAction){
				((AddStructuralFeatureValueAction) ac).setResult(UMLFactory.eINSTANCE.createOutputPin());
				((AddStructuralFeatureValueAction) ac).setObject(UMLFactory.eINSTANCE.createValuePin());
				((AddStructuralFeatureValueAction) ac).setValue(UMLFactory.eINSTANCE.createValuePin());
			}else if(ac instanceof AddVariableValueAction){
				((AddVariableValueAction) ac).setValue(UMLFactory.eINSTANCE.createValuePin());
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
					for(EObject e:getReferences(behavior, UMLPackage.eINSTANCE.getCallBehaviorAction_Behavior())){
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
						Collection<EObject> references = StereotypesHelper.getReferencesOnAnnotation(parameter);
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
				EList<EObject> references = StereotypesHelper.findOrCreateNumlAnnotation(p).getReferences();
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
				doGeneralInitialization(p, notification.getNewValue());
				break;
			case Notification.REMOVE:
				if(notification.getOldValue() instanceof Association){
					// Bug in Papyrus leaves dangling properties
					Association a = (Association) notification.getOldValue();
					for(Property property:new ArrayList<Property>(a.getMemberEnds())){
						if(property.getOwner() != null && property.getOwner() != notification.getOldValue()){
							// EList<Property> r = (EList<Property>) property.getOwner().eGet(property.eContainmentFeature());
							// r.remove(property);
						}
					}
				}
				break;
			}
			return null;
		}
		public EObject caseComponent(Component o){
			Namespace p = o;
			switch(notification.getEventType()){
			case Notification.ADD:
				doGeneralInitialization(p, notification.getNewValue());
				break;
			}
			return null;
		}
		private void doGeneralInitialization(Namespace p,Object newValue){
			if(newValue instanceof Association){
				Association ass = (Association) newValue;
				for(Property property:ass.getMemberEnds()){
					if(property.getOtherEnd() != null && property.getOtherEnd().isNavigable() && property.getType() instanceof Enumeration){
						for(EnumerationLiteral e:((Enumeration) property.getType()).getOwnedLiterals()){
							ensureSlotsPresence(e, property.getOtherEnd());
						}
					}
				}
			}
			if(newValue instanceof TimeEvent){
				TimeEvent te = (TimeEvent) newValue;
				if(te.getWhen() == null){
					te.setWhen(EmfValueSpecificationUtil.buildTimeExpression(te, "When", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
				}
				if(te.getWhen().getExpr() == null){
					te.getWhen().setExpr(EmfValueSpecificationUtil.buildOpaqueExpression(te, "When", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
				}
			}
			if(newValue instanceof ChangeEvent){
				ChangeEvent ce = (ChangeEvent) newValue;
				if(ce.getChangeExpression() == null){
					ce.setChangeExpression(EmfValueSpecificationUtil.buildOpaqueExpression(ce, "ChangeExpression", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
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
		@Override
		public EObject caseInterfaceRealization(InterfaceRealization object){
			switch(notification.getFeatureID(InterfaceRealization.class)){
			case UMLPackage.INTERFACE_REALIZATION__CONTRACT:
				if(notification.getNewValue() == null){
					object.getImplementingClassifier().getInterfaceRealizations().remove(object);
				}
				break;
			}
			return null;
		};
		@Override
		public EObject caseGeneralization(Generalization g){
			switch(notification.getFeatureID(Generalization.class)){
			case UMLPackage.GENERALIZATION__GENERAL:
				if(notification.getNewValue() instanceof Signal){
					synchronizeSignalPins((Signal) g.getSpecific());
				}
				if(notification.getNewValue() == null){
					// g.getSpecific().getGeneralizations().remove(g);
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
			Collection<Classifier> subClasses = EmfClassifierUtil.getAllSubClassifiers(s, getModelsInScope(s.eResource().getResourceSet()));
			subClasses.add(s);
			for(Classifier subSignal:subClasses){
				if(subSignal instanceof Signal){
					for(EObject r:getReferences(subSignal, UMLPackage.eINSTANCE.getSendSignalAction_Signal())){
						if(r instanceof SendSignalAction){
							SendSignalAction ssa = (SendSignalAction) r;
							synchronizeArguments(EmfParameterUtil.getArguments(ssa), (SendSignalAction) r);
						}
					}
					for(EObject event:getReferences(subSignal, UMLPackage.eINSTANCE.getSignalEvent_Signal())){
						for(EObject r:getReferences((Element) event, UMLPackage.eINSTANCE.getTrigger_Event())){
							if(r instanceof Trigger && r.eContainer() instanceof AcceptEventAction){
								synchronizeResults(EmfParameterUtil.getArguments(subSignal), (AcceptEventAction) r.eContainer());
							}
						}
					}
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
		public EObject caseGeneralizationSet(GeneralizationSet object){
			Enumeration pt = (Enumeration) object.getPowertype();
			if(pt != null){
				switch(notification.getFeatureID(GeneralizationSet.class)){
				case UMLPackage.GENERALIZATION_SET__GENERALIZATION:
					switch(notification.getEventType()){
					case Notification.ADD:
						Generalization newValue = (Generalization) notification.getNewValue();
						pt.createOwnedLiteral(newValue.getSpecific().getName());
						break;
					case Notification.REMOVE:
						Generalization oldValue = (Generalization) notification.getOldValue();
						pt.getOwnedLiterals().remove(pt.getOwnedLiteral(oldValue.getSpecific().getName()));
						break;
					default:
						break;
					}
				}
			}
			return null;
		}
		@Override
		public EObject caseClass(Class object){
			switch(notification.getFeatureID(Class.class)){
			case UMLPackage.CLASSIFIER__GENERALIZATION:
				synchronizeSlotsOnReferringInstances(object);
				break;
			case UMLPackage.CLASS__OWNED_ATTRIBUTE:
				if(notification.getEventType() == Notification.ADD){
					applyStereotypeIfNecessary(object, (Element) notification.getNewValue(), StereotypeNames.PARTICIPANT_REFERENCE, StereotypeNames.OPAEUM_BPM_PROFILE);
					applyStereotypeIfNecessary(object, (Element) notification.getNewValue(), StereotypeNames.BUSINESS_ROLE_CONTAINMENT,
							StereotypeNames.OPAEUM_BPM_PROFILE);
				}
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
					EnumerationLiteral literal = (EnumerationLiteral) notification.getNewValue();
					synchronizeSlots(en, literal);
					break;
				case Notification.ADD_MANY:
					break;
				default:
				}
			case UMLPackage.ENUMERATION__OWNED_ATTRIBUTE:
				EList<EnumerationLiteral> ownedLiterals = en.getOwnedLiterals();
				for(EnumerationLiteral literal:ownedLiterals){
					synchronizeSlots(en, literal);
				}
				break;
			}
			return null;
		}
		private void synchronizeSlotsOnReferringInstances(Classifier en){
			Collection<Classifier> subClasses = EmfClassifierUtil.getAllSubClassifiers(en, getModelsInScope(en.eResource().getResourceSet()));
			subClasses.add(en);
			for(Classifier classifier:subClasses){
				Collection<EObject> references = getReferences(classifier, UMLPackage.eINSTANCE.getInstanceSpecification_Classifier());
				for(EObject eObject:references){
					InstanceSpecification is = (InstanceSpecification) eObject;
					EList<Classifier> classifiers = is.getClassifiers();
					if(classifiers.size() == 1){
						synchronizeSlots(classifiers.get(0), is);
					}
				}
			}
		}
		private Collection<Package> getModelsInScope(ResourceSet resourceSet){
			Collection<Package> result = new HashSet<Package>();
			for(Resource resource:resourceSet.getResources()){
				if(resource.getContents().size() > 0 && resource.getContents().get(0) instanceof Package){
					result.add((Package) resource.getContents().get(0));
				}
			}
			return result;
		}
		private Collection<Setting> getRefs(Element en){
			if(this.crossReferenceAdapter == null){
				crossReferenceAdapter = ECrossReferenceAdapter.getCrossReferenceAdapter(en);
			}
			return crossReferenceAdapter.getInverseReferences(en);
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
			case UMLPackage.OPERATION__OWNED_RULE:
				break;
			}
			return null;
		}
		private void synchronizeOperationPins(Operation oper){
			EList<Parameter> ownedParameters = oper.getOwnedParameters();
			for(EObject e:getReferences(oper, UMLPackage.eINSTANCE.getCallOperationAction_Operation())){
				synchronizeParameters(ownedParameters, (CallAction) e);
			}
			for(EObject event:getReferences(oper, UMLPackage.eINSTANCE.getCallEvent_Operation())){
				for(EObject e:getReferences((Element) event, UMLPackage.eINSTANCE.getTrigger_Event())){
					if(e instanceof Trigger && e.eContainer() instanceof AcceptEventAction){
						synchronizeResults(ownedParameters, (AcceptEventAction) e.eContainer());
					}
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
				for(EObject eObject:StereotypesHelper.getReferencesOnAnnotation(p)){
					if(eObject instanceof Parameter){
						((Parameter) eObject).setDirection(newDirection);
					}
				}
				Element owner = (Element) p.eContainer();
				EList<Parameter> parms = owner instanceof Behavior ? ((Behavior) owner).getOwnedParameters() : ((Operation) owner).getOwnedParameters();
				for(EObject eObject:StereotypesHelper.getReferencesOnAnnotation(owner)){
					if(eObject instanceof CallAction){
						synchronizeParameters(parms, (CallAction) eObject);
					}else if(eObject instanceof Trigger && eObject.eContainer() instanceof AcceptEventAction){
						AcceptEventAction a = (AcceptEventAction) eObject.eContainer();
						synchronizeResults(parms, a);
					}
				}
				break;
			case UMLPackage.PARAMETER__IS_EXCEPTION:
				for(EObject eObject:StereotypesHelper.findOrCreateNumlAnnotation(p).getReferences()){
					if(eObject instanceof Parameter){
						boolean newBooleanValue = notification.getNewBooleanValue();
						((Parameter) eObject).setIsException(newBooleanValue);
					}
				}
			}
			return null;
		}
		@Override
		public EObject caseRegion(Region object){
			switch(notification.getFeatureID(Region.class)){
			case UMLPackage.REGION__TRANSITION:
				break;
			}
			return super.caseRegion(object);
		}
		@Override
		public EObject caseChangeEvent(ChangeEvent object){
			switch(notification.getFeatureID(ChangeEvent.class)){
			case UMLPackage.CHANGE_EVENT__CHANGE_EXPRESSION:
				if(notification.getNewValue() == null){
					// No cigar
					object.setChangeExpression(EmfValueSpecificationUtil.buildOpaqueExpression(object, "ChangeExpression", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
				}
				break;
			}
			return null;
		}
		@Override
		public EObject caseTimeEvent(TimeEvent object){
			switch(notification.getFeatureID(TimeEvent.class)){
			case UMLPackage.TIME_EVENT__WHEN:
				if(notification.getNewValue() == null){
					// No cigar
					object.setWhen(EmfValueSpecificationUtil.buildTimeExpression(object, "Expr", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
				}
				break;
			}
			return null;
		}
		@Override
		public EObject caseTimeExpression(TimeExpression object){
			switch(notification.getFeatureID(TimeExpression.class)){
			case UMLPackage.TIME_EXPRESSION__EXPR:
				if(notification.getNewValue() == null){
					// No cigar
					object.setExpr(EmfValueSpecificationUtil.buildOpaqueExpression(object, "Expr", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
				}
				break;
			}
			return null;
		}
		public EObject caseTrigger(Trigger trigger){
			switch(notification.getFeatureID(Trigger.class)){
			case UMLPackage.TRIGGER__EVENT:
				Element origin = getOrigin(notification.getOldValue());
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
						outputPin.setType(LibraryImporter.importLibraryIfNecessary(aea.getModel(), StereotypeNames.OPAEUM_SIMPLE_TYPES).getOwnedType("DateTime"));
						outputPin.setName("time");
						while(1 < aea.getResults().size()){
							aea.getResults().remove(1);
						}
						if(StereotypesHelper.hasStereotype(aea, StereotypeNames.ACCEPT_DEADLINE_ACTION)){
							Model lib = LibraryImporter.importLibraryIfNecessary(aea.getModel(), StereotypeNames.OPAEUM_BPM_LIBRARY);
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
						Model lib = LibraryImporter.importLibraryIfNecessary(aea.getModel(), StereotypeNames.OPAEUM_BPM_LIBRARY);
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
								StereotypesHelper.findOrCreateNumlAnnotation(t).getReferences().add(pin);
							}
							i++;
						}
						while(i < aea.getResults().size()){
							aea.getResults().remove(i);
						}
					}else{
						origin = getOrigin(notification.getNewValue());
						if(origin != null){
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
				Operation oper = (Operation) notification.getNewValue();
				List<Parameter> emptyList = Collections.emptyList();
				synchronizeParameters(oper == null ? emptyList : oper.getOwnedParameters(), a);
				if(StereotypesHelper.hasKeyword(oper, StereotypeNames.RESPONSIBILITY)
						&& !StereotypesHelper.hasKeyword(EmfElementFinder.getNearestClassifier(a), StereotypeNames.BUSINES_PROCESS)){
					a.setIsSynchronous(false);
				}
				break;
			}
			return null;
		}
		public EObject caseSendSignalAction(SendSignalAction a){
			switch(notification.getFeatureID(SendSignalAction.class)){
			case UMLPackage.SEND_SIGNAL_ACTION__SIGNAL:
				Signal s = (Signal) notification.getNewValue();
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
				Behavior oper = (Behavior) notification.getNewValue();
				if(oper != null){
					synchronizeParameters(oper.getOwnedParameters(), a);
				}
				if(StereotypesHelper.hasKeyword(oper, StereotypeNames.BUSINESS_STATE_MACHINE) || StereotypesHelper.hasKeyword(oper, StereotypeNames.BUSINES_PROCESS)
						|| StereotypesHelper.hasKeyword(oper, StereotypeNames.SCREEN_FLOW)
						&& !StereotypesHelper.hasKeyword(EmfElementFinder.getNearestClassifier(a), StereotypeNames.BUSINES_PROCESS)){
					a.setIsSynchronous(false);
				}
				break;
			}
			return null;
		}
		public EObject caseTypedElement(TypedElement te){
			switch(notification.getFeatureID(TypedElement.class)){
			case UMLPackage.NAMED_ELEMENT__NAME:
				for(EObject e:StereotypesHelper.getReferencesOnAnnotation(te)){
					if(e instanceof Pin){
						((Pin) e).setName(te.getName());
					}else if(e instanceof Parameter){
						((Parameter) e).setName(te.getName());
					}
				}
				break;
			case UMLPackage.TYPED_ELEMENT__TYPE:
				for(EObject e:StereotypesHelper.getReferencesOnAnnotation(te)){
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
				if(te instanceof Property && !EmfPropertyUtil.isMany(te) && te.getType() != null){
					Collection<Setting> refs = getRefs(te);
					for(Setting setting:refs){
						if(setting.getEObject() instanceof Slot){
							Slot slot = (Slot) setting.getEObject();
							boolean isIncorrectType = false;
							if(slot.getValues().size() == 1 && !(slot.getValues().get(0) instanceof OpaqueExpression)){
								ValueSpecification vs = slot.getValues().get(0);
								isIncorrectType = (EmfClassifierUtil.comformsToLibraryType(te.getType(), "Boolean") && !(vs instanceof LiteralBoolean))
										|| EmfClassifierUtil.comformsToLibraryType(te.getType(), "Integer") && !(vs instanceof LiteralInteger)
										|| EmfClassifierUtil.comformsToLibraryType(te.getType(), "String") && !(vs instanceof LiteralString);
							}
							if(isIncorrectType || slot.getValues().isEmpty()
									|| (slot.getValues().get(0) instanceof OpaqueExpression && isUnInitialised((OpaqueExpression) slot.getValues().get(0)))){
								slot.getValues().clear();
								addSlotValue((Property) te, slot);
							}
						}
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
						if(p.getOtherEnd() != null && !p.getOtherEnd().isNavigable() && p.getOtherEnd().getType() instanceof Class){
							p.getOtherEnd().setIsNavigable(true);
						}
					}
				}
				break;
			case UMLPackage.PROPERTY__IS_DERIVED:
			case UMLPackage.PROPERTY__IS_DERIVED_UNION:
			case UMLPackage.PROPERTY__UPPER:
			case UMLPackage.PROPERTY__UPPER_VALUE:
			case UMLPackage.PROPERTY___UPPER_BOUND:
				Classifier context = (Classifier) (p.getOtherEnd() != null ? p.getOtherEnd().getType() : p.getOwner());
				if(context != null){
					synchronizeSlotsOnReferringInstances(context);
				}
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
			for(EObject e:StereotypesHelper.findOrCreateNumlAnnotation(oldProp).getReferences()){
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
			EList<EObject> references = StereotypesHelper.findOrCreateNumlAnnotation(p).getReferences();
			if(a.getArguments().size() <= idx || insertAtIndex){
				ValuePin pin = UMLFactory.eINSTANCE.createValuePin();
				pin.setName(p.getName());
				pin.setType(p.getType());
				pin.createUpperBound("ub", null, UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
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
		private Element getOrigin(Object oldValue){
			Element origin = null;
			if(oldValue instanceof SignalEvent){
				origin = ((SignalEvent) oldValue).getSignal();
			}
			if(oldValue instanceof CallEvent){
				origin = ((CallEvent) oldValue).getOperation();
			}
			return origin;
		}
		public static void setOutputpin(TypedElement newValue,EList<OutputPin> results,boolean insertAtIndex){
			int idx = EmfParameterUtil.calculateIndex(newValue, EmfParameterUtil.RESULT);
			EList<EObject> references = StereotypesHelper.findOrCreateNumlAnnotation(newValue).getReferences();
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
		@Deprecated
		private static void applyStereotypeIfNecessary(Element parent,Element ass,String stereotypeName,String profileName){
			if(StereotypesHelper.hasKeyword(ass, stereotypeName)){
				Profile pr = ProfileApplier.applyProfile(parent.getModel(), profileName);
				Stereotype st = pr.getOwnedStereotype(stereotypeName);
				if(st != null && !ass.isStereotypeApplied(st)){
					StereotypesHelper.applyStereotype(ass, st);
				}
			}
		}
	}
	public static void synchronizeSlots(Classifier en,InstanceSpecification newValue){
		List<Property> propertiesInScope = EmfPropertyUtil.getEffectiveProperties(en);
		outer:for(Slot slot:new ArrayList<Slot>(newValue.getSlots())){
			for(Property a:propertiesInScope){
				if(a.equals(slot.getDefiningFeature()) && !(EmfPropertyUtil.isDerived(a))){
					continue outer;
				}
			}
			newValue.getSlots().remove(slot);
		}
		for(Property a:propertiesInScope){
			if(!(EmfPropertyUtil.isDerived(a))){
				ensureSlotsPresence(newValue, a);
			}
		}
	}
	public static void ensureSlotsPresence(InstanceSpecification is,Property a){
		Slot found = EmfValueSpecificationUtil.getSlotForFeature(is, a);
		if(found == null){
			found = UMLFactory.eINSTANCE.createSlot();
			found.setDefiningFeature(a);
			is.getSlots().add(found);
			if(a.getType() instanceof Enumeration){
				// No criterion to choose one - needs to be handled by ui
			}else{
				if(a.getType() != null){
					addSlotValue(a, found);
				}
			}
		}else if(is instanceof EnumerationLiteral && a.getType() instanceof Enumeration){
			if(found.getOwningInstance() instanceof EnumerationLiteral){
				for(ValueSpecification vs:new ArrayList<ValueSpecification>(found.getValues())){
					if(vs instanceof OpaqueExpression){
						OpaqueExpression oe = (OpaqueExpression) vs;
						if(isUnInitialised(oe)){
							// Most likely changed to a many
							found.getValues().remove(vs);
						}
					}
				}
			}else if(found.getOwningInstance().eContainer() instanceof ValuePin){
				// TODO decide what to do here
			}
		}
	}
	protected static void addSlotValue(Property a,Slot found){
		ValueSpecification literal = null;
		if(!EmfPropertyUtil.isMany(a)){
			if(EmfClassifierUtil.comformsToLibraryType(a.getType(), "Boolean")){
				literal = UMLFactory.eINSTANCE.createLiteralBoolean();
			}else if(EmfClassifierUtil.comformsToLibraryType(a.getType(), "Integer")){
				literal = UMLFactory.eINSTANCE.createLiteralInteger();
			}else if(EmfClassifierUtil.comformsToLibraryType(a.getType(), "String")){
				literal = UMLFactory.eINSTANCE.createLiteralString();
			}
		}
		if(literal == null){
			literal = UMLFactory.eINSTANCE.createOpaqueExpression();
			((OpaqueExpression) literal).getBodies().add(EmfValidationUtil.TYPE_EXPRESSION_HERE);
			((OpaqueExpression) literal).getLanguages().add("ocl");
		}
		found.getValues().add(literal);
	}
	private static boolean isUnInitialised(OpaqueExpression oe){
		return oe.getBodies().isEmpty() || oe.getBodies().get(0).equals(EmfValidationUtil.TYPE_EXPRESSION_HERE);
	}
	public static Collection<EObject> getReferences(Element e,EReference feature){
		Collection<Setting> refs = ECrossReferenceAdapter.getCrossReferenceAdapter(e).getNonNavigableInverseReferences(e);
		Set<EObject> result = new HashSet<EObject>();
		for(Setting setting:refs){
			if(setting.getEStructuralFeature().equals(feature)){
				result.add(setting.getEObject());
			}
		}
		return result;
	}
}