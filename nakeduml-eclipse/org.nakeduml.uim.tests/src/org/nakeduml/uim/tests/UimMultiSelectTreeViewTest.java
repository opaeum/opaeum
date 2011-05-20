/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimMultiSelectTreeView;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Multi Select Tree View</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimMultiSelectTreeViewTest extends UimLookupTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UimMultiSelectTreeViewTest.class);
	}

	/**
	 * Constructs a new Multi Select Tree View test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimMultiSelectTreeViewTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Multi Select Tree View test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UimMultiSelectTreeView getFixture() {
		return (UimMultiSelectTreeView)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUimMultiSelectTreeView());
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

} //UimMultiSelectTreeViewTest
