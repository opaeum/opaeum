/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.IgnoredElement;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInterfaceRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ignored Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.IgnoredElementImpl#getUserInterfaceRoot <em>User Interface Root</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IgnoredElementImpl extends UmlReferenceImpl implements IgnoredElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IgnoredElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.IGNORED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInterfaceRoot getUserInterfaceRoot() {
		if (eContainerFeatureID() != UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT) return null;
		return (UserInterfaceRoot)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUserInterfaceRoot(UserInterfaceRoot newUserInterfaceRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUserInterfaceRoot, UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserInterfaceRoot(UserInterfaceRoot newUserInterfaceRoot) {
		if (newUserInterfaceRoot != eInternalContainer() || (eContainerFeatureID() != UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT && newUserInterfaceRoot != null)) {
			if (EcoreUtil.isAncestor(this, newUserInterfaceRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUserInterfaceRoot != null)
				msgs = ((InternalEObject)newUserInterfaceRoot).eInverseAdd(this, UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS, UserInterfaceRoot.class, msgs);
			msgs = basicSetUserInterfaceRoot(newUserInterfaceRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT, newUserInterfaceRoot, newUserInterfaceRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUserInterfaceRoot((UserInterfaceRoot)otherEnd, msgs);
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
			case UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT:
				return basicSetUserInterfaceRoot(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT:
				return eInternalContainer().eInverseRemove(this, UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS, UserInterfaceRoot.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT:
				return getUserInterfaceRoot();
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
			case UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT:
				setUserInterfaceRoot((UserInterfaceRoot)newValue);
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
			case UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT:
				setUserInterfaceRoot((UserInterfaceRoot)null);
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
			case UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT:
				return getUserInterfaceRoot() != null;
		}
		return super.eIsSet(featureID);
	}

} //IgnoredElementImpl
