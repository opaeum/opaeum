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
import org.eclipse.uml2.uml.Model;
import org.nakeduml.uim.ModelSecurityConstraint;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.UserInteractionModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UserInteractionModelImpl#getUmlModel <em>Uml Model</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UserInteractionModelImpl#getSecurityOnVisibility <em>Security On Visibility</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UserInteractionModelImpl#getSecuirytOnEditability <em>Secuiryt On Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserInteractionModelImpl extends AbstractFolderImpl implements UserInteractionModel {
	/**
	 * The cached value of the '{@link #getUmlModel() <em>Uml Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlModel()
	 * @generated
	 * @ordered
	 */
	protected Model umlModel;

	/**
	 * The cached value of the '{@link #getSecurityOnVisibility() <em>Security On Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityOnVisibility()
	 * @generated
	 * @ordered
	 */
	protected ModelSecurityConstraint securityOnVisibility;
	/**
	 * The cached value of the '{@link #getSecuirytOnEditability() <em>Secuiryt On Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecuirytOnEditability()
	 * @generated
	 * @ordered
	 */
	protected ModelSecurityConstraint secuirytOnEditability;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserInteractionModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.USER_INTERACTION_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getUmlModel() {
		if (umlModel != null && umlModel.eIsProxy()) {
			InternalEObject oldUmlModel = (InternalEObject)umlModel;
			umlModel = (Model)eResolveProxy(oldUmlModel);
			if (umlModel != oldUmlModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIMPackage.USER_INTERACTION_MODEL__UML_MODEL, oldUmlModel, umlModel));
			}
		}
		return umlModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model basicGetUmlModel() {
		return umlModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlModel(Model newUmlModel) {
		Model oldUmlModel = umlModel;
		umlModel = newUmlModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.USER_INTERACTION_MODEL__UML_MODEL, oldUmlModel, umlModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSecurityConstraint getSecurityOnVisibility() {
		return securityOnVisibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecurityOnVisibility(ModelSecurityConstraint newSecurityOnVisibility, NotificationChain msgs) {
		ModelSecurityConstraint oldSecurityOnVisibility = securityOnVisibility;
		securityOnVisibility = newSecurityOnVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY, oldSecurityOnVisibility, newSecurityOnVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityOnVisibility(ModelSecurityConstraint newSecurityOnVisibility) {
		if (newSecurityOnVisibility != securityOnVisibility) {
			NotificationChain msgs = null;
			if (securityOnVisibility != null)
				msgs = ((InternalEObject)securityOnVisibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY, null, msgs);
			if (newSecurityOnVisibility != null)
				msgs = ((InternalEObject)newSecurityOnVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY, null, msgs);
			msgs = basicSetSecurityOnVisibility(newSecurityOnVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY, newSecurityOnVisibility, newSecurityOnVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSecurityConstraint getSecuirytOnEditability() {
		return secuirytOnEditability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecuirytOnEditability(ModelSecurityConstraint newSecuirytOnEditability, NotificationChain msgs) {
		ModelSecurityConstraint oldSecuirytOnEditability = secuirytOnEditability;
		secuirytOnEditability = newSecuirytOnEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY, oldSecuirytOnEditability, newSecuirytOnEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecuirytOnEditability(ModelSecurityConstraint newSecuirytOnEditability) {
		if (newSecuirytOnEditability != secuirytOnEditability) {
			NotificationChain msgs = null;
			if (secuirytOnEditability != null)
				msgs = ((InternalEObject)secuirytOnEditability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY, null, msgs);
			if (newSecuirytOnEditability != null)
				msgs = ((InternalEObject)newSecuirytOnEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY, null, msgs);
			msgs = basicSetSecuirytOnEditability(newSecuirytOnEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY, newSecuirytOnEditability, newSecuirytOnEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY:
				return basicSetSecurityOnVisibility(null, msgs);
			case UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY:
				return basicSetSecuirytOnEditability(null, msgs);
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
			case UIMPackage.USER_INTERACTION_MODEL__UML_MODEL:
				if (resolve) return getUmlModel();
				return basicGetUmlModel();
			case UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY:
				return getSecurityOnVisibility();
			case UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY:
				return getSecuirytOnEditability();
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
			case UIMPackage.USER_INTERACTION_MODEL__UML_MODEL:
				setUmlModel((Model)newValue);
				return;
			case UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY:
				setSecurityOnVisibility((ModelSecurityConstraint)newValue);
				return;
			case UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY:
				setSecuirytOnEditability((ModelSecurityConstraint)newValue);
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
			case UIMPackage.USER_INTERACTION_MODEL__UML_MODEL:
				setUmlModel((Model)null);
				return;
			case UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY:
				setSecurityOnVisibility((ModelSecurityConstraint)null);
				return;
			case UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY:
				setSecuirytOnEditability((ModelSecurityConstraint)null);
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
			case UIMPackage.USER_INTERACTION_MODEL__UML_MODEL:
				return umlModel != null;
			case UIMPackage.USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY:
				return securityOnVisibility != null;
			case UIMPackage.USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY:
				return secuirytOnEditability != null;
		}
		return super.eIsSet(featureID);
	}

} //UserInteractionModelImpl
