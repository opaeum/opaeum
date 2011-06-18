/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.form;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.nakeduml.uim.UimPackage;

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
 * @see org.nakeduml.uim.form.FormFactory
 * @model kind="package"
 * @generated
 */
public interface FormPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "form";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://nakeduml.org/uimetamodel/form/1.0";

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
	FormPackage eINSTANCE = org.nakeduml.uim.form.impl.FormPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.form.impl.FormPanelImpl <em>Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.form.impl.FormPanelImpl
	 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getFormPanel()
	 * @generated
	 */
	int FORM_PANEL = 0;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__NAME = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__VISIBILITY = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__EDITABILITY = UimPackage.UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__LAYOUT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Form</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__FORM = UimPackage.UML_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.form.impl.ActionTaskFormImpl <em>Action Task Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.form.impl.ActionTaskFormImpl
	 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getActionTaskForm()
	 * @generated
	 */
	int ACTION_TASK_FORM = 1;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__UML_ELEMENT_UID = FORM_PANEL__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__NAME = FORM_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__VISIBILITY = FORM_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__EDITABILITY = FORM_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__LAYOUT = FORM_PANEL__LAYOUT;

	/**
	 * The feature id for the '<em><b>Form</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__FORM = FORM_PANEL__FORM;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__FOLDER = FORM_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action Task Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM_FEATURE_COUNT = FORM_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.form.impl.StateFormImpl <em>State Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.form.impl.StateFormImpl
	 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getStateForm()
	 * @generated
	 */
	int STATE_FORM = 2;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__UML_ELEMENT_UID = FORM_PANEL__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__NAME = FORM_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__VISIBILITY = FORM_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__EDITABILITY = FORM_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__LAYOUT = FORM_PANEL__LAYOUT;

	/**
	 * The feature id for the '<em><b>Form</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__FORM = FORM_PANEL__FORM;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__FOLDER = FORM_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>State Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM_FEATURE_COUNT = FORM_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.form.impl.ClassFormImpl <em>Class Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.form.impl.ClassFormImpl
	 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getClassForm()
	 * @generated
	 */
	int CLASS_FORM = 3;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__UML_ELEMENT_UID = FORM_PANEL__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__NAME = FORM_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__VISIBILITY = FORM_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__EDITABILITY = FORM_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__LAYOUT = FORM_PANEL__LAYOUT;

	/**
	 * The feature id for the '<em><b>Form</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__FORM = FORM_PANEL__FORM;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__FOLDER = FORM_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Class Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM_FEATURE_COUNT = FORM_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.form.impl.UimFormImpl <em>Uim Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.form.impl.UimFormImpl
	 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getUimForm()
	 * @generated
	 */
	int UIM_FORM = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FORM__NAME = UimPackage.USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FORM__PANEL = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Uim Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FORM_FEATURE_COUNT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.form.impl.OperationTaskFormImpl <em>Operation Task Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.form.impl.OperationTaskFormImpl
	 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getOperationTaskForm()
	 * @generated
	 */
	int OPERATION_TASK_FORM = 5;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__UML_ELEMENT_UID = FORM_PANEL__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__NAME = FORM_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__VISIBILITY = FORM_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__EDITABILITY = FORM_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__LAYOUT = FORM_PANEL__LAYOUT;

	/**
	 * The feature id for the '<em><b>Form</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__FORM = FORM_PANEL__FORM;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__FOLDER = FORM_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Task Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM_FEATURE_COUNT = FORM_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.form.impl.OperationInvocationFormImpl <em>Operation Invocation Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.form.impl.OperationInvocationFormImpl
	 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getOperationInvocationForm()
	 * @generated
	 */
	int OPERATION_INVOCATION_FORM = 6;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__UML_ELEMENT_UID = FORM_PANEL__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__NAME = FORM_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__VISIBILITY = FORM_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__EDITABILITY = FORM_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__LAYOUT = FORM_PANEL__LAYOUT;

	/**
	 * The feature id for the '<em><b>Form</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__FORM = FORM_PANEL__FORM;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__FOLDER = FORM_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Invocation Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM_FEATURE_COUNT = FORM_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.form.impl.DetailPanelImpl <em>Detail Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.form.impl.DetailPanelImpl
	 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getDetailPanel()
	 * @generated
	 */
	int DETAIL_PANEL = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__NAME = UIM_FORM__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__PANEL = UIM_FORM__PANEL;

	/**
	 * The feature id for the '<em><b>Master Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__MASTER_COMPONENT = UIM_FORM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Detail Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL_FEATURE_COUNT = UIM_FORM_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.form.FormPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Panel</em>'.
	 * @see org.nakeduml.uim.form.FormPanel
	 * @generated
	 */
	EClass getFormPanel();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.form.FormPanel#getForm <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Form</em>'.
	 * @see org.nakeduml.uim.form.FormPanel#getForm()
	 * @see #getFormPanel()
	 * @generated
	 */
	EReference getFormPanel_Form();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.form.ActionTaskForm <em>Action Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Task Form</em>'.
	 * @see org.nakeduml.uim.form.ActionTaskForm
	 * @generated
	 */
	EClass getActionTaskForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.form.ActionTaskForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.form.ActionTaskForm#getFolder()
	 * @see #getActionTaskForm()
	 * @generated
	 */
	EReference getActionTaskForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.form.StateForm <em>State Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Form</em>'.
	 * @see org.nakeduml.uim.form.StateForm
	 * @generated
	 */
	EClass getStateForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.form.StateForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.form.StateForm#getFolder()
	 * @see #getStateForm()
	 * @generated
	 */
	EReference getStateForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.form.ClassForm <em>Class Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Form</em>'.
	 * @see org.nakeduml.uim.form.ClassForm
	 * @generated
	 */
	EClass getClassForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.form.ClassForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.form.ClassForm#getFolder()
	 * @see #getClassForm()
	 * @generated
	 */
	EReference getClassForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.form.UimForm <em>Uim Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Form</em>'.
	 * @see org.nakeduml.uim.form.UimForm
	 * @generated
	 */
	EClass getUimForm();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.form.UimForm#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Panel</em>'.
	 * @see org.nakeduml.uim.form.UimForm#getPanel()
	 * @see #getUimForm()
	 * @generated
	 */
	EReference getUimForm_Panel();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.form.OperationTaskForm <em>Operation Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Task Form</em>'.
	 * @see org.nakeduml.uim.form.OperationTaskForm
	 * @generated
	 */
	EClass getOperationTaskForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.form.OperationTaskForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.form.OperationTaskForm#getFolder()
	 * @see #getOperationTaskForm()
	 * @generated
	 */
	EReference getOperationTaskForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.form.OperationInvocationForm <em>Operation Invocation Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Invocation Form</em>'.
	 * @see org.nakeduml.uim.form.OperationInvocationForm
	 * @generated
	 */
	EClass getOperationInvocationForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.form.OperationInvocationForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.form.OperationInvocationForm#getFolder()
	 * @see #getOperationInvocationForm()
	 * @generated
	 */
	EReference getOperationInvocationForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.form.DetailPanel <em>Detail Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Detail Panel</em>'.
	 * @see org.nakeduml.uim.form.DetailPanel
	 * @generated
	 */
	EClass getDetailPanel();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.form.DetailPanel#getMasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Master Component</em>'.
	 * @see org.nakeduml.uim.form.DetailPanel#getMasterComponent()
	 * @see #getDetailPanel()
	 * @generated
	 */
	EReference getDetailPanel_MasterComponent();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FormFactory getFormFactory();

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
		 * The meta object literal for the '{@link org.nakeduml.uim.form.impl.FormPanelImpl <em>Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.form.impl.FormPanelImpl
		 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getFormPanel()
		 * @generated
		 */
		EClass FORM_PANEL = eINSTANCE.getFormPanel();

		/**
		 * The meta object literal for the '<em><b>Form</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORM_PANEL__FORM = eINSTANCE.getFormPanel_Form();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.form.impl.ActionTaskFormImpl <em>Action Task Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.form.impl.ActionTaskFormImpl
		 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getActionTaskForm()
		 * @generated
		 */
		EClass ACTION_TASK_FORM = eINSTANCE.getActionTaskForm();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_TASK_FORM__FOLDER = eINSTANCE.getActionTaskForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.form.impl.StateFormImpl <em>State Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.form.impl.StateFormImpl
		 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getStateForm()
		 * @generated
		 */
		EClass STATE_FORM = eINSTANCE.getStateForm();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FORM__FOLDER = eINSTANCE.getStateForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.form.impl.ClassFormImpl <em>Class Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.form.impl.ClassFormImpl
		 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getClassForm()
		 * @generated
		 */
		EClass CLASS_FORM = eINSTANCE.getClassForm();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FORM__FOLDER = eINSTANCE.getClassForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.form.impl.UimFormImpl <em>Uim Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.form.impl.UimFormImpl
		 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getUimForm()
		 * @generated
		 */
		EClass UIM_FORM = eINSTANCE.getUimForm();

		/**
		 * The meta object literal for the '<em><b>Panel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FORM__PANEL = eINSTANCE.getUimForm_Panel();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.form.impl.OperationTaskFormImpl <em>Operation Task Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.form.impl.OperationTaskFormImpl
		 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getOperationTaskForm()
		 * @generated
		 */
		EClass OPERATION_TASK_FORM = eINSTANCE.getOperationTaskForm();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_TASK_FORM__FOLDER = eINSTANCE.getOperationTaskForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.form.impl.OperationInvocationFormImpl <em>Operation Invocation Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.form.impl.OperationInvocationFormImpl
		 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getOperationInvocationForm()
		 * @generated
		 */
		EClass OPERATION_INVOCATION_FORM = eINSTANCE.getOperationInvocationForm();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INVOCATION_FORM__FOLDER = eINSTANCE.getOperationInvocationForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.form.impl.DetailPanelImpl <em>Detail Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.form.impl.DetailPanelImpl
		 * @see org.nakeduml.uim.form.impl.FormPackageImpl#getDetailPanel()
		 * @generated
		 */
		EClass DETAIL_PANEL = eINSTANCE.getDetailPanel();

		/**
		 * The meta object literal for the '<em><b>Master Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DETAIL_PANEL__MASTER_COMPONENT = eINSTANCE.getDetailPanel_MasterComponent();

	}

} //FormPackage
