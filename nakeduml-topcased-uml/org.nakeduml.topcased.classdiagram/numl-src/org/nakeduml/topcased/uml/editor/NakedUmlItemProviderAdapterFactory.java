package org.nakeduml.topcased.uml.editor;

import java.util.Collection;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

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
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.edit.providers.ActivityItemProvider;
import org.eclipse.uml2.uml.edit.providers.ClassItemProvider;
import org.eclipse.uml2.uml.edit.providers.ComponentItemProvider;
import org.eclipse.uml2.uml.edit.providers.InterfaceItemProvider;
import org.eclipse.uml2.uml.edit.providers.SignalItemProvider;
import org.eclipse.uml2.uml.edit.providers.StateItemProvider;
import org.eclipse.uml2.uml.edit.providers.StateMachineItemProvider;
import org.eclipse.uml2.uml.edit.providers.StereotypeApplicationItemProvider;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.topcased.modeler.uml.editor.outline.CustomCallBehaviorActionItemProvider;

public class NakedUmlItemProviderAdapterFactory extends UMLItemProviderAdapterFactory{
	public NakedUmlItemProviderAdapterFactory(){
	}
	@Override
	public Adapter createCallBehaviorActionAdapter(){
		if(callBehaviorActionItemProvider == null){
			callBehaviorActionItemProvider = new CustomCallBehaviorActionItemProvider(this);
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
						if(StereotypesHelper.hasKeyword(a, StereotypeNames.BUSINES_PROCESS)){
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
					//					return overlayImage(object, NakedUmlPlugin.getDefault().getImageRegistry().getDescriptor("Actor")); //$NON-NLS-1$
				}
			};
		}
		return activityItemProvider;
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
				public String getText(Object object){
					if(object instanceof org.eclipse.uml2.uml.Class){
						org.eclipse.uml2.uml.Class a = (org.eclipse.uml2.uml.Class) object;
						if(StereotypesHelper.hasKeyword(a, StereotypeNames.BUSINESS_ROLE)){
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
						if(StereotypesHelper.hasKeyword(a, StereotypeNames.BUSINESS_SERVICE)){
							return "<Business Service> " + a.getName();
						}else if(StereotypesHelper.hasKeyword(a, StereotypeNames.HELPER)){
							return "<Helper> " + a.getName();
						}else{
							return "<Interface> " + a.getName();
						}
					}
					return super.getText(object);
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
						if(StereotypesHelper.hasKeyword(c, StereotypeNames.BUSINESS_COMPONENT)){
							return "<Business Component> " + c.getName();
						}
					}
					return super.getText(object);
				}
			};
		}
		return componentItemProvider;
	}
	@Override
	public Adapter createStateMachineAdapter(){
		if(stateMachineItemProvider == null){
			stateMachineItemProvider = new StateMachineItemProvider(this){
				@Override
				public String getText(Object object){
					if(object instanceof StateMachine){
						StateMachine c = (StateMachine) object;
						if(StereotypesHelper.hasKeyword(c, StereotypeNames.SCREEN_FLOW)){
							return "<Screen Flow> " + c.getName();
						}else{
							return "<Business Statemachine>" + c.getName();
						}
					}
					return super.getText(object);
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
						if(StereotypesHelper.hasKeyword(c, StereotypeNames.SCREEN)){
							return "<Screen> " + c.getName();
						}else{
							return super.getText(object);
						}
					}
					return super.getText(object);
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
						if(StereotypesHelper.hasKeyword(c, StereotypeNames.NOTIFICATION)){
							return "<Notification> " + c.getName();
						}else{
							return super.getText(object);
						}
					}
					return super.getText(object);
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
				public void notifyChanged(Notification notification) {
					boolean labelUpdate = notification.getFeature() instanceof EAttribute && ((EAttribute)notification.getFeature()).getName().equalsIgnoreCase("name");
					fireNotifyChanged(new ViewerNotification(notification, UMLUtil
						.getBaseElement((EObject) notification.getNotifier()), true, labelUpdate));
				}

			};
		}
		return stereotypeApplicationItemProvider;
	}
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
			result=removeCommand;
		}
		return result;
	};
}
