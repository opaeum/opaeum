/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.folder;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;

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
 * @see org.opaeum.uim.folder.FolderFactory
 * @model kind="package"
 * @generated
 */
public interface FolderPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "folder";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/folder/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "fld";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FolderPackage eINSTANCE = org.opaeum.uim.folder.impl.FolderPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.AbstractFolderImpl <em>Abstract Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.AbstractFolderImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getAbstractFolder()
	 * @generated
	 */
	int ABSTRACT_FOLDER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER__NAME = UimPackage.USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER__UML_ELEMENT_UID = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER__CHILDREN = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_FOLDER_FEATURE_COUNT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.AbstractFormFolderImpl <em>Abstract Form Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.AbstractFormFolderImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getAbstractFormFolder()
	 * @generated
	 */
	int ABSTRACT_FORM_FOLDER = 4;

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
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.ActivityFolderImpl <em>Activity Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.ActivityFolderImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getActivityFolder()
	 * @generated
	 */
	int ACTIVITY_FOLDER = 1;

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
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.OperationContainingFolderImpl <em>Operation Containing Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.OperationContainingFolderImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getOperationContainingFolder()
	 * @generated
	 */
	int OPERATION_CONTAINING_FOLDER = 6;

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
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.EntityFolderImpl <em>Entity Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.EntityFolderImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getEntityFolder()
	 * @generated
	 */
	int ENTITY_FOLDER = 2;

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
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.StateMachineFolderImpl <em>State Machine Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.StateMachineFolderImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getStateMachineFolder()
	 * @generated
	 */
	int STATE_MACHINE_FOLDER = 3;

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
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.PackageFolderImpl <em>Package Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.PackageFolderImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getPackageFolder()
	 * @generated
	 */
	int PACKAGE_FOLDER = 5;

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
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.UserInteractionModelImpl <em>User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.UserInteractionModelImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getUserInteractionModel()
	 * @generated
	 */
	int USER_INTERACTION_MODEL = 7;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__NAME = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__CHILDREN = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__VISIBILITY = UimPackage.UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__EDITABILITY = UimPackage.UML_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL__PARENT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_MODEL_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.folder.impl.UserInteractionWorkspaceImpl <em>User Interaction Workspace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.folder.impl.UserInteractionWorkspaceImpl
	 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getUserInteractionWorkspace()
	 * @generated
	 */
	int USER_INTERACTION_WORKSPACE = 8;

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
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.AbstractFolder <em>Abstract Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Folder</em>'.
	 * @see org.opaeum.uim.folder.AbstractFolder
	 * @generated
	 */
	EClass getAbstractFolder();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.folder.AbstractFolder#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.opaeum.uim.folder.AbstractFolder#getChildren()
	 * @see #getAbstractFolder()
	 * @generated
	 */
	EReference getAbstractFolder_Children();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.ActivityFolder <em>Activity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Folder</em>'.
	 * @see org.opaeum.uim.folder.ActivityFolder
	 * @generated
	 */
	EClass getActivityFolder();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.EntityFolder <em>Entity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Folder</em>'.
	 * @see org.opaeum.uim.folder.EntityFolder
	 * @generated
	 */
	EClass getEntityFolder();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.StateMachineFolder <em>State Machine Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Machine Folder</em>'.
	 * @see org.opaeum.uim.folder.StateMachineFolder
	 * @generated
	 */
	EClass getStateMachineFolder();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.AbstractFormFolder <em>Abstract Form Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Form Folder</em>'.
	 * @see org.opaeum.uim.folder.AbstractFormFolder
	 * @generated
	 */
	EClass getAbstractFormFolder();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.folder.AbstractFormFolder#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.opaeum.uim.folder.AbstractFormFolder#getParent()
	 * @see #getAbstractFormFolder()
	 * @generated
	 */
	EReference getAbstractFormFolder_Parent();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.PackageFolder <em>Package Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Folder</em>'.
	 * @see org.opaeum.uim.folder.PackageFolder
	 * @generated
	 */
	EClass getPackageFolder();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.OperationContainingFolder <em>Operation Containing Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Containing Folder</em>'.
	 * @see org.opaeum.uim.folder.OperationContainingFolder
	 * @generated
	 */
	EClass getOperationContainingFolder();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.UserInteractionModel <em>User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interaction Model</em>'.
	 * @see org.opaeum.uim.folder.UserInteractionModel
	 * @generated
	 */
	EClass getUserInteractionModel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.folder.UserInteractionWorkspace <em>User Interaction Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interaction Workspace</em>'.
	 * @see org.opaeum.uim.folder.UserInteractionWorkspace
	 * @generated
	 */
	EClass getUserInteractionWorkspace();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.folder.UserInteractionWorkspace#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visibility</em>'.
	 * @see org.opaeum.uim.folder.UserInteractionWorkspace#getVisibility()
	 * @see #getUserInteractionWorkspace()
	 * @generated
	 */
	EReference getUserInteractionWorkspace_Visibility();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.folder.UserInteractionWorkspace#getEditability <em>Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editability</em>'.
	 * @see org.opaeum.uim.folder.UserInteractionWorkspace#getEditability()
	 * @see #getUserInteractionWorkspace()
	 * @generated
	 */
	EReference getUserInteractionWorkspace_Editability();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FolderFactory getFolderFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.AbstractFolderImpl <em>Abstract Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.AbstractFolderImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getAbstractFolder()
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
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.ActivityFolderImpl <em>Activity Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.ActivityFolderImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getActivityFolder()
		 * @generated
		 */
		EClass ACTIVITY_FOLDER = eINSTANCE.getActivityFolder();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.EntityFolderImpl <em>Entity Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.EntityFolderImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getEntityFolder()
		 * @generated
		 */
		EClass ENTITY_FOLDER = eINSTANCE.getEntityFolder();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.StateMachineFolderImpl <em>State Machine Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.StateMachineFolderImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getStateMachineFolder()
		 * @generated
		 */
		EClass STATE_MACHINE_FOLDER = eINSTANCE.getStateMachineFolder();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.AbstractFormFolderImpl <em>Abstract Form Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.AbstractFormFolderImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getAbstractFormFolder()
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
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.PackageFolderImpl <em>Package Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.PackageFolderImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getPackageFolder()
		 * @generated
		 */
		EClass PACKAGE_FOLDER = eINSTANCE.getPackageFolder();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.OperationContainingFolderImpl <em>Operation Containing Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.OperationContainingFolderImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getOperationContainingFolder()
		 * @generated
		 */
		EClass OPERATION_CONTAINING_FOLDER = eINSTANCE.getOperationContainingFolder();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.UserInteractionModelImpl <em>User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.UserInteractionModelImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getUserInteractionModel()
		 * @generated
		 */
		EClass USER_INTERACTION_MODEL = eINSTANCE.getUserInteractionModel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.folder.impl.UserInteractionWorkspaceImpl <em>User Interaction Workspace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.folder.impl.UserInteractionWorkspaceImpl
		 * @see org.opaeum.uim.folder.impl.FolderPackageImpl#getUserInteractionWorkspace()
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

	}

} //FolderPackage
