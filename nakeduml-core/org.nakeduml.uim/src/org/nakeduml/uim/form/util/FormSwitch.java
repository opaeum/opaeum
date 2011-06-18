/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.form.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.nakeduml.uim.UimComponent;
import org.nakeduml.uim.UimContainer;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.UserInteractionElement;

import org.nakeduml.uim.form.*;

import org.nakeduml.uim.layout.LayoutContainer;

import org.nakeduml.uim.security.EditableSecureObject;
import org.nakeduml.uim.security.SecureObject;

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
 * @see org.nakeduml.uim.form.FormPackage
 * @generated
 */
public class FormSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FormPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormSwitch() {
		if (modelPackage == null) {
			modelPackage = FormPackage.eINSTANCE;
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
			case FormPackage.FORM_PANEL: {
				FormPanel formPanel = (FormPanel)theEObject;
				T result = caseFormPanel(formPanel);
				if (result == null) result = caseUmlReference(formPanel);
				if (result == null) result = caseLayoutContainer(formPanel);
				if (result == null) result = caseUimContainer(formPanel);
				if (result == null) result = caseUimComponent(formPanel);
				if (result == null) result = caseEditableSecureObject(formPanel);
				if (result == null) result = caseUserInteractionElement(formPanel);
				if (result == null) result = caseSecureObject(formPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.ACTION_TASK_FORM: {
				ActionTaskForm actionTaskForm = (ActionTaskForm)theEObject;
				T result = caseActionTaskForm(actionTaskForm);
				if (result == null) result = caseFormPanel(actionTaskForm);
				if (result == null) result = caseUmlReference(actionTaskForm);
				if (result == null) result = caseLayoutContainer(actionTaskForm);
				if (result == null) result = caseUimContainer(actionTaskForm);
				if (result == null) result = caseUimComponent(actionTaskForm);
				if (result == null) result = caseEditableSecureObject(actionTaskForm);
				if (result == null) result = caseUserInteractionElement(actionTaskForm);
				if (result == null) result = caseSecureObject(actionTaskForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.STATE_FORM: {
				StateForm stateForm = (StateForm)theEObject;
				T result = caseStateForm(stateForm);
				if (result == null) result = caseFormPanel(stateForm);
				if (result == null) result = caseUmlReference(stateForm);
				if (result == null) result = caseLayoutContainer(stateForm);
				if (result == null) result = caseUimContainer(stateForm);
				if (result == null) result = caseUimComponent(stateForm);
				if (result == null) result = caseEditableSecureObject(stateForm);
				if (result == null) result = caseUserInteractionElement(stateForm);
				if (result == null) result = caseSecureObject(stateForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.CLASS_FORM: {
				ClassForm classForm = (ClassForm)theEObject;
				T result = caseClassForm(classForm);
				if (result == null) result = caseFormPanel(classForm);
				if (result == null) result = caseUmlReference(classForm);
				if (result == null) result = caseLayoutContainer(classForm);
				if (result == null) result = caseUimContainer(classForm);
				if (result == null) result = caseUimComponent(classForm);
				if (result == null) result = caseEditableSecureObject(classForm);
				if (result == null) result = caseUserInteractionElement(classForm);
				if (result == null) result = caseSecureObject(classForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.UIM_FORM: {
				UimForm uimForm = (UimForm)theEObject;
				T result = caseUimForm(uimForm);
				if (result == null) result = caseUserInteractionElement(uimForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.OPERATION_TASK_FORM: {
				OperationTaskForm operationTaskForm = (OperationTaskForm)theEObject;
				T result = caseOperationTaskForm(operationTaskForm);
				if (result == null) result = caseFormPanel(operationTaskForm);
				if (result == null) result = caseUmlReference(operationTaskForm);
				if (result == null) result = caseLayoutContainer(operationTaskForm);
				if (result == null) result = caseUimContainer(operationTaskForm);
				if (result == null) result = caseUimComponent(operationTaskForm);
				if (result == null) result = caseEditableSecureObject(operationTaskForm);
				if (result == null) result = caseUserInteractionElement(operationTaskForm);
				if (result == null) result = caseSecureObject(operationTaskForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.OPERATION_INVOCATION_FORM: {
				OperationInvocationForm operationInvocationForm = (OperationInvocationForm)theEObject;
				T result = caseOperationInvocationForm(operationInvocationForm);
				if (result == null) result = caseFormPanel(operationInvocationForm);
				if (result == null) result = caseUmlReference(operationInvocationForm);
				if (result == null) result = caseLayoutContainer(operationInvocationForm);
				if (result == null) result = caseUimContainer(operationInvocationForm);
				if (result == null) result = caseUimComponent(operationInvocationForm);
				if (result == null) result = caseEditableSecureObject(operationInvocationForm);
				if (result == null) result = caseUserInteractionElement(operationInvocationForm);
				if (result == null) result = caseSecureObject(operationInvocationForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FormPackage.DETAIL_PANEL: {
				DetailPanel detailPanel = (DetailPanel)theEObject;
				T result = caseDetailPanel(detailPanel);
				if (result == null) result = caseUimForm(detailPanel);
				if (result == null) result = caseUserInteractionElement(detailPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormPanel(FormPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Task Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Task Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionTaskForm(ActionTaskForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStateForm(StateForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassForm(ClassForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimForm(UimForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Task Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Task Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationTaskForm(OperationTaskForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Invocation Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Invocation Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationInvocationForm(OperationInvocationForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Detail Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Detail Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDetailPanel(DetailPanel object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimComponent(UimComponent object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimContainer(UimContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLayoutContainer(LayoutContainer object) {
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

} //FormSwitch
