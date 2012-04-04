/**
 */
package org.opaeum.uim;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Interaction Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.UserInteractionElement#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.UserInteractionElement#isUnderUserControl <em>Under User Control</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getUserInteractionElement()
 * @model abstract="true"
 * @generated
 */
public interface UserInteractionElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.opaeum.uim.UimPackage#getUserInteractionElement_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UserInteractionElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Under User Control</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Under User Control</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Under User Control</em>' attribute.
	 * @see #setUnderUserControl(boolean)
	 * @see org.opaeum.uim.UimPackage#getUserInteractionElement_UnderUserControl()
	 * @model default="false"
	 * @generated
	 */
	boolean isUnderUserControl();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UserInteractionElement#isUnderUserControl <em>Under User Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Under User Control</em>' attribute.
	 * @see #isUnderUserControl()
	 * @generated
	 */
	void setUnderUserControl(boolean value);

} // UserInteractionElement
