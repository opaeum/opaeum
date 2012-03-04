/**
 */
package org.opaeum.uim.editor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.panel.PanelPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.editor.EditorFactory
 * @model kind="package"
 * @generated
 */
public interface EditorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "editor";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/form/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "frm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EditorPackage eINSTANCE = org.opaeum.uim.editor.impl.EditorPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.AbstractEditorImpl <em>Abstract Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.AbstractEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getAbstractEditor()
	 * @generated
	 */
	int ABSTRACT_EDITOR = 0;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__EDITABILITY = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__VISIBILITY = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__ACTION_BAR = UimPackage.UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__MENU_CONFIGURATION = UimPackage.UML_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Abstract Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.ActionTaskEditorImpl <em>Action Task Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.ActionTaskEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getActionTaskEditor()
	 * @generated
	 */
	int ACTION_TASK_EDITOR = 1;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_EDITOR__UML_ELEMENT_UID = ABSTRACT_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_EDITOR__EDITABILITY = ABSTRACT_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_EDITOR__VISIBILITY = ABSTRACT_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_EDITOR__ACTION_BAR = ABSTRACT_EDITOR__ACTION_BAR;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_EDITOR__MENU_CONFIGURATION = ABSTRACT_EDITOR__MENU_CONFIGURATION;

	/**
	 * The number of structural features of the '<em>Action Task Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_EDITOR_FEATURE_COUNT = ABSTRACT_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.ClassEditorImpl <em>Class Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.ClassEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getClassEditor()
	 * @generated
	 */
	int CLASS_EDITOR = 2;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EDITOR__UML_ELEMENT_UID = ABSTRACT_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EDITOR__EDITABILITY = ABSTRACT_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EDITOR__VISIBILITY = ABSTRACT_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EDITOR__ACTION_BAR = ABSTRACT_EDITOR__ACTION_BAR;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EDITOR__MENU_CONFIGURATION = ABSTRACT_EDITOR__MENU_CONFIGURATION;

	/**
	 * The number of structural features of the '<em>Class Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_EDITOR_FEATURE_COUNT = ABSTRACT_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.OperationTaskEditorImpl <em>Operation Task Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.OperationTaskEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getOperationTaskEditor()
	 * @generated
	 */
	int OPERATION_TASK_EDITOR = 3;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_EDITOR__UML_ELEMENT_UID = ABSTRACT_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_EDITOR__EDITABILITY = ABSTRACT_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_EDITOR__VISIBILITY = ABSTRACT_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_EDITOR__ACTION_BAR = ABSTRACT_EDITOR__ACTION_BAR;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_EDITOR__MENU_CONFIGURATION = ABSTRACT_EDITOR__MENU_CONFIGURATION;

	/**
	 * The number of structural features of the '<em>Operation Task Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_EDITOR_FEATURE_COUNT = ABSTRACT_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.OperationInvocationEditorImpl <em>Operation Invocation Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.OperationInvocationEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getOperationInvocationEditor()
	 * @generated
	 */
	int OPERATION_INVOCATION_EDITOR = 4;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_EDITOR__UML_ELEMENT_UID = ABSTRACT_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_EDITOR__EDITABILITY = ABSTRACT_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_EDITOR__VISIBILITY = ABSTRACT_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_EDITOR__ACTION_BAR = ABSTRACT_EDITOR__ACTION_BAR;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_EDITOR__MENU_CONFIGURATION = ABSTRACT_EDITOR__MENU_CONFIGURATION;

	/**
	 * The number of structural features of the '<em>Operation Invocation Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_EDITOR_FEATURE_COUNT = ABSTRACT_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.EditorPageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.EditorPageImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getEditorPage()
	 * @generated
	 */
	int EDITOR_PAGE = 5;

	/**
	 * The feature id for the '<em><b>Editor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__EDITOR = UimPackage.PAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__PANEL = UimPackage.PAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE_FEATURE_COUNT = UimPackage.PAGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.ActionBarImpl <em>Action Bar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.ActionBarImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getActionBar()
	 * @generated
	 */
	int ACTION_BAR = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__NAME = PanelPackage.ABSTRACT_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__VISIBILITY = PanelPackage.ABSTRACT_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__EDITABILITY = PanelPackage.ABSTRACT_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__CHILDREN = PanelPackage.ABSTRACT_PANEL__CHILDREN;

	/**
	 * The number of structural features of the '<em>Action Bar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR_FEATURE_COUNT = PanelPackage.ABSTRACT_PANEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.MenuConfigurationImpl <em>Menu Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.MenuConfigurationImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getMenuConfiguration()
	 * @generated
	 */
	int MENU_CONFIGURATION = 7;

	/**
	 * The feature id for the '<em><b>Editor</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONFIGURATION__EDITOR = 0;

	/**
	 * The feature id for the '<em><b>Visible Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONFIGURATION__VISIBLE_OPERATIONS = 1;

	/**
	 * The number of structural features of the '<em>Menu Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONFIGURATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.VisibleOperationImpl <em>Visible Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.VisibleOperationImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getVisibleOperation()
	 * @generated
	 */
	int VISIBLE_OPERATION = 8;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_OPERATION__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_OPERATION__MENU_CONFIGURATION = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Visible Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_OPERATION_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.AbstractEditor <em>Abstract Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Editor</em>'.
	 * @see org.opaeum.uim.editor.AbstractEditor
	 * @generated
	 */
	EClass getAbstractEditor();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.editor.AbstractEditor#getEditability <em>Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editability</em>'.
	 * @see org.opaeum.uim.editor.AbstractEditor#getEditability()
	 * @see #getAbstractEditor()
	 * @generated
	 */
	EReference getAbstractEditor_Editability();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.editor.AbstractEditor#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visibility</em>'.
	 * @see org.opaeum.uim.editor.AbstractEditor#getVisibility()
	 * @see #getAbstractEditor()
	 * @generated
	 */
	EReference getAbstractEditor_Visibility();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.editor.AbstractEditor#getActionBar <em>Action Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action Bar</em>'.
	 * @see org.opaeum.uim.editor.AbstractEditor#getActionBar()
	 * @see #getAbstractEditor()
	 * @generated
	 */
	EReference getAbstractEditor_ActionBar();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.editor.AbstractEditor#getMenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Menu Configuration</em>'.
	 * @see org.opaeum.uim.editor.AbstractEditor#getMenuConfiguration()
	 * @see #getAbstractEditor()
	 * @generated
	 */
	EReference getAbstractEditor_MenuConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.ActionTaskEditor <em>Action Task Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Task Editor</em>'.
	 * @see org.opaeum.uim.editor.ActionTaskEditor
	 * @generated
	 */
	EClass getActionTaskEditor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.ClassEditor <em>Class Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Editor</em>'.
	 * @see org.opaeum.uim.editor.ClassEditor
	 * @generated
	 */
	EClass getClassEditor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.OperationTaskEditor <em>Operation Task Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Task Editor</em>'.
	 * @see org.opaeum.uim.editor.OperationTaskEditor
	 * @generated
	 */
	EClass getOperationTaskEditor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.OperationInvocationEditor <em>Operation Invocation Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Invocation Editor</em>'.
	 * @see org.opaeum.uim.editor.OperationInvocationEditor
	 * @generated
	 */
	EClass getOperationInvocationEditor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.EditorPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.opaeum.uim.editor.EditorPage
	 * @generated
	 */
	EClass getEditorPage();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.editor.EditorPage#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Editor</em>'.
	 * @see org.opaeum.uim.editor.EditorPage#getEditor()
	 * @see #getEditorPage()
	 * @generated
	 */
	EReference getEditorPage_Editor();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.editor.EditorPage#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Panel</em>'.
	 * @see org.opaeum.uim.editor.EditorPage#getPanel()
	 * @see #getEditorPage()
	 * @generated
	 */
	EReference getEditorPage_Panel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.ActionBar <em>Action Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Bar</em>'.
	 * @see org.opaeum.uim.editor.ActionBar
	 * @generated
	 */
	EClass getActionBar();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.MenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Menu Configuration</em>'.
	 * @see org.opaeum.uim.editor.MenuConfiguration
	 * @generated
	 */
	EClass getMenuConfiguration();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.MenuConfiguration#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Editor</em>'.
	 * @see org.opaeum.uim.editor.MenuConfiguration#getEditor()
	 * @see #getMenuConfiguration()
	 * @generated
	 */
	EReference getMenuConfiguration_Editor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.editor.MenuConfiguration#getVisibleOperations <em>Visible Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Visible Operations</em>'.
	 * @see org.opaeum.uim.editor.MenuConfiguration#getVisibleOperations()
	 * @see #getMenuConfiguration()
	 * @generated
	 */
	EReference getMenuConfiguration_VisibleOperations();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.VisibleOperation <em>Visible Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visible Operation</em>'.
	 * @see org.opaeum.uim.editor.VisibleOperation
	 * @generated
	 */
	EClass getVisibleOperation();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.VisibleOperation#getMenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Menu Configuration</em>'.
	 * @see org.opaeum.uim.editor.VisibleOperation#getMenuConfiguration()
	 * @see #getVisibleOperation()
	 * @generated
	 */
	EReference getVisibleOperation_MenuConfiguration();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EditorFactory getEditorFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.AbstractEditorImpl <em>Abstract Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.AbstractEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getAbstractEditor()
		 * @generated
		 */
		EClass ABSTRACT_EDITOR = eINSTANCE.getAbstractEditor();

		/**
		 * The meta object literal for the '<em><b>Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_EDITOR__EDITABILITY = eINSTANCE.getAbstractEditor_Editability();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_EDITOR__VISIBILITY = eINSTANCE.getAbstractEditor_Visibility();

		/**
		 * The meta object literal for the '<em><b>Action Bar</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_EDITOR__ACTION_BAR = eINSTANCE.getAbstractEditor_ActionBar();

		/**
		 * The meta object literal for the '<em><b>Menu Configuration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_EDITOR__MENU_CONFIGURATION = eINSTANCE.getAbstractEditor_MenuConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.ActionTaskEditorImpl <em>Action Task Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.ActionTaskEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getActionTaskEditor()
		 * @generated
		 */
		EClass ACTION_TASK_EDITOR = eINSTANCE.getActionTaskEditor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.ClassEditorImpl <em>Class Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.ClassEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getClassEditor()
		 * @generated
		 */
		EClass CLASS_EDITOR = eINSTANCE.getClassEditor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.OperationTaskEditorImpl <em>Operation Task Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.OperationTaskEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getOperationTaskEditor()
		 * @generated
		 */
		EClass OPERATION_TASK_EDITOR = eINSTANCE.getOperationTaskEditor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.OperationInvocationEditorImpl <em>Operation Invocation Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.OperationInvocationEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getOperationInvocationEditor()
		 * @generated
		 */
		EClass OPERATION_INVOCATION_EDITOR = eINSTANCE.getOperationInvocationEditor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.EditorPageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.EditorPageImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getEditorPage()
		 * @generated
		 */
		EClass EDITOR_PAGE = eINSTANCE.getEditorPage();

		/**
		 * The meta object literal for the '<em><b>Editor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITOR_PAGE__EDITOR = eINSTANCE.getEditorPage_Editor();

		/**
		 * The meta object literal for the '<em><b>Panel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITOR_PAGE__PANEL = eINSTANCE.getEditorPage_Panel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.ActionBarImpl <em>Action Bar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.ActionBarImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getActionBar()
		 * @generated
		 */
		EClass ACTION_BAR = eINSTANCE.getActionBar();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.MenuConfigurationImpl <em>Menu Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.MenuConfigurationImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getMenuConfiguration()
		 * @generated
		 */
		EClass MENU_CONFIGURATION = eINSTANCE.getMenuConfiguration();

		/**
		 * The meta object literal for the '<em><b>Editor</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_CONFIGURATION__EDITOR = eINSTANCE.getMenuConfiguration_Editor();

		/**
		 * The meta object literal for the '<em><b>Visible Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_CONFIGURATION__VISIBLE_OPERATIONS = eINSTANCE.getMenuConfiguration_VisibleOperations();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.VisibleOperationImpl <em>Visible Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.VisibleOperationImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getVisibleOperation()
		 * @generated
		 */
		EClass VISIBLE_OPERATION = eINSTANCE.getVisibleOperation();

		/**
		 * The meta object literal for the '<em><b>Menu Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VISIBLE_OPERATION__MENU_CONFIGURATION = eINSTANCE.getVisibleOperation_MenuConfiguration();

	}

} //EditorPackage
