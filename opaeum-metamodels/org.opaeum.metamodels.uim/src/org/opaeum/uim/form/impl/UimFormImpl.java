/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.form.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.folder.AbstractFormFolder;
import org.opaeum.uim.form.FormPackage;
import org.opaeum.uim.form.FormPanel;
import org.opaeum.uim.form.UimForm;
import org.opaeum.uim.impl.UserInteractionElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uim Form</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.form.impl.UimFormImpl#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimFormImpl extends UserInteractionElementImpl implements UimForm {
	/**
	 * The cached value of the '{@link #getPanel() <em>Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPanel()
	 * @generated
	 * @ordered
	 */
	protected FormPanel panel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimFormImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.UIM_FORM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormPanel getPanel() {
		return panel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPanel(FormPanel newPanel, NotificationChain msgs) {
		FormPanel oldPanel = panel;
		panel = newPanel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormPackage.UIM_FORM__PANEL, oldPanel, newPanel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPanel(FormPanel newPanel) {
		if (newPanel != panel) {
			NotificationChain msgs = null;
			if (panel != null)
				msgs = ((InternalEObject)panel).eInverseRemove(this, FormPackage.FORM_PANEL__FORM, FormPanel.class, msgs);
			if (newPanel != null)
				msgs = ((InternalEObject)newPanel).eInverseAdd(this, FormPackage.FORM_PANEL__FORM, FormPanel.class, msgs);
			msgs = basicSetPanel(newPanel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.UIM_FORM__PANEL, newPanel, newPanel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractFormFolder getFolder() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FormPackage.UIM_FORM__PANEL:
				if (panel != null)
					msgs = ((InternalEObject)panel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormPackage.UIM_FORM__PANEL, null, msgs);
				return basicSetPanel((FormPanel)otherEnd, msgs);
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
			case FormPackage.UIM_FORM__PANEL:
				return basicSetPanel(null, msgs);
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
			case FormPackage.UIM_FORM__PANEL:
				return getPanel();
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
			case FormPackage.UIM_FORM__PANEL:
				setPanel((FormPanel)newValue);
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
			case FormPackage.UIM_FORM__PANEL:
				setPanel((FormPanel)null);
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
			case FormPackage.UIM_FORM__PANEL:
				return panel != null;
		}
		return super.eIsSet(featureID);
	}

} //UimFormImpl
