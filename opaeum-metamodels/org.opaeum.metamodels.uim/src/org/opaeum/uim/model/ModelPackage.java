/**
 */
package org.opaeum.uim.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.wizard.WizardPackage;

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
 * @see org.opaeum.uim.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/model/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.opaeum.uim.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl <em>Class User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.model.impl.ClassUserInteractionModelImpl
	 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getClassUserInteractionModel()
	 * @generated
	 */
	int CLASS_USER_INTERACTION_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__NAME = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__UNDER_USER_CONTROL = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Linked Uml Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE = UimPackage.UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Primary Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR = UimPackage.UML_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Secondary Editors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS = UimPackage.UML_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>New Object Wizard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD = UimPackage.UML_REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Cube Query Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR = UimPackage.UML_REFERENCE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Class User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.model.impl.ResponsibilityUserInteractionModelImpl <em>Responsibility User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.model.impl.ResponsibilityUserInteractionModelImpl
	 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getResponsibilityUserInteractionModel()
	 * @generated
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__NAME = UimPackage.USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__UNDER_USER_CONTROL = UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Linked Uml Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Invocation Wizard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Viewer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Responsibility User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL_FEATURE_COUNT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.model.AbstractUserInteractionModel <em>Abstract User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.model.AbstractUserInteractionModel
	 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getAbstractUserInteractionModel()
	 * @generated
	 */
	int ABSTRACT_USER_INTERACTION_MODEL = 2;

	/**
	 * The feature id for the '<em><b>Linked Uml Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE = 0;

	/**
	 * The number of structural features of the '<em>Abstract User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_USER_INTERACTION_MODEL_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.model.impl.BehaviorUserInteractionModelImpl <em>Behavior User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.model.impl.BehaviorUserInteractionModelImpl
	 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getBehaviorUserInteractionModel()
	 * @generated
	 */
	int BEHAVIOR_USER_INTERACTION_MODEL = 3;

	/**
	 * The feature id for the '<em><b>Linked Uml Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE = ABSTRACT_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE;

	/**
	 * The feature id for the '<em><b>Invocation Wizard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_USER_INTERACTION_MODEL__INVOCATION_WIZARD = ABSTRACT_USER_INTERACTION_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_USER_INTERACTION_MODEL__EDITOR = ABSTRACT_USER_INTERACTION_MODEL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Behavior User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_USER_INTERACTION_MODEL_FEATURE_COUNT = ABSTRACT_USER_INTERACTION_MODEL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.model.impl.QueryInvokerImpl <em>Query Invoker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.model.impl.QueryInvokerImpl
	 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getQueryInvoker()
	 * @generated
	 */
	int QUERY_INVOKER = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__NAME = EditorPackage.ABSTRACT_EDITOR__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__UNDER_USER_CONTROL = EditorPackage.ABSTRACT_EDITOR__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__UML_ELEMENT_UID = EditorPackage.ABSTRACT_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__LABEL_OVERRIDE = EditorPackage.ABSTRACT_EDITOR__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__EDITABILITY = EditorPackage.ABSTRACT_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__VISIBILITY = EditorPackage.ABSTRACT_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__IGNORED_ELEMENTS = EditorPackage.ABSTRACT_EDITOR__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__PAGES = EditorPackage.ABSTRACT_EDITOR__PAGES;

	/**
	 * The feature id for the '<em><b>Linked Uml Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__LINKED_UML_RESOURCE = EditorPackage.ABSTRACT_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Result Page</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER__RESULT_PAGE = EditorPackage.ABSTRACT_EDITOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Query Invoker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_INVOKER_FEATURE_COUNT = EditorPackage.ABSTRACT_EDITOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.model.impl.OperationInvocationWizardImpl <em>Operation Invocation Wizard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.model.impl.OperationInvocationWizardImpl
	 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getOperationInvocationWizard()
	 * @generated
	 */
	int OPERATION_INVOCATION_WIZARD = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__NAME = WizardPackage.INVOCATION_WIZARD__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__UNDER_USER_CONTROL = WizardPackage.INVOCATION_WIZARD__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__UML_ELEMENT_UID = WizardPackage.INVOCATION_WIZARD__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__LABEL_OVERRIDE = WizardPackage.INVOCATION_WIZARD__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__EDITABILITY = WizardPackage.INVOCATION_WIZARD__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__VISIBILITY = WizardPackage.INVOCATION_WIZARD__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__IGNORED_ELEMENTS = WizardPackage.INVOCATION_WIZARD__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__PAGES = WizardPackage.INVOCATION_WIZARD__PAGES;

	/**
	 * The feature id for the '<em><b>Linked Uml Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__LINKED_UML_RESOURCE = WizardPackage.INVOCATION_WIZARD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Result Page</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD__RESULT_PAGE = WizardPackage.INVOCATION_WIZARD_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Invocation Wizard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_WIZARD_FEATURE_COUNT = WizardPackage.INVOCATION_WIZARD_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link org.opaeum.uim.model.impl.EmbeddedTaskEditorImpl <em>Embedded Task Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.model.impl.EmbeddedTaskEditorImpl
	 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getEmbeddedTaskEditor()
	 * @generated
	 */
	int EMBEDDED_TASK_EDITOR = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__NAME = EditorPackage.ABSTRACT_EDITOR__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__UNDER_USER_CONTROL = EditorPackage.ABSTRACT_EDITOR__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__UML_ELEMENT_UID = EditorPackage.ABSTRACT_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__LABEL_OVERRIDE = EditorPackage.ABSTRACT_EDITOR__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__EDITABILITY = EditorPackage.ABSTRACT_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__VISIBILITY = EditorPackage.ABSTRACT_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__IGNORED_ELEMENTS = EditorPackage.ABSTRACT_EDITOR__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__PAGES = EditorPackage.ABSTRACT_EDITOR__PAGES;

	/**
	 * The feature id for the '<em><b>Linked Uml Resource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR__LINKED_UML_RESOURCE = EditorPackage.ABSTRACT_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Embedded Task Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_TASK_EDITOR_FEATURE_COUNT = EditorPackage.ABSTRACT_EDITOR_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.model.ClassUserInteractionModel <em>Class User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class User Interaction Model</em>'.
	 * @see org.opaeum.uim.model.ClassUserInteractionModel
	 * @generated
	 */
	EClass getClassUserInteractionModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.ClassUserInteractionModel#getPrimaryEditor <em>Primary Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Primary Editor</em>'.
	 * @see org.opaeum.uim.model.ClassUserInteractionModel#getPrimaryEditor()
	 * @see #getClassUserInteractionModel()
	 * @generated
	 */
	EReference getClassUserInteractionModel_PrimaryEditor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.model.ClassUserInteractionModel#getSecondaryEditors <em>Secondary Editors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Secondary Editors</em>'.
	 * @see org.opaeum.uim.model.ClassUserInteractionModel#getSecondaryEditors()
	 * @see #getClassUserInteractionModel()
	 * @generated
	 */
	EReference getClassUserInteractionModel_SecondaryEditors();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.ClassUserInteractionModel#getNewObjectWizard <em>New Object Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>New Object Wizard</em>'.
	 * @see org.opaeum.uim.model.ClassUserInteractionModel#getNewObjectWizard()
	 * @see #getClassUserInteractionModel()
	 * @generated
	 */
	EReference getClassUserInteractionModel_NewObjectWizard();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.ClassUserInteractionModel#getCubeQueryEditor <em>Cube Query Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cube Query Editor</em>'.
	 * @see org.opaeum.uim.model.ClassUserInteractionModel#getCubeQueryEditor()
	 * @see #getClassUserInteractionModel()
	 * @generated
	 */
	EReference getClassUserInteractionModel_CubeQueryEditor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel <em>Responsibility User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Responsibility User Interaction Model</em>'.
	 * @see org.opaeum.uim.model.ResponsibilityUserInteractionModel
	 * @generated
	 */
	EClass getResponsibilityUserInteractionModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Invocation Wizard</em>'.
	 * @see org.opaeum.uim.model.ResponsibilityUserInteractionModel#getInvocationWizard()
	 * @see #getResponsibilityUserInteractionModel()
	 * @generated
	 */
	EReference getResponsibilityUserInteractionModel_InvocationWizard();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel#getViewer <em>Viewer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Viewer</em>'.
	 * @see org.opaeum.uim.model.ResponsibilityUserInteractionModel#getViewer()
	 * @see #getResponsibilityUserInteractionModel()
	 * @generated
	 */
	EReference getResponsibilityUserInteractionModel_Viewer();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.model.AbstractUserInteractionModel <em>Abstract User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract User Interaction Model</em>'.
	 * @see org.opaeum.uim.model.AbstractUserInteractionModel
	 * @generated
	 */
	EClass getAbstractUserInteractionModel();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.model.AbstractUserInteractionModel#getLinkedUmlResource <em>Linked Uml Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Linked Uml Resource</em>'.
	 * @see org.opaeum.uim.model.AbstractUserInteractionModel#getLinkedUmlResource()
	 * @see #getAbstractUserInteractionModel()
	 * @generated
	 */
	EAttribute getAbstractUserInteractionModel_LinkedUmlResource();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.model.BehaviorUserInteractionModel <em>Behavior User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior User Interaction Model</em>'.
	 * @see org.opaeum.uim.model.BehaviorUserInteractionModel
	 * @generated
	 */
	EClass getBehaviorUserInteractionModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.BehaviorUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Invocation Wizard</em>'.
	 * @see org.opaeum.uim.model.BehaviorUserInteractionModel#getInvocationWizard()
	 * @see #getBehaviorUserInteractionModel()
	 * @generated
	 */
	EReference getBehaviorUserInteractionModel_InvocationWizard();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.BehaviorUserInteractionModel#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editor</em>'.
	 * @see org.opaeum.uim.model.BehaviorUserInteractionModel#getEditor()
	 * @see #getBehaviorUserInteractionModel()
	 * @generated
	 */
	EReference getBehaviorUserInteractionModel_Editor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.model.QueryInvoker <em>Query Invoker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query Invoker</em>'.
	 * @see org.opaeum.uim.model.QueryInvoker
	 * @generated
	 */
	EClass getQueryInvoker();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.QueryInvoker#getResultPage <em>Result Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result Page</em>'.
	 * @see org.opaeum.uim.model.QueryInvoker#getResultPage()
	 * @see #getQueryInvoker()
	 * @generated
	 */
	EReference getQueryInvoker_ResultPage();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.model.OperationInvocationWizard <em>Operation Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Invocation Wizard</em>'.
	 * @see org.opaeum.uim.model.OperationInvocationWizard
	 * @generated
	 */
	EClass getOperationInvocationWizard();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.model.OperationInvocationWizard#getResultPage <em>Result Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result Page</em>'.
	 * @see org.opaeum.uim.model.OperationInvocationWizard#getResultPage()
	 * @see #getOperationInvocationWizard()
	 * @generated
	 */
	EReference getOperationInvocationWizard_ResultPage();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.model.EmbeddedTaskEditor <em>Embedded Task Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Embedded Task Editor</em>'.
	 * @see org.opaeum.uim.model.EmbeddedTaskEditor
	 * @generated
	 */
	EClass getEmbeddedTaskEditor();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.model.impl.ClassUserInteractionModelImpl <em>Class User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.model.impl.ClassUserInteractionModelImpl
		 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getClassUserInteractionModel()
		 * @generated
		 */
		EClass CLASS_USER_INTERACTION_MODEL = eINSTANCE.getClassUserInteractionModel();

		/**
		 * The meta object literal for the '<em><b>Primary Editor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR = eINSTANCE.getClassUserInteractionModel_PrimaryEditor();

		/**
		 * The meta object literal for the '<em><b>Secondary Editors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS = eINSTANCE.getClassUserInteractionModel_SecondaryEditors();

		/**
		 * The meta object literal for the '<em><b>New Object Wizard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD = eINSTANCE.getClassUserInteractionModel_NewObjectWizard();

		/**
		 * The meta object literal for the '<em><b>Cube Query Editor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_USER_INTERACTION_MODEL__CUBE_QUERY_EDITOR = eINSTANCE.getClassUserInteractionModel_CubeQueryEditor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.model.impl.ResponsibilityUserInteractionModelImpl <em>Responsibility User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.model.impl.ResponsibilityUserInteractionModelImpl
		 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getResponsibilityUserInteractionModel()
		 * @generated
		 */
		EClass RESPONSIBILITY_USER_INTERACTION_MODEL = eINSTANCE.getResponsibilityUserInteractionModel();

		/**
		 * The meta object literal for the '<em><b>Invocation Wizard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD = eINSTANCE.getResponsibilityUserInteractionModel_InvocationWizard();

		/**
		 * The meta object literal for the '<em><b>Viewer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER = eINSTANCE.getResponsibilityUserInteractionModel_Viewer();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.model.AbstractUserInteractionModel <em>Abstract User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.model.AbstractUserInteractionModel
		 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getAbstractUserInteractionModel()
		 * @generated
		 */
		EClass ABSTRACT_USER_INTERACTION_MODEL = eINSTANCE.getAbstractUserInteractionModel();

		/**
		 * The meta object literal for the '<em><b>Linked Uml Resource</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE = eINSTANCE.getAbstractUserInteractionModel_LinkedUmlResource();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.model.impl.BehaviorUserInteractionModelImpl <em>Behavior User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.model.impl.BehaviorUserInteractionModelImpl
		 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getBehaviorUserInteractionModel()
		 * @generated
		 */
		EClass BEHAVIOR_USER_INTERACTION_MODEL = eINSTANCE.getBehaviorUserInteractionModel();

		/**
		 * The meta object literal for the '<em><b>Invocation Wizard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_USER_INTERACTION_MODEL__INVOCATION_WIZARD = eINSTANCE.getBehaviorUserInteractionModel_InvocationWizard();

		/**
		 * The meta object literal for the '<em><b>Editor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_USER_INTERACTION_MODEL__EDITOR = eINSTANCE.getBehaviorUserInteractionModel_Editor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.model.impl.QueryInvokerImpl <em>Query Invoker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.model.impl.QueryInvokerImpl
		 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getQueryInvoker()
		 * @generated
		 */
		EClass QUERY_INVOKER = eINSTANCE.getQueryInvoker();

		/**
		 * The meta object literal for the '<em><b>Result Page</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUERY_INVOKER__RESULT_PAGE = eINSTANCE.getQueryInvoker_ResultPage();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.model.impl.OperationInvocationWizardImpl <em>Operation Invocation Wizard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.model.impl.OperationInvocationWizardImpl
		 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getOperationInvocationWizard()
		 * @generated
		 */
		EClass OPERATION_INVOCATION_WIZARD = eINSTANCE.getOperationInvocationWizard();

		/**
		 * The meta object literal for the '<em><b>Result Page</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INVOCATION_WIZARD__RESULT_PAGE = eINSTANCE.getOperationInvocationWizard_ResultPage();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.model.impl.EmbeddedTaskEditorImpl <em>Embedded Task Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.model.impl.EmbeddedTaskEditorImpl
		 * @see org.opaeum.uim.model.impl.ModelPackageImpl#getEmbeddedTaskEditor()
		 * @generated
		 */
		EClass EMBEDDED_TASK_EDITOR = eINSTANCE.getEmbeddedTaskEditor();

	}

} //ModelPackage
