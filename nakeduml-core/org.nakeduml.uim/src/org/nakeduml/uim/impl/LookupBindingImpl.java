/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nakeduml.uim.LookupBinding;
import org.nakeduml.uim.UIMLookup;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lookup UIMBinding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.LookupBindingImpl#getLookup <em>Lookup</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LookupBindingImpl extends UIMBindingImpl implements LookupBinding {
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
		return UIMPackage.Literals.LOOKUP_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMLookup getLookup() {
		if (eContainerFeatureID() != UIMPackage.LOOKUP_BINDING__LOOKUP) return null;
		return (UIMLookup)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLookup(UIMLookup newLookup, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLookup, UIMPackage.LOOKUP_BINDING__LOOKUP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLookup(UIMLookup newLookup) {
		if (newLookup != eInternalContainer() || (eContainerFeatureID() != UIMPackage.LOOKUP_BINDING__LOOKUP && newLookup != null)) {
			if (EcoreUtil.isAncestor(this, newLookup))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLookup != null)
				msgs = ((InternalEObject)newLookup).eInverseAdd(this, UIMPackage.UIM_LOOKUP__LOOKUP_SOURCE, UIMLookup.class, msgs);
			msgs = basicSetLookup(newLookup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.LOOKUP_BINDING__LOOKUP, newLookup, newLookup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.LOOKUP_BINDING__LOOKUP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLookup((UIMLookup)otherEnd, msgs);
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
			case UIMPackage.LOOKUP_BINDING__LOOKUP:
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
			case UIMPackage.LOOKUP_BINDING__LOOKUP:
				return eInternalContainer().eInverseRemove(this, UIMPackage.UIM_LOOKUP__LOOKUP_SOURCE, UIMLookup.class, msgs);
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
			case UIMPackage.LOOKUP_BINDING__LOOKUP:
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
			case UIMPackage.LOOKUP_BINDING__LOOKUP:
				setLookup((UIMLookup)newValue);
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
			case UIMPackage.LOOKUP_BINDING__LOOKUP:
				setLookup((UIMLookup)null);
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
			case UIMPackage.LOOKUP_BINDING__LOOKUP:
				return getLookup() != null;
		}
		return super.eIsSet(featureID);
	}

} //LookupBindingImpl
