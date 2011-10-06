/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.layout.provider;

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
import org.opaeum.uim.layout.util.LayoutAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LayoutItemProviderAdapterFactory extends LayoutAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
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
	public LayoutItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.layout.UimColumnLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimColumnLayoutItemProvider uimColumnLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.layout.UimColumnLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUimColumnLayoutAdapter() {
		if (uimColumnLayoutItemProvider == null) {
			uimColumnLayoutItemProvider = new UimColumnLayoutItemProvider(this);
		}

		return uimColumnLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.layout.UimFullLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimFullLayoutItemProvider uimFullLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.layout.UimFullLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUimFullLayoutAdapter() {
		if (uimFullLayoutItemProvider == null) {
			uimFullLayoutItemProvider = new UimFullLayoutItemProvider(this);
		}

		return uimFullLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.layout.UimXYLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimXYLayoutItemProvider uimXYLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.layout.UimXYLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUimXYLayoutAdapter() {
		if (uimXYLayoutItemProvider == null) {
			uimXYLayoutItemProvider = new UimXYLayoutItemProvider(this);
		}

		return uimXYLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.layout.UimBorderLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimBorderLayoutItemProvider uimBorderLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.layout.UimBorderLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUimBorderLayoutAdapter() {
		if (uimBorderLayoutItemProvider == null) {
			uimBorderLayoutItemProvider = new UimBorderLayoutItemProvider(this);
		}

		return uimBorderLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.layout.UimToolbarLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimToolbarLayoutItemProvider uimToolbarLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.layout.UimToolbarLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUimToolbarLayoutAdapter() {
		if (uimToolbarLayoutItemProvider == null) {
			uimToolbarLayoutItemProvider = new UimToolbarLayoutItemProvider(this);
		}

		return uimToolbarLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.layout.UimLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimLayoutItemProvider uimLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.layout.UimLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUimLayoutAdapter() {
		if (uimLayoutItemProvider == null) {
			uimLayoutItemProvider = new UimLayoutItemProvider(this);
		}

		return uimLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.layout.UimGridLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimGridLayoutItemProvider uimGridLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.layout.UimGridLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUimGridLayoutAdapter() {
		if (uimGridLayoutItemProvider == null) {
			uimGridLayoutItemProvider = new UimGridLayoutItemProvider(this);
		}

		return uimGridLayoutItemProvider;
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
		if (uimColumnLayoutItemProvider != null) uimColumnLayoutItemProvider.dispose();
		if (uimFullLayoutItemProvider != null) uimFullLayoutItemProvider.dispose();
		if (uimXYLayoutItemProvider != null) uimXYLayoutItemProvider.dispose();
		if (uimBorderLayoutItemProvider != null) uimBorderLayoutItemProvider.dispose();
		if (uimToolbarLayoutItemProvider != null) uimToolbarLayoutItemProvider.dispose();
		if (uimLayoutItemProvider != null) uimLayoutItemProvider.dispose();
		if (uimGridLayoutItemProvider != null) uimGridLayoutItemProvider.dispose();
	}

}
