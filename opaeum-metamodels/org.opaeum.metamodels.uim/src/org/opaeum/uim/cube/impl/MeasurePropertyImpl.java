/**
 */
package org.opaeum.uim.cube.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.opaeum.uim.cube.AggregationFormula;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.MeasureProperty;

import org.opaeum.uim.impl.UmlReferenceImpl;
import org.opaeum.uim.impl.UserInteractionElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measure Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.impl.MeasurePropertyImpl#getAggregationFormula <em>Aggregation Formula</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurePropertyImpl extends UmlReferenceImpl implements MeasureProperty {
	/**
	 * The default value of the '{@link #getAggregationFormula() <em>Aggregation Formula</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregationFormula()
	 * @generated
	 * @ordered
	 */
	protected static final AggregationFormula AGGREGATION_FORMULA_EDEFAULT = AggregationFormula.SUM;

	/**
	 * The cached value of the '{@link #getAggregationFormula() <em>Aggregation Formula</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregationFormula()
	 * @generated
	 * @ordered
	 */
	protected AggregationFormula aggregationFormula = AGGREGATION_FORMULA_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurePropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CubePackage.Literals.MEASURE_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AggregationFormula getAggregationFormula() {
		return aggregationFormula;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setAggregationFormula(AggregationFormula newAggregationFormula) {
		AggregationFormula oldAggregationFormula = aggregationFormula;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.MEASURE_PROPERTY__AGGREGATION_FORMULA, oldAggregationFormula, aggregationFormula));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean equals(Object o) {
		return toString().equals(o.toString());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CubePackage.MEASURE_PROPERTY__AGGREGATION_FORMULA:
				return getAggregationFormula();
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
			case CubePackage.MEASURE_PROPERTY__AGGREGATION_FORMULA:
				setAggregationFormula((AggregationFormula)newValue);
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
			case CubePackage.MEASURE_PROPERTY__AGGREGATION_FORMULA:
				setAggregationFormula(AGGREGATION_FORMULA_EDEFAULT);
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
			case CubePackage.MEASURE_PROPERTY__AGGREGATION_FORMULA:
				return aggregationFormula != AGGREGATION_FORMULA_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return getUmlElementUid() + getAggregationFormula();
	}

} //MeasurePropertyImpl
