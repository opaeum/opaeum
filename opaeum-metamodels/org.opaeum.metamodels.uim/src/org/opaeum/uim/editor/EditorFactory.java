/**
 */
package org.opaeum.uim.editor;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.editor.EditorPackage
 * @generated
 */
public interface EditorFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EditorFactory eINSTANCE = org.opaeum.uim.editor.impl.EditorFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Abstract Editor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Editor</em>'.
	 * @generated
	 */
	AbstractEditor createAbstractEditor();

	/**
	 * Returns a new object of class '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Page</em>'.
	 * @generated
	 */
	EditorPage createEditorPage();

	/**
	 * Returns a new object of class '<em>Action Bar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Bar</em>'.
	 * @generated
	 */
	ActionBar createActionBar();

	/**
	 * Returns a new object of class '<em>Menu Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Menu Configuration</em>'.
	 * @generated
	 */
	MenuConfiguration createMenuConfiguration();

	/**
	 * Returns a new object of class '<em>Operation Menu Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Menu Item</em>'.
	 * @generated
	 */
	OperationMenuItem createOperationMenuItem();

	/**
	 * Returns a new object of class '<em>Responsibility Viewer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Responsibility Viewer</em>'.
	 * @generated
	 */
	ResponsibilityViewer createResponsibilityViewer();

	/**
	 * Returns a new object of class '<em>Query Result Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Result Page</em>'.
	 * @generated
	 */
	QueryResultPage createQueryResultPage();

	/**
	 * Returns a new object of class '<em>Object Editor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Object Editor</em>'.
	 * @generated
	 */
	ObjectEditor createObjectEditor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EditorPackage getEditorPackage();

} //EditorFactory
