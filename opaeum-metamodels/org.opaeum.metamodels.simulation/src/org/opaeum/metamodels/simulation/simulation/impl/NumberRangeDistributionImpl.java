/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Number Range Distribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.NumberRangeDistributionImpl#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.NumberRangeDistributionImpl#getLowerValue <em>Lower Value</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.NumberRangeDistributionImpl#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NumberRangeDistributionImpl extends NumericValueDistributionImpl implements NumberRangeDistribution {
	/**
	 * The default value of the '{@link #getUpperValue() <em>Upper Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperValue()
	 * @generated
	 * @ordered
	 */
	protected static final Double UPPER_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUpperValue() <em>Upper Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperValue()
	 * @generated
	 * @ordered
	 */
	protected Double upperValue = UPPER_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLowerValue() <em>Lower Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerValue()
	 * @generated
	 * @ordered
	 */
	protected static final Double LOWER_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLowerValue() <em>Lower Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerValue()
	 * @generated
	 * @ordered
	 */
	protected Double lowerValue = LOWER_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected static final Double WEIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected Double weight = WEIGHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NumberRangeDistributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.NUMBER_RANGE_DISTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getUpperValue() {
		return upperValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperValue(Double newUpperValue) {
		Double oldUpperValue = upperValue;
		upperValue = newUpperValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE, oldUpperValue, upperValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getLowerValue() {
		return lowerValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerValue(Double newLowerValue) {
		Double oldLowerValue = lowerValue;
		lowerValue = newLowerValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE, oldLowerValue, lowerValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeight(Double newWeight) {
		Double oldWeight = weight;
		weight = newWeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.NUMBER_RANGE_DISTRIBUTION__WEIGHT, oldWeight, weight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE:
				return getUpperValue();
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE:
				return getLowerValue();
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__WEIGHT:
				return getWeight();
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
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE:
				setUpperValue((Double)newValue);
				return;
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE:
				setLowerValue((Double)newValue);
				return;
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__WEIGHT:
				setWeight((Double)newValue);
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
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE:
				setUpperValue(UPPER_VALUE_EDEFAULT);
				return;
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE:
				setLowerValue(LOWER_VALUE_EDEFAULT);
				return;
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__WEIGHT:
				setWeight(WEIGHT_EDEFAULT);
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
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE:
				return UPPER_VALUE_EDEFAULT == null ? upperValue != null : !UPPER_VALUE_EDEFAULT.equals(upperValue);
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE:
				return LOWER_VALUE_EDEFAULT == null ? lowerValue != null : !LOWER_VALUE_EDEFAULT.equals(lowerValue);
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION__WEIGHT:
				return WEIGHT_EDEFAULT == null ? weight != null : !WEIGHT_EDEFAULT.equals(weight);
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
		result.append(" (upperValue: ");
		result.append(upperValue);
		result.append(", lowerValue: ");
		result.append(lowerValue);
		result.append(", weight: ");
		result.append(weight);
		result.append(')');
		return result.toString();
	}

} //NumberRangeDistributionImpl
