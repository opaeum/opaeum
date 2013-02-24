/**
 */
package org.opaeum.uim.perspective.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.perspective.*;
import org.opaeum.uim.perspective.BehaviorNavigationConstraint;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.NavigatorConfiguration;
import org.opaeum.uim.perspective.OperationNavigationConstraint;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.ParameterNavigationConstraint;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.perspective.PropertyNavigationConstraint;
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
			case PerspectivePackage.NAVIGATOR_CONFIGURATION: {
				NavigatorConfiguration navigatorConfiguration = (NavigatorConfiguration)theEObject;
				T result = caseNavigatorConfiguration(navigatorConfiguration);
				if (result == null) result = caseViewAllocation(navigatorConfiguration);
				if (result == null) result = caseUserInteractionElement(navigatorConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.CLASS_NAVIGATION_CONSTRAINT: {
				ClassNavigationConstraint classNavigationConstraint = (ClassNavigationConstraint)theEObject;
				T result = caseClassNavigationConstraint(classNavigationConstraint);
				if (result == null) result = caseNavigationConstraint(classNavigationConstraint);
				if (result == null) result = caseUserInteractionConstraint(classNavigationConstraint);
				if (result == null) result = caseLabeledElement(classNavigationConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(classNavigationConstraint);
				if (result == null) result = caseUserInteractionElement(classNavigationConstraint);
				if (result == null) result = caseUmlReference(classNavigationConstraint);
				if (result == null) result = caseLabelContainer(classNavigationConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.PROPERTY_NAVIGATION_CONSTRAINT: {
				PropertyNavigationConstraint propertyNavigationConstraint = (PropertyNavigationConstraint)theEObject;
				T result = casePropertyNavigationConstraint(propertyNavigationConstraint);
				if (result == null) result = caseMultiplicityElementNavigationConstraint(propertyNavigationConstraint);
				if (result == null) result = caseNavigationConstraint(propertyNavigationConstraint);
				if (result == null) result = caseUserInteractionConstraint(propertyNavigationConstraint);
				if (result == null) result = caseLabeledElement(propertyNavigationConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(propertyNavigationConstraint);
				if (result == null) result = caseUserInteractionElement(propertyNavigationConstraint);
				if (result == null) result = caseUmlReference(propertyNavigationConstraint);
				if (result == null) result = caseLabelContainer(propertyNavigationConstraint);
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
			case PerspectivePackage.NAVIGATION_CONSTRAINT: {
				NavigationConstraint navigationConstraint = (NavigationConstraint)theEObject;
				T result = caseNavigationConstraint(navigationConstraint);
				if (result == null) result = caseUserInteractionConstraint(navigationConstraint);
				if (result == null) result = caseLabeledElement(navigationConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(navigationConstraint);
				if (result == null) result = caseUserInteractionElement(navigationConstraint);
				if (result == null) result = caseUmlReference(navigationConstraint);
				if (result == null) result = caseLabelContainer(navigationConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.OPERATION_NAVIGATION_CONSTRAINT: {
				OperationNavigationConstraint operationNavigationConstraint = (OperationNavigationConstraint)theEObject;
				T result = caseOperationNavigationConstraint(operationNavigationConstraint);
				if (result == null) result = caseNavigationConstraint(operationNavigationConstraint);
				if (result == null) result = caseUserInteractionConstraint(operationNavigationConstraint);
				if (result == null) result = caseLabeledElement(operationNavigationConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(operationNavigationConstraint);
				if (result == null) result = caseUserInteractionElement(operationNavigationConstraint);
				if (result == null) result = caseUmlReference(operationNavigationConstraint);
				if (result == null) result = caseLabelContainer(operationNavigationConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.BEHAVIOR_NAVIGATION_CONSTRAINT: {
				BehaviorNavigationConstraint behaviorNavigationConstraint = (BehaviorNavigationConstraint)theEObject;
				T result = caseBehaviorNavigationConstraint(behaviorNavigationConstraint);
				if (result == null) result = caseNavigationConstraint(behaviorNavigationConstraint);
				if (result == null) result = caseUserInteractionConstraint(behaviorNavigationConstraint);
				if (result == null) result = caseLabeledElement(behaviorNavigationConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(behaviorNavigationConstraint);
				if (result == null) result = caseUserInteractionElement(behaviorNavigationConstraint);
				if (result == null) result = caseUmlReference(behaviorNavigationConstraint);
				if (result == null) result = caseLabelContainer(behaviorNavigationConstraint);
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
			case PerspectivePackage.PARAMETER_NAVIGATION_CONSTRAINT: {
				ParameterNavigationConstraint parameterNavigationConstraint = (ParameterNavigationConstraint)theEObject;
				T result = caseParameterNavigationConstraint(parameterNavigationConstraint);
				if (result == null) result = caseMultiplicityElementNavigationConstraint(parameterNavigationConstraint);
				if (result == null) result = caseNavigationConstraint(parameterNavigationConstraint);
				if (result == null) result = caseUserInteractionConstraint(parameterNavigationConstraint);
				if (result == null) result = caseLabeledElement(parameterNavigationConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(parameterNavigationConstraint);
				if (result == null) result = caseUserInteractionElement(parameterNavigationConstraint);
				if (result == null) result = caseUmlReference(parameterNavigationConstraint);
				if (result == null) result = caseLabelContainer(parameterNavigationConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PerspectivePackage.MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT: {
				MultiplicityElementNavigationConstraint multiplicityElementNavigationConstraint = (MultiplicityElementNavigationConstraint)theEObject;
				T result = caseMultiplicityElementNavigationConstraint(multiplicityElementNavigationConstraint);
				if (result == null) result = caseNavigationConstraint(multiplicityElementNavigationConstraint);
				if (result == null) result = caseUserInteractionConstraint(multiplicityElementNavigationConstraint);
				if (result == null) result = caseLabeledElement(multiplicityElementNavigationConstraint);
				if (result == null) result = caseRootUserInteractionConstraint(multiplicityElementNavigationConstraint);
				if (result == null) result = caseUserInteractionElement(multiplicityElementNavigationConstraint);
				if (result == null) result = caseUmlReference(multiplicityElementNavigationConstraint);
				if (result == null) result = caseLabelContainer(multiplicityElementNavigationConstraint);
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
	 * Returns the result of interpreting the object as an instance of '<em>Navigator Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigator Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigatorConfiguration(NavigatorConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Navigation Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassNavigationConstraint(ClassNavigationConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Navigation Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyNavigationConstraint(PropertyNavigationConstraint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationConstraint(NavigationConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Navigation Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationNavigationConstraint(OperationNavigationConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behavior Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behavior Navigation Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehaviorNavigationConstraint(BehaviorNavigationConstraint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Navigation Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterNavigationConstraint(ParameterNavigationConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multiplicity Element Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multiplicity Element Navigation Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiplicityElementNavigationConstraint(MultiplicityElementNavigationConstraint object) {
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
