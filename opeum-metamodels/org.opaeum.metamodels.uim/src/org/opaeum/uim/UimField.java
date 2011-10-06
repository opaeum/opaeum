/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim;

import org.opeum.uim.binding.FieldBinding;
import org.opeum.uim.control.ControlKind;
import org.opeum.uim.control.UimControl;
import org.opeum.uim.layout.OutlayableComponent;
import org.opeum.uim.security.EditableSecureObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A User Field is an abstract superclass for all fields a user can view and/or update on a form, typically represented as some kind of input/output form field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.UimField#getControl <em>Control</em>}</li>
 *   <li>{@link org.opeum.uim.UimField#getControlKind <em>Control Kind</em>}</li>
 *   <li>{@link org.opeum.uim.UimField#getLabelWidth <em>Label Width</em>}</li>
 *   <li>{@link org.opeum.uim.UimField#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.UimPackage#getUimField()
 * @model
 * @generated
 */
public interface UimField extends EditableSecureObject, OutlayableComponent {
	/**
	 * Returns the value of the '<em><b>Control</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.control.UimControl#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control</em>' containment reference.
	 * @see #setControl(UimControl)
	 * @see org.opeum.uim.UimPackage#getUimField_Control()
	 * @see org.opeum.uim.control.UimControl#getField
	 * @model opposite="field" containment="true" required="true"
	 * @generated
	 */
	UimControl getControl();

	/**
	 * Sets the value of the '{@link org.opeum.uim.UimField#getControl <em>Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control</em>' containment reference.
	 * @see #getControl()
	 * @generated
	 */
	void setControl(UimControl value);

	/**
	 * Returns the value of the '<em><b>Control Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.opeum.uim.control.ControlKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Kind</em>' attribute.
	 * @see org.opeum.uim.control.ControlKind
	 * @see #setControlKind(ControlKind)
	 * @see org.opeum.uim.UimPackage#getUimField_ControlKind()
	 * @model
	 * @generated
	 */
	ControlKind getControlKind();

	/**
	 * Sets the value of the '{@link org.opeum.uim.UimField#getControlKind <em>Control Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Kind</em>' attribute.
	 * @see org.opeum.uim.control.ControlKind
	 * @see #getControlKind()
	 * @generated
	 */
	void setControlKind(ControlKind value);

	/**
	 * Returns the value of the '<em><b>Label Width</b></em>' attribute.
	 * The default value is <code>"200"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Width</em>' attribute.
	 * @see #setLabelWidth(Integer)
	 * @see org.opeum.uim.UimPackage#getUimField_LabelWidth()
	 * @model default="200"
	 * @generated
	 */
	Integer getLabelWidth();

	/**
	 * Sets the value of the '{@link org.opeum.uim.UimField#getLabelWidth <em>Label Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Width</em>' attribute.
	 * @see #getLabelWidth()
	 * @generated
	 */
	void setLabelWidth(Integer value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.binding.FieldBinding#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(FieldBinding)
	 * @see org.opeum.uim.UimPackage#getUimField_Binding()
	 * @see org.opeum.uim.binding.FieldBinding#getField
	 * @model opposite="field" containment="true" required="true"
	 * @generated
	 */
	FieldBinding getBinding();

	/**
	 * Sets the value of the '{@link org.opeum.uim.UimField#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(FieldBinding value);

} // UimField
