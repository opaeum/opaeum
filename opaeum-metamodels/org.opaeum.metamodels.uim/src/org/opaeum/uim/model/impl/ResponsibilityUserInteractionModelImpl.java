/**
 */
package org.opaeum.uim.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.ResponsibilityViewer;
import org.opaeum.uim.impl.UserInteractionElementImpl;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;
import org.opaeum.uim.wizard.WizardPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Responsibility User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.impl.ResponsibilityUserInteractionModelImpl#getUmlElementUid <em>Uml Element Uid</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ResponsibilityUserInteractionModelImpl#getLinkedUmlResource <em>Linked Uml Resource</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ResponsibilityUserInteractionModelImpl#getInvocationWizard <em>Invocation Wizard</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.ResponsibilityUserInteractionModelImpl#getViewer <em>Viewer</em>}</li>
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
	protected ResponsibilityInvocationWizard invocationWizard;

	/**
	 * The cached value of the '{@link #getViewer() <em>Viewer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewer()
	 * @generated
	 * @ordered
	 */
	protected ResponsibilityViewer viewer;

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
		return ModelPackage.Literals.RESPONSIBILITY_USER_INTERACTION_MODEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID, oldUmlElementUid, umlElementUid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE, oldLinkedUmlResource, linkedUmlResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsibilityInvocationWizard getInvocationWizard() {
		return invocationWizard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInvocationWizard(ResponsibilityInvocationWizard newInvocationWizard, NotificationChain msgs) {
		ResponsibilityInvocationWizard oldInvocationWizard = invocationWizard;
		invocationWizard = newInvocationWizard;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD, oldInvocationWizard, newInvocationWizard);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvocationWizard(ResponsibilityInvocationWizard newInvocationWizard) {
		if (newInvocationWizard != invocationWizard) {
			NotificationChain msgs = null;
			if (invocationWizard != null)
				msgs = ((InternalEObject)invocationWizard).eInverseRemove(this, WizardPackage.RESPONSIBILITY_INVOCATION_WIZARD__MODEL, ResponsibilityInvocationWizard.class, msgs);
			if (newInvocationWizard != null)
				msgs = ((InternalEObject)newInvocationWizard).eInverseAdd(this, WizardPackage.RESPONSIBILITY_INVOCATION_WIZARD__MODEL, ResponsibilityInvocationWizard.class, msgs);
			msgs = basicSetInvocationWizard(newInvocationWizard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD, newInvocationWizard, newInvocationWizard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsibilityViewer getViewer() {
		return viewer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetViewer(ResponsibilityViewer newViewer, NotificationChain msgs) {
		ResponsibilityViewer oldViewer = viewer;
		viewer = newViewer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER, oldViewer, newViewer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewer(ResponsibilityViewer newViewer) {
		if (newViewer != viewer) {
			NotificationChain msgs = null;
			if (viewer != null)
				msgs = ((InternalEObject)viewer).eInverseRemove(this, EditorPackage.RESPONSIBILITY_VIEWER__MODEL, ResponsibilityViewer.class, msgs);
			if (newViewer != null)
				msgs = ((InternalEObject)newViewer).eInverseAdd(this, EditorPackage.RESPONSIBILITY_VIEWER__MODEL, ResponsibilityViewer.class, msgs);
			msgs = basicSetViewer(newViewer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER, newViewer, newViewer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				if (invocationWizard != null)
					msgs = ((InternalEObject)invocationWizard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD, null, msgs);
				return basicSetInvocationWizard((ResponsibilityInvocationWizard)otherEnd, msgs);
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER:
				if (viewer != null)
					msgs = ((InternalEObject)viewer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER, null, msgs);
				return basicSetViewer((ResponsibilityViewer)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				return basicSetInvocationWizard(null, msgs);
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER:
				return basicSetViewer(null, msgs);
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
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID:
				return getUmlElementUid();
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				return getLinkedUmlResource();
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				return getInvocationWizard();
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER:
				return getViewer();
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
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID:
				setUmlElementUid((String)newValue);
				return;
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				setLinkedUmlResource((String)newValue);
				return;
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				setInvocationWizard((ResponsibilityInvocationWizard)newValue);
				return;
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER:
				setViewer((ResponsibilityViewer)newValue);
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
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID:
				setUmlElementUid(UML_ELEMENT_UID_EDEFAULT);
				return;
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				setLinkedUmlResource(LINKED_UML_RESOURCE_EDEFAULT);
				return;
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				setInvocationWizard((ResponsibilityInvocationWizard)null);
				return;
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER:
				setViewer((ResponsibilityViewer)null);
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
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID:
				return UML_ELEMENT_UID_EDEFAULT == null ? umlElementUid != null : !UML_ELEMENT_UID_EDEFAULT.equals(umlElementUid);
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE:
				return LINKED_UML_RESOURCE_EDEFAULT == null ? linkedUmlResource != null : !LINKED_UML_RESOURCE_EDEFAULT.equals(linkedUmlResource);
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD:
				return invocationWizard != null;
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__VIEWER:
				return viewer != null;
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
				case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID: return UimPackage.UML_REFERENCE__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == AbstractUserInteractionModel.class) {
			switch (derivedFeatureID) {
				case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE: return ModelPackage.ABSTRACT_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE;
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
				case UimPackage.UML_REFERENCE__UML_ELEMENT_UID: return ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID;
				default: return -1;
			}
		}
		if (baseClass == AbstractUserInteractionModel.class) {
			switch (baseFeatureID) {
				case ModelPackage.ABSTRACT_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE: return ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE;
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
