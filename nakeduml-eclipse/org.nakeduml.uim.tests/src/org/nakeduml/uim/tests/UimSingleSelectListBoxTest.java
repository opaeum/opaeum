/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimSingleSelectListBox;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Single Select List Box</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimSingleSelectListBoxTest extends UimLookupTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UimSingleSelectListBoxTest.class);
	}

	/**
	 * Constructs a new Single Select List Box test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSingleSelectListBoxTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Single Select List Box test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UimSingleSelectListBox getFixture() {
		return (UimSingleSelectListBox)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUimSingleSelectListBox());
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

} //UimSingleSelectListBoxTest
