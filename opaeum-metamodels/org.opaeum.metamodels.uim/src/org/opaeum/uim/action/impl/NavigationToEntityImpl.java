/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.action.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.NavigationToEntity;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.NavigationBinding;
import org.opaeum.uim.form.ClassForm;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation To Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.impl.NavigationToEntityImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opaeum.uim.action.impl.NavigationToEntityImpl#getToForm <em>To Form</em>}</li>
 *   <li>{@link org.opaeum.uim.action.impl.NavigationToEntityImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NavigationToEntityImpl extends UimNavigationImpl implements NavigationToEntity {
	/**
	 * The default value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UML_ELEMENT_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected String umlElementUid = UML_ELEMENT_UID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getToForm() <em>To Form</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToForm()
	 * @generated
	 * @ordered
	 */
	protected ClassForm toForm;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected NavigationBinding binding;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationToEntityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ActionPackage.Literals.NAVIGATION_TO_ENTITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUmlElementUid() {
		return umlElementUid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlElementUid(String newUmlElementUid) {
		String oldUmlElementUid = umlElementUid;
		umlElementUid = newUmlElementUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.NAVIGATION_TO_ENTITY__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassForm getToForm() {
		if (toForm != null && toForm.eIsProxy()) {
			InternalEObject oldToForm = (InternalEObject)toForm;
			toForm = (ClassForm)eResolveProxy(oldToForm);
			if (toForm != oldToForm) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActionPackage.NAVIGATION_TO_ENTITY__TO_FORM, oldToForm, toForm));
			}
		}
		return toForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassForm basicGetToForm() {
		return toForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToForm(ClassForm newToForm) {
		ClassForm oldToForm = toForm;
		toForm = newToForm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.NAVIGATION_TO_ENTITY__TO_FORM, oldToForm, toForm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationBinding getBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(NavigationBinding newBinding, NotificationChain msgs) {
		NavigationBinding oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActionPackage.NAVIGATION_TO_ENTITY__BINDING, oldBinding, newBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(NavigationBinding newBinding) {
		if (newBinding != binding) {
			NotificationChain msgs = null;
			if (binding != null)
				msgs = ((InternalEObject)binding).eInverseRemove(this, BindingPackage.NAVIGATION_BINDING__NAVIGATION, NavigationBinding.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, BindingPackage.NAVIGATION_BINDING__NAVIGATION, NavigationBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ActionPackage.NAVIGATION_TO_ENTITY__BINDING, newBinding, newBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ActionPackage.NAVIGATION_TO_ENTITY__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActionPackage.NAVIGATION_TO_ENTITY__BINDING, null, msgs);
				return basicSetBinding((NavigationBinding)otherEnd, msgs);
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
			case ActionPackage.NAVIGATION_TO_ENTITY__BINDING:
				return basicSetBinding(null, msgs);
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
			case ActionPackage.NAVIGATION_TO_ENTITY__UML_ELEMENT_UID:
				return getUmlElementUid();
			case ActionPackage.NAVIGATION_TO_ENTITY__TO_FORM:
				if (resolve) return getToForm();
				return basicGetToForm();
			case ActionPackage.NAVIGATION_TO_ENTITY__BINDING:
				return getBinding();
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
			case ActionPackage.NAVIGATION_TO_ENTITY__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case ActionPackage.NAVIGATION_TO_ENTITY__TO_FORM:
				setToForm((ClassForm)newValue);
				return;
			case ActionPackage.NAVIGATION_TO_ENTITY__BINDING:
				setBinding((NavigationBinding)newValue);
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
			case ActionPackage.NAVIGATION_TO_ENTITY__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case ActionPackage.NAVIGATION_TO_ENTITY__TO_FORM:
				setToForm((ClassForm)null);
				return;
			case ActionPackage.NAVIGATION_TO_ENTITY__BINDING:
				setBinding((NavigationBinding)null);
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
			case ActionPackage.NAVIGATION_TO_ENTITY__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case ActionPackage.NAVIGATION_TO_ENTITY__TO_FORM:
				return toForm != null;
			case ActionPackage.NAVIGATION_TO_ENTITY__BINDING:
				return binding != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == UmlReference.class) {
			switch (derivedFeatureID) {
				case ActionPackage.NAVIGATION_TO_ENTITY__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == UmlReference.class) {
			switch (baseFeatureID) {
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return ActionPackage.NAVIGATION_TO_ENTITY__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (umlElementUid: ");
		result.append(umlElementUid);
		result.append(')');
		return result.toString();
	}

} //NavigationToEntityImpl
