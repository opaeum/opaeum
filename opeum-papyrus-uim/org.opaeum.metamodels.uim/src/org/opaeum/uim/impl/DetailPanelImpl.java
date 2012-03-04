/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.opaeum.uim.DetailPanel;
import org.opaeum.uim.MasterComponent;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Detail Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.DetailPanelImpl#getMasterComponent <em>Master Component</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.DetailPanelImpl#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DetailPanelImpl extends EObjectImpl implements DetailPanel {
	/**
	 * The cached value of the '{@link #getMasterComponent() <em>Master Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMasterComponent()
	 * @generated
	 * @ordered
	 */
	protected MasterComponent masterComponent;

	/**
	 * The cached value of the '{@link #getPanel() <em>Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanel()
	 * @generated
	 * @ordered
	 */
	protected AbstractPanel panel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DetailPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.DETAIL_PANEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterComponent getMasterComponent() {
		if (masterComponent != null && masterComponent.eIsProxy()) {
			InternalEObject oldMasterComponent = (InternalEObject)masterComponent;
			masterComponent = (MasterComponent)eResolveProxy(oldMasterComponent);
			if (masterComponent != oldMasterComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UimPackage.DETAIL_PANEL__MASTER_COMPONENT, oldMasterComponent, masterComponent));
			}
		}
		return masterComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterComponent basicGetMasterComponent() {
		return masterComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMasterComponent(MasterComponent newMasterComponent, NotificationChain msgs) {
		MasterComponent oldMasterComponent = masterComponent;
		masterComponent = newMasterComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.DETAIL_PANEL__MASTER_COMPONENT, oldMasterComponent, newMasterComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMasterComponent(MasterComponent newMasterComponent) {
		if (newMasterComponent != masterComponent) {
			NotificationChain msgs = null;
			if (masterComponent != null)
				msgs = ((InternalEObject)masterComponent).eInverseRemove(this, UimPackage.MASTER_COMPONENT__DETAIL_PANELS, MasterComponent.class, msgs);
			if (newMasterComponent != null)
				msgs = ((InternalEObject)newMasterComponent).eInverseAdd(this, UimPackage.MASTER_COMPONENT__DETAIL_PANELS, MasterComponent.class, msgs);
			msgs = basicSetMasterComponent(newMasterComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.DETAIL_PANEL__MASTER_COMPONENT, newMasterComponent, newMasterComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractPanel getPanel() {
		return panel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPanel(AbstractPanel newPanel, NotificationChain msgs) {
		AbstractPanel oldPanel = panel;
		panel = newPanel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.DETAIL_PANEL__PANEL, oldPanel, newPanel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanel(AbstractPanel newPanel) {
		if (newPanel != panel) {
			NotificationChain msgs = null;
			if (panel != null)
				msgs = ((InternalEObject)panel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.DETAIL_PANEL__PANEL, null, msgs);
			if (newPanel != null)
				msgs = ((InternalEObject)newPanel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.DETAIL_PANEL__PANEL, null, msgs);
			msgs = basicSetPanel(newPanel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.DETAIL_PANEL__PANEL, newPanel, newPanel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UimPackage.DETAIL_PANEL__MASTER_COMPONENT:
				if (masterComponent != null)
					msgs = ((InternalEObject)masterComponent).eInverseRemove(this, UimPackage.MASTER_COMPONENT__DETAIL_PANELS, MasterComponent.class, msgs);
				return basicSetMasterComponent((MasterComponent)otherEnd, msgs);
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
			case UimPackage.DETAIL_PANEL__MASTER_COMPONENT:
				return basicSetMasterComponent(null, msgs);
			case UimPackage.DETAIL_PANEL__PANEL:
				return basicSetPanel(null, msgs);
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
			case UimPackage.DETAIL_PANEL__MASTER_COMPONENT:
				if (resolve) return getMasterComponent();
				return basicGetMasterComponent();
			case UimPackage.DETAIL_PANEL__PANEL:
				return getPanel();
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
			case UimPackage.DETAIL_PANEL__MASTER_COMPONENT:
				setMasterComponent((MasterComponent)newValue);
				return;
			case UimPackage.DETAIL_PANEL__PANEL:
				setPanel((AbstractPanel)newValue);
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
			case UimPackage.DETAIL_PANEL__MASTER_COMPONENT:
				setMasterComponent((MasterComponent)null);
				return;
			case UimPackage.DETAIL_PANEL__PANEL:
				setPanel((AbstractPanel)null);
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
			case UimPackage.DETAIL_PANEL__MASTER_COMPONENT:
				return masterComponent != null;
			case UimPackage.DETAIL_PANEL__PANEL:
				return panel != null;
		}
		return super.eIsSet(featureID);
	}

} //DetailPanelImpl
