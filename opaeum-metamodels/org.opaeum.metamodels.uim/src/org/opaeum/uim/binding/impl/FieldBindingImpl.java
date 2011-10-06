/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.binding.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.FieldBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.binding.impl.FieldBindingImpl#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldBindingImpl extends UimBindingImpl implements FieldBinding {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BindingPackage.Literals.FIELD_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimField getField() {
		if (eContainerFeatureID() != BindingPackage.FIELD_BINDING__FIELD) return null;
		return (UimField)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetField(UimField newField, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newField, BindingPackage.FIELD_BINDING__FIELD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setField(UimField newField) {
		if (newField != eInternalContainer() || (eContainerFeatureID() != BindingPackage.FIELD_BINDING__FIELD && newField != null)) {
			if (EcoreUtil.isAncestor(this, newField))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newField != null)
				msgs = ((InternalEObject)newField).eInverseAdd(this, UimPackage.UIM_FIELD__BINDING, UimField.class, msgs);
			msgs = basicSetField(newField, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BindingPackage.FIELD_BINDING__FIELD, newField, newField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BindingPackage.FIELD_BINDING__FIELD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetField((UimField)otherEnd, msgs);
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
			case BindingPackage.FIELD_BINDING__FIELD:
				return basicSetField(null, msgs);
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
			case BindingPackage.FIELD_BINDING__FIELD:
				return eInternalContainer().eInverseRemove(this, UimPackage.UIM_FIELD__BINDING, UimField.class, msgs);
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
			case BindingPackage.FIELD_BINDING__FIELD:
				return getField();
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
			case BindingPackage.FIELD_BINDING__FIELD:
				setField((UimField)newValue);
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
			case BindingPackage.FIELD_BINDING__FIELD:
				setField((UimField)null);
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
			case BindingPackage.FIELD_BINDING__FIELD:
				return getField() != null;
		}
		return super.eIsSet(featureID);
	}

} //FieldBindingImpl
