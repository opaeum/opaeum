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

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMField;
import org.nakeduml.uim.UIMPackage;

/**
 * This is the item provider adapter for a {@link org.nakeduml.uim.UIMField} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UIMFieldItemProvider
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
	public UIMFieldItemProvider(AdapterFactory adapterFactory) {
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

			addControlKindPropertyDescriptor(object);
			addLabelWidthPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Control Kind feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addControlKindPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_UIMField_controlKind_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_UIMField_controlKind_feature", "_UI_UIMField_type"),
				 UIMPackage.Literals.UIM_FIELD__CONTROL_KIND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Label Width feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelWidthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_UIMField_labelWidth_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_UIMField_labelWidth_feature", "_UI_UIMField_type"),
				 UIMPackage.Literals.UIM_FIELD__LABEL_WIDTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
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
			childrenFeatures.add(UIMPackage.Literals.UIM_FIELD__CONTROL);
			childrenFeatures.add(UIMPackage.Literals.UIM_FIELD__BINDING);
			childrenFeatures.add(UIMPackage.Literals.UIM_FIELD__SECURITY_ON_EDITABILITY);
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
	 * This returns UIMField.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/UIMField"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((UIMField)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_UIMField_type") :
			getString("_UI_UIMField_type") + " " + label;
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

		switch (notification.getFeatureID(UIMField.class)) {
			case UIMPackage.UIM_FIELD__CONTROL_KIND:
			case UIMPackage.UIM_FIELD__LABEL_WIDTH:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case UIMPackage.UIM_FIELD__CONTROL:
			case UIMPackage.UIM_FIELD__BINDING:
			case UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY:
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
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMControl()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMCheckBox()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMLookup()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMText()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMTextArea()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMDropdown()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMDatePopup()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMSingleSelectListBox()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMSingleSelectTreeView()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMMultiSelectTreeView()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMMultiSelectListBox()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMMultiSelectPopupSearch()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMSingleSelectPopupSearch()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMToggleButton()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__CONTROL,
				 UIMFactory.eINSTANCE.createUIMNumberScroller()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__BINDING,
				 UIMFactory.eINSTANCE.createFieldBinding()));

		newChildDescriptors.add
			(createChildParameter
				(UIMPackage.Literals.UIM_FIELD__SECURITY_ON_EDITABILITY,
				 UIMFactory.eINSTANCE.createChildSecurityConstraint()));
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
			childFeature == UIMPackage.Literals.UIM_FIELD__SECURITY_ON_EDITABILITY;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
