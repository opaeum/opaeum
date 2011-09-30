/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import java.util.Collection;

import org.drools.drools._5._0.process.EventFiltersType;
import org.drools.drools._5._0.process.EventNodeType;
import org.drools.drools._5._0.process.MetaDataType;
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
 * An implementation of the model object '<em><b>Event Node Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getMetaData <em>Meta Data</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getEventFilters <em>Event Filters</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getVariableName <em>Variable Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getX <em>X</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventNodeTypeImpl extends EObjectImpl implements EventNodeType {
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
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final String HEIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected String height = HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	 * The default value of the '{@link #getScope() <em>Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScope()
	 * @generated
	 * @ordered
	 */
	protected static final String SCOPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScope() <em>Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScope()
	 * @generated
	 * @ordered
	 */
	protected String scope = SCOPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVariableName() <em>Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableName()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIABLE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariableName() <em>Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableName()
	 * @generated
	 * @ordered
	 */
	protected String variableName = VARIABLE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final String WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected String width = WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final String X_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected String x = X_EDEFAULT;

	/**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final String Y_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected String y = Y_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventNodeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.EVENT_NODE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ProcessPackage.EVENT_NODE_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaDataType> getMetaData() {
		return getGroup().list(ProcessPackage.Literals.EVENT_NODE_TYPE__META_DATA);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventFiltersType> getEventFilters() {
		return getGroup().list(ProcessPackage.Literals.EVENT_NODE_TYPE__EVENT_FILTERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(String newHeight) {
		String oldHeight = height;
		height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EVENT_NODE_TYPE__HEIGHT, oldHeight, height));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EVENT_NODE_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EVENT_NODE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScope(String newScope) {
		String oldScope = scope;
		scope = newScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EVENT_NODE_TYPE__SCOPE, oldScope, scope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVariableName() {
		return variableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariableName(String newVariableName) {
		String oldVariableName = variableName;
		variableName = newVariableName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EVENT_NODE_TYPE__VARIABLE_NAME, oldVariableName, variableName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(String newWidth) {
		String oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EVENT_NODE_TYPE__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getX() {
		return x;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX(String newX) {
		String oldX = x;
		x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EVENT_NODE_TYPE__X, oldX, x));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getY() {
		return y;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setY(String newY) {
		String oldY = y;
		y = newY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.EVENT_NODE_TYPE__Y, oldY, y));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.EVENT_NODE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ProcessPackage.EVENT_NODE_TYPE__META_DATA:
				return ((InternalEList<?>)getMetaData()).basicRemove(otherEnd, msgs);
			case ProcessPackage.EVENT_NODE_TYPE__EVENT_FILTERS:
				return ((InternalEList<?>)getEventFilters()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.EVENT_NODE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ProcessPackage.EVENT_NODE_TYPE__META_DATA:
				return getMetaData();
			case ProcessPackage.EVENT_NODE_TYPE__EVENT_FILTERS:
				return getEventFilters();
			case ProcessPackage.EVENT_NODE_TYPE__HEIGHT:
				return getHeight();
			case ProcessPackage.EVENT_NODE_TYPE__ID:
				return getId();
			case ProcessPackage.EVENT_NODE_TYPE__NAME:
				return getName();
			case ProcessPackage.EVENT_NODE_TYPE__SCOPE:
				return getScope();
			case ProcessPackage.EVENT_NODE_TYPE__VARIABLE_NAME:
				return getVariableName();
			case ProcessPackage.EVENT_NODE_TYPE__WIDTH:
				return getWidth();
			case ProcessPackage.EVENT_NODE_TYPE__X:
				return getX();
			case ProcessPackage.EVENT_NODE_TYPE__Y:
				return getY();
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
			case ProcessPackage.EVENT_NODE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__META_DATA:
				getMetaData().clear();
				getMetaData().addAll((Collection<? extends MetaDataType>)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__EVENT_FILTERS:
				getEventFilters().clear();
				getEventFilters().addAll((Collection<? extends EventFiltersType>)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__HEIGHT:
				setHeight((String)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__ID:
				setId((String)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__NAME:
				setName((String)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__SCOPE:
				setScope((String)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__VARIABLE_NAME:
				setVariableName((String)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__WIDTH:
				setWidth((String)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__X:
				setX((String)newValue);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__Y:
				setY((String)newValue);
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
			case ProcessPackage.EVENT_NODE_TYPE__GROUP:
				getGroup().clear();
				return;
			case ProcessPackage.EVENT_NODE_TYPE__META_DATA:
				getMetaData().clear();
				return;
			case ProcessPackage.EVENT_NODE_TYPE__EVENT_FILTERS:
				getEventFilters().clear();
				return;
			case ProcessPackage.EVENT_NODE_TYPE__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__SCOPE:
				setScope(SCOPE_EDEFAULT);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__VARIABLE_NAME:
				setVariableName(VARIABLE_NAME_EDEFAULT);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__X:
				setX(X_EDEFAULT);
				return;
			case ProcessPackage.EVENT_NODE_TYPE__Y:
				setY(Y_EDEFAULT);
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
			case ProcessPackage.EVENT_NODE_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ProcessPackage.EVENT_NODE_TYPE__META_DATA:
				return !getMetaData().isEmpty();
			case ProcessPackage.EVENT_NODE_TYPE__EVENT_FILTERS:
				return !getEventFilters().isEmpty();
			case ProcessPackage.EVENT_NODE_TYPE__HEIGHT:
				return HEIGHT_EDEFAULT == null ? height != null : !HEIGHT_EDEFAULT.equals(height);
			case ProcessPackage.EVENT_NODE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ProcessPackage.EVENT_NODE_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProcessPackage.EVENT_NODE_TYPE__SCOPE:
				return SCOPE_EDEFAULT == null ? scope != null : !SCOPE_EDEFAULT.equals(scope);
			case ProcessPackage.EVENT_NODE_TYPE__VARIABLE_NAME:
				return VARIABLE_NAME_EDEFAULT == null ? variableName != null : !VARIABLE_NAME_EDEFAULT.equals(variableName);
			case ProcessPackage.EVENT_NODE_TYPE__WIDTH:
				return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT.equals(width);
			case ProcessPackage.EVENT_NODE_TYPE__X:
				return X_EDEFAULT == null ? x != null : !X_EDEFAULT.equals(x);
			case ProcessPackage.EVENT_NODE_TYPE__Y:
				return Y_EDEFAULT == null ? y != null : !Y_EDEFAULT.equals(y);
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
		result.append(", height: ");
		result.append(height);
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", scope: ");
		result.append(scope);
		result.append(", variableName: ");
		result.append(variableName);
		result.append(", width: ");
		result.append(width);
		result.append(", x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(')');
		return result.toString();
	}

} //EventNodeTypeImpl
