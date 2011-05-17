/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimToggleButton;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Toggle Button</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimToggleButtonTest extends UimControlTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UimToggleButtonTest.class);
	}

	/**
	 * Constructs a new Toggle Button test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimToggleButtonTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Toggle Button test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UimToggleButton getFixture() {
		return (UimToggleButton)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUimToggleButton());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //UimToggleButtonTest
