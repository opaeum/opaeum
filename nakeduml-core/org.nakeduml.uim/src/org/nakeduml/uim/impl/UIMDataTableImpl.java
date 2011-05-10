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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nakeduml.uim.ChildSecurityConstraint;
import org.nakeduml.uim.DetailPanel;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.UIMComponent;
import org.nakeduml.uim.UIMContainer;
import org.nakeduml.uim.UIMDataTable;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.UserInteractionElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UIMDataTableImpl#getDetailPanels <em>Detail Panels</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMDataTableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMDataTableImpl#getSecurityOnVisibility <em>Security On Visibility</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMDataTableImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMDataTableImpl#getSecurityOnEditability <em>Security On Editability</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMDataTableImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UIMDataTableImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UIMDataTableImpl extends EObjectImpl implements UIMDataTable {
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
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<UIMComponent> children;

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
	protected UIMDataTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.UIM_DATA_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DetailPanel> getDetailPanels() {
		if (detailPanels == null) {
			detailPanels = new EObjectWithInverseResolvingEList<DetailPanel>(DetailPanel.class, this, UIMPackage.UIM_DATA_TABLE__DETAIL_PANELS, UIMPackage.DETAIL_PANEL__MASTER_COMPONENT);
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
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_DATA_TABLE__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY, oldSecurityOnVisibility, newSecurityOnVisibility);
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
				msgs = ((InternalEObject)securityOnVisibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY, null, msgs);
			if (newSecurityOnVisibility != null)
				msgs = ((InternalEObject)newSecurityOnVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY, null, msgs);
			msgs = basicSetSecurityOnVisibility(newSecurityOnVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY, newSecurityOnVisibility, newSecurityOnVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMContainer getParent() {
		if (eContainerFeatureID() != UIMPackage.UIM_DATA_TABLE__PARENT) return null;
		return (UIMContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(UIMContainer newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, UIMPackage.UIM_DATA_TABLE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(UIMContainer newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != UIMPackage.UIM_DATA_TABLE__PARENT && newParent != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_DATA_TABLE__PARENT, newParent, newParent));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY, oldSecurityOnEditability, newSecurityOnEditability);
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
				msgs = ((InternalEObject)securityOnEditability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY, null, msgs);
			if (newSecurityOnEditability != null)
				msgs = ((InternalEObject)newSecurityOnEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY, null, msgs);
			msgs = basicSetSecurityOnEditability(newSecurityOnEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY, newSecurityOnEditability, newSecurityOnEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UIMComponent> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<UIMComponent>(UIMComponent.class, this, UIMPackage.UIM_DATA_TABLE__CHILDREN, UIMPackage.UIM_COMPONENT__PARENT);
		}
		return children;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_DATA_TABLE__BINDING, oldBinding, newBinding);
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
				msgs = ((InternalEObject)binding).eInverseRemove(this, UIMPackage.TABLE_BINDING__TABLE, TableBinding.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, UIMPackage.TABLE_BINDING__TABLE, TableBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_DATA_TABLE__BINDING, newBinding, newBinding));
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
			case UIMPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDetailPanels()).basicAdd(otherEnd, msgs);
			case UIMPackage.UIM_DATA_TABLE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((UIMContainer)otherEnd, msgs);
			case UIMPackage.UIM_DATA_TABLE__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case UIMPackage.UIM_DATA_TABLE__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIMPackage.UIM_DATA_TABLE__BINDING, null, msgs);
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
			case UIMPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return ((InternalEList<?>)getDetailPanels()).basicRemove(otherEnd, msgs);
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY:
				return basicSetSecurityOnVisibility(null, msgs);
			case UIMPackage.UIM_DATA_TABLE__PARENT:
				return basicSetParent(null, msgs);
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY:
				return basicSetSecurityOnEditability(null, msgs);
			case UIMPackage.UIM_DATA_TABLE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case UIMPackage.UIM_DATA_TABLE__BINDING:
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
			case UIMPackage.UIM_DATA_TABLE__PARENT:
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
			case UIMPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return getDetailPanels();
			case UIMPackage.UIM_DATA_TABLE__NAME:
				return getName();
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY:
				return getSecurityOnVisibility();
			case UIMPackage.UIM_DATA_TABLE__PARENT:
				return getParent();
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY:
				return getSecurityOnEditability();
			case UIMPackage.UIM_DATA_TABLE__CHILDREN:
				return getChildren();
			case UIMPackage.UIM_DATA_TABLE__BINDING:
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
			case UIMPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				getDetailPanels().clear();
				getDetailPanels().addAll((Collection<? extends DetailPanel>)newValue);
				return;
			case UIMPackage.UIM_DATA_TABLE__NAME:
				setName((String)newValue);
				return;
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY:
				setSecurityOnVisibility((ChildSecurityConstraint)newValue);
				return;
			case UIMPackage.UIM_DATA_TABLE__PARENT:
				setParent((UIMContainer)newValue);
				return;
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY:
				setSecurityOnEditability((ChildSecurityConstraint)newValue);
				return;
			case UIMPackage.UIM_DATA_TABLE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends UIMComponent>)newValue);
				return;
			case UIMPackage.UIM_DATA_TABLE__BINDING:
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
			case UIMPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				getDetailPanels().clear();
				return;
			case UIMPackage.UIM_DATA_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY:
				setSecurityOnVisibility((ChildSecurityConstraint)null);
				return;
			case UIMPackage.UIM_DATA_TABLE__PARENT:
				setParent((UIMContainer)null);
				return;
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY:
				setSecurityOnEditability((ChildSecurityConstraint)null);
				return;
			case UIMPackage.UIM_DATA_TABLE__CHILDREN:
				getChildren().clear();
				return;
			case UIMPackage.UIM_DATA_TABLE__BINDING:
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
			case UIMPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return detailPanels != null && !detailPanels.isEmpty();
			case UIMPackage.UIM_DATA_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY:
				return securityOnVisibility != null;
			case UIMPackage.UIM_DATA_TABLE__PARENT:
				return getParent() != null;
			case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY:
				return securityOnEditability != null;
			case UIMPackage.UIM_DATA_TABLE__CHILDREN:
				return children != null && !children.isEmpty();
			case UIMPackage.UIM_DATA_TABLE__BINDING:
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
				case UIMPackage.UIM_DATA_TABLE__NAME: return UIMPackage.USER_INTERACTION_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == UIMComponent.class) {
			switch (derivedFeatureID) {
				case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY: return UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY;
				case UIMPackage.UIM_DATA_TABLE__PARENT: return UIMPackage.UIM_COMPONENT__PARENT;
				default: return -1;
			}
		}
		if (baseClass == UIMContainer.class) {
			switch (derivedFeatureID) {
				case UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY: return UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY;
				case UIMPackage.UIM_DATA_TABLE__CHILDREN: return UIMPackage.UIM_CONTAINER__CHILDREN;
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
				case UIMPackage.USER_INTERACTION_ELEMENT__NAME: return UIMPackage.UIM_DATA_TABLE__NAME;
				default: return -1;
			}
		}
		if (baseClass == UIMComponent.class) {
			switch (baseFeatureID) {
				case UIMPackage.UIM_COMPONENT__SECURITY_ON_VISIBILITY: return UIMPackage.UIM_DATA_TABLE__SECURITY_ON_VISIBILITY;
				case UIMPackage.UIM_COMPONENT__PARENT: return UIMPackage.UIM_DATA_TABLE__PARENT;
				default: return -1;
			}
		}
		if (baseClass == UIMContainer.class) {
			switch (baseFeatureID) {
				case UIMPackage.UIM_CONTAINER__SECURITY_ON_EDITABILITY: return UIMPackage.UIM_DATA_TABLE__SECURITY_ON_EDITABILITY;
				case UIMPackage.UIM_CONTAINER__CHILDREN: return UIMPackage.UIM_DATA_TABLE__CHILDREN;
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

} //UIMDataTableImpl
