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
	 * Returns a new object of class '<em>Value Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Simulation</em>'.
	 * @generated
	 */
	ValueSimulation createValueSimulation();

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
	 * Returns a new object of class '<em>Simulated Slot</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simulated Slot</em>'.
	 * @generated
	 */
	SimulatedSlot createSimulatedSlot();

	/**
	 * Returns a new object of class '<em>Numeric Value Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Numeric Value Distribution</em>'.
	 * @generated
	 */
	NumericValueDistribution createNumericValueDistribution();

	/**
	 * Returns a new object of class '<em>Enum Literal Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enum Literal Simulation</em>'.
	 * @generated
	 */
	EnumLiteralSimulation createEnumLiteralSimulation();

	/**
	 * Returns a new object of class '<em>Contained Instance Value Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contained Instance Value Simulation</em>'.
	 * @generated
	 */
	ContainedInstanceValueSimulation createContainedInstanceValueSimulation();

	/**
	 * Returns a new object of class '<em>Boolean Value Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Value Simulation</em>'.
	 * @generated
	 */
	BooleanValueSimulation createBooleanValueSimulation();

	/**
	 * Returns a new object of class '<em>Number Range Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Range Distribution</em>'.
	 * @generated
	 */
	NumberRangeDistribution createNumberRangeDistribution();

	/**
	 * Returns a new object of class '<em>Referenced Instance Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Referenced Instance Simulation</em>'.
	 * @generated
	 */
	ReferencedInstanceSimulation createReferencedInstanceSimulation();

	/**
	 * Returns a new object of class '<em>String Value Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Value Simulation</em>'.
	 * @generated
	 */
	StringValueSimulation createStringValueSimulation();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	SimulationModel createSimulationModel();

	/**
	 * Returns a new object of class '<em>All Instance Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>All Instance Simulation</em>'.
	 * @generated
	 */
	AllInstanceSimulation createAllInstanceSimulation();

	/**
	 * Returns a new object of class '<em>Actual Instance Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Actual Instance Simulation</em>'.
	 * @generated
	 */
	ActualInstanceSimulation createActualInstanceSimulation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SimulationPackage getSimulationPackage();

} //SimulationFactory
