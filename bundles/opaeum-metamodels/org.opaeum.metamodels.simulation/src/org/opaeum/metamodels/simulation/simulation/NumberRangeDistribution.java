/**
 */
package org.opaeum.metamodels.simulation.simulation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Number Range Distribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getNumberRangeDistribution()
 * @model
 * @generated
 */
public interface NumberRangeDistribution extends NumericValueDistribution {
	/**
	 * Returns the value of the '<em><b>Upper Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Value</em>' attribute.
	 * @see #setUpperValue(Double)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getNumberRangeDistribution_UpperValue()
	 * @model
	 * @generated
	 */
	Double getUpperValue();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getUpperValue <em>Upper Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Value</em>' attribute.
	 * @see #getUpperValue()
	 * @generated
	 */
	void setUpperValue(Double value);

	/**
	 * Returns the value of the '<em><b>Lower Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Value</em>' attribute.
	 * @see #setLowerValue(Double)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getNumberRangeDistribution_LowerValue()
	 * @model
	 * @generated
	 */
	Double getLowerValue();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getLowerValue <em>Lower Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Value</em>' attribute.
	 * @see #getLowerValue()
	 * @generated
	 */
	void setLowerValue(Double value);

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
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getNumberRangeDistribution_Weight()
	 * @model
	 * @generated
	 */
	Double getWeight();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getWeight <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight</em>' attribute.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(Double value);

} // NumberRangeDistribution
