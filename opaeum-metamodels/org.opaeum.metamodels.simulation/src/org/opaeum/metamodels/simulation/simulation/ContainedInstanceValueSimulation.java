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
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getContainedInstanceValueSimulation()
 * @model
 * @generated
 */
public interface ContainedInstanceValueSimulation extends ValueSimulation, InstanceSpecification {
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

} // ContainedInstanceValueSimulation
