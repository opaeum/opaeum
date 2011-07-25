/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import org.drools.drools._5._0.process.ConstraintType;
import org.drools.drools._5._0.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl#getDialect <em>Dialect</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl#getToNodeId <em>To Node Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl#getToType <em>To Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintTypeImpl extends EObjectImpl implements ConstraintType {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDialect() <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDialect()
	 * @generated
	 * @ordered
	 */
	protected static final String DIALECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDialect() <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDialect()
	 * @generated
	 * @ordered
	 */
	protected String dialect = DIALECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIORITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected String priority = PRIORITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getToNodeId() <em>To Node Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToNodeId()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_NODE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToNodeId() <em>To Node Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToNodeId()
	 * @generated
	 * @ordered
	 */
	protected String toNodeId = TO_NODE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getToType() <em>To Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToType()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getToType() <em>To Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToType()
	 * @generated
	 * @ordered
	 */
	protected String toType = TO_TYPE_EDEFAULT;

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
	protected ConstraintTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.CONSTRAINT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONSTRAINT_TYPE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDialect() {
		return dialect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDialect(String newDialect) {
		String oldDialect = dialect;
		dialect = newDialect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONSTRAINT_TYPE__DIALECT, oldDialect, dialect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONSTRAINT_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(String newPriority) {
		String oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONSTRAINT_TYPE__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getToNodeId() {
		return toNodeId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToNodeId(String newToNodeId) {
		String oldToNodeId = toNodeId;
		toNodeId = newToNodeId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONSTRAINT_TYPE__TO_NODE_ID, oldToNodeId, toNodeId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getToType() {
		return toType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToType(String newToType) {
		String oldToType = toType;
		toType = newToType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONSTRAINT_TYPE__TO_TYPE, oldToType, toType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONSTRAINT_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProcessPackage.CONSTRAINT_TYPE__VALUE:
				return getValue();
			case ProcessPackage.CONSTRAINT_TYPE__DIALECT:
				return getDialect();
			case ProcessPackage.CONSTRAINT_TYPE__NAME:
				return getName();
			case ProcessPackage.CONSTRAINT_TYPE__PRIORITY:
				return getPriority();
			case ProcessPackage.CONSTRAINT_TYPE__TO_NODE_ID:
				return getToNodeId();
			case ProcessPackage.CONSTRAINT_TYPE__TO_TYPE:
				return getToType();
			case ProcessPackage.CONSTRAINT_TYPE__TYPE:
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
			case ProcessPackage.CONSTRAINT_TYPE__VALUE:
				setValue((String)newValue);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__DIALECT:
				setDialect((String)newValue);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__NAME:
				setName((String)newValue);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__PRIORITY:
				setPriority((String)newValue);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__TO_NODE_ID:
				setToNodeId((String)newValue);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__TO_TYPE:
				setToType((String)newValue);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__TYPE:
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
			case ProcessPackage.CONSTRAINT_TYPE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__DIALECT:
				setDialect(DIALECT_EDEFAULT);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__TO_NODE_ID:
				setToNodeId(TO_NODE_ID_EDEFAULT);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__TO_TYPE:
				setToType(TO_TYPE_EDEFAULT);
				return;
			case ProcessPackage.CONSTRAINT_TYPE__TYPE:
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
			case ProcessPackage.CONSTRAINT_TYPE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case ProcessPackage.CONSTRAINT_TYPE__DIALECT:
				return DIALECT_EDEFAULT == null ? dialect != null : !DIALECT_EDEFAULT.equals(dialect);
			case ProcessPackage.CONSTRAINT_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProcessPackage.CONSTRAINT_TYPE__PRIORITY:
				return PRIORITY_EDEFAULT == null ? priority != null : !PRIORITY_EDEFAULT.equals(priority);
			case ProcessPackage.CONSTRAINT_TYPE__TO_NODE_ID:
				return TO_NODE_ID_EDEFAULT == null ? toNodeId != null : !TO_NODE_ID_EDEFAULT.equals(toNodeId);
			case ProcessPackage.CONSTRAINT_TYPE__TO_TYPE:
				return TO_TYPE_EDEFAULT == null ? toType != null : !TO_TYPE_EDEFAULT.equals(toType);
			case ProcessPackage.CONSTRAINT_TYPE__TYPE:
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
		result.append(" (value: ");
		result.append(value);
		result.append(", dialect: ");
		result.append(dialect);
		result.append(", name: ");
		result.append(name);
		result.append(", priority: ");
		result.append(priority);
		result.append(", toNodeId: ");
		result.append(toNodeId);
		result.append(", toType: ");
		result.append(toType);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //ConstraintTypeImpl
