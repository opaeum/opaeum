/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.folder;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nakeduml.uim.folder.FolderPackage
 * @generated
 */
public interface FolderFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FolderFactory eINSTANCE = org.nakeduml.uim.folder.impl.FolderFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Activity Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Folder</em>'.
	 * @generated
	 */
	ActivityFolder createActivityFolder();

	/**
	 * Returns a new object of class '<em>Entity Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Folder</em>'.
	 * @generated
	 */
	EntityFolder createEntityFolder();

	/**
	 * Returns a new object of class '<em>State Machine Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Machine Folder</em>'.
	 * @generated
	 */
	StateMachineFolder createStateMachineFolder();

	/**
	 * Returns a new object of class '<em>Package Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Folder</em>'.
	 * @generated
	 */
	PackageFolder createPackageFolder();

	/**
	 * Returns a new object of class '<em>User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Interaction Model</em>'.
	 * @generated
	 */
	UserInteractionModel createUserInteractionModel();

	/**
	 * Returns a new object of class '<em>User Interaction Workspace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Interaction Workspace</em>'.
	 * @generated
	 */
	UserInteractionWorkspace createUserInteractionWorkspace();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FolderPackage getFolderPackage();

} //FolderFactory
