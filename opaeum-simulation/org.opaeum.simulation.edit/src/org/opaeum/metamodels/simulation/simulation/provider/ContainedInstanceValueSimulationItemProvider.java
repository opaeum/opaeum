/**
 */
package org.opaeum.metamodels.simulation.simulation.provider;


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
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

import org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

/**
 * This is the item provider adapter for a {@link org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContainedInstanceValueSimulationItemProvider
	extends ValueSimulationItemProvider
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
	public ContainedInstanceValueSimulationItemProvider(AdapterFactory adapterFactory) {
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

			addDeployedElementPropertyDescriptor(object);
			addDeploymentPropertyDescriptor(object);
			addClassifierPropertyDescriptor(object);
			addSlotPropertyDescriptor(object);
			addSpecificationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Deployed Element feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDeployedElementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DeploymentTarget_deployedElement_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DeploymentTarget_deployedElement_feature", "_UI_DeploymentTarget_type"),
				 UMLPackage.Literals.DEPLOYMENT_TARGET__DEPLOYED_ELEMENT,
				 false,
				 false,
				 false,
				 null,
				 null,
				 new String[] {
					"org.eclipse.ui.views.properties.expert"
				 }));
	}

	/**
	 * This adds a property descriptor for the Deployment feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDeploymentPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_DeploymentTarget_deployment_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_DeploymentTarget_deployment_feature", "_UI_DeploymentTarget_type"),
				 UMLPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT,
				 true,
				 false,
				 true,
				 null,
				 null,
				 new String[] {
					"org.eclipse.ui.views.properties.expert"
				 }));
	}

	/**
	 * This adds a property descriptor for the Classifier feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addClassifierPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstanceSpecification_classifier_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstanceSpecification_classifier_feature", "_UI_InstanceSpecification_type"),
				 UMLPackage.Literals.INSTANCE_SPECIFICATION__CLASSIFIER,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Slot feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSlotPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstanceSpecification_slot_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstanceSpecification_slot_feature", "_UI_InstanceSpecification_type"),
				 UMLPackage.Literals.INSTANCE_SPECIFICATION__SLOT,
				 true,
				 false,
				 true,
				 null,
				 null,
				 new String[] {
					"org.eclipse.ui.views.properties.expert"
				 }));
	}

	/**
	 * This adds a property descriptor for the Specification feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSpecificationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstanceSpecification_specification_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstanceSpecification_specification_feature", "_UI_InstanceSpecification_type"),
				 UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 new String[] {
					"org.eclipse.ui.views.properties.expert"
				 }));
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
			childrenFeatures.add(UMLPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT);
			childrenFeatures.add(UMLPackage.Literals.INSTANCE_SPECIFICATION__SLOT);
			childrenFeatures.add(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION);
			childrenFeatures.add(SimulationPackage.Literals.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES);
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
	 * This returns ContainedInstanceValueSimulation.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ContainedInstanceValueSimulation"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((ContainedInstanceValueSimulation)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ContainedInstanceValueSimulation_type") :
			getString("_UI_ContainedInstanceValueSimulation_type") + " " + label;
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

		switch (notification.getFeatureID(ContainedInstanceValueSimulation.class)) {
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT:
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT:
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION:
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
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
				(UMLPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT,
				 UMLFactory.eINSTANCE.createDeployment()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SLOT,
				 SimulationFactory.eINSTANCE.createSimulatedSlot()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SLOT,
				 UMLFactory.eINSTANCE.createSlot()));

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

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createExpression()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createStringExpression()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createOpaqueExpression()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createDuration()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createInterval()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createDurationInterval()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createInstanceValue()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createLiteralBoolean()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createLiteralInteger()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createLiteralNull()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createLiteralReal()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createLiteralString()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createLiteralUnlimitedNatural()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createTimeInterval()));

		newChildDescriptors.add
			(createChildParameter
				(UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION,
				 UMLFactory.eINSTANCE.createTimeExpression()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES,
				 SimulationFactory.eINSTANCE.createContainedInstanceValueSimulation()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES,
				 UMLFactory.eINSTANCE.createInstanceSpecification()));

		newChildDescriptors.add
			(createChildParameter
				(SimulationPackage.Literals.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES,
				 UMLFactory.eINSTANCE.createEnumerationLiteral()));
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
			childFeature == UMLPackage.Literals.INSTANCE_SPECIFICATION__SPECIFICATION ||
			childFeature == SimulationPackage.Literals.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
