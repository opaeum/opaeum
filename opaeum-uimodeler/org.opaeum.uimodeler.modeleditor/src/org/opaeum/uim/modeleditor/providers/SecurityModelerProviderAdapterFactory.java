/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.uim.modeleditor.providers;

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
import org.opaeum.uim.security.util.SecurityAdapterFactory;
import org.topcased.modeler.providers.ILabelFeatureProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers. The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}. The adapters also support Eclipse property sheets. Note that most of the adapters are shared among multiple instances.
 * 
 * @generated
 */
public class SecurityModelerProviderAdapterFactory extends SecurityAdapterFactory implements ComposeableAdapterFactory,IChangeNotifier,IDisposable{
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
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.security.EditableSecureObject} instances.
	 * 
	 * @generated
	 */
	private EditableSecureObjectModelerProvider editablesecureobjectModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.security.SecureObject} instances.
	 * 
	 * @generated
	 */
	private SecureObjectModelerProvider secureobjectModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.security.RequiredRole} instances.
	 * 
	 * @generated
	 */
	private RequiredRoleModelerProvider requiredroleModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.security.WorkspaceSecurityConstraint} instances.
	 * 
	 * @generated
	 */
	private WorkspaceSecurityConstraintModelerProvider workspacesecurityconstraintModelerProvider;
	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.security.SecurityConstraint} instances.
	 * 
	 * @generated
	 */
	private SecurityConstraintModelerProvider securityconstraintModelerProvider;
	/**
	 * This constructs an instance.
	 * 
	 * @generated
	 */
	public SecurityModelerProviderAdapterFactory(){
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
	 * This creates an adapter for a {@link org.opaeum.uim.security.EditableSecureObject}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createEditableSecureObjectAdapter(){
		if(editablesecureobjectModelerProvider == null){
			editablesecureobjectModelerProvider = new EditableSecureObjectModelerProvider(this);
		}
		return editablesecureobjectModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.opaeum.uim.security.SecureObject}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createSecureObjectAdapter(){
		if(secureobjectModelerProvider == null){
			secureobjectModelerProvider = new SecureObjectModelerProvider(this);
		}
		return secureobjectModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.opaeum.uim.security.RequiredRole}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createRequiredRoleAdapter(){
		if(requiredroleModelerProvider == null){
			requiredroleModelerProvider = new RequiredRoleModelerProvider(this);
		}
		return requiredroleModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.opaeum.uim.security.WorkspaceSecurityConstraint}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createWorkspaceSecurityConstraintAdapter(){
		if(workspacesecurityconstraintModelerProvider == null){
			workspacesecurityconstraintModelerProvider = new WorkspaceSecurityConstraintModelerProvider(this);
		}
		return workspacesecurityconstraintModelerProvider;
	}
	/**
	 * This creates an adapter for a {@link org.opaeum.uim.security.SecurityConstraint}.
	 *
	 * @return the Adapter
	 * @generated
	 */
	public Adapter createSecurityConstraintAdapter(){
		if(securityconstraintModelerProvider == null){
			securityconstraintModelerProvider = new SecurityConstraintModelerProvider(this);
		}
		return securityconstraintModelerProvider;
	}
	/**
	 * This disposes all of the item providers created by this factory.
	 * 
	 * @generated
	 */
	public void dispose(){
		if(editablesecureobjectModelerProvider != null){
			editablesecureobjectModelerProvider.dispose();
		}
		if(secureobjectModelerProvider != null){
			secureobjectModelerProvider.dispose();
		}
		if(requiredroleModelerProvider != null){
			requiredroleModelerProvider.dispose();
		}
		if(workspacesecurityconstraintModelerProvider != null){
			workspacesecurityconstraintModelerProvider.dispose();
		}
		if(securityconstraintModelerProvider != null){
			securityconstraintModelerProvider.dispose();
		}
	}
}
