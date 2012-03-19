/**
 */
package org.opaeum.metamodels.simulation.simulation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.uml2.uml.InstanceSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contained Instance Value Simulation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation#getValues <em>Values</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation#getContainedInstance <em>Contained Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getContainedInstanceValueSimulation()
 * @model
 * @generated
 */
public interface ContainedInstanceValueSimulation extends ValueSimulation {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.InstanceSpecification}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getContainedInstanceValueSimulation_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<InstanceSpecification> getValues();

	/**
	 * Returns the value of the '<em><b>Contained Instance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Instance</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Instance</em>' containment reference.
	 * @see #setContainedInstance(ActualInstanceSimulation)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getContainedInstanceValueSimulation_ContainedInstance()
	 * @model containment="true"
	 * @generated
	 */
	ActualInstanceSimulation getContainedInstance();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation#getContainedInstance <em>Contained Instance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contained Instance</em>' containment reference.
	 * @see #getContainedInstance()
	 * @generated
	 */
	void setContainedInstance(ActualInstanceSimulation value);

} // ContainedInstanceValueSimulation
