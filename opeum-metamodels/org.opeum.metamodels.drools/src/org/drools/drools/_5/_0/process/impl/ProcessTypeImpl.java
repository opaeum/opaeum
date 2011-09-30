/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import java.util.Collection;

import org.drools.drools._5._0.process.ConnectionsType;
import org.drools.drools._5._0.process.HeaderType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.ProcessPackage;
import org.drools.drools._5._0.process.ProcessType;
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
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getRouterLayout <em>Router Layout</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessTypeImpl extends EObjectImpl implements ProcessType {
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
	 * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected String packageName = PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRouterLayout() <em>Router Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRouterLayout()
	 * @generated
	 * @ordered
	 */
	protected static final String ROUTER_LAYOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRouterLayout() <em>Router Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRouterLayout()
	 * @generated
	 * @ordered
	 */
	protected String routerLayout = ROUTER_LAYOUT_EDEFAULT;

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
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.PROCESS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ProcessPackage.PROCESS_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HeaderType> getHeader() {
		return getGroup().list(ProcessPackage.Literals.PROCESS_TYPE__HEADER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NodesType> getNodes() {
		return getGroup().list(ProcessPackage.Literals.PROCESS_TYPE__NODES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectionsType> getConnections() {
		return getGroup().list(ProcessPackage.Literals.PROCESS_TYPE__CONNECTIONS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageName(String newPackageName) {
		String oldPackageName = packageName;
		packageName = newPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_TYPE__PACKAGE_NAME, oldPackageName, packageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRouterLayout() {
		return routerLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRouterLayout(String newRouterLayout) {
		String oldRouterLayout = routerLayout;
		routerLayout = newRouterLayout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_TYPE__ROUTER_LAYOUT, oldRouterLayout, routerLayout));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.PROCESS_TYPE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.PROCESS_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ProcessPackage.PROCESS_TYPE__HEADER:
				return ((InternalEList<?>)getHeader()).basicRemove(otherEnd, msgs);
			case ProcessPackage.PROCESS_TYPE__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case ProcessPackage.PROCESS_TYPE__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.PROCESS_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ProcessPackage.PROCESS_TYPE__HEADER:
				return getHeader();
			case ProcessPackage.PROCESS_TYPE__NODES:
				return getNodes();
			case ProcessPackage.PROCESS_TYPE__CONNECTIONS:
				return getConnections();
			case ProcessPackage.PROCESS_TYPE__ID:
				return getId();
			case ProcessPackage.PROCESS_TYPE__NAME:
				return getName();
			case ProcessPackage.PROCESS_TYPE__PACKAGE_NAME:
				return getPackageName();
			case ProcessPackage.PROCESS_TYPE__ROUTER_LAYOUT:
				return getRouterLayout();
			case ProcessPackage.PROCESS_TYPE__TYPE:
				return getType();
			case ProcessPackage.PROCESS_TYPE__VERSION:
				return getVersion();
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
			case ProcessPackage.PROCESS_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__HEADER:
				getHeader().clear();
				getHeader().addAll((Collection<? extends HeaderType>)newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__NODES:
				getNodes().clear();
				getNodes().addAll((Collection<? extends NodesType>)newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends ConnectionsType>)newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__ID:
				setId((String)newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__NAME:
				setName((String)newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__PACKAGE_NAME:
				setPackageName((String)newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__ROUTER_LAYOUT:
				setRouterLayout((String)newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__TYPE:
				setType((String)newValue);
				return;
			case ProcessPackage.PROCESS_TYPE__VERSION:
				setVersion((String)newValue);
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
			case ProcessPackage.PROCESS_TYPE__GROUP:
				getGroup().clear();
				return;
			case ProcessPackage.PROCESS_TYPE__HEADER:
				getHeader().clear();
				return;
			case ProcessPackage.PROCESS_TYPE__NODES:
				getNodes().clear();
				return;
			case ProcessPackage.PROCESS_TYPE__CONNECTIONS:
				getConnections().clear();
				return;
			case ProcessPackage.PROCESS_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case ProcessPackage.PROCESS_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProcessPackage.PROCESS_TYPE__PACKAGE_NAME:
				setPackageName(PACKAGE_NAME_EDEFAULT);
				return;
			case ProcessPackage.PROCESS_TYPE__ROUTER_LAYOUT:
				setRouterLayout(ROUTER_LAYOUT_EDEFAULT);
				return;
			case ProcessPackage.PROCESS_TYPE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ProcessPackage.PROCESS_TYPE__VERSION:
				setVersion(VERSION_EDEFAULT);
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
			case ProcessPackage.PROCESS_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ProcessPackage.PROCESS_TYPE__HEADER:
				return !getHeader().isEmpty();
			case ProcessPackage.PROCESS_TYPE__NODES:
				return !getNodes().isEmpty();
			case ProcessPackage.PROCESS_TYPE__CONNECTIONS:
				return !getConnections().isEmpty();
			case ProcessPackage.PROCESS_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ProcessPackage.PROCESS_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProcessPackage.PROCESS_TYPE__PACKAGE_NAME:
				return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
			case ProcessPackage.PROCESS_TYPE__ROUTER_LAYOUT:
				return ROUTER_LAYOUT_EDEFAULT == null ? routerLayout != null : !ROUTER_LAYOUT_EDEFAULT.equals(routerLayout);
			case ProcessPackage.PROCESS_TYPE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ProcessPackage.PROCESS_TYPE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
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
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", packageName: ");
		result.append(packageName);
		result.append(", routerLayout: ");
		result.append(routerLayout);
		result.append(", type: ");
		result.append(type);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //ProcessTypeImpl
