/**
 */
package org.opaeum.uim.provider;


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
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.provider.EditableConstrainedObjectItemProvider;
import org.opaeum.uim.control.ControlFactory;
import org.opaeum.uim.panel.PanelPackage;

/**
 * This is the item provider adapter for a {@link org.opaeum.uim.UimField} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UimFieldItemProvider
	extends EditableConstrainedObjectItemProvider
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
			addPreferredWidthPropertyDescriptor(object);
			addPreferredHeightPropertyDescriptor(object);
			addFillHorizontallyPropertyDescriptor(object);
			addFillVerticallyPropertyDescriptor(object);
			addControlKindPropertyDescriptor(object);
			addMinimumLabelWidthPropertyDescriptor(object);
			addOrientationPropertyDescriptor(object);
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
	 * This adds a property descriptor for the Preferred Width feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPreferredWidthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Outlayable_preferredWidth_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Outlayable_preferredWidth_feature", "_UI_Outlayable_type"),
				 PanelPackage.Literals.OUTLAYABLE__PREFERRED_WIDTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Preferred Height feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPreferredHeightPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Outlayable_preferredHeight_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Outlayable_preferredHeight_feature", "_UI_Outlayable_type"),
				 PanelPackage.Literals.OUTLAYABLE__PREFERRED_HEIGHT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Fill Horizontally feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFillHorizontallyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Outlayable_fillHorizontally_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Outlayable_fillHorizontally_feature", "_UI_Outlayable_type"),
				 PanelPackage.Literals.OUTLAYABLE__FILL_HORIZONTALLY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Fill Vertically feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFillVerticallyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Outlayable_fillVertically_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Outlayable_fillVertically_feature", "_UI_Outlayable_type"),
				 PanelPackage.Literals.OUTLAYABLE__FILL_VERTICALLY,
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
	 * This adds a property descriptor for the Minimum Label Width feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMinimumLabelWidthPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_UimField_minimumLabelWidth_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_UimField_minimumLabelWidth_feature", "_UI_UimField_type"),
				 UimPackage.Literals.UIM_FIELD__MINIMUM_LABEL_WIDTH,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Orientation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOrientationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_UimField_orientation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_UimField_orientation_feature", "_UI_UimField_type"),
				 UimPackage.Literals.UIM_FIELD__ORIENTATION,
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
			case UimPackage.UIM_FIELD__PREFERRED_WIDTH:
			case UimPackage.UIM_FIELD__PREFERRED_HEIGHT:
			case UimPackage.UIM_FIELD__FILL_HORIZONTALLY:
			case UimPackage.UIM_FIELD__FILL_VERTICALLY:
			case UimPackage.UIM_FIELD__CONTROL_KIND:
			case UimPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH:
			case UimPackage.UIM_FIELD__ORIENTATION:
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
				 ControlFactory.eINSTANCE.createUimPopupSearch()));

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
				 ControlFactory.eINSTANCE.createUimListBox()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimTreeView()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimLinkControl()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimDateScroller()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimSelectionTable()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimRadioButton()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimLabel()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_FIELD__CONTROL,
				 ControlFactory.eINSTANCE.createUimDateTimePopup()));

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
			childFeature == ConstraintPackage.Literals.CONSTRAINED_OBJECT__VISIBILITY ||
			childFeature == ConstraintPackage.Literals.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY;

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
