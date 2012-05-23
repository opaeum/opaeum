/**
 */
package org.opaeum.uim.perspective.provider;

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
import org.opaeum.uim.perspective.util.PerspectiveAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PerspectiveItemProviderAdapterFactory extends PerspectiveAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
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
	public PerspectiveItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.UimPerspective} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimPerspectiveItemProvider uimPerspectiveItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.UimPerspective}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUimPerspectiveAdapter() {
		if (uimPerspectiveItemProvider == null) {
			uimPerspectiveItemProvider = new UimPerspectiveItemProvider(this);
		}

		return uimPerspectiveItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.ExplorerConfiguration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerConfigurationItemProvider explorerConfigurationItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.ExplorerConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExplorerConfigurationAdapter() {
		if (explorerConfigurationItemProvider == null) {
			explorerConfigurationItemProvider = new ExplorerConfigurationItemProvider(this);
		}

		return explorerConfigurationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.ExplorerClassConfiguration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerClassConfigurationItemProvider explorerClassConfigurationItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.ExplorerClassConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExplorerClassConfigurationAdapter() {
		if (explorerClassConfigurationItemProvider == null) {
			explorerClassConfigurationItemProvider = new ExplorerClassConfigurationItemProvider(this);
		}

		return explorerClassConfigurationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerPropertyConfigurationItemProvider explorerPropertyConfigurationItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.ExplorerPropertyConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExplorerPropertyConfigurationAdapter() {
		if (explorerPropertyConfigurationItemProvider == null) {
			explorerPropertyConfigurationItemProvider = new ExplorerPropertyConfigurationItemProvider(this);
		}

		return explorerPropertyConfigurationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.EditorConfiguration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EditorConfigurationItemProvider editorConfigurationItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.EditorConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEditorConfigurationAdapter() {
		if (editorConfigurationItemProvider == null) {
			editorConfigurationItemProvider = new EditorConfigurationItemProvider(this);
		}

		return editorConfigurationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.PropertiesConfiguration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertiesConfigurationItemProvider propertiesConfigurationItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.PropertiesConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPropertiesConfigurationAdapter() {
		if (propertiesConfigurationItemProvider == null) {
			propertiesConfigurationItemProvider = new PropertiesConfigurationItemProvider(this);
		}

		return propertiesConfigurationItemProvider;
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
		if (uimPerspectiveItemProvider != null) uimPerspectiveItemProvider.dispose();
		if (explorerConfigurationItemProvider != null) explorerConfigurationItemProvider.dispose();
		if (explorerClassConfigurationItemProvider != null) explorerClassConfigurationItemProvider.dispose();
		if (explorerPropertyConfigurationItemProvider != null) explorerPropertyConfigurationItemProvider.dispose();
		if (editorConfigurationItemProvider != null) editorConfigurationItemProvider.dispose();
		if (propertiesConfigurationItemProvider != null) propertiesConfigurationItemProvider.dispose();
	}

}