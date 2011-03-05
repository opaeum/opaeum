/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import java.util.Collection;

import org.drools.drools._5._0.process.ConstraintType;
import org.drools.drools._5._0.process.EventFiltersType;
import org.drools.drools._5._0.process.MappingType;
import org.drools.drools._5._0.process.ProcessPackage;
import org.drools.drools._5._0.process.TriggerType;
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
 * An implementation of the model object '<em><b>Trigger Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.TriggerTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.TriggerTypeImpl#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.TriggerTypeImpl#getEventFilters <em>Event Filters</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.TriggerTypeImpl#getMapping <em>Mapping</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.TriggerTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TriggerTypeImpl extends EObjectImpl implements TriggerType {
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
	protected TriggerTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.TRIGGER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ProcessPackage.TRIGGER_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConstraintType> getConstraint() {
		return getGroup().list(ProcessPackage.Literals.TRIGGER_TYPE__CONSTRAINT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventFiltersType> getEventFilters() {
		return getGroup().list(ProcessPackage.Literals.TRIGGER_TYPE__EVENT_FILTERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MappingType> getMapping() {
		return getGroup().list(ProcessPackage.Literals.TRIGGER_TYPE__MAPPING);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.TRIGGER_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.TRIGGER_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TRIGGER_TYPE__CONSTRAINT:
				return ((InternalEList<?>)getConstraint()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TRIGGER_TYPE__EVENT_FILTERS:
				return ((InternalEList<?>)getEventFilters()).basicRemove(otherEnd, msgs);
			case ProcessPackage.TRIGGER_TYPE__MAPPING:
				return ((InternalEList<?>)getMapping()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.TRIGGER_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ProcessPackage.TRIGGER_TYPE__CONSTRAINT:
				return getConstraint();
			case ProcessPackage.TRIGGER_TYPE__EVENT_FILTERS:
				return getEventFilters();
			case ProcessPackage.TRIGGER_TYPE__MAPPING:
				return getMapping();
			case ProcessPackage.TRIGGER_TYPE__TYPE:
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
			case ProcessPackage.TRIGGER_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ProcessPackage.TRIGGER_TYPE__CONSTRAINT:
				getConstraint().clear();
				getConstraint().addAll((Collection<? extends ConstraintType>)newValue);
				return;
			case ProcessPackage.TRIGGER_TYPE__EVENT_FILTERS:
				getEventFilters().clear();
				getEventFilters().addAll((Collection<? extends EventFiltersType>)newValue);
				return;
			case ProcessPackage.TRIGGER_TYPE__MAPPING:
				getMapping().clear();
				getMapping().addAll((Collection<? extends MappingType>)newValue);
				return;
			case ProcessPackage.TRIGGER_TYPE__TYPE:
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
			case ProcessPackage.TRIGGER_TYPE__GROUP:
				getGroup().clear();
				return;
			case ProcessPackage.TRIGGER_TYPE__CONSTRAINT:
				getConstraint().clear();
				return;
			case ProcessPackage.TRIGGER_TYPE__EVENT_FILTERS:
				getEventFilters().clear();
				return;
			case ProcessPackage.TRIGGER_TYPE__MAPPING:
				getMapping().clear();
				return;
			case ProcessPackage.TRIGGER_TYPE__TYPE:
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
			case ProcessPackage.TRIGGER_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ProcessPackage.TRIGGER_TYPE__CONSTRAINT:
				return !getConstraint().isEmpty();
			case ProcessPackage.TRIGGER_TYPE__EVENT_FILTERS:
				return !getEventFilters().isEmpty();
			case ProcessPackage.TRIGGER_TYPE__MAPPING:
				return !getMapping().isEmpty();
			case ProcessPackage.TRIGGER_TYPE__TYPE:
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
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //TriggerTypeImpl
