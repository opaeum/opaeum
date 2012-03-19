/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.UMLPackage;

import org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation;
import org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation;
import org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation;
import org.opaeum.metamodels.simulation.simulation.ExponentialDistribution;
import org.opaeum.metamodels.simulation.simulation.NormalDistribution;
import org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution;
import org.opaeum.metamodels.simulation.simulation.NumericValueDistribution;
import org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.SimulatedSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;
import org.opaeum.metamodels.simulation.simulation.StringValueSimulation;
import org.opaeum.metamodels.simulation.simulation.UniformDistribution;
import org.opaeum.metamodels.simulation.simulation.ValueSimulation;

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
	private EClass valueSimulationEClass = null;

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
	private EClass simulatedSlotEClass = null;

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
	private EClass enumLiteralSimulationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containedInstanceValueSimulationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanValueSimulationEClass = null;

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
	private EClass referencedInstanceSimulationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringValueSimulationEClass = null;

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
	public EClass getValueSimulation() {
		return valueSimulationEClass;
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
	public EClass getSimulatedSlot() {
		return simulatedSlotEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimulatedSlot_SizeDistribution() {
		return (EReference)simulatedSlotEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSimulatedSlot_Property() {
		return (EReference)simulatedSlotEClass.getEStructuralFeatures().get(1);
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
	public EClass getEnumLiteralSimulation() {
		return enumLiteralSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumLiteralSimulation_Weight() {
		return (EAttribute)enumLiteralSimulationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainedInstanceValueSimulation() {
		return containedInstanceValueSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainedInstanceValueSimulation_Values() {
		return (EReference)containedInstanceValueSimulationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanValueSimulation() {
		return booleanValueSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanValueSimulation_Weight() {
		return (EAttribute)booleanValueSimulationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanValueSimulation_Value() {
		return (EAttribute)booleanValueSimulationEClass.getEStructuralFeatures().get(1);
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
	public EClass getReferencedInstanceSimulation() {
		return referencedInstanceSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferencedInstanceSimulation_Weight() {
		return (EAttribute)referencedInstanceSimulationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferencedInstanceSimulation_Instance() {
		return (EReference)referencedInstanceSimulationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringValueSimulation() {
		return stringValueSimulationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringValueSimulation_Weight() {
		return (EAttribute)stringValueSimulationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringValueSimulation_Value() {
		return (EAttribute)stringValueSimulationEClass.getEStructuralFeatures().get(1);
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
		valueSimulationEClass = createEClass(VALUE_SIMULATION);

		normalDistributionEClass = createEClass(NORMAL_DISTRIBUTION);
		createEAttribute(normalDistributionEClass, NORMAL_DISTRIBUTION__MEAN);
		createEAttribute(normalDistributionEClass, NORMAL_DISTRIBUTION__STANDARD_DEVIATION);

		uniformDistributionEClass = createEClass(UNIFORM_DISTRIBUTION);
		createEAttribute(uniformDistributionEClass, UNIFORM_DISTRIBUTION__LOWER_LIMIT);
		createEAttribute(uniformDistributionEClass, UNIFORM_DISTRIBUTION__UPPER_LIMIT);

		exponentialDistributionEClass = createEClass(EXPONENTIAL_DISTRIBUTION);
		createEAttribute(exponentialDistributionEClass, EXPONENTIAL_DISTRIBUTION__MEAN);

		simulatedSlotEClass = createEClass(SIMULATED_SLOT);
		createEReference(simulatedSlotEClass, SIMULATED_SLOT__SIZE_DISTRIBUTION);
		createEReference(simulatedSlotEClass, SIMULATED_SLOT__PROPERTY);

		numericValueDistributionEClass = createEClass(NUMERIC_VALUE_DISTRIBUTION);

		enumLiteralSimulationEClass = createEClass(ENUM_LITERAL_SIMULATION);
		createEAttribute(enumLiteralSimulationEClass, ENUM_LITERAL_SIMULATION__WEIGHT);

		containedInstanceValueSimulationEClass = createEClass(CONTAINED_INSTANCE_VALUE_SIMULATION);
		createEReference(containedInstanceValueSimulationEClass, CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES);

		booleanValueSimulationEClass = createEClass(BOOLEAN_VALUE_SIMULATION);
		createEAttribute(booleanValueSimulationEClass, BOOLEAN_VALUE_SIMULATION__WEIGHT);
		createEAttribute(booleanValueSimulationEClass, BOOLEAN_VALUE_SIMULATION__VALUE);

		numberRangeDistributionEClass = createEClass(NUMBER_RANGE_DISTRIBUTION);
		createEAttribute(numberRangeDistributionEClass, NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE);
		createEAttribute(numberRangeDistributionEClass, NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE);
		createEAttribute(numberRangeDistributionEClass, NUMBER_RANGE_DISTRIBUTION__WEIGHT);

		referencedInstanceSimulationEClass = createEClass(REFERENCED_INSTANCE_SIMULATION);
		createEAttribute(referencedInstanceSimulationEClass, REFERENCED_INSTANCE_SIMULATION__WEIGHT);
		createEReference(referencedInstanceSimulationEClass, REFERENCED_INSTANCE_SIMULATION__INSTANCE);

		stringValueSimulationEClass = createEClass(STRING_VALUE_SIMULATION);
		createEAttribute(stringValueSimulationEClass, STRING_VALUE_SIMULATION__WEIGHT);
		createEAttribute(stringValueSimulationEClass, STRING_VALUE_SIMULATION__VALUE);
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
		valueSimulationEClass.getESuperTypes().add(theUMLPackage.getValueSpecification());
		normalDistributionEClass.getESuperTypes().add(this.getNumericValueDistribution());
		uniformDistributionEClass.getESuperTypes().add(this.getNumericValueDistribution());
		exponentialDistributionEClass.getESuperTypes().add(this.getNumericValueDistribution());
		simulatedSlotEClass.getESuperTypes().add(theUMLPackage.getSlot());
		numericValueDistributionEClass.getESuperTypes().add(this.getValueSimulation());
		enumLiteralSimulationEClass.getESuperTypes().add(this.getValueSimulation());
		containedInstanceValueSimulationEClass.getESuperTypes().add(this.getValueSimulation());
		containedInstanceValueSimulationEClass.getESuperTypes().add(theUMLPackage.getInstanceSpecification());
		booleanValueSimulationEClass.getESuperTypes().add(this.getValueSimulation());
		numberRangeDistributionEClass.getESuperTypes().add(this.getNumericValueDistribution());
		referencedInstanceSimulationEClass.getESuperTypes().add(this.getValueSimulation());
		stringValueSimulationEClass.getESuperTypes().add(this.getValueSimulation());

		// Initialize classes and features; add operations and parameters
		initEClass(valueSimulationEClass, ValueSimulation.class, "ValueSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(normalDistributionEClass, NormalDistribution.class, "NormalDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNormalDistribution_Mean(), ecorePackage.getEDoubleObject(), "mean", null, 0, 1, NormalDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNormalDistribution_StandardDeviation(), ecorePackage.getEDoubleObject(), "standardDeviation", null, 0, 1, NormalDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uniformDistributionEClass, UniformDistribution.class, "UniformDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUniformDistribution_LowerLimit(), ecorePackage.getEDoubleObject(), "lowerLimit", null, 0, 1, UniformDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUniformDistribution_UpperLimit(), ecorePackage.getEDoubleObject(), "upperLimit", null, 0, 1, UniformDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exponentialDistributionEClass, ExponentialDistribution.class, "ExponentialDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExponentialDistribution_Mean(), ecorePackage.getEDoubleObject(), "mean", null, 0, 1, ExponentialDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(simulatedSlotEClass, SimulatedSlot.class, "SimulatedSlot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSimulatedSlot_SizeDistribution(), this.getNumericValueDistribution(), null, "sizeDistribution", null, 0, 1, SimulatedSlot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSimulatedSlot_Property(), theUMLPackage.getProperty(), null, "property", null, 0, 1, SimulatedSlot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numericValueDistributionEClass, NumericValueDistribution.class, "NumericValueDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(enumLiteralSimulationEClass, EnumLiteralSimulation.class, "EnumLiteralSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumLiteralSimulation_Weight(), ecorePackage.getEDoubleObject(), "weight", null, 0, 1, EnumLiteralSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containedInstanceValueSimulationEClass, ContainedInstanceValueSimulation.class, "ContainedInstanceValueSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainedInstanceValueSimulation_Values(), theUMLPackage.getInstanceSpecification(), null, "values", null, 0, -1, ContainedInstanceValueSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanValueSimulationEClass, BooleanValueSimulation.class, "BooleanValueSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanValueSimulation_Weight(), ecorePackage.getEDoubleObject(), "weight", "0.5", 0, 1, BooleanValueSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBooleanValueSimulation_Value(), theEcorePackage.getEBoolean(), "value", null, 0, 1, BooleanValueSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numberRangeDistributionEClass, NumberRangeDistribution.class, "NumberRangeDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumberRangeDistribution_UpperValue(), ecorePackage.getEDoubleObject(), "upperValue", null, 0, 1, NumberRangeDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberRangeDistribution_LowerValue(), ecorePackage.getEDoubleObject(), "lowerValue", null, 0, 1, NumberRangeDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNumberRangeDistribution_Weight(), ecorePackage.getEDoubleObject(), "weight", null, 0, 1, NumberRangeDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referencedInstanceSimulationEClass, ReferencedInstanceSimulation.class, "ReferencedInstanceSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReferencedInstanceSimulation_Weight(), ecorePackage.getEDoubleObject(), "weight", null, 0, 1, ReferencedInstanceSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReferencedInstanceSimulation_Instance(), this.getContainedInstanceValueSimulation(), null, "instance", null, 0, 1, ReferencedInstanceSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringValueSimulationEClass, StringValueSimulation.class, "StringValueSimulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringValueSimulation_Weight(), ecorePackage.getEDoubleObject(), "weight", "0.5", 0, 1, StringValueSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringValueSimulation_Value(), theEcorePackage.getEString(), "value", null, 0, 1, StringValueSimulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SimulationPackageImpl
