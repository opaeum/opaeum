/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.action.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opeum.uim.UimPackage;
import org.opeum.uim.UmlReference;
import org.opeum.uim.action.ActionPackage;
import org.opeum.uim.action.NavigationToOperation;
import org.opeum.uim.form.OperationInvocationForm;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation To Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opeum.uim.action.impl.NavigationToOperationImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opeum.uim.action.impl.NavigationToOperationImpl#getToForm <em>To Form</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NavigationToOperationImpl extends UimNavigationImpl implements NavigationToOperation {
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
	 * The cached value of the '{@link #getToForm() <em>To Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToForm()
	 * @generated
	 * @ordered
	 */
	protected OperationInvocationForm toForm;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationToOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ActionPackage.Literals.NAVIGATION_TO_OPERATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.NAVIGATION_TO_OPERATION__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInvocationForm getToForm() {
		if (toForm != null && toForm.eIsProxy()) {
			InternalEObject oldToForm = (InternalEObject)toForm;
			toForm = (OperationInvocationForm)eResolveProxy(oldToForm);
			if (toForm != oldToForm) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActionPackage.NAVIGATION_TO_OPERATION__TO_FORM, oldToForm, toForm));
			}
		}
		return toForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInvocationForm basicGetToForm() {
		return toForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToForm(OperationInvocationForm newToForm) {
		OperationInvocationForm oldToForm = toForm;
		toForm = newToForm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.NAVIGATION_TO_OPERATION__TO_FORM, oldToForm, toForm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ActionPackage.NAVIGATION_TO_OPERATION__UML_ELEMENT_UID:
				return getUmlElementUid();
			case ActionPackage.NAVIGATION_TO_OPERATION__TO_FORM:
				if (resolve) return getToForm();
				return basicGetToForm();
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
			case ActionPackage.NAVIGATION_TO_OPERATION__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case ActionPackage.NAVIGATION_TO_OPERATION__TO_FORM:
				setToForm((OperationInvocationForm)newValue);
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
			case ActionPackage.NAVIGATION_TO_OPERATION__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case ActionPackage.NAVIGATION_TO_OPERATION__TO_FORM:
				setToForm((OperationInvocationForm)null);
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
			case ActionPackage.NAVIGATION_TO_OPERATION__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case ActionPackage.NAVIGATION_TO_OPERATION__TO_FORM:
				return toForm != null;
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
				case ActionPackage.NAVIGATION_TO_OPERATION__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
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
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return ActionPackage.NAVIGATION_TO_OPERATION__UML_ELEMENT_UID;
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

} //NavigationToOperationImpl
