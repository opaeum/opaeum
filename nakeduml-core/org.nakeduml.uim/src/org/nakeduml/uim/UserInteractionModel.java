/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.uml2.uml.Model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UserInteractionModel#getUmlModel <em>Uml Model</em>}</li>
 *   <li>{@link org.nakeduml.uim.UserInteractionModel#getSecurityOnVisibility <em>Security On Visibility</em>}</li>
 *   <li>{@link org.nakeduml.uim.UserInteractionModel#getSecuirytOnEditability <em>Secuiryt On Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getUserInteractionModel()
 * @model
 * @generated
 */
public interface UserInteractionModel extends AbstractFolder, UmlReference {
	/**
	 * Returns the value of the '<em><b>Uml Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uml Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uml Model</em>' reference.
	 * @see #setUmlModel(Model)
	 * @see org.nakeduml.uim.UIMPackage#getUserInteractionModel_UmlModel()
	 * @model
	 * @generated
	 */
	Model getUmlModel();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UserInteractionModel#getUmlModel <em>Uml Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uml Model</em>' reference.
	 * @see #getUmlModel()
	 * @generated
	 */
	void setUmlModel(Model value);

	/**
	 * Returns the value of the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security On Visibility</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security On Visibility</em>' containment reference.
	 * @see #setSecurityOnVisibility(ModelSecurityConstraint)
	 * @see org.nakeduml.uim.UIMPackage#getUserInteractionModel_SecurityOnVisibility()
	 * @model containment="true"
	 * @generated
	 */
	ModelSecurityConstraint getSecurityOnVisibility();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UserInteractionModel#getSecurityOnVisibility <em>Security On Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security On Visibility</em>' containment reference.
	 * @see #getSecurityOnVisibility()
	 * @generated
	 */
	void setSecurityOnVisibility(ModelSecurityConstraint value);

	/**
	 * Returns the value of the '<em><b>Secuiryt On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secuiryt On Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secuiryt On Editability</em>' containment reference.
	 * @see #setSecuirytOnEditability(ModelSecurityConstraint)
	 * @see org.nakeduml.uim.UIMPackage#getUserInteractionModel_SecuirytOnEditability()
	 * @model containment="true"
	 * @generated
	 */
	ModelSecurityConstraint getSecuirytOnEditability();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UserInteractionModel#getSecuirytOnEditability <em>Secuiryt On Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secuiryt On Editability</em>' containment reference.
	 * @see #getSecuirytOnEditability()
	 * @generated
	 */
	void setSecuirytOnEditability(ModelSecurityConstraint value);

} // UserInteractionModel
