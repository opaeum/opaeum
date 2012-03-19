/**
 */
package org.opaeum.metamodels.simulation.simulation.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.DeployedArtifact;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;

import org.opaeum.metamodels.simulation.simulation.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage
 * @generated
 */
public class SimulationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SimulationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SimulationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimulationSwitch<Adapter> modelSwitch =
		new SimulationSwitch<Adapter>() {
			@Override
			public Adapter caseValueSimulation(ValueSimulation object) {
				return createValueSimulationAdapter();
			}
			@Override
			public Adapter caseNormalDistribution(NormalDistribution object) {
				return createNormalDistributionAdapter();
			}
			@Override
			public Adapter caseUniformDistribution(UniformDistribution object) {
				return createUniformDistributionAdapter();
			}
			@Override
			public Adapter caseExponentialDistribution(ExponentialDistribution object) {
				return createExponentialDistributionAdapter();
			}
			@Override
			public Adapter caseSimulatedSlot(SimulatedSlot object) {
				return createSimulatedSlotAdapter();
			}
			@Override
			public Adapter caseNumericValueDistribution(NumericValueDistribution object) {
				return createNumericValueDistributionAdapter();
			}
			@Override
			public Adapter caseEnumLiteralSimulation(EnumLiteralSimulation object) {
				return createEnumLiteralSimulationAdapter();
			}
			@Override
			public Adapter caseContainedInstanceValueSimulation(ContainedInstanceValueSimulation object) {
				return createContainedInstanceValueSimulationAdapter();
			}
			@Override
			public Adapter caseBooleanValueSimulation(BooleanValueSimulation object) {
				return createBooleanValueSimulationAdapter();
			}
			@Override
			public Adapter caseNumberRangeDistribution(NumberRangeDistribution object) {
				return createNumberRangeDistributionAdapter();
			}
			@Override
			public Adapter caseReferencedInstanceSimulation(ReferencedInstanceSimulation object) {
				return createReferencedInstanceSimulationAdapter();
			}
			@Override
			public Adapter caseStringValueSimulation(StringValueSimulation object) {
				return createStringValueSimulationAdapter();
			}
			@Override
			public Adapter caseEModelElement(EModelElement object) {
				return createEModelElementAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseParameterableElement(ParameterableElement object) {
				return createParameterableElementAdapter();
			}
			@Override
			public Adapter casePackageableElement(PackageableElement object) {
				return createPackageableElementAdapter();
			}
			@Override
			public Adapter caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			@Override
			public Adapter caseValueSpecification(ValueSpecification object) {
				return createValueSpecificationAdapter();
			}
			@Override
			public Adapter caseSlot(Slot object) {
				return createSlotAdapter();
			}
			@Override
			public Adapter caseDeploymentTarget(DeploymentTarget object) {
				return createDeploymentTargetAdapter();
			}
			@Override
			public Adapter caseDeployedArtifact(DeployedArtifact object) {
				return createDeployedArtifactAdapter();
			}
			@Override
			public Adapter caseInstanceSpecification(InstanceSpecification object) {
				return createInstanceSpecificationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.ValueSimulation <em>Value Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.ValueSimulation
	 * @generated
	 */
	public Adapter createValueSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.NormalDistribution <em>Normal Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.NormalDistribution
	 * @generated
	 */
	public Adapter createNormalDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.UniformDistribution <em>Uniform Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.UniformDistribution
	 * @generated
	 */
	public Adapter createUniformDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.ExponentialDistribution <em>Exponential Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.ExponentialDistribution
	 * @generated
	 */
	public Adapter createExponentialDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot <em>Simulated Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulatedSlot
	 * @generated
	 */
	public Adapter createSimulatedSlotAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.NumericValueDistribution <em>Numeric Value Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.NumericValueDistribution
	 * @generated
	 */
	public Adapter createNumericValueDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation <em>Enum Literal Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation
	 * @generated
	 */
	public Adapter createEnumLiteralSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation <em>Contained Instance Value Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation
	 * @generated
	 */
	public Adapter createContainedInstanceValueSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation <em>Boolean Value Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation
	 * @generated
	 */
	public Adapter createBooleanValueSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution <em>Number Range Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution
	 * @generated
	 */
	public Adapter createNumberRangeDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation <em>Referenced Instance Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation
	 * @generated
	 */
	public Adapter createReferencedInstanceSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.metamodels.simulation.simulation.StringValueSimulation <em>String Value Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.metamodels.simulation.simulation.StringValueSimulation
	 * @generated
	 */
	public Adapter createStringValueSimulationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EModelElement
	 * @generated
	 */
	public Adapter createEModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.ParameterableElement <em>Parameterable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.ParameterableElement
	 * @generated
	 */
	public Adapter createParameterableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.PackageableElement <em>Packageable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.PackageableElement
	 * @generated
	 */
	public Adapter createPackageableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.ValueSpecification <em>Value Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.ValueSpecification
	 * @generated
	 */
	public Adapter createValueSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.Slot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.Slot
	 * @generated
	 */
	public Adapter createSlotAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.DeploymentTarget <em>Deployment Target</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.DeploymentTarget
	 * @generated
	 */
	public Adapter createDeploymentTargetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.DeployedArtifact <em>Deployed Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.DeployedArtifact
	 * @generated
	 */
	public Adapter createDeployedArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.uml2.uml.InstanceSpecification <em>Instance Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.uml2.uml.InstanceSpecification
	 * @generated
	 */
	public Adapter createInstanceSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SimulationAdapterFactory
