/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.uml2.types.TypesPackage;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.ContainedActualInstance;
import org.opaeum.metamodels.simulation.simulation.ExponentialDistribution;
import org.opaeum.metamodels.simulation.simulation.InstanceSimulation;
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
import org.opaeum.metamodels.simulation.simulation.LiteralSimpleType;
import org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue;
import org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;
import org.opaeum.metamodels.simulation.simulation.WeightedStringValue;
import org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimulationPackageImpl extends EPackageImpl implements SimulationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simulatedValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass normalDistributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uniformDistributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialDistributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simulatingSlotEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericValueDistributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weightedEnumLiteralValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containedActualInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weightedBooleanValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberRangeDistributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weightedStringValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simulationModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceSimulationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actualInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weightedInstanceValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass weightedSimpleTypeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalSimpleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum simulationStrategyEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SimulationPackageImpl() {
		super(eNS_URI, SimulationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SimulationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SimulationPackage init() {
		if (isInited) return (SimulationPackage)EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);

		// Obtain or create and register package
		SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SimulationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SimulationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		TypesPackage.eINSTANCE.eClass();
		UMLPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSimulationPackage.createPackageContents();

		// Initialize created meta-data
		theSimulationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSimulationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SimulationPackage.eNS_URI, theSimulationPackage);
		return theSimulationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimulatedValue() {
		return simulatedValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNormalDistribution() {
		return normalDistributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNormalDistribution_Mean() {
		return (EAttribute)normalDistributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNormalDistribution_StandardDeviation() {
		return (EAttribute)normalDistributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUniformDistribution() {
		return uniformDistributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUniformDistribution_LowerLimit() {
		return (EAttribute)uniformDistributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUniformDistribution_UpperLimit() {
		return (EAttribute)uniformDistributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExponentialDistribution() {
		return exponentialDistributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExponentialDistribution_Mean() {
		return (EAttribute)exponentialDistributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimulatingSlot() {
		return simulatingSlotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimulatingSlot_SizeDistribution() {
		return (EReference)simulatingSlotEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSimulatingSlot_SimulationStrategy() {
		return (EAttribute)simulatingSlotEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumericValueDistribution() {
		return numericValueDistributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeightedEnumLiteralValue() {
		return weightedEnumLiteralValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedEnumLiteralValue_Weight() {
		return (EAttribute)weightedEnumLiteralValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeightedEnumLiteralValue_Literal() {
		return (EReference)weightedEnumLiteralValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainedActualInstance() {
		return containedActualInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainedActualInstance_Values() {
		return (EReference)containedActualInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainedActualInstance_ContainedInstance() {
		return (EReference)containedActualInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeightedBooleanValue() {
		return weightedBooleanValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedBooleanValue_Weight() {
		return (EAttribute)weightedBooleanValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedBooleanValue_Value() {
		return (EAttribute)weightedBooleanValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumberRangeDistribution() {
		return numberRangeDistributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumberRangeDistribution_UpperValue() {
		return (EAttribute)numberRangeDistributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumberRangeDistribution_LowerValue() {
		return (EAttribute)numberRangeDistributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumberRangeDistribution_Weight() {
		return (EAttribute)numberRangeDistributionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeightedStringValue() {
		return weightedStringValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedStringValue_Weight() {
		return (EAttribute)weightedStringValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedStringValue_Value() {
		return (EAttribute)weightedStringValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSimulationModel() {
		return simulationModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstanceSimulation() {
		return instanceSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActualInstance() {
		return actualInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeightedInstanceValue() {
		return weightedInstanceValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedInstanceValue_Weight() {
		return (EAttribute)weightedInstanceValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWeightedInstanceValue_Instance() {
		return (EReference)weightedInstanceValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWeightedSimpleTypeValue() {
		return weightedSimpleTypeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedSimpleTypeValue_StringValue() {
		return (EAttribute)weightedSimpleTypeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedSimpleTypeValue_Weight() {
		return (EAttribute)weightedSimpleTypeValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWeightedSimpleTypeValue_RuntimeStrategyFactory() {
		return (EAttribute)weightedSimpleTypeValueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLiteralSimpleType() {
		return literalSimpleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLiteralSimpleType_StringValue() {
		return (EAttribute)literalSimpleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLiteralSimpleType_RuntimeStrategyFactory() {
		return (EAttribute)literalSimpleTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSimulationStrategy() {
		return simulationStrategyEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationFactory getSimulationFactory() {
		return (SimulationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		simulatedValueEClass = createEClass(SIMULATED_VALUE);

		normalDistributionEClass = createEClass(NORMAL_DISTRIBUTION);
		createEAttribute(normalDistributionEClass, NORMAL_DISTRIBUTION__MEAN);
		createEAttribute(normalDistributionEClass, NORMAL_DISTRIBUTION__STANDARD_DEVIATION);

		uniformDistributionEClass = createEClass(UNIFORM_DISTRIBUTION);
		createEAttribute(uniformDistributionEClass, UNIFORM_DISTRIBUTION__LOWER_LIMIT);
		createEAttribute(uniformDistributionEClass, UNIFORM_DISTRIBUTION__UPPER_LIMIT);

		exponentialDistributionEClass = createEClass(EXPONENTIAL_DISTRIBUTION);
		createEAttribute(exponentialDistributionEClass, EXPONENTIAL_DISTRIBUTION__MEAN);

		simulatingSlotEClass = createEClass(SIMULATING_SLOT);
		createEReference(simulatingSlotEClass, SIMULATING_SLOT__SIZE_DISTRIBUTION);
		createEAttribute(simulatingSlotEClass, SIMULATING_SLOT__SIMULATION_STRATEGY);

		numericValueDistributionEClass = createEClass(NUMERIC_VALUE_DISTRIBUTION);

		weightedEnumLiteralValueEClass = createEClass(WEIGHTED_ENUM_LITERAL_VALUE);
		createEAttribute(weightedEnumLiteralValueEClass, WEIGHTED_ENUM_LITERAL_VALUE__WEIGHT);
		createEReference(weightedEnumLiteralValueEClass, WEIGHTED_ENUM_LITERAL_VALUE__LITERAL);

		containedActualInstanceEClass = createEClass(CONTAINED_ACTUAL_INSTANCE);
		createEReference(containedActualInstanceEClass, CONTAINED_ACTUAL_INSTANCE__VALUES);
		createEReference(containedActualInstanceEClass, CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE);

		weightedBooleanValueEClass = createEClass(WEIGHTED_BOOLEAN_VALUE);
		createEAttribute(weightedBooleanValueEClass, WEIGHTED_BOOLEAN_VALUE__WEIGHT);
		createEAttribute(weightedBooleanValueEClass, WEIGHTED_BOOLEAN_VALUE__VALUE);

		numberRangeDistributionEClass = createEClass(NUMBER_RANGE_DISTRIBUTION);
		createEAttribute(numberRangeDistributionEClass, NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE);
		createEAttribute(numberRangeDistributionEClass, NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE);
		createEAttribute(numberRangeDistributionEClass, NUMBER_RANGE_DISTRIBUTION__WEIGHT);

		weightedStringValueEClass = createEClass(WEIGHTED_STRING_VALUE);
		createEAttribute(weightedStringValueEClass, WEIGHTED_STRING_VALUE__WEIGHT);
		createEAttribute(weightedStringValueEClass, WEIGHTED_STRING_VALUE__VALUE);

		simulationModelEClass = createEClass(SIMULATION_MODEL);

		instanceSimulationEClass = createEClass(INSTANCE_SIMULATION);

		actualInstanceEClass = createEClass(ACTUAL_INSTANCE);

		weightedInstanceValueEClass = createEClass(WEIGHTED_INSTANCE_VALUE);
		createEAttribute(weightedInstanceValueEClass, WEIGHTED_INSTANCE_VALUE__WEIGHT);
		createEReference(weightedInstanceValueEClass, WEIGHTED_INSTANCE_VALUE__INSTANCE);

		weightedSimpleTypeValueEClass = createEClass(WEIGHTED_SIMPLE_TYPE_VALUE);
		createEAttribute(weightedSimpleTypeValueEClass, WEIGHTED_SIMPLE_TYPE_VALUE__STRING_VALUE);
		createEAttribute(weightedSimpleTypeValueEClass, WEIGHTED_SIMPLE_TYPE_VALUE__WEIGHT);
		createEAttribute(weightedSimpleTypeValueEClass, WEIGHTED_SIMPLE_TYPE_VALUE__RUNTIME_STRATEGY_FACTORY);

		literalSimpleTypeEClass = createEClass(LITERAL_SIMPLE_TYPE);
		createEAttribute(literalSimpleTypeEClass, LITERAL_SIMPLE_TYPE__STRING_VALUE);
		createEAttribute(literalSimpleTypeEClass, LITERAL_SIMPLE_TYPE__RUNTIME_STRATEGY_FACTORY);

		// Create enums
		simulationStrategyEEnum = createEEnum(SIMULATION_STRATEGY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		simulatedValueEClass.getESuperTypes().add(theUMLPackage.getValueSpecification());
		normalDistributionEClass.getESuperTypes().add(this.getNumericValueDistribution());
		uniformDistributionEClass.getESuperTypes().add(this.getNumericValueDistribution());
		exponentialDistributionEClass.getESuperTypes().add(this.getNumericValueDistribution());
		simulatingSlotEClass.getESuperTypes().add(theUMLPackage.getSlot());
		numericValueDistributionEClass.getESuperTypes().add(this.getSimulatedValue());
		weightedEnumLiteralValueEClass.getESuperTypes().add(this.getSimulatedValue());
		containedActualInstanceEClass.getESuperTypes().add(this.getSimulatedValue());
		weightedBooleanValueEClass.getESuperTypes().add(this.getSimulatedValue());
		numberRangeDistributionEClass.getESuperTypes().add(this.getNumericValueDistribution());
		weightedStringValueEClass.getESuperTypes().add(this.getSimulatedValue());
		simulationModelEClass.getESuperTypes().add(theUMLPackage.getPackage());
		instanceSimulationEClass.getESuperTypes().add(theUMLPackage.getInstanceSpecification());
		actualInstanceEClass.getESuperTypes().add(theUMLPackage.getInstanceSpecification());
		weightedInstanceValueEClass.getESuperTypes().add(this.getSimulatedValue());
		weightedSimpleTypeValueEClass.getESuperTypes().add(this.getSimulatedValue());
		literalSimpleTypeEClass.getESuperTypes().add(theUMLPackage.getLiteralString());

		// Initialize classes and features; add operations and parameters
		initEClass(simulatedValueEClass, SimulatedValue.class, "SimulatedValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(normalDistributionEClass, NormalDistribution.class, "NormalDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNormalDistribution_Mean(), ecorePackage.getEDoubleObject(), "mean", null, 0, 1, NormalDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNormalDistribution_StandardDeviation(), ecorePackage.getEDoubleObject(), "standardDeviation", null, 0, 1, NormalDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uniformDistributionEClass, UniformDistribution.class, "UniformDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUniformDistribution_LowerLimit(), ecorePackage.getEDoubleObject(), "lowerLimit", null, 0, 1, UniformDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUniformDistribution_UpperLimit(), ecorePackage.getEDoubleObject(), "upperLimit", null, 0, 1, UniformDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exponentialDistributionEClass, ExponentialDistribution.class, "ExponentialDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExponentialDistribution_Mean(), ecorePackage.getEDoubleObject(), "mean", null, 0, 1, ExponentialDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(simulatingSlotEClass, SimulatingSlot.class, "SimulatingSlot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSimulatingSlot_SizeDistribution(), this.getNumericValueDistribution(), null, "sizeDistribution", null, 0, 1, SimulatingSlot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulatingSlot_SimulationStrategy(), this.getSimulationStrategy(), "simulationStrategy", "", 0, 1, SimulatingSlot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numericValueDistributionEClass, NumericValueDistribution.class, "NumericValueDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(weightedEnumLiteralValueEClass, WeightedEnumLiteralValue.class, "WeightedEnumLiteralValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeightedEnumLiteralValue_Weight(), ecorePackage.getEDoubleObject(), "weight", null, 0, 1, WeightedEnumLiteralValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeightedEnumLiteralValue_Literal(), theUMLPackage.getEnumerationLiteral(), null, "literal", null, 0, 1, WeightedEnumLiteralValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containedActualInstanceEClass, ContainedActualInstance.class, "ContainedActualInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainedActualInstance_Values(), theUMLPackage.getInstanceSpecification(), null, "values", null, 0, -1, ContainedActualInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainedActualInstance_ContainedInstance(), this.getActualInstance(), null, "containedInstance", null, 0, 1, ContainedActualInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(weightedBooleanValueEClass, WeightedBooleanValue.class, "WeightedBooleanValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeightedBooleanValue_Weight(), ecorePackage.getEDoubleObject(), "weight", "0.5", 0, 1, WeightedBooleanValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeightedBooleanValue_Value(), theEcorePackage.getEBoolean(), "value", null, 0, 1, WeightedBooleanValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numberRangeDistributionEClass, NumberRangeDistribution.class, "NumberRangeDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumberRangeDistribution_UpperValue(), ecorePackage.getEDoubleObject(), "upperValue", null, 0, 1, NumberRangeDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberRangeDistribution_LowerValue(), ecorePackage.getEDoubleObject(), "lowerValue", null, 0, 1, NumberRangeDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberRangeDistribution_Weight(), ecorePackage.getEDoubleObject(), "weight", null, 0, 1, NumberRangeDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(weightedStringValueEClass, WeightedStringValue.class, "WeightedStringValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeightedStringValue_Weight(), ecorePackage.getEDoubleObject(), "weight", "0.5", 0, 1, WeightedStringValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeightedStringValue_Value(), theEcorePackage.getEString(), "value", null, 0, 1, WeightedStringValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(simulationModelEClass, SimulationModel.class, "SimulationModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(instanceSimulationEClass, InstanceSimulation.class, "InstanceSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(actualInstanceEClass, ActualInstance.class, "ActualInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(weightedInstanceValueEClass, WeightedInstanceValue.class, "WeightedInstanceValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeightedInstanceValue_Weight(), theEcorePackage.getEDoubleObject(), "weight", null, 0, 1, WeightedInstanceValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWeightedInstanceValue_Instance(), theUMLPackage.getInstanceSpecification(), null, "instance", null, 0, 1, WeightedInstanceValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(weightedSimpleTypeValueEClass, WeightedSimpleTypeValue.class, "WeightedSimpleTypeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWeightedSimpleTypeValue_StringValue(), theEcorePackage.getEString(), "stringValue", null, 0, 1, WeightedSimpleTypeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeightedSimpleTypeValue_Weight(), ecorePackage.getEDoubleObject(), "weight", null, 0, 1, WeightedSimpleTypeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWeightedSimpleTypeValue_RuntimeStrategyFactory(), theEcorePackage.getEString(), "runtimeStrategyFactory", null, 0, 1, WeightedSimpleTypeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(literalSimpleTypeEClass, LiteralSimpleType.class, "LiteralSimpleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLiteralSimpleType_StringValue(), theEcorePackage.getEString(), "stringValue", null, 0, 1, LiteralSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLiteralSimpleType_RuntimeStrategyFactory(), theEcorePackage.getEString(), "runtimeStrategyFactory", null, 0, 1, LiteralSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(simulationStrategyEEnum, SimulationStrategy.class, "SimulationStrategy");
		addEEnumLiteral(simulationStrategyEEnum, SimulationStrategy.GIVEN_VALUE);
		addEEnumLiteral(simulationStrategyEEnum, SimulationStrategy.WEIGHTED_DISTRIBUTION);
		addEEnumLiteral(simulationStrategyEEnum, SimulationStrategy.NORMAL_DISTRIBUTION);
		addEEnumLiteral(simulationStrategyEEnum, SimulationStrategy.UNIFORM_DISTRIBUTION);
		addEEnumLiteral(simulationStrategyEEnum, SimulationStrategy.INSTANCE_SIMULATION);

		// Create resource
		createResource(eNS_URI);
	}

} //SimulationPackageImpl
