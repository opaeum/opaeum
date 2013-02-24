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
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.wizard.OperationResultPage;
import org.opaeum.uim.wizard.WizardPackage;
import org.opaeum.uim.wizard.impl.InvocationWizardImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Invocation Wizard</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.impl.OperationInvocationWizardImpl#getLinkedUmlResource <em>Linked Uml Resource</em>}</li>
 *   <li>{@link org.opaeum.uim.model.impl.OperationInvocationWizardImpl#getResultPage <em>Result Page</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationInvocationWizardImpl extends InvocationWizardImpl implements OperationInvocationWizard {
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
	 * The cached value of the '{@link #getResultPage() <em>Result Page</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultPage()
	 * @generated
	 * @ordered
	 */
	protected OperationResultPage resultPage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationInvocationWizardImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.OPERATION_INVOCATION_WIZARD;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OPERATION_INVOCATION_WIZARD__LINKED_UML_RESOURCE, oldLinkedUmlResource, linkedUmlResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationResultPage getResultPage() {
		return resultPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResultPage(OperationResultPage newResultPage, NotificationChain msgs) {
		OperationResultPage oldResultPage = resultPage;
		resultPage = newResultPage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE, oldResultPage, newResultPage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultPage(OperationResultPage newResultPage) {
		if (newResultPage != resultPage) {
			NotificationChain msgs = null;
			if (resultPage != null)
				msgs = ((InternalEObject)resultPage).eInverseRemove(this, WizardPackage.OPERATION_RESULT_PAGE__WIZARD, OperationResultPage.class, msgs);
			if (newResultPage != null)
				msgs = ((InternalEObject)newResultPage).eInverseAdd(this, WizardPackage.OPERATION_RESULT_PAGE__WIZARD, OperationResultPage.class, msgs);
			msgs = basicSetResultPage(newResultPage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE, newResultPage, newResultPage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE:
				if (resultPage != null)
					msgs = ((InternalEObject)resultPage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE, null, msgs);
				return basicSetResultPage((OperationResultPage)otherEnd, msgs);
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
			case ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE:
				return basicSetResultPage(null, msgs);
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
			case ModelPackage.OPERATION_INVOCATION_WIZARD__LINKED_UML_RESOURCE:
				return getLinkedUmlResource();
			case ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE:
				return getResultPage();
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
			case ModelPackage.OPERATION_INVOCATION_WIZARD__LINKED_UML_RESOURCE:
				setLinkedUmlResource((String)newValue);
				return;
			case ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE:
				setResultPage((OperationResultPage)newValue);
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
			case ModelPackage.OPERATION_INVOCATION_WIZARD__LINKED_UML_RESOURCE:
				setLinkedUmlResource(LINKED_UML_RESOURCE_EDEFAULT);
				return;
			case ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE:
				setResultPage((OperationResultPage)null);
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
			case ModelPackage.OPERATION_INVOCATION_WIZARD__LINKED_UML_RESOURCE:
				return LINKED_UML_RESOURCE_EDEFAULT == null ? linkedUmlResource != null : !LINKED_UML_RESOURCE_EDEFAULT.equals(linkedUmlResource);
			case ModelPackage.OPERATION_INVOCATION_WIZARD__RESULT_PAGE:
				return resultPage != null;
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
		if (baseClass == AbstractUserInteractionModel.class) {
			switch (derivedFeatureID) {
				case ModelPackage.OPERATION_INVOCATION_WIZARD__LINKED_UML_RESOURCE: return ModelPackage.ABSTRACT_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE;
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
		if (baseClass == AbstractUserInteractionModel.class) {
			switch (baseFeatureID) {
				case ModelPackage.ABSTRACT_USER_INTERACTION_MODEL__LINKED_UML_RESOURCE: return ModelPackage.OPERATION_INVOCATION_WIZARD__LINKED_UML_RESOURCE;
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

} //OperationInvocationWizardImpl
