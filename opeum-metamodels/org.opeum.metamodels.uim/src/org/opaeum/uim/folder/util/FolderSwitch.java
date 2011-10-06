/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.folder.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opeum.uim.UmlReference;
import org.opeum.uim.UserInteractionElement;
import org.opeum.uim.folder.AbstractFolder;
import org.opeum.uim.folder.AbstractFormFolder;
import org.opeum.uim.folder.ActivityFolder;
import org.opeum.uim.folder.EntityFolder;
import org.opeum.uim.folder.FolderPackage;
import org.opeum.uim.folder.OperationContainingFolder;
import org.opeum.uim.folder.PackageFolder;
import org.opeum.uim.folder.StateMachineFolder;
import org.opeum.uim.folder.UserInteractionModel;
import org.opeum.uim.folder.UserInteractionWorkspace;
import org.opeum.uim.security.EditableSecureObject;
import org.opeum.uim.security.SecureObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.opeum.uim.folder.FolderPackage
 * @generated
 */
public class FolderSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FolderPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FolderSwitch() {
		if (modelPackage == null) {
			modelPackage = FolderPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case FolderPackage.ABSTRACT_FOLDER: {
				AbstractFolder abstractFolder = (AbstractFolder)theEObject;
				T result = caseAbstractFolder(abstractFolder);
				if (result == null) result = caseUserInteractionElement(abstractFolder);
				if (result == null) result = caseUmlReference(abstractFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FolderPackage.ACTIVITY_FOLDER: {
				ActivityFolder activityFolder = (ActivityFolder)theEObject;
				T result = caseActivityFolder(activityFolder);
				if (result == null) result = caseAbstractFormFolder(activityFolder);
				if (result == null) result = caseAbstractFolder(activityFolder);
				if (result == null) result = caseEditableSecureObject(activityFolder);
				if (result == null) result = caseUserInteractionElement(activityFolder);
				if (result == null) result = caseUmlReference(activityFolder);
				if (result == null) result = caseSecureObject(activityFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FolderPackage.ENTITY_FOLDER: {
				EntityFolder entityFolder = (EntityFolder)theEObject;
				T result = caseEntityFolder(entityFolder);
				if (result == null) result = caseOperationContainingFolder(entityFolder);
				if (result == null) result = caseAbstractFormFolder(entityFolder);
				if (result == null) result = caseAbstractFolder(entityFolder);
				if (result == null) result = caseEditableSecureObject(entityFolder);
				if (result == null) result = caseUserInteractionElement(entityFolder);
				if (result == null) result = caseUmlReference(entityFolder);
				if (result == null) result = caseSecureObject(entityFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FolderPackage.STATE_MACHINE_FOLDER: {
				StateMachineFolder stateMachineFolder = (StateMachineFolder)theEObject;
				T result = caseStateMachineFolder(stateMachineFolder);
				if (result == null) result = caseOperationContainingFolder(stateMachineFolder);
				if (result == null) result = caseAbstractFormFolder(stateMachineFolder);
				if (result == null) result = caseAbstractFolder(stateMachineFolder);
				if (result == null) result = caseEditableSecureObject(stateMachineFolder);
				if (result == null) result = caseUserInteractionElement(stateMachineFolder);
				if (result == null) result = caseUmlReference(stateMachineFolder);
				if (result == null) result = caseSecureObject(stateMachineFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FolderPackage.ABSTRACT_FORM_FOLDER: {
				AbstractFormFolder abstractFormFolder = (AbstractFormFolder)theEObject;
				T result = caseAbstractFormFolder(abstractFormFolder);
				if (result == null) result = caseAbstractFolder(abstractFormFolder);
				if (result == null) result = caseEditableSecureObject(abstractFormFolder);
				if (result == null) result = caseUserInteractionElement(abstractFormFolder);
				if (result == null) result = caseUmlReference(abstractFormFolder);
				if (result == null) result = caseSecureObject(abstractFormFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FolderPackage.PACKAGE_FOLDER: {
				PackageFolder packageFolder = (PackageFolder)theEObject;
				T result = casePackageFolder(packageFolder);
				if (result == null) result = caseAbstractFormFolder(packageFolder);
				if (result == null) result = caseAbstractFolder(packageFolder);
				if (result == null) result = caseEditableSecureObject(packageFolder);
				if (result == null) result = caseUserInteractionElement(packageFolder);
				if (result == null) result = caseUmlReference(packageFolder);
				if (result == null) result = caseSecureObject(packageFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FolderPackage.OPERATION_CONTAINING_FOLDER: {
				OperationContainingFolder operationContainingFolder = (OperationContainingFolder)theEObject;
				T result = caseOperationContainingFolder(operationContainingFolder);
				if (result == null) result = caseAbstractFormFolder(operationContainingFolder);
				if (result == null) result = caseAbstractFolder(operationContainingFolder);
				if (result == null) result = caseEditableSecureObject(operationContainingFolder);
				if (result == null) result = caseUserInteractionElement(operationContainingFolder);
				if (result == null) result = caseUmlReference(operationContainingFolder);
				if (result == null) result = caseSecureObject(operationContainingFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FolderPackage.USER_INTERACTION_MODEL: {
				UserInteractionModel userInteractionModel = (UserInteractionModel)theEObject;
				T result = caseUserInteractionModel(userInteractionModel);
				if (result == null) result = caseAbstractFormFolder(userInteractionModel);
				if (result == null) result = caseAbstractFolder(userInteractionModel);
				if (result == null) result = caseEditableSecureObject(userInteractionModel);
				if (result == null) result = caseUmlReference(userInteractionModel);
				if (result == null) result = caseUserInteractionElement(userInteractionModel);
				if (result == null) result = caseSecureObject(userInteractionModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FolderPackage.USER_INTERACTION_WORKSPACE: {
				UserInteractionWorkspace userInteractionWorkspace = (UserInteractionWorkspace)theEObject;
				T result = caseUserInteractionWorkspace(userInteractionWorkspace);
				if (result == null) result = caseAbstractFolder(userInteractionWorkspace);
				if (result == null) result = caseUserInteractionElement(userInteractionWorkspace);
				if (result == null) result = caseUmlReference(userInteractionWorkspace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractFolder(AbstractFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityFolder(ActivityFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntityFolder(EntityFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Machine Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Machine Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStateMachineFolder(StateMachineFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Form Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Form Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractFormFolder(AbstractFormFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageFolder(PackageFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Containing Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Containing Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationContainingFolder(OperationContainingFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interaction Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInteractionModel(UserInteractionModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interaction Workspace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interaction Workspace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInteractionWorkspace(UserInteractionWorkspace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interaction Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interaction Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInteractionElement(UserInteractionElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uml Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uml Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUmlReference(UmlReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Secure Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secure Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecureObject(SecureObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Editable Secure Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editable Secure Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditableSecureObject(EditableSecureObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //FolderSwitch
