/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UserInteractionWorkspace;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>User Interaction Workspace</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UserInteractionWorkspaceTest extends AbstractFolderTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UserInteractionWorkspaceTest.class);
	}

	/**
	 * Constructs a new User Interaction Workspace test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionWorkspaceTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this User Interaction Workspace test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UserInteractionWorkspace getFixture() {
		return (UserInteractionWorkspace)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUserInteractionWorkspace());
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

} //UserInteractionWorkspaceTest
