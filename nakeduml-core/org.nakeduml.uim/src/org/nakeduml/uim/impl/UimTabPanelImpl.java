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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.UimTab;
import org.nakeduml.uim.UimTabPanel;
import org.nakeduml.uim.layout.LayoutPackage;
import org.nakeduml.uim.layout.OutlayableComponent;
import org.nakeduml.uim.layout.UimLayout;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tab Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UimTabPanelImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UimTabPanelImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.UimTabPanelImpl#getActiveTabIndex <em>Active Tab Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimTabPanelImpl extends UimContainerImpl implements UimTabPanel {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<UimTab> children;

	/**
	 * The default value of the '{@link #getActiveTabIndex() <em>Active Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveTabIndex()
	 * @generated
	 * @ordered
	 */
	protected static final Integer ACTIVE_TAB_INDEX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActiveTabIndex() <em>Active Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveTabIndex()
	 * @generated
	 * @ordered
	 */
	protected Integer activeTabIndex = ACTIVE_TAB_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimTabPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.UIM_TAB_PANEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLayout getParent() {
		if (eContainerFeatureID() != UimPackage.UIM_TAB_PANEL__PARENT) return null;
		return (UimLayout)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(UimLayout newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, UimPackage.UIM_TAB_PANEL__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(UimLayout newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != UimPackage.UIM_TAB_PANEL__PARENT && newParent != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_TAB_PANEL__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UimTab> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<UimTab>(UimTab.class, this, UimPackage.UIM_TAB_PANEL__CHILDREN, UimPackage.UIM_TAB__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getActiveTabIndex() {
		return activeTabIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveTabIndex(Integer newActiveTabIndex) {
		Integer oldActiveTabIndex = activeTabIndex;
		activeTabIndex = newActiveTabIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX, oldActiveTabIndex, activeTabIndex));
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
			case UimPackage.UIM_TAB_PANEL__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((UimLayout)otherEnd, msgs);
			case UimPackage.UIM_TAB_PANEL__CHILDREN:
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
			case UimPackage.UIM_TAB_PANEL__PARENT:
				return basicSetParent(null, msgs);
			case UimPackage.UIM_TAB_PANEL__CHILDREN:
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case UimPackage.UIM_TAB_PANEL__PARENT:
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
			case UimPackage.UIM_TAB_PANEL__PARENT:
				return getParent();
			case UimPackage.UIM_TAB_PANEL__CHILDREN:
				return getChildren();
			case UimPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX:
				return getActiveTabIndex();
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
			case UimPackage.UIM_TAB_PANEL__PARENT:
				setParent((UimLayout)newValue);
				return;
			case UimPackage.UIM_TAB_PANEL__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends UimTab>)newValue);
				return;
			case UimPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX:
				setActiveTabIndex((Integer)newValue);
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
			case UimPackage.UIM_TAB_PANEL__PARENT:
				setParent((UimLayout)null);
				return;
			case UimPackage.UIM_TAB_PANEL__CHILDREN:
				getChildren().clear();
				return;
			case UimPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX:
				setActiveTabIndex(ACTIVE_TAB_INDEX_EDEFAULT);
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
			case UimPackage.UIM_TAB_PANEL__PARENT:
				return getParent() != null;
			case UimPackage.UIM_TAB_PANEL__CHILDREN:
				return children != null && !children.isEmpty();
			case UimPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX:
				return ACTIVE_TAB_INDEX_EDEFAULT == null ? activeTabIndex != null : !ACTIVE_TAB_INDEX_EDEFAULT.equals(activeTabIndex);
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
		if (baseClass == OutlayableComponent.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_TAB_PANEL__PARENT: return LayoutPackage.OUTLAYABLE_COMPONENT__PARENT;
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
		if (baseClass == OutlayableComponent.class) {
			switch (baseFeatureID) {
				case LayoutPackage.OUTLAYABLE_COMPONENT__PARENT: return UimPackage.UIM_TAB_PANEL__PARENT;
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
		result.append(" (activeTabIndex: ");
		result.append(activeTabIndex);
		result.append(')');
		return result.toString();
	}

} //UimTabPanelImpl
