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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.nakeduml.uim.UIMContainer;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMPackage;

/**
 * This is the item provider adapter for a {@link org.nakeduml.uim.UIMContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UIMContainerItemProvider
	extends UIMComponentItemProvider
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
	public UIMContainerItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(UIMPackage.Literals.UIM_CONTAINER__SECURITY_ON_EDITABILITY);
			childrenFeatures.add(UIMPackage.Literals.UIM_CONTAINER__CHILDREN);
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
	 * This returns UIMContainer.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/UIMContainer"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((UIMContainer)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_UIMContainer_type") :
			getString("_UI_UIMContainer_type") + " " + label;
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

		switch (notification.getFeatureID(UIMContainer.class)) {
			case UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY:
			case UIMPackage.UIM_CONTAINER__CHILDREN:
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

}
