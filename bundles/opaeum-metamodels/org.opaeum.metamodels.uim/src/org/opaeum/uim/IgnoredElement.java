/**
 */
package org.opaeum.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ignored Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.IgnoredElement#getUserInterfaceRoot <em>User Interface Root</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getIgnoredElement()
 * @model
 * @generated
 */
public interface IgnoredElement extends UmlReference {
	/**
	 * Returns the value of the '<em><b>User Interface Root</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.UserInterfaceRoot#getIgnoredElements <em>Ignored Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Interface Root</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Interface Root</em>' container reference.
	 * @see #setUserInterfaceRoot(UserInterfaceRoot)
	 * @see org.opaeum.uim.UimPackage#getIgnoredElement_UserInterfaceRoot()
	 * @see org.opaeum.uim.UserInterfaceRoot#getIgnoredElements
	 * @model opposite="ignoredElements" transient="false"
	 * @generated
	 */
	UserInterfaceRoot getUserInterfaceRoot();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.IgnoredElement#getUserInterfaceRoot <em>User Interface Root</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Interface Root</em>' container reference.
	 * @see #getUserInterfaceRoot()
	 * @generated
	 */
	void setUserInterfaceRoot(UserInterfaceRoot value);

} // IgnoredElement
