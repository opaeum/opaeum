/**
 */
package org.opaeum.metamodels.simulation.simulation;

import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simulated Slot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot#getSizeDistribution <em>Size Distribution</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot#getProperty <em>Property</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getSimulatedSlot()
 * @model
 * @generated
 */
public interface SimulatedSlot extends Slot {
	/**
	 * Returns the value of the '<em><b>Size Distribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size Distribution</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size Distribution</em>' reference.
	 * @see #setSizeDistribution(NumericValueDistribution)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getSimulatedSlot_SizeDistribution()
	 * @model
	 * @generated
	 */
	NumericValueDistribution getSizeDistribution();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot#getSizeDistribution <em>Size Distribution</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size Distribution</em>' reference.
	 * @see #getSizeDistribution()
	 * @generated
	 */
	void setSizeDistribution(NumericValueDistribution value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' reference.
	 * @see #setProperty(Property)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getSimulatedSlot_Property()
	 * @model
	 * @generated
	 */
	Property getProperty();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot#getProperty <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(Property value);

} // SimulatedSlot
