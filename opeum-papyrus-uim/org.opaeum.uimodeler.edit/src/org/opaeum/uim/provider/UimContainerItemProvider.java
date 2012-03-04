/**
 */
package org.opaeum.uim.provider;


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
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.panel.PanelFactory;

/**
 * This is the item provider adapter for a {@link org.opaeum.uim.UimContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UimContainerItemProvider
	extends UimComponentItemProvider
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
	public UimContainerItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(ConstraintPackage.Literals.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY);
			childrenFeatures.add(UimPackage.Literals.UIM_CONTAINER__CHILDREN);
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
	 * This returns UimContainer.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/UimContainer"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((UimContainer)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_UimContainer_type") :
			getString("_UI_UimContainer_type") + " " + label;
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

		switch (notification.getFeatureID(UimContainer.class)) {
			case UimPackage.UIM_CONTAINER__EDITABILITY:
			case UimPackage.UIM_CONTAINER__CHILDREN:
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
				(ConstraintPackage.Literals.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY,
				 ConstraintFactory.eINSTANCE.createUserInteractionConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 UimFactory.eINSTANCE.createUimField()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 EditorFactory.eINSTANCE.createActionBar()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 ActionFactory.eINSTANCE.createBuiltInAction()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 ActionFactory.eINSTANCE.createTransitionAction()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 ActionFactory.eINSTANCE.createOperationAction()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 PanelFactory.eINSTANCE.createCollapsiblePanel()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 PanelFactory.eINSTANCE.createGridPanel()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 PanelFactory.eINSTANCE.createVerticalPanel()));

		newChildDescriptors.add
			(createChildParameter
				(UimPackage.Literals.UIM_CONTAINER__CHILDREN,
				 PanelFactory.eINSTANCE.createHorizontalPanel()));
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

}
