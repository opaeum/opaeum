/**
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
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.DetailPanel;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.TableBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getDetailPanels <em>Detail Panels</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getBinding <em>Binding</em>}</li>
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
			detailPanels = new EObjectWithInverseResolvingEList<DetailPanel>(DetailPanel.class, this, UimPackage.UIM_DATA_TABLE__DETAIL_PANELS, UimPackage.DETAIL_PANEL__MASTER_COMPONENT);
		}
		return detailPanels;
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UimPackage.UIM_DATA_TABLE__DETAIL_PANELS:
				return getDetailPanels();
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
			case UimPackage.UIM_DATA_TABLE__BINDING:
				return binding != null;
		}
		return super.eIsSet(featureID);
	}

} //UimDataTableImpl
