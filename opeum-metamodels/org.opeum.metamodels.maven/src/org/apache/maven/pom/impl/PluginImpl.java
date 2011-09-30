/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom.impl;

import org.apache.maven.pom.ConfigurationType2;
import org.apache.maven.pom.DependenciesType3;
import org.apache.maven.pom.ExecutionsType;
import org.apache.maven.pom.GoalsType;
import org.apache.maven.pom.POMPackage;
import org.apache.maven.pom.Plugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plugin</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#getArtifactId <em>Artifact Id</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#isExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#getExecutions <em>Executions</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#getGoals <em>Goals</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#getInherited <em>Inherited</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.PluginImpl#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PluginImpl extends EObjectImpl implements Plugin {
	/**
	 * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = "org.apache.maven.plugins";

	/**
	 * The cached value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected String groupId = GROUP_ID_EDEFAULT;

	/**
	 * This is true if the Group Id attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean groupIdESet;

	/**
	 * The default value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected String artifactId = ARTIFACT_ID_EDEFAULT;

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
	 * The default value of the '{@link #isExtensions() <em>Extensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtensions()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTENSIONS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExtensions() <em>Extensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtensions()
	 * @generated
	 * @ordered
	 */
	protected boolean extensions = EXTENSIONS_EDEFAULT;

	/**
	 * This is true if the Extensions attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean extensionsESet;

	/**
	 * The cached value of the '{@link #getExecutions() <em>Executions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutions()
	 * @generated
	 * @ordered
	 */
	protected ExecutionsType executions;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected DependenciesType3 dependencies;

	/**
	 * The cached value of the '{@link #getGoals() <em>Goals</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGoals()
	 * @generated
	 * @ordered
	 */
	protected GoalsType goals;

	/**
	 * The default value of the '{@link #getInherited() <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInherited()
	 * @generated
	 * @ordered
	 */
	protected static final String INHERITED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInherited() <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInherited()
	 * @generated
	 * @ordered
	 */
	protected String inherited = INHERITED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConfiguration() <em>Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfiguration()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationType2 configuration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PluginImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return POMPackage.Literals.PLUGIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupId(String newGroupId) {
		String oldGroupId = groupId;
		groupId = newGroupId;
		boolean oldGroupIdESet = groupIdESet;
		groupIdESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__GROUP_ID, oldGroupId, groupId, !oldGroupIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetGroupId() {
		String oldGroupId = groupId;
		boolean oldGroupIdESet = groupIdESet;
		groupId = GROUP_ID_EDEFAULT;
		groupIdESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, POMPackage.PLUGIN__GROUP_ID, oldGroupId, GROUP_ID_EDEFAULT, oldGroupIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetGroupId() {
		return groupIdESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArtifactId(String newArtifactId) {
		String oldArtifactId = artifactId;
		artifactId = newArtifactId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__ARTIFACT_ID, oldArtifactId, artifactId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExtensions() {
		return extensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtensions(boolean newExtensions) {
		boolean oldExtensions = extensions;
		extensions = newExtensions;
		boolean oldExtensionsESet = extensionsESet;
		extensionsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__EXTENSIONS, oldExtensions, extensions, !oldExtensionsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExtensions() {
		boolean oldExtensions = extensions;
		boolean oldExtensionsESet = extensionsESet;
		extensions = EXTENSIONS_EDEFAULT;
		extensionsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, POMPackage.PLUGIN__EXTENSIONS, oldExtensions, EXTENSIONS_EDEFAULT, oldExtensionsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExtensions() {
		return extensionsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionsType getExecutions() {
		return executions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecutions(ExecutionsType newExecutions, NotificationChain msgs) {
		ExecutionsType oldExecutions = executions;
		executions = newExecutions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__EXECUTIONS, oldExecutions, newExecutions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutions(ExecutionsType newExecutions) {
		if (newExecutions != executions) {
			NotificationChain msgs = null;
			if (executions != null)
				msgs = ((InternalEObject)executions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PLUGIN__EXECUTIONS, null, msgs);
			if (newExecutions != null)
				msgs = ((InternalEObject)newExecutions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PLUGIN__EXECUTIONS, null, msgs);
			msgs = basicSetExecutions(newExecutions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__EXECUTIONS, newExecutions, newExecutions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DependenciesType3 getDependencies() {
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDependencies(DependenciesType3 newDependencies, NotificationChain msgs) {
		DependenciesType3 oldDependencies = dependencies;
		dependencies = newDependencies;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__DEPENDENCIES, oldDependencies, newDependencies);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependencies(DependenciesType3 newDependencies) {
		if (newDependencies != dependencies) {
			NotificationChain msgs = null;
			if (dependencies != null)
				msgs = ((InternalEObject)dependencies).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PLUGIN__DEPENDENCIES, null, msgs);
			if (newDependencies != null)
				msgs = ((InternalEObject)newDependencies).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PLUGIN__DEPENDENCIES, null, msgs);
			msgs = basicSetDependencies(newDependencies, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__DEPENDENCIES, newDependencies, newDependencies));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GoalsType getGoals() {
		return goals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGoals(GoalsType newGoals, NotificationChain msgs) {
		GoalsType oldGoals = goals;
		goals = newGoals;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__GOALS, oldGoals, newGoals);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGoals(GoalsType newGoals) {
		if (newGoals != goals) {
			NotificationChain msgs = null;
			if (goals != null)
				msgs = ((InternalEObject)goals).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PLUGIN__GOALS, null, msgs);
			if (newGoals != null)
				msgs = ((InternalEObject)newGoals).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PLUGIN__GOALS, null, msgs);
			msgs = basicSetGoals(newGoals, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__GOALS, newGoals, newGoals));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInherited() {
		return inherited;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInherited(String newInherited) {
		String oldInherited = inherited;
		inherited = newInherited;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__INHERITED, oldInherited, inherited));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationType2 getConfiguration() {
		return configuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConfiguration(ConfigurationType2 newConfiguration, NotificationChain msgs) {
		ConfigurationType2 oldConfiguration = configuration;
		configuration = newConfiguration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__CONFIGURATION, oldConfiguration, newConfiguration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfiguration(ConfigurationType2 newConfiguration) {
		if (newConfiguration != configuration) {
			NotificationChain msgs = null;
			if (configuration != null)
				msgs = ((InternalEObject)configuration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PLUGIN__CONFIGURATION, null, msgs);
			if (newConfiguration != null)
				msgs = ((InternalEObject)newConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PLUGIN__CONFIGURATION, null, msgs);
			msgs = basicSetConfiguration(newConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PLUGIN__CONFIGURATION, newConfiguration, newConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case POMPackage.PLUGIN__EXECUTIONS:
				return basicSetExecutions(null, msgs);
			case POMPackage.PLUGIN__DEPENDENCIES:
				return basicSetDependencies(null, msgs);
			case POMPackage.PLUGIN__GOALS:
				return basicSetGoals(null, msgs);
			case POMPackage.PLUGIN__CONFIGURATION:
				return basicSetConfiguration(null, msgs);
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
			case POMPackage.PLUGIN__GROUP_ID:
				return getGroupId();
			case POMPackage.PLUGIN__ARTIFACT_ID:
				return getArtifactId();
			case POMPackage.PLUGIN__VERSION:
				return getVersion();
			case POMPackage.PLUGIN__EXTENSIONS:
				return isExtensions();
			case POMPackage.PLUGIN__EXECUTIONS:
				return getExecutions();
			case POMPackage.PLUGIN__DEPENDENCIES:
				return getDependencies();
			case POMPackage.PLUGIN__GOALS:
				return getGoals();
			case POMPackage.PLUGIN__INHERITED:
				return getInherited();
			case POMPackage.PLUGIN__CONFIGURATION:
				return getConfiguration();
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
			case POMPackage.PLUGIN__GROUP_ID:
				setGroupId((String)newValue);
				return;
			case POMPackage.PLUGIN__ARTIFACT_ID:
				setArtifactId((String)newValue);
				return;
			case POMPackage.PLUGIN__VERSION:
				setVersion((String)newValue);
				return;
			case POMPackage.PLUGIN__EXTENSIONS:
				setExtensions((Boolean)newValue);
				return;
			case POMPackage.PLUGIN__EXECUTIONS:
				setExecutions((ExecutionsType)newValue);
				return;
			case POMPackage.PLUGIN__DEPENDENCIES:
				setDependencies((DependenciesType3)newValue);
				return;
			case POMPackage.PLUGIN__GOALS:
				setGoals((GoalsType)newValue);
				return;
			case POMPackage.PLUGIN__INHERITED:
				setInherited((String)newValue);
				return;
			case POMPackage.PLUGIN__CONFIGURATION:
				setConfiguration((ConfigurationType2)newValue);
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
			case POMPackage.PLUGIN__GROUP_ID:
				unsetGroupId();
				return;
			case POMPackage.PLUGIN__ARTIFACT_ID:
				setArtifactId(ARTIFACT_ID_EDEFAULT);
				return;
			case POMPackage.PLUGIN__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case POMPackage.PLUGIN__EXTENSIONS:
				unsetExtensions();
				return;
			case POMPackage.PLUGIN__EXECUTIONS:
				setExecutions((ExecutionsType)null);
				return;
			case POMPackage.PLUGIN__DEPENDENCIES:
				setDependencies((DependenciesType3)null);
				return;
			case POMPackage.PLUGIN__GOALS:
				setGoals((GoalsType)null);
				return;
			case POMPackage.PLUGIN__INHERITED:
				setInherited(INHERITED_EDEFAULT);
				return;
			case POMPackage.PLUGIN__CONFIGURATION:
				setConfiguration((ConfigurationType2)null);
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
			case POMPackage.PLUGIN__GROUP_ID:
				return isSetGroupId();
			case POMPackage.PLUGIN__ARTIFACT_ID:
				return ARTIFACT_ID_EDEFAULT == null ? artifactId != null : !ARTIFACT_ID_EDEFAULT.equals(artifactId);
			case POMPackage.PLUGIN__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case POMPackage.PLUGIN__EXTENSIONS:
				return isSetExtensions();
			case POMPackage.PLUGIN__EXECUTIONS:
				return executions != null;
			case POMPackage.PLUGIN__DEPENDENCIES:
				return dependencies != null;
			case POMPackage.PLUGIN__GOALS:
				return goals != null;
			case POMPackage.PLUGIN__INHERITED:
				return INHERITED_EDEFAULT == null ? inherited != null : !INHERITED_EDEFAULT.equals(inherited);
			case POMPackage.PLUGIN__CONFIGURATION:
				return configuration != null;
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
		result.append(" (groupId: ");
		if (groupIdESet) result.append(groupId); else result.append("<unset>");
		result.append(", artifactId: ");
		result.append(artifactId);
		result.append(", version: ");
		result.append(version);
		result.append(", extensions: ");
		if (extensionsESet) result.append(extensions); else result.append("<unset>");
		result.append(", inherited: ");
		result.append(inherited);
		result.append(')');
		return result.toString();
	}

} //PluginImpl
