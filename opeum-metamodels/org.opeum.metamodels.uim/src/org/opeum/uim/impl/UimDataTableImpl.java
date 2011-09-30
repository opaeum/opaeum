/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opeum.uim.UimComponent;
import org.opeum.uim.UimContainer;
import org.opeum.uim.UimDataTable;
import org.opeum.uim.UimPackage;
import org.opeum.uim.UserInteractionElement;
import org.opeum.uim.binding.BindingPackage;
import org.opeum.uim.binding.TableBinding;
import org.opeum.uim.form.DetailPanel;
import org.opeum.uim.form.FormPackage;
import org.opeum.uim.layout.LayoutContainer;
import org.opeum.uim.layout.LayoutPackage;
import org.opeum.uim.layout.OutlayableComponent;
import org.opeum.uim.layout.UimLayout;
import org.opeum.uim.security.EditableSecureObject;
import org.opeum.uim.security.SecureObject;
import org.opeum.uim.security.SecurityConstraint;
import org.opeum.uim.security.SecurityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opeum.uim.impl.UimDataTableImpl#getDetailPanels <em>Detail Panels</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimDataTableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimDataTableImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimDataTableImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimDataTableImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimDataTableImpl#getLayout <em>Layout</em>}</li>
 *   <li>{@link org.opeum.uim.impl.UimDataTableImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimDataTableImpl extends EObjectImpl implements UimDataTable {
	/**
	 * The cached value of the '{@link #getDetailPanels() <em>Detail Panels</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetailPanels()
	 * @generated
	 * @ordered
	 */
	protected EList<DetailPanel> detailPanels;

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
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected TableBinding binding;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimDataTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.UIM_DATA_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DetailPanel> getDetailPanels() {
		if (detailPanels == null) {
			detailPanels = new EObjectWithInverseResolvingEList<DetailPanel>(DetailPanel.class, this, UimPackage.UIM_DATA_TABLE__DETAIL_PANELS, FormPackage.DETAIL_PANEL__MASTER_COMPONENT);
		}
		return detailPanels;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__VISIBILITY, oldVisibility, newVisibility);
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
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLayout getParent() {
		if (eContainerFeatureID() != UimPackage.UIM_DATA_TABLE__PARENT) return null;
		return (UimLayout)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(UimLayout newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, UimPackage.UIM_DATA_TABLE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(UimLayout newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != UimPackage.UIM_DATA_TABLE__PARENT && newParent != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__PARENT, newParent, newParent));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__EDITABILITY, oldEditability, newEditability);
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
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__EDITABILITY, newEditability, newEditability));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__LAYOUT, oldLayout, newLayout);
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__LAYOUT, newLayout, newLayout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableBinding getBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(TableBinding newBinding, NotificationChain msgs) {
		TableBinding oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__BINDING, oldBinding, newBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(TableBinding newBinding) {
		if (newBinding != binding) {
			NotificationChain msgs = null;
			if (binding != null)
				msgs = ((InternalEObject)binding).eInverseRemove(this, BindingPackage.TABLE_BINDING__TABLE, TableBinding.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, BindingPackage.TABLE_BINDING__TABLE, TableBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__BINDING, newBinding, newBinding));
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
			case UimPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDetailPanels()).basicAdd(otherEnd, msgs);
			case UimPackage.UIM_DATA_TABLE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((UimLayout)otherEnd, msgs);
			case UimPackage.UIM_DATA_TABLE__LAYOUT:
				if (layout != null)
					msgs = ((InternalEObject)layout).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__LAYOUT, null, msgs);
				return basicSetLayout((UimLayout)otherEnd, msgs);
			case UimPackage.UIM_DATA_TABLE__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__BINDING, null, msgs);
				return basicSetBinding((TableBinding)otherEnd, msgs);
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
			case UimPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return ((InternalEList<?>)getDetailPanels()).basicRemove(otherEnd, msgs);
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case UimPackage.UIM_DATA_TABLE__PARENT:
				return basicSetParent(null, msgs);
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return basicSetEditability(null, msgs);
			case UimPackage.UIM_DATA_TABLE__LAYOUT:
				return basicSetLayout(null, msgs);
			case UimPackage.UIM_DATA_TABLE__BINDING:
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
			case UimPackage.UIM_DATA_TABLE__PARENT:
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
			case UimPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return getDetailPanels();
			case UimPackage.UIM_DATA_TABLE__NAME:
				return getName();
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return getVisibility();
			case UimPackage.UIM_DATA_TABLE__PARENT:
				return getParent();
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return getEditability();
			case UimPackage.UIM_DATA_TABLE__LAYOUT:
				return getLayout();
			case UimPackage.UIM_DATA_TABLE__BINDING:
				return getBinding();
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
			case UimPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				getDetailPanels().clear();
				getDetailPanels().addAll((Collection<? extends DetailPanel>)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__NAME:
				setName((String)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				setVisibility((SecurityConstraint)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__PARENT:
				setParent((UimLayout)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				setEditability((SecurityConstraint)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__LAYOUT:
				setLayout((UimLayout)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__BINDING:
				setBinding((TableBinding)newValue);
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
			case UimPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				getDetailPanels().clear();
				return;
			case UimPackage.UIM_DATA_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				setVisibility((SecurityConstraint)null);
				return;
			case UimPackage.UIM_DATA_TABLE__PARENT:
				setParent((UimLayout)null);
				return;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				setEditability((SecurityConstraint)null);
				return;
			case UimPackage.UIM_DATA_TABLE__LAYOUT:
				setLayout((UimLayout)null);
				return;
			case UimPackage.UIM_DATA_TABLE__BINDING:
				setBinding((TableBinding)null);
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
			case UimPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return detailPanels != null && !detailPanels.isEmpty();
			case UimPackage.UIM_DATA_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return visibility != null;
			case UimPackage.UIM_DATA_TABLE__PARENT:
				return getParent() != null;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return editability != null;
			case UimPackage.UIM_DATA_TABLE__LAYOUT:
				return layout != null;
			case UimPackage.UIM_DATA_TABLE__BINDING:
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
				case UimPackage.UIM_DATA_TABLE__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == SecureObject.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__VISIBILITY: return SecurityPackage.SECURE_OBJECT__VISIBILITY;
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
				case UimPackage.UIM_DATA_TABLE__PARENT: return LayoutPackage.OUTLAYABLE_COMPONENT__PARENT;
				default: return -1;
			}
		}
		if (baseClass == EditableSecureObject.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__EDITABILITY: return SecurityPackage.EDITABLE_SECURE_OBJECT__EDITABILITY;
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
				case UimPackage.UIM_DATA_TABLE__LAYOUT: return LayoutPackage.LAYOUT_CONTAINER__LAYOUT;
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
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return UimPackage.UIM_DATA_TABLE__NAME;
				default: return -1;
			}
		}
		if (baseClass == SecureObject.class) {
			switch (baseFeatureID) {
				case SecurityPackage.SECURE_OBJECT__VISIBILITY: return UimPackage.UIM_DATA_TABLE__VISIBILITY;
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
				case LayoutPackage.OUTLAYABLE_COMPONENT__PARENT: return UimPackage.UIM_DATA_TABLE__PARENT;
				default: return -1;
			}
		}
		if (baseClass == EditableSecureObject.class) {
			switch (baseFeatureID) {
				case SecurityPackage.EDITABLE_SECURE_OBJECT__EDITABILITY: return UimPackage.UIM_DATA_TABLE__EDITABILITY;
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
				case LayoutPackage.LAYOUT_CONTAINER__LAYOUT: return UimPackage.UIM_DATA_TABLE__LAYOUT;
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

} //UimDataTableImpl
