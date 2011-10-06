/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opeum.uim.UimComponent;
import org.opeum.uim.UimField;
import org.opeum.uim.UimPackage;
import org.opeum.uim.UserInteractionElement;
import org.opeum.uim.binding.BindingPackage;
import org.opeum.uim.binding.FieldBinding;
import org.opeum.uim.control.ControlKind;
import org.opeum.uim.control.ControlPackage;
import org.opeum.uim.control.UimControl;
import org.opeum.uim.layout.LayoutPackage;
import org.opeum.uim.layout.OutlayableComponent;
import org.opeum.uim.layout.UimLayout;
import org.opeum.uim.security.impl.EditableSecureObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opeum.uim.impl.UimFieldImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimFieldImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimFieldImpl#getControl <em>Control</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimFieldImpl#getControlKind <em>Control Kind</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimFieldImpl#getLabelWidth <em>Label Width</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimFieldImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimFieldImpl extends EditableSecureObjectImpl implements UimField {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getControl() <em>Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControl()
	 * @generated
	 * @ordered
	 */
	protected UimControl control;

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
	protected static final Integer LABEL_WIDTH_EDEFAULT = new Integer(200);

	/**
	 * The cached value of the '{@link #getLabelWidth() <em>Label Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelWidth()
	 * @generated
	 * @ordered
	 */
	protected Integer labelWidth = LABEL_WIDTH_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.UIM_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_FIELD__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLayout getParent() {
		if (eContainerFeatureID() != UimPackage.UIM_FIELD__PARENT) return null;
		return (UimLayout)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(UimLayout newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, UimPackage.UIM_FIELD__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(UimLayout newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != UimPackage.UIM_FIELD__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, LayoutPackage.UIM_LAYOUT__CHILDREN, UimLayout.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_FIELD__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimControl getControl() {
		return control;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetControl(UimControl newControl, NotificationChain msgs) {
		UimControl oldControl = control;
		control = newControl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_FIELD__CONTROL, oldControl, newControl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControl(UimControl newControl) {
		if (newControl != control) {
			NotificationChain msgs = null;
			if (control != null)
				msgs = ((InternalEObject)control).eInverseRemove(this, ControlPackage.UIM_CONTROL__FIELD, UimControl.class, msgs);
			if (newControl != null)
				msgs = ((InternalEObject)newControl).eInverseAdd(this, ControlPackage.UIM_CONTROL__FIELD, UimControl.class, msgs);
			msgs = basicSetControl(newControl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_FIELD__CONTROL, newControl, newControl));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_FIELD__CONTROL_KIND, oldControlKind, controlKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLabelWidth() {
		return labelWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelWidth(Integer newLabelWidth) {
		Integer oldLabelWidth = labelWidth;
		labelWidth = newLabelWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_FIELD__LABEL_WIDTH, oldLabelWidth, labelWidth));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_FIELD__BINDING, oldBinding, newBinding);
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
				msgs = ((InternalEObject)binding).eInverseRemove(this, BindingPackage.FIELD_BINDING__FIELD, FieldBinding.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, BindingPackage.FIELD_BINDING__FIELD, FieldBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_FIELD__BINDING, newBinding, newBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UimPackage.UIM_FIELD__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((UimLayout)otherEnd, msgs);
			case UimPackage.UIM_FIELD__CONTROL:
				if (control != null)
					msgs = ((InternalEObject)control).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_FIELD__CONTROL, null, msgs);
				return basicSetControl((UimControl)otherEnd, msgs);
			case UimPackage.UIM_FIELD__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_FIELD__BINDING, null, msgs);
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
			case UimPackage.UIM_FIELD__PARENT:
				return basicSetParent(null, msgs);
			case UimPackage.UIM_FIELD__CONTROL:
				return basicSetControl(null, msgs);
			case UimPackage.UIM_FIELD__BINDING:
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case UimPackage.UIM_FIELD__PARENT:
				return eInternalContainer().eInverseRemove(this, LayoutPackage.UIM_LAYOUT__CHILDREN, UimLayout.class, msgs);
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
			case UimPackage.UIM_FIELD__NAME:
				return getName();
			case UimPackage.UIM_FIELD__PARENT:
				return getParent();
			case UimPackage.UIM_FIELD__CONTROL:
				return getControl();
			case UimPackage.UIM_FIELD__CONTROL_KIND:
				return getControlKind();
			case UimPackage.UIM_FIELD__LABEL_WIDTH:
				return getLabelWidth();
			case UimPackage.UIM_FIELD__BINDING:
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
			case UimPackage.UIM_FIELD__NAME:
				setName((String)newValue);
				return;
			case UimPackage.UIM_FIELD__PARENT:
				setParent((UimLayout)newValue);
				return;
			case UimPackage.UIM_FIELD__CONTROL:
				setControl((UimControl)newValue);
				return;
			case UimPackage.UIM_FIELD__CONTROL_KIND:
				setControlKind((ControlKind)newValue);
				return;
			case UimPackage.UIM_FIELD__LABEL_WIDTH:
				setLabelWidth((Integer)newValue);
				return;
			case UimPackage.UIM_FIELD__BINDING:
				setBinding((FieldBinding)newValue);
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
			case UimPackage.UIM_FIELD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UimPackage.UIM_FIELD__PARENT:
				setParent((UimLayout)null);
				return;
			case UimPackage.UIM_FIELD__CONTROL:
				setControl((UimControl)null);
				return;
			case UimPackage.UIM_FIELD__CONTROL_KIND:
				setControlKind(CONTROL_KIND_EDEFAULT);
				return;
			case UimPackage.UIM_FIELD__LABEL_WIDTH:
				setLabelWidth(LABEL_WIDTH_EDEFAULT);
				return;
			case UimPackage.UIM_FIELD__BINDING:
				setBinding((FieldBinding)null);
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
			case UimPackage.UIM_FIELD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UimPackage.UIM_FIELD__PARENT:
				return getParent() != null;
			case UimPackage.UIM_FIELD__CONTROL:
				return control != null;
			case UimPackage.UIM_FIELD__CONTROL_KIND:
				return controlKind != CONTROL_KIND_EDEFAULT;
			case UimPackage.UIM_FIELD__LABEL_WIDTH:
				return LABEL_WIDTH_EDEFAULT == null ? labelWidth != null : !LABEL_WIDTH_EDEFAULT.equals(labelWidth);
			case UimPackage.UIM_FIELD__BINDING:
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
		if (baseClass == UserInteractionElement.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_FIELD__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == OutlayableComponent.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_FIELD__PARENT: return LayoutPackage.OUTLAYABLE_COMPONENT__PARENT;
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
		if (baseClass == UserInteractionElement.class) {
			switch (baseFeatureID) {
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return UimPackage.UIM_FIELD__NAME;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == OutlayableComponent.class) {
			switch (baseFeatureID) {
				case LayoutPackage.OUTLAYABLE_COMPONENT__PARENT: return UimPackage.UIM_FIELD__PARENT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", controlKind: ");
		result.append(controlKind);
		result.append(", labelWidth: ");
		result.append(labelWidth);
		result.append(')');
		return result.toString();
	}

} //UimFieldImpl
