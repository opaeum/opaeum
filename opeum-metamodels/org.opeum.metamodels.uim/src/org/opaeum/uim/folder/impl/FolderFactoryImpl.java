/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.folder.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opeum.uim.folder.ActivityFolder;
import org.opeum.uim.folder.EntityFolder;
import org.opeum.uim.folder.FolderFactory;
import org.opeum.uim.folder.FolderPackage;
import org.opeum.uim.folder.PackageFolder;
import org.opeum.uim.folder.StateMachineFolder;
import org.opeum.uim.folder.UserInteractionModel;
import org.opeum.uim.folder.UserInteractionWorkspace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FolderFactoryImpl extends EFactoryImpl implements FolderFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FolderFactory init() {
		try {
			FolderFactory theFolderFactory = (FolderFactory)EPackage.Registry.INSTANCE.getEFactory("http://opeum.org/uimetamodel/folder/1.0"); 
			if (theFolderFactory != null) {
				return theFolderFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FolderFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FolderFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case FolderPackage.ACTIVITY_FOLDER: return createActivityFolder();
			case FolderPackage.ENTITY_FOLDER: return createEntityFolder();
			case FolderPackage.STATE_MACHINE_FOLDER: return createStateMachineFolder();
			case FolderPackage.PACKAGE_FOLDER: return createPackageFolder();
			case FolderPackage.USER_INTERACTION_MODEL: return createUserInteractionModel();
			case FolderPackage.USER_INTERACTION_WORKSPACE: return createUserInteractionWorkspace();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityFolder createActivityFolder() {
		ActivityFolderImpl activityFolder = new ActivityFolderImpl();
		return activityFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityFolder createEntityFolder() {
		EntityFolderImpl entityFolder = new EntityFolderImpl();
		return entityFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachineFolder createStateMachineFolder() {
		StateMachineFolderImpl stateMachineFolder = new StateMachineFolderImpl();
		return stateMachineFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageFolder createPackageFolder() {
		PackageFolderImpl packageFolder = new PackageFolderImpl();
		return packageFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionModel createUserInteractionModel() {
		UserInteractionModelImpl userInteractionModel = new UserInteractionModelImpl();
		return userInteractionModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionWorkspace createUserInteractionWorkspace() {
		UserInteractionWorkspaceImpl userInteractionWorkspace = new UserInteractionWorkspaceImpl();
		return userInteractionWorkspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FolderPackage getFolderPackage() {
		return (FolderPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FolderPackage getPackage() {
		return FolderPackage.eINSTANCE;
	}

} //FolderFactoryImpl
