package org.opaeum.topcased.uml.editor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.edit.providers.ActivityItemProvider;
import org.eclipse.uml2.uml.edit.providers.AssociationItemProvider;
import org.eclipse.uml2.uml.edit.providers.CallBehaviorActionItemProvider;
import org.eclipse.uml2.uml.edit.providers.ClassItemProvider;
import org.eclipse.uml2.uml.edit.providers.ComponentItemProvider;
import org.eclipse.uml2.uml.edit.providers.ConnectorItemProvider;
import org.eclipse.uml2.uml.edit.providers.ConstraintItemProvider;
import org.eclipse.uml2.uml.edit.providers.DataTypeItemProvider;
import org.eclipse.uml2.uml.edit.providers.DurationObservationItemProvider;
import org.eclipse.uml2.uml.edit.providers.ElementImportItemProvider;
import org.eclipse.uml2.uml.edit.providers.ExpansionNodeItemProvider;
import org.eclipse.uml2.uml.edit.providers.ExpansionRegionItemProvider;
import org.eclipse.uml2.uml.edit.providers.InputPinItemProvider;
import org.eclipse.uml2.uml.edit.providers.InterfaceItemProvider;
import org.eclipse.uml2.uml.edit.providers.InterfaceRealizationItemProvider;
import org.eclipse.uml2.uml.edit.providers.ModelItemProvider;
import org.eclipse.uml2.uml.edit.providers.OpaqueActionItemProvider;
import org.eclipse.uml2.uml.edit.providers.OpaqueBehaviorItemProvider;
import org.eclipse.uml2.uml.edit.providers.OpaqueExpressionItemProvider;
import org.eclipse.uml2.uml.edit.providers.OperationItemProvider;
import org.eclipse.uml2.uml.edit.providers.OutputPinItemProvider;
import org.eclipse.uml2.uml.edit.providers.PortItemProvider;
import org.eclipse.uml2.uml.edit.providers.PrimitiveTypeItemProvider;
import org.eclipse.uml2.uml.edit.providers.PropertyItemProvider;
import org.eclipse.uml2.uml.edit.providers.ReceptionItemProvider;
import org.eclipse.uml2.uml.edit.providers.SendSignalActionItemProvider;
import org.eclipse.uml2.uml.edit.providers.SignalItemProvider;
import org.eclipse.uml2.uml.edit.providers.StateItemProvider;
import org.eclipse.uml2.uml.edit.providers.StateMachineItemProvider;
import org.eclipse.uml2.uml.edit.providers.StereotypeApplicationItemProvider;
import org.eclipse.uml2.uml.edit.providers.StructuredActivityNodeItemProvider;
import org.eclipse.uml2.uml.edit.providers.TimeEventItemProvider;
import org.eclipse.uml2.uml.edit.providers.TimeObservationItemProvider;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.eclipse.uml2.uml.edit.providers.ValuePinItemProvider;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class OpaeumItemProviderAdapterFactory extends UMLItemProviderAdapterFactory{
	public OpaeumItemProviderAdapterFactory(){
	}
	@Override
	public Adapter createTimeObservationAdapter(){
		if(timeObservationItemProvider == null){
			timeObservationItemProvider = new TimeObservationItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					TimeObservation te = (TimeObservation) object;
					if(StereotypesHelper.hasStereotype(te, StereotypeNames.QUANTITY_BASED_COST_OBSERVATION)){
						return "<Quantity Based Cost Observation> " + te.getName();
					}
					return "<Time Observation> " + te.getName();
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return timeObservationItemProvider;
	}
	@Override
	public Adapter createDurationObservationAdapter(){
		if(durationObservationItemProvider == null){
			durationObservationItemProvider = new DurationObservationItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					DurationObservation te = (DurationObservation) object;
					if(StereotypesHelper.hasStereotype(te, StereotypeNames.DURATION_BASED_COST_OBSERVATION)){
						return "<Duration Based Cost Observation> " + te.getName();
					}
					return "<Duration Observation> " + te.getName();
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return durationObservationItemProvider;
	}
	@Override
	public Adapter createDataTypeAdapter(){
		if(dataTypeItemProvider == null){
			dataTypeItemProvider = new DataTypeItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					DataType pt = (DataType) object;
					if(EmfClassifierUtil.isSimpleType(pt)){
						return "<Value Type> " + pt.getName();
					}else{
						return "<Data Type> " + pt.getName();
					}
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return dataTypeItemProvider;
	}
	public Adapter createPrimitiveTypeAdapter(){
		if(primitiveTypeItemProvider == null){
			primitiveTypeItemProvider = new PrimitiveTypeItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					org.eclipse.uml2.uml.PrimitiveType pt = (org.eclipse.uml2.uml.PrimitiveType) object;
					return "<Primitive Type> " + pt.getName();
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return primitiveTypeItemProvider;
	}
	@Override
	public Adapter createOpaqueExpressionAdapter(){
		if(opaqueExpressionItemProvider == null){
			opaqueExpressionItemProvider = new OpaqueExpressionItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					OpaqueExpression oe = (OpaqueExpression) object;
					OpaeumLibrary lib = OpaeumEclipseContext.findOpenUmlFileFor(oe).getEmfWorkspace().getOpaeumLibrary();
					if(oe.getType() != null){
						if(lib.getNotificationReceiver().equals(oe.getType())){
							return "<Recipient Expression> " + oe.getName();
						}else if(lib.getParticipant().equals(oe.getType())){
							return "<Participant Expression> " + oe.getName();
						}else if(lib.getBusinessRole().equals(oe.getType())){
							return "<Business Role Expression> " + oe.getName();
						}else if(lib.getBusinessCalendar().equals(oe.getType())){
							return "<Calendar Expression> " + oe.getName();
						}else if(lib.getTimedResource().equals(oe.getType())){
							return "<Timed Resource Expression> " + oe.getName();
						}else if(lib.getQuantifiedResource().equals(oe.getType())){
							return "<Quantified Resource Expression> " + oe.getName();
						}
					}
					return "<Opaque Expression> " + oe.getName();
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return opaqueExpressionItemProvider;
	}
	@Override
	public Adapter createReceptionAdapter(){
		if(receptionItemProvider == null){
			receptionItemProvider = new ReceptionItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					Reception reception = (Reception) object;
					if(reception.getSignal() == null){
						return "<Receives> nothing";
					}else{
						return "<Receives> " + reception.getSignal().getName();
					}
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return receptionItemProvider;
	}
	@Override
	public Adapter createElementImportAdapter(){
		if(elementImportItemProvider == null){
			elementImportItemProvider = new ElementImportItemProvider(this){
				@Override
				public String getText(Object object){
					ElementImport e = (ElementImport) object;
					if(e.getImportedElement() == null){
						return "<Imports> nothing";
					}else{
						return "<Imports> " + e.getImportedElement().getName();
					}
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return elementImportItemProvider;
	}
	@Override
	public Adapter createInterfaceRealizationAdapter(){
		if(interfaceRealizationItemProvider == null){
			interfaceRealizationItemProvider = new InterfaceRealizationItemProvider(this){
				@Override
				public String getText(Object object){
					if(object instanceof InterfaceRealization){
						InterfaceRealization a = (InterfaceRealization) object;
						if(a.getContract() != null){
							return "<Implements>" + a.getContract().getName();
						}else{
							return "<Implements> unassigned";
						}
					}
					return super.getText(object);
				}
			};
		}
		return interfaceRealizationItemProvider;
	}
	@Override
	public Adapter createConnectorAdapter(){
		if(connectorItemProvider == null){
			connectorItemProvider = new ConnectorItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Connector){
						Connector a = (Connector) object;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_CHANNEL)){
							return "<Business Channel> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.RESPONSIBILITY_DELEGATION)){
							return "<Delegation> " + a.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return connectorItemProvider;
	}
	@Override
	public Adapter createPropertyAdapter(){
		if(propertyItemProvider == null){
			propertyItemProvider = new PropertyItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					Property a = (Property) object;
					if(StereotypesHelper.hasStereotype(a, "CmParameter")){
						return "<CmParameter>" + a.getName();
					}else if(StereotypesHelper.hasStereotype(a, "CmRelation")){
						return "<CmRelation>" + a.getName() + ":" + a.getType().getName();
					}else{
						String result = null;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.PARTICIPANT_REFERENCE)){
							result = "<Participant Reference> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_ROLE_CONTAINMENT)){
							result = "<Business Role Containment> " + a.getName();
						}else if(a.getAssociation() == null){
							if(StereotypesHelper.hasStereotype(a, StereotypeNames.ATTRIBUTE)){
								if(EmfPropertyUtil.isMeasure(a)){
									result = "<Measure> " + a.getName();
								}else if(EmfPropertyUtil.isDimension(a)){
									result = "<Dimension> " + a.getName();
								}else{
									result = "<Attribute> " + a.getName();
								}
							}else{
								result = "<Attribute> " + a.getName();
							}
						}else{
							if(StereotypesHelper.hasStereotype(a, StereotypeNames.ASSOCIATION_END)){
								if(EmfPropertyUtil.isDimension(a)){
									result = "<Dimension>" + a.getName();
								}else{
									result = "<Assocation End> " + a.getName();
								}
							}else{
								result = "<Assocation End> " + a.getName();
							}
						}
						if(a.getType() == null){
							return result + " : untyped";
						}else{
							return result + " : " + a.getType().getName();
						}
					}
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return propertyItemProvider;
	}
	@Override
	public Adapter createPortAdapter(){
		if(portItemProvider == null){
			portItemProvider = new PortItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Port){
						Port a = (Port) object;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_GATEWAY)){
							return "<Business Gateway> " + a.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return portItemProvider;
	}
	@Override
	public Adapter createCallBehaviorActionAdapter(){
		if(callBehaviorActionItemProvider == null){
			callBehaviorActionItemProvider = new CallBehaviorActionItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof CallBehaviorAction){
						CallBehaviorAction a = (CallBehaviorAction) object;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.CALL_BUSINES_PROCESS_ACTION)){
							return "<Business Process Call> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.CALL_BUSINESS_STATE_MACHINE_ACTION)){
							return "<Business Statemachine Call> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK)){
							return "<Screen Flow Call> " + a.getName();
						}else{
							return "<Method Call> " + a.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
				public void notifyChanged(Notification notification){
					switch(notification.getFeatureID(CallBehaviorAction.class)){
					case UMLPackage.CALL_BEHAVIOR_ACTION__BEHAVIOR:
						fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
						return;
					}
					super.notifyChanged(notification);
				}
			};
		}
		return callBehaviorActionItemProvider;
	}
	public Adapter createActivityAdapter(){
		if(activityItemProvider == null){
			activityItemProvider = new ActivityItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Activity){
						Activity a = (Activity) object;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINES_PROCESS)){
							return "<Business Process> " + a.getName();
						}else{
							return "<Method> " + a.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				public Object getImage(Object object){
					return super.getImage(object);
					//					return overlayImage(object, OpaeumPlugin.getDefault().getImageRegistry().getDescriptor("Actor")); //$NON-NLS-1$
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return activityItemProvider;
	}
	@Override
	public Adapter createOpaqueActionAdapter(){
		if(opaqueActionItemProvider == null){
			opaqueActionItemProvider = new OpaqueActionItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				public String getText(Object object){
					if(object instanceof OpaqueAction){
						OpaqueAction a = (OpaqueAction) object;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK)){
							return "<Single Screen Task> " + a.getName();
						}else{
							return "<Ocl Action> " + a.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return opaqueActionItemProvider;
	}
	@Override
	public Adapter createClassAdapter(){
		if(classItemProvider == null){
			classItemProvider = new ClassItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object){
					if(childrenFeatures == null){
						super.getChildrenFeatures(object);
					}
					return childrenFeatures;
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
				@Override
				public Object getImage(Object object){
					// TODO Auto-generated method stub
					return super.getImage(object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof org.eclipse.uml2.uml.Class){
						org.eclipse.uml2.uml.Class a = (org.eclipse.uml2.uml.Class) object;
						if(StereotypesHelper.hasStereotype(a, "CmElement")){
							return "<Network Element> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_ROLE)){
							return "<Business Role> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_DOCUMENT)){
							return "<Business Document> " + a.getName();
						}else{
							return "<Entity> " + a.getName();
						}
					}
					return super.getText(object);
				}
			};
		}
		return classItemProvider;
	}
	@Override
	public Adapter createModelAdapter(){
		if(modelItemProvider == null){
			modelItemProvider = new ModelItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Model){
						Model a = (Model) object;
						if(StereotypesHelper.hasStereotype(a, "Model")){
							// TODO check for library type
							return "<Model> " + a.getName();
						}else{
							return "<Model> " + a.getName();
						}
					}
					return super.getText(object);
				}
			};
		}
		return modelItemProvider;
	}
	@Override
	public Adapter createAssociationAdapter(){
		if(associationItemProvider == null){
			associationItemProvider = new AssociationItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Association){
						Association a = (Association) object;
						if(StereotypesHelper.hasStereotype(a, "CmAssociation")){
							return "<Topology> " + a.getName();
						}else{
							return "<Association> " + a.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return associationItemProvider;
	}
	@Override
	public Adapter createInterfaceAdapter(){
		if(interfaceItemProvider == null){
			interfaceItemProvider = new InterfaceItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Interface){
						Interface a = (Interface) object;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_SERVICE)){
							return "<Business Service> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.HELPER)){
							return "<Helper> " + a.getName();
						}else{
							return "<Interface> " + a.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return interfaceItemProvider;
	}
	@Override
	public Adapter createComponentAdapter(){
		if(componentItemProvider == null){
			componentItemProvider = new ComponentItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Component){
						Component c = (Component) object;
						if(StereotypesHelper.hasStereotype(c, StereotypeNames.BUSINESS_COMPONENT)){
							return "<Business Component> " + c.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return componentItemProvider;
	}
	@Override
	public Adapter createValuePinAdapter(){
		if(valuePinItemProvider == null){
			valuePinItemProvider = new ValuePinItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof ValuePin){
						ValuePin c = (ValuePin) object;
						if(StereotypesHelper.hasStereotype(c, StereotypeNames.NEW_OBJECT_INPUT)){
							return "<New Object Input> " + c.getName();
						}else{
							return "<Ocl Input>" + c.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return valuePinItemProvider;
	}
	@Override
	public Adapter createExpansionNodeAdapter(){
		if(expansionNodeItemProvider == null){
			expansionNodeItemProvider = new ExpansionNodeItemProvider(this){
				@Override
				public String getText(Object object){
					if(object instanceof ExpansionNode){
						ExpansionNode c = (ExpansionNode) object;
						if(c.getRegionAsInput() != null){
							return "<Loop Input Collection>" + c.getName();
						}else if(c.getRegionAsOutput() != null){
							return "<Loop Output Collection>" + c.getName();
						}else{
							return "<Unassigned Loop Collection>" + c.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return expansionNodeItemProvider;
	}
	@Override
	public Adapter createOpaqueBehaviorAdapter(){
		if(opaqueBehaviorItemProvider == null){
			opaqueBehaviorItemProvider = new OpaqueBehaviorItemProvider(this){
				@Override
				public String getText(Object object){
					OpaqueBehavior ob = (OpaqueBehavior) object;
					if(StereotypesHelper.hasStereotype(ob, StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK)){
						return "<Standalone Task> " + ob.getName();
					}else{
						return "<Opaque Behavior> " + ob.getName();
					}
				}
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return opaqueBehaviorItemProvider;
	}
	@Override
	public Adapter createStructuredActivityNodeAdapter(){
		if(structuredActivityNodeItemProvider == null){
			structuredActivityNodeItemProvider = new StructuredActivityNodeItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
			};
		}
		return structuredActivityNodeItemProvider;
	}
	@Override
	public Adapter createExpansionRegionAdapter(){
		if(expansionRegionItemProvider == null){
			expansionRegionItemProvider = new ExpansionRegionItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof ExpansionRegion){
						ExpansionRegion c = (ExpansionRegion) object;
						return "<ForEach Loop>" + c.getName();
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return expansionRegionItemProvider;
	}
	@Override
	public Adapter createInputPinAdapter(){
		if(inputPinItemProvider == null){
			inputPinItemProvider = new InputPinItemProvider(this){
				@Override
				public String getText(Object object){
					if(object instanceof InputPin){
						Pin c = (Pin) object;
						return "<Object Input>" + c.getName();
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return inputPinItemProvider;
	}
	@Override
	public Adapter createOutputPinAdapter(){
		if(outputPinItemProvider == null){
			outputPinItemProvider = new OutputPinItemProvider(this){
				@Override
				public String getText(Object object){
					if(object instanceof OutputPin){
						Pin c = (Pin) object;
						return "<Object Output>" + c.getName();
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return outputPinItemProvider;
	}
	@Override
	public Adapter createOperationAdapter(){
		if(operationItemProvider == null){
			operationItemProvider = new OperationItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Operation){
						Operation c = (Operation) object;
						if(StereotypesHelper.hasStereotype(c, StereotypeNames.RESPONSIBILITY)){
							return "<Responsibility> " + c.getName();
						}else{
							return "<Operation>" + c.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return operationItemProvider;
	}
	@Override
	public Adapter createConstraintAdapter(){
		if(constraintItemProvider == null){
			constraintItemProvider = new ConstraintItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Constraint) object);
				}
				public String getText(Object object){
					if(object instanceof Constraint){
						Constraint c = (Constraint) object;
						if(StereotypesHelper.hasStereotype(c, StereotypeNames.ESCALATION)){
							return "<Escalation> " + c.getName();
						}else{
							return "<Constraint>" + c.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return constraintItemProvider;
	}
	@Override
	public Adapter createStateMachineAdapter(){
		if(stateMachineItemProvider == null){
			stateMachineItemProvider = new StateMachineItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof StateMachine){
						StateMachine c = (StateMachine) object;
						if(StereotypesHelper.hasStereotype(c, StereotypeNames.SCREEN_FLOW)){
							return "<Screen Flow> " + c.getName();
						}else if(StereotypesHelper.hasStereotype(c, StereotypeNames.STANDALONE_SCREENFLOW_TASK)){
							return "<Standalone Task> " + c.getName();
						}else{
							return "<Business Statemachine>" + c.getName();
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return stateMachineItemProvider;
	}
	@Override
	public Adapter createStateAdapter(){
		if(stateItemProvider == null){
			stateItemProvider = new StateItemProvider(this){
				@Override
				public String getText(Object object){
					if(object instanceof State){
						State c = (State) object;
						if(StereotypesHelper.hasStereotype(c, StereotypeNames.SCREEN)){
							return "<Screen> " + c.getName();
						}else{
							return super.getText(object);
						}
					}
					return super.getText(object);
				}
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return stateItemProvider;
	}
	@Override
	public Adapter createSendSignalActionAdapter(){
		if(sendSignalActionItemProvider == null){
			sendSignalActionItemProvider = new SendSignalActionItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					SendSignalAction te = (SendSignalAction) object;
					if(StereotypesHelper.hasStereotype(te, StereotypeNames.SEND_NOTIFICATION_ACTION)){
						return "<Send Notification> " + te.getName();
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return sendSignalActionItemProvider;
	}
	@Override
	public Adapter createTimeEventAdapter(){
		if(timeEventItemProvider == null){
			timeEventItemProvider = new TimeEventItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					TimeEvent te = (TimeEvent) object;
					if(StereotypesHelper.hasStereotype(te, StereotypeNames.CONTEXTUAL_BUSINESS_TIME_EVENT)){
						return "<Business Time Event> " + te.getName();
					}else if(StereotypesHelper.hasStereotype(te, StereotypeNames.DEADLINE)){
						return "<Deadline> " + te.getName();
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return timeEventItemProvider;
	}
	@Override
	public Adapter createSignalAdapter(){
		if(signalItemProvider == null){
			signalItemProvider = new SignalItemProvider(this){
				@Override
				public Collection<?> getChildren(Object object){
					return getChildrenToDisplay(super.getChildren(object), (Element) object);
				}
				@Override
				public String getText(Object object){
					if(object instanceof Signal){
						Signal c = (Signal) object;
						if(StereotypesHelper.hasStereotype(c, StereotypeNames.NOTIFICATION)){
							return "<Notification> " + c.getName();
						}else{
							return super.getText(object);
						}
					}
					return super.getText(object);
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return signalItemProvider;
	}
	public Adapter createStereotypeApplicationAdapter(){
		if(stereotypeApplicationItemProvider == null){
			stereotypeApplicationItemProvider = new StereotypeApplicationItemProvider(this){
				protected Command createAddCommand(EditingDomain domain,EObject owner,EStructuralFeature feature,Collection<?> collection,int index){
					return new AddCommand(domain, owner, feature, collection, index){
						@Override
						public void doExecute(){
							super.doExecute();
						}
						@Override
						public Collection<?> doGetAffectedObjects(){
							return Collections.singleton(UMLUtil.getBaseElement(owner));
						}
					};
				}
				@Override
				public String getText(Object object){
					EObject eObject = (EObject) object;
					EClass eClass = eObject.eClass();
					for(EAttribute att:eClass.getEAllAttributes()){
						if(att.getName().equalsIgnoreCase("name")){
							Object name = eObject.eGet(att);
							return "<" + format(capName(eClass.getName()), ' ') + "> " + name;
						}
					}
					return format(capName(eClass.getName()), ' ');
				}
				@Override
				public void notifyChanged(Notification notification){
					boolean labelUpdate = notification.getFeature() instanceof EAttribute
							&& ((EAttribute) notification.getFeature()).getName().equalsIgnoreCase("name");
					fireNotifyChanged(new ViewerNotification(notification, UMLUtil.getBaseElement((EObject) notification.getNotifier()), true,
							labelUpdate));
				}
				@Override
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return stereotypeApplicationItemProvider;
	}
	@SuppressWarnings("rawtypes")
	public static Command factorRemovalFromAppliedStereotypes(EditingDomain domain,CommandParameter commandParameter,Command result){
		if(result == UnexecutableCommand.INSTANCE){
			Element element = (Element) commandParameter.getEOwner();
			CompoundCommand removeCommand = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL);
			for(EObject sa:element.getStereotypeApplications()){
				for(Object object:commandParameter.getCollection()){
					for(EReference ref:sa.eClass().getEAllContainments()){
						Object eGet = sa.eGet(ref);
						if(eGet == object){
							return SetCommand.create(domain, sa, ref, SetCommand.UNSET_VALUE);
						}else if(eGet instanceof EList){
							if(((EList) eGet).contains(object)){
								removeCommand.append(RemoveCommand.create(domain, sa, ref, object));
							}
						}
					}
				}
			}
			result = removeCommand;
		}
		return result;
	};
	@SuppressWarnings("unchecked")
	static Collection<Object> getChildrenToDisplay(Collection<?> a,Element e){
		Collection<Object> children = new HashSet<Object>(a);
		EAnnotation ann = StereotypesHelper.getNumlAnnotation(e);
		if(ann != null && ann.getContents().size() > 0){
			for(EObject eObject:e.getStereotypeApplications()){
				for(EReference ref:eObject.eClass().getEAllReferences()){
					Object eGet = eObject.eGet(ref);
					if(eGet != null){
						if(eGet instanceof List){
							for(EObject eo:(List<EObject>) eGet){
								if(ann.getContents().contains(eo)){
									children.add((EObject) eo);
								}
							}
						}else{
							if(ann.getContents().contains(eGet)){
								children.add((EObject) eGet);
							}
						}
					}
				}
			}
			return children;
		}else{
			return children;
		}
	}
}
