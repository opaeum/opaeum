/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UimDataTable#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.nakeduml.uim.UimDataTable#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UimPackage#getUimDataTable()
 * @model
 * @generated
 */
public interface UimDataTable extends MasterComponent, UimContainer, OutlayableComponent {
	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.TableBinding#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(TableBinding)
	 * @see org.nakeduml.uim.UimPackage#getUimDataTable_Binding()
	 * @see org.nakeduml.uim.TableBinding#getTable
	 * @model opposite="table" containment="true"
	 * @generated
	 */
	TableBinding getBinding();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UimDataTable#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(TableBinding value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.UimDataColumn}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.UimDataColumn#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nakeduml.uim.UimPackage#getUimDataTable_Children()
	 * @see org.nakeduml.uim.UimDataColumn#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<UimDataColumn> getChildren();

} // UimDataTable
