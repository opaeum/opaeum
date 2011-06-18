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
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPackage;

import org.nakeduml.uim.binding.BindingFactory;

import org.nakeduml.uim.control.ControlFactory;

import org.nakeduml.uim.security.SecurityPackage;

import org.nakeduml.uim.security.provider.EditableSecureObjectItemProvider;

/**
 * This is the item provider adapter for a {@link org.nakeduml.uim.UimField} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UimFieldItemProvider
	extends EditableSecureObjectItemProvider
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
	public UimFieldItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
			addControlKindPropertyDescriptor(object);
			addLabelWidthPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
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
				 UimPackage.Literals.USER_INTERACTION_ELEMENT__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
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
				 getString("_UI_UimField_controlKind_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_UimField_controlKind_feature", "_UI_UimField_type"),
				 UimPackage.Literals.UIM_FIELD__CONTROL_KIND,
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
				 getString("_UI_UimField_labelWidth_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_UimField_labelWidth_feature", "_UI_UimField_type"),
				 UimPackage.Literals.UIM_FIELD__LABEL_WIDTH,
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
			childrenFeatures.add(UimPackage.Literals.UIM_FIELD__CONTROL);
			childrenFeatures.add(UimPackage.Literals.UIM_FIELD__BINDING);
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
	 * This returns UimField.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/UimField"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((UimField)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_UimField_type") :
			getString("_UI_UimField_type") + " " + label;
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

		switch (notification.getFeatureID(UimField.class)) {
			case UimPackage.UIM_FIELD__NAME:
			case UimPackage.UIM_FIELD__CONTROL_KIND:
			case UimPackage.UIM_FIELD__LABEL_WIDTH:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case UimPackage.UIM_FIELD__CONTROL:
			case UimPackage.UIM_FIELD__BINDING:
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
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimControl()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimNumberScroller()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimToggleButton()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimLookup()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimSingleSelectPopupSearch()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimMultiSelectPopupSearch()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimMultiSelectTreeView()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimMultiSelectListBox()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimDropdown()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimCheckBox()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimTextArea()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimText()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimDatePopup()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimSingleSelectListBox()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimSingleSelectTreeView()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__BINDING,
				 BindingFactory.eINSTANCE.createFieldBinding()));
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
