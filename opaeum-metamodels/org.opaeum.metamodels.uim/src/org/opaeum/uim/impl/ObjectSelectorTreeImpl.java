/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.impl;

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
import org.opaeum.uim.ObjectSelectorTree;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.form.DetailPanel;
import org.opaeum.uim.form.FormPackage;
import org.opaeum.uim.layout.LayoutPackage;
import org.opaeum.uim.layout.OutlayableComponent;
import org.opaeum.uim.layout.UimLayout;
import org.opaeum.uim.security.SecureObject;
import org.opaeum.uim.security.SecurityConstraint;
import org.opaeum.uim.security.SecurityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Selector Tree</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl#getDetailPanels <em>Detail Panels</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ObjectSelectorTreeImpl extends EObjectImpl implements ObjectSelectorTree {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectSelectorTreeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.OBJECT_SELECTOR_TREE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DetailPanel> getDetailPanels() {
		if (detailPanels == null) {
			detailPanels = new EObjectWithInverseResolvingEList<DetailPanel>(DetailPanel.class, this, UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS, FormPackage.DETAIL_PANEL__MASTER_COMPONENT);
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.OBJECT_SELECTOR_TREE__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY, oldVisibility, newVisibility);
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
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLayout getParent() {
		if (eContainerFeatureID() != UimPackage.OBJECT_SELECTOR_TREE__PARENT) return null;
		return (UimLayout)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(UimLayout newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, UimPackage.OBJECT_SELECTOR_TREE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(UimLayout newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != UimPackage.OBJECT_SELECTOR_TREE__PARENT && newParent != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.OBJECT_SELECTOR_TREE__PARENT, newParent, newParent));
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
			case UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDetailPanels()).basicAdd(otherEnd, msgs);
			case UimPackage.OBJECT_SELECTOR_TREE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((UimLayout)otherEnd, msgs);
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
			case UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS:
				return ((InternalEList<?>)getDetailPanels()).basicRemove(otherEnd, msgs);
			case UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case UimPackage.OBJECT_SELECTOR_TREE__PARENT:
				return basicSetParent(null, msgs);
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
			case UimPackage.OBJECT_SELECTOR_TREE__PARENT:
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
			case UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS:
				return getDetailPanels();
			case UimPackage.OBJECT_SELECTOR_TREE__NAME:
				return getName();
			case UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY:
				return getVisibility();
			case UimPackage.OBJECT_SELECTOR_TREE__PARENT:
				return getParent();
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
			case UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS:
				getDetailPanels().clear();
				getDetailPanels().addAll((Collection<? extends DetailPanel>)newValue);
				return;
			case UimPackage.OBJECT_SELECTOR_TREE__NAME:
				setName((String)newValue);
				return;
			case UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY:
				setVisibility((SecurityConstraint)newValue);
				return;
			case UimPackage.OBJECT_SELECTOR_TREE__PARENT:
				setParent((UimLayout)newValue);
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
			case UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS:
				getDetailPanels().clear();
				return;
			case UimPackage.OBJECT_SELECTOR_TREE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY:
				setVisibility((SecurityConstraint)null);
				return;
			case UimPackage.OBJECT_SELECTOR_TREE__PARENT:
				setParent((UimLayout)null);
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
			case UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS:
				return detailPanels != null && !detailPanels.isEmpty();
			case UimPackage.OBJECT_SELECTOR_TREE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY:
				return visibility != null;
			case UimPackage.OBJECT_SELECTOR_TREE__PARENT:
				return getParent() != null;
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
				case UimPackage.OBJECT_SELECTOR_TREE__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == SecureObject.class) {
			switch (derivedFeatureID) {
				case UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY: return SecurityPackage.SECURE_OBJECT__VISIBILITY;
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
				case UimPackage.OBJECT_SELECTOR_TREE__PARENT: return LayoutPackage.OUTLAYABLE_COMPONENT__PARENT;
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
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return UimPackage.OBJECT_SELECTOR_TREE__NAME;
				default: return -1;
			}
		}
		if (baseClass == SecureObject.class) {
			switch (baseFeatureID) {
				case SecurityPackage.SECURE_OBJECT__VISIBILITY: return UimPackage.OBJECT_SELECTOR_TREE__VISIBILITY;
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
				case LayoutPackage.OUTLAYABLE_COMPONENT__PARENT: return UimPackage.OBJECT_SELECTOR_TREE__PARENT;
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

} //ObjectSelectorTreeImpl
