/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;

import java.util.Collection;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nakeduml.uim.ChildSecurityConstraint;
import org.nakeduml.uim.UIMComponent;
import org.nakeduml.uim.UIMContainer;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UIMContainerImpl#getSecurityOnEditability <em>Security On Editability</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMContainerImpl#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UIMContainerImpl extends UIMComponentImpl implements UIMContainer {
	/**
	 * The cached value of the '{@link #getSecurityOnEditability() <em>Security On Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityOnEditability()
	 * @generated
	 * @ordered
	 */
	protected ChildSecurityConstraint securityOnEditability;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<UIMComponent> children;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UIMContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.UIM_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChildSecurityConstraint getSecurityOnEditability() {
		return securityOnEditability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecurityOnEditability(ChildSecurityConstraint newSecurityOnEditability, NotificationChain msgs) {
		ChildSecurityConstraint oldSecurityOnEditability = securityOnEditability;
		securityOnEditability = newSecurityOnEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY, oldSecurityOnEditability, newSecurityOnEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityOnEditability(ChildSecurityConstraint newSecurityOnEditability) {
		if (newSecurityOnEditability != securityOnEditability) {
			NotificationChain msgs = null;
			if (securityOnEditability != null)
				msgs = ((InternalEObject)securityOnEditability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY, null, msgs);
			if (newSecurityOnEditability != null)
				msgs = ((InternalEObject)newSecurityOnEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY, null, msgs);
			msgs = basicSetSecurityOnEditability(newSecurityOnEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY, newSecurityOnEditability, newSecurityOnEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UIMComponent> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<UIMComponent>(UIMComponent.class, this, UIMPackage.UIM_CONTAINER__CHILDREN, UIMPackage.UIM_COMPONENT__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.UIM_CONTAINER__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
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
			case UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY:
				return basicSetSecurityOnEditability(null, msgs);
			case UIMPackage.UIM_CONTAINER__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
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
			case UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY:
				return getSecurityOnEditability();
			case UIMPackage.UIM_CONTAINER__CHILDREN:
				return getChildren();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY:
				setSecurityOnEditability((ChildSecurityConstraint)newValue);
				return;
			case UIMPackage.UIM_CONTAINER__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends UIMComponent>)newValue);
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
			case UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY:
				setSecurityOnEditability((ChildSecurityConstraint)null);
				return;
			case UIMPackage.UIM_CONTAINER__CHILDREN:
				getChildren().clear();
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
			case UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY:
				return securityOnEditability != null;
			case UIMPackage.UIM_CONTAINER__CHILDREN:
				return children != null && !children.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UIMContainerImpl
