/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


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
 *   <li>{@link org.nakeduml.uim.UIMField#getControl <em>Control</em>}</li>
 *   <li>{@link org.nakeduml.uim.UIMField#getControlKind <em>Control Kind</em>}</li>
 *   <li>{@link org.nakeduml.uim.UIMField#getLabelWidth <em>Label Width</em>}</li>
 *   <li>{@link org.nakeduml.uim.UIMField#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.nakeduml.uim.UIMField#getSecurityOnEditability <em>Security On Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getUIMField()
 * @model
 * @generated
 */
public interface UIMField extends UIMComponent {
	/**
	 * Returns the value of the '<em><b>Control</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.UIMControl#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control</em>' containment reference.
	 * @see #setControl(UIMControl)
	 * @see org.nakeduml.uim.UIMPackage#getUIMField_Control()
	 * @see org.nakeduml.uim.UIMControl#getField
	 * @model opposite="field" containment="true" required="true"
	 * @generated
	 */
	UIMControl getControl();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMField#getControl <em>Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control</em>' containment reference.
	 * @see #getControl()
	 * @generated
	 */
	void setControl(UIMControl value);

	/**
	 * Returns the value of the '<em><b>Control Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.nakeduml.uim.ControlKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Kind</em>' attribute.
	 * @see org.nakeduml.uim.ControlKind
	 * @see #setControlKind(ControlKind)
	 * @see org.nakeduml.uim.UIMPackage#getUIMField_ControlKind()
	 * @model
	 * @generated
	 */
	ControlKind getControlKind();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMField#getControlKind <em>Control Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Kind</em>' attribute.
	 * @see org.nakeduml.uim.ControlKind
	 * @see #getControlKind()
	 * @generated
	 */
	void setControlKind(ControlKind value);

	/**
	 * Returns the value of the '<em><b>Label Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Width</em>' attribute.
	 * @see #setLabelWidth(int)
	 * @see org.nakeduml.uim.UIMPackage#getUIMField_LabelWidth()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getLabelWidth();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMField#getLabelWidth <em>Label Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Width</em>' attribute.
	 * @see #getLabelWidth()
	 * @generated
	 */
	void setLabelWidth(int value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.FieldBinding#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>UIMBinding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' containment reference.
	 * @see #setBinding(FieldBinding)
	 * @see org.nakeduml.uim.UIMPackage#getUIMField_Binding()
	 * @see org.nakeduml.uim.FieldBinding#getField
	 * @model opposite="field" containment="true" required="true"
	 * @generated
	 */
	FieldBinding getBinding();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMField#getBinding <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' containment reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(FieldBinding value);

	/**
	 * Returns the value of the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security On Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security On Editability</em>' containment reference.
	 * @see #setSecurityOnEditability(ChildSecurityConstraint)
	 * @see org.nakeduml.uim.UIMPackage#getUIMField_SecurityOnEditability()
	 * @model containment="true"
	 * @generated
	 */
	ChildSecurityConstraint getSecurityOnEditability();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMField#getSecurityOnEditability <em>Security On Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security On Editability</em>' containment reference.
	 * @see #getSecurityOnEditability()
	 * @generated
	 */
	void setSecurityOnEditability(ChildSecurityConstraint value);

} // UIMField
