/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.ContainedActualInstance;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contained Actual Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedActualInstanceImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedActualInstanceImpl#getContainedInstance <em>Contained Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainedActualInstanceImpl extends SimulatedValueImpl implements ContainedActualInstance {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<InstanceSpecification> values;

	/**
	 * The cached value of the '{@link #getContainedInstance() <em>Contained Instance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedInstance()
	 * @generated
	 * @ordered
	 */
	protected ActualInstance containedInstance;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainedActualInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.CONTAINED_ACTUAL_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InstanceSpecification> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<InstanceSpecification>(InstanceSpecification.class, this, SimulationPackage.CONTAINED_ACTUAL_INSTANCE__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActualInstance getContainedInstance() {
		return containedInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainedInstance(ActualInstance newContainedInstance, NotificationChain msgs) {
		ActualInstance oldContainedInstance = containedInstance;
		containedInstance = newContainedInstance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE, oldContainedInstance, newContainedInstance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainedInstance(ActualInstance newContainedInstance) {
		if (newContainedInstance != containedInstance) {
			NotificationChain msgs = null;
			if (containedInstance != null)
				msgs = ((InternalEObject)containedInstance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE, null, msgs);
			if (newContainedInstance != null)
				msgs = ((InternalEObject)newContainedInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE, null, msgs);
			msgs = basicSetContainedInstance(newContainedInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE, newContainedInstance, newContainedInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE:
				return basicSetContainedInstance(null, msgs);
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
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__VALUES:
				return getValues();
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE:
				return getContainedInstance();
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
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends InstanceSpecification>)newValue);
				return;
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE:
				setContainedInstance((ActualInstance)newValue);
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
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__VALUES:
				getValues().clear();
				return;
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE:
				setContainedInstance((ActualInstance)null);
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
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__VALUES:
				return values != null && !values.isEmpty();
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE:
				return containedInstance != null;
		}
		return super.eIsSet(featureID);
	}

} //ContainedActualInstanceImpl
