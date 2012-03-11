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
import org.opaeum.uim.DetailComponent;
import org.opaeum.uim.MasterComponent;
import org.opaeum.uim.UimPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Master Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.MasterComponentImpl#getDetailPanels <em>Detail Panels</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MasterComponentImpl extends EObjectImpl implements MasterComponent {
	/**
	 * The cached value of the '{@link #getDetailPanels() <em>Detail Panels</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetailPanels()
	 * @generated
	 * @ordered
	 */
	protected EList<DetailComponent> detailPanels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MasterComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.MASTER_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DetailComponent> getDetailPanels() {
		if (detailPanels == null) {
			detailPanels = new EObjectWithInverseResolvingEList<DetailComponent>(DetailComponent.class, this, UimPackage.MASTER_COMPONENT__DETAIL_PANELS, UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT);
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
			case UimPackage.MASTER_COMPONENT__DETAIL_PANELS:
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
			case UimPackage.MASTER_COMPONENT__DETAIL_PANELS:
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
			case UimPackage.MASTER_COMPONENT__DETAIL_PANELS:
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
			case UimPackage.MASTER_COMPONENT__DETAIL_PANELS:
				getDetailPanels().clear();
				getDetailPanels().addAll((Collection<? extends DetailComponent>)newValue);
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
			case UimPackage.MASTER_COMPONENT__DETAIL_PANELS:
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
			case UimPackage.MASTER_COMPONENT__DETAIL_PANELS:
				return detailPanels != null && !detailPanels.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MasterComponentImpl
