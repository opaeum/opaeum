/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Form Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.AbstractFormFolder#getParent <em>Parent</em>}</li>
 *   <li>{@link org.nakeduml.uim.AbstractFormFolder#getSecurityOnVisibility <em>Security On Visibility</em>}</li>
 *   <li>{@link org.nakeduml.uim.AbstractFormFolder#getSecurityOnEditability <em>Security On Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getAbstractFormFolder()
 * @model abstract="true"
 * @generated
 */
public interface AbstractFormFolder extends AbstractFolder {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.AbstractFolder#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(AbstractFolder)
	 * @see org.nakeduml.uim.UIMPackage#getAbstractFormFolder_Parent()
	 * @see org.nakeduml.uim.AbstractFolder#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	AbstractFolder getParent();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.AbstractFormFolder#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(AbstractFolder value);

	/**
	 * Returns the value of the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security On Visibility</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security On Visibility</em>' containment reference.
	 * @see #setSecurityOnVisibility(ChildSecurityConstraint)
	 * @see org.nakeduml.uim.UIMPackage#getAbstractFormFolder_SecurityOnVisibility()
	 * @model containment="true"
	 * @generated
	 */
	ChildSecurityConstraint getSecurityOnVisibility();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.AbstractFormFolder#getSecurityOnVisibility <em>Security On Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security On Visibility</em>' containment reference.
	 * @see #getSecurityOnVisibility()
	 * @generated
	 */
	void setSecurityOnVisibility(ChildSecurityConstraint value);

	/**
	 * Returns the value of the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security On Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security On Editability</em>' containment reference.
	 * @see #setSecurityOnEditability(ChildSecurityConstraint)
	 * @see org.nakeduml.uim.UIMPackage#getAbstractFormFolder_SecurityOnEditability()
	 * @model containment="true"
	 * @generated
	 */
	ChildSecurityConstraint getSecurityOnEditability();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.AbstractFormFolder#getSecurityOnEditability <em>Security On Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security On Editability</em>' containment reference.
	 * @see #getSecurityOnEditability()
	 * @generated
	 */
	void setSecurityOnEditability(ChildSecurityConstraint value);

} // AbstractFormFolder
