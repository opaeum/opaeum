/**
 */
package org.opaeum.uim.action.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.action.OperationActionPopup;

import org.opaeum.uim.impl.UserInterfaceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Action Popup</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.impl.OperationActionPopupImpl#getOperationAction <em>Operation Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationActionPopupImpl extends UserInterfaceImpl implements OperationActionPopup {
	/**
	 * The cached value of the '{@link #getOperationAction() <em>Operation Action</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationAction()
	 * @generated
	 * @ordered
	 */
	protected OperationAction operationAction;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationActionPopupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ActionPackage.Literals.OPERATION_ACTION_POPUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationAction getOperationAction() {
		if (operationAction != null && operationAction.eIsProxy()) {
			InternalEObject oldOperationAction = (InternalEObject)operationAction;
			operationAction = (OperationAction)eResolveProxy(oldOperationAction);
			if (operationAction != oldOperationAction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION, oldOperationAction, operationAction));
			}
		}
		return operationAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationAction basicGetOperationAction() {
		return operationAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperationAction(OperationAction newOperationAction, NotificationChain msgs) {
		OperationAction oldOperationAction = operationAction;
		operationAction = newOperationAction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION, oldOperationAction, newOperationAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperationAction(OperationAction newOperationAction) {
		if (newOperationAction != operationAction) {
			NotificationChain msgs = null;
			if (operationAction != null)
				msgs = ((InternalEObject)operationAction).eInverseRemove(this, ActionPackage.OPERATION_ACTION__POPUP, OperationAction.class, msgs);
			if (newOperationAction != null)
				msgs = ((InternalEObject)newOperationAction).eInverseAdd(this, ActionPackage.OPERATION_ACTION__POPUP, OperationAction.class, msgs);
			msgs = basicSetOperationAction(newOperationAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION, newOperationAction, newOperationAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION:
				if (operationAction != null)
					msgs = ((InternalEObject)operationAction).eInverseRemove(this, ActionPackage.OPERATION_ACTION__POPUP, OperationAction.class, msgs);
				return basicSetOperationAction((OperationAction)otherEnd, msgs);
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
			case ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION:
				return basicSetOperationAction(null, msgs);
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
			case ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION:
				if (resolve) return getOperationAction();
				return basicGetOperationAction();
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
			case ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION:
				setOperationAction((OperationAction)newValue);
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
			case ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION:
				setOperationAction((OperationAction)null);
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
			case ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION:
				return operationAction != null;
		}
		return super.eIsSet(featureID);
	}

} //OperationActionPopupImpl
