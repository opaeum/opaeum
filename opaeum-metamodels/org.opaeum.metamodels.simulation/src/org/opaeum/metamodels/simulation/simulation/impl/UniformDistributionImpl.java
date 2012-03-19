/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.opaeum.metamodels.simulation.simulation.SimulationPackage;
import org.opaeum.metamodels.simulation.simulation.UniformDistribution;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uniform Distribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.UniformDistributionImpl#getLowerLimit <em>Lower Limit</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.UniformDistributionImpl#getUpperLimit <em>Upper Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniformDistributionImpl extends NumericValueDistributionImpl implements UniformDistribution {
	/**
	 * The default value of the '{@link #getLowerLimit() <em>Lower Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerLimit()
	 * @generated
	 * @ordered
	 */
	protected static final Double LOWER_LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLowerLimit() <em>Lower Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerLimit()
	 * @generated
	 * @ordered
	 */
	protected Double lowerLimit = LOWER_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperLimit() <em>Upper Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperLimit()
	 * @generated
	 * @ordered
	 */
	protected static final Double UPPER_LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUpperLimit() <em>Upper Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperLimit()
	 * @generated
	 * @ordered
	 */
	protected Double upperLimit = UPPER_LIMIT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UniformDistributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.UNIFORM_DISTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerLimit(Double newLowerLimit) {
		Double oldLowerLimit = lowerLimit;
		lowerLimit = newLowerLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.UNIFORM_DISTRIBUTION__LOWER_LIMIT, oldLowerLimit, lowerLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getUpperLimit() {
		return upperLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpperLimit(Double newUpperLimit) {
		Double oldUpperLimit = upperLimit;
		upperLimit = newUpperLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.UNIFORM_DISTRIBUTION__UPPER_LIMIT, oldUpperLimit, upperLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimulationPackage.UNIFORM_DISTRIBUTION__LOWER_LIMIT:
				return getLowerLimit();
			case SimulationPackage.UNIFORM_DISTRIBUTION__UPPER_LIMIT:
				return getUpperLimit();
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
			case SimulationPackage.UNIFORM_DISTRIBUTION__LOWER_LIMIT:
				setLowerLimit((Double)newValue);
				return;
			case SimulationPackage.UNIFORM_DISTRIBUTION__UPPER_LIMIT:
				setUpperLimit((Double)newValue);
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
			case SimulationPackage.UNIFORM_DISTRIBUTION__LOWER_LIMIT:
				setLowerLimit(LOWER_LIMIT_EDEFAULT);
				return;
			case SimulationPackage.UNIFORM_DISTRIBUTION__UPPER_LIMIT:
				setUpperLimit(UPPER_LIMIT_EDEFAULT);
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
			case SimulationPackage.UNIFORM_DISTRIBUTION__LOWER_LIMIT:
				return LOWER_LIMIT_EDEFAULT == null ? lowerLimit != null : !LOWER_LIMIT_EDEFAULT.equals(lowerLimit);
			case SimulationPackage.UNIFORM_DISTRIBUTION__UPPER_LIMIT:
				return UPPER_LIMIT_EDEFAULT == null ? upperLimit != null : !UPPER_LIMIT_EDEFAULT.equals(upperLimit);
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
		result.append(" (lowerLimit: ");
		result.append(lowerLimit);
		result.append(", upperLimit: ");
		result.append(upperLimit);
		result.append(')');
		return result.toString();
	}

} //UniformDistributionImpl
