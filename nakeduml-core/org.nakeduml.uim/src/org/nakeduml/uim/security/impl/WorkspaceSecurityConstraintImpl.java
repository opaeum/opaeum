/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.security.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nakeduml.uim.security.RequiredRole;
import org.nakeduml.uim.security.SecurityPackage;
import org.nakeduml.uim.security.WorkspaceSecurityConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Workspace Security Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.security.impl.WorkspaceSecurityConstraintImpl#isRequiresGroupOwnership <em>Requires Group Ownership</em>}</li>
 *   <li>{@link org.nakeduml.uim.security.impl.WorkspaceSecurityConstraintImpl#isRequiresOwnership <em>Requires Ownership</em>}</li>
 *   <li>{@link org.nakeduml.uim.security.impl.WorkspaceSecurityConstraintImpl#getRequiredRoles <em>Required Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkspaceSecurityConstraintImpl extends EObjectImpl implements WorkspaceSecurityConstraint {
	/**
	 * The default value of the '{@link #isRequiresGroupOwnership() <em>Requires Group Ownership</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiresGroupOwnership()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRES_GROUP_OWNERSHIP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequiresGroupOwnership() <em>Requires Group Ownership</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiresGroupOwnership()
	 * @generated
	 * @ordered
	 */
	protected boolean requiresGroupOwnership = REQUIRES_GROUP_OWNERSHIP_EDEFAULT;

	/**
	 * The default value of the '{@link #isRequiresOwnership() <em>Requires Ownership</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiresOwnership()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRES_OWNERSHIP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequiresOwnership() <em>Requires Ownership</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiresOwnership()
	 * @generated
	 * @ordered
	 */
	protected boolean requiresOwnership = REQUIRES_OWNERSHIP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequiredRoles() <em>Required Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredRole> requiredRoles;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkspaceSecurityConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SecurityPackage.Literals.WORKSPACE_SECURITY_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRequiresGroupOwnership() {
		return requiresGroupOwnership;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequiresGroupOwnership(boolean newRequiresGroupOwnership) {
		boolean oldRequiresGroupOwnership = requiresGroupOwnership;
		requiresGroupOwnership = newRequiresGroupOwnership;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP, oldRequiresGroupOwnership, requiresGroupOwnership));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRequiresOwnership() {
		return requiresOwnership;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequiresOwnership(boolean newRequiresOwnership) {
		boolean oldRequiresOwnership = requiresOwnership;
		requiresOwnership = newRequiresOwnership;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP, oldRequiresOwnership, requiresOwnership));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiredRole> getRequiredRoles() {
		if (requiredRoles == null) {
			requiredRoles = new EObjectContainmentEList<RequiredRole>(RequiredRole.class, this, SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES);
		}
		return requiredRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				return ((InternalEList<?>)getRequiredRoles()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				return isRequiresGroupOwnership();
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP:
				return isRequiresOwnership();
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				return getRequiredRoles();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				setRequiresGroupOwnership((Boolean)newValue);
				return;
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP:
				setRequiresOwnership((Boolean)newValue);
				return;
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				getRequiredRoles().clear();
				getRequiredRoles().addAll((Collection<? extends RequiredRole>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				setRequiresGroupOwnership(REQUIRES_GROUP_OWNERSHIP_EDEFAULT);
				return;
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP:
				setRequiresOwnership(REQUIRES_OWNERSHIP_EDEFAULT);
				return;
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				getRequiredRoles().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				return requiresGroupOwnership != REQUIRES_GROUP_OWNERSHIP_EDEFAULT;
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP:
				return requiresOwnership != REQUIRES_OWNERSHIP_EDEFAULT;
			case SecurityPackage.WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				return requiredRoles != null && !requiredRoles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (requiresGroupOwnership: ");
		result.append(requiresGroupOwnership);
		result.append(", requiresOwnership: ");
		result.append(requiresOwnership);
		result.append(')');
		return result.toString();
	}

} //WorkspaceSecurityConstraintImpl
