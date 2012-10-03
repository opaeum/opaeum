/**
 */
package org.opaeum.uim.component.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.DetailComponent;
import org.opaeum.uim.component.MasterComponent;
import org.opaeum.uim.component.PanelForClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Detail Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.impl.DetailComponentImpl#getMasterComponent <em>Master Component</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.DetailComponentImpl#getPanelsForClasses <em>Panels For Classes</em>}</li>
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
	 * The cached value of the '{@link #getPanelsForClasses() <em>Panels For Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanelsForClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<PanelForClass> panelsForClasses;

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
		return ComponentPackage.Literals.DETAIL_COMPONENT;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT, oldMasterComponent, masterComponent));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT, oldMasterComponent, newMasterComponent);
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
				msgs = ((InternalEObject)masterComponent).eInverseRemove(this, ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS, MasterComponent.class, msgs);
			if (newMasterComponent != null)
				msgs = ((InternalEObject)newMasterComponent).eInverseAdd(this, ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS, MasterComponent.class, msgs);
			msgs = basicSetMasterComponent(newMasterComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT, newMasterComponent, newMasterComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PanelForClass> getPanelsForClasses() {
		if (panelsForClasses == null) {
			panelsForClasses = new EObjectContainmentWithInverseEList<PanelForClass>(PanelForClass.class, this, ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES, ComponentPackage.PANEL_FOR_CLASS__DETAIL_COMPONENT);
		}
		return panelsForClasses;
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
			case ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				if (masterComponent != null)
					msgs = ((InternalEObject)masterComponent).eInverseRemove(this, ComponentPackage.MASTER_COMPONENT__DETAIL_COMPONENTS, MasterComponent.class, msgs);
				return basicSetMasterComponent((MasterComponent)otherEnd, msgs);
			case ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPanelsForClasses()).basicAdd(otherEnd, msgs);
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
			case ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				return basicSetMasterComponent(null, msgs);
			case ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES:
				return ((InternalEList<?>)getPanelsForClasses()).basicRemove(otherEnd, msgs);
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
			case ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				if (resolve) return getMasterComponent();
				return basicGetMasterComponent();
			case ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES:
				return getPanelsForClasses();
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
			case ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				setMasterComponent((MasterComponent)newValue);
				return;
			case ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES:
				getPanelsForClasses().clear();
				getPanelsForClasses().addAll((Collection<? extends PanelForClass>)newValue);
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
			case ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				setMasterComponent((MasterComponent)null);
				return;
			case ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES:
				getPanelsForClasses().clear();
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
			case ComponentPackage.DETAIL_COMPONENT__MASTER_COMPONENT:
				return masterComponent != null;
			case ComponentPackage.DETAIL_COMPONENT__PANELS_FOR_CLASSES:
				return panelsForClasses != null && !panelsForClasses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DetailComponentImpl
