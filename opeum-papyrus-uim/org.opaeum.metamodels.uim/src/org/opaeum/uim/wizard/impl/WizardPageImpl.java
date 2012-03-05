/**
 */
package org.opaeum.uim.wizard.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.impl.PageImpl;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.wizard.AbstractWizard;
import org.opaeum.uim.wizard.WizardPackage;
import org.opaeum.uim.wizard.WizardPage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.wizard.impl.WizardPageImpl#getWizard <em>Wizard</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WizardPageImpl extends PageImpl implements WizardPage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WizardPageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WizardPackage.Literals.WIZARD_PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractWizard getWizard() {
		if (eContainerFeatureID() != WizardPackage.WIZARD_PAGE__WIZARD) return null;
		return (AbstractWizard)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWizard(AbstractWizard newWizard, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWizard, WizardPackage.WIZARD_PAGE__WIZARD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWizard(AbstractWizard newWizard) {
		if (newWizard != eInternalContainer() || (eContainerFeatureID() != WizardPackage.WIZARD_PAGE__WIZARD && newWizard != null)) {
			if (EcoreUtil.isAncestor(this, newWizard))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWizard != null)
				msgs = ((InternalEObject)newWizard).eInverseAdd(this, WizardPackage.ABSTRACT_WIZARD__PAGES, AbstractWizard.class, msgs);
			msgs = basicSetWizard(newWizard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WizardPackage.WIZARD_PAGE__WIZARD, newWizard, newWizard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WizardPackage.WIZARD_PAGE__WIZARD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWizard((AbstractWizard)otherEnd, msgs);
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
			case WizardPackage.WIZARD_PAGE__WIZARD:
				return basicSetWizard(null, msgs);
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
			case WizardPackage.WIZARD_PAGE__WIZARD:
				return eInternalContainer().eInverseRemove(this, WizardPackage.ABSTRACT_WIZARD__PAGES, AbstractWizard.class, msgs);
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
			case WizardPackage.WIZARD_PAGE__WIZARD:
				return getWizard();
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
			case WizardPackage.WIZARD_PAGE__WIZARD:
				setWizard((AbstractWizard)newValue);
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
			case WizardPackage.WIZARD_PAGE__WIZARD:
				setWizard((AbstractWizard)null);
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
			case WizardPackage.WIZARD_PAGE__WIZARD:
				return getWizard() != null;
		}
		return super.eIsSet(featureID);
	}

} //WizardPageImpl
