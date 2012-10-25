/**
 */
package org.opaeum.metamodels.simulation.simulation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Weighted Boolean Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue#isValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getWeightedBooleanValue()
 * @model
 * @generated
 */
public interface WeightedBooleanValue extends SimulatedValue {
	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute.
	 * The default value is <code>"0.5"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see #setWeight(Double)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getWeightedBooleanValue_Weight()
	 * @model default="0.5"
	 * @generated
	 */
	Double getWeight();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue#getWeight <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight</em>' attribute.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(Double value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(boolean)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getWeightedBooleanValue_Value()
	 * @model
	 * @generated
	 */
	boolean isValue();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue#isValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #isValue()
	 * @generated
	 */
	void setValue(boolean value);

} // WeightedBooleanValue
