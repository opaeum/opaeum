/**
 */
package org.opaeum.metamodels.simulation.simulation;

import org.eclipse.uml2.uml.LiteralString;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Type Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.LiteralSimpleType#getStringValue <em>String Value</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.LiteralSimpleType#getRuntimeStrategyFactory <em>Runtime Strategy Factory</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getLiteralSimpleType()
 * @model
 * @generated
 */
public interface LiteralSimpleType extends LiteralString {
	/**
	 * Returns the value of the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String Value</em>' attribute.
	 * @see #setStringValue(String)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getLiteralSimpleType_StringValue()
	 * @model
	 * @generated
	 */
	String getStringValue();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.LiteralSimpleType#getStringValue <em>String Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String Value</em>' attribute.
	 * @see #getStringValue()
	 * @generated
	 */
	void setStringValue(String value);

	/**
	 * Returns the value of the '<em><b>Runtime Strategy Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runtime Strategy Factory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runtime Strategy Factory</em>' attribute.
	 * @see #setRuntimeStrategyFactory(String)
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage#getLiteralSimpleType_RuntimeStrategyFactory()
	 * @model
	 * @generated
	 */
	String getRuntimeStrategyFactory();

	/**
	 * Sets the value of the '{@link org.opaeum.metamodels.simulation.simulation.LiteralSimpleType#getRuntimeStrategyFactory <em>Runtime Strategy Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runtime Strategy Factory</em>' attribute.
	 * @see #getRuntimeStrategyFactory()
	 * @generated
	 */
	void setRuntimeStrategyFactory(String value);

} // ValueTypeLiteral
