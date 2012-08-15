/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom.impl;

import org.apache.maven.pom.Activation;
import org.apache.maven.pom.BuildBase;
import org.apache.maven.pom.DependenciesType2;
import org.apache.maven.pom.DependencyManagement;
import org.apache.maven.pom.DistributionManagement;
import org.apache.maven.pom.ModulesType1;
import org.apache.maven.pom.POMPackage;
import org.apache.maven.pom.PluginRepositoriesType1;
import org.apache.maven.pom.Profile;
import org.apache.maven.pom.PropertiesType1;
import org.apache.maven.pom.Reporting;
import org.apache.maven.pom.ReportsType2;
import org.apache.maven.pom.RepositoriesType1;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getActivation <em>Activation</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getBuild <em>Build</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getModules <em>Modules</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getPluginRepositories <em>Plugin Repositories</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getReports <em>Reports</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getReporting <em>Reporting</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getDependencyManagement <em>Dependency Management</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getDistributionManagement <em>Distribution Management</em>}</li>
 *   <li>{@link org.apache.maven.pom.impl.ProfileImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProfileImpl extends EObjectImpl implements Profile {
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
	 * The cached value of the '{@link #getActivation() <em>Activation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivation()
	 * @generated
	 * @ordered
	 */
	protected Activation activation;

	/**
	 * The cached value of the '{@link #getBuild() <em>Build</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuild()
	 * @generated
	 * @ordered
	 */
	protected BuildBase build;

	/**
	 * The cached value of the '{@link #getModules() <em>Modules</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModules()
	 * @generated
	 * @ordered
	 */
	protected ModulesType1 modules;

	/**
	 * The cached value of the '{@link #getRepositories() <em>Repositories</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepositories()
	 * @generated
	 * @ordered
	 */
	protected RepositoriesType1 repositories;

	/**
	 * The cached value of the '{@link #getPluginRepositories() <em>Plugin Repositories</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginRepositories()
	 * @generated
	 * @ordered
	 */
	protected PluginRepositoriesType1 pluginRepositories;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected DependenciesType2 dependencies;

	/**
	 * The cached value of the '{@link #getReports() <em>Reports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReports()
	 * @generated
	 * @ordered
	 */
	protected ReportsType2 reports;

	/**
	 * The cached value of the '{@link #getReporting() <em>Reporting</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReporting()
	 * @generated
	 * @ordered
	 */
	protected Reporting reporting;

	/**
	 * The cached value of the '{@link #getDependencyManagement() <em>Dependency Management</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencyManagement()
	 * @generated
	 * @ordered
	 */
	protected DependencyManagement dependencyManagement;

	/**
	 * The cached value of the '{@link #getDistributionManagement() <em>Distribution Management</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDistributionManagement()
	 * @generated
	 * @ordered
	 */
	protected DistributionManagement distributionManagement;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected PropertiesType1 properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return POMPackage.Literals.PROFILE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activation getActivation() {
		return activation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivation(Activation newActivation, NotificationChain msgs) {
		Activation oldActivation = activation;
		activation = newActivation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__ACTIVATION, oldActivation, newActivation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivation(Activation newActivation) {
		if (newActivation != activation) {
			NotificationChain msgs = null;
			if (activation != null)
				msgs = ((InternalEObject)activation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__ACTIVATION, null, msgs);
			if (newActivation != null)
				msgs = ((InternalEObject)newActivation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__ACTIVATION, null, msgs);
			msgs = basicSetActivation(newActivation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__ACTIVATION, newActivation, newActivation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuildBase getBuild() {
		return build;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBuild(BuildBase newBuild, NotificationChain msgs) {
		BuildBase oldBuild = build;
		build = newBuild;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__BUILD, oldBuild, newBuild);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBuild(BuildBase newBuild) {
		if (newBuild != build) {
			NotificationChain msgs = null;
			if (build != null)
				msgs = ((InternalEObject)build).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__BUILD, null, msgs);
			if (newBuild != null)
				msgs = ((InternalEObject)newBuild).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__BUILD, null, msgs);
			msgs = basicSetBuild(newBuild, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__BUILD, newBuild, newBuild));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModulesType1 getModules() {
		return modules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModules(ModulesType1 newModules, NotificationChain msgs) {
		ModulesType1 oldModules = modules;
		modules = newModules;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__MODULES, oldModules, newModules);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModules(ModulesType1 newModules) {
		if (newModules != modules) {
			NotificationChain msgs = null;
			if (modules != null)
				msgs = ((InternalEObject)modules).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__MODULES, null, msgs);
			if (newModules != null)
				msgs = ((InternalEObject)newModules).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__MODULES, null, msgs);
			msgs = basicSetModules(newModules, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__MODULES, newModules, newModules));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepositoriesType1 getRepositories() {
		return repositories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRepositories(RepositoriesType1 newRepositories, NotificationChain msgs) {
		RepositoriesType1 oldRepositories = repositories;
		repositories = newRepositories;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__REPOSITORIES, oldRepositories, newRepositories);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepositories(RepositoriesType1 newRepositories) {
		if (newRepositories != repositories) {
			NotificationChain msgs = null;
			if (repositories != null)
				msgs = ((InternalEObject)repositories).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__REPOSITORIES, null, msgs);
			if (newRepositories != null)
				msgs = ((InternalEObject)newRepositories).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__REPOSITORIES, null, msgs);
			msgs = basicSetRepositories(newRepositories, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__REPOSITORIES, newRepositories, newRepositories));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PluginRepositoriesType1 getPluginRepositories() {
		return pluginRepositories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPluginRepositories(PluginRepositoriesType1 newPluginRepositories, NotificationChain msgs) {
		PluginRepositoriesType1 oldPluginRepositories = pluginRepositories;
		pluginRepositories = newPluginRepositories;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__PLUGIN_REPOSITORIES, oldPluginRepositories, newPluginRepositories);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPluginRepositories(PluginRepositoriesType1 newPluginRepositories) {
		if (newPluginRepositories != pluginRepositories) {
			NotificationChain msgs = null;
			if (pluginRepositories != null)
				msgs = ((InternalEObject)pluginRepositories).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__PLUGIN_REPOSITORIES, null, msgs);
			if (newPluginRepositories != null)
				msgs = ((InternalEObject)newPluginRepositories).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__PLUGIN_REPOSITORIES, null, msgs);
			msgs = basicSetPluginRepositories(newPluginRepositories, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__PLUGIN_REPOSITORIES, newPluginRepositories, newPluginRepositories));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DependenciesType2 getDependencies() {
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDependencies(DependenciesType2 newDependencies, NotificationChain msgs) {
		DependenciesType2 oldDependencies = dependencies;
		dependencies = newDependencies;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__DEPENDENCIES, oldDependencies, newDependencies);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependencies(DependenciesType2 newDependencies) {
		if (newDependencies != dependencies) {
			NotificationChain msgs = null;
			if (dependencies != null)
				msgs = ((InternalEObject)dependencies).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__DEPENDENCIES, null, msgs);
			if (newDependencies != null)
				msgs = ((InternalEObject)newDependencies).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__DEPENDENCIES, null, msgs);
			msgs = basicSetDependencies(newDependencies, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__DEPENDENCIES, newDependencies, newDependencies));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReportsType2 getReports() {
		return reports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReports(ReportsType2 newReports, NotificationChain msgs) {
		ReportsType2 oldReports = reports;
		reports = newReports;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__REPORTS, oldReports, newReports);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReports(ReportsType2 newReports) {
		if (newReports != reports) {
			NotificationChain msgs = null;
			if (reports != null)
				msgs = ((InternalEObject)reports).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__REPORTS, null, msgs);
			if (newReports != null)
				msgs = ((InternalEObject)newReports).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__REPORTS, null, msgs);
			msgs = basicSetReports(newReports, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__REPORTS, newReports, newReports));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reporting getReporting() {
		return reporting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReporting(Reporting newReporting, NotificationChain msgs) {
		Reporting oldReporting = reporting;
		reporting = newReporting;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__REPORTING, oldReporting, newReporting);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReporting(Reporting newReporting) {
		if (newReporting != reporting) {
			NotificationChain msgs = null;
			if (reporting != null)
				msgs = ((InternalEObject)reporting).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__REPORTING, null, msgs);
			if (newReporting != null)
				msgs = ((InternalEObject)newReporting).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__REPORTING, null, msgs);
			msgs = basicSetReporting(newReporting, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__REPORTING, newReporting, newReporting));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DependencyManagement getDependencyManagement() {
		return dependencyManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDependencyManagement(DependencyManagement newDependencyManagement, NotificationChain msgs) {
		DependencyManagement oldDependencyManagement = dependencyManagement;
		dependencyManagement = newDependencyManagement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__DEPENDENCY_MANAGEMENT, oldDependencyManagement, newDependencyManagement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDependencyManagement(DependencyManagement newDependencyManagement) {
		if (newDependencyManagement != dependencyManagement) {
			NotificationChain msgs = null;
			if (dependencyManagement != null)
				msgs = ((InternalEObject)dependencyManagement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__DEPENDENCY_MANAGEMENT, null, msgs);
			if (newDependencyManagement != null)
				msgs = ((InternalEObject)newDependencyManagement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__DEPENDENCY_MANAGEMENT, null, msgs);
			msgs = basicSetDependencyManagement(newDependencyManagement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__DEPENDENCY_MANAGEMENT, newDependencyManagement, newDependencyManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DistributionManagement getDistributionManagement() {
		return distributionManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDistributionManagement(DistributionManagement newDistributionManagement, NotificationChain msgs) {
		DistributionManagement oldDistributionManagement = distributionManagement;
		distributionManagement = newDistributionManagement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT, oldDistributionManagement, newDistributionManagement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDistributionManagement(DistributionManagement newDistributionManagement) {
		if (newDistributionManagement != distributionManagement) {
			NotificationChain msgs = null;
			if (distributionManagement != null)
				msgs = ((InternalEObject)distributionManagement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT, null, msgs);
			if (newDistributionManagement != null)
				msgs = ((InternalEObject)newDistributionManagement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT, null, msgs);
			msgs = basicSetDistributionManagement(newDistributionManagement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT, newDistributionManagement, newDistributionManagement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertiesType1 getProperties() {
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProperties(PropertiesType1 newProperties, NotificationChain msgs) {
		PropertiesType1 oldProperties = properties;
		properties = newProperties;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__PROPERTIES, oldProperties, newProperties);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperties(PropertiesType1 newProperties) {
		if (newProperties != properties) {
			NotificationChain msgs = null;
			if (properties != null)
				msgs = ((InternalEObject)properties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__PROPERTIES, null, msgs);
			if (newProperties != null)
				msgs = ((InternalEObject)newProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - POMPackage.PROFILE__PROPERTIES, null, msgs);
			msgs = basicSetProperties(newProperties, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, POMPackage.PROFILE__PROPERTIES, newProperties, newProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case POMPackage.PROFILE__ACTIVATION:
				return basicSetActivation(null, msgs);
			case POMPackage.PROFILE__BUILD:
				return basicSetBuild(null, msgs);
			case POMPackage.PROFILE__MODULES:
				return basicSetModules(null, msgs);
			case POMPackage.PROFILE__REPOSITORIES:
				return basicSetRepositories(null, msgs);
			case POMPackage.PROFILE__PLUGIN_REPOSITORIES:
				return basicSetPluginRepositories(null, msgs);
			case POMPackage.PROFILE__DEPENDENCIES:
				return basicSetDependencies(null, msgs);
			case POMPackage.PROFILE__REPORTS:
				return basicSetReports(null, msgs);
			case POMPackage.PROFILE__REPORTING:
				return basicSetReporting(null, msgs);
			case POMPackage.PROFILE__DEPENDENCY_MANAGEMENT:
				return basicSetDependencyManagement(null, msgs);
			case POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT:
				return basicSetDistributionManagement(null, msgs);
			case POMPackage.PROFILE__PROPERTIES:
				return basicSetProperties(null, msgs);
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
			case POMPackage.PROFILE__ID:
				return getId();
			case POMPackage.PROFILE__ACTIVATION:
				return getActivation();
			case POMPackage.PROFILE__BUILD:
				return getBuild();
			case POMPackage.PROFILE__MODULES:
				return getModules();
			case POMPackage.PROFILE__REPOSITORIES:
				return getRepositories();
			case POMPackage.PROFILE__PLUGIN_REPOSITORIES:
				return getPluginRepositories();
			case POMPackage.PROFILE__DEPENDENCIES:
				return getDependencies();
			case POMPackage.PROFILE__REPORTS:
				return getReports();
			case POMPackage.PROFILE__REPORTING:
				return getReporting();
			case POMPackage.PROFILE__DEPENDENCY_MANAGEMENT:
				return getDependencyManagement();
			case POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT:
				return getDistributionManagement();
			case POMPackage.PROFILE__PROPERTIES:
				return getProperties();
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
			case POMPackage.PROFILE__ID:
				setId((String)newValue);
				return;
			case POMPackage.PROFILE__ACTIVATION:
				setActivation((Activation)newValue);
				return;
			case POMPackage.PROFILE__BUILD:
				setBuild((BuildBase)newValue);
				return;
			case POMPackage.PROFILE__MODULES:
				setModules((ModulesType1)newValue);
				return;
			case POMPackage.PROFILE__REPOSITORIES:
				setRepositories((RepositoriesType1)newValue);
				return;
			case POMPackage.PROFILE__PLUGIN_REPOSITORIES:
				setPluginRepositories((PluginRepositoriesType1)newValue);
				return;
			case POMPackage.PROFILE__DEPENDENCIES:
				setDependencies((DependenciesType2)newValue);
				return;
			case POMPackage.PROFILE__REPORTS:
				setReports((ReportsType2)newValue);
				return;
			case POMPackage.PROFILE__REPORTING:
				setReporting((Reporting)newValue);
				return;
			case POMPackage.PROFILE__DEPENDENCY_MANAGEMENT:
				setDependencyManagement((DependencyManagement)newValue);
				return;
			case POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT:
				setDistributionManagement((DistributionManagement)newValue);
				return;
			case POMPackage.PROFILE__PROPERTIES:
				setProperties((PropertiesType1)newValue);
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
			case POMPackage.PROFILE__ID:
				setId(ID_EDEFAULT);
				return;
			case POMPackage.PROFILE__ACTIVATION:
				setActivation((Activation)null);
				return;
			case POMPackage.PROFILE__BUILD:
				setBuild((BuildBase)null);
				return;
			case POMPackage.PROFILE__MODULES:
				setModules((ModulesType1)null);
				return;
			case POMPackage.PROFILE__REPOSITORIES:
				setRepositories((RepositoriesType1)null);
				return;
			case POMPackage.PROFILE__PLUGIN_REPOSITORIES:
				setPluginRepositories((PluginRepositoriesType1)null);
				return;
			case POMPackage.PROFILE__DEPENDENCIES:
				setDependencies((DependenciesType2)null);
				return;
			case POMPackage.PROFILE__REPORTS:
				setReports((ReportsType2)null);
				return;
			case POMPackage.PROFILE__REPORTING:
				setReporting((Reporting)null);
				return;
			case POMPackage.PROFILE__DEPENDENCY_MANAGEMENT:
				setDependencyManagement((DependencyManagement)null);
				return;
			case POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT:
				setDistributionManagement((DistributionManagement)null);
				return;
			case POMPackage.PROFILE__PROPERTIES:
				setProperties((PropertiesType1)null);
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
			case POMPackage.PROFILE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case POMPackage.PROFILE__ACTIVATION:
				return activation != null;
			case POMPackage.PROFILE__BUILD:
				return build != null;
			case POMPackage.PROFILE__MODULES:
				return modules != null;
			case POMPackage.PROFILE__REPOSITORIES:
				return repositories != null;
			case POMPackage.PROFILE__PLUGIN_REPOSITORIES:
				return pluginRepositories != null;
			case POMPackage.PROFILE__DEPENDENCIES:
				return dependencies != null;
			case POMPackage.PROFILE__REPORTS:
				return reports != null;
			case POMPackage.PROFILE__REPORTING:
				return reporting != null;
			case POMPackage.PROFILE__DEPENDENCY_MANAGEMENT:
				return dependencyManagement != null;
			case POMPackage.PROFILE__DISTRIBUTION_MANAGEMENT:
				return distributionManagement != null;
			case POMPackage.PROFILE__PROPERTIES:
				return properties != null;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ProfileImpl
