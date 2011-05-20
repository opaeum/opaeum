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
 * @see org.nakeduml.uim.UimFactory
 * @model kind="package"
 * @generated
 */
public interface UimPackage extends EPackage {
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
	String eNS_URI = "http://nakeduml.org/uimetamodel/1.0";

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
	UimPackage eINSTANCE = org.nakeduml.uim.impl.UimPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UserInteractionElementImpl <em>User Interaction Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UserInteractionElementImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUserInteractionElement()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimFormImpl <em>Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimFormImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimForm()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UmlReferenceImpl <em>Uml Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UmlReferenceImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUmlReference()
	 * @generated
	 */
	int UML_REFERENCE = 61;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UML_REFERENCE__UML_ELEMENT_UID = 0;

	/**
	 * The number of structural features of the '<em>Uml Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UML_REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UserInteractionModelImpl <em>User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UserInteractionModelImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUserInteractionModel()
	 * @generated
	 */
	int USER_INTERACTION_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__NAME = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__CHILDREN = UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__VISIBILITY = UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__EDITABILITY = UML_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__PARENT = UML_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.AbstractFolderImpl <em>Abstract Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.AbstractFolderImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getAbstractFolder()
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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER__UML_ELEMENT_UID = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER__CHILDREN = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.AbstractFormFolderImpl <em>Abstract Form Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.AbstractFormFolderImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getAbstractFormFolder()
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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__UML_ELEMENT_UID = ABSTRACT_FOLDER__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__CHILDREN = ABSTRACT_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__VISIBILITY = ABSTRACT_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__EDITABILITY = ABSTRACT_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER__PARENT = ABSTRACT_FOLDER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Abstract Form Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FORM_FOLDER_FEATURE_COUNT = ABSTRACT_FOLDER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.SecureObjectImpl <em>Secure Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.SecureObjectImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getSecureObject()
	 * @generated
	 */
	int SECURE_OBJECT = 64;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_OBJECT__VISIBILITY = 0;

	/**
	 * The number of structural features of the '<em>Secure Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_OBJECT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.EditableSecureObjectImpl <em>Editable Secure Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.EditableSecureObjectImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getEditableSecureObject()
	 * @generated
	 */
	int EDITABLE_SECURE_OBJECT = 65;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_SECURE_OBJECT__VISIBILITY = SECURE_OBJECT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_SECURE_OBJECT__EDITABILITY = SECURE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Editable Secure Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_SECURE_OBJECT_FEATURE_COUNT = SECURE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimFieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimFieldImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimField()
	 * @generated
	 */
	int UIM_FIELD = 4;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__VISIBILITY = EDITABLE_SECURE_OBJECT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__EDITABILITY = EDITABLE_SECURE_OBJECT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__NAME = EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__PARENT = EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL = EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Control Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL_KIND = EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__LABEL_WIDTH = EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__BINDING = EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD_FEATURE_COUNT = EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimComponentImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimComponent()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__VISIBILITY = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.OutlayableComponentImpl <em>Outlayable Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.OutlayableComponentImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getOutlayableComponent()
	 * @generated
	 */
	int OUTLAYABLE_COMPONENT = 67;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_COMPONENT__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_COMPONENT__VISIBILITY = UIM_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_COMPONENT__PARENT = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Outlayable Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTLAYABLE_COMPONENT_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimNavigationImpl <em>Navigation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimNavigationImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimNavigation()
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
	int UIM_NAVIGATION__NAME = OUTLAYABLE_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION__VISIBILITY = OUTLAYABLE_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION__PARENT = OUTLAYABLE_COMPONENT__PARENT;

	/**
	 * The number of structural features of the '<em>Navigation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_NAVIGATION_FEATURE_COUNT = OUTLAYABLE_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimContainerImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimContainer()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__VISIBILITY = UIM_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__EDITABILITY = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.LayoutContainerImpl <em>Layout Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.LayoutContainerImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getLayoutContainer()
	 * @generated
	 */
	int LAYOUT_CONTAINER = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER__NAME = UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER__VISIBILITY = UIM_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER__EDITABILITY = UIM_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER__LAYOUT = UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Layout Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYOUT_CONTAINER_FEATURE_COUNT = UIM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.FormPanelImpl <em>Form Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.FormPanelImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getFormPanel()
	 * @generated
	 */
	int FORM_PANEL = 30;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__NAME = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__VISIBILITY = UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__EDITABILITY = UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__LAYOUT = UML_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Form</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL__FORM = UML_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Form Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORM_PANEL_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.ClassFormImpl <em>Class Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.ClassFormImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getClassForm()
	 * @generated
	 */
	int CLASS_FORM = 7;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.StateFormImpl <em>State Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.StateFormImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getStateForm()
	 * @generated
	 */
	int STATE_FORM = 8;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.WorkspaceSecurityConstraintImpl <em>Workspace Security Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.WorkspaceSecurityConstraintImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getWorkspaceSecurityConstraint()
	 * @generated
	 */
	int WORKSPACE_SECURITY_CONSTRAINT = 9;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = 0;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = 1;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES = 2;

	/**
	 * The number of structural features of the '<em>Workspace Security Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_SECURITY_CONSTRAINT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.OperationInvocationFormImpl <em>Operation Invocation Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.OperationInvocationFormImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getOperationInvocationForm()
	 * @generated
	 */
	int OPERATION_INVOCATION_FORM = 10;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimActionImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimAction()
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
	int UIM_ACTION__NAME = OUTLAYABLE_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__VISIBILITY = OUTLAYABLE_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION__PARENT = OUTLAYABLE_COMPONENT__PARENT;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_ACTION_FEATURE_COUNT = OUTLAYABLE_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.OperationActionImpl <em>Operation Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.OperationActionImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getOperationAction()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__VISIBILITY = UIM_ACTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__PARENT = UIM_ACTION__PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_ACTION__UML_ELEMENT_UID = UIM_ACTION_FEATURE_COUNT + 0;

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
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getNavigationToOperation()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__VISIBILITY = UIM_NAVIGATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__PARENT = UIM_NAVIGATION__PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__UML_ELEMENT_UID = UIM_NAVIGATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION__TO_FORM = UIM_NAVIGATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Navigation To Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_OPERATION_FEATURE_COUNT = UIM_NAVIGATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.BuiltInActionImpl <em>Built In Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.BuiltInActionImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getBuiltInAction()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_ACTION__VISIBILITY = UIM_ACTION__VISIBILITY;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimControlImpl <em>Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimControlImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimControl()
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
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getNavigationToEntity()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__VISIBILITY = UIM_NAVIGATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__PARENT = UIM_NAVIGATION__PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__UML_ELEMENT_UID = UIM_NAVIGATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To Form</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__TO_FORM = UIM_NAVIGATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY__BINDING = UIM_NAVIGATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Navigation To Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_TO_ENTITY_FEATURE_COUNT = UIM_NAVIGATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.TransitionActionImpl <em>Transition Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.TransitionActionImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getTransitionAction()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__VISIBILITY = UIM_ACTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__PARENT = UIM_ACTION__PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTION__UML_ELEMENT_UID = UIM_ACTION_FEATURE_COUNT + 0;

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
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getOperationTaskForm()
	 * @generated
	 */
	int OPERATION_TASK_FORM = 19;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.ActionTaskFormImpl <em>Action Task Form</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.ActionTaskFormImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getActionTaskForm()
	 * @generated
	 */
	int ACTION_TASK_FORM = 20;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.SecurityConstraintImpl <em>Security Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.SecurityConstraintImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getSecurityConstraint()
	 * @generated
	 */
	int SECURITY_CONSTRAINT = 22;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__REQUIRED_ROLES = WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__INHERIT_FROM_PARENT = WORKSPACE_SECURITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Security Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT_FEATURE_COUNT = WORKSPACE_SECURITY_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimLayoutImpl <em>Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimLayoutImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimLayout()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__VISIBILITY = UIM_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__EDITABILITY = UIM_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__PARENT = UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT__CHILDREN = UIM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_LAYOUT_FEATURE_COUNT = UIM_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimGridLayoutImpl <em>Grid Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimGridLayoutImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimGridLayout()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_GRID_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

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
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getMasterComponent()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimDataTableImpl <em>Data Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimDataTableImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimDataTable()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__VISIBILITY = MASTER_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__EDITABILITY = MASTER_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__PARENT = MASTER_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__BINDING = MASTER_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__CHILDREN = MASTER_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Data Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimBindingImpl <em>Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimBindingImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimBinding()
	 * @generated
	 */
	int UIM_BINDING = 25;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING__NEXT = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BINDING_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.PropertyRefImpl <em>Property Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.PropertyRefImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getPropertyRef()
	 * @generated
	 */
	int PROPERTY_REF = 26;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__BINDING = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__PREVIOUS = UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF__NEXT = UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Property Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_REF_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimDataColumnImpl <em>Data Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimDataColumnImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimDataColumn()
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
	int UIM_DATA_COLUMN__NAME = LAYOUT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__VISIBILITY = LAYOUT_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__EDITABILITY = LAYOUT_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__LAYOUT = LAYOUT_CONTAINER__LAYOUT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__WIDTH = LAYOUT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN__PARENT = LAYOUT_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Data Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_COLUMN_FEATURE_COUNT = LAYOUT_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.TableBindingImpl <em>Table Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.TableBindingImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getTableBinding()
	 * @generated
	 */
	int TABLE_BINDING = 28;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING__UML_ELEMENT_UID = UIM_BINDING__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_BINDING__NEXT = UIM_BINDING__NEXT;

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
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getFieldBinding()
	 * @generated
	 */
	int FIELD_BINDING = 29;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING__UML_ELEMENT_UID = UIM_BINDING__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_BINDING__NEXT = UIM_BINDING__NEXT;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.OperationContainingFolderImpl <em>Operation Containing Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.OperationContainingFolderImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getOperationContainingFolder()
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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__UML_ELEMENT_UID = ABSTRACT_FORM_FOLDER__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__CHILDREN = ABSTRACT_FORM_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__VISIBILITY = ABSTRACT_FORM_FOLDER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__EDITABILITY = ABSTRACT_FORM_FOLDER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER__PARENT = ABSTRACT_FORM_FOLDER__PARENT;

	/**
	 * The number of structural features of the '<em>Operation Containing Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CONTAINING_FOLDER_FEATURE_COUNT = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.StateMachineFolderImpl <em>State Machine Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.StateMachineFolderImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getStateMachineFolder()
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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__UML_ELEMENT_UID = OPERATION_CONTAINING_FOLDER__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__CHILDREN = OPERATION_CONTAINING_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__VISIBILITY = OPERATION_CONTAINING_FOLDER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__EDITABILITY = OPERATION_CONTAINING_FOLDER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER__PARENT = OPERATION_CONTAINING_FOLDER__PARENT;

	/**
	 * The number of structural features of the '<em>State Machine Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FOLDER_FEATURE_COUNT = OPERATION_CONTAINING_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.EntityFolderImpl <em>Entity Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.EntityFolderImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getEntityFolder()
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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__UML_ELEMENT_UID = OPERATION_CONTAINING_FOLDER__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__CHILDREN = OPERATION_CONTAINING_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__VISIBILITY = OPERATION_CONTAINING_FOLDER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__EDITABILITY = OPERATION_CONTAINING_FOLDER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER__PARENT = OPERATION_CONTAINING_FOLDER__PARENT;

	/**
	 * The number of structural features of the '<em>Entity Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FOLDER_FEATURE_COUNT = OPERATION_CONTAINING_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.ActivityFolderImpl <em>Activity Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.ActivityFolderImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getActivityFolder()
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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__UML_ELEMENT_UID = ABSTRACT_FORM_FOLDER__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__CHILDREN = ABSTRACT_FORM_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__VISIBILITY = ABSTRACT_FORM_FOLDER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__EDITABILITY = ABSTRACT_FORM_FOLDER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER__PARENT = ABSTRACT_FORM_FOLDER__PARENT;

	/**
	 * The number of structural features of the '<em>Activity Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FOLDER_FEATURE_COUNT = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.NavigationBindingImpl <em>Navigation Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.NavigationBindingImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getNavigationBinding()
	 * @generated
	 */
	int NAVIGATION_BINDING = 35;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BINDING__UML_ELEMENT_UID = UIM_BINDING__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BINDING__NEXT = UIM_BINDING__NEXT;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimPanelImpl <em>Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimPanelImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimPanel()
	 * @generated
	 */
	int UIM_PANEL = 68;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__NAME = LAYOUT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__VISIBILITY = LAYOUT_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__EDITABILITY = LAYOUT_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__LAYOUT = LAYOUT_CONTAINER__LAYOUT;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__PARENT = LAYOUT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL_FEATURE_COUNT = LAYOUT_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.DetailPanelImpl <em>Detail Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.DetailPanelImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getDetailPanel()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__VISIBILITY = UIM_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__EDITABILITY = UIM_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__LAYOUT = UIM_PANEL__LAYOUT;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_PANEL__PARENT = UIM_PANEL__PARENT;

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
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getPackageFolder()
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
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__UML_ELEMENT_UID = ABSTRACT_FORM_FOLDER__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__CHILDREN = ABSTRACT_FORM_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__VISIBILITY = ABSTRACT_FORM_FOLDER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__EDITABILITY = ABSTRACT_FORM_FOLDER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER__PARENT = ABSTRACT_FORM_FOLDER__PARENT;

	/**
	 * The number of structural features of the '<em>Package Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FOLDER_FEATURE_COUNT = ABSTRACT_FORM_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimTabPanelImpl <em>Tab Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimTabPanelImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimTabPanel()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__VISIBILITY = UIM_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__EDITABILITY = UIM_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__PARENT = UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Active Tab Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__ACTIVE_TAB_INDEX = UIM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__CHILDREN = UIM_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tab Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL_FEATURE_COUNT = UIM_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimTabImpl <em>Tab</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimTabImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimTab()
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
	int UIM_TAB__NAME = LAYOUT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__VISIBILITY = LAYOUT_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__EDITABILITY = LAYOUT_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__LAYOUT = LAYOUT_CONTAINER__LAYOUT;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__PARENT = LAYOUT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tab</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_FEATURE_COUNT = LAYOUT_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimCheckBoxImpl <em>Check Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimCheckBoxImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimCheckBox()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimLookupImpl <em>Lookup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimLookupImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimLookup()
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
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getLookupBinding()
	 * @generated
	 */
	int LOOKUP_BINDING = 42;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING__UML_ELEMENT_UID = UIM_BINDING__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOOKUP_BINDING__NEXT = UIM_BINDING__NEXT;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimTextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimTextImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimText()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimTextAreaImpl <em>Text Area</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimTextAreaImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimTextArea()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimDropdownImpl <em>Dropdown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimDropdownImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimDropdown()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimDatePopupImpl <em>Date Popup</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimDatePopupImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimDatePopup()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimSingleSelectListBoxImpl <em>Single Select List Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimSingleSelectListBoxImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimSingleSelectListBox()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimSingleSelectTreeViewImpl <em>Single Select Tree View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimSingleSelectTreeViewImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimSingleSelectTreeView()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimToolbarLayoutImpl <em>Toolbar Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimToolbarLayoutImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimToolbarLayout()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TOOLBAR_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimBorderLayoutImpl <em>Border Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimBorderLayoutImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimBorderLayout()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_BORDER_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimXYLayoutImpl <em>XY Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimXYLayoutImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimXYLayout()
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
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_XY_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimMultiSelectTreeViewImpl <em>Multi Select Tree View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimMultiSelectTreeViewImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimMultiSelectTreeView()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimMultiSelectListBoxImpl <em>Multi Select List Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimMultiSelectListBoxImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimMultiSelectListBox()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimMultiSelectPopupSearchImpl <em>Multi Select Popup Search</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimMultiSelectPopupSearchImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimMultiSelectPopupSearch()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimSingleSelectPopupSearchImpl <em>Single Select Popup Search</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimSingleSelectPopupSearchImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimSingleSelectPopupSearch()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimToggleButtonImpl <em>Toggle Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimToggleButtonImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimToggleButton()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimNumberScrollerImpl <em>Number Scroller</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimNumberScrollerImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimNumberScroller()
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
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UserInteractionWorkspaceImpl <em>User Interaction Workspace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UserInteractionWorkspaceImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUserInteractionWorkspace()
	 * @generated
	 */
	int USER_INTERACTION_WORKSPACE = 62;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_WORKSPACE__NAME = ABSTRACT_FOLDER__NAME;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_WORKSPACE__UML_ELEMENT_UID = ABSTRACT_FOLDER__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_WORKSPACE__CHILDREN = ABSTRACT_FOLDER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_WORKSPACE__VISIBILITY = ABSTRACT_FOLDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_WORKSPACE__EDITABILITY = ABSTRACT_FOLDER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Interaction Workspace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_WORKSPACE_FEATURE_COUNT = ABSTRACT_FOLDER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.RequiredRoleImpl <em>Required Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.RequiredRoleImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getRequiredRole()
	 * @generated
	 */
	int REQUIRED_ROLE = 63;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_ROLE__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The number of structural features of the '<em>Required Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_ROLE_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.impl.UimFullLayoutImpl <em>Full Layout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.impl.UimFullLayoutImpl
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimFullLayout()
	 * @generated
	 */
	int UIM_FULL_LAYOUT = 66;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__NAME = UIM_LAYOUT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__VISIBILITY = UIM_LAYOUT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__EDITABILITY = UIM_LAYOUT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__PARENT = UIM_LAYOUT__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT__CHILDREN = UIM_LAYOUT__CHILDREN;

	/**
	 * The number of structural features of the '<em>Full Layout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FULL_LAYOUT_FEATURE_COUNT = UIM_LAYOUT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.ActionKind <em>Action Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.ActionKind
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getActionKind()
	 * @generated
	 */
	int ACTION_KIND = 69;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.ControlKind <em>Control Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.ControlKind
	 * @see org.nakeduml.uim.impl.UimPackageImpl#getControlKind()
	 * @generated
	 */
	int CONTROL_KIND = 70;


	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimForm <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Form</em>'.
	 * @see org.nakeduml.uim.UimForm
	 * @generated
	 */
	EClass getUimForm();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UimForm#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Panel</em>'.
	 * @see org.nakeduml.uim.UimForm#getPanel()
	 * @see #getUimForm()
	 * @generated
	 */
	EReference getUimForm_Panel();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see org.nakeduml.uim.UimField
	 * @generated
	 */
	EClass getUimField();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UimField#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Control</em>'.
	 * @see org.nakeduml.uim.UimField#getControl()
	 * @see #getUimField()
	 * @generated
	 */
	EReference getUimField_Control();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimField#getControlKind <em>Control Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Kind</em>'.
	 * @see org.nakeduml.uim.UimField#getControlKind()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_ControlKind();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimField#getLabelWidth <em>Label Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Width</em>'.
	 * @see org.nakeduml.uim.UimField#getLabelWidth()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_LabelWidth();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UimField#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.nakeduml.uim.UimField#getBinding()
	 * @see #getUimField()
	 * @generated
	 */
	EReference getUimField_Binding();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimNavigation <em>Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation</em>'.
	 * @see org.nakeduml.uim.UimNavigation
	 * @generated
	 */
	EClass getUimNavigation();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.LayoutContainer <em>Layout Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layout Container</em>'.
	 * @see org.nakeduml.uim.LayoutContainer
	 * @generated
	 */
	EClass getLayoutContainer();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.LayoutContainer#getLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Layout</em>'.
	 * @see org.nakeduml.uim.LayoutContainer#getLayout()
	 * @see #getLayoutContainer()
	 * @generated
	 */
	EReference getLayoutContainer_Layout();

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
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.ClassForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
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
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.StateForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.StateForm#getFolder()
	 * @see #getStateForm()
	 * @generated
	 */
	EReference getStateForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.WorkspaceSecurityConstraint <em>Workspace Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workspace Security Constraint</em>'.
	 * @see org.nakeduml.uim.WorkspaceSecurityConstraint
	 * @generated
	 */
	EClass getWorkspaceSecurityConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.WorkspaceSecurityConstraint#isRequiresGroupOwnership <em>Requires Group Ownership</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Group Ownership</em>'.
	 * @see org.nakeduml.uim.WorkspaceSecurityConstraint#isRequiresGroupOwnership()
	 * @see #getWorkspaceSecurityConstraint()
	 * @generated
	 */
	EAttribute getWorkspaceSecurityConstraint_RequiresGroupOwnership();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.WorkspaceSecurityConstraint#isRequiresOwnership <em>Requires Ownership</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Ownership</em>'.
	 * @see org.nakeduml.uim.WorkspaceSecurityConstraint#isRequiresOwnership()
	 * @see #getWorkspaceSecurityConstraint()
	 * @generated
	 */
	EAttribute getWorkspaceSecurityConstraint_RequiresOwnership();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.WorkspaceSecurityConstraint#getRequiredRoles <em>Required Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Roles</em>'.
	 * @see org.nakeduml.uim.WorkspaceSecurityConstraint#getRequiredRoles()
	 * @see #getWorkspaceSecurityConstraint()
	 * @generated
	 */
	EReference getWorkspaceSecurityConstraint_RequiredRoles();

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
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.OperationInvocationForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.OperationInvocationForm#getFolder()
	 * @see #getOperationInvocationForm()
	 * @generated
	 */
	EReference getOperationInvocationForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.nakeduml.uim.UimComponent
	 * @generated
	 */
	EClass getUimComponent();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control</em>'.
	 * @see org.nakeduml.uim.UimControl
	 * @generated
	 */
	EClass getUimControl();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimControl#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.nakeduml.uim.UimControl#getWidth()
	 * @see #getUimControl()
	 * @generated
	 */
	EAttribute getUimControl_Width();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.UimControl#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Field</em>'.
	 * @see org.nakeduml.uim.UimControl#getField()
	 * @see #getUimControl()
	 * @generated
	 */
	EReference getUimControl_Field();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.OperationTaskForm <em>Operation Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Task Form</em>'.
	 * @see org.nakeduml.uim.OperationTaskForm
	 * @generated
	 */
	EClass getOperationTaskForm();

	/**
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.OperationTaskForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
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
	 * Returns the meta object for the reference '{@link org.nakeduml.uim.ActionTaskForm#getFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Folder</em>'.
	 * @see org.nakeduml.uim.ActionTaskForm#getFolder()
	 * @see #getActionTaskForm()
	 * @generated
	 */
	EReference getActionTaskForm_Folder();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.nakeduml.uim.UimAction
	 * @generated
	 */
	EClass getUimAction();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.SecurityConstraint <em>Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Security Constraint</em>'.
	 * @see org.nakeduml.uim.SecurityConstraint
	 * @generated
	 */
	EClass getSecurityConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.SecurityConstraint#isInheritFromParent <em>Inherit From Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit From Parent</em>'.
	 * @see org.nakeduml.uim.SecurityConstraint#isInheritFromParent()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EAttribute getSecurityConstraint_InheritFromParent();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimGridLayout <em>Grid Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grid Layout</em>'.
	 * @see org.nakeduml.uim.UimGridLayout
	 * @generated
	 */
	EClass getUimGridLayout();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimGridLayout#getNumberOfColumns <em>Number Of Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Columns</em>'.
	 * @see org.nakeduml.uim.UimGridLayout#getNumberOfColumns()
	 * @see #getUimGridLayout()
	 * @generated
	 */
	EAttribute getUimGridLayout_NumberOfColumns();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimDataTable <em>Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Table</em>'.
	 * @see org.nakeduml.uim.UimDataTable
	 * @generated
	 */
	EClass getUimDataTable();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UimDataTable#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.nakeduml.uim.UimDataTable#getBinding()
	 * @see #getUimDataTable()
	 * @generated
	 */
	EReference getUimDataTable_Binding();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.UimDataTable#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nakeduml.uim.UimDataTable#getChildren()
	 * @see #getUimDataTable()
	 * @generated
	 */
	EReference getUimDataTable_Children();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding</em>'.
	 * @see org.nakeduml.uim.UimBinding
	 * @generated
	 */
	EClass getUimBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UimBinding#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Next</em>'.
	 * @see org.nakeduml.uim.UimBinding#getNext()
	 * @see #getUimBinding()
	 * @generated
	 */
	EReference getUimBinding_Next();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimDataColumn <em>Data Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Column</em>'.
	 * @see org.nakeduml.uim.UimDataColumn
	 * @generated
	 */
	EClass getUimDataColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimDataColumn#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.nakeduml.uim.UimDataColumn#getWidth()
	 * @see #getUimDataColumn()
	 * @generated
	 */
	EAttribute getUimDataColumn_Width();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.UimDataColumn#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.nakeduml.uim.UimDataColumn#getParent()
	 * @see #getUimDataColumn()
	 * @generated
	 */
	EReference getUimDataColumn_Parent();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.EntityFolder <em>Entity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Folder</em>'.
	 * @see org.nakeduml.uim.EntityFolder
	 * @generated
	 */
	EClass getEntityFolder();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.OperationContainingFolder <em>Operation Containing Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Containing Folder</em>'.
	 * @see org.nakeduml.uim.OperationContainingFolder
	 * @generated
	 */
	EClass getOperationContainingFolder();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimTabPanel <em>Tab Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tab Panel</em>'.
	 * @see org.nakeduml.uim.UimTabPanel
	 * @generated
	 */
	EClass getUimTabPanel();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimTabPanel#getActiveTabIndex <em>Active Tab Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active Tab Index</em>'.
	 * @see org.nakeduml.uim.UimTabPanel#getActiveTabIndex()
	 * @see #getUimTabPanel()
	 * @generated
	 */
	EAttribute getUimTabPanel_ActiveTabIndex();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.UimTabPanel#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nakeduml.uim.UimTabPanel#getChildren()
	 * @see #getUimTabPanel()
	 * @generated
	 */
	EReference getUimTabPanel_Children();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimTab <em>Tab</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tab</em>'.
	 * @see org.nakeduml.uim.UimTab
	 * @generated
	 */
	EClass getUimTab();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.UimTab#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.nakeduml.uim.UimTab#getParent()
	 * @see #getUimTab()
	 * @generated
	 */
	EReference getUimTab_Parent();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimCheckBox <em>Check Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Check Box</em>'.
	 * @see org.nakeduml.uim.UimCheckBox
	 * @generated
	 */
	EClass getUimCheckBox();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimLookup <em>Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lookup</em>'.
	 * @see org.nakeduml.uim.UimLookup
	 * @generated
	 */
	EClass getUimLookup();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UimLookup#getLookupSource <em>Lookup Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lookup Source</em>'.
	 * @see org.nakeduml.uim.UimLookup#getLookupSource()
	 * @see #getUimLookup()
	 * @generated
	 */
	EReference getUimLookup_LookupSource();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see org.nakeduml.uim.UimText
	 * @generated
	 */
	EClass getUimText();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimTextArea <em>Text Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Area</em>'.
	 * @see org.nakeduml.uim.UimTextArea
	 * @generated
	 */
	EClass getUimTextArea();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimTextArea#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rows</em>'.
	 * @see org.nakeduml.uim.UimTextArea#getRows()
	 * @see #getUimTextArea()
	 * @generated
	 */
	EAttribute getUimTextArea_Rows();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimDropdown <em>Dropdown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dropdown</em>'.
	 * @see org.nakeduml.uim.UimDropdown
	 * @generated
	 */
	EClass getUimDropdown();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimDatePopup <em>Date Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Popup</em>'.
	 * @see org.nakeduml.uim.UimDatePopup
	 * @generated
	 */
	EClass getUimDatePopup();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimSingleSelectListBox <em>Single Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Select List Box</em>'.
	 * @see org.nakeduml.uim.UimSingleSelectListBox
	 * @generated
	 */
	EClass getUimSingleSelectListBox();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimSingleSelectListBox#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rows</em>'.
	 * @see org.nakeduml.uim.UimSingleSelectListBox#getRows()
	 * @see #getUimSingleSelectListBox()
	 * @generated
	 */
	EAttribute getUimSingleSelectListBox_Rows();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see org.nakeduml.uim.UimContainer
	 * @generated
	 */
	EClass getUimContainer();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimSingleSelectTreeView <em>Single Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Select Tree View</em>'.
	 * @see org.nakeduml.uim.UimSingleSelectTreeView
	 * @generated
	 */
	EClass getUimSingleSelectTreeView();

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
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layout</em>'.
	 * @see org.nakeduml.uim.UimLayout
	 * @generated
	 */
	EClass getUimLayout();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.UimLayout#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.nakeduml.uim.UimLayout#getParent()
	 * @see #getUimLayout()
	 * @generated
	 */
	EReference getUimLayout_Parent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.UimLayout#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nakeduml.uim.UimLayout#getChildren()
	 * @see #getUimLayout()
	 * @generated
	 */
	EReference getUimLayout_Children();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimToolbarLayout <em>Toolbar Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Toolbar Layout</em>'.
	 * @see org.nakeduml.uim.UimToolbarLayout
	 * @generated
	 */
	EClass getUimToolbarLayout();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimBorderLayout <em>Border Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Border Layout</em>'.
	 * @see org.nakeduml.uim.UimBorderLayout
	 * @generated
	 */
	EClass getUimBorderLayout();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UimBorderLayout#isHorizontal <em>Horizontal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Horizontal</em>'.
	 * @see org.nakeduml.uim.UimBorderLayout#isHorizontal()
	 * @see #getUimBorderLayout()
	 * @generated
	 */
	EAttribute getUimBorderLayout_Horizontal();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimXYLayout <em>XY Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XY Layout</em>'.
	 * @see org.nakeduml.uim.UimXYLayout
	 * @generated
	 */
	EClass getUimXYLayout();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimMultiSelectTreeView <em>Multi Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Select Tree View</em>'.
	 * @see org.nakeduml.uim.UimMultiSelectTreeView
	 * @generated
	 */
	EClass getUimMultiSelectTreeView();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimMultiSelectListBox <em>Multi Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Select List Box</em>'.
	 * @see org.nakeduml.uim.UimMultiSelectListBox
	 * @generated
	 */
	EClass getUimMultiSelectListBox();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimMultiSelectPopupSearch <em>Multi Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multi Select Popup Search</em>'.
	 * @see org.nakeduml.uim.UimMultiSelectPopupSearch
	 * @generated
	 */
	EClass getUimMultiSelectPopupSearch();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimSingleSelectPopupSearch <em>Single Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Select Popup Search</em>'.
	 * @see org.nakeduml.uim.UimSingleSelectPopupSearch
	 * @generated
	 */
	EClass getUimSingleSelectPopupSearch();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimToggleButton <em>Toggle Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Toggle Button</em>'.
	 * @see org.nakeduml.uim.UimToggleButton
	 * @generated
	 */
	EClass getUimToggleButton();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimNumberScroller <em>Number Scroller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Scroller</em>'.
	 * @see org.nakeduml.uim.UimNumberScroller
	 * @generated
	 */
	EClass getUimNumberScroller();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UmlReference <em>Uml Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uml Reference</em>'.
	 * @see org.nakeduml.uim.UmlReference
	 * @generated
	 */
	EClass getUmlReference();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.UmlReference#getUmlElementUid <em>Uml Element Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uml Element Uid</em>'.
	 * @see org.nakeduml.uim.UmlReference#getUmlElementUid()
	 * @see #getUmlReference()
	 * @generated
	 */
	EAttribute getUmlReference_UmlElementUid();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UserInteractionWorkspace <em>User Interaction Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interaction Workspace</em>'.
	 * @see org.nakeduml.uim.UserInteractionWorkspace
	 * @generated
	 */
	EClass getUserInteractionWorkspace();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UserInteractionWorkspace#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visibility</em>'.
	 * @see org.nakeduml.uim.UserInteractionWorkspace#getVisibility()
	 * @see #getUserInteractionWorkspace()
	 * @generated
	 */
	EReference getUserInteractionWorkspace_Visibility();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.UserInteractionWorkspace#getEditability <em>Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editability</em>'.
	 * @see org.nakeduml.uim.UserInteractionWorkspace#getEditability()
	 * @see #getUserInteractionWorkspace()
	 * @generated
	 */
	EReference getUserInteractionWorkspace_Editability();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.RequiredRole <em>Required Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Role</em>'.
	 * @see org.nakeduml.uim.RequiredRole
	 * @generated
	 */
	EClass getRequiredRole();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.SecureObject <em>Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secure Object</em>'.
	 * @see org.nakeduml.uim.SecureObject
	 * @generated
	 */
	EClass getSecureObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.SecureObject#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visibility</em>'.
	 * @see org.nakeduml.uim.SecureObject#getVisibility()
	 * @see #getSecureObject()
	 * @generated
	 */
	EReference getSecureObject_Visibility();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.EditableSecureObject <em>Editable Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Editable Secure Object</em>'.
	 * @see org.nakeduml.uim.EditableSecureObject
	 * @generated
	 */
	EClass getEditableSecureObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.EditableSecureObject#getEditability <em>Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editability</em>'.
	 * @see org.nakeduml.uim.EditableSecureObject#getEditability()
	 * @see #getEditableSecureObject()
	 * @generated
	 */
	EReference getEditableSecureObject_Editability();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimFullLayout <em>Full Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Full Layout</em>'.
	 * @see org.nakeduml.uim.UimFullLayout
	 * @generated
	 */
	EClass getUimFullLayout();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.OutlayableComponent <em>Outlayable Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outlayable Component</em>'.
	 * @see org.nakeduml.uim.OutlayableComponent
	 * @generated
	 */
	EClass getOutlayableComponent();

	/**
	 * Returns the meta object for the container reference '{@link org.nakeduml.uim.OutlayableComponent#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.nakeduml.uim.OutlayableComponent#getParent()
	 * @see #getOutlayableComponent()
	 * @generated
	 */
	EReference getOutlayableComponent_Parent();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.UimPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Panel</em>'.
	 * @see org.nakeduml.uim.UimPanel
	 * @generated
	 */
	EClass getUimPanel();

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
	UimFactory getUimFactory();

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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimFormImpl <em>Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimFormImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimForm()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UserInteractionModelImpl <em>User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UserInteractionModelImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUserInteractionModel()
		 * @generated
		 */
		EClass USER_INTERACTION_MODEL = eINSTANCE.getUserInteractionModel();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.AbstractFormFolderImpl <em>Abstract Form Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.AbstractFormFolderImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getAbstractFormFolder()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.AbstractFolderImpl <em>Abstract Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.AbstractFolderImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getAbstractFolder()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimFieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimFieldImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimField()
		 * @generated
		 */
		EClass UIM_FIELD = eINSTANCE.getUimField();

		/**
		 * The meta object literal for the '<em><b>Control</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FIELD__CONTROL = eINSTANCE.getUimField_Control();

		/**
		 * The meta object literal for the '<em><b>Control Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_FIELD__CONTROL_KIND = eINSTANCE.getUimField_ControlKind();

		/**
		 * The meta object literal for the '<em><b>Label Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_FIELD__LABEL_WIDTH = eINSTANCE.getUimField_LabelWidth();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FIELD__BINDING = eINSTANCE.getUimField_Binding();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimNavigationImpl <em>Navigation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimNavigationImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimNavigation()
		 * @generated
		 */
		EClass UIM_NAVIGATION = eINSTANCE.getUimNavigation();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.LayoutContainerImpl <em>Layout Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.LayoutContainerImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getLayoutContainer()
		 * @generated
		 */
		EClass LAYOUT_CONTAINER = eINSTANCE.getLayoutContainer();

		/**
		 * The meta object literal for the '<em><b>Layout</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYOUT_CONTAINER__LAYOUT = eINSTANCE.getLayoutContainer_Layout();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.ClassFormImpl <em>Class Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.ClassFormImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getClassForm()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.StateFormImpl <em>State Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.StateFormImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getStateForm()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.WorkspaceSecurityConstraintImpl <em>Workspace Security Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.WorkspaceSecurityConstraintImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getWorkspaceSecurityConstraint()
		 * @generated
		 */
		EClass WORKSPACE_SECURITY_CONSTRAINT = eINSTANCE.getWorkspaceSecurityConstraint();

		/**
		 * The meta object literal for the '<em><b>Requires Group Ownership</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = eINSTANCE.getWorkspaceSecurityConstraint_RequiresGroupOwnership();

		/**
		 * The meta object literal for the '<em><b>Requires Ownership</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = eINSTANCE.getWorkspaceSecurityConstraint_RequiresOwnership();

		/**
		 * The meta object literal for the '<em><b>Required Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES = eINSTANCE.getWorkspaceSecurityConstraint_RequiredRoles();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.OperationInvocationFormImpl <em>Operation Invocation Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.OperationInvocationFormImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getOperationInvocationForm()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimComponentImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimComponent()
		 * @generated
		 */
		EClass UIM_COMPONENT = eINSTANCE.getUimComponent();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UserInteractionElementImpl <em>User Interaction Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UserInteractionElementImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUserInteractionElement()
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
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getOperationAction()
		 * @generated
		 */
		EClass OPERATION_ACTION = eINSTANCE.getOperationAction();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.NavigationToOperationImpl <em>Navigation To Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.NavigationToOperationImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getNavigationToOperation()
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
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getBuiltInAction()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimControlImpl <em>Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimControlImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimControl()
		 * @generated
		 */
		EClass UIM_CONTROL = eINSTANCE.getUimControl();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_CONTROL__WIDTH = eINSTANCE.getUimControl_Width();

		/**
		 * The meta object literal for the '<em><b>Field</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_CONTROL__FIELD = eINSTANCE.getUimControl_Field();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.NavigationToEntityImpl <em>Navigation To Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.NavigationToEntityImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getNavigationToEntity()
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
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getTransitionAction()
		 * @generated
		 */
		EClass TRANSITION_ACTION = eINSTANCE.getTransitionAction();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.OperationTaskFormImpl <em>Operation Task Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.OperationTaskFormImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getOperationTaskForm()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.ActionTaskFormImpl <em>Action Task Form</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.ActionTaskFormImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getActionTaskForm()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimActionImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimAction()
		 * @generated
		 */
		EClass UIM_ACTION = eINSTANCE.getUimAction();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.SecurityConstraintImpl <em>Security Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.SecurityConstraintImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getSecurityConstraint()
		 * @generated
		 */
		EClass SECURITY_CONSTRAINT = eINSTANCE.getSecurityConstraint();

		/**
		 * The meta object literal for the '<em><b>Inherit From Parent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_CONSTRAINT__INHERIT_FROM_PARENT = eINSTANCE.getSecurityConstraint_InheritFromParent();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimGridLayoutImpl <em>Grid Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimGridLayoutImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimGridLayout()
		 * @generated
		 */
		EClass UIM_GRID_LAYOUT = eINSTANCE.getUimGridLayout();

		/**
		 * The meta object literal for the '<em><b>Number Of Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_GRID_LAYOUT__NUMBER_OF_COLUMNS = eINSTANCE.getUimGridLayout_NumberOfColumns();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimDataTableImpl <em>Data Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimDataTableImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimDataTable()
		 * @generated
		 */
		EClass UIM_DATA_TABLE = eINSTANCE.getUimDataTable();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_DATA_TABLE__BINDING = eINSTANCE.getUimDataTable_Binding();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_DATA_TABLE__CHILDREN = eINSTANCE.getUimDataTable_Children();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimBindingImpl <em>Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimBindingImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimBinding()
		 * @generated
		 */
		EClass UIM_BINDING = eINSTANCE.getUimBinding();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_BINDING__NEXT = eINSTANCE.getUimBinding_Next();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.PropertyRefImpl <em>Property Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.PropertyRefImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getPropertyRef()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimDataColumnImpl <em>Data Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimDataColumnImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimDataColumn()
		 * @generated
		 */
		EClass UIM_DATA_COLUMN = eINSTANCE.getUimDataColumn();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_DATA_COLUMN__WIDTH = eINSTANCE.getUimDataColumn_Width();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_DATA_COLUMN__PARENT = eINSTANCE.getUimDataColumn_Parent();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.TableBindingImpl <em>Table Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.TableBindingImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getTableBinding()
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
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getFieldBinding()
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
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getFormPanel()
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
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getStateMachineFolder()
		 * @generated
		 */
		EClass STATE_MACHINE_FOLDER = eINSTANCE.getStateMachineFolder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.EntityFolderImpl <em>Entity Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.EntityFolderImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getEntityFolder()
		 * @generated
		 */
		EClass ENTITY_FOLDER = eINSTANCE.getEntityFolder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.ActivityFolderImpl <em>Activity Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.ActivityFolderImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getActivityFolder()
		 * @generated
		 */
		EClass ACTIVITY_FOLDER = eINSTANCE.getActivityFolder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.OperationContainingFolderImpl <em>Operation Containing Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.OperationContainingFolderImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getOperationContainingFolder()
		 * @generated
		 */
		EClass OPERATION_CONTAINING_FOLDER = eINSTANCE.getOperationContainingFolder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.NavigationBindingImpl <em>Navigation Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.NavigationBindingImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getNavigationBinding()
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
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getDetailPanel()
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
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getPackageFolder()
		 * @generated
		 */
		EClass PACKAGE_FOLDER = eINSTANCE.getPackageFolder();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimTabPanelImpl <em>Tab Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimTabPanelImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimTabPanel()
		 * @generated
		 */
		EClass UIM_TAB_PANEL = eINSTANCE.getUimTabPanel();

		/**
		 * The meta object literal for the '<em><b>Active Tab Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_TAB_PANEL__ACTIVE_TAB_INDEX = eINSTANCE.getUimTabPanel_ActiveTabIndex();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_TAB_PANEL__CHILDREN = eINSTANCE.getUimTabPanel_Children();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimTabImpl <em>Tab</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimTabImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimTab()
		 * @generated
		 */
		EClass UIM_TAB = eINSTANCE.getUimTab();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_TAB__PARENT = eINSTANCE.getUimTab_Parent();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimCheckBoxImpl <em>Check Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimCheckBoxImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimCheckBox()
		 * @generated
		 */
		EClass UIM_CHECK_BOX = eINSTANCE.getUimCheckBox();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimLookupImpl <em>Lookup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimLookupImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimLookup()
		 * @generated
		 */
		EClass UIM_LOOKUP = eINSTANCE.getUimLookup();

		/**
		 * The meta object literal for the '<em><b>Lookup Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_LOOKUP__LOOKUP_SOURCE = eINSTANCE.getUimLookup_LookupSource();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.LookupBindingImpl <em>Lookup Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.LookupBindingImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getLookupBinding()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimTextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimTextImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimText()
		 * @generated
		 */
		EClass UIM_TEXT = eINSTANCE.getUimText();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimTextAreaImpl <em>Text Area</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimTextAreaImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimTextArea()
		 * @generated
		 */
		EClass UIM_TEXT_AREA = eINSTANCE.getUimTextArea();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_TEXT_AREA__ROWS = eINSTANCE.getUimTextArea_Rows();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimDropdownImpl <em>Dropdown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimDropdownImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimDropdown()
		 * @generated
		 */
		EClass UIM_DROPDOWN = eINSTANCE.getUimDropdown();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimDatePopupImpl <em>Date Popup</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimDatePopupImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimDatePopup()
		 * @generated
		 */
		EClass UIM_DATE_POPUP = eINSTANCE.getUimDatePopup();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimSingleSelectListBoxImpl <em>Single Select List Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimSingleSelectListBoxImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimSingleSelectListBox()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_LIST_BOX = eINSTANCE.getUimSingleSelectListBox();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_SINGLE_SELECT_LIST_BOX__ROWS = eINSTANCE.getUimSingleSelectListBox_Rows();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimContainerImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimContainer()
		 * @generated
		 */
		EClass UIM_CONTAINER = eINSTANCE.getUimContainer();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimSingleSelectTreeViewImpl <em>Single Select Tree View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimSingleSelectTreeViewImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimSingleSelectTreeView()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_TREE_VIEW = eINSTANCE.getUimSingleSelectTreeView();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.MasterComponent <em>Master Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.MasterComponent
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getMasterComponent()
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
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimLayoutImpl <em>Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimLayoutImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimLayout()
		 * @generated
		 */
		EClass UIM_LAYOUT = eINSTANCE.getUimLayout();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_LAYOUT__PARENT = eINSTANCE.getUimLayout_Parent();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_LAYOUT__CHILDREN = eINSTANCE.getUimLayout_Children();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimToolbarLayoutImpl <em>Toolbar Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimToolbarLayoutImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimToolbarLayout()
		 * @generated
		 */
		EClass UIM_TOOLBAR_LAYOUT = eINSTANCE.getUimToolbarLayout();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimBorderLayoutImpl <em>Border Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimBorderLayoutImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimBorderLayout()
		 * @generated
		 */
		EClass UIM_BORDER_LAYOUT = eINSTANCE.getUimBorderLayout();

		/**
		 * The meta object literal for the '<em><b>Horizontal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_BORDER_LAYOUT__HORIZONTAL = eINSTANCE.getUimBorderLayout_Horizontal();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimXYLayoutImpl <em>XY Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimXYLayoutImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimXYLayout()
		 * @generated
		 */
		EClass UIM_XY_LAYOUT = eINSTANCE.getUimXYLayout();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimMultiSelectTreeViewImpl <em>Multi Select Tree View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimMultiSelectTreeViewImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimMultiSelectTreeView()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_TREE_VIEW = eINSTANCE.getUimMultiSelectTreeView();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimMultiSelectListBoxImpl <em>Multi Select List Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimMultiSelectListBoxImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimMultiSelectListBox()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_LIST_BOX = eINSTANCE.getUimMultiSelectListBox();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimMultiSelectPopupSearchImpl <em>Multi Select Popup Search</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimMultiSelectPopupSearchImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimMultiSelectPopupSearch()
		 * @generated
		 */
		EClass UIM_MULTI_SELECT_POPUP_SEARCH = eINSTANCE.getUimMultiSelectPopupSearch();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimSingleSelectPopupSearchImpl <em>Single Select Popup Search</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimSingleSelectPopupSearchImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimSingleSelectPopupSearch()
		 * @generated
		 */
		EClass UIM_SINGLE_SELECT_POPUP_SEARCH = eINSTANCE.getUimSingleSelectPopupSearch();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimToggleButtonImpl <em>Toggle Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimToggleButtonImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimToggleButton()
		 * @generated
		 */
		EClass UIM_TOGGLE_BUTTON = eINSTANCE.getUimToggleButton();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimNumberScrollerImpl <em>Number Scroller</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimNumberScrollerImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimNumberScroller()
		 * @generated
		 */
		EClass UIM_NUMBER_SCROLLER = eINSTANCE.getUimNumberScroller();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UmlReferenceImpl <em>Uml Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UmlReferenceImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUmlReference()
		 * @generated
		 */
		EClass UML_REFERENCE = eINSTANCE.getUmlReference();

		/**
		 * The meta object literal for the '<em><b>Uml Element Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UML_REFERENCE__UML_ELEMENT_UID = eINSTANCE.getUmlReference_UmlElementUid();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UserInteractionWorkspaceImpl <em>User Interaction Workspace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UserInteractionWorkspaceImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUserInteractionWorkspace()
		 * @generated
		 */
		EClass USER_INTERACTION_WORKSPACE = eINSTANCE.getUserInteractionWorkspace();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERACTION_WORKSPACE__VISIBILITY = eINSTANCE.getUserInteractionWorkspace_Visibility();

		/**
		 * The meta object literal for the '<em><b>Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERACTION_WORKSPACE__EDITABILITY = eINSTANCE.getUserInteractionWorkspace_Editability();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.RequiredRoleImpl <em>Required Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.RequiredRoleImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getRequiredRole()
		 * @generated
		 */
		EClass REQUIRED_ROLE = eINSTANCE.getRequiredRole();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.SecureObjectImpl <em>Secure Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.SecureObjectImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getSecureObject()
		 * @generated
		 */
		EClass SECURE_OBJECT = eINSTANCE.getSecureObject();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURE_OBJECT__VISIBILITY = eINSTANCE.getSecureObject_Visibility();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.EditableSecureObjectImpl <em>Editable Secure Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.EditableSecureObjectImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getEditableSecureObject()
		 * @generated
		 */
		EClass EDITABLE_SECURE_OBJECT = eINSTANCE.getEditableSecureObject();

		/**
		 * The meta object literal for the '<em><b>Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITABLE_SECURE_OBJECT__EDITABILITY = eINSTANCE.getEditableSecureObject_Editability();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimFullLayoutImpl <em>Full Layout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimFullLayoutImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimFullLayout()
		 * @generated
		 */
		EClass UIM_FULL_LAYOUT = eINSTANCE.getUimFullLayout();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.OutlayableComponentImpl <em>Outlayable Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.OutlayableComponentImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getOutlayableComponent()
		 * @generated
		 */
		EClass OUTLAYABLE_COMPONENT = eINSTANCE.getOutlayableComponent();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTLAYABLE_COMPONENT__PARENT = eINSTANCE.getOutlayableComponent_Parent();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.impl.UimPanelImpl <em>Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.impl.UimPanelImpl
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getUimPanel()
		 * @generated
		 */
		EClass UIM_PANEL = eINSTANCE.getUimPanel();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.ActionKind <em>Action Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.ActionKind
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getActionKind()
		 * @generated
		 */
		EEnum ACTION_KIND = eINSTANCE.getActionKind();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.ControlKind <em>Control Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.ControlKind
		 * @see org.nakeduml.uim.impl.UimPackageImpl#getControlKind()
		 * @generated
		 */
		EEnum CONTROL_KIND = eINSTANCE.getControlKind();

	}

} //UimPackage
