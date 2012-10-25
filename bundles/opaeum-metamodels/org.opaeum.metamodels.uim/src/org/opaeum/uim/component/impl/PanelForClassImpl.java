/**
 */
package org.opaeum.uim.component.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.DetailComponent;
import org.opaeum.uim.component.PanelForClass;
import org.opaeum.uim.impl.UmlReferenceImpl;
import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Panel For Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.impl.PanelForClassImpl#getDetailComponent <em>Detail Component</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.PanelForClassImpl#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PanelForClassImpl extends UmlReferenceImpl implements PanelForClass {
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
	protected PanelForClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.PANEL_FOR_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DetailComponent getDetailComponent() {
		if (eContainerFeatureID() != ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT) return null;
		return (DetailComponent)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDetailComponent(DetailComponent newDetailComponent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDetailComponent, ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDetailComponent(DetailComponent newDetailComponent) {
		if (newDetailComponent != eInternalContainer() || (eContainerFeatureID() != ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT && newDetailComponent != null)) {
			if (EcoreUtil.isAncestor(this, newDetailComponent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDetailComponent != null)
				msgs = ((InternalEObject)newDetailComponent).eInverseAdd(this, ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES, DetailComponent.class, msgs);
			msgs = basicSetDetailComponent(newDetailComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT, newDetailComponent, newDetailComponent));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentPackage.PANEL_FOR_CLASS__PANEL, oldPanel, panel));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.PANEL_FOR_CLASS__PANEL, oldPanel, panel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT:
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
			case ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT:
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
			case ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT:
				return eInternalContainer().eInverseRemove(this, ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES, DetailComponent.class, msgs);
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
			case ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT:
				return getDetailComponent();
			case ComponentPackage.PANEL_FOR_CLASS__PANEL:
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
			case ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT:
				setDetailComponent((DetailComponent)newValue);
				return;
			case ComponentPackage.PANEL_FOR_CLASS__PANEL:
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
			case ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT:
				setDetailComponent((DetailComponent)null);
				return;
			case ComponentPackage.PANEL_FOR_CLASS__PANEL:
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
			case ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT:
				return getDetailComponent() != null;
			case ComponentPackage.PANEL_FOR_CLASS__PANEL:
				return panel != null;
		}
		return super.eIsSet(featureID);
	}

} //PanelForClassImpl
