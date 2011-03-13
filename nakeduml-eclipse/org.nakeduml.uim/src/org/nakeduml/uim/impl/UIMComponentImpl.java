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
import org.nakeduml.uim.ChildSecurityConstraint;
import org.nakeduml.uim.UIMComponent;
import org.nakeduml.uim.UIMContainer;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UIMComponentImpl#getSecurityOnVisibility <em>Security On Visibility</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMComponentImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class UIMComponentImpl extends UserInteractionElementImpl implements UIMComponent {
	/**
	 * The cached value of the '{@link #getSecurityOnVisibility() <em>Security On Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityOnVisibility()
	 * @generated
	 * @ordered
	 */
	protected ChildSecurityConstraint securityOnVisibility;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UIMComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.UIM_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChildSecurityConstraint getSecurityOnVisibility() {
		return securityOnVisibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecurityOnVisibility(ChildSecurityConstraint newSecurityOnVisibility, NotificationChain msgs) {
		ChildSecurityConstraint oldSecurityOnVisibility = securityOnVisibility;
		securityOnVisibility = newSecurityOnVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY, oldSecurityOnVisibility, newSecurityOnVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityOnVisibility(ChildSecurityConstraint newSecurityOnVisibility) {
		if (newSecurityOnVisibility != securityOnVisibility) {
			NotificationChain msgs = null;
			if (securityOnVisibility != null)
				msgs = ((InternalEObject)securityOnVisibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY, null, msgs);
			if (newSecurityOnVisibility != null)
				msgs = ((InternalEObject)newSecurityOnVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY, null, msgs);
			msgs = basicSetSecurityOnVisibility(newSecurityOnVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY, newSecurityOnVisibility, newSecurityOnVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMContainer getParent() {
		if (eContainerFeatureID() != UIMPackage.UIM_COMPONENT__PARENT) return null;
		return (UIMContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(UIMContainer newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, UIMPackage.UIM_COMPONENT__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(UIMContainer newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != UIMPackage.UIM_COMPONENT__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, UIMPackage.UIM_CONTAINER__CHILDREN, UIMContainer.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_COMPONENT__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.UIM_COMPONENT__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((UIMContainer)otherEnd, msgs);
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
			case UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY:
				return basicSetSecurityOnVisibility(null, msgs);
			case UIMPackage.UIM_COMPONENT__PARENT:
				return basicSetParent(null, msgs);
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
			case UIMPackage.UIM_COMPONENT__PARENT:
				return eInternalContainer().eInverseRemove(this, UIMPackage.UIM_CONTAINER__CHILDREN, UIMContainer.class, msgs);
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
			case UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY:
				return getSecurityOnVisibility();
			case UIMPackage.UIM_COMPONENT__PARENT:
				return getParent();
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
			case UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY:
				setSecurityOnVisibility((ChildSecurityConstraint)newValue);
				return;
			case UIMPackage.UIM_COMPONENT__PARENT:
				setParent((UIMContainer)newValue);
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
			case UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY:
				setSecurityOnVisibility((ChildSecurityConstraint)null);
				return;
			case UIMPackage.UIM_COMPONENT__PARENT:
				setParent((UIMContainer)null);
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
			case UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY:
				return securityOnVisibility != null;
			case UIMPackage.UIM_COMPONENT__PARENT:
				return getParent() != null;
		}
		return super.eIsSet(featureID);
	}

} //UIMComponentImpl
