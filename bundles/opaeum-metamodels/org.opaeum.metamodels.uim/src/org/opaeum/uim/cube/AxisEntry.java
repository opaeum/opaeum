/**
 */
package org.opaeum.uim.cube;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Axis Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.AxisEntry#getDimensionBinding <em>Dimension Binding</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.AxisEntry#getLevelProperty <em>Level Property</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.cube.CubePackage#getAxisEntry()
 * @model abstract="true"
 * @generated
 */
public interface AxisEntry extends EObject {
	/**
	 * Returns the value of the '<em><b>Dimension Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dimension Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimension Binding</em>' containment reference.
	 * @see #setDimensionBinding(DimensionBinding)
	 * @see org.opaeum.uim.cube.CubePackage#getAxisEntry_DimensionBinding()
	 * @model containment="true"
	 * @generated
	 */
	DimensionBinding getDimensionBinding();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.cube.AxisEntry#getDimensionBinding <em>Dimension Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimension Binding</em>' containment reference.
	 * @see #getDimensionBinding()
	 * @generated
	 */
	void setDimensionBinding(DimensionBinding value);

	/**
	 * Returns the value of the '<em><b>Level Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.cube.LevelProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Level Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level Property</em>' containment reference list.
	 * @see org.opaeum.uim.cube.CubePackage#getAxisEntry_LevelProperty()
	 * @model containment="true"
	 * @generated
	 */
	EList<LevelProperty> getLevelProperty();

} // AxisEntry
