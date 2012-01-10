package org.opaeum.topcased.uml.editor;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.edit.providers.ActivityItemProvider;
import org.eclipse.uml2.uml.edit.providers.ClassItemProvider;
import org.eclipse.uml2.uml.edit.providers.ComponentItemProvider;
import org.eclipse.uml2.uml.edit.providers.ConnectorItemProvider;
import org.eclipse.uml2.uml.edit.providers.ElementImportItemProvider;
import org.eclipse.uml2.uml.edit.providers.ExpansionNodeItemProvider;
import org.eclipse.uml2.uml.edit.providers.ExpansionRegionItemProvider;
import org.eclipse.uml2.uml.edit.providers.InputPinItemProvider;
import org.eclipse.uml2.uml.edit.providers.InterfaceItemProvider;
import org.eclipse.uml2.uml.edit.providers.InterfaceRealizationItemProvider;
import org.eclipse.uml2.uml.edit.providers.OpaqueActionItemProvider;
import org.eclipse.uml2.uml.edit.providers.OperationItemProvider;
import org.eclipse.uml2.uml.edit.providers.OutputPinItemProvider;
import org.eclipse.uml2.uml.edit.providers.PortItemProvider;
import org.eclipse.uml2.uml.edit.providers.PropertyItemProvider;
import org.eclipse.uml2.uml.edit.providers.ReceptionItemProvider;
import org.eclipse.uml2.uml.edit.providers.SignalItemProvider;
import org.eclipse.uml2.uml.edit.providers.StateItemProvider;
import org.eclipse.uml2.uml.edit.providers.StateMachineItemProvider;
import org.eclipse.uml2.uml.edit.providers.StereotypeApplicationItemProvider;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.eclipse.uml2.uml.edit.providers.ValuePinItemProvider;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.topcased.modeler.uml.editor.outline.CustomCallBehaviorActionItemProvider;

public class OpaeumItemProviderAdapterFactory extends UMLItemProviderAdapterFactory{
	public OpaeumItemProviderAdapterFactory(){
	}
	@Override
	public Adapter createReceptionAdapter(){
		if(receptionItemProvider == null){
			receptionItemProvider = new ReceptionItemProvider(this){
				@Override
				public String getText(Object object){
					Reception reception = (Reception) object;
					if(reception.getSignal() == null){
						return "<Receives> nothing";
					}else{
						return "<Receives> " + reception.getSignal().getName();
					}
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
				public String getText(Object object){
					if(object instanceof Connector){
						Connector a = (Connector) object;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_CHANNEL)){
							return "<Business Channel> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.DELEGATION)){
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
				public String getText(Object object){
					if(object instanceof Property){
						Property a = (Property) object;
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.PARTICIPANT_REFERENCE)){
							return "<Participant Reference> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_ROLE_CONTAINMENT)){
							return "<Business Role Containment> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.DIMENSION)){
							return "<Dimension> " + a.getName();
						}else if(StereotypesHelper.hasStereotype(a, StereotypeNames.MEASURE)){
							return "<Fact> " + a.getName();
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
		return propertyItemProvider;
	}
	@Override
	public Adapter createPortAdapter(){
		if(portItemProvider == null){
			portItemProvider = new PortItemProvider(this){
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
			callBehaviorActionItemProvider = new CustomCallBehaviorActionItemProvider(this){
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
			};
		}
		return callBehaviorActionItemProvider;
	}
	public Adapter createActivityAdapter(){
		if(activityItemProvider == null){
			activityItemProvider = new ActivityItemProvider(this){
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
						if(StereotypesHelper.hasStereotype(a, StereotypeNames.BUSINESS_ROLE)){
							return "<Business Role> " + a.getName();
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
	public Adapter createInterfaceAdapter(){
		if(interfaceItemProvider == null){
			interfaceItemProvider = new InterfaceItemProvider(this){
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
	public Adapter createExpansionRegionAdapter(){
		if(expansionRegionItemProvider == null){
			expansionRegionItemProvider = new ExpansionRegionItemProvider(this){
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
	public Adapter createStateMachineAdapter(){
		if(stateMachineItemProvider == null){
			stateMachineItemProvider = new StateMachineItemProvider(this){
				@Override
				public String getText(Object object){
					if(object instanceof StateMachine){
						StateMachine c = (StateMachine) object;
						if(StereotypesHelper.hasStereotype(c, StereotypeNames.SCREEN_FLOW)){
							return "<Screen Flow> " + c.getName();
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
				protected Command factorRemoveCommand(EditingDomain domain,CommandParameter commandParameter){
					Command result = super.factorRemoveCommand(domain, commandParameter);
					return factorRemovalFromAppliedStereotypes(domain, commandParameter, result);
				}
			};
		}
		return stateItemProvider;
	}
	@Override
	public Adapter createSignalAdapter(){
		if(signalItemProvider == null){
			signalItemProvider = new SignalItemProvider(this){
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
					boolean labelUpdate = notification.getFeature() instanceof EAttribute && ((EAttribute) notification.getFeature()).getName().equalsIgnoreCase("name");
					fireNotifyChanged(new ViewerNotification(notification, UMLUtil.getBaseElement((EObject) notification.getNotifier()), true, labelUpdate));
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
								removeCommand.append(RemoveCommand.create(domain, sa, ref, element));
							}
						}
					}
				}
			}
			result = removeCommand;
		}
		return result;
	};
}
