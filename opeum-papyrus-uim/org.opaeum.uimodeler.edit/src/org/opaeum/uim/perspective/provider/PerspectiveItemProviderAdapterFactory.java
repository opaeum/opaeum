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
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.PerspectiveConfiguration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PerspectiveConfigurationItemProvider perspectiveConfigurationItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.PerspectiveConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPerspectiveConfigurationAdapter() {
		if (perspectiveConfigurationItemProvider == null) {
			perspectiveConfigurationItemProvider = new PerspectiveConfigurationItemProvider(this);
		}

		return perspectiveConfigurationItemProvider;
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
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.ExplorerClassConstraint} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerClassConstraintItemProvider explorerClassConstraintItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.ExplorerClassConstraint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExplorerClassConstraintAdapter() {
		if (explorerClassConstraintItemProvider == null) {
			explorerClassConstraintItemProvider = new ExplorerClassConstraintItemProvider(this);
		}

		return explorerClassConstraintItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.ExplorerPropertyConstraint} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerPropertyConstraintItemProvider explorerPropertyConstraintItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.ExplorerPropertyConstraint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExplorerPropertyConstraintAdapter() {
		if (explorerPropertyConstraintItemProvider == null) {
			explorerPropertyConstraintItemProvider = new ExplorerPropertyConstraintItemProvider(this);
		}

		return explorerPropertyConstraintItemProvider;
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
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.ExplorerOperationConstraint} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerOperationConstraintItemProvider explorerOperationConstraintItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.ExplorerOperationConstraint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExplorerOperationConstraintAdapter() {
		if (explorerOperationConstraintItemProvider == null) {
			explorerOperationConstraintItemProvider = new ExplorerOperationConstraintItemProvider(this);
		}

		return explorerOperationConstraintItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplorerBehaviorConstraintItemProvider explorerBehaviorConstraintItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExplorerBehaviorConstraintAdapter() {
		if (explorerBehaviorConstraintItemProvider == null) {
			explorerBehaviorConstraintItemProvider = new ExplorerBehaviorConstraintItemProvider(this);
		}

		return explorerBehaviorConstraintItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.InboxConfiguration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InboxConfigurationItemProvider inboxConfigurationItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.InboxConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createInboxConfigurationAdapter() {
		if (inboxConfigurationItemProvider == null) {
			inboxConfigurationItemProvider = new InboxConfigurationItemProvider(this);
		}

		return inboxConfigurationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.opaeum.uim.perspective.OutboxConfiguration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutboxConfigurationItemProvider outboxConfigurationItemProvider;

	/**
	 * This creates an adapter for a {@link org.opaeum.uim.perspective.OutboxConfiguration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOutboxConfigurationAdapter() {
		if (outboxConfigurationItemProvider == null) {
			outboxConfigurationItemProvider = new OutboxConfigurationItemProvider(this);
		}

		return outboxConfigurationItemProvider;
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
		if (perspectiveConfigurationItemProvider != null) perspectiveConfigurationItemProvider.dispose();
		if (explorerConfigurationItemProvider != null) explorerConfigurationItemProvider.dispose();
		if (explorerClassConstraintItemProvider != null) explorerClassConstraintItemProvider.dispose();
		if (explorerPropertyConstraintItemProvider != null) explorerPropertyConstraintItemProvider.dispose();
		if (editorConfigurationItemProvider != null) editorConfigurationItemProvider.dispose();
		if (propertiesConfigurationItemProvider != null) propertiesConfigurationItemProvider.dispose();
		if (explorerOperationConstraintItemProvider != null) explorerOperationConstraintItemProvider.dispose();
		if (explorerBehaviorConstraintItemProvider != null) explorerBehaviorConstraintItemProvider.dispose();
		if (inboxConfigurationItemProvider != null) inboxConfigurationItemProvider.dispose();
		if (outboxConfigurationItemProvider != null) outboxConfigurationItemProvider.dispose();
	}

}
