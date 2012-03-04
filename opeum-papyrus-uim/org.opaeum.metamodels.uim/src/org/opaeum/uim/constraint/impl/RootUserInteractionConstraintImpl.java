/**
 */
package org.opaeum.uim.constraint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.RequiredState;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root User Interaction Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl#isRequiresGroupOwnership <em>Requires Group Ownership</em>}</li>
 *   <li>{@link org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl#isRequiresOwnership <em>Requires Ownership</em>}</li>
 *   <li>{@link org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl#getRequiredRoles <em>Required Roles</em>}</li>
 *   <li>{@link org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl#getOpenToPublic <em>Open To Public</em>}</li>
 *   <li>{@link org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl#getRequiredStates <em>Required States</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RootUserInteractionConstraintImpl extends EObjectImpl implements RootUserInteractionConstraint {
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
	 * The default value of the '{@link #getOpenToPublic() <em>Open To Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpenToPublic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean OPEN_TO_PUBLIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOpenToPublic() <em>Open To Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpenToPublic()
	 * @generated
	 * @ordered
	 */
	protected Boolean openToPublic = OPEN_TO_PUBLIC_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequiredStates() <em>Required States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredStates()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredState> requiredStates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootUserInteractionConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintPackage.Literals.ROOT_USER_INTERACTION_CONSTRAINT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP, oldRequiresGroupOwnership, requiresGroupOwnership));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP, oldRequiresOwnership, requiresOwnership));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiredRole> getRequiredRoles() {
		if (requiredRoles == null) {
			requiredRoles = new EObjectContainmentWithInverseEList<RequiredRole>(RequiredRole.class, this, ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES, ConstraintPackage.REQUIRED_ROLE__CONSTRAINT);
		}
		return requiredRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getOpenToPublic() {
		return openToPublic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpenToPublic(Boolean newOpenToPublic) {
		Boolean oldOpenToPublic = openToPublic;
		openToPublic = newOpenToPublic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC, oldOpenToPublic, openToPublic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiredState> getRequiredStates() {
		if (requiredStates == null) {
			requiredStates = new EObjectContainmentWithInverseEList<RequiredState>(RequiredState.class, this, ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES, ConstraintPackage.REQUIRED_STATE__CONSTRAINT);
		}
		return requiredStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequiredRoles()).basicAdd(otherEnd, msgs);
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequiredStates()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES:
				return ((InternalEList<?>)getRequiredRoles()).basicRemove(otherEnd, msgs);
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES:
				return ((InternalEList<?>)getRequiredStates()).basicRemove(otherEnd, msgs);
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
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				return isRequiresGroupOwnership();
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP:
				return isRequiresOwnership();
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES:
				return getRequiredRoles();
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC:
				return getOpenToPublic();
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES:
				return getRequiredStates();
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
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				setRequiresGroupOwnership((Boolean)newValue);
				return;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP:
				setRequiresOwnership((Boolean)newValue);
				return;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES:
				getRequiredRoles().clear();
				getRequiredRoles().addAll((Collection<? extends RequiredRole>)newValue);
				return;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC:
				setOpenToPublic((Boolean)newValue);
				return;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES:
				getRequiredStates().clear();
				getRequiredStates().addAll((Collection<? extends RequiredState>)newValue);
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
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				setRequiresGroupOwnership(REQUIRES_GROUP_OWNERSHIP_EDEFAULT);
				return;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP:
				setRequiresOwnership(REQUIRES_OWNERSHIP_EDEFAULT);
				return;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES:
				getRequiredRoles().clear();
				return;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC:
				setOpenToPublic(OPEN_TO_PUBLIC_EDEFAULT);
				return;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES:
				getRequiredStates().clear();
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
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP:
				return requiresGroupOwnership != REQUIRES_GROUP_OWNERSHIP_EDEFAULT;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP:
				return requiresOwnership != REQUIRES_OWNERSHIP_EDEFAULT;
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES:
				return requiredRoles != null && !requiredRoles.isEmpty();
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC:
				return OPEN_TO_PUBLIC_EDEFAULT == null ? openToPublic != null : !OPEN_TO_PUBLIC_EDEFAULT.equals(openToPublic);
			case ConstraintPackage.ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES:
				return requiredStates != null && !requiredStates.isEmpty();
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
		result.append(", openToPublic: ");
		result.append(openToPublic);
		result.append(')');
		return result.toString();
	}

} //RootUserInteractionConstraintImpl
