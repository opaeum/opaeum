/**
 */
package org.opaeum.metamodels.simulation.simulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Strategy</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getSimulationStrategy()
 * @model
 * @generated
 */
public enum SimulationStrategy implements Enumerator {
	/**
	 * The '<em><b>Given Value</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GIVEN_VALUE_VALUE
	 * @generated
	 * @ordered
	 */
	GIVEN_VALUE(0, "givenValue", "givenValue"),

	/**
	 * The '<em><b>Weighted Distribution</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEIGHTED_DISTRIBUTION_VALUE
	 * @generated
	 * @ordered
	 */
	WEIGHTED_DISTRIBUTION(1, "weightedDistribution", "weightedDistribution"),

	/**
	 * The '<em><b>Normal Distribution</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NORMAL_DISTRIBUTION_VALUE
	 * @generated
	 * @ordered
	 */
	NORMAL_DISTRIBUTION(2, "normalDistribution", "normalDistribution"),

	/**
	 * The '<em><b>Uniform Distribution</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIFORM_DISTRIBUTION_VALUE
	 * @generated
	 * @ordered
	 */
	UNIFORM_DISTRIBUTION(3, "uniformDistribution", "uniformDistribution"),

	/**
	 * The '<em><b>All Instance Simulation</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL_INSTANCE_SIMULATION_VALUE
	 * @generated
	 * @ordered
	 */
	ALL_INSTANCE_SIMULATION(4, "allInstanceSimulation", "allInstanceSimulation");

	/**
	 * The '<em><b>Given Value</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Given Value</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GIVEN_VALUE
	 * @model name="givenValue"
	 * @generated
	 * @ordered
	 */
	public static final int GIVEN_VALUE_VALUE = 0;

	/**
	 * The '<em><b>Weighted Distribution</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Weighted Distribution</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEIGHTED_DISTRIBUTION
	 * @model name="weightedDistribution"
	 * @generated
	 * @ordered
	 */
	public static final int WEIGHTED_DISTRIBUTION_VALUE = 1;

	/**
	 * The '<em><b>Normal Distribution</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Normal Distribution</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NORMAL_DISTRIBUTION
	 * @model name="normalDistribution"
	 * @generated
	 * @ordered
	 */
	public static final int NORMAL_DISTRIBUTION_VALUE = 2;

	/**
	 * The '<em><b>Uniform Distribution</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Uniform Distribution</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNIFORM_DISTRIBUTION
	 * @model name="uniformDistribution"
	 * @generated
	 * @ordered
	 */
	public static final int UNIFORM_DISTRIBUTION_VALUE = 3;

	/**
	 * The '<em><b>All Instance Simulation</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>All Instance Simulation</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ALL_INSTANCE_SIMULATION
	 * @model name="allInstanceSimulation"
	 * @generated
	 * @ordered
	 */
	public static final int ALL_INSTANCE_SIMULATION_VALUE = 4;

	/**
	 * An array of all the '<em><b>Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SimulationStrategy[] VALUES_ARRAY =
		new SimulationStrategy[] {
			GIVEN_VALUE,
			WEIGHTED_DISTRIBUTION,
			NORMAL_DISTRIBUTION,
			UNIFORM_DISTRIBUTION,
			ALL_INSTANCE_SIMULATION,
		};

	/**
	 * A public read-only list of all the '<em><b>Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SimulationStrategy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Strategy</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SimulationStrategy get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SimulationStrategy result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Strategy</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SimulationStrategy getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SimulationStrategy result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Strategy</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SimulationStrategy get(int value) {
		switch (value) {
			case GIVEN_VALUE_VALUE: return GIVEN_VALUE;
			case WEIGHTED_DISTRIBUTION_VALUE: return WEIGHTED_DISTRIBUTION;
			case NORMAL_DISTRIBUTION_VALUE: return NORMAL_DISTRIBUTION;
			case UNIFORM_DISTRIBUTION_VALUE: return UNIFORM_DISTRIBUTION;
			case ALL_INSTANCE_SIMULATION_VALUE: return ALL_INSTANCE_SIMULATION;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SimulationStrategy(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //SimulationStrategy
