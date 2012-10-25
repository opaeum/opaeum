/**
 */
package org.opaeum.uim.component;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.Labels;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.panel.Outlayable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Data Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.UimDataTable#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.opaeum.uim.component.UimDataTable#getActionsOnMultipleSelection <em>Actions On Multiple Selection</em>}</li>
 *   <li>{@link org.opaeum.uim.component.UimDataTable#getLabelOverride <em>Label Override</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.component.ComponentPackage#getUimDataTable()
 * @model
 * @generated
 */
public interface UimDataTable extends MasterComponent, UimContainer, Outlayable {
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
	 * @see org.opaeum.uim.component.ComponentPackage#getUimDataTable_Binding()
	 * @see org.opaeum.uim.binding.TableBinding#getTable
	 * @model opposite="table" containment="true"
	 * @generated
	 */
	TableBinding getBinding();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.component.UimDataTable#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(TableBinding value);

	/**
	 * Returns the value of the '<em><b>Actions On Multiple Selection</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.action.AbstractActionButton}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions On Multiple Selection</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions On Multiple Selection</em>' containment reference list.
	 * @see org.opaeum.uim.component.ComponentPackage#getUimDataTable_ActionsOnMultipleSelection()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractActionButton> getActionsOnMultipleSelection();

	/**
	 * Returns the value of the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Override</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Override</em>' containment reference.
	 * @see #setLabelOverride(Labels)
	 * @see org.opaeum.uim.component.ComponentPackage#getUimDataTable_LabelOverride()
	 * @model containment="true"
	 * @generated
	 */
	Labels getLabelOverride();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.component.UimDataTable#getLabelOverride <em>Label Override</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Override</em>' containment reference.
	 * @see #getLabelOverride()
	 * @generated
	 */
	void setLabelOverride(Labels value);

} // UimDataTable
