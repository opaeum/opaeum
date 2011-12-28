/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.layout.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.layout.*;
import org.opaeum.uim.layout.LayoutContainer;
import org.opaeum.uim.layout.LayoutPackage;
import org.opaeum.uim.layout.OutlayableComponent;
import org.opaeum.uim.layout.UimBorderLayout;
import org.opaeum.uim.layout.UimColumnLayout;
import org.opaeum.uim.layout.UimFullLayout;
import org.opaeum.uim.layout.UimGridLayout;
import org.opaeum.uim.layout.UimLayout;
import org.opaeum.uim.layout.UimToolbarLayout;
import org.opaeum.uim.layout.UimXYLayout;
import org.opaeum.uim.security.EditableSecureObject;
import org.opaeum.uim.security.SecureObject;

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
 * @see org.opaeum.uim.layout.LayoutPackage
 * @generated
 */
public class LayoutSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LayoutPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayoutSwitch() {
		if (modelPackage == null) {
			modelPackage = LayoutPackage.eINSTANCE;
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
			case LayoutPackage.UIM_COLUMN_LAYOUT: {
				UimColumnLayout uimColumnLayout = (UimColumnLayout)theEObject;
				T result = caseUimColumnLayout(uimColumnLayout);
				if (result == null) result = caseUimLayout(uimColumnLayout);
				if (result == null) result = caseUimContainer(uimColumnLayout);
				if (result == null) result = caseUimComponent(uimColumnLayout);
				if (result == null) result = caseEditableSecureObject(uimColumnLayout);
				if (result == null) result = caseUserInteractionElement(uimColumnLayout);
				if (result == null) result = caseSecureObject(uimColumnLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LayoutPackage.UIM_FULL_LAYOUT: {
				UimFullLayout uimFullLayout = (UimFullLayout)theEObject;
				T result = caseUimFullLayout(uimFullLayout);
				if (result == null) result = caseUimLayout(uimFullLayout);
				if (result == null) result = caseUimContainer(uimFullLayout);
				if (result == null) result = caseUimComponent(uimFullLayout);
				if (result == null) result = caseEditableSecureObject(uimFullLayout);
				if (result == null) result = caseUserInteractionElement(uimFullLayout);
				if (result == null) result = caseSecureObject(uimFullLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LayoutPackage.UIM_XY_LAYOUT: {
				UimXYLayout uimXYLayout = (UimXYLayout)theEObject;
				T result = caseUimXYLayout(uimXYLayout);
				if (result == null) result = caseUimLayout(uimXYLayout);
				if (result == null) result = caseUimContainer(uimXYLayout);
				if (result == null) result = caseUimComponent(uimXYLayout);
				if (result == null) result = caseEditableSecureObject(uimXYLayout);
				if (result == null) result = caseUserInteractionElement(uimXYLayout);
				if (result == null) result = caseSecureObject(uimXYLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LayoutPackage.UIM_BORDER_LAYOUT: {
				UimBorderLayout uimBorderLayout = (UimBorderLayout)theEObject;
				T result = caseUimBorderLayout(uimBorderLayout);
				if (result == null) result = caseUimLayout(uimBorderLayout);
				if (result == null) result = caseUimContainer(uimBorderLayout);
				if (result == null) result = caseUimComponent(uimBorderLayout);
				if (result == null) result = caseEditableSecureObject(uimBorderLayout);
				if (result == null) result = caseUserInteractionElement(uimBorderLayout);
				if (result == null) result = caseSecureObject(uimBorderLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LayoutPackage.UIM_TOOLBAR_LAYOUT: {
				UimToolbarLayout uimToolbarLayout = (UimToolbarLayout)theEObject;
				T result = caseUimToolbarLayout(uimToolbarLayout);
				if (result == null) result = caseUimLayout(uimToolbarLayout);
				if (result == null) result = caseUimContainer(uimToolbarLayout);
				if (result == null) result = caseUimComponent(uimToolbarLayout);
				if (result == null) result = caseEditableSecureObject(uimToolbarLayout);
				if (result == null) result = caseUserInteractionElement(uimToolbarLayout);
				if (result == null) result = caseSecureObject(uimToolbarLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LayoutPackage.OUTLAYABLE_COMPONENT: {
				OutlayableComponent outlayableComponent = (OutlayableComponent)theEObject;
				T result = caseOutlayableComponent(outlayableComponent);
				if (result == null) result = caseUimComponent(outlayableComponent);
				if (result == null) result = caseUserInteractionElement(outlayableComponent);
				if (result == null) result = caseSecureObject(outlayableComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LayoutPackage.UIM_LAYOUT: {
				UimLayout uimLayout = (UimLayout)theEObject;
				T result = caseUimLayout(uimLayout);
				if (result == null) result = caseUimContainer(uimLayout);
				if (result == null) result = caseUimComponent(uimLayout);
				if (result == null) result = caseEditableSecureObject(uimLayout);
				if (result == null) result = caseUserInteractionElement(uimLayout);
				if (result == null) result = caseSecureObject(uimLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LayoutPackage.LAYOUT_CONTAINER: {
				LayoutContainer layoutContainer = (LayoutContainer)theEObject;
				T result = caseLayoutContainer(layoutContainer);
				if (result == null) result = caseUimContainer(layoutContainer);
				if (result == null) result = caseUimComponent(layoutContainer);
				if (result == null) result = caseEditableSecureObject(layoutContainer);
				if (result == null) result = caseUserInteractionElement(layoutContainer);
				if (result == null) result = caseSecureObject(layoutContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LayoutPackage.UIM_GRID_LAYOUT: {
				UimGridLayout uimGridLayout = (UimGridLayout)theEObject;
				T result = caseUimGridLayout(uimGridLayout);
				if (result == null) result = caseUimLayout(uimGridLayout);
				if (result == null) result = caseUimContainer(uimGridLayout);
				if (result == null) result = caseUimComponent(uimGridLayout);
				if (result == null) result = caseEditableSecureObject(uimGridLayout);
				if (result == null) result = caseUserInteractionElement(uimGridLayout);
				if (result == null) result = caseSecureObject(uimGridLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Column Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Column Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimColumnLayout(UimColumnLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Full Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Full Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimFullLayout(UimFullLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim XY Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim XY Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimXYLayout(UimXYLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Border Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Border Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimBorderLayout(UimBorderLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Toolbar Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Toolbar Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimToolbarLayout(UimToolbarLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outlayable Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outlayable Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutlayableComponent(OutlayableComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimLayout(UimLayout object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Uim Grid Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Grid Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimGridLayout(UimGridLayout object) {
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

} //LayoutSwitch
