/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import org.drools.drools._5._0.process.ConnectionType;
import org.drools.drools._5._0.process.ProcessPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConnectionTypeImpl#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConnectionTypeImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConnectionTypeImpl#getFromType <em>From Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConnectionTypeImpl#getTo <em>To</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ConnectionTypeImpl#getToType <em>To Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectionTypeImpl extends EObjectImpl implements ConnectionType {
	/**
	 * The default value of the '{@link #getBendpoints() <em>Bendpoints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBendpoints()
	 * @generated
	 * @ordered
	 */
	protected static final String BENDPOINTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBendpoints() <em>Bendpoints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBendpoints()
	 * @generated
	 * @ordered
	 */
	protected String bendpoints = BENDPOINTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected String from = FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getFromType() <em>From Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromType()
	 * @generated
	 * @ordered
	 */
	protected static final String FROM_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFromType() <em>From Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromType()
	 * @generated
	 * @ordered
	 */
	protected String fromType = FROM_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected String to = TO_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.CONNECTION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBendpoints() {
		return bendpoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBendpoints(String newBendpoints) {
		String oldBendpoints = bendpoints;
		bendpoints = newBendpoints;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION_TYPE__BENDPOINTS, oldBendpoints, bendpoints));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrom(String newFrom) {
		String oldFrom = from;
		from = newFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION_TYPE__FROM, oldFrom, from));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFromType() {
		return fromType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromType(String newFromType) {
		String oldFromType = fromType;
		fromType = newFromType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION_TYPE__FROM_TYPE, oldFromType, fromType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(String newTo) {
		String oldTo = to;
		to = newTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION_TYPE__TO, oldTo, to));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.CONNECTION_TYPE__TO_TYPE, oldToType, toType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProcessPackage.CONNECTION_TYPE__BENDPOINTS:
				return getBendpoints();
			case ProcessPackage.CONNECTION_TYPE__FROM:
				return getFrom();
			case ProcessPackage.CONNECTION_TYPE__FROM_TYPE:
				return getFromType();
			case ProcessPackage.CONNECTION_TYPE__TO:
				return getTo();
			case ProcessPackage.CONNECTION_TYPE__TO_TYPE:
				return getToType();
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
			case ProcessPackage.CONNECTION_TYPE__BENDPOINTS:
				setBendpoints((String)newValue);
				return;
			case ProcessPackage.CONNECTION_TYPE__FROM:
				setFrom((String)newValue);
				return;
			case ProcessPackage.CONNECTION_TYPE__FROM_TYPE:
				setFromType((String)newValue);
				return;
			case ProcessPackage.CONNECTION_TYPE__TO:
				setTo((String)newValue);
				return;
			case ProcessPackage.CONNECTION_TYPE__TO_TYPE:
				setToType((String)newValue);
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
			case ProcessPackage.CONNECTION_TYPE__BENDPOINTS:
				setBendpoints(BENDPOINTS_EDEFAULT);
				return;
			case ProcessPackage.CONNECTION_TYPE__FROM:
				setFrom(FROM_EDEFAULT);
				return;
			case ProcessPackage.CONNECTION_TYPE__FROM_TYPE:
				setFromType(FROM_TYPE_EDEFAULT);
				return;
			case ProcessPackage.CONNECTION_TYPE__TO:
				setTo(TO_EDEFAULT);
				return;
			case ProcessPackage.CONNECTION_TYPE__TO_TYPE:
				setToType(TO_TYPE_EDEFAULT);
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
			case ProcessPackage.CONNECTION_TYPE__BENDPOINTS:
				return BENDPOINTS_EDEFAULT == null ? bendpoints != null : !BENDPOINTS_EDEFAULT.equals(bendpoints);
			case ProcessPackage.CONNECTION_TYPE__FROM:
				return FROM_EDEFAULT == null ? from != null : !FROM_EDEFAULT.equals(from);
			case ProcessPackage.CONNECTION_TYPE__FROM_TYPE:
				return FROM_TYPE_EDEFAULT == null ? fromType != null : !FROM_TYPE_EDEFAULT.equals(fromType);
			case ProcessPackage.CONNECTION_TYPE__TO:
				return TO_EDEFAULT == null ? to != null : !TO_EDEFAULT.equals(to);
			case ProcessPackage.CONNECTION_TYPE__TO_TYPE:
				return TO_TYPE_EDEFAULT == null ? toType != null : !TO_TYPE_EDEFAULT.equals(toType);
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
		result.append(" (bendpoints: ");
		result.append(bendpoints);
		result.append(", from: ");
		result.append(from);
		result.append(", fromType: ");
		result.append(fromType);
		result.append(", to: ");
		result.append(to);
		result.append(", toType: ");
		result.append(toType);
		result.append(')');
		return result.toString();
	}

} //ConnectionTypeImpl
