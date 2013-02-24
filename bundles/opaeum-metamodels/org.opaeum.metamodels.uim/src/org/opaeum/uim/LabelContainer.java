/**
 */
package org.opaeum.uim;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.LabelContainer#getLabelOverride <em>Label Override</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getLabelContainer()
 * @model
 * @generated
 */
public interface LabelContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Override</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Override</em>' containment reference.
	 * @see #setLabelOverride(Labels)
	 * @see org.opaeum.uim.UimPackage#getLabelContainer_LabelOverride()
	 * @model containment="true"
	 * @generated
	 */
	Labels getLabelOverride();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.LabelContainer#getLabelOverride <em>Label Override</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Override</em>' containment reference.
	 * @see #getLabelOverride()
	 * @generated
	 */
	void setLabelOverride(Labels value);

} // LabelContainer
