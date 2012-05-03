/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.internal.impl.LiteralStringImpl;

import org.opaeum.metamodels.simulation.simulation.LiteralSimpleType;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Literal Simple Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.LiteralSimpleTypeImpl#getStringValue <em>String Value</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.LiteralSimpleTypeImpl#getRuntimeStrategyFactory <em>Runtime Strategy Factory</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LiteralSimpleTypeImpl extends LiteralStringImpl implements LiteralSimpleType {
	/**
	 * The default value of the '{@link #getStringValue() <em>String Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringValue()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringValue() <em>String Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringValue()
	 * @generated
	 * @ordered
	 */
	protected String stringValue = STRING_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRuntimeStrategyFactory() <em>Runtime Strategy Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeStrategyFactory()
	 * @generated
	 * @ordered
	 */
	protected static final String RUNTIME_STRATEGY_FACTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRuntimeStrategyFactory() <em>Runtime Strategy Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRuntimeStrategyFactory()
	 * @generated
	 * @ordered
	 */
	protected String runtimeStrategyFactory = RUNTIME_STRATEGY_FACTORY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LiteralSimpleTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.LITERAL_SIMPLE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStringValue() {
		return stringValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringValue(String newStringValue) {
		String oldStringValue = stringValue;
		stringValue = newStringValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.LITERAL_SIMPLE_TYPE__STRING_VALUE, oldStringValue, stringValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRuntimeStrategyFactory() {
		return runtimeStrategyFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRuntimeStrategyFactory(String newRuntimeStrategyFactory) {
		String oldRuntimeStrategyFactory = runtimeStrategyFactory;
		runtimeStrategyFactory = newRuntimeStrategyFactory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.LITERAL_SIMPLE_TYPE__RUNTIME_STRATEGY_FACTORY, oldRuntimeStrategyFactory, runtimeStrategyFactory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimulationPackage.LITERAL_SIMPLE_TYPE__STRING_VALUE:
				return getStringValue();
			case SimulationPackage.LITERAL_SIMPLE_TYPE__RUNTIME_STRATEGY_FACTORY:
				return getRuntimeStrategyFactory();
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
			case SimulationPackage.LITERAL_SIMPLE_TYPE__STRING_VALUE:
				setStringValue((String)newValue);
				return;
			case SimulationPackage.LITERAL_SIMPLE_TYPE__RUNTIME_STRATEGY_FACTORY:
				setRuntimeStrategyFactory((String)newValue);
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
			case SimulationPackage.LITERAL_SIMPLE_TYPE__STRING_VALUE:
				setStringValue(STRING_VALUE_EDEFAULT);
				return;
			case SimulationPackage.LITERAL_SIMPLE_TYPE__RUNTIME_STRATEGY_FACTORY:
				setRuntimeStrategyFactory(RUNTIME_STRATEGY_FACTORY_EDEFAULT);
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
			case SimulationPackage.LITERAL_SIMPLE_TYPE__STRING_VALUE:
				return STRING_VALUE_EDEFAULT == null ? stringValue != null : !STRING_VALUE_EDEFAULT.equals(stringValue);
			case SimulationPackage.LITERAL_SIMPLE_TYPE__RUNTIME_STRATEGY_FACTORY:
				return RUNTIME_STRATEGY_FACTORY_EDEFAULT == null ? runtimeStrategyFactory != null : !RUNTIME_STRATEGY_FACTORY_EDEFAULT.equals(runtimeStrategyFactory);
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
		result.append(" (stringValue: ");
		result.append(stringValue);
		result.append(", runtimeStrategyFactory: ");
		result.append(runtimeStrategyFactory);
		result.append(')');
		return result.toString();
	}

} //LiteralSimpleTypeImpl
