/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim;

import org.opeum.uim.binding.TableBinding;
import org.opeum.uim.layout.LayoutContainer;
import org.opeum.uim.layout.OutlayableComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.UimDataTable#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.UimPackage#getUimDataTable()
 * @model
 * @generated
 */
public interface UimDataTable extends MasterComponent, OutlayableComponent, LayoutContainer {
	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.binding.TableBinding#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(TableBinding)
	 * @see org.opeum.uim.UimPackage#getUimDataTable_Binding()
	 * @see org.opeum.uim.binding.TableBinding#getTable
	 * @model opposite="table" containment="true"
	 * @generated
	 */
	TableBinding getBinding();

	/**
	 * Sets the value of the '{@link org.opeum.uim.UimDataTable#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(TableBinding value);

} // UimDataTable
