/**
 */
package org.opaeum.metamodels.simulation.simulation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uniform Distribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.UniformDistribution#getLowerLimit <em>Lower Limit</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.UniformDistribution#getUpperLimit <em>Upper Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getUniformDistribution()
 * @model
 * @generated
 */
public interface UniformDistribution extends NumericValueDistribution {
	/**
	 * Returns the value of the '<em><b>Lower Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Limit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Limit</em>' attribute.
	 * @see #setLowerLimit(Double)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getUniformDistribution_LowerLimit()
	 * @model
	 * @generated
	 */
	Double getLowerLimit();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.UniformDistribution#getLowerLimit <em>Lower Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Limit</em>' attribute.
	 * @see #getLowerLimit()
	 * @generated
	 */
	void setLowerLimit(Double value);

	/**
	 * Returns the value of the '<em><b>Upper Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Limit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Limit</em>' attribute.
	 * @see #setUpperLimit(Double)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getUniformDistribution_UpperLimit()
	 * @model
	 * @generated
	 */
	Double getUpperLimit();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.UniformDistribution#getUpperLimit <em>Upper Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Limit</em>' attribute.
	 * @see #getUpperLimit()
	 * @generated
	 */
	void setUpperLimit(Double value);

} // UniformDistribution
