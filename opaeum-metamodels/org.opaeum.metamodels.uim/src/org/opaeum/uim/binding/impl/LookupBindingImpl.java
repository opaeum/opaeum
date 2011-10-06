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
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.LookupBinding;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimLookup;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lookup Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.binding.impl.LookupBindingImpl#getLookup <em>Lookup</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LookupBindingImpl extends UimBindingImpl implements LookupBinding {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LookupBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BindingPackage.Literals.LOOKUP_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLookup getLookup() {
		if (eContainerFeatureID() != BindingPackage.LOOKUP_BINDING__LOOKUP) return null;
		return (UimLookup)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLookup(UimLookup newLookup, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLookup, BindingPackage.LOOKUP_BINDING__LOOKUP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLookup(UimLookup newLookup) {
		if (newLookup != eInternalContainer() || (eContainerFeatureID() != BindingPackage.LOOKUP_BINDING__LOOKUP && newLookup != null)) {
			if (EcoreUtil.isAncestor(this, newLookup))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLookup != null)
				msgs = ((InternalEObject)newLookup).eInverseAdd(this, ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE, UimLookup.class, msgs);
			msgs = basicSetLookup(newLookup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BindingPackage.LOOKUP_BINDING__LOOKUP, newLookup, newLookup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BindingPackage.LOOKUP_BINDING__LOOKUP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLookup((UimLookup)otherEnd, msgs);
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
			case BindingPackage.LOOKUP_BINDING__LOOKUP:
				return basicSetLookup(null, msgs);
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
			case BindingPackage.LOOKUP_BINDING__LOOKUP:
				return eInternalContainer().eInverseRemove(this, ControlPackage.UIM_LOOKUP__LOOKUP_SOURCE, UimLookup.class, msgs);
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
			case BindingPackage.LOOKUP_BINDING__LOOKUP:
				return getLookup();
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
			case BindingPackage.LOOKUP_BINDING__LOOKUP:
				setLookup((UimLookup)newValue);
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
			case BindingPackage.LOOKUP_BINDING__LOOKUP:
				setLookup((UimLookup)null);
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
			case BindingPackage.LOOKUP_BINDING__LOOKUP:
				return getLookup() != null;
		}
		return super.eIsSet(featureID);
	}

} //LookupBindingImpl
