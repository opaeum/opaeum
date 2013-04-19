/**
 */
package org.opaeum.metamodels.simulation.simulation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exponential Distribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.ExponentialDistribution#getMean <em>Mean</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getExponentialDistribution()
 * @model
 * @generated
 */
public interface ExponentialDistribution extends NumericValueDistribution {
	/**
	 * Returns the value of the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mean</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mean</em>' attribute.
	 * @see #setMean(Double)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getExponentialDistribution_Mean()
	 * @model
	 * @generated
	 */
	Double getMean();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.ExponentialDistribution#getMean <em>Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mean</em>' attribute.
	 * @see #getMean()
	 * @generated
	 */
	void setMean(Double value);

} // ExponentialDistribution
