/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;

import java.util.Collection;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.uml2.uml.Interface;
import org.nakeduml.uim.ModelSecurityConstraint;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Security Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.ModelSecurityConstraintImpl#getRequiredRoles <em>Required Roles</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.ModelSecurityConstraintImpl#isRequiresGroupOwnership <em>Requires Group Ownership</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.ModelSecurityConstraintImpl#isRequiresOwnership <em>Requires Ownership</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelSecurityConstraintImpl extends EObjectImpl implements ModelSecurityConstraint {
	/**
	 * The cached value of the '{@link #getRequiredRoles() <em>Required Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> requiredRoles;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelSecurityConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.MODEL_SECURITY_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface> getRequiredRoles() {
		if (requiredRoles == null) {
			requiredRoles = new EObjectResolvingEList<Interface>(Interface.class, this, UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES);
		}
		return requiredRoles;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP, oldRequiresGroupOwnership, requiresGroupOwnership));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP, oldRequiresOwnership, requiresOwnership));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				return getRequiredRoles();
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				return isRequiresGroupOwnership();
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP:
				return isRequiresOwnership();
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
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				getRequiredRoles().clear();
				getRequiredRoles().addAll((Collection<? extends Interface>)newValue);
				return;
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				setRequiresGroupOwnership((Boolean)newValue);
				return;
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP:
				setRequiresOwnership((Boolean)newValue);
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
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				getRequiredRoles().clear();
				return;
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				setRequiresGroupOwnership(REQUIRES_GROUP_OWNERSHIP_EDEFAULT);
				return;
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP:
				setRequiresOwnership(REQUIRES_OWNERSHIP_EDEFAULT);
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
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES:
				return requiredRoles != null && !requiredRoles.isEmpty();
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				return requiresGroupOwnership != REQUIRES_GROUP_OWNERSHIP_EDEFAULT;
			case UIMPackage.MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP:
				return requiresOwnership != REQUIRES_OWNERSHIP_EDEFAULT;
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

} //ModelSecurityConstraintImpl
