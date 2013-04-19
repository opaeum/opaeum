/**
 */
package org.opaeum.uim.binding;

import org.opaeum.uim.component.UimDataTable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.binding.TableBinding#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.binding.BindingPackage#getTableBinding()
 * @model
 * @generated
 */
public interface TableBinding extends UimBinding {
	/**
	 * Returns the value of the '<em><b>Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.component.UimDataTable#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' container reference.
	 * @see #setTable(UimDataTable)
	 * @see org.opaeum.uim.binding.BindingPackage#getTableBinding_Table()
	 * @see org.opaeum.uim.component.UimDataTable#getBinding
	 * @model opposite="binding" required="true" transient="false"
	 * @generated
	 */
	UimDataTable getTable();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.binding.TableBinding#getTable <em>Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' container reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(UimDataTable value);

} // TableBinding
