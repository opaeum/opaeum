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
import org.nakeduml.uim.NavigationBinding;
import org.nakeduml.uim.NavigationToEntity;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation UIMBinding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.NavigationBindingImpl#getNavigation <em>Navigation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NavigationBindingImpl extends UIMBindingImpl implements NavigationBinding {
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
		return UIMPackage.Literals.NAVIGATION_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationToEntity getNavigation() {
		if (eContainerFeatureID() != UIMPackage.NAVIGATION_BINDING__NAVIGATION) return null;
		return (NavigationToEntity)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNavigation(NavigationToEntity newNavigation, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newNavigation, UIMPackage.NAVIGATION_BINDING__NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigation(NavigationToEntity newNavigation) {
		if (newNavigation != eInternalContainer() || (eContainerFeatureID() != UIMPackage.NAVIGATION_BINDING__NAVIGATION && newNavigation != null)) {
			if (EcoreUtil.isAncestor(this, newNavigation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNavigation != null)
				msgs = ((InternalEObject)newNavigation).eInverseAdd(this, UIMPackage.NAVIGATION_TO_ENTITY__BINDING, NavigationToEntity.class, msgs);
			msgs = basicSetNavigation(newNavigation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.NAVIGATION_BINDING__NAVIGATION, newNavigation, newNavigation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.NAVIGATION_BINDING__NAVIGATION:
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
			case UIMPackage.NAVIGATION_BINDING__NAVIGATION:
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
			case UIMPackage.NAVIGATION_BINDING__NAVIGATION:
				return eInternalContainer().eInverseRemove(this, UIMPackage.NAVIGATION_TO_ENTITY__BINDING, NavigationToEntity.class, msgs);
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
			case UIMPackage.NAVIGATION_BINDING__NAVIGATION:
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
			case UIMPackage.NAVIGATION_BINDING__NAVIGATION:
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
			case UIMPackage.NAVIGATION_BINDING__NAVIGATION:
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
			case UIMPackage.NAVIGATION_BINDING__NAVIGATION:
				return getNavigation() != null;
		}
		return super.eIsSet(featureID);
	}

} //NavigationBindingImpl
