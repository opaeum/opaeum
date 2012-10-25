/**
 */
package org.opaeum.uim.wizard.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.wizard.NewObjectWizard;
import org.opaeum.uim.wizard.WizardPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Object Wizard</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.wizard.impl.NewObjectWizardImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NewObjectWizardImpl extends AbstractWizardImpl implements NewObjectWizard {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NewObjectWizardImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WizardPackage.Literals.NEW_OBJECT_WIZARD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassUserInteractionModel getModel() {
		if (eContainerFeatureID() != WizardPackage.NEW_OBJECT_WIZARD__MODEL) return null;
		return (ClassUserInteractionModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(ClassUserInteractionModel newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, WizardPackage.NEW_OBJECT_WIZARD__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(ClassUserInteractionModel newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != WizardPackage.NEW_OBJECT_WIZARD__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, ClassUserInteractionModel.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WizardPackage.NEW_OBJECT_WIZARD__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WizardPackage.NEW_OBJECT_WIZARD__MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModel((ClassUserInteractionModel)otherEnd, msgs);
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
			case WizardPackage.NEW_OBJECT_WIZARD__MODEL:
				return basicSetModel(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case WizardPackage.NEW_OBJECT_WIZARD__MODEL:
				return eInternalContainer().eInverseRemove(this, ModelPackage.CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD, ClassUserInteractionModel.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WizardPackage.NEW_OBJECT_WIZARD__MODEL:
				return getModel();
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
			case WizardPackage.NEW_OBJECT_WIZARD__MODEL:
				setModel((ClassUserInteractionModel)newValue);
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
			case WizardPackage.NEW_OBJECT_WIZARD__MODEL:
				setModel((ClassUserInteractionModel)null);
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
			case WizardPackage.NEW_OBJECT_WIZARD__MODEL:
				return getModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //NewObjectWizardImpl
