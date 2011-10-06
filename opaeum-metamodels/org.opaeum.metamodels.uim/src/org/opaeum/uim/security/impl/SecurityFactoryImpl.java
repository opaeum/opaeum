/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.security.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.security.EditableSecureObject;
import org.opaeum.uim.security.RequiredRole;
import org.opaeum.uim.security.SecureObject;
import org.opaeum.uim.security.SecurityConstraint;
import org.opaeum.uim.security.SecurityFactory;
import org.opaeum.uim.security.SecurityPackage;
import org.opaeum.uim.security.WorkspaceSecurityConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SecurityFactoryImpl extends EFactoryImpl implements SecurityFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SecurityFactory init() {
		try {
			SecurityFactory theSecurityFactory = (SecurityFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/security/1.0"); 
			if (theSecurityFactory != null) {
				return theSecurityFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SecurityFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityFactoryImpl() {
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
			case SecurityPackage.EDITABLE_SECURE_OBJECT: return createEditableSecureObject();
			case SecurityPackage.SECURE_OBJECT: return createSecureObject();
			case SecurityPackage.REQUIRED_ROLE: return createRequiredRole();
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT: return createWorkspaceSecurityConstraint();
			case SecurityPackage.SECURITY_CONSTRAINT: return createSecurityConstraint();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditableSecureObject createEditableSecureObject() {
		EditableSecureObjectImpl editableSecureObject = new EditableSecureObjectImpl();
		return editableSecureObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecureObject createSecureObject() {
		SecureObjectImpl secureObject = new SecureObjectImpl();
		return secureObject;
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
	public WorkspaceSecurityConstraint createWorkspaceSecurityConstraint() {
		WorkspaceSecurityConstraintImpl workspaceSecurityConstraint = new WorkspaceSecurityConstraintImpl();
		return workspaceSecurityConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityConstraint createSecurityConstraint() {
		SecurityConstraintImpl securityConstraint = new SecurityConstraintImpl();
		return securityConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityPackage getSecurityPackage() {
		return (SecurityPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SecurityPackage getPackage() {
		return SecurityPackage.eINSTANCE;
	}

} //SecurityFactoryImpl
