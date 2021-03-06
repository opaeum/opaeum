/**
 */
package org.opaeum.uim.action.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.InvocationButton;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Invocation Button</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.impl.InvocationButtonImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opaeum.uim.action.impl.InvocationButtonImpl#getPopup <em>Popup</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InvocationButtonImpl extends AbstractActionButtonImpl implements InvocationButton {
	/**
	 * The default value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UML_ELEMENT_UID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected String umlElementUid = UML_ELEMENT_UID_EDEFAULT;
	/**
	 * The cached value of the '{@link #getPopup() <em>Popup</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPopup()
	 * @generated
	 * @ordered
	 */
	protected UserInterfaceRoot popup;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InvocationButtonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ActionPackage.Literals.INVOCATION_BUTTON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUmlElementUid() {
		return umlElementUid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlElementUid(String newUmlElementUid) {
		String oldUmlElementUid = umlElementUid;
		umlElementUid = newUmlElementUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.INVOCATION_BUTTON__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInterfaceRoot getPopup() {
		if (popup != null && popup.eIsProxy()) {
			InternalEObject oldPopup = (InternalEObject)popup;
			popup = (UserInterfaceRoot)eResolveProxy(oldPopup);
			if (popup != oldPopup) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActionPackage.INVOCATION_BUTTON__POPUP, oldPopup, popup));
			}
		}
		return popup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInterfaceRoot basicGetPopup() {
		return popup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPopup(UserInterfaceRoot newPopup) {
		UserInterfaceRoot oldPopup = popup;
		popup = newPopup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.INVOCATION_BUTTON__POPUP, oldPopup, popup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ActionPackage.INVOCATION_BUTTON__UML_ELEMENT_UID:
				return getUmlElementUid();
			case ActionPackage.INVOCATION_BUTTON__POPUP:
				if (resolve) return getPopup();
				return basicGetPopup();
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
			case ActionPackage.INVOCATION_BUTTON__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case ActionPackage.INVOCATION_BUTTON__POPUP:
				setPopup((UserInterfaceRoot)newValue);
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
			case ActionPackage.INVOCATION_BUTTON__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case ActionPackage.INVOCATION_BUTTON__POPUP:
				setPopup((UserInterfaceRoot)null);
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
			case ActionPackage.INVOCATION_BUTTON__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case ActionPackage.INVOCATION_BUTTON__POPUP:
				return popup != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == UmlReference.class) {
			switch (derivedFeatureID) {
				case ActionPackage.INVOCATION_BUTTON__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == LabeledElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == UmlReference.class) {
			switch (baseFeatureID) {
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return ActionPackage.INVOCATION_BUTTON__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == LabeledElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (umlElementUid: ");
		result.append(umlElementUid);
		result.append(')');
		return result.toString();
	}

} //InvocationButtonImpl
