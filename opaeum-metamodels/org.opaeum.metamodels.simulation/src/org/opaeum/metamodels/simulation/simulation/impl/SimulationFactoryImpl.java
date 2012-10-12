/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.ContainedActualInstance;
import org.opaeum.metamodels.simulation.simulation.ExponentialDistribution;
import org.opaeum.metamodels.simulation.simulation.InstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.LiteralSimpleType;
import org.opaeum.metamodels.simulation.simulation.NormalDistribution;
import org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution;
import org.opaeum.metamodels.simulation.simulation.NumericValueDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulatedValue;
import org.opaeum.metamodels.simulation.simulation.SimulatingSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;
import org.opaeum.metamodels.simulation.simulation.SimulationStrategy;
import org.opaeum.metamodels.simulation.simulation.UniformDistribution;
import org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue;
import org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;
import org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue;
import org.opaeum.metamodels.simulation.simulation.WeightedStringValue;

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
			case SimulationPackage.SIMULATED_VALUE: return createSimulatedValue();
			case SimulationPackage.NORMAL_DISTRIBUTION: return createNormalDistribution();
			case SimulationPackage.UNIFORM_DISTRIBUTION: return createUniformDistribution();
			case SimulationPackage.EXPONENTIAL_DISTRIBUTION: return createExponentialDistribution();
			case SimulationPackage.SIMULATING_SLOT: return createSimulatingSlot();
			case SimulationPackage.NUMERIC_VALUE_DISTRIBUTION: return createNumericValueDistribution();
			case SimulationPackage.WEIGHTED_ENUM_LITERAL_VALUE: return createWeightedEnumLiteralValue();
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE: return createContainedActualInstance();
			case SimulationPackage.WEIGHTED_BOOLEAN_VALUE: return createWeightedBooleanValue();
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION: return createNumberRangeDistribution();
			case SimulationPackage.WEIGHTED_STRING_VALUE: return createWeightedStringValue();
			case SimulationPackage.SIMULATION_MODEL: return createSimulationModel();
			case SimulationPackage.INSTANCE_SIMULATION: return createInstanceSimulation();
			case SimulationPackage.ACTUAL_INSTANCE: return createActualInstance();
			case SimulationPackage.WEIGHTED_INSTANCE_VALUE: return createWeightedInstanceValue();
			case SimulationPackage.WEIGHTED_SIMPLE_TYPE_VALUE: return createWeightedSimpleTypeValue();
			case SimulationPackage.LITERAL_SIMPLE_TYPE: return createLiteralSimpleType();
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
	public SimulatedValue createSimulatedValue() {
		SimulatedValueImpl simulatedValue = new SimulatedValueImpl();
		return simulatedValue;
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
	public SimulatingSlot createSimulatingSlot() {
		SimulatingSlotImpl simulatingSlot = new SimulatingSlotImpl();
		return simulatingSlot;
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
	public WeightedEnumLiteralValue createWeightedEnumLiteralValue() {
		WeightedEnumLiteralValueImpl weightedEnumLiteralValue = new WeightedEnumLiteralValueImpl();
		return weightedEnumLiteralValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainedActualInstance createContainedActualInstance() {
		ContainedActualInstanceImpl containedActualInstance = new ContainedActualInstanceImpl();
		return containedActualInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeightedBooleanValue createWeightedBooleanValue() {
		WeightedBooleanValueImpl weightedBooleanValue = new WeightedBooleanValueImpl();
		return weightedBooleanValue;
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
	public WeightedStringValue createWeightedStringValue() {
		WeightedStringValueImpl weightedStringValue = new WeightedStringValueImpl();
		return weightedStringValue;
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
	public InstanceSimulation createInstanceSimulation() {
		InstanceSimulationImpl instanceSimulation = new InstanceSimulationImpl();
		return instanceSimulation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActualInstance createActualInstance() {
		ActualInstanceImpl actualInstance = new ActualInstanceImpl();
		return actualInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeightedInstanceValue createWeightedInstanceValue() {
		WeightedInstanceValueImpl weightedInstanceValue = new WeightedInstanceValueImpl();
		return weightedInstanceValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WeightedSimpleTypeValue createWeightedSimpleTypeValue() {
		WeightedSimpleTypeValueImpl weightedSimpleTypeValue = new WeightedSimpleTypeValueImpl();
		return weightedSimpleTypeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralSimpleType createLiteralSimpleType() {
		LiteralSimpleTypeImpl literalSimpleType = new LiteralSimpleTypeImpl();
		return literalSimpleType;
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
