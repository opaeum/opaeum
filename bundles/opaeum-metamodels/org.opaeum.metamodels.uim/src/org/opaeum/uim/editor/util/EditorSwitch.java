/**
 */
package org.opaeum.uim.editor.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.Page;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.editor.OperationMenuItem;
import org.opaeum.uim.editor.QueryResultPage;
import org.opaeum.uim.editor.ResponsibilityViewer;
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
				if (result == null) result = caseUserInterfaceRoot(abstractEditor);
				if (result == null) result = caseLabeledElement(abstractEditor);
				if (result == null) result = caseUserInteractionElement(abstractEditor);
				if (result == null) result = caseUmlReference(abstractEditor);
				if (result == null) result = caseLabelContainer(abstractEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.INSTANCE_EDITOR: {
				InstanceEditor instanceEditor = (InstanceEditor)theEObject;
				T result = caseInstanceEditor(instanceEditor);
				if (result == null) result = caseAbstractEditor(instanceEditor);
				if (result == null) result = caseUserInterfaceRoot(instanceEditor);
				if (result == null) result = caseLabeledElement(instanceEditor);
				if (result == null) result = caseUserInteractionElement(instanceEditor);
				if (result == null) result = caseUmlReference(instanceEditor);
				if (result == null) result = caseLabelContainer(instanceEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.BEHAVIOR_EXECUTION_EDITOR: {
				BehaviorExecutionEditor behaviorExecutionEditor = (BehaviorExecutionEditor)theEObject;
				T result = caseBehaviorExecutionEditor(behaviorExecutionEditor);
				if (result == null) result = caseInstanceEditor(behaviorExecutionEditor);
				if (result == null) result = caseAbstractEditor(behaviorExecutionEditor);
				if (result == null) result = caseUserInterfaceRoot(behaviorExecutionEditor);
				if (result == null) result = caseLabeledElement(behaviorExecutionEditor);
				if (result == null) result = caseUserInteractionElement(behaviorExecutionEditor);
				if (result == null) result = caseUmlReference(behaviorExecutionEditor);
				if (result == null) result = caseLabelContainer(behaviorExecutionEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.EDITOR_PAGE: {
				EditorPage editorPage = (EditorPage)theEObject;
				T result = caseEditorPage(editorPage);
				if (result == null) result = casePage(editorPage);
				if (result == null) result = caseEditableConstrainedObject(editorPage);
				if (result == null) result = caseLabeledElement(editorPage);
				if (result == null) result = caseConstrainedObject(editorPage);
				if (result == null) result = caseUserInteractionElement(editorPage);
				if (result == null) result = caseUmlReference(editorPage);
				if (result == null) result = caseLabelContainer(editorPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.ACTION_BAR: {
				ActionBar actionBar = (ActionBar)theEObject;
				T result = caseActionBar(actionBar);
				if (result == null) result = caseAbstractPanel(actionBar);
				if (result == null) result = caseUimContainer(actionBar);
				if (result == null) result = caseLabeledElement(actionBar);
				if (result == null) result = caseUimComponent(actionBar);
				if (result == null) result = caseEditableConstrainedObject(actionBar);
				if (result == null) result = caseUmlReference(actionBar);
				if (result == null) result = caseUserInteractionElement(actionBar);
				if (result == null) result = caseConstrainedObject(actionBar);
				if (result == null) result = caseLabelContainer(actionBar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.MENU_CONFIGURATION: {
				MenuConfiguration menuConfiguration = (MenuConfiguration)theEObject;
				T result = caseMenuConfiguration(menuConfiguration);
				if (result == null) result = caseUserInteractionElement(menuConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.OPERATION_MENU_ITEM: {
				OperationMenuItem operationMenuItem = (OperationMenuItem)theEObject;
				T result = caseOperationMenuItem(operationMenuItem);
				if (result == null) result = caseUserInteractionConstraint(operationMenuItem);
				if (result == null) result = caseLabeledElement(operationMenuItem);
				if (result == null) result = caseRootUserInteractionConstraint(operationMenuItem);
				if (result == null) result = caseUserInteractionElement(operationMenuItem);
				if (result == null) result = caseUmlReference(operationMenuItem);
				if (result == null) result = caseLabelContainer(operationMenuItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.RESPONSIBILITY_VIEWER: {
				ResponsibilityViewer responsibilityViewer = (ResponsibilityViewer)theEObject;
				T result = caseResponsibilityViewer(responsibilityViewer);
				if (result == null) result = caseAbstractEditor(responsibilityViewer);
				if (result == null) result = caseUserInterfaceRoot(responsibilityViewer);
				if (result == null) result = caseLabeledElement(responsibilityViewer);
				if (result == null) result = caseUserInteractionElement(responsibilityViewer);
				if (result == null) result = caseUmlReference(responsibilityViewer);
				if (result == null) result = caseLabelContainer(responsibilityViewer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.QUERY_RESULT_PAGE: {
				QueryResultPage queryResultPage = (QueryResultPage)theEObject;
				T result = caseQueryResultPage(queryResultPage);
				if (result == null) result = casePage(queryResultPage);
				if (result == null) result = caseEditableConstrainedObject(queryResultPage);
				if (result == null) result = caseLabeledElement(queryResultPage);
				if (result == null) result = caseConstrainedObject(queryResultPage);
				if (result == null) result = caseUserInteractionElement(queryResultPage);
				if (result == null) result = caseUmlReference(queryResultPage);
				if (result == null) result = caseLabelContainer(queryResultPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EditorPackage.OBJECT_EDITOR: {
				ObjectEditor objectEditor = (ObjectEditor)theEObject;
				T result = caseObjectEditor(objectEditor);
				if (result == null) result = caseInstanceEditor(objectEditor);
				if (result == null) result = caseAbstractEditor(objectEditor);
				if (result == null) result = caseUserInterfaceRoot(objectEditor);
				if (result == null) result = caseLabeledElement(objectEditor);
				if (result == null) result = caseUserInteractionElement(objectEditor);
				if (result == null) result = caseUmlReference(objectEditor);
				if (result == null) result = caseLabelContainer(objectEditor);
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
	 * Returns the result of interpreting the object as an instance of '<em>Instance Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instance Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanceEditor(InstanceEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behavior Execution Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behavior Execution Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehaviorExecutionEditor(BehaviorExecutionEditor object) {
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
	public T caseActionBar(ActionBar object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Operation Menu Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Menu Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationMenuItem(OperationMenuItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Responsibility Viewer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Responsibility Viewer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResponsibilityViewer(ResponsibilityViewer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query Result Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query Result Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQueryResultPage(QueryResultPage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectEditor(ObjectEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Labeled Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Labeled Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabeledElement(LabeledElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interface Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interface Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInterfaceRoot(UserInterfaceRoot object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Label Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabelContainer(LabelContainer object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Uim Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Component</em>'.
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
	 * Returns the result of interpreting the object as an instance of '<em>Uim Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Container</em>'.
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
	 * Returns the result of interpreting the object as an instance of '<em>Root User Interaction Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Root User Interaction Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRootUserInteractionConstraint(RootUserInteractionConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interaction Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interaction Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInteractionConstraint(UserInteractionConstraint object) {
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
