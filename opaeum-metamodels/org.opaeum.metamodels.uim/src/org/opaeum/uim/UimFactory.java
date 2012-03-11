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
	 * Returns a new object of class '<em>Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field</em>'.
	 * @generated
	 */
	UimField createUimField();

	/**
	 * Returns a new object of class '<em>Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Table</em>'.
	 * @generated
	 */
	UimDataTable createUimDataTable();

	/**
	 * Returns a new object of class '<em>Uml Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uml Reference</em>'.
	 * @generated
	 */
	UmlReference createUmlReference();

	/**
	 * Returns a new object of class '<em>Object Selector Tree</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Object Selector Tree</em>'.
	 * @generated
	 */
	ObjectSelectorTree createObjectSelectorTree();

	/**
	 * Returns a new object of class '<em>Detail Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Detail Component</em>'.
	 * @generated
	 */
	DetailComponent createDetailComponent();

	/**
	 * Returns a new object of class '<em>User Interface Entry Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Interface Entry Point</em>'.
	 * @generated
	 */
	UserInterfaceEntryPoint createUserInterfaceEntryPoint();

	/**
	 * Returns a new object of class '<em>User Interface</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Interface</em>'.
	 * @generated
	 */
	UserInterface createUserInterface();

	/**
	 * Returns a new object of class '<em>Panel Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Panel Class</em>'.
	 * @generated
	 */
	PanelClass createPanelClass();

	/**
	 * Returns a new object of class '<em>Class User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class User Interaction Model</em>'.
	 * @generated
	 */
	ClassUserInteractionModel createClassUserInteractionModel();

	/**
	 * Returns a new object of class '<em>Responsibility User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Responsibility User Interaction Model</em>'.
	 * @generated
	 */
	ResponsibilityUserInteractionModel createResponsibilityUserInteractionModel();

	/**
	 * Returns a new object of class '<em>Abstract Action Bar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Action Bar</em>'.
	 * @generated
	 */
	AbstractActionBar createAbstractActionBar();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UimPackage getUimPackage();

} //UimFactory
