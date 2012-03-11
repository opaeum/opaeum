/**
 */
package org.opaeum.uim.action.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
		if (eContainerFeatureID() != ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION) return null;
		return (OperationAction)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperationAction(OperationAction newOperationAction, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOperationAction, ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperationAction(OperationAction newOperationAction) {
		if (newOperationAction != eInternalContainer() || (eContainerFeatureID() != ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION && newOperationAction != null)) {
			if (EcoreUtil.isAncestor(this, newOperationAction))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
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
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION:
				return eInternalContainer().eInverseRemove(this, ActionPackage.OPERATION_ACTION__POPUP, OperationAction.class, msgs);
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
			case ActionPackage.OPERATION_ACTION_POPUP__OPERATION_ACTION:
				return getOperationAction();
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
				return getOperationAction() != null;
		}
		return super.eIsSet(featureID);
	}

} //OperationActionPopupImpl
