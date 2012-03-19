/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.Property;

import org.eclipse.uml2.uml.internal.impl.SlotImpl;

import org.opaeum.metamodels.simulation.simulation.NumericValueDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulatedSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simulated Slot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatedSlotImpl#getSizeDistribution <em>Size Distribution</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatedSlotImpl#getProperty <em>Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SimulatedSlotImpl extends SlotImpl implements SimulatedSlot {
	/**
	 * The cached value of the '{@link #getSizeDistribution() <em>Size Distribution</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeDistribution()
	 * @generated
	 * @ordered
	 */
	protected NumericValueDistribution sizeDistribution;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected Property property;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimulatedSlotImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.SIMULATED_SLOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumericValueDistribution getSizeDistribution() {
		if (sizeDistribution != null && sizeDistribution.eIsProxy()) {
			InternalEObject oldSizeDistribution = (InternalEObject)sizeDistribution;
			sizeDistribution = (NumericValueDistribution)eResolveProxy(oldSizeDistribution);
			if (sizeDistribution != oldSizeDistribution) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimulationPackage.SIMULATED_SLOT__SIZE_DISTRIBUTION, oldSizeDistribution, sizeDistribution));
			}
		}
		return sizeDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumericValueDistribution basicGetSizeDistribution() {
		return sizeDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSizeDistribution(NumericValueDistribution newSizeDistribution) {
		NumericValueDistribution oldSizeDistribution = sizeDistribution;
		sizeDistribution = newSizeDistribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATED_SLOT__SIZE_DISTRIBUTION, oldSizeDistribution, sizeDistribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getProperty() {
		if (property != null && property.eIsProxy()) {
			InternalEObject oldProperty = (InternalEObject)property;
			property = (Property)eResolveProxy(oldProperty);
			if (property != oldProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimulationPackage.SIMULATED_SLOT__PROPERTY, oldProperty, property));
			}
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetProperty() {
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(Property newProperty) {
		Property oldProperty = property;
		property = newProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.SIMULATED_SLOT__PROPERTY, oldProperty, property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimulationPackage.SIMULATED_SLOT__SIZE_DISTRIBUTION:
				if (resolve) return getSizeDistribution();
				return basicGetSizeDistribution();
			case SimulationPackage.SIMULATED_SLOT__PROPERTY:
				if (resolve) return getProperty();
				return basicGetProperty();
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
			case SimulationPackage.SIMULATED_SLOT__SIZE_DISTRIBUTION:
				setSizeDistribution((NumericValueDistribution)newValue);
				return;
			case SimulationPackage.SIMULATED_SLOT__PROPERTY:
				setProperty((Property)newValue);
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
			case SimulationPackage.SIMULATED_SLOT__SIZE_DISTRIBUTION:
				setSizeDistribution((NumericValueDistribution)null);
				return;
			case SimulationPackage.SIMULATED_SLOT__PROPERTY:
				setProperty((Property)null);
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
			case SimulationPackage.SIMULATED_SLOT__SIZE_DISTRIBUTION:
				return sizeDistribution != null;
			case SimulationPackage.SIMULATED_SLOT__PROPERTY:
				return property != null;
		}
		return super.eIsSet(featureID);
	}

} //SimulatedSlotImpl
