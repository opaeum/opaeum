/**
 */
package org.opaeum.uim.provider;


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
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.perspective.PerspectiveFactory;

/**
 * This is the item provider adapter for a {@link org.opaeum.uim.UserInterfaceRoot} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UserInterfaceRootItemProvider
	extends UserInteractionElementItemProvider
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
	public UserInterfaceRootItemProvider(AdapterFactory adapterFactory) {
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

			addUmlElementUidPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Uml Element Uid feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUmlElementUidPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_UmlReference_umlElementUid_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_UmlReference_umlElementUid_feature", "_UI_UmlReference_type"),
				 UimPackage.Literals.UML_REFERENCE__UML_ELEMENT_UID,
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
			childrenFeatures.add(UimPackage.Literals.LABELED_ELEMENT__LABEL_OVERRIDE);
			childrenFeatures.add(UimPackage.Literals.USER_INTERFACE_ROOT__EDITABILITY);
			childrenFeatures.add(UimPackage.Literals.USER_INTERFACE_ROOT__VISIBILITY);
			childrenFeatures.add(UimPackage.Literals.USER_INTERFACE_ROOT__IGNORED_ELEMENTS);
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
	 * This returns UserInterfaceRoot.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/UserInterfaceRoot"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((UserInterfaceRoot)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_UserInterfaceRoot_type") :
			getString("_UI_UserInterfaceRoot_type") + " " + label;
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

		switch (notification.getFeatureID(UserInterfaceRoot.class)) {
			case UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE:
			case UimPackage.USER_INTERFACE_ROOT__EDITABILITY:
			case UimPackage.USER_INTERFACE_ROOT__VISIBILITY:
			case UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS:
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
				(UimPackage.Literals.LABELED_ELEMENT__LABEL_OVERRIDE,
				 UimFactory.eINSTANCE.createLabels()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__EDITABILITY,
				 EditorFactory.eINSTANCE.createOperationMenuItem()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__EDITABILITY,
				 ConstraintFactory.eINSTANCE.createRootUserInteractionConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__EDITABILITY,
				 ConstraintFactory.eINSTANCE.createUserInteractionConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__EDITABILITY,
				 PerspectiveFactory.eINSTANCE.createExplorerPropertyConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__EDITABILITY,
				 PerspectiveFactory.eINSTANCE.createExplorerOperationConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__EDITABILITY,
				 PerspectiveFactory.eINSTANCE.createExplorerBehaviorConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__VISIBILITY,
				 EditorFactory.eINSTANCE.createOperationMenuItem()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__VISIBILITY,
				 ConstraintFactory.eINSTANCE.createRootUserInteractionConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__VISIBILITY,
				 ConstraintFactory.eINSTANCE.createUserInteractionConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__VISIBILITY,
				 PerspectiveFactory.eINSTANCE.createExplorerPropertyConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__VISIBILITY,
				 PerspectiveFactory.eINSTANCE.createExplorerOperationConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__VISIBILITY,
				 PerspectiveFactory.eINSTANCE.createExplorerBehaviorConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.USER_INTERFACE_ROOT__IGNORED_ELEMENTS,
				 UimFactory.eINSTANCE.createIgnoredElement()));
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
			childFeature == UimPackage.Literals.USER_INTERFACE_ROOT__EDITABILITY ||
			childFeature == UimPackage.Literals.USER_INTERFACE_ROOT__VISIBILITY;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
