/**
 */
package org.opaeum.uim.cube;

import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measure Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.MeasureProperty#getAggregationFormula <em>Aggregation Formula</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.cube.CubePackage#getMeasureProperty()
 * @model
 * @generated
 */
public interface MeasureProperty extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Aggregation Formula</b></em>' attribute.
	 * The default value is <code>"null"</code>.
	 * The literals are from the enumeration {@link org.opaeum.uim.cube.AggregationFormula}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregation Formula</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregation Formula</em>' attribute.
	 * @see org.opaeum.uim.cube.AggregationFormula
	 * @see #setAggregationFormula(AggregationFormula)
	 * @see org.opaeum.uim.cube.CubePackage#getMeasureProperty_AggregationFormula()
	 * @model default="null"
	 * @generated
	 */
	AggregationFormula getAggregationFormula();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.cube.MeasureProperty#getAggregationFormula <em>Aggregation Formula</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregation Formula</em>' attribute.
	 * @see org.opaeum.uim.cube.AggregationFormula
	 * @see #getAggregationFormula()
	 * @generated
	 */
	void setAggregationFormula(AggregationFormula value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean equals(Object o);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int hashCode();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String toString();

} // MeasureProperty
