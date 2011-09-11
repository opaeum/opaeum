package org.nakeduml.topcased.uml.editor;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Component;
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
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.topcased.modeler.uml.editor.outline.CustomCallBehaviorActionItemProvider;

public class NakedUmlItemProviderAdapterFactory extends UMLItemProviderAdapterFactory{
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
}
