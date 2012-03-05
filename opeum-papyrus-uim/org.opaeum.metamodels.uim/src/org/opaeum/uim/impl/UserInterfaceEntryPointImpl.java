/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Interface Entry Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceEntryPointImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UserInterfaceEntryPointImpl#getVisibility <em>Visibility</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserInterfaceEntryPointImpl extends UserInteractionElementImpl implements UserInterfaceEntryPoint {
	/**
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected RootUserInteractionConstraint editability;
	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected RootUserInteractionConstraint visibility;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserInterfaceEntryPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.USER_INTERFACE_ENTRY_POINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(RootUserInteractionConstraint newEditability, NotificationChain msgs) {
		RootUserInteractionConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(RootUserInteractionConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(RootUserInteractionConstraint newVisibility, NotificationChain msgs) {
		RootUserInteractionConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(RootUserInteractionConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY:
				return basicSetEditability(null, msgs);
			case UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY:
				return basicSetVisibility(null, msgs);
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
			case UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY:
				return getEditability();
			case UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY:
				return getVisibility();
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
			case UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY:
				setEditability((RootUserInteractionConstraint)newValue);
				return;
			case UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY:
				setVisibility((RootUserInteractionConstraint)newValue);
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
			case UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY:
				setEditability((RootUserInteractionConstraint)null);
				return;
			case UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY:
				setVisibility((RootUserInteractionConstraint)null);
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
			case UimPackage.USER_INTERFACE_ENTRY_POINT__EDITABILITY:
				return editability != null;
			case UimPackage.USER_INTERFACE_ENTRY_POINT__VISIBILITY:
				return visibility != null;
		}
		return super.eIsSet(featureID);
	}

} //UserInterfaceEntryPointImpl
