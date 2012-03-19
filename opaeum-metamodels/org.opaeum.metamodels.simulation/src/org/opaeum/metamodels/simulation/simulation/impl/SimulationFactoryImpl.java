/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.opaeum.metamodels.simulation.simulation.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimulationFactoryImpl extends EFactoryImpl implements SimulationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SimulationFactory init() {
		try {
			SimulationFactory theSimulationFactory = (SimulationFactory)EPackage.Registry.INSTANCE.getEFactory("http://simulation/1.0"); 
			if (theSimulationFactory != null) {
				return theSimulationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SimulationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SimulationPackage.VALUE_SIMULATION: return createValueSimulation();
			case SimulationPackage.NORMAL_DISTRIBUTION: return createNormalDistribution();
			case SimulationPackage.UNIFORM_DISTRIBUTION: return createUniformDistribution();
			case SimulationPackage.EXPONENTIAL_DISTRIBUTION: return createExponentialDistribution();
			case SimulationPackage.SIMULATED_SLOT: return createSimulatedSlot();
			case SimulationPackage.NUMERIC_VALUE_DISTRIBUTION: return createNumericValueDistribution();
			case SimulationPackage.ENUM_LITERAL_SIMULATION: return createEnumLiteralSimulation();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION: return createContainedInstanceValueSimulation();
			case SimulationPackage.BOOLEAN_VALUE_SIMULATION: return createBooleanValueSimulation();
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION: return createNumberRangeDistribution();
			case SimulationPackage.REFERENCED_INSTANCE_SIMULATION: return createReferencedInstanceSimulation();
			case SimulationPackage.STRING_VALUE_SIMULATION: return createStringValueSimulation();
			case SimulationPackage.SIMULATION_MODEL: return createSimulationModel();
			case SimulationPackage.ALL_INSTANCE_SIMULATION: return createAllInstanceSimulation();
			case SimulationPackage.ACTUAL_INSTANCE_SIMULATION: return createActualInstanceSimulation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SimulationPackage.SIMULATION_STRATEGY:
				return createSimulationStrategyFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SimulationPackage.SIMULATION_STRATEGY:
				return convertSimulationStrategyToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSimulation createValueSimulation() {
		ValueSimulationImpl valueSimulation = new ValueSimulationImpl();
		return valueSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NormalDistribution createNormalDistribution() {
		NormalDistributionImpl normalDistribution = new NormalDistributionImpl();
		return normalDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UniformDistribution createUniformDistribution() {
		UniformDistributionImpl uniformDistribution = new UniformDistributionImpl();
		return uniformDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExponentialDistribution createExponentialDistribution() {
		ExponentialDistributionImpl exponentialDistribution = new ExponentialDistributionImpl();
		return exponentialDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulatedSlot createSimulatedSlot() {
		SimulatedSlotImpl simulatedSlot = new SimulatedSlotImpl();
		return simulatedSlot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumericValueDistribution createNumericValueDistribution() {
		NumericValueDistributionImpl numericValueDistribution = new NumericValueDistributionImpl();
		return numericValueDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumLiteralSimulation createEnumLiteralSimulation() {
		EnumLiteralSimulationImpl enumLiteralSimulation = new EnumLiteralSimulationImpl();
		return enumLiteralSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainedInstanceValueSimulation createContainedInstanceValueSimulation() {
		ContainedInstanceValueSimulationImpl containedInstanceValueSimulation = new ContainedInstanceValueSimulationImpl();
		return containedInstanceValueSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanValueSimulation createBooleanValueSimulation() {
		BooleanValueSimulationImpl booleanValueSimulation = new BooleanValueSimulationImpl();
		return booleanValueSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumberRangeDistribution createNumberRangeDistribution() {
		NumberRangeDistributionImpl numberRangeDistribution = new NumberRangeDistributionImpl();
		return numberRangeDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencedInstanceSimulation createReferencedInstanceSimulation() {
		ReferencedInstanceSimulationImpl referencedInstanceSimulation = new ReferencedInstanceSimulationImpl();
		return referencedInstanceSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringValueSimulation createStringValueSimulation() {
		StringValueSimulationImpl stringValueSimulation = new StringValueSimulationImpl();
		return stringValueSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationModel createSimulationModel() {
		SimulationModelImpl simulationModel = new SimulationModelImpl();
		return simulationModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllInstanceSimulation createAllInstanceSimulation() {
		AllInstanceSimulationImpl allInstanceSimulation = new AllInstanceSimulationImpl();
		return allInstanceSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActualInstanceSimulation createActualInstanceSimulation() {
		ActualInstanceSimulationImpl actualInstanceSimulation = new ActualInstanceSimulationImpl();
		return actualInstanceSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationStrategy createSimulationStrategyFromString(EDataType eDataType, String initialValue) {
		SimulationStrategy result = SimulationStrategy.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSimulationStrategyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationPackage getSimulationPackage() {
		return (SimulationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SimulationPackage getPackage() {
		return SimulationPackage.eINSTANCE;
	}

} //SimulationFactoryImpl
