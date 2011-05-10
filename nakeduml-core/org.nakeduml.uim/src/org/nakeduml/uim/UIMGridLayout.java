/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grid Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UIMGridLayout#getNumberOfColumns <em>Number Of Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getUIMGridLayout()
 * @model
 * @generated
 */
public interface UIMGridLayout extends UIMLayout {
	/**
	 * Returns the value of the '<em><b>Number Of Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Columns</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Columns</em>' attribute.
	 * @see #setNumberOfColumns(int)
	 * @see org.nakeduml.uim.UIMPackage#getUIMGridLayout_NumberOfColumns()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getNumberOfColumns();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMGridLayout#getNumberOfColumns <em>Number Of Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Columns</em>' attribute.
	 * @see #getNumberOfColumns()
	 * @generated
	 */
	void setNumberOfColumns(int value);

} // UIMGridLayout
