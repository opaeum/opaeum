/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.binding;

import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.binding.UimBinding#getNext <em>Next</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.binding.BindingPackage#getUimBinding()
 * @model abstract="true"
 * @generated
 */
public interface UimBinding extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Next</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.binding.PropertyRef#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' containment reference.
	 * @see #setNext(PropertyRef)
	 * @see org.opaeum.uim.binding.BindingPackage#getUimBinding_Next()
	 * @see org.opaeum.uim.binding.PropertyRef#getBinding
	 * @model opposite="binding" containment="true"
	 * @generated
	 */
	PropertyRef getNext();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.binding.UimBinding#getNext <em>Next</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next</em>' containment reference.
	 * @see #getNext()
	 * @generated
	 */
	void setNext(PropertyRef value);

} // UimBinding
