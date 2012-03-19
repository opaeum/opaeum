/**
 */
package org.opaeum.metamodels.simulation.simulation;

import org.eclipse.uml2.uml.EnumerationLiteral;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Literal Simulation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation#getLiteral <em>Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getEnumLiteralSimulation()
 * @model
 * @generated
 */
public interface EnumLiteralSimulation extends ValueSimulation {
	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see #setWeight(Double)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getEnumLiteralSimulation_Weight()
	 * @model
	 * @generated
	 */
	Double getWeight();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation#getWeight <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight</em>' attribute.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(Double value);

	/**
	 * Returns the value of the '<em><b>Literal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Literal</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Literal</em>' reference.
	 * @see #setLiteral(EnumerationLiteral)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getEnumLiteralSimulation_Literal()
	 * @model
	 * @generated
	 */
	EnumerationLiteral getLiteral();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation#getLiteral <em>Literal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Literal</em>' reference.
	 * @see #getLiteral()
	 * @generated
	 */
	void setLiteral(EnumerationLiteral value);

} // EnumLiteralSimulation
