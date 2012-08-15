/**
 */
package org.opaeum.uim.panel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.PanelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grid Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.panel.impl.GridPanelImpl#getNumberOfColumns <em>Number Of Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GridPanelImpl extends CollapsiblePanelImpl implements GridPanel {
	/**
	 * The default value of the '{@link #getNumberOfColumns() <em>Number Of Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfColumns()
	 * @generated NOT
	 * @ordered
	 */
	protected static final Integer NUMBER_OF_COLUMNS_EDEFAULT=2; // TODO The default value literal "" is not valid.
	/**
	 * The cached value of the '{@link #getNumberOfColumns() <em>Number Of Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfColumns()
	 * @generated
	 * @ordered
	 */
	protected Integer numberOfColumns = NUMBER_OF_COLUMNS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GridPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PanelPackage.Literals.GRID_PANEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfColumns(Integer newNumberOfColumns) {
		Integer oldNumberOfColumns = numberOfColumns;
		numberOfColumns = newNumberOfColumns;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PanelPackage.GRID_PANEL__NUMBER_OF_COLUMNS, oldNumberOfColumns, numberOfColumns));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PanelPackage.GRID_PANEL__NUMBER_OF_COLUMNS:
				return getNumberOfColumns();
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
			case PanelPackage.GRID_PANEL__NUMBER_OF_COLUMNS:
				setNumberOfColumns((Integer)newValue);
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
			case PanelPackage.GRID_PANEL__NUMBER_OF_COLUMNS:
				setNumberOfColumns(NUMBER_OF_COLUMNS_EDEFAULT);
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
			case PanelPackage.GRID_PANEL__NUMBER_OF_COLUMNS:
				return NUMBER_OF_COLUMNS_EDEFAULT == null ? numberOfColumns != null : !NUMBER_OF_COLUMNS_EDEFAULT.equals(numberOfColumns);
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (numberOfColumns: ");
		result.append(numberOfColumns);
		result.append(')');
		return result.toString();
	}

} //GridPanelImpl
