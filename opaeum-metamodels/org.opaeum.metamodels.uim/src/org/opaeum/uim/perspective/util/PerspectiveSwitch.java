/**
 */
package org.opaeum.uim.perspective.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.perspective.*;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.ExplorerBehaviorConstraint;
import org.opaeum.uim.perspective.ExplorerClassConstraint;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.ExplorerConstraint;
import org.opaeum.uim.perspective.ExplorerOperationConstraint;
import org.opaeum.uim.perspective.ExplorerPropertyConstraint;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.perspective.ViewAllocation;

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
 * @see org.opaeum.uim.perspective.PerspectivePackage
 * @generated
 */
public class PerspectiveSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PerspectivePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerspectiveSwitch() {
		if (modelPackage == null) {
			modelPackage = PerspectivePackage.eINSTANCE;
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
			case PerspectivePackage.PERSPECTIVE_CONFIGURATION: {
				PerspectiveConfiguration perspectiveConfiguration = (PerspectiveConfiguration)theEObject;
				T result = casePerspectiveConfiguration(perspectiveConfiguration);
				if (result == null) result = caseUserInteractionElement(perspectiveConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.VIEW_ALLOCATION: {
				ViewAllocation viewAllocation = (ViewAllocation)theEObject;
				T result = caseViewAllocation(viewAllocation);
				if (result == null) result = caseUserInteractionElement(viewAllocation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.EXPLORER_CONFIGURATION: {
				ExplorerConfiguration explorerConfiguration = (ExplorerConfiguration)theEObject;
				T result = caseExplorerConfiguration(explorerConfiguration);
				if (result == null) result = caseViewAllocation(explorerConfiguration);
				if (result == null) result = caseUserInteractionElement(explorerConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.EXPLORER_CLASS_CONSTRAINT: {
				ExplorerClassConstraint explorerClassConstraint = (ExplorerClassConstraint)theEObject;
				T result = caseExplorerClassConstraint(explorerClassConstraint);
				if (result == null) result = caseExplorerConstraint(explorerClassConstraint);
				if (result == null) result = caseUserInteractionConstraint(explorerClassConstraint);
				if (result == null) result = caseLabeledElement(explorerClassConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(explorerClassConstraint);
				if (result == null) result = caseUmlReference(explorerClassConstraint);
				if (result == null) result = caseUserInteractionElement(explorerClassConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.EXPLORER_PROPERTY_CONSTRAINT: {
				ExplorerPropertyConstraint explorerPropertyConstraint = (ExplorerPropertyConstraint)theEObject;
				T result = caseExplorerPropertyConstraint(explorerPropertyConstraint);
				if (result == null) result = caseExplorerConstraint(explorerPropertyConstraint);
				if (result == null) result = caseUserInteractionConstraint(explorerPropertyConstraint);
				if (result == null) result = caseLabeledElement(explorerPropertyConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(explorerPropertyConstraint);
				if (result == null) result = caseUmlReference(explorerPropertyConstraint);
				if (result == null) result = caseUserInteractionElement(explorerPropertyConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.EDITOR_CONFIGURATION: {
				EditorConfiguration editorConfiguration = (EditorConfiguration)theEObject;
				T result = caseEditorConfiguration(editorConfiguration);
				if (result == null) result = caseViewAllocation(editorConfiguration);
				if (result == null) result = caseUserInteractionElement(editorConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.PROPERTIES_CONFIGURATION: {
				PropertiesConfiguration propertiesConfiguration = (PropertiesConfiguration)theEObject;
				T result = casePropertiesConfiguration(propertiesConfiguration);
				if (result == null) result = caseViewAllocation(propertiesConfiguration);
				if (result == null) result = caseUserInteractionElement(propertiesConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.EXPLORER_CONSTRAINT: {
				ExplorerConstraint explorerConstraint = (ExplorerConstraint)theEObject;
				T result = caseExplorerConstraint(explorerConstraint);
				if (result == null) result = caseUserInteractionConstraint(explorerConstraint);
				if (result == null) result = caseLabeledElement(explorerConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(explorerConstraint);
				if (result == null) result = caseUmlReference(explorerConstraint);
				if (result == null) result = caseUserInteractionElement(explorerConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.EXPLORER_OPERATION_CONSTRAINT: {
				ExplorerOperationConstraint explorerOperationConstraint = (ExplorerOperationConstraint)theEObject;
				T result = caseExplorerOperationConstraint(explorerOperationConstraint);
				if (result == null) result = caseExplorerConstraint(explorerOperationConstraint);
				if (result == null) result = caseUserInteractionConstraint(explorerOperationConstraint);
				if (result == null) result = caseLabeledElement(explorerOperationConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(explorerOperationConstraint);
				if (result == null) result = caseUmlReference(explorerOperationConstraint);
				if (result == null) result = caseUserInteractionElement(explorerOperationConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.EXPLORER_BEHAVIOR_CONSTRAINT: {
				ExplorerBehaviorConstraint explorerBehaviorConstraint = (ExplorerBehaviorConstraint)theEObject;
				T result = caseExplorerBehaviorConstraint(explorerBehaviorConstraint);
				if (result == null) result = caseExplorerConstraint(explorerBehaviorConstraint);
				if (result == null) result = caseUserInteractionConstraint(explorerBehaviorConstraint);
				if (result == null) result = caseLabeledElement(explorerBehaviorConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(explorerBehaviorConstraint);
				if (result == null) result = caseUmlReference(explorerBehaviorConstraint);
				if (result == null) result = caseUserInteractionElement(explorerBehaviorConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.INBOX_CONFIGURATION: {
				InboxConfiguration inboxConfiguration = (InboxConfiguration)theEObject;
				T result = caseInboxConfiguration(inboxConfiguration);
				if (result == null) result = caseViewAllocation(inboxConfiguration);
				if (result == null) result = caseUserInteractionElement(inboxConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.OUTBOX_CONFIGURATION: {
				OutboxConfiguration outboxConfiguration = (OutboxConfiguration)theEObject;
				T result = caseOutboxConfiguration(outboxConfiguration);
				if (result == null) result = caseViewAllocation(outboxConfiguration);
				if (result == null) result = caseUserInteractionElement(outboxConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePerspectiveConfiguration(PerspectiveConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>View Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>View Allocation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViewAllocation(ViewAllocation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Explorer Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Explorer Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExplorerConfiguration(ExplorerConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Explorer Class Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Explorer Class Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExplorerClassConstraint(ExplorerClassConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Explorer Property Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Explorer Property Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExplorerPropertyConstraint(ExplorerPropertyConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Editor Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editor Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditorConfiguration(EditorConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertiesConfiguration(PropertiesConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Explorer Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Explorer Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExplorerConstraint(ExplorerConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Explorer Operation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Explorer Operation Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExplorerOperationConstraint(ExplorerOperationConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Explorer Behavior Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Explorer Behavior Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExplorerBehaviorConstraint(ExplorerBehaviorConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Inbox Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Inbox Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInboxConfiguration(InboxConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outbox Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outbox Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutboxConfiguration(OutboxConfiguration object) {
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

} //PerspectiveSwitch
