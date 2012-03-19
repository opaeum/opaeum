/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DeployedArtifact;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.opaeum.metamodels.simulation.simulation.ActualInstanceSimulation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;

import org.eclipse.uml2.uml.util.UMLValidator;

import org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contained Instance Value Simulation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl#getContainedInstance <em>Contained Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainedInstanceValueSimulationImpl extends ValueSimulationImpl implements ContainedInstanceValueSimulation {
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
	protected ActualInstanceSimulation containedInstance;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainedInstanceValueSimulationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.CONTAINED_INSTANCE_VALUE_SIMULATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InstanceSpecification> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<InstanceSpecification>(InstanceSpecification.class, this, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActualInstanceSimulation getContainedInstance() {
		return containedInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainedInstance(ActualInstanceSimulation newContainedInstance, NotificationChain msgs) {
		ActualInstanceSimulation oldContainedInstance = containedInstance;
		containedInstance = newContainedInstance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE, oldContainedInstance, newContainedInstance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainedInstance(ActualInstanceSimulation newContainedInstance) {
		if (newContainedInstance != containedInstance) {
			NotificationChain msgs = null;
			if (containedInstance != null)
				msgs = ((InternalEObject)containedInstance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE, null, msgs);
			if (newContainedInstance != null)
				msgs = ((InternalEObject)newContainedInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE, null, msgs);
			msgs = basicSetContainedInstance(newContainedInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE, newContainedInstance, newContainedInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE:
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				return getValues();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE:
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends InstanceSpecification>)newValue);
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE:
				setContainedInstance((ActualInstanceSimulation)newValue);
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				getValues().clear();
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE:
				setContainedInstance((ActualInstanceSimulation)null);
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				return values != null && !values.isEmpty();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CONTAINED_INSTANCE:
				return containedInstance != null;
		}
		return super.eIsSet(featureID);
	}

} //ContainedInstanceValueSimulationImpl
