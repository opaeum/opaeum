/**
 */
package org.opaeum.uim;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Interface Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.UserInterfaceRoot#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.UserInterfaceRoot#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.UserInterfaceRoot#getIgnoredElements <em>Ignored Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getUserInterfaceRoot()
 * @model
 * @generated
 */
public interface UserInterfaceRoot extends UserInteractionElement, LabeledElement {
	/**
	 * Returns the value of the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editability</em>' containment reference.
	 * @see #setEditability(RootUserInteractionConstraint)
	 * @see org.opaeum.uim.UimPackage#getUserInterfaceRoot_Editability()
	 * @model containment="true"
	 * @generated
	 */
	RootUserInteractionConstraint getEditability();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UserInterfaceRoot#getEditability <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editability</em>' containment reference.
	 * @see #getEditability()
	 * @generated
	 */
	void setEditability(RootUserInteractionConstraint value);

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' containment reference.
	 * @see #setVisibility(RootUserInteractionConstraint)
	 * @see org.opaeum.uim.UimPackage#getUserInterfaceRoot_Visibility()
	 * @model containment="true"
	 * @generated
	 */
	RootUserInteractionConstraint getVisibility();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UserInterfaceRoot#getVisibility <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' containment reference.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(RootUserInteractionConstraint value);

	/**
	 * Returns the value of the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.IgnoredElement}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.IgnoredElement#getUserInterfaceRoot <em>User Interface Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignored Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignored Elements</em>' containment reference list.
	 * @see org.opaeum.uim.UimPackage#getUserInterfaceRoot_IgnoredElements()
	 * @see org.opaeum.uim.IgnoredElement#getUserInterfaceRoot
	 * @model opposite="userInterfaceRoot" containment="true"
	 * @generated
	 */
	EList<IgnoredElement> getIgnoredElements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated NOT
	 */
	EList<? extends Page> getPages();

} // UserInterfaceRoot
