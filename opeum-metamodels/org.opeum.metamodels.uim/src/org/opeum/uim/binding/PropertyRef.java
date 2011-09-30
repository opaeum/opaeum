/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.binding;

import org.opeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.binding.PropertyRef#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.opeum.uim.binding.PropertyRef#getPrevious <em>Previous</em>}</li>
 *   <li>{@link org.opeum.uim.binding.PropertyRef#getNext <em>Next</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.binding.BindingPackage#getPropertyRef()
 * @model
 * @generated
 */
public interface PropertyRef extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Binding</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.binding.UimBinding#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' container reference.
	 * @see #setBinding(UimBinding)
	 * @see org.opeum.uim.binding.BindingPackage#getPropertyRef_Binding()
	 * @see org.opeum.uim.binding.UimBinding#getNext
	 * @model opposite="next" transient="false"
	 * @generated
	 */
	UimBinding getBinding();

	/**
	 * Sets the value of the '{@link org.opeum.uim.binding.PropertyRef#getBinding <em>Binding</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' container reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(UimBinding value);

	/**
	 * Returns the value of the '<em><b>Previous</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.binding.PropertyRef#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous</em>' container reference.
	 * @see #setPrevious(PropertyRef)
	 * @see org.opeum.uim.binding.BindingPackage#getPropertyRef_Previous()
	 * @see org.opeum.uim.binding.PropertyRef#getNext
	 * @model opposite="next" transient="false"
	 * @generated
	 */
	PropertyRef getPrevious();

	/**
	 * Sets the value of the '{@link org.opeum.uim.binding.PropertyRef#getPrevious <em>Previous</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Previous</em>' container reference.
	 * @see #getPrevious()
	 * @generated
	 */
	void setPrevious(PropertyRef value);

	/**
	 * Returns the value of the '<em><b>Next</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.binding.PropertyRef#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' containment reference.
	 * @see #setNext(PropertyRef)
	 * @see org.opeum.uim.binding.BindingPackage#getPropertyRef_Next()
	 * @see org.opeum.uim.binding.PropertyRef#getPrevious
	 * @model opposite="previous" containment="true"
	 * @generated
	 */
	PropertyRef getNext();

	/**
	 * Sets the value of the '{@link org.opeum.uim.binding.PropertyRef#getNext <em>Next</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next</em>' containment reference.
	 * @see #getNext()
	 * @generated
	 */
	void setNext(PropertyRef value);

} // PropertyRef
