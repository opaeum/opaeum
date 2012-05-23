/**
 */
package org.opaeum.uim.action.provider;

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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.opaeum.uim.action.util.ActionAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ActionItemProviderAdapterFactory extends ActionAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.action.BuiltInActionButton} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuiltInActionButtonItemProvider builtInActionButtonItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.action.BuiltInActionButton}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBuiltInActionButtonAdapter() {
		if (builtInActionButtonItemProvider == null) {
			builtInActionButtonItemProvider = new BuiltInActionButtonItemProvider(this);
		}

		return builtInActionButtonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.action.TransitionButton} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionButtonItemProvider transitionButtonItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.action.TransitionButton}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTransitionButtonAdapter() {
		if (transitionButtonItemProvider == null) {
			transitionButtonItemProvider = new TransitionButtonItemProvider(this);
		}

		return transitionButtonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.action.LinkToQuery} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkToQueryItemProvider linkToQueryItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.action.LinkToQuery}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createLinkToQueryAdapter() {
		if (linkToQueryItemProvider == null) {
			linkToQueryItemProvider = new LinkToQueryItemProvider(this);
		}

		return linkToQueryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.action.OperationButton} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationButtonItemProvider operationButtonItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.action.OperationButton}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationButtonAdapter() {
		if (operationButtonItemProvider == null) {
			operationButtonItemProvider = new OperationButtonItemProvider(this);
		}

		return operationButtonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.action.OperationPopup} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationPopupItemProvider operationPopupItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.action.OperationPopup}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationPopupAdapter() {
		if (operationPopupItemProvider == null) {
			operationPopupItemProvider = new OperationPopupItemProvider(this);
		}

		return operationPopupItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.action.OperationPopupPage} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationPopupPageItemProvider operationPopupPageItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.action.OperationPopupPage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationPopupPageAdapter() {
		if (operationPopupPageItemProvider == null) {
			operationPopupPageItemProvider = new OperationPopupPageItemProvider(this);
		}

		return operationPopupPageItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.action.BuiltInLink} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuiltInLinkItemProvider builtInLinkItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.action.BuiltInLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBuiltInLinkAdapter() {
		if (builtInLinkItemProvider == null) {
			builtInLinkItemProvider = new BuiltInLinkItemProvider(this);
		}

		return builtInLinkItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (builtInActionButtonItemProvider != null) builtInActionButtonItemProvider.dispose();
		if (transitionButtonItemProvider != null) transitionButtonItemProvider.dispose();
		if (linkToQueryItemProvider != null) linkToQueryItemProvider.dispose();
		if (operationButtonItemProvider != null) operationButtonItemProvider.dispose();
		if (operationPopupItemProvider != null) operationPopupItemProvider.dispose();
		if (operationPopupPageItemProvider != null) operationPopupPageItemProvider.dispose();
		if (builtInLinkItemProvider != null) builtInLinkItemProvider.dispose();
	}

}