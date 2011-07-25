/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import java.util.Collection;

import org.drools.drools._5._0.process.MappingType;
import org.drools.drools._5._0.process.MetaDataType;
import org.drools.drools._5._0.process.OnEntryType;
import org.drools.drools._5._0.process.OnExitType;
import org.drools.drools._5._0.process.ProcessPackage;
import org.drools.drools._5._0.process.SubProcessType;
import org.drools.drools._5._0.process.TimersType;

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
 * An implementation of the model object '<em><b>Sub Process Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getMetaData <em>Meta Data</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getMapping <em>Mapping</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getTimers <em>Timers</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getOnEntry <em>On Entry</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getOnExit <em>On Exit</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getIndependent <em>Independent</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getProcessId <em>Process Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getWaitForCompletion <em>Wait For Completion</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getX <em>X</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubProcessTypeImpl extends EObjectImpl implements SubProcessType {
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
	 * The default value of the '{@link #getIndependent() <em>Independent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndependent()
	 * @generated
	 * @ordered
	 */
	protected static final String INDEPENDENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIndependent() <em>Independent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndependent()
	 * @generated
	 * @ordered
	 */
	protected String independent = INDEPENDENT_EDEFAULT;

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
	 * The default value of the '{@link #getProcessId() <em>Process Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessId()
	 * @generated
	 * @ordered
	 */
	protected static final String PROCESS_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProcessId() <em>Process Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessId()
	 * @generated
	 * @ordered
	 */
	protected String processId = PROCESS_ID_EDEFAULT;

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
	protected SubProcessTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.SUB_PROCESS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ProcessPackage.SUB_PROCESS_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaDataType> getMetaData() {
		return getGroup().list(ProcessPackage.Literals.SUB_PROCESS_TYPE__META_DATA);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MappingType> getMapping() {
		return getGroup().list(ProcessPackage.Literals.SUB_PROCESS_TYPE__MAPPING);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimersType> getTimers() {
		return getGroup().list(ProcessPackage.Literals.SUB_PROCESS_TYPE__TIMERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OnEntryType> getOnEntry() {
		return getGroup().list(ProcessPackage.Literals.SUB_PROCESS_TYPE__ON_ENTRY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OnExitType> getOnExit() {
		return getGroup().list(ProcessPackage.Literals.SUB_PROCESS_TYPE__ON_EXIT);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__HEIGHT, oldHeight, height));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIndependent() {
		return independent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndependent(String newIndependent) {
		String oldIndependent = independent;
		independent = newIndependent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__INDEPENDENT, oldIndependent, independent));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessId(String newProcessId) {
		String oldProcessId = processId;
		processId = newProcessId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__PROCESS_ID, oldProcessId, processId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__WAIT_FOR_COMPLETION, oldWaitForCompletion, waitForCompletion));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__WIDTH, oldWidth, width));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__X, oldX, x));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.SUB_PROCESS_TYPE__Y, oldY, y));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.SUB_PROCESS_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ProcessPackage.SUB_PROCESS_TYPE__META_DATA:
				return ((InternalEList<?>)getMetaData()).basicRemove(otherEnd, msgs);
			case ProcessPackage.SUB_PROCESS_TYPE__MAPPING:
				return ((InternalEList<?>)getMapping()).basicRemove(otherEnd, msgs);
			case ProcessPackage.SUB_PROCESS_TYPE__TIMERS:
				return ((InternalEList<?>)getTimers()).basicRemove(otherEnd, msgs);
			case ProcessPackage.SUB_PROCESS_TYPE__ON_ENTRY:
				return ((InternalEList<?>)getOnEntry()).basicRemove(otherEnd, msgs);
			case ProcessPackage.SUB_PROCESS_TYPE__ON_EXIT:
				return ((InternalEList<?>)getOnExit()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.SUB_PROCESS_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ProcessPackage.SUB_PROCESS_TYPE__META_DATA:
				return getMetaData();
			case ProcessPackage.SUB_PROCESS_TYPE__MAPPING:
				return getMapping();
			case ProcessPackage.SUB_PROCESS_TYPE__TIMERS:
				return getTimers();
			case ProcessPackage.SUB_PROCESS_TYPE__ON_ENTRY:
				return getOnEntry();
			case ProcessPackage.SUB_PROCESS_TYPE__ON_EXIT:
				return getOnExit();
			case ProcessPackage.SUB_PROCESS_TYPE__HEIGHT:
				return getHeight();
			case ProcessPackage.SUB_PROCESS_TYPE__ID:
				return getId();
			case ProcessPackage.SUB_PROCESS_TYPE__INDEPENDENT:
				return getIndependent();
			case ProcessPackage.SUB_PROCESS_TYPE__NAME:
				return getName();
			case ProcessPackage.SUB_PROCESS_TYPE__PROCESS_ID:
				return getProcessId();
			case ProcessPackage.SUB_PROCESS_TYPE__WAIT_FOR_COMPLETION:
				return getWaitForCompletion();
			case ProcessPackage.SUB_PROCESS_TYPE__WIDTH:
				return getWidth();
			case ProcessPackage.SUB_PROCESS_TYPE__X:
				return getX();
			case ProcessPackage.SUB_PROCESS_TYPE__Y:
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
			case ProcessPackage.SUB_PROCESS_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__META_DATA:
				getMetaData().clear();
				getMetaData().addAll((Collection<? extends MetaDataType>)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__MAPPING:
				getMapping().clear();
				getMapping().addAll((Collection<? extends MappingType>)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__TIMERS:
				getTimers().clear();
				getTimers().addAll((Collection<? extends TimersType>)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__ON_ENTRY:
				getOnEntry().clear();
				getOnEntry().addAll((Collection<? extends OnEntryType>)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__ON_EXIT:
				getOnExit().clear();
				getOnExit().addAll((Collection<? extends OnExitType>)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__HEIGHT:
				setHeight((String)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__ID:
				setId((String)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__INDEPENDENT:
				setIndependent((String)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__NAME:
				setName((String)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__PROCESS_ID:
				setProcessId((String)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__WAIT_FOR_COMPLETION:
				setWaitForCompletion((String)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__WIDTH:
				setWidth((String)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__X:
				setX((String)newValue);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__Y:
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
			case ProcessPackage.SUB_PROCESS_TYPE__GROUP:
				getGroup().clear();
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__META_DATA:
				getMetaData().clear();
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__MAPPING:
				getMapping().clear();
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__TIMERS:
				getTimers().clear();
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__ON_ENTRY:
				getOnEntry().clear();
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__ON_EXIT:
				getOnExit().clear();
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__INDEPENDENT:
				setIndependent(INDEPENDENT_EDEFAULT);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__PROCESS_ID:
				setProcessId(PROCESS_ID_EDEFAULT);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__WAIT_FOR_COMPLETION:
				setWaitForCompletion(WAIT_FOR_COMPLETION_EDEFAULT);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__X:
				setX(X_EDEFAULT);
				return;
			case ProcessPackage.SUB_PROCESS_TYPE__Y:
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
			case ProcessPackage.SUB_PROCESS_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ProcessPackage.SUB_PROCESS_TYPE__META_DATA:
				return !getMetaData().isEmpty();
			case ProcessPackage.SUB_PROCESS_TYPE__MAPPING:
				return !getMapping().isEmpty();
			case ProcessPackage.SUB_PROCESS_TYPE__TIMERS:
				return !getTimers().isEmpty();
			case ProcessPackage.SUB_PROCESS_TYPE__ON_ENTRY:
				return !getOnEntry().isEmpty();
			case ProcessPackage.SUB_PROCESS_TYPE__ON_EXIT:
				return !getOnExit().isEmpty();
			case ProcessPackage.SUB_PROCESS_TYPE__HEIGHT:
				return HEIGHT_EDEFAULT == null ? height != null : !HEIGHT_EDEFAULT.equals(height);
			case ProcessPackage.SUB_PROCESS_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ProcessPackage.SUB_PROCESS_TYPE__INDEPENDENT:
				return INDEPENDENT_EDEFAULT == null ? independent != null : !INDEPENDENT_EDEFAULT.equals(independent);
			case ProcessPackage.SUB_PROCESS_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProcessPackage.SUB_PROCESS_TYPE__PROCESS_ID:
				return PROCESS_ID_EDEFAULT == null ? processId != null : !PROCESS_ID_EDEFAULT.equals(processId);
			case ProcessPackage.SUB_PROCESS_TYPE__WAIT_FOR_COMPLETION:
				return WAIT_FOR_COMPLETION_EDEFAULT == null ? waitForCompletion != null : !WAIT_FOR_COMPLETION_EDEFAULT.equals(waitForCompletion);
			case ProcessPackage.SUB_PROCESS_TYPE__WIDTH:
				return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT.equals(width);
			case ProcessPackage.SUB_PROCESS_TYPE__X:
				return X_EDEFAULT == null ? x != null : !X_EDEFAULT.equals(x);
			case ProcessPackage.SUB_PROCESS_TYPE__Y:
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
		result.append(", independent: ");
		result.append(independent);
		result.append(", name: ");
		result.append(name);
		result.append(", processId: ");
		result.append(processId);
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

} //SubProcessTypeImpl
