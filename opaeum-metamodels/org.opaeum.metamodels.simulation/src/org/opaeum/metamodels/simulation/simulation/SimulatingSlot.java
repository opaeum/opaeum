/**
 */
package org.opaeum.metamodels.simulation.simulation;

import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Slot;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simulating Slot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.SimulatingSlot#getSizeDistribution <em>Size Distribution</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.SimulatingSlot#getSimulationStrategy <em>Simulation Strategy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getSimulatingSlot()
 * @model
 * @generated
 */
public interface SimulatingSlot extends Slot {
	/**
	 * Returns the value of the '<em><b>Size Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size Distribution</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size Distribution</em>' containment reference.
	 * @see #setSizeDistribution(NumericValueDistribution)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getSimulatingSlot_SizeDistribution()
	 * @model containment="true"
	 * @generated
	 */
	NumericValueDistribution getSizeDistribution();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.SimulatingSlot#getSizeDistribution <em>Size Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size Distribution</em>' containment reference.
	 * @see #getSizeDistribution()
	 * @generated
	 */
	void setSizeDistribution(NumericValueDistribution value);

	/**
	 * Returns the value of the '<em><b>Simulation Strategy</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link org.opaeum.metamodels.simulation.simulation.SimulationStrategy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simulation Strategy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simulation Strategy</em>' attribute.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationStrategy
	 * @see #setSimulationStrategy(SimulationStrategy)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getSimulatingSlot_SimulationStrategy()
	 * @model default=""
	 * @generated
	 */
	SimulationStrategy getSimulationStrategy();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.SimulatingSlot#getSimulationStrategy <em>Simulation Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Simulation Strategy</em>' attribute.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationStrategy
	 * @see #getSimulationStrategy()
	 * @generated
	 */
	void setSimulationStrategy(SimulationStrategy value);

} // SimulatingSlot
