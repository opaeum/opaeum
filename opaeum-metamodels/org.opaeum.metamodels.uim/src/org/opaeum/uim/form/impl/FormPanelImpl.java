/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.form.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opeum.uim.UimComponent;
import org.opeum.uim.UimContainer;
import org.opeum.uim.UimPackage;
import org.opeum.uim.UserInteractionElement;
import org.opeum.uim.form.FormPackage;
import org.opeum.uim.form.FormPanel;
import org.opeum.uim.form.UimForm;
import org.opeum.uim.impl.UmlReferenceImpl;
import org.opeum.uim.layout.LayoutContainer;
import org.opeum.uim.layout.LayoutPackage;
import org.opeum.uim.layout.UimLayout;
import org.opeum.uim.security.EditableSecureObject;
import org.opeum.uim.security.SecureObject;
import org.opeum.uim.security.SecurityConstraint;
import org.opeum.uim.security.SecurityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opeum.uim.form.impl.FormPanelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opeum.uim.form.impl.FormPanelImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opeum.uim.form.impl.FormPanelImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opeum.uim.form.impl.FormPanelImpl#getLayout <em>Layout</em>}</li>
 *   <li>{@link org.opeum.uim.form.impl.FormPanelImpl#getForm <em>Form</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormPanelImpl extends UmlReferenceImpl implements FormPanel {
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
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected SecurityConstraint visibility;

	/**
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected SecurityConstraint editability;

	/**
	 * The cached value of the '{@link #getLayout() <em>Layout</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLayout()
	 * @generated
	 * @ordered
	 */
	protected UimLayout layout;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.FORM_PANEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_PANEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(SecurityConstraint newVisibility, NotificationChain msgs) {
		SecurityConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.FORM_PANEL__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(SecurityConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.FORM_PANEL__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.FORM_PANEL__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_PANEL__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(SecurityConstraint newEditability, NotificationChain msgs) {
		SecurityConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.FORM_PANEL__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(SecurityConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.FORM_PANEL__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormPackage.FORM_PANEL__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_PANEL__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLayout getLayout() {
		return layout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLayout(UimLayout newLayout, NotificationChain msgs) {
		UimLayout oldLayout = layout;
		layout = newLayout;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.FORM_PANEL__LAYOUT, oldLayout, newLayout);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLayout(UimLayout newLayout) {
		if (newLayout != layout) {
			NotificationChain msgs = null;
			if (layout != null)
				msgs = ((InternalEObject)layout).eInverseRemove(this, LayoutPackage.UIM_LAYOUT__PARENT, UimLayout.class, msgs);
			if (newLayout != null)
				msgs = ((InternalEObject)newLayout).eInverseAdd(this, LayoutPackage.UIM_LAYOUT__PARENT, UimLayout.class, msgs);
			msgs = basicSetLayout(newLayout, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_PANEL__LAYOUT, newLayout, newLayout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimForm getForm() {
		if (eContainerFeatureID() != FormPackage.FORM_PANEL__FORM) return null;
		return (UimForm)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetForm(UimForm newForm, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newForm, FormPackage.FORM_PANEL__FORM, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForm(UimForm newForm) {
		if (newForm != eInternalContainer() || (eContainerFeatureID() != FormPackage.FORM_PANEL__FORM && newForm != null)) {
			if (EcoreUtil.isAncestor(this, newForm))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newForm != null)
				msgs = ((InternalEObject)newForm).eInverseAdd(this, FormPackage.UIM_FORM__PANEL, UimForm.class, msgs);
			msgs = basicSetForm(newForm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.FORM_PANEL__FORM, newForm, newForm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimContainer getParent() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FormPackage.FORM_PANEL__LAYOUT:
				if (layout != null)
					msgs = ((InternalEObject)layout).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.FORM_PANEL__LAYOUT, null, msgs);
				return basicSetLayout((UimLayout)otherEnd, msgs);
			case FormPackage.FORM_PANEL__FORM:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetForm((UimForm)otherEnd, msgs);
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
			case FormPackage.FORM_PANEL__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case FormPackage.FORM_PANEL__EDITABILITY:
				return basicSetEditability(null, msgs);
			case FormPackage.FORM_PANEL__LAYOUT:
				return basicSetLayout(null, msgs);
			case FormPackage.FORM_PANEL__FORM:
				return basicSetForm(null, msgs);
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
			case FormPackage.FORM_PANEL__FORM:
				return eInternalContainer().eInverseRemove(this, FormPackage.UIM_FORM__PANEL, UimForm.class, msgs);
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
			case FormPackage.FORM_PANEL__NAME:
				return getName();
			case FormPackage.FORM_PANEL__VISIBILITY:
				return getVisibility();
			case FormPackage.FORM_PANEL__EDITABILITY:
				return getEditability();
			case FormPackage.FORM_PANEL__LAYOUT:
				return getLayout();
			case FormPackage.FORM_PANEL__FORM:
				return getForm();
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
			case FormPackage.FORM_PANEL__NAME:
				setName((String)newValue);
				return;
			case FormPackage.FORM_PANEL__VISIBILITY:
				setVisibility((SecurityConstraint)newValue);
				return;
			case FormPackage.FORM_PANEL__EDITABILITY:
				setEditability((SecurityConstraint)newValue);
				return;
			case FormPackage.FORM_PANEL__LAYOUT:
				setLayout((UimLayout)newValue);
				return;
			case FormPackage.FORM_PANEL__FORM:
				setForm((UimForm)newValue);
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
			case FormPackage.FORM_PANEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case FormPackage.FORM_PANEL__VISIBILITY:
				setVisibility((SecurityConstraint)null);
				return;
			case FormPackage.FORM_PANEL__EDITABILITY:
				setEditability((SecurityConstraint)null);
				return;
			case FormPackage.FORM_PANEL__LAYOUT:
				setLayout((UimLayout)null);
				return;
			case FormPackage.FORM_PANEL__FORM:
				setForm((UimForm)null);
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
			case FormPackage.FORM_PANEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case FormPackage.FORM_PANEL__VISIBILITY:
				return visibility != null;
			case FormPackage.FORM_PANEL__EDITABILITY:
				return editability != null;
			case FormPackage.FORM_PANEL__LAYOUT:
				return layout != null;
			case FormPackage.FORM_PANEL__FORM:
				return getForm() != null;
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
				case FormPackage.FORM_PANEL__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == SecureObject.class) {
			switch (derivedFeatureID) {
				case FormPackage.FORM_PANEL__VISIBILITY: return SecurityPackage.SECURE_OBJECT__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == EditableSecureObject.class) {
			switch (derivedFeatureID) {
				case FormPackage.FORM_PANEL__EDITABILITY: return SecurityPackage.EDITABLE_SECURE_OBJECT__EDITABILITY;
				default: return -1;
			}
		}
		if (baseClass == UimContainer.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == LayoutContainer.class) {
			switch (derivedFeatureID) {
				case FormPackage.FORM_PANEL__LAYOUT: return LayoutPackage.LAYOUT_CONTAINER__LAYOUT;
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
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return FormPackage.FORM_PANEL__NAME;
				default: return -1;
			}
		}
		if (baseClass == SecureObject.class) {
			switch (baseFeatureID) {
				case SecurityPackage.SECURE_OBJECT__VISIBILITY: return FormPackage.FORM_PANEL__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == EditableSecureObject.class) {
			switch (baseFeatureID) {
				case SecurityPackage.EDITABLE_SECURE_OBJECT__EDITABILITY: return FormPackage.FORM_PANEL__EDITABILITY;
				default: return -1;
			}
		}
		if (baseClass == UimContainer.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == LayoutContainer.class) {
			switch (baseFeatureID) {
				case LayoutPackage.LAYOUT_CONTAINER__LAYOUT: return FormPackage.FORM_PANEL__LAYOUT;
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
		result.append(')');
		return result.toString();
	}

} //FormPanelImpl
