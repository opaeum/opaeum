/**
 */
package org.opaeum.uim;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.UimPackage
 * @generated
 */
public interface UimFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UimFactory eINSTANCE = org.opaeum.uim.impl.UimFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Uml Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uml Reference</em>'.
	 * @generated
	 */
	UmlReference createUmlReference();

	/**
	 * Returns a new object of class '<em>User Interface Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Interface Root</em>'.
	 * @generated
	 */
	UserInterfaceRoot createUserInterfaceRoot();

	/**
	 * Returns a new object of class '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Page</em>'.
	 * @generated
	 */
	Page createPage();

	/**
	 * Returns a new object of class '<em>Labels</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Labels</em>'.
	 * @generated
	 */
	Labels createLabels();

	/**
	 * Returns a new object of class '<em>Labeled Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Labeled Element</em>'.
	 * @generated
	 */
	LabeledElement createLabeledElement();

	/**
	 * Returns a new object of class '<em>Ignored Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ignored Element</em>'.
	 * @generated
	 */
	IgnoredElement createIgnoredElement();

	/**
	 * Returns a new object of class '<em>Page Ordering</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Page Ordering</em>'.
	 * @generated
	 */
	PageOrdering createPageOrdering();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UimPackage getUimPackage();

} //UimFactory
