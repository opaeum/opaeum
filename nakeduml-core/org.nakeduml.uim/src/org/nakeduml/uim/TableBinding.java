/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table UIMBinding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.TableBinding#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getTableBinding()
 * @model
 * @generated
 */
public interface TableBinding extends UIMBinding {
	/**
	 * Returns the value of the '<em><b>Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.UIMDataTable#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' container reference.
	 * @see #setTable(UIMDataTable)
	 * @see org.nakeduml.uim.UIMPackage#getTableBinding_Table()
	 * @see org.nakeduml.uim.UIMDataTable#getBinding
	 * @model opposite="binding" required="true" transient="false"
	 * @generated
	 */
	UIMDataTable getTable();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.TableBinding#getTable <em>Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' container reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(UIMDataTable value);

} // TableBinding
