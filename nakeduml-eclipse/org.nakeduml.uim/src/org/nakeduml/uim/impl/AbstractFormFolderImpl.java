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
import org.nakeduml.uim.AbstractFolder;
import org.nakeduml.uim.AbstractFormFolder;
import org.nakeduml.uim.ChildSecurityConstraint;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Form Folder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.AbstractFormFolderImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.AbstractFormFolderImpl#getSecurityOnVisibility <em>Security On Visibility</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.AbstractFormFolderImpl#getSecurityOnEditability <em>Security On Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractFormFolderImpl extends AbstractFolderImpl implements AbstractFormFolder {
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
	 * The cached value of the '{@link #getSecurityOnEditability() <em>Security On Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityOnEditability()
	 * @generated
	 * @ordered
	 */
	protected ChildSecurityConstraint securityOnEditability;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractFormFolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.ABSTRACT_FORM_FOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractFolder getParent() {
		if (eContainerFeatureID() != UIMPackage.ABSTRACT_FORM_FOLDER__PARENT) return null;
		return (AbstractFolder)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(AbstractFolder newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, UIMPackage.ABSTRACT_FORM_FOLDER__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(AbstractFolder newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != UIMPackage.ABSTRACT_FORM_FOLDER__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, UIMPackage.ABSTRACT_FOLDER__CHILDREN, AbstractFolder.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.ABSTRACT_FORM_FOLDER__PARENT, newParent, newParent));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY, oldSecurityOnVisibility, newSecurityOnVisibility);
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
				msgs = ((InternalEObject)securityOnVisibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY, null, msgs);
			if (newSecurityOnVisibility != null)
				msgs = ((InternalEObject)newSecurityOnVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY, null, msgs);
			msgs = basicSetSecurityOnVisibility(newSecurityOnVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY, newSecurityOnVisibility, newSecurityOnVisibility));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY, oldSecurityOnEditability, newSecurityOnEditability);
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
				msgs = ((InternalEObject)securityOnEditability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY, null, msgs);
			if (newSecurityOnEditability != null)
				msgs = ((InternalEObject)newSecurityOnEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY, null, msgs);
			msgs = basicSetSecurityOnEditability(newSecurityOnEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY, newSecurityOnEditability, newSecurityOnEditability));
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
			case UIMPackage.ABSTRACT_FORM_FOLDER__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((AbstractFolder)otherEnd, msgs);
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
			case UIMPackage.ABSTRACT_FORM_FOLDER__PARENT:
				return basicSetParent(null, msgs);
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY:
				return basicSetSecurityOnVisibility(null, msgs);
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY:
				return basicSetSecurityOnEditability(null, msgs);
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
			case UIMPackage.ABSTRACT_FORM_FOLDER__PARENT:
				return eInternalContainer().eInverseRemove(this, UIMPackage.ABSTRACT_FOLDER__CHILDREN, AbstractFolder.class, msgs);
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
			case UIMPackage.ABSTRACT_FORM_FOLDER__PARENT:
				return getParent();
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY:
				return getSecurityOnVisibility();
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY:
				return getSecurityOnEditability();
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
			case UIMPackage.ABSTRACT_FORM_FOLDER__PARENT:
				setParent((AbstractFolder)newValue);
				return;
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY:
				setSecurityOnVisibility((ChildSecurityConstraint)newValue);
				return;
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY:
				setSecurityOnEditability((ChildSecurityConstraint)newValue);
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
			case UIMPackage.ABSTRACT_FORM_FOLDER__PARENT:
				setParent((AbstractFolder)null);
				return;
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY:
				setSecurityOnVisibility((ChildSecurityConstraint)null);
				return;
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY:
				setSecurityOnEditability((ChildSecurityConstraint)null);
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
			case UIMPackage.ABSTRACT_FORM_FOLDER__PARENT:
				return getParent() != null;
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY:
				return securityOnVisibility != null;
			case UIMPackage.ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY:
				return securityOnEditability != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractFormFolderImpl
