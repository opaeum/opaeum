/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.internal.impl.SlotImpl;

import org.opaeum.metamodels.simulation.simulation.NumericValueDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulatingSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;
import org.opaeum.metamodels.simulation.simulation.SimulationStrategy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simulating Slot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatingSlotImpl#getSizeDistribution <em>Size Distribution</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatingSlotImpl#getSimulationStrategy <em>Simulation Strategy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SimulatingSlotImpl extends SlotImpl implements SimulatingSlot {
	/**
	 * The cached value of the '{@link #getSizeDistribution() <em>Size Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeDistribution()
	 * @generated
	 * @ordered
	 */
	protected NumericValueDistribution sizeDistribution;

	/**
	 * The default value of the '{@link #getSimulationStrategy() <em>Simulation Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final SimulationStrategy SIMULATION_STRATEGY_EDEFAULT = SimulationStrategy.GIVEN_VALUE;

	/**
	 * The cached value of the '{@link #getSimulationStrategy() <em>Simulation Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationStrategy()
	 * @generated
	 * @ordered
	 */
	protected SimulationStrategy simulationStrategy = SIMULATION_STRATEGY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimulatingSlotImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.SIMULATING_SLOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumericValueDistribution getSizeDistribution() {
		return sizeDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSizeDistribution(NumericValueDistribution newSizeDistribution, NotificationChain msgs) {
		NumericValueDistribution oldSizeDistribution = sizeDistribution;
		sizeDistribution = newSizeDistribution;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION, oldSizeDistribution, newSizeDistribution);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSizeDistribution(NumericValueDistribution newSizeDistribution) {
		if (newSizeDistribution != sizeDistribution) {
			NotificationChain msgs = null;
			if (sizeDistribution != null)
				msgs = ((InternalEObject)sizeDistribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION, null, msgs);
			if (newSizeDistribution != null)
				msgs = ((InternalEObject)newSizeDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION, null, msgs);
			msgs = basicSetSizeDistribution(newSizeDistribution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION, newSizeDistribution, newSizeDistribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationStrategy getSimulationStrategy() {
		return simulationStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulationStrategy(SimulationStrategy newSimulationStrategy) {
		SimulationStrategy oldSimulationStrategy = simulationStrategy;
		simulationStrategy = newSimulationStrategy == null ? SIMULATION_STRATEGY_EDEFAULT : newSimulationStrategy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATING_SLOT__SIMULATION_STRATEGY, oldSimulationStrategy, simulationStrategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION:
				return basicSetSizeDistribution(null, msgs);
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
			case SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION:
				return getSizeDistribution();
			case SimulationPackage.SIMULATING_SLOT__SIMULATION_STRATEGY:
				return getSimulationStrategy();
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
			case SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION:
				setSizeDistribution((NumericValueDistribution)newValue);
				return;
			case SimulationPackage.SIMULATING_SLOT__SIMULATION_STRATEGY:
				setSimulationStrategy((SimulationStrategy)newValue);
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
			case SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION:
				setSizeDistribution((NumericValueDistribution)null);
				return;
			case SimulationPackage.SIMULATING_SLOT__SIMULATION_STRATEGY:
				setSimulationStrategy(SIMULATION_STRATEGY_EDEFAULT);
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
			case SimulationPackage.SIMULATING_SLOT__SIZE_DISTRIBUTION:
				return sizeDistribution != null;
			case SimulationPackage.SIMULATING_SLOT__SIMULATION_STRATEGY:
				return simulationStrategy != SIMULATION_STRATEGY_EDEFAULT;
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
		result.append(" (simulationStrategy: ");
		result.append(simulationStrategy);
		result.append(')');
		return result.toString();
	}

} //SimulatingSlotImpl
