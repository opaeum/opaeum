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
public class UIMTests extends TestSuite {

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
		TestSuite suite = new UIMTests("uim Tests");
		suite.addTestSuite(ClassFormTest.class);
		suite.addTestSuite(StateFormTest.class);
		suite.addTestSuite(OperationInvocationFormTest.class);
		suite.addTestSuite(OperationTaskFormTest.class);
		suite.addTestSuite(ActionTaskFormTest.class);
		suite.addTestSuite(StateMachineFolderTest.class);
		suite.addTestSuite(EntityFolderTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMTests(String name) {
		super(name);
	}

} //UIMTests
