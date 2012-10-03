/**
 */
package org.opaeum.uim.component.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.DetailComponent;
import org.opaeum.uim.component.MasterComponent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Master Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.impl.MasterComponentImpl#getDetailComponents <em>Detail Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MasterComponentImpl extends EObjectImpl implements MasterComponent {
	/**
	 * The cached value of the '{@link #getDetailComponents() <em>Detail Components</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetailComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<DetailComponent> detailComponents;

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
		return ComponentPackage.Literals.MASTER_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DetailComponent> getDetailComponents() {
		if (detailComponents == null) {
			detailComponents = new EObjectWithInverseResolvingEList<DetailComponent>(DetailComponent.class, this, ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS, ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT);
		}
		return detailComponents;
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
			case ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDetailComponents()).basicAdd(otherEnd, msgs);
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
			case ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS:
				return ((InternalEList<?>)getDetailComponents()).basicRemove(otherEnd, msgs);
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
			case ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS:
				return getDetailComponents();
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
			case ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS:
				getDetailComponents().clear();
				getDetailComponents().addAll((Collection<? extends DetailComponent>)newValue);
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
			case ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS:
				getDetailComponents().clear();
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
			case ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS:
				return detailComponents != null && !detailComponents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MasterComponentImpl
