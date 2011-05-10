/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Interface;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Security Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.ModelSecurityConstraint#getRequiredRoles <em>Required Roles</em>}</li>
 *   <li>{@link org.nakeduml.uim.ModelSecurityConstraint#isRequiresGroupOwnership <em>Requires Group Ownership</em>}</li>
 *   <li>{@link org.nakeduml.uim.ModelSecurityConstraint#isRequiresOwnership <em>Requires Ownership</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getModelSecurityConstraint()
 * @model
 * @generated
 */
public interface ModelSecurityConstraint extends EObject {
	/**
	 * Returns the value of the '<em><b>Required Roles</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Roles</em>' reference list.
	 * @see org.nakeduml.uim.UIMPackage#getModelSecurityConstraint_RequiredRoles()
	 * @model
	 * @generated
	 */
	EList<Interface> getRequiredRoles();

	/**
	 * Returns the value of the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requires Group Ownership</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires Group Ownership</em>' attribute.
	 * @see #setRequiresGroupOwnership(boolean)
	 * @see org.nakeduml.uim.UIMPackage#getModelSecurityConstraint_RequiresGroupOwnership()
	 * @model
	 * @generated
	 */
	boolean isRequiresGroupOwnership();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.ModelSecurityConstraint#isRequiresGroupOwnership <em>Requires Group Ownership</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requires Group Ownership</em>' attribute.
	 * @see #isRequiresGroupOwnership()
	 * @generated
	 */
	void setRequiresGroupOwnership(boolean value);

	/**
	 * Returns the value of the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requires Ownership</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires Ownership</em>' attribute.
	 * @see #setRequiresOwnership(boolean)
	 * @see org.nakeduml.uim.UIMPackage#getModelSecurityConstraint_RequiresOwnership()
	 * @model
	 * @generated
	 */
	boolean isRequiresOwnership();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.ModelSecurityConstraint#isRequiresOwnership <em>Requires Ownership</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requires Ownership</em>' attribute.
	 * @see #isRequiresOwnership()
	 * @generated
	 */
	void setRequiresOwnership(boolean value);

} // ModelSecurityConstraint
