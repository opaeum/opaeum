/**
 */
package org.opaeum.uim.cube.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.cube.ColumnAxisEntry;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.MeasureProperty;
import org.opaeum.uim.cube.RowAxisEntry;
import org.opaeum.uim.impl.PageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryImpl#getColumnAxis <em>Column Axis</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryImpl#getRowAxis <em>Row Axis</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryImpl#getMeasures <em>Measures</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CubeQueryImpl extends PageImpl implements CubeQuery {
	/**
	 * The cached value of the '{@link #getColumnAxis() <em>Column Axis</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnAxis()
	 * @generated
	 * @ordered
	 */
	protected EList<ColumnAxisEntry> columnAxis;

	/**
	 * The cached value of the '{@link #getRowAxis() <em>Row Axis</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRowAxis()
	 * @generated
	 * @ordered
	 */
	protected EList<RowAxisEntry> rowAxis;

	/**
	 * The cached value of the '{@link #getMeasures() <em>Measures</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasures()
	 * @generated
	 * @ordered
	 */
	protected EList<MeasureProperty> measures;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CubeQueryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CubePackage.Literals.CUBE_QUERY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ColumnAxisEntry> getColumnAxis() {
		if (columnAxis == null) {
			columnAxis = new EObjectContainmentEList<ColumnAxisEntry>(ColumnAxisEntry.class, this, CubePackage.CUBE_QUERY__COLUMN_AXIS);
		}
		return columnAxis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RowAxisEntry> getRowAxis() {
		if (rowAxis == null) {
			rowAxis = new EObjectContainmentEList<RowAxisEntry>(RowAxisEntry.class, this, CubePackage.CUBE_QUERY__ROW_AXIS);
		}
		return rowAxis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MeasureProperty> getMeasures() {
		if (measures == null) {
			measures = new EObjectContainmentEList<MeasureProperty>(MeasureProperty.class, this, CubePackage.CUBE_QUERY__MEASURES);
		}
		return measures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				return ((InternalEList<?>)getColumnAxis()).basicRemove(otherEnd, msgs);
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				return ((InternalEList<?>)getRowAxis()).basicRemove(otherEnd, msgs);
			case CubePackage.CUBE_QUERY__MEASURES:
				return ((InternalEList<?>)getMeasures()).basicRemove(otherEnd, msgs);
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
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				return getColumnAxis();
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				return getRowAxis();
			case CubePackage.CUBE_QUERY__MEASURES:
				return getMeasures();
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
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				getColumnAxis().clear();
				getColumnAxis().addAll((Collection<? extends ColumnAxisEntry>)newValue);
				return;
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				getRowAxis().clear();
				getRowAxis().addAll((Collection<? extends RowAxisEntry>)newValue);
				return;
			case CubePackage.CUBE_QUERY__MEASURES:
				getMeasures().clear();
				getMeasures().addAll((Collection<? extends MeasureProperty>)newValue);
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
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				getColumnAxis().clear();
				return;
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				getRowAxis().clear();
				return;
			case CubePackage.CUBE_QUERY__MEASURES:
				getMeasures().clear();
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
			case CubePackage.CUBE_QUERY__COLUMN_AXIS:
				return columnAxis != null && !columnAxis.isEmpty();
			case CubePackage.CUBE_QUERY__ROW_AXIS:
				return rowAxis != null && !rowAxis.isEmpty();
			case CubePackage.CUBE_QUERY__MEASURES:
				return measures != null && !measures.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CubeQueryImpl
