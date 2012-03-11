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
	 * Returns a new object of class '<em>Action Task Editor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Task Editor</em>'.
	 * @generated
	 */
	ActionTaskEditor createActionTaskEditor();

	/**
	 * Returns a new object of class '<em>Class Editor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Editor</em>'.
	 * @generated
	 */
	ClassEditor createClassEditor();

	/**
	 * Returns a new object of class '<em>Responsibility Task Editor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Responsibility Task Editor</em>'.
	 * @generated
	 */
	ResponsibilityTaskEditor createResponsibilityTaskEditor();

	/**
	 * Returns a new object of class '<em>Query Invocation Editor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Invocation Editor</em>'.
	 * @generated
	 */
	QueryInvocationEditor createQueryInvocationEditor();

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
	EditorActionBar createEditorActionBar();

	/**
	 * Returns a new object of class '<em>Menu Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Menu Configuration</em>'.
	 * @generated
	 */
	MenuConfiguration createMenuConfiguration();

	/**
	 * Returns a new object of class '<em>Visible Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Visible Operation</em>'.
	 * @generated
	 */
	VisibleOperation createVisibleOperation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EditorPackage getEditorPackage();

} //EditorFactory
