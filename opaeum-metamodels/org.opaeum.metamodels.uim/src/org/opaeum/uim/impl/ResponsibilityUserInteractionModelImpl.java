/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.ResponsibilityUserInteractionModel;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UimRootElement;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.editor.ResponsibilityTaskEditor;
import org.opaeum.uim.wizard.InvokeResponsibilityWizard;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Responsibility User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.ResponsibilityUserInteractionModelImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ResponsibilityUserInteractionModelImpl#getLinkedUmlResource <em>Linked Uml Resource</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ResponsibilityUserInteractionModelImpl#getInvocationWizard <em>Invocation Wizard</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.ResponsibilityUserInteractionModelImpl#getTaskEditor <em>Task Editor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResponsibilityUserInteractionModelImpl extends UserInteractionElementImpl implements ResponsibilityUserInteractionModel {
	/**
	 * The default value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UML_ELEMENT_UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUmlElementUid() <em>Uml Element Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUmlElementUid()
	 * @generated
	 * @ordered
	 */
	protected String umlElementUid = UML_ELEMENT_UID_EDEFAULT;

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
	 * The cached value of the '{@link #getInvocationWizard() <em>Invocation Wizard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvocationWizard()
	 * @generated
	 * @ordered
	 */
	protected InvokeResponsibilityWizard invocationWizard;

	/**
	 * The cached value of the '{@link #getTaskEditor() <em>Task Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaskEditor()
	 * @generated
	 * @ordered
	 */
	protected ResponsibilityTaskEditor taskEditor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResponsibilityUserInteractionModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.RESPONSIBILITY_USER_INTERACTION_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUmlElementUid() {
		return umlElementUid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUmlElementUid(String newUmlElementUid) {
		String oldUmlElementUid = umlElementUid;
		umlElementUid = newUmlElementUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE, oldLinkedUmlResource, linkedUmlResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvokeResponsibilityWizard getInvocationWizard() {
		return invocationWizard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInvocationWizard(InvokeResponsibilityWizard newInvocationWizard, NotificationChain msgs) {
		InvokeResponsibilityWizard oldInvocationWizard = invocationWizard;
		invocationWizard = newInvocationWizard;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD, oldInvocationWizard, newInvocationWizard);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvocationWizard(InvokeResponsibilityWizard newInvocationWizard) {
		if (newInvocationWizard != invocationWizard) {
			NotificationChain msgs = null;
			if (invocationWizard != null)
				msgs = ((InternalEObject)invocationWizard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD, null, msgs);
			if (newInvocationWizard != null)
				msgs = ((InternalEObject)newInvocationWizard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD, null, msgs);
			msgs = basicSetInvocationWizard(newInvocationWizard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD, newInvocationWizard, newInvocationWizard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsibilityTaskEditor getTaskEditor() {
		return taskEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTaskEditor(ResponsibilityTaskEditor newTaskEditor, NotificationChain msgs) {
		ResponsibilityTaskEditor oldTaskEditor = taskEditor;
		taskEditor = newTaskEditor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR, oldTaskEditor, newTaskEditor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTaskEditor(ResponsibilityTaskEditor newTaskEditor) {
		if (newTaskEditor != taskEditor) {
			NotificationChain msgs = null;
			if (taskEditor != null)
				msgs = ((InternalEObject)taskEditor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR, null, msgs);
			if (newTaskEditor != null)
				msgs = ((InternalEObject)newTaskEditor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR, null, msgs);
			msgs = basicSetTaskEditor(newTaskEditor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR, newTaskEditor, newTaskEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				return basicSetInvocationWizard(null, msgs);
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR:
				return basicSetTaskEditor(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID:
				return getUmlElementUid();
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				return getLinkedUmlResource();
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				return getInvocationWizard();
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR:
				return getTaskEditor();
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
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				setLinkedUmlResource((String)newValue);
				return;
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				setInvocationWizard((InvokeResponsibilityWizard)newValue);
				return;
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR:
				setTaskEditor((ResponsibilityTaskEditor)newValue);
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
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				setLinkedUmlResource(LINKED_UML_RESOURCE_EDEFAULT);
				return;
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				setInvocationWizard((InvokeResponsibilityWizard)null);
				return;
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR:
				setTaskEditor((ResponsibilityTaskEditor)null);
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
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				return LINKED_UML_RESOURCE_EDEFAULT == null ? linkedUmlResource != null : !LINKED_UML_RESOURCE_EDEFAULT.equals(linkedUmlResource);
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				return invocationWizard != null;
			case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR:
				return taskEditor != null;
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
		if (baseClass == UmlReference.class) {
			switch (derivedFeatureID) {
				case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == UimRootElement.class) {
			switch (derivedFeatureID) {
				case UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE: return UimPackage.UIM_ROOT_ELEMENT__LINKED_UML_RESOURCE;
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
		if (baseClass == UmlReference.class) {
			switch (baseFeatureID) {
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == UimRootElement.class) {
			switch (baseFeatureID) {
				case UimPackage.UIM_ROOT_ELEMENT__LINKED_UML_RESOURCE: return UimPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE;
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
		result.append(" (umlElementUid: ");
		result.append(umlElementUid);
		result.append(", linkedUmlResource: ");
		result.append(linkedUmlResource);
		result.append(')');
		return result.toString();
	}

} //ResponsibilityUserInteractionModelImpl
