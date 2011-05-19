/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;

import java.util.Collection;


import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.OperationContainingFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Containing Folder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.OperationContainingFolderImpl#getOperationInvocationForms <em>Operation Invocation Forms</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.OperationContainingFolderImpl#getClassForm <em>Class Form</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OperationContainingFolderImpl extends AbstractFormFolderImpl implements OperationContainingFolder {
	/**
	 * The cached value of the '{@link #getOperationInvocationForms() <em>Operation Invocation Forms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationInvocationForms()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationInvocationForm> operationInvocationForms;

	/**
	 * The cached value of the '{@link #getClassForm() <em>Class Form</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassForm()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassForm> classForm;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationContainingFolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.OPERATION_CONTAINING_FOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationInvocationForm> getOperationInvocationForms() {
		if (operationInvocationForms == null) {
			operationInvocationForms = new EObjectContainmentWithInverseEList<OperationInvocationForm>(OperationInvocationForm.class, this, UIMPackage.OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS, UIMPackage.OPERATION_INVOCATION_FORM__FOLDER);
		}
		return operationInvocationForms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassForm> getClassForm() {
		if (classForm == null) {
			classForm = new EObjectContainmentWithInverseEList<ClassForm>(ClassForm.class, this, UIMPackage.OPERATION_CONTAINING_FOLDER__CLASS_FORM, UIMPackage.CLASS_FORM__FOLDER);
		}
		return classForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public org.eclipse.uml2.uml.Class getRepresentedClass() {
		
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOperationInvocationForms()).basicAdd(otherEnd, msgs);
			case UIMPackage.OPERATION_CONTAINING_FOLDER__CLASS_FORM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClassForm()).basicAdd(otherEnd, msgs);
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
			case UIMPackage.OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS:
				return ((InternalEList<?>)getOperationInvocationForms()).basicRemove(otherEnd, msgs);
			case UIMPackage.OPERATION_CONTAINING_FOLDER__CLASS_FORM:
				return ((InternalEList<?>)getClassForm()).basicRemove(otherEnd, msgs);
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
			case UIMPackage.OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS:
				return getOperationInvocationForms();
			case UIMPackage.OPERATION_CONTAINING_FOLDER__CLASS_FORM:
				return getClassForm();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UIMPackage.OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS:
				getOperationInvocationForms().clear();
				getOperationInvocationForms().addAll((Collection<? extends OperationInvocationForm>)newValue);
				return;
			case UIMPackage.OPERATION_CONTAINING_FOLDER__CLASS_FORM:
				getClassForm().clear();
				getClassForm().addAll((Collection<? extends ClassForm>)newValue);
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
			case UIMPackage.OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS:
				getOperationInvocationForms().clear();
				return;
			case UIMPackage.OPERATION_CONTAINING_FOLDER__CLASS_FORM:
				getClassForm().clear();
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
			case UIMPackage.OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS:
				return operationInvocationForms != null && !operationInvocationForms.isEmpty();
			case UIMPackage.OPERATION_CONTAINING_FOLDER__CLASS_FORM:
				return classForm != null && !classForm.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OperationContainingFolderImpl
