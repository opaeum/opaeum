/**
 */
package org.opaeum.uim.action;

import org.opaeum.uim.Labels;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Built In Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.BuiltInLink#getKind <em>Kind</em>}</li>
 *   <li>{@link org.opaeum.uim.action.BuiltInLink#getLabels <em>Labels</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getBuiltInLink()
 * @model
 * @generated
 */
public interface BuiltInLink extends AbstractLink {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The default value is <code>"null"</code>.
	 * The literals are from the enumeration {@link org.opaeum.uim.action.BuiltInLinkKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.opaeum.uim.action.BuiltInLinkKind
	 * @see #setKind(BuiltInLinkKind)
	 * @see org.opaeum.uim.action.ActionPackage#getBuiltInLink_Kind()
	 * @model default="null"
	 * @generated
	 */
	BuiltInLinkKind getKind();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.BuiltInLink#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.opaeum.uim.action.BuiltInLinkKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(BuiltInLinkKind value);

	/**
	 * Returns the value of the '<em><b>Labels</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Labels</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Labels</em>' containment reference.
	 * @see #setLabels(Labels)
	 * @see org.opaeum.uim.action.ActionPackage#getBuiltInLink_Labels()
	 * @model containment="true"
	 * @generated
	 */
	Labels getLabels();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.BuiltInLink#getLabels <em>Labels</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Labels</em>' containment reference.
	 * @see #getLabels()
	 * @generated
	 */
	void setLabels(Labels value);

} // BuiltInLink
