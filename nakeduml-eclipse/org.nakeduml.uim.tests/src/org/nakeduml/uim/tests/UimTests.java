/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>uim</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new UimTests("uim Tests");
		suite.addTestSuite(UimFormTest.class);
		suite.addTestSuite(UimFieldTest.class);
		suite.addTestSuite(ClassFormTest.class);
		suite.addTestSuite(StateFormTest.class);
		suite.addTestSuite(OperationInvocationFormTest.class);
		suite.addTestSuite(OperationActionTest.class);
		suite.addTestSuite(NavigationToOperationTest.class);
		suite.addTestSuite(BuiltInActionTest.class);
		suite.addTestSuite(NavigationToEntityTest.class);
		suite.addTestSuite(TransitionActionTest.class);
		suite.addTestSuite(OperationTaskFormTest.class);
		suite.addTestSuite(ActionTaskFormTest.class);
		suite.addTestSuite(UimGridLayoutTest.class);
		suite.addTestSuite(UimDataTableTest.class);
		suite.addTestSuite(UimDataColumnTest.class);
		suite.addTestSuite(FormPanelTest.class);
		suite.addTestSuite(DetailPanelTest.class);
		suite.addTestSuite(UimTabPanelTest.class);
		suite.addTestSuite(UimTabTest.class);
		suite.addTestSuite(UimContainerTest.class);
		suite.addTestSuite(UimLayoutTest.class);
		suite.addTestSuite(UimToolbarLayoutTest.class);
		suite.addTestSuite(UimBorderLayoutTest.class);
		suite.addTestSuite(UimXYLayoutTest.class);
		suite.addTestSuite(UimFullLayoutTest.class);
		suite.addTestSuite(UimPanelTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimTests(String name) {
		super(name);
	}

} //UimTests
