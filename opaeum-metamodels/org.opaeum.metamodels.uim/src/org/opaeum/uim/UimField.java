/**
 */
package org.opaeum.uim;

import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.panel.Outlayable;

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
 *   <li>{@link org.opaeum.uim.UimField#getControl <em>Control</em>}</li>
 *   <li>{@link org.opaeum.uim.UimField#getControlKind <em>Control Kind</em>}</li>
 *   <li>{@link org.opaeum.uim.UimField#getMinimumLabelWidth <em>Minimum Label Width</em>}</li>
 *   <li>{@link org.opaeum.uim.UimField#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.opaeum.uim.UimField#getOrientation <em>Orientation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getUimField()
 * @model
 * @generated
 */
public interface UimField extends EditableConstrainedObject, UimComponent, Outlayable {
	/**
	 * Returns the value of the '<em><b>Control</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.control.UimControl#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control</em>' containment reference.
	 * @see #setControl(UimControl)
	 * @see org.opaeum.uim.UimPackage#getUimField_Control()
	 * @see org.opaeum.uim.control.UimControl#getField
	 * @model opposite="field" containment="true" required="true"
	 * @generated
	 */
	UimControl getControl();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UimField#getControl <em>Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control</em>' containment reference.
	 * @see #getControl()
	 * @generated
	 */
	void setControl(UimControl value);

	/**
	 * Returns the value of the '<em><b>Control Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.opaeum.uim.control.ControlKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Kind</em>' attribute.
	 * @see org.opaeum.uim.control.ControlKind
	 * @see #setControlKind(ControlKind)
	 * @see org.opaeum.uim.UimPackage#getUimField_ControlKind()
	 * @model
	 * @generated
	 */
	ControlKind getControlKind();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UimField#getControlKind <em>Control Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Kind</em>' attribute.
	 * @see org.opaeum.uim.control.ControlKind
	 * @see #getControlKind()
	 * @generated
	 */
	void setControlKind(ControlKind value);

	/**
	 * Returns the value of the '<em><b>Minimum Label Width</b></em>' attribute.
	 * The default value is <code>"200"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minimum Label Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minimum Label Width</em>' attribute.
	 * @see #setMinimumLabelWidth(Integer)
	 * @see org.opaeum.uim.UimPackage#getUimField_MinimumLabelWidth()
	 * @model default="200"
	 * @generated
	 */
	Integer getMinimumLabelWidth();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UimField#getMinimumLabelWidth <em>Minimum Label Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minimum Label Width</em>' attribute.
	 * @see #getMinimumLabelWidth()
	 * @generated
	 */
	void setMinimumLabelWidth(Integer value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.binding.FieldBinding#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(FieldBinding)
	 * @see org.opaeum.uim.UimPackage#getUimField_Binding()
	 * @see org.opaeum.uim.binding.FieldBinding#getField
	 * @model opposite="field" containment="true" required="true"
	 * @generated
	 */
	FieldBinding getBinding();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UimField#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(FieldBinding value);

	/**
	 * Returns the value of the '<em><b>Orientation</b></em>' attribute.
	 * The literals are from the enumeration {@link org.opaeum.uim.Orientation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orientation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation</em>' attribute.
	 * @see org.opaeum.uim.Orientation
	 * @see #setOrientation(Orientation)
	 * @see org.opaeum.uim.UimPackage#getUimField_Orientation()
	 * @model
	 * @generated
	 */
	Orientation getOrientation();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UimField#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation</em>' attribute.
	 * @see org.opaeum.uim.Orientation
	 * @see #getOrientation()
	 * @generated
	 */
	void setOrientation(Orientation value);

} // UimField
