/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.layout;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Grid Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.layout.UimGridLayout#getNumberOfColumns <em>Number Of Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.layout.LayoutPackage#getUimGridLayout()
 * @model
 * @generated
 */
public interface UimGridLayout extends UimLayout {
	/**
	 * Returns the value of the '<em><b>Number Of Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Columns</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Columns</em>' attribute.
	 * @see #setNumberOfColumns(Integer)
	 * @see org.opaeum.uim.layout.LayoutPackage#getUimGridLayout_NumberOfColumns()
	 * @model
	 * @generated
	 */
	Integer getNumberOfColumns();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.layout.UimGridLayout#getNumberOfColumns <em>Number Of Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Columns</em>' attribute.
	 * @see #getNumberOfColumns()
	 * @generated
	 */
	void setNumberOfColumns(Integer value);

} // UimGridLayout
