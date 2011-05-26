/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.NavigationToOperation;
import org.nakeduml.uim.UimFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Navigation To Operation</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class NavigationToOperationTest extends UimNavigationTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(NavigationToOperationTest.class);
	}

	/**
	 * Constructs a new Navigation To Operation test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationToOperationTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Navigation To Operation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected NavigationToOperation getFixture() {
		return (NavigationToOperation)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createNavigationToOperation());
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

} //NavigationToOperationTest
