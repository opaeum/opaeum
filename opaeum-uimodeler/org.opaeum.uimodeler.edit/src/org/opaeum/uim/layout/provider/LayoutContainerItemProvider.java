/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.layout.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.opaeum.uim.layout.LayoutContainer;
import org.opaeum.uim.layout.LayoutFactory;
import org.opaeum.uim.layout.LayoutPackage;
import org.opaeum.uim.provider.UimContainerItemProvider;
import org.opaeum.uim.provider.UimEditPlugin;
import org.opaeum.uim.security.SecurityPackage;

/**
 * This is the item provider adapter for a {@link org.opaeum.uim.layout.LayoutContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LayoutContainerItemProvider
	extends UimContainerItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayoutContainerItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(LayoutPackage.Literals.LAYOUT_CONTAINER__LAYOUT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((LayoutContainer)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_LayoutContainer_type") :
			getString("_UI_LayoutContainer_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(LayoutContainer.class)) {
			case LayoutPackage.LAYOUT_CONTAINER__LAYOUT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.LAYOUT_CONTAINER__LAYOUT,
				 LayoutFactory.eINSTANCE.createUimLayout()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.LAYOUT_CONTAINER__LAYOUT,
				 LayoutFactory.eINSTANCE.createUimColumnLayout()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.LAYOUT_CONTAINER__LAYOUT,
				 LayoutFactory.eINSTANCE.createUimFullLayout()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.LAYOUT_CONTAINER__LAYOUT,
				 LayoutFactory.eINSTANCE.createUimXYLayout()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.LAYOUT_CONTAINER__LAYOUT,
				 LayoutFactory.eINSTANCE.createUimBorderLayout()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.LAYOUT_CONTAINER__LAYOUT,
				 LayoutFactory.eINSTANCE.createUimToolbarLayout()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.LAYOUT_CONTAINER__LAYOUT,
				 LayoutFactory.eINSTANCE.createUimGridLayout()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == SecurityPackage.Literals.SECURE_OBJECT__VISIBILITY ||
			childFeature == SecurityPackage.Literals.EDITABLE_SECURE_OBJECT__EDITABILITY;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return UimEditPlugin.INSTANCE;
	}

}
