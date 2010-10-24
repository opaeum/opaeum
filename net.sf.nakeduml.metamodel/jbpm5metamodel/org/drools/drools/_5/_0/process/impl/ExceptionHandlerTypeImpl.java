/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import java.util.Collection;

import org.drools.drools._5._0.process.ActionType;
import org.drools.drools._5._0.process.ExceptionHandlerType;
import org.drools.drools._5._0.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exception Handler Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl#getAction <em>Action</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl#getFaultName <em>Fault Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl#getFaultVariable <em>Fault Variable</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExceptionHandlerTypeImpl extends EObjectImpl implements ExceptionHandlerType {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getFaultName() <em>Fault Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultName()
	 * @generated
	 * @ordered
	 */
	protected static final String FAULT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFaultName() <em>Fault Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultName()
	 * @generated
	 * @ordered
	 */
	protected String faultName = FAULT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFaultVariable() <em>Fault Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultVariable()
	 * @generated
	 * @ordered
	 */
	protected static final String FAULT_VARIABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFaultVariable() <em>Fault Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFaultVariable()
	 * @generated
	 * @ordered
	 */
	protected String faultVariable = FAULT_VARIABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExceptionHandlerTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.EXCEPTION_HANDLER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ProcessPackage.EXCEPTION_HANDLER_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActionType> getAction() {
		return getGroup().list(ProcessPackage.Literals.EXCEPTION_HANDLER_TYPE__ACTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFaultName() {
		return faultName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFaultName(String newFaultName) {
		String oldFaultName = faultName;
		faultName = newFaultName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_NAME, oldFaultName, faultName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFaultVariable() {
		return faultVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFaultVariable(String newFaultVariable) {
		String oldFaultVariable = faultVariable;
		faultVariable = newFaultVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_VARIABLE, oldFaultVariable, faultVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EXCEPTION_HANDLER_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__ACTION:
				return ((InternalEList<?>)getAction()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__ACTION:
				return getAction();
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_NAME:
				return getFaultName();
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_VARIABLE:
				return getFaultVariable();
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__TYPE:
				return getType();
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
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__ACTION:
				getAction().clear();
				getAction().addAll((Collection<? extends ActionType>)newValue);
				return;
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_NAME:
				setFaultName((String)newValue);
				return;
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_VARIABLE:
				setFaultVariable((String)newValue);
				return;
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__TYPE:
				setType((String)newValue);
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
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__GROUP:
				getGroup().clear();
				return;
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__ACTION:
				getAction().clear();
				return;
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_NAME:
				setFaultName(FAULT_NAME_EDEFAULT);
				return;
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_VARIABLE:
				setFaultVariable(FAULT_VARIABLE_EDEFAULT);
				return;
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__TYPE:
				setType(TYPE_EDEFAULT);
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
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__ACTION:
				return !getAction().isEmpty();
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_NAME:
				return FAULT_NAME_EDEFAULT == null ? faultName != null : !FAULT_NAME_EDEFAULT.equals(faultName);
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__FAULT_VARIABLE:
				return FAULT_VARIABLE_EDEFAULT == null ? faultVariable != null : !FAULT_VARIABLE_EDEFAULT.equals(faultVariable);
			case ProcessPackage.EXCEPTION_HANDLER_TYPE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		}
		return super.eIsSet(featureID);
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
		result.append(" (group: ");
		result.append(group);
		result.append(", faultName: ");
		result.append(faultName);
		result.append(", faultVariable: ");
		result.append(faultVariable);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //ExceptionHandlerTypeImpl
