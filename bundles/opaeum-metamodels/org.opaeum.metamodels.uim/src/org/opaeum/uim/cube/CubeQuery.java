/**
 */
package org.opaeum.uim.cube;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.Page;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.CubeQuery#getColumnAxis <em>Column Axis</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.CubeQuery#getRowAxis <em>Row Axis</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.CubeQuery#getMeasures <em>Measures</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.cube.CubePackage#getCubeQuery()
 * @model
 * @generated
 */
public interface CubeQuery extends Page {
	/**
	 * Returns the value of the '<em><b>Column Axis</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.cube.ColumnAxisEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Axis</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Axis</em>' containment reference list.
	 * @see org.opaeum.uim.cube.CubePackage#getCubeQuery_ColumnAxis()
	 * @model containment="true"
	 * @generated
	 */
	EList<ColumnAxisEntry> getColumnAxis();

	/**
	 * Returns the value of the '<em><b>Row Axis</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.cube.RowAxisEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Axis</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Axis</em>' containment reference list.
	 * @see org.opaeum.uim.cube.CubePackage#getCubeQuery_RowAxis()
	 * @model containment="true"
	 * @generated
	 */
	EList<RowAxisEntry> getRowAxis();

	/**
	 * Returns the value of the '<em><b>Measures</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.cube.MeasureProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measures</em>' containment reference list.
	 * @see org.opaeum.uim.cube.CubePackage#getCubeQuery_Measures()
	 * @model containment="true"
	 * @generated
	 */
	EList<MeasureProperty> getMeasures();

} // CubeQuery
