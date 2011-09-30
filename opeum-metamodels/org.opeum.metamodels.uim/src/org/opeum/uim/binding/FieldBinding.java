/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.binding;

import org.opeum.uim.UimField;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.binding.FieldBinding#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.binding.BindingPackage#getFieldBinding()
 * @model
 * @generated
 */
public interface FieldBinding extends UimBinding {
	/**
	 * Returns the value of the '<em><b>Field</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.UimField#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field</em>' container reference.
	 * @see #setField(UimField)
	 * @see org.opeum.uim.binding.BindingPackage#getFieldBinding_Field()
	 * @see org.opeum.uim.UimField#getBinding
	 * @model opposite="binding" transient="false"
	 * @generated
	 */
	UimField getField();

	/**
	 * Sets the value of the '{@link org.opeum.uim.binding.FieldBinding#getField <em>Field</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field</em>' container reference.
	 * @see #getField()
	 * @generated
	 */
	void setField(UimField value);

} // FieldBinding
