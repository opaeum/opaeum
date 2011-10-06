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
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.NavigationToEntity;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.NavigationBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.binding.impl.NavigationBindingImpl#getNavigation <em>Navigation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NavigationBindingImpl extends UimBindingImpl implements NavigationBinding {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BindingPackage.Literals.NAVIGATION_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationToEntity getNavigation() {
		if (eContainerFeatureID() != BindingPackage.NAVIGATION_BINDING__NAVIGATION) return null;
		return (NavigationToEntity)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNavigation(NavigationToEntity newNavigation, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newNavigation, BindingPackage.NAVIGATION_BINDING__NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigation(NavigationToEntity newNavigation) {
		if (newNavigation != eInternalContainer() || (eContainerFeatureID() != BindingPackage.NAVIGATION_BINDING__NAVIGATION && newNavigation != null)) {
			if (EcoreUtil.isAncestor(this, newNavigation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNavigation != null)
				msgs = ((InternalEObject)newNavigation).eInverseAdd(this, ActionPackage.NAVIGATION_TO_ENTITY__BINDING, NavigationToEntity.class, msgs);
			msgs = basicSetNavigation(newNavigation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BindingPackage.NAVIGATION_BINDING__NAVIGATION, newNavigation, newNavigation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BindingPackage.NAVIGATION_BINDING__NAVIGATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetNavigation((NavigationToEntity)otherEnd, msgs);
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
			case BindingPackage.NAVIGATION_BINDING__NAVIGATION:
				return basicSetNavigation(null, msgs);
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
			case BindingPackage.NAVIGATION_BINDING__NAVIGATION:
				return eInternalContainer().eInverseRemove(this, ActionPackage.NAVIGATION_TO_ENTITY__BINDING, NavigationToEntity.class, msgs);
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
			case BindingPackage.NAVIGATION_BINDING__NAVIGATION:
				return getNavigation();
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
			case BindingPackage.NAVIGATION_BINDING__NAVIGATION:
				setNavigation((NavigationToEntity)newValue);
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
			case BindingPackage.NAVIGATION_BINDING__NAVIGATION:
				setNavigation((NavigationToEntity)null);
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
			case BindingPackage.NAVIGATION_BINDING__NAVIGATION:
				return getNavigation() != null;
		}
		return super.eIsSet(featureID);
	}

} //NavigationBindingImpl
