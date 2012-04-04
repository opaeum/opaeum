/**
 */
package org.opaeum.uim.editor.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UimRootElement;
import org.opaeum.uim.editor.ActionTaskEditor;
import org.opaeum.uim.editor.EditorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Task Editor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.editor.impl.ActionTaskEditorImpl#getLinkedUmlResource <em>Linked Uml Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionTaskEditorImpl extends AbstractEditorImpl implements ActionTaskEditor {
	/**
	 * The default value of the '{@link #getLinkedUmlResource() <em>Linked Uml Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedUmlResource()
	 * @generated
	 * @ordered
	 */
	protected static final String LINKED_UML_RESOURCE_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getLinkedUmlResource() <em>Linked Uml Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedUmlResource()
	 * @generated
	 * @ordered
	 */
	protected String linkedUmlResource = LINKED_UML_RESOURCE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionTaskEditorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditorPackage.Literals.ACTION_TASK_EDITOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLinkedUmlResource() {
		return linkedUmlResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkedUmlResource(String newLinkedUmlResource) {
		String oldLinkedUmlResource = linkedUmlResource;
		linkedUmlResource = newLinkedUmlResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ACTION_TASK_EDITOR__LINKED_UML_RESOURCE, oldLinkedUmlResource, linkedUmlResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EditorPackage.ACTION_TASK_EDITOR__LINKED_UML_RESOURCE:
				return getLinkedUmlResource();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EditorPackage.ACTION_TASK_EDITOR__LINKED_UML_RESOURCE:
				setLinkedUmlResource((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EditorPackage.ACTION_TASK_EDITOR__LINKED_UML_RESOURCE:
				setLinkedUmlResource(LINKED_UML_RESOURCE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EditorPackage.ACTION_TASK_EDITOR__LINKED_UML_RESOURCE:
				return LINKED_UML_RESOURCE_EDEFAULT == null ? linkedUmlResource != null : !LINKED_UML_RESOURCE_EDEFAULT.equals(linkedUmlResource);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == UimRootElement.class) {
			switch (derivedFeatureID) {
				case EditorPackage.ACTION_TASK_EDITOR__LINKED_UML_RESOURCE: return UimPackage.UIM_ROOT_ELEMENT__LINKED_UML_RESOURCE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == UimRootElement.class) {
			switch (baseFeatureID) {
				case UimPackage.UIM_ROOT_ELEMENT__LINKED_UML_RESOURCE: return EditorPackage.ACTION_TASK_EDITOR__LINKED_UML_RESOURCE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (linkedUmlResource: ");
		result.append(linkedUmlResource);
		result.append(')');
		return result.toString();
	}

} //ActionTaskEditorImpl
