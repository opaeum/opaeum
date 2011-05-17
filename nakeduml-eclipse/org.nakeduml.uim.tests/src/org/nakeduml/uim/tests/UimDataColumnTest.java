/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.tests;

import junit.textui.TestRunner;

import org.nakeduml.uim.UimDataColumn;
import org.nakeduml.uim.UimFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Data Column</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimDataColumnTest extends LayoutContainerTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(UimDataColumnTest.class);
	}

	/**
	 * Constructs a new Data Column test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDataColumnTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Data Column test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected UimDataColumn getFixture() {
		return (UimDataColumn)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(UimFactory.eINSTANCE.createUimDataColumn());
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

} //UimDataColumnTest
