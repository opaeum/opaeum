/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.DetailComponent;
import org.opaeum.uim.PanelClass;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Panel Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.PanelClassImpl#getDetailComponent <em>Detail Component</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.PanelClassImpl#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PanelClassImpl extends UmlReferenceImpl implements PanelClass {
	/**
	 * The cached value of the '{@link #getPanel() <em>Panel</em>}' reference.
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
	protected PanelClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.PANEL_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DetailComponent getDetailComponent() {
		if (eContainerFeatureID() != UimPackage.PANEL_CLASS__DETAIL_COMPONENT) return null;
		return (DetailComponent)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDetailComponent(DetailComponent newDetailComponent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDetailComponent, UimPackage.PANEL_CLASS__DETAIL_COMPONENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDetailComponent(DetailComponent newDetailComponent) {
		if (newDetailComponent != eInternalContainer() || (eContainerFeatureID() != UimPackage.PANEL_CLASS__DETAIL_COMPONENT && newDetailComponent != null)) {
			if (EcoreUtil.isAncestor(this, newDetailComponent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDetailComponent != null)
				msgs = ((InternalEObject)newDetailComponent).eInverseAdd(this, UimPackage.DETAIL_COMPONENT__PANEL_CLASSES, DetailComponent.class, msgs);
			msgs = basicSetDetailComponent(newDetailComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.PANEL_CLASS__DETAIL_COMPONENT, newDetailComponent, newDetailComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractPanel getPanel() {
		if (panel != null && panel.eIsProxy()) {
			InternalEObject oldPanel = (InternalEObject)panel;
			panel = (AbstractPanel)eResolveProxy(oldPanel);
			if (panel != oldPanel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UimPackage.PANEL_CLASS__PANEL, oldPanel, panel));
			}
		}
		return panel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractPanel basicGetPanel() {
		return panel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanel(AbstractPanel newPanel) {
		AbstractPanel oldPanel = panel;
		panel = newPanel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.PANEL_CLASS__PANEL, oldPanel, panel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UimPackage.PANEL_CLASS__DETAIL_COMPONENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDetailComponent((DetailComponent)otherEnd, msgs);
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
			case UimPackage.PANEL_CLASS__DETAIL_COMPONENT:
				return basicSetDetailComponent(null, msgs);
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
			case UimPackage.PANEL_CLASS__DETAIL_COMPONENT:
				return eInternalContainer().eInverseRemove(this, UimPackage.DETAIL_COMPONENT__PANEL_CLASSES, DetailComponent.class, msgs);
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
			case UimPackage.PANEL_CLASS__DETAIL_COMPONENT:
				return getDetailComponent();
			case UimPackage.PANEL_CLASS__PANEL:
				if (resolve) return getPanel();
				return basicGetPanel();
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
			case UimPackage.PANEL_CLASS__DETAIL_COMPONENT:
				setDetailComponent((DetailComponent)newValue);
				return;
			case UimPackage.PANEL_CLASS__PANEL:
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
			case UimPackage.PANEL_CLASS__DETAIL_COMPONENT:
				setDetailComponent((DetailComponent)null);
				return;
			case UimPackage.PANEL_CLASS__PANEL:
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
			case UimPackage.PANEL_CLASS__DETAIL_COMPONENT:
				return getDetailComponent() != null;
			case UimPackage.PANEL_CLASS__PANEL:
				return panel != null;
		}
		return super.eIsSet(featureID);
	}

} //PanelClassImpl
