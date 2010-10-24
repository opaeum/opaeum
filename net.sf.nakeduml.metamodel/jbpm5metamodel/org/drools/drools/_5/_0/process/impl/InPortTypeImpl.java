/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import org.drools.drools._5._0.process.InPortType;
import org.drools.drools._5._0.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>In Port Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.InPortTypeImpl#getNodeId <em>Node Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.InPortTypeImpl#getNodeInType <em>Node In Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.InPortTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InPortTypeImpl extends EObjectImpl implements InPortType {
	/**
	 * The default value of the '{@link #getNodeId() <em>Node Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeId()
	 * @generated
	 * @ordered
	 */
	protected static final String NODE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNodeId() <em>Node Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeId()
	 * @generated
	 * @ordered
	 */
	protected String nodeId = NODE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getNodeInType() <em>Node In Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeInType()
	 * @generated
	 * @ordered
	 */
	protected static final String NODE_IN_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNodeInType() <em>Node In Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeInType()
	 * @generated
	 * @ordered
	 */
	protected String nodeInType = NODE_IN_TYPE_EDEFAULT;

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
	protected InPortTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.IN_PORT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeId(String newNodeId) {
		String oldNodeId = nodeId;
		nodeId = newNodeId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.IN_PORT_TYPE__NODE_ID, oldNodeId, nodeId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNodeInType() {
		return nodeInType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeInType(String newNodeInType) {
		String oldNodeInType = nodeInType;
		nodeInType = newNodeInType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.IN_PORT_TYPE__NODE_IN_TYPE, oldNodeInType, nodeInType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.IN_PORT_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProcessPackage.IN_PORT_TYPE__NODE_ID:
				return getNodeId();
			case ProcessPackage.IN_PORT_TYPE__NODE_IN_TYPE:
				return getNodeInType();
			case ProcessPackage.IN_PORT_TYPE__TYPE:
				return getType();
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
			case ProcessPackage.IN_PORT_TYPE__NODE_ID:
				setNodeId((String)newValue);
				return;
			case ProcessPackage.IN_PORT_TYPE__NODE_IN_TYPE:
				setNodeInType((String)newValue);
				return;
			case ProcessPackage.IN_PORT_TYPE__TYPE:
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
			case ProcessPackage.IN_PORT_TYPE__NODE_ID:
				setNodeId(NODE_ID_EDEFAULT);
				return;
			case ProcessPackage.IN_PORT_TYPE__NODE_IN_TYPE:
				setNodeInType(NODE_IN_TYPE_EDEFAULT);
				return;
			case ProcessPackage.IN_PORT_TYPE__TYPE:
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
			case ProcessPackage.IN_PORT_TYPE__NODE_ID:
				return NODE_ID_EDEFAULT == null ? nodeId != null : !NODE_ID_EDEFAULT.equals(nodeId);
			case ProcessPackage.IN_PORT_TYPE__NODE_IN_TYPE:
				return NODE_IN_TYPE_EDEFAULT == null ? nodeInType != null : !NODE_IN_TYPE_EDEFAULT.equals(nodeInType);
			case ProcessPackage.IN_PORT_TYPE__TYPE:
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
		result.append(" (nodeId: ");
		result.append(nodeId);
		result.append(", nodeInType: ");
		result.append(nodeInType);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //InPortTypeImpl
