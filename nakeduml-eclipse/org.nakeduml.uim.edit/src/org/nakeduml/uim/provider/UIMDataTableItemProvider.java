/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.provider;


import java.util.Collection;
import java.util.List;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.nakeduml.uim.UIMDataTable;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMPackage;

/**
 * This is the item provider adapter for a {@link org.nakeduml.uim.UIMDataTable} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UIMDataTableItemProvider
	extends ItemProviderAdapter
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
	public UIMDataTableItemProvider(AdapterFactory adapterFactory) {
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

			addDetailPanelsPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Detail Panels feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDetailPanelsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MasterComponent_detailPanels_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MasterComponent_detailPanels_feature", "_UI_MasterComponent_type"),
				 UIMPackage.Literals.MASTER_COMPONENT__DETAIL_PANELS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_UserInteractionElement_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_UserInteractionElement_name_feature", "_UI_UserInteractionElement_type"),
				 UIMPackage.Literals.USER_INTERACTION_ELEMENT__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
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
			childrenFeatures.add(UIMPackage.Literals.UIM_COMPONENT__SECURITY_ON_VISIBILITY);
			childrenFeatures.add(UIMPackage.Literals.UIM_CONTAINER__SECURITY_ON_EDITABILITY);
			childrenFeatures.add(UIMPackage.Literals.UIM_CONTAINER__CHILDREN);
			childrenFeatures.add(UIMPackage.Literals.UIM_DATA_TABLE__BINDING);
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
	 * This returns UIMDataTable.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/UIMDataTable"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((UIMDataTable)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_UIMDataTable_type") :
			getString("_UI_UIMDataTable_type") + " " + label;
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

		switch (notification.getFeatureID(UIMDataTable.class)) {
			case UIMPackage.UIM_DATA_TABLE__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY:
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY:
			case UIMPackage.UIM_DATA_TABLE__CHILDREN:
			case UIMPackage.UIM_DATA_TABLE__BINDING:
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
				(UIMPackage.Literals.UIM_COMPONENT__SECURITY_ON_VISIBILITY,
				 UIMFactory.eINSTANCE.createChildSecurityConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__SECURITY_ON_EDITABILITY,
				 UIMFactory.eINSTANCE.createChildSecurityConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMField()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMContainer()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMPanel()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createOperationAction()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createNavigationToOperation()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createBuiltInAction()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createNavigationToEntity()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createTransitionAction()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMLayout()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMGridLayout()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMDataTable()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMDataColumn()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createFormPanel()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createDetailPanel()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMTabPanel()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMTab()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMToolbarLayout()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMBorderLayout()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UIMFactory.eINSTANCE.createUIMXYLayout()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_DATA_TABLE__BINDING,
				 UIMFactory.eINSTANCE.createTableBinding()));
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
			childFeature == UIMPackage.Literals.UIM_COMPONENT__SECURITY_ON_VISIBILITY ||
			childFeature == UIMPackage.Literals.UIM_CONTAINER__SECURITY_ON_EDITABILITY;

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
		return UIMEditPlugin.INSTANCE;
	}

}
