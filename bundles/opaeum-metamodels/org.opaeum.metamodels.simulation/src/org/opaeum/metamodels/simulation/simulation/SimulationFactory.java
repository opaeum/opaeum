/**
 */
package org.opaeum.metamodels.simulation.simulation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage
 * @generated
 */
public interface SimulationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimulationFactory eINSTANCE = org.opaeum.metamodels.simulation.simulation.impl.SimulationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Simulated Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simulated Value</em>'.
	 * @generated
	 */
	SimulatedValue createSimulatedValue();

	/**
	 * Returns a new object of class '<em>Normal Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Normal Distribution</em>'.
	 * @generated
	 */
	NormalDistribution createNormalDistribution();

	/**
	 * Returns a new object of class '<em>Uniform Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uniform Distribution</em>'.
	 * @generated
	 */
	UniformDistribution createUniformDistribution();

	/**
	 * Returns a new object of class '<em>Exponential Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exponential Distribution</em>'.
	 * @generated
	 */
	ExponentialDistribution createExponentialDistribution();

	/**
	 * Returns a new object of class '<em>Simulating Slot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simulating Slot</em>'.
	 * @generated
	 */
	SimulatingSlot createSimulatingSlot();

	/**
	 * Returns a new object of class '<em>Numeric Value Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Numeric Value Distribution</em>'.
	 * @generated
	 */
	NumericValueDistribution createNumericValueDistribution();

	/**
	 * Returns a new object of class '<em>Weighted Enum Literal Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weighted Enum Literal Value</em>'.
	 * @generated
	 */
	WeightedEnumLiteralValue createWeightedEnumLiteralValue();

	/**
	 * Returns a new object of class '<em>Contained Actual Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contained Actual Instance</em>'.
	 * @generated
	 */
	ContainedActualInstance createContainedActualInstance();

	/**
	 * Returns a new object of class '<em>Weighted Boolean Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weighted Boolean Value</em>'.
	 * @generated
	 */
	WeightedBooleanValue createWeightedBooleanValue();

	/**
	 * Returns a new object of class '<em>Number Range Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Range Distribution</em>'.
	 * @generated
	 */
	NumberRangeDistribution createNumberRangeDistribution();

	/**
	 * Returns a new object of class '<em>Weighted String Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weighted String Value</em>'.
	 * @generated
	 */
	WeightedStringValue createWeightedStringValue();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	SimulationModel createSimulationModel();

	/**
	 * Returns a new object of class '<em>Instance Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instance Simulation</em>'.
	 * @generated
	 */
	InstanceSimulation createInstanceSimulation();

	/**
	 * Returns a new object of class '<em>Actual Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Actual Instance</em>'.
	 * @generated
	 */
	ActualInstance createActualInstance();

	/**
	 * Returns a new object of class '<em>Weighted Instance Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weighted Instance Value</em>'.
	 * @generated
	 */
	WeightedInstanceValue createWeightedInstanceValue();

	/**
	 * Returns a new object of class '<em>Weighted Simple Type Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Weighted Simple Type Value</em>'.
	 * @generated
	 */
	WeightedSimpleTypeValue createWeightedSimpleTypeValue();

	/**
	 * Returns a new object of class '<em>Literal Simple Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Literal Simple Type</em>'.
	 * @generated
	 */
	LiteralSimpleType createLiteralSimpleType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SimulationPackage getSimulationPackage();

} //SimulationFactory
