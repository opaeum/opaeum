/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.folder.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.UserInteractionElement;

import org.nakeduml.uim.folder.*;

import org.nakeduml.uim.security.EditableSecureObject;
import org.nakeduml.uim.security.SecureObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.nakeduml.uim.folder.FolderPackage
 * @generated
 */
public class FolderAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FolderPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FolderAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = FolderPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FolderSwitch<Adapter> modelSwitch =
		new FolderSwitch<Adapter>() {
			@Override
			public Adapter caseAbstractFolder(AbstractFolder object) {
				return createAbstractFolderAdapter();
			}
			@Override
			public Adapter caseActivityFolder(ActivityFolder object) {
				return createActivityFolderAdapter();
			}
			@Override
			public Adapter caseEntityFolder(EntityFolder object) {
				return createEntityFolderAdapter();
			}
			@Override
			public Adapter caseStateMachineFolder(StateMachineFolder object) {
				return createStateMachineFolderAdapter();
			}
			@Override
			public Adapter caseAbstractFormFolder(AbstractFormFolder object) {
				return createAbstractFormFolderAdapter();
			}
			@Override
			public Adapter casePackageFolder(PackageFolder object) {
				return createPackageFolderAdapter();
			}
			@Override
			public Adapter caseOperationContainingFolder(OperationContainingFolder object) {
				return createOperationContainingFolderAdapter();
			}
			@Override
			public Adapter caseUserInteractionModel(UserInteractionModel object) {
				return createUserInteractionModelAdapter();
			}
			@Override
			public Adapter caseUserInteractionWorkspace(UserInteractionWorkspace object) {
				return createUserInteractionWorkspaceAdapter();
			}
			@Override
			public Adapter caseUserInteractionElement(UserInteractionElement object) {
				return createUserInteractionElementAdapter();
			}
			@Override
			public Adapter caseUmlReference(UmlReference object) {
				return createUmlReferenceAdapter();
			}
			@Override
			public Adapter caseSecureObject(SecureObject object) {
				return createSecureObjectAdapter();
			}
			@Override
			public Adapter caseEditableSecureObject(EditableSecureObject object) {
				return createEditableSecureObjectAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.AbstractFolder <em>Abstract Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.AbstractFolder
	 * @generated
	 */
	public Adapter createAbstractFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.ActivityFolder <em>Activity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.ActivityFolder
	 * @generated
	 */
	public Adapter createActivityFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.EntityFolder <em>Entity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.EntityFolder
	 * @generated
	 */
	public Adapter createEntityFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.StateMachineFolder <em>State Machine Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.StateMachineFolder
	 * @generated
	 */
	public Adapter createStateMachineFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.AbstractFormFolder <em>Abstract Form Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.AbstractFormFolder
	 * @generated
	 */
	public Adapter createAbstractFormFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.PackageFolder <em>Package Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.PackageFolder
	 * @generated
	 */
	public Adapter createPackageFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.OperationContainingFolder <em>Operation Containing Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.OperationContainingFolder
	 * @generated
	 */
	public Adapter createOperationContainingFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.UserInteractionModel <em>User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.UserInteractionModel
	 * @generated
	 */
	public Adapter createUserInteractionModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.folder.UserInteractionWorkspace <em>User Interaction Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.folder.UserInteractionWorkspace
	 * @generated
	 */
	public Adapter createUserInteractionWorkspaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UserInteractionElement <em>User Interaction Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UserInteractionElement
	 * @generated
	 */
	public Adapter createUserInteractionElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UmlReference <em>Uml Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UmlReference
	 * @generated
	 */
	public Adapter createUmlReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.security.SecureObject <em>Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.security.SecureObject
	 * @generated
	 */
	public Adapter createSecureObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.security.EditableSecureObject <em>Editable Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.security.EditableSecureObject
	 * @generated
	 */
	public Adapter createEditableSecureObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //FolderAdapterFactory
