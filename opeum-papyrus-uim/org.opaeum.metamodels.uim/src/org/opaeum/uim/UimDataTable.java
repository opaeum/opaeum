/**
 */
package org.opaeum.uim;

import org.opaeum.uim.binding.TableBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.UimDataTable#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getUimDataTable()
 * @model
 * @generated
 */
public interface UimDataTable extends MasterComponent, UimContainer {
	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.binding.TableBinding#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(TableBinding)
	 * @see org.opaeum.uim.UimPackage#getUimDataTable_Binding()
	 * @see org.opaeum.uim.binding.TableBinding#getTable
	 * @model opposite="table" containment="true"
	 * @generated
	 */
	TableBinding getBinding();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UimDataTable#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(TableBinding value);

} // UimDataTable
