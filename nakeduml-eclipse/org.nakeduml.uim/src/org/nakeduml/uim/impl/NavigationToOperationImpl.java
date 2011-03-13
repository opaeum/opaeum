/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.nakeduml.uim.NavigationToOperation;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation To Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.NavigationToOperationImpl#getToForm <em>To Form</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NavigationToOperationImpl extends UIMNavigationImpl implements NavigationToOperation {
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
		return UIMPackage.Literals.NAVIGATION_TO_OPERATION;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIMPackage.NAVIGATION_TO_OPERATION__TO_FORM, oldToForm, toForm));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.NAVIGATION_TO_OPERATION__TO_FORM, oldToForm, toForm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UIMPackage.NAVIGATION_TO_OPERATION__TO_FORM:
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
			case UIMPackage.NAVIGATION_TO_OPERATION__TO_FORM:
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
			case UIMPackage.NAVIGATION_TO_OPERATION__TO_FORM:
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
			case UIMPackage.NAVIGATION_TO_OPERATION__TO_FORM:
				return toForm != null;
		}
		return super.eIsSet(featureID);
	}

} //NavigationToOperationImpl
