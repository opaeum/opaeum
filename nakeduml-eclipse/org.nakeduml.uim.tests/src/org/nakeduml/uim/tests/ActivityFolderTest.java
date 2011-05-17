/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.UimFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Activity Folder</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ActivityFolderTest extends AbstractFormFolderTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ActivityFolderTest.class);
	}

	/**
	 * Constructs a new Activity Folder test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityFolderTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Activity Folder test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ActivityFolder getFixture() {
		return (ActivityFolder)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createActivityFolder());
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

} //ActivityFolderTest
