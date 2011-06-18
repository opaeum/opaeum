/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.control.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.nakeduml.uim.binding.BindingPackage;
import org.nakeduml.uim.binding.LookupBinding;

import org.nakeduml.uim.control.ControlPackage;
import org.nakeduml.uim.control.UimLookup;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uim Lookup</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.control.impl.UimLookupImpl#getLookupSource <em>Lookup Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimLookupImpl extends UimControlImpl implements UimLookup {
	/**
	 * The cached value of the '{@link #getLookupSource() <em>Lookup Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupSource()
	 * @generated
	 * @ordered
	 */
	protected LookupBinding lookupSource;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimLookupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.UIM_LOOKUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LookupBinding getLookupSource() {
		return lookupSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLookupSource(LookupBinding newLookupSource, NotificationChain msgs) {
		LookupBinding oldLookupSource = lookupSource;
		lookupSource = newLookupSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE, oldLookupSource, newLookupSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLookupSource(LookupBinding newLookupSource) {
		if (newLookupSource != lookupSource) {
			NotificationChain msgs = null;
			if (lookupSource != null)
				msgs = ((InternalEObject)lookupSource).eInverseRemove(this, BindingPackage.LOOKUP_BINDING__LOOKUP, LookupBinding.class, msgs);
			if (newLookupSource != null)
				msgs = ((InternalEObject)newLookupSource).eInverseAdd(this, BindingPackage.LOOKUP_BINDING__LOOKUP, LookupBinding.class, msgs);
			msgs = basicSetLookupSource(newLookupSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE, newLookupSource, newLookupSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE:
				if (lookupSource != null)
					msgs = ((InternalEObject)lookupSource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE, null, msgs);
				return basicSetLookupSource((LookupBinding)otherEnd, msgs);
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
			case ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE:
				return basicSetLookupSource(null, msgs);
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
			case ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE:
				return getLookupSource();
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
			case ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE:
				setLookupSource((LookupBinding)newValue);
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
			case ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE:
				setLookupSource((LookupBinding)null);
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
			case ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE:
				return lookupSource != null;
		}
		return super.eIsSet(featureID);
	}

} //UimLookupImpl
