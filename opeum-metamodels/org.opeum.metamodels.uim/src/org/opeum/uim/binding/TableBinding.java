/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.binding;

import org.opeum.uim.UimDataTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.binding.TableBinding#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.binding.BindingPackage#getTableBinding()
 * @model
 * @generated
 */
public interface TableBinding extends UimBinding {
	/**
	 * Returns the value of the '<em><b>Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.UimDataTable#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' container reference.
	 * @see #setTable(UimDataTable)
	 * @see org.opeum.uim.binding.BindingPackage#getTableBinding_Table()
	 * @see org.opeum.uim.UimDataTable#getBinding
	 * @model opposite="binding" required="true" transient="false"
	 * @generated
	 */
	UimDataTable getTable();

	/**
	 * Sets the value of the '{@link org.opeum.uim.binding.TableBinding#getTable <em>Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' container reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(UimDataTable value);

} // TableBinding
