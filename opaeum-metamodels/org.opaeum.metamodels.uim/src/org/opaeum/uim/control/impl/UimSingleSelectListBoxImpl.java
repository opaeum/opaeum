/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.control.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimSingleSelectListBox;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uim Single Select List Box</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.control.impl.UimSingleSelectListBoxImpl#getRows <em>Rows</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimSingleSelectListBoxImpl extends UimLookupImpl implements UimSingleSelectListBox {
	/**
	 * The default value of the '{@link #getRows() <em>Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRows()
	 * @generated
	 * @ordered
	 */
	protected static final Integer ROWS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRows() <em>Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRows()
	 * @generated
	 * @ordered
	 */
	protected Integer rows = ROWS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimSingleSelectListBoxImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlPackage.Literals.UIM_SINGLE_SELECT_LIST_BOX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getRows() {
		return rows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRows(Integer newRows) {
		Integer oldRows = rows;
		rows = newRows;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlPackage.UIM_SINGLE_SELECT_LIST_BOX__ROWS, oldRows, rows));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlPackage.UIM_SINGLE_SELECT_LIST_BOX__ROWS:
				return getRows();
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
			case ControlPackage.UIM_SINGLE_SELECT_LIST_BOX__ROWS:
				setRows((Integer)newValue);
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
			case ControlPackage.UIM_SINGLE_SELECT_LIST_BOX__ROWS:
				setRows(ROWS_EDEFAULT);
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
			case ControlPackage.UIM_SINGLE_SELECT_LIST_BOX__ROWS:
				return ROWS_EDEFAULT == null ? rows != null : !ROWS_EDEFAULT.equals(rows);
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
		result.append(" (rows: ");
		result.append(rows);
		result.append(')');
		return result.toString();
	}

} //UimSingleSelectListBoxImpl
