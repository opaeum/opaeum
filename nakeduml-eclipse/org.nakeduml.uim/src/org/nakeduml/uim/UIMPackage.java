/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.nakeduml.uim.UIMFactory
 * @model kind="package"
 * @generated
 */
public interface UIMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "uim";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://nakeduml.sf.net/uimetamodel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "uim";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UIMPackage eINSTANCE = org.nakeduml.uim.impl.UIMPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UserInteractionElementImpl <em>User Interaction Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UserInteractionElementImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUserInteractionElement()
	 * @generated
	 */
	int USER_INTERACTION_ELEMENT = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>User Interaction Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMFormImpl <em>Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMFormImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMForm()
	 * @generated
	 */
	int UIM_FORM = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FORM__NAME = USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FORM__PANEL = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FORM_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.AbstractFolderImpl <em>Abstract Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.AbstractFolderImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getAbstractFolder()
	 * @generated
	 */
	int ABSTRACT_FOLDER = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER__NAME = USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER__CHILDREN = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UserInteractionModelImpl <em>User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UserInteractionModelImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUserInteractionModel()
	 * @generated
	 */
	int USER_INTERACTION_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__NAME = ABSTRACT_FOLDER__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__CHILDREN = ABSTRACT_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Uml Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__UML_MODEL = ABSTRACT_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY = ABSTRACT_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Secuiryt On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY = ABSTRACT_FOLDER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL_FEATURE_COUNT = ABSTRACT_FOLDER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.AbstractFormFolderImpl <em>Abstract Form Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.AbstractFormFolderImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getAbstractFormFolder()
	 * @generated
	 */
	int ABSTRACT_FORM_FOLDER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__NAME = ABSTRACT_FOLDER__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__CHILDREN = ABSTRACT_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__PARENT = ABSTRACT_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY = ABSTRACT_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY = ABSTRACT_FOLDER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Abstract Form Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER_FEATURE_COUNT = ABSTRACT_FOLDER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMComponentImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMComponent()
	 * @generated
	 */
	int UIM_COMPONENT = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__NAME = USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__SECURITY_ON_VISIBILITY = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__PARENT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMFieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMFieldImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMField()
	 * @generated
	 */
	int UIM_FIELD = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__SECURITY_ON_VISIBILITY = UIM_COMPONENT__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__PARENT = UIM_COMPONENT__PARENT;

	/**
	 * The feature id for the '<em><b>Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Control Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL_KIND = UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Label Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__LABEL_WIDTH = UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__BINDING = UIM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__SECURITY_ON_EDITABILITY = UIM_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMNavigationImpl <em>Navigation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMNavigationImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMNavigation()
	 * @generated
	 */
	int UIM_NAVIGATION = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION__SECURITY_ON_VISIBILITY = UIM_COMPONENT__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION__PARENT = UIM_COMPONENT__PARENT;

	/**
	 * The number of structural features of the '<em>Navigation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMContainerImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMContainer()
	 * @generated
	 */
	int UIM_CONTAINER = 48;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__SECURITY_ON_VISIBILITY = UIM_COMPONENT__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__PARENT = UIM_COMPONENT__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__SECURITY_ON_EDITABILITY = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__CHILDREN = UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMPanelImpl <em>Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMPanelImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMPanel()
	 * @generated
	 */
	int UIM_PANEL = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__NAME = UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__SECURITY_ON_VISIBILITY = UIM_CONTAINER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__PARENT = UIM_CONTAINER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__SECURITY_ON_EDITABILITY = UIM_CONTAINER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__CHILDREN = UIM_CONTAINER__CHILDREN;

	/**
	 * The number of structural features of the '<em>Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL_FEATURE_COUNT = UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.ClassFormImpl <em>Class Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.ClassFormImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getClassForm()
	 * @generated
	 */
	int CLASS_FORM = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__NAME = UIM_FORM__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__PANEL = UIM_FORM__PANEL;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM__FOLDER = UIM_FORM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Class Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FORM_FEATURE_COUNT = UIM_FORM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.StateFormImpl <em>State Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.StateFormImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getStateForm()
	 * @generated
	 */
	int STATE_FORM = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__NAME = UIM_FORM__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__PANEL = UIM_FORM__PANEL;

	/**
	 * The feature id for the '<em><b>State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__STATE = UIM_FORM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM__FOLDER = UIM_FORM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>State Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FORM_FEATURE_COUNT = UIM_FORM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.ModelSecurityConstraintImpl <em>Model Security Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.ModelSecurityConstraintImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getModelSecurityConstraint()
	 * @generated
	 */
	int MODEL_SECURITY_CONSTRAINT = 9;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES = 0;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = 1;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = 2;

	/**
	 * The number of structural features of the '<em>Model Security Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_SECURITY_CONSTRAINT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.OperationInvocationFormImpl <em>Operation Invocation Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.OperationInvocationFormImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getOperationInvocationForm()
	 * @generated
	 */
	int OPERATION_INVOCATION_FORM = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__NAME = UIM_FORM__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__PANEL = UIM_FORM__PANEL;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__OPERATION = UIM_FORM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM__FOLDER = UIM_FORM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Invocation Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_INVOCATION_FORM_FEATURE_COUNT = UIM_FORM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMActionImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMAction()
	 * @generated
	 */
	int UIM_ACTION = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__SECURITY_ON_VISIBILITY = UIM_COMPONENT__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__PARENT = UIM_COMPONENT__PARENT;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.OperationActionImpl <em>Operation Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.OperationActionImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getOperationAction()
	 * @generated
	 */
	int OPERATION_ACTION = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__SECURITY_ON_VISIBILITY = UIM_ACTION__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__PARENT = UIM_ACTION__PARENT;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__OPERATION = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.NavigationToOperationImpl <em>Navigation To Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.NavigationToOperationImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getNavigationToOperation()
	 * @generated
	 */
	int NAVIGATION_TO_OPERATION = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__NAME = UIM_NAVIGATION__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__SECURITY_ON_VISIBILITY = UIM_NAVIGATION__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__PARENT = UIM_NAVIGATION__PARENT;

	/**
	 * The feature id for the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__TO_FORM = UIM_NAVIGATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Navigation To Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION_FEATURE_COUNT = UIM_NAVIGATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.BuiltInActionImpl <em>Built In Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.BuiltInActionImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getBuiltInAction()
	 * @generated
	 */
	int BUILT_IN_ACTION = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__SECURITY_ON_VISIBILITY = UIM_ACTION__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__PARENT = UIM_ACTION__PARENT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__KIND = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Built In Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMControlImpl <em>Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMControlImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMControl()
	 * @generated
	 */
	int UIM_CONTROL = 16;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTROL__WIDTH = 0;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTROL__FIELD = 1;

	/**
	 * The number of structural features of the '<em>Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTROL_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.NavigationToEntityImpl <em>Navigation To Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.NavigationToEntityImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getNavigationToEntity()
	 * @generated
	 */
	int NAVIGATION_TO_ENTITY = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__NAME = UIM_NAVIGATION__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__SECURITY_ON_VISIBILITY = UIM_NAVIGATION__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__PARENT = UIM_NAVIGATION__PARENT;

	/**
	 * The feature id for the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__TO_FORM = UIM_NAVIGATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__BINDING = UIM_NAVIGATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Navigation To Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY_FEATURE_COUNT = UIM_NAVIGATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.TransitionActionImpl <em>Transition Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.TransitionActionImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getTransitionAction()
	 * @generated
	 */
	int TRANSITION_ACTION = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__NAME = UIM_ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__SECURITY_ON_VISIBILITY = UIM_ACTION__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__PARENT = UIM_ACTION__PARENT;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__TRANSITION = UIM_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Transition Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION_FEATURE_COUNT = UIM_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.OperationTaskFormImpl <em>Operation Task Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.OperationTaskFormImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getOperationTaskForm()
	 * @generated
	 */
	int OPERATION_TASK_FORM = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__NAME = UIM_FORM__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__PANEL = UIM_FORM__PANEL;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__OPERATION = UIM_FORM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM__FOLDER = UIM_FORM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Task Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_TASK_FORM_FEATURE_COUNT = UIM_FORM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.ActionTaskFormImpl <em>Action Task Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.ActionTaskFormImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getActionTaskForm()
	 * @generated
	 */
	int ACTION_TASK_FORM = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__NAME = UIM_FORM__NAME;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__PANEL = UIM_FORM__PANEL;

	/**
	 * The feature id for the '<em><b>Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__ACTION = UIM_FORM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM__FOLDER = UIM_FORM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action Task Form</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TASK_FORM_FEATURE_COUNT = UIM_FORM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.ChildSecurityConstraintImpl <em>Child Security Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.ChildSecurityConstraintImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getChildSecurityConstraint()
	 * @generated
	 */
	int CHILD_SECURITY_CONSTRAINT = 22;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_SECURITY_CONSTRAINT__REQUIRED_ROLES = MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_SECURITY_CONSTRAINT__INHERIT_FROM_PARENT = MODEL_SECURITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Child Security Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHILD_SECURITY_CONSTRAINT_FEATURE_COUNT = MODEL_SECURITY_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMLayoutImpl <em>Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMLayoutImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMLayout()
	 * @generated
	 */
	int UIM_LAYOUT = 51;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__NAME = UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__SECURITY_ON_VISIBILITY = UIM_CONTAINER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__PARENT = UIM_CONTAINER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__SECURITY_ON_EDITABILITY = UIM_CONTAINER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__CHILDREN = UIM_CONTAINER__CHILDREN;

	/**
	 * The number of structural features of the '<em>Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT_FEATURE_COUNT = UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMGridLayoutImpl <em>Grid Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMGridLayoutImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMGridLayout()
	 * @generated
	 */
	int UIM_GRID_LAYOUT = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__SECURITY_ON_VISIBILITY = UIM_LAYOUT__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__SECURITY_ON_EDITABILITY = UIM_LAYOUT__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Number Of Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__NUMBER_OF_COLUMNS = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Grid Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.MasterComponent <em>Master Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.MasterComponent
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getMasterComponent()
	 * @generated
	 */
	int MASTER_COMPONENT = 50;

	/**
	 * The feature id for the '<em><b>Detail Panels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_COMPONENT__DETAIL_PANELS = 0;

	/**
	 * The number of structural features of the '<em>Master Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_COMPONENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMDataTableImpl <em>Data Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMDataTableImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMDataTable()
	 * @generated
	 */
	int UIM_DATA_TABLE = 24;

	/**
	 * The feature id for the '<em><b>Detail Panels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__DETAIL_PANELS = MASTER_COMPONENT__DETAIL_PANELS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__NAME = MASTER_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__SECURITY_ON_VISIBILITY = MASTER_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__PARENT = MASTER_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__SECURITY_ON_EDITABILITY = MASTER_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__CHILDREN = MASTER_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__BINDING = MASTER_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Data Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMBindingImpl <em>Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMBindingImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMBinding()
	 * @generated
	 */
	int UIM_BINDING = 25;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING__NEXT = 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING__ELEMENT = 1;

	/**
	 * The number of structural features of the '<em>Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.PropertyRefImpl <em>Property Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.PropertyRefImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getPropertyRef()
	 * @generated
	 */
	int PROPERTY_REF = 26;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__BINDING = 0;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__PREVIOUS = 1;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__NEXT = 2;

	/**
	 * The feature id for the '<em><b>Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__PROPERTY = 3;

	/**
	 * The number of structural features of the '<em>Property Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMDataColumnImpl <em>Data Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMDataColumnImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMDataColumn()
	 * @generated
	 */
	int UIM_DATA_COLUMN = 27;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__NAME = UIM_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__SECURITY_ON_VISIBILITY = UIM_PANEL__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__PARENT = UIM_PANEL__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__SECURITY_ON_EDITABILITY = UIM_PANEL__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__CHILDREN = UIM_PANEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__WIDTH = UIM_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN_FEATURE_COUNT = UIM_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.TableBindingImpl <em>Table Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.TableBindingImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getTableBinding()
	 * @generated
	 */
	int TABLE_BINDING = 28;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING__NEXT = UIM_BINDING__NEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING__ELEMENT = UIM_BINDING__ELEMENT;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING__TABLE = UIM_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Table Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING_FEATURE_COUNT = UIM_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.FieldBindingImpl <em>Field Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.FieldBindingImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getFieldBinding()
	 * @generated
	 */
	int FIELD_BINDING = 29;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING__NEXT = UIM_BINDING__NEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING__ELEMENT = UIM_BINDING__ELEMENT;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING__FIELD = UIM_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Field Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING_FEATURE_COUNT = UIM_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.FormPanelImpl <em>Form Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.FormPanelImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getFormPanel()
	 * @generated
	 */
	int FORM_PANEL = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__NAME = UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__SECURITY_ON_VISIBILITY = UIM_CONTAINER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__PARENT = UIM_CONTAINER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__SECURITY_ON_EDITABILITY = UIM_CONTAINER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__CHILDREN = UIM_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Form</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__FORM = UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Form Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL_FEATURE_COUNT = UIM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.OperationContainingFolderImpl <em>Operation Containing Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.OperationContainingFolderImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getOperationContainingFolder()
	 * @generated
	 */
	int OPERATION_CONTAINING_FOLDER = 34;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__NAME = ABSTRACT_FORM_FOLDER__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__CHILDREN = ABSTRACT_FORM_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__PARENT = ABSTRACT_FORM_FOLDER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__SECURITY_ON_VISIBILITY = ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__SECURITY_ON_EDITABILITY = ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Operation Invocation Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Class Form</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__CLASS_FORM = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Containing Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER_FEATURE_COUNT = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.StateMachineFolderImpl <em>State Machine Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.StateMachineFolderImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getStateMachineFolder()
	 * @generated
	 */
	int STATE_MACHINE_FOLDER = 31;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__NAME = OPERATION_CONTAINING_FOLDER__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__CHILDREN = OPERATION_CONTAINING_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__PARENT = OPERATION_CONTAINING_FOLDER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__SECURITY_ON_VISIBILITY = OPERATION_CONTAINING_FOLDER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__SECURITY_ON_EDITABILITY = OPERATION_CONTAINING_FOLDER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Operation Invocation Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__OPERATION_INVOCATION_FORMS = OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS;

	/**
	 * The feature id for the '<em><b>Class Form</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__CLASS_FORM = OPERATION_CONTAINING_FOLDER__CLASS_FORM;

	/**
	 * The feature id for the '<em><b>State Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__STATE_FORMS = OPERATION_CONTAINING_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>State Machine</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__STATE_MACHINE = OPERATION_CONTAINING_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>State Machine Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER_FEATURE_COUNT = OPERATION_CONTAINING_FOLDER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.EntityFolderImpl <em>Entity Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.EntityFolderImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getEntityFolder()
	 * @generated
	 */
	int ENTITY_FOLDER = 32;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__NAME = OPERATION_CONTAINING_FOLDER__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__CHILDREN = OPERATION_CONTAINING_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__PARENT = OPERATION_CONTAINING_FOLDER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__SECURITY_ON_VISIBILITY = OPERATION_CONTAINING_FOLDER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__SECURITY_ON_EDITABILITY = OPERATION_CONTAINING_FOLDER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Operation Invocation Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__OPERATION_INVOCATION_FORMS = OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS;

	/**
	 * The feature id for the '<em><b>Class Form</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__CLASS_FORM = OPERATION_CONTAINING_FOLDER__CLASS_FORM;

	/**
	 * The feature id for the '<em><b>Operation Task Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__OPERATION_TASK_FORMS = OPERATION_CONTAINING_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__ENTITY = OPERATION_CONTAINING_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Entity Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER_FEATURE_COUNT = OPERATION_CONTAINING_FOLDER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.ActivityFolderImpl <em>Activity Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.ActivityFolderImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getActivityFolder()
	 * @generated
	 */
	int ACTIVITY_FOLDER = 33;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__NAME = ABSTRACT_FORM_FOLDER__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__CHILDREN = ABSTRACT_FORM_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__PARENT = ABSTRACT_FORM_FOLDER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__SECURITY_ON_VISIBILITY = ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__SECURITY_ON_EDITABILITY = ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Action Task Forms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__ACTION_TASK_FORMS = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__ACTIVITY = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Activity Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER_FEATURE_COUNT = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.NavigationBindingImpl <em>Navigation Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.NavigationBindingImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getNavigationBinding()
	 * @generated
	 */
	int NAVIGATION_BINDING = 35;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BINDING__NEXT = UIM_BINDING__NEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BINDING__ELEMENT = UIM_BINDING__ELEMENT;

	/**
	 * The feature id for the '<em><b>Navigation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BINDING__NAVIGATION = UIM_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Navigation Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BINDING_FEATURE_COUNT = UIM_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.DetailPanelImpl <em>Detail Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.DetailPanelImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getDetailPanel()
	 * @generated
	 */
	int DETAIL_PANEL = 36;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__NAME = UIM_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__SECURITY_ON_VISIBILITY = UIM_PANEL__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__PARENT = UIM_PANEL__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__SECURITY_ON_EDITABILITY = UIM_PANEL__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__CHILDREN = UIM_PANEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Master Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__MASTER_COMPONENT = UIM_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Detail Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL_FEATURE_COUNT = UIM_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.PackageFolderImpl <em>Package Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.PackageFolderImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getPackageFolder()
	 * @generated
	 */
	int PACKAGE_FOLDER = 37;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__NAME = ABSTRACT_FORM_FOLDER__NAME;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__CHILDREN = ABSTRACT_FORM_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__PARENT = ABSTRACT_FORM_FOLDER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__SECURITY_ON_VISIBILITY = ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__SECURITY_ON_EDITABILITY = ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Uml Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__UML_PACKAGE = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Package Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER_FEATURE_COUNT = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMTabPanelImpl <em>Tab Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMTabPanelImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMTabPanel()
	 * @generated
	 */
	int UIM_TAB_PANEL = 38;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__NAME = UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__SECURITY_ON_VISIBILITY = UIM_CONTAINER__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__PARENT = UIM_CONTAINER__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__SECURITY_ON_EDITABILITY = UIM_CONTAINER__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__CHILDREN = UIM_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Active Tab Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__ACTIVE_TAB_INDEX = UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tab Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL_FEATURE_COUNT = UIM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMTabImpl <em>Tab</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMTabImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMTab()
	 * @generated
	 */
	int UIM_TAB = 39;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__NAME = UIM_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__SECURITY_ON_VISIBILITY = UIM_PANEL__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__PARENT = UIM_PANEL__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__SECURITY_ON_EDITABILITY = UIM_PANEL__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__CHILDREN = UIM_PANEL__CHILDREN;

	/**
	 * The number of structural features of the '<em>Tab</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_FEATURE_COUNT = UIM_PANEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMCheckBoxImpl <em>Check Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMCheckBoxImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMCheckBox()
	 * @generated
	 */
	int UIM_CHECK_BOX = 40;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CHECK_BOX__WIDTH = UIM_CONTROL__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CHECK_BOX__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The number of structural features of the '<em>Check Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CHECK_BOX_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMLookupImpl <em>Lookup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMLookupImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMLookup()
	 * @generated
	 */
	int UIM_LOOKUP = 41;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP__WIDTH = UIM_CONTROL__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP__LOOKUP_SOURCE = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Lookup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LOOKUP_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.LookupBindingImpl <em>Lookup Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.LookupBindingImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getLookupBinding()
	 * @generated
	 */
	int LOOKUP_BINDING = 42;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING__NEXT = UIM_BINDING__NEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING__ELEMENT = UIM_BINDING__ELEMENT;

	/**
	 * The feature id for the '<em><b>Lookup</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING__LOOKUP = UIM_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Lookup Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING_FEATURE_COUNT = UIM_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMTextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMTextImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMText()
	 * @generated
	 */
	int UIM_TEXT = 43;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT__WIDTH = UIM_CONTROL__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The number of structural features of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMTextAreaImpl <em>Text Area</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMTextAreaImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMTextArea()
	 * @generated
	 */
	int UIM_TEXT_AREA = 44;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA__WIDTH = UIM_CONTROL__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The feature id for the '<em><b>Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA__ROWS = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Area</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TEXT_AREA_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMDropdownImpl <em>Dropdown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMDropdownImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMDropdown()
	 * @generated
	 */
	int UIM_DROPDOWN = 45;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN__WIDTH = UIM_LOOKUP__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Dropdown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DROPDOWN_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMDatePopupImpl <em>Date Popup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMDatePopupImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMDatePopup()
	 * @generated
	 */
	int UIM_DATE_POPUP = 46;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_POPUP__WIDTH = UIM_CONTROL__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_POPUP__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The number of structural features of the '<em>Date Popup</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATE_POPUP_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMSingleSelectListBoxImpl <em>Single Select List Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMSingleSelectListBoxImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMSingleSelectListBox()
	 * @generated
	 */
	int UIM_SINGLE_SELECT_LIST_BOX = 47;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__WIDTH = UIM_LOOKUP__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The feature id for the '<em><b>Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX__ROWS = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Select List Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_LIST_BOX_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMSingleSelectTreeViewImpl <em>Single Select Tree View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMSingleSelectTreeViewImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMSingleSelectTreeView()
	 * @generated
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW = 49;

	/**
	 * The feature id for the '<em><b>Detail Panels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW__DETAIL_PANELS = MASTER_COMPONENT__DETAIL_PANELS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW__WIDTH = MASTER_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW__FIELD = MASTER_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW__LOOKUP_SOURCE = MASTER_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Single Select Tree View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_TREE_VIEW_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMToolbarLayoutImpl <em>Toolbar Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMToolbarLayoutImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMToolbarLayout()
	 * @generated
	 */
	int UIM_TOOLBAR_LAYOUT = 52;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__SECURITY_ON_VISIBILITY = UIM_LAYOUT__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__SECURITY_ON_EDITABILITY = UIM_LAYOUT__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The number of structural features of the '<em>Toolbar Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMBorderLayoutImpl <em>Border Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMBorderLayoutImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMBorderLayout()
	 * @generated
	 */
	int UIM_BORDER_LAYOUT = 53;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__SECURITY_ON_VISIBILITY = UIM_LAYOUT__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__SECURITY_ON_EDITABILITY = UIM_LAYOUT__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Horizontal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__HORIZONTAL = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Border Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMXYLayoutImpl <em>XY Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMXYLayoutImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMXYLayout()
	 * @generated
	 */
	int UIM_XY_LAYOUT = 54;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__SECURITY_ON_VISIBILITY = UIM_LAYOUT__SECURITY_ON_VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__SECURITY_ON_EDITABILITY = UIM_LAYOUT__SECURITY_ON_EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The number of structural features of the '<em>XY Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMMultiSelectTreeViewImpl <em>Multi Select Tree View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMMultiSelectTreeViewImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMMultiSelectTreeView()
	 * @generated
	 */
	int UIM_MULTI_SELECT_TREE_VIEW = 55;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW__WIDTH = UIM_LOOKUP__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Multi Select Tree View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_TREE_VIEW_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMMultiSelectListBoxImpl <em>Multi Select List Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMMultiSelectListBoxImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMMultiSelectListBox()
	 * @generated
	 */
	int UIM_MULTI_SELECT_LIST_BOX = 56;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX__WIDTH = UIM_LOOKUP__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Multi Select List Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_LIST_BOX_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMMultiSelectPopupSearchImpl <em>Multi Select Popup Search</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMMultiSelectPopupSearchImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMMultiSelectPopupSearch()
	 * @generated
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH = 57;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH__WIDTH = UIM_LOOKUP__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Multi Select Popup Search</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_MULTI_SELECT_POPUP_SEARCH_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMSingleSelectPopupSearchImpl <em>Single Select Popup Search</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMSingleSelectPopupSearchImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMSingleSelectPopupSearch()
	 * @generated
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH = 58;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH__WIDTH = UIM_LOOKUP__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH__FIELD = UIM_LOOKUP__FIELD;

	/**
	 * The feature id for the '<em><b>Lookup Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH__LOOKUP_SOURCE = UIM_LOOKUP__LOOKUP_SOURCE;

	/**
	 * The number of structural features of the '<em>Single Select Popup Search</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_SINGLE_SELECT_POPUP_SEARCH_FEATURE_COUNT = UIM_LOOKUP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMToggleButtonImpl <em>Toggle Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMToggleButtonImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMToggleButton()
	 * @generated
	 */
	int UIM_TOGGLE_BUTTON = 59;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOGGLE_BUTTON__WIDTH = UIM_CONTROL__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOGGLE_BUTTON__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The number of structural features of the '<em>Toggle Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOGGLE_BUTTON_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UIMNumberScrollerImpl <em>Number Scroller</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UIMNumberScrollerImpl
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMNumberScroller()
	 * @generated
	 */
	int UIM_NUMBER_SCROLLER = 60;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NUMBER_SCROLLER__WIDTH = UIM_CONTROL__WIDTH;

	/**
	 * The feature id for the '<em><b>Field</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NUMBER_SCROLLER__FIELD = UIM_CONTROL__FIELD;

	/**
	 * The number of structural features of the '<em>Number Scroller</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NUMBER_SCROLLER_FEATURE_COUNT = UIM_CONTROL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.ActionKind <em>Action Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.ActionKind
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getActionKind()
	 * @generated
	 */
	int ACTION_KIND = 61;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.ControlKind <em>Control Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.ControlKind
	 * @see org.nakeduml.uim.impl.UIMPackageImpl#getControlKind()
	 * @generated
	 */
	int CONTROL_KIND = 62;


	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMForm <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form</em>'.
	 * @see org.nakeduml.uim.UIMForm
	 * @generated
	 */
	EClass getUIMForm();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMForm#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Panel</em>'.
	 * @see org.nakeduml.uim.UIMForm#getPanel()
	 * @see #getUIMForm()
	 * @generated
	 */
	EReference getUIMForm_Panel();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UserInteractionModel <em>User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interaction Model</em>'.
	 * @see org.nakeduml.uim.UserInteractionModel
	 * @generated
	 */
	EClass getUserInteractionModel();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.UserInteractionModel#getUmlModel <em>Uml Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Uml Model</em>'.
	 * @see org.nakeduml.uim.UserInteractionModel#getUmlModel()
	 * @see #getUserInteractionModel()
	 * @generated
	 */
	EReference getUserInteractionModel_UmlModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UserInteractionModel#getSecurityOnVisibility <em>Security On Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Security On Visibility</em>'.
	 * @see org.nakeduml.uim.UserInteractionModel#getSecurityOnVisibility()
	 * @see #getUserInteractionModel()
	 * @generated
	 */
	EReference getUserInteractionModel_SecurityOnVisibility();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UserInteractionModel#getSecuirytOnEditability <em>Secuiryt On Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Secuiryt On Editability</em>'.
	 * @see org.nakeduml.uim.UserInteractionModel#getSecuirytOnEditability()
	 * @see #getUserInteractionModel()
	 * @generated
	 */
	EReference getUserInteractionModel_SecuirytOnEditability();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.AbstractFormFolder <em>Abstract Form Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Form Folder</em>'.
	 * @see org.nakeduml.uim.AbstractFormFolder
	 * @generated
	 */
	EClass getAbstractFormFolder();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.AbstractFormFolder#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.nakeduml.uim.AbstractFormFolder#getParent()
	 * @see #getAbstractFormFolder()
	 * @generated
	 */
	EReference getAbstractFormFolder_Parent();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.AbstractFormFolder#getSecurityOnVisibility <em>Security On Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Security On Visibility</em>'.
	 * @see org.nakeduml.uim.AbstractFormFolder#getSecurityOnVisibility()
	 * @see #getAbstractFormFolder()
	 * @generated
	 */
	EReference getAbstractFormFolder_SecurityOnVisibility();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.AbstractFormFolder#getSecurityOnEditability <em>Security On Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Security On Editability</em>'.
	 * @see org.nakeduml.uim.AbstractFormFolder#getSecurityOnEditability()
	 * @see #getAbstractFormFolder()
	 * @generated
	 */
	EReference getAbstractFormFolder_SecurityOnEditability();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.AbstractFolder <em>Abstract Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Folder</em>'.
	 * @see org.nakeduml.uim.AbstractFolder
	 * @generated
	 */
	EClass getAbstractFolder();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.AbstractFolder#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nakeduml.uim.AbstractFolder#getChildren()
	 * @see #getAbstractFolder()
	 * @generated
	 */
	EReference getAbstractFolder_Children();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see org.nakeduml.uim.UIMField
	 * @generated
	 */
	EClass getUIMField();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMField#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Control</em>'.
	 * @see org.nakeduml.uim.UIMField#getControl()
	 * @see #getUIMField()
	 * @generated
	 */
	EReference getUIMField_Control();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMField#getControlKind <em>Control Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Kind</em>'.
	 * @see org.nakeduml.uim.UIMField#getControlKind()
	 * @see #getUIMField()
	 * @generated
	 */
	EAttribute getUIMField_ControlKind();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMField#getLabelWidth <em>Label Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Width</em>'.
	 * @see org.nakeduml.uim.UIMField#getLabelWidth()
	 * @see #getUIMField()
	 * @generated
	 */
	EAttribute getUIMField_LabelWidth();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMField#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.nakeduml.uim.UIMField#getBinding()
	 * @see #getUIMField()
	 * @generated
	 */
	EReference getUIMField_Binding();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMField#getSecurityOnEditability <em>Security On Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Security On Editability</em>'.
	 * @see org.nakeduml.uim.UIMField#getSecurityOnEditability()
	 * @see #getUIMField()
	 * @generated
	 */
	EReference getUIMField_SecurityOnEditability();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMNavigation <em>Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation</em>'.
	 * @see org.nakeduml.uim.UIMNavigation
	 * @generated
	 */
	EClass getUIMNavigation();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Panel</em>'.
	 * @see org.nakeduml.uim.UIMPanel
	 * @generated
	 */
	EClass getUIMPanel();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.ClassForm <em>Class Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Form</em>'.
	 * @see org.nakeduml.uim.ClassForm
	 * @generated
	 */
	EClass getClassForm();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.ClassForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.ClassForm#getFolder()
	 * @see #getClassForm()
	 * @generated
	 */
	EReference getClassForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.StateForm <em>State Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Form</em>'.
	 * @see org.nakeduml.uim.StateForm
	 * @generated
	 */
	EClass getStateForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.StateForm#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>State</em>'.
	 * @see org.nakeduml.uim.StateForm#getState()
	 * @see #getStateForm()
	 * @generated
	 */
	EReference getStateForm_State();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.StateForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.StateForm#getFolder()
	 * @see #getStateForm()
	 * @generated
	 */
	EReference getStateForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.ModelSecurityConstraint <em>Model Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Security Constraint</em>'.
	 * @see org.nakeduml.uim.ModelSecurityConstraint
	 * @generated
	 */
	EClass getModelSecurityConstraint();

	/**
	 * Returns the meta object for the reference list '{@link org.nakeduml.uim.ModelSecurityConstraint#getRequiredRoles <em>Required Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Required Roles</em>'.
	 * @see org.nakeduml.uim.ModelSecurityConstraint#getRequiredRoles()
	 * @see #getModelSecurityConstraint()
	 * @generated
	 */
	EReference getModelSecurityConstraint_RequiredRoles();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.ModelSecurityConstraint#isRequiresGroupOwnership <em>Requires Group Ownership</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Group Ownership</em>'.
	 * @see org.nakeduml.uim.ModelSecurityConstraint#isRequiresGroupOwnership()
	 * @see #getModelSecurityConstraint()
	 * @generated
	 */
	EAttribute getModelSecurityConstraint_RequiresGroupOwnership();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.ModelSecurityConstraint#isRequiresOwnership <em>Requires Ownership</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Ownership</em>'.
	 * @see org.nakeduml.uim.ModelSecurityConstraint#isRequiresOwnership()
	 * @see #getModelSecurityConstraint()
	 * @generated
	 */
	EAttribute getModelSecurityConstraint_RequiresOwnership();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.OperationInvocationForm <em>Operation Invocation Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Invocation Form</em>'.
	 * @see org.nakeduml.uim.OperationInvocationForm
	 * @generated
	 */
	EClass getOperationInvocationForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.OperationInvocationForm#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see org.nakeduml.uim.OperationInvocationForm#getOperation()
	 * @see #getOperationInvocationForm()
	 * @generated
	 */
	EReference getOperationInvocationForm_Operation();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.OperationInvocationForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.OperationInvocationForm#getFolder()
	 * @see #getOperationInvocationForm()
	 * @generated
	 */
	EReference getOperationInvocationForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.nakeduml.uim.UIMComponent
	 * @generated
	 */
	EClass getUIMComponent();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMComponent#getSecurityOnVisibility <em>Security On Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Security On Visibility</em>'.
	 * @see org.nakeduml.uim.UIMComponent#getSecurityOnVisibility()
	 * @see #getUIMComponent()
	 * @generated
	 */
	EReference getUIMComponent_SecurityOnVisibility();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.UIMComponent#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.nakeduml.uim.UIMComponent#getParent()
	 * @see #getUIMComponent()
	 * @generated
	 */
	EReference getUIMComponent_Parent();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UserInteractionElement <em>User Interaction Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interaction Element</em>'.
	 * @see org.nakeduml.uim.UserInteractionElement
	 * @generated
	 */
	EClass getUserInteractionElement();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UserInteractionElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nakeduml.uim.UserInteractionElement#getName()
	 * @see #getUserInteractionElement()
	 * @generated
	 */
	EAttribute getUserInteractionElement_Name();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.OperationAction <em>Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Action</em>'.
	 * @see org.nakeduml.uim.OperationAction
	 * @generated
	 */
	EClass getOperationAction();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.OperationAction#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see org.nakeduml.uim.OperationAction#getOperation()
	 * @see #getOperationAction()
	 * @generated
	 */
	EReference getOperationAction_Operation();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.NavigationToOperation <em>Navigation To Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation To Operation</em>'.
	 * @see org.nakeduml.uim.NavigationToOperation
	 * @generated
	 */
	EClass getNavigationToOperation();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.NavigationToOperation#getToForm <em>To Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Form</em>'.
	 * @see org.nakeduml.uim.NavigationToOperation#getToForm()
	 * @see #getNavigationToOperation()
	 * @generated
	 */
	EReference getNavigationToOperation_ToForm();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.BuiltInAction <em>Built In Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Built In Action</em>'.
	 * @see org.nakeduml.uim.BuiltInAction
	 * @generated
	 */
	EClass getBuiltInAction();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.BuiltInAction#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.nakeduml.uim.BuiltInAction#getKind()
	 * @see #getBuiltInAction()
	 * @generated
	 */
	EAttribute getBuiltInAction_Kind();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control</em>'.
	 * @see org.nakeduml.uim.UIMControl
	 * @generated
	 */
	EClass getUIMControl();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMControl#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.nakeduml.uim.UIMControl#getWidth()
	 * @see #getUIMControl()
	 * @generated
	 */
	EAttribute getUIMControl_Width();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.UIMControl#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Field</em>'.
	 * @see org.nakeduml.uim.UIMControl#getField()
	 * @see #getUIMControl()
	 * @generated
	 */
	EReference getUIMControl_Field();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.NavigationToEntity <em>Navigation To Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation To Entity</em>'.
	 * @see org.nakeduml.uim.NavigationToEntity
	 * @generated
	 */
	EClass getNavigationToEntity();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.NavigationToEntity#getToForm <em>To Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Form</em>'.
	 * @see org.nakeduml.uim.NavigationToEntity#getToForm()
	 * @see #getNavigationToEntity()
	 * @generated
	 */
	EReference getNavigationToEntity_ToForm();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.NavigationToEntity#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.nakeduml.uim.NavigationToEntity#getBinding()
	 * @see #getNavigationToEntity()
	 * @generated
	 */
	EReference getNavigationToEntity_Binding();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.TransitionAction <em>Transition Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Action</em>'.
	 * @see org.nakeduml.uim.TransitionAction
	 * @generated
	 */
	EClass getTransitionAction();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.TransitionAction#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Transition</em>'.
	 * @see org.nakeduml.uim.TransitionAction#getTransition()
	 * @see #getTransitionAction()
	 * @generated
	 */
	EReference getTransitionAction_Transition();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.OperationTaskForm <em>Operation Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Task Form</em>'.
	 * @see org.nakeduml.uim.OperationTaskForm
	 * @generated
	 */
	EClass getOperationTaskForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.OperationTaskForm#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see org.nakeduml.uim.OperationTaskForm#getOperation()
	 * @see #getOperationTaskForm()
	 * @generated
	 */
	EReference getOperationTaskForm_Operation();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.OperationTaskForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.OperationTaskForm#getFolder()
	 * @see #getOperationTaskForm()
	 * @generated
	 */
	EReference getOperationTaskForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.ActionTaskForm <em>Action Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Task Form</em>'.
	 * @see org.nakeduml.uim.ActionTaskForm
	 * @generated
	 */
	EClass getActionTaskForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.ActionTaskForm#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Action</em>'.
	 * @see org.nakeduml.uim.ActionTaskForm#getAction()
	 * @see #getActionTaskForm()
	 * @generated
	 */
	EReference getActionTaskForm_Action();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.ActionTaskForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.ActionTaskForm#getFolder()
	 * @see #getActionTaskForm()
	 * @generated
	 */
	EReference getActionTaskForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.nakeduml.uim.UIMAction
	 * @generated
	 */
	EClass getUIMAction();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.ChildSecurityConstraint <em>Child Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Child Security Constraint</em>'.
	 * @see org.nakeduml.uim.ChildSecurityConstraint
	 * @generated
	 */
	EClass getChildSecurityConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.ChildSecurityConstraint#isInheritFromParent <em>Inherit From Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit From Parent</em>'.
	 * @see org.nakeduml.uim.ChildSecurityConstraint#isInheritFromParent()
	 * @see #getChildSecurityConstraint()
	 * @generated
	 */
	EAttribute getChildSecurityConstraint_InheritFromParent();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMGridLayout <em>Grid Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grid Layout</em>'.
	 * @see org.nakeduml.uim.UIMGridLayout
	 * @generated
	 */
	EClass getUIMGridLayout();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMGridLayout#getNumberOfColumns <em>Number Of Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Columns</em>'.
	 * @see org.nakeduml.uim.UIMGridLayout#getNumberOfColumns()
	 * @see #getUIMGridLayout()
	 * @generated
	 */
	EAttribute getUIMGridLayout_NumberOfColumns();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMDataTable <em>Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Table</em>'.
	 * @see org.nakeduml.uim.UIMDataTable
	 * @generated
	 */
	EClass getUIMDataTable();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMDataTable#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.nakeduml.uim.UIMDataTable#getBinding()
	 * @see #getUIMDataTable()
	 * @generated
	 */
	EReference getUIMDataTable_Binding();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding</em>'.
	 * @see org.nakeduml.uim.UIMBinding
	 * @generated
	 */
	EClass getUIMBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMBinding#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Next</em>'.
	 * @see org.nakeduml.uim.UIMBinding#getNext()
	 * @see #getUIMBinding()
	 * @generated
	 */
	EReference getUIMBinding_Next();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.UIMBinding#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see org.nakeduml.uim.UIMBinding#getElement()
	 * @see #getUIMBinding()
	 * @generated
	 */
	EReference getUIMBinding_Element();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.PropertyRef <em>Property Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Ref</em>'.
	 * @see org.nakeduml.uim.PropertyRef
	 * @generated
	 */
	EClass getPropertyRef();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.PropertyRef#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Binding</em>'.
	 * @see org.nakeduml.uim.PropertyRef#getBinding()
	 * @see #getPropertyRef()
	 * @generated
	 */
	EReference getPropertyRef_Binding();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.PropertyRef#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Previous</em>'.
	 * @see org.nakeduml.uim.PropertyRef#getPrevious()
	 * @see #getPropertyRef()
	 * @generated
	 */
	EReference getPropertyRef_Previous();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.PropertyRef#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Next</em>'.
	 * @see org.nakeduml.uim.PropertyRef#getNext()
	 * @see #getPropertyRef()
	 * @generated
	 */
	EReference getPropertyRef_Next();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.PropertyRef#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Property</em>'.
	 * @see org.nakeduml.uim.PropertyRef#getProperty()
	 * @see #getPropertyRef()
	 * @generated
	 */
	EReference getPropertyRef_Property();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMDataColumn <em>Data Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Column</em>'.
	 * @see org.nakeduml.uim.UIMDataColumn
	 * @generated
	 */
	EClass getUIMDataColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMDataColumn#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.nakeduml.uim.UIMDataColumn#getWidth()
	 * @see #getUIMDataColumn()
	 * @generated
	 */
	EAttribute getUIMDataColumn_Width();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.TableBinding <em>Table Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Binding</em>'.
	 * @see org.nakeduml.uim.TableBinding
	 * @generated
	 */
	EClass getTableBinding();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.TableBinding#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table</em>'.
	 * @see org.nakeduml.uim.TableBinding#getTable()
	 * @see #getTableBinding()
	 * @generated
	 */
	EReference getTableBinding_Table();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.FieldBinding <em>Field Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Binding</em>'.
	 * @see org.nakeduml.uim.FieldBinding
	 * @generated
	 */
	EClass getFieldBinding();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.FieldBinding#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Field</em>'.
	 * @see org.nakeduml.uim.FieldBinding#getField()
	 * @see #getFieldBinding()
	 * @generated
	 */
	EReference getFieldBinding_Field();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.FormPanel <em>Form Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form Panel</em>'.
	 * @see org.nakeduml.uim.FormPanel
	 * @generated
	 */
	EClass getFormPanel();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.FormPanel#getForm <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Form</em>'.
	 * @see org.nakeduml.uim.FormPanel#getForm()
	 * @see #getFormPanel()
	 * @generated
	 */
	EReference getFormPanel_Form();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.StateMachineFolder <em>State Machine Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Machine Folder</em>'.
	 * @see org.nakeduml.uim.StateMachineFolder
	 * @generated
	 */
	EClass getStateMachineFolder();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.StateMachineFolder#getStateForms <em>State Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State Forms</em>'.
	 * @see org.nakeduml.uim.StateMachineFolder#getStateForms()
	 * @see #getStateMachineFolder()
	 * @generated
	 */
	EReference getStateMachineFolder_StateForms();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.StateMachineFolder#getStateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>State Machine</em>'.
	 * @see org.nakeduml.uim.StateMachineFolder#getStateMachine()
	 * @see #getStateMachineFolder()
	 * @generated
	 */
	EReference getStateMachineFolder_StateMachine();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.EntityFolder <em>Entity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Folder</em>'.
	 * @see org.nakeduml.uim.EntityFolder
	 * @generated
	 */
	EClass getEntityFolder();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.EntityFolder#getOperationTaskForms <em>Operation Task Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operation Task Forms</em>'.
	 * @see org.nakeduml.uim.EntityFolder#getOperationTaskForms()
	 * @see #getEntityFolder()
	 * @generated
	 */
	EReference getEntityFolder_OperationTaskForms();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.EntityFolder#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entity</em>'.
	 * @see org.nakeduml.uim.EntityFolder#getEntity()
	 * @see #getEntityFolder()
	 * @generated
	 */
	EReference getEntityFolder_Entity();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.ActivityFolder <em>Activity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Folder</em>'.
	 * @see org.nakeduml.uim.ActivityFolder
	 * @generated
	 */
	EClass getActivityFolder();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.ActivityFolder#getActionTaskForms <em>Action Task Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action Task Forms</em>'.
	 * @see org.nakeduml.uim.ActivityFolder#getActionTaskForms()
	 * @see #getActivityFolder()
	 * @generated
	 */
	EReference getActivityFolder_ActionTaskForms();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.ActivityFolder#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity</em>'.
	 * @see org.nakeduml.uim.ActivityFolder#getActivity()
	 * @see #getActivityFolder()
	 * @generated
	 */
	EReference getActivityFolder_Activity();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.OperationContainingFolder <em>Operation Containing Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Containing Folder</em>'.
	 * @see org.nakeduml.uim.OperationContainingFolder
	 * @generated
	 */
	EClass getOperationContainingFolder();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.OperationContainingFolder#getOperationInvocationForms <em>Operation Invocation Forms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operation Invocation Forms</em>'.
	 * @see org.nakeduml.uim.OperationContainingFolder#getOperationInvocationForms()
	 * @see #getOperationContainingFolder()
	 * @generated
	 */
	EReference getOperationContainingFolder_OperationInvocationForms();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.OperationContainingFolder#getClassForm <em>Class Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class Form</em>'.
	 * @see org.nakeduml.uim.OperationContainingFolder#getClassForm()
	 * @see #getOperationContainingFolder()
	 * @generated
	 */
	EReference getOperationContainingFolder_ClassForm();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.NavigationBinding <em>Navigation Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Binding</em>'.
	 * @see org.nakeduml.uim.NavigationBinding
	 * @generated
	 */
	EClass getNavigationBinding();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.NavigationBinding#getNavigation <em>Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Navigation</em>'.
	 * @see org.nakeduml.uim.NavigationBinding#getNavigation()
	 * @see #getNavigationBinding()
	 * @generated
	 */
	EReference getNavigationBinding_Navigation();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.DetailPanel <em>Detail Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Detail Panel</em>'.
	 * @see org.nakeduml.uim.DetailPanel
	 * @generated
	 */
	EClass getDetailPanel();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.DetailPanel#getMasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Master Component</em>'.
	 * @see org.nakeduml.uim.DetailPanel#getMasterComponent()
	 * @see #getDetailPanel()
	 * @generated
	 */
	EReference getDetailPanel_MasterComponent();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.PackageFolder <em>Package Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Folder</em>'.
	 * @see org.nakeduml.uim.PackageFolder
	 * @generated
	 */
	EClass getPackageFolder();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.PackageFolder#getUmlPackage <em>Uml Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Uml Package</em>'.
	 * @see org.nakeduml.uim.PackageFolder#getUmlPackage()
	 * @see #getPackageFolder()
	 * @generated
	 */
	EReference getPackageFolder_UmlPackage();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMTabPanel <em>Tab Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tab Panel</em>'.
	 * @see org.nakeduml.uim.UIMTabPanel
	 * @generated
	 */
	EClass getUIMTabPanel();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMTabPanel#getActiveTabIndex <em>Active Tab Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active Tab Index</em>'.
	 * @see org.nakeduml.uim.UIMTabPanel#getActiveTabIndex()
	 * @see #getUIMTabPanel()
	 * @generated
	 */
	EAttribute getUIMTabPanel_ActiveTabIndex();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMTab <em>Tab</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tab</em>'.
	 * @see org.nakeduml.uim.UIMTab
	 * @generated
	 */
	EClass getUIMTab();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMCheckBox <em>Check Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Check Box</em>'.
	 * @see org.nakeduml.uim.UIMCheckBox
	 * @generated
	 */
	EClass getUIMCheckBox();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMLookup <em>Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lookup</em>'.
	 * @see org.nakeduml.uim.UIMLookup
	 * @generated
	 */
	EClass getUIMLookup();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMLookup#getLookupSource <em>Lookup Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lookup Source</em>'.
	 * @see org.nakeduml.uim.UIMLookup#getLookupSource()
	 * @see #getUIMLookup()
	 * @generated
	 */
	EReference getUIMLookup_LookupSource();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.LookupBinding <em>Lookup Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lookup Binding</em>'.
	 * @see org.nakeduml.uim.LookupBinding
	 * @generated
	 */
	EClass getLookupBinding();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.LookupBinding#getLookup <em>Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Lookup</em>'.
	 * @see org.nakeduml.uim.LookupBinding#getLookup()
	 * @see #getLookupBinding()
	 * @generated
	 */
	EReference getLookupBinding_Lookup();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see org.nakeduml.uim.UIMText
	 * @generated
	 */
	EClass getUIMText();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMTextArea <em>Text Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Area</em>'.
	 * @see org.nakeduml.uim.UIMTextArea
	 * @generated
	 */
	EClass getUIMTextArea();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMTextArea#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rows</em>'.
	 * @see org.nakeduml.uim.UIMTextArea#getRows()
	 * @see #getUIMTextArea()
	 * @generated
	 */
	EAttribute getUIMTextArea_Rows();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMDropdown <em>Dropdown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dropdown</em>'.
	 * @see org.nakeduml.uim.UIMDropdown
	 * @generated
	 */
	EClass getUIMDropdown();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMDatePopup <em>Date Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Popup</em>'.
	 * @see org.nakeduml.uim.UIMDatePopup
	 * @generated
	 */
	EClass getUIMDatePopup();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMSingleSelectListBox <em>Single Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Select List Box</em>'.
	 * @see org.nakeduml.uim.UIMSingleSelectListBox
	 * @generated
	 */
	EClass getUIMSingleSelectListBox();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMSingleSelectListBox#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rows</em>'.
	 * @see org.nakeduml.uim.UIMSingleSelectListBox#getRows()
	 * @see #getUIMSingleSelectListBox()
	 * @generated
	 */
	EAttribute getUIMSingleSelectListBox_Rows();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see org.nakeduml.uim.UIMContainer
	 * @generated
	 */
	EClass getUIMContainer();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UIMContainer#getSecurityOnEditability <em>Security On Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Security On Editability</em>'.
	 * @see org.nakeduml.uim.UIMContainer#getSecurityOnEditability()
	 * @see #getUIMContainer()
	 * @generated
	 */
	EReference getUIMContainer_SecurityOnEditability();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.UIMContainer#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nakeduml.uim.UIMContainer#getChildren()
	 * @see #getUIMContainer()
	 * @generated
	 */
	EReference getUIMContainer_Children();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMSingleSelectTreeView <em>Single Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Select Tree View</em>'.
	 * @see org.nakeduml.uim.UIMSingleSelectTreeView
	 * @generated
	 */
	EClass getUIMSingleSelectTreeView();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.MasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Master Component</em>'.
	 * @see org.nakeduml.uim.MasterComponent
	 * @generated
	 */
	EClass getMasterComponent();

	/**
	 * Returns the meta object for the reference list '{@link org.nakeduml.uim.MasterComponent#getDetailPanels <em>Detail Panels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Detail Panels</em>'.
	 * @see org.nakeduml.uim.MasterComponent#getDetailPanels()
	 * @see #getMasterComponent()
	 * @generated
	 */
	EReference getMasterComponent_DetailPanels();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layout</em>'.
	 * @see org.nakeduml.uim.UIMLayout
	 * @generated
	 */
	EClass getUIMLayout();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMToolbarLayout <em>Toolbar Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Toolbar Layout</em>'.
	 * @see org.nakeduml.uim.UIMToolbarLayout
	 * @generated
	 */
	EClass getUIMToolbarLayout();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMBorderLayout <em>Border Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Border Layout</em>'.
	 * @see org.nakeduml.uim.UIMBorderLayout
	 * @generated
	 */
	EClass getUIMBorderLayout();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UIMBorderLayout#isHorizontal <em>Horizontal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal</em>'.
	 * @see org.nakeduml.uim.UIMBorderLayout#isHorizontal()
	 * @see #getUIMBorderLayout()
	 * @generated
	 */
	EAttribute getUIMBorderLayout_Horizontal();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMXYLayout <em>XY Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XY Layout</em>'.
	 * @see org.nakeduml.uim.UIMXYLayout
	 * @generated
	 */
	EClass getUIMXYLayout();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMMultiSelectTreeView <em>Multi Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Select Tree View</em>'.
	 * @see org.nakeduml.uim.UIMMultiSelectTreeView
	 * @generated
	 */
	EClass getUIMMultiSelectTreeView();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMMultiSelectListBox <em>Multi Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Select List Box</em>'.
	 * @see org.nakeduml.uim.UIMMultiSelectListBox
	 * @generated
	 */
	EClass getUIMMultiSelectListBox();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMMultiSelectPopupSearch <em>Multi Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Select Popup Search</em>'.
	 * @see org.nakeduml.uim.UIMMultiSelectPopupSearch
	 * @generated
	 */
	EClass getUIMMultiSelectPopupSearch();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMSingleSelectPopupSearch <em>Single Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Select Popup Search</em>'.
	 * @see org.nakeduml.uim.UIMSingleSelectPopupSearch
	 * @generated
	 */
	EClass getUIMSingleSelectPopupSearch();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMToggleButton <em>Toggle Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Toggle Button</em>'.
	 * @see org.nakeduml.uim.UIMToggleButton
	 * @generated
	 */
	EClass getUIMToggleButton();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UIMNumberScroller <em>Number Scroller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Scroller</em>'.
	 * @see org.nakeduml.uim.UIMNumberScroller
	 * @generated
	 */
	EClass getUIMNumberScroller();

	/**
	 * Returns the meta object for enum '{@link org.nakeduml.uim.ActionKind <em>Action Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Action Kind</em>'.
	 * @see org.nakeduml.uim.ActionKind
	 * @generated
	 */
	EEnum getActionKind();

	/**
	 * Returns the meta object for enum '{@link org.nakeduml.uim.ControlKind <em>Control Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Control Kind</em>'.
	 * @see org.nakeduml.uim.ControlKind
	 * @generated
	 */
	EEnum getControlKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UIMFactory getUIMFactory();

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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMFormImpl <em>Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMFormImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMForm()
		 * @generated
		 */
		EClass UIM_FORM = eINSTANCE.getUIMForm();

		/**
		 * The meta object literal for the '<em><b>Panel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FORM__PANEL = eINSTANCE.getUIMForm_Panel();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UserInteractionModelImpl <em>User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UserInteractionModelImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUserInteractionModel()
		 * @generated
		 */
		EClass USER_INTERACTION_MODEL = eINSTANCE.getUserInteractionModel();

		/**
		 * The meta object literal for the '<em><b>Uml Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERACTION_MODEL__UML_MODEL = eINSTANCE.getUserInteractionModel_UmlModel();

		/**
		 * The meta object literal for the '<em><b>Security On Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY = eINSTANCE.getUserInteractionModel_SecurityOnVisibility();

		/**
		 * The meta object literal for the '<em><b>Secuiryt On Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY = eINSTANCE.getUserInteractionModel_SecuirytOnEditability();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.AbstractFormFolderImpl <em>Abstract Form Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.AbstractFormFolderImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getAbstractFormFolder()
		 * @generated
		 */
		EClass ABSTRACT_FORM_FOLDER = eINSTANCE.getAbstractFormFolder();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_FORM_FOLDER__PARENT = eINSTANCE.getAbstractFormFolder_Parent();

		/**
		 * The meta object literal for the '<em><b>Security On Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY = eINSTANCE.getAbstractFormFolder_SecurityOnVisibility();

		/**
		 * The meta object literal for the '<em><b>Security On Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY = eINSTANCE.getAbstractFormFolder_SecurityOnEditability();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.AbstractFolderImpl <em>Abstract Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.AbstractFolderImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getAbstractFolder()
		 * @generated
		 */
		EClass ABSTRACT_FOLDER = eINSTANCE.getAbstractFolder();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_FOLDER__CHILDREN = eINSTANCE.getAbstractFolder_Children();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMFieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMFieldImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMField()
		 * @generated
		 */
		EClass UIM_FIELD = eINSTANCE.getUIMField();

		/**
		 * The meta object literal for the '<em><b>Control</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FIELD__CONTROL = eINSTANCE.getUIMField_Control();

		/**
		 * The meta object literal for the '<em><b>Control Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_FIELD__CONTROL_KIND = eINSTANCE.getUIMField_ControlKind();

		/**
		 * The meta object literal for the '<em><b>Label Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_FIELD__LABEL_WIDTH = eINSTANCE.getUIMField_LabelWidth();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FIELD__BINDING = eINSTANCE.getUIMField_Binding();

		/**
		 * The meta object literal for the '<em><b>Security On Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FIELD__SECURITY_ON_EDITABILITY = eINSTANCE.getUIMField_SecurityOnEditability();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMNavigationImpl <em>Navigation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMNavigationImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMNavigation()
		 * @generated
		 */
		EClass UIM_NAVIGATION = eINSTANCE.getUIMNavigation();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMPanelImpl <em>Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMPanelImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMPanel()
		 * @generated
		 */
		EClass UIM_PANEL = eINSTANCE.getUIMPanel();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.ClassFormImpl <em>Class Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.ClassFormImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getClassForm()
		 * @generated
		 */
		EClass CLASS_FORM = eINSTANCE.getClassForm();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_FORM__FOLDER = eINSTANCE.getClassForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.StateFormImpl <em>State Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.StateFormImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getStateForm()
		 * @generated
		 */
		EClass STATE_FORM = eINSTANCE.getStateForm();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FORM__STATE = eINSTANCE.getStateForm_State();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_FORM__FOLDER = eINSTANCE.getStateForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.ModelSecurityConstraintImpl <em>Model Security Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.ModelSecurityConstraintImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getModelSecurityConstraint()
		 * @generated
		 */
		EClass MODEL_SECURITY_CONSTRAINT = eINSTANCE.getModelSecurityConstraint();

		/**
		 * The meta object literal for the '<em><b>Required Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES = eINSTANCE.getModelSecurityConstraint_RequiredRoles();

		/**
		 * The meta object literal for the '<em><b>Requires Group Ownership</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = eINSTANCE.getModelSecurityConstraint_RequiresGroupOwnership();

		/**
		 * The meta object literal for the '<em><b>Requires Ownership</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = eINSTANCE.getModelSecurityConstraint_RequiresOwnership();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.OperationInvocationFormImpl <em>Operation Invocation Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.OperationInvocationFormImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getOperationInvocationForm()
		 * @generated
		 */
		EClass OPERATION_INVOCATION_FORM = eINSTANCE.getOperationInvocationForm();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INVOCATION_FORM__OPERATION = eINSTANCE.getOperationInvocationForm_Operation();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_INVOCATION_FORM__FOLDER = eINSTANCE.getOperationInvocationForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMComponentImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMComponent()
		 * @generated
		 */
		EClass UIM_COMPONENT = eINSTANCE.getUIMComponent();

		/**
		 * The meta object literal for the '<em><b>Security On Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_COMPONENT__SECURITY_ON_VISIBILITY = eINSTANCE.getUIMComponent_SecurityOnVisibility();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_COMPONENT__PARENT = eINSTANCE.getUIMComponent_Parent();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UserInteractionElementImpl <em>User Interaction Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UserInteractionElementImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUserInteractionElement()
		 * @generated
		 */
		EClass USER_INTERACTION_ELEMENT = eINSTANCE.getUserInteractionElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INTERACTION_ELEMENT__NAME = eINSTANCE.getUserInteractionElement_Name();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.OperationActionImpl <em>Operation Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.OperationActionImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getOperationAction()
		 * @generated
		 */
		EClass OPERATION_ACTION = eINSTANCE.getOperationAction();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_ACTION__OPERATION = eINSTANCE.getOperationAction_Operation();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.NavigationToOperationImpl <em>Navigation To Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.NavigationToOperationImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getNavigationToOperation()
		 * @generated
		 */
		EClass NAVIGATION_TO_OPERATION = eINSTANCE.getNavigationToOperation();

		/**
		 * The meta object literal for the '<em><b>To Form</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_TO_OPERATION__TO_FORM = eINSTANCE.getNavigationToOperation_ToForm();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.BuiltInActionImpl <em>Built In Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.BuiltInActionImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getBuiltInAction()
		 * @generated
		 */
		EClass BUILT_IN_ACTION = eINSTANCE.getBuiltInAction();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILT_IN_ACTION__KIND = eINSTANCE.getBuiltInAction_Kind();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMControlImpl <em>Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMControlImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMControl()
		 * @generated
		 */
		EClass UIM_CONTROL = eINSTANCE.getUIMControl();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_CONTROL__WIDTH = eINSTANCE.getUIMControl_Width();

		/**
		 * The meta object literal for the '<em><b>Field</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_CONTROL__FIELD = eINSTANCE.getUIMControl_Field();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.NavigationToEntityImpl <em>Navigation To Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.NavigationToEntityImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getNavigationToEntity()
		 * @generated
		 */
		EClass NAVIGATION_TO_ENTITY = eINSTANCE.getNavigationToEntity();

		/**
		 * The meta object literal for the '<em><b>To Form</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_TO_ENTITY__TO_FORM = eINSTANCE.getNavigationToEntity_ToForm();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_TO_ENTITY__BINDING = eINSTANCE.getNavigationToEntity_Binding();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.TransitionActionImpl <em>Transition Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.TransitionActionImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getTransitionAction()
		 * @generated
		 */
		EClass TRANSITION_ACTION = eINSTANCE.getTransitionAction();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_ACTION__TRANSITION = eINSTANCE.getTransitionAction_Transition();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.OperationTaskFormImpl <em>Operation Task Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.OperationTaskFormImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getOperationTaskForm()
		 * @generated
		 */
		EClass OPERATION_TASK_FORM = eINSTANCE.getOperationTaskForm();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_TASK_FORM__OPERATION = eINSTANCE.getOperationTaskForm_Operation();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_TASK_FORM__FOLDER = eINSTANCE.getOperationTaskForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.ActionTaskFormImpl <em>Action Task Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.ActionTaskFormImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getActionTaskForm()
		 * @generated
		 */
		EClass ACTION_TASK_FORM = eINSTANCE.getActionTaskForm();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_TASK_FORM__ACTION = eINSTANCE.getActionTaskForm_Action();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_TASK_FORM__FOLDER = eINSTANCE.getActionTaskForm_Folder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMActionImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMAction()
		 * @generated
		 */
		EClass UIM_ACTION = eINSTANCE.getUIMAction();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.ChildSecurityConstraintImpl <em>Child Security Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.ChildSecurityConstraintImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getChildSecurityConstraint()
		 * @generated
		 */
		EClass CHILD_SECURITY_CONSTRAINT = eINSTANCE.getChildSecurityConstraint();

		/**
		 * The meta object literal for the '<em><b>Inherit From Parent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHILD_SECURITY_CONSTRAINT__INHERIT_FROM_PARENT = eINSTANCE.getChildSecurityConstraint_InheritFromParent();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMGridLayoutImpl <em>Grid Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMGridLayoutImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMGridLayout()
		 * @generated
		 */
		EClass UIM_GRID_LAYOUT = eINSTANCE.getUIMGridLayout();

		/**
		 * The meta object literal for the '<em><b>Number Of Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_GRID_LAYOUT__NUMBER_OF_COLUMNS = eINSTANCE.getUIMGridLayout_NumberOfColumns();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMDataTableImpl <em>Data Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMDataTableImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMDataTable()
		 * @generated
		 */
		EClass UIM_DATA_TABLE = eINSTANCE.getUIMDataTable();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_DATA_TABLE__BINDING = eINSTANCE.getUIMDataTable_Binding();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMBindingImpl <em>Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMBindingImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMBinding()
		 * @generated
		 */
		EClass UIM_BINDING = eINSTANCE.getUIMBinding();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_BINDING__NEXT = eINSTANCE.getUIMBinding_Next();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_BINDING__ELEMENT = eINSTANCE.getUIMBinding_Element();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.PropertyRefImpl <em>Property Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.PropertyRefImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getPropertyRef()
		 * @generated
		 */
		EClass PROPERTY_REF = eINSTANCE.getPropertyRef();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_REF__BINDING = eINSTANCE.getPropertyRef_Binding();

		/**
		 * The meta object literal for the '<em><b>Previous</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_REF__PREVIOUS = eINSTANCE.getPropertyRef_Previous();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_REF__NEXT = eINSTANCE.getPropertyRef_Next();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_REF__PROPERTY = eINSTANCE.getPropertyRef_Property();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMDataColumnImpl <em>Data Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMDataColumnImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMDataColumn()
		 * @generated
		 */
		EClass UIM_DATA_COLUMN = eINSTANCE.getUIMDataColumn();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_DATA_COLUMN__WIDTH = eINSTANCE.getUIMDataColumn_Width();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.TableBindingImpl <em>Table Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.TableBindingImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getTableBinding()
		 * @generated
		 */
		EClass TABLE_BINDING = eINSTANCE.getTableBinding();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_BINDING__TABLE = eINSTANCE.getTableBinding_Table();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.FieldBindingImpl <em>Field Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.FieldBindingImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getFieldBinding()
		 * @generated
		 */
		EClass FIELD_BINDING = eINSTANCE.getFieldBinding();

		/**
		 * The meta object literal for the '<em><b>Field</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_BINDING__FIELD = eINSTANCE.getFieldBinding_Field();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.FormPanelImpl <em>Form Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.FormPanelImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getFormPanel()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.StateMachineFolderImpl <em>State Machine Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.StateMachineFolderImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getStateMachineFolder()
		 * @generated
		 */
		EClass STATE_MACHINE_FOLDER = eINSTANCE.getStateMachineFolder();

		/**
		 * The meta object literal for the '<em><b>State Forms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE_FOLDER__STATE_FORMS = eINSTANCE.getStateMachineFolder_StateForms();

		/**
		 * The meta object literal for the '<em><b>State Machine</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE_FOLDER__STATE_MACHINE = eINSTANCE.getStateMachineFolder_StateMachine();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.EntityFolderImpl <em>Entity Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.EntityFolderImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getEntityFolder()
		 * @generated
		 */
		EClass ENTITY_FOLDER = eINSTANCE.getEntityFolder();

		/**
		 * The meta object literal for the '<em><b>Operation Task Forms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_FOLDER__OPERATION_TASK_FORMS = eINSTANCE.getEntityFolder_OperationTaskForms();

		/**
		 * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_FOLDER__ENTITY = eINSTANCE.getEntityFolder_Entity();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.ActivityFolderImpl <em>Activity Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.ActivityFolderImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getActivityFolder()
		 * @generated
		 */
		EClass ACTIVITY_FOLDER = eINSTANCE.getActivityFolder();

		/**
		 * The meta object literal for the '<em><b>Action Task Forms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_FOLDER__ACTION_TASK_FORMS = eINSTANCE.getActivityFolder_ActionTaskForms();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_FOLDER__ACTIVITY = eINSTANCE.getActivityFolder_Activity();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.OperationContainingFolderImpl <em>Operation Containing Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.OperationContainingFolderImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getOperationContainingFolder()
		 * @generated
		 */
		EClass OPERATION_CONTAINING_FOLDER = eINSTANCE.getOperationContainingFolder();

		/**
		 * The meta object literal for the '<em><b>Operation Invocation Forms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS = eINSTANCE.getOperationContainingFolder_OperationInvocationForms();

		/**
		 * The meta object literal for the '<em><b>Class Form</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CONTAINING_FOLDER__CLASS_FORM = eINSTANCE.getOperationContainingFolder_ClassForm();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.NavigationBindingImpl <em>Navigation Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.NavigationBindingImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getNavigationBinding()
		 * @generated
		 */
		EClass NAVIGATION_BINDING = eINSTANCE.getNavigationBinding();

		/**
		 * The meta object literal for the '<em><b>Navigation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_BINDING__NAVIGATION = eINSTANCE.getNavigationBinding_Navigation();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.DetailPanelImpl <em>Detail Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.DetailPanelImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getDetailPanel()
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

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.PackageFolderImpl <em>Package Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.PackageFolderImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getPackageFolder()
		 * @generated
		 */
		EClass PACKAGE_FOLDER = eINSTANCE.getPackageFolder();

		/**
		 * The meta object literal for the '<em><b>Uml Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_FOLDER__UML_PACKAGE = eINSTANCE.getPackageFolder_UmlPackage();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMTabPanelImpl <em>Tab Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMTabPanelImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMTabPanel()
		 * @generated
		 */
		EClass UIM_TAB_PANEL = eINSTANCE.getUIMTabPanel();

		/**
		 * The meta object literal for the '<em><b>Active Tab Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_TAB_PANEL__ACTIVE_TAB_INDEX = eINSTANCE.getUIMTabPanel_ActiveTabIndex();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMTabImpl <em>Tab</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMTabImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMTab()
		 * @generated
		 */
		EClass UIM_TAB = eINSTANCE.getUIMTab();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMCheckBoxImpl <em>Check Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMCheckBoxImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMCheckBox()
		 * @generated
		 */
		EClass UIM_CHECK_BOX = eINSTANCE.getUIMCheckBox();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMLookupImpl <em>Lookup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMLookupImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMLookup()
		 * @generated
		 */
		EClass UIM_LOOKUP = eINSTANCE.getUIMLookup();

		/**
		 * The meta object literal for the '<em><b>Lookup Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_LOOKUP__LOOKUP_SOURCE = eINSTANCE.getUIMLookup_LookupSource();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.LookupBindingImpl <em>Lookup Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.LookupBindingImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getLookupBinding()
		 * @generated
		 */
		EClass LOOKUP_BINDING = eINSTANCE.getLookupBinding();

		/**
		 * The meta object literal for the '<em><b>Lookup</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOKUP_BINDING__LOOKUP = eINSTANCE.getLookupBinding_Lookup();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMTextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMTextImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMText()
		 * @generated
		 */
		EClass UIM_TEXT = eINSTANCE.getUIMText();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMTextAreaImpl <em>Text Area</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMTextAreaImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMTextArea()
		 * @generated
		 */
		EClass UIM_TEXT_AREA = eINSTANCE.getUIMTextArea();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_TEXT_AREA__ROWS = eINSTANCE.getUIMTextArea_Rows();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMDropdownImpl <em>Dropdown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMDropdownImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMDropdown()
		 * @generated
		 */
		EClass UIM_DROPDOWN = eINSTANCE.getUIMDropdown();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMDatePopupImpl <em>Date Popup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMDatePopupImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMDatePopup()
		 * @generated
		 */
		EClass UIM_DATE_POPUP = eINSTANCE.getUIMDatePopup();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMSingleSelectListBoxImpl <em>Single Select List Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMSingleSelectListBoxImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMSingleSelectListBox()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_LIST_BOX = eINSTANCE.getUIMSingleSelectListBox();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_SINGLE_SELECT_LIST_BOX__ROWS = eINSTANCE.getUIMSingleSelectListBox_Rows();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMContainerImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMContainer()
		 * @generated
		 */
		EClass UIM_CONTAINER = eINSTANCE.getUIMContainer();

		/**
		 * The meta object literal for the '<em><b>Security On Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_CONTAINER__SECURITY_ON_EDITABILITY = eINSTANCE.getUIMContainer_SecurityOnEditability();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_CONTAINER__CHILDREN = eINSTANCE.getUIMContainer_Children();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMSingleSelectTreeViewImpl <em>Single Select Tree View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMSingleSelectTreeViewImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMSingleSelectTreeView()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_TREE_VIEW = eINSTANCE.getUIMSingleSelectTreeView();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.MasterComponent <em>Master Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.MasterComponent
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getMasterComponent()
		 * @generated
		 */
		EClass MASTER_COMPONENT = eINSTANCE.getMasterComponent();

		/**
		 * The meta object literal for the '<em><b>Detail Panels</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MASTER_COMPONENT__DETAIL_PANELS = eINSTANCE.getMasterComponent_DetailPanels();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMLayoutImpl <em>Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMLayoutImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMLayout()
		 * @generated
		 */
		EClass UIM_LAYOUT = eINSTANCE.getUIMLayout();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMToolbarLayoutImpl <em>Toolbar Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMToolbarLayoutImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMToolbarLayout()
		 * @generated
		 */
		EClass UIM_TOOLBAR_LAYOUT = eINSTANCE.getUIMToolbarLayout();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMBorderLayoutImpl <em>Border Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMBorderLayoutImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMBorderLayout()
		 * @generated
		 */
		EClass UIM_BORDER_LAYOUT = eINSTANCE.getUIMBorderLayout();

		/**
		 * The meta object literal for the '<em><b>Horizontal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_BORDER_LAYOUT__HORIZONTAL = eINSTANCE.getUIMBorderLayout_Horizontal();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMXYLayoutImpl <em>XY Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMXYLayoutImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMXYLayout()
		 * @generated
		 */
		EClass UIM_XY_LAYOUT = eINSTANCE.getUIMXYLayout();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMMultiSelectTreeViewImpl <em>Multi Select Tree View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMMultiSelectTreeViewImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMMultiSelectTreeView()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_TREE_VIEW = eINSTANCE.getUIMMultiSelectTreeView();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMMultiSelectListBoxImpl <em>Multi Select List Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMMultiSelectListBoxImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMMultiSelectListBox()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_LIST_BOX = eINSTANCE.getUIMMultiSelectListBox();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMMultiSelectPopupSearchImpl <em>Multi Select Popup Search</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMMultiSelectPopupSearchImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMMultiSelectPopupSearch()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_POPUP_SEARCH = eINSTANCE.getUIMMultiSelectPopupSearch();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMSingleSelectPopupSearchImpl <em>Single Select Popup Search</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMSingleSelectPopupSearchImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMSingleSelectPopupSearch()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_POPUP_SEARCH = eINSTANCE.getUIMSingleSelectPopupSearch();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMToggleButtonImpl <em>Toggle Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMToggleButtonImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMToggleButton()
		 * @generated
		 */
		EClass UIM_TOGGLE_BUTTON = eINSTANCE.getUIMToggleButton();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UIMNumberScrollerImpl <em>Number Scroller</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UIMNumberScrollerImpl
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getUIMNumberScroller()
		 * @generated
		 */
		EClass UIM_NUMBER_SCROLLER = eINSTANCE.getUIMNumberScroller();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.ActionKind <em>Action Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.ActionKind
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getActionKind()
		 * @generated
		 */
		EEnum ACTION_KIND = eINSTANCE.getActionKind();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.ControlKind <em>Control Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.ControlKind
		 * @see org.nakeduml.uim.impl.UIMPackageImpl#getControlKind()
		 * @generated
		 */
		EEnum CONTROL_KIND = eINSTANCE.getControlKind();

	}

} //UIMPackage
