/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimMultiSelectListBox;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Multi Select List Box</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimMultiSelectListBoxTest extends UimLookupTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UimMultiSelectListBoxTest.class);
	}

	/**
	 * Constructs a new Multi Select List Box test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimMultiSelectListBoxTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Multi Select List Box test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UimMultiSelectListBox getFixture() {
		return (UimMultiSelectListBox)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUimMultiSelectListBox());
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

} //UimMultiSelectListBoxTest