/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.control;

import org.opaeum.uim.binding.LookupBinding;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uim Lookup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.control.UimLookup#getLookupSource <em>Lookup Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.control.ControlPackage#getUimLookup()
 * @model
 * @generated
 */
public interface UimLookup extends UimControl {
	/**
	 * Returns the value of the '<em><b>Lookup Source</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.binding.LookupBinding#getLookup <em>Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lookup Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lookup Source</em>' containment reference.
	 * @see #setLookupSource(LookupBinding)
	 * @see org.opaeum.uim.control.ControlPackage#getUimLookup_LookupSource()
	 * @see org.opaeum.uim.binding.LookupBinding#getLookup
	 * @model opposite="lookup" containment="true"
	 * @generated
	 */
	LookupBinding getLookupSource();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.control.UimLookup#getLookupSource <em>Lookup Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lookup Source</em>' containment reference.
	 * @see #getLookupSource()
	 * @generated
	 */
	void setLookupSource(LookupBinding value);

} // UimLookup
