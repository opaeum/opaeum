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

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.uml2.uml.UMLPackage;

import org.eclipse.uml2.uml.edit.providers.InstanceSpecificationItemProvider;

import org.opaeum.metamodels.simulation.simulation.ActualInstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;

import org.opaeum.simulation.simulation.provider.SimulationEditPlugin;

/**
 * This is the item provider adapter for a {@link org.opaeum.metamodels.simulation.simulation.ActualInstanceSimulation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ActualInstanceSimulationItemProvider
	extends InstanceSpecificationItemProvider
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
	public ActualInstanceSimulationItemProvider(AdapterFactory adapterFactory) {
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
	 * This returns ActualInstanceSimulation.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ActualInstanceSimulation"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ActualInstanceSimulation)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ActualInstanceSimulation_type") :
			getString("_UI_ActualInstanceSimulation_type") + " " + label;
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
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SLOT,
				 SimulationFactory.eINSTANCE.createSimulatedSlot()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createValueSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createNumericValueDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createNormalDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createUniformDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createExponentialDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createEnumLiteralSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createContainedInstanceValueSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createBooleanValueSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createNumberRangeDistribution()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createReferencedInstanceSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 SimulationFactory.eINSTANCE.createStringValueSimulation()));
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
			childFeature == UMLPackage.Literals.NAMED_ELEMENT__NAME_EXPRESSION ||
			childFeature == UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION;

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
