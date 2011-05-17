/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimXYLayout;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>XY Layout</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimXYLayoutTest extends UimLayoutTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UimXYLayoutTest.class);
	}

	/**
	 * Constructs a new XY Layout test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimXYLayoutTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this XY Layout test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UimXYLayout getFixture() {
		return (UimXYLayout)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUimXYLayout());
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

} //UimXYLayoutTest
