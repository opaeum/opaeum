/**
 */
package org.opaeum.metamodels.simulation.simulation.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.uml2.uml.edit.providers.SlotItemProvider;

import org.opaeum.metamodels.simulation.simulation.SimulatingSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;
import org.opaeum.metamodels.simulation.simulation.SimulationStrategy;

import org.opaeum.simulation.simulation.provider.SimulationEditPlugin;

/**
 * This is the item provider adapter for a {@link org.opaeum.metamodels.simulation.simulation.SimulatingSlot} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SimulatingSlotItemProvider
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
	public SimulatingSlotItemProvider(AdapterFactory adapterFactory) {
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

			addSimulationStrategyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Simulation Strategy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSimulationStrategyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SimulatingSlot_simulationStrategy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SimulatingSlot_simulationStrategy_feature", "_UI_SimulatingSlot_type"),
				 SimulationPackage.Literals.SIMULATING_SLOT__SIMULATION_STRATEGY,
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
			childrenFeatures.add(SimulationPackage.Literals.SIMULATING_SLOT__SIZE_DISTRIBUTION);
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
	 * This returns SimulatingSlot.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SimulatingSlot"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		SimulationStrategy labelValue = ((SimulatingSlot)object).getSimulationStrategy();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ?
			getString("_UI_SimulatingSlot_type") :
			getString("_UI_SimulatingSlot_type") + " " + label;
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

		switch (notification.getFeatureID(SimulatingSlot.class)) {
			case SimulationPackage.SIMULATING_SLOT__SIMULATION_STRATEGY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION:
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
				(EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS,
				 EcoreFactory.eINSTANCE.createEAnnotation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createSimulatedValue()));

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
				 SimulationFactory.eINSTANCE.createWeightedEnumLiteralValue()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createContainedActualInstance()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createWeightedBooleanValue()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createNumberRangeDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createWeightedStringValue()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.SLOT__VALUE,
				 SimulationFactory.eINSTANCE.createWeightedInstanceValue()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATING_SLOT__SIZE_DISTRIBUTION,
				 SimulationFactory.eINSTANCE.createNumericValueDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATING_SLOT__SIZE_DISTRIBUTION,
				 SimulationFactory.eINSTANCE.createNormalDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATING_SLOT__SIZE_DISTRIBUTION,
				 SimulationFactory.eINSTANCE.createUniformDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATING_SLOT__SIZE_DISTRIBUTION,
				 SimulationFactory.eINSTANCE.createExponentialDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.SIMULATING_SLOT__SIZE_DISTRIBUTION,
				 SimulationFactory.eINSTANCE.createNumberRangeDistribution()));
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
			childFeature == UMLPackage.Literals.SLOT__VALUE ||
			childFeature == SimulationPackage.Literals.SIMULATING_SLOT__SIZE_DISTRIBUTION;

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
		return SimulationEditPlugin.INSTANCE;
	}

}
