/**
 */
package org.opaeum.metamodels.simulation.simulation.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.uml2.uml.edit.providers.SlotItemProvider;

import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

import org.opaeum.simulation.simulation.provider.SimulationEditPlugin;

/**
 * This is the item provider adapter for a {@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SimulatedSlotItemProvider
	extends SlotItemProvider
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
	public SimulatedSlotItemProvider(AdapterFactory adapterFactory) {
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

			addSizeDistributionPropertyDescriptor(object);
			addPropertyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Size Distribution feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSizeDistributionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulatedSlot_sizeDistribution_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulatedSlot_sizeDistribution_feature", "_UI_SimulatedSlot_type"),
				 SimulationPackage.Literals.SIMULATED_SLOT__SIZE_DISTRIBUTION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Property feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPropertyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulatedSlot_property_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulatedSlot_property_feature", "_UI_SimulatedSlot_type"),
				 SimulationPackage.Literals.SIMULATED_SLOT__PROPERTY,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns SimulatedSlot.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SimulatedSlot"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_SimulatedSlot_type");
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
				(EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS,
				 EcoreFactory.eINSTANCE.createEAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createValueSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createNumericValueDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createNormalDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createUniformDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createExponentialDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createEnumLiteralSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createContainedInstanceValueSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createBooleanValueSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createNumberRangeDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createReferencedInstanceSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createStringValueSimulation()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return SimulationEditPlugin.INSTANCE;
	}

}
