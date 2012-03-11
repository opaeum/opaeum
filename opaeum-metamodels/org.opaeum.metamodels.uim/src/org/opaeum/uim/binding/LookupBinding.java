/**
 */
package org.opaeum.uim.binding;

import org.opaeum.uim.control.UimLookup;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lookup Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.binding.LookupBinding#getLookup <em>Lookup</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.binding.BindingPackage#getLookupBinding()
 * @model
 * @generated
 */
public interface LookupBinding extends UimBinding {
	/**
	 * Returns the value of the '<em><b>Lookup</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.control.UimLookup#getLookupSource <em>Lookup Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lookup</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lookup</em>' container reference.
	 * @see #setLookup(UimLookup)
	 * @see org.opaeum.uim.binding.BindingPackage#getLookupBinding_Lookup()
	 * @see org.opaeum.uim.control.UimLookup#getLookupSource
	 * @model opposite="lookupSource" transient="false"
	 * @generated
	 */
	UimLookup getLookup();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.binding.LookupBinding#getLookup <em>Lookup</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lookup</em>' container reference.
	 * @see #getLookup()
	 * @generated
	 */
	void setLookup(UimLookup value);

} // LookupBinding
