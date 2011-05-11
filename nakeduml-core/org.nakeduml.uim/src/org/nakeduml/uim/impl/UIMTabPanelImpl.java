/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.UIMTabPanel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tab Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.UIMTabPanelImpl#getActiveTabIndex <em>Active Tab Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UIMTabPanelImpl extends UIMContainerImpl implements UIMTabPanel {
	/**
	 * The default value of the '{@link #getActiveTabIndex() <em>Active Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveTabIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int ACTIVE_TAB_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getActiveTabIndex() <em>Active Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveTabIndex()
	 * @generated
	 * @ordered
	 */
	protected int activeTabIndex = ACTIVE_TAB_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UIMTabPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.UIM_TAB_PANEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getActiveTabIndex() {
		return activeTabIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveTabIndex(int newActiveTabIndex) {
		int oldActiveTabIndex = activeTabIndex;
		activeTabIndex = newActiveTabIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX, oldActiveTabIndex, activeTabIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UIMPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX:
				return getActiveTabIndex();
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
			case UIMPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX:
				setActiveTabIndex((Integer)newValue);
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
			case UIMPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX:
				setActiveTabIndex(ACTIVE_TAB_INDEX_EDEFAULT);
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
			case UIMPackage.UIM_TAB_PANEL__ACTIVE_TAB_INDEX:
				return activeTabIndex != ACTIVE_TAB_INDEX_EDEFAULT;
		}
		return super.eIsSet(featureID);
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
		result.append(" (activeTabIndex: ");
		result.append(activeTabIndex);
		result.append(')');
		return result.toString();
	}

} //UIMTabPanelImpl
