/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.modeleditor.providers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.nakeduml.uim.action.util.ActionAdapterFactory;
import org.topcased.modeler.providers.ILabelFeatureProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}. The adapters also support Eclipse property sheets. Note that most of the adapters are shared among multiple instances.
 * 
 * @generated
 */
public class ActionModelerProviderAdapterFactory extends ActionAdapterFactory implements ComposeableAdapterFactory,IChangeNotifier,IDisposable{
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * 
	 * @generated
	 */
	private ComposedAdapterFactory parentAdapterFactory;
	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * 
	 * @generated
	 */
	private IChangeNotifier changeNotifier = new ChangeNotifier();
	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * 
	 * @generated
	 */
	private Collection supportedTypes = new ArrayList();
	/**
	 * This keeps track of the one adapter used for all {@link org.nakeduml.uim.action.BuiltInAction} instances.
	 * 
	 * @generated
	 */
	private BuiltInActionModelerProvider builtinactionModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.nakeduml.uim.action.UimAction} instances.
	 * 
	 * @generated
	 */
	private UimActionModelerProvider uimactionModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.nakeduml.uim.action.TransitionAction} instances.
	 * 
	 * @generated
	 */
	private TransitionActionModelerProvider transitionactionModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.nakeduml.uim.action.NavigationToOperation} instances.
	 * 
	 * @generated
	 */
	private NavigationToOperationModelerProvider navigationtooperationModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.nakeduml.uim.action.OperationAction} instances.
	 * 
	 * @generated
	 */
	private OperationActionModelerProvider operationactionModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.nakeduml.uim.action.UimNavigation} instances.
	 * 
	 * @generated
	 */
	private UimNavigationModelerProvider uimnavigationModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.nakeduml.uim.action.NavigationToEntity} instances.
	 * 
	 * @generated
	 */
	private NavigationToEntityModelerProvider navigationtoentityModelerProvider;
	/**
	 * This constructs an instance.
	 * 
	 * @generated
	 */
	public ActionModelerProviderAdapterFactory(){
		supportedTypes.add(ILabelFeatureProvider.class);
	}
	/**
	 * This returns the root adapter factory that contains this factory.
	 *
	 * @return the root AdapterFactory
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory(){
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}
	/**
	 * This sets the composed adapter factory that contains this factory.
	 *
	 * @param parentAdapterFactory the new parent adapter factory
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory){
		this.parentAdapterFactory = parentAdapterFactory;
	}
	/**
	 * @param type the type to test
	 * @return true if supported
	 * 
	 * @generated
	 */
	public boolean isFactoryForType(Object type){
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}
	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 *
	 * @param notifier the notifier
	 * @param type the object to adapt
	 * @return the Adapter the created adatper
	 * @generated
	 */
	public Adapter adapt(Notifier notifier,Object type){
		return super.adapt(notifier, this);
	}
	/**
	 * @param object the object to adapt
	 * @param type the type to adapt
	 * @return the adapted Object
	 * @generated
	 */
	public Object adapt(Object object,Object type){
		if(isFactoryForType(type)){
			Object adapter = super.adapt(object, type);
			if(!(type instanceof Class) || (((Class) type).isInstance(adapter))){
				return adapter;
			}
		}
		return null;
	}
	/**
	 * This adds a listener.
	 *
	 * @param notifyChangedListener the listener to add
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener){
		changeNotifier.addListener(notifyChangedListener);
	}
	/**
	 * This removes a listener.
	 *
	 * @param notifyChangedListener the listener to remove
	 * 
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener){
		changeNotifier.removeListener(notifyChangedListener);
	}
	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 *
	 * @param notification the notification to fire
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification){
		changeNotifier.fireNotifyChanged(notification);
		if(parentAdapterFactory != null){
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}
	/**
	 * This creates an adapter for a {@link org.nakeduml.uim.action.BuiltInAction}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createBuiltInActionAdapter(){
		if(builtinactionModelerProvider == null){
			builtinactionModelerProvider = new BuiltInActionModelerProvider(this);
		}
		return builtinactionModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.nakeduml.uim.action.UimAction}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createUimActionAdapter(){
		if(uimactionModelerProvider == null){
			uimactionModelerProvider = new UimActionModelerProvider(this);
		}
		return uimactionModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.nakeduml.uim.action.TransitionAction}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createTransitionActionAdapter(){
		if(transitionactionModelerProvider == null){
			transitionactionModelerProvider = new TransitionActionModelerProvider(this);
		}
		return transitionactionModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.nakeduml.uim.action.NavigationToOperation}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createNavigationToOperationAdapter(){
		if(navigationtooperationModelerProvider == null){
			navigationtooperationModelerProvider = new NavigationToOperationModelerProvider(this);
		}
		return navigationtooperationModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.nakeduml.uim.action.OperationAction}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createOperationActionAdapter(){
		if(operationactionModelerProvider == null){
			operationactionModelerProvider = new OperationActionModelerProvider(this);
		}
		return operationactionModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.nakeduml.uim.action.UimNavigation}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createUimNavigationAdapter(){
		if(uimnavigationModelerProvider == null){
			uimnavigationModelerProvider = new UimNavigationModelerProvider(this);
		}
		return uimnavigationModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.nakeduml.uim.action.NavigationToEntity}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createNavigationToEntityAdapter(){
		if(navigationtoentityModelerProvider == null){
			navigationtoentityModelerProvider = new NavigationToEntityModelerProvider(this);
		}
		return navigationtoentityModelerProvider;
	}
	/**
	 * This disposes all of the item providers created by this factory.
	 * 
	 * @generated
	 */
	public void dispose(){
		if(builtinactionModelerProvider != null){
			builtinactionModelerProvider.dispose();
		}
		if(uimactionModelerProvider != null){
			uimactionModelerProvider.dispose();
		}
		if(transitionactionModelerProvider != null){
			transitionactionModelerProvider.dispose();
		}
		if(navigationtooperationModelerProvider != null){
			navigationtooperationModelerProvider.dispose();
		}
		if(operationactionModelerProvider != null){
			operationactionModelerProvider.dispose();
		}
		if(uimnavigationModelerProvider != null){
			uimnavigationModelerProvider.dispose();
		}
		if(navigationtoentityModelerProvider != null){
			navigationtoentityModelerProvider.dispose();
		}
	}
}
