/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.layout.provider;


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
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.action.ActionFactory;
import org.nakeduml.uim.layout.LayoutPackage;
import org.nakeduml.uim.layout.UimLayout;
import org.nakeduml.uim.provider.UimContainerItemProvider;
import org.nakeduml.uim.provider.UimEditPlugin;
import org.nakeduml.uim.security.SecurityPackage;

/**
 * This is the item provider adapter for a {@link org.nakeduml.uim.layout.UimLayout} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UimLayoutItemProvider
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
	public UimLayoutItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN);
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
	 * This returns UimLayout.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/UimLayout"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((UimLayout)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_UimLayout_type") :
			getString("_UI_UimLayout_type") + " " + label;
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

		switch (notification.getFeatureID(UimLayout.class)) {
			case LayoutPackage.UIM_LAYOUT__CHILDREN:
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
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 UimFactory.eINSTANCE.createUimField()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 UimFactory.eINSTANCE.createUimDataTable()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 UimFactory.eINSTANCE.createUimTabPanel()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 UimFactory.eINSTANCE.createUimPanel()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 UimFactory.eINSTANCE.createObjectSelectorTree()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 ActionFactory.eINSTANCE.createBuiltInAction()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 ActionFactory.eINSTANCE.createTransitionAction()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 ActionFactory.eINSTANCE.createNavigationToOperation()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 ActionFactory.eINSTANCE.createOperationAction()));

		newChildDescriptors.add
			(createChildParameter
				(LayoutPackage.Literals.UIM_LAYOUT__CHILDREN,
				 ActionFactory.eINSTANCE.createNavigationToEntity()));
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
