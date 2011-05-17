/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimTab;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Tab</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimTabTest extends LayoutContainerTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UimTabTest.class);
	}

	/**
	 * Constructs a new Tab test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimTabTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Tab test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UimTab getFixture() {
		return (UimTab)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUimTab());
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

} //UimTabTest
