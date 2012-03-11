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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.DetailComponent;
import org.opaeum.uim.MasterComponent;
import org.opaeum.uim.PanelClass;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Detail Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.DetailComponentImpl#getMasterComponent <em>Master Component</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.DetailComponentImpl#getPanelClasses <em>Panel Classes</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.DetailComponentImpl#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DetailComponentImpl extends UimComponentImpl implements DetailComponent {
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
	 * The cached value of the '{@link #getPanelClasses() <em>Panel Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanelClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<PanelClass> panelClasses;

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
	protected DetailComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.DETAIL_COMPONENT;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT, oldMasterComponent, masterComponent));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT, oldMasterComponent, newMasterComponent);
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT, newMasterComponent, newMasterComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PanelClass> getPanelClasses() {
		if (panelClasses == null) {
			panelClasses = new EObjectContainmentWithInverseEList<PanelClass>(PanelClass.class, this, UimPackage.DETAIL_COMPONENT__PANEL_CLASSES, UimPackage.PANEL_CLASS__DETAIL_COMPONENT);
		}
		return panelClasses;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.DETAIL_COMPONENT__PANEL, oldPanel, newPanel);
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
				msgs = ((InternalEObject)panel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.DETAIL_COMPONENT__PANEL, null, msgs);
			if (newPanel != null)
				msgs = ((InternalEObject)newPanel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.DETAIL_COMPONENT__PANEL, null, msgs);
			msgs = basicSetPanel(newPanel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.DETAIL_COMPONENT__PANEL, newPanel, newPanel));
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
			case UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				if (masterComponent != null)
					msgs = ((InternalEObject)masterComponent).eInverseRemove(this, UimPackage.MASTER_COMPONENT__DETAIL_PANELS, MasterComponent.class, msgs);
				return basicSetMasterComponent((MasterComponent)otherEnd, msgs);
			case UimPackage.DETAIL_COMPONENT__PANEL_CLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPanelClasses()).basicAdd(otherEnd, msgs);
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
			case UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				return basicSetMasterComponent(null, msgs);
			case UimPackage.DETAIL_COMPONENT__PANEL_CLASSES:
				return ((InternalEList<?>)getPanelClasses()).basicRemove(otherEnd, msgs);
			case UimPackage.DETAIL_COMPONENT__PANEL:
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
			case UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				if (resolve) return getMasterComponent();
				return basicGetMasterComponent();
			case UimPackage.DETAIL_COMPONENT__PANEL_CLASSES:
				return getPanelClasses();
			case UimPackage.DETAIL_COMPONENT__PANEL:
				return getPanel();
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
			case UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				setMasterComponent((MasterComponent)newValue);
				return;
			case UimPackage.DETAIL_COMPONENT__PANEL_CLASSES:
				getPanelClasses().clear();
				getPanelClasses().addAll((Collection<? extends PanelClass>)newValue);
				return;
			case UimPackage.DETAIL_COMPONENT__PANEL:
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
			case UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				setMasterComponent((MasterComponent)null);
				return;
			case UimPackage.DETAIL_COMPONENT__PANEL_CLASSES:
				getPanelClasses().clear();
				return;
			case UimPackage.DETAIL_COMPONENT__PANEL:
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
			case UimPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				return masterComponent != null;
			case UimPackage.DETAIL_COMPONENT__PANEL_CLASSES:
				return panelClasses != null && !panelClasses.isEmpty();
			case UimPackage.DETAIL_COMPONENT__PANEL:
				return panel != null;
		}
		return super.eIsSet(featureID);
	}

} //DetailComponentImpl
