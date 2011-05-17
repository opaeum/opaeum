/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimCheckBox;
import org.nakeduml.uim.UimFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Check Box</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimCheckBoxTest extends UimControlTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UimCheckBoxTest.class);
	}

	/**
	 * Constructs a new Check Box test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimCheckBoxTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Check Box test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UimCheckBox getFixture() {
		return (UimCheckBox)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUimCheckBox());
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

} //UimCheckBoxTest
