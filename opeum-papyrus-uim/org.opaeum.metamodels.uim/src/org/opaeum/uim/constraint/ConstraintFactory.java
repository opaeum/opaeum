/**
 */
package org.opaeum.uim.constraint;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.constraint.ConstraintPackage
 * @generated
 */
public interface ConstraintFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConstraintFactory eINSTANCE = org.opaeum.uim.constraint.impl.ConstraintFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Editable Constrained Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Editable Constrained Object</em>'.
	 * @generated
	 */
	EditableConstrainedObject createEditableConstrainedObject();

	/**
	 * Returns a new object of class '<em>Constrained Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constrained Object</em>'.
	 * @generated
	 */
	ConstrainedObject createConstrainedObject();

	/**
	 * Returns a new object of class '<em>Required Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Required Role</em>'.
	 * @generated
	 */
	RequiredRole createRequiredRole();

	/**
	 * Returns a new object of class '<em>Root User Interaction Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Root User Interaction Constraint</em>'.
	 * @generated
	 */
	RootUserInteractionConstraint createRootUserInteractionConstraint();

	/**
	 * Returns a new object of class '<em>User Interaction Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Interaction Constraint</em>'.
	 * @generated
	 */
	UserInteractionConstraint createUserInteractionConstraint();

	/**
	 * Returns a new object of class '<em>Required State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Required State</em>'.
	 * @generated
	 */
	RequiredState createRequiredState();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ConstraintPackage getConstraintPackage();

} //ConstraintFactory
