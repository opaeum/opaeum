/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import java.util.Collection;

import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.ForEachType;
import org.drools.drools._5._0.process.InPortsType;
import org.drools.drools._5._0.process.MetaDataType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.OutPortsType;
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
 * An implementation of the model object '<em><b>For Each Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getMetaData <em>Meta Data</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getInPorts <em>In Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getOutPorts <em>Out Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getCollectionExpression <em>Collection Expression</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getVariableName <em>Variable Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getWaitForCompletion <em>Wait For Completion</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getX <em>X</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForEachTypeImpl extends EObjectImpl implements ForEachType {
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
	 * The default value of the '{@link #getCollectionExpression() <em>Collection Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollectionExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String COLLECTION_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCollectionExpression() <em>Collection Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollectionExpression()
	 * @generated
	 * @ordered
	 */
	protected String collectionExpression = COLLECTION_EXPRESSION_EDEFAULT;

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
	 * The default value of the '{@link #getWaitForCompletion() <em>Wait For Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaitForCompletion()
	 * @generated
	 * @ordered
	 */
	protected static final String WAIT_FOR_COMPLETION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWaitForCompletion() <em>Wait For Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaitForCompletion()
	 * @generated
	 * @ordered
	 */
	protected String waitForCompletion = WAIT_FOR_COMPLETION_EDEFAULT;

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
	protected ForEachTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.FOR_EACH_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ProcessPackage.FOR_EACH_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaDataType> getMetaData() {
		return getGroup().list(ProcessPackage.Literals.FOR_EACH_TYPE__META_DATA);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NodesType> getNodes() {
		return getGroup().list(ProcessPackage.Literals.FOR_EACH_TYPE__NODES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectionsType> getConnections() {
		return getGroup().list(ProcessPackage.Literals.FOR_EACH_TYPE__CONNECTIONS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InPortsType> getInPorts() {
		return getGroup().list(ProcessPackage.Literals.FOR_EACH_TYPE__IN_PORTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OutPortsType> getOutPorts() {
		return getGroup().list(ProcessPackage.Literals.FOR_EACH_TYPE__OUT_PORTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCollectionExpression() {
		return collectionExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollectionExpression(String newCollectionExpression) {
		String oldCollectionExpression = collectionExpression;
		collectionExpression = newCollectionExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__COLLECTION_EXPRESSION, oldCollectionExpression, collectionExpression));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__HEIGHT, oldHeight, height));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__VARIABLE_NAME, oldVariableName, variableName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWaitForCompletion() {
		return waitForCompletion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaitForCompletion(String newWaitForCompletion) {
		String oldWaitForCompletion = waitForCompletion;
		waitForCompletion = newWaitForCompletion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__WAIT_FOR_COMPLETION, oldWaitForCompletion, waitForCompletion));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__WIDTH, oldWidth, width));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__X, oldX, x));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FOR_EACH_TYPE__Y, oldY, y));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.FOR_EACH_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ProcessPackage.FOR_EACH_TYPE__META_DATA:
				return ((InternalEList<?>)getMetaData()).basicRemove(otherEnd, msgs);
			case ProcessPackage.FOR_EACH_TYPE__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case ProcessPackage.FOR_EACH_TYPE__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
			case ProcessPackage.FOR_EACH_TYPE__IN_PORTS:
				return ((InternalEList<?>)getInPorts()).basicRemove(otherEnd, msgs);
			case ProcessPackage.FOR_EACH_TYPE__OUT_PORTS:
				return ((InternalEList<?>)getOutPorts()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.FOR_EACH_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ProcessPackage.FOR_EACH_TYPE__META_DATA:
				return getMetaData();
			case ProcessPackage.FOR_EACH_TYPE__NODES:
				return getNodes();
			case ProcessPackage.FOR_EACH_TYPE__CONNECTIONS:
				return getConnections();
			case ProcessPackage.FOR_EACH_TYPE__IN_PORTS:
				return getInPorts();
			case ProcessPackage.FOR_EACH_TYPE__OUT_PORTS:
				return getOutPorts();
			case ProcessPackage.FOR_EACH_TYPE__COLLECTION_EXPRESSION:
				return getCollectionExpression();
			case ProcessPackage.FOR_EACH_TYPE__HEIGHT:
				return getHeight();
			case ProcessPackage.FOR_EACH_TYPE__ID:
				return getId();
			case ProcessPackage.FOR_EACH_TYPE__NAME:
				return getName();
			case ProcessPackage.FOR_EACH_TYPE__VARIABLE_NAME:
				return getVariableName();
			case ProcessPackage.FOR_EACH_TYPE__WAIT_FOR_COMPLETION:
				return getWaitForCompletion();
			case ProcessPackage.FOR_EACH_TYPE__WIDTH:
				return getWidth();
			case ProcessPackage.FOR_EACH_TYPE__X:
				return getX();
			case ProcessPackage.FOR_EACH_TYPE__Y:
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
			case ProcessPackage.FOR_EACH_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__META_DATA:
				getMetaData().clear();
				getMetaData().addAll((Collection<? extends MetaDataType>)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends NodesType>)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends ConnectionsType>)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__IN_PORTS:
				getInPorts().clear();
				getInPorts().addAll((Collection<? extends InPortsType>)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__OUT_PORTS:
				getOutPorts().clear();
				getOutPorts().addAll((Collection<? extends OutPortsType>)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__COLLECTION_EXPRESSION:
				setCollectionExpression((String)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__HEIGHT:
				setHeight((String)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__ID:
				setId((String)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__NAME:
				setName((String)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__VARIABLE_NAME:
				setVariableName((String)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__WAIT_FOR_COMPLETION:
				setWaitForCompletion((String)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__WIDTH:
				setWidth((String)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__X:
				setX((String)newValue);
				return;
			case ProcessPackage.FOR_EACH_TYPE__Y:
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
			case ProcessPackage.FOR_EACH_TYPE__GROUP:
				getGroup().clear();
				return;
			case ProcessPackage.FOR_EACH_TYPE__META_DATA:
				getMetaData().clear();
				return;
			case ProcessPackage.FOR_EACH_TYPE__NODES:
				getNodes().clear();
				return;
			case ProcessPackage.FOR_EACH_TYPE__CONNECTIONS:
				getConnections().clear();
				return;
			case ProcessPackage.FOR_EACH_TYPE__IN_PORTS:
				getInPorts().clear();
				return;
			case ProcessPackage.FOR_EACH_TYPE__OUT_PORTS:
				getOutPorts().clear();
				return;
			case ProcessPackage.FOR_EACH_TYPE__COLLECTION_EXPRESSION:
				setCollectionExpression(COLLECTION_EXPRESSION_EDEFAULT);
				return;
			case ProcessPackage.FOR_EACH_TYPE__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case ProcessPackage.FOR_EACH_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case ProcessPackage.FOR_EACH_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProcessPackage.FOR_EACH_TYPE__VARIABLE_NAME:
				setVariableName(VARIABLE_NAME_EDEFAULT);
				return;
			case ProcessPackage.FOR_EACH_TYPE__WAIT_FOR_COMPLETION:
				setWaitForCompletion(WAIT_FOR_COMPLETION_EDEFAULT);
				return;
			case ProcessPackage.FOR_EACH_TYPE__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case ProcessPackage.FOR_EACH_TYPE__X:
				setX(X_EDEFAULT);
				return;
			case ProcessPackage.FOR_EACH_TYPE__Y:
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
			case ProcessPackage.FOR_EACH_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ProcessPackage.FOR_EACH_TYPE__META_DATA:
				return !getMetaData().isEmpty();
			case ProcessPackage.FOR_EACH_TYPE__NODES:
				return !getNodes().isEmpty();
			case ProcessPackage.FOR_EACH_TYPE__CONNECTIONS:
				return !getConnections().isEmpty();
			case ProcessPackage.FOR_EACH_TYPE__IN_PORTS:
				return !getInPorts().isEmpty();
			case ProcessPackage.FOR_EACH_TYPE__OUT_PORTS:
				return !getOutPorts().isEmpty();
			case ProcessPackage.FOR_EACH_TYPE__COLLECTION_EXPRESSION:
				return COLLECTION_EXPRESSION_EDEFAULT == null ? collectionExpression != null : !COLLECTION_EXPRESSION_EDEFAULT.equals(collectionExpression);
			case ProcessPackage.FOR_EACH_TYPE__HEIGHT:
				return HEIGHT_EDEFAULT == null ? height != null : !HEIGHT_EDEFAULT.equals(height);
			case ProcessPackage.FOR_EACH_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ProcessPackage.FOR_EACH_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProcessPackage.FOR_EACH_TYPE__VARIABLE_NAME:
				return VARIABLE_NAME_EDEFAULT == null ? variableName != null : !VARIABLE_NAME_EDEFAULT.equals(variableName);
			case ProcessPackage.FOR_EACH_TYPE__WAIT_FOR_COMPLETION:
				return WAIT_FOR_COMPLETION_EDEFAULT == null ? waitForCompletion != null : !WAIT_FOR_COMPLETION_EDEFAULT.equals(waitForCompletion);
			case ProcessPackage.FOR_EACH_TYPE__WIDTH:
				return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT.equals(width);
			case ProcessPackage.FOR_EACH_TYPE__X:
				return X_EDEFAULT == null ? x != null : !X_EDEFAULT.equals(x);
			case ProcessPackage.FOR_EACH_TYPE__Y:
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
		result.append(", collectionExpression: ");
		result.append(collectionExpression);
		result.append(", height: ");
		result.append(height);
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", variableName: ");
		result.append(variableName);
		result.append(", waitForCompletion: ");
		result.append(waitForCompletion);
		result.append(", width: ");
		result.append(width);
		result.append(", x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(')');
		return result.toString();
	}

} //ForEachTypeImpl
