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
import org.nakeduml.uim.ChildSecurityConstraint;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.UIMControl;
import org.nakeduml.uim.UIMField;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UIMFieldImpl#getControl <em>Control</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMFieldImpl#getControlKind <em>Control Kind</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMFieldImpl#getLabelWidth <em>Label Width</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMFieldImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMFieldImpl#getSecurityOnEditability <em>Security On Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UIMFieldImpl extends UIMComponentImpl implements UIMField {
	/**
	 * The cached value of the '{@link #getControl() <em>Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControl()
	 * @generated
	 * @ordered
	 */
	protected UIMControl control;

	/**
	 * The default value of the '{@link #getControlKind() <em>Control Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlKind()
	 * @generated
	 * @ordered
	 */
	protected static final ControlKind CONTROL_KIND_EDEFAULT = ControlKind.DATE_POPUP;

	/**
	 * The cached value of the '{@link #getControlKind() <em>Control Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlKind()
	 * @generated
	 * @ordered
	 */
	protected ControlKind controlKind = CONTROL_KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabelWidth() <em>Label Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int LABEL_WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLabelWidth() <em>Label Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelWidth()
	 * @generated
	 * @ordered
	 */
	protected int labelWidth = LABEL_WIDTH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected FieldBinding binding;

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
	protected UIMFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.UIM_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMControl getControl() {
		return control;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetControl(UIMControl newControl, NotificationChain msgs) {
		UIMControl oldControl = control;
		control = newControl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_FIELD__CONTROL, oldControl, newControl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControl(UIMControl newControl) {
		if (newControl != control) {
			NotificationChain msgs = null;
			if (control != null)
				msgs = ((InternalEObject)control).eInverseRemove(this, UIMPackage.UIM_CONTROL__FIELD, UIMControl.class, msgs);
			if (newControl != null)
				msgs = ((InternalEObject)newControl).eInverseAdd(this, UIMPackage.UIM_CONTROL__FIELD, UIMControl.class, msgs);
			msgs = basicSetControl(newControl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_FIELD__CONTROL, newControl, newControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlKind getControlKind() {
		return controlKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControlKind(ControlKind newControlKind) {
		ControlKind oldControlKind = controlKind;
		controlKind = newControlKind == null ? CONTROL_KIND_EDEFAULT : newControlKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_FIELD__CONTROL_KIND, oldControlKind, controlKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLabelWidth() {
		return labelWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelWidth(int newLabelWidth) {
		int oldLabelWidth = labelWidth;
		labelWidth = newLabelWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_FIELD__LABEL_WIDTH, oldLabelWidth, labelWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldBinding getBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(FieldBinding newBinding, NotificationChain msgs) {
		FieldBinding oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_FIELD__BINDING, oldBinding, newBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(FieldBinding newBinding) {
		if (newBinding != binding) {
			NotificationChain msgs = null;
			if (binding != null)
				msgs = ((InternalEObject)binding).eInverseRemove(this, UIMPackage.FIELD_BINDING__FIELD, FieldBinding.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, UIMPackage.FIELD_BINDING__FIELD, FieldBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_FIELD__BINDING, newBinding, newBinding));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY, oldSecurityOnEditability, newSecurityOnEditability);
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
				msgs = ((InternalEObject)securityOnEditability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY, null, msgs);
			if (newSecurityOnEditability != null)
				msgs = ((InternalEObject)newSecurityOnEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY, null, msgs);
			msgs = basicSetSecurityOnEditability(newSecurityOnEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY, newSecurityOnEditability, newSecurityOnEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.UIM_FIELD__CONTROL:
				if (control != null)
					msgs = ((InternalEObject)control).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_FIELD__CONTROL, null, msgs);
				return basicSetControl((UIMControl)otherEnd, msgs);
			case UIMPackage.UIM_FIELD__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_FIELD__BINDING, null, msgs);
				return basicSetBinding((FieldBinding)otherEnd, msgs);
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
			case UIMPackage.UIM_FIELD__CONTROL:
				return basicSetControl(null, msgs);
			case UIMPackage.UIM_FIELD__BINDING:
				return basicSetBinding(null, msgs);
			case UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UIMPackage.UIM_FIELD__CONTROL:
				return getControl();
			case UIMPackage.UIM_FIELD__CONTROL_KIND:
				return getControlKind();
			case UIMPackage.UIM_FIELD__LABEL_WIDTH:
				return getLabelWidth();
			case UIMPackage.UIM_FIELD__BINDING:
				return getBinding();
			case UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY:
				return getSecurityOnEditability();
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
			case UIMPackage.UIM_FIELD__CONTROL:
				setControl((UIMControl)newValue);
				return;
			case UIMPackage.UIM_FIELD__CONTROL_KIND:
				setControlKind((ControlKind)newValue);
				return;
			case UIMPackage.UIM_FIELD__LABEL_WIDTH:
				setLabelWidth((Integer)newValue);
				return;
			case UIMPackage.UIM_FIELD__BINDING:
				setBinding((FieldBinding)newValue);
				return;
			case UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY:
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
			case UIMPackage.UIM_FIELD__CONTROL:
				setControl((UIMControl)null);
				return;
			case UIMPackage.UIM_FIELD__CONTROL_KIND:
				setControlKind(CONTROL_KIND_EDEFAULT);
				return;
			case UIMPackage.UIM_FIELD__LABEL_WIDTH:
				setLabelWidth(LABEL_WIDTH_EDEFAULT);
				return;
			case UIMPackage.UIM_FIELD__BINDING:
				setBinding((FieldBinding)null);
				return;
			case UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY:
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
			case UIMPackage.UIM_FIELD__CONTROL:
				return control != null;
			case UIMPackage.UIM_FIELD__CONTROL_KIND:
				return controlKind != CONTROL_KIND_EDEFAULT;
			case UIMPackage.UIM_FIELD__LABEL_WIDTH:
				return labelWidth != LABEL_WIDTH_EDEFAULT;
			case UIMPackage.UIM_FIELD__BINDING:
				return binding != null;
			case UIMPackage.UIM_FIELD__SECURITY_ON_EDITABILITY:
				return securityOnEditability != null;
		}
		return super.eIsSet(featureID);
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
		result.append(" (controlKind: ");
		result.append(controlKind);
		result.append(", labelWidth: ");
		result.append(labelWidth);
		result.append(')');
		return result.toString();
	}

} //UIMFieldImpl
