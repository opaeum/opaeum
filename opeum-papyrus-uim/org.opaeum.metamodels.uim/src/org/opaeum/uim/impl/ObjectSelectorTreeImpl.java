/**
 */
package org.opaeum.uim.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.DetailPanel;
import org.opaeum.uim.ObjectSelectorTree;
import org.opaeum.uim.UimPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Selector Tree</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl#getDetailPanels <em>Detail Panels</em>}</li>
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
			detailPanels = new EObjectWithInverseResolvingEList<DetailPanel>(DetailPanel.class, this, UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS, UimPackage.DETAIL_PANEL__MASTER_COMPONENT);
		}
		return detailPanels;
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
			case UimPackage.OBJECT_SELECTOR_TREE__DETAIL_PANELS:
				return getDetailPanels();
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
		}
		return super.eIsSet(featureID);
	}

} //ObjectSelectorTreeImpl
