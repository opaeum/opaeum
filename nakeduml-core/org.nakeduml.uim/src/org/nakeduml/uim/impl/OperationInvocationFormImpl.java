/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.nakeduml.uim.OperationContainingFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.UimPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Invocation Form</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.OperationInvocationFormImpl#getFolder <em>Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationInvocationFormImpl extends FormPanelImpl implements OperationInvocationForm {
	/**
	 * The cached value of the '{@link #getFolder() <em>Folder</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFolder()
	 * @generated
	 * @ordered
	 */
	protected OperationContainingFolder folder;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationInvocationFormImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.OPERATION_INVOCATION_FORM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationContainingFolder getFolder() {
		if (folder != null && folder.eIsProxy()) {
			InternalEObject oldFolder = (InternalEObject)folder;
			folder = (OperationContainingFolder)eResolveProxy(oldFolder);
			if (folder != oldFolder) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UimPackage.OPERATION_INVOCATION_FORM__FOLDER, oldFolder, folder));
			}
		}
		return folder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationContainingFolder basicGetFolder() {
		return folder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFolder(OperationContainingFolder newFolder) {
		OperationContainingFolder oldFolder = folder;
		folder = newFolder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.OPERATION_INVOCATION_FORM__FOLDER, oldFolder, folder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UimPackage.OPERATION_INVOCATION_FORM__FOLDER:
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
			case UimPackage.OPERATION_INVOCATION_FORM__FOLDER:
				setFolder((OperationContainingFolder)newValue);
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
			case UimPackage.OPERATION_INVOCATION_FORM__FOLDER:
				setFolder((OperationContainingFolder)null);
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
			case UimPackage.OPERATION_INVOCATION_FORM__FOLDER:
				return folder != null;
		}
		return super.eIsSet(featureID);
	}

} //OperationInvocationFormImpl
