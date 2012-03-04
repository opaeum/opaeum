/**
 */
package org.opaeum.uim.constraint.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.constraint.*;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.RequiredState;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConstraintFactoryImpl extends EFactoryImpl implements ConstraintFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConstraintFactory init() {
		try {
			ConstraintFactory theConstraintFactory = (ConstraintFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/security/1.0"); 
			if (theConstraintFactory != null) {
				return theConstraintFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ConstraintFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT: return createEditableConstrainedObject();
			case ConstraintPackage.CONSTRAINED_OBJECT: return createConstrainedObject();
			case ConstraintPackage.REQUIRED_ROLE: return createRequiredRole();
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT: return createRootUserInteractionConstraint();
			case ConstraintPackage.USER_INTERACTION_CONSTRAINT: return createUserInteractionConstraint();
			case ConstraintPackage.REQUIRED_STATE: return createRequiredState();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditableConstrainedObject createEditableConstrainedObject() {
		EditableConstrainedObjectImpl editableConstrainedObject = new EditableConstrainedObjectImpl();
		return editableConstrainedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstrainedObject createConstrainedObject() {
		ConstrainedObjectImpl constrainedObject = new ConstrainedObjectImpl();
		return constrainedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequiredRole createRequiredRole() {
		RequiredRoleImpl requiredRole = new RequiredRoleImpl();
		return requiredRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint createRootUserInteractionConstraint() {
		RootUserInteractionConstraintImpl rootUserInteractionConstraint = new RootUserInteractionConstraintImpl();
		return rootUserInteractionConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionConstraint createUserInteractionConstraint() {
		UserInteractionConstraintImpl userInteractionConstraint = new UserInteractionConstraintImpl();
		return userInteractionConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequiredState createRequiredState() {
		RequiredStateImpl requiredState = new RequiredStateImpl();
		return requiredState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintPackage getConstraintPackage() {
		return (ConstraintPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ConstraintPackage getPackage() {
		return ConstraintPackage.eINSTANCE;
	}

} //ConstraintFactoryImpl
