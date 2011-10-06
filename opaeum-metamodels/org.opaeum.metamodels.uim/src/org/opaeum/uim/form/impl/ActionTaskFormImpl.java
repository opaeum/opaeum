/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.form.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.folder.ActivityFolder;
import org.opaeum.uim.form.ActionTaskForm;
import org.opaeum.uim.form.FormPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Task Form</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.form.impl.ActionTaskFormImpl#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionTaskFormImpl extends FormPanelImpl implements ActionTaskForm {
	/**
	 * The cached value of the '{@link #getFolder() <em>Folder</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolder()
	 * @generated
	 * @ordered
	 */
	protected ActivityFolder folder;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionTaskFormImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FormPackage.Literals.ACTION_TASK_FORM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityFolder getFolder() {
		if (folder != null && folder.eIsProxy()) {
			InternalEObject oldFolder = (InternalEObject)folder;
			folder = (ActivityFolder)eResolveProxy(oldFolder);
			if (folder != oldFolder) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormPackage.ACTION_TASK_FORM__FOLDER, oldFolder, folder));
			}
		}
		return folder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityFolder basicGetFolder() {
		return folder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolder(ActivityFolder newFolder) {
		ActivityFolder oldFolder = folder;
		folder = newFolder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FormPackage.ACTION_TASK_FORM__FOLDER, oldFolder, folder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FormPackage.ACTION_TASK_FORM__FOLDER:
				if (resolve) return getFolder();
				return basicGetFolder();
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
			case FormPackage.ACTION_TASK_FORM__FOLDER:
				setFolder((ActivityFolder)newValue);
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
			case FormPackage.ACTION_TASK_FORM__FOLDER:
				setFolder((ActivityFolder)null);
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
			case FormPackage.ACTION_TASK_FORM__FOLDER:
				return folder != null;
		}
		return super.eIsSet(featureID);
	}

} //ActionTaskFormImpl
