/**
 */
package org.opaeum.uim.editor.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.AbstractActionBar;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimRootElement;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.editor.*;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionTaskEditor;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.editor.EditorActionBar;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.editor.QueryInvocationEditor;
import org.opaeum.uim.editor.ResponsibilityTaskEditor;
import org.opaeum.uim.editor.VisibleOperation;
import org.opaeum.uim.panel.AbstractPanel;

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
 * @see org.opaeum.uim.editor.EditorPackage
 * @generated
 */
public class EditorSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EditorPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditorSwitch() {
		if (modelPackage == null) {
			modelPackage = EditorPackage.eINSTANCE;
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
			case EditorPackage.ABSTRACT_EDITOR: {
				AbstractEditor abstractEditor = (AbstractEditor)theEObject;
				T result = caseAbstractEditor(abstractEditor);
				if (result == null) result = caseUmlReference(abstractEditor);
				if (result == null) result = caseUserInterfaceEntryPoint(abstractEditor);
				if (result == null) result = caseUserInteractionElement(abstractEditor);
				if (result == null) result = casePageContainer(abstractEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.ACTION_TASK_EDITOR: {
				ActionTaskEditor actionTaskEditor = (ActionTaskEditor)theEObject;
				T result = caseActionTaskEditor(actionTaskEditor);
				if (result == null) result = caseAbstractEditor(actionTaskEditor);
				if (result == null) result = caseUimRootElement(actionTaskEditor);
				if (result == null) result = caseUmlReference(actionTaskEditor);
				if (result == null) result = caseUserInterfaceEntryPoint(actionTaskEditor);
				if (result == null) result = caseUserInteractionElement(actionTaskEditor);
				if (result == null) result = casePageContainer(actionTaskEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.CLASS_EDITOR: {
				ClassEditor classEditor = (ClassEditor)theEObject;
				T result = caseClassEditor(classEditor);
				if (result == null) result = caseAbstractEditor(classEditor);
				if (result == null) result = caseUmlReference(classEditor);
				if (result == null) result = caseUserInterfaceEntryPoint(classEditor);
				if (result == null) result = caseUserInteractionElement(classEditor);
				if (result == null) result = casePageContainer(classEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.RESPONSIBILITY_TASK_EDITOR: {
				ResponsibilityTaskEditor responsibilityTaskEditor = (ResponsibilityTaskEditor)theEObject;
				T result = caseResponsibilityTaskEditor(responsibilityTaskEditor);
				if (result == null) result = caseAbstractEditor(responsibilityTaskEditor);
				if (result == null) result = caseUmlReference(responsibilityTaskEditor);
				if (result == null) result = caseUserInterfaceEntryPoint(responsibilityTaskEditor);
				if (result == null) result = caseUserInteractionElement(responsibilityTaskEditor);
				if (result == null) result = casePageContainer(responsibilityTaskEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.QUERY_INVOCATION_EDITOR: {
				QueryInvocationEditor queryInvocationEditor = (QueryInvocationEditor)theEObject;
				T result = caseQueryInvocationEditor(queryInvocationEditor);
				if (result == null) result = caseAbstractEditor(queryInvocationEditor);
				if (result == null) result = caseUimRootElement(queryInvocationEditor);
				if (result == null) result = caseUmlReference(queryInvocationEditor);
				if (result == null) result = caseUserInterfaceEntryPoint(queryInvocationEditor);
				if (result == null) result = caseUserInteractionElement(queryInvocationEditor);
				if (result == null) result = casePageContainer(queryInvocationEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.EDITOR_PAGE: {
				EditorPage editorPage = (EditorPage)theEObject;
				T result = caseEditorPage(editorPage);
				if (result == null) result = casePage(editorPage);
				if (result == null) result = caseUserInterface(editorPage);
				if (result == null) result = caseEditableConstrainedObject(editorPage);
				if (result == null) result = caseUserInteractionElement(editorPage);
				if (result == null) result = caseUmlReference(editorPage);
				if (result == null) result = caseConstrainedObject(editorPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.EDITOR_ACTION_BAR: {
				EditorActionBar editorActionBar = (EditorActionBar)theEObject;
				T result = caseEditorActionBar(editorActionBar);
				if (result == null) result = caseAbstractActionBar(editorActionBar);
				if (result == null) result = caseAbstractPanel(editorActionBar);
				if (result == null) result = caseUimContainer(editorActionBar);
				if (result == null) result = caseUimComponent(editorActionBar);
				if (result == null) result = caseEditableConstrainedObject(editorActionBar);
				if (result == null) result = caseUserInteractionElement(editorActionBar);
				if (result == null) result = caseConstrainedObject(editorActionBar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.MENU_CONFIGURATION: {
				MenuConfiguration menuConfiguration = (MenuConfiguration)theEObject;
				T result = caseMenuConfiguration(menuConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.VISIBLE_OPERATION: {
				VisibleOperation visibleOperation = (VisibleOperation)theEObject;
				T result = caseVisibleOperation(visibleOperation);
				if (result == null) result = caseUmlReference(visibleOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEditor(AbstractEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Task Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Task Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionTaskEditor(ActionTaskEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassEditor(ClassEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Responsibility Task Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Responsibility Task Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResponsibilityTaskEditor(ResponsibilityTaskEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query Invocation Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query Invocation Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQueryInvocationEditor(QueryInvocationEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditorPage(EditorPage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Bar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Bar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditorActionBar(EditorActionBar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Menu Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Menu Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMenuConfiguration(MenuConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Visible Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Visible Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVisibleOperation(VisibleOperation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>User Interface Entry Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interface Entry Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInterfaceEntryPoint(UserInterfaceEntryPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Root Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Root Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimRootElement(UimRootElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInterface(UserInterface object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePage(Page object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Page Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePageContainer(PageContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constrained Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constrained Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstrainedObject(ConstrainedObject object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Editable Constrained Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editable Constrained Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditableConstrainedObject(EditableConstrainedObject object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractPanel(AbstractPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Action Bar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Action Bar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractActionBar(AbstractActionBar object) {
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

} //EditorSwitch
