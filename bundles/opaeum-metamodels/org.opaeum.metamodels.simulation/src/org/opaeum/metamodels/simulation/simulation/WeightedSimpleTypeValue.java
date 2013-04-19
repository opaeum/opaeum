/**
 */
package org.opaeum.metamodels.simulation.simulation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Weighted Value Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getStringValue <em>String Value</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getRuntimeStrategyFactory <em>Runtime Strategy Factory</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getWeightedSimpleTypeValue()
 * @model
 * @generated
 */
public interface WeightedSimpleTypeValue extends SimulatedValue {
	/**
	 * Returns the value of the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String Value</em>' attribute.
	 * @see #setStringValue(String)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getWeightedSimpleTypeValue_StringValue()
	 * @model
	 * @generated
	 */
	String getStringValue();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getStringValue <em>String Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String Value</em>' attribute.
	 * @see #getStringValue()
	 * @generated
	 */
	void setStringValue(String value);

	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see #setWeight(Double)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getWeightedSimpleTypeValue_Weight()
	 * @model
	 * @generated
	 */
	Double getWeight();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getWeight <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight</em>' attribute.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(Double value);

	/**
	 * Returns the value of the '<em><b>Runtime Strategy Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runtime Strategy Factory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runtime Strategy Factory</em>' attribute.
	 * @see #setRuntimeStrategyFactory(String)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getWeightedSimpleTypeValue_RuntimeStrategyFactory()
	 * @model
	 * @generated
	 */
	String getRuntimeStrategyFactory();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getRuntimeStrategyFactory <em>Runtime Strategy Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runtime Strategy Factory</em>' attribute.
	 * @see #getRuntimeStrategyFactory()
	 * @generated
	 */
	void setRuntimeStrategyFactory(String value);

} // WeightedValueType
