/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field UIMBinding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.FieldBinding#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getFieldBinding()
 * @model
 * @generated
 */
public interface FieldBinding extends UIMBinding {
	/**
	 * Returns the value of the '<em><b>Field</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.UIMField#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field</em>' container reference.
	 * @see #setField(UIMField)
	 * @see org.nakeduml.uim.UIMPackage#getFieldBinding_Field()
	 * @see org.nakeduml.uim.UIMField#getBinding
	 * @model opposite="binding" transient="false"
	 * @generated
	 */
	UIMField getField();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.FieldBinding#getField <em>Field</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field</em>' container reference.
	 * @see #getField()
	 * @generated
	 */
	void setField(UIMField value);

} // FieldBinding
