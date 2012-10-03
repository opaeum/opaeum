/**
 */
package org.opaeum.uim.constraint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Interaction Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.constraint.impl.UserInteractionConstraintImpl#isInheritFromParent <em>Inherit From Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserInteractionConstraintImpl extends RootUserInteractionConstraintImpl implements UserInteractionConstraint {
	/**
	 * The default value of the '{@link #isInheritFromParent() <em>Inherit From Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInheritFromParent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INHERIT_FROM_PARENT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isInheritFromParent() <em>Inherit From Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInheritFromParent()
	 * @generated
	 * @ordered
	 */
	protected boolean inheritFromParent = INHERIT_FROM_PARENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserInteractionConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintPackage.Literals.USER_INTERACTION_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInheritFromParent() {
		return inheritFromParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInheritFromParent(boolean newInheritFromParent) {
		boolean oldInheritFromParent = inheritFromParent;
		inheritFromParent = newInheritFromParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT, oldInheritFromParent, inheritFromParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT:
				return isInheritFromParent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT:
				setInheritFromParent((Boolean)newValue);
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
			case ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT:
				setInheritFromParent(INHERIT_FROM_PARENT_EDEFAULT);
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
			case ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT:
				return inheritFromParent != INHERIT_FROM_PARENT_EDEFAULT;
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
		result.append(" (inheritFromParent: ");
		result.append(inheritFromParent);
		result.append(')');
		return result.toString();
	}

} //UserInteractionConstraintImpl
