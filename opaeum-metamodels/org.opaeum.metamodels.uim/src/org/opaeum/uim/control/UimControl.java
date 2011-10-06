/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.control;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimField;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Control</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.control.UimControl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.opaeum.uim.control.UimControl#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.control.ControlPackage#getUimControl()
 * @model
 * @generated
 */
public interface UimControl extends EObject {
	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(String)
	 * @see org.opaeum.uim.control.ControlPackage#getUimControl_Width()
	 * @model
	 * @generated
	 */
	String getWidth();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.control.UimControl#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(String value);

	/**
	 * Returns the value of the '<em><b>Field</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.UimField#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field</em>' container reference.
	 * @see #setField(UimField)
	 * @see org.opaeum.uim.control.ControlPackage#getUimControl_Field()
	 * @see org.opaeum.uim.UimField#getControl
	 * @model opposite="control" transient="false"
	 * @generated
	 */
	UimField getField();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.control.UimControl#getField <em>Field</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field</em>' container reference.
	 * @see #getField()
	 * @generated
	 */
	void setField(UimField value);

} // UimControl
