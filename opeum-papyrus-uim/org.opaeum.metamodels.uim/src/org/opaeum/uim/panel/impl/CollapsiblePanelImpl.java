/**
 */
package org.opaeum.uim.panel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.panel.CollapsiblePanel;
import org.opaeum.uim.panel.PanelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collapsible Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.panel.impl.CollapsiblePanelImpl#getIsCollapsible <em>Is Collapsible</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollapsiblePanelImpl extends AbstractPanelImpl implements CollapsiblePanel {
	/**
	 * The default value of the '{@link #getIsCollapsible() <em>Is Collapsible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsCollapsible()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_COLLAPSIBLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsCollapsible() <em>Is Collapsible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsCollapsible()
	 * @generated
	 * @ordered
	 */
	protected Boolean isCollapsible = IS_COLLAPSIBLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollapsiblePanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PanelPackage.Literals.COLLAPSIBLE_PANEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsCollapsible() {
		return isCollapsible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsCollapsible(Boolean newIsCollapsible) {
		Boolean oldIsCollapsible = isCollapsible;
		isCollapsible = newIsCollapsible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PanelPackage.COLLAPSIBLE_PANEL__IS_COLLAPSIBLE, oldIsCollapsible, isCollapsible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PanelPackage.COLLAPSIBLE_PANEL__IS_COLLAPSIBLE:
				return getIsCollapsible();
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
			case PanelPackage.COLLAPSIBLE_PANEL__IS_COLLAPSIBLE:
				setIsCollapsible((Boolean)newValue);
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
			case PanelPackage.COLLAPSIBLE_PANEL__IS_COLLAPSIBLE:
				setIsCollapsible(IS_COLLAPSIBLE_EDEFAULT);
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
			case PanelPackage.COLLAPSIBLE_PANEL__IS_COLLAPSIBLE:
				return IS_COLLAPSIBLE_EDEFAULT == null ? isCollapsible != null : !IS_COLLAPSIBLE_EDEFAULT.equals(isCollapsible);
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
		result.append(" (isCollapsible: ");
		result.append(isCollapsible);
		result.append(')');
		return result.toString();
	}

} //CollapsiblePanelImpl
