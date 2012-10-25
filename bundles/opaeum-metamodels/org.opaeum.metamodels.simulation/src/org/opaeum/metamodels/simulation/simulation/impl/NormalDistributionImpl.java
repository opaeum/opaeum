/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.metamodels.simulation.simulation.NormalDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Normal Distribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.NormalDistributionImpl#getMean <em>Mean</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.NormalDistributionImpl#getStandardDeviation <em>Standard Deviation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NormalDistributionImpl extends NumericValueDistributionImpl implements NormalDistribution {
	/**
	 * The default value of the '{@link #getMean() <em>Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMean()
	 * @generated
	 * @ordered
	 */
	protected static final Double MEAN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMean() <em>Mean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMean()
	 * @generated
	 * @ordered
	 */
	protected Double mean = MEAN_EDEFAULT;

	/**
	 * The default value of the '{@link #getStandardDeviation() <em>Standard Deviation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardDeviation()
	 * @generated
	 * @ordered
	 */
	protected static final Double STANDARD_DEVIATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStandardDeviation() <em>Standard Deviation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStandardDeviation()
	 * @generated
	 * @ordered
	 */
	protected Double standardDeviation = STANDARD_DEVIATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NormalDistributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.NORMAL_DISTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getMean() {
		return mean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMean(Double newMean) {
		Double oldMean = mean;
		mean = newMean;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.NORMAL_DISTRIBUTION__MEAN, oldMean, mean));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getStandardDeviation() {
		return standardDeviation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStandardDeviation(Double newStandardDeviation) {
		Double oldStandardDeviation = standardDeviation;
		standardDeviation = newStandardDeviation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.NORMAL_DISTRIBUTION__STANDARD_DEVIATION, oldStandardDeviation, standardDeviation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimulationPackage.NORMAL_DISTRIBUTION__MEAN:
				return getMean();
			case SimulationPackage.NORMAL_DISTRIBUTION__STANDARD_DEVIATION:
				return getStandardDeviation();
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
			case SimulationPackage.NORMAL_DISTRIBUTION__MEAN:
				setMean((Double)newValue);
				return;
			case SimulationPackage.NORMAL_DISTRIBUTION__STANDARD_DEVIATION:
				setStandardDeviation((Double)newValue);
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
			case SimulationPackage.NORMAL_DISTRIBUTION__MEAN:
				setMean(MEAN_EDEFAULT);
				return;
			case SimulationPackage.NORMAL_DISTRIBUTION__STANDARD_DEVIATION:
				setStandardDeviation(STANDARD_DEVIATION_EDEFAULT);
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
			case SimulationPackage.NORMAL_DISTRIBUTION__MEAN:
				return MEAN_EDEFAULT == null ? mean != null : !MEAN_EDEFAULT.equals(mean);
			case SimulationPackage.NORMAL_DISTRIBUTION__STANDARD_DEVIATION:
				return STANDARD_DEVIATION_EDEFAULT == null ? standardDeviation != null : !STANDARD_DEVIATION_EDEFAULT.equals(standardDeviation);
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
		result.append(" (mean: ");
		result.append(mean);
		result.append(", standardDeviation: ");
		result.append(standardDeviation);
		result.append(')');
		return result.toString();
	}

} //NormalDistributionImpl
